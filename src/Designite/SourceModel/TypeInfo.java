package Designite.SourceModel;

public class TypeInfo {

	private SM_Type typeObj;
	private boolean primitiveType;
	private String primitiveObj;
	private boolean parametrizedType;
	
	public SM_Type getTypeObj() {
		return typeObj;
	}
	
	public void setTypeObj(SM_Type typeObj) {
		this.typeObj = typeObj;
	}
	
	public boolean isPrimitiveType() {
		return primitiveType;
	}
	
	public void setPrimitiveType(boolean primitiveType) {
		this.primitiveType = primitiveType;
	}
	
	public String getPrimitiveObj() {
		return primitiveObj;
	}
	
	public void setPrimitiveObj(String primitiveObj) {
		this.primitiveObj = primitiveObj;
	}

	public boolean isParametrizedType() {
		return parametrizedType;
	}

	public void setParametrizedType(boolean parametrizedType) {
		this.parametrizedType = parametrizedType;
	}

}