package com.app.siget.cucumber.pruebas;

import static org.junit.Assert.assertNotEquals;

import com.app.siget.dominio.Manager;
import com.app.siget.dominio.User;
import com.app.siget.persistencia.UserDAO;

import cucumber.api.java.en.When;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class CasoDePruebaModificarDatos {
	
	@Given("^En la vista user \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void en_la_vista_user(String nombre, String email, String password) throws Throwable {
	   
	}
	
	@When("^El user \"([^\"]*)\" con contrasenia \"([^\"]*)\" ha modificado \"([^\"]*)\"$")
	public void el_user_con_contrasenia_ha_modificado(String nombre, String contrasena, String emailNuevo) {
		Manager.get().modificarUsuario(nombre,emailNuevo,contrasena);
	}

	@Then("^El user \"([^\"]*)\" ha modificado el correo \"([^\"]*)\" correctamente$")
	public void el_user_ha_modificado_el_correo_correctamente(String nombre, String email) throws Throwable {
		User user = UserDAO.findUser(nombre);
		assertNotEquals(email,user.getEmail());
		Manager.get().modificarUsuario(user.getName(),email,"Password1");
	}
	
	
	
	@When("^El user \"(.*?)\" con el correo \"(.*?)\"  ha modificado \"(.*?)\"$")
	public void el_user_con_el_correo_ha_modificado(String nombre, String emailNuevo, String contrasena) {
		Manager.get().modificarUsuario(nombre,emailNuevo,contrasena);
	}

	@Then("^El user \"([^\"]*)\" ha modificado la contrasenia \"([^\"]*)\" correctamente$")
	public void el_user_ha_modificado_la_contrasenia_correctamente(String nombre, String contrasena) throws Throwable {
		User user = UserDAO.findUser(nombre);
		assertNotEquals(Manager.get().encriptarMD5(contrasena),user.getPassword());
		Manager.get().modificarUsuario(user.getName(),user.getEmail(),contrasena);
	}
	
}