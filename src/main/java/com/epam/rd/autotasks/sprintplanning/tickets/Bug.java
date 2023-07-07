package com.epam.rd.autotasks.sprintplanning.tickets;

public class Bug extends Ticket {

    UserStory userStory;

    private Bug(int id, String name, int estimate, UserStory userStory) {
        super(id, name, estimate);
        this.userStory = userStory;
    }

    public static Bug createBug(int id, String name, int estimate,  UserStory userStory) {
        if (!userStory.isCompleted()) return null;
        return new Bug(id, name, estimate, userStory);
    }

    @Override
    public String toString() {
        return String.format("[Bug %d] %s: %s", getId(), userStory.getName(), getName());
    }
}