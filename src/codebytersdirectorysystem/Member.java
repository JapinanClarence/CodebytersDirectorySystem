/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codebytersdirectorysystem;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import static java.nio.file.StandardOpenOption.APPEND;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author japin
 */
public class Member extends User {

    @Override
    public void add() {
        int id;
        try {
            if (this.getFullName().isEmpty() || this.getGender().isEmpty() || this.getBirthDate().isEmpty() || this.getCellNum().isEmpty() || this.getEmail().isEmpty()) {
                System.out.println("Error: All member information fields must be provided.");
                return;
            }

            Path path = Paths.get("src/CodebytersDirectorySystem/Database/member.txt").toAbsolutePath();
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);

            if (lines.isEmpty()) {
                // If the file is empty, start 2 the ID from 1
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
            String newMemberData = this.getId() + "," + this.getFirstName() + "," + this.getMidIn() + "," + this.getLastName() + "," + this.getGender() + "," + this.getBirthDate() + "," + this.getCellNum() + "," + this.getEmail();

            Files.write(path, (newMemberData + System.lineSeparator()).getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
            System.out.println(ANSI_GREEN + "Member added successfully" + ANSI_RESET);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Override
    public ArrayList<User> read() {
        ArrayList<User> members = new ArrayList<>();

        try {
            Path path = Paths.get("src/CodebytersDirectorySystem/Database/member.txt").toAbsolutePath();
            BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);

            String line;
            while ((line = reader.readLine()) != null) {
                String[] userAttributes = line.split(",");
                if (userAttributes.length == 8) {
                    Member member = new Member();
                    member.setId(userAttributes[0]);
                    member.setFirstName(userAttributes[1]);
                    member.setMiddleInitial(userAttributes[2]);
                    member.setLastName(userAttributes[3]);
                    member.setGender(userAttributes[4]);
                    member.setBirthDate(userAttributes[5]);
                    member.setCellNum(userAttributes[6]);
                    member.setEmail(userAttributes[7]);
                    members.add(member);
                }
            }

            reader.close();
        } catch (Exception e) {
            System.out.println("Error reading users: " + e.getMessage());
        }

        return members;
    }

    @Override
    public void update() {
        try {
            Path path = Paths.get("src/CodebytersDirectorySystem/Database/member.txt").toAbsolutePath();
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);

            // Find the user with the given ID and update their information
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                StringTokenizer stn = new StringTokenizer(line, ",");
                String id = stn.nextToken(); // Assuming the first token is the ID

                if (id.equals(this.getId())) {

                    // Convert the user object to a string and update the line in the list
                    String updatedLine = this.getId() + "," + this.getFirstName() + "," + this.getMidIn() + "," + this.getLastName() + ","+ this.getGender() + "," + this.getBirthDate() + "," + this.getCellNum() + "," + this.getEmail();
                    
                    lines.set(i, updatedLine);

                    // Write the updated list back to the file
                    Files.write(path, lines, StandardCharsets.UTF_8);
                    System.out.println(ANSI_GREEN + "Member with ID " + this.getId() + " updated successfully" + ANSI_RESET);
                    return;
                }
            }

            // If the ID is not found, print an error message
            System.out.println("Error: Member with ID " + this.getId() + " not found.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
