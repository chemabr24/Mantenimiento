package com.app.siget.cucumber.pruebas;

import static org.junit.Assert.assertEquals;

import com.app.siget.dominio.Manager;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CasoDePruebaVistaGeneral {
	
	@Given("^como admin$")
	public void como_admin() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	   
	}

	@Then("^veo todas las reuniones$")
	public void veo_todas_las_reuniones() throws Throwable {
		
		assertEquals(false,Manager.get().leerReuniones().isNull(0));
	}



}