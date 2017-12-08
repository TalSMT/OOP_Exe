package Tests;
/**
 * simple (trivial Junit test for Cords,
 * Tests are based on the following coordinates:
 * 	p1			p2			p3	
x	35.209687	35.20954	35.210063	lon
y	32.10283	32.10368	32.104084	lat
z	680			685			691	
				
	p1-->p2	p1-->p3	p2-->p3	
	96.5	147	69	65
	351.5	14.1	47	
 */
import static org.junit.Assert.*;
import org.junit.Test;

import Tools.Cords;
import Tools.Point3D;
public class CordsTest {
	//////////////////////////////////////////////////////
	
	public static void main(String[] a) {
		test3();
	}
	//@Test
	public static void test3() {
		double k1 = 0.001;
		double[] p1 = {35.0,30.0,0};
		double[] p2 = {35 + k1,30 +2*k1,0};
		double[] p3 = {35 + k1*2,30 +k1,0};
		
		
		double[] vec12 = Cords.flatWorldDist(p1, p2);
		double[] vec13 = Cords.flatWorldDist(p1, p3);
		double[] vec23 = Cords.flatWorldDist(p2, p3);
		double[] ll2a = Cords.offsetLatLonAlt(p1,vec12);
		double[] d22 =  Cords.flatWorldDist(p2,ll2a);
		System.out.println("Vec22:"+d22[0]+", "+d22[1]+", "+d22[2]);
		
		double[] ad12 = Cords.azmDist(p1, p2);
		double[] ad13 = Cords.azmDist(p1, p3);
		double[] ad23 = Cords.azmDist(p2, p3);
		
		System.out.println("Vec12:"+ad12[0]+", "+ad12[1]);
		System.out.println("Vec13:"+ad13[0]+", "+ad13[1]);
		System.out.println("Vec23:"+ad23[0]+", "+ad23[1]);
	}
	@Test
	/**
	 * 35.209687	35.20954	35.210063	lon
		y	32.10283	32.10368	32.104084	lat
		z	680			685			691	
						
			p1-->p2	p1-->p3	p2-->p3	
			95.5	147	69	65
		
	 */
	public void simpleDistTest() {
		double m12 = 95.5; 
		double m13 = 143.8;
		double m23 = 66.67;
		Point3D p1 = new Point3D(32.10283, 35.209687, 680);
		Point3D p2 = new Point3D(32.10368, 35.20954, 685);
		Point3D p3 = new Point3D(32.104084, 35.210063, 691);
		double d12 = Cords.dist2D_Lat_Lon(p1, p2);
		double d23 = Cords.dist2D_Lat_Lon(p2, p3);
		double d13 = Cords.dist2D_Lat_Lon(p1, p3);
		if(Math.abs(d12-m12)>0.5) {
			System.err.println("ERR: Cords  p12: "+d12);
			fail();
		}
		if(Math.abs(d13-m13)>0.5) {
			System.err.println("ERR: Cords  p13: "+d13);
			fail();
		}
		if(Math.abs(d23-m23)>0.5) {
			System.err.println("ERR: Cords  p23: "+d23);
			fail();
		}
	}
	@Test
	public void test1() {
		double[] ll1 = {32.166916,34.803395,100};
		double[] ll2 = {32.166917,34.81401,120};
		double[] vec = Cords.flatWorldDist(ll1, ll2);
		
		double[] ll2a = Cords.offsetLatLonAlt(ll1,vec);
		System.out.println("Vec:"+vec[0]+", "+vec[1]+", "+vec[2]);
		System.out.println("ll2a:"+ll2a[0]+", "+ll2a[1]+", "+ll2a[2]);
		if(Math.abs(vec[1])>1 | Math.abs(vec[0]-1000)>1 || Math.abs(vec[2]-20)>0.001 ) {
			System.err.println("Wrong Coords to dVec convertor");
			fail("");
		}
		if(Math.abs(ll2[0]-ll2a[0])>0.0001 | Math.abs(ll2[1]-ll2a[1])>0.0001 || Math.abs(ll2[2]-ll2a[2])>0.0001 ) {
			System.err.println("Wrong Coords to dVec2 convertor");
			fail("");
		}
	}
	

	
	//@Test
//	public void testSimple() {
	//	main(null);
//	}
	@Test
	public void testFlatWorldDist2() {
		double[] ll1 = {32.166916,34.803395,100};
		double[] vec = {300,400,0};
		double[] ll2 = Cords.offsetLatLonAlt(ll1, vec);
		
		double[] vec2 = Cords.flatWorldDist(ll1, ll2);
		System.out.println("x="+vec2[0]+" y="+vec2[1]);
		if(Math.abs(vec2[0]-vec[0])>.1 | Math.abs(vec[1]- vec2[1])>.1 || Math.abs(vec[2]-vec2[2])>0.001 ) {
			
			System.err.println("Wrong Coords to dVec convertor");
			fail("Wrong Coords to dVec convertor");
		}
	}
	@Test
	public void testFlatWorldDist() {
		double[] ll1 = {32.166916,34.803395,100};
		double[] ll2 = {32.166917,34.81401,120};
		double[] vec = Cords.flatWorldDist(ll1, ll2);
		
		double[] ll2a = Cords.offsetLatLonAlt(ll1,vec);
		System.out.println("Vec:"+vec[0]+", "+vec[1]+", "+vec[2]);
		System.out.println("ll2a:"+ll2a[0]+", "+ll2a[1]+", "+ll2a[2]);
		//if(Math.abs(vec[0])>1 | Math.abs(vec[1]-969)>1 || Math.abs(vec[2]-20)>0.001 ) {
		if(Math.abs(vec[0]-999)>1 || Math.abs(vec[1])>1 || Math.abs(vec[2]-20)>0.001 ) {
	
			System.err.println("Wrong Coords to dVec convertor "+vec[2]);
			fail("Wrong Coords to dVec convertor");
		}
		if(Math.abs(ll2[0]-ll2a[0])>0.001 | Math.abs(ll2[1]-ll2a[1])>0.001 || Math.abs(ll2[2]-ll2a[2])>0.001 ) {
			System.err.println("Wrong Coords to dVec2 convertor");
			fail("Wrong Coords to dVec2 convertor");
		}
	}

