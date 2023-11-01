/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sintaxis;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

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
            System.out.println("1");
        
        try {
            String txt = Files.readString(Path.of(lexico.AnalizadorLexico.rutap));
            System.out.println("2");
        
            Syntax s = new Syntax(new sintaxis.LexerCup(new StringReader(txt)));
            System.out.println("3");

            List<String> errores = new ArrayList<>();
            System.out.println("4");

            s.parse();
            System.out.println("5");
            System.out.println(s);
            if (errores.isEmpty()) {
                System.out.println("Analisis realizado correctamente");
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Se encontraron los siguientes errores de sintaxis:\n");
                for (String error : errores) {
                    sb.append(error).append("\n");
                }
                System.out.println(sb.toString());
            }
            System.out.println("6");
        } catch (Exception ex) {
            System.err.println(ex);
            //Sym sym = s.getS();
            /*errores.add("Error de sintaxis. Linea: " + (sym.right + 1) +
                    " columna: " + (sym.left + 1) + ", Texto: \"" + sym.value + "\"");
            StringBuilder sb = new StringBuilder();
            sb.append("Se encontraron los siguientes errores de sintaxis:\n");
            for (String error : errores) {
                sb.append(error).append("\n");
            }
            txtSintactico.setText(sb.toString());
            txtSintactico.setForeground(Color.red);*/
        }
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
