package Algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import Tools.Point3D;
import Tools.Wighted_Point3D;
import WiFi_data.AP_Scan;
import WiFi_data.AP_Scans;
import WiFi_data.APs_Scans;
import WiFi_data.WiFi_Scans;

/**
 * This class represents a simple weighted center point algorithm for computing (approximating) the Access Point location 
 * from several GEO samples (position & signal strength).
 * This is an educational implementation (for Ariel-University, OOP course 2017-2018) and should not be use for commercial application.
 * @author boaz
 *
 */
public class MAC_approx_location {
/**
 * This function assume the weighted points are sorted by weight (biggest first), assumes all weighted are positive!
 * @param wps
 * @param max_samples
 * @return
 */
	public static final double POWER = -2.0; // Note should be negative as the weight should increase as the signal increases;
	public static final int NUM_OF_POINTS = 4; // [3-6] is the common range. 
	public static Point3D weighted_center(ArrayList<Wighted_Point3D> wps, int max_samples) {
		Point3D ans = null;
		if(wps ==null && wps.size()==0) {
			throw new RuntimeException("ERR: can not compute a weightrd center point using an empty list");
		}
		int size = Math.min(max_samples, wps.size());
		double w_sum=0;
		double x=0,y=0,z=0;
		for(int i=0;i<size;i++) {
			Wighted_Point3D p = wps.get(i);
			double w = p.get_weight();
			w_sum+=w;
			x+=p.x()*w;
			y+=p.y()*w;
			z+=p.z()*w;
		}
		x/=w_sum;
		y/=w_sum;
		z/=w_sum;
		ans = new Point3D(x,y,z);
		return ans;
	}
	public static AP_Scan weighted_center(AP_Scans aps, int max_samples) {
		AP_Scan ans = null;
		if(aps ==null || aps.size()==0) {
			throw new RuntimeException("ERR: can not compute a weightrd center point for AP_Scans using an empty list");
		}
		int i=0;
		ArrayList<Wighted_Point3D> wps = new ArrayList<Wighted_Point3D>();
		Iterator<AP_Scan> iter = aps.iterator();
		while(iter.hasNext() && i<max_samples){
			AP_Scan ap = iter.next();
			if(ans == null) {ans = new AP_Scan(ap);}
			double w = compute_weight(ap.get_rssi());
			Wighted_Point3D p = new Wighted_Point3D(ap.get_pos(), w);
			wps.add(p);
		}
		Point3D w_cen = weighted_center(wps,max_samples);
		ans.set_pos(w_cen);
		ans.set_device_type("Approx. w-center algo1");
		return ans;
	}
	public static AP_Scan weighted_center(AP_Scans aps) {
		return weighted_center(aps, NUM_OF_POINTS);
	}
	/**
	 * this function gets a list (HashMap) of AP samples (AP_Scans) and for each AP computes its approximated position. 
	 * @param aps
	 * @param max_samples
	 * @return
	 */
	public static ArrayList<AP_Scan> weighted_center(APs_Scans wps, int max_samples) {
		ArrayList<AP_Scan> ans = new ArrayList<AP_Scan>();
		HashMap<String, AP_Scans> map = wps.get_HashMap();
		Set<String> keys = map.keySet();
		Iterator<String> iter = keys.iterator();
		while(iter.hasNext()) {
			String key = iter.next();
			AP_Scans aps = wps.get(key);
			AP_Scan c = weighted_center(aps,max_samples);
			ans.add(c);
		}
		return ans;
	}
	public static ArrayList<AP_Scan> weighted_center(APs_Scans wps) {
		return weighted_center(wps,NUM_OF_POINTS);
	}
	
	public static ArrayList<AP_Scan> weighted_center(WiFi_Scans wps, int size) {
		APs_Scans macs = new APs_Scans(wps);
		return weighted_center(macs, size);
	}
	public static ArrayList<AP_Scan> weighted_center(String csv_file, int size) {
		WiFi_Scans macs = new WiFi_Scans(csv_file);
		return weighted_center(macs, size);
	}
	public static ArrayList<AP_Scan> weighted_center(String csv_file) {
		WiFi_Scans macs = new WiFi_Scans(csv_file);
		return weighted_center(macs, NUM_OF_POINTS);
	}
	
	public static double compute_weight(double signal) {
		double sig = Math.abs(signal);
		double ans = Math.pow(sig, POWER);
		return ans;
	}
	
}
