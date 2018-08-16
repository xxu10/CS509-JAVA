package com.cs509.common;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Hashtable;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public class timeConvert {

	
	/**
	 * This class provides an interface to google maps timezone API
	 * The following is an example
	 *  String Longitude = "-89.6822510";
	 *  String Latitude = "39.6034810";
	 *  String GMTtime = "2008-05-16T10:15:30";
	 *  GoogleTimeZone timeconvert = new timeConvert();
	 *  String Localtime = timeconvert.GMTtoLocalTime(GMTtime, Longitude, Latitude);
	 * 
	 *
	 *
	 */


		
	private final String mUrlBase = "https://maps.googleapis.com/maps/api/timezone/xml?";

	public String APIKey = "AIzaSyD6Dcn9dDfEY529T6xK2HuyUjLIALypiYI";

	public String TimeStamp = "1331766000";

	public String QueryGoogleDatabase(String query)
	{
		URL url;
		HttpURLConnection connection;
		BufferedReader reader;
		String line;
		StringBuffer result = new StringBuffer();
		
		
		try {
			/**
			 * Create an HTTP connection to the server for a GET 
			 */
			url = new URL(mUrlBase + query);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", APIKey);
			
			/**
			 * If response code of SUCCESS read the XML string returned
			 * line by line to build the full return string
			 */
			
			int responseCode = connection.getResponseCode();
			if (responseCode >= HttpURLConnection.HTTP_OK) {
				InputStream inputStream = connection.getInputStream();
				String encoding = connection.getContentEncoding();
				encoding = (encoding == null ? "URF-8" : encoding);

				reader = new BufferedReader(new InputStreamReader(inputStream));
				while ((line = reader.readLine()) != null) {
					result.append(line);
				}
				reader.close();
			}
		}catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}

	/**
	 * Return a collection of all the airplanes from server
	 * 
	 * Retrieve the list of airplanes available to the specified ticketAgency via HTTPGet of the server
	 * 
	 * @param teamName identifies the name of the team requesting the collection of airplanes
	 * @return collection of Airplanes from server
	 */
	public String getTimeZoneId(String Longitude, String Latitude){
		String TimeZoneQuery = "location=" + Latitude + "," + Longitude + "&timestamp=" + TimeStamp + "&key=" + APIKey;
		String TimeZoneResponse = QueryGoogleDatabase(TimeZoneQuery);
		String googleTimeZoneId = ParseTimeZoneId (TimeZoneResponse);
		return googleTimeZoneId;
	}

	public String ParseTimeZoneId(String XMLresponse){
		
		/**
		 * load the xml string into a DOM document and return the Document
		 */
		String TimeZoneId;
		
		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			InputSource inputSource = new InputSource();
			inputSource.setCharacterStream(new StringReader(XMLresponse));
			Document Response = docBuilder.parse(inputSource);
			NodeList nodeTimeZoneId = Response.getElementsByTagName("time_zone_id");
			Element eleTimeZoneId = (Element)nodeTimeZoneId.item(0);
			TimeZoneId = eleTimeZoneId.getTextContent();	
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
			
			
		return TimeZoneId;
	}
	public String GMTtoLocalTime(String GMTtime, String Longitude, String Latitude){
		 String TimeZoneId = getTimeZoneId(Longitude, Latitude);
		 
		 Hashtable <String, String> Month_convert = new Hashtable <>();
			Month_convert.put("Jan", "01");
			Month_convert.put("Feb", "02");
			Month_convert.put("Mar", "03");
			Month_convert.put("Apr", "04");
			Month_convert.put("May", "05");
			Month_convert.put("Jun", "06");
			Month_convert.put("Jul", "07");
			Month_convert.put("Aug", "08");
			Month_convert.put("Sep", "09");
			Month_convert.put("Oct", "10");
			Month_convert.put("Nov", "11");
			Month_convert.put("Dec", "12");	
		 String GMT_year = GMTtime.substring(0, 4);
		 String GMT_month= Month_convert.get(GMTtime.substring(5, 8));
		 String GMT_day = GMTtime.substring(9,11);
		 String GMT_hour = GMTtime.substring(12,14);
		 String GMT_min = GMTtime.substring(14, 17);
		 String GMT =GMT_year+" "+GMT_month+" "+GMT_day+" "+GMT_hour+GMT_min;
		 //System.out.println(TimeZoneId);
		//System.out.println(GMT);
		 DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy MM dd HH:mm");
		 
	     //Convert String to LocalDateTime
		 ZonedDateTime LocalTime = LocalDateTime.parse(GMT,format).atOffset(ZoneOffset.UTC).atZoneSameInstant(ZoneId.of(TimeZoneId));


	     String LocalTimeResult= LocalTime.format(format);
	     //System.out.println("GMT:\t\t" + GMT);
	     //System.out.println("Local Time:\t" + LocalTimeResult);
		 return LocalTimeResult;
		 
		
		}



}
