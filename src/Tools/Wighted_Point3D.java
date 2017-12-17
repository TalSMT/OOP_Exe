package Tools;

import java.util.Comparator;

/**
 * This class represents a point3D - with weight.
 * @author benmo
 *
 */
public class Wighted_Point3D extends Point3D{

	private static final long serialVersionUID = 1L;
	public static final double DEFAULT_WEIGHT = 1;
	public static final Comparator<Wighted_Point3D> WP_COMP = WP_Comparator.get_comp();
	private double _weight;
	public Wighted_Point3D(double x, double y, double z, double w) {
		super(x, y,z);
		this.set_weight(w);
	}
	public Wighted_Point3D(double x, double y, double z) {
		this(x,y,z,DEFAULT_WEIGHT);
	}
	public Wighted_Point3D(Point3D p, double w) {
		super(p);
		this.set_weight(w);
	}
	public Wighted_Point3D(Point3D p) {
		this(p, DEFAULT_WEIGHT);
	}
	

	public double get_weight() {
		return _weight;
	}

	public void set_weight(double _weight) {
		this._weight = _weight;
	}

	
	/**
	 * This is a simple comparator for sorting WiFi Access Points according to their received signal strength 
	 * @author benmo
	 *
	 */
	static class WP_Comparator implements Comparator<Wighted_Point3D> {
			@Override
			public int compare(Wighted_Point3D o1, Wighted_Point3D o2) {
				double t = o2.get_weight() - o1.get_weight();
				if(t<0) return -1;
				if(t>0) return 1;
				return 0;
			}
			public static Comparator<Wighted_Point3D> get_comp() {
				 return new WP_Comparator();
			}
		}
}
