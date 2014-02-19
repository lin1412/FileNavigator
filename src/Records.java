
/**
 * @author Hang
 * A data structure for holding the 4 informations that going to be returned
 */
public class Records {
	private int FID;
	private String FName;
	private Coords LatCoord;
	private Coords LonCoord;
	
	/**
	 * @param id the Feature ID
	 * @param name the Feature name
	 * @param lat the Primary latitude
	 * @param lon the Primary longitude
	 */
	public Records ( int id, String name, Coords lat, Coords lon){
		FID = id;
		FName = name;
		LatCoord = lat;
		LonCoord = lon;
	}
	
	/**
	 * @return the Feature ID
	 */
	public int getFID(){
		return FID;
	}
	
	/**
	 * @return the Feature name
	 */
	public String getName(){
		return FName;
	}
	
	/**
	 * @return Primary latitude
	 */
	public Coords getLat(){
		return LatCoord;
	}
	
	/**
	 * @return the Primary longitude
	 */
	public Coords getLon(){
		return LonCoord;
	}
	
	/**
	 * @return everything this class contains in a correct format
	 */
	public String toString(){
		return FID + "  " + FName + "  " + LatCoord.toString() + LonCoord.toString();
	}

}
