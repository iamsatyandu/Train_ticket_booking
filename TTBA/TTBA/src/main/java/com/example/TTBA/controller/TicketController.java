package com.example.TTBA.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TTBA.model.Ticket;
import com.example.TTBA.model.User;
import com.example.TTBA.service.TicketService;


@RestController
@RequestMapping("/api/tickets")
public class TicketController {
	
	private final TicketService ticketService;
	
	public TicketController(TicketService ticketService)
	{
		this.ticketService = ticketService;
	}
	
	@PostMapping("/purchase")
	public ResponseEntity<Ticket> purchaseTicket(@RequestBody User user)
	{
		Ticket ticket = ticketService.purchaseTicket(user);
		return ResponseEntity.ok(ticket);
	}
	
	@GetMapping("/receipt/{email}")
	public ResponseEntity<Ticket> getReceipt(@PathVariable String email)
	{
		Ticket ticket = ticketService.getTicket(email);
		return ticket != null ? ResponseEntity.ok(ticket) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/users/{section}")
	public ResponseEntity<Map<String, User>> getUsersBySection(@PathVariable String section)
	{
		Map<String, User> users = ticketService.getUserBySection(section);
		return ResponseEntity.ok(users);
	
	}
	
	@DeleteMapping("/remove/{email}")
	public ResponseEntity<Void> removeUser(@PathVariable String email)
	{
		ticketService.removeUser(email);
		return ResponseEntity.noContent().build();
				
	}
	
	@PutMapping("/modify-seat/{email}")
	public ResponseEntity<Void> modifySeat(@PathVariable String email, @RequestParam String newSeat)
	{
		
		ticketService.modifySeat(email, newSeat);
		return ResponseEntity.noContent().build();
	}

}
