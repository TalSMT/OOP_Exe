/**
 * This is the main container for multi divices;
 */
package Ex0;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import Data_sets.MyVector;

public class WiFi_Beacons_md {
	private ArrayList< WiFi_Scan> _devices;
	
	public WiFi_Beacons_md(String dir) {
		 File file1 = new File(dir);
	      File[] files = file1.listFiles();
	      String[] names = file1.list();
	      System.out.println(file1.toString());
	      ArrayList< WiFi_Beacons_sd> dd = new ArrayList< WiFi_Beacons_sd>();
	      _devices = new ArrayList< WiFi_Scan>();
	      for(int i=0;i<names.length;i++) {
	    	  if(names[i].startsWith("WigleWifi") && names[i].endsWith(".csv")){
	    		  System.out.println(i+") found a nother file: "+names[i]);
	    		  WiFi_Beacons_sd sd = new WiFi_Beacons_sd(files[i]);
	    		  sd.order();
	    		  for(int a=0;a<sd.size();a++) {
	    			  WiFi_Scan w = sd.get(a);
	    			  w.set_device_id(sd.get_device_id());
	    			  _devices.add(w);
	    		  }
	    	  }
	      }
	      _devices.sort(WiFi_Scan.COMP_SCANS);
	}
	public MyVector convert() {
		MyVector ans = new MyVector();
		Iterator<WiFi_Scan> iter = this._devices.iterator();
		while(iter.hasNext()) {
			ans.add(iter.next());  // NOTE this is only a shallow copy!!
		}
		return ans;
	}
	public void save_to_csv(String file_name) {
		try {
			PrintWriter pw = new PrintWriter(file_name);
			StringBuilder sb = new StringBuilder();
			Iterator<WiFi_Scan> is = this._devices.iterator();
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
