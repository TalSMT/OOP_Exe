package WiFi_data;

import Tools.Point3D;

/**
 * This class represents a single WiFi Access Point Scan in time and place;
 * @author benmo
 *
 */
public class AP_Scan extends WiFi_AP{
	private Point3D _pos;
	private String _time; // should be a long in mili seconds - starting at 1/1/1970 - as in java.util.Date
	private String _device_type; // should be an int (hashing the main parameters of the device / OS).
	
	public AP_Scan(WiFi_AP ap, Point3D p, String t, String d) {
		super(ap);
		_pos = p;
		_time = t;
		_device_type = d;
	}
	
	public AP_Scan(AP_Scan ap) {
		this(ap, ap.get_pos(), ap.get_time(), ap.get_device_type());
	}

	public String toString() {
		String ans = super.toString();
		ans+=","+_pos.toFile()+","+_time+","+this._device_type;
		return ans;
	}

	public Point3D get_pos() {
		return _pos;
	}

	public void set_pos(Point3D _pos) {
		this._pos = _pos;
	}

	public String get_time() {
		return _time;
	}

	public void set_time(String _time) {
		this._time = _time;
	}

	public String get_device_type() {
		return _device_type;
	}

	public void set_device_type(String _device_type) {
		this._device_type = _device_type;
	}
	
}
