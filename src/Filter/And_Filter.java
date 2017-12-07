package Filter;
import WiFi_data.WiFi_Scan;

public class And_Filter implements filter{
	private filter _f1, _f2;
	public And_Filter(filter f1, filter f2) {
		_f1 = f1;
		_f2 = f2;
	}

	public boolean test(WiFi_Scan rec) {
		return _f1.test(rec) && _f2.test(rec);
	}
}
