package com.example.bighomework.model;

public class Teacher extends Person{
    public Teacher(String name, String sentence, String school) {
        super(name, sentence, school);
    }
    @Override
    public String toString() {
        return  "Teacher{" +
                "name='" + getName() + '\'' +
                ", sentence='" + getSentence() + '\'' +
                ", school='" + getSchool() + '\'' +
                '}';
    }
}
