package ejercicios;

import java.io.*;

/*
 * Programa para leer una línea de un archivo y generar otro
 * con los caracteres de cada palabra invertidos.
 */
public class AD01Evaluativa01Ejercicio01 {
    public static void main(String[] args)  {

        // Rutas de los archivos de entrada y salida
        String inputPathFile = "." + File.separator + "src" + File.separator + "ejercicios" + File.separator + "inputFileExercise01.txt";
        String outputPathFile = "." + File.separator + "src" + File.separator + "ejercicios" + File.separator + "outputFileExercise01.txt";

        // Bloque try-with-resources para manejar archivos automáticamente
        try (
                FileReader fr = new FileReader(inputPathFile);
                FileWriter fw = new FileWriter(outputPathFile)
        ) {

            StringBuilder inputLine = new StringBuilder();
            StringBuilder reversedOutLine = new StringBuilder();
            int character;

            // Lectura del archivo carácter por carácter
            while ((character = fr.read()) != -1) {
                inputLine.append((char) character);
            }

            String[] words = inputLine.toString().split(" "); // Separa la línea en palabras

            // Invierte cada palabra y construye una nueva línea
            for (String word : words) {
                reversedOutLine.append(reverse(word));
            }

            // Escribe la línea con las palabras invertidas en el archivo de salida
            fw.write(reversedOutLine.toString().trim());

        // Manejo de excepciones
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo: " + inputPathFile);
        } catch (IOException e) {
            System.out.println("Error de entrada/salida: " + e.getMessage());
        } catch (Exception e) {
           System.out.println("Error inesperado: " + e.getMessage());
        }
    }

    // Metodo para invertir los carácteres de una palabra
    public static String reverse(String word) {
        StringBuilder reversedWord = new StringBuilder();
        for (int i = word.length() - 1; i >= 0; i--) {
            reversedWord.append(word.charAt(i));
        }

        return reversedWord + " ";
    }
}
