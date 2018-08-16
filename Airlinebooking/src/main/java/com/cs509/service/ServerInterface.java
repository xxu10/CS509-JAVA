/**
 * 
 */
package com.cs509.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

import com.cs509.common.CompareTime;
import com.cs509.common.timeConvert;
import com.cs509.dao.DaoAirplane;
import com.cs509.dao.DaoAirport;
import com.cs509.dao.DaoDeparting;
import com.cs509.entity.Airport;
import com.cs509.entity.Airports;
import com.cs509.entity.Airplane;
import com.cs509.entity.Airplanes;
import com.cs509.entity.Departing;
import com.cs509.entity.Departings;
import com.cs509.entity.Transfer1;
import com.cs509.entity.Transfer2;
import com.cs509.common.QueryFactory;

/**
 * This class provides an interface to the CS509 server. It provides sample methods to perform
 * HTTP GET and HTTP POSTS
 *   
 * @author blake
 * @version 1.1
 * @since 2016-02-24
 *
 */
@Service
public class ServerInterface {

	private final String mUrlBase = "http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem";
	@Autowired DaoAirport daoairport;
	@Autowired DaoAirplane daoairplane;
	@Autowired DaoDeparting daodeparting;
	@Autowired CompareTime comparetime;
	/**
	 * Return a collection of all the airports from server
	 * 
	 * Retrieve the list of airports available to the specified ticketAgency via HTTPGet of the server
	 * 
	 * @param teamName identifies the name of the team requesting the collection of airports
	 * @return collection of Airports from server
	 */
	public Airports getAirports (String teamName) {

		URL url;
		HttpURLConnection connection;
		BufferedReader reader;
		String line;
		StringBuffer result = new StringBuffer();
		
		String xmlAirports;
		Airports airports;

		try {
			/**
			 * Create an HTTP connection to the server for a GET 
			 */
			url = new URL(mUrlBase + QueryFactory.getAirports(teamName));
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", teamName);

			/**
			 * If response code of SUCCESS read the XML string returned
			 * line by line to build the full return string
			 */
			int responseCode = connection.getResponseCode();
			if (responseCode >= HttpURLConnection.HTTP_OK) {
				InputStream inputStream = connection.getInputStream();
				String encoding = connection.getContentEncoding();
				encoding = (encoding == null ? "UTF-8" : encoding);

				reader = new BufferedReader(new InputStreamReader(inputStream));
				while ((line = reader.readLine()) != null) {
					result.append(line);
				}
				reader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		xmlAirports = result.toString();
		airports = daoairport.addAll(xmlAirports);
		return airports;
		
	}
	
	/**
	 * Lock the database for updating by the specified team. The operation will fail if the lock is held by another team.
	 * 
	 * @param teamName is the name of team requesting server lock
	 * @return true if the server was locked successfully, else false
	 */
	public boolean lock (String teamName) {
		URL url;
		HttpURLConnection connection;

		try {
			url = new URL(mUrlBase);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("User-Agent", teamName);
			connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			
			String params = QueryFactory.lock(teamName);
			
			connection.setDoOutput(true);
			DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
			writer.writeBytes(params);
			writer.flush();
			writer.close();
			
			int responseCode = connection.getResponseCode();
			System.out.println("\nSending 'POST' to lock database");
			System.out.println(("\nResponse Code : " + responseCode));
			
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			StringBuffer response = new StringBuffer();
			
			while ((line = in.readLine()) != null) {
				response.append(line);
			}
			in.close();
			
			System.out.println(response.toString());
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Unlock the database previous locked by specified team. The operation will succeed if the server lock is held by the specified
	 * team or if the server is not currently locked. If the lock is held be another team, the operation will fail.
	 * 
	 * The server interface to unlock the server interface uses HTTP POST protocol
	 * 
	 * @param teamName is the name of the team holding the lock
	 * @return true if the server was successfully unlocked.
	 */
	public boolean unlock (String teamName) {
		URL url;
		HttpURLConnection connection;
		
		try {
			url = new URL(mUrlBase);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			
			String params = QueryFactory.unlock(teamName);
			
			connection.setDoOutput(true);
			connection.setDoInput(true);
			
			DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
			writer.writeBytes(params);
			writer.flush();
			writer.close();
		    
			int responseCode = connection.getResponseCode();
			System.out.println("\nSending 'POST' to unlock database");
			System.out.println(("\nResponse Code : " + responseCode));

			if (responseCode >= HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line;
				StringBuffer response = new StringBuffer();

				while ((line = in.readLine()) != null) {
					response.append(line);
				}
				in.close();

				System.out.println(response.toString());
			}
		}
		catch (IOException ex) {
			ex.printStackTrace();
			return false;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}
/** get Airplane
 * 
 */
	public Airplanes getAirplanes (String teamName) {

		URL url;
		HttpURLConnection connection;
		BufferedReader reader;
		String line;
		StringBuffer result = new StringBuffer();
		
		String xmlAirplanes;
		Airplanes airplanes;

		try {
			/**
			 * Create an HTTP connection to the server for a GET 
			 */
			url = new URL(mUrlBase + QueryFactory.getAirplanes(teamName));
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", teamName);

			/**
			 * If response code of SUCCESS read the XML string returned
			 * line by line to build the full return string
			 */
			int responseCode = connection.getResponseCode();
			if (responseCode >= HttpURLConnection.HTTP_OK) {
				InputStream inputStream = connection.getInputStream();
				String encoding = connection.getContentEncoding();
				encoding = (encoding == null ? "UTF-8" : encoding);

				reader = new BufferedReader(new InputStreamReader(inputStream));
				while ((line = reader.readLine()) != null) {
					result.append(line);
				}
				reader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		xmlAirplanes = result.toString();
		airplanes = daoairplane.addAll(xmlAirplanes);
		return airplanes;
		
	}
	
	/** get Departing
	 * 
	 */	
	
	public Departings getDepartings (String teamName) {

		URL url;
		HttpURLConnection connection;
		BufferedReader reader;
		String line;
		StringBuffer result = new StringBuffer();
		
		String xmlDepartings;
		Departings departings;

		try {
			/**
			 * Create an HTTP connection to the server for a GET 
			 */
			url = new URL(mUrlBase + QueryFactory.getDepartings(teamName));
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", teamName);

			/**
			 * If response code of SUCCESS read the XML string returned
			 * line by line to build the full return string
			 */
			int responseCode = connection.getResponseCode();
			if (responseCode >= HttpURLConnection.HTTP_OK) {
				InputStream inputStream = connection.getInputStream();
				String encoding = connection.getContentEncoding();
				encoding = (encoding == null ? "UTF-8" : encoding);

				reader = new BufferedReader(new InputStreamReader(inputStream));
				while ((line = reader.readLine()) != null) {
					result.append(line);
				}
				reader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		xmlDepartings = result.toString();
		departings = daodeparting.addAll(xmlDepartings);
		return departings;
		
	}
	
	public Departings getDepartings (String teamName, String depart_airport, String date) {

		URL url;
		HttpURLConnection connection;
		BufferedReader reader;
		String line;
		StringBuffer result = new StringBuffer();
		
		String xmlDepartings;
		Departings departings;

		try {
			/**
			 * Create an HTTP connection to the server for a GET 
			 */
			url = new URL(mUrlBase + QueryFactory.getDepartings(teamName,depart_airport,date));
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", teamName);

			/**
			 * If response code of SUCCESS read the XML string returned
			 * line by line to build the full return string
			 */
			int responseCode = connection.getResponseCode();
			if (responseCode >= HttpURLConnection.HTTP_OK) {
				InputStream inputStream = connection.getInputStream();
				String encoding = connection.getContentEncoding();
				encoding = (encoding == null ? "UTF-8" : encoding);

				reader = new BufferedReader(new InputStreamReader(inputStream));
				while ((line = reader.readLine()) != null) {
					result.append(line);
				}
				reader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		xmlDepartings = result.toString();
		departings = daodeparting.addAll(xmlDepartings);
		return departings;
		
	}
	
	public ArrayList<Integer> SeatsValid(String teamName, Departing departing){
        int first_valid ;
        int coach_valid ;
        ArrayList<Integer> seat_valid = new ArrayList<Integer>();
		Airplanes airplanes = getAirplanes(teamName);
		for (Airplane airplane : airplanes) {
		    if(airplane.model().equals(departing.airplane())){
		    	int first_booked = Integer.parseInt(departing.firstbooked());
		    	first_valid = airplane.firstclassseat()-first_booked;
		    	int coach_booked = Integer.parseInt(departing.coachbooked());
		    	coach_valid = airplane.coachseat()-coach_booked;	
		    	seat_valid.add(first_valid);
		    	seat_valid.add(coach_valid);
		    	break;
		    }
		    }
		return seat_valid;
	}
		
	public ArrayList<Departing> Search_flight_no(String teamName,String depart_airport, String destination,String date){
		ArrayList<Departing> transfer0 = new ArrayList<Departing>();
	    Departings departings = getDepartings(teamName,depart_airport,date);
		for (Departing departing : departings) {
			    if(departing.aCode().equals(destination)){
			    	//Departing departing_timeconvert = ServerInterface.INSTANCE.time_convert(teamName, departing);
			    	transfer0.add(departing);
			    }
		}
		return transfer0;
	}
	
	public ArrayList<Transfer1> Search_flight_one(String teamName,String depart_airport, String destination,String date){
		ArrayList<Transfer1> transfer1 = new ArrayList<Transfer1>();
		Departings departings = getDepartings(teamName,depart_airport,date);
		int time_length;
		int change_date;
		String change_bound;
		for (Departing departing : departings) {
			change_bound =departing.adate().substring(0, 12)+"24:00";
			change_date =comparetime.Compare_time(departing.adate(), change_bound); 
			Hashtable <String, String> Month_convert = new Hashtable <String, String>();
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
			String date_part1 = departing.adate().substring(0, 4);
			String date_part2 = Month_convert.get(departing.adate().substring(5, 8));
			int date_change = Integer.parseInt(departing.adate().substring(9,11))+1;
			String date1 =date_part1+"_"+date_part2+"_"+String.valueOf(date_change);
			String date_ori = date_part1+"_"+date_part2+"_"+departing.adate().substring(9,11);
			
			if((change_date>=30)&&(change_date<=180)){
				 Departings departings1 = getDepartings(teamName,departing.aCode(),date_ori);
				    for(Departing departing1 : departings1) {
				    	time_length = comparetime.Compare_time(departing.adate(), departing1.ddate());
				    	if((time_length>=30)&&(time_length<=180)) {
				    		if(departing1.aCode().equals(destination)){
				    		  Transfer1 transfer_one = Transfer_one(teamName, departing, departing1);	
				    		  transfer1.add(transfer_one);
				    		}
				    	}
				    }				    
				    Departings departings2 = getDepartings(teamName,departing.aCode(),date1);
				    for(Departing departing2 : departings2) {
				    	time_length = comparetime.Compare_time(departing.adate(), departing2.ddate());
				    	if((time_length>=30)&&(time_length<=180)) {
				    		if(departing2.aCode().equals(destination)){
				    		  Transfer1 transfer_one = Transfer_one(teamName, departing, departing2);	
				    		  transfer1.add(transfer_one);
				    		}
				    	}
				    }		    				    
			}else{
			    Departings departings1 = getDepartings(teamName,departing.aCode(),date_ori);
			    for(Departing departing1 : departings1) {
			    	time_length = comparetime.Compare_time(departing.adate(), departing1.ddate());
			    	if((time_length>=30)&&(time_length<=180)) {
			    		if(departing1.aCode().equals(destination)){
			    		  Transfer1 transfer_one = Transfer_one(teamName, departing, departing1);	
			    		  transfer1.add(transfer_one);
			    		}
			    	}
			    }
			}	
		}
		return transfer1;
	}
    public Transfer1 Transfer_one(String teamName,Departing departing1,Departing departing2) {
    	Transfer1 transfer1 = new Transfer1();
		transfer1.number1(departing1.number());
		transfer1.dCode1(departing1.dCode());
		transfer1.ddate1(departing1.ddate());
		transfer1.aCode1(departing1.aCode());
		transfer1.adate1(departing1.adate());
		transfer1.firstclass1(departing1.firstclass());
		transfer1.coach1(departing1.coach());
		transfer1.firstvalid1(ServerInterface.SeatsValid(teamName, departing1).get(0));
		transfer1.coachvalid1(ServerInterface.SeatsValid(teamName, departing1).get(1));
		String firstclass1 = departing1.firstclass().substring(1);
		String firstclass2 = departing2.firstclass().substring(1);
		float first_price1 = Float.parseFloat(firstclass1);
		float first_price2 = Float.parseFloat(firstclass2);
		transfer1.setAvgFprice((first_price1+first_price2)/2);
		String coach1 = departing1.coach().substring(1);
		String coach2 = departing2.coach().substring(1);
		float coach_price1 = Float.parseFloat(coach1);
		float coach_price2 = Float.parseFloat(coach2);
		transfer1.setAvfCprice((coach_price1+coach_price2)/2);
		transfer1.number2(departing2.number());
		transfer1.dCode2(departing2.dCode());
		transfer1.ddate2(departing2.ddate());
		transfer1.aCode2(departing2.aCode());
		transfer1.adate2(departing2.adate());
		transfer1.firstclass2(departing2.firstclass());
		transfer1.coach2(departing2.coach());
		transfer1.firstvalid2(ServerInterface.SeatsValid(teamName, departing2).get(0));
		transfer1.coachvalid2(ServerInterface.SeatsValid(teamName, departing2).get(1));
		return transfer1;
	}
    public ArrayList<Transfer2> Search_flight_two(String teamName,String depart_airport, String destination,String date){
		ArrayList<Transfer2> transfer2 = new ArrayList<Transfer2>();
		int time_length;
		int change_date;
		int change_date1;
		String change_bound;
		String change_bound1;
		Hashtable <String, String> Month_convert = new Hashtable <String, String>();
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
		Departings departings = getDepartings(teamName,depart_airport,date);
		for (Departing departing : departings) {
			change_bound =departing.adate().substring(0, 12)+"24:00";
			change_date =comparetime.Compare_time(departing.adate(), change_bound); 
			String date_part1 = departing.adate().substring(0, 4);
			String date_part2 = Month_convert.get(departing.adate().substring(5, 8));
			int date_change = Integer.parseInt(departing.adate().substring(9,11))+1;
			String date1 =date_part1+"_"+date_part2+"_"+String.valueOf(date_change);
			String date1_ori = date_part1+"_"+date_part2+"_"+departing.adate().substring(9,11);
			if((change_date>=30)&&(change_date<=180)){				
				 Departings departings1 = getDepartings(teamName,departing.aCode(),date1_ori);
				 for(Departing departing1 : departings1) {
					    time_length = comparetime.Compare_time(departing.adate(), departing1.ddate());
						change_bound1 = departing1.adate().substring(0, 12)+"24:00";
						change_date1 = comparetime.Compare_time(departing1.adate(), change_bound1);
						String date1_part1 = departing1.adate().substring(0, 4);
						String date1_part2 =  Month_convert.get(departing1.adate().substring(5, 8));
						int date_change1 = Integer.parseInt(departing1.adate().substring(9,11))+1;
						String date2 =date1_part1+"_"+date1_part2+"_"+String.valueOf(date_change1);	
						String date2_ori = date1_part1+"_"+date1_part2+"_"+departing1.adate().substring(9,11);
						if((change_date1>=30)&&(change_date1<=180)){
					    	if((time_length>=30)&&(time_length<=180)) {
					    		Departings departings2 = getDepartings(teamName,departing1.aCode(),date2_ori);
				    			for(Departing departing2 : departings2){
				    				time_length =comparetime.Compare_time(departing1.adate(), departing2.ddate());
				    				if((time_length>=30)&&(time_length<=180)){
				    					if(departing2.aCode().equals(destination)){
				    						Transfer2 transfer_twice = Transfer_twice(teamName, departing, departing1, departing2);	
				    						transfer2.add(transfer_twice);
				  			    		}
				    				}
				    			}
				    			Departings departings3 = getDepartings(teamName,departing1.aCode(),date2);
				    			for(Departing departing3 : departings3){
				    				time_length =comparetime.Compare_time(departing1.adate(), departing3.ddate());
				    				if((time_length>=30)&&(time_length<=180)){
				    					if(departing3.aCode().equals(destination)){
				    						Transfer2 transfer_twice = Transfer_twice(teamName, departing, departing1, departing3);	
				    						transfer2.add(transfer_twice);
				  			    		}
				    				}
				    			}
					    	}	
						}
						else{
					    	time_length = comparetime.Compare_time(departing.adate(), departing1.ddate());
					    	if((time_length>=30)&&(time_length<=180)) {
					    		Departings departings2 = getDepartings(teamName,departing1.aCode(),date2_ori);
				    			for(Departing departing2 : departings2){
				    				time_length =comparetime.Compare_time(departing1.adate(), departing2.ddate());
				    				if((time_length>=30)&&(time_length<=180)){
				    					if(departing2.aCode().equals(destination)){
				    						Transfer2 transfer_twice = Transfer_twice(teamName, departing, departing1, departing2);	
				    						transfer2.add(transfer_twice);
				  			    		}
				    				}
				    			}
					    	}
						}

				    }
				 
				 Departings departings11 = getDepartings(teamName,departing.aCode(),date1);
				 for(Departing departing11 : departings11) {
					    time_length = comparetime.Compare_time(departing.adate(), departing11.ddate());
						change_bound1 = departing11.adate().substring(0, 12)+"24:00";
						change_date1 = comparetime.Compare_time(departing11.adate(), change_bound1);
						String date1_part1 = departing11.adate().substring(0, 4);
						String date1_part2 = Month_convert.get(departing11.adate().substring(5, 8));
						int date_change1 = Integer.parseInt(departing11.adate().substring(9,11))+1;
						String date2 =date1_part1+"_"+date1_part2+"_"+String.valueOf(date_change1);	
						String date2_ori =date1_part1+"_"+date1_part2+"_"+departing11.adate().substring(9, 11);
						if((change_date1>=30)&&(change_date1<=180)){
					    	if((time_length>=30)&&(time_length<=180)) {
					    		Departings departings2 = getDepartings(teamName,departing11.aCode(),date2_ori);
				    			for(Departing departing2 : departings2){
				    				time_length =comparetime.Compare_time(departing11.adate(), departing2.ddate());
				    				if((time_length>=30)&&(time_length<=180)){
				    					if(departing2.aCode().equals(destination)){
				    						Transfer2 transfer_twice = Transfer_twice(teamName, departing, departing11, departing2);	
				    						transfer2.add(transfer_twice);
				  			    		}
				    				}
				    			}
				    			Departings departings3 = getDepartings(teamName,departing11.aCode(),date2);
				    			for(Departing departing3 : departings3){
				    				time_length =comparetime.Compare_time(departing11.adate(), departing3.ddate());
				    				if((time_length>=30)&&(time_length<=180)){
				    					if(departing3.aCode().equals(destination)){
				    						Transfer2 transfer_twice = Transfer_twice(teamName, departing, departing11, departing3);	
				    						transfer2.add(transfer_twice);
				  			    		}
				    				}
				    			}
					    	}	
						}
						else{
					    	time_length = comparetime.Compare_time(departing.adate(), departing11.ddate());
					    	if((time_length>=30)&&(time_length<=180)) {
					    		Departings departings2 = getDepartings(teamName,departing11.aCode(),date2_ori);
				    			for(Departing departing2 : departings2){
				    				time_length =comparetime.Compare_time(departing11.adate(), departing2.ddate());
				    				if((time_length>=30)&&(time_length<=180)){
				    					if(departing2.aCode().equals(destination)){
				    						Transfer2 transfer_twice = Transfer_twice(teamName, departing, departing11, departing2);	
				    						transfer2.add(transfer_twice);
				  			    		}
				    				}
				    			}
					    	}
						}

				    }
				 			 
				 
			}
			else{
			    Departings departings1 = getDepartings(teamName,departing.aCode(),date1_ori);
			    for(Departing departing1 : departings1) {
			    	time_length = comparetime.Compare_time(departing.adate(), departing1.ddate());
			    	if((time_length>=30)&&(time_length<=180)) {
			    		String date1_part1 = departing1.adate().substring(0, 4);
						String date1_part2 =  Month_convert.get(departing1.adate().substring(5, 8));
						String date2_ori = date1_part1+"_"+date1_part2+"_"+departing1.adate().substring(9,11);
			    		Departings departings2 = getDepartings(teamName,departing1.aCode(),date2_ori);
		    			for(Departing departing2 : departings2){
		    				time_length =comparetime.Compare_time(departing1.adate(), departing2.ddate());
		    				if((time_length>=30)&&(time_length<=180)){
		    					if(departing2.aCode().equals(destination)){
		    						Transfer2 transfer_twice = Transfer_twice(teamName, departing, departing1, departing2);	
		    						transfer2.add(transfer_twice);
		  			    		}
		    				}
		    			}
			    	}
			    }
			}
	
		}
		return transfer2;
	}
    public Transfer2 Transfer_twice(String teamName,Departing departing1,Departing departing2,Departing departing3) {
		Transfer2 transfer2 = new Transfer2();
		transfer2.number1(departing1.number());
		transfer2.dCode1(departing1.dCode());
		transfer2.ddate1(departing1.ddate());
		transfer2.aCode1(departing1.aCode());
		transfer2.adate1(departing1.adate());
		transfer2.firstclass1(departing1.firstclass());
		transfer2.coach1(departing1.coach());
		transfer2.firstvalid1(ServerInterface.SeatsValid(teamName, departing1).get(0));
		transfer2.coachvalid1(ServerInterface.SeatsValid(teamName, departing1).get(1));

		transfer2.number2(departing2.number());
		transfer2.dCode2(departing2.dCode());
		transfer2.ddate2(departing2.ddate());
		transfer2.aCode2(departing2.aCode());
		transfer2.adate2(departing2.adate());
		transfer2.firstclass2(departing2.firstclass());
		transfer2.coach2(departing2.coach());
		transfer2.firstvalid2(ServerInterface.SeatsValid(teamName, departing2).get(0));
		transfer2.coachvalid2(ServerInterface.SeatsValid(teamName, departing2).get(1));
		transfer2.number3(departing3.number());
		transfer2.dCode3(departing3.dCode());
		transfer2.ddate3(departing3.ddate());
		transfer2.aCode3(departing3.aCode());
		transfer2.adate3(departing3.adate());
		transfer2.firstclass3(departing3.firstclass());
		transfer2.coach3(departing3.coach());
		transfer2.firstvalid3(ServerInterface.SeatsValid(teamName, departing3).get(0));
		transfer2.coachvalid3(ServerInterface.SeatsValid(teamName, departing3).get(1));
		String firstclass1 = departing1.firstclass().substring(1);
		String firstclass2 = departing2.firstclass().substring(1);
		String firstclass3 = departing3.firstclass().substring(1);
		float first_price1 = Float.parseFloat(firstclass1);
		float first_price2 = Float.parseFloat(firstclass2);
		float first_price3 = Float.parseFloat(firstclass3);
		String coach1 = departing1.coach().substring(1);
		String coach2 = departing2.coach().substring(1);
		String coach3 = departing3.coach().substring(1);
		float coach_price1 = Float.parseFloat(coach1);
		float coach_price2 = Float.parseFloat(coach2);
		float coach_price3 = Float.parseFloat(coach3);
		transfer2.setAvgFprice((first_price1 + first_price2 + first_price3)/3);
		transfer2.setAvgCprice((coach_price1 + coach_price2 + coach_price3)/3);
		return transfer2;
	}
	   
    public boolean ticket_reserve_no(String teamName,ArrayList<Departing> transfer0){
    	System.out.println("\nReserve:");
		System.out.println("Please type in the flight number you want:");
		Scanner fn = new Scanner(System.in);
		String flight = fn.next();
		int fnumber = Integer.parseInt(flight);
		int fnum = fnumber-1;
		Departing transferflight = transfer0.get(fnum) ;
		System.out.println("Please type which kind of seat you want ------ f: firstclass  c:coach");		
		Scanner st = new Scanner(System.in);		
		String class_choice = st.next();   
		Boolean databaseStauts = ServerInterface.lock("DragonWarriors");
		Reserve reserveno = new Reserve();
		boolean b1 =reserveno.reserver(teamName, transferflight, class_choice);
		return b1;
    }
    
    public boolean ticket_reserve_one(String teamName,ArrayList<Transfer1> transfer1){
    	System.out.println("\nReserve:");
		System.out.println("Please type in the flight number you want:");
		Scanner fn = new Scanner(System.in);
		String flight = fn.next();
		int fnumber = Integer.parseInt(flight);
		int fnum = fnumber-1;
		Transfer1 transferflight = transfer1.get(fnum) ;
		System.out.println("Please type which kind of seat you want ------ f: firstclass  c:coach");		
		Scanner st = new Scanner(System.in);		
		String class_choice = st.next();   
		Boolean databaseStauts = ServerInterface.lock("DragonWarriors");
		Reserve reserveone = new Reserve();
		boolean b1 =reserveone.reserver_one(teamName, transferflight, class_choice);
		return b1;
    }
    
    public boolean ticket_reserve_two(String teamName,ArrayList<Transfer2> transfer2){
    	System.out.println("\nReserve:");
		System.out.println("Please type in the flight number you want:");
		Scanner fn = new Scanner(System.in);
		String flight = fn.next();
		int fnumber = Integer.parseInt(flight);
		int fnum = fnumber-1;
		Transfer2 transferflight = transfer2.get(fnum) ;
		System.out.println("Please type which kind of seat you want ------ f: firstclass  c:coach");		
		Scanner st = new Scanner(System.in);		
		String class_choice = st.next();   
		Boolean databaseStauts = ServerInterface.lock("DragonWarriors");
		Reserve reservetwo = new Reserve();
		boolean b1 =reservetwo.reserver_two(teamName, transferflight, class_choice);
		return b1;
    }
       
    public Departing time_convert_no(String teamName, Departing departing){
    	Airports airports = getAirports(teamName);
		timeConvert timeconvert1 = new timeConvert();
		for(Airport airport : airports){
			if(airport.code().equals(departing.dCode())){
				String delongitude = String.valueOf(airport.longitude());
				String delatitude = String.valueOf(airport.latitude());
				String Localdeparttime = timeconvert1.GMTtoLocalTime(departing.ddate().substring(0, 17), delongitude, delatitude);
				departing.ddate(Localdeparttime);
			}
			if(airport.code().equals(departing.aCode())){
				String arlongitude = String.valueOf(airport.longitude());
				String arlatitude = String.valueOf(airport.latitude());
				String Localarrivetime = timeconvert1.GMTtoLocalTime(departing.adate().substring(0, 17), arlongitude, arlatitude);
				departing.adate(Localarrivetime);
			}
		}
		return departing;
    }
    
	public Transfer1 time_convert_one(String teamName, Transfer1 transfer1) {
		Airports airports = getAirports(teamName);
		timeConvert timeconvert1 = new timeConvert();
		for (Airport airport : airports) {
			if (airport.code().equals(transfer1.dCode1())) {
				String delongitude = String.valueOf(airport.longitude());
				String delatitude = String.valueOf(airport.latitude());
				String Localdeparttime = timeconvert1.GMTtoLocalTime(transfer1.ddate1().substring(0, 17), delongitude,
						delatitude);
				transfer1.ddate1(Localdeparttime);
			}
			if (airport.code().equals(transfer1.dCode2())) {
				String delongitude = String.valueOf(airport.longitude());
				String delatitude = String.valueOf(airport.latitude());
				String Localdeparttime = timeconvert1.GMTtoLocalTime(transfer1.ddate2().substring(0, 17), delongitude,
						delatitude);
				transfer1.ddate2(Localdeparttime);
			}
			if (airport.code().equals(transfer1.aCode1())) {
				String arlongitude = String.valueOf(airport.longitude());
				String arlatitude = String.valueOf(airport.latitude());
				String Localarrivetime = timeconvert1.GMTtoLocalTime(transfer1.adate1().substring(0, 17), arlongitude,
						arlatitude);
				transfer1.adate1(Localarrivetime);
			}
			if (airport.code().equals(transfer1.aCode2())) {
				String arlongitude = String.valueOf(airport.longitude());
				String arlatitude = String.valueOf(airport.latitude());
				String Localarrivetime = timeconvert1.GMTtoLocalTime(transfer1.adate2().substring(0, 17), arlongitude,
						arlatitude);
				transfer1.adate2(Localarrivetime);
			}
		}
		return transfer1;
	}

	public Transfer2 time_convert_two(String teamName, Transfer2 transfer2) {
		Airports airports = getAirports(teamName);
		timeConvert timeconvert1 = new timeConvert();
		for (Airport airport : airports) {
			if (airport.code().equals(transfer2.dCode1())) {
				String delongitude = String.valueOf(airport.longitude());
				String delatitude = String.valueOf(airport.latitude());
				String Localdeparttime = timeconvert1.GMTtoLocalTime(transfer2.ddate1().substring(0, 17), delongitude,
						delatitude);
				transfer2.ddate1(Localdeparttime);
			}
			if (airport.code().equals(transfer2.dCode2())) {
				String delongitude = String.valueOf(airport.longitude());
				String delatitude = String.valueOf(airport.latitude());
				String Localdeparttime = timeconvert1.GMTtoLocalTime(transfer2.ddate2().substring(0, 17), delongitude,
						delatitude);
				transfer2.ddate2(Localdeparttime);
			}
			if (airport.code().equals(transfer2.dCode3())) {
				String delongitude = String.valueOf(airport.longitude());
				String delatitude = String.valueOf(airport.latitude());
				String Localdeparttime = timeconvert1.GMTtoLocalTime(transfer2.ddate3().substring(0, 17), delongitude,
						delatitude);
				transfer2.ddate3(Localdeparttime);
			}
			if (airport.code().equals(transfer2.aCode1())) {
				String arlongitude = String.valueOf(airport.longitude());
				String arlatitude = String.valueOf(airport.latitude());
				String Localarrivetime = timeconvert1.GMTtoLocalTime(transfer2.adate1().substring(0, 17), arlongitude,
						arlatitude);
				transfer2.adate1(Localarrivetime);
			}
			if (airport.code().equals(transfer2.aCode2())) {
				String arlongitude = String.valueOf(airport.longitude());
				String arlatitude = String.valueOf(airport.latitude());
				String Localarrivetime = timeconvert1.GMTtoLocalTime(transfer2.adate2().substring(0, 17), arlongitude,
						arlatitude);
				transfer2.adate2(Localarrivetime);
			}
			if (airport.code().equals(transfer2.aCode3())) {
				String arlongitude = String.valueOf(airport.longitude());
				String arlatitude = String.valueOf(airport.latitude());
				String Localarrivetime = timeconvert1.GMTtoLocalTime(transfer2.adate3().substring(0, 17), arlongitude,
						arlatitude);
				transfer2.adate3(Localarrivetime);
			}
		}
		return transfer2;
	}
		
    
}
    

