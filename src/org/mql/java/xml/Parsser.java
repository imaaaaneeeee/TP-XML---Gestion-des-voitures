package org.mql.java.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.mql.java.models.VoitureModel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Parsser {
	private String xmlFile ;
	private DocumentBuilderFactory factory;
	private DocumentBuilder builder;
	private Document document ;
	private List<VoitureModel> voituresList;
	
	public Parsser(String xmlFile) {
		voituresList = new ArrayList<>();
		try {
			this.xmlFile=xmlFile;	
			factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
			document = builder.parse(xmlFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void enregistrerModifications() {
		try {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource domSource = new DOMSource(document);
		StreamResult streamResult = new StreamResult(xmlFile);
		//transformer.setOutputProperty(OutputKeys.INDENT,"yes");
		transformer.transform(domSource, streamResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addCar(VoitureModel voitureM) {
		if (!checkIfAlreadyExists(voitureM)) {
			Element racine = document.getDocumentElement();
			// Creer un nouvel element voiture
			Element voiture = document.createElement("voiture");
			voiture.setAttribute("model", voitureM.getModel());
			voiture.setAttribute("matricule", voitureM.getMatricule());
			voiture.setAttribute("année",(voitureM.getAnnée()));
			voiture.setAttribute("couleur", voitureM.getColeur());
			voiture.setAttribute("automatique",voitureM.getAutomatique());
			voiture.setAttribute("marque",voitureM.getMarque());
			// ajouter l'element voiture à la racine de l'arborescence de données XML
			racine.appendChild(voiture);
			enregistrerModifications();
		}
	}
	
	public void deleteCar(String matricule) {
		try {
			NodeList voitures = document.getElementsByTagName("voiture");
			for (int i = 0; i < voitures.getLength(); i++) {
				Node voiture =voitures.item(i);
				if(voiture.getAttributes().getNamedItem("matricule").getNodeValue().equals(matricule)) {
					voiture.getParentNode().removeChild(voiture);
				}
				enregistrerModifications();	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteCar(VoitureModel voitureM) {
		try {
			NodeList voitures = document.getElementsByTagName("voiture");
			for (int i = 0; i < voitures.getLength(); i++) {
				Node voiture =voitures.item(i);
				if(voiture.getAttributes().getNamedItem("matricule").getNodeValue().equals(voitureM.getMatricule())) {
					voiture.getParentNode().removeChild(voiture);
				}
				enregistrerModifications();	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkIfAlreadyExists(VoitureModel voiture) {
		for (VoitureModel voitureTmp : voituresList) {
			if (voitureTmp.getMatricule().equals(voiture.getMatricule())) {
				return true;
			}
		}
		return false;
	}
	
	public List<VoitureModel> voitureParse() {
			NodeList voitures = document.getElementsByTagName("voiture");
			for (int i = 0; i < voitures.getLength(); i++) {
				Node voiture = voitures.item(i);
				if (voiture.getNodeType() == Node.ELEMENT_NODE) {
					Element element =(Element) voiture;
					VoitureModel voitureModel = new VoitureModel();
					voitureModel.setAnnée(element.getAttribute("année"));
					voitureModel.setAutomatique(element.getAttribute("automatique"));
					voitureModel.setColeur(element.getAttribute("couleur"));
					voitureModel.setMarque(element.getAttribute("marque"));
					voitureModel.setMatricule(element.getAttribute("matricule"));
					voitureModel.setModel(element.getAttribute("model"));
					voituresList.add(voitureModel);
				}
		}
		return voituresList;
	}
	
	public void modifyCar(VoitureModel voiture) {
		NodeList nodeList = document.getElementsByTagName("voiture");
		for (int temp = 0; temp < nodeList.getLength(); temp++) {
			Node node = nodeList.item(temp);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				if(element.getAttribute("matricule").equals(voiture.getMatricule())) {
					element.setAttribute("model", voiture.getModel());
					element.setAttribute("marque", voiture.getMarque());
					element.setAttribute("coleur", voiture.getColeur());
					element.setAttribute("annee", voiture.getAnnée());
					element.setAttribute("automatique", voiture.getAutomatique());
					enregistrerModifications();	
				}
			}
		}
	}

	public static void main(String[] args) {
		Parsser parser = new  Parsser("resources/voitures.xml");
		VoitureModel voiture = new VoitureModel("KKKK", "KKKK", "KKKK", "KKKK", "KKKK", "ooooo");
		parser.modifyCar(voiture);
		
	
	}
	
	

}
