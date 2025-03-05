package net.syscon.s4.sa.admin.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookmarkRec implements SQLData {
	
	@JsonProperty("param_id")
	String param_id;
	
	@JsonProperty("description")
	String description;
	
	@JsonProperty("param_type")
	String param_type;
	
	@JsonProperty("data_type")
	String data_type;

	/**
	 * @return the param_id
	 */
	public String getParam_id() {
		return param_id;
	}

	/**
	 * @param param_id the param_id to set
	 */
	public void setParam_id(final String param_id) {
		this.param_id = param_id;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @return the param_type
	 */
	public String getParam_type() {
		return param_type;
	}

	/**
	 * @param param_type the param_type to set
	 */
	public void setParam_type(final String param_type) {
		this.param_type = param_type;
	}

	/**
	 * @return the data_type
	 */
	public String getData_type() {
		return data_type;
	}

	/**
	 * @param data_type the data_type to set
	 */
	public void setData_type(final String data_type) {
		this.data_type = data_type;
	}
	
	public String getSQLTypeName() {
        return "OUMBMARK.G_BMK_PARAMS_TAB";
	}
	 public void readSQL(SQLInput sqlInput, String string) { 
		 try {
			setParam_id(sqlInput.readString());
			setDescription(sqlInput.readString());
			 setParam_type(sqlInput.readString());
			 setData_type(sqlInput.readString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	    }

	 public void writeSQL(SQLOutput sqlOutput){ 
	        try {
				sqlOutput.writeString(getParam_id());
				sqlOutput.writeString(getDescription());
		        sqlOutput.writeString(getParam_type());
		        sqlOutput.writeString(getData_type());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	    }

}
