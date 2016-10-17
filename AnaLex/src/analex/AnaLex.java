/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analex;


/**
 *
 * @author jbarrenechea
 */
public class AnaLex {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        String s1= "Hola como estas";

        String[]  array;
        array = s1.split("");

        for (String array1 : array) {
            System.out.println(array1);
        }
        System.out.println(array[0]);
        System.out.println(array.length);
        //   System.out.println(array[1]);
        
    }
    
}
