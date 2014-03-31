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
public class User {

    private String userName;
    private String password;

    //TODO change behaviour
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    private void encryptPassword(){
        //TODO encryption of password
    }
}
