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
 * Bill.java
 * Creado a las 6:39:08 PM del 22/03/2014
 * Copyright (c) 2014, Adrian Rodriguez, Saul Zamora, Tomas Apeltauer
 * Todos los derechos reservados.
 */

package businessLogic;

import java.util.*;

/**
 *
 * @author Adrian Rodriguez Villalobos
 */
public class Bill {
    private Date date;
    private int billNumber;
    private float cost;
    
    // Constructor
    public Bill(Date pDate)
    {
        this.date = pDate;
        this.billNumber = 0;
        this.cost = 0;
    }
    
    // getters and setters
    Date getDate()
    {
        return this.date;
    }
    
    void setDate(int nYear, int nMonth, int nDay, int nHour, int nMinute)
    {
        this.date = new Date(nYear, nMonth, nDay, nHour, nMinute);
    }
    
    int getBillNumber()
    {
        return this.billNumber;
    }
    
    void setNillNumber(int nBillNumber)
    {
        this.billNumber = nBillNumber;
    }
    
    float getCost()
    {
        return this.cost;
    }
    
    void setCost(float nCost)
    {
        this.cost = nCost;
    }
    
    @Override
    public String toString()
    {
        String data = "";
        return data+"Date: "+date.toString()+"\n"+
                    "Bill #: "+billNumber+"\n"+
                    "Cost: "+cost;
    }
}
