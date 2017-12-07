package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Data_sets.WiFi_Records_2_KML;
import WiFi_data.WiFi_Beacons_md;

public class WiFi_Records_2_KMLTest {

	@Test
	public void testConvert() {
		String dir = "data/BM2/Wifi_scans";
		String output_file = "out.kml";
		WiFi_Beacons_md records = new WiFi_Beacons_md(dir);
		WiFi_Records_2_KML.convert(records, output_file);
		
		//fail("Not yet implemented");
	}

}
