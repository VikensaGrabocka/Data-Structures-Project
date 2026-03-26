package com.example.part1;

import java.io.Serializable;

public class Doctor implements Comparable<Doctor> {
    private int doctor_id;
    private String name;
    private String surname;
    private String position;
    private String department;
    private String email;

    public Doctor(int doctor_id, String name, String surname, String position, String department, String email) {
        this.doctor_id = doctor_id;
        this.name = name;
        this.surname = surname;
        this.position = position;
        this.department = department;
        this.email = email;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return  doctor_id +" "+ name + " " +surname + " "+ position +
               " "+ department + " "+ email;
    }

    @Override
    public int compareTo(Doctor o) {
        return this.getDoctor_id() - o.getDoctor_id();
    }
}
