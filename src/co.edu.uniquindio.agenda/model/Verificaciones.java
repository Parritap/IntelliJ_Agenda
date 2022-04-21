package co.edu.uniquindio.agenda.model;



import co.edu.uniquindio.agenda.exceptions.EdadNegativaException;
import co.edu.uniquindio.agenda.exceptions.NombreConDigitosException;
import co.edu.uniquindio.agenda.exceptions.StringLlenoEspacios;
import co.edu.uniquindio.agenda.exceptions.StringVacioException;


import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Verificaciones {

    /**
     * Método que verifica si un String cumple con la expresión regular que ponga en el argumento.
     * @param regex Expresión egular.
     * @param str String a evaluar
     * @return True si existe el patron @regex dentro de @str.
     */
    public boolean matchesRegex (String regex, String str){
        Pattern patt = Pattern.compile(regex);
        Matcher mat = patt.matcher (str);

        boolean result = mat.matches();

        return result;
    }

    /**
     * Método que verifica que la edad ingresada en el argumento no sea negativa.
     * @param edad Edad.
     * @throws EdadNegativaException Excepción lanzada en caso de que la edad sea negativa.
     */
    public void verificarEdadNegativa(int edad) throws EdadNegativaException {
        if (edad < 0){
            throw new EdadNegativaException();
        }
    }

    /**
     * Método que verifica que un String no esté vacío.
     * @param str String a evaluar
     * @throws StringVacioException Lanzada si el String está vacío.
     */
    public void verificarSiStringVacio (String str) throws StringVacioException {

        boolean esVacio = matchesRegex("", str);
        if (esVacio){
            throw new StringVacioException();
        }
    }

    /**
     * Método encargado de lanzar una excepción en caso de que
     * @param str String a evaluar.
     * @throws StringLlenoEspacios
     */
    public void verificarSiStringLlenoEspacios (String str) throws StringLlenoEspacios {

        boolean llenoDeEspacios = matchesRegex("( )*", str);
        if (llenoDeEspacios){
            throw new StringLlenoEspacios();
        }
    }

    /**
     * Método que verifica (condición) si un nombre posee al menos un dígito. (Los nombres de una persona no tiene digits).
     * @param nombre Nombre a evaluar.
     * @throws NombreConDigitosException Excepción lanzada en caso de que la condición se cumpla.
     */
    public void verificarNombreConDigitos (String nombre) throws NombreConDigitosException {
      boolean coincide = matchesRegex("(.)*(\\d)(.)*", nombre);
      if (coincide){
        throw new NombreConDigitosException();
      }
    }

}
