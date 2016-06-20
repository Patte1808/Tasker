package de.patte.tasker;

/**
 * Created by Patte on 17.06.16.
 */
public class Task {

    private int id;
    private String title;
    private int isDone;

    public Task() {

    }

    public Task(String title, int isDone) {
        this.title = title;
        this.isDone = isDone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int isDone() {
        return this.isDone;
    }

    public void setDone(int isDone) {
        this.isDone = isDone;
    }

}
