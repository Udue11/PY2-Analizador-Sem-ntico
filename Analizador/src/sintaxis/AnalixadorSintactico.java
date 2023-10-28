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

        String ruta1 = "..//AnalizadorLexicoSintactico/src/lexico/Lexer.flex";
        String ruta2 = "..//AnalizadorLexicoSintactico/src/sintaxis/LexerCup";
        String[] rutaS = {"-parser", "Syntax", "..//AnalizadorLexicoSintactico/src/sintaxis/Syntax.cup"};
        generar(ruta1, ruta2, rutaS);
    }

    public static void generar(String ruta1, String ruta2, String[] rutaS) {
        try {
            File archivo = new File(ruta1);
            JFlex.Main.generate(archivo);
            archivo = new File(ruta2);
            JFlex.Main.generate(archivo);
            
            System.out.println(rutaS[2]);
            System.out.println("X");
            
            java_cup.Main.main(rutaS);
            System.out.println("y");
            
            Path rutaSym = Paths.get("..//src/sintaxis/sym.java");
            System.out.println("z");
            if (Files.exists(rutaSym)) {
                Files.delete(rutaSym);
            }
            Files.move(
                    Paths.get("..//sym.java"),
                    Paths.get("..//src/sintaxis/sym.java"));

            Path rutaSin = Paths.get("..//src/sintaxis/Syntax.java");
            if (Files.exists(rutaSin)) {
                Files.delete(rutaSin);
            }
            Files.move(
                    Paths.get("..//Sintax.java"),
                    Paths.get("..//src/sintaxis/Syntax.java"));
        } catch (IOException ex) {
            Logger.getLogger(AnalixadorSintactico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AnalixadorSintactico.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
