package dao;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import departing.Departing;
import transfer.Transfer1;
import transfer.Transfer2;


public class Reserve {
	
	private final String mUrlBase = "http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem";
	
	public boolean reserver(String teamName, Departing flight, String class_choice) {
		URL url2;
		String	xmlFlights;
		HttpURLConnection connection;
		StringBuilder bookFlight = new StringBuilder();
		bookFlight.append("<Flights>");
		System.out.println(flight);
	    xmlFlights = "<Flight number=\""+flight.number()+"\" seating=\""+flight.getclass(class_choice)+"\"/>";
	    bookFlight.append(xmlFlights);	
		bookFlight.append("</Flights>");
		StringBuilder newUrl = new StringBuilder();
		newUrl.append(mUrlBase);
		String data = bookFlight.toString();
		String params = "team="+teamName+"&action=buyTickets&flightData="+data;
		newUrl.append(params);
		String reverseUrl = newUrl.toString();
		//String haha = reverseUrl.replaceAll(" ", "%20");
		//System.out.println(haha);
		try {
			//String encodedURL=java.net.URLEncoder.encode(reverseUrl,"UTF-8");
			url2 = new URL(mUrlBase);
			connection = (HttpURLConnection) url2.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("User-Agent", teamName);
			connection.setDoOutput(true);
			DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
			writer.writeBytes(params);
			writer.flush();
			writer.close();

			int responseCode = connection.getResponseCode();
			System.out.println("\nSending 'POST' to reserver database");
			System.out.println(("\nResponse Code : " + responseCode));

			if (responseCode >= HttpURLConnection.HTTP_ACCEPTED) {
				//System.out.println("OK");
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line;
				StringBuffer response = new StringBuffer();

				while ((line = in.readLine()) != null) {
					response.append(line);
				}
				in.close();

				System.out.println(response.toString() +"respose sss");
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			return false;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean reserver_one(String teamName, Transfer1 flight, String class_choice) {
		URL url2;
		String	xmlFlights;
		HttpURLConnection connection;
		StringBuilder bookFlight = new StringBuilder();
		bookFlight.append("<Flights>");
		System.out.println("Going to reserve :"+flight);
		xmlFlights = "<Flight number=\""+flight.number1()+"\" seating=\""+flight.getclass(class_choice)+"\"/>";
		bookFlight.append(xmlFlights);
		xmlFlights = "<Flight number=\""+flight.number2()+"\" seating=\""+flight.getclass(class_choice)+"\"/>";
		bookFlight.append(xmlFlights);
		bookFlight.append("</Flights>");
		StringBuilder newUrl = new StringBuilder();
		newUrl.append(mUrlBase);
		String data = bookFlight.toString();
		String params = "team="+teamName+"&action=buyTickets&flightData="+data;
		newUrl.append(params);
		String reverseUrl = newUrl.toString();
		String haha = reverseUrl.replaceAll(" ", "%20");
		//System.out.println(haha);
		try {
			//String encodedURL=java.net.URLEncoder.encode(reverseUrl,"UTF-8");
			url2 = new URL(mUrlBase);
			connection = (HttpURLConnection) url2.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("User-Agent", teamName);
			connection.setDoOutput(true);
			DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
			writer.writeBytes(params);
			writer.flush();
			writer.close();

			int responseCode = connection.getResponseCode();
			System.out.println("\nSending 'POST' to reserver database");
			System.out.println(("\nResponse Code : " + responseCode));

			if (responseCode >= HttpURLConnection.HTTP_ACCEPTED) {
				//System.out.println("OK");
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line;
				StringBuffer response = new StringBuffer();

				while ((line = in.readLine()) != null) {
					response.append(line);
				}
				in.close();

				System.out.println(response.toString() +"respose sss");
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			return false;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean reserver_two(String teamName, Transfer2 flight, String class_choice) {
		URL url2;
		String	xmlFlights;
		HttpURLConnection connection;
		StringBuilder bookFlight = new StringBuilder();
		bookFlight.append("<Flights>");
		System.out.println("Going to reserve :"+flight);
		xmlFlights = "<Flight number=\""+flight.number1()+"\" seating=\""+flight.getclass(class_choice)+"\"/>";
		bookFlight.append(xmlFlights);
		xmlFlights = "<Flight number=\""+flight.number2()+"\" seating=\""+flight.getclass(class_choice)+"\"/>";
		bookFlight.append(xmlFlights);
		xmlFlights = "<Flight number=\""+flight.number3()+"\" seating=\""+flight.getclass(class_choice)+"\"/>";
		bookFlight.append(xmlFlights);
		bookFlight.append("</Flights>");
		StringBuilder newUrl = new StringBuilder();
		newUrl.append(mUrlBase);
		String data = bookFlight.toString();
		String params = "team="+teamName+"&action=buyTickets&flightData="+data;
		newUrl.append(params);
		String reverseUrl = newUrl.toString();
		String haha = reverseUrl.replaceAll(" ", "%20");
		//System.out.println(haha);
		try {
			//String encodedURL=java.net.URLEncoder.encode(reverseUrl,"UTF-8");
			url2 = new URL(mUrlBase);
			connection = (HttpURLConnection) url2.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("User-Agent", teamName);
			connection.setDoOutput(true);
			DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
			writer.writeBytes(params);
			writer.flush();
			writer.close();

			int responseCode = connection.getResponseCode();
			System.out.println("\nSending 'POST' to reserver database");
			System.out.println(("\nResponse Code : " + responseCode));

			if (responseCode >= HttpURLConnection.HTTP_ACCEPTED) {
				//System.out.println("OK");
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line;
				StringBuffer response = new StringBuffer();

				while ((line = in.readLine()) != null) {
					response.append(line);
				}
				in.close();

				System.out.println(response.toString() +"respose sss");
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			return false;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}
	
}
