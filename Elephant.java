
public class Elephant extends Animal{
	private static final double PLANT_FOR_AGE_20_ELEPHANT = 10.0;
	private static final double AGE_EFFECT_RATE = 0.015;
	private double dailyPlantSize = 0;
	
	public Elephant(String animal_name,int age) {
		super (AnimalKind.Elephant,animal_name,age);
		dailyPlantSize = PLANT_FOR_AGE_20_ELEPHANT + PLANT_FOR_AGE_20_ELEPHANT * AGE_EFFECT_RATE * (age - 20);
	}

	public double getDailyMeal() {
		return dailyPlantSize;
	}
	
	public void cleaning() {
		Main.getOutput().write("Cleaning " + getAnimalName() + "'s habitat: Washing the water area.");
	}
	
	public void feeding(int numberOfMeal) {
		Main.getOutput().write(getAnimalName() + " has been given " +  String.format("%.3f", (numberOfMeal * getDailyMeal())) + " kgs assorted fruits and hay" );
	}
}
