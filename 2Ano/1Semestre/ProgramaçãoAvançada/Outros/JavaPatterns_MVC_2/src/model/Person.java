package model;

public class Person {
    private int id;
    private  String name;
    private PersonRole role;

    public enum PersonRole { STUDENT, TEACHER }

    public Person(int id, String name, PersonRole role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public PersonRole getRole() {
        return role;
    }

    @Override
    public String toString() {
        return String.format("%s (%d) | %s", name, id, role.toString());
    }

    public boolean isRole(PersonRole role){
        return this.role == role;
    }
}

