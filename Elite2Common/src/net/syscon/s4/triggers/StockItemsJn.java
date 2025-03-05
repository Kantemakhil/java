package net.syscon.s4.triggers;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class StockItemsJn extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String jnOperation;
	private String jnOracleUser;
	private Date jnDatetime;
	private String jnNotes;
	private String jnAppln;
	private BigDecimal jnSession;
	private Long stockItemId;
	private String caseloadId;
	private String stockLocId;
	private String stockItemCode;
	private String productType;
	private String stockItemCat;
	private Long primaryVendorId;
	private Long secondaryVendorId;
	private String itemName;
	private BigDecimal currentCount;
	private Double reorderPoint;
	private BigDecimal sellingPrice;
	private String taxable1Flag;
	private String taxable2Flag;
	private String taxable3Flag;
	private String indigentFlag;
	private String onOrderFlag;
	private BigDecimal avgItemCost;
	private BigDecimal newSellingPrice;
	private BigDecimal minItemsToOrder;
	private Date newPriceEffDate;
	private BigDecimal maxItemsToOrder;
	private Date inventoryTakenDate;
	private String primaryVendorItemCode;
	private String secondaryVendorItemCode;
	private String containerCode;
	private Double minPurchaseAge;
	private String containsMetalFlag;
	private BigDecimal maxSalesQuantity;
	private BigDecimal unitDepositCost;
	private BigDecimal currentCost;
	private BigDecimal caselotStockItemId;
	private BigDecimal itemsPerCaselot;
	private BigDecimal markupPercentage;
	private String activeFlag;
	private String leadTime;
	private BigDecimal quantityOnOrder;
	private Date confirmedDate;
	private String limitPeriodType;
	private BigDecimal periodQuantityLimit;
	private String stockItemLocation1;
	private String stockItemLocation2;
	private String sexCode;
	private BigDecimal substituteItemId;
	private String spendableLimitFlag;
	private String substituteItemCode;
	private String inventoryOverdraftFlag;
	private Date createDatetime;
	private String createUserId;
	private Date modifyDatetime;
	private String modifyUserId;
	private String sealFlag;

	public String getJnOperation() {
		return jnOperation;
	}

	public void setJnOperation(String jnOperation) {
		this.jnOperation = jnOperation;
	}

	public String getJnOracleUser() {
		return jnOracleUser;
	}

	public void setJnOracleUser(String jnOracleUser) {
		this.jnOracleUser = jnOracleUser;
	}

	public Date getJnDatetime() {
		return jnDatetime;
	}

	public void setJnDatetime(Date jnDatetime) {
		this.jnDatetime = jnDatetime;
	}

	public String getJnNotes() {
		return jnNotes;
	}

	public void setJnNotes(String jnNotes) {
		this.jnNotes = jnNotes;
	}

	public String getJnAppln() {
		return jnAppln;
	}

	public void setJnAppln(String jnAppln) {
		this.jnAppln = jnAppln;
	}

	public BigDecimal getJnSession() {
		return jnSession;
	}

	public void setJnSession(BigDecimal jnSession) {
		this.jnSession = jnSession;
	}

	public Long getStockItemId() {
		return stockItemId;
	}

	public void setStockItemId(Long stockItemId) {
		this.stockItemId = stockItemId;
	}

	public String getCaseloadId() {
		return caseloadId;
	}

	public void setCaseloadId(String caseloadId) {
		this.caseloadId = caseloadId;
	}

	public String getStockLocId() {
		return stockLocId;
	}

	public void setStockLocId(String stockLocId) {
		this.stockLocId = stockLocId;
	}

	public String getStockItemCode() {
		return stockItemCode;
	}

	public void setStockItemCode(String stockItemCode) {
		this.stockItemCode = stockItemCode;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getStockItemCat() {
		return stockItemCat;
	}

	public void setStockItemCat(String stockItemCat) {
		this.stockItemCat = stockItemCat;
	}

	public Long getPrimaryVendorId() {
		return primaryVendorId;
	}

	public void setPrimaryVendorId(Long primaryVendorId) {
		this.primaryVendorId = primaryVendorId;
	}

	public Long getSecondaryVendorId() {
		return secondaryVendorId;
	}

	public void setSecondaryVendorId(Long secondaryVendorId) {
		this.secondaryVendorId = secondaryVendorId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public BigDecimal getCurrentCount() {
		return currentCount;
	}

	public void setCurrentCount(BigDecimal currentCount) {
		this.currentCount = currentCount;
	}

	public Double getReorderPoint() {
		return reorderPoint;
	}

	public void setReorderPoint(Double reorderPoint) {
		this.reorderPoint = reorderPoint;
	}

	public BigDecimal getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public String getTaxable1Flag() {
		return taxable1Flag;
	}

	public void setTaxable1Flag(String taxable1Flag) {
		this.taxable1Flag = taxable1Flag;
	}

	public String getTaxable2Flag() {
		return taxable2Flag;
	}

	public void setTaxable2Flag(String taxable2Flag) {
		this.taxable2Flag = taxable2Flag;
	}

	public String getTaxable3Flag() {
		return taxable3Flag;
	}

	public void setTaxable3Flag(String taxable3Flag) {
		this.taxable3Flag = taxable3Flag;
	}

	public String getIndigentFlag() {
		return indigentFlag;
	}

	public void setIndigentFlag(String indigentFlag) {
		this.indigentFlag = indigentFlag;
	}

	public String getOnOrderFlag() {
		return onOrderFlag;
	}

	public void setOnOrderFlag(String onOrderFlag) {
		this.onOrderFlag = onOrderFlag;
	}

	public BigDecimal getAvgItemCost() {
		return avgItemCost;
	}

	public void setAvgItemCost(BigDecimal avgItemCost) {
		this.avgItemCost = avgItemCost;
	}

	public BigDecimal getNewSellingPrice() {
		return newSellingPrice;
	}

	public void setNewSellingPrice(BigDecimal newSellingPrice) {
		this.newSellingPrice = newSellingPrice;
	}

	public BigDecimal getMinItemsToOrder() {
		return minItemsToOrder;
	}

	public void setMinItemsToOrder(BigDecimal minItemsToOrder) {
		this.minItemsToOrder = minItemsToOrder;
	}

	public Date getNewPriceEffDate() {
		return newPriceEffDate;
	}

	public void setNewPriceEffDate(Date newPriceEffDate) {
		this.newPriceEffDate = newPriceEffDate;
	}

	public BigDecimal getMaxItemsToOrder() {
		return maxItemsToOrder;
	}

	public void setMaxItemsToOrder(BigDecimal maxItemsToOrder) {
		this.maxItemsToOrder = maxItemsToOrder;
	}

	public Date getInventoryTakenDate() {
		return inventoryTakenDate;
	}

	public void setInventoryTakenDate(Date inventoryTakenDate) {
		this.inventoryTakenDate = inventoryTakenDate;
	}

	public String getPrimaryVendorItemCode() {
		return primaryVendorItemCode;
	}

	public void setPrimaryVendorItemCode(String primaryVendorItemCode) {
		this.primaryVendorItemCode = primaryVendorItemCode;
	}

	public String getSecondaryVendorItemCode() {
		return secondaryVendorItemCode;
	}

	public void setSecondaryVendorItemCode(String secondaryVendorItemCode) {
		this.secondaryVendorItemCode = secondaryVendorItemCode;
	}

	public String getContainerCode() {
		return containerCode;
	}

	public void setContainerCode(String containerCode) {
		this.containerCode = containerCode;
	}

	public Double getMinPurchaseAge() {
		return minPurchaseAge;
	}

	public void setMinPurchaseAge(Double minPurchaseAge) {
		this.minPurchaseAge = minPurchaseAge;
	}

	public String getContainsMetalFlag() {
		return containsMetalFlag;
	}

	public void setContainsMetalFlag(String containsMetalFlag) {
		this.containsMetalFlag = containsMetalFlag;
	}

	public BigDecimal getMaxSalesQuantity() {
		return maxSalesQuantity;
	}

	public void setMaxSalesQuantity(BigDecimal maxSalesQuantity) {
		this.maxSalesQuantity = maxSalesQuantity;
	}

	public BigDecimal getUnitDepositCost() {
		return unitDepositCost;
	}

	public void setUnitDepositCost(BigDecimal unitDepositCost) {
		this.unitDepositCost = unitDepositCost;
	}

	public BigDecimal getCurrentCost() {
		return currentCost;
	}

	public void setCurrentCost(BigDecimal currentCost) {
		this.currentCost = currentCost;
	}

	public BigDecimal getCaselotStockItemId() {
		return caselotStockItemId;
	}

	public void setCaselotStockItemId(BigDecimal caselotStockItemId) {
		this.caselotStockItemId = caselotStockItemId;
	}

	public BigDecimal getItemsPerCaselot() {
		return itemsPerCaselot;
	}

	public void setItemsPerCaselot(BigDecimal itemsPerCaselot) {
		this.itemsPerCaselot = itemsPerCaselot;
	}

	public BigDecimal getMarkupPercentage() {
		return markupPercentage;
	}

	public void setMarkupPercentage(BigDecimal markupPercentage) {
		this.markupPercentage = markupPercentage;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getLeadTime() {
		return leadTime;
	}

	public void setLeadTime(String leadTime) {
		this.leadTime = leadTime;
	}

	public BigDecimal getQuantityOnOrder() {
		return quantityOnOrder;
	}

	public void setQuantityOnOrder(BigDecimal quantityOnOrder) {
		this.quantityOnOrder = quantityOnOrder;
	}

	public Date getConfirmedDate() {
		return confirmedDate;
	}

	public void setConfirmedDate(Date confirmedDate) {
		this.confirmedDate = confirmedDate;
	}

	public String getLimitPeriodType() {
		return limitPeriodType;
	}

	public void setLimitPeriodType(String limitPeriodType) {
		this.limitPeriodType = limitPeriodType;
	}

	public BigDecimal getPeriodQuantityLimit() {
		return periodQuantityLimit;
	}

	public void setPeriodQuantityLimit(BigDecimal periodQuantityLimit) {
		this.periodQuantityLimit = periodQuantityLimit;
	}

	public String getStockItemLocation1() {
		return stockItemLocation1;
	}

	public void setStockItemLocation1(String stockItemLocation1) {
		this.stockItemLocation1 = stockItemLocation1;
	}

	public String getStockItemLocation2() {
		return stockItemLocation2;
	}

	public void setStockItemLocation2(String stockItemLocation2) {
		this.stockItemLocation2 = stockItemLocation2;
	}

	public String getSexCode() {
		return sexCode;
	}

	public void setSexCode(String sexCode) {
		this.sexCode = sexCode;
	}

	public BigDecimal getSubstituteItemId() {
		return substituteItemId;
	}

	public void setSubstituteItemId(BigDecimal substituteItemId) {
		this.substituteItemId = substituteItemId;
	}

	public String getSpendableLimitFlag() {
		return spendableLimitFlag;
	}

	public void setSpendableLimitFlag(String spendableLimitFlag) {
		this.spendableLimitFlag = spendableLimitFlag;
	}

	public String getSubstituteItemCode() {
		return substituteItemCode;
	}

	public void setSubstituteItemCode(String substituteItemCode) {
		this.substituteItemCode = substituteItemCode;
	}

	public String getInventoryOverdraftFlag() {
		return inventoryOverdraftFlag;
	}

	public void setInventoryOverdraftFlag(String inventoryOverdraftFlag) {
		this.inventoryOverdraftFlag = inventoryOverdraftFlag;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

}
