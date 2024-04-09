package com.titdevelopercommunity.titsports;

public class DataClass {
    private final String phoneNo;
    private final String Student_name;
    private final String Enrollment;
    private final String document;

    public DataClass(String phoneNo, String studentName, String enrollment, String document) {
        this.phoneNo = phoneNo;
        Student_name = studentName;
        Enrollment = enrollment;
        this.document = document;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getStudent_name() {
        return Student_name;
    }

    public String getEnrollment() {
        return Enrollment;
    }

    public String getDocument() {
        return document;
    }
}
