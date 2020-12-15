package com.app.siget.dominio;

import org.json.JSONObject;

public abstract class User {
	protected String name;
	protected String email;
	protected String password;	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRol() {
		
		if(this.getClass().toString().contains("Admin")) {
			return "ADMIN";
		} else {
			return "ASISTENTE";
		}
		
	}
	
	public boolean isAdmin() {
		boolean res = false;
		if ("ADMIN".equals(this.getRol())) {
			res = true;
		}
		return res;
	}
	
	protected abstract JSONObject toJSON();
	
	
	
	

}
