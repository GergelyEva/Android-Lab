package com.example.otpapp4;

public class Voter {
    private String phoneNumber;
    private String otpCode;
    private String vote;

    public Voter(String phoneNumber, String otpCode, String vote) {
        this.phoneNumber = phoneNumber;
        this.otpCode = otpCode;
        this.vote = vote;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }
}
