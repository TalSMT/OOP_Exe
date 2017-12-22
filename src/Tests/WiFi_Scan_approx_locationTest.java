package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Algorithms.WiFi_Scan_approx_location;
import WiFi_data.WiFi_Scans;

public class WiFi_Scan_approx_locationTest {

	@Test
	public void testApprox_Scans_positions() {
		String csv_training = "data/BM1/comb/_comb4_.csv";
		String csv_testing = "data/BM1/comb/_comb3_.csv";
		WiFi_Scans training = new WiFi_Scans(csv_training);
		WiFi_Scans testing = new WiFi_Scans(csv_testing);
		int max_samples = 4;
		WiFi_Scans Ex2_2_BM1_4_x = WiFi_Scan_approx_location.approx_Scans_positions(training, testing, max_samples);
		Ex2_2_BM1_4_x.save_to_csv("data/BM1/solve_4_3_"+max_samples+".csv");
		
		//fail("Not yet implemented");
	}

}
