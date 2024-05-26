package com.example.TTBA.model;



import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class Ticket {
	
	@NotBlank(message = "From location is mandatory")
	private String from = "London";
	
	@NotBlank(message = "To location is mandatory")
	private String to = "France";
	
	@Valid
	@NotNull(message = "User is mandatory")
	private User user;
	
	@Positive(message = "Price should be positive")
	private double price = 20.0;
	
	private String seat;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}
	
	

}
