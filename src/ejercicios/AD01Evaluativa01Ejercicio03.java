package ejercicios;

import java.io.*;
import java.util.Arrays;

/*
 * Programa para verificar si un archivo
 * es realmente un PDF
 */
public class AD01Evaluativa01Ejercicio03 {
    public static void main(String[] args) {

        // Ruta del archivo de entrada
        //String inputPathFile = "." + File.separator + "src" + File.separator + "ejercicios" + File.separator + "inputPDFExercise03.pdf";
        String inputPathFile = "." + File.separator + "src" + File.separator + "ejercicios" + File.separator + "inputFileExercise01.txt"; // Ejemplo de PDF no válido
        File inputFile = new File(inputPathFile);

        // Firma PDF esperada (primeros 4 bytes de un archivo PDF)
        byte[] pdfSignature = {37, 80, 68, 70};

        // Bloque try-with-resources para asegurar el cierre del archivo
        try (InputStream inputStream = new FileInputStream(inputFile)) {
            // Array para almacenar los primeros 4 bytes (cabecera) del archivo PDF
            byte[] pdfHeader = new byte[4];

            // Lee los primeros 4 bytes del archivo
            int bytesRead = inputStream.read(pdfHeader);

            // Verificar si se leyeron exactamente 4 bytes
            if (bytesRead != 4) {
                System.out.println("Error: No se puede leer el archivo");
                return;
            }

            /*
            // Código opcional para imprimir la cabecera leída y verificar su contenido
            for (byte b : pdfHeader) {
                System.out.println(b);
            }
            */

            // Comparar la cabecera leída con la firma PDF esperada
            if (!Arrays.equals(pdfSignature, pdfHeader)) {
                System.out.println("Error: No es un archivo PDF válido");
                System.exit(-1);
            } else {
                System.out.println("PDF file found");
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
