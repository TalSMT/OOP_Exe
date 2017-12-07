package Ex0;
import java.util.Comparator;
public class WiFi_Scan_Comparator implements Comparator<WiFi_Scan> {

			@Override
			public int compare(WiFi_Scan o1, WiFi_Scan o2) {
				// TODO Auto-generated method stub
				int d = o1.get_time().compareTo(o2.get_time());
				return d;
			}
	}
