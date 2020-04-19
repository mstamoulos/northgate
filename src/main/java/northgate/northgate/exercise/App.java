package northgate.northgate.exercise;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * 1. Remove any duplicates (looking at just first_name , middle_name, last_name
 * , and date_of_birth) - if more than one record has all these fields matching,
 * it is a 'duplicate record' and should be removed from the list
 * 
 * 2. Filter to only include person records that were born before 31st December
 * 1999
 * 
 * 3. Sort the list ascending, by first_name, and then by last_name
 * 
 * 4. print all remaining elements of the list, this should include first_name ,
 * last_name and their age in years
 * 
 */
public class App {
	

	public static void main(String[] args) {
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {

			App app = new App();
			
			byte[] jsonData = App.class.getClassLoader().getResourceAsStream("person test data.json").readAllBytes();
			ObjectMapper objectMapper = new ObjectMapper();

			List<Person> persons = objectMapper.readValue(jsonData, new TypeReference<List<Person>>() {
			});
			
			persons = app.removeDuplicate(persons);
			
			persons = app.filterDobBefore(persons, df.parse("1999-12-31"));
			
			persons = app.sortAscending(persons);
			
			app.printList(persons);
			

		} catch (IOException | ParseException e) {
			System.err.println(e.getMessage());
		}

	}

	public List<Person> removeDuplicate(List<Person> personList) {
		List<Person> rtnList = personList.stream().distinct().map(p -> (Person) p).collect(Collectors.toList());
		return rtnList;
	}

	public List<Person> filterDobBefore(List<Person> personList, Date date) {
		List<Person> rtnList = personList.stream()
				.filter(p -> p.getDateOfBirth().before(date))
				.map(p -> (Person) p)
				.collect(Collectors.toList());
		
		return rtnList;
	}

	public List<Person> sortAscending(List<Person> personList) {
		Comparator<Person> comparator = Comparator.comparing(Person::getFirstName)
				.thenComparing(Person::getLastName);
		
		List<Person> sortedList = personList.stream()
				.sorted(comparator)
				.collect(Collectors.toList());
		return sortedList;
	}

	public void printList(List<Person> personList) {
		ResultPrinter consumer = new ResultPrinter();
		for (Person person : personList) {
			consumer.accept(person);
		}
	}
}
