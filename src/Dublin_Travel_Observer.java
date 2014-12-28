import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Dublin_Travel_Observer {
	
	public static void main(String [] args){
				
		// no user interface so hard-coded stops are provided
		Stop DublinBusStop = new Stop("1686", "bac", "Castleknock");
		Stop IrishRailStation = new Stop("cnock", "ir", "Castleknock");
		Stop BusEireannStop = new Stop("135001", "be", "Busaras");
		Stop LuasStop = new Stop("luas24", "luas", "St. Stephen's Green");
		
		// declare observable
		RTPIObservable observable = new RTPIObservable(3000);
		
		// add stops to observable
		observable.addStop(DublinBusStop);
		observable.addStop(LuasStop);
		observable.addStop(BusEireannStop);
		observable.addStop(IrishRailStation);

		// declare observers and add the observable to each
		DublinBusDisplay dbDisplay = new DublinBusDisplay(observable);
		observable.addObserver(dbDisplay);
		
		LuasDisplay luasDisplay = new LuasDisplay(observable);
		observable.addObserver(luasDisplay);

		IrishRailDisplay irDisplay = new IrishRailDisplay(observable);
		observable.addObserver(irDisplay);

		BusEireannDisplay ieDisplay = new BusEireannDisplay(observable);
		observable.addObserver(ieDisplay);
		
		// start observable thread
		ExecutorService threadExecutor= Executors.newSingleThreadExecutor();
		threadExecutor.execute(observable);
		
	}
}
