package Filter;
import Tools.Point3D;
import WiFi_data.WiFi_Scan;
public class Position_Filter_Rectangle3D implements filter{
	
	/**
	 * This class represents a simple position filter based on a 3D (OPEN) Rectangle (axis parallel Box) represented by two Points (in 3D).
	 * @author Boaz
	 *
	 */ 
		public Position_Filter_Rectangle3D(Point3D p0, Point3D p1) {
			if(!p0.smallerThan3D(p1)) {
				throw new RuntimeException("Got wrong parameters for 2D Rectangle p0 should be smallerThan2D p1!");
			}
			_min = new Point3D(p0);
			_max = new Point3D(p1);
		}
		public boolean test(WiFi_Scan rec) {
			boolean ans = false;
			if(rec!=null) {
				Point3D pos = rec.get_pos();
				if(_min.smallerThan3D(pos) && _max.biggerThan3D(pos)) {ans = true;}
			}
			return ans;
		}
		public String toString() {
			return ""+this.getClass().getName()+" ["+this._min+","+_max+"]";
		}
	
		/********** Private data can be located anywhere *************/
		private final Point3D _min, _max;
}
