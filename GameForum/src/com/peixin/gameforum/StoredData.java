package com.peixin.gameforum;

public class StoredData {
	private boolean isLog = false;
	private String username = "";
	private String game = "";
	private String title = "";

	private static final StoredData holder = new StoredData();

	public static StoredData getInstance() {
		return holder;
	}

	public boolean getLogin() {
		return isLog;
	}

	public String getUser() {
		return username;
	}

	public String getGame() {
		return game;
	}

	public String getTitle() {
		return title;
	}

	public void setLogin(boolean value) {
		this.isLog = value;
	}
	
	public void setUser(String username){
		this.username = username;
	}
	
	public void setGame(String game){
		this.game = game;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
}
