package WiFi_data;
import java.util.ArrayList;
import java.util.Iterator;

import Filter.filter;

import java.io.*;

public class WiFi_Scans extends ArrayList<WiFi_Scan> {

	public WiFi_Scans() {
		super();
	}
	public WiFi_Scans(String csv_file) {
		this();
		try {
			FileReader fr = new FileReader(csv_file);
			BufferedReader csv_br = new BufferedReader(fr);
			String line = csv_br.readLine();
			int i=1;
		    while(line!=null) {
		    	try{
		    		WiFi_Scan wi = WiFi_Scan.init_from_46_csv_file(line);
		    		if(wi.is_is_wifi_scan()) {this.add(wi);}
		    	}
		    	catch(Exception e) {
		    		e.printStackTrace();
		    		System.err.println("ERR reading line "+i+") Line: "+line);
		    	}
		    	line = csv_br.readLine();
		    	i++;
		      }
		   }
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public WiFi_Scans copy() {
		WiFi_Scans ans = new WiFi_Scans();
		Iterator<WiFi_Scan> itr = this.iterator();
		while(itr.hasNext()) {
			ans.add(itr.next());
		}
		return ans;
	}
	/**
	 * Note: we would rewrite this code many times, mainly with lambda exp.
	 * 
	 * @param f
	 */
	public void filter(filter f){
		int i=0;
		while(i<this.size()) {
			WiFi_Scan rec = this.get(i);
			boolean ans = f.test(rec);
			if(ans) {i++;}
			else {
				this.remove(i);
			}
		}
	}
	
	public void save_to_csv(String file_name) {
		try {
			PrintWriter pw = new PrintWriter(file_name);
			StringBuilder sb = new StringBuilder();
			Iterator<WiFi_Scan> is = this.iterator();
			while (is.hasNext()) {
				sb.append(is.next().toString()+"\n");
			}
			pw.println(sb);
			pw.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
