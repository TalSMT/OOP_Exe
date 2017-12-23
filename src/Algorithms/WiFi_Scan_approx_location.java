package Algorithms;

import java.util.ArrayList;

import Tools.Point3D;
import Tools.Weighted_WiFi_Scan;
import Tools.Wighted_Point3D;
import WiFi_data.WiFi_AP;
import WiFi_data.WiFi_Scan;
import WiFi_data.WiFi_Scans;

/**
 * This class represents a simple weighted finger-printing algorithm for WiFi position - as part
 * of OOP course - Ariel University 2017-2018.
 * @author benmo
 *
 */
public class WiFi_Scan_approx_location {
	public static final double MIN_DB_ERR=3.0;
	public static final double NO_AP_DB_DIFF = 100;
	public static final double POWER_OF_SIGNAL = 2.0;
	public static final double POWER_OF_DIFF = 0.4;
	/**
	 * This is the main function implementing the positioning algorithm for a given WiFi_Scan - with respect to 
	 * a given DB (Testing set).
	 * The algorithm sorts each of the DB scans according to the correlation value to a given WiFi_Scan - the best 
	 * 3-5 Scans in the DB are used to compute a weighted center (as in MAC_approx_location).
	 * @param db
	 * @param testing
	 * @return
	 */
	public static WiFi_Scans approx_Scans_positions(WiFi_Scans db, WiFi_Scans testing, int max_samples) {
		WiFi_Scans ans = new WiFi_Scans();
		int size = testing.size();
		for(int i=0;i<size;i++) {
			WiFi_Scan curr = testing.get(i);
			ArrayList<Weighted_WiFi_Scan> sort = compute_correlation(curr, db);
			Point3D approx_pos = compute(sort, max_samples);
			curr.set_pos(approx_pos);
			ans.add(curr);
		}
		return ans;
	}
	/**
	 * This function scan the list of WiFi_Scans and for each one computes its resembles (correlation) to a given
	 * current WiFi_Scan - the List is then being sorted according to the weight.
	 * @param curr
	 * @param db
	 * @return
	 */
	public static ArrayList<Weighted_WiFi_Scan> compute_correlation(WiFi_Scan curr, WiFi_Scans db ) {
		int size = db.size();
		ArrayList<Weighted_WiFi_Scan> ans = new ArrayList<Weighted_WiFi_Scan>();
		for(int i=0;i<size;i++) {
			WiFi_Scan db_rec = db.get(i);
			double w = correlation(curr, db_rec);
			Weighted_WiFi_Scan w_rec = new Weighted_WiFi_Scan(db_rec,w);
			ans.add(w_rec);
		}
		ans.sort(Weighted_WiFi_Scan.W_WIFI_SCAN_COMP);
		return ans;
	}
	/**
	 * This function computes the correlation between two WiFi Scan (curr, db): 
	 * for each AP in curr, look for the same AP in db:
	 * 		if exists, compute the signal diff + some constant (MIN_DB_ERR), 
	 * 		if not assume 100 db diff (NO_AP_DB_DIFF).
	 * 		let v(curr) be  1/(diff^c1 *curr.signal^c2): c1 ~ [0.2,1], c2 ~ [1,3].
	 * Compute the value of PI(v(AP_i)), for each ap_i in curr. 
	 * @param curr
	 * @param db_record
	 * @return if none of the MACS in curr are found - return 0; 
	 */
	public static double correlation(WiFi_Scan curr, WiFi_Scan db_record){
		double ans = 1;
		int size = curr.size();
		int count = 0;
		if(size==0) {throw new RuntimeException("ERR: got an empty WiFi Scan_can not use it for position!");}
		for(int i=0;i<size;i++) {
			WiFi_AP curr_ap = curr.get(i);
			double diff = diff(curr_ap, db_record);
			if(diff < NO_AP_DB_DIFF) {
				count++;
			}
			double sig = curr_ap.get_rssi();
			sig = Math.abs(sig);
			sig = Math.pow(sig, POWER_OF_SIGNAL);
			diff = Math.pow(diff, POWER_OF_DIFF);
			ans *= 1.0/(sig*diff);
		}
		if(count > 0) {
			return ans;
		}
		return 0;
	}
	public static double diff(WiFi_AP curr, WiFi_Scan db) {
		double ans = NO_AP_DB_DIFF;
		WiFi_AP db_ap = find(curr.get_mac(), db);
		if(db_ap!=null) {
			ans = curr.get_rssi() - db_ap.get_rssi();
			ans = Math.abs(ans) + MIN_DB_ERR;
		}
		return ans;
	}
	private static WiFi_AP find(String mac, WiFi_Scan db) {
		WiFi_AP ans = null;
		int size = db.size();
		int i=0;
		while(i<size && ans==null) {
			WiFi_AP curr = db.get(i);
			if(mac.equals(curr.get_mac())) {
				ans = curr;
			}
			i++;
		}
		return ans;
	}
	/** 
	 * Bug fixed - if no match - return null;
	 * @param sort
	 * @param max_samples
	 * @return
	 */
	private static Point3D compute(ArrayList<Weighted_WiFi_Scan> sort, int max_samples) {
		Point3D ans = null;
		if(sort==null || sort.size()==0|| sort.get(0).get_weight()==0) {
			return ans;
		}
		ArrayList<Wighted_Point3D> wps = new ArrayList<Wighted_Point3D>(max_samples);
		for(int i=0;i<max_samples;i++) {
			Weighted_WiFi_Scan c = sort.get(i);
			Wighted_Point3D p = new Wighted_Point3D(c.get_pos(), c.get_weight());
			wps.add(p);
		}
		ans = MAC_approx_location.weighted_center(wps, max_samples);
		return ans;
	}
}
