package WiFi_data;
import java.util.ArrayList;
import java.util.Iterator;


import java.io.*;

public class WiFi_Beacons_sd extends ArrayList<WiFi_Scan> {
	private String _device_id;
	
	public WiFi_Beacons_sd() {
		super();
	}
	public WiFi_Beacons_sd(File file) {
		this();
		try {
			FileReader fr = new FileReader(file);
			BufferedReader csv_br = new BufferedReader(fr);
			String line = csv_br.readLine();
			this._device_id = line.split(",")[2]+"_"+line.split(",")[4];
			line = csv_br.readLine();
			line = csv_br.readLine();
			int i=0;
		    while(line!=null) {
		    	WiFi_Scan wi = new WiFi_Scan(line);
		    	if(wi.is_is_wifi_scan()) {
		    		this.add(wi);
		    	}
		    	line = csv_br.readLine();
		  //  	System.out.println(i+") "+wi);
		    	i++;
		      }
		   }
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public String get_device_id() {return this._device_id;}
/**
 * This method simply combine all the same time WiFi_Samples into a single ArrayList.
 */
	public void order() {
		int i0=0;
		int i1=i0+1;
		while(i1<this.size()) {
			WiFi_Scan s0 = this.get(i0);
			WiFi_Scan s1 = this.get(i1);
			if(s0.get_time().equals(s1.get_time())) {
				s0.combine(s1);
				this.remove(i1);
			}
			else {
				s0.sort();
				i0=i1;
				i1++;
			}
		}
	}
	public void save_to_csv(String file_name) {
		try {
			PrintWriter pw = new PrintWriter(file_name);
			StringBuilder sb = new StringBuilder();
			Iterator<WiFi_Scan> is = this.iterator();
			while (is.hasNext()) {
				sb.append(this._device_id+","+is.next().toString()+"\n");
			}
			pw.println(sb);
			pw.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
