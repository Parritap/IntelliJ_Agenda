package co.edu.uniquindio.agenda.model;

import java.util.Arrays;

import co.edu.uniquindio.agenda.exceptions.ContactoExcepction;

public class Agenda {


	private String titulo;
	private Contacto[] listaContactos;
	private Cita [] listaCitas;
	private Grupo [] listaGrupos;


	public Agenda(String titulo, int numContactos, int numGrupos, int numCitas) {

		this.titulo = titulo;
		this.listaContactos = new Contacto[numContactos];
		this.listaCitas = new Cita [numCitas];
		this.listaGrupos = new Grupo [numGrupos]; 
	}
	public Agenda(String titulo) {

		this.titulo = titulo;
	}


 public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getNumContactos() {
		return listaContactos.length;
	}

	public int getNumGrupos() {
		return listaGrupos.length;
	}

	public int getNumCitas() {
		return listaCitas.length;
	}


	public Contacto[] getListaContactos() {
		return listaContactos;
	}

	public void setListaContactos(Contacto[] listaContactos) {
		this.listaContactos = listaContactos;
	}

	public Cita[] getListaCitas() {
		return listaCitas;
	}

	public void setListaCitas(Cita[] listaCitas) {
		this.listaCitas = listaCitas;
	}

	public Grupo[] getListaGrupos() {
		return listaGrupos;
	}

	public void setListaGrupos(Grupo[] listaGrupos) {
		this.listaGrupos = listaGrupos;
	}


	@Override
	public String toString() {
		return "Agenda{" +
				"titulo='" + titulo + '\'' +
				", listaContactos=" + Arrays.toString(listaContactos) +
				", listaCitas=" + Arrays.toString(listaCitas) +
				", listaGrupos=" + Arrays.toString(listaGrupos) +
				'}';
	}

	/**
  * metdodo que crea el contacto y lo pone en la listaContactos
  * @param nombre
  * @param correo
  * @param direccion
  * @param edad
  * @param telefono
  * @param tamanioListaGruposContacto
  * @param tamanioListaCitasContacto
  * @return
  */

