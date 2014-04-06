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
 * User.java
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

    private String username;
    //pasword has to be hashed in all times!
    private String password;

    /**
     * Creates new user according to the parameters.
     *
     * @param username
     * @param password NEVER use plain text! Always pass hashed password. For
     * this use class PasswordHash
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        String msg = "";
        msg += "Username: " + username + "\n"
                + "Hashed password: " + password;
        return msg;
    }
}
