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
 * ParkingLotController.java
 * Copyright (c) 2014, Adrian Rodriguez, Saul Zamora, Tomas Apeltauer
 * Todos los derechos reservados.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.ParkingLot;
import view.ParkingLotFrame;
import view.SettingsFrame;

/**
 *
 * @author Tom Apeltauer <apelttom@live.com>, Carné: 2013389910
 * Created on 06/04/2014
 * Last modified on 06/04/2014
 */
public class ParkingLotController {

    private static final String PARKING_LOT_NOT_OPEN_ERROR
            = "Parking Lot has to be opened first!";
    private static final String NOT_ADMIN_ERROR
            = "You need administrador rights for this operation!";
    private static final String PARKING_LOT_OPEN_ERROR
            = "You cannot change settings if parking lot is open!";

    private ParkingLot model;
    private ParkingLotFrame view;
    private SettingsController settingsController = null;

    public ParkingLotController(ParkingLot model, ParkingLotFrame view) {
        this.model = model;
        this.view = view;
        this.view.addStartPLotListener(new parkingLotStarListener());
        this.view.addStopPLotListener(new parkingLotStopListener());
        this.view.addCarEntryListener(new carEntryListener());
        this.view.addCarExitListener(new carExitListener());
        this.view.addSettingsListener(new settingsListener());
    }

    void showParkingLot() {
        view.setVisible(true);
    }

    void hideParkingLot() {
        view.setVisible(false);
    }

    class parkingLotStarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                model.openParkingLot();
            } catch (IllegalStateException e) {
                view.displayMessage(e.getMessage());
            }
        }
    }

    class parkingLotStopListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            model.closeParkingLot();
        }
    }

    class carEntryListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (model.isOpen()) {
                //TODO entry car
            } else {
                view.displayMessage(PARKING_LOT_NOT_OPEN_ERROR);
            }
        }
    }

    class carExitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (model.isOpen()) {
                //TODO exit car   
            } else {
                view.displayMessage(PARKING_LOT_NOT_OPEN_ERROR);
            }
        }
    }

    class settingsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (model.getLoggedUser().isAdministrador()) {
                if (!model.isOpen()) {
                    if (settingsController == null) {
                        SettingsFrame settingsView = new SettingsFrame();
                        settingsController
                                = new SettingsController(settingsView, model);
                    }
                    settingsController.showSettings();
                } else {
                    view.displayMessage(PARKING_LOT_OPEN_ERROR);
                }
            } else {
                view.displayMessage(NOT_ADMIN_ERROR);
            }
        }
    }
}
