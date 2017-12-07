package Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Data_sets.Point3D;
import Filter.Position_Filter_Circle;
import Filter.Position_Filter_Rectangle3D;
import Filter.Time_Filter;
import Filter.filter;
import WiFi_data.WiFi_Scans;
import WiFi_data.WiFi_Beacons_md;

public class MyVectorTest {
	private WiFi_Scans _mv;
	@Before
	public void setUp() throws Exception {
		String s0 = "data/BM1/wifi_logs";
		WiFi_Beacons_md t = new WiFi_Beacons_md(s0);
		_mv = t.convert();
	}
	@Test
	public void testMyVector() {
		int s = 347;
		if(_mv.size()!=s) {
			System.err.println("Size Wrong vector size: "+_mv.size()+"  should be "+s );
			fail("Wrong vector size: "+_mv.size()+"  should be "+s );
		}
	}

	@Test
	public void testPosFilter() {
		double dist = 0.005;
		Point3D cen = new Point3D(32.1678190337,34.8061381649,0);
		filter pf = new Position_Filter_Circle(cen, dist); 
		_mv.filter(pf);
		int s = 49;
		if(_mv.size()!=s) {
			System.err.println("Position Wrong vector size: "+_mv.size()+"  should be "+s );
			fail("Position Wrong vector size: "+_mv.size()+"  should be "+s );
		}
	}
	@Test
	public void testPosFilter2() {
		double dist = 0.01;
		Point3D cen = new Point3D(32.1608190337,34.8061381649,0);
		filter pf = new Position_Filter_Circle(cen, dist); 
		_mv.filter(pf);
		int s = 67;
		if(_mv.size()!=s) {
			System.err.println("Test2: Position Wrong vector size: "+_mv.size()+"  should be "+s );
			fail("Position Wrong vector size: "+_mv.size()+"  should be "+s );
		}
	}
	@Test
	public void testPosFilter3() {
		double d_lat = 0.01, d_lon=0.02, d_alt=100;
		Point3D min = new Point3D(32.1608190337,34.8061381649,0);
		Point3D max = new Point3D(min.x()+d_lat, min.y()+d_lon,min.z()+d_alt);
		filter pf = new Position_Filter_Rectangle3D(min, max); 
		_mv.filter(pf);
		int s = 183;
		if(_mv.size()!=s) {
			System.err.println("Test3: Position Wrong vector size: "+_mv.size()+"  should be "+s );
			fail("Position Wrong vector size: "+_mv.size()+"  should be "+s );
		}
	}
	@Test
	public void testTimeFilter() {
		String start = "2017-12-01 10:43:06";
		String end = "2017-12-01 11:06:24";
		filter tf = new Time_Filter(start, end);
		_mv.filter(tf);
		int s = 297;
		if(_mv.size()!=s) {
			System.err.println("Time Filter Wrong vector size: "+_mv.size()+"  should be "+s );
			fail("Time Filter Wrong vector size: "+_mv.size()+"  should be "+s );
		}
	}
}
