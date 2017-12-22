package Algorithms;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import WiFi_data.AP_Scan;
import WiFi_data.WiFi_Scan;
import WiFi_data.WiFi_Scans;
import java.io.*;

/**
 * This main class represents a simple testing tool for Ex2.1 
 * @author benmo
 *
 */
public class Ex2_Algo1 {
	public static void main(String[] a) {
		System.out.println("OOP Course: EX2, Algo1 self testing tool");
		String dir = "data/BM2/comb/";
		String file = "_comb_all_.csv";
		String in = dir+file;
		if(a.length>1) {
			in = a[0];
		}
		else {
			System.err.println("\n Wrong parameters for Ex2_Algo1 testing tool should get an input csv file"+
		"\n general input format: java -jar Ex2_Algo1 input.csv output.csv number_of_points\n if no output file is given an out put file with time based name is generated" 
					);
			
		}
		
		int number = 4;
		if(a.length>2) {
			number = new Integer(a[2]);
		}
		long time = new Date().getTime();
		String out = dir+"Algo1_"+number+"_"+time+"_"+file;
		if(a.length>2){
			out = a[1];
		}
		System.out.println("Algo1 is ready to run with "+number+" strongest points");
		System.out.println("Input: "+in);
		System.out.println("Output: "+out);
		
		WiFi_Scans ws = new WiFi_Scans(in);
		ArrayList<AP_Scan> algo1_solution = MAC_approx_location.weighted_center(in,number);
		Iterator<AP_Scan> iter = algo1_solution.iterator();
		try {
			PrintWriter pw = new PrintWriter(out);
			StringBuilder sb = new StringBuilder();
			int i=0;
			while(iter.hasNext()) {
				AP_Scan c = iter.next();
			//	System.out.println(i+" , "+c.toString());
				sb.append(i+","+c.toString()+"\n");
				i++;
			}
			pw.print(sb);
			pw.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
