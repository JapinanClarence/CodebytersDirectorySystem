package FinalProject;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String loop = "yes";
        do {
            //Shows the system menu
            System.out.println("""
                           *----Codebyters Officers and Members Directory System----*
                       
                           System Menu
                           *--------------------------------------*"   
                           a. Register
                           b. Sign Up
                           c. View Members
                           d. Exit
                           """);
            System.out.print("Enter option: ");
            char operator = scan.next().charAt(0);

                switch (operator) {
                    case 'a' -> {
                        Transaction.register();
                    }
                    case 'b' -> System.out.println("Register Position");
                    case 'c' -> System.out.println("View Members");
                    case 'd' -> {
                        loop = "no";
                        System.out.println("Program Terminated");
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + operator);
                }
        } while (loop == "yes");


    }
}
