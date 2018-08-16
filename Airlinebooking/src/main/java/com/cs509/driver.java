package com.cs509;

import com.cs509.entity.Departing;
import com.cs509.entity.Transfer1;
import com.cs509.entity.Transfer2;
import com.cs509.common.Sort_by;
import java.util.ArrayList;
import java.util.Scanner;
import com.cs509.service.ServerInterface;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;


@Component
public class driver {
    @Autowired
    private ServerInterface serverinterface;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("usage: CS509.sample teamName");
            System.exit(-1);
            return;
        }
        while (true) {
            String teamName = args[0];

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
                Sort_by Sort = new Sort_by();
                if(transfer_choice.equals("1")){
                    System.out.println("\nNo transfer:");
                    transfer0 = serverinterface.Search_flight_no(teamName, depart_airport, destination,date);
                    ArrayList<Departing> convert1 =new ArrayList<>();
                    ArrayList<Departing> sorted1 = new ArrayList<>();
                    int k =0;
                    for(int i=0;i<transfer0.size();i++){
                        Departing con_sorted1 =serverinterface.time_convert_no(teamName, transfer0.get(i));
                        convert1.add(con_sorted1);

                    }
                    sorted1 = Sort.Sort_by_no(Sort_by, convert1,order);
                    for(int i=0;i<convert1.size();i++){
                        k=i+1;
                        System.out.println("the "+k+"th flight:  "+sorted1.get(i));
                    }
                    boolean b = serverinterface.ticket_reserve_no(teamName, sorted1);
                    System.out.println(b);
                }
                if(transfer_choice.equals("2")){
                    System.out.println("\nTransfer once:");
                    transfer1 = serverinterface.Search_flight_one(teamName, depart_airport, destination,date);
                    ArrayList<Transfer1> sorted2 = new ArrayList<>();
                    sorted2 = Sort.Sort_by_onetransfer(Sort_by, transfer1,order);
                    ArrayList<Transfer1> convert2 =new ArrayList<>();
                    int k = 0;
                    for(int i=0;i<transfer1.size();i++){
                        Transfer1 con_sorted2 =serverinterface.time_convert_one(teamName, transfer1.get(i));
                        convert2.add(con_sorted2);

                    }
                    sorted2 = Sort.Sort_by_onetransfer(Sort_by, convert2,order);
                    for(int i=0;i<convert2.size();i++){
                        k=i+1;
                        System.out.println("the "+k+"th flight:  "+sorted2.get(i));
                    }
                    boolean b = serverinterface.ticket_reserve_one(teamName, sorted2);
                    System.out.println(b);
                }
                if(transfer_choice.equals("3")){
                    System.out.println("\nTransfer twice:");
                    transfer2 = serverinterface.Search_flight_two(teamName, depart_airport, destination,date);
                    ArrayList<Transfer2> convert3 = new ArrayList<>();
                    ArrayList<Transfer2> sorted3 = new ArrayList<>();
                    int k =0;
                    for(int i=0;i<transfer2.size();i++){
                        Transfer2 con_sorted3 =serverinterface.time_convert_two(teamName, transfer2.get(i));
                        convert3.add(con_sorted3);
                    }
                    sorted3 = Sort.Sort_by_twotransfer(Sort_by, convert3,order);
                    for(int i=0;i<convert3.size();i++){
                        k=i+1;
                        System.out.println("the "+k+"th flight:  "+sorted3.get(i));
                    }
                    boolean b = serverinterface.ticket_reserve_two(teamName, sorted3);
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
                    transfer0 = serverinterface.Search_flight_no(teamName,destination,depart_airport,date);
                    ArrayList<Departing> convert1 =new ArrayList<>();
                    ArrayList<Departing> sorted1 = new ArrayList<>();
                    int k =0;
                    for(int i=0;i<transfer0.size();i++){
                        Departing con_sorted1 =serverinterface.time_convert_no(teamName, transfer0.get(i));
                        convert1.add(con_sorted1);

                    }
                    sorted1 = Sort.Sort_by_no(Sort_by, convert1,order);
                    for(int i=0;i<convert1.size();i++){
                        k=i+1;
                        System.out.println("the "+k+"th flight:  "+sorted1.get(i));
                    }
                    boolean b = serverinterface.ticket_reserve_no(teamName, sorted1);
                    System.out.println(b);
                }
                if (transfer_choice.equals("2")) {
                    System.out.println("\nTransfer once:");
                    transfer1 = serverinterface.Search_flight_one(teamName, depart_airport, destination,date);
                    ArrayList<Transfer1> sorted2 = new ArrayList<>();
                    sorted2 = Sort.Sort_by_onetransfer(Sort_by, transfer1,order);
                    ArrayList<Transfer1> convert2 =new ArrayList<>();
                    int k = 0;
                    for(int i=0;i<transfer1.size();i++){
                        Transfer1 con_sorted2 =serverinterface.time_convert_one(teamName, transfer1.get(i));
                        convert2.add(con_sorted2);

                    }
                    sorted2 = Sort.Sort_by_onetransfer(Sort_by, convert2,order);
                    for(int i=0;i<convert2.size();i++){
                        k=i+1;
                        System.out.println("the "+k+"th flight:  "+sorted2.get(i));
                    }
                    boolean b = serverinterface.ticket_reserve_one(teamName, sorted2);
                    System.out.println(b);
                }
                if (transfer_choice.equals("3")) {
                    System.out.println("\nTransfer twice:");
                    transfer2 = serverinterface.Search_flight_two(teamName, depart_airport, destination,date);
                    ArrayList<Transfer2> convert3 = new ArrayList<>();
                    ArrayList<Transfer2> sorted3 = new ArrayList<>();
                    int k =0;
                    for(int i=0;i<transfer2.size();i++){
                        Transfer2 con_sorted3 =serverinterface.time_convert_two(teamName, transfer2.get(i));
                        convert3.add(con_sorted3);
                    }
                    sorted3 = Sort.Sort_by_twotransfer(Sort_by, convert3,order);
                    for(int i=0;i<convert3.size();i++){
                        k=i+1;
                        System.out.println("the "+k+"th flight:  "+sorted3.get(i));
                    }
                    boolean b = serverinterface.ticket_reserve_two(teamName, sorted3);
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
                    transfer0 = serverinterface.Search_flight_no(teamName, destination, depart_airport, returnDate);
                    ArrayList<Departing> convert1 = new ArrayList<>();
                    ArrayList<Departing> sorted1 = new ArrayList<>();
                    sorted1 = SortR.Sort_by_no(Sort_byR, transfer0, orderR);
                    int k = 0;
                    for (int i = 0; i < sorted1.size(); i++) {
                        k = i + 1;
                        Departing con_sorted1 = serverinterface.time_convert_no(teamName, sorted1.get(i));
                        convert1.add(con_sorted1);
                        System.out.println("the " + k + "th flight:  " + con_sorted1);
                    }
                    boolean b = serverinterface.ticket_reserve_no(teamName, convert1);
                    System.out.println(b);
                }
                if (transfer_choiceR.equals("2")) {
                    System.out.println("\nTransfer once:");
                    transfer1 = serverinterface.Search_flight_one(teamName, destination,depart_airport,date);
                    ArrayList<Transfer1> sorted2 = new ArrayList<>();
                    ArrayList<Transfer1> convert2 =new ArrayList<>();
                    int k = 0;
                    for(int i=0;i<transfer1.size();i++){
                        Transfer1 con_sorted2 =serverinterface.time_convert_one(teamName, transfer1.get(i));
                        convert2.add(con_sorted2);

                    }
                    sorted2 = Sort.Sort_by_onetransfer(Sort_by, convert2,order);
                    for(int i=0;i<convert2.size();i++){
                        k=i+1;
                        System.out.println("the "+k+"th flight:  "+sorted2.get(i));
                    }
                    boolean b = serverinterface.ticket_reserve_one(teamName, sorted2);
                    System.out.println(b);
                }
                if (transfer_choiceR.equals("3")) {
                    System.out.println("\nTransfer twice:");
                    transfer2 = serverinterface.Search_flight_two(teamName, destination,depart_airport,date);
                    ArrayList<Transfer2> convert3 = new ArrayList<>();
                    ArrayList<Transfer2> sorted3 = new ArrayList<>();
                    int k =0;
                    for(int i=0;i<transfer2.size();i++){
                        Transfer2 con_sorted3 =serverinterface.time_convert_two(teamName, transfer2.get(i));
                        convert3.add(con_sorted3);
                    }
                    sorted3 = Sort.Sort_by_twotransfer(Sort_by, convert3,order);
                    for(int i=0;i<convert3.size();i++){
                        k=i+1;
                        System.out.println("the "+k+"th flight:  "+sorted3.get(i));
                    }
                    boolean b = serverinterface.ticket_reserve_two(teamName, sorted3);
                    System.out.println(b);
                }
            }



        }
    }
}
