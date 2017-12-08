package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import WiFi_data.APs_Scans;
import WiFi_data.WiFi_Scans;

public class APs_ScansTest {

	@Test
	public void testAPs_ScansWiFi_Scans() {
		String csv_file = "data/BM2/comb/_comb_all_.csv";
		WiFi_Scans ws = new WiFi_Scans(csv_file);
		APs_Scans all = new APs_Scans(ws);
		int number_of_macs = 110;
		if(all.size()!=number_of_macs) {
			System.err.println("ERR: APs_Scans: should have "+number_of_macs+" MACs, got: "+all.size());
			fail("Not yet implemented");
		}
	} 

}