	/*
	Exepciones posibles.
	 - Strings vacios.
	 - Edad menor que 0.
	 - Telefono tiene letras.
	 */
	public String crearContacto(String nombre, String correo,String direccion,
							  int edad,String telefono,int tamanioListaGruposContacto,
							  int tamanioListaCitasContacto,Agenda agenda) {
		String mensaje = "El contacto fue creado";
		int posicionDisponible = 0;
		boolean existeContacto = false;

		posicionDisponible = obtenerPosicionDisponibleContacto();

		if(posicionDisponible == -1){
			mensaje = "No hay posiciobes disponibles";
		}else{

			existeContacto = verificarExistenciaContacto(telefono,nombre);
			if(existeContacto == false){
				listaContactos[posicionDisponible] = new Contacto(nombre, correo,  direccion,edad, telefono, tamanioListaGruposContacto, tamanioListaCitasContacto,agenda);
			}else{
				mensaje = "Ya existe un contacto con este numero de telefono";
			}
		}

		return mensaje;
	}
	public String crearContactoSinListas(String nombre, String correo,String direccion, int edad,String telefono, Agenda agenda) {
		String mensaje = "El contacto fue creado";
		int posicionDisponible = 0;
		boolean existeContacto = false;

		posicionDisponible = obtenerPosicionDisponibleContacto();

		if(posicionDisponible == -1){
			mensaje = "No hay posiciobes disponibles";
		}else{

			existeContacto = verificarExistenciaContacto(telefono,nombre);
			if(existeContacto == false){
				listaContactos[posicionDisponible] = new Contacto(nombre, correo,  direccion,edad, telefono,agenda);
			}else{
				mensaje = "Ya existe un contacto con este numero de telefono";
			}
		}

		return mensaje;
	}
/**
 * Obtiene un contacto dado el telefono
 * @param telefono
 * @return
 */
	private Contacto obtenerContacto(String telefono) {

		for (Contacto contacto : listaContactos) {
			if(contacto != null && contacto.getTelefono().equals(telefono)){
				return contacto;
			}
		}

		return null;
	}


/**
 * verifica la existencia de un contacto por el telefono
 * @param telefono
 * @return
 */
	public boolean verificarExistenciaContacto(String telefono, String nombre) {

		for (Contacto contacto : listaContactos) {
			if(contacto != null && contacto.getTelefono().equals(telefono) || contacto.getNombre().equals(nombre)){
				return true;
			}
		}

		return false;
	}
	public boolean verificarExistenciaContactoSoloTelefono(String telefono) {

		for (Contacto contacto : listaContactos) {
			if(contacto != null && contacto.getTelefono().equals(telefono) ){
				return true;
			}
		}

		return false;
	}



/**
 * busca una posicion disponible en listaContactos
 * @return
 */
	private int obtenerPosicionDisponibleContacto() {

		int pos = -1;

		for (int i = 0; i < listaContactos.length; i++) {
			if(listaContactos [i] == null){
				return i;
			}
		}

		return pos;
	}

	
/**
 * Elimina un contacto segun telefono
 * @param telefono
 * @return
 * @throws ContactoExcepction
 */
	public String eliminarContacto (String telefono) throws ContactoExcepction{

		boolean existeContacto = false;

		existeContacto = verificarExistenciaContactoSoloTelefono(telefono);

		if(existeContacto == false){
			throw new ContactoExcepction("El contacto no existe");
		}else{

			int posContacto = obtenerPosContacto(telefono);

			listaContactos [posContacto] = null;
		}

		return "Contacto eliminado";
	}
	/**
	 * encuentra la posicion de un contacto en listaContacto dado el telefono
	 * @param telefono
	 * @return
	 */
	private int obtenerPosContacto(String telefono) {


		int pos = 0;
		Contacto contactoAuxliar = null;

		for (int i = 0; i < listaContactos.length; i++) {
			contactoAuxliar = listaContactos[i];
			if(contactoAuxliar != null && contactoAuxliar.getTelefono().equals(telefono)){
				return i;
			}
		}
		return pos;
	}


/**
 * Actualiza el contacto
 * @param telefonoAnterior
 * @param telefonoNuevo
 * @param nombreNuevo
 * @param correpoNuevo
 * @param direccionNueva
 * @return
 * @throws ContactoExcepction
 */
	public String actualizarContacto(String telefonoAnterior, String telefonoNuevo,
			String nombreNuevo,String correpoNuevo,String direccionNueva) throws ContactoExcepction{

		String mensaje = "";
		Contacto contactoEncontrado= null;

		contactoEncontrado = obtenerContacto(telefonoAnterior);

		if(contactoEncontrado == null){
			 throw new ContactoExcepction("El contacto no existe");
		}
		else{
			 contactoEncontrado.setNombre(nombreNuevo);
			 contactoEncontrado.setCorreo(correpoNuevo);
			 contactoEncontrado.setDireccion(direccionNueva);
			 contactoEncontrado.setTelefono(telefonoNuevo);
		}

		return "El contacto ha sido actualizado";

	}
	/**
	 * metdo que dado un contacto, verifica si ya esta
	 * @param c
	 * @return
	 */
	public boolean existeContacto (Contacto c){
		boolean existe = false;
		if (listaContactos != null){
			for (int i = 0; i<listaContactos.length;i+=1){
				if (listaContactos [i] != null && listaContactos [i]==c){
					existe = true;
				}
			}
		}
		return existe;
	}
	/**
	 * Metodo que retorna un stirng con todos los contactos impares
	 * @return
	 */
	public String mostrarContactosImpares (){
		String contactosImpares = "";
		for (int i =0 ; i<listaContactos.length;i+=1){
			if (i%2 !=0){
				contactosImpares += listaContactos [i];
			}
		}
		return contactosImpares;
	}
	/**
	 * metodo que determina la edad mas repetida entre odos los contactos
	 * @return
	 */
	public int determinarEdadMasRepetida (){
		int edadMasRepetida = -1;
		int aux = -1;
		if (listaContactos != null){
			for (int i = 0; i<listaContactos.length;i+=1){
				if (listaContactos [i]!= null && listaContactos [i].getEdad() > aux){
					aux = listaContactos [i].getEdad();
				}
			}
		}
		edadMasRepetida = aux;
		return edadMasRepetida;
	}
	/**
	 * Metodo que determina el promedio de todas las edades de los contactos
	 * @return
	 */
	public double determinarPromedioEdades (){
		double promedio = 0;
		double aux = 0;
		int contador=0;
		if (listaContactos != null){
			for (int i = 0; i<listaContactos.length;i+=1){
				if (listaContactos [i]!= null){
					aux += listaContactos [i].getEdad();
					contador+=1;
				}
			}
		}
		if (contador != 0){
			promedio = aux/contador;
		}
		return promedio;
	}
	/**
	 * metodo que obtiene un arreglo con todos los contactos menores de edad
	 * @return
	 */
	public Contacto [] obtenerMenoresEdad (){
		Contacto [] menoresEdad= new Contacto [determinarCantidadMenoresEdad()];
		int contador =0;
		if (listaContactos != null){
			for (int i = 0; i<listaContactos.length;i+=1){
				if (listaContactos [i]!= null && listaContactos [i].getEdad() <18){
					menoresEdad [contador] = listaContactos [i];
					contador+=1;
				}
			}
		}
		return menoresEdad;
	}
	/**
	 * metodo que calcula la cantidad de menores de edad en listaContactos
	 * @return
	 */
	public int determinarCantidadMenoresEdad (){
		int cantidadMenores = 0;
		if (listaContactos != null){
			for (int i = 0; i<listaContactos.length;i+=1){
				if (listaContactos [i]!= null && listaContactos [i].getEdad() <18){
					cantidadMenores += 1;
				}
			}
		}
		return cantidadMenores;
	}
	/**
	 * Metodo que retorna un String con el nombre de todos los contactos invertido
	 * @return
	 */
	public String mostrarNombreContactoInvertido (){
		String nombresInvertidos = "";
		if (listaContactos != null){
			for (int i = 0; i<listaContactos.length;i+=1){
				if (listaContactos [i]!= null ){
					nombresInvertidos += invertirNombre (listaContactos [i].getNombre());
				}
			}
		}
		return nombresInvertidos;
	}
	/**
	 * Metodo que dado un nombre, lo invierte
	 * @param nombre
	 * @return
	 */
	public String invertirNombre(String nombre) {
		String nombreInvertido ="";
		for (int i = nombre.length()-1; i>0;i-=1){
			nombreInvertido += nombre.charAt(i);
		}
		return nombreInvertido;
	}
	/**
	 * Metodo que dada una frase es String, la converte en un arreglo de caracteres
	 * @param frase
	 * @return
	 */
	public  char[] pasarFraseArreglo (String frase){
		char [] arregloFrase = frase.toCharArray();
		/*char [] arregloFrase = new char [frase.length()];
		for (int i = 0; i<frase.length();i+=1){
			arregloFrase [i] = frase.charAt(i);
			
		}
	*/
		return arregloFrase;
	}
	/**
	 * metodo que asocia un contacto a un grupo dado el contacto y el nombre del grupo
	 * @param contacto
	 * @param nombreGrupo
	 * @return
	 */
	public String asociarContactoGrupo (Contacto contacto, String nombreGrupo){
		String mensaje = "";
		Grupo grupo = obtenerGrupoDadoNombre (nombreGrupo);
		if (grupo != null){
			mensaje = grupo.asociarContactoGrupo(contacto);
		}
		return mensaje;
		
	}
	/**
	 * metodo que obtiene un grupo dado su nombre
	 * @param nombreGrupo
	 * @return
	 */
	public Grupo obtenerGrupoDadoNombre (String nombreGrupo){
		Grupo grupo = null;
		if (listaGrupos != null){
			for (int i = 0; i<listaGrupos.length;i+=1){
				if (listaGrupos [i]!= null && listaGrupos [i].getNombre().equals(nombreGrupo)){
					grupo = listaGrupos [i];
				}
			}
		}
		
		return grupo;
	}
	/**
	 * Mètodo que calcula la desviación estandar, es decir:
	 * la raíz cuadrada de la suma de los cuadrados de las diferencias de cada dato con el promedio
	 * dividido entre la cantidad de datos
	 * @return
	 */
	public double calcularDesviacionEdades (){
		double desviacion =0;
		double promedio = determinarPromedioEdades ();
		double aux =0;
		double promedioDistancias = 0;
		int contador = 0;
		if (promedio != 0){
			if (listaContactos != null){
				for (int i = 0; i<listaContactos.length;i+=1){
					if (listaContactos [i]!= null){
						aux = Math.pow((listaContactos [i].getEdad() - promedio),2);
						promedioDistancias +=aux;
						contador ++;
					}
				}
			}
		}
		if (contador != 0){
			promedioDistancias = promedioDistancias/contador;
			desviacion = Math.pow(promedioDistancias, (1/2));
		}
		return desviacion;
	}

