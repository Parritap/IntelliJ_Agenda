package co.edu.uniquindio.agenda.test;

import java.util.Arrays;

public class RandomTest {

    private static final String EXAMPLE_TEST = "Hola Mundo";

    public static void main(String[] args)
    {
        int arr[] = { 1, 5, 2, 1, 3, 2, 1 };
        int n = arr.length;
        System.out.println(encontrarIntMasFrecuente(arr, n));
    }


    static int encontrarIntMasFrecuente(int array[], int n)
    {
        //Ordenamos el arreglo
        Arrays.sort(array);

        int cuentaMax = 1, elemet = array[0];
        int cuentaActual = 1;
        for (int i = 1; i < n; i++) {
            if (array[i] == array[i - 1])
                cuentaActual++;

            else if (cuentaActual > cuentaMax) {
                cuentaMax = cuentaActual;
                elemet = array[i - 1];
            }
            cuentaActual = 1;
        }

        // Si el último elemento es el más frecuente:
        if (cuentaActual > cuentaMax) {
            cuentaMax = cuentaActual;
            elemet = array[n - 1];
        }

        return elemet;
    }

    // Driver program


}
