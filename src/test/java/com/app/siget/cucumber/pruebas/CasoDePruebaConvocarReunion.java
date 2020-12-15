package com.app.siget.cucumber.pruebas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.time.LocalTime;
import java.util.ArrayList;

import com.app.siget.dominio.Actividad;
import com.app.siget.dominio.Asistente;
import com.app.siget.dominio.DiaSemana;
import com.app.siget.dominio.Manager;
import com.app.siget.persistencia.ActividadDAO;
import com.app.siget.persistencia.UserDAO;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CasoDePruebaConvocarReunion {	
	
	@Given("^los datos \"(.*?)\", \"(.*?)\",  \"(.*?)\",  \"(.*?)\",  \"(.*?)\",  \"(.*?)\", \"(.*?)\" y \"(.*?)\"$")
	public void los_datos_y(String nombre, String dia, String horaI, String minutosI, String horaF,
			String minutosF, String semana, String usuario) throws NumberFormatException {
		String user = "["+usuario+"]";
		
		try {
			Manager.get().convocarReunion(nombre, dia, horaI, minutosI, horaF, minutosF, user, "true", semana);
		}catch(Exception e) {
			
		}
		
	}
	
	@Then("^Crea una reunion \"(.*?)\", \"(.*?)\",  \"(.*?)\",  \"(.*?)\",  \"(.*?)\",  \"(.*?)\", \"(.*?)\" y \"(.*?)\"$")
	public void crea_una_reunion(String nombre, String dia, String horaI, String minutosI, String horaF,
			String minutosF, String semana, String usuarios) throws Throwable {
		Asistente asistente = (Asistente)UserDAO.findUser(usuarios);
		
		assertEquals(1,asistente.getReunionesPendientes().size());
		LocalTime horaIni = LocalTime.of(Integer.parseInt("12"), Integer.parseInt("00"));
		LocalTime horaFin = LocalTime.of(Integer.parseInt("13"), Integer.parseInt("00"));
		Actividad act = new Actividad(nombre, DiaSemana.valueOf("LUNES"), horaIni, horaFin,true, semana);
		ActividadDAO.eliminar(act);
	}


	@When("^el dia no existe$")
	public void el_dia_no_existe() throws Throwable {
	    
	}

   

	@When("^la hora inicial no existe$")
	public void la_hora_inicial_no_existe() throws Throwable {
	    
	}
	
	@When("^la hora final no existe$")
	public void la_hora_final_no_existe() throws Throwable {
	      
	}
	
	
	@Then("^no se registra la reunion \"(.*?)\", \"(.*?)\",  \"(.*?)\",  \"(.*?)\",  \"(.*?)\",  \"(.*?)\", \"(.*?)\" y \"(.*?)\"$")
	public void no_se_registra_la_reunion_y(String nombre, String dia, String horaI, String minutosI, String horaF,
			String minutosF, String semana, String usuarios) throws Throwable {
		boolean comprobado = false;
		ArrayList<Actividad> actividades = ActividadDAO.leerActividades(usuarios);

		while (!actividades.isEmpty()) {
			Actividad actividaduser = actividades.remove(0);
			if (actividaduser.getName().equals(nombre) && actividaduser.getDia().equals(dia) && actividaduser.getSemana().equals(semana))
				comprobado = true;
		}
		assertEquals(false, comprobado);
	}
	

	
}