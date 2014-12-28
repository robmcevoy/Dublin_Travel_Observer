import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;

// used as a helper to parse XML format web service responses
public class XMLHelper {

	// returns a String representing the time until the next public transporter arrives at the stop provided
	public String getXMLNextDue(String data){
		
		String dueIn = ""; 
		SAXBuilder saxBuilder = new SAXBuilder();
		try {
	
			Document doc = saxBuilder.build(new StringReader(data));
			Element rootNode = doc.getRootElement();
			Namespace namespace = rootNode.getNamespace();
		    List list = rootNode.getChildren("objStationData", namespace);
		    Element node = (Element)list.get(0);
		    dueIn = node.getChildText("Duein", namespace);
		  
		} catch (JDOMException e) {
			System.out.println(e.toString());
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		return dueIn;
	}
}
