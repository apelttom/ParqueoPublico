/*
 * Instituto Tecnologico de Costa Rica
 * Escuela de Ingenieria en Computacion
 *
 * Curso: IC-2101 Programacion Orientada a Objetos
 * Profesor: Mauricio Aviles Cisneros
 * I Semestre 2014
 *
 * Tarea Programada Nº1
 *
 * ParkingLot.java
 * Creado a las 6:31:40 PM del 22/03/2014
 * Copyright (c) 2014, Adrian Rodriguez, Saul Zamora, Tomas Apeltauer
 * Todos los derechos reservados.
 */

package businessLogic;

import java.util.*;

/**
 *
 * @author Adrian Rodriguez Villalobos
 */
public class ParkingLot {
    String name;
    String slogan;
    String telephone;
    String companyID;
    boolean isOpen;
    Date openningTime;
    Date closingTime;
    float rate; /* ? */
    
    // Constructor
    public ParkingLot(String pName)
    {
        this.name = pName;
        this.slogan = "";
        this.telephone = "";
        this.companyID = "";
        this.isOpen = false;
        this.openningTime = null;
        this.closingTime = null;
        this.rate = 0;
    }
    
    // getters and setters
    String getName()
    {
        return this.name;
    }
    
    void setName(String nName)
    {
        this.name = nName;
    }
    
    String getSlogan()
    {
        return this.slogan;
    }
    
    void setSlogan(String nSlogan)
    {
        this.slogan = nSlogan;
    }
    
    String getTelephone()
    {
        return this.telephone;
    }
    
    void setTelephone(String nTelephone)
    {
        this.telephone = nTelephone; 
        /* for now I'm gonna leave it like this,
         * but I plan to check if the new phone number
         * is valid by using regular expressions. - Saul
         */
    }
    
    boolean isOpen()
    {
        return this.isOpen;
    }
    
    void open_close(boolean nState)
    {
        this.isOpen = nState;
    }
    
    Date getOpenningTime()
    {
        return this.openningTime;
    }
    
    void setOpenningTime(int nYear, int nMonth, int nDay, int nHour, int nMinute)
    {
        this.openningTime = new Date(nYear, nMonth, nDay, nHour, nMinute);
    }
    
    Date getClosingTime()
    {
        return this.closingTime;
    }
    
    void setClosingTime(int nYear, int nMonth, int nDay, int nHour, int nMinute)
    {
        this.closingTime = new Date(nYear, nMonth, nDay, nHour, nMinute);
    }
    
    float getRate()
    {
        return this.rate;
    }
    
    void setRate(float nRate)
    {
        this.rate = nRate;
    }
    
    @Override
    public String toString()
    {
        String data = "";
        
        data = data+name+"\n"+
                    slogan+"\n"+
                    "Phone #: "+telephone+"\n"+
                    "Company ID: "+companyID+"\n"+
                    "Rate: "+rate;
        
        return data;
    }
}
