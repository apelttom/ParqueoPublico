/*
 * Instituto Tecnologico de Costa Rica
 * Escuela de Ingenieria en Computacion
 *
 * Curso: IC-2101 Programacion Orientada a Objetos
 * Profesor: Mauricio Aviles Cisneros
 * I Semestre 2014
 *
 * Tarea Programada N°1
 *
 * ParkingSpot.java
 * Creado a las 6:39:53 PM del 22/03/2014
 * Copyright (c) 2014, Adrian Rodriguez, Saul Zamora, Tomas Apeltauer
 * Todos los derechos reservados.
 */

package model;

/**
 *
 * @author Tom Apeltauer <apelttom@live.com>, Carné: 2013389910
 * Created on 31/03/2014
 * Last modified on 31/03/2014
 */
public class CashDesk {

    private float actualCash;
    private float minCash;
    
    public CashDesk(){
        this.actualCash = 0;
        this.minCash = 0;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public void setActualCash(float pActualCash){
        actualCash = pActualCash;
    }
    public void setMinCash(float pMinCash){
        minCash = pMinCash;
    }
    public float getActualCash(){
        return actualCash;
    }
    public float getMinCash(){
        return minCash;
    }
    //</editor-fold>
    
    public void depositMoney(){
        //TODO implement depositing certain amount of money
    }
    
    public void withdrawMoney(){
        //TODO implement withdrawing certain amount of money
    }
    
    public boolean isSufficient(){
        throw new UnsupportedOperationException("Operation not yet implemented");
        //TODO implement test if there is sufficient certain amount of money
    }
    
    public String toString(){
        String data = new String();
        data += "Minimum Cash on Desk: " + minCash + "\n"+
                "Actual Cash on Desk: " + actualCash + "\n";
        return data;
    }
}
