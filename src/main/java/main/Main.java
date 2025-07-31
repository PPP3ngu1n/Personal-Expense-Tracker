package main;

import java.util.Scanner;
import java.io.*;
import java.time.LocalDate;

class Main {
    static File receipt = new File("receipt.txt");
    static File keptBalance = new File("Balance.txt");

    static double balance;
    static double UpdatedBalance;

    public static void account(){
        try {
            if (!keptBalance.exists()) {
                balance = 0.0;
            } else {
                Scanner MyReader = new Scanner(keptBalance);
                balance = MyReader.nextDouble();
                MyReader.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

    public static void updateBalance(double balance, double updatedBalance){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(keptBalance));
            File temp = new File("temp.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(temp));

            writer.write(Double.toString(updatedBalance));

            writer.close();
            reader.close();

            if (keptBalance.delete()) {
                if (!temp.renameTo(keptBalance)) {
                    System.out.println("Error renaming file");
                }
            } else {
                System.out.println("Error deleting file");
            }

            Main.balance = updatedBalance;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void add(int amount, String reason){
        LocalDate date = LocalDate.now();
        UpdatedBalance = balance + amount;
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(receipt, true));
            writer.write(date + " -- In -- £" + amount + " - " + reason + "\n");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        updateBalance(balance, UpdatedBalance);
    }

    public static void minus(int amount, String reason){
        LocalDate date = LocalDate.now();
        UpdatedBalance = balance - amount;
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(receipt, true));
            writer.write(date + " -- Out -- £" + amount + " -- " + reason +"\n");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        updateBalance(balance, UpdatedBalance);
    }

    public static void display(){
        System.out.println("Balance: £" + balance);
    }

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to your account!");

        account();

        while(true){
            System.out.println("\nPlease select an option: ");
            System.out.println("1. Add");
            System.out.println("2. Minus");
            System.out.println("3. Display");
            System.out.println("4. Previous Logs");
            System.out.println("5. Exit");

            int choice = input.nextInt();

            switch(choice){
                case 1:
                    System.out.println("Enter amount to add: ");
                    int amount = input.nextInt();
                    input.nextLine();
                    System.out.println("Enter reason for transaction: ");
                    String reason = input.nextLine();
                    add(amount, reason);
                    updateBalance(balance, UpdatedBalance);
                    break;
                case 2:
                    System.out.println("Enter amount to minus: ");
                    amount = input.nextInt();
                    input.nextLine();
                    System.out.println("Enter reason for transaction: ");
                    reason = input.nextLine();
                    minus(amount, reason);
                    updateBalance(balance, UpdatedBalance);
                    break;
                case 3:
                    System.out.println("Your current balance is: ");
                    display();
                    break;
                case 4:
                    System.out.println("What logs would you like to see?");
                    System.out.println("1. All");
                    System.out.println("2. Income");
                    System.out.println("3. Expenditures");
                    int logChoice = input.nextInt();

                    switch(logChoice){
                        case 1:
                            System.out.println("\nAll logs:");
                            try {
                                BufferedReader reader = new BufferedReader(new FileReader(receipt));
                                String line;
                                while ((line = reader.readLine()) != null) {
                                    System.out.println(line);
                                }
                                reader.close();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            break;
                        case 2:
                            System.out.println("\nIncome logs:");
                            try{
                                BufferedReader reader = new BufferedReader(new FileReader(receipt));
                                String line;
                                while ((line = reader.readLine()) != null){
                                    String[] splits = line.split(" -- ");
                                    if (splits[1].equals("In")){
                                        System.out.println(line);
                                    }
                                }
                                reader.close();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            break;
                        case 3:
                            System.out.println("\nExpenditure logs:");
                            try{
                                BufferedReader reader = new BufferedReader(new FileReader(receipt));
                                String line;
                                while ((line = reader.readLine()) != null){
                                    String[] splits = line.split(" -- ");
                                    if (splits[1].equals("Out")){
                                        System.out.println(line);
                                    }
                                }
                                reader.close();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            break;
                    }
                    break;
                case 5:
                    System.out.println("Thank you for using our service!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}