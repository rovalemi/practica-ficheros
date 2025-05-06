
package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;

public class Funciones {
    
    static String pathG = System.getProperty("user.dir");
    static String sep = File.separator;
    static FileWriter fw = null;
    static BufferedWriter bw = null;
    
    /**
     * Se crea una nueva carpeta si no existe aun
     * @param fileName 
     */
    public static void createFolder(String fileName) {
        String pathFolder = pathG + sep + fileName;
        File folder = new File(pathFolder);
        if (!folder.exists()) {
            folder.mkdir();
        }
    }
    /**
     * Se crea un archivo txt preguntando la ruta, nombre y contenido
     * @param path
     * @param fileName
     * @param content 
     */
    public static void createFile(String path, String fileName, String content) {
        createFolder(path);
        try {
            File file = new File(pathG + sep + path + sep + fileName + ".txt");
            fw = new FileWriter(file, true);
            bw = new BufferedWriter(fw);
            bw.write(content);
            bw.newLine();
            bw.flush();
            bw.close();
        } catch (IOException ex) {
            System.out.println("Error al crear o escribir en el fichero:" + ex.getMessage());
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                System.out.println("Error al cerrar el fichero.");
            }
        }
    }
    
    /**
     * Se muestran todos los archivos de una ruta
     * @param path
     * @return 
     */
    public static String[] showListFiles(String path) {
        File folder = new File(path);
        
        if (!folder.exists() || !folder.isDirectory()) {
            return new String[]{"La ruta no existe o no es una carpeta valida."};
        }
        
        File[] files = folder.listFiles();
        if (files == null || files.length == 0) {
            return new String[]{"La carpeta esta vacia."};
        }
        
        String[] names = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            names[i] = files[i].getName();
        }
        
        return names;
    }
    
    /**
     * Se muestra el contenido de un archivo
     * @param path
     * @param fileName
     * @return
     * @throws FileNotFoundException 
     */
    public static String showFile(String path, String fileName) throws FileNotFoundException {
        String content = "";
        File file = new File(pathG + sep + path + sep + fileName + ".txt");
        try (Scanner scan = new Scanner(file)) {
            scan.useDelimiter("\n");
            String line;
            while (scan.hasNext()) {
                line = scan.next();
                content += line + "\n";
            }
            scan.close();
        }
        return content;
    }
    
    /**
     * Se sobreescribe el contenido de un archivo
     * @param path
     * @param fileName
     * @param content
     * @return 
     */
    public static boolean overWriteFile(String path, String fileName, String content) {
        File file = new File(pathG + sep + path + sep + fileName + ".txt");
        if (!file.exists()) {
            return false;
        }
        try {
            fw = new FileWriter(file, false);
            bw = new BufferedWriter(fw);
            bw.write(content);
            bw.newLine();
            bw.flush();
            bw.close();
            return true;
        } catch (IOException ex) {
            System.out.println("Error al escribir el archivo." + ex.getMessage());
            return false;
        }
    }
    
    /**
     * Se elimina el archivo
     * @param path
     * @param fileName 
     */
    public static void deleteFile(String path, String fileName) {
        File file = new File(pathG + sep + path + sep + fileName + ".txt");
        
        if (file.exists()) {
            file.delete();
        }
    }
    
    /**
     * Se cuentan los caracteres de un archivo
     * @param path
     * @param fileName
     * @return
     * @throws FileNotFoundException 
     */
    public static int countChars(String path, String fileName) throws FileNotFoundException {
        File file = new File(pathG + sep + path + sep + fileName + ".txt");
        int count = 0;
        
        Scanner scan = new Scanner(file);
        String line;
        while (scan.hasNext()) {
            line = scan.next();
            count += line.length();
        }
        
        return count;
    }
    
    /**
     * Se cuentan las palabras de una archivo
     * @param path
     * @param fileName
     * @return 
     */
    public static int countWords(String path, String fileName) {
        File file = new File(pathG + sep + path + sep + fileName + ".txt");
        int count = 0;
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNext()) {
                scan.next();
                count++;
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Error al encontrar el archivo." + ex.getMessage());
        }
        return count;
    }
    
    /**
     * Se cambian las palabras selccionadas por otra nueva
     * @param path
     * @param fileName
     * @param oldWord
     * @param newWord
     * @return
     * @throws FileNotFoundException 
     */
    public static String swapWords(String path, String fileName, String oldWord, String newWord) throws FileNotFoundException {
        File file = new File(pathG + sep + path + sep + fileName + ".txt");
        StringBuilder content = new StringBuilder();
        
        try (Scanner scan = new Scanner(file)) {
            scan.useDelimiter("\n");
            while(scan.hasNext()) {
                String line = scan.next();
                line = line.replace(oldWord, newWord);
                content.append(line).append("\n");
            }
        }
        overWriteFile(path, fileName, content.toString().trim());
        
        return content.toString();
    }
    
    /**
     * Se crea un archivo PDF
     * @param path
     * @param fileName 
     */
    public static void printPDF(String path, String fileName) {      
        Document document = new Document();
        
        try {
            String pathFolder = "C:/Users/ALIZON ROSALES/Downloads";
            String fullPath = pathFolder + "/" + path + "/" + fileName + ".pdf";
            PdfWriter.getInstance(document, new FileOutputStream(fullPath));
            document.open();
            document.add(new Paragraph("PDF Demo"));
            System.out.println("PDF creado en: " + fullPath);
        } catch (DocumentException | FileNotFoundException ex) {
            System.out.println("Error al encontrar el archivo." + ex.getMessage());
        } finally {
            document.close();
        }
    }
}