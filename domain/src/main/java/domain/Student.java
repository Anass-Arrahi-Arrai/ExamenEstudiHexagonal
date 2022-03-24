package domain;

import java.util.UUID;

public class Student {

    private String id;
    private String name;
    private String secondName;
    private String email;

    public Student(String name, String secondName, String email) {
        id = UUID.randomUUID().toString();
        this.name = name;
        this.secondName = secondName;
        this.email = email;
    }

    public Student() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
