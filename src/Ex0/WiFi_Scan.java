package Ex0;

import java.util.ArrayList;
import java.util.Date;

import java.text.*;

/**
 * This class represents a simple WiFi beacon in time, with: time, ssid, mac. channel, rssi and position.
 * 
 * @author benmo
  */
public class WiFi_Scan {
	private ArrayList<WiFi_AP> _aps;
	private boolean _is_wifi_scan;
	private String _time;
	private Point3D _pos;
	private String _device_id;
	public static final AP_Comparator COMP = new AP_Comparator();
	public static final WiFi_Scan_Comparator COMP_SCANS = new WiFi_Scan_Comparator();
	// 0a:8d:db:6e:71:6d,888Corp,[WPA2-EAP-CCMP][ESS],2017-10-27 16:16:45,1,-79,32.16766121892341,34.80988155918773,39.018065819940986,3,WIFI
	public WiFi_Scan(String l) {
		String line = l.replaceAll(",,", ", ,");
		String[] ll = line.split(",");
		this._aps = new ArrayList<WiFi_AP>(1);
		if(line.endsWith("GSM") || line.endsWith("LTE")){
			this.set_is_wifi_scan(false);
		}
		else {
			this.set_is_wifi_scan(true);
			WiFi_AP ap = new WiFi_AP(ll[0],ll[1],Integer.parseInt(ll[4]),Double.parseDouble(ll[5]));
			_aps.add(ap);
		}
		this.set_time(ll[3]);
		String pos = ll[6]+" "+ll[7]+" "+ll[8];
		this.set_pos(new Point3D(pos));
		this._device_id="";
	}
	public String toString() {
		String s = "";
		int num = Math.min(size(), 10);
		for(int i=0;i<num;i++) {
			s+=get(i)+",";
		}
		
		return this.get_time()+","+this._device_id+","+this.get_pos().toFile()+","+num+","+s;
	}
	
	public int size() {return this._aps.size();}
	public WiFi_AP get(int i) {return this._aps.get(i);}
	
	public Point3D get_pos() {
		return _pos;
	}

	public String get_time() {
		return _time;
	}
	/**
	 * 
	 * @param ot
	 * @return
	 */
	public boolean combine(WiFi_Scan ot) {
		boolean ans = false;
		if(ot!=null && ot.get_time().equals(this.get_time())) {
			this._aps.add(ot.get(0));
			ans = true;
		}
		return ans;
	}
	public void sort() {
		this._aps.sort(COMP);
	}
	public String get_device_id() {return this._device_id;}
	public void set_device_id(String id) {this._device_id = id;}
	////////////////////////////////////////////////////////
	private void set_pos(Point3D _pos) {
		this._pos = _pos;
	}
	
	private void set_time(String _time) {
		this._time = _time;
	}
	public boolean is_is_wifi_scan() {
		return _is_wifi_scan;
	}
	public void set_is_wifi_scan(boolean _is_wifi_scan) {
		this._is_wifi_scan = _is_wifi_scan;
	}

}
