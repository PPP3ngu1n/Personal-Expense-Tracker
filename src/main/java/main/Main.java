package main;

import java.util.Scanner;
import java.io.*;

class Main {
    static File receipt = new File("receipt.txt");
    static File keptBalance = new File("Balance.txt");

    static double balance;

    public static void account(){
        try {
            if (!keptBalance.exists()) {
                balance = 150.0;
                System.out.println("SDjaifsjfhik");
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


    public static void add(int amount){
        balance += amount;
    }

    public static void minus(int amount){
        balance -= amount;
    }

    public static void display(){
        System.out.println("Balance: " + balance);
    }

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to your account!");

        account();

        while(true){
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
                    break;
                case 2:
                    System.out.println("Enter amount to minus: ");
                    int amount2 = input.nextInt();
                    minus(amount2);
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