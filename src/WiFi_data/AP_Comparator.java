package WiFi_data;
import java.util.Comparator;
/**
 * This is a simple comparator for sorting WiFi Access Points according to their received signal strength 
 * @author benmo
 *
 */
public class AP_Comparator implements Comparator<WiFi_AP> {

		@Override
		public int compare(WiFi_AP o1, WiFi_AP o2) {
			double t = o2.get_rssi() - o1.get_rssi();
			if(t<0) return -1;
			if(t>0) return 1;
			return 0;
		}
	}

