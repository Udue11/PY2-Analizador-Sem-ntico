/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sintaxis;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Froylan Lara Oses
 */
public class AnalixadorSintactico {
    public static void main(String[] args) {

        String ruta1 = "..//Analizador/src/lexico/Lexer.flex";
        String ruta2 = "..//Analizador/src/sintaxis/LexerCup";
        String[] rutaS = {"-parser", "Syntax", "..//Analizador/src/sintaxis/Syntax.cup"};
        generar(ruta1, ruta2, rutaS);
    }

    public static void generar(String ruta1, String ruta2, String[] rutaS) {
        try {
            File archivo = new File(ruta1);
            JFlex.Main.generate(archivo);
            archivo = new File(ruta2);
            JFlex.Main.generate(archivo);
                        
            java_cup.Main.main(rutaS);
            
            Path rutaSym = Paths.get("..//Analizador/src/sintaxis/sym.java");

            if (Files.exists(rutaSym)) {
                Files.delete(rutaSym);
            }
            Files.move(
                    Paths.get("..//Analizador/sym.java"),
                    Paths.get("..//Analizador/src/sintaxis/sym.java"));
            System.out.println("X");

            Path rutaSin = Paths.get("..//Analizador/src/sintaxis/Syntax.java");
            if (Files.exists(rutaSin)) {
                Files.delete(rutaSin);
            }
            System.out.println("Y");
            Files.move(
                    Paths.get("..//Analizador/Syntax.java"),
                    Paths.get("..//Analizador/src/sintaxis/Syntax.java"));
            System.out.println("Z");
        } catch (IOException ex) {
            Logger.getLogger(AnalixadorSintactico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AnalixadorSintactico.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
