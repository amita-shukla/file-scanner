import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import static java.nio.file.StandardWatchEventKinds.*;

public class WatchDir {

	private final WatchService watcher;

	WatchDir(Path dir) throws IOException {
		// init watcher
		watcher = FileSystems.getDefault().newWatchService();

		// Register the directory path for watch
		dir.register(watcher, ENTRY_CREATE, ENTRY_MODIFY);//todo: register for modify event only
		System.out.println("Watch service registered for: " + dir);

	}

	public void processEvents() {
		while (true) {
			WatchKey key;

			try {
				key = watcher.take();
			} catch (InterruptedException e) {
				return;
			}
			
			for(WatchEvent<?> event : key.pollEvents()){
				
				@SuppressWarnings("unchecked")
				WatchEvent<Path> kind = (WatchEvent<Path>) event.kind();
				Path fileName = (Path)event.context();
				//todo : call a notify method
				System.out.println("file changed : "+ fileName +" Event : "+ kind);
				
			}
		}
	}

}
