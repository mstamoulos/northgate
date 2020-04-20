package northgate.northgate.exercise;

import java.util.function.Predicate;

public class AllPredicate implements Predicate<Person> {

	@Override
	public boolean test(Person t) {

		return true;
	}

}
