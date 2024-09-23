package ejercicios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/*
 * Programa que permite cargar datos de personajes de Marvel desde arreglos
 * en un archivo de acceso aleatorio.
 * Crea un archivo nuevo (si existe, lo elimina).
 */
public class AD01Evaluativa01Ejercicio04a {
    private static final int RECORD_LENGTH = 114; // Longitud de cada registro en bytes
    
    public static void main(String[] args) {
        // Ruta del archivo de salida
        String outputPathFile = "." + File.separator + "src" + File.separator + "ejercicios" + File.separator + "Marvel.dat";
        File file = new File(outputPathFile);
        
        // Elimina el archivo si ya existe
        if (file.exists()) {
            file.delete();
        }
        
        // Intenta abrir el archivo en modo lectura y escritura y maneja el cierre automáticamente
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw")) {
            
            // Datos de los personajes de Marvel
            int[] ids = {1, 2, 3, 4, 5, 6, 7};
            String[] dnis = {"01010101A", "03030303C", "05050505E", "07070707G", "02020202B", "04040404D", "06060606F"};
            String[] noms = {"Spiderman", "Green Goblin", "Storm", "Wolverine", "Mystique", "IronMan", "Mandarin"};
            String[] identidades = {"Peter Parker", "Norman Osborn", "Ororo Munroe", "James Howlett", "Raven Darkholme", "Tony Stark", "Zhang Tong"};
            String[] tipos = {"heroe", "villano", "heroe", "heroe", "villano", "heroe", "villano"};
            int[] pesos = {76, 84, 66, 136, 78, 102, 70};
            int[] alturas = {178, 183, 156, 152, 177, 182, 188};
            
            // Llama al método para cargar los personajes en el fichero
            loadCharacters(randomAccessFile, ids, dnis, noms, identidades, tipos, pesos, alturas);
            
            // Manejo de excepciones
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo: " + outputPathFile);
        } catch (IOException e) {
            System.out.println("Error de entrada/salida: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
    
    /*
     * Carga los personajes en el archivo
     *
     * @param file El archivo en el que se almacenarán los personajes
     * @param ids, dnis, names, identities, types, weights, heights Arreglos con los
     * datos de los personajes
     */
    private static void loadCharacters(RandomAccessFile file, int[] ids, String[] dnis, String[] names,
                                       String[] identities, String[] types, int[] weights, int[] heights) {
        int howMany = dnis.length; // Total de personajes a introducir
        int successCount = 0; // Contador de inserciones exitosas
        
        // Escribe un registro en el fichero por cada personaje y lo contabiliza
        for (int i = 0; i < howMany; i++) {
            try {
                writeCharacter(file, ids[i], dnis[i], names[i], identities[i], types[i], weights[i], heights[i]);
                successCount++;
            } catch (IOException e) {
                System.out.println("Error al insertar el superhéroe con DNI: " + dnis[i]);
            }
        }
        
        // Mensaje informativo al usuario sobre el estado de la carga
        if (successCount == howMany) {
            System.out.println("La carga de los personajes ha terminado correctamente.");
        } else {
            System.out.println("Se han cargado " + successCount + " de " + howMany + " personajes");
        }
    }
    
    /*
     * Escribe un registro de personaje en el fichero
     *
     * @param file El archivo donde se almacenará el personaje
     * @param id, dni, name, identity, type, weight, height Los datos de un personaje
     * @throws IOException Si ocurre un error de entrada/salida al escribir en un archivo
     */
    private static void writeCharacter(RandomAccessFile file, int id, String dni, String name, String identity,
                                       String type, int weight, int height) throws IOException {
        int position = (id - 1) * RECORD_LENGTH;
        file.seek(position);
        
        // Escribe el ID del personaje (4 bytes)
        file.writeInt(id);
        
        // Escribe el DNI (18 bytes), nombre (24 bytes), identidad (40 bytes) y tipo del personaje (20 bytes)
        writeString(file, dni, 9);
        writeString(file, name, 12);
        writeString(file, identity, 20);
        writeString(file, type, 10);
        
        // Escribe el peso (4 bytes) y la altura (4 bytes) del personaje
        file.writeInt(weight);
        file.writeInt(height);
    }
    
    /*
     * Escribe una cadena de caracteres en el archivo con una longitud fija
     *
     * @param file El archivo donde se escribirá la cadena
     * @param line La cadena que se escribirá en el archivo
     * @param length La longitud fija que tendrá la cadena en el archivo
     * @throws IOException Si ocurre un error de entrada/salida al escribir en el archivo
     */
    private static void writeString(RandomAccessFile file, String line, int length) throws IOException {
        StringBuilder builder = new StringBuilder(line);
        builder.setLength(length); // Ajusta la longitud de la cadena
        file.writeChars(builder.toString()); // Escribe la cadena en el archivo
    }
}
