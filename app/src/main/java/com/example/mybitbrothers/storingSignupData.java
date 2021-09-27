package com.example.mybitbrothers;

public class storingSignupData {
    String name,mobileNumber,emailid,password,confirmPassword;

    public storingSignupData() {
    }
    public storingSignupData(String name, String mobileNumber, String emailid, String password, String confirmPassword) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.emailid = emailid;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}