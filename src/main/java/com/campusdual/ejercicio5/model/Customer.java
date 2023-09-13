package com.campusdual.ejercicio5.model;

import com.campusdual.ejercicio5.enums.Days;

import java.util.HashMap;
import java.util.Map;

public class Customer {
    private String name;
    private String surname;
    private Integer weight;
    private Integer heigth;
    private Integer age;
    private String gender;

    private Map<Days,String> dietList;

    public Customer() {
        dietList = new HashMap<>();
    }

    public Customer(String name, String surname, Integer weight, Integer heigth, Integer age, String gender) {
        this.name = name;
        this.surname = surname;
        this.weight = weight;
        this.heigth = heigth;
        this.age = age;
        this.gender = gender;
        dietList = new HashMap<>();
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

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getHeigth() {
        return heigth;
    }

    public void setHeigth(Integer heigth) {
        this.heigth = heigth;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Map<Days, String> getDietList() {
        return dietList;
    }

    public void setDietList(Map<Days,String> dietList) {
        this.dietList = dietList;
    }
}
