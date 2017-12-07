package Tests;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import WiFi_data.WiFi_Beacons_sd;

public class WiFi_Beacons_sdTest {

	@Test
	public void testWiFi_Beacons_sdFile() {
		String s0 = "data/Nvidia_Shield/WigleWifi_20171027163008.csv";
		File f = new File(s0);
		WiFi_Beacons_sd t = new WiFi_Beacons_sd(f);
		t.order();
		t.save_to_csv(s0+"_p2_.csv");
		//fail("Not yet implemented");
	}
	@Test
	public void testWiFi_Beacons_sdFile2() {
		String s0 = "data/OP3T/WigleWifi_20171027162905.csv";
		File f = new File(s0);
		WiFi_Beacons_sd t = new WiFi_Beacons_sd(f);
		t.order();
		t.save_to_csv(s0+"_p2_.csv");
		//fail("Not yet implemented");
	}
	
}
