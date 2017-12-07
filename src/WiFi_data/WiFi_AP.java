package WiFi_data;
/**
 * This class represents the simplest wifi single AP received beacon in time.
 * @author benmo
 *
 */
public class WiFi_AP {
	private String _ssid;
	private String _mac;
	private int _channel;
	private double _rssi;
	
	public WiFi_AP(String mac, String ssid, int channel, double rssi) {
		this.set_ssid(ssid);
		this.set_mac(mac);
		this.set_channel(channel);
		this.set_rssi(rssi);
	}
	
	public String toString() {
		return ""+","+this.get_ssid()+","+this.get_mac()+","+this.get_channel()+","+this.get_rssi();
	}
	
	public String get_ssid() {
		return _ssid;
	}

	private void set_ssid(String _ssid) {
		this._ssid = _ssid;
	}

	public String get_mac() {
		return _mac;
	}

	private void set_mac(String _mac) {
		this._mac = _mac;
	}

	public int get_channel() {
		return _channel;
	}

	private void set_channel(int _channel) {
		this._channel = _channel;
	}
	public double get_rssi() {
		return _rssi;
	}

	private void set_rssi(double _rssi) {
		this._rssi = _rssi;
	}
}
