public abstract class Person {	
	private String personName;
	private int id;
	private PersonType personType;
	
	enum PersonType {Personnel,Visitor};
	
	public Person(PersonType personType,String personName,int id) {
		this.personType = personType;
		this.personName = personName;
		this.id = id;
	}
	
	public String getPersonName() {
		return personName;
	}
	
	public int getId() {
		return id;
	}
	
	public abstract void visitAnimal(Animal animal, String name) throws AnimalNotFoundException ;
	
	public abstract void feedAnimal(Animal animal, int numberOfMeals, boolean isEnough) throws Person.UnauthorizedFeedingException, Person.NotEnoughFoodException;
	
	public static class NotEnoughFoodException extends Exception {
		private static final long serialVersionUID = 1L;
		public NotEnoughFoodException() {
		}
		public NotEnoughFoodException(String message) {
			super(message);
		}
	}
	
	public static class UnauthorizedFeedingException extends Exception {
		private static final long serialVersionUID = 1L;
		public UnauthorizedFeedingException() {
		}
	    public UnauthorizedFeedingException(String message) {
	        super(message);
	    }
	}
	
	public static class PersonNotFoundException extends Exception {
		private static final long serialVersionUID = 1L;
		public PersonNotFoundException() {
		}
		public PersonNotFoundException(String message) {
			super(message);
		}
	}
	
	public static class AnimalNotFoundException extends Exception {
		private static final long serialVersionUID = 1L;
		public AnimalNotFoundException() {
		}
		public AnimalNotFoundException(String message) {
			super(message);
		}
	}
}

