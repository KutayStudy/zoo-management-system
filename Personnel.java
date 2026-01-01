public class Personnel extends Person{
	
	public Personnel(String personName,int id) {
		super(PersonType.Personnel,personName,id);
	}
	
	public void visitAnimal(Animal animal,String name) throws AnimalNotFoundException {
		 if (animal == null) {
			 Main.getOutput().write(getPersonName() + " attempts to clean " + name + "'s habitat.");
			 throw new AnimalNotFoundException("Error: There are no animals with the name " + name + ".");
		    }
		 Main.getOutput().write(getPersonName() + " attempts to clean " + animal.getAnimalName() + "'s habitat.");
		Main.getOutput().write(getPersonName() + " started cleaning " + animal.getAnimalName() + "'s habitat.");
		animal.cleaning();
	}
	
	public void feedAnimal(Animal animal, int numberOfMeal , boolean isEnough) throws NotEnoughFoodException {
		if (isEnough) {
			Main.getOutput().write(getPersonName() + " attempts to feed " + animal.getAnimalName() + ".");
			animal.feeding(numberOfMeal);
		}
		else {
			Main.getOutput().write(getPersonName() + " attempts to feed " + animal.getAnimalName() + ".");
			throw new NotEnoughFoodException();
		}
	}
}
