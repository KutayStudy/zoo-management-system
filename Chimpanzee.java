public class Chimpanzee extends Animal{
	private static final double TOTAL_AMOUNT_AGE_10_EATS = 6.0;
	private static final double AGE_EFFECT_RATE = 0.025;
	private double dailyMeatAndPlantSize = 0;
	
	public Chimpanzee(String animal_name,int age) {
		super (AnimalKind.Chimpanzee,animal_name,age);
		dailyMeatAndPlantSize = TOTAL_AMOUNT_AGE_10_EATS + (( TOTAL_AMOUNT_AGE_10_EATS * AGE_EFFECT_RATE) * (age - 10));
	}

	public double getDailyMeal() {
		return dailyMeatAndPlantSize;
	}
	
	public void cleaning() {
		Main.getOutput().write("Cleaning " + getAnimalName() + "'s habitat: Sweeping the enclosure and replacing branches.");
	}
	
	public void feeding(int numberOfMeal) {
		Main.getOutput().write(getAnimalName() + " has been given " + String.format("%.3f",(numberOfMeal * getDailyMeal()) / 2 ) + " kgs of meat and " +String.format("%.3f",(numberOfMeal * getDailyMeal()) / 2 ) + " kgs of leaves");
	}
}
