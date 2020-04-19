package northgate.northgate.exercise;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class LargeJsonApp {

	public static void main(String[] args) {

		JsonParser parser;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		App app = new App();
		List<Person> personList = new ArrayList<Person>();

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

					personList.add(
							Person.builder().firstName(fname).lastName(lastName).dateOfBirth(df.parse(dob)).build());

				}

			}
			parser.close();
			
			personList = app.removeDuplicate(personList);
			
			personList = app.filterDobBefore(personList, df.parse("1999-12-31"));
			
			personList = app.sortAscending(personList);
			
			app.printList(personList);

		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
