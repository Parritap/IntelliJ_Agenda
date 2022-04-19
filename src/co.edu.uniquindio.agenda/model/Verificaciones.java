package co.edu.uniquindio.agenda.model;


import co.edu.uniquindio.agenda.exceptions.EdadNegativaException;
import co.edu.uniquindio.agenda.exceptions.StringVacioException;

public class Verificaciones {


    public void verificarEdad (int edad) throws EdadNegativaException {
        if (edad < 0){
            throw new EdadNegativaException();
        }
    }


    public void verificarSiStringVacio (String str) throws StringVacioException {

        if (str.equals("\0")){
            throw new StringVacioException();
        }
    }




}
