import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Mechanics {
	static Map<String, String> availability = new HashMap<>();
	static ArrayList<String> mechanic_list = new ArrayList<>();
	static Map<String, String> assigned_work = new HashMap<>();
	static Map<String, String> repair_note = new HashMap<>();

	public Mechanics() {
		mechanic_list.add("jhon");
		mechanic_list.add("David");
		availability.put("jhon", "Free");
		availability.put("David", "Free");
	}

	public static void hire_new_mechanic(String name) {
		mechanic_list.add(name);
		availability.put(name, "Free");
	}

	public static void getMechanic_list() {

		for (String i : availability.keySet()) {
			System.out.println(i + "   " + availability.get(i));
		}

	}

	public static void occupy_mec(String name) {
		availability.put(name, "Occupied");
	}

	public static void work(String name, String info) {
		assigned_work.put(name, info);
	}

	public static void getWork(String n) {
		System.out.println(assigned_work.get(n));
	}

	public static void set_note(String n, String note) {
		repair_note.put(n, note);
	}

	public static void get_note(String n) {
		System.out.println(repair_note.get(n));
	}

}
