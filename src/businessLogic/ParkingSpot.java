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

package businessLogic;

/**
 *
 * @author Adrian Rodriguez Villalobos
 */
public class ParkingSpot {
    int spotNumber;
    String type;
    boolean isTaken;
    
    // Constructor
    public ParkingSpot(int pSpotNumber, String pType)
    {
        this.spotNumber = pSpotNumber;
        this.type = pType;
        this.isTaken = false;
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
    
    String getType()
    {
        return this.type;
    }
    
    void setType(String nType)
    {
        this.type = nType;
    }
    
    boolean isTaken()
    {
        return this.isTaken;
    }
    
    void setAvailability(boolean nAvailability)
    {
        this.isTaken = nAvailability;
    }
    
    @Override
    public String toString()
    {
        String data = "";
        
        data = data+"Spot #: "+spotNumber+"\n"+
                    "Type: "+type+"\n";
        if(isTaken)
        {
            data = data+"Availability: Taken"+"\n";
        }else{
            data = data+"Availability: Free"+"\n";
        }
        
        return data;
    }
}
