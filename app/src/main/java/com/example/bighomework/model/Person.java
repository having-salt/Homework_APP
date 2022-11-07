package com.example.bighomework.model;

public abstract class Person {
    private String name;
    //private boolean sex;//male:true female:false
    private String sentence;
    private String school;

    public Person() {}

    public Person(String name, String sentence, String school) {
        this.name = name;
        this.sentence = sentence;
        this.school = school;
    }

    public String getName() {
        return name;
    }

    public String getSentence() {
        return sentence;
    }

    public String getSchool() {
        return school;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", sentence='" + sentence + '\'' +
                ", school='" + school + '\'' +
                '}';
    }
}
