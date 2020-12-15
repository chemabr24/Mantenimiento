package com.app.siget.cucumber.pruebas;

import static org.junit.Assert.assertEquals;

import java.time.LocalTime;

import com.app.siget.dominio.Actividad;
import com.app.siget.dominio.Asistente;
import com.app.siget.dominio.DiaSemana;
import com.app.siget.dominio.Manager;
import com.app.siget.dominio.User;
import com.app.siget.excepciones.FranjaHorariaOcupadaException;
import com.app.siget.persistencia.ActividadDAO;
import com.app.siget.persistencia.UserDAO;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class CasoDePruebaAsistirReuniones {

	@Given("^el usuario \"([^\"]*)\" acepta la reunion \"([^\"]*)\"$")
	public void el_usuario_acepta_la_reunion(String nombre, String id) throws FranjaHorariaOcupadaException {
		LocalTime horaIni = LocalTime.of(Integer.parseInt("12"), Integer.parseInt("00"));
		LocalTime horaFin = LocalTime.of(Integer.parseInt("13"), Integer.parseInt("00"));
		Actividad act = new Actividad(Integer.parseInt(id),nombre, DiaSemana.valueOf("LUNES"), horaIni, horaFin,true,"2020-W50");
		ActividadDAO.insertarReunionPend((Asistente)UserDAO.findUser(nombre),act);
		Manager.get().aceptarReunion(nombre, Integer.parseInt(id));
	}

	@Then("^el usuario \"([^\"]*)\" tiene la reunion \"([^\"]*)\" en su agenda$")
	public void el_usuario_tiene_la_reunion_en_su_agenda(String usuario, String id) throws Throwable {
		User u = UserDAO.findUser(usuario);
		int[][] horario = ((Asistente) u).getHorario().getMatrizHorario();
		boolean reunion = false;
		for(int i =0; i< horario.length;i++) {
			for(int j =0; j<horario[i].length;j++) {
				if(horario[i][j] == Integer.parseInt(id)) {
					reunion = true;
				}
			}
		}
		assertEquals(true,reunion);
	}

	@Given("^el usuario \"([^\"]*)\" rechaza la reunion \"([^\"]*)\"$")
	public void el_usuario_rechaza_la_reunion(String nombre, String id) throws FranjaHorariaOcupadaException {
		LocalTime horaIni = LocalTime.of(Integer.parseInt("12"), Integer.parseInt("00"));
		LocalTime horaFin = LocalTime.of(Integer.parseInt("13"), Integer.parseInt("00"));
		Actividad act = new Actividad(Integer.parseInt(id),nombre, DiaSemana.valueOf("LUNES"), horaIni, horaFin,true,"2020-W50");
		ActividadDAO.insertarActividad(act);
		Manager.get().rechazarReunion(nombre, Integer.parseInt(id));
	}

	@Then("^el usuario \"([^\"]*)\" no tiene la reunion \"([^\"]*)\" en su agenda$")
	public void el_usuario_no_tiene_la_reunion_en_su_agenda(String usuario, String id) throws Throwable {
		User u = UserDAO.findUser(usuario);
		int[][] horario = ((Asistente) u).getHorario().getMatrizHorario();
		boolean reunion = false;
		for(int i =0; i< horario.length;i++) {
			for(int j =0; j<horario[i].length;j++) {
				if(horario[i][j] == Integer.parseInt(id)) {
					reunion = true;
				}
			}
		}
		assertEquals(false,reunion);
	}
	
	@Then("^el usuario \"(.*?)\" no tiene la reunion \"(.*?)\" en su agenda por coincidir la hora$")
	public void el_usuario_no_tiene_la_reunion_en_su_agenda_por_coincidir_la_hora(String usuario, String id) throws Throwable {
		User u = UserDAO.findUser(usuario);
		int[][] horario = ((Asistente) u).getHorario().getMatrizHorario();
		boolean reunion = false;
		for(int i =0; i< horario.length;i++) {
			for(int j =0; j<horario[i].length;j++) {
				if(horario[i][j] == Integer.parseInt(id)) {
					reunion = true;
				}
			}
		}
		assertEquals(false,reunion);
	}

}