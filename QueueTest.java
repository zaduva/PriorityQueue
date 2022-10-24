import java.io.*;
import java.util.Scanner;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.HashMap;
	
public class QueueTest {
	
	// this is the number of seats available that can be booked for upgrades
	final static int numSeats = 10;
	
	//create PriorityQueue and Comparator
		static PriorityQueue<Passenger> q1 = new PriorityQueue<Passenger>(new Comparator <Passenger>() {
			public int compare(Passenger a, Passenger b) {
				//1) super
				//2) platinum
				//3) gold
				//4) silver
				int aType = typeEqualsNumber(a.membershipType); 
				int bType = typeEqualsNumber(b.membershipType);
				
				//compare the membership statuses
				if (aType < bType) {
					return 1;
				}
				if (bType < aType) {
					return -1;
				}
				return 0;
				
			}
		});
		//create hashmap for confirmation codes
		static HashMap <Integer, Passenger> codeMap = new HashMap<Integer, Passenger>();
		
		static int count;

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			menu();	
			Scanner scnr = new Scanner(System.in);
			int userSelection = -1;
			try {
			userSelection = Integer.parseInt(scnr.nextLine());
			}catch(Exception e) {}
			while (true) {
				userSelection = menuSelection(userSelection);
				menu();	
				try {
				userSelection = Integer.parseInt(scnr.nextLine());
			}catch(Exception e) {}
			}
			//try catch in case user doesnt enter a correct number option in menu()

		}
		
		public static void menu() {
			System.out.println("	  MENU	");
			System.out.println();
			System.out.println("Select 1 to upgrade your flight");
			System.out.println("Select 2 to cancel your flight");
			System.out.println("Select 3 to view queue");
			System.out.println("Select 4 to print out the flight seats");
			System.out.println("Select 0 to exit");
		}
		
		public static int menuSelection (int selection) {
			Scanner scnr = new Scanner(System.in);
			switch(selection) {
			case 1:
				String type = "";
				do {
					System.out.println("Enter your priority status (super, platinum, gold, silver): ");
					type = scnr.nextLine();
				} while(!type.equalsIgnoreCase("super") && !type.equalsIgnoreCase("platinum") && !type.equalsIgnoreCase("gold") && !type.equalsIgnoreCase("silver"));
				
				System.out.println("Enter your name: ");
				String name = scnr.nextLine();
				System.out.println("You have been added to the queue.");
				//count is time, everytime a new passenger has been added, each passengers count will increase
				count++;
				
				//add passenger to queue
				Passenger p = new Passenger (name, type, count, confirmationNumber());
				q1.add(p);
				codeMap.put(p.confirmationCode, p);
				
				System.out.println("Your confirmation number is: " + p.confirmationCode);
				
				break;
			case 2:
				System.out.println("Enter your confirmation number: ");
				int cancelFlight=0;
				
				try {
					cancelFlight = Integer.parseInt(scnr.nextLine());
				}catch(Exception e) {
					
				}
				//cancelledPassenger receives confirmation code stored in hashmap
				Passenger cancelledPassenger = codeMap.get(cancelFlight);
				/*
				 * q1.remove(cancelledPassenger); codeMap.remove(cancelFlight);
				 */
				if (cancelledPassenger != null) {
					q1.remove(cancelledPassenger);
					codeMap.remove(cancelFlight);
					System.out.println("Successfully removed from queue.");
				}
				else {
					System.out.println("Not a valid confirmation number.");
				}
				break;
			
				//iterate through queue to find confirmation code that matches passenger who wishes to cancel
//				Iterator<Passenger> it = q1.iterator();
//				while(it.hasNext()) {
//					Passenger p1 = (Passenger) it.next();
//					if (cancelFlight == (p1.confirmationCode)) {
//						q1.remove(p1);
//						System.out.println("Successfully removed from queue.");
//						break;
					
			case 3:
			printQueue();
			break;
			
			case 4:
				PriorityQueue<Passenger> copySeats = new PriorityQueue<Passenger>(q1);
				int seats_taken = 0;
				int listNumber = 1;
				Passenger polledOut;
				
				System.out.println("There are " + numSeats + " seats available. These passengers have seats: ");
				//print passengers who have seats
				while(!copySeats.isEmpty() && seats_taken < numSeats) {
					polledOut = copySeats.poll();
					System.out.println(listNumber + ". " + polledOut.passengerName);
					seats_taken++;
					listNumber++;
				}
				listNumber = 1;
				System.out.println("Passengers still in queue:  ");
				//print passengers who don't have seats
				while(!copySeats.isEmpty()) {
					polledOut = copySeats.poll();
					System.out.println(listNumber + ". " + polledOut.passengerName);			
					listNumber++;
				}
				break;
				
			
			case 0:
				System.out.println("Goodbye!");
				System.exit(0);
				break;
			}
			return selection;
		}
		
		//set each type of membership status to number so they can be compared
		public static int typeEqualsNumber (String membershipType) {
			int type = 0;
			
			if(membershipType.equals("super") || membershipType.equals("Super") ) {
				type = 4;
			}
			else if(membershipType.equals("platinum") || membershipType.equals("Platinum")) {
				type = 3;
			}
			else if(membershipType.equals("gold") || membershipType.equals("Gold")) {
				type = 2;
			}
			else if(membershipType.equals("silver") || membershipType.equals("Silver")) {
				type = 1;
			}
			return type;
			
		}
		//print out priorityQueue of list of passengers who upgraded
		public static void printQueue() {
			PriorityQueue<Passenger> copy = new PriorityQueue<Passenger>(q1);
			
			while (!copy.isEmpty()) {
				Passenger p = copy.poll();
				System.out.println(p.passengerName + " -" + p.membershipType);
				
			}
			
		}
		//generate random confirmation code for upgraded passenger
		public static int confirmationNumber() {
			int confirmationCode = (int)(Math.random()*100000);
			return confirmationCode;
		}
		

	}


