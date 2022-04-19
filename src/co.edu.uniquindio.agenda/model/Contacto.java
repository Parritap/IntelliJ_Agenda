package co.edu.uniquindio.agenda.model;

import java.util.Arrays;

public class Contacto {


	private String nombre;
	private String correo;
	private String direccion;
	private int edad;
	private String telefono;
	private Agenda agenda;
	private Grupo [] listaGruposContacto;
	private Cita [] listaCitasContacto;
	/**
	 *
	 * @param nombre
	 * @param correo
	 * @param direccion
	 * @param telefono
	 */
	


	public String getNombre() {
		return nombre;
	}

	public Contacto(String nombre, String correo, String direccion, int edad, String telefono,
			int tamanioListaGruposContacto, int tamanioListaCitasContacto, Agenda agenda) {
		super();
		this.nombre = nombre;
		this.correo = correo;
		this.direccion = direccion;
		this.edad = edad;
		this.telefono = telefono;
		this.listaGruposContacto = new Grupo [tamanioListaGruposContacto];
		this.listaCitasContacto = new Cita [tamanioListaCitasContacto] ;
		this.agenda = agenda;
	}
	public Contacto (String nombre, String correo, String direccion, int edad, String telefono,Agenda agenda){
		super();
		this.nombre = nombre;
		this.correo = correo;
		this.direccion = direccion;
		this.edad = edad;
		this.telefono = telefono;
		this.agenda = agenda;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public Grupo[] getListaGruposContacto() {
		return listaGruposContacto;
	}

	public void setListaGruposContacto(Grupo[] listaGruposContacto) {
		this.listaGruposContacto = listaGruposContacto;
	}

	public Cita[] getListaCitasContacto() {
		return listaCitasContacto;
	}

	public void setListaCitasContacto(Cita[] listaCitasContacto) {
		this.listaCitasContacto = listaCitasContacto;
	}



	@Override
	public String toString() {
		return "Contacto [nombre=" + nombre + ", correo=" + correo + ", direccion=" + direccion + ", edad=" + edad
				+ ", telefono=" + telefono + ", agenda=" + agenda + ", listaGruposContacto="
				+ Arrays.toString(listaGruposContacto) + ", listaCitasContacto=" + Arrays.toString(listaCitasContacto)
				+ "]";
	}
	



}
