package ejercicios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class AD01Evaluativa01Ejercicio04a {
    public static void main(String[] args) {

        final int recordLength = 110;
        String inputPathFile = "." + File.separator + "src" + File.separator + "ejercicios" + File.separator + "Marvel.dat";
        File file = new File(inputPathFile);

        if (!file.exists()) {
            file.delete();
        }

        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw")) {

            int[] ids= {1, 2, 3, 4, 5, 6, 7};
            String[] dnis= {"01010101A", "03030303C", "05050505E", "07070707G", "02020202B", "04040404D", "06060606F"};
            String[] noms= {"Spiderman", "Green Goblin", "Storm", "Wolverine", "Mystique", "IronMan", "Mandarin"};
            String[] identidades = {"Peter Parker", "Norman Osborn", "Ororo Munroe","James Howlett", "Raven Darkholme", "Tony Stark", "Zhang Tong"};
            String[] tipos = {"heroe","villano","heroe","heroe","villano","heroe", "villano"};
            int[] pesos = {76,84,66,136,78,102,70};
            int[] alturas = {178,183,156,152,177,182,188};

            StringBuffer bufferDnis = null;
            StringBuffer bufferNoms = null;
            StringBuffer bufferIdentidades = null;
            StringBuffer bufferTipos = null;
            int howMany = dnis.length;
            int successCount = 0;
            int position = 0;

            for (int i = 0; i < howMany; i++) {
                try {

                    position = i * recordLength;
                    randomAccessFile.seek(position);

                    // Mensaje para depurar
                    // System.out.println("Empezamos a insertar superhéroe " + (i+1));

                    // Escribe ID (4 bytes)
                    randomAccessFile.writeInt(ids[i]);

                    // Escribe el DNI (18 bytes)
                    bufferDnis = new StringBuffer(dnis[i]);
                    bufferDnis.setLength(9);
                    randomAccessFile.writeChars(bufferDnis.toString());

                    // Escribe el nombre (20 bytes)
                    bufferNoms = new StringBuffer(noms[i]);
                    bufferNoms.setLength(10);
                    randomAccessFile.writeChars(bufferNoms.toString());

                    // Escribe la identidad secreta (40 bytes)
                    bufferIdentidades = new StringBuffer(identidades[i]);
                    bufferIdentidades.setLength(20);
                    randomAccessFile.writeChars(bufferIdentidades.toString());

                    // Escribe el tipo (20 bytes)
                    bufferTipos = new StringBuffer(tipos[i]);
                    bufferTipos.setLength(10);
                    randomAccessFile.writeChars(bufferTipos.toString());

                    // Escribe el peso (4 bytes)
                    randomAccessFile.writeInt(pesos[i]);

                    // Escribe la altura (4 bytes)
                    randomAccessFile.writeInt(alturas[i]);

                    // Mensaje para depurar
                    // System.out.println("Superhéroe " + (i+1) + " insertado correctamente");

                    successCount++;

                } catch (IOException e) {
                    System.out.println("Error al insertar el superhéroe con id: " + ids[i]);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            if (successCount == howMany) {
                System.out.println("La carga de los personajes ha terminado correctamente");
            } else {
                System.out.println("Se han cargado " + successCount + " de " + howMany + " personajes");
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
