package com.epam.rd.autotasks.sprintplanning;

import com.epam.rd.autotasks.sprintplanning.tickets.Bug;
import com.epam.rd.autotasks.sprintplanning.tickets.Ticket;
import com.epam.rd.autotasks.sprintplanning.tickets.UserStory;
import java.util.ArrayList;
import java.util.List;

public class Sprint {

    int index;
    int capacity;
    int ticketsLimit;
    Ticket[] tickets;

    public Sprint(int capacity, int ticketsLimit) {
        if (index + 1 > ticketsLimit) throw new IllegalArgumentException();

        this.capacity = capacity;
        this.ticketsLimit = ticketsLimit;
        tickets = new Ticket[ticketsLimit];

    }

    public Sprint() {
    }

    public boolean addUserStory(UserStory userStory) {
        if (index + 1 <= ticketsLimit
                && userStory != null
                && !userStory.isCompleted()
                && getTotalEstimate() + userStory.getEstimate() <= capacity) {

            for (UserStory dependency : userStory.dependencies) {
                if (!containsTicket(dependency)) {
                    return false;
                }
            }

            tickets[index++] = userStory;

            return true;
        }
        return false;
    }

    public boolean addBug(Bug bugReport) {
        if (index + 1 <= ticketsLimit
                && bugReport != null
                && !bugReport.isCompleted()
                && getTotalEstimate() + bugReport.getEstimate() <= capacity) {

            tickets[index++] = Bug.createBug(bugReport.getId(), bugReport.getName(), bugReport.getEstimate(),
                    bugReport.userStory);
            return true;
        }
        return false;
    }

    public Ticket[] getTickets() {
        List<Ticket> list = new ArrayList<>();
        for (Ticket ticket : tickets) {
            if (ticket == null) {
                break;
            }
            list.add(ticket);
        }

        Ticket[] copy = new Ticket[list.size()];
        return list.toArray(copy);
    }

    public int getTotalEstimate() {
        int sum = 0;
        for (Ticket ticket : tickets) {
            if (ticket != null)
                sum += ticket.getEstimate();
            else
                break;
        }
        return sum;
    }

    private boolean containsTicket(Ticket ticket) {
        for (int i = 0; i < ticketsLimit; i++) {
            if (tickets[i] == ticket) {
                return true;
            }
        }
        return false;
    }
}