package ejercicios;

import java.io.*;

public class AD01Evaluativa01Ejercicio02 {
    public static void main(String[] args) {

        // Rutas de los archivos de entrada y salida
        String inputPathFile = "." + File.separator + "src" + File.separator + "ejercicios" + File.separator + "inputFileExercise02.txt";
        String outputPathFile = "." + File.separator + "src" + File.separator + "ejercicios" + File.separator + "outputFileExercise02.txt";

        // Bloque try-with-resources para manejar archivos automáticamente
        try (
                BufferedReader br = new BufferedReader(new FileReader(inputPathFile));
                BufferedWriter bw = new BufferedWriter(new FileWriter(outputPathFile))
        ) {

            String line;
            // Lee cada línea del archivo de entrada
            while ((line = br.readLine()) != null) {
                String[] words = line.split(" "); // Separa la línea en palabras

                // Verifica si hay palabras y si la primera tiene exactamente 5 letras
                if (words.length > 0 && words[0].length() == 5) {
                   bw.write(words[0]); // Escribe el nombre en el archivo de salida
                   bw.newLine();
                }
            }

        // Manejo de excepciones
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo: " + inputPathFile);
        } catch (IOException e) {
            System.out.println("Error de entrada/salida: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
