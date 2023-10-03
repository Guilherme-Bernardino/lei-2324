package model;

public class Relationship {
    private String description;
    private RelRole role;

    public enum RelRole {
        CLASS, GROUP
    }

    public Relationship(String description, RelRole role) {
        this.description = description;
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("%s | %s", description, role.toString());
    }

    public boolean isRole(RelRole role){
        return this.role == role;
    }
}
