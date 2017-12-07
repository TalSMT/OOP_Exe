package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import WiFi_data.WiFi_Scans;

public class WiFi_ScansTest {

	@Test
	public void testWiFi_ScansString() {
		String file = "data/BM2/comb/_comb_all_.csv";
		WiFi_Scans ws = new WiFi_Scans(file);
		if(ws.size()!=386) {
			System.err.println(ws.size());
			fail("Error constructing a  WiFi_Scans container from file: "+file);
			
		}
	}

}
