package net.syscon.s4.common;

import java.util.Map;

import org.json.JSONObject;

public abstract class TaskDescriptionParser {
	
	public String getFormType(String description) {
		String formType=null;
		if (description != null) {
			try {
				JSONObject obj = new JSONObject(description);
				  if(obj.get("formType")!=null) {
					  formType= obj.getString("formType");
				  }
					  

				} catch (Exception e) {

			}

		
	}
  return formType;
}
  public abstract Map<String,Object> parseFormData(String description);
	  
	  
	  
	  
	  
	  
	  
  
	
}
