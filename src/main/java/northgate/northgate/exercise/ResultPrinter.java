
package northgate.northgate.exercise;

import java.util.function.Consumer;


public class ResultPrinter implements Consumer<Person> {

	@Override
	public void accept(Person person) {
		
		System.out.print("First Name : " + person.getFirstName());
		System.out.print(" Last Name: " + person.getLastName());
		System.out.println(" Age: " + person.getAge());
		System.out.println("-----------------------------------------------");

		

	}

}
