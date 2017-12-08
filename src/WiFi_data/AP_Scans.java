package WiFi_data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 * This class represents a simple container of AP_Scan - of the SAME Access Point. * 
 * @author benmo
 *
 */
public class AP_Scans{
	public static Comparator<WiFi_AP> SIGNAL_COMP = new AP_Comparator();
	private ArrayList<AP_Scan> _scans;
	private String _AP_MAC;
	public AP_Scans() {
		_scans = new ArrayList<AP_Scan>();
		_AP_MAC = null;
	}
	public void add(AP_Scan a) {
		if(a==null) {
			throw new RuntimeException("ERR: can not add null as an AP_Scan");
		}
		String mac = a.get_mac();
		if(_AP_MAC == null) {
			_AP_MAC = mac;
		}
		if(!mac.equals(_AP_MAC)) {
			throw new RuntimeException("ERR: all MACs should be the same");
		}
		this._scans.add(a);
		this.sort(); // this is not so efficient - yet it makes it much simpler to assume a sorted set invariant.
	}
	
	public String get_MAC() {
		return _AP_MAC;
	}
	public void setMAC(String mac) {
		if(this.size()==0) {
			_AP_MAC = mac;
		}
		else {
			throw new RuntimeException("ERR: can not change the container MAC once is is not empty!");
		}
	}
	
	public int size() {
		return this._scans.size();
	}
	private void sort() {
		this._scans.sort(SIGNAL_COMP);
	}
	public Iterator<AP_Scan> iterator() {
		return this._scans.iterator();
	}
}
