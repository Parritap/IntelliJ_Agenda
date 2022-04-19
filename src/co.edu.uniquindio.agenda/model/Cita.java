package co.edu.uniquindio.agenda.model;

import java.util.Arrays;

public class Cita {
	private String fecha;
	private String hora;
	private String asunto;
	private Grupo grupoCita;
	private Contacto [] listaContactosCita;
	private Agenda agenda;
	
	

	public Cita(String fecha, String hora, String asunto, Grupo grupoCita, int tamanioListaContactosCita,
			Agenda agenda) {
		super();
		this.fecha = fecha;
		this.hora = hora;
		this.asunto = asunto;
		this.grupoCita = grupoCita;
		this.listaContactosCita = new Contacto [tamanioListaContactosCita];
		this.agenda = agenda;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public Grupo getGrupoCita() {
		return grupoCita;
	}

	public void setGrupoCita(Grupo grupoCita) {
		this.grupoCita = grupoCita;
	}

	public Contacto[] getListaContactosCita() {
		return listaContactosCita;
	}

	public void setListaContactosCita(Contacto[] listaContactosCita) {
		this.listaContactosCita = listaContactosCita;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	@Override
	public String toString() {
		return "Cita [fecha=" + fecha + ", hora=" + hora + ", asunto=" + asunto + ", grupoCita=" + grupoCita
				+ ", listaContactosCita=" + Arrays.toString(listaContactosCita) + ", agenda=" + agenda + "]";
	}
	
	
	
	
	
	
}
