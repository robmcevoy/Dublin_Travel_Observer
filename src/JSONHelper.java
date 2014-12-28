import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//used as a helper to parse JSON format web service responses
public class JSONHelper {
	
	// returns a String representing the time until the next public transporter arrives at the stop provided
	public String getJSONNextDue(String data){
		
		String duetime="";
		try {
			JSONObject json = new JSONObject(data);
			JSONArray result = json.getJSONArray("results");
			JSONObject firstResult = result.getJSONObject(0);
			duetime = firstResult.getString("duetime");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
		}
		return duetime;
	}

}
