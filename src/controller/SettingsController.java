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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import model.ParkingLot;
import model.XMLDataStorage;
import view.SettingsFrame;

/**
 *
 * @author Tom Apeltauer <apelttom@live.com>, Carné: 2013389910
 * Created on 07/04/2014
 * Last modified on 07/04/2014
 */
public class SettingsController {

    private static final String WRONG_NUMBER_FORMAT
            = "Hourly fee and Minimal cash fields have to contain numbers.";
    private static final String WRONG_DAYTIME_FORMAT
            = "Please insert openning and closing hours in 24 hour format:\n"
            + "Hours:Minutes:Seconds";

    private ParkingLot model;
    private SettingsFrame view;

    SettingsController(SettingsFrame view, ParkingLot model) {
        this.model = model;
        this.view = view;
        loadCurrSettings();
        view.addSaveSettingsListener(new saveSettingsListener());
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

    private void saveNewSettings() {
        model.setName(view.getNameField().getText());
        model.setAddress(view.getAddressTextArea().getText());
        model.setTelephone(view.getTelephoneField().getText());
        model.setSlogan(view.getSloganTextArea().getText());
        model.setCompanyID(view.getCompanyIDField().getText());
        model.setHourlyRate(Float.parseFloat(view.getHourlyRateField().getText()));
        model.setMinCash(Float.parseFloat(view.getMinCashField().getText()));
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            model.setOpenningTime(formatter.parse(view.getOpenFromField().getText()));
            model.setClosingTime(formatter.parse(view.getCloseAtField().getText()));
        } catch (ParseException ex) {
        }
        XMLDataStorage.getInstance().saveParkingLotInfo(model);
    }

    class saveSettingsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (isNumeric(view.getHourlyRateField().getText())
                    && isNumeric(view.getMinCashField().getText())) {
                if (isDayTime(view.getOpenFromField().getText())
                        && isDayTime(view.getCloseAtField().getText())) {
                    saveNewSettings();
                } else {
                    view.displayMessage(WRONG_DAYTIME_FORMAT);
                }
            } else {
                view.displayMessage(WRONG_NUMBER_FORMAT);
            }
        }
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
        } catch (NumberFormatException foo) {
            return false;
        }
        return true;
    }

    public static boolean isDayTime(String str) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            formatter.parse(str);
        } catch (ParseException foo) {
            return false;
        }
        return true;
    }

}
