/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea2;

import java.io.IOException;
import java.util.*;

/**
 *
 * @author jbarrenechea
 */
public class Tarea2 {
    
    //define estructura para tabla de símbolos del compilador
    static LinkedList <Variable> listaVariables = new LinkedList(); 

    //define estructura para lista de variables definidas por el usuario
    static LinkedList <Simbolo> tablaSimbolos = new LinkedList();

    //define estructura para almacenar lista de palabras reservadas
    static String[] palabrasReservadas = {"VERS","LIMP","TERM","DEFI","DEFIN","BORR",
                                          "CALC","VALI","GRAB","LIST"};
    
    //define expresiones regulares para análisis de caracteres
    static String esLetra = "[a-zA-z]";  //letras
    static String esNumero = "[0-9]";    //números
    static String esOperador ="[<>()=*+-/#^?]";   //símbolos y operadores
    static boolean existeVariable = false;  //bandera de control para validación de existencia de variable      
    /* El siguiente método convierte y analiza caracter por caracter el texto
       de entrada del usuario*/
    
    public static int analizaEntrada(String strEntrada) throws IOException{

        //convierte string de entrada en array
              
        if (strEntrada.contains("DEFI")||strEntrada.contains("DEFIN")) {            
            if (strEntrada.contains("DEFIN")){
                strEntrada = strEntrada.replace("DEFIN", "");
                if (strEntrada.length()<1){    // valida que el string no haya quedado vacío
                    return 1;
                }
                else if (strEntrada.startsWith(" ")||strEntrada.endsWith(" ")){  // evalúa si después del replace el primer caracter es espacio
                    strEntrada = strEntrada.trim();   // elimina primer caracter del string ya que contiene un espacio
                }
            }
            if (strEntrada.contains("DEFI")){
                strEntrada = strEntrada.replace("DEFI", "");
                if (strEntrada.length()<1){    // valida que el string no haya quedado vacío
                    return 1;
                }
                else if (strEntrada.startsWith(" ")||strEntrada.endsWith(" ")){
                    strEntrada = strEntrada.trim();   // elimina primer caracter del string ya que contiene un espacio
                }                
            }            
            if (strEntrada.contains("=")){          
                String [] arrayEntrada = strEntrada.split("");
                return procesaDefi(arrayEntrada);   // retorna resultado procesaDefi
            }
            else {
                return 5;   // retorna 5, implica comando no reconocido            
            }
        }
        if (strEntrada.contains("=")){
            String [] arrayEntrada = strEntrada.split("");
            return (procesaDefi(arrayEntrada)); // retorna resultado procesaDefi
        }
        else {
            return 5;   // retorna 5, implica comando no reconocido            
        }
    }

    /*  analizaCaracter evalúa el parámetro "c" y determina si es número, letra, 
        operador o símbolo 
        Utilizado solamente para pruebas, no es requerimiento de tarea 2*/
    public static String analizaCaracter(String c){
        if (c.matches(esLetra)){
        //    System.out.println(" --> Es letra");            
            //lexemaAux = lexemaAux.concat(c);
            return "letra";
        }      
        else if (c.matches(esNumero)){
            return "numero";
        //    System.out.println(" --> Es numero");                
        }
        else if (c.matches(esOperador)){
            return "operador";
        //    System.out.println(" --> Es operador");                
        }
        else {
            return "desconocido";
        //    System.out.println(" --> No reconocido");                
        }    
    }    

