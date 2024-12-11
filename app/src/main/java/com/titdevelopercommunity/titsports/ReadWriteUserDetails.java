package com.titdevelopercommunity.titsports;

public class ReadWriteUserDetails {
    private String email,password,name,enrollment,phone,sport;

    public ReadWriteUserDetails() {
    }



    public ReadWriteUserDetails(String email, String password, String name, String enrollment, String phone,String sport) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.enrollment = enrollment;
        this.phone = phone;
        this.sport=sport;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSport() {return sport;}

    public void setSport(String sport) {this.sport=sport;}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(String enrollment) {
        this.enrollment = enrollment;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
