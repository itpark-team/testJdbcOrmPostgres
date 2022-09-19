package com.company.model.entities;

public class User {
    private int id;
    private String name;
    private int age;
    private int countryId;
    private Country country;

    public User(int id, String name, int age, int countryId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.countryId = countryId;
        this.country = null;
    }

    public User(String name, int age, int countryId) {
        this.id = 0;
        this.name = name;
        this.age = age;
        this.countryId = countryId;
        this.country = null;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getCountryId() {
        return countryId;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", countryId=" + countryId +
                ", country=" + country +
                '}';
    }
}