	/**
	 * Mètodo que crea un grupo dado el nombre
	 * @param nombre
	 * @return
	 */
	public String crearGrupo(String nombre, Agenda agenda) {
		String mensaje = "no hay una lista de grupos definida aún";
		if (listaGrupos != null){
			mensaje ="no se puedo crear el grupo, no hay cupo";
			int posicion = obtenerPosicionDisponibleGrupo();
			if (posicion!=-1){
				mensaje = "el grupo no se creó porque ya existía";
				if (verificarExisteGrupo (nombre) != true){
					listaGrupos [posicion]= new Grupo (nombre,listaCitas.length,listaContactos.length,agenda);
					mensaje = "se creó el grupo con èxito";
				}
			}
		}

		return mensaje;
	}
	
	/**
	 * Mètodo que obtiene las posicionesl libres de grupo
	 * @return
	 */
	private int obtenerPosicionDisponibleGrupo() {
		int pos = -1;
		for (int i = 0; i < listaGrupos.length; i++) {
			if(listaGrupos [i] == null){
				return i;
			}
		}
		return pos;
	}
	/**
	 * mètodo que verifica si ya existe el grupo
	 * @param nombre
	 * @return
	 */
	public boolean verificarExisteGrupo (String nombre){
		boolean yaExiste = false;
		if  (listaGrupos !=null){
			for  (int i = 0; i<listaGrupos.length;i++){
				if (listaGrupos[i]!= null && listaGrupos[i].getNombre().equals(nombre)){
					yaExiste = true;
				}
			}
		}

		return yaExiste;
	}
	/**
	 * mètodo que obtiene los nombres de los grupos
	 * @return
	 */
	public String[] obtenerNombresGrupos() {
		String [] nombresGrupos = new String [determinarCantidadNombresGrupos ()];
		int contador = 0;
		if (listaGrupos != null){
			for (int i = 0; i < listaGrupos.length; i++){
				if (listaGrupos [i]!= null && listaGrupos[i].getNombre() != null){
					nombresGrupos [contador]=listaGrupos [i].getNombre();
					contador+=1;
				}
			}
		}
		return nombresGrupos;
	}
	/**
	 * mètodo que determina la cantidad de nombres de grupos que hay
	 * @return
	 */
	public int determinarCantidadNombresGrupos() {
		int cantidadNombresGrupos = 0;
		if (listaGrupos != null){
			for (int i = 0; i < listaGrupos.length; i++){
				if (listaGrupos[i]!= null && listaGrupos[i].getNombre() != null){
					cantidadNombresGrupos+=1;
				}
			}
		}
		return cantidadNombresGrupos;
	}
	/**
	 * Mètodo que obtiene los telefono de los contactos
	 * @return
	 */
	public String[] obtenerTelefonoContactos() {
		String[] telefonoContactos = new String [definirCantidadTelefonosContactos()];
		int contador =0;
		if (listaContactos != null){
			for (int i = 0; i < listaContactos.length; i++){
				if (listaContactos[i]!= null && listaContactos[i].getTelefono() != null){
					telefonoContactos [contador]= listaContactos [i].getTelefono();
					contador+=1;
				}
			}
		}
		return telefonoContactos;
	}
	/**
	 * mètodo que obtiene la cantidad de teléfonos que hay en total por todos los contactos
	 * @return
	 */
	public int definirCantidadTelefonosContactos() {
		int cantidadTelefonos = 0;
		if (listaContactos != null){
			for (int i = 0; i < listaContactos.length; i++){
				if (listaContactos[i]!= null && listaContactos[i].getTelefono() != null){
					cantidadTelefonos+=1;
				}
			}
		}
		return cantidadTelefonos;
	}
	/**
	 * Mètodo que obtiene los asuntos de las citas
	 * @return
	 */
	public String [] obtenerAsuntosCitas() {
		String [] asuntosCitas = new String [definirCantidadAsuntosCitas()];
		int contador =0;
		if (listaCitas != null){
			for (int i = 0; i < listaCitas.length; i++){
				if (listaCitas[i]!= null && listaCitas[i].getAsunto() != null){
					asuntosCitas [contador] = listaCitas[i].getAsunto();
					contador+=1;
				}
			}
		}
		return asuntosCitas;
	}
	/**
	 * mètodo que determina la cantidad de asuntos total por todas las citas
	 * @return
	 */
	public int definirCantidadAsuntosCitas() {
		int cantidadAsuntos= 0;
		if (listaCitas != null){
			for (int i = 0; i < listaCitas.length; i++){
				if (listaCitas[i]!= null && listaCitas[i].getAsunto() != null){
					cantidadAsuntos+=1;
				}
			}
		}
		return cantidadAsuntos;
	}
	/**
	 * mètodo que crea una cita
	 * @param fecha
	 * @param hora
	 * @param asunto
	 * @param grupoCita
	 * @param tamanioListaContactosCita
	 * @param agenda
	 * @return
	 */
	public String crearCita (String fecha, String hora, String asunto, Grupo grupoCita, int tamanioListaContactosCita,
			Agenda agenda){
		String mensaje = "no hay una lista definida aún";
		int  posicion =obtenerPosicionDisponibleCitas();
		if  (listaCitas != null){
			mensaje = "no hay cupo";
			if (posicion != -1){
				mensaje = "ya hay una cita para esa fecha y hora :V";
				if (verificarExisteCita (hora, fecha)!= true){
					listaCitas [posicion]= new Cita (fecha, hora, asunto, grupoCita, tamanioListaContactosCita,agenda);
					mensaje = "se ha creado con éxito la cita";
				}
			}
		}
		return mensaje;
	}
	private boolean verificarExisteCita(String hora, String fecha) {
		boolean yaExiste = false;
		if (listaCitas != null){
			for (int i=0;i<listaCitas.length;i++){
				if (listaCitas [i]!=null){
					if (listaCitas[i].getFecha().equals(fecha)&& listaCitas[i].getHora().equals(hora)){
						yaExiste = true;
					}
				}
			}
		}
		return yaExiste;
	}
	private int obtenerPosicionDisponibleCitas() {
		int pos = -1;
		for (int i = 0; i < listaCitas.length; i++) {
			if(listaCitas [i] == null){
				return i;
			}
		}
		return pos;
	}
	
