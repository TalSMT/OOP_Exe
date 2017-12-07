package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import WiFi_data.WiFi_Scan;

public class WiFi_ScanTest {

	@Test
	public void testWiFi_BeaconString() {
		String s = "0a:8d:db:6e:71:6d,888Corp,[WPA2-EAP-CCMP][ESS],2017-10-27 16:16:45,1,-79,32.16766121892341,34.80988155918773,39.018065819940986,3,WIFI";
		WiFi_Scan wi = new WiFi_Scan(s);
		String s2 = wi.toString();
		System.out.println(s2);
//		fail("Not yet implemented");
	}

}
