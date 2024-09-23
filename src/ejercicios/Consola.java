package ejercicios;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * Clase para gestionar las entradas del usuario por teclado
 */
public class Consola {
    
    // Metodo para leer un número entero válido
    public static int readInt() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int x = 0;
        boolean valid = false;
        
        while (!valid) {
            try {
                x = Integer.parseInt(in.readLine());
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Introduzca un numero");
            } catch (IOException e) {
                System.out.println("Error de entrada/salida: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }
        }
        return x;
    }
    
    // Metodo para leer una cadena válida
    public static String readString() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        boolean valid = false;
        
        while (!valid) {
            try {
                str = in.readLine();
                valid = true;
                
            } catch (IOException e) {
                System.out.println("Error de entrada/salida: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }
        }
        return str;
    }
}
