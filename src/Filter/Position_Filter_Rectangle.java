package Filter;
package Filter;
import Ex0.Point3D;
import Ex0.WiFi_Scan;
public class Position_Filter_Rectangle implements filter{
	

	/**
	 * This class represents a simple position filter based on a 3D Rectangle (axis parallel Box) represented by two Points (in 3D).
	 * @author Boaz
	 *
	 */ 
		public Position_Filter_Rectangle(Point3D p0, Point3D p1) {
			_min = new Point3D(p0);
			_max = new Point3D(p1);
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
		private final Point3D _min, _max;
	}

}
