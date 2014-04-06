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
 * @author Adrian Rodriguez Villalobos
 * @author Tomáš Apeltauer
 */
public class ParkingSpot {
    private int spotNumber;
    private String description;
    private boolean occupied;
    private Car parkedCar;
    
    // Constructor
    public ParkingSpot(int pSpotNumber, String description)
    {
        this.spotNumber = pSpotNumber;
        this.description = description;
        this.occupied = false;
        this.parkedCar = null;
    }
    
    // getters and setters
    int getSpotNumber()
    {
        return this.spotNumber;
    }
    
    void setSpotNumber(int nSpotNumber)
    {
        this.spotNumber = nSpotNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public Car getParkedCar() {
        return parkedCar;
    }

    public void setParkedCar(Car parkedCar) {
        this.parkedCar = parkedCar;
    }
    
    @Override
    public String toString()
    {
        String data = "";
        
        data = data+"Spot #: "+spotNumber+"\n"+
                    "Type: "+description+"\n";
        if(occupied)
        {
            data = data+"Availability: Taken"+"\n";
            data += parkedCar.toString();
        }else{
            data = data+"Availability: Free"+"\n";
        }
        
        return data;
    }
}
