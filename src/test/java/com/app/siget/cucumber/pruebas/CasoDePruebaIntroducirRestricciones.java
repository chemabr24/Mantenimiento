package com.app.siget.cucumber.pruebas;

import static org.junit.Assert.assertEquals;

import java.time.LocalTime;
import java.util.ArrayList;

import com.app.siget.dominio.Actividad;
import com.app.siget.dominio.DiaSemana;
import com.app.siget.dominio.Manager;
import com.app.siget.persistencia.ActividadDAO;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CasoDePruebaIntroducirRestricciones {

	@Given("^\"(.*?)\", \"(.*?)\", \"(.*?)\", \"(.*?)\", \"(.*?)\", \"(.*?)\",\"(.*?)\", \"(.*?)\" involucrado y si es \"(.*?)\"$")
	public void involucrado_y_si_es(String nombre, String dia, String horaI, String minutosI, String horaF,
			String minutosF, String semana, String usuario, String reunion) throws Exception {
		try {
		Manager.get().insertarActividad(nombre, dia, horaI, minutosI, horaF, minutosF, usuario, reunion,semana);
		
		}catch(Exception e) {
		}
	}

	@Then("^se aniade la actividad no laborable con \"(.*?)\", \"(.*?)\", \"(.*?)\", \"(.*?)\", \"(.*?)\", \"(.*?)\", \"(.*?)\", \"(.*?)\" y se vincula al \"(.*?)\"$")
	public void se_aniade_la_actividad_no_laborable_con_y_se_vincula_al(String nombre, String dia, String horaI,
			String minutosI, String horaF, String minutosF, String semana, String reunion, String usuario)
			throws NumberFormatException {
		boolean comprobado = false;
		ArrayList<Actividad> actividades = ActividadDAO.leerActividades(usuario);
		while (!actividades.isEmpty()) {
			Actividad actividaduser = actividades.remove(0);
			if (actividaduser.getName().equals(nombre)) {
				comprobado = true;
			}
		}
		assertEquals(true, comprobado);
		// para eliminar la actividad que hemos insertado
		LocalTime horaIni = LocalTime.of(Integer.parseInt(horaI), Integer.parseInt(minutosI));
		LocalTime horaFin = LocalTime.of(Integer.parseInt(horaF), Integer.parseInt(minutosF));
		ActividadDAO.eliminar(new Actividad(nombre, DiaSemana.valueOf(dia), horaIni, horaFin, false,semana));

	}

	@When("^usuario no esta registrado$")
	public void usuario_no_esta_registrado() {

	}

	@Then("^no se aniade la actividad no laborable con \"(.*?)\", \"(.*?)\", \"(.*?)\", \"(.*?)\", \"(.*?)\", \"(.*?)\", \"(.*?)\", \"(.*?)\" y se vincula al \"(.*?)\"$")
	public void no_se_aniade_la_actividad_no_laborable_con_y_se_vincula_al(String nombre, String dia, String horaI,
			String minutosI, String horaF, String minutosF, String semana, String reunion, String usuario)
			throws NumberFormatException {

		boolean comprobado = false;
		try {
			ArrayList<Actividad> actividades = ActividadDAO.leerActividades(usuario);
			while (!actividades.isEmpty() && comprobado) {
				Actividad actividaduser = actividades.remove(0);
				if (actividaduser.getName().equals(nombre))
					comprobado = true;
			}
		} catch (Exception e) {
		}
		assertEquals(false, comprobado);
	}

	@When("^el dia no es correcto$")
	public void el_dia_no_es_correcto() {

	}

	
	@When("^la hora inicial no es correcta$")
	public void la_hora_inicial_no_es_correcta() {

	}

	
	
	@When("^la hora final no es correcta$")
	public void la_hora_final_no_es_correcta() {

	}

	
	
	
}
