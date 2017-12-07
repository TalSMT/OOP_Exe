package Filter;
import Ex0.Point3D;
import Ex0.WiFi_Scan;

/**
 * This class represents a simple position filter based on a 2D distance from a center Point
 * @author Boaz
 *
 */
public class Position_Filter implements filter{
	public Position_Filter(Point3D p, double dist) {
		_center = p;
		_dist = dist;
	}
	public boolean test(WiFi_Scan rec) {
		boolean ans = false;
		if(rec!=null) {
			Point3D pos = rec.get_pos();
			double dist2D = pos.distance2D(_center);
			if(dist2D < _dist) {ans = true;}
		}
		return ans;
	}
	
	/********** Private data can be located anywhere *************/
	private final Point3D _center;
	private double _dist;
}
