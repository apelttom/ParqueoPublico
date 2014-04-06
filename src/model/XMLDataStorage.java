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
            this.PL = new ParkingLot();
            this.CD = new CashDesk();
            this.receiptHistory = new ArrayList<Receipt>();
            this.parkingSpots = new ArrayList<ParkingSpot>();
            this.registeredUsers = new ArrayList<User>();
            
            this.fileName = pFileName;
            this.fXmlFile = new File(fileName);
            this.dbFactory = DocumentBuilderFactory.newInstance();
            this.dBuilder = dbFactory.newDocumentBuilder();
            this.doc = dBuilder.parse(fXmlFile);
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
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void loadCashDeskInfo(){
        try{
            Element eElement = (Element) doc.getDocumentElement().getElementsByTagName("cashDesk").item(0);
            
            CD.setActualCash(Float.parseFloat(eElement.getElementsByTagName("actualCash").item(0).getTextContent()));
            CD.setMinCash(Float.parseFloat(eElement.getElementsByTagName("minCash").item(0).getTextContent()));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void loadReceiptHistory(){
        try{
            Element rHistoryElement = (Element) doc.getDocumentElement().getElementsByTagName("receiptHistory").item(0);
            NodeList nList = rHistoryElement.getElementsByTagName("receipt");
            
            for (int temp = 0; temp < nList.getLength(); temp++) {
 
		Node nNode = nList.item(temp);
 
		System.out.println("\nCurrent Element :" + nNode.getNodeName());
 
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    
                    System.out.println("Receipt id : " + eElement.getAttribute("id"));
                    
                    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat hourFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    
                    String dateString = eElement.getElementsByTagName("date").item(0).getTextContent();
                    String entryTimeString = dateString + " " + eElement.getElementsByTagName("entryTime").item(0).getTextContent();
                    String exitTimeString = dateString + " " + eElement.getElementsByTagName("exitTime").item(0).getTextContent();
                    
                    try {
                        System.out.println("Date : " + dateFormatter.parse(dateString));
                    } catch (Exception exc) {
                        exc.printStackTrace();
                    }
                    
                    Element carElement = (Element) eElement.getElementsByTagName("car").item(0);
                    Car car = new Car(
                            carElement.getElementsByTagName("licensePlate").item(0).getTextContent(),
                            carElement.getElementsByTagName("color").item(0).getTextContent(),
                            carElement.getElementsByTagName("brand").item(0).getTextContent(),
                            carElement.getElementsByTagName("model").item(0).getTextContent()
                            );
                    
                    try {
                        car.setEntryTime(hourFormatter.parse(entryTimeString));
                        car.setExitTime(hourFormatter.parse(exitTimeString));
                    } catch (Exception exc) {
                        exc.printStackTrace();
                    }
                    
                    System.out.println(car.toString());
                    
                    System.out.println("Cost: " + Float.parseFloat(eElement.getElementsByTagName("cost").item(0).getTextContent()));
 
		}
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
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
