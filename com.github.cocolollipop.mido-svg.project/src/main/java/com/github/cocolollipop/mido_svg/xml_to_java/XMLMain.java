package com.github.cocolollipop.mido_svg.xml_to_java;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.github.cocolollipop.mido_svg.university.components.Licence;
import com.github.cocolollipop.mido_svg.university.components.Master;
import com.github.cocolollipop.mido_svg.university.components.Subject;
import com.github.cocolollipop.mido_svg.university.components.Teacher;

import junit.framework.TestCase;

public class XMLMain extends TestCase {
	/**
	 * myXMLDocument contains data after getXMLFile()
	 */
	private Document myXMLDocument;

	public Document getMyXMLDocument() {
		return myXMLDocument;
	}

	/**
	 * getXMLFile() is a method to get the XML File you gave us This XML
	 * contains data to supply the database All data is stored in the attribute
	 * myXMLDocument myXMLDocument is already parsed
	 * 
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public void getXMLFile() throws ParserConfigurationException, SAXException, IOException {
		// First, we point to the URL given
		URL myXML = new URL("https://raw.githubusercontent.com/oliviercailloux/projets/master/Voeux/OF_MEA5STI.xml");

		// Then we parse
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document document = dBuilder.parse(myXML.openStream());

		// Some infos
		// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		document.getDocumentElement().normalize();

		// Finally, we set the document parsed in the attribute myXMLDocument
		this.myXMLDocument = document;
	}

	/**
	 * fillSubjectsXML is a procedure to create and fill Subjects (Coursename
	 * and ECTS)
	 * 
	 * and put in the HashMap
	 * 
	 * @param mapSubjects
	 * @param document
	 */
	public void fillSubjectsXML(HashMap mapSubjects) {

		final Element racine = this.myXMLDocument.getDocumentElement();
		final NodeList racineNoeuds = racine.getChildNodes();
		final int nbRacineNoeuds = racineNoeuds.getLength();

		for (int i = 0; i < nbRacineNoeuds; i++) {

			if (racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {

				final Element subject = (Element) racineNoeuds.item(i);

				// Looking for subjects
				if (subject.getNodeName() == "ns3:course") {
					String courseTitle = "courseTitleBlank";
					double courseCredit = 0;

					// we get the name of the course here
					courseTitle = subject.getChildNodes().item(5).getFirstChild().getNodeValue();

					// we get ECTS of the course
					// we use try catch to avoid NULLPOINTEREXCEPTION if ECTS
					// doesn't exists in XML
					// I kno you don't encourage us to use this, but It is the
					// only solution to keep going if ECTS doesn't exist !
					try {
						if (subject.getChildNodes().item(23).getAttributes().getNamedItem("ECTScredits")
								.getNodeValue() != null) {
							courseCredit = Double.parseDouble(subject.getChildNodes().item(23).getAttributes()
									.getNamedItem("ECTScredits").getNodeValue());
						}
					} catch (NullPointerException e) {
						// if ECTS can't be read, we set it to "0"
						courseCredit = 0.0;
					}

					// we create a new subject
					Subject sub = new Subject(courseTitle, courseCredit);
					mapSubjects.put(sub.getTitle(), sub);
				}
			}
		}

	}

	/**
	 * fillTeachersXML is a procedure to create and fill all the Teachers found
	 * in myXMLDocument and put in the HashMap
	 * 
	 * @param mapTeachers
	 */
	public void fillTeachersXML(HashMap mapTeachers) {

		final Element racine = this.myXMLDocument.getDocumentElement();
		final NodeList racineNoeuds = racine.getChildNodes();
		final int nbRacineNoeuds = racineNoeuds.getLength();
		for (int i = 0; i < nbRacineNoeuds; i++) {
			if (racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
				final Element teacher = (Element) racineNoeuds.item(i);
				String fname = "Jules";
				String lname = "Scohy";
				String mail = "a@a.fr";
				String tel = "000";
				// Looking for subjects
				if (teacher.getNodeName() == "ns3:person") {
					fname = teacher.getElementsByTagName("ns3:given").item(0).getFirstChild().getNodeValue();
					lname = teacher.getElementsByTagName("ns3:family").item(0).getFirstChild().getNodeValue();
					mail = teacher.getElementsByTagName("ns3:email").item(0).getFirstChild().getNodeValue();
					tel = teacher.getElementsByTagName("ns3:telephone").item(0).getTextContent();
				}
				Teacher tch = new Teacher();
				tch.setFirstName(fname);
				tch.setLastName(lname);
				tch.setAddress(mail);
				tch.setPhone(tel);

				mapTeachers.put(tch.getLastName(), tch);
			}
		}
	}

	/**
	 * fillFormationsXML is a method to create Formations thanks to
	 * myXMLDocument and fill the HashMap with these data
	 * 
	 * @param mapFormations
	 */
	public void fillFormationsXML(HashMap mapFormations) {

		final Element racine = this.myXMLDocument.getDocumentElement();

		final NodeList racineNoeuds = racine.getChildNodes();
		final int nbRacineNoeuds = racineNoeuds.getLength();

		for (int i = 0; i < nbRacineNoeuds; i++) {
			if (racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
				final Element program = (Element) racineNoeuds.item(i);

				// Looking for subjects
				if (program.getNodeName() == "ns3:program") {
					// We verify if it's a L1 L2 L3 M1 or M2
					String intitule = program.getElementsByTagName("ns2:text").item(0).getTextContent();
					if (intitule.matches("[L][123]\\s.*|[M][12]\\s.*")) {
						switch (intitule.substring(0, 1)) {

						case "L1":
							Licence L1 = new Licence(intitule, 1, 0, 0);
							mapFormations.put(intitule, L1);
							break;

						case "L2":
							Licence L2 = new Licence(intitule, 2, 0, 0);
							mapFormations.put(intitule, L2);
							break;

						case "L3":
							Licence L3 = new Licence(intitule, 3, 0, 0);
							mapFormations.put(intitule, L3);
							break;

						case "M1":
							Master M1 = new Master(intitule, 1, 0, 0);
							mapFormations.put(intitule, M1);
							break;

						case "M2":
							Master M2 = new Master(intitule, 2, 0, 0);
							mapFormations.put(intitule, M2);
							break;

						default:
							System.out.println("error import formations");
							break;
						}
					}
				}
			}
		}
	}

	/**
	 * JUNIT TEST
	 * 
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public void testGetXML() throws ParserConfigurationException, SAXException, IOException {

		URL myXML = new URL("https://raw.githubusercontent.com/oliviercailloux/projets/master/Voeux/OF_MEA5STI.xml");

		// Then we parse
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document document = dBuilder.parse(myXML.openStream());
		assertNotNull(document);
		assertTrue(document.getFirstChild().getNodeName() == "CDM");
	}

	public static void main(final String[] args) throws ParserConfigurationException, SAXException, IOException {
		// some tests
		XMLMain myTestXMLMain = new XMLMain();
		myTestXMLMain.getXMLFile();
		myTestXMLMain.testGetXML();
		HashMap lesTeachers = new HashMap();
		myTestXMLMain.fillTeachersXML(lesTeachers);

		System.out.println("Boucle while");
		Iterator iterator = lesTeachers.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry mapentry = (Map.Entry) iterator.next();
			System.out.println("clé: " + mapentry.getKey() + " | valeur: " + mapentry.getValue());
		}
		System.out.println("OK");
	}
}