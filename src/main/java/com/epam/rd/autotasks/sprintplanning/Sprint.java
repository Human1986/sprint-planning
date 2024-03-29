package com.epam.rd.autotasks.sprintplanning;

import com.epam.rd.autotasks.sprintplanning.tickets.Bug;
import com.epam.rd.autotasks.sprintplanning.tickets.Ticket;
import com.epam.rd.autotasks.sprintplanning.tickets.UserStory;

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
                && ! userStory.isCompleted()
                && getTotalEstimate() + userStory.getEstimate() <= capacity) {

            for (UserStory dependency : userStory.dependencies) {
                if (! containsTicket(dependency)) {
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
                && ! bugReport.isCompleted()
                && getTotalEstimate() + bugReport.getEstimate() <= capacity) {

            tickets[index++] = bugReport;
            return true;
        }
        return false;
    }

    public Ticket[] getTickets() {
        int i = 0;
        int length = 0;
        for (Ticket ticket : tickets) {
            if (ticket != null) length++;
        }
        Ticket[] copy = new Ticket[length];
        for (Ticket ticket : tickets) {
            if (ticket == null) {
                continue;
            }
            copy[i++] = ticket;
        }
        return copy;
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