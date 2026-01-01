import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
	private static OutputManager output;
	
	public static void main(String[] args) {
		if (args.length != 5) {
			System.out.println("You have to write in this syntax: java Main animals.txt persons.txt foods.txt commands.txt output.txt");
			return;
		}
		
		String animals_file = args[0];
		String people_file = args[1];
		String foods_file = args[2];
		String commands_file = args[3];
		String output_file = args[4];
		
		try {
			output = new OutputManager(output_file);
			
			// Read and process the data
			ZooManager.createAnimalsList(animals_file);
			ZooManager.createPeopleList(people_file);
			ZooManager.createFoodsList(foods_file);
			ZooManager.processCommandsList(commands_file);
	        
	        output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static OutputManager getOutput() {
		return output;
	}
	

public static class OutputManager {
	 private PrintWriter outputWriter;
	 
	 public OutputManager(String fileName) throws IOException {
		 this.outputWriter = new PrintWriter(new FileWriter(fileName));
	 }
	 
	 //Writes a line with newline
	 public void write(String msg) {
		 outputWriter.println(msg);
	 }
	 
	 //Writes a line without adding newline
	 public void writewln(String msg) {
		 outputWriter.print(msg);
	 }
	 
	 
	 public void close() {
		 outputWriter.close();
	 }
	 
}


}
