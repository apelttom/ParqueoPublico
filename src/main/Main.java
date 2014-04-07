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
package main;

import controller.LoginController;
import java.util.Date;
import model.ParkingLot;
import model.XMLDataStorage;
import view.LoginFrame;

/**
 *
 * @author Tom Apeltauer <apelttom@live.com>, Carné: 2013389910
 * Created on 05/04/2014
 * Last modified on 05/04/2014
 */
public class Main {

    public static final String TEST_PL_NAME = "Testing Parking Lot!";
    public static final String TEST_PL_ADDRESS = "Testing Address!";
    public static final String TEST_PL_TELEPHONE = "Testing Telephone!";
    public static final String TEST_PL_SLOGAN = "Testing Slogan!";
    public static final String TEST_PL_COMP_ID = "Testing Company ID!";
    public static final int TEST_PL_HOURLY_RATE = -1;
    public static final int TEST_PL_LAST_RECEIPT = -1;
    public static final Date TEST_PL_OPENNING = null;
    public static final Date TEST_PL_CLOSING = null;

    public static void main(String[] args) {
        ParkingLot modelPL = new ParkingLot(TEST_PL_NAME, TEST_PL_ADDRESS,
                TEST_PL_TELEPHONE, TEST_PL_SLOGAN, TEST_PL_COMP_ID,
                TEST_PL_HOURLY_RATE, TEST_PL_LAST_RECEIPT, TEST_PL_OPENNING,
                TEST_PL_CLOSING);
        LoginFrame viewLogin = new LoginFrame();
        LoginController controllerLogin = new LoginController(modelPL, viewLogin);
        viewLogin.setVisible(true);
        
        ParkingLot parkingLotModel = new ParkingLot();   
        XMLDataStorage DS = new XMLDataStorage("src/data/ParkingLotInfo.xml");
        parkingLotModel = DS.loadParkingLotInfo();
        
        System.out.println(parkingLotModel.toString());
         

    }

}
