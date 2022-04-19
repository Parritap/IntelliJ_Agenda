package co.edu.uniquindio.agenda.test;

public class RandomTest {

    public static void main(String[] args) {


        String str = "\0";

        if (str.equals("\0"))
            System.out.println( "Vacio");

        else {
            System.out.println("No está vacio");
        }

        char c = str.charAt(0);
        System.out.println(c);

    }
}
