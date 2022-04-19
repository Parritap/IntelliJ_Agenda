package co.edu.uniquindio.agenda.model;

public class Grupo {
	
	private String nombre;
	private Cita [] listaCitasGrupo;
	private Contacto [] listaContactosGrupo;
	private Agenda agenda;
	
	
	
	public Grupo(String nombre, int tamanioListaCitasGrupo, int tamanioListaContactosGrupo, Agenda agenda) {
		super();
		this.nombre = nombre;
		this.listaCitasGrupo = new Cita [tamanioListaCitasGrupo];
		this.listaContactosGrupo = new Contacto [tamanioListaContactosGrupo] ;
		this.agenda = agenda;
	}
	public Grupo(String nombre, int tamanioListaCitasGrupo, int tamanioListaContactosGrupo) {
		super();
		this.nombre = nombre;
		this.listaCitasGrupo = new Cita [tamanioListaCitasGrupo];
		this.listaContactosGrupo = new Contacto [tamanioListaContactosGrupo] ;
	}
	public Grupo(String nombre) {
		super();
		this.nombre = nombre;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Cita[] getListaCitasGrupo() {
		return listaCitasGrupo;
	}
	public void setListaCitasGrupo(Cita[] listaCitasGrupo) {
		this.listaCitasGrupo = listaCitasGrupo;
	}
	public Contacto[] getListaContactosGrupo() {
		return listaContactosGrupo;
	}
	public void setListaContactosGrupo(Contacto[] listaContactosGrupo) {
		this.listaContactosGrupo = listaContactosGrupo;
	}
	public Agenda getAgenda() {
		return agenda;
	}
	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}
	/**
	 * Metodo que asocia un contacto a la listaContactosGrupo despues de verificar que se pueda
	 * @param contacto
	 * @return
	 */
	public String asociarContactoGrupo (Contacto contacto){
		String mensaje = "no hay una lista definida en grupo";
		if (listaContactosGrupo != null){
			mensaje = "No hay cupo para el contacto";
			if (verificarHayCupoListaContactosGrupo ()){
				mensaje = "el contacto ya esta registrado";
				if (verificarExisteContacto (contacto)!= true){
					listaContactosGrupo [obtenerEspacioDisponibleListaContactosGrupo()] = contacto;
					mensaje = "el contacto fue registrado con exito";
				}
			}
		}
		return mensaje;
	}
	private boolean verificarHayCupoListaContactosGrupo() {
		boolean hayCupo = false;
		boolean bandera = true;
		if (listaContactosGrupo != null){
			for (int i =0; i<listaContactosGrupo.length && bandera;i+=1){
				if (listaContactosGrupo [i] == null){
					hayCupo = true;
					bandera = false;
				}
			}
		}
		return hayCupo;
	}
	private boolean verificarExisteContacto(Contacto contacto) {
		boolean yaExiste = false;
		if (listaContactosGrupo != null){
			for (int i =0; i<listaContactosGrupo.length;i+=1){
				if (listaContactosGrupo [i]!= null && listaContactosGrupo [i] == contacto){
					yaExiste = true;
				}
			}
		}
		return yaExiste;
	}
	public int obtenerEspacioDisponibleListaContactosGrupo (){
		int espacio = 0;
		if (listaContactosGrupo != null){
			for (int i =0; i<listaContactosGrupo.length;i+=1){
				if (listaContactosGrupo [i] == null){
					espacio = i;
				}
			}
		}
		return espacio;
		
	}
}
