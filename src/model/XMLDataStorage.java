/*
 * Instituto Tecnologico de Costa Rica
 * Escuela de Ingenieria en Computacion
 *
 * Curso: IC-2101 Programacion Orientada a Objetos
 * Profesor: Mauricio Aviles Cisneros
 * I Semestre 2014
 *
 * Laboratorio N°2
 *
 * XMLDataStorage.java
 * Creado a las 16:06:51 del 09/03/2014
 * Copyright (c) 2014, Adrian Rodriguez Villalobos <adrian.rov@gmail.com>
 * Todos los derechos reservados.
 */

package model;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.*;
import java.text.SimpleDateFormat;

/**
 *
 * @author Adrian Rodriguez Villalobos
 */
public class XMLDataStorage {
    private String fileName;
    private File fXmlFile;
    private DocumentBuilderFactory dbFactory;
    private DocumentBuilder dBuilder;
    private Document doc;
    
    private ParkingLot PL;
    private CashDesk CD;
    private List<Receipt> receiptHistory;
    private List<ParkingSpot> parkingSpots;
    private List<User> registeredUsers;
    
    public XMLDataStorage(String pFileName){
        try{
        fileName = pFileName;
        PL = new ParkingLot();
        fXmlFile = new File(fileName);
        dbFactory = DocumentBuilderFactory.newInstance();
        dBuilder = dbFactory.newDocumentBuilder();
        doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //<editor-fold defaultstate="collapsed" desc="Load Methods">
    public void loadParkingLotInfo(){
        try{
            Element eElement = doc.getDocumentElement();

            System.out.println("Root element :" + eElement.getNodeName());
            
            PL.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
            PL.setAddress(eElement.getElementsByTagName("address").item(0).getTextContent());
            PL.setTelephone(eElement.getElementsByTagName("telephone").item(0).getTextContent());
            PL.setSlogan(eElement.getElementsByTagName("slogan").item(0).getTextContent());
            PL.setCompanyID(eElement.getElementsByTagName("companyID").item(0).getTextContent());
            PL.setHourlyRate(Float.parseFloat(eElement.getElementsByTagName("hourlyRate").item(0).getTextContent()));
            PL.setParkSpotNumber(Integer.parseInt(eElement.getElementsByTagName("parkSpotNumber").item(0).getTextContent()));
            PL.setLastReceiptNumber(Integer.parseInt(eElement.getElementsByTagName("lastReceiptNumber").item(0).getTextContent()));
            
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            String oTimeString = eElement.getElementsByTagName("openningTime").item(0).getTextContent();
            String cTimeString = eElement.getElementsByTagName("closingTime").item(0).getTextContent();
            try {
                PL.setOpenningTime(formatter.parse(oTimeString));
                PL.setClosingTime(formatter.parse(cTimeString));
            } catch (Exception exc) {
                exc.printStackTrace();
            }

            System.out.println("Name :" + PL.getName());
            System.out.println("Address :" + PL.getAddress());
            System.out.println("Telephone :" + PL.getTelephone());
            System.out.println("Slogan :" + PL.getSlogan());
            System.out.println("CompanyID :" + PL.getCompanyID());
            System.out.println("HourlyRate :" + PL.getHourlyRate());
            System.out.println("Last Receipt Number :" + PL.getLastReceiptNumber());
            System.out.println("Parking Spot Number :" + PL.getParkSpotNumber());
            System.out.println("Openning Time :" + PL.getOpenningTime());
            System.out.println("Closing Time :" + PL.getClosingTime());

            System.out.println("----------------------------");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void loadCashDeskInfo(){
        //TODO
    }
    
    public void loadReceiptHistory(){
        //TODO
    }
    
    public void loadParkingSpots(){
        //TODO
    }
    
    public void loadRegisteredUsers(){
        //TODO
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Save Methods">
    public void saveParkingLotInfo(){
        //TODO
    }
    
    public void saveCashDeskInfo(){
        //TODO
    }
    
    public void saveReceiptHistory(){
        //TODO
    }
    
    public void saveParkingSpots(){
        //TODO
    }
    
    public void saveRegisteredUsers(){
        //TODO
    }
    //</editor-fold>

}
