package airplane;

import java.util.Comparator;


public class Airplane implements Comparable<Airplane>, Comparator<Airplane>{
	private String mManufacturer;              // Full name of the manufacturer
	private String mModel;              // Model of the plane
	private int mFirstclass;          // maximum number of first class seats
	private int mCoach;         //  maximum number of coach seats
	
	public Airplane() {
		mManufacturer = "";
		mModel = "";
		mFirstclass = 0;
		mCoach = 0;
	}
	public Airplane (String manufacturer, String model, int firstclassseat, int coachseat) {
		
		mManufacturer = manufacturer;
		mModel = model;
		mFirstclass = firstclassseat;
		mCoach = coachseat;
	}
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append(mManufacturer).append(", ");
		sb.append(mModel).append(", ");
		sb.append("(").append(mFirstclass).append(", ");
		sb.append(mCoach).append("), ");
		return sb.toString();
	}
	
	public void manufacturer (String manufacturer) {
			mManufacturer = manufacturer;
	}
	public String manufacturer () {
		return mManufacturer;
	}
	
	public void model (String model) {
			mModel = model;
	}
	public String model () {
		return mModel;
	}
	public void firstclassseat (int firstclassseat) {
			mFirstclass = firstclassseat;
	}
	public int firstclassseat() {
		return mFirstclass;
	}
	public void coachseat (int coachseat) {
		mCoach = coachseat;
    }
    public int coachseat() {
	return mCoach;
    }
    
    public int compareTo(Airplane other) {
		return (this.mModel.compareToIgnoreCase(other.mModel))&(this.mManufacturer.compareToIgnoreCase(other.mManufacturer));
	}
    public int compare(Airplane airplane1, Airplane airplane2) {
		return airplane1.compareTo(airplane2);
	}
    public boolean equals (Object obj) {
		// every object is equal to itself
		if (obj == this)
			return true;
		
		// null not equal to anything
		if (obj == null)
			return false;
		
		// can't be equal if obj is not an instance of Airplane
		if (!(obj instanceof Airplane)) 
			return false;
		
		// if all fields are equal, the Airports are the same
		Airplane rhs = (Airplane) obj;
		if ((rhs.mManufacturer.equals(mManufacturer)) &&
				(rhs.mModel.equals(mModel)) &&
				(rhs.mFirstclass == mFirstclass) &&
				(rhs.mCoach == mCoach)) {
			return true;
		}
		return false;	
	}
    
        public boolean isValid() {
		
		if ((mManufacturer == null) || (mManufacturer == ""))
			return false;
		
		if (mModel == null|| (mModel == ""))
			return false;
		
		// Verify latitude and longitude are within range
		if ((mFirstclass == 0) || (mCoach == 0 ) ) {
			return false;
		}
		
		return true;
	}
	
}
