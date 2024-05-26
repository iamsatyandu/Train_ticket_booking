package com.example.TTBA;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.TTBA.model.Ticket;
import com.example.TTBA.model.User;
import com.example.TTBA.service.TicketService;



@SpringBootTest
public class TtbaApplicationTests {
	
	@Autowired
	private TicketService ticketService;
	
	@Test
	public void testPurchaseTicket()
	{
		User user = new User();
		user.setFirstName("Satyendu");
		user.setLastname("Panda");
		user.setEmail("Satyendu@gmail.com");
		
		Ticket ticket = ticketService.purchaseTicket(user);
		assertNotNull(ticket);
		assertEquals("Satyendu",ticket.getUser().getFirstName());
		assertEquals("Panda",ticket.getUser().getLastname());
		assertEquals("Satyendu@gmail.com",ticket.getUser().getEmail());
		assertEquals(20.0, ticket.getPrice());
		
		/*
		 * ticket.setUser(user); ticket.setFrom("England"); ticket.setTo("France");
		 * ticket.setPrice(20); ticket.setSeat("A1");
		 * 
		 * Ticket purchasedTicket = ticketService.purchaseTicket(ticket);
		 * 
		 * assertNotNull(purchasedTicket, "Ticket purchase shoud be successful");
		 * assertEquals("Satyendu@gmail.com", purchasedTicket.getUser().getEmail());
		 */

	}
	
	@Test
	public void testGetReceipt()
	{
		User user = new User();
		user.setFirstName("Sipra");
		user.setLastname("Panda");
		user.setEmail("Sipra@gmail.com");
		ticketService.purchaseTicket(user);
		Ticket ticket = ticketService.getTicket("Sipra@gmail.com");
		assertNotNull(ticket);
		
		/*
		 * Ticket ticket = new Ticket(); ticket.setUser(user);
		 * ticket.setFrom("England"); ticket.setTo("France"); ticket.setPrice(20);
		 * ticket.setSeat("A1"); ticketService.purchaseTicket(ticket);
		 * 
		 * Ticket receiptTicket = ticketService.getTicket("Sipra@gmail.com");
		 * 
		 * 
		 * assertNotNull(receiptTicket, "Receipt ticket should not be null");
		 * assertEquals("Sipra@gmail.com", receiptTicket.getUser().getEmail());
		 * 
		 */
	}
	
	@Test
	public void testRemoveUser()
	{
		User user = new User();
		user.setFirstName("Alia");
		user.setLastname("Bhatt");
		user.setEmail("Alia@gmail.com");
		ticketService.purchaseTicket(user);
		ticketService.removeUser("Alia@gmail.com");
		assertNull(ticketService.getTicket("Alia@gmail.com"));
		
		/*
		 * Ticket ticket = new Ticket(); ticket.setUser(user);
		 * ticket.setFrom("England"); ticket.setTo("France"); ticket.setPrice(20);
		 * ticket.setSeat("A1"); ticketService.purchaseTicket(ticket);
		 * 
		 * ticketService.removeUser("Alia@gmail.com");
		 * 
		 * Ticket removedTicket = ticketService.getTicket("Alia@gmail.com");
		 * assertNull(removedTicket, "User removal should be successful");
		 */
	}
	
	
	@Test
	public void testModifySeat()
	{
		User user = new User();
		user.setFirstName("Bob");
		user.setLastname("Brown");
		user.setEmail("bob@brwon.com");

		Ticket ticket = ticketService.purchaseTicket(user);
		ticketService.modifySeat("bob@brown.com", "B1");
		assertEquals("B1",ticket.getSeat());
		
		/*
		 * Ticket ticket = new Ticket(); ticket.setUser(user);
		 * ticket.setFrom("England"); ticket.setTo("France"); ticket.setPrice(20);
		 * ticket.setSeat("A1"); ticketService.purchaseTicket(ticket);
		 * 
		 * ticketService.modifySeat("bob@brown.com", "B1");
		 * 
		 * Ticket modifiedTicket = ticketService.getTicket("bob@brown.com");
		 * assertNotNull(modifiedTicket, "Modified ticket should not be null");
		 * assertEquals("B1", modifiedTicket.getSeat(),
		 * "Seat modification should be successful");
		 */
	}
}