	@Test
	public void testAzmDist() {
		for(double i=0;i<360;i=i+7.89) {
			double a = Math.toRadians(i);
			double x = Math.sin(a), y = Math.cos(a);
			double ang = Cords.angXY(x,y);
			if(Math.abs(ang-i)>0.0001) {
				fail("Wrong azm!");
			}
		}
		
		
	}
	@Test
	public void test2() {
		double lon=35.0, lat = 30.0, k1 = 0.001;
		double[] p1 = {lat,lon,0};
		double[] p2 = {lat+k1*2,lon+k1,0};
		double[] p3 = {lat+k1,lon+k1*2,0};
		
	//	double[] vec12 = Cords.flatWorldDist(p1, p2);
		double[] vec13 = Cords.flatWorldDist(p1, p3);
		double[] ll3a = Cords.offsetLatLonAlt(p1,vec13);
		double[] vec13a =  Cords.flatWorldDist(p1,ll3a);
		
		double[] vec33 = Cords.flatWorldDist(p3, ll3a);
		System.out.println("Vec13:"+vec13[0]+", "+vec13[1]+", "+vec13[2]);
		System.out.println("Vec13a:"+vec13a[0]+", "+vec13a[1]+", "+vec13a[2]);
		System.out.println("Vec33:"+vec33[0]+", "+vec33[1]+", "+vec33[2]);
		double[] ad12 = Cords.azmDist(p1, p2);
		double[] ad13 = Cords.azmDist(p1, p3);
		double[] ad23 = Cords.azmDist(p2, p3);
		
		System.out.println("Vec12:"+ad12[0]+", "+ad12[1]);
		System.out.println("Vec13:"+ad13[0]+", "+ad13[1]);
		System.out.println("Vec23:"+ad23[0]+", "+ad23[1]);
		
	}
	
}
