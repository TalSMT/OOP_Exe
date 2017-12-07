package Tests;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import WiFi_data.WiFi_Beacons_md;

public class WiFi_Beacons_mdTest {

	@Test
	public void testWiFi_Beacons_md() {
		String s0 = "data/BM3/Wifi_scans/";
		WiFi_Beacons_md t = new WiFi_Beacons_md(s0);
		t.save_to_csv(s0+"_comb_all_.csv");
		//fail("Not yet implemented");
	//	fail("Not yet implemented");
	}
}
