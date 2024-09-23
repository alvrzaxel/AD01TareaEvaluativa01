package ejercicios;

import java.io.*;

/*
 * Programa que lee un fichero con nombres y apellidos separados
 * por espacios y escribe en un nuevo fichero solo los nombres
 * que tienen exactamente cinco letras.
 */
public class AD01Evaluativa01Ejercicio02 {
    public static void main(String[] args) {
        
        // Rutas de los archivos de entrada y salida
        String inputPathFile = "." + File.separator + "src" + File.separator + "ejercicios" + File.separator + "inputFileExercise02.txt";
        String outputPathFile = "." + File.separator + "src" + File.separator + "ejercicios" + File.separator + "outputFileExercise02.txt";
        
        // Bloque try-with-resources para manejar archivos automáticamente
        try (
                BufferedReader bffReader = new BufferedReader(new FileReader(inputPathFile));
                BufferedWriter bffWriter = new BufferedWriter(new FileWriter(outputPathFile))
        ) {
            // Variable temporal para almacenar cada línea del fichero
            String inputLine;
            
            // Lectura línea a línea del fichero de entrada
            while ((inputLine = bffReader.readLine()) != null) {
                String[] words = inputLine.split(" "); // Separa la línea en palabras
                
                // Comprueba si hay palabras y si la primera tiene exactamente 5 letras
                if (words.length > 0 && words[0].length() == 5) {
                    bffWriter.write(words[0]); // Escribe el nombre en el archivo de salida
                    bffWriter.newLine();
                }
            }
            
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
