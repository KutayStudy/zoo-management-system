
abstract class Animal {
	private String animal_name;
	private int age;
	private AnimalKind animal_kind;
	
	enum AnimalKind {Lion,Elephant,Penguin,Chimpanzee};
	
	public Animal(AnimalKind animal_kind,String animal_name,int age) {
		
		if (animal_kind == null) {
            throw new IllegalArgumentException("Animal kind cannot be null.");
        }
        if (animal_name == null || animal_name.trim().isEmpty()) {
            throw new IllegalArgumentException("Animal name cannot be null or empty.");
        }
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative.");
        }
		
		this.animal_kind = animal_kind;
		this.animal_name = animal_name;
		this.age = age;
	}
	
	public abstract void feeding(int n);
	
	public abstract void cleaning();

	public abstract double getDailyMeal();
	
	public String getAnimalName() {
		return animal_name;
	}
	
	public int getAge() {
		return age;
	}
	
	public String getAnimalKindByString() {
		return animal_kind.toString();
	}
}
