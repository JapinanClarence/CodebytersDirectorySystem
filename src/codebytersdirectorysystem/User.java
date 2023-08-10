/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codebytersdirectorysystem;

import java.util.ArrayList;

/**
 *
 * @author japin
 */
abstract public class User {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    protected String id;
    protected String firstname;
    protected String lastname;
    protected String middleInitial;

    //member specific attributes
    protected String gender;
    protected String birthDate;
    protected String cellphoneNumber;
    protected String email;

    //officer specific attributes
    protected String schoolYear;
    protected String position;

    //abstract classes
    abstract public void add();

    abstract public ArrayList<User> read();
    
    abstract public void update();

    //setters
    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirthDate(String dateOfBirth) {
        this.birthDate = dateOfBirth;
    }

    public void setCellNum(String cellNum) {
        this.cellphoneNumber = cellNum;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //getters
    public String getGender() {
        return this.gender;
    }

    public String getBirthDate() {
        return this.birthDate;
    }

    public String getCellNum() {
        return this.cellphoneNumber;
    }

    public String getEmail() {
        return this.email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstname = firstName;
    }

    public void setLastName(String lastName) {
        this.lastname = lastName;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    public String getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstname;
    }

    public String getLastName() {
        return this.lastname;
    }

    public String getMidIn() {
        return this.middleInitial;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSchoolYear() {
        return this.schoolYear;
    }

    public String getPosition() {
        return this.position;
    }

    public void setFullName(String fullName) {
        String[] names = fullName.split(" ");
        if (names.length == 1) {
            this.firstname = names[0];
            this.middleInitial = "";
            this.lastname = "";
        } else if (names.length == 2) {
            this.firstname = names[0];
            this.middleInitial = "";
            this.lastname = names[1];
        } else if (names.length >= 3) {
            this.firstname = names[0];
            this.middleInitial = names[1].substring(0, 1);
            this.lastname = names[names.length - 1];
        }
    }

    //get fullname
    public String getFullName() {
        String fullName = this.firstname + " " + this.middleInitial + " " + this.lastname;
        return fullName;
    }

}
