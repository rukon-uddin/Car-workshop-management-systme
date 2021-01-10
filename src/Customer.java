import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Customer {

	static private String first_name;
	static private String last_name;
	static private String Dob;
	static private long number;
	static private String address;
	static private int no_cars;
	static Map<String, ArrayList<String>> car_list = new HashMap<>();
	static ArrayList<String> names = new ArrayList<String>();
	static Map<String, String> name_info = new HashMap<>();

	public Customer(String f, String l, String dob, long num, String add, int no_car,
			Map<String, ArrayList<String>> ap) {
		this.first_name = f;
		this.last_name = l;
		this.Dob = dob;
		this.number = num;
		this.address = add;
		this.no_cars = no_car;
		this.car_list = ap;
	}

	static public void getInfo() {
		System.out.println("Name: " + first_name + " " + last_name);
		System.out.println("Date of birth: " + Dob);
		System.out.println("Mobile number: " + number);
		System.out.println("Address: " + address);
		System.out.println(
				"The customer has " + car_list.get(last_name).size() + " cars they are: " + car_list.get(last_name));
	}

	static public void carNames(String n) {
		System.out.println("Cars: " + car_list.get(n));
	}

	static public void saveNames_toDatabase(String name) {
		names.add(name);
	}

	static public void saveInfo_toDatabase(String name, String info) {
		name_info.put(name, info);
	}

	static public void getInfo_fromDatabase(String n) {

		for (String i : names) {
			if (n.equalsIgnoreCase(i)) {
				System.out.println(name_info.get(i));
				break;
			}
		}

	}

	static public boolean findName(String n) {
		for (String i : names) {
			if (n.equalsIgnoreCase(i)) {
				return true;
			}
		}
		return false;
	}

	static public int getAllCustomerList() {
		System.out.println("\n");
		if (names.size() == 0) {
			System.out.println("No customers.");
			return names.size();

		} else {
			System.out.println("Customers inforamations: \n");

			for (String i : names) {
				System.out.println(name_info.get(i));
			}
			return names.size();
		}

	}

}
