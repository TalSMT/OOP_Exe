package Tests;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Filter.Not_Filter;
import Filter.Or_Filter;
import Filter.Position_Filter_Circle;
import Filter.Position_Filter_Rectangle3D;
import Filter.Time_Filter;
import Filter.filter;
import Tools.Point3D;
import WiFi_data.WiFi_Scans;
import WiFi_data.WiFi_Beacons_md;

public class Filter_WiFi_Scans_Test {
	private WiFi_Scans _mv;
	@Before
	public void setUp() throws Exception {
		String s0 = "data/BM1/Wifi_scans";
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
	@Test
	public void testFilterSave() throws IOException, ClassNotFoundException {
		String start = "2017-12-01 10:43:06";
		String end = "2017-12-01 11:06:24";
		filter tf = new Time_Filter(start, end);
		double d_lat = 0.01, d_lon=0.02, d_alt=100;
		Point3D min = new Point3D(32.1608190337,34.8061381649,0);
		Point3D max = new Point3D(min.x()+d_lat, min.y()+d_lon,min.z()+d_alt);
		filter pf = new Position_Filter_Rectangle3D(min, max); 
		filter ff = new Or_Filter(pf,new Not_Filter(tf));
		System.out.println(ff);
		
		FileOutputStream fout = new FileOutputStream("test_filter.ser", true);
		ObjectOutputStream oos = new ObjectOutputStream(fout);
		oos.writeObject(ff);
		InputStream file = new FileInputStream("test_filter.ser");
	    InputStream buffer = new BufferedInputStream(file);
	    ObjectInput input = new ObjectInputStream (buffer);
	      //deserialize the filter
	    filter ff2 = (filter)input.readObject();
	    System.out.println(ff2);
	    if(!ff.toString().equals(ff2.toString())) {
	    	System.err.println("ERR                "+ff);
	    	System.err.println("Should be equals to"+ff2);
	    	fail("ERR Write and Read methods of filter are not returning the same filter");
	    }
	}
	
}
