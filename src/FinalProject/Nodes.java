package FinalProject;

import java.util.Date;

public class Nodes extends Member{
    private int schoolYear;
    private int memberId;
    private String position;

    public Nodes(int memberId, String firstName, String lastName, char gender, String email) {
        super(memberId, firstName, lastName, gender, email);
    }


    // Getter and setter methods for the fields of the officer
    public int getSchoolYear() {
        return this.schoolYear;
    }

    public void setSchoolYear(int schoolYear) {
        this.schoolYear = schoolYear;
    }

    public int getMemberId() {
        return this.memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
