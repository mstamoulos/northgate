package northgate.northgate.exercise;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class AppTest {

	App app = new App();
	@Test
	public void shouldAnswerWithTrue() {
		assertTrue(true);
	}

	@Test
    public void removeDuplicatesTest() {
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	List<Person> personList = new ArrayList<Person>();
    	try {
			personList.add(Person.builder().firstName("James").lastName("Smith").middleName("Michael").dateOfBirth(df.parse("2000-04-14")).build());
			personList.add(Person.builder().firstName("James").lastName("Smith").middleName("Michael").dateOfBirth(df.parse("2000-04-14")).build());
			personList.add(Person.builder().firstName("Phillip").lastName("Price").middleName("Mitchel").dateOfBirth(df.parse("1974-04-21")).build());
			personList = app.removeDuplicate(personList);
			assertEquals(2, personList.size());	
		} catch (ParseException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}

    }
	
	@Test
    public void filterDobBefore2000() {
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	List<Person> personList = new ArrayList<Person>();
    	try {
			personList.add(Person.builder().firstName("James").lastName("Smith").middleName("Michael").dateOfBirth(df.parse("2000-04-14")).build());
			personList.add(Person.builder().firstName("Phillip").lastName("Price").middleName("Mitchel").dateOfBirth(df.parse("1974-04-21")).build());
			personList.add(Person.builder().firstName("James").lastName("Smith").middleName("Paul").dateOfBirth(df.parse("1999-12-31")).build());
			personList.add(Person.builder().firstName("James").lastName("Baker").middleName("Michael").dateOfBirth(df.parse("1999-12-30")).build());

			personList = app.filterDobBefore(personList, df.parse("1999-12-31"));
			assertEquals(2, personList.size());
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	
    
    }
	
	@Test
    public void sortAscenidngTest() {
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	List<Person> personList = new ArrayList<Person>();
    	try {
    		personList.add(Person.builder().firstName("James").lastName("Smith").middleName("Michael").dateOfBirth(df.parse("2000-04-14")).build());
			personList.add(Person.builder().firstName("Phillip").lastName("Price").middleName("Mitchel").dateOfBirth(df.parse("1974-04-21")).build());
			personList.add(Person.builder().firstName("Emery").lastName("Ibarra").middleName("Flossie").dateOfBirth(df.parse("1986-03-07")).build());
			personList.add(Person.builder().firstName("Eloise").lastName("Dickerson").middleName("Jordan").dateOfBirth(df.parse("1990-02-07")).build());
			personList.add(Person.builder().firstName("James").lastName("Baker").middleName("Michael").dateOfBirth(df.parse("2000-04-14")).build());
			personList = app.sortAscending(personList);
			assertEquals(5, personList.size());	
			assertEquals(Person.builder().firstName("Eloise").lastName("Dickerson").middleName("Jordan").dateOfBirth(df.parse("1990-02-07")).build(), personList.get(0));
			assertEquals(Person.builder().firstName("James").lastName("Smith").middleName("Michael").dateOfBirth(df.parse("2000-04-14")).build(), personList.get(3));

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    
    }
}
