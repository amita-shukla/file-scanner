import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import static java.nio.file.StandardWatchEventKinds.*;

public class WatchDir {

	private final WatchService watcher;

	WatchDir(Path dir) throws IOException {
		// init watcher
		watcher = FileSystems.getDefault().newWatchService();

		// Register the directory path for watch
		dir.register(watcher, ENTRY_MODIFY);
											
		System.out.println("Watch service registered for: " + dir);

	}

	@SuppressWarnings("unchecked")
	static <T> WatchEvent<T> cast(WatchEvent<?> event) {
		return (WatchEvent<T>) event;
	}

	public void processEvents() {
		while (true) {
			WatchKey key;

			try {
				key = watcher.take();
			} catch (InterruptedException e) {
				return;
			}

			
			for (WatchEvent<?> event : key.pollEvents()) {

				WatchEvent<Path> ev = cast(event);
				Kind<?> kind = event.kind();
				// handle OVERFLOW event
				if (kind == OVERFLOW) {
					continue;
				}

				if (kind == ENTRY_MODIFY ) {
					
					// retrieve file name
					
					Path fileName = (Path) ev.context();
					
					Main.setState(fileName.toString());

				}

				// reset the key
				// if directory is inaccessible, break
				boolean valid = key.reset();
				if (!valid)
					break;

			}
		}
	}

}
