import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 * @author Hang
 * Thsi class parse the GIS input file, and uses RAF to look for the specific record at specific offset.
 */
public class Parser {
	
	RandomAccessFile raf;
	Scanner scan;
	private Records record;
	private int length = 0;
	private int line = 0;
	
	/**
	 * @param f the input GIS record file
	 */
	public Parser (File f){
		
		try {
			raf = new RandomAccessFile(f, "r");
			scan = new Scanner(f);
			length = (int) raf.length();
			
			//get total number of lines/record in the GIS record file
			while(scan.hasNextLine()){
				line ++;
				scan.nextLine();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param offSet the location of record wanted
	 * @return the record at the specific offset
	 * @throws Exception FileNotFound exception
	 */
	public Records getRecord(int offSet) throws Exception{
		raf.seek(offSet);
		String[] str = raf.readLine().split("\\|");
		
		//construct new Coords class and use for construction of new record class
		Coords lat = new Coords(Integer.parseInt(str[7].substring(0, 2)), 
						Integer.parseInt(str[7].substring(2, 4)), 
						Integer.parseInt(str[7].substring(4, 6)), 
						str[7].substring(6));
		Coords lon = new Coords(Integer.parseInt(str[8].substring(0, 3)), 
						Integer.parseInt(str[8].substring(3, 5)), 
						Integer.parseInt(str[8].substring(5, 7)), 
						str[8].substring(7));
		record = new Records(Integer.parseInt(str[0]), str[1], lat, lon);
		
		return record;
	}
	
	/**
	 * @return the total number of offsets in the file
	 */
	public int getLength(){
		return length;
	}
	
	/**
	 * @return the total number of line/records in file
	 */
	public int getLineNum(){
		return line;
	}
	
	/**
	 * @param num the lineNumber
	 * @return the offset location of a specific line number
	 * @throws IOException FileNotFound exception
	 */
	public int getOffset(int num) throws IOException{
		raf.seek(0);
		for ( int i = 0; i < num; i++){
			raf.readLine();
		}
		return (int)raf.getFilePointer();
	}
	
	/**
	 * @param offset the location of the record info wanted
	 * @return the feature ID at that specific offset
	 * @throws IOException FileNotFound exception
	 */
	public int getID(int offset) throws IOException{
		raf.seek(offset);
		String[] str = raf.readLine().split("\\|");
		return Integer.parseInt(str[0]);
		
	}

	/**
	 * @param offset the location in the file
	 * @return whether if the offset is at beginning of record or not
	 * @throws IOException FileNotFound exception
	 */
	public boolean atStart(int offset) throws IOException {
		raf.seek(offset-1);
		raf.readLine();
		int LineStart = (int) raf.getFilePointer();
		if( offset == LineStart){
			return true;
		}
			return false;
	}
	
}
