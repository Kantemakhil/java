package net.syscon.s4.inst.movements.housingchanges.beans;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * The persistent class for the V_LIV_UNIT database table.
 * 
 */
public class VLivUnits implements Serializable {
	private static final long serialVersionUID = 1L;

	private String activeFlag;

	private String agyLocId;

	private BigDecimal capacity;

	private String description;

	private String level1Code;

	private String level1Desc;

	private BigDecimal level1ListSeq;

	private String level1Type;

	private String level2Code;

	private String level2Desc;

	private BigDecimal level2ListSeq;

	private String level2Type;

	private String level3Code;

	private String level3Desc;

	private BigDecimal level3ListSeq;

	private String level3Type;

	private String level4Code;

	private String level4Desc;

	private BigDecimal level4ListSeq;

	private String level4Type;

	private BigDecimal livingUnitId;

	public VLivUnits() {
	}

	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getAgyLocId() {
		return this.agyLocId;
	}

	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

	public BigDecimal getCapacity() {
		return this.capacity;
	}

	public void setCapacity(BigDecimal capacity) {
		this.capacity = capacity;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLevel1Code() {
		return this.level1Code;
	}

	public void setLevel1Code(String level1Code) {
		this.level1Code = level1Code;
	}

	public String getLevel1Desc() {
		return this.level1Desc;
	}

	public void setLevel1Desc(String level1Desc) {
		this.level1Desc = level1Desc;
	}

	public BigDecimal getLevel1ListSeq() {
		return this.level1ListSeq;
	}

	public void setLevel1ListSeq(BigDecimal level1ListSeq) {
		this.level1ListSeq = level1ListSeq;
	}

	public String getLevel1Type() {
		return this.level1Type;
	}

	public void setLevel1Type(String level1Type) {
		this.level1Type = level1Type;
	}

	public String getLevel2Code() {
		return this.level2Code;
	}

	public void setLevel2Code(String level2Code) {
		this.level2Code = level2Code;
	}

	public String getLevel2Desc() {
		return this.level2Desc;
	}

	public void setLevel2Desc(String level2Desc) {
		this.level2Desc = level2Desc;
	}

	public BigDecimal getLevel2ListSeq() {
		return this.level2ListSeq;
	}

	public void setLevel2ListSeq(BigDecimal level2ListSeq) {
		this.level2ListSeq = level2ListSeq;
	}

	public String getLevel2Type() {
		return this.level2Type;
	}

	public void setLevel2Type(String level2Type) {
		this.level2Type = level2Type;
	}

	public String getLevel3Code() {
		return this.level3Code;
	}

	public void setLevel3Code(String level3Code) {
		this.level3Code = level3Code;
	}

	public String getLevel3Desc() {
		return this.level3Desc;
	}

	public void setLevel3Desc(String level3Desc) {
		this.level3Desc = level3Desc;
	}

	public BigDecimal getLevel3ListSeq() {
		return this.level3ListSeq;
	}

	public void setLevel3ListSeq(BigDecimal level3ListSeq) {
		this.level3ListSeq = level3ListSeq;
	}

	public String getLevel3Type() {
		return this.level3Type;
	}

	public void setLevel3Type(String level3Type) {
		this.level3Type = level3Type;
	}

	public String getLevel4Code() {
		return this.level4Code;
	}

	public void setLevel4Code(String level4Code) {
		this.level4Code = level4Code;
	}

	public String getLevel4Desc() {
		return this.level4Desc;
	}

	public void setLevel4Desc(String level4Desc) {
		this.level4Desc = level4Desc;
	}

	public BigDecimal getLevel4ListSeq() {
		return this.level4ListSeq;
	}

	public void setLevel4ListSeq(BigDecimal level4ListSeq) {
		this.level4ListSeq = level4ListSeq;
	}

	public String getLevel4Type() {
		return this.level4Type;
	}

	public void setLevel4Type(String level4Type) {
		this.level4Type = level4Type;
	}

	public BigDecimal getLivingUnitId() {
		return this.livingUnitId;
	}

	public void setLivingUnitId(BigDecimal livingUnitId) {
		this.livingUnitId = livingUnitId;
	}

}
