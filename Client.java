package sample;

public class Client {

    @MyPattern(regex="imie",message = "This value is not correct",myValidatedField = "")
    public String name;

    @MyPattern(regex="nazwisko",message = "This value is not correct",myValidatedField = "")
    public String surName;
}
