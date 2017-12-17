package Filter;

import WiFi_data.WiFi_Scan;

/**
	 * This class represents a simple time filter based on a 1D time window range
	 * @author Boaz
	 *
	 */
public class Time_Filter implements filter{
	public Time_Filter(String start, String end) {
			_start = start;
			_end = end;
	}

	
	public boolean test(WiFi_Scan rec) {
		boolean ans = false;
		if(rec!=null) {
			String t = rec.get_time();
			int ts = _start.compareTo(t);
			int te = _end.compareTo(t);
	//		System.out.println(_start+"  <  "+t+"   <  "+_end+"  "+ts+"  "+te);
			// t0.compareTo(t)<0 && t1.compareTo(t)>0
			if(ts<=0 && te>0) {ans = true;}
		}
		return ans;
	}

	public String toString() {
		return ""+this.getClass().getName()+" ["+this._start+","+_end+"]";
	}
		
	/********** Private data can be located anywhere *************/
	private String _start, _end;
}
