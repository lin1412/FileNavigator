import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;


/**
 * 
 * @author Hang Lin
 * This program will performs a basic file parsing and navigation tasks using two inputed files.
 * A GIS record file, and a command file.
 */
public class Project1 {
	public static File GISFile;
	public static File CommandFile;
	public static Processor process;
	static Scanner scan;
	public static int count = 0;

	/**
	 * @param args the input files
	 * @throws Exception FileNotFound exception
	 */
	public static void main(String[] args) throws Exception {
		GISFile = new File(args[0]);
		CommandFile = new File (args[1]);
		process = new Processor(new Parser (GISFile));
		scan = new Scanner(CommandFile);
		
		//setup the output file
		File file = new File("Results.txt");
		file.createNewFile();
		FileWriter FWriter = new FileWriter(file.getAbsoluteFile());
		BufferedWriter writer = new BufferedWriter(FWriter);
		
		//start writing to the output file
		writer.write("GIS data file contains the following records:");
		writer.newLine();
		writer.newLine();
		
		writer.write(process.makeTable());
		writer.newLine();
		
		// start reading in from the command file
		String command = "";
		int off = 0;
		while(scan.hasNext()){
			count++;
			command = scan.next();
			//if the command is 'report', read in the next value and use it to find record at that location
			if ( command.equals("report")){
				off = scan.nextInt();
				writer.write(count + ": " + command + "     " + off);
				writer.newLine();
				writer.write(process.report(off));
				writer.newLine();
			}
			//if command is 'quit', exit loop.
			else if ( command.equals("quit")){
				writer.write(count + ": " + command);
				writer.newLine();
				writer.write("   Exiting.");
				break;
			}
		}
		//close output file and exit program
		writer.close();
	}

}
//On my honor:
//
//- I have not discussed the Java language code in my program with
//anyone other than my instructor or the teaching assistants
//assigned to this course.
//
//- I have not used Java language code obtained from another student,
//or any other unauthorized source, either modified or unmodified.
//
//- If any Java language code or documentation used in my program
//was obtained from another source, such as a text book or course
//notes, that has been clearly noted with a proper citation in
//the comments of my program.
//
//- I have not designed this program in such a way as to defeat or
//interfere with the normal operation of the Automated Grader.
//
//Pledge: On my honor, I have neither given nor received unauthorized
//aid on this assignment.
//
//<Hang Lin>
