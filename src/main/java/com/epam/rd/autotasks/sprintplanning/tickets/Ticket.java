package com.epam.rd.autotasks.sprintplanning.tickets;

public class Ticket extends Sprint{
    boolean isComplete;
    private final int id;
    private final String name;
    private final int estimate;


    public Ticket(int id, String name, int estimate) {
        this.id = id;
        this.name = name;
        this.estimate = estimate;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isCompleted() {
        return isComplete;
    }

    public void complete() {
        isComplete = true;
    }

    public int getEstimate() {
        return estimate;
    }
}