	public String actualizarCita (Cita cita, String fecha, String hora, String asunto, Grupo grupoCita, Contacto [] listaContactosCita,
			Agenda agenda){
		String mensaje = "no hay una lista definida aún";
		if  (listaCitas != null){
			for  (int i=0;i<listaCitas.length;i++){
				if (listaCitas [i]!=null && listaCitas [i]==cita){
					cita.setAsunto(asunto);
					cita.setFecha(fecha);
					cita.setHora(hora);
					cita.setListaContactosCita(listaContactosCita);
					cita.setAgenda(agenda);
					cita.setGrupoCita(grupoCita);
					mensaje = "se ha actualizado con éxito la cita";
				}
			}
		}
		return mensaje;
	}
	public String eliminarCita (Cita cita){
		String mensaje = "no hay una lista definida aún";
		if  (listaCitas != null){
			for  (int i=0;i<listaCitas.length;i++){
				if (listaCitas [i]!=null && listaCitas [i]==cita){
					cita.setAsunto(null);
					cita.setFecha(null);
					cita.setHora(null);
					cita.setListaContactosCita(null);
					cita.setAgenda(null);
					cita.setGrupoCita(null);
					mensaje = "se ha eliminado con éxito la cita";
				}
			}
		}
		return mensaje;
	}
	public String actualizarGrupo (Grupo grupo, String nombre, Cita [] listaCitasGrupo, Contacto [] listaContactosGrupo, Agenda agenda){
		String mensaje = "no hay una lista de grupos definida aún";
		if (listaGrupos != null){
			for  (int i=0;i<listaGrupos.length;i++){
				if (listaGrupos [i]!= null && listaGrupos[i]==grupo){
					grupo.setAgenda(agenda);
					grupo.setListaCitasGrupo(listaCitasGrupo);
					grupo.setListaContactosGrupo(listaContactosGrupo);
					grupo.setNombre(nombre);
					mensaje = "se ha actualizado con èxito";
				}
			}
		}
		return mensaje;
	}

