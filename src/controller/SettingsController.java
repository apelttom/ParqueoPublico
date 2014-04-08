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
 * SettingsController.java
 * Copyright (c) 2014, Adrian Rodriguez, Saul Zamora, Tomas Apeltauer
 * Todos los derechos reservados.
 */
package controller;

import model.ParkingLot;
import view.SettingsFrame;

/**
 *
 * @author Tom Apeltauer <apelttom@live.com>, Carné: 2013389910
 * Created on 07/04/2014
 * Last modified on 07/04/2014
 */
public class SettingsController {

    private ParkingLot model;
    private SettingsFrame view;

    SettingsController(SettingsFrame view, ParkingLot model) {
        this.model = model;
        this.view = view;
        loadCurrSettings();
    }

    void showSettings() {
        view.setVisible(true);
    }

    void hideSettings() {
        view.setVisible(false);
    }

    private void loadCurrSettings() {
        view.setParkingLotName(model.getName());
        view.setAddress(model.getAddress());
        view.setTelephone(model.getTelephone());
        view.setSlogan(model.getSlogan());
        view.setCompanyID(model.getCompanyID());
        view.setHourlyRate(model.getHourlyRate());
        view.setOpenFrom(model.getOpenningTime());
        view.setCloseAt(model.getClosingTime());
        view.setMinCash(model.getMinCash());
    }
}
