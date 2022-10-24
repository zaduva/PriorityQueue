
public class Passenger {
	final int NUMBER_FLIGHT_SEATS = 30;
	String membershipType = "";
	int count = 0;
	String passengerName = "";
	int confirmationCode;
	
	public Passenger(String passengerName, String membershipType, int count, int confirmationCode) {
		this.passengerName = passengerName;
		this.membershipType = membershipType;
		this.count = count;
		this.confirmationCode = confirmationCode;
	}
	
	
	
	
}
