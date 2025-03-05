package net.syscon.s4.pkgs;

import java.io.Serializable;

import net.syscon.s4.common.beans.BaseModel;

public class PotConcurrencies extends BaseModel implements Serializable {
	private String orderType;
	private String orderCode;
	private String orderTypeConcurrentTo;
	private String orderCodeConcurrentTo;

	public String getOredrType() {
		return orderType;
	}

	public void setOredrType(String orderType){
		this.orderType = orderType;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getOrderTypeConcurrentTo() {
		return orderTypeConcurrentTo;
	}

	public void setOrderTypeConcurrentTo(String orderTypeConcurrentTo) {
		this.orderTypeConcurrentTo = orderTypeConcurrentTo;
	}

	public String getOrderCodeConcurrentTo() {
		return orderCodeConcurrentTo;
	}

	public void setOrderCodeConcurrentTo(String orderCodeConcurrentTo) {
		this.orderCodeConcurrentTo = orderCodeConcurrentTo;
	}

}
