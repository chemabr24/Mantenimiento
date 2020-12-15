package com.app.siget.cucumber.pruebas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import com.app.siget.dominio.Manager;
import com.app.siget.dominio.User;
import com.app.siget.persistencia.UserDAO;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class CasoDePruebaAniadirUsuario {

	// Caso 1
	@Given("^En la vista admin \"(.*?)\",\"(.*?)\",\"(.*?)\", \"(.*?)\" y \"(.*?)\"$")
	public void en_la_vista_admin_y(String nombre, String email, String password,
			String passwordConfirm, String rol) throws Throwable {
			Manager.get().register(nombre, email, password, rol);
	
	}


	@Then("^el usuario se ha creado el usuario correctamente \"(.*?)\"$")
	public void el_usuario_se_ha_creado_el_usuario_correctamente(String nombre) throws Throwable {
		System.out.println(UserDAO.findUser(nombre).toString());
		assertEquals(nombre,UserDAO.findUser(nombre).getName());
		UserDAO.eliminar(UserDAO.findUser(nombre), true);
	}



	
	@Then("^no se ha registrado el usuario vacio \"(.*?)\"$")
	public void no_se_ha_registrado_el_usuario_vacio(String nombre) throws Throwable {
		assertNotEquals(nombre,UserDAO.findUser(nombre).getName());
		
	}
	
	
	@Then("^no se registrado el usuario \"(.*?)\"$")
	public void sno_se_ha_registrado_el_usuario(String nombre) throws Throwable {
		int repeticiones=0;
		ArrayList<User> listausers= (ArrayList) UserDAO.leerUsers();
		for(int i=0; i<listausers.size();i++) {
			if(nombre.equals(listausers.get(i).getName()))
				repeticiones ++;
		}
		assertEquals(1,repeticiones);

	}
		

}