	public String eliminarGrupo (Grupo grupo){
		String mensaje = "no hay una lista de grupos definida aún";
		if (listaGrupos != null){
			for  (int i=0;i<listaGrupos.length;i++){
				if (listaGrupos [i]!= null && listaGrupos[i]==grupo){
					grupo.setAgenda(null);
					grupo.setListaCitasGrupo(null);
					grupo.setListaContactosGrupo(null);
					grupo.setNombre(null);
					mensaje = "se ha eliminado con èxito";
				}
			}
		}
		return mensaje;
	}
	public String actualizarContacto (Contacto contacto, String nombre, String correo, String direccion, int edad, String telefono,
			Grupo [] listaGruposContacto, Cita [] listaCitasContacto,Agenda agenda){
		String mensaje = "no hay lista de contactos aún";
		if (listaContactos != null){
			for  (int i=0;i<listaContactos.length;i++){
				if  (listaContactos[i]!= null && listaContactos [i]==contacto){
					contacto.setAgenda(agenda);
					contacto.setCorreo(correo);
					contacto.setDireccion(direccion);
					contacto.setEdad(edad);
					contacto.setListaCitasContacto(listaCitasContacto);
					contacto.setListaGruposContacto(listaGruposContacto);
					contacto.setNombre(nombre);
					contacto.setTelefono(telefono);
					mensaje = "se ha actualizado con éxito";
				}
			}
		}
		return mensaje;
	}
	public String eliminarContacto (Contacto contacto){
		String mensaje = "no hay lista de contactos aún";
		if (listaContactos != null){
			for  (int i=0;i<listaContactos.length;i++){
				if  (listaContactos[i]!= null && listaContactos [i]==contacto){
					contacto.setAgenda(null);
					contacto.setCorreo(null);
					contacto.setDireccion(null);
					contacto.setEdad(0);
					contacto.setListaCitasContacto(null);
					contacto.setListaGruposContacto(null);
					contacto.setNombre(null);
					contacto.setTelefono(null);
					mensaje = "se ha eliminado con éxito";
				}
			}
		}
		return mensaje;
	}
	public Contacto obtenerContactoDadoNombre (String nombre){
		Contacto contactoEncontrado = null;
		if (listaContactos != null){
			for  (int i=0;i<listaContactos.length;i++){
				if (listaContactos [i]!= null && listaContactos [i].getNombre().equals(nombre)){
					contactoEncontrado = listaContactos [i];
				}
			}
		}
		return contactoEncontrado;
	}
	/**
	 * mètodo que retorna mensaje explicando si la agenda esta llena
	 * @return
	 */
	public String determinarAgendaLlena (){
		String respuesta = "no hay listas definidas, falta una, dos o todas";
		boolean aux = false;
		boolean hayEspacio = true;
		if (listaContactos != null && listaGrupos != null && listaCitas != null){
			respuesta = "";
			if (obtenerPosicionDisponibleContacto()== -1){
				respuesta = "la agenda esta llena de contactos, no le caben mas";
				hayEspacio= false;
				aux = true;
			}
			if (obtenerPosicionDisponibleGrupo()== -1){
				if (aux == false){
					respuesta = "la agenda esta llena de grupos, no le caben mas";
					aux = true;
				}else{
					respuesta += " y la agenda esta llena de grupos, no le caben mas";
				}
				hayEspacio= false;
			}
			if (obtenerPosicionDisponibleCitas()==-1){
				if (aux == false ){
					respuesta = "La agenda también esta rebosada de citas, no le caben mas ";
				}else{
					respuesta += " y la agenda también esta rebosada de citas, no le caben mas ";
				}
				hayEspacio= false;
			}
		}
		if (hayEspacio == true){
			respuesta = "la agenda aún tiene espacio disponible";
		}
		return respuesta;
	}
	/**
	 * mètodo que retorna la cantidad de espacios que quedan en lista contactos
	 * @return
	 */
	public int determinarEspaciosQuedanContactos (){
		int cantidadEspaciosRestantes = 0;
		if (listaContactos!= null){
			for  (int i=0;i<listaContactos.length;i++){
				if (listaContactos [i]==null){
					cantidadEspaciosRestantes+=1;
				}
			}
		}
		
		return cantidadEspaciosRestantes;
	}

	
}
