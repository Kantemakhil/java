package net.syscon.s4.sa.admin.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class AllTabColumns extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("owner")
	private String owner;
	
	@JsonProperty("tableName")
	private String tableName;
	
	
	@JsonProperty("columnName")
	private String columnName;
	
	
	@JsonProperty("dataType")
	private String dataType;
	
	
	@JsonProperty("dataTypeMod")
	private String dataTypeMod;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("code")
	private String code;
	
	
	@JsonProperty("dataTypeOwner")
	private String dataTypeOwner;
	
	
	@JsonProperty("dataLength")
	private Long dataLength;
	
	
	@JsonProperty("dataPrecision")
	private String dataPrecision;
	
	
	@JsonProperty("dataScale")
	private String dataScale;
	
	
	@JsonProperty("nullable")
	private String nullable;
	
	
	@JsonProperty("columnId")
	private Long columnId;
	
	
	@JsonProperty("defaultLength")
	private String defaultLength;
	
	
	@JsonProperty("dataDefault")
	private String dataDefault;
	
	
	@JsonProperty("numDistinct")
	private Long numDistinct;
	
	
	@JsonProperty("lowValue")
	private String lowValue;
	
	
	@JsonProperty("highValue")
	private String highValue;
	
	
	@JsonProperty("density")
	private Long density;
	
	@JsonProperty("numNulls")
	private Long numNulls;
	
	
	@JsonProperty("numBuckets")
	private Long numBuckets;
	
	
	@JsonProperty("lastAnalyzed")
	private Date lastAnalyzed;
	
	
	@JsonProperty("sampleSize")
	private Long sampleSize;
	
	
	@JsonProperty("characterSetName")
	private String characterSetName;
	
	
	@JsonProperty("charColDeclLength")
	private Long charColDeclLength;
	
	
	@JsonProperty("globalStats")
	private String globalStats;
	
	
	@JsonProperty("userStats")
	private String userStats;
	
	
	@JsonProperty("avgColLen")
	private Long avgColLen;
	
	
	@JsonProperty("charLength")
	private Long charLength;
	
	
	@JsonProperty("charUsed")
	private String charUsed;
	
	
	@JsonProperty("v80FmtImage")
	private String v80FmtImage;
	
	@JsonProperty("dataUpgraded")
	private String dataUpgraded;
	
	
	@JsonProperty("histogram")
	private String histogram;


	public String getOwner() {
		return owner;
	}


	public void setOwner(final String owner) {
		this.owner = owner;
	}


	public String getTableName() {
		return tableName;
	}


	public void setTableName(final String tableName) {
		this.tableName = tableName;
	}


	public String getColumnName() {
		return columnName;
	}


	public void setColumnName(final String columnName) {
		this.columnName = columnName;
	}


	public String getDataType() {
		return dataType;
	}


	public void setDataType(final String dataType) {
		this.dataType = dataType;
	}


	public String getDataTypeMod() {
		return dataTypeMod;
	}


	public void setDataTypeMod(final String dataTypeMod) {
		this.dataTypeMod = dataTypeMod;
	}


	public String getDataTypeOwner() {
		return dataTypeOwner;
	}


	public void setDataTypeOwner(final String dataTypeOwner) {
		this.dataTypeOwner = dataTypeOwner;
	}


	public Long getDataLength() {
		return dataLength;
	}


	public void setDataLength(final Long dataLength) {
		this.dataLength = dataLength;
	}


	public String getDataPrecision() {
		return dataPrecision;
	}


	public void setDataPrecision(final String dataPrecision) {
		this.dataPrecision = dataPrecision;
	}


	public String getDataScale() {
		return dataScale;
	}


	public void setDataScale(final String dataScale) {
		this.dataScale = dataScale;
	}


	public String getNullable() {
		return nullable;
	}


	public void setNullable(final String nullable) {
		this.nullable = nullable;
	}


	public Long getColumnId() {
		return columnId;
	}


	public void setColumnId(final Long columnId) {
		this.columnId = columnId;
	}


	public String getDefaultLength() {
		return defaultLength;
	}


	public void setDefaultLength(final String defaultLength) {
		this.defaultLength = defaultLength;
	}


	public String getDataDefault() {
		return dataDefault;
	}


	public void setDataDefault(final String dataDefault) {
		this.dataDefault = dataDefault;
	}


	public Long getNumDistinct() {
		return numDistinct;
	}


	public void setNumDistinct(final Long numDistinct) {
		this.numDistinct = numDistinct;
	}


	public String getLowValue() {
		return lowValue;
	}


	public void setLowValue(final String lowValue) {
		this.lowValue = lowValue;
	}


	public String getHighValue() {
		return highValue;
	}


	public void setHighValue(final String highValue) {
		this.highValue = highValue;
	}


	public Long getDensity() {
		return density;
	}


	public void setDensity(final Long density) {
		this.density = density;
	}


	public Long getNumNulls() {
		return numNulls;
	}


	public void setNumNulls(final Long numNulls) {
		this.numNulls = numNulls;
	}


	public Long getNumBuckets() {
		return numBuckets;
	}


	public void setNumBuckets(final Long numBuckets) {
		this.numBuckets = numBuckets;
	}


	public Date getLastAnalyzed() {
		return lastAnalyzed;
	}


	public void setLastAnalyzed(final Date lastAnalyzed) {
		this.lastAnalyzed = lastAnalyzed;
	}


	public Long getSampleSize() {
		return sampleSize;
	}


	public void setSampleSize(final Long sampleSize) {
		this.sampleSize = sampleSize;
	}


	public String getCharacterSetName() {
		return characterSetName;
	}


	public void setCharacterSetName(final String characterSetName) {
		this.characterSetName = characterSetName;
	}


	public Long getCharColDeclLength() {
		return charColDeclLength;
	}


	public void setCharColDeclLength(final Long charColDeclLength) {
		this.charColDeclLength = charColDeclLength;
	}


	public String getGlobalStats() {
		return globalStats;
	}


	public void setGlobalStats(final String globalStats) {
		this.globalStats = globalStats;
	}


	public String getUserStats() {
		return userStats;
	}


	public void setUserStats(final String userStats) {
		this.userStats = userStats;
	}


	public Long getAvgColLen() {
		return avgColLen;
	}


	public void setAvgColLen(final Long avgColLen) {
		this.avgColLen = avgColLen;
	}


	public Long getCharLength() {
		return charLength;
	}


	public void setCharLength(final Long charLength) {
		this.charLength = charLength;
	}


	public String getCharUsed() {
		return charUsed;
	}


	public void setCharUsed(final String charUsed) {
		this.charUsed = charUsed;
	}


	public String getV80FmtImage() {
		return v80FmtImage;
	}


	public void setV80FmtImage(final String v80FmtImage) {
		this.v80FmtImage = v80FmtImage;
	}


	public String getDataUpgraded() {
		return dataUpgraded;
	}


	public void setDataUpgraded(final String dataUpgraded) {
		this.dataUpgraded = dataUpgraded;
	}


	public String getHistogram() {
		return histogram;
	}


	public void setHistogram(final String histogram) {
		this.histogram = histogram;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}
	
	 
	
}
