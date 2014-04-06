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
 * Automobile.java
 * Creado a las 6:39:41 PM del 22/03/2014
 * Copyright (c) 2014, Adrian Rodriguez, Saul Zamora, Tomas Apeltauer
 * Todos los derechos reservados.
 */

package model;

import java.util.Date;

/**
 *
 * @author Adrian Rodriguez Villalobos
 * @author Tomáš Apeltauer
 */
public class Car {
    private String licensePlate;
    private String color;
    private String brand;
    private String model;
    private Date entryTime;
    private Date exitTime;
    
    // Constructor
    public Car(String pPlate, String pColor, String pBrand, String pModel)
    {
        this.licensePlate = pPlate;
        this.color = pColor;
        this.model = pModel;
        this.entryTime = null;
        this.exitTime = null;
    }
    
    // getters and setters
    String getLicensePlate()
    {
        return this.licensePlate;
    }
    
    void setLicensePlate(String nLicense)
    {
        this.licensePlate = nLicense;
    }
    
    String getColor()
    {
        return this.color;
    }
    
    void setColor(String nColor)
    {
        this.color = nColor;
    }
    
    String getBrand()
    {
        return this.brand;
    }
    
    void setBrand(String nBrand)
    {
        this.brand = nBrand;
    }
    
    String getModel()
    {
        return this.model;
    }
    
    void setModel(String nModel)
    {
        this.model = nModel;
    }
    
    Date getEntryTime()
    {
        return this.entryTime;
    }
    
    void setEntryTime(Date pDate)
    {
        this.entryTime = pDate;
    }
    
    Date getExitTime()
    {
        return this.exitTime;
    }
    
    void setExitTime(Date pDate)
    {
        this.exitTime = pDate;
    }
    
    @Override
    public String toString()
    {
        String data = "";
        return data+"Brand: "+brand+"\n"+
                    "Model: "+model+"\n"+
                    "Color: "+color+"\n"+
                    "License Plate: "+licensePlate+"\n"+
                    "Entry Date: "+entryTime+"\n"+
                    "Exit Time: "+exitTime;
    }
}
