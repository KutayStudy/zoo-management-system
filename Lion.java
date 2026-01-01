public class Lion extends Animal {
	private static final double MEAT_FOR_AGE_5_LION = 5.0;
	private double dailyMeatSize = 0;
	
	public Lion(String animal_name,int age) {
		super(AnimalKind.Lion,animal_name,age);
		dailyMeatSize = MEAT_FOR_AGE_5_LION + (0.05 * (age - 5));
	}

	public double getDailyMeal() {
		return dailyMeatSize;
	}
	
	public void cleaning() {
		Main.getOutput().write("Cleaning " + getAnimalName() + "'s habitat: Removing bones and refreshing sand.");
	}
	
	public void feeding(int numberOfMeal) {
		Main.getOutput().write(getAnimalName() + " has been given " + String.format("%.3f",(numberOfMeal * getDailyMeal())) + " kgs of meat" );
	}
	
}
