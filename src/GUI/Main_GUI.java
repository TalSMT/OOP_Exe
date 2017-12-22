package GUI;

import javax.swing.JFrame;

public class Main_GUI {

	public static void main(String[] args) {
		String s0 = "data/BM2/comb/_comb_all_.csv";
		
		JFrame win = new Simple_MAP_GUI(s0);
		win.show();
	}

}
