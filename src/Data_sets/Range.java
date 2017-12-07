package Data_sets;
/**
 * This class relresents a dimple 1D range [min,max)
 * @author Boaz
 *
 */
public class Range {
	private double _min;
	private double _max;
	
	public Range(double min, double max) {
		_min = min;
		_max = max;
	}
	public boolean isIn(double v) {
		return v>=_min && v<_max;
	}
	public String toString() {
		return "Range["+_min+","+_max+"]";
	}
	public double getMin() {return _min;}
	public double getMax() {return _max;}
}
