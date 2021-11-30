package com.bilgeadam.movie.dto;

import java.time.LocalDateTime;

import com.bilgeadam.movie.dto.entitiy.User;

public class Message {
	
	private String text;
	private LocalDateTime logDate;
	private User sendUser;
	
	public Message(User sendUser) {
		this.text = "";
		this.sendUser = sendUser;
		this.logDate = LocalDateTime.now();
		
	}
	
	@Override
	public String toString() {
		return "Messages [  text=" + text + ", logDate=" + logDate + ", sendUser=" + sendUser + "]";
	}
	
	public String getText() {
		return text;
	}
	
	public LocalDateTime getLogDate() {
		return logDate;
	}
	
	public User getSendUser() {
		return sendUser;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
}
