package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Filter.Time_Filter;
import WiFi_data.WiFi_Scan;

public class Time_FilterTest {

	
	@Test
	public void testTest() {
		String start = "12/03/2017 08:37:18";
		String end = "12/03/2017 08:38:10";
		String mid1 = "12/03/2017 08:38:03";
		String mid2 = "12/03/2017 08:37:31";
		String before = "12/03/2017 08:37:10";
		String after = "12/03/2017 08:39:10";
		String ws0 = " 0a:8d:db:6e:71:6d,888Corp,[WPA2-EAP-CCMP][ESS],";
		String ws1 = ",1,-79,32.16766121892341,34.80988155918773,39.018065819940986,3,WIFI";
		WiFi_Scan w1 = new WiFi_Scan(ws0+mid1+ws1); 
		Time_Filter tf = new Time_Filter(start,end);
		if(tf.test(w1)!=true) {
			System.err.println("ERR: got wrong filter test -should be: "+start+" < "+mid1+" < "+end);
			fail("Not yet implemented");
		}
		w1 = new WiFi_Scan(ws0+mid2+ws1); 
		tf = new Time_Filter(start,end);
		if(tf.test(w1)!=true) {
			System.err.println("ERR: got wrong filter test -should be: "+start+" < "+mid2+" < "+end);
			fail("Not yet implemented");
		}
		w1 = new WiFi_Scan(ws0+before+ws1); 
		tf = new Time_Filter(start,end);
		if(tf.test(w1)==true) {
			System.err.println("ERR: got wrong filter test -should fail: as "+mid2+" < "+start);
			fail("Not yet implemented");
		}
		w1 = new WiFi_Scan(ws0+after+ws1); 
		tf = new Time_Filter(start,end);
		if(tf.test(w1)==true) {
			System.err.println("ERR: got wrong filter test -should fail: as "+mid2+" > "+end);
			fail("Not yet implemented");
		}
	}

}
