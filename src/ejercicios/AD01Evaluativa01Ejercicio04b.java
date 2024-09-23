package ejercicios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/*
 * Programa que permite modificar los datos (el peso) de un personaje.
 * El usuario introduce el DNI y el peso del último mes, y el programa
 * verifica si existe el personaje en el fichero, informando por consola
 * si ha cambiado de peso y actualizando el registro.
 */
public class AD01Evaluativa01Ejercicio04b {
    private static final int RECORD_LENGTH = 114; // Longitud de cada registro en bytes
    
    public static void main(String[] args) {
        // Ruta del fichero
        String inputPathFile = "." + File.separator + "src" + File.separator + "ejercicios" + File.separator + "Marvel.dat";
        File file = new File(inputPathFile);
        
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw")) {
            
            // Solicitud al usuario del DNI del personaje
            System.out.println("Introduzca el DNI (con letra) del personaje para control de peso: ");
            String inputUserDni = Consola.readString();
            
            // Verifica si el personaje existe
            if (characterExist(randomAccessFile, inputUserDni)) {
                collectsDataCharacter(randomAccessFile);
            } else {
                System.out.println("No existe el personaje con DNI " + inputUserDni);
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
    
    /*
     * Verifica si el personaje existe en el fichero basándose en su DNI
     *
     * @param file El archivo donde se busca el personaje
     * @param inputUserDni El DNI del personaje a buscar
     * @return true si el personaje existe, false si no
     * @throws IOException Si ocurre un error de entrada/salida
     */
    private static boolean characterExist(RandomAccessFile file, String inputUserDni) throws IOException {
        long numberOfRecords = file.length() / RECORD_LENGTH;
        String fileCharacterDni;
        
        for (int i = 0; i < numberOfRecords; i++) {
            file.seek((long) i * RECORD_LENGTH);
            file.skipBytes(4); // Salta el ID del personaje
            
            // Lectura del DNI del personaje
            fileCharacterDni = readStringFromFile(file, 9);
            if (fileCharacterDni.equalsIgnoreCase(inputUserDni)) {
                return true; // Personaje encontrado
            }
        }
        
        return false; // Personaje no encontrado
    }
    
    /*
     * Solicita al usuario el peso actual del personaje y actualiza el registro si
     * ha cambiado su peso
     *
     * @param file El archivo donde se encuentra el registro del personaje
     * @throws IOException Si ocurre un error de entrada/salida
     */
    public static void collectsDataCharacter(RandomAccessFile file) throws IOException {
        String fileName;
        int inputUserWeight, fileWeight;
        
        // Solicitud al usuario del peso actual del personaje
        System.out.println("Introduzca su peso actual: ");
        inputUserWeight = Consola.readInt();
        
        // Lectura del nombre del personaje
        fileName = readStringFromFile(file, 12);
        file.skipBytes(60); // Salto de identidad y tipo de personaje
        long weightPosition = file.getFilePointer(); // Almacenamos la posición del puntero en el fichero
        
        // Lectura del peso del personaje
        fileWeight = file.readInt();
        
        // Compara pesos y actualiza el registro si es necesario
        if (inputUserWeight != fileWeight) {
            compareWeights(fileName, inputUserWeight, fileWeight);
            file.seek(weightPosition);
            file.writeInt(inputUserWeight); // Actualiza el peso en el archivo
            
        } else {
            System.out.println(fileName + " se mantiene en su peso anterior.");
        }
    }
    
    /*
     * Compara el peso introducido por el usuario con el peso guardado y
     * muestra el resultado por consola
     *
     * @param fileName El nombre del personaje
     * @param userWeight El peso introducido por el usuario
     * @param fileWeight El peso almacenado en el archivo
     */
    private static void compareWeights(String fileName, int userWeight, int fileWeight) {
        int difference = userWeight - fileWeight;
        String change = difference > 0 ? "engordado" : "adelgazado";
        System.out.println(fileName + " ha " + change + " " + Math.abs(difference) + " kilo" + (Math.abs(difference) > 1 ? "s." : "."));
    }
    
    /*
     * Lee una cadena de caracteres del fichero
     *
     * @param file El archivo desde donde se lee la cadena
     * @param length La longitud de la cadena a leer
     * @return La cadena leída del fichero
     * @throws IOException Si ocurre un error de entrada/salida
     */
    private static String readStringFromFile(RandomAccessFile file, int length) throws IOException {
        char[] aux = new char[length];
        for (int i = 0; i < length; i++) {
            aux[i] = file.readChar(); // Lectura de cada carácter
        }
        
        return new String(aux).trim(); // Retorno de la cadena sin espacios
    }
}
