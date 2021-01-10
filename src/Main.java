import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Customer cm = null;
		Manger mg = null;
		Mechanics mec = new Mechanics();

		String first_name;
		String last_name;
		String Dob;
		long number;
		String address;
		int no_cars;
		Map<String, ArrayList<String>> multiMap = new HashMap<>();
		Map<String, String> name_info = new HashMap<>();
		ArrayList<String> cars = new ArrayList<String>();
		Map<String, String> name_car = new HashMap<>();
		ArrayList<String> names = new ArrayList<String>();
		Scanner in = new Scanner(System.in);
		int op;
		while (true) {

			System.out.println("\n\n\n***********WELCOME TO MY CAR WORKSHOP*********");
			System.out.println("Please Read the instructions carefully\n\n");
			System.out.println("If you are a customer press 1:");
			System.out.println("If you are the Manager press 2");
			System.out.println("If you are a mechanic press 3");
			System.out.println("To quit press 9 and hit enter");
			op = in.nextInt();

			if (op == 9) {
				break;
			}
			if (op == 1) {
				System.out.println("Are you a new customer? press 1 to register");
				System.out.println("Press 4 to make appointment");
				System.out.println("Press 5 to check status of your appointment");
				System.out.println("Press 6 to check status of you car repair");
				System.out.println("Press 7 to quit");
				int s = in.nextInt();
				in.nextLine();
				if (s == 7) {
					break;
				}

				if (s == 1) {
					System.out.println("*********Please insert the informations correctly***********");
					System.out.println("First name: ");
					first_name = in.next();
					System.out.println("last name: ");
					last_name = in.next();
					System.out.println("Date ob birth: ");
					Dob = in.next();
					System.out.println("Mobile number: ");
					number = in.nextLong();
					System.out.println("Address: ");
					address = in.next();
					System.out.println("No of cars you have: ");
					no_cars = in.nextInt();

					in.nextLine();

					for (int i = 0; i < no_cars; i++) {
						System.out.println("Car " + (i + 1) + ": ");
						String car = in.nextLine();
						cars.add(car);
					}

					String str_info = "Name: " + first_name + " " + last_name + "     Number: " + number
							+ "     Number of cars: " + no_cars;

					multiMap.put(first_name, cars);
					cm.saveNames_toDatabase(first_name);
					cm.saveInfo_toDatabase(first_name, str_info);

					cm = new Customer(first_name, last_name, Dob, number, address, no_cars, multiMap);
				} else if (s == 4) {
					System.out.println("Your name: ");
					String n = in.nextLine();
					System.out.println("Car list: ");
					if (multiMap.get(n).size() == 0) {
						System.out.println("Null");
					} else {
						System.out.println(multiMap.get(n));
						System.out.println("Please specify which car you want to repair: ");
						String c = in.nextLine();
						System.out.println("What problems you face?");
						String problem = in.nextLine();
						System.out.println(
								"Did you previously repaired your car? If yes write what you did, if no then type 'n'");
						String repared = in.nextLine();
						System.out.println("Data you prefer: ");
						String d = in.nextLine();

						String st = "\nCars name: " + c + "\nDate pref.: " + d + "\nProblems: " + problem
								+ "\npreviusly repaired: " + repared;

						mg.set_appoinment(n, st);

						mg.pending(n);
					}

				} else if (s == 5) {
					System.out.println("\nEnter your name:");
					String n = in.nextLine();

					if (mg.check_if_approved(n)) {
						System.out.println("APPROVED");
					} else if (mg.check_if_declined(n)) {
						System.out.println("DECLINE");
					} else {
						System.out.println("PENDING");
					}

				} else {
					System.out.println("\nEnter your name:");
					String n = in.nextLine();
					if (mg.check_if_completed(n)) {

						System.out.println("Completed\n");
						mec.get_note(n);

					} else {
						System.out.println("Processing");
					}

				}

			}
			if (op == 2) {
				System.out.println("Press 1 to show appointment list");
				System.out.println("Press 2 to show customer's information");
				System.out.println("Press 4 to approve or decline a proposal");
				System.out.println("Press 5 to hire new mechanics");
				System.out.println("Press 6 to assign work to a mechanics");
				int k = in.nextInt();
				if (k == 1) {

					System.out.println("\nHere is the list of appointments:");
					System.out.println("*************************************\n");
					if (mg.showAppointmentList() == 0) {
						System.out.println("There is no appointment");
					}

				} else if (k == 2) {

					cm.getAllCustomerList();

				} else if (k == 4) {

					System.out.println("\nThe list of appointments:\n");
					if (mg.showAppointmentList() == 0) {
						System.out.println("\nThere is no appointment.\n");
					} else {
						System.out.println("\n");
						System.out.println(
								"Enter name and than write A to accept and D to decline\n\nExample:- rukon A or netro D\n\n");
						String na = in.next();
						String ac = in.next();

						if (ac.equals("A")) {
							mg.aprove(na);
							System.out.println(na + " approved");
						} else {
							mg.decline(na);
							System.out.println(na + " declined");
						}
					}

				} else if (k == 5) {
					in.nextLine();
					System.out.println("Enter the name of the mechanic you want to hire: ");
					String mec_name = in.nextLine();
					mec.hire_new_mechanic(mec_name);
					System.out.println(mec_name + " hired.");
				} else {

					in.nextLine();
					mec.getMechanic_list();
					System.out.println("Assign work to a mechanic by writing his name");
					String mec_name = in.nextLine();
					mec.occupy_mec(mec_name);
					System.out.println("Which client's work you want to give to this mechanic? Write his/her name");
					mg.getApprovedList();
					String nn = in.nextLine();
					System.out.println("Mechanic: " + mec_name + "  client: " + nn + "    availability: Occupied");
					mec.occupy_mec(mec_name);
					String client_info = mg.getAppointment(nn);

					// System.out.println(client_info);
					mec.work(mec_name, client_info);
				}

			}
			if (op == 3) {
				System.out.println("Press 1 to check assigned work");
				System.out.println("Press 2 if you are done with your work");
				in.nextLine();
				int k = in.nextInt();
				in.nextLine();
				if (k == 1) {

					System.out.println("Enter your name: ");
					String n = in.nextLine();
					System.out.println("Your work: ");
					mec.getWork(n);
				}
				if (k == 2) {
					System.out.println("Enter your name: ");
					String n = in.nextLine();
					mec.getWork(n);
					System.out.println("\nWhich work you completed? writes clients name");
					String w = in.nextLine();
					mg.complete(w);
					System.out.println("What you did or reapired?");
					String rep = in.nextLine();
					mec.set_note(w, "Mechanic note: " + rep);

				}
			}

		}

	}
}
