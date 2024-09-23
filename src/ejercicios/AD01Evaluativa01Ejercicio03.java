package ejercicios;

import java.io.*;
import java.util.Arrays;

/*
 * Programa que lee la cabecera de un fichero PDF y verifica
 * si realmente se trata de un archivo PDF válido.
 */
public class AD01Evaluativa01Ejercicio03 {
    public static void main(String[] args) {
        
        // Ruta del archivo de entrada
        String inputPathFile = "." + File.separator + "src" + File.separator + "ejercicios" + File.separator + "inputPDFExercise03.pdf";
        //String inputPathFile = "." + File.separator + "src" + File.separator + "ejercicios" + File.separator + "inputFileExercise01.txt"; // Ejemplo de PDF no válido
        File inputFile = new File(inputPathFile);
        
        // Firma PDF esperada
        byte[] pdfSignature = {37, 80, 68, 70};
        
        // Bloque try-with-resources para asegurar el cierre del archivo
        try (InputStream inputStream = new FileInputStream(inputFile)) {
            // Array para almacenar la cabecera del fichero
            byte[] inputPdfHeader = new byte[4];
            
            // Lectura la secuencia de bytes de la cabecera del fichero
            int bytesRead = inputStream.read(inputPdfHeader);
            
            // Verificar si se leyeron la cantidad de elementos esperados
            if (bytesRead != 4) {
                System.out.println("Error: No se puede leer el archivo");
                return;
            }
            
            // Comprueba si el archivo PDF es válido
            if (Arrays.equals(pdfSignature, inputPdfHeader)) {
                System.out.println("El archivo es un PDF válido.");
            } else {
                System.out.println("El archivo no es un PDF válido.");
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
