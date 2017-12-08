package WiFi_data;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import Tools.Point3D;

/**
 * This class represents a set (HashMap) of AP_Scans ordered by the AccessPoints MAC address.
 * This is the main Data Structure for WiFi based location algorithms such as signal finger printing and 
 * @author benmo
 *
 */
public class APs_Scans {
	private HashMap<String, AP_Scans> _map;
	public APs_Scans() {
		_map = new HashMap<String, AP_Scans>(); 
	}
	public APs_Scans(String csv_file) {
		this(new WiFi_Scans(csv_file));
	}
	public APs_Scans(WiFi_Scans ws) {
		this();
		for(int i=0;i<ws.size();i++) {
			WiFi_Scan w = ws.get(i);
			Point3D pos = w.get_pos();
			String time = w.get_time();
			String device = w.get_device_id();
			for(int a = 0;a<w.size();a++) {
				WiFi_AP ap = w.get(a);
				AP_Scan aps = new AP_Scan(ap,pos,time,device);
				this.add(aps);
			}
		}
	}
	 public HashMap<String, AP_Scans> get_HashMap() {
		 return _map;
	 }
	/**
	 * return the number of MACs
	 * @return
	 */
	public int size() {return this._map.size();}
	public void add(AP_Scan aps) {
		String mac = aps.get_mac();
		if(_map.containsKey(mac)) {
			 AP_Scans ap = _map.get(mac);
			 ap.add(aps);
		}
		else {
			AP_Scans ap = new AP_Scans();
			ap.add(aps);
			_map.put(mac, ap);
		}
	}
	public AP_Scans get(String mac) {
		return this._map.get(mac);
	}
}
