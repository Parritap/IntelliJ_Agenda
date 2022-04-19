package co.edu.uniquindio.agenda.exceptions;


public class EdadNegativaException extends  Exception {

    public EdadNegativaException(String mensaje){
        super( mensaje);
    }

    public EdadNegativaException(){
        super();
    }

}
