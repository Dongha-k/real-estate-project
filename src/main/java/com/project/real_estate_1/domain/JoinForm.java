package com.project.real_estate_1.domain;

public class JoinForm {
    private String id;
    private String password;
    private String passwordConfirm;
    public JoinForm(String id, String password, String passwordConfirm) {
        this.id = id;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public JoinForm() {
    }

    @Override
    public String toString() {
        return "JoinForm{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", passwordConfirm='" + passwordConfirm + '\'' +
                '}';
    }
}
