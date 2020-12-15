package com.app.siget.cucumber.pruebas;

import static org.junit.Assert.assertEquals;

import com.app.siget.dominio.Manager;
import com.app.siget.excepciones.CredencialesInvalidasException;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CasoDePruebaLogin {
	boolean exception = false;
	
	//Caso 1
	@Given("^acceso con \"([^\"]*)\" y \"([^\"]*)\" correctos asistente$")
	public void acceso_con_y_correctos_asistente(String nombre, String password){
	    try {
		Manager.get().login(nombre, password);
	    }catch(Exception e) {
	    	
            exception = true;
        }
	}

	@When("^los datos son correctos y de un asistente$")
	public void los_datos_son_correctos_y_de_un_asistente() {
	}

	@Then("^Accedo a la pantalla principal de asistente$")
	public void accedo_a_la_pantalla_principal_de_asistente() {
		assertEquals(false, exception);
	}
	
	
	//Caso 2
	@Given("^acceso con \"([^\"]*)\" y \"([^\"]*)\" correctos admin$")
	public void acceso_con_y_correctos_admin(String nombre, String password){
		exception = false;
		  try {
				Manager.get().login(nombre, password);
			    }catch(Exception e) {
		            exception = true;
		        }
	}

	@When("^los datos son correctos y de un administrador$")
	public void los_datos_son_correctos_y_de_un_administrador() {
	}

	@Then("^Accedo a la pantalla principal de admin$")
	public void accedo_a_la_pantalla_principal_de_admin() {
		assertEquals(false, exception);
	}
	
	
	//Caso 3
	@Given("^acceso con \"([^\"]*)\" correcto y \"([^\"]*)\" incorrecto$")
	public void acceso_con_correcto_y_incorrecto(String nombre, String password){
		exception = false;
		  try {
				Manager.get().login(nombre, password);
			    }catch(Exception e) {
		            exception = true;
		        }
	}

	@When("^intento acceder con el \"([^\"]*)\" correcto y \"([^\"]*)\" mal$")
	public void intento_acceder_con_el_correcto_y_mal(String arg1, String arg2) {

	}

	@Then("^se lanza la excepcion CredencialesInvalidas$")
	public void se_lanza_la_excepcion_CredencialesInvalidas() {
		assertEquals(true, exception);
	}
	
	//Caso 4
	@Given("^acceso con \"([^\"]*)\" incorrecto y \"([^\"]*)\" correcto$")
	public void acceso_con_incorrecto_y_correcto(String nombre, String password){
		exception = false;
		  try {
				Manager.get().login(nombre, password);
			    }catch(Exception e) {
		            exception = true;
		        }
	}

	@When("^intento acceder con el \"([^\"]*)\" mal y \"([^\"]*)\" bien$")
	public void intento_acceder_con_el_mal_y_bien(String arg1, String arg2) {

	}

	@Then("^se lanza la excepcion de CredencialesInvalidas$")
	public void se_lanza_la_excepcion_de_CredencialesInvalidas() {
		assertEquals(true, exception);
	}
}