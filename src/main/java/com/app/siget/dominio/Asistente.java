package com.app.siget.dominio;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import com.app.siget.excepciones.FranjaHorariaOcupadaException;

public class Asistente extends User {

	private Horario horario;
	protected List<Integer> reunionesPendientes;

	public Asistente(String name, String email, String password) {

		this.name = name;
		this.email = email;
		this.password = password;
		this.reunionesPendientes = new ArrayList<>();
		this.horario = new Horario();
	}

	public List<Integer> getReunionesPendientes() {
		return reunionesPendientes;
	}

	public void setReunionesPendientes(String reunionesPendientes) {
		if (reunionesPendientes != null) {
			JSONArray jsa = new JSONArray(reunionesPendientes);
			for (int i = 0; i < jsa.length(); i++) {
				this.reunionesPendientes.add(jsa.getInt(i));
			}
		}

	}

	public void insertarActividad(Actividad actividad) throws FranjaHorariaOcupadaException {

		if (!this.horario.estaOcupado(actividad)) {
			this.horario.insertarActividad(actividad);
		} else {
			throw new FranjaHorariaOcupadaException();
		}

	}

	public void insertarReunionPendiente(Actividad a) {
		this.reunionesPendientes.add(a.getId());

	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	protected JSONObject toJSON() {
		JSONObject jso = new JSONObject();
		jso.put("name", this.getName());
		jso.put("email", this.getEmail());
		jso.put("password", this.getPassword());
		jso.put("rol", this.getRol());
		return jso;
	}

	public void quitarReunionPendiente(int id) {
		for (int i = 0; i < this.reunionesPendientes.size(); i++) {
			if (this.reunionesPendientes.get(i) == id) {
				this.reunionesPendientes.remove(i);
			}
		}
	}
}
