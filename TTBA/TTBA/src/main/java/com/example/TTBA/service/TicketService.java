package com.example.TTBA.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.TTBA.model.Ticket;
import com.example.TTBA.model.User;


@Service
public class TicketService {
	
	private final Map<String, Ticket> tickets = new HashMap<>();
	private final Map<String, User> users = new HashMap<>();
	private final String[] seats = {"A1", "A2", "B1", "B2"};
	private int seatIndex =0;
	
	
	public Ticket purchaseTicket(User user) {
		if(seatIndex >= seats.length) {
			throw new IllegalStateException("No seats available");
		}
		Ticket ticket = new Ticket();
		ticket.setUser(user);
		ticket.setSeat(seats[seatIndex++]);
		String ticketId = UUID.randomUUID().toString();
		tickets.put(ticketId, ticket);
		users.put(user.getEmail(), user);
		return ticket;
		
	}
	
	public Ticket getTicket(String email)
	{
		return tickets.values().stream()
				.filter(ticket -> ticket.getUser().getEmail().equals(email))
				.findFirst()
				.orElse(null);
	}
	
	public Map<String, User> getUserBySection(String section)
	{
		Map<String, User> sectionUsers = new HashMap<>();
		tickets.values().stream()
		        .filter(ticket -> ticket.getSeat().startsWith(section))
		        .forEach(ticket -> sectionUsers.put(ticket.getUser().getEmail(),ticket.getUser()));
		return sectionUsers;
		
	}
	
	public void removeUser(String email)
	{
		tickets.entrySet().removeIf(entry -> entry.getValue().getUser().getEmail().equals(email));
		users.remove(email);
	}
	
	public void modifySeat(String email, String newSeat)
	{
		tickets.values().stream()
		        .filter(ticket -> ticket.getUser().getEmail().equals(email))
		        .forEach(ticket -> ticket.setSeat(newSeat));
	}

}
