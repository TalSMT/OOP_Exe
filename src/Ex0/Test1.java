package Ex0;
import java.io.File;

public class Test1{
   public static void main(String[] args) {
      File file1 = new File(".");
      File[] files = file1.listFiles();
      String[] names = file1.list();
      System.out.println(file1.toString());
      for(int i=0;i<names.length;i++) {
    	  System.out.println(i+") "+names[i]+ "   is Dir: "+files[i].isDirectory());
      }
   }
}
