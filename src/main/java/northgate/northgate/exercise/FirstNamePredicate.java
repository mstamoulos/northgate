package northgate.northgate.exercise;

import java.util.function.Predicate;

public class FirstNamePredicate implements Predicate<Person> {
	private String startingChar;

	FirstNamePredicate(String startingChar){
		this.startingChar=startingChar;
	}
	@Override
	public boolean test(Person t) {
		
		return t.getFirstName().startsWith(this.startingChar);
	}

}
