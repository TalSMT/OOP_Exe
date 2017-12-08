package Tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

import WiFi_data.AP_Scan;
import WiFi_data.AP_Scans;
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
			fail("");
		}
	} 
	@Test
	public void testPrint() {
		String csv_file = "data/BM2/comb/_comb_all_.csv";
		WiFi_Scans ws = new WiFi_Scans(csv_file);
		APs_Scans all = new APs_Scans(ws);
		HashMap<String, AP_Scans> map = all.get_HashMap();
		Set<String> keys = map.keySet();
		Iterator<String> iter = keys.iterator();
		int count = 0;
		int i1=0;
		while(iter.hasNext()) {
			String key = iter.next();
			AP_Scans aps = all.get(key);
			System.out.println(i1+") ************ MAC: "+aps.get_MAC()+" ************");
			Iterator<AP_Scan> itr2 = aps.iterator();
			int i=0;
			i1++;
			while(itr2.hasNext()) {
				AP_Scan a = itr2.next();
				System.out.println(i+") "+a.toString());
				i++;
				count++;
			}
		}
		int total = 1621;
		if(count!=total) {
			System.err.println("ERR: AP_Scan: should have "+total+" MACs, got: "+count);
			fail("");
		}
	}
	

}
