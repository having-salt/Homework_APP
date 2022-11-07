package com.example.bighomework.model;

public class Student extends Person{
    public Student(String name, String sentence, String school) {
        super(name, sentence, school);
    }

    @Override
    public String toString() {
        return  "Student{" +
                "name='" + getName() + '\'' +
                ", sentence='" + getSentence() + '\'' +
                ", school='" + getSchool() + '\'' +
                '}';
    }
}

