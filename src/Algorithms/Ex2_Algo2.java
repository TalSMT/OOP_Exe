package Algorithms;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import WiFi_data.AP_Scan;
import WiFi_data.WiFi_Scan;
import WiFi_data.WiFi_Scans;
import java.io.*;

/**
 * This main class represents a simple testing tool for Ex2.2
 * @author benmo
 *
 */
public class Ex2_Algo2 {
	public static void main(String[] a) {
		System.out.println("OOP Course: EX2, Algo2 self testing tool");
		String dir = "data/BM3/comb/";
		String training_file = "_comb_all_.csv";
		String testing_file = "_comb_no_gps_ts2.csv";
		String train = dir+training_file;
		String test = dir+testing_file;
		if(a.length>2) {
			train = a[0];
			test = a[1];
		}
		else {
			System.err.println("\n Wrong parameters for Ex2_Algo2 testing tool should get an input csv file"+
		"\n general input format: java -jar Ex2_Algo2 test.csv train.csv output.csv number_of_points\n if no output file is given an out put file with time based name is generated" 
					);
		}
		int number = 4;
		if(a.length>3) {
			number = new Integer(a[3]);
		}
		long time = new Date().getTime();
		String out = dir+"Algo2_"+number+"_"+time+"_.csv";
		if(a.length>2){
			out = a[2];
		}
		System.out.println("Algo2 is ready to run with "+number+" strongest points");
		System.out.println("Training Set: "+train);
		System.out.println("Testing Set: "+test);
		System.out.println("Output: "+out);
		
		WiFi_Scans ws_train = new WiFi_Scans(train);
		WiFi_Scans ws_test = new WiFi_Scans(test);
		WiFi_Scans algo2_solution = WiFi_Scan_approx_location.approx_Scans_positions(ws_train, ws_test, number);
		algo2_solution.save_to_csv(out);
	}
}
