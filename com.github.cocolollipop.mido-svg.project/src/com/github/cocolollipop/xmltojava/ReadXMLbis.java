package com.github.cocolollipop.xmltojava;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import com.github.cocolollipop.univ.Teacher;

public class ReadXMLbis {
	/**
	 * The aim of this class is to get data form an XML FILE in static mode
	 * In a second time we will create a function which can used in the main program, in order to create
	 * multiple objects such as Enseignant, Matiere, Formation (package univ)
	 * 
	 * We will use a DOM parser
	 * @param args
	 * @throws XPathExpressionException 
	 */
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException{
		
		// First we have to create an object DocumentBuilderFactory in order to use the DocumentBuilder
		// then we create the DocumentBuilder
		// And finally we create the document which opens "myFile.xml"
		// the try catch block is mandatory is order to use DocumentBuilder
			
		final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		final DocumentBuilder db = dbf.newDocumentBuilder();
		
		// MyFile is our XML file which contains data about Maude Manouvrier
		final Document myDocument= db.parse(new File("myFile.xml"));

		/*
		 * Now we are going to create the Enseignant object and add set some attributes, thanks to the parser
		 */
		Element root = myDocument.getDocumentElement();
		NodeList childNodesList = root.getChildNodes();
		int nbRootNodesList = childNodesList.getLength();
		
		// We show node Root Name !
		System.out.println(root.getNodeName());
		
		// We show the nodeChild of the Root Node
	    for (int i = 0; i<nbRootNodesList; i++) {
	        if(childNodesList.item(i).getNodeType() == Node.ELEMENT_NODE) {
	            Element person = (Element) childNodesList.item(i);
				
		    	// We show the gender of the person ; using getAttribute Method
			    System.out.println("\nThe PERSON");
			    System.out.println("GENDER : " + person.getAttribute("sexe"));
				
		    	// We use getElementsByTagName in order to get the name and the surname of the person
			    Element name = (Element) person.getElementsByTagName("nom").item(0);
			    Element surname = (Element) person.getElementsByTagName("prenom").item(0);
						
			    //We show the name and surname
			    System.out.println("Name : " + name.getTextContent());
			    System.out.println("Surname : " + surname.getTextContent());
			    /**
			    // We set attribute to the Enseignant object
				Enseignant firstEnseignant = new Enseignant();
				firstEnseignant.setNomEnseignant(name.getTextContent());
				firstEnseignant.setPrenomEnseignant(surname.getTextContent());
				
				// Now we check whether it has been saved or not
				System.out.println("\n READING FROM THE OBJECT "+firstEnseignant.toString());
				System.out.println("Name obj : "+firstEnseignant.getNomEnseignant());
				
				
				/**
				 * Ici on va essayer de faire intervenir XPATH pour recuperer le nom des personne
				 *  ON DEVRA EN FAIRE UNE METHODE
				 */
			    /**
				XPath xPath =  XPathFactory.newInstance().newXPath();
				String expressionXpath = "/enseignant/personne/nom";
				NodeList myNodeList = (NodeList) xPath.compile(expressionXpath).evaluate(myDocument, XPathConstants.NODESET);
				System.out.println("D'apr�s XPATH : ");
				for (int k = 0; i < myNodeList.getLength(); i++) {
				    System.out.println(myNodeList.item(i).getFirstChild().getNodeName()); 
				}
				*/
	        }
	    }
		
		
		

		
		
		
		}

	}
