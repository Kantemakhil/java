package syscon.s4.iwp.base;
/**
 * 
 * @version 1.0.0
 * @author Alexander Kapustin
 * TAG 10g PJC
 * File compressor using GZIP method
 * London, 2005
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public final class Worker  {
  private final static int bufSize = 2048;
  private int len = 0;

  public Worker() {
  }
	public static void main(String... args) {
		Worker worker = new Worker();
		worker.compress("D:\\eva\\SASH_Plan_v1.docx", "D:\\\\eva\\\\SASH_Plan_v1.gz");
	}
  
  public boolean compress(String inFile, String outFile){
    FileInputStream fin = null;
    GZIPOutputStream out = null;
    try{
       // open file
   fin = new FileInputStream(inFile);
   // prepare output
   out = new GZIPOutputStream(new FileOutputStream(outFile));
   // transfer bytes
   len = 0;
   byte[] buf = new byte[bufSize];
   while((len = fin.read(buf)) > 0){
     out.write(buf, 0, len);
   }
   fin.close();
   // complete
       out.finish();
       out.close();    
       return true;
    }catch(Throwable e){
       try{
         if (fin != null) fin.close();
         if (out != null) out.close();
       }catch(Throwable ex){}
       return false;
    }
  }
  
  public boolean uncompress(String inFile, String outFile){
    GZIPInputStream fin = null;
    OutputStream out = null;
    try{
       // open output file
       out = new FileOutputStream(outFile);
       fin = new GZIPInputStream(new FileInputStream(inFile));
       //transfer bytes
       byte[] buf = new byte[bufSize];
       len = 0;
       while((len = fin.read(buf)) > 0){
         out.write(buf, 0, len);
       }
       // close
       fin.close();
       out.close();
       return true;
    }catch(Throwable e){
       try{
         if (fin != null) fin.close();
         if (out != null) out.close();
       }catch(Throwable ex){}
       return false;
    }
  }
}
