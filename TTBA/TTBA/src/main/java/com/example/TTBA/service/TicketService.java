package com.example.TTBA.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.TTBA.model.Ticket;
import com.example.TTBA.model.User;

@Service
public class TicketService {

    private final Map<String, Ticket> ticketRepository = new HashMap<>();

    public Ticket purchaseTicket(User user) {
        Ticket ticket = new Ticket();
        ticket.setFrom("London");
        ticket.setTo("France");
        ticket.setUser(user);
        ticket.setPrice(20);
        ticket.setSeat(allocateSeat());
        ticketRepository.put(user.getEmail(), ticket);
        return ticket;
    }

    public Ticket getTicket(String email) {
        return ticketRepository.get(email);
    }

    public Map<String, User> getUserBySection(String section) {
        Map<String, User> sectionUsers = new HashMap<>();
        for (Ticket ticket : ticketRepository.values()) {
            if (ticket.getSeat().startsWith(section)) {
                sectionUsers.put(ticket.getUser().getEmail(), ticket.getUser());
            }
        }
        return sectionUsers;
    }

    public void removeUser(String email) {
        ticketRepository.remove(email);
    }

    public void modifySeat(String email, String newSeat) {
        Ticket ticket = ticketRepository.get(email);
        if (ticket != null) {
            ticket.setSeat(newSeat);
        }
    }

    private String allocateSeat() {
        int seatNumber = ticketRepository.size() + 1;
        if (seatNumber <= 50) {
            return "A" + seatNumber;
        } else {
            return "B" + (seatNumber - 50);
        }
    }
}
