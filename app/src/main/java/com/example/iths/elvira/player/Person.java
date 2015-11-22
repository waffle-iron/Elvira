package com.example.iths.elvira.player;

/**
 * Created by Bartek Svaberg on 15-11-15.
 */
public abstract class Person {
    //Robin ändrade id till String då alla värden tas in som String i XML filen (2015-11-19)
    private String firstName;
    private String lastName;
    private String id;

    //Konstruktorn ändrad för att kunna ta emot "String id" (2015-11-19)
    public Person(String firstName, String lastName, String id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
