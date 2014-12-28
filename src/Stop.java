// encapsulates the concept of bus, luas or train stop/station

public class Stop{

	private String stopid;
	private String nextDue;
	// string used to identify pub transport operator
	private String operator;
	private String name;
	Location location;
	// distinction needs to be made on whether the stop uses Dub Linked web services or not
	private boolean usesDublinked=true;
	
	Stop(String stopid1, String operator1, String name1){
		stopid = stopid1;
		operator = operator1;
		if(operator.equals("ir")){
			usesDublinked = false;
		}
		name = name1;
		location = new Location(0.0, 0.0);
	}
	
	// Getters and Setters
	
	public String getStopid(){
		return stopid;
	}
	
	public String getNextDue(){
		return nextDue;
	}
	
	public String getOperator(){
		return operator;
	}
	
	public String getStopName(){
		return name;
	}
	
	public Boolean usesDubLinked(){
		return usesDublinked;
	}
	
	public void updateNextDue(String n){
		nextDue = n;
	}
	
	public boolean equals(Object o){
		
		if (o == null) {
            return false;
        }
		Stop tmp;
        try {
            tmp = (Stop) o;
        }
        catch (ClassCastException e) {
            return false;
        }
        if((stopid == tmp.getStopid()) && (operator == tmp.getOperator())){
        	return true;
        }
        else{
        	return false;
        }
	}
}
