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
import model.User;
import view.LoginFrame;
import view.ParkingLotFrame;

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

    private final ParkingLot model;
    private final LoginFrame view;

    public LoginController(ParkingLot modelPL, LoginFrame viewLogin) {
        this.model = modelPL;

        //FOR TESTING PURPOUSES ADDING DEFAULT USER IN CONSTRUCTOR
        try {
            this.model.registerUser(new User(TEST_USERNAME,
                    PasswordHash.getSaltedHash(TEST_PASSWORD)));
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        //TODO delete after XMLparser will be able to load and save users

        this.view = viewLogin;
        this.view.addLoginListener(new LoginListener());
    }

    public void showLogin() {
        view.setVisible(true);
    }

    public void hideLogin() {
        view.setVisible(false);
    }

    class LoginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = view.getUsername();
            String password = view.getPassword();

            try {
                //checks if username and password provided by user are valid
                if (model.verifyUser(username, password)) {
                    //valid user, now start ParkingLot
                    model.setLoggedUser(model.getRegisteredUser(username));
                    ParkingLotFrame parkingLotView = new ParkingLotFrame();
                    ParkingLotController parkingLotController =
                            new ParkingLotController(model, parkingLotView);
                    view.dispose();
                    parkingLotController.showParkingLot();
                } else {
                    //invalid user
                    view.displayMessage(WRONG_CREDENTIALS);
                }
            } catch (Exception ex) {
                //something went wrong during the verification process
                view.displayMessage(ex.getMessage());
            }
        }

    }
}
