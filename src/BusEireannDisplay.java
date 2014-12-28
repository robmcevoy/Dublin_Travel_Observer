import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

//Observer for RTPI Observable
//used to display real time data for Bus Eireann Bus Stops

public class BusEireannDisplay implements Observer, Display{

	private RTPIObservable observable;
	
	BusEireannDisplay(RTPIObservable observable1){
		observable = observable1;
	}
	
	// display on receiving an update
	public void update(Observable o, Object arg) {
		if(o == observable){
			display();
		}
	}
	
	public void display(){
		// get all Bus Eireann stops currently being observed
		ArrayList<Stop> stops = observable.getBusEireannStops();
		for(int i=0; i < stops.size(); i++){
			Stop tmpStop = stops.get(i);
			// print out stop name and the time that the next Bus Eireann Bus will arrive at that stop
			System.out.printf("%-30s%-30s%-5s%-7s", "Bus Eireann Stop: ",
							tmpStop.getStopName(),
							tmpStop.getNextDue(),
							" min(s)");
			System.out.println();
		}
	}
}
