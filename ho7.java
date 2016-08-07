import java.net.InetAddress;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.DirectoryStream;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
public class ho7 {
   // the following strings will become directories in 
   // the dir where the java .class file resides
   public static final String  TARGET = "target_dir/";
   public static final String SOURCE = "source_dir/";

   public static void main(String[] argv) {
     System.out.println(getHostName() );
     String host = getHostName();
     Path target_path = Paths.get (TARGET, host);
     Path source_path = Paths.get (SOURCE);
     createHostDirIfNecessary(target_path);
   }

   private static String getHostName() {
     String host = "unknown_host";
     try {
        host = InetAddress.getLocalHost().getHostName();
        if (host.contains(".")) {
           host = host.substring(0, host.indexOf("."));
        }
     } catch (java.net.UnknownHostException e) {
        System.out.println("UnknownHostException  : "+ e.toString());
     }
      return host;
   }

   public static void createHostDirIfNecessary(Path path) {
      if (!Files.isDirectory(path) || (Files.isRegularFile(path))) {
         try {
            // checks if there is already a file with 
            // same name as the dir we want to create
            if(Files.isRegularFile(path)) {
               Path newPath = Paths.get(path.toString()+"_orig");
               Files.move(path, newPath, REPLACE_EXISTING); 
            }
            Files.createDirectories(path);
         } catch (java.io.IOException e) {
            System.out.println("Problem creating local data host-" 
                      + "specific output directory"+ e.toString());
            e.printStackTrace();  
         }
     } 
   }
}
