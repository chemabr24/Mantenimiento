package com.app.siget.cucumber.pruebas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONObject;

import com.app.siget.dominio.Manager;
import com.app.siget.dominio.User;
import com.app.siget.excepciones.DiferentesContrasenasException;
import com.app.siget.persistencia.UserDAO;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CasoDePruebaRegister {

	// Caso 1
	@Given("^el usuario \"(.?)\",\"(.?)\",\"(.?)\", \"(.?)\", \"(.*?)\"$")
	public void los_datos(String nombre, String email, String password, String passwordConfirm, String rol)
			throws Throwable {
		
			Manager.get().register(nombre, email, password, rol);
		}

	

	@Then("^el usuario se ha creado correctamente \"(.*?)\"$")
	public void el_usuario_se_ha_creado_correctamente(String nombre) throws Throwable {
		assertNotEquals(null, UserDAO.findUser(nombre));
	}
	@Given("^el usuario \"(.*?)\",\"(.*?)\",\"(.*?)\", \"(.*?)\", \"(.*?)\"$")
	public void el_usuario(String nombre, String email, String password, String passwordConfirm, String rol) throws Throwable {
		Manager.get().register(nombre, email, password, rol);
	}
	@Then("^ya existe el usuario \"(.*?)\"$")
	public void ya_existe_el_usuario(String nombre) {
		int repeticiones=0;
		ArrayList<User> listausers= (ArrayList) UserDAO.leerUsers();
		for(int i=0; i<listausers.size();i++) {
			if(nombre.equals(listausers.get(i).getName()))
				repeticiones ++;
		}
		assertEquals(1,repeticiones);

	}

}
