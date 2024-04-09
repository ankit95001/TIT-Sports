package com.titdevelopercommunity.titsports;

public class CertificateDataClass {
    private final String phoneNo;
    private final String Student_name;
    private final String Enrollment;
    private final String document;
    private final String description;

    public CertificateDataClass(String phoneNo, String studentName, String enrollment, String document, String description) {
        this.phoneNo = phoneNo;
        Student_name = studentName;
        Enrollment = enrollment;
        this.document = document;
        this.description = description;
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

    public String getDescription() {
        return description;
    }
}
