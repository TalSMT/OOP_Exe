package WiFi_data;
import java.util.Comparator;
/** This class represents a simple WiFi_Scan comparator - based on time (with a String representation) 
*/
public class WiFi_Scan_Comparator implements Comparator<WiFi_Scan> {

			public int compare(WiFi_Scan o1, WiFi_Scan o2) {
				int d = o1.get_time().compareTo(o2.get_time());
				return d;
			}
	}
