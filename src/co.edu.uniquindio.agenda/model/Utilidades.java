package co.edu.uniquindio.agenda.model;

import java.util.Arrays;

public class Utilidades {

    public static int buscarNumMasRepetido(int[] array) {

        //El método requiere de que los números estén ordenados.
        //Lo que nos importa realmente es que los números iguales estén todos juntos.
        Arrays.sort(array);

        //Nota, cuando me refiero a tramo de números, todos el conjunto de números iguales. Esto es, verbigracia:
        //conjuntoA = {1,1,2,2,2,3,3,3,4,4,4,4} Aquí existen 4 tramos de números dados por los números 1,2,3 y 4.

        int num = array[0]; //Número que retornaremos
        int cOld = 0;       //Contador que guarda el número de repeticiones del número más repetido. Va cambiando según se corra el código.
        int cNew;            //Contador de repeticiones de los nuevos tramos de números.
        int repCounter = 1; //Contador de repeticiones.


        for (int i = 0; i <= array.length - 2; i++) {

            if (array[i] == array[i + 1])
                repCounter++;

            if (array[i] != array[i + 1]) {
                cNew = repCounter;
                repCounter = 1;

                if (cNew>cOld) {
                    num = array[i];

                    cOld = cNew;
                    cNew =0;
                }
            }

            //Si no se pone este if, entonces nunca se va a evaluar el último tramo de números.
            if (i == array.length-2){
                if (repCounter>cOld)
                    num= array[array.length-1];
            }
        }


        return num;
    }
}
