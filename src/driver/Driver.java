/**
 * 
 */
package driver;

import java.util.Scanner;
import java.util.Hashtable;
import java.util.ArrayList;
import airport.Airport;
import airport.Airports;
import airplane.Airplane;
import airplane.Airplanes;
import departing.Departing;
import departing.Departings;
import dao.ServerInterface;
import java.util.Enumeration;
import transfer.Transfer1;
import transfer.Transfer2;
import sort.Sort_by;
import utils.timeConvert;

/**
 * @author blake
 *
 */
public class Driver {

	/**
	 * Entry point for CS509 sample code driver
	 * 
	 * This driver will retrieve the list of airports from the CS509 server and print the list 
	 * to the console sorted by 3 character airport code
	 * 
	 * @param args is the arguments passed to java vm in format of "CS509.sample teamName" where teamName is a valid team
	 */
	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("usage: CS509.sample teamName");
			System.exit(-1);
			return;
		}
		while (true) {
			String teamName = args[0];
			int time_length;
			Hashtable<String, ArrayList<Departing>> transfer1_flight = new Hashtable<>();
			Hashtable<Integer, ArrayList<Departing>> transfer_time = new Hashtable<>();
			ArrayList<Departing> transfer0 = new ArrayList<>();
			ArrayList<Transfer1> transfer1 = new ArrayList<>();
			ArrayList<Transfer2> transfer2 = new ArrayList<>();
			System.out.println("Please type in the departing airport");
			Scanner da = new Scanner(System.in);
			String depart_airport = da.next();
			System.out.println("Please type in the arriving airport");
			String destination = da.next();
			System.out.println("Please type in the date for departing");
			String date = da.next();
			System.out.println("Do you want to book  a onetrip or round trip------1:onetrip   2 round trip");
			String tripType = da.next();
			if (tripType.equals("1")) {
				System.out.println("Do you want transfer?-----1:No transfer   2:Transfer once  3:Transfer twice");
				String transfer_choice = da.next();
				System.out.println("Sort by.....1:departure time,  2:arriving time,  3:firstclass price,  4:coach price,  5:flighttime");
				String sort_by = da.next();
				System.out.println("Sort by.....1:Positive sequence,  2:Negative sequence");
				String sortOrder = da.next();
				int order = Integer.parseInt(sortOrder);
				int Sort_by = Integer.parseInt(sort_by);
			//Departings departings = ServerInterface.INSTANCE.getDepartings(teamName,depart_airport);
			/*for (Departing departing : departings) {
				    if(departing.aCode().equals(destination)){
				    	//Departing departing_timeconvert = ServerInterface.INSTANCE.time_convert(teamName, departing);
				    	transfer0.add(departing);
				    }
				    Departings departings1 = ServerInterface.INSTANCE.getDepartings(teamName,departing.aCode());
				    for(Departing departing1 : departings1) {
				    	time_length = ServerInterface.INSTANCE.Compare_time(departing.adate(), departing1.ddate());
				    	if((time_length>=30)&&(time_length<=180)) {
				    		if(departing1.aCode().equals(destination)){
				    		  Transfer1 transfer_one = ServerInterface.INSTANCE.Transfer_one(teamName, departing, departing1);	
				    		  transfer1.add(transfer_one);
				    		}
				    		else {
				    			Departings departings2 = ServerInterface.INSTANCE.getDepartings(teamName,departing1.aCode());
				    			for(Departing departing2 : departings2){
				    				time_length = ServerInterface.INSTANCE.Compare_time(departing1.adate(), departing2.ddate());
				    				if((time_length>=30)&&(time_length<=180)){
				    					if(departing2.aCode().equals(destination)){
				    						Transfer2 transfer_twice = ServerInterface.INSTANCE.Transfer_twice(teamName, departing, departing1, departing2);	
				    						transfer2.add(transfer_twice);
				  			    		}
				    				}
				    			}
				    			
				    		}			    		
				    	}
				     }
		     }
		     */
			Sort_by Sort = new Sort_by();
			if(transfer_choice.equals("1")){
				System.out.println("\nNo transfer:");
				transfer0 = ServerInterface.INSTANCE.Search_flight_no(teamName, depart_airport, destination,date);
				ArrayList<Departing> convert1 =new ArrayList<>();
				ArrayList<Departing> sorted1 = new ArrayList<>();
				int k =0;
				for(int i=0;i<transfer0.size();i++){				
					Departing con_sorted1 =ServerInterface.INSTANCE.time_convert_no(teamName, transfer0.get(i));
					convert1.add(con_sorted1);
					
				}
				sorted1 = Sort.Sort_by_no(Sort_by, convert1,order);
				for(int i=0;i<convert1.size();i++){				
					k=i+1;
					System.out.println("the "+k+"th flight:  "+sorted1.get(i));				
				}
				boolean b = ServerInterface.INSTANCE.ticket_reserve_no(teamName, sorted1);
				System.out.println(b);
			}
			if(transfer_choice.equals("2")){
				System.out.println("\nTransfer once:");
				transfer1 = ServerInterface.INSTANCE.Search_flight_one(teamName, depart_airport, destination,date);
				ArrayList<Transfer1> sorted2 = new ArrayList<>();
				sorted2 = Sort.Sort_by_onetransfer(Sort_by, transfer1,order);
				ArrayList<Transfer1> convert2 =new ArrayList<>();
				int k = 0;
				for(int i=0;i<transfer1.size();i++){				
					Transfer1 con_sorted2 =ServerInterface.INSTANCE.time_convert_one(teamName, transfer1.get(i));
					convert2.add(con_sorted2);
					
				}
				sorted2 = Sort.Sort_by_onetransfer(Sort_by, convert2,order);
				for(int i=0;i<convert2.size();i++){				
					k=i+1;
					System.out.println("the "+k+"th flight:  "+sorted2.get(i));				
				}
				boolean b = ServerInterface.INSTANCE.ticket_reserve_one(teamName, sorted2);
				System.out.println(b);
			}
			if(transfer_choice.equals("3")){
				System.out.println("\nTransfer twice:");
				transfer2 = ServerInterface.INSTANCE.Search_flight_two(teamName, depart_airport, destination,date);
				ArrayList<Transfer2> convert3 = new ArrayList<>();
				ArrayList<Transfer2> sorted3 = new ArrayList<>();
				int k =0;
				for(int i=0;i<transfer2.size();i++){				
					Transfer2 con_sorted3 =ServerInterface.INSTANCE.time_convert_two(teamName, transfer2.get(i));
					convert3.add(con_sorted3);
				}
				sorted3 = Sort.Sort_by_twotransfer(Sort_by, convert3,order);
				for(int i=0;i<convert3.size();i++){				
					k=i+1;
					System.out.println("the "+k+"th flight:  "+sorted3.get(i));				
				}
				 boolean b = ServerInterface.INSTANCE.ticket_reserve_two(teamName, sorted3);
				 System.out.println(b);
			}    
	  }
	
            else { // return trip
            	
			System.out.println("Do you want transfer?-----1:No transfer   2:Transfer once  3:Transfer twice");
			String transfer_choice = da.next();
			System.out.println("Sort by.....1:departure time,  2:arriving time,  3:firstclass price,  4:coach price, 5:flight time");
			String sort_by = da.next();
			System.out.println("Sort by.....1:Positive sequence,  2:Negative sequence");
			String sortOrder = da.next();
			int order = Integer.parseInt(sortOrder);
			int Sort_by = Integer.parseInt(sort_by);
			Sort_by Sort = new Sort_by();
			if (transfer_choice.equals("1")) {
				System.out.println("\nNo transfer:");
				transfer0 = ServerInterface.INSTANCE.Search_flight_no(teamName,destination,depart_airport,date);
				ArrayList<Departing> convert1 =new ArrayList<>();
				ArrayList<Departing> sorted1 = new ArrayList<>();
				int k =0;
				for(int i=0;i<transfer0.size();i++){				
					Departing con_sorted1 =ServerInterface.INSTANCE.time_convert_no(teamName, transfer0.get(i));
					convert1.add(con_sorted1);
					
				}
				sorted1 = Sort.Sort_by_no(Sort_by, convert1,order);
				for(int i=0;i<convert1.size();i++){				
					k=i+1;
					System.out.println("the "+k+"th flight:  "+sorted1.get(i));				
				}
				boolean b = ServerInterface.INSTANCE.ticket_reserve_no(teamName, sorted1);
				System.out.println(b);
			}
			if (transfer_choice.equals("2")) {
				System.out.println("\nTransfer once:");
				transfer1 = ServerInterface.INSTANCE.Search_flight_one(teamName, depart_airport, destination,date);
				ArrayList<Transfer1> sorted2 = new ArrayList<>();
				sorted2 = Sort.Sort_by_onetransfer(Sort_by, transfer1,order);
				ArrayList<Transfer1> convert2 =new ArrayList<>();
				int k = 0;
				for(int i=0;i<transfer1.size();i++){				
					Transfer1 con_sorted2 =ServerInterface.INSTANCE.time_convert_one(teamName, transfer1.get(i));
					convert2.add(con_sorted2);
					
				}
				sorted2 = Sort.Sort_by_onetransfer(Sort_by, convert2,order);
				for(int i=0;i<convert2.size();i++){				
					k=i+1;
					System.out.println("the "+k+"th flight:  "+sorted2.get(i));				
				}
				boolean b = ServerInterface.INSTANCE.ticket_reserve_one(teamName, sorted2);
				System.out.println(b);
			}
			if (transfer_choice.equals("3")) {
				System.out.println("\nTransfer twice:");
				transfer2 = ServerInterface.INSTANCE.Search_flight_two(teamName, depart_airport, destination,date);
				ArrayList<Transfer2> convert3 = new ArrayList<>();
				ArrayList<Transfer2> sorted3 = new ArrayList<>();
				int k =0;
				for(int i=0;i<transfer2.size();i++){				
					Transfer2 con_sorted3 =ServerInterface.INSTANCE.time_convert_two(teamName, transfer2.get(i));
					convert3.add(con_sorted3);
				}
				sorted3 = Sort.Sort_by_twotransfer(Sort_by, convert3,order);
				for(int i=0;i<convert3.size();i++){				
					k=i+1;
					System.out.println("the "+k+"th flight:  "+sorted3.get(i));				
				}
				 boolean b = ServerInterface.INSTANCE.ticket_reserve_two(teamName, sorted3);
				 System.out.println(b);
			}
              	System.out.println("---------Return trip selection process:");
            	System.out.println("Which date do you want to return");
				String returnDate = da.next();
				System.out.println("Do you want transfer?-----1:No transfer   2:Transfer once  3:Transfer twice");
				String transfer_choiceR = da.next();
				System.out.println("Sort by.....1:departure time,  2:arriving time,  3:firstclass price,  4:coach price,  5:flighttime");
				String sort_byR = da.next();
				System.out.println("Sort by.....1:Positive sequence,  2:Negative sequence");
				String sortOrderR = da.next();
				int orderR = Integer.parseInt(sortOrderR);
				int Sort_byR = Integer.parseInt(sort_byR);
				Sort_by SortR = new Sort_by();
				if (transfer_choiceR.equals("1")) {
					System.out.println("\nNo transfer:");
					transfer0 = ServerInterface.INSTANCE.Search_flight_no(teamName, destination, depart_airport, returnDate);
					ArrayList<Departing> convert1 = new ArrayList<>();
					ArrayList<Departing> sorted1 = new ArrayList<>();
					sorted1 = SortR.Sort_by_no(Sort_byR, transfer0, orderR);
					int k = 0;
					for (int i = 0; i < sorted1.size(); i++) {
						k = i + 1;
						Departing con_sorted1 = ServerInterface.INSTANCE.time_convert_no(teamName, sorted1.get(i));
						convert1.add(con_sorted1);
						System.out.println("the " + k + "th flight:  " + con_sorted1);
					}
					boolean b = ServerInterface.INSTANCE.ticket_reserve_no(teamName, convert1);
					System.out.println(b);
				}
				if (transfer_choiceR.equals("2")) {
					System.out.println("\nTransfer once:");
					transfer1 = ServerInterface.INSTANCE.Search_flight_one(teamName, destination,depart_airport,date);
					ArrayList<Transfer1> sorted2 = new ArrayList<>();
					ArrayList<Transfer1> convert2 =new ArrayList<>();
					int k = 0;
					for(int i=0;i<transfer1.size();i++){				
						Transfer1 con_sorted2 =ServerInterface.INSTANCE.time_convert_one(teamName, transfer1.get(i));
						convert2.add(con_sorted2);
						
					}
					sorted2 = Sort.Sort_by_onetransfer(Sort_by, convert2,order);
					for(int i=0;i<convert2.size();i++){				
						k=i+1;
						System.out.println("the "+k+"th flight:  "+sorted2.get(i));				
					}
					boolean b = ServerInterface.INSTANCE.ticket_reserve_one(teamName, sorted2);
					System.out.println(b);
				}
				if (transfer_choiceR.equals("3")) {
					System.out.println("\nTransfer twice:");
					transfer2 = ServerInterface.INSTANCE.Search_flight_two(teamName, destination,depart_airport,date);
					ArrayList<Transfer2> convert3 = new ArrayList<>();
					ArrayList<Transfer2> sorted3 = new ArrayList<>();
					int k =0;
					for(int i=0;i<transfer2.size();i++){				
						Transfer2 con_sorted3 =ServerInterface.INSTANCE.time_convert_two(teamName, transfer2.get(i));
						convert3.add(con_sorted3);
					}
					sorted3 = Sort.Sort_by_twotransfer(Sort_by, convert3,order);
					for(int i=0;i<convert3.size();i++){				
						k=i+1;
						System.out.println("the "+k+"th flight:  "+sorted3.get(i));				
					}
					 boolean b = ServerInterface.INSTANCE.ticket_reserve_two(teamName, sorted3);
					 System.out.println(b);
				}
		}	
			
			
			
	}
	}
}
