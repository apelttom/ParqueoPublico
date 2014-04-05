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
 * Copyright (c) 2014, Adrian Rodriguez, Saul Zamora, Tomas Apeltauer
 * Todos los derechos reservados.
 */

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.ParkingLot;
import view.LoginFrame;

/**
 *
 * @author Adrian Rodriguez Villalobos <adrian.rov@gmail.com>
 * @author Tom Apeltauer <apelttom@live.com>, Carné: 2013389910
 */
public class LoginController {
    
    public static final String TEST_USERNAME = "admin";
    public static final String TEST_PASSWORD = "admin";
    public static final String WRONG_CREDENTIALS = "User doesn't exist!\n"
            + "Default username: admin\n"
            + "Default password: admin";
    
    private final ParkingLot modelPL;
    private final LoginFrame viewLogin;

    public LoginController(ParkingLot modelPL, LoginFrame viewLogin) {
        this.modelPL = modelPL;
        this.viewLogin = viewLogin;
        this.viewLogin.addLoginListener(new LoginListener());
    }
    
    class LoginListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = viewLogin.getUsername();
            String password = viewLogin.getPassword();
            if(!(username.equals(TEST_USERNAME)) || !(password.equals(TEST_PASSWORD))){
                viewLogin.displayMessage(WRONG_CREDENTIALS);
            }else{
                viewLogin.displayMessage("Successfully logged in!\n"
                        + "We still have not implemented the functionality of "
                        + "showing the Parking Lot Window.\n\n"
                        + "Comming soon...");
            }
        }
        
    }
}
