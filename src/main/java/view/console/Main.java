
package view.console;

import java.io.FileNotFoundException;
import java.util.Scanner;
import model.Funciones;

public class Main {

    static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        sc.useDelimiter("\n");
        
        String option;
        
        do {
            System.out.println("********** MENU **********");
            System.out.println("1.- void createFolder(String fileName)");
            System.out.println("2.- void createFile(String path, String fileName, String content)");
            System.out.println("3.- String[] showListFiles(String path)");
            System.out.println("4.- String showFile(String path, String fileName)");
            System.out.println("5.- boolean overWriteFile(String path, String fileName, String newContent)");
            System.out.println("6.- void deleteFile(String path, String filename)");
            System.out.println("7.- int countChars(String path, String fileName)");
            System.out.println("8.- int countWords(String path, String fileName)");
            System.out.println("9.- String swapWords(String path, String fileName, String oldWord, String newWorld)");
            System.out.println("10.- void printPDF(String path, String fileName)");
            System.out.println("Z.- Salir");
            
            System.out.print("Option: ");
            option = sc.next();
            
            String fileName;
            String path;
            String content;
            
            switch (option) {
                case "1" -> {
                    System.out.print("Ingresa el nombre de la carpeta: ");
                    fileName = sc.next();
                    Funciones.createFolder(fileName);
                }
                case "2" -> {
                    System.out.print("Ingresa el nombre de la carpeta: ");
                    path = sc.next();
                    System.out.print("Ingresa el nombre del archivo: ");
                    fileName = sc.next();
                    System.out.print("Ingresa el contenido del archivo: ");
                    content = sc.next();
                    Funciones.createFile(path, fileName, content);
                }
                case "3" -> {
                    System.out.print("Ingresa el nombre de la carpeta: ");
                    path = sc.next();
                    System.out.println("Listado de archivos:");
                    String[] names = Funciones.showListFiles(path);
                    for (String name : names) {
                        System.out.println(name);
                    }
                }
                case "4" -> {
                    try {
                        System.out.print("Ingresa el nombre de la carpeta: ");
                        path = sc.next();
                        System.out.print("Ingresa el nombre del archivo: ");
                        fileName = sc.next();
                        System.out.println("Contenido del archivo:");
                        content = Funciones.showFile(path, fileName);
                        System.out.println(content);
                    } catch (FileNotFoundException ex) {
                        System.out.println("Error al encontrar el archivo. " + ex.getMessage());
                    }
                }
                case "5" -> {
                    System.out.print("Ingresa nombre de carpeta: ");
                    path = sc.next();
                    System.out.print("Ingresa nombre del archivo: ");
                    fileName = sc.next();
                    System.out.print("Ingresa el contenido del archivo: ");
                    content = sc.next();
                    boolean fileExists = Funciones.overWriteFile(path, fileName, content);
                    if (fileExists) {
                        System.out.println("Archivo modificafdo correctamente.");
                    } else {    
                        System.out.println("No existe el archivo.");
                    }
                }
                case "6" -> {
                    System.out.print("Ingresa nombre de carpeta: ");
                    path = sc.next();
                    System.out.print("Ingresa nombre del archivo: ");
                    fileName = sc.next();
                    Funciones.deleteFile(path, fileName);
                }
                case "7" -> {
                    try {
                        System.out.print("Ingresa nombre de carpeta: ");
                        path = sc.next();
                        System.out.print("Ingresa nombre del archivo: ");
                        fileName = sc.next();
                        int count = Funciones.countChars(path, fileName);
                        System.out.println("El numero de caracteres es: " + count);
                    } catch (FileNotFoundException ex) {
                        System.out.println("Error al encontrar el archivo." + ex.getMessage());
                    }
                }
                case "8" -> {
                    System.out.print("Ingresa nombre de carpeta: ");
                    path = sc.next();
                    System.out.print("Ingresa nombre del archivo: ");
                    fileName = sc.next();
                    int count = Funciones.countWords(path, fileName);
                    System.out.println("El numero de caracteres es: " + count);
                }
                case "9" -> {
                    try {
                        System.out.print("Ingresa nombre de carpeta: ");
                        path = sc.next();
                        System.out.print("Ingresa nombre del archivo: ");
                        fileName = sc.next();
                        System.out.print("Ingresa la palabra antigua: ");
                        String oldWord = sc.next();
                        System.out.print("Ingresa la palabra nueva: ");
                        String newWord = sc.next();
                        content = Funciones.swapWords(path, fileName, oldWord, newWord);
                        System.out.println("Contenido actualizado del archivo:");
                        System.out.println(content);
                    } catch (FileNotFoundException ex) {
                        System.out.println("Error al encontrar el archivo." + ex.getMessage());
                    }
                }
                case "10" -> {
                    System.out.print("Ingresa nombre de carpeta: ");
                    path = sc.next();
                    System.out.print("Ingresa nombre del archivo: ");
                    fileName = sc.next();
                    Funciones.printPDF(path, fileName);
                }
                default -> System.out.println("Incorrect option.");
            }
            
            if (!option.equals("4") && !option.equals("9")) {
                System.out.println("");
            }
            
        } while (!option.equalsIgnoreCase("Z"));
    }
}