import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyRecorder {
	Properties propA;
	Properties propB;
	Properties propC;

	PropertyRecorder(String[] files, String root) {

		propA = initProp(root +"\\Properties\\" +files[0]);
		propB = initProp(root +"\\Properties\\" + files[1]);
		propC = initProp(root+"\\Properties\\"  + files[2]);

	}

	private Properties initProp(String fileName) {
		Properties props = new Properties();
		try {
			System.out.println("sdn "+fileName);
			InputStream in = new FileInputStream(fileName);
			props.load(in);
			//in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getClass());
			e.printStackTrace();
		}
		return props;
	}

	public void compareProps(String root, String fileName) {
		Properties newProps = initProp(root+"/Properties/"  + fileName);
		Properties oldProps = getProps(fileName);

		if (newProps.size() > oldProps.size()) {
			// ADD
			checkAdd(newProps, oldProps, fileName);
		} else if (newProps.size() < oldProps.size()) {
			// DELETE
			checkDelete(newProps, oldProps, fileName);
		} else {
			// MODIFY
			checkModify(newProps, oldProps, fileName);
		}

	}

	private void checkModify(Properties newProps, Properties oldProps, String fileName) {
		for(Object key : oldProps.keySet()){
			if(!newProps.get(key).equals(oldProps.get(key))){
				printResult(fileName,key.toString(),oldProps.get(key).toString(),newProps.get(key).toString());
				//newProps.put(key, oldProps.get(key));
				break;
			}
		}
		
		if(fileName.equals("propA.properties")){
			propA = newProps;
		}else if(fileName.equals("propB.properties")){
			propB = newProps;
		}else{
			propC = newProps;
		}
		
	}

	private void checkDelete(Properties newProps, Properties oldProps, String fileName) {
		
		for(Object key : oldProps.keySet()){
			if(!newProps.containsKey(key)){
				printResult(fileName,key.toString(),oldProps.get(key).toString(),"null");
				//newProps.put(key, oldProps.get(key));
				break;
			}
		}
		
		if(fileName.equals("propA.properties")){
			propA = newProps;
		}else if(fileName.equals("propB.properties")){
			propB = newProps;
		}else{
			propC = newProps;
		}
		
	}

	private void checkAdd(Properties newProps, Properties oldProps, String fileName) {
		
		for(Object key : newProps.keySet()){
			if(!oldProps.containsKey(key)){
				
				printResult(fileName,key.toString(),"none",newProps.get(key).toString());
				//oldProps.put(key, newProps.get(key));
				break;
			}
		}
		
		if(fileName.equals("propA.properties")){
			propA = newProps;
		}else if(fileName.equals("propB.properties")){
			propB = newProps;
		}else{
			propC = newProps;
		}
		
		
	}

	private void printResult(String fileName, String key, String oldValue, String newValue) {
		System.out.println("File changed : " + fileName);
		System.out.println("Property Chnaged : " + (String)key);
		System.out.println("Old Value : " + oldValue);
		System.out.println("New Value : " + newValue);
	}

	private Properties getProps(String fileName) {
		if(fileName.equals("propA.properties")){
			return propA;
		}else if(fileName.equals("propB.properties")){
			return propB;
		}else{
			return propC;
		}
		
	}
}
