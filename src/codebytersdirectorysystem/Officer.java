/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codebytersdirectorysystem;

import static codebytersdirectorysystem.User.ANSI_GREEN;
import static codebytersdirectorysystem.User.ANSI_RESET;
import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author japin
 */
public class Officer extends User {

    @Override
    public void add() {
        int id;
        try {
            String fullName = this.getFullName();
            String schoolYear = this.getSchoolYear();
            String position = this.getPosition();

            if (fullName.isEmpty() || schoolYear.isEmpty() || position.isEmpty()) {
                System.out.println("Error: All member information fields must be provided.");
                return;
            }

            Path path = Paths.get("src/CodebytersDirectorySystem/Database/officer.txt").toAbsolutePath();
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);

            if (lines.isEmpty()) {
                // If the file is empty, start the ID from 1
                id = 1;
            } else {
                // Find the highest existing ID and increment it for the new member
                String lastLine = lines.get(lines.size() - 1);
                StringTokenizer stn = new StringTokenizer(lastLine, ",");
                String[] members = lastLine.split(",");
                id = Integer.parseInt(members[0]);
                id++;
            }

            // Set the new ID for the current member
            this.setId(Integer.toString(id));

            // Now append the new member information to the file
            String newMemberData = this.getId() + "," + fullName + "," + schoolYear + "," + position;
            Files.write(path, (newMemberData + System.lineSeparator()).getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
            System.out.println(ANSI_GREEN + "Officer added successfully" + ANSI_RESET);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Override
    public ArrayList<User> read() {
        ArrayList<User> officers = new ArrayList<>();

        try {
            Path path = Paths.get("src/CodebytersDirectorySystem/Database/officer.txt").toAbsolutePath();
            BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);

            String line;
            while ((line = reader.readLine()) != null) {
                String[] userAttributes = line.split(",");
                if (userAttributes.length == 4) {
                    Officer officer = new Officer();
                    officer.setId(userAttributes[0]);
                    officer.setFullName(userAttributes[1]);
                    officer.setSchoolYear(userAttributes[2]);
                    officer.setPosition(userAttributes[3]);

                    officers.add(officer);
                }
            }

            reader.close();
        } catch (Exception e) {
            System.out.println("Error reading users: " + e.getMessage());
        }

        return officers;
    }

    @Override
    public void update() {
         try {
            Path path = Paths.get("src/CodebytersDirectorySystem/Database/officer.txt").toAbsolutePath();
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);

            // Find the user with the given ID and update their information
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                StringTokenizer stn = new StringTokenizer(line, ",");
                String id = stn.nextToken(); // Assuming the first token is the ID

                if (id.equals(this.getId())) {

                    // Convert the user object to a string and update the line in the list
                    String updatedLine = this.getId() + "," + this.getFullName() + "," + this.getSchoolYear() + "," + this.getPosition();
                    lines.set(i, updatedLine);

                    // Write the updated list back to the file
                    Files.write(path, lines, StandardCharsets.UTF_8);
                    System.out.println(ANSI_GREEN + "Officer with ID " + this.getId() + " updated successfully" + ANSI_RESET);
                    return;
                }
            }

            // If the ID is not found, print an error message
            System.out.println("Error: Officer with ID " + this.getId() + " not found.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
