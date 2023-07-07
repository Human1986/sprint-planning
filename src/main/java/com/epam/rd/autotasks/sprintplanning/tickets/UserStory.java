package com.epam.rd.autotasks.sprintplanning.tickets;

public class UserStory extends Ticket {

    public UserStory[] dependencies;

    public UserStory(int id, String name, int estimate, UserStory... dependsOn) {
        super(id, name, estimate);
        this.dependencies = dependsOn;
    }

    @Override
    public boolean isCompleted() {
        return isComplete;
    }

    @Override
    public void complete() {
        boolean allDependenciesCompleted = true;
        for (UserStory dependency : dependencies) {
            if (!dependency.isCompleted()) {
                allDependenciesCompleted = false;
                break;
            }
        }
        if (allDependenciesCompleted) {
            super.complete();
        }
    }

    public UserStory[] getDependencies() {
        UserStory[] defensiveCopy = new UserStory[dependencies.length];
        System.arraycopy(dependencies, 0, defensiveCopy, 0, dependencies.length);
        return defensiveCopy;
    }

    @Override
    public String toString() {
        return String.format("[US %d] %s", getId(), getName());
    }
}