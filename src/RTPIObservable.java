import java.util.ArrayList;
import java.util.Observable;

// Observable that is run as a thread that continuously queries the RTPI and Irish rail web services
// to get the latest data on when a bus, train, luas is arriving to a stop

public class RTPIObservable extends Observable implements Runnable{
	
	private HttpSender hs;
	//rate at which queries are made in seconds
	private int queryRate;
	
	private final String DUBLINKED_URL = 
		"http://www.dublinked.ie/cgi-bin/rtpi/realtimebusinformation?" ;
	private final String IRISH_RAIL_URL = 
		"http://api.irishrail.ie/realtime/realtime.asmx/getStationDataByCodeXML?StationCode=";

	// list of all stops
	private ArrayList<Stop> stops = new ArrayList<Stop>();
	
	RTPIObservable(int queryRate1){
		queryRate = queryRate1;
		hs = new HttpSender();
	}
	
	public void addStop(Stop s){
		stops.add(s);
	}
	
	// get real-time data on a Dub Linked stop in JSON format
	public String getDubLinkedStopNextDue(Stop s){
		String url = DUBLINKED_URL
					+"stopid=" + s.getStopid() +
					"&operator=" + s.getOperator() +
					"&format=json";
		String jsonData = hs.sendGetRequest(url, true);
		JSONHelper helper = new JSONHelper();
		return helper.getJSONNextDue(jsonData);
	}
	
	//get real-time data on a Irish Rail stop in XML format
	public String getIrishRailStopNextDue(Stop s){
		String url = IRISH_RAIL_URL + s.getStopid();
		String xmlData = hs.sendGetRequest(url, true);
		XMLHelper helper = new XMLHelper();
		return helper.getXMLNextDue(xmlData);
	}
	
	
	public ArrayList<Stop> getDublinBusStops(){
		return getStopsByOperator("bac");
	}
	
	public ArrayList<Stop> getLuasStops(){
		return getStopsByOperator("luas");
	}
	
	public ArrayList<Stop> getBusEireannStops(){
		return getStopsByOperator("be");
	}
	
	public ArrayList<Stop> getIrishRailStops(){
		return getStopsByOperator("ir");
	}
	
	// get all stops related to particular operator
	private ArrayList<Stop> getStopsByOperator(String operator){
		
		ArrayList<Stop> tmpStops = new ArrayList<Stop>();
		for(int i=0; i<stops.size(); i++){
			Stop tmp = stops.get(i);
			if(tmp.getOperator().equals(operator)){
				tmpStops.add(tmp);
			}
		}
		return tmpStops;
	}
	
	// method that runs continuously querying the web services
	public void run() {
		while(true){
			String tmpNextDue;
			for(int i=0; i<stops.size(); i++){
				Stop tmp = stops.get(i);
				if(tmp.usesDubLinked()){
					tmpNextDue = getDubLinkedStopNextDue(tmp);
					tmp.updateNextDue(tmpNextDue);
				}
				else{
					tmpNextDue = getIrishRailStopNextDue(tmp);
					tmp.updateNextDue(tmpNextDue);
				}
			}
			// signal that data has changed and notify all observers
			setChanged();
			notifyObservers(this);
			try {
				Thread.sleep(queryRate);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