    /*  procesaDefi es el método utilizado para implementar el comando DEFI 
        - Realiza lectura del array para armar lexema nombre de variable
        hasta encontrar el caracter de separación espacio " ", para luego
        leer el valor asignado de la variable */
    public static int procesaDefi(String [] arrayEnt) throws IOException{

        String lexemaAux=""; //string auxiliar para almacenamiento de lexemas encontrados    
        Variable nuevaVar = new Variable();
        boolean finLexema = false; // bandera para controlar lectura de lexema de variable
        boolean leeValor  = false;  // bandera para controlar lectura de valor de variable
        boolean valorEsVariable= false; // bandera para controlar si el valor asignado corresponde a  una variable
        existeVariable=false;  //inicializa bandera control de existencia de Variable en false        
        int j=0; //puntero siguiente para recorrer arreglo buscando fin de lexemas        
        

        // valida si primer caracter de la variable corresponde a letra
        if (!"letra".equals(analizaCaracter(arrayEnt[0]))){
            return 1;
        }
        else {
            for (int i = 0; i < arrayEnt.length; i++) {
                j++;
                if (!finLexema && !"=".equals(arrayEnt[j])){      // si aún no termina de leer lexema actual o el siguiente
                    lexemaAux = lexemaAux.concat(arrayEnt[i]);    // caracter de lectura es diferente a "=", debe seguir armando lexema
                }
                else if (!finLexema){       // fin de lexema, nombre de variable, escribe último caracter del nombre y guarda
                    finLexema=true;         // en la estructura de variables
                    lexemaAux = lexemaAux.concat(arrayEnt[i]);
                    if (lexemaAux.length()>5){
                        return 1;   
                    }
                    if (existeLexema(lexemaAux)){
                        return 2;
                    }
                    if (existePalReservada(lexemaAux)){
                        return 3;
                    }
                    else {
                        nuevaVar.setNombreVariable(lexemaAux);   // guarda nombre de variable en nuevo objeto variable
                        listaVariables.add(nuevaVar);           // guarda nombre de variable en estructura de variables
                        lexemaAux="";
                        j++;
                    }
                }
                if (finLexema){
        // valida si primer caracter del valor de la variable corresponde a letra para evaluar existencia de variable
                    if (!leeValor){
                        if (arrayEnt.length==j){
                            listaVariables.removeLast();
                            return 1;
                        }                            
                        switch(analizaCaracter(arrayEnt[j])){
                            case "numero": 
                                lexemaAux = lexemaAux.concat(arrayEnt[j]);
                                leeValor=true;  // ajusta bandera para identificar que ya inició lectura de valor
                                i=j;    // ajusta contador i para poder finalizar lectura del array
                                break;
                            
                            case "letra":
                                lexemaAux = lexemaAux.concat(arrayEnt[j]);
                                leeValor=true;  // ajusta bandera para identificar que ya inició lectura de valor
                                valorEsVariable=true;
                                i=j;    // ajusta contador i para poder finalizar lectura del array
                                break;
                                
                            default: {
                                listaVariables.removeLast();
                                return 1;                                
                            }       
                        }                                                  
                    }  // siguen mismas validaciones de valores correctos
                    else if ("numero".equals(analizaCaracter(arrayEnt[j]))||
                            "letra".equals(analizaCaracter(arrayEnt[j]))||
                            ".".equals(arrayEnt[j])){
                            lexemaAux = lexemaAux.concat(arrayEnt[j]);
                            leeValor=true;  // ajusta bandera para identificar que ya inició lectura de valor
                            i=j;    // ajusta contador i para poder finalizar lectura del array
                        }
                        else {
                            listaVariables.removeLast();
                            return 1;
                    }
                }
            }
        }
        // valida si la variable que se asigna a la nueva variable existe, de ser así obtiene el tipo de variable
        if (valorEsVariable){
            if(existeLexema(lexemaAux)&&!lexemaAux.equals(nuevaVar.nomVariable)){
                nuevaVar.setValorVariable(lexemaAux);
                nuevaVar.setTipoVariable(getTipoLexema(lexemaAux));
                return 0;
            }
            else{
                listaVariables.removeLast();
                return 4;
            }          
        }
        else {
            nuevaVar.setValorVariable(lexemaAux);
            nuevaVar.setTipoVariable(defineTipoLexema(lexemaAux));
            return 0;            
        }
    }
     
    // Definición de método para imprimir listado de variables
    public static void listarVariables() throws IOException{
        if (listaVariables.isEmpty()){   // evalúa si la lista de variables está vacía
            System.out.println("\nNo hay variables definidas por el usuario!");
            System.out.println("Presione una tecla para continuar...");
            System.in.read();
        }
        else {
            System.out.println("\nVariable  Tipo    Valor");
            System.out.print("------------------------------");
            Collections.sort(listaVariables);  // realiza el ordenamiento alfabético
            listaVariables.stream().map((listaVariable) -> {
                return listaVariable;
            }).forEach((listaVariable) -> {
                String v=listaVariable.getNombreVariable();
                String t=listaVariable.getTipoVariable();
                String vv=listaVariable.getValorVariable();
                System.out.printf("\n%-10s%-8s%-5s",v,t,vv);
            });
            System.out.println("\n------------------------------");
            System.out.print("\nFin de la lista, presione una tecla para continuar... ");
            System.in.read();  
        }     
    }
    // método para validación de existencia de variable
    public static boolean existeLexema (String e){
        listaVariables.stream().map((listaVariable) -> {
                return listaVariable;
            }).forEach((listaVariable) -> {
                if (e.equals(listaVariable.nomVariable))
                    existeVariable=true;
            });
        return existeVariable==true;
    }
    // método para obtener el tipo de variable del lexema
    public static String getTipoLexema (String e){
        for (Variable listaVariable : listaVariables) {
            if (listaVariable.nomVariable.equals(e)){
                return listaVariable.getTipoVariable();
            }
        }
        return "";
    }    
    // método valida si el parámetro es una palabra reservada
    public static boolean existePalReservada (String e){
        for(String palabraRes : palabrasReservadas){
            if (palabraRes.equals(e))
                return true;
        }
        return false;
    }
    //método evalúa el tipo de variable
    public static String defineTipoLexema (String e){
        if (e.contains(".")){
            return "float";
            
        }
        return "int";
    }
}


