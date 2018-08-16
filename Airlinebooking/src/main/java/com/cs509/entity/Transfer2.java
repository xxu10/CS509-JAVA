package com.cs509.entity;
import com.cs509.service.ServerInterface;


public class Transfer2 {
	private String mNumber1;       
	private String mdCode1;
	private String dDate1;
	private String maCode1;
	private String aDate1;
	private String Firstclass1;
	private String Coach1;
	private int Firstvalid1;
	private int Coachvalid1;
	private String mNumber2;       
	private String mdCode2;
	private String dDate2;
	private String maCode2;
	private String aDate2;
	private String Firstclass2;
	private String Coach2;
	private int Firstvalid2;
	private int Coachvalid2;
	private String mNumber3;       
	private String mdCode3;
	private String dDate3;
	private String maCode3;
	private String aDate3;
	private String Firstclass3;
	private String Coach3;
	private int Firstvalid3;
	private int Coachvalid3;
	private float avgFprice;
	private float avgCprice;
	
	public Transfer2() {
		mNumber1 = "";       
	    mdCode1 = "";
		dDate1 = "";
	    maCode1 = "";
		aDate1 = "";
		Firstclass1 = "";
		Coach1 = "";
		mNumber2 = "";       
	    mdCode2 = "";
		dDate2 = "";
	    maCode2 = "";
		aDate2 = "";
		Firstclass2 = "";
		Coach2 = "";
		mNumber3 = "";       
	    mdCode3 = "";
		dDate3 = "";
	    maCode3 = "";
		aDate3 = "";
		Firstclass3 = "";
		Coach3 = "";
		Firstvalid1 = 0;
		Coachvalid1 = 0;
		Firstvalid2 = 0;
		Coachvalid2 = 0;
		Firstvalid3 = 0;
		Coachvalid3 = 0;
		avgFprice = 0;
		avgCprice = 0;
	}
	public Transfer2 (String teamName,Departing departing1,Departing departing2,Departing departing3) {
		
		mNumber1 = departing1.number();       
	    mdCode1 = departing1.dCode();
		dDate1 = departing1.ddate();
	    maCode1 = departing1.aCode();
		aDate1 = departing1.adate();
		Firstclass1 = departing1.firstclass();
		Coach1 = departing1.coach();
		Firstvalid1 = ServerInterface.INSTANCE.SeatsValid(teamName, departing1).get(0);
		Coachvalid1 = ServerInterface.INSTANCE.SeatsValid(teamName, departing1).get(1);
		mNumber2 = departing2.number();       
	    mdCode2 = departing2.dCode();
		dDate2 = departing2.ddate();
	    maCode2 = departing2.aCode();
		aDate2 = departing2.adate();
		Firstclass2 = departing2.firstclass();
		Coach2 = departing2.coach();	
		Firstvalid2 = ServerInterface.INSTANCE.SeatsValid(teamName, departing2).get(0);
		Coachvalid2 = ServerInterface.INSTANCE.SeatsValid(teamName, departing2).get(1);
		mNumber3 = departing3.number();       
	    mdCode3 = departing3.dCode();
		dDate3 = departing3.ddate();
	    maCode3 = departing3.aCode();
		aDate3 = departing3.adate();
		Firstclass3 = departing3.firstclass();
		Coach3 = departing3.coach();	
		Firstvalid3 = ServerInterface.INSTANCE.SeatsValid(teamName, departing3).get(0);
		Coachvalid3 = ServerInterface.INSTANCE.SeatsValid(teamName, departing3).get(1);
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("1:");
		sb.append("flight number:");
		sb.append(mNumber1).append(", ");
		sb.append("Departuring airport:");
		sb.append(mdCode1).append(", ");
		sb.append("Departuring date:");
		sb.append(dDate1).append(", ");
		sb.append("Arrival airport:");
		sb.append(maCode1).append(", ");
		sb.append("Arrival date:");
		sb.append(aDate1).append(", ");
		sb.append("Price fot the first class:");
		sb.append("(").append(Firstclass1).append("), ");
		sb.append("FirstClass seat available:");
		sb.append(Firstvalid1).append(", ");
		sb.append("Price fot the coach class:");
		sb.append("(").append(Coach1).append("), ");
		sb.append("Coach seat available:");
		sb.append(Coachvalid1).append(", ");
		sb.append("2:");
		sb.append("flight number:");
		sb.append(mNumber2).append(", ");
		sb.append("Departuring airport:");
		sb.append(mdCode2).append(", ");
		sb.append("Departuring date:");
		sb.append(dDate2).append(", ");
		sb.append("Arrival airport:");
		sb.append(maCode2).append(", ");
		sb.append("Arrival date:");
		sb.append(aDate2).append(", ");
		sb.append("Price fot the first class:");
		sb.append("(").append(Firstclass2).append("), ");
		sb.append("FirstClass seat available:");
		sb.append(Firstvalid2).append(", ");
		sb.append("Price fot the coach class:");
		sb.append("(").append(Coach2).append("), ");
		sb.append("Coach seat available:");
		sb.append(Coachvalid2);
		sb.append("3:");
		sb.append("flight number:");
		sb.append(mNumber3).append(", ");
		sb.append("Departuring airport:");
		sb.append(mdCode3).append(", ");
		sb.append("Departuring date:");
		sb.append(dDate3).append(", ");
		sb.append("Arrival airport:");
		sb.append(maCode3).append(", ");
		sb.append("Arrival date:");
		sb.append(aDate3).append(", ");
		sb.append("Price fot the first class:");
		sb.append("(").append(Firstclass3).append("), ");
		sb.append("FirstClass seat available:");
		sb.append(Firstvalid3).append(", ");
		sb.append("Price fot the coach class:");
		sb.append("(").append(Coach3).append("), ");
		sb.append("Coach seat available:");
		sb.append(Coachvalid3).append(", ");
		sb.append("average price for firstClass:");
		sb.append(avgFprice).append(", ");
		sb.append("average price for coach:");
		sb.append(avgCprice).append(", ");
		return sb.toString();
	}
	public void number1 (String number) {
		mNumber1 = number; 
	}
	public String number1 () {
		return mNumber1;
	}
	public void dCode1(String dCode){
		 mdCode1 = dCode;
	}
	public String dCode1(){
		return mdCode1;
	}
    public void ddate1(String ddate){
	   dDate1 = ddate;
    }
	public String ddate1(){
		return dDate1;
	}
	public void aCode1(String aCode){
		maCode1=aCode;
	}
	public String aCode1(){
		return maCode1;
	}
	public void adate1(String adate){
		   aDate1 = adate;
    }
	public String adate1(){
			return aDate1;
	}
	public void firstclass1(String firstclass){
		Firstclass1 = firstclass;
	}
	public String firstclass1(){
		return Firstclass1;
	}
	public void coach1(String coach){
		Coach1 = coach;
	}
	public String coach1(){
		return Coach1;
	}
	public void firstvalid1(int first_valid){
		Firstvalid1 = first_valid;
	}
	public int firstvalid1(){
		return Firstvalid1;
	}
	public void coachvalid1(int coach_valid){
		Coachvalid1 = coach_valid;
	}
	public int coachvalid1(){
		return Coachvalid1;
	}
	
