package Tests;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

import Algorithms.MAC_approx_location;
import Tools.Point3D;
import Tools.Wighted_Point3D;
import WiFi_data.AP_Scan;
import WiFi_data.WiFi_Scans;

public class MAC_approx_locationTest {

	@Test
	/**
	 * This test simply compare the java implementation if weighted center point and the Excel one, see:
	 * -30	650	35.208	32.103
		-40	660	35.205	32.105
		-90	680	35.307	32.103
		655.3526970954	35.2135643154	32.1036721992

	 */
	public void testWeightd_Center_Excel_like(){
		double w1 = -30;
		w1 = MAC_approx_location.compute_weight(w1);
		Wighted_Point3D p1 = new Wighted_Point3D(32.103, 35.208, 650,w1);
		double w2 = -40;
		w2 = MAC_approx_location.compute_weight(w2);
		Wighted_Point3D p2 = new Wighted_Point3D(32.105, 35.205, 660,w2);
		double w3 = -90;
		w3 = MAC_approx_location.compute_weight(w3);
		Wighted_Point3D p3 = new Wighted_Point3D(32.103, 35.307, 680,w3);
		double w4 = -91;
		w4 = MAC_approx_location.compute_weight(w4);
		Wighted_Point3D p4 = new Wighted_Point3D(32.103, 35.307, 680,w4);
		
		Point3D expect = new Point3D(32.1036721992, 35.2135643154, 655.3526970954);
		ArrayList<Wighted_Point3D> wps = new ArrayList<Wighted_Point3D>();
		wps.add(p1); wps.add(p2); wps.add(p3);wps.add(p4);
		Point3D wc3 = MAC_approx_location.weighted_center(wps, 3);
		Point3D wc2 = MAC_approx_location.weighted_center(wps, 2);
		Point3D wc4 = MAC_approx_location.weighted_center(wps, 4);
		
		double d3 = wc3.distance3D(expect);
		if(d3>0.000000001) {
			System.err.println("Err: computing weighted center point (Excel like), expected: "+expect+"    got: "+wc3+"  2D dst: "+d3);
			System.err.println("dx: "+(wc3.x()-expect.x())+"   dy: "+(wc3.y()-expect.y())+"    dz: "+(wc3.z()-expect.z()));
			fail();
		}
		double d23 = wc3.distance2D(wc2);
		double d43 = wc3.distance2D(wc4);
		if(d23<0.001) {
			System.err.println("Err: wc3 and wc2 should not be that close!");
			fail();
		}
		if(d43<0.001) {
			System.err.println("Err: wc3 and wc4 should not be that close!");
			fail();
		}
	}
	
	@Test
	public void testWeighted_centerString() {
		String csv_file = "data/BM2/comb/_comb_all_.csv";
		WiFi_Scans ws = new WiFi_Scans(csv_file);
		ArrayList<AP_Scan> algo1_solution = MAC_approx_location.weighted_center(csv_file,1);
		
		Iterator<AP_Scan> iter = algo1_solution.iterator();
		int i=0;
		while(iter.hasNext()) {
			AP_Scan c = iter.next();
//			System.out.println(i+" , "+c.toString());
			i++;
		}
		int total = 110;
		if(i!=total) {
			System.err.println("ERR: Algorithm1: should have solve "+total+" MACs' positions, got: "+i);
			fail("");
		}
	}

	@Test
	public void testCompute_weight() {
		double[] sigs ={-30, -40,-90};
		int size = sigs.length;
		double[] ws = new double[size];
		ws[0] = 0.0011111111;
		for(int i=1;i<size;i++) {
			ws[i] = 1/(sigs[i]*sigs[i]);
			
		}
		for(int i=0;i<size;i++) {
			double expect = MAC_approx_location.compute_weight(sigs[i]);
			if(Math.abs(ws[i]-expect)>0.1) {
				System.err.println("ERR: RSSI --> wight is not using a power of (-2)!, sig: "+sigs[i]+"  expect: "+ws[i]+"  got: "+expect);
				fail();
			}
		}
		
	}

}
