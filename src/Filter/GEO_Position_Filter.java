package Filter;
import Tools.Cords;
import Tools.Point3D;
import WiFi_data.WiFi_Scan;

/**
 * This class represents a simple position filter based on a 2D distance from a center Point
 * Note: this is a simple L2 distance - when using GEO coordinates - one can expect a sphere (and not a circle).
 * @author Boaz
 *
 */
public class GEO_Position_Filter implements filter{
	public GEO_Position_Filter(Point3D p, double dist) {
		_center = p;
		_dist = dist;
	}
	public boolean test(WiFi_Scan rec) {
		boolean ans = false;
		if(rec!=null) {
			Point3D pos = rec.get_pos();
			double dist = Cords.dist2D_Lat_Lon(pos, this._center);
			if(dist < _dist) {ans = true;}
		}
		return ans;
	}
	public String toString() {
		return this.getClass().getName()+" cen: "+_center+"  dist: "+_dist;
	}
	/********** Private data can be located anywhere *************/
	private final Point3D _center;
	private double _dist;
}
