package com.app.siget.cucumber.pruebas;

import static org.junit.Assert.assertEquals;

import com.app.siget.dominio.Manager;
import com.app.siget.persistencia.UserDAO;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class CasoDePruebaEliminarUsuario {
	@Given("^Como administrador borro el usuario \"(.*?)\"$")
	public void como_administrador_borro_el_usuario(String nombre) throws Throwable {
		Manager.get().register(nombre, "mail", "pass", "ADMI");
		Manager.get().eliminarUsuario(nombre);
		
	}

	@Then("^Se ha eliminado el usuario \"(.*?)\"$")
	public void se_ha_eliminado_el_usuario(String nombre) throws Throwable {
		assertEquals(null,UserDAO.findUser(nombre));
	}
	
	@Given("^Como admin borro el usuario \"(.*?)\"$")
	public void como_admin_borro_el_usuario(String nombre) throws Throwable {

		Manager.get().eliminarUsuario(nombre);
		
	}

	@Then("^El usuario \"(.*?)\" no existe$")
	public void el_usuario_no_existe(String nombre) throws Throwable {
		assertEquals(null,UserDAO.findUser(nombre));
	}
	@Given("^Como admin borro el admin \"(.*?)\"$")
	public void como_admin_borro_el_admin(String nombre) throws Throwable {
		Manager.get().eliminarUsuario(nombre);
	}

	@Then("^El usuario \"(.*?)\" no se ha borrado$")
	public void el_usuario_no_se_ha_borrado(String nombre) throws Throwable {
		assertEquals(nombre,UserDAO.findUser(nombre).getName());
	}

}