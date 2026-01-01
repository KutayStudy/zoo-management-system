public class Penguin extends Animal {
	private static double FISH_FOR_AGE_4_PENGUIN = 3.0;
	private double dailyFishSizeByGram = 0;
	
	public Penguin(String animal_name,int age) {
		super (AnimalKind.Penguin,animal_name,age);
		dailyFishSizeByGram = FISH_FOR_AGE_4_PENGUIN + (0.04 * (age - 4));
	}

	public double getDailyMeal() {
		return dailyFishSizeByGram;
	}
	
	public void cleaning() {
		Main.getOutput().write("Cleaning " + getAnimalName() + "'s habitat: Replenishing ice and scrubbing walls.");
	}
	
	public void feeding(int numberOfMeal) {
		Main.getOutput().write(getAnimalName() + " has been given " + String.format("%.3f",(numberOfMeal * getDailyMeal())) + " kgs of various kinds of fish" );
	}
}
