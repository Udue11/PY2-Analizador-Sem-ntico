/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lexico;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Froylan Lara Oses
 */
public class AnalizadorLexico {
    public static String rutap = "C:/Users/Froylan Lara Oses/Downloads/ejemplo código 1.sintactico.txt";
    //public static String rutap = "C:/Users/Froylan Lara Oses/Downloads/ejemplo código 2.sintactico.txt";
    
    public static void main(String[] args) {
        String ruta = "..//Analizador/src/lexico/lexer.flex";
        GenerarLexer(ruta);
        
        AnalizarDoc(rutap);
    }
    public static void GenerarLexer(String ruta){
        File archivo = new File(ruta);
        JFlex.Main.generate(archivo);
    }
    public static void AnalizarDoc(String ruta) {
        try {
            String txt = Files.readString(Path.of(ruta));            
            Lexer lexer = new Lexer(new StringReader(txt));
            String res = "";
            while (true) {     
                Tokens tokens = lexer.yylex();                
                if (tokens == null){                    
                    String rutaRes = "resAnalisisLexico.txt";
                    SobreEscribirEnArchivo(rutaRes,res);
                    return;
                }
                
                switch(tokens){
                    case ERROR:
                        res += lexer.lexeme+" -> "+ "Error. Simbolo no encontrado\n";
                        System.out.println(lexer.lexeme+" -> "+ "Error. Simbolo no encontrado");
                        break;
                    case IDENTIFICADOR:case ENDLINE:case SEPARADOR:case SIGNOIGUAL:case DOSPUNTOS:case COMA:case LITINT:case LITFLOAT:case LITTRUE:case LITFALSE:case LITCHAR:case LITSTRING:case TIPOINT:case TIPOFlOAT:case TIPOBOOL:case TIPOCHAR:case TIPOSTRING:case SIGRESTA:case SIGSUMA:case SIGMULTIPLICACION:case SIGDIVISION:case SIGMODULO:case SIGPOTENCIA:case CONGUNCION:case DISYUNSION:case NEGACION:case AUMENTAR:case DISMINUIR:case MENOR:case MENORIGUAL:case MAYOR:case MAYORIGUAL:case IGUAL:case DIFERENTE:case ABREPARENTESIS:case CIERRAPARENTESIS:case ABRELLAVE:case CIERRALLAVE:case RESERVREAD:case RESERVWRITE:case RESERVIF:case RESERVELSE:case RESERVSWITCH:case RESERVWHILE:case RESERVFORRANGE:case RESERVMAIN:case RESERVBREAK:case RESERVRETURN:case RESERVCASE:case RESERVDEFAULT:
                        res += lexer.lexeme+"-> "+ tokens + "\n";
                        System.out.println(lexer.lexeme+"-> "+ tokens);
                        break;
                    default:
                        res += "Token -> "+ tokens + "\n";
                        System.out.println("Token -> "+ tokens);                        
                        break;
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Error: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        } 
    }
    public static void SobreEscribirEnArchivo(String ruta, String texto){
        FileWriter escribir = null;
        try {
            PrintWriter pw = new PrintWriter(ruta);
            pw.close();
            File archivo = new File(ruta);
            escribir = new FileWriter(archivo, false);
            escribir.write(texto);
            escribir.close();
        } catch (IOException ex) {
            Logger.getLogger(AnalizadorLexico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static void ClearFile(String ruta){
        try {
            PrintWriter pw = new PrintWriter(ruta);
            pw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AnalizadorLexico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
