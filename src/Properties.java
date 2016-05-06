import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

public class Properties {
	ArrayList<Hashtable<String, PropertyValues>> propTables;

	/**
	 * Reads the property file and fills the properties in a hashtable.
	 * 
	 * @param file
	 *            : file path + file name + extention
	 * @return
	 */
	public Hashtable<String, PropertyValues> readProps(String file) {
		Hashtable<String, PropertyValues> ht = new Hashtable<>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(
					new File(file)));

			String line;
			while (((line = br.readLine()) != null) && (!line.isEmpty())) {
				String[] pair = line.trim().split("[-:=]");

				String key = pair[0].trim();
				String value = pair[1].trim();

				PropertyValues props = new PropertyValues();
				props.setNewValue(value);

				ht.put(key, props);

			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ht;
	}

	/**
	 * Initializes the properties of given files. Populates the propTables.
	 * 
	 * @param files
	 *            : Array of complete file names
	 */
	public void initProps(String[] files) {
		propTables = new ArrayList<>();
		for (int i = 0; i < files.length; i++) {
			Hashtable<String, PropertyValues> ht = readProps(files[i]);
			propTables.add(ht);
		}

	}

	/**
	 * Determine which file is changed
	 * 
	 * @param root
	 *            : Path of property file
	 * @param fileName
	 *            : Name of the file
	 */
	public void checkProps(String root, String fileName) {
		// determine the changed file
		switch (fileName) {
		case "propA.properties":
			checkChange(0, root, fileName);
			break;
		case "propB.properties":
			checkChange(1, root, fileName);
			break;
		case "propC.properties":
			checkChange(2, root, fileName);
			break;
		default:
			System.out.println("File not found");
		}
	}

	/**
	 * Takes the necessary changes and outputs results
	 * @param index
	 *            : index of the property file and corresponding hashtable in
	 *            propTables
	 * @param root
	 * @param fileName
	 */
	private void checkChange(int index, String root, String fileName) {
		Hashtable<String, PropertyValues> ht = propTables.get(index);
		Set<String> keys = ht.keySet();
		Set<String> temp = new HashSet<>(keys);

		
		// read the file
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(root
					+ fileName)));
			String line;
			while (((line = br.readLine()) != null)) {
				if (line.isEmpty())
					continue;
				
				String[] pair = line.trim().split("[=:-]");
				String key = pair[0].trim();
				String value = pair[1].trim();

				if (ht.containsKey(key)) {
					// check for modification

					PropertyValues values = ht.get(key);
					temp.remove(key);

					if (!value.equals(values.getNewValue())) {
						values.setOldValue(values.getNewValue());
						values.setNewValue(value);
						ht.put(key, values);
						print(fileName, key, values);
						// break;
					}

				} else {
					// check for addition
					PropertyValues values = new PropertyValues();
					values.setNewValue(value);
					ht.put(key, values);
					print(fileName, key, values);
					// break;
				}

				
			}

			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// check for deletion
		
		if (!temp.isEmpty()) {
			for (String key : temp) {
				PropertyValues values = ht.get(key);
				values.setOldValue(values.getNewValue());
				values.setNewValue(null);
				ht.remove(key);
				print(fileName, key, values);
			}
		}

	}

	private void print(String fileName, String key, PropertyValues values) {
		System.out.println("File changed : " + fileName);
		if (values.getOldValue() == null) {
			// addition
			System.out.println("Property Added : " + key);
			System.out.println("Value added : " + values.getNewValue());
		} else if (values.getNewValue() == null) {
			// deletion
			System.out.println("Property Deleted : " + key);
			System.out.println("Value deleted : " + values.getOldValue());
		} else {
			//modification
			String oldVal = values.getOldValue();
			String newVal = values.getNewValue();
			System.out.println("Property changed : " + key);
			System.out.println("Old Value : " + oldVal);
			System.out.println("New Value : " + newVal);
		}
		System.out.println("------------------------------------------------");
	}
}
