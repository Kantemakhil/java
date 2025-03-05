package net.syscon.s4.common.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportInputControll extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String[] dataType;
	private String description;
	private String id;
	private String label;
	private boolean mandatory;
	private String[] masterDependencies;
	private boolean reedonly;
	private String[] slaveDependencies;
	private InputState state;
	private String type;
	private String uri;
	private boolean visible;
	private String value;
	private Object[] validationRules;
	
	/*<dataType>
    <strictMax>false</strictMax>
    <strictMin>false</strictMin>
    <type>text</type>
</dataType>
<id>my_offender_id</id>
<label>my_offender_id</label>
<mandatory>false</mandatory>
<masterDependencies/>
<readOnly>false</readOnly>
<slaveDependencies/>
<state>
    <id>my_offender_id</id>
    <uri>/reports/offender_param_report_files/my_offender_id</uri>
    <value>~NULL~</value>
</state>
<type>singleValueText</type>
<uri>repo:/reports/offender_param_report_files/my_offender_id</uri>
<visible>true</visible>*/
	public String[] getDataType() {
		return dataType;
	}
	public void setDataType(String[] dataType) {
		this.dataType = dataType;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public boolean isMandatory() {
		return mandatory;
	}
	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}
	public boolean isReedonly() {
		return reedonly;
	}
	public void setReedonly(boolean reedonly) {
		this.reedonly = reedonly;
	}
	public InputState getState() {
		return state;
	}
	public void setState(InputState state) {
		this.state = state;
	}
	public String[] getMasterDependencies() {
		return masterDependencies;
	}
	public void setMasterDependencies(String[] masterDependencies) {
		this.masterDependencies = masterDependencies;
	}
	public String[] getSlaveDependencies() {
		return slaveDependencies;
	}
	public void setSlaveDependencies(String[] slaveDependencies) {
		this.slaveDependencies = slaveDependencies;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Object[] getValidationRules() {
		return validationRules;
	}
	public void setValidationRules(Object[] validationRules) {
		this.validationRules = validationRules;
	}
	
	

}
