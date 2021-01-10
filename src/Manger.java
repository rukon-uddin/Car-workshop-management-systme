import java.util.HashMap;
import java.util.Map;

public class Manger {
	static Map<String, String> appointment_list = new HashMap<>();
	static Map<String, String> approved_list = new HashMap<>();
	static Map<String, String> declined_list = new HashMap<>();
	static Map<String, String> pending_list = new HashMap<>();
	static Map<String, String> completed_list = new HashMap<>();
	static Map<String, String> processing_list = new HashMap<>();
	static Map<String, String> delivered_list = new HashMap<>();

	public static void set_appoinment(String n, String c) {
		appointment_list.put(n, c);
	}

	public static int showAppointmentList() {

		for (String i : appointment_list.keySet()) {
			System.out.println("Client Name: " + i + "  " + appointment_list.get(i));
			System.out.println("\n");
		}
		return appointment_list.size();
	}

	public static void aprove(String n) {
		pending_list.remove(n);
		approved_list.put(n, "Approved");
	}

	public static boolean check_if_approved(String n) {
		for (String i : approved_list.keySet()) {
			if (n.equalsIgnoreCase(i)) {
				return true;
			}
		}
		return false;
	}

	public static void decline(String n) {

		pending_list.remove(n);

		declined_list.put(n, "Approved");
	}

	public static boolean check_if_declined(String n) {
		for (String i : declined_list.keySet()) {
			if (n.equalsIgnoreCase(i)) {
				return true;
			}
		}
		return false;
	}

	public static void pending(String name) {
		pending_list.put(name, "Pending");
	}

	public static boolean check_if_pending(String n) {
		for (String i : pending_list.keySet()) {
			if (n.equalsIgnoreCase(i)) {
				return true;
			}
		}
		return false;
	}

	public static void getApprovedList() {
		for (String i : approved_list.keySet()) {
			System.out.println(i + "  " + approved_list.get(i));
			System.out.println("\n");
		}
	}

	public static String getAppointment(String n) {
		for (String i : appointment_list.keySet()) {
			if (n.equalsIgnoreCase(i)) {
				return appointment_list.get(i);
			}

		}
		appointment_list.remove(n);
		processing_list.put(n, "Processin");
		return "Not found";
	}

	public static void complete(String name) {
		completed_list.put(name, "Completed");
		approved_list.remove(name);
	}

	public static boolean check_if_completed(String n) {

		for (String i : completed_list.keySet()) {
			if (n.equalsIgnoreCase(i)) {
				return true;
			}
		}
		return false;

	}

}
