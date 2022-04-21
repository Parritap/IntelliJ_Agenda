package co.edu.uniquindio.agenda.model;

import java.util.Arrays;

import co.edu.uniquindio.agenda.exceptions.ContactoExcepction;

public class Agenda {


    private String titulo;
    private Contacto[] listaContactos;
    private Cita[] listaCitas;
    private Grupo[] listaGrupos;


    public Agenda(String titulo, int numContactos, int numGrupos, int numCitas) {

        this.titulo = titulo;
        this.listaContactos = new Contacto[numContactos];
        this.listaCitas = new Cita[numCitas];
        this.listaGrupos = new Grupo[numGrupos];
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


    public void crearContacto(String nombre, String correo, String direccion, int edad, String telefono,
                              Grupo[] listaGruposContacto, Cita[] listaCitasContacto, Agenda agenda) throws Exception {


        //Verificamos que el nombre no sea nulo ni sea igual a vacio
        if (nombre == null || nombre.equals("") || nombre.equals("\0")) {
            throw new ContactoExcepction("El nombre es nulo o vacio");
        }

        int posicionDisponible = 0;
        boolean existeContacto = false;

        posicionDisponible = obtenerPosicionDisponibleContacto();

        if (posicionDisponible == -1) {
            throw new Exception("La lista de contactos está llena");
        } else {

            //Vericamos que ningún atributo (además del nombre y las listas) sea nulo.
            //El método setear es el que, en caso de ser null, retorne un String vacio, de lo contario, retorne el string
            //Mandado en el parámetro.
            //Es lo mismo para edad.
            String newCorreo = setearString(correo);
            String newDirec = setearString(direccion);
            String newTelefono = setearString(telefono);
            int newEdad = setearEdad(edad); // Seteamos la edad a 0 en caso de que sea negativa.


            existeContacto = existeContacto(nombre);
            if (!existeContacto) {
                listaContactos[posicionDisponible] = new Contacto(nombre, newCorreo, newDirec, newEdad, newTelefono, listaGruposContacto, listaCitasContacto, this);
            } else {
                throw new Exception("Ya existe un contacto con el nombre " + nombre);
            }
        }
    }

    private int setearEdad(int edad) {
        int newEdad = edad;

        if (newEdad < 0)
            newEdad = 0;

        return newEdad;
    }

    private String setearString(String str) {
        String newString = str;

        if (newString == null) {
            newString = "";
        }
        return newString;
    }

    public String crearContactoSinListas(String nombre, String correo, String direccion, int edad, String telefono, Agenda agenda) {
        String mensaje = "El contacto fue creado";
        int posicionDisponible = 0;
        boolean existeContacto = false;

        posicionDisponible = obtenerPosicionDisponibleContacto();

        if (posicionDisponible == -1) {
            mensaje = "No hay posiciobes disponibles";
        } else {

            existeContacto = existeContacto(nombre);
            if (existeContacto == false) {
                listaContactos[posicionDisponible] = new Contacto(nombre, correo, direccion, edad, telefono, agenda);
            } else {
                mensaje = "Ya existe un contacto con este numero de telefono";
            }
        }

        return mensaje;
    }

    /**
     * Método que obtiene un contacto dado el nombre
     *
     * @param nombre nombre del contacto a buscar.
     * @return null de no encontrar el contacto buscado.
     */
    private Contacto obtenerContacto(String nombre) { //ReadContact.

        for (Contacto contacto : listaContactos) {
            if (contacto != null && contacto.getNombre().equals(nombre)) {
                return contacto;
            }
        }

        return null;
    }


    /**
     * Método que verifica la existencia de un contacto comparando por su nombre.
     *
     * @param nombre Nombre del contacto. Se verifica no exista ya un contacto dentro de listaContactos con este nombre.
     * @return True de existir otro contacto con el mismo nombre.
     */
    public boolean existeContacto(String nombre) {

        for (Contacto contacto : listaContactos) {
            if (contacto != null && contacto.getNombre().equals(nombre)) {
                return true;
            }
        }

        return false;
    }

    public boolean verificarExistenciaContactoSoloTelefono(String telefono) {

        for (Contacto contacto : listaContactos) {
            if (contacto != null && contacto.getTelefono().equals(telefono)) {
                return true;
            }
        }

        return false;
    }


    /**
     * Busca una posición disponible en listaContactos
     *
     * @return Retorna dicha posición. De no haber espacio, retorna -1.
     */
    private int obtenerPosicionDisponibleContacto() {

        int pos = -1;

        for (int i = 0; i < listaContactos.length; i++) {
            if (listaContactos[i] == null) {
                return i;
            }
        }

        return pos;
    }


    /**
     * Método que elimina un contacto según su nombre.
     *
     * @param nombre String usado para buscar el contacto. Si existe un contacto con tal nombre, entonces ese se elimina.
     * @throws ContactoExcepction En caso de que no exista un contacto con el @nombre indicado en el argumento.
     */
    public void eliminarContacto(String nombre) throws ContactoExcepction {

        boolean existeContacto = false;
        existeContacto = existeContacto(nombre);

        if (!existeContacto) {

            throw new ContactoExcepction("El contacto no existe");

        } else {

            int posContacto = obtenerPosContacto(nombre);
            listaContactos[posContacto] = null;
        }
    }

    /**
     * Método que encuentra la posición de un contacto en listaContacto dado su nombre.
     *
     * @param nombre Nombre del contacto buscado dentro de listaContactos.
     * @return
     */
    private int obtenerPosContacto(String nombre) {

        Contacto contactoAuxliar = null;

        for (int i = 0; i < listaContactos.length; i++) {
            contactoAuxliar = listaContactos[i];
            if (contactoAuxliar != null && contactoAuxliar.getNombre().equals(nombre)) {
                return i;
            }
        }
        return -1;
    }


    /**
     * Método que actualiza un contacto. El método busca el contacto dentro de las listas por su nombre
     *
     * @throws ContactoExcepction En caso de que el contacto buscado no exista, o que @nuevoNombre sea nulo o vacío.
     */
    public void actualizarContacto(String nombreAnterior, int edadNueva, String telefonoNuevo,
                                   String nombreNuevo, String correoNuevo, String direccionNueva) throws ContactoExcepction {

        Contacto contactoEncontrado = null;

        contactoEncontrado = obtenerContacto(nombreAnterior);

        if (contactoEncontrado == null) {
            throw new ContactoExcepction("El contacto con el nombre indicado no existe.");
        } else {

            if (nombreNuevo == null || nombreNuevo.equals("") || nombreNuevo.equals("\0"))
                throw new ContactoExcepction("El nombre al que se desea actualiza es nulo o está vacío");


            contactoEncontrado.setEdad(setearEdad(edadNueva)); //Esta línea cubre las edades negativas.
            contactoEncontrado.setNombre(nombreNuevo);
            contactoEncontrado.setCorreo(setearString(correoNuevo));
            contactoEncontrado.setDireccion(setearString(direccionNueva));
            contactoEncontrado.setTelefono(setearString(telefonoNuevo));
        }
    }

    /**
     * Método que actualiza un contacto dado en el argumento
     *
     * @param contacto       Contacto cuya info se va a actualizar.
     * @param nuevoNombre
     * @param nuevoTelefono
     * @param nuevaEdad
     * @param nuevaDireccion
     * @param nuevoCorreo
     * @throws ContactoExcepction
     */
    public void actualizarContacto(Contacto contacto, String nuevoNombre, String nuevoTelefono, int nuevaEdad,
                                   String nuevaDireccion, String nuevoCorreo) throws ContactoExcepction {

        if (contacto == null)
            throw new ContactoExcepction("El contacto pasado en el argumento es nulo");

        if (nuevoNombre == null || nuevoNombre.equals("") || nuevoNombre.equals("\0"))
            throw new ContactoExcepction("El nombre es nulo o está vacío");


        contacto.setNombre(nuevoNombre);
        contacto.setTelefono(setearString(nuevoTelefono));
        contacto.setEdad(setearEdad(nuevaEdad));
        contacto.setDireccion(setearString(nuevaDireccion));
        contacto.setCorreo(setearString(nuevoCorreo));
    }


    /**
     * Método que, dado un contacto, verifica que existe dentro de la listaContactos de la Agenda.
     *
     * @param c Contacto a buscar.
     * @return True si el contacto existe dentro de la lista. False de lo contrario.
     */
    public boolean existeContacto(Contacto c) {
        boolean existe = false;
        if (listaContactos != null) {
            for (int i = 0; i < listaContactos.length; i += 1) {
                if (listaContactos[i] != null && listaContactos[i] == c) {
                    existe = true;
                }
            }
        }
        return existe;
    }

    /**
     * Método que retorna un String con todos los contactos impares dentro de la lista de contactos.
     *
     * @return
     */
    public String mostrarContactosImpares() {
        StringBuilder contactosImpares = new StringBuilder();
        for (int i = 0; i < listaContactos.length; i += 1) {
            if (i % 2 != 0) {
                String aux = listaContactos[i].getNombre() + ", ";
                contactosImpares.append(aux);
            }
        }
        return contactosImpares.toString();
    }

    /**
     * Método que determina la edad más repetida entre todos los contactos de listaContactos.
     *
     * @return Entero que representa la edad más repetida dentro.
     */
    public int obtenerEdadMasRepetida() {
        int edadMasRepetida = -1;
        int[] edades;
        int edadesLength = 0;
        if (listaContactos != null) {

            // El siguiente bloque de código nos da la cantidad de edades que existen dentro de agenda.
            // Necesitamos hacer esto para luego verificar cual de los elementos dentro del arreglo es
            // el más repetido.
            for (Contacto contacto : listaContactos) {
                if (contacto != null) {
                    edadesLength++;
                }
            }


            //Declaramos el tamaño del arreglo.
            edades = new int[edadesLength];
            int pos = 0; //Variable para meter elementos dentro del arreglo edades.


            //Ahora le asignamos valores
            for (Contacto contacto : listaContactos) {
                if (contacto != null) {
                    edades[pos] = contacto.getEdad();
                }
            }
            //Ahora, con el método de utilidades encontramos el número más repetido.
            edadMasRepetida = Utilidades.buscarNumMasRepetido(edades);


        }
        return edadMasRepetida;
    }

    /**
     * Método que determina el promedio de las edades de listaContactos.
     *
     * @return Promedio de las edades.
     */
    public double determinarPromedioEdades() {
        double promedio = 0;
        double aux = 0;
        int divisor = 0;
        if (listaContactos != null) {
            for (Contacto contacto : listaContactos) {
                if (contacto != null) {
                    aux += contacto.getEdad();
                    divisor += 1;
                }
            }
        }
        if (divisor != 0) {
            promedio = aux / divisor;
        }
        return promedio;
    }

    /**
     * metodo que obtiene un arreglo con todos los contactos menores de edad
     *
     * @return Lista con los contactos menores de edad.
     */
    public Contacto[] obtenerMenoresEdad() {
        Contacto[] menoresEdad = new Contacto[determinarCantidadMenoresEdad()];
        int contador = 0;
        if (listaContactos != null) {
            for (Contacto contacto : listaContactos) {
                if (contacto != null && contacto.getEdad() < 18) {
                    menoresEdad[contador] = contacto;
                    contador += 1;
                }
            }
        }
        return menoresEdad;
    }

    /**
     * Método que calcula la cantidad de menores de edad en listaContactos
     *
     * @return Int que representa la cantidad de contactos menores de edad dentro de Agenda.
     */
    public int determinarCantidadMenoresEdad() {
        int cantidadMenores = 0;
        if (listaContactos != null) {
            for (int i = 0; i < listaContactos.length; i += 1) {
                if (listaContactos[i] != null && listaContactos[i].getEdad() < 18) {
                    cantidadMenores += 1;
                }
            }
        }
        return cantidadMenores;
    }

    /**
     * Método que retorna un String con el nombre de todos los contactos invertido
     *
     * @return String con todos los nombres de listaContactos in
     */
    public String obtenerNombreContactoInvertido() {
        StringBuilder nombresInvertidos = new StringBuilder();
        if (listaContactos != null) {
            for (int i = 0; i < listaContactos.length; i += 1) {
                if (listaContactos[i] != null) {
                    nombresInvertidos.append(invertirNombre(listaContactos[i].getNombre()));
                }
            }
        }
        return nombresInvertidos.toString();
    }

    /**
     * Metodo que dado un nombre, lo invierte.
     *
     * @param nombre String a invertir.
     * @return Nombre invertido.
     */
    public String invertirNombre(String nombre)  throws NullPointerException{
        if (nombre == null)
            throw new NullPointerException();

        StringBuilder nombreInvertido = new StringBuilder();
        for (int i = nombre.length() - 1; i > 0; i -= 1) {
            nombreInvertido.append(nombre.charAt(i));
        }
        return nombreInvertido.toString();
    }

    /**
     * PENDIENTE
     * metodo que asocia un contacto a un grupo dado el contacto y el nombre del grupo
     *
     * @param contacto
     * @param nombreGrupo
     * @return
     */
    public void asociarContactoGrupo(Contacto contacto, String nombreGrupo) {
        // String mensaje = "";
        Grupo grupo = obtenerGrupoDadoNombre(nombreGrupo);
        if (grupo != null) {
            grupo.asociarContactoGrupo(contacto);
        }
    }

    /**
     * metodo que obtiene un grupo dado su nombre
     *
     * @param nombreGrupo
     * @return
     */
    public Grupo obtenerGrupoDadoNombre(String nombreGrupo) {
        Grupo grupo = null;
        if (listaGrupos != null) {
            for (int i = 0; i < listaGrupos.length; i += 1) {
                if (listaGrupos[i] != null && listaGrupos[i].getNombre().equals(nombreGrupo)) {
                    grupo = listaGrupos[i];
                }
            }
        }

        return grupo;
    }

    /**
     * Mètodo que calcula la desviación estandar, es decir:
     * la raíz cuadrada de la suma de los cuadrados de las diferencias de cada dato con el promedio
     * dividido entre la cantidad de datos
     *
     * @return
     */
    public double calcularDesviacionEdades() {
        double desviacion = 0;
        double promedio = determinarPromedioEdades();
        double aux = 0;
        double promedioDistancias = 0;
        int contador = 0;
        if (promedio != 0) {
            if (listaContactos != null) {
                for (int i = 0; i < listaContactos.length; i += 1) {
                    if (listaContactos[i] != null) {
                        aux = Math.pow((listaContactos[i].getEdad() - promedio), 2);
                        promedioDistancias += aux;
                        contador++;
                    }
                }
            }
        }
        if (contador != 0) {
            promedioDistancias = promedioDistancias / contador;
            desviacion = Math.pow(promedioDistancias, (1 / 2));
        }
        return desviacion;
    }

    /**
     * Mètodo que crea un grupo dado el nombre
     *
     * @param nombre
     * @return
     */
    public String crearGrupo(String nombre, Agenda agenda) {
        String mensaje = "no hay una lista de grupos definida aún";
        if (listaGrupos != null) {
            mensaje = "no se puedo crear el grupo, no hay cupo";
            int posicion = obtenerPosicionDisponibleGrupo();
            if (posicion != -1) {
                mensaje = "el grupo no se creó porque ya existía";
                if (verificarExisteGrupo(nombre) != true) {
                    listaGrupos[posicion] = new Grupo(nombre, listaCitas.length, listaContactos.length, agenda);
                    mensaje = "se creó el grupo con èxito";
                }
            }
        }

        return mensaje;
    }

    /**
     * Mètodo que obtiene las posicionesl libres de grupo
     *
     * @return
     */
    private int obtenerPosicionDisponibleGrupo() {
        int pos = -1;
        for (int i = 0; i < listaGrupos.length; i++) {
            if (listaGrupos[i] == null) {
                return i;
            }
        }
        return pos;
    }

    /**
     * mètodo que verifica si ya existe el grupo
     *
     * @param nombre
     * @return
     */
    public boolean verificarExisteGrupo(String nombre) {
        boolean yaExiste = false;
        if (listaGrupos != null) {
            for (int i = 0; i < listaGrupos.length; i++) {
                if (listaGrupos[i] != null && listaGrupos[i].getNombre().equals(nombre)) {
                    yaExiste = true;
                }
            }
        }

        return yaExiste;
    }

    /**
     * mètodo que obtiene los nombres de los grupos
     *
     * @return
     */
    public String[] obtenerNombresGrupos() {
        String[] nombresGrupos = new String[determinarCantidadNombresGrupos()];
        int contador = 0;
        if (listaGrupos != null) {
            for (int i = 0; i < listaGrupos.length; i++) {
                if (listaGrupos[i] != null && listaGrupos[i].getNombre() != null) {
                    nombresGrupos[contador] = listaGrupos[i].getNombre();
                    contador += 1;
                }
            }
        }
        return nombresGrupos;
    }

    /**
     * mètodo que determina la cantidad de nombres de grupos que hay
     *
     * @return
     */
    public int determinarCantidadNombresGrupos() {
        int cantidadNombresGrupos = 0;
        if (listaGrupos != null) {
            for (int i = 0; i < listaGrupos.length; i++) {
                if (listaGrupos[i] != null && listaGrupos[i].getNombre() != null) {
                    cantidadNombresGrupos += 1;
                }
            }
        }
        return cantidadNombresGrupos;
    }

    /**
     * Mètodo que obtiene los telefono de los contactos
     *
     * @return
     */
    public String[] obtenerTelefonoContactos() {
        String[] telefonoContactos = new String[definirCantidadTelefonosContactos()];
        int contador = 0;
        if (listaContactos != null) {
            for (int i = 0; i < listaContactos.length; i++) {
                if (listaContactos[i] != null && listaContactos[i].getTelefono() != null) {
                    telefonoContactos[contador] = listaContactos[i].getTelefono();
                    contador += 1;
                }
            }
        }
        return telefonoContactos;
    }

    /**
     * mètodo que obtiene la cantidad de teléfonos que hay en total por todos los contactos
     *
     * @return
     */
    public int definirCantidadTelefonosContactos() {
        int cantidadTelefonos = 0;
        if (listaContactos != null) {
            for (int i = 0; i < listaContactos.length; i++) {
                if (listaContactos[i] != null && listaContactos[i].getTelefono() != null) {
                    cantidadTelefonos += 1;
                }
            }
        }
        return cantidadTelefonos;
    }

    /**
     * Mètodo que obtiene los asuntos de las citas
     *
     * @return
     */
    public String[] obtenerAsuntosCitas() {
        String[] asuntosCitas = new String[definirCantidadAsuntosCitas()];
        int contador = 0;
        if (listaCitas != null) {
            for (int i = 0; i < listaCitas.length; i++) {
                if (listaCitas[i] != null && listaCitas[i].getAsunto() != null) {
                    asuntosCitas[contador] = listaCitas[i].getAsunto();
                    contador += 1;
                }
            }
        }
        return asuntosCitas;
    }

    /**
     * mètodo que determina la cantidad de asuntos total por todas las citas
     *
     * @return
     */
    public int definirCantidadAsuntosCitas() {
        int cantidadAsuntos = 0;
        if (listaCitas != null) {
            for (int i = 0; i < listaCitas.length; i++) {
                if (listaCitas[i] != null && listaCitas[i].getAsunto() != null) {
                    cantidadAsuntos += 1;
                }
            }
        }
        return cantidadAsuntos;
    }

    /**
     * mètodo que crea una cita
     *
     * @param fecha
     * @param hora
     * @param asunto
     * @param grupoCita
     * @param tamanioListaContactosCita
     * @param agenda
     * @return
     */
    public String crearCita(String fecha, String hora, String asunto, Grupo grupoCita, int tamanioListaContactosCita,
                            Agenda agenda) {
        String mensaje = "no hay una lista definida aún";
        int posicion = obtenerPosicionDisponibleCitas();
        if (listaCitas != null) {
            mensaje = "no hay cupo";
            if (posicion != -1) {
                mensaje = "ya hay una cita para esa fecha y hora :V";
                if (verificarExisteCita(hora, fecha) != true) {
                    listaCitas[posicion] = new Cita(fecha, hora, asunto, grupoCita, tamanioListaContactosCita, agenda);
                    mensaje = "se ha creado con éxito la cita";
                }
            }
        }
        return mensaje;
    }

    private boolean verificarExisteCita(String hora, String fecha) {
        boolean yaExiste = false;
        if (listaCitas != null) {
            for (int i = 0; i < listaCitas.length; i++) {
                if (listaCitas[i] != null) {
                    if (listaCitas[i].getFecha().equals(fecha) && listaCitas[i].getHora().equals(hora)) {
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
            if (listaCitas[i] == null) {
                return i;
            }
        }
        return pos;
    }

    public String actualizarCita(Cita cita, String fecha, String hora, String asunto, Grupo grupoCita, Contacto[] listaContactosCita,
                                 Agenda agenda) {
        String mensaje = "no hay una lista definida aún";
        if (listaCitas != null) {
            for (int i = 0; i < listaCitas.length; i++) {
                if (listaCitas[i] != null && listaCitas[i] == cita) {
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

    public String eliminarCita(Cita cita) {
        String mensaje = "no hay una lista definida aún";
        if (listaCitas != null) {
            for (int i = 0; i < listaCitas.length; i++) {
                if (listaCitas[i] != null && listaCitas[i] == cita) {
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
Z|
    public String actualizarGrupo(Grupo grupo, String nombre, Cita[] listaCitasGrupo, Contacto[] listaContactosGrupo, Agenda agenda) {
        String mensaje = "no hay una lista de grupos definida aún";
        if (listaGrupos != null) {
            for (int i = 0; i < listaGrupos.length; i++) {
                if (listaGrupos[i] != null && listaGrupos[i] == grupo) {
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

    /**
     * Método que elimina un grupo. En realidad lo que see hace es dejar nulos todos los atributos que posee grupo.
     *
     * @param grupo Grupo a eliminar.
     * @throws ContactoExcepction En caso de que el grupo sea nulo.
     */
    public void eliminarGrupo(Grupo grupo) throws ContactoExcepction {
        if (listaGrupos != null) {

            for (int i = 0; i < listaGrupos.length; i++) {
                if (listaGrupos[i] != null && listaGrupos[i] == grupo) {
                    grupo.setAgenda(null);
                    grupo.setListaCitasGrupo(null);
                    grupo.setListaContactosGrupo(null);
                    grupo.setNombre(null);
                }
            }
        } else {
            throw new ContactoExcepction("El grupo a eliminar es nulo");
        }
    }

    public String actualizarContacto(Contacto contacto, String nombre, String correo, String direccion, int edad, String telefono,
                                     Grupo[] listaGruposContacto, Cita[] listaCitasContacto, Agenda agenda) {
        String mensaje = "no hay lista de contactos aún";
        if (listaContactos != null) {
            for (int i = 0; i < listaContactos.length; i++) {
                if (listaContactos[i] != null && listaContactos[i] == contacto) {
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

    public String eliminarContacto(Contacto contacto) {
        String mensaje = "no hay lista de contactos aún";
        if (listaContactos != null) {
            for (int i = 0; i < listaContactos.length; i++) {
                if (listaContactos[i] != null && listaContactos[i] == contacto) {
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

    public Contacto obtenerContactoDadoNombre(String nombre) {
        Contacto contactoEncontrado = null;
        if (listaContactos != null) {
            for (int i = 0; i < listaContactos.length; i++) {
                if (listaContactos[i] != null && listaContactos[i].getNombre().equals(nombre)) {
                    contactoEncontrado = listaContactos[i];
                }
            }
        }
        return contactoEncontrado;
    }

    /**
     * mètodo que retorna mensaje explicando si la agenda esta llena
     *
     * @return
     */
    public String determinarAgendaLlena() {
        String respuesta = "no hay listas definidas, falta una, dos o todas";
        boolean aux = false;
        boolean hayEspacio = true;
        if (listaContactos != null && listaGrupos != null && listaCitas != null) {
            respuesta = "";
            if (obtenerPosicionDisponibleContacto() == -1) { // Se verifica si hay espacios disponibles dentro de listaContactos;
                respuesta = "la agenda esta llena de contactos, no le caben mas";
                hayEspacio = false;
                aux = true;
            }
            if (obtenerPosicionDisponibleGrupo() == -1) {
                if (aux == false) {
                    respuesta = "la agenda esta llena de grupos, no le caben mas";
                    aux = true;
                } else {
                    respuesta += " y la agenda esta llena de grupos, no le caben mas";
                }
                hayEspacio = false;
            }
            if (obtenerPosicionDisponibleCitas() == -1) {
                if (aux == false) {
                    respuesta = "La agenda también esta rebosada de citas, no le caben mas ";
                } else {
                    respuesta += " y la agenda también esta rebosada de citas, no le caben mas ";
                }
                hayEspacio = false;
            }
        }
        if (hayEspacio == true) {
            respuesta = "la agenda aún tiene espacio disponible";
        }
        return respuesta;
    }

    /**
     * Método que verifca si la agenda está llena.
     * La agenda se encuentra llena si la lista de contactos, grupos y citas está llena.
     *
     * @return True si la agenda está llena. False de lo conctrario.
     */
    public boolean estaAgendaLlena() {
        boolean listaContactosLlena = true;
        boolean listaGruposLlena = true;
        boolean listaCitasLlena = true;

        if (listaContactos != null && obtenerPosicionDisponibleContacto() != -1)
            listaContactosLlena = false;

        if (listaGrupos != null && obtenerPosicionDisponibleGrupo() != -1)
            listaGruposLlena = false;

        if (listaCitas != null && obtenerPosicionDisponibleCitas() != -1) {
            listaCitasLlena = false;
        }

        if (listaContactosLlena && listaCitasLlena && listaGruposLlena)
            return true;

        return false;
    }

    /**
     * Método que retorna la cantidad de espacios disponibles que quedan en listaContactos.
     *
     * @return Int que representa la cantidad de espacios disponibles.
     */
    public int obtenerCantidadEspaciosListaContactos() {
        int cantidadEspaciosRestantes = 0;
        if (listaContactos != null) {
            for (int i = 0; i < listaContactos.length; i++) {
                if (listaContactos[i] == null) {
                    cantidadEspaciosRestantes += 1;
                }
            }
        }

        return cantidadEspaciosRestantes;
    }

    /** PUNTO PARCIAL**/
	/*
	Devolver una lista de contactos que su nombre inicie en vocal y pertenezcan a al menos
	a un grupo que termine en consonante.
	NOTA: no se permiten elementos nulos en el arreglo que va a retornar.

	 */

	/*
	public Contacto [] obtenerListaContactos(){

		Contacto [] list = new Contacto [determinarCantidadCumple()];
		int contador = 0;
		if (listaContactos!=null){
			for (int i=0;i<listaContactos.length;i++){
				if (listaContactos[i]!=null){
					if ((listaContactos[i].determinarNombreCumple())){
						if (listaContactos[i].determinarGrupoCumple()){
							list[contador]= listaContactos[i];
							contador+=1;

						}
					}
				}
			}
		}
		return list;
	}

	/*

	public int determinarCantidadCumple (){
		int contador = 0;
		boolean cumpleLetraInicial = false;
		if (listaContactos!=null){
			for (int i=0;i<listaContactos.length;i++){
				if (listaContactos[i]!=null){
					cumpleLetraInicial = listaContactos[i].determinarNombreCumple();
					if (){
						if (listaContactos[i].determinarGrupoCumple()){
							contador+=1;

						}
					}
				}
			}
		}
		return contador;
	}

	 */


    /*** don PARRA LISTA VOCALES CONSONANTES **/

    public boolean esVocal(char c) {

        String vocalesStr = "aeiouAEIOU";
        char[] vocales = vocalesStr.toCharArray();

        for (char vocal : vocales
        ) {

            if (c == vocal)
                return true;
        }
        return false;
    }

    public int obtenerTamanioLista() {
        int size = 0;

        for (Contacto contacto : this.listaContactos
        ) {
            if (esVocal(contacto.getNombre().charAt(0)) &&
                    perteneceAGrupoConsonante(contacto)) {
                size++;
            }
        }
        return size;


    }

    private boolean perteneceAGrupoConsonante(Contacto contacto) {

        for (Grupo grupo : contacto.getListaGruposContacto()
        ) {

            if (!esVocal(grupo.getNombre().charAt(grupo.getNombre().length() - 1)))
                return true;
        }
        return false;
    }

    public Contacto[] obtenerListaCond() {
        int listSize = obtenerTamanioLista();
        Contacto[] lista = new Contacto[listSize];

        int pos = 0; // index para meter elementos dentro de listsSize.

        for (Contacto contacto : this.listaContactos
        ) {

            if (esVocal(contacto.getNombre().charAt(0)) &&
                    perteneceAGrupoConsonante(contacto)) {
                lista[pos] = contacto;
            }
        }
        return lista;
    }

}
