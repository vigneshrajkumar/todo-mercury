package Model;

import java.util.Observable;

public class Task extends Observable {
    private int ID;
    private String description;

    public Task(int ID, String description) {
        this.ID = ID;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getID() {
        return ID;
    }

    @Override
    public String toString() {
        return "Task{" +
                "ID=" + ID +
                ", description='" + description + '\'' +
                '}';
    }
}
