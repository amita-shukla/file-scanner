import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

	static String root;
	static Properties properties;

	public static void main(String[] args) {

		// Provide path of files
		root = System.getProperty("user.dir");
		Path dir = Paths.get(root + "/Properties");
		

		// hard coded names of property files
		String[] files = { dir.toString() + "/propA.properties",
				dir.toString() + "/propB.properties",
				dir.toString() + "/propC.properties" };

		properties = new Properties();

		properties.initProps(files);

		runWatch(dir);

	}

	private static void runWatch(Path dir) {
		WatchDir watch;
		try {
			watch = new WatchDir(dir);
			// run watch
			watch.processEvents();

		} catch (IOException e) {
			System.out.println("IO Exception");
			e.printStackTrace();
		}

	}

	/*
	 * Called from WatchDir.java
	 */
	public static void setState(String fileName) {
		properties.checkProps(root+"/Properties/", fileName);
	}

}
