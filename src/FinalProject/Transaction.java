package FinalProject;

import java.io.*;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class Transaction {
    static Scanner scan = new Scanner(System.in);
    static ArrayList<Member> list = new ArrayList<>();
    static final File FILE = new File("members.txt");
    static ObjectInputStream ois;
    static ListIterator li;

    static String firstName, lastName, email;
    static char  gender = scan.next().charAt(0);
    static int memberId;



    public static void register(){
        System.out.print("Enter member ID: ");
        memberId = scan.nextInt();
        System.out.print("Enter first name: ");
        firstName = scan.next();
        System.out.print("Enter last name: ");
        lastName = scan.next();
        System.out.print("Enter gender : ");
        gender = scan.next().charAt(0);
        System.out.print("Enter email: ");
        email = scan.next();
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE));
            list.add(new Member(memberId, firstName, lastName, gender, email));
            oos.writeObject(list);
            oos.close();

        }catch (IOException e){
            e.printStackTrace();
        }

    }

}
