package main;

import java.util.Scanner;
import java.io.*;

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

    public static void add(int amount){
        UpdatedBalance = balance + amount;
        updateBalance(balance, UpdatedBalance);
    }

    public static void minus(int amount){
        UpdatedBalance = balance - amount;
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
                    add(amount);
                    updateBalance(balance, UpdatedBalance);
                    break;
                case 2:
                    System.out.println("Enter amount to minus: ");
                    amount = input.nextInt();
                    minus(amount);
                    updateBalance(balance, UpdatedBalance);
                    break;
                case 3:
                    System.out.println("Your current balance is: ");
                    display();
                    break;
                case 4:
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