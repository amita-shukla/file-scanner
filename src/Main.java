import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Main {

	static String root;
	static PropertyRecorder pr;
	public static void main(String[] args) {
		//hardcoded names of property files
		
		String[] files = {"propA.properties", "propB.properties","propC.properties"};
		
		//Provide path of files
		root = System.getProperty("user.dir");
		Path dir = Paths.get(root + "/Properties");
		
		pr = new PropertyRecorder(files,root);
		
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
		pr.compareProps(root ,fileName);
	}

}
