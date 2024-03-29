/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package codebytersdirectorysystem;

import static codebytersdirectorysystem.User.ANSI_GREEN;
import static codebytersdirectorysystem.User.ANSI_RED;
import static codebytersdirectorysystem.User.ANSI_RESET;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author japin
 */
public class CodebytersDirectorySystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        boolean isContinue = true;
        do {
            //Shows the system menu
            System.out.println("""

                                *----------------System Menu---------------*
                                a. Add Member/Officer
                                b. Display List of Members/Officers
                                c. Search Member/Officer
                                d. Edit Member Info
                                e. Exit
                                """);
            System.out.print("Enter option: ");
            char operator = scan.next().charAt(0);

            switch (operator) {
                case 'a' ->
                    addList();
                case 'b' ->
                    displayList();
                case 'c' ->
                    searchList();
                case 'd' ->
                    updateList();
                case 'e' -> {
                    isContinue = false;
                    System.out.println("Program Terminated");
                }
                default ->
//                    throw new IllegalStateException("Unexpected value: " + operator);
                    System.out.println(ANSI_RED + "Invalid option." + ANSI_RESET);
            }
        } while (isContinue);
    }

    public static void addList() {
        //instantiate classes
        Member member = new Member();
        Officer officer = new Officer();
        boolean isContinue = true;

        do {
            Scanner sc = new Scanner(System.in);
            System.out.print("""
                        Enter:
                        a. Members
                        b. Officers
                        c. Menu
                        """);

            System.out.print("Enter option : ");
            String option = sc.nextLine().toLowerCase();

            switch (option) {
                case "a": {
                    boolean pass = false;

                    //set firstname
                    System.out.print("Enter first name: ");
                    String firstName = sc.nextLine();
                    member.setFirstName(firstName);

                    boolean validInitial = false;
                    //set mid initial
                    String initial = null;
                    while (validInitial == false) {
                        System.out.print("Enter middle intial: ");
                        initial = sc.nextLine();

                        if (!isValidMiddleInitial(initial)) {
                            System.out.println(ANSI_RED + "Invalid middle initial. Please enter a single uppercase letter." + ANSI_RESET);
                        } else {
                            validInitial = true;
                        }
                    }
                    member.setMiddleInitial(initial);

                    //set last name
                    System.out.print("Enter last name: ");
                    String lastName = sc.nextLine();
                    member.setLastName(lastName);

                    //set gender
                    String gender = null;
                    while (pass == false) {
                        System.out.print("Enter gender(male/female): ");
                        gender = sc.nextLine();

                        if (gender.length() < 2) {
                            System.out.println(ANSI_RED + "Entered gender must be atleast two characters long" + ANSI_RESET);
                        } else {
                            pass = true;
                        }
                    }
                    member.setGender(gender);

                    pass = false;
                    //set bdate
                    String bDate = null;
                    while (pass == false) {
                        System.out.print("Enter Date of birth (mm/dd/yyyy): ");
                        bDate = sc.nextLine();

                        LocalDate dateFormat = isValidDate(bDate);

                        if (dateFormat == null) {
                            System.out.println(ANSI_RED + "Entered date is invalid format." + ANSI_RESET);
                        } else {
                            pass = true;
                        }
                    }

                    member.setBirthDate(bDate);

                    pass = false;
                    //set contact num
                    String contactNum = null;
                    while (pass == false) {
                        System.out.print("Enter contact num: ");
                        contactNum = sc.nextLine();
                        if (contactNum.length() < 11 || contactNum.length() > 11) {
                            System.out.println(ANSI_RED + "Contact number must be atleast 11 characters long" + ANSI_RESET);
                        } else {
                            pass = true;
                        }
                    }
                    member.setCellNum(contactNum);
                    pass = false;
                    //set email
                    String email = null;
                    while (pass == false) {
                        System.out.print("Enter email: ");
                        email = sc.nextLine();
                        if (!isValidEmail(email)) {
                            System.out.println(ANSI_RED + "Invalid email format" + ANSI_RESET);
                        } else {
                            member.setEmail(email);
                            pass = true;
                        }
                    }
                    member.setEmail(email);

                    //append fullname
                    String fullName = firstName + " " + initial + " " + lastName;
                    //verify if user existed
                    boolean isMemberExists = false;
                    ArrayList<User> members = member.read();
                    for (User item : members) {
                        if (item.getFullName().equals(fullName)) {
                            isMemberExists = true;
                            break;
                        }
                    }

                    if (isMemberExists) {
                        System.out.println(ANSI_RED + "Member already exists." + ANSI_RESET);
                    } else {
                        member.add();
                    }
                    break;
                }
                case "b": {
                    boolean pass = false;
                    //set id
                    System.out.print("Enter Id: ");
                    String id = sc.nextLine();
                    officer.setId(id);

                    ArrayList<User> members = member.read();

                    for (User item : members) {
                        if (item.getId().equals(id)) {
                            System.out.print(item.getId());
                            System.out.print("    |    " + item.getFirstName());
                            System.out.print("    |    " + item.getMidIn());
                            System.out.print("    |    " + item.getLastName());
                            System.out.print("    |    " + item.getGender());
                            System.out.print("    |    " + item.getBirthDate());
                            System.out.print("    |    " + item.getCellNum());
                            System.out.print("    |    " + item.getEmail() + "\n");

                            officer.setId(id);
                            officer.setFirstName(item.getFirstName());
                            officer.setMiddleInitial(item.getMidIn());
                            officer.setLastName(item.getLastName());
                            officer.setGender(item.getGender());
                            officer.setBirthDate(item.getBirthDate());
                            officer.setCellNum(item.getCellNum());
                            officer.setEmail(item.getEmail());

                            //set school year  
                            String schoolYear = null;
                            while (pass == false) {
                                System.out.print("Enter school year: ");
                                schoolYear = sc.nextLine();

                                String format = verifySchoolYearFormat(schoolYear);

                                if (format == null) {
                                    System.out.println("Invalid format");
                                } else {
                                    pass = true;
                                }
                            }
                            officer.setSchoolYear(schoolYear);

                            //set position
                            System.out.print("Enter position: ");
                            officer.setPosition(sc.nextLine());

                            officer.add();
                            int userId = Integer.parseInt(id);//id from user prompt, convert to integer
                            
                            officer.deleteCurrentInstance(userId);//delete this entry from the members file
                        }
                    }
                    break;
                }
                case "c": {
                    isContinue = false;
                    break;
                }
                default: {
                    System.out.println(ANSI_RED + "Invalid option." + ANSI_RESET);
                    break;
                }
            }

        } while (isContinue);

    }

    public static void displayList() {
        //instantiate classes
        Member member = new Member();
        Officer officer = new Officer();

        boolean isContinue = true;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print("""
                            Enter:
                            a. Members
                            b. Female Members
                            c. Male Members
                            d. Officers
                            e. Menu
                            """);
            System.out.print("Enter option : ");
            String option = sc.nextLine().toLowerCase();

            switch (option) {
                case "a": {
                    ArrayList<User> members = member.read();
                    System.out.println("*-------------------------------------------------MEMBERS---------------------------------------------------------------------*");
                    System.out.println("ID    |    Firstname    |    Middle Initial    |    Lastname    |    Gender    |    Birthdate    |    Contact num    |    Email");
                    for (User item : members) {
                        System.out.print(item.getId());
                        System.out.print("    |    " + item.getFirstName());
                        System.out.print("    |    " + item.getMidIn());
                        System.out.print("    |    " + item.getLastName());
                        System.out.print("    |    " + item.getGender());
                        System.out.print("    |    " + item.getBirthDate());
                        System.out.print("    |    " + item.getCellNum());
                        System.out.print("    |    " + item.getEmail() + "\n");
                    }
                    if (members == null) {
                        System.out.println("No members found");
                    }
                    break;
                }
                case "b": {
                    ArrayList<User> members = member.read();
                    System.out.println("*---------------------------------------------  FEMALE MEMBERS----- ----------------------------------------------------------*");
                    System.out.println("ID    |    Firstname    |    Middle Initial    |    Lastname    |    Gender    |    Birthdate    |    Contact num    |    Email");
                    for (User item : members) {
                        if (item.getGender().equals("female")) {
                            System.out.print(item.getId());
                            System.out.print("    |    " + item.getFirstName());
                            System.out.print("    |    " + item.getMidIn());
                            System.out.print("    |    " + item.getLastName());
                            System.out.print("    |    " + item.getGender());
                            System.out.print("    |    " + item.getBirthDate());
                            System.out.print("    |    " + item.getCellNum());
                            System.out.print("    |    " + item.getEmail() + "\n");
                        }

                    }

                    if (members == null) {
                        System.out.println("No members found");
                    }
                    break;
                }
                case "c": {
                    ArrayList<User> members = member.read();
                    System.out.println("*-----------------------------------------------MALE MEMBERS------------------------------------------------------------------*");
                    System.out.println("ID    |    Firstname    |    Middle Initial    |    Lastname    |    Gender    |    Birthdate    |    Contact num    |    Email");
                    for (User item : members) {
                        if (item.getGender().equals("male")) {
                            System.out.print(item.getId());
                            System.out.print("    |    " + item.getFirstName());
                            System.out.print("    |    " + item.getMidIn());
                            System.out.print("    |    " + item.getLastName());
                            System.out.print("    |    " + item.getGender());
                            System.out.print("    |    " + item.getBirthDate());
                            System.out.print("    |    " + item.getCellNum());
                            System.out.print("    |    " + item.getEmail() + "\n");
                        }

                    }
                    if (members == null) {
                        System.out.println("No members found");
                    }
                    break;
                }
                case "d": {
                    ArrayList<User> officers = officer.read();
                    System.out.println("*---------------OFFICERS-------------*");
                    displayOfficerHorizontally(officers);

                    if (officers == null) {
                        System.out.println("No members found");
                    }
                    break;
                }
                case "e": {
                    isContinue = false;
                    break;
                }
                default: {
                    System.out.println(ANSI_RED + "Invalid option." + ANSI_RESET);
                }
            }
        } while (isContinue);

    }

    public static void displayOfficerHorizontally(ArrayList<User> officers) {
        System.out.println("ID    |    Firstname    |    Middle Initial    |    Lastname    |    Gender    |    Birthdate    |    Contact num    |    Email    |    School Year    |    Position");
        for (User item : officers) {
            System.out.print(item.getId());
            System.out.print("    |    " + item.getFirstName());
            System.out.print("    |    " + item.getMidIn());
            System.out.print("    |    " + item.getLastName());
            System.out.print("    |    " + item.getGender());
            System.out.print("    |    " + item.getBirthDate());
            System.out.print("    |    " + item.getCellNum());
            System.out.print("    |    " + item.getEmail());
            System.out.print("    |    " + item.getSchoolYear());
            System.out.print("    |    " + item.getPosition() + "\n");
        }
        if (officers == null) {
            System.out.println("No members found");
        }
    }

    public static void searchList() {
        //instantiate classes
        Member member = new Member();
        Officer officer = new Officer();

        boolean isContinue = true;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print("""
                            Enter:
                            a. Member
                            b. Officer
                            c. Menu
                            """);
            System.out.print("Enter option : ");
            String option = sc.nextLine().toLowerCase();

            switch (option) {
                case "a": {
                    System.out.print("Enter id: ");
                    String id = sc.nextLine();

                    ArrayList<User> members = member.read();
                    System.out.println("*-------------------------------------------------MEMBER--------------------------------------------------------------------*");
                    System.out.println("ID    |    Firstname    |    Middle Initial    |    Lastname    |    Gender    |    Birthdate    |    Contact num    |    Email");
                    for (User item : members) {
                        if (item.getId().equals(id)) {
                            System.out.print(item.getId());
                            System.out.print("    |    " + item.getFirstName());
                            System.out.print("    |    " + item.getMidIn());
                            System.out.print("    |    " + item.getLastName());
                            System.out.print("    |    " + item.getGender());
                            System.out.print("    |    " + item.getBirthDate());
                            System.out.print("    |    " + item.getCellNum());
                            System.out.print("    |    " + item.getEmail() + "\n");
                        }

                    }
                    if (members == null) {
                        System.out.println("No members found");
                    }
                    break;
                }
                case "b": {
                    System.out.print("Enter id: ");
                    String id = sc.nextLine();

                    ArrayList<User> officers = officer.read();
                    System.out.println("*------------------------------------------OFFICER---------------------------------------------*");
                    System.out.println("ID    |    Firstname    |    Middle Initial    |    Lastname    |    Gender    |    Birthdate    |    Contact num    |    Email    |    School Year    |    Position");
                    for (User item : officers) {
                        if (item.getId().equals(id)) {
                            System.out.print("    |    " + item.getFirstName());
                            System.out.print("    |    " + item.getMidIn());
                            System.out.print("    |    " + item.getLastName());
                            System.out.print("    |    " + item.getGender());
                            System.out.print("    |    " + item.getBirthDate());
                            System.out.print("    |    " + item.getCellNum());
                            System.out.print("    |    " + item.getEmail());
                            System.out.print("    |    " + item.getSchoolYear());
                            System.out.print("    |    " + item.getPosition() + "\n");
                        }

                    }
                    break;
                }
                case "c": {
                    isContinue = false;
                    break;
                }
                default: {
                    System.out.println(ANSI_RED + "Invalid option." + ANSI_RESET);
                }
            }
        } while (isContinue);

    }

    public static void updateList() {

        //instantiate classes
        Member member = new Member();
        Scanner sc = new Scanner(System.in);

        boolean pass = false;
        //set id
        System.out.print("Enter Id: ");
        member.setId(sc.nextLine());
        //set firstname
        System.out.print("Enter first name: ");
        member.setFirstName(sc.nextLine());
        //set mid initial
        System.out.print("Enter middle intial: ");
        member.setMiddleInitial(sc.nextLine());
        //set last name
        System.out.print("Enter last name: ");
        member.setLastName(sc.nextLine());

        //set gender
        String gender = null;
        while (pass == false) {
            System.out.print("Enter gender(male/female): ");
            gender = sc.nextLine();

            if (gender.length() < 2) {
                System.out.println("Entered gender must be atleast two characters long.");
            } else {
                pass = true;
            }
        }
        member.setGender(gender);

        pass = false;
        //set bdate
        String bDate = null;
        while (pass == false) {
            System.out.print("Enter Date of birth (mm/dd/yyyy): ");
            bDate = sc.nextLine();

            LocalDate dateFormat = isValidDate(bDate);

            if (dateFormat == null) {
                System.out.println("Entered date is invalid format");
            } else {
                pass = true;
            }
        }

        member.setBirthDate(bDate);

        //set contact num
        System.out.print("Enter contact num: ");
        member.setCellNum(sc.nextLine());
        //set email
        System.out.print("Enter email: ");
        member.setEmail(sc.nextLine());

        member.update();

//        Officer officer = new Officer();
//
//        
//        System.out.print("""
//                            Enter:
//                            a. Member
//                            b. Officer
//                            """);
//        String option = sc.nextLine().toLowerCase();
//
//        switch (option) {
//            case "a": {
//                boolean pass = false;
//                //set id
//                System.out.print("Enter Id: ");
//                member.setId(sc.nextLine());
//                //set firstname
//                System.out.print("Enter first name: ");
//                member.setFirstName(sc.nextLine());
//                //set mid initial
//                System.out.print("Enter middle intial: ");
//                member.setMiddleInitial(sc.nextLine());
//                //set last name
//                System.out.print("Enter last name: ");
//                member.setLastName(sc.nextLine());
//
//                //set gender
//                String gender = null;
//                while (pass == false) {
//                    System.out.print("Enter gender(male/female): ");
//                    gender = sc.nextLine();
//
//                    if (gender.length() < 2) {
//                        System.out.println("Entered gender must be atleast two characters long.");
//                    } else {
//                        pass = true;
//                    }
//                }
//                member.setGender(gender);
//
//                pass = false;
//                //set bdate
//                String bDate = null;
//                while (pass == false) {
//                    System.out.print("Enter Date of birth (mm/dd/yyyy): ");
//                    bDate = sc.nextLine();
//
//                    LocalDate dateFormat = isValidDate(bDate);
//
//                    if (dateFormat == null) {
//                        System.out.println("Entered date is invalid format");
//                    } else {
//                        pass = true;
//                    }
//                }
//
//                member.setDateOfBirth(bDate);
//
//                //set contact num
//                System.out.print("Enter contact num: ");
//                member.setCellNum(sc.nextLine());
//                //set email
//                System.out.print("Enter email: ");
//                member.setEmail(sc.nextLine());
//
//                member.update();
//                break;
//            }
//            case "b": {
//                boolean pass = false;
//                //set id
//                System.out.print("Enter Id: ");
//                officer.setId(sc.nextLine());
//                //set firstname
//                System.out.print("Enter first name: ");
//                officer.setFirstName(sc.nextLine());
//                //set mid initial
//                System.out.print("Enter middle intial: ");
//                officer.setMiddleInitial(sc.nextLine());
//                //set last name
//                System.out.print("Enter last name: ");
//                officer.setLastName(sc.nextLine());
//
//                //set school year  
//                String schoolYear = null;
//                while (pass == false) {
//                    System.out.println("Enter school year: ");
//                    schoolYear = sc.nextLine();
//
//                    String format = verifySchoolYearFormat(schoolYear);
//
//                    if (format == null) {
//                        System.out.println("Invalid format");
//                    } else {
//                        pass = true;
//                    }
//                }
//                officer.setSchoolYear(schoolYear);
//                //set position
//                System.out.print("Enter position: ");
//                officer.setPosition(sc.nextLine());
//
//                officer.update();
//                break;
//            }
//            default: {
//                System.out.println(ANSI_RED + "Invalid option." + ANSI_RESET);
//            }
//        }
    }

    public static LocalDate isValidDate(String dateStr) {
        try {
            LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            return date;
        } catch (DateTimeParseException e) {
            System.err.println("Error: Invalid date format. Date must be in the format 'mm/dd/yyyy'.");
            return null;
        }
    }

    public static String verifySchoolYearFormat(String schoolYear) {
        // Define the regular expression pattern for the expected format "yyyy-yyyy"
        String pattern = "^(\\d{4})-(\\d{4})$";

        // Create a Pattern object
        Pattern yearPattern = Pattern.compile(pattern);

        // Match the provided school year against the pattern
        Matcher matcher = yearPattern.matcher(schoolYear);

        // If the format matches, return the valid school year format
        if (matcher.matches()) {
            return schoolYear;
        }

        // If the format is incorrect, return null
        return null;
    }

    private static boolean isValidMiddleInitial(String input) {
        // Check if the input is a single character
        if (input.length() != 1) {
            return false;
        }

        // Check if the input is an uppercase letter
        char character = input.charAt(0);
        if (!Character.isLetter(character) || !Character.isUpperCase(character)) {
            return false;
        }

        return true;
    }

    //email validator
    public static boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
