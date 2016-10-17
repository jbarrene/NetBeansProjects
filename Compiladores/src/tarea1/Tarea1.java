/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea1;

import java.io.IOException;
import java.util.Scanner;
import static tarea2.Tarea2.*;

/**
 *
 * Proyecto: Tarea1
 * Creado por: Jose Pablo Barrenechea Azofeifa
 * Curso: Compiladores
 * UNED
 * 
 **/

public class Tarea1 {

    public static void main(String[] args) throws IOException {
        // Menú Principal
        
        // Definición de variables requeridas
        String entradaUsuario = "VERS";
        String entradaUsuarioCaps= null;
        
        // Objeto que maneja entrada el usuario
        Scanner datoEntrada = new Scanner (System.in); 
                   
        // Bucle para ejecución del programa
        VERS();
        digitaLinea();
        while (null!=entradaUsuario){
            entradaUsuario = datoEntrada.nextLine();
  
            // Elimina espacios redundantes y tabuladores
            String entradaUsuarioDep = entradaUsuario.replaceAll("\t"," ");
            entradaUsuarioDep = entradaUsuarioDep.replaceAll("\\s{2,}"," ");
            entradaUsuarioCaps = entradaUsuarioDep.toUpperCase();

            // Imprime el comando depurado -- sin espacios ni tabuladores
            /*System.out.println("\nEl comando digitado es: " 
                              + entradaUsuarioDep
                              + "\nPresione una tecla para continuar...");
            System.in.read();*/

            //bloque try para ingreso de datos
            /*try {
                 entradaUsuario = datoEntrada.nextLine();
                 String entradaUsuarioDep = entradaUsuario.replaceAll("\\s{2,}"," ");
            } 
            catch (Exception e) {
                
                System.out.print("\n Ocurrio un error en la entrada: " 
                                + e.getMessage()
                                + "\n Presione una tecla para continuar...");
                System.in.read();
            }    */        
            switch (entradaUsuarioCaps){  //evalúa la opción digitada por usuario
                case "VERS":{
                    VERS();
                    digitaLinea();
                    break;
                }                    
                case "LIMP":{                 
                    LIMP();
                    break;
                }
                case "TERM":{
                    TERM();
                    break;
                }
                case "LIST":{
                    listarVariables();
                    LIMP();
                    break;
                }
//                case "DEFI":
//                case "defi":{
//                    analizaEntrada(entradaUsuarioDep);
//                    LIMP();
//                    break;
//                }                
                default:
                {
                    int result=analizaEntrada(entradaUsuarioCaps);
                switch (result) {
                    case 0:
                        LIMP();
                        break;
                    case 1:
                        DEFVARINV();
                        break;
                    case 2:
                        DEFVAREXISTE();
                        break;
                    case 3:
                        DEFVARPALRES();
                        break;
                    case 4:
                        ASIGVARNOEXISTE();
                        break;
                    default:
                        NORECON();
                        break;
                }
                    break;                    
                }
            }
        }
        TERM();      
    }
    
    public static void digitaLinea(){
        // Implementa el menú de la Tarea1
        
        System.out.println("\nOpciones \n"
                         + "     VERS: Imprimir Version"
                         + "     LIMP: Limpiar Pantalla"
                         + "     DEFI: Definir Variable"
                         + "     LIST: Listar Variables"
                         + "     TERM: Terminar Programa"
                         + "\n\nDigite el comando deseado: ");
    }
    
    public static void VERS(){
        // Implementa el comando VERS
        System.out.println("\n\n*******************************************\n"
                         + "* Universidad Estatal a Distancia         *\n"
                         + "* Escuela de Ciencias Exactas y Naturales *\n"
                         + "* Cátedra Tecnología de Sistemas          *\n"
                         + "* Curso Compiladores - 3307               *\n"
                         + "*******************************************\n"
                         + "* Proyecto - CalcJPB v1.0                 *\n"
                         + "* Alumno: Jose Pablo Barrenechea A.       *\n"
                         + "*******************************************");
    }
    
    public static void LIMP(){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        digitaLinea();
}
    
    public static void NORECON(){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("\nComando no reconocido!");
        digitaLinea();
    }

    public static void DEFVARINV(){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("\nDefinición de variable inválida!");
        digitaLinea();
    }

    public static void DEFVARPALRES(){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("\nNo se puede definir variable con nombre de palabra reservada!");
        digitaLinea();
    }

     public static void ASIGVARNOEXISTE(){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("\nNo se puede asignar como valor una variable que no existe!");
        digitaLinea();
    }   
    
    
    public static void DEFVAREXISTE(){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("\nVariable ya existe!");
        digitaLinea();
    }    
    
    public static void TERM(){
        String decisionUsuario;
        Scanner datoDecision = new Scanner (System.in); 
        System.out.println("\nSeguro que desea finalizar? (S=Sí, N=No): ");
        decisionUsuario = datoDecision.nextLine();

        switch(decisionUsuario){
            case "S":
            case "s": System.exit(0);
                      break;
            case "N":
            case "n": LIMP();
                      break;
        }
    }   
}
