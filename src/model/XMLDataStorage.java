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
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
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
 * @author Tom Apeltauer <apelttom@live.com>, Carné: 2013389910
 */
public class XMLDataStorage {

    //name of the file we will be accessing
    private static final String SETTINGS_FILE = "src/data/ParkingLotInfo.xml";

    //SINGLETON pattern
    private static XMLDataStorage singleton = null;
    private String fileName;
    private File fXmlFile;
    private DocumentBuilderFactory dbFactory;
    private DocumentBuilder dBuilder;
    private Document doc;

    protected XMLDataStorage(String pFileName) {
        try {
            this.fileName = pFileName;
            this.fXmlFile = new File(fileName);
            this.dbFactory = DocumentBuilderFactory.newInstance();
            this.dBuilder = dbFactory.newDocumentBuilder();
            this.doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static XMLDataStorage getInstance() {
        if (singleton == null) {
            return new XMLDataStorage(SETTINGS_FILE);
        } else {
            return singleton;
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Load Methods">
    public ParkingLot loadParkingLotInfo() {
        ParkingLot PL = new ParkingLot();
        try {
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

            PL.setCashDesk(loadCashDeskInfo());
            PL.setParkingSpots(loadParkingSpots());
            PL.setReceiptHistory(loadReceiptHistory());
            PL.setRegisteredUsers(loadRegisteredUsers());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return PL;
    }

    public CashDesk loadCashDeskInfo() {
        CashDesk CD = new CashDesk();
        try {
            Element eElement = (Element) doc.getDocumentElement().getElementsByTagName("cashDesk").item(0);

            CD.setActualCash(Float.parseFloat(eElement.getElementsByTagName("actualCash").item(0).getTextContent()));
            CD.setMinCash(Float.parseFloat(eElement.getElementsByTagName("minCash").item(0).getTextContent()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CD;
    }

    public List<Receipt> loadReceiptHistory() {
        List<Receipt> receiptHistory = new ArrayList<Receipt>();
        try {
            Element rHistoryElement = (Element) doc.getDocumentElement().getElementsByTagName("receiptHistory").item(0);
            NodeList nList = rHistoryElement.getElementsByTagName("receipt");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    Receipt receipt = new Receipt();

                    receipt.setReceiptID(Integer.parseInt(eElement.getAttribute("id")));

                    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

                    String dateString = eElement.getElementsByTagName("date").item(0).getTextContent();

                    try {
                        receipt.setDate(dateFormatter.parse(dateString));
                    } catch (Exception exc) {
                        exc.printStackTrace();
                    }

                    Element carElement = (Element) eElement.getElementsByTagName("car").item(0);

                    receipt.setChargedCar(loadCar(carElement));
                    receipt.setCost(Float.parseFloat(eElement.getElementsByTagName("cost").item(0).getTextContent()));

                    receiptHistory.add(receipt);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return receiptHistory;
    }

    public List<ParkingSpot> loadParkingSpots() {
        List<ParkingSpot> parkingSpots = new ArrayList<ParkingSpot>();
        try {
            Element sListElement = (Element) doc.getDocumentElement().getElementsByTagName("parkingSpotList").item(0);
            NodeList nList = sListElement.getElementsByTagName("parkingSpot");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    
                    ParkingSpot pParkingSpot = new ParkingSpot(
                            Integer.parseInt(eElement.getAttribute("id")),
                            eElement.getElementsByTagName("description").item(0).getTextContent()
                    );

                    pParkingSpot.setOccupied(Boolean.parseBoolean(eElement.getElementsByTagName("occupied").item(0).getTextContent()));

                    if (pParkingSpot.isOccupied()) {
                        Element carElement = (Element) eElement.getElementsByTagName("car").item(0);

                        pParkingSpot.setParkedCar(loadCar(carElement));
                    }

                    parkingSpots.add(pParkingSpot);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parkingSpots;
    }

    private Car loadCar(Element pElement) {
        Car car = new Car(
                pElement.getElementsByTagName("licensePlate").item(0).getTextContent(),
                pElement.getElementsByTagName("color").item(0).getTextContent(),
                pElement.getElementsByTagName("brand").item(0).getTextContent(),
                pElement.getElementsByTagName("model").item(0).getTextContent()
        );

        SimpleDateFormat hourFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        String entryTimeString = pElement.getElementsByTagName("entryTime").item(0).getTextContent();
        String exitTimeString = pElement.getElementsByTagName("exitTime").item(0).getTextContent();

        try {
            car.setEntryTime(hourFormatter.parse(entryTimeString));
            if (!exitTimeString.isEmpty()) {
                car.setExitTime(hourFormatter.parse(exitTimeString));
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return car;
    }

    public List<User> loadRegisteredUsers() {
        List<User> registeredUsers = new ArrayList<User>();
        try {
            Element uListElement = (Element) doc.getDocumentElement().getElementsByTagName("registeredUsers").item(0);
            NodeList nList = uListElement.getElementsByTagName("user");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    User pUser = new User(
                            eElement.getElementsByTagName("username").item(0).getTextContent(),
                            eElement.getElementsByTagName("password").item(0).getTextContent(),
                            Boolean.parseBoolean(eElement.getElementsByTagName("administrador").item(0).getTextContent())
                    );

                    registeredUsers.add(pUser);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return registeredUsers;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Save Methods">
    public void saveParkingLotInfo(ParkingLot pParkingLot) throws TransformerException {
        Document newDoc = dBuilder.newDocument();
        Element parkingLot = newDoc.createElement("ParkingLot");
        newDoc.appendChild(parkingLot);

        SimpleDateFormat hourFormatter = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat datetimeFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        Element name = newDoc.createElement("name");
        parkingLot.appendChild(name);
        name.appendChild(newDoc.createTextNode(pParkingLot.getName()));

        Element address = newDoc.createElement("address");
        parkingLot.appendChild(address);
        address.appendChild(newDoc.createTextNode(pParkingLot.getAddress()));

        Element telephone = newDoc.createElement("telephone");
        parkingLot.appendChild(telephone);
        telephone.appendChild(newDoc.createTextNode(pParkingLot.getTelephone()));

        Element slogan = newDoc.createElement("slogan");
        parkingLot.appendChild(slogan);
        slogan.appendChild(newDoc.createTextNode(pParkingLot.getSlogan()));

        Element companyID = newDoc.createElement("companyID");
        parkingLot.appendChild(companyID);
        companyID.appendChild(newDoc.createTextNode(pParkingLot.getCompanyID()));

        Element hourlyRate = newDoc.createElement("hourlyRate");
        parkingLot.appendChild(hourlyRate);
        hourlyRate.appendChild(newDoc.createTextNode(String.valueOf(pParkingLot.getHourlyRate())));

        Element lastReceiptNumber = newDoc.createElement("lastReceiptNumber");
        parkingLot.appendChild(lastReceiptNumber);
        lastReceiptNumber.appendChild(newDoc.createTextNode(String.valueOf(pParkingLot.getLastReceiptNumber())));

        Element parkSpotNumber = newDoc.createElement("parkSpotNumber");
        parkingLot.appendChild(parkSpotNumber);
        parkSpotNumber.appendChild(newDoc.createTextNode(String.valueOf(pParkingLot.getParkSpotNumber())));

        Element openningTime = newDoc.createElement("openningTime");
        parkingLot.appendChild(openningTime);
        openningTime.appendChild(newDoc.createTextNode(hourFormatter.format(pParkingLot.getOpenningTime())));

        Element closingTime = newDoc.createElement("closingTime");
        parkingLot.appendChild(closingTime);
        closingTime.appendChild(newDoc.createTextNode(hourFormatter.format(pParkingLot.getClosingTime())));

        // Save CashDesk Information
        CashDesk CD = pParkingLot.getCashDesk();

        Element cashDesk = newDoc.createElement("cashDesk");
        parkingLot.appendChild(cashDesk);
        Element actualCash = newDoc.createElement("actualCash");
        Element minCash = newDoc.createElement("minCash");
        cashDesk.appendChild(actualCash);
        cashDesk.appendChild(minCash);
        actualCash.appendChild(newDoc.createTextNode(String.valueOf(CD.getActualCash())));
        minCash.appendChild(newDoc.createTextNode(String.valueOf(CD.getMinCash())));
        
        // Save Parking Spot List Information
        List<ParkingSpot> PSList = pParkingLot.getParkingSpots();
        Element eParkingSpotList = newDoc.createElement("parkingSpotList");
        parkingLot.appendChild(eParkingSpotList);
        for(int i = 0; i < PSList.size(); i++){
            
            Element eParkingSpot = newDoc.createElement("parkingSpot");
            ParkingSpot pParkingSpot = PSList.get(i);
            eParkingSpot.setAttribute("id", String.valueOf(pParkingSpot.getSpotNumber()));
            
            Element eDescription = newDoc.createElement("description");
            if(pParkingSpot.getDescription() != null)
                eDescription.appendChild(newDoc.createTextNode(pParkingSpot.getDescription()));
            else
                eDescription.appendChild(newDoc.createTextNode(""));
            eParkingSpot.appendChild(eDescription);
            
            Element eOcuppied = newDoc.createElement("occupied");
            eOcuppied.appendChild(newDoc.createTextNode(String.valueOf(pParkingSpot.isOccupied())));
            eParkingSpot.appendChild(eOcuppied);
            
            Element eCar = newDoc.createElement("car");
            Car pCar = pParkingSpot.getParkedCar();
            if(pCar != null){
            Element eLicensePlate = newDoc.createElement("licensePlate");
            eLicensePlate.appendChild(newDoc.createTextNode(pCar.getLicensePlate()));
            eCar.appendChild(eLicensePlate);
            
            Element eColor = newDoc.createElement("color");
            eColor.appendChild(newDoc.createTextNode(pCar.getColor()));
            eCar.appendChild(eColor);
            
            Element eBrand = newDoc.createElement("brand");
            eBrand.appendChild(newDoc.createTextNode(pCar.getBrand()));
            eCar.appendChild(eBrand);
            
            Element eModel = newDoc.createElement("model");
            eModel.appendChild(newDoc.createTextNode(pCar.getModel()));
            eCar.appendChild(eModel);
            
            Element eEntryTime = newDoc.createElement("entryTime");
            eEntryTime.appendChild(newDoc.createTextNode(datetimeFormatter.format(pCar.getEntryTime())));
            eCar.appendChild(eEntryTime);
            
            Element eExitTime = newDoc.createElement("exitTime");
            if(pCar.getExitTime() != null)
                eExitTime.appendChild(newDoc.createTextNode(datetimeFormatter.format(pCar.getExitTime())));
            eCar.appendChild(eExitTime);
            }
            eParkingSpot.appendChild(eCar);
            
            eParkingSpotList.appendChild(eParkingSpot);
            
        }
        
        // Save Receipt History List Information
        List<Receipt> RHList = pParkingLot.getReceiptHistory();
        Element eReceiptHistoryList = newDoc.createElement("receiptHistory");
        parkingLot.appendChild(eReceiptHistoryList);
        for(int i = 0; i < RHList.size(); i++){
            Element eReceipt = newDoc.createElement("receipt");
            Receipt pReceipt = RHList.get(i);
            
            eReceipt.setAttribute("id", String.valueOf(pReceipt.getReceiptID()));
            
            Element eDate = newDoc.createElement("date");
            eDate.appendChild(newDoc.createTextNode(dateFormatter.format(pReceipt.getDate())));
            eReceipt.appendChild(eDate);
            
            Element eCar = newDoc.createElement("car");
            Car pCar = pReceipt.getChargedCar();
            Element eLicensePlate = newDoc.createElement("licensePlate");
            eLicensePlate.appendChild(newDoc.createTextNode(pCar.getLicensePlate()));
            eCar.appendChild(eLicensePlate);
            
            Element eColor = newDoc.createElement("color");
            eColor.appendChild(newDoc.createTextNode(pCar.getColor()));
            eCar.appendChild(eColor);
            
            Element eBrand = newDoc.createElement("brand");
            eBrand.appendChild(newDoc.createTextNode(pCar.getBrand()));
            eCar.appendChild(eBrand);
            
            Element eModel = newDoc.createElement("model");
            eModel.appendChild(newDoc.createTextNode(pCar.getModel()));
            eCar.appendChild(eModel);
            
            Element eEntryTime = newDoc.createElement("entryTime");
            eEntryTime.appendChild(newDoc.createTextNode(datetimeFormatter.format(pCar.getEntryTime())));
            eCar.appendChild(eEntryTime);
            
            Element eExitTime = newDoc.createElement("exitTime");
            eExitTime.appendChild(newDoc.createTextNode(datetimeFormatter.format(pCar.getExitTime())));
            eCar.appendChild(eExitTime);
            eReceipt.appendChild(eCar);
            
            Element eCost = newDoc.createElement("cost");
            eCost.appendChild(newDoc.createTextNode(String.valueOf(pReceipt.getCost())));
            eReceipt.appendChild(eCost);
            eReceiptHistoryList.appendChild(eReceipt);
            
        }
        
        // Save Receipt History List Information
        List<User> RUList = pParkingLot.getRegisteredUsers();
        Element eRegisteredUsersList = newDoc.createElement("registeredUsers");
        parkingLot.appendChild(eRegisteredUsersList);
        for(int i = 0; i < RUList.size(); i++){
            Element eUser = newDoc.createElement("user");
            User pUser = RUList.get(i);
            
            Element eUsername = newDoc.createElement("username");
            eUsername.appendChild(newDoc.createTextNode(String.valueOf(pUser.getUsername())));
            eUser.appendChild(eUsername);
            
            Element ePassword = newDoc.createElement("password");
            ePassword.appendChild(newDoc.createTextNode(String.valueOf(pUser.getPassword())));
            eUser.appendChild(ePassword);
            
            Element eAdministador = newDoc.createElement("administrador");
            eAdministador.appendChild(newDoc.createTextNode(String.valueOf(pUser.isAdministrador())));
            eUser.appendChild(eAdministador);
            
            eRegisteredUsersList.appendChild(eUser);
        }
        

        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            //TODO should be CONSTANT (private static final String attributes)
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            DOMSource source = new DOMSource(newDoc);
            StreamResult result = new StreamResult(fXmlFile);

            // esto es para imprimir en pantalla en lugar de archivo
            //StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);
        } catch (TransformerException ex) {
            ex.printStackTrace();
        }
        //TODO
    }

    public void saveCashDeskInfo() {
    }

    public void saveReceiptHistory() {
        //TODO
    }

    public void saveParkingSpots(List<ParkingSpot> pParkingSpots) {
        /*
         NodeList parkingSpots = doc.getElementsByTagName("parkingSpotList").item(0).getChildNodes();
        
         parkingSpots.
        
         for (int i = 0; i < pParkingSpots.size(); i++) {
         Element nombre = parkingSpots.createElement("nombre");
         if (espacios.item(i).getNodeType() == Node.ELEMENT_NODE) {
         espacios.item(i).setTextContent("Reservado");
         }
         }

         TransformerFactory transformerFactory = TransformerFactory.newInstance();
         Transformer transformer = transformerFactory.newTransformer();
         transformer.setOutputProperty(OutputKeys.INDENT, "yes");
         transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
         DOMSource source = new DOMSource(document);
         StreamResult result = new StreamResult(new File(rutaArchivo));
                
         transformer.transform(source, result);*/
    }

    public void saveRegisteredUsers() {
        //TODO
    }
    //</editor-fold>

}
