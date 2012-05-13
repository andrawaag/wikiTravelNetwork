import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public class parseAirport {

	/**
	 * @param args
	 * @return 
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 */
	public static String parseWikiEntry(String urlAirport) throws ParserConfigurationException, SAXException, IOException{

		System.out.println("In function");
		org.jsoup.nodes.Document doc = Jsoup.connect(urlAirport).get();
		String title = doc.title();
		Elements tables = doc.select("table");
		for (Element table : tables){
			int correctTable = -1;
			Elements records = table.select("tr");
			for (Element record : records){
				Elements fields = record.children();
				if (((fields.get(0).nodeName().equals("th")) && (fields.get(0).text().equals("Airlines")))){
					correctTable = 0;
				}
				if (correctTable == 0) {

					for (String destination : fields.get(1).text().split(",")){
						System.out.println(fields.get(0).text() + ": Amsterdam -- "+ destination); 
					}

				}
			}
		}
		return null;
	}

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
		String urlAirport = "http://en.wikipedia.org/wiki/Amsterdam_Airport_Schiphol";
		System.out.println("Start");
		parseWikiEntry(urlAirport);
	}

}
