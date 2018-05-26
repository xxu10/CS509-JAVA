package departing;
import java.util.Comparator;

public class Departing implements Comparable<Departing>, Comparator<Departing> {
	
		private String mAirplane;              
		private String mFlighttime;             
		private String mNumber;       
		private String mdCode;
		private String dDate;
		private String maCode;
		private String aDate;
		private String Firstclass;
		private String Coach;
		private String Firstbooked;
		private String Coachbooked;
		public Departing() {
			mAirplane = "";
			mFlighttime = "";
			mNumber = "";       
		    mdCode = "";
			dDate = "";
		    maCode = "";
			aDate = "";
			Firstclass = "";
			Firstbooked = "";
			Coach = "";
			Coachbooked = "";
		}
		public Departing (String airplane, String flighttime, String number, String dCode, String ddate, String aCode, String adate, String first, String coach, String first_booked, String coach_booked) {
			
			mAirplane = airplane;
			mFlighttime = flighttime;
			mNumber = number;       
		    mdCode = dCode;
			dDate = ddate;
		    maCode = aCode;
			aDate = adate;
			Firstclass = first;
			Coach = coach;
			Firstbooked = first_booked;
			Coachbooked = coach_booked;
		}
		public String toString() {
			StringBuffer sb = new StringBuffer();
			
			sb.append(mAirplane).append(", ");
			sb.append(mFlighttime).append(", ");
			sb.append(mNumber).append(", ");
			sb.append(mdCode).append(", ");
			sb.append(dDate).append(", ");
			sb.append(maCode).append(", ");
			sb.append(aDate).append(", ");
			sb.append("(").append(Firstclass).append("), ");
			sb.append(Firstbooked).append(", ");
			sb.append("(").append(Coach).append("), ");
			sb.append(Coachbooked).append(", ");
			
			return sb.toString();
		}
		
		public void airplane (String airplane) {
			mAirplane = airplane;
		}
		public String airplane () {
			return mAirplane;
		}
		
		public void flighttime (String flighttime) {
			mFlighttime = flighttime;
		}
		public String flighttime () {
			return mFlighttime;
		}
		public void number (String number) {
			mNumber = number; 
		}
		public String number () {
			return mNumber;
		}
		public void dCode(String dCode){
			 mdCode = dCode;
		}
		public String dCode(){
			return mdCode;
		}
	    public void ddate(String ddate){
		   dDate = ddate;
	    }
		public String ddate(){
			return dDate;
		}
		public void aCode(String aCode){
			maCode=aCode;
		}
		public String aCode(){
			return maCode;
		}
		public void adate(String adate){
			   aDate = adate;
	    }
		public String adate(){
				return aDate;
		}
		public void firstclass(String firstclass){
			Firstclass = firstclass;
		}
		public String firstclass(){
			return Firstclass;
		}
		public void coach(String coach){
			Coach = coach;
		}
		public String coach(){
			return Coach;
		}
		public void firstbooked(String first_booked){
			Firstbooked = first_booked;
		}
		public String firstbooked(){
			return Firstbooked;
		}
		public void coachbooked(String coach_booked){
			Coachbooked = coach_booked;
		}
		public String coachbooked(){
			return Coachbooked;
		}
	    public String getclass(String class_choice){
	    	if (class_choice.equals("f")){
	    		return "FirstClass";
	    	}
	    	else 
	    		return "Coach";
	    }
	    public int compareTo(Departing other) {
			return (this.dDate.compareToIgnoreCase(other.dDate));
		}
	    public int compare(Departing departing1, Departing departing2) {
			return departing1.compareTo(departing2);
		}
	    public boolean equals (Object obj) {
			// every object is equal to itself
			if (obj == this)
				return true;
			
			// null not equal to anything
			if (obj == null)
				return false;
			
			// can't be equal if obj is not an instance of Airplane
			if (!(obj instanceof Departing)) 
				return false;
			
			// if all fields are equal, the Airports are the same
			Departing rhs = (Departing) obj;
			if ((rhs.mAirplane.equals(mAirplane)) &&
					(rhs.mNumber.equals(mNumber)) &&
					(rhs.mdCode == mdCode) &&
					(rhs.dDate == dDate)) {
				return true;
			}
			return false;	
		}
	    
	        public boolean isValid() {
			
			if ((mAirplane == null) || (mAirplane == ""))
				return false;
			
			if (mNumber == null|| (mNumber == ""))
				return false;
			
			// Verify latitude and longitude are within range
			if ((mdCode == null) || (mdCode.length() != 3) ) {
				return false;
			}
			
			return true;
		}
		

}
