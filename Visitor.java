public class Visitor extends Person {
	
	public Visitor(String personName,int id) {
		super(PersonType.Visitor,personName,id);
	}
	
	public void visitAnimal(Animal animal, String name) throws AnimalNotFoundException  {
		if (animal == null) {
			Main.getOutput().write(getPersonName() + " tried to register for a visit to " + name + ".");
			throw new AnimalNotFoundException("Error: There are no animals with the name " + name + ".");
		    }
		Main.getOutput().write(getPersonName() + " tried to register for a visit to " + animal.getAnimalName() + ".");
		Main.getOutput().write(getPersonName() + " successfully visited " + animal.getAnimalName() + ".");
	}
	
	public void feedAnimal(Animal animal, int numberOfMeal, boolean isEnough) throws UnauthorizedFeedingException {
		Main.getOutput().write(getPersonName() + " tried to feed " + animal.getAnimalName()+ ".");
		throw new UnauthorizedFeedingException("Error: Visitors do not have the authority to feed animals.");
	}
}