	public void number2 (String number) {
		mNumber2 = number; 
	}
	public String number2 () {
		return mNumber2;
	}
	public void dCode2(String dCode){
		 mdCode2 = dCode;
	}
	public String dCode2(){
		return mdCode2;
	}
    public void ddate2(String ddate){
	   dDate2 = ddate;
    }
	public String ddate2(){
		return dDate2;
	}
	public void aCode2(String aCode){
		maCode2=aCode;
	}
	public String aCode2(){
		return maCode2;
	}
	public void adate2(String adate){
		   aDate2 = adate;
    }
	public String adate2(){
			return aDate2;
	}
	public void firstclass2(String firstclass){
		Firstclass2 = firstclass;
	}
	public String firstclass2(){
		return Firstclass2;
	}
	public void coach2(String coach){
		Coach2 = coach;
	}
	public String coach2(){
		return Coach2;
	}
	public void firstvalid2(int first_valid){
		Firstvalid2 = first_valid;
	}
	public int firstvalid2(){
		return Firstvalid2;
	}
	public void coachvalid2(int coach_valid){
		Coachvalid2 = coach_valid;
	}
	public int coachvalid2(){
		return Coachvalid2;
	} 
	
	
	public void number3 (String number) {
		mNumber3 = number; 
	}
	public String number3 () {
		return mNumber3;
	}
	public void dCode3(String dCode){
		 mdCode3 = dCode;
	}
	public String dCode3(){
		return mdCode3;
	}
    public void ddate3(String ddate){
	   dDate3 = ddate;
    }
	public String ddate3(){
		return dDate3;
	}
	public void aCode3(String aCode){
		maCode3=aCode;
	}
	public String aCode3(){
		return maCode3;
	}
	public void adate3(String adate){
		   aDate3 = adate;
    }
	public String adate3(){
			return aDate3;
	}
	public void firstclass3(String firstclass){
		Firstclass3 = firstclass;
	}
	public String firstclass3(){
		return Firstclass3;
	}
	public void coach3(String coach){
		Coach3 = coach;
	}
	public String coach3(){
		return Coach3;
	}
	public void firstvalid3(int first_valid){
		Firstvalid3 = first_valid;
	}
	public int firstvalid3(){
		return Firstvalid3;
	}
	public void coachvalid3(int coach_valid){
		Coachvalid3 = coach_valid;
	}
	public int coachvalid3(){
		return Coachvalid3;
	} 
	public String getclass(String class_choice){
    	if (class_choice.equals("f")){
    		return "FirstClass";
    	}
    	else 
    		return "Coach";
    }
	public float getAvgFprice() {
		return avgFprice;
	}
	public void setAvgFprice(float avgFprice) {
		this.avgFprice = avgFprice;
	}
	public float getAvgCprice() {
		return avgCprice;
	}
	public void setAvgCprice(float avgCprice) {
		this.avgCprice = avgCprice;
	}
}
