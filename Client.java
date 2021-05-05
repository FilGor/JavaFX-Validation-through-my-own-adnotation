package sample;

public class Client {

    @MyPattern(regex="imie",message = "name value is not correct",myValidatedField = "")
    public String name;

    @MyPattern(regex="nazwisko",message = "surname value is not correct",myValidatedField = "")
    public String surName;


    public int pole;
    public int pole2;
    public String pole3;
}
