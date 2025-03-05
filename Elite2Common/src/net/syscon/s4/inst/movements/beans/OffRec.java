package net.syscon.s4.inst.movements.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OffRec implements SQLData  {
	@JsonProperty("offender_id_display")
	String offender_id_display;
	
	@JsonProperty("last_name")
	String last_name;
	
	@JsonProperty("first_name")
	String first_name;

	/**
	 * @return the offender_id_display
	 */
	public String getOffender_id_display() {
		return offender_id_display;
	}

	/**
	 * @param offender_id_display the offender_id_display to set
	 */
	public void setOffender_id_display(String offender_id_display) {
		this.offender_id_display = offender_id_display;
	}

	/**
	 * @return the last_name
	 */
	public String getLast_name() {
		return last_name;
	}

	/**
	 * @param last_name the last_name to set
	 */
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	/**
	 * @return the first_name
	 */
	public String getFirst_name() {
		return first_name;
	}

	/**
	 * @param first_name the first_name to set
	 */
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	
	 public String getSQLTypeName() {
	        return "TAG_CORE.OFF_INFO_TYPE"; 
	    }
	 
	 public void readSQL(SQLInput sqlInput, String string) { 
		 try {
			setFirst_name(sqlInput.readString());
			setLast_name(sqlInput.readString());
			 setOffender_id_display(sqlInput.readString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	    }

	 public void writeSQL(SQLOutput sqlOutput){ 
	        try {
				sqlOutput.writeString(getFirst_name());
				sqlOutput.writeString(getLast_name());
		        sqlOutput.writeString(getOffender_id_display());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	    }
}
