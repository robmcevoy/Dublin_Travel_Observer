// encapsulates the concept of a location made up of a longitude and latitude

public class Location{
	
	private double longitude;
	private double latitude;
	
	Location(double longitude1, double latitude1){
		longitude = longitude1;
		latitude = latitude1;
	}
	
	public void setLocation(double longitude1, double latitude1){
		longitude = longitude1;
		latitude = latitude1;
	}
	
	public double getLongitude(){
		return longitude;
	}
	
	public double getLatitude(){
		return latitude;
	}
	
	public boolean equals(Object o){
		
		if (o == null) {
            return false;
        }
		
		Location tmp;
        try {
            tmp = (Location) o;
        }
        catch (ClassCastException e) {
            return false;
        }
        
        if((longitude == tmp.longitude) && (latitude == tmp.latitude)){
        	return true;
        }
        else{
        	return false;
        }
	}
}
