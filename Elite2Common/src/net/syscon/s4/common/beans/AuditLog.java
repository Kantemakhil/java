package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.validators.GlobalValidation;
import net.syscon.s4.sa.admin.beans.ModuleTables;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class AuditLog extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("vHeaderBlock")
	private VHeaderBlock vHeaderBlock;

	@JsonProperty("moduleTableList")
	private List<ModuleTables> moduleTableList;

	@JsonProperty("moduleName")
	private String moduleName;

	@JsonProperty("viewAuditFlag")
	private String viewAuditFlag;

	public String getModuleName() {
		return moduleName;
	}

	public VHeaderBlock getvHeaderBlock() {
		return vHeaderBlock;
	}

	public void setvHeaderBlock(VHeaderBlock vHeaderBlock) {
		this.vHeaderBlock = vHeaderBlock;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public List<ModuleTables> getModuleTableList() {
		return moduleTableList;
	}

	public void setModuleTableList(List<ModuleTables> moduleTableList) {
		this.moduleTableList = moduleTableList;
	}

	public String getViewAuditFlag() {
		return viewAuditFlag;
	}

	public void setViewAuditFlag(String viewAuditFlag) {
		this.viewAuditFlag = viewAuditFlag;
	}

}
