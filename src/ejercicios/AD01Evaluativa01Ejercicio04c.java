package ejercicios;

import java.io.*;
import java.util.ArrayList;

/*
 * Programa que permite visualizar todos los datos de los personajes
 * de un tipo específico (héroe o villano) introducido por el usuario.
 * Los datos se extraen de un archivo binario.
 */
public class AD01Evaluativa01Ejercicio04c {
    private static final int RECORD_LENGTH = 114; // Longitud de cada registro en bytes
    
    public static void main(String[] args) {
        // Ruta del fichero binario Marvel.dat
        String inputPathFile = "." + File.separator + "src" + File.separator + "ejercicios" + File.separator + "Marvel.dat";
        File file = new File(inputPathFile);
        
        // Abre el archivo en modo solo lectura y gestiona el cierre automáticamente
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r")) {
            
            // Solicitud al usuario del tipo de personaje
            System.out.println("Introduce un tipo de personaje: ");
            String inputUserType = Consola.readString();
            
            // Búsqueda de personajes en el archivo según el tipo introducido
            ArrayList<String> foundCharacters = characterSearch(randomAccessFile, inputUserType);
            
            // Muestra el resultado de la búsqueda
            if (foundCharacters.isEmpty()) {
                System.out.println("No existen " + inputUserType + "s en el fichero");
            } else {
                System.out.println("Se han encontrado " + foundCharacters.size() + " " + inputUserType + (foundCharacters.size() > 1 ? "s." : "."));
                for (String hero : foundCharacters) {
                    System.out.println("Personaje " + hero);
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
    
    /*
     * Busca personajes de un tipo específico en el fichero
     *
     * @param file El archivo desde donde se leen los personajes
     * @param inputUserType El tipo de personaje que busca el usuario
     * @return Una lista de personajes encontrados que coinciden con el tipo buscado
     * @throws IOException Si ocurre un error de entrada/salida durante la lectura
     */
    public static ArrayList<String> characterSearch(RandomAccessFile file, String inputUserType) throws IOException {
        long numberOfRecords = file.length() / RECORD_LENGTH; // Número de registros en el archivo
        ArrayList<String> listCharactersFound = new ArrayList<>();
        int position;
        String fileType;
        
        // Recorre cada registro del archivo para buscar coincidencias con el tipo especificado
        for (int i = 0; i < numberOfRecords; i++) {
            position = i * RECORD_LENGTH;
            file.seek(position);
            file.skipBytes(86); // Saltamos hasta el campo del tipo del personaje
            
            // Lectura del tipo de personaje en el archivo
            fileType = readStringFromFile(file, 10);
            
            // Si el tipo coincide, recolectamos los datos del personaje
            if (inputUserType.equalsIgnoreCase(fileType)) {
                file.seek(position); // Volvemos al inicio del registro
                collectsDataCharacter(listCharactersFound, file);
            }
        }
        
        return listCharactersFound;
    }
    
    /*
     * Recopila y almacena en una lista los datos de un personaje
     * Lee los campos del personaje en el archivo y los formatea en una línea con formato
     *
     * @param listCharactersFound La lista para almacenar los personajes encontrados
     * @param file El archivo desde donde se leen los datos del personaje
     * @throws IOException Si ocurre un error de entrada/salida durante la lectura
     */
    public static void collectsDataCharacter(ArrayList<String> listCharactersFound, RandomAccessFile file) throws IOException {
        // Variables temporales para almacenar datos
        int fileWeight, fileHeight;
        String fileDni, fileName, fileIdentity, fileType;
        
        file.skipBytes(4); // Salta el ID del personaje (4 bytes)
        
        // Lectura del DNI, nombre, identidad y tipo del personaje
        fileDni = readStringFromFile(file, 9);
        fileName = readStringFromFile(file, 12);
        fileIdentity = readStringFromFile(file, 20);
        fileType = readStringFromFile(file, 10);
        
        // Lectura del peso y altura del personaje
        fileWeight = file.readInt();
        fileHeight = file.readInt();
        
        // Formatea los datos en una sola línea con un formato fijo
        String formatedLine = String.format("[dni=%-9s, nombre=%-12s, identidad=%-20s, tipo=%-10s, peso=%d," +
                " altura=%d]", fileDni, fileName, fileIdentity, fileType, fileWeight, fileHeight);
        
        // Añade la línea formateada a la lista de personajes encontrados
        listCharactersFound.add(formatedLine);
    }
    
    /*
     * Lee una cadena de caracteres del fichero
     *
     * @param file El archivo desde donde se lee la cadena
     * @param length La longitud de la cadena a leer
     * @return La cadena leída del fichero
     * @throws IOException Si ocurre un error de entrada/salida durante la lectura
     */
    private static String readStringFromFile(RandomAccessFile file, int length) throws IOException {
        char[] aux = new char[length];
        for (int i = 0; i < length; i++) {
            aux[i] = file.readChar(); // Lectura de cada carácter
        }
        
        return new String(aux).trim(); // Retorno de la cadena sin espacios
    }
}
