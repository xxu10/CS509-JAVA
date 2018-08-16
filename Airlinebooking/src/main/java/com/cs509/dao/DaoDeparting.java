package com.cs509.dao;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.cs509.entity.Airplane;
import com.cs509.entity.Airplanes;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.cs509.entity.Departing;
import com.cs509.entity.Departings;

public class DaoDeparting {
	public static Departings addAll (String xmlDepartings) throws NullPointerException {
		Departings departings = new Departings();
		
		// Load the XML string into a DOM tree for ease of processing
		// then iterate over all nodes adding each airport to our collection
		Document docDepartings = buildDomDoc (xmlDepartings);
		NodeList nodesDepartings = docDepartings.getElementsByTagName("Flight");
		
		for (int i = 0; i < nodesDepartings.getLength(); i++) {
			Element elementDeparting = (Element) nodesDepartings.item(i);
			Departing departing = buildDeparting (elementDeparting);
			
			if (departing.isValid()) {
				departings.add(departing);
			}
		}
		
		return departings;
	}

	/**
	 * Creates a Departing object from a DOM node
	 * 
	
	 */
	static private Departing buildDeparting (Node nodeDeparting) {
		/**
		 *
		 */
		Departing departing = new Departing();

		String airplane;
		String flighttime;
		String number;       
	    String dCode;
		String ddate;
	    String aCode;
		String adate;
		String first;
		String first_booked;
		String coach;
		String coach_booked;
		
		// The airport element has attributes of Name and 3 character airport code
		Element elementDeparting = (Element) nodeDeparting;
		airplane = elementDeparting.getAttributeNode("Airplane").getValue();
		flighttime = elementDeparting.getAttributeNode("FlightTime").getValue();
		number = elementDeparting.getAttributeNode("Number").getValue();

		Element elementDeparture = (Element)elementDeparting.getElementsByTagName("Departure").item(0);
		Element elementdCodTime;
		elementdCodTime = (Element)elementDeparture.getElementsByTagName("Code").item(0);
		dCode = getCharacterDataFromElement(elementdCodTime);	
		elementdCodTime = (Element)elementDeparture.getElementsByTagName("Time").item(0);
		ddate = getCharacterDataFromElement(elementdCodTime);
		Element elementArrival = (Element)elementDeparting.getElementsByTagName("Arrival").item(0);
		Element elementaCodTime;
		elementaCodTime = (Element)elementArrival.getElementsByTagName("Code").item(0);
		aCode = getCharacterDataFromElement(elementaCodTime);	
		elementaCodTime = (Element)elementArrival.getElementsByTagName("Time").item(0);
		adate = getCharacterDataFromElement(elementaCodTime);
		Element elementSeating = (Element)elementDeparting.getElementsByTagName("Seating").item(0);
		Element elementFirst = (Element)elementSeating.getElementsByTagName("FirstClass").item(0);
		first = elementFirst.getAttributeNode("Price").getValue();
		first_booked =elementFirst.getTextContent();
		Element elementCoach = (Element)elementSeating.getElementsByTagName("Coach").item(0);
		coach = elementCoach.getAttributeNode("Price").getValue();
		coach_booked =elementCoach.getTextContent();
		/**
		 * Update the Airport object with values from XML node
		 */
		departing.airplane(airplane);
		departing.flighttime(flighttime);
		departing.number(number);
		departing.dCode(dCode);
		departing.ddate(ddate);
		departing.aCode(aCode);
		departing.adate(adate);
		departing.dCode(dCode);
		departing.firstclass(first);
		departing.coach(coach);
		departing.firstbooked(first_booked);
		departing.coachbooked(coach_booked);
		return departing;
	}

	/**
	 * Builds a DOM tree from an XML string
	 * 
	 * Parses the XML file and returns a DOM tree that can be processed
	 * 
	 * @param xmlString XML String containing set of objects
	 * @return DOM tree from parsed XML or null if exception is caught
	 */
	static private Document buildDomDoc (String xmlString) {
		/**
		 * load the xml string into a DOM document and return the Document
		 */
		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			InputSource inputSource = new InputSource();
			inputSource.setCharacterStream(new StringReader(xmlString));
			
			return docBuilder.parse(inputSource);
		}
		catch (ParserConfigurationException e) {
			e.printStackTrace();
			return null;
		}
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		catch (SAXException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Retrieve character data from an element if it exists
	 * 
	 * @param e is the DOM Element to retrieve character data from
	 * @return the character data as String [possibly empty String]
	 */
	private static String getCharacterDataFromElement (Element e) {
		Node child = e.getFirstChild();
	    if (child instanceof CharacterData) {
	        CharacterData cd = (CharacterData) child;
	        return cd.getData();
	      }
	      return "";
	}

}
