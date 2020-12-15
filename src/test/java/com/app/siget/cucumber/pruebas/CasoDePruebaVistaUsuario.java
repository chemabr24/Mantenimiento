package com.app.siget.cucumber.pruebas;

import static org.junit.Assert.assertEquals;

import com.app.siget.dominio.Manager;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class CasoDePruebaVistaUsuario {
	
	@Given("^como usuario \"(.*?)\"$")
	public void como_usuario(String arg1) throws Throwable {

	}

	@Then("^veo la agenda del usuario \"(.*?)\"$")
	public void veo_la_agenda_del_usuario(String arg1) throws Throwable {
		assertEquals(false,Manager.get().leerActividades(arg1).isNull("actividades"));
	}

}