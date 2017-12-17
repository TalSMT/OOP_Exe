package Filter;

import WiFi_data.WiFi_Scan;

/**
 * KALLLL
 * @author Boaz
 *
 */
public class Not_Filter implements filter{
	private filter _filter;
	public Not_Filter(filter f) {
		_filter = f;
	}
	public boolean test(WiFi_Scan rec) {
		return !(_filter.test(rec));
	}

	public String toString() {
		return "not("+_filter+")";
	}
}
