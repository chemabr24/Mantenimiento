package com.app.siget.cucumber.pruebas; 

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.app.siget.dominio.Manager;
import com.app.siget.dominio.User;
import com.app.siget.persistencia.UserDAO;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CasoDePruebaModificarUsuario {
	
	@Given("^Como admin en la vista del user \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void como_adnmin_en_la_vista_del_user(String nombre, String email, String password) throws Throwable {
	   
	}
	
	@When("^Como admin el user\"(.*?)\"con contraseniaa \"(.*?)\" ha sido modificado \"(.*?)\"$")
	public void como_admin_el_user_con_contraseniaa_ha_sido_modificado(String nombre, String emailNuevo, String contrasena) throws Throwable {
		Manager.get().modificarUsuario(nombre,emailNuevo,contrasena);
	}

	@Then("^Como admin el user\"(.*?)\"ha sido modificado el correo \"(.*?)\" correctamente$")
	public void como_admin_el_user_ha_sido_modificado_el_correo_correctamente(String nombre, String email) throws Throwable {
		User user = UserDAO.findUser(nombre);
		assertNotEquals(email,user.getEmail());
		Manager.get().modificarUsuario(user.getName(),user.getEmail(),"Password1");
	}
	
	
	
	@When("^Como admin el user \"(.*?)\" con el correo \"(.*?)\"  ha sido modificado \"(.*?)\"$")
	public void como_admin_el_user_con_el_correo_ha_sido_modificado(String nombre, String emailNuevo, String contrasena) {
		Manager.get().modificarUsuario(nombre,emailNuevo,contrasena);
	}

	@Then("^Como admin el user \"(.*?)\" ha sido modificado la contraseniaa \"(.*?)\" correctamente$")
	public void como_admin_el_user_ha_sido_modificado_la_contraseniaa_correctamente(String nombre, String contrasena) throws Throwable {
		User user = UserDAO.findUser(nombre);
		assertNotEquals(Manager.get().encriptarMD5(contrasena),user.getPassword());
		Manager.get().modificarUsuario(user.getName(),user.getEmail(),contrasena);
	}



	

	
}