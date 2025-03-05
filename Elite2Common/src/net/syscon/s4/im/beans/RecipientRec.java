package net.syscon.s4.im.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RecipientRec implements SQLData {
	@JsonProperty("internetAddress")
	private String internetAddress;
	@JsonProperty("internetAddressClass")
	private String internetAddressClass;
	public String getInternetAddress() {
		return internetAddress;
	}
	public void setInternetAddress(String internetAddress) {
		this.internetAddress = internetAddress;
	}
	public String getInternetAddressClass() {
		return internetAddressClass;
	}
	public void setInternetAddressClass(String internetAddressClass) {
		this.internetAddressClass = internetAddressClass;
	}
	public String getSQLTypeName() {
        return "TAG_WORKFLOW_ADHOC.T_RECIPIENTS"; 
    }
 
 public void readSQL(SQLInput sqlInput, String string) { 
	 try {
		 setInternetAddress(sqlInput.readString());
		setInternetAddressClass(sqlInput.readString());
	} catch (SQLException e) {
		e.printStackTrace();
	}
	 
    }

 public void writeSQL(SQLOutput sqlOutput){ 
        try {
			sqlOutput.writeString(getInternetAddress());
			sqlOutput.writeString(getInternetAddressClass());
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
    }
}
