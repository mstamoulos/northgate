package northgate.northgate.exercise;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class LargeJsonApp {
	static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	public static void main(String[] args) {

		LargeJsonApp largeJsonApp = new LargeJsonApp();
		App app = new App();
		//List<Person> personList = largeJsonApp.getPersonList(new AllPredicate());
		List<Person> personList = largeJsonApp.getPersonList(new FirstNamePredicate("A"));
		personList = app.removeDuplicate(personList);

		try {
			personList = app.filterDobBefore(personList, DATE_FORMAT.parse("1999-12-31"));
		} catch (ParseException e) {
			System.err.println(e.getMessage());
		}

		personList = app.sortAscending(personList);

		app.printList(personList);

	}

	public List<Person> getPersonList(Predicate<Person> personPredicate) {
		List<Person> personList = new ArrayList<Person>();
		JsonParser parser;
		try {
			parser = new JsonFactory()
					.createParser(App.class.getClassLoader().getResourceAsStream("person test data.json"));
			String fname = null;
			String lastName = null;
			String dob = null;

			while (parser.nextToken() != JsonToken.END_ARRAY) {
				String token = parser.getCurrentName();

				if ("first_name".equals(token)) {
					parser.nextToken();
					fname = parser.getText();

				}
				if ("last_name".equals(token)) {
					parser.nextToken();
					lastName = parser.getText();

				}
				if ("date_of_birth".equals(token)) {
					parser.nextToken();
					dob = parser.getText();
				}

				if (JsonToken.END_OBJECT == parser.getCurrentToken()) {
					Person p = Person.builder().firstName(fname).lastName(lastName)
							.dateOfBirth(DATE_FORMAT.parse(dob)).build();
					if(personPredicate.test(p)) {
						personList.add(p);

					}

				}

			}
			parser.close();
			return personList;
		} catch (IOException | ParseException e) {
			System.err.println(e.getMessage());
		}
		return personList;

	}
}
