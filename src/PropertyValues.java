
public class PropertyValues {

	private String oldValue;
	private String newValue;
	
	public PropertyValues(){
		oldValue = null;
		newValue = null;
	}
	
	//Getters
	public String getOldValue(){
		return oldValue;
	}
	
	public String getNewValue(){
		return newValue;
	}
	
	//Setters 
	public void setOldValue(String oldValue){
		this.oldValue = oldValue;
	}
	
	public void setNewValue(String newValue){
		this.newValue = newValue;
	}
}
