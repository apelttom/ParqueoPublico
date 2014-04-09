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
 * Receipt.java
 * Creado a las 6:39:08 PM del 22/03/2014
 * Copyright (c) 2014, Adrian Rodriguez, Saul Zamora, Tomas Apeltauer
 * Todos los derechos reservados.
 */

package model;

import java.util.*;

/**
 *
 * @author Adrian Rodriguez Villalobos
 * @author Tomáš Apeltauer
 */
public class Receipt {
    private Date date;
    private int receiptID;
    private float cost;
    private Car chargedCar;
    
    // Constructor
    public Receipt()
    {
        this.date = new Date();
        this.receiptID = 0;
        this.cost = 0;
    }
    
    public Receipt(Date pDate)
    {
        this.date = pDate;
        this.receiptID = 0;
        this.cost = 0;
    }
    
    // getters and setters
    Date getDate()
    {
        return this.date;
    }
    
    void setDate(Date pDate)
    {
        this.date = pDate;
    }
    
    int getReceiptID()
    {
        return this.receiptID;
    }
    
    void setReceiptID(int receiptID)
    {
        this.receiptID = receiptID;
    }
    
    float getCost()
    {
        return this.cost;
    }
    
    void setCost(float nCost)
    {
        this.cost = nCost;
    }

    public Car getChargedCar() {
        return chargedCar;
    }

    public void setChargedCar(Car chargedCar) {
        this.chargedCar = chargedCar;
    }
    
    @Override
    public String toString()
    {
        String data = "";
        return data+"Date: "+date.toString()+"\n"+
                    "Bill #: "+receiptID+"\n"+
                    chargedCar.toString()+"\n"+
                    "Cost: "+cost;
    }
}
