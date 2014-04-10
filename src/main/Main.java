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
 * Main.java
 * Copyright (c) 2014, Adrian Rodriguez, Saul Zamora, Tomas Apeltauer
 * Todos los derechos reservados.
 */
package main;

import controller.LoginController;
import javax.xml.transform.TransformerException;
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

    public static void main(String[] args) throws TransformerException {
        XMLDataStorage DBConnect = XMLDataStorage.getInstance();
//      =======LOADING PARKING LOT=======
        ParkingLot parkingLotModel = DBConnect.loadParkingLotInfo();
        parkingLotModel.setCashDesk(DBConnect.loadCashDeskInfo());
        parkingLotModel.setReceiptHistory(DBConnect.loadReceiptHistory());
        parkingLotModel.setParkingSpots(DBConnect.loadParkingSpots());
        parkingLotModel.setRegisteredUsers(DBConnect.loadRegisteredUsers());
//      =================================
        parkingLotModel.addParkingSpot();
        parkingLotModel.addParkingSpot();
        parkingLotModel.setName("Parqueo Morazán");
        DBConnect.saveParkingLotInfo(parkingLotModel);
/*
        LoginFrame loginView = new LoginFrame();
        LoginController controllerLogin = new LoginController(parkingLotModel, loginView);
        controllerLogin.showLogin();
        System.out.println(parkingLotModel.toString());*/
                
    }

}
