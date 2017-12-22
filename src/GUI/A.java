/** a simple GEO java example from  
 * https://stackoverflow.com/questions/17598074/google-map-in-java-swing
 * 
 * */

package GUI;
import java.awt.Graphics;
import java.awt.Image;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Tools.Point3D;
public class A {

	//////////////////////////////////////////////////////////
	    public static void main(String[] args) throws IOException {
	        JFrame test = new JFrame("Google Maps Test: for OOP_Ex3");
	        ImageIcon _image = null;
	        try {
	        	Point3D cen = new Point3D(32.104,35.209,660);
	        	String center = "center="+cen.x()+","+cen.y();
	        	int z = 17;
	        	String zoom = "&zoom="+z;
	        	String size = "&size=612x612&scale=2";
	        	//String map = "&maptype=satellite";
	        	String map = "&maptype=roadmap";
	        	//String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?center=32.104728,35.208672&zoom=17&size=612x612&scale=2&maptype=satellite";
	        	String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?"+center+zoom+size+map;
	        	String destinationFile = "image.jpg";
	            String str = destinationFile;
	            URL url = new URL(imageUrl);
	            InputStream is = url.openStream();
	            OutputStream os = new FileOutputStream(destinationFile);

	            byte[] b = new byte[2048];
	            int length;

	            while ((length = is.read(b)) != -1) {
	                os.write(b, 0, length);
	            }
	            
	            is.close();
	            os.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	            System.exit(1);
	        }
	       _image = new ImageIcon((new ImageIcon("image.jpg")).getImage().getScaledInstance(630, 600,
	                java.awt.Image.SCALE_SMOOTH));
	        test.add(new JLabel(_image));
	        test.setVisible(true);
	        test.pack();
	    }
	    public void paint(Graphics g) {
	    	
	    }
}
