package co.edu.uniquindio.agenda.test;

import java.util.Random;

import javax.swing.JOptionPane;

import co.edu.uniquindio.agenda.exceptions.ContactoExcepction;
import co.edu.uniquindio.agenda.model.Agenda;

public class Test {
	static Agenda agenda = new Agenda("Mi agenda personal", 10, 3,3);
	static String [] listaNombresPosibles ={"Isabella","Daniel","Olivia","David","Alexis","Gabriel",
			"Sofia","Benjamin","Victoria","Samuel", "Amelia",	"Lucas", "Alexa","Angel","Julia",	
			"Jose","Camila","Adrian", "Alexandra","Sebastian"};
	static String [] listaCorreosPosibles = {"Isabella@uniquindio.co","Daniel@uniquindio.co",
			"Olivia@uniquindio.co","David@uniquindio.co","Alexis@uniquindio.co","Gabriel@uniquindio.co",
			"Sofia@uniquindio.co","Benjamin@uniquindio.co","Victoria@uniquindio.co","Samuel@uniquindio.co",
			"Amelia@uniquindio.co",	"Lucas@uniquindio.co", "Alexa@uniquindio.co","Angel@uniquindio.co",
			"Julia@uniquindio.co","Jose@uniquindio.co","Camila@uniquindio.co","Adrian@uniquindio.co", 
			"Alexandra@uniquindio.co","Sebastian@uniquindio.co"};
	static String [] listaDireccionesPosibles = {"casaIsabella","casaDaniel","casaOlivia","casaDavid","casaAlexis"
			,"casaGabriel","casaSofia","casaBenjamin","casaVictoria","casaSamuel", "casaAmelia",
			"casaLucas", "casaAlexa","casaAngel","casaJulia","casaJose","casaCamila",
			"casaAdrian", "casaAlexandra","casaSebastian"};
	static String [] listaNombresGrupos = {"grupoMemes","grupoProgramacionDos","grupoMatematicas","grupoCalculo",
			"grupoCopiaTareas","grupoMusica","grupoTdo"};
	
	public static void main(String[] args) {
		crearVeinteContactosAleatorios ();
		System.out.println(agenda.toString());
		crearGrupoAleatorio ();
		System.out.println(agenda.toString());
		
		// aqui pueden pedir los datos al usuario
		
		

		// crear contacto
		
/**  x.,washg3woikp

		try {
			agenda.eliminarContacto("134455");
		} catch (ContactoExcepction e) {

			JOptionPane.showMessageDialog(null,e.getMessage());
		}

		// CRUD : crear obtener  actualizar eliminar
*/
	}
	private static void crearGrupoAleatorio() {
		String nombre = listaNombresGrupos [devolverNumeroAleatorioSegunRango(0,6)];
		agenda.crearGrupo (nombre,agenda);
		
		
	}
	//Mètodos generadoresç
	//Generador de  contactos
	public static void crearVeinteContactosAleatorios (){
		int contador =0;
		while (contador <20){
			int aleatorio = devolverNumeroAleatorioSegunRango (0,19);
			String nombre = listaNombresPosibles [aleatorio];
			String correo = listaCorreosPosibles [aleatorio];
			String direccion = listaDireccionesPosibles [aleatorio];
			int edad = devolverNumeroAleatorioSegunRango (15,60);
			String telefono = generarNumeroTelefonoAleatorio();
			System.out.println(agenda.crearContactoSinListas(nombre, correo, direccion, edad, telefono,agenda));
			contador+=1;
		}
	}
	public static String generarNumeroTelefonoAleatorio() {
		int contador = 0;
		String telefono = "";
		while  (contador <10){
			int aux = devolverNumeroAleatorioSegunRango (0,9);
			telefono += aux;
			contador+=1;
		}
		return telefono;
	}

	public static int devolverNumeroAleatorioSegunRango(int numeroInicial, int numeroFinal) {
        Random aleatorio = new Random();
        int numeroAleatorio = aleatorio.nextInt(numeroFinal - numeroInicial + 1) + numeroInicial;
        return numeroAleatorio;
    }



}
