/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea2;

/**
 *
 * @author jbarrenechea
 */
public class Variable implements Comparable<Variable> {
    String nomVariable;
    String tipoVariable;
    String valorVariable;
    
    public void setNombreVariable(String nomVar){
        nomVariable=nomVar;
    };
    
    public String getNombreVariable(){
        return nomVariable;
    };  
    
    public void setTipoVariable(String tipoVar){
        tipoVariable=tipoVar;
    };
    
    public String getTipoVariable(){
        return tipoVariable;
    };
    
    public void setValorVariable(String valVar){
        valorVariable=valVar;
    };
    
    public String getValorVariable(){
        return valorVariable;
    };    

    @Override
    public int compareTo(Variable o) {
        return nomVariable.compareTo(o.getNombreVariable());
    }
    
}
