import java.io.IOException;

/**
 * @author Hang
 * This class handle the interaction between the Main and Parser class, 
 * and the return statement of the Parser class.
 */
public class Processor {
	
	private Parser par;
	private String table = "";
	private int off;
	private int ID;
	
	/**
	 * @param p the Parser this class will be handling
	 */
	public Processor(Parser p){
		par = p;
	}
	
	/**
	 * @return a string representation of the table with just the offset and feature ID
	 * @throws IOException FileNotFound exception
	 */
	public String makeTable() throws IOException{
		for ( int i = 1; i < par.getLineNum(); i++){
			off = getOff(i);
			ID = getID(off);
			table += "" + off + "   " + ID + "\n";
		}
		return table;
	}
	
	/**
	 * @param i the line number
	 * @return the offset of the specific line/record
	 * @throws IOException FileNotFound exception
	 */
	public int getOff(int i) throws IOException{
		return par.getOffset(i);
	}
	
	/**
	 * @param i the offset location
	 * @return the feature ID at that location
	 * @throws IOException FileNotFound exception
	 */
	public int getID(int i) throws IOException{
		return par.getID(i);
	}
	
	/**
	 * @param offset the location of the record wanted
	 * @return the record at the location if it exits and offset follows regulations
	 * @throws Exception FileNotFound exception
	 */
	public String report(int offset) throws Exception{

		if ( offset <= 0 ){
			return "   Offset is not positive.";
		}
		else if (offset > par.getLength()){
			return "   Offset too large.";
		}
		else if(!par.atStart(offset)){
			return "   Unaligned offset.";
		}
		else
			return "   " + par.getRecord(offset).toString();
	}
}

