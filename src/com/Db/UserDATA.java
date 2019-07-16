package com.Db;

public class UserDATA{
    private Long id;
    private User user;
    private String name, surname, gender, country,city;

    public UserDATA() {
    }

    public UserDATA(Long id, User user, String name, String surname, String gender, String country, String city) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.country = country;
        this.city = city;
    }

    public UserDATA(User user, String name, String surname, String gender, String country, String city) {
        this.user = user;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.country = country;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "UserDATA{" +
                "id=" + id +
                ", user=" + user +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", gender='" + gender + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
