/*
 * Instituto Tecnologico de Costa Rica
 * Escuela de Ingenieria en Computacion
 *
 * Curso: IC-2101 Programacion Orientada a Objetos
 * Profesor: Mauricio Aviles Cisneros
 * I Semestre 2014
 *
 * Tarea Programada Nº1
 *
 * ParkingLot.java
 * Creado a las 6:31:40 PM del 22/03/2014
 * Copyright (c) 2014, Adrian Rodriguez, Saul Zamora, Tomas Apeltauer
 * Todos los derechos reservados.
 */
package model;

import java.util.*;

/**
 *
 * @author Adrian Rodriguez Villalobos
 * @author Tomáš Apeltauer
 */
public class ParkingLot {

    private String name;
    private String address;
    private String telephone;
    private String slogan;
    private String companyID;
    private float hourlyRate;
    private boolean open;
    //ID of the last receipt, so we can continue with the other one
    private int lastReceiptNumber;
    //number of available parking spots
    private int parkSpotNumber;
    private Date openningTime;
    private Date closingTime;
    private CashDesk cashDesk;
    private List<Receipt> receiptHistory;
    private List<ParkingLot> parkingSpots;
    private List<User> registeredUsers;

    // Constructor #1
    public ParkingLot() {
        this.name = "";
        this.address = "";
        this.telephone = "";
        this.slogan = "";
        this.companyID = "";
        this.hourlyRate = 0;
        this.open = false;
        this.lastReceiptNumber = 0;
        this.openningTime = null;
        this.closingTime = null;
        this.cashDesk = new CashDesk();
        this.receiptHistory = new ArrayList<Receipt>();
        this.parkingSpots = new ArrayList<ParkingLot>();
        this.registeredUsers = new ArrayList<User>();
    }

    // Constructor #2
    public ParkingLot(String pName, String pAddress, String pTelephone,
            String pSlogan, String pCompanyID, int pHourlyRate,
            int pLastReceiptNumber, Date pOpenningTime, Date pClosingTime) {
        this.name = pName;
        this.address = pAddress;
        this.telephone = pTelephone;
        this.slogan = pSlogan;
        this.companyID = pCompanyID;
        this.hourlyRate = pHourlyRate;
        this.open = false;
        this.lastReceiptNumber = pLastReceiptNumber;
        this.openningTime = pOpenningTime;
        this.closingTime = pClosingTime;
        this.cashDesk = new CashDesk();
        this.receiptHistory = new ArrayList<Receipt>();
        this.parkingSpots = new ArrayList<ParkingLot>();
        this.registeredUsers = new ArrayList<User>();
    }

    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    String getTelephone() {
        return this.telephone;
    }

    void setTelephone(String nTelephone) {
        this.telephone = nTelephone;
        /* for now I'm gonna leave it like this,
         * but I plan to check if the new phone number
         * is valid by using regular expressions. - Saul
         */
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public float getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(float hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public int getLastReceiptNumber() {
        return lastReceiptNumber;
    }

    public void setLastReceiptNumber(int lastReceiptNumber) {
        this.lastReceiptNumber = lastReceiptNumber;
    }

    public int getParkSpotNumber() {
        return parkSpotNumber;
    }

    public void setParkSpotNumber(int parkSpotNumber) {
        this.parkSpotNumber = parkSpotNumber;
    }

    Date getOpenningTime() {
        return this.openningTime;
    }

    void setOpenningTime(int nYear, int nMonth, int nDay, int nHour, int nMinute) {
        this.openningTime = new Date(nYear, nMonth, nDay, nHour, nMinute);
    }

    Date getClosingTime() {
        return this.closingTime;
    }

    void setClosingTime(int nYear, int nMonth, int nDay, int nHour, int nMinute) {
        this.closingTime = new Date(nYear, nMonth, nDay, nHour, nMinute);
    }
//</editor-fold>

    private void openParkingLot() {
        //TODO implement funcionality of openning the ParkingLot
        //Also with verification
    }

    private void closeParkingLot() {
        //TODO implement funcionality of closing the ParkingLot
        //Also with verification
    }

    private void updateSettings() {
        //TODO implement method for saving settings (calling XMLdataStorage)
    }

    private void verificateAdmin() {
        //TODO implement method for verification of admin account and password
    }

    @Override
    public String toString() {
        String data = "";

        data = data + name + "\n"
                + slogan + "\n"
                + "Phone #: " + telephone + "\n"
                + "Company ID: " + companyID + "\n"
                + "Rate: " + hourlyRate;

        return data;
    }
}
