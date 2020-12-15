package com.app.siget.cucumber.pruebas;

import static org.junit.Assert.assertEquals;

import com.app.siget.dominio.Asistente;
import com.app.siget.dominio.Manager;
import com.app.siget.dominio.User;
import com.app.siget.persistencia.UserDAO;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CasoDePruebaAcenderUsuario {


	@Given("^un administrador asciende al usuario \"([^\"]*)\"$")
	public void un_administrador_asciende_al_usuario(String nombre) throws Throwable {
		
		Manager.get().ascenderUsuario(nombre);
	}

	@Then("^el usuario \"([^\"]*)\" ahora es administrador$")
	public void el_usuario_ahora_es_administrador(String nombre) throws Throwable {
		
		assertEquals(true, Manager.get().isAdmin(nombre));
		
	}

	@When("^el usuario \"([^\"]*)\" ya es administrador$")
	public void el_usuario_ya_es_administrador(String nombre) throws Throwable {
		
	}

	@Then("^el usuario \"([^\"]*)\" sigue siendo administrador$")
	public void el_usuario_sigue_siendo_administrador(String nombre) throws Throwable {
		assertEquals(true, Manager.get().isAdmin(nombre));
		User u = UserDAO.findUser(nombre);
		Asistente user = new Asistente(u.getName(), u.getEmail(), u.getPassword());
		UserDAO.modificar(user);
		System.out.println("El usuario se ha cambiado a no admin otra vez false"+UserDAO.findUser(nombre).isAdmin());
	}

}
