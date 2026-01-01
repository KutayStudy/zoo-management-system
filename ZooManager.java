import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZooManager {
	private static ArrayList<Lion> lions = new ArrayList<Lion>();
	private static ArrayList<Elephant> elephants = new ArrayList<Elephant>();
	private static ArrayList<Penguin> penguins = new ArrayList<Penguin>();
	private static ArrayList<Chimpanzee> chimpanzees = new ArrayList<Chimpanzee>();
	private static ArrayList<Visitor> visitors = new ArrayList<Visitor>();
	private static ArrayList<Personnel> personnels = new ArrayList<Personnel>();
	private static Map<String, Double> foodStock = new HashMap<>();
	private static Map<Integer, Person> personMap = new HashMap<>();
	private static Map<String, Animal> animalMap = new HashMap<>();
	
	
	//Creates animals list from input file
	 public static void createAnimalsList(String filename) {
		 List<String[]> animals = InputReader.readTxt(filename);
		 	Main.getOutput().write("***********************************");
		 	Main.getOutput().write("***Initializing Animal information***");
	        for (String[] animal : animals) {
	        	switch(animal[0]) {
	        	case "Lion":
	        		Lion lion = new Lion(animal[1],Integer.parseInt(animal[2]));
	        		lions.add(lion);
	        		addAnimal(lion);
	        		Main.getOutput().write("Added new Lion with name " + lion.getAnimalName() + " aged " + lion.getAge() + ".");
	        		break;
	        	case "Elephant":
	        		Elephant elephant = new Elephant(animal[1],Integer.parseInt(animal[2]));
	        		elephants.add(elephant);
	        		addAnimal(elephant);
	        		Main.getOutput().write("Added new Elephant with name " + elephant.getAnimalName() + " aged " + elephant.getAge() + ".");
	        		break;
	        	case "Penguin":
	        		Penguin penguin = new Penguin(animal[1],Integer.parseInt(animal[2]));
	        		penguins.add(penguin);
	        		addAnimal(penguin);
	        		Main.getOutput().write("Added new Penguin with name " + penguin.getAnimalName() + " aged " + penguin.getAge() + ".");
	        		break;
	        	case "Chimpanzee":
	        		Chimpanzee chimpanzee = new Chimpanzee(animal[1],Integer.parseInt(animal[2]));
	        		chimpanzees.add(chimpanzee);
	        		addAnimal(chimpanzee);
	        		Main.getOutput().write("Added new Chimpanzee with name " + chimpanzee.getAnimalName() + " aged " + chimpanzee.getAge() + ".");
	        		break;
	        	}
	        }
	 }
	 
	 //Creates people list from input file
	 public static void createPeopleList(String filename) {
		 List<String[]> people = InputReader.readTxt(filename);
		 Main.getOutput().write("***********************************");
		 Main.getOutput().write("***Initializing Visitor and Personnel information***");
	        for (String[] person : people) {
	        	switch(person[0]) {
	        	case "Visitor":
	        		Visitor visitor = new Visitor(person[1], Integer.parseInt(person[2]));
	        		visitors.add(visitor);
	        		addPerson(visitor);
	        		Main.getOutput().write("Added new Visitor with id " + visitor.getId() + " and name " + visitor.getPersonName() + ".");
	        		break;
	        	case "Personnel":
	        		Personnel personnel = new Personnel(person[1],Integer.parseInt(person[2]));
	        		personnels.add(personnel);
	        		addPerson(personnel);
	        		Main.getOutput().write("Added new Personnel with id " + personnel.getId() + " and name " + personnel.getPersonName() + ".");
	        		break;
	        	}
	        }
	        
	 }
	 
	 public static void createFoodsList(String filename) {
		 List<String[]> foods = InputReader.readTxt(filename);
		 Main.getOutput().write("***********************************");
		 Main.getOutput().write("***Initializing Food Stock***");
		 for (String[] food : foods) {
			 double amountOfFood = (double) Integer.parseInt(food[1]);
			 foodStock.put(food[0],amountOfFood);
			 Main.getOutput().write("There are " + String.format("%.3f",amountOfFood) + " kg of " + food[0] + " in stock");
		 }
	 }
	 
	 public static void addPerson(Person person) {
		 personMap.put(person.getId(), person);
	 }
	 
	 public static Person findPersonById(int id) {
		 return personMap.get(id);
	 }
	 
	 public static void addAnimal(Animal animal) {
		 animalMap.put(animal.getAnimalName(), animal);
	 }
	 
	 public static Animal findAnimalByName(String name) {
		 return animalMap.get(name);
	 }
	 
	 public static void processCommandsList(String filename) {
		 List<String[]> commands = InputReader.readTxt(filename);
		 for (String[] command : commands) {
			 Main.getOutput().write("***********************************");
			 Main.getOutput().write("***Processing new Command***");
			 switch(command[0]) {
			 case "List Food Stock":
				 Main.getOutput().write("Listing available Food Stock:");
				 Main.getOutput().write("Plant: " + String.format("%.3f",foodStock.get("Plant")) + " kgs");
				 Main.getOutput().write("Fish: " + String.format("%.3f",foodStock.get("Fish")) + " kgs");
				 Main.getOutput().write("Meat: " + String.format("%.3f",foodStock.get("Meat")) + " kgs");
				 break;
			 case "Animal Visitation":
				 Person personV = findPersonById(Integer.parseInt(command[1]));
				 Animal animalV = findAnimalByName(command[2]);
				 try {
					personV.visitAnimal(animalV,command[2]);
				} catch (Person.AnimalNotFoundException e) {
					Main.getOutput().write(e.getMessage());
				}
				 break;
			 case "Feed Animal":
				 Person personF = findPersonById(Integer.parseInt(command[1]));
				 Animal animalF = findAnimalByName(command[2]);
				 try {
					 int numberOfMeal = Integer.parseInt(command[3]);
					 double requiredFood = animalF.getDailyMeal() * numberOfMeal;
					 if (personF instanceof Visitor) {
						 try {
							personF.feedAnimal(animalF, numberOfMeal,true);
						} catch (Person.UnauthorizedFeedingException | Person.NotEnoughFoodException e) {
							Main.getOutput().write(e.getMessage());
						}
					 }
					 else {
						 if (animalF instanceof Lion) {
							 if (foodStock.get("Meat") >= requiredFood) {
								 double currentFood = foodStock.get("Meat");
								 boolean isEnough = true;
								 foodStock.put("Meat",currentFood - requiredFood);
								 try {
									personF.feedAnimal(animalF, numberOfMeal,isEnough);
								} catch (Person.UnauthorizedFeedingException | Person.NotEnoughFoodException e) {
									Main.getOutput().write(e.getMessage());
								}
							 }
							 else {
								 boolean isEnough = false;
								 try {
									personF.feedAnimal(animalF, numberOfMeal,isEnough);
								} catch (Person.UnauthorizedFeedingException | Person.NotEnoughFoodException e) {
									Main.getOutput().write("Error: Not enough Meat");
								}
							 }
						 }
						 else if (animalF instanceof Elephant) {
							 if (foodStock.get("Plant") >= requiredFood) {
								 double currentFood = foodStock.get("Plant");
								 boolean isEnough = true;
								 foodStock.put("Plant",currentFood - requiredFood);
								 try {
									personF.feedAnimal(animalF, numberOfMeal,isEnough);
								} catch (Person.UnauthorizedFeedingException | Person.NotEnoughFoodException e) {
									Main.getOutput().write(e.getMessage());
								}
							 }
							 else {
								 boolean isEnough = false;
								 try {
									personF.feedAnimal(animalF, numberOfMeal,isEnough);
								} catch (Person.UnauthorizedFeedingException | Person.NotEnoughFoodException e) {
									Main.getOutput().write("Error: Not enough Plant");
								}
							 }
						 }
						 else if (animalF instanceof Penguin) {
							 if (foodStock.get("Fish") >= requiredFood) {
								 double currentFood = foodStock.get("Fish");
								 boolean isEnough = true;
								 foodStock.put("Fish",currentFood - requiredFood);
								 try {
									personF.feedAnimal(animalF, numberOfMeal,isEnough);
								} catch (Person.UnauthorizedFeedingException | Person.NotEnoughFoodException e) {
									Main.getOutput().write(e.getMessage());
								}
							 }
							 else {
								 boolean isEnough = false;
								 try {
									personF.feedAnimal(animalF, numberOfMeal,isEnough);
								} catch (Person.UnauthorizedFeedingException | Person.NotEnoughFoodException e) {
									Main.getOutput().write("Error: Not enough Fish");
								}
							 }
						 }
						 else if (animalF instanceof Chimpanzee) {
							 if ((foodStock.get("Meat") >= (requiredFood / 2)) && (foodStock.get("Plant") >= (requiredFood / 2))) {
								 boolean isEnough = true;
								 double currentFood1 = foodStock.get("Meat");
								 double currentFood2 = foodStock.get("Plant");
								 foodStock.put("Meat",currentFood1 - (requiredFood / 2));
								 foodStock.put("Plant",currentFood2 - (requiredFood / 2));
								 try {
									personF.feedAnimal(animalF, numberOfMeal,isEnough);
								} catch (Person.UnauthorizedFeedingException | Person.NotEnoughFoodException e) {
									Main.getOutput().write(e.getMessage());
								}
							 }
							 else if (foodStock.get("Plant") >= (requiredFood / 2)) {
								 boolean isEnough = false;
								 try {
									personF.feedAnimal(animalF, numberOfMeal,isEnough);
								} catch (Person.UnauthorizedFeedingException | Person.NotEnoughFoodException e) {
									Main.getOutput().write("Error: Not enough Meat");
								}
							 }
							 else if (foodStock.get("Meat") >= (requiredFood / 2)) {
								 boolean isEnough = false;
								 try {
									personF.feedAnimal(animalF, numberOfMeal,isEnough);
								} catch (Person.UnauthorizedFeedingException | Person.NotEnoughFoodException e) {
									Main.getOutput().write("Error: Not enough Plant");
								}
							 }
						 }
					 }
				 } catch(NumberFormatException e) {
					 Main.getOutput().write("Error processing command: " + command[0] + "," + command[1] + "," + command[2] + "," + command[3]);
					 Main.getOutput().write("Error:For input string: \""+ command[3] + "\"");
				 } catch(NullPointerException e) {
					 if (personF == null) {
					        Main.getOutput().write("Error: There are no visitors or personnel with the id " + command[1]);
					    } 
					 else if (animalF == null) {
					        Main.getOutput().write("Error: There are no animals with the name " + command[2] + ".");
					    } 
					 else {
					        Main.getOutput().write("Error: Unexpected null pointer exception.");
					    }
				 }
				 break;
			 }
		 }
	 }
	 
	 public static class InputReader {
		 //Class for reading input and executing commands in it
		 
		 //Reads a text file and returns list of string arrays
		 public static List<String[]> readTxt(String input) {
		        List<String[]> data = new ArrayList<>();
		        
		        try (BufferedReader br = new BufferedReader(new FileReader(input))) {
		            String line;
		            while ((line = br.readLine()) != null) {
		                String[] parts = line.split(","); 
		                data.add(parts);  
		            }
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		        
		        return data;
		 }
		 
	}
	 
	 
}
