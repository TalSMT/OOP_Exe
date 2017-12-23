package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Tools.Point3D;
import Tools.Rect3D;
import WiFi_data.WiFi_Scan;
import WiFi_data.WiFi_Scans;
/**
 * This is a simple demo file for presenting GEO information on simple map.
 * The GUI is very simple and inaccurate. 
 * @author benmo
 *
 */
public class Simple_MAP_GUI extends JFrame{
	private Point3D _center = null;
	private static String title = "Google Maps Test: for OOP_Ex3";
	private ImageIcon _image = null;
    private Rect3D _world, _frame;
    private WiFi_Scans _samples;
    
     public Simple_MAP_GUI(String file) {
    	 super(title);
    	 double lat_c = 32.104;
    	 double lon_c = 35.210;
    	 int zoom = 17; // [14-17]
    	 int factor = 17-zoom;
    	 double norm = Math.pow(2, factor);
    	 double eps_x=0.00275*norm, eps_y=0.00275*norm;
    	 _center = new Point3D(lat_c,lon_c,660);
    	 Point3D min = new Point3D(_center.x()-eps_x,_center.y()-eps_y);
    	 Point3D max = new Point3D(_center.x()+eps_x,_center.y()+eps_y);
    	 _world = new Rect3D(min,max);
    	 _frame = new Rect3D(new Point3D(0,0), new Point3D(530,600));
    	init(file, zoom); 
    	this.add(new JLabel(_image));
	    this.setVisible(true);
	    this.pack();
     }
     
     private void init(String file, int z) {
      try {
      	_samples = new WiFi_Scans(file);
      	String center = "center="+_center.x()+","+_center.y();
      	String zoom = "&zoom="+z;
      	String size = "&size=600x600&scale=1";
      	String map = "&maptype=satellite";
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
     _image = new ImageIcon((new ImageIcon("image.jpg")).getImage().getScaledInstance(600, 600,
              java.awt.Image.SCALE_SMOOTH));
   }
     
  public void paint(Graphics g) {
  		g.drawImage(_image.getImage(), 0, 0, null, null);
  		if(_samples!=null) {
  			g.setColor(Color.RED);
  			int r = 4;
  			for(int i=0;i<_samples.size();i++) {
  				WiFi_Scan c = _samples.get(i);
  				Point3D f = this.w2f(c.get_pos());
  				g.fillOval(f.ix()-r, f.iy()-r, r, r);
  			}
  		}
  }
  /**
   * this function converts a lat/lon point to a frame pixel.
   * This code is very simple it converts: lat(north)--> -y  |  lon (east) to x. 
   * @param p (lat / lon) geo point
   * @return a pixel in frame
   */
  public Point3D w2f(Point3D p) {
	  double x0  =_world.get_min().x();
	  double y0  =_world.get_min().y();
	  double dy = (p.x()-x0) / _world.dx();
	  double dx = (p.y() - y0) / _world.dy();
	  double x =  x0+ _frame.dx()*dx;
	  double y = _frame.get_max().y() - (_frame.dy()*dy);
	  return new Point3D(x,y,0);
  } 
}
