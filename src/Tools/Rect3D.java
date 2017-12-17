package Tools;

public class Rect3D {
	private Point3D _min;
	private Point3D _max;
	
	public Rect3D(Point3D min, Point3D max) {
		this.set_min(min);
		this.set_max(max);
	}
	public double dx(){
		return _max.x()-_min.x();
	}
	public double dy(){
		return _max.y()-_min.y();
	}
	public double dz(){
		return _max.z()-_min.z();
	}
	public Point3D get_max() {
		return _max;
	}
	private void set_max(Point3D _max) {
		this._max = _max;
	}
	public Point3D get_min() {
		return _min;
	}
	private void set_min(Point3D _min) {
		this._min = _min;
	}
	
	
}
