/**
 * This class represents a simple class for converting a wifi data - with time & position info
 * into a KML (google earth) file. 
 * Based on the JAK api: https://labs.micromata.de/projects/jak.html
 */
package Data_sets;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;

import WiFi_data.WiFi_Beacons_md;
import WiFi_data.WiFi_Scan;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.Icon;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.KmlFactory;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Style;
public class WiFi_Records_2_KML {
	//private WiFi_Beacons_md _data;
	
	/** this is the main file converter: it takes a time based set of wifi_scans and convert it to a KML file
*/
	public static void convert(WiFi_Beacons_md records, String file) {
		Kml kml = KmlFactory.createKml();
		// Create <Placemark> and set values.
		Document doc = kml.createAndSetDocument().withName("file").withOpen(true);

		// create a Folder
		Folder folder = doc.createAndAddFolder();
		folder.withName("Continents with Earth's surface").withOpen(true);
		add_all(records, doc, folder);
		
		try {
			File tt = new File (file);
			kml.marshal();
			kml.marshal(tt);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}         
		
	}
	private static void add_all(WiFi_Beacons_md records, Document kml, Folder f) {
		MyVector mv = records.convert();
		
		Iterator<WiFi_Scan> itr = mv.iterator();
		int i=0;
		while(itr.hasNext()) {
			WiFi_Scan w = itr.next();
			create_placemark(w,kml,f, i);
			i++;
		}
	}
	private static void create_placemark(WiFi_Scan w, Document kml, Folder f, int ind) {
		Point3D pos = w.get_pos();
		String pos_kml = ""+pos.y()+","+pos.x()+","+pos.z();
		String strongest_AP = w.get(0).get_ssid();
		
		createPlacemarkWithChart(kml, f, pos.y(), pos.x(), strongest_AP);
	}
		/*
	
	*/
	/**
	 * This code is Extreamly ugly - make sure you improved it!!
	 * @param document
	 * @param folder
	 * @param longitude
	 * @param latitude
	 * @param continentName
	 */
	private static void createPlacemarkWithChart(Document document, Folder folder, double longitude, double latitude, 
		    String continentName) {

		//	int remainingLand = 100 - coveredLandmass;
			Icon icon = new Icon()
			    .withHref("http://chart.apis.google.com/chart?chs=380x200&chd=t:"  + "," + "&cht=p&chf=bg,s,ffffff00");
			Style style = document.createAndAddStyle();
			style.withId("style_" + continentName) // set the stylename to use this style from the placemark
			    .createAndSetIconStyle().withScale(1.0).withIcon(icon); // set size and icon
			
			style.createAndSetLabelStyle().withColor("ff43b3ff").withScale(1.0); // set color and size of the continent name

			Placemark placemark = folder.createAndAddPlacemark();
			// use the style for each continent
			placemark.withName(continentName)
			    .withStyleUrl("#style_" + continentName)
			    // 3D chart imgae
			    .withDescription(
			        "<![CDATA[<img src=\"http://chart.apis.google.com/chart?chs=430x200&chd=t:" + "&cht=p3&chl=" + continentName + "|remaining&chtt=Earth's surface\" />")
			    // coordinates and distance (zoom level) of the viewer
			    .createAndSetLookAt().withLongitude(longitude).withLatitude(latitude).withAltitude(0).withRange(12000000);
			
			placemark.createAndSetPoint().addToCoordinates(longitude, latitude); // set coordinates
		}
}
