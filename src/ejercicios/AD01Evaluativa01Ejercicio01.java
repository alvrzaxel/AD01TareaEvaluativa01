package ejercicios;

import java.io.*;

/*
 * Programa para leer una línea de un archivo y generar otro
 * con los caracteres de cada palabra invertidos.
 */
public class AD01Evaluativa01Ejercicio01 {
    public static void main(String[] args) {
        
        // Rutas de los archivos de entrada y salida
        String inputPathFile = "." + File.separator + "src" + File.separator + "ejercicios" + File.separator + "inputFileExercise01.txt";
        String outputPathFile = "." + File.separator + "src" + File.separator + "ejercicios" + File.separator + "outputFileExercise01.txt";
        
        // Bloque try-with-resources para manejar archivos automáticamente
        try (
                FileReader fReader = new FileReader(inputPathFile);
                FileWriter fWriter = new FileWriter(outputPathFile)
        ) {
            
            // Variables temporales para almacenar datos
            StringBuilder inputLine = new StringBuilder();
            StringBuilder reversedOutLine = new StringBuilder();
            int character;
            
            // Lectura del archivo carácter por carácter
            while ((character = fReader.read()) != -1) {
                inputLine.append((char) character);
            }
            
            // Divide la línea por palabras
            String[] words = inputLine.toString().split(" ");
            StringBuilder reversedWord = new StringBuilder();
            for (String word : words) {
                reversedWord.setLength(0);
                reversedOutLine.append(reversedWord.append(word).reverse()); // Invierte la palabra y la añade
                reversedOutLine.append(" ");
            }
            
            // Escritura de la línea en el archivo de salida
            fWriter.write(reversedOutLine.toString().trim());
            
            // Manejo de excepciones
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo: " + inputPathFile);
        } catch (IOException e) {
            System.out.println("Error de entrada/salida: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
}
