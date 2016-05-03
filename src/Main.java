import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Main {

	
	
	public static void main(String[] args) {
		//Provide path of files
		String root = System.getProperty("user.dir");
		Path dir = Paths.get(root + "/Properties");
		runWatch(dir);
		

	}

	private static void runWatch(Path dir) {
		WatchDir watch;
		try {
			watch = new WatchDir(dir);
			//run watch
			watch.processEvents();
			
		} catch (IOException e) {
			System.out.println("IO Exception");
			e.printStackTrace();
		}
		
		
	}
	
	public static void setState(String fileName){
		
	}

}
