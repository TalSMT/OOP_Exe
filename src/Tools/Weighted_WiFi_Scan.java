package Tools;
import java.util.Comparator;
import WiFi_data.WiFi_Scan;

/**
 * This class represents a simple weight addition to the regular WiFi_Scan - so it can be sorted according to 
 * a correlation of a given WiFi_Scan - in order to implement Algorithm#2 of Ex2 - OOP Course Ariel University 2017-2018.
 * 
 * @author benmo
 *
 */
public class Weighted_WiFi_Scan extends WiFi_Scan{
	public static Comparator<Weighted_WiFi_Scan> W_WIFI_SCAN_COMP = new WP_Comparator();
	private double _weight;
	public Weighted_WiFi_Scan(WiFi_Scan ws, double w) {
		super(ws);
		this.set_weight(w);
	}
	public double get_weight() {
		return _weight;
	}
	public void set_weight(double _weight) {
		this._weight = _weight;
	}
	

	/**
	 * This is a simple comparator for sorting weighted WiFi_Scans according to their weight 
	 * @author benmo
	 *
	 */
	static class WP_Comparator implements Comparator<Weighted_WiFi_Scan> {
			@Override
			public int compare(Weighted_WiFi_Scan o1, Weighted_WiFi_Scan o2) {
				double t = o2.get_weight() - o1.get_weight();
				if(t<0) return -1;
				if(t>0) return 1;
				return 0;
			}
			public static Comparator<Weighted_WiFi_Scan> get_comp() {
				 return new WP_Comparator();
			}
		}
}
