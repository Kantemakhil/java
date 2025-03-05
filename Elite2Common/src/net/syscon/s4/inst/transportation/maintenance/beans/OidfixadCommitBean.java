package net.syscon.s4.inst.transportation.maintenance.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OidfixadCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private FixedAssetsCommitBean fixedAssetsCommitBean;

	private VehiclesCommitBean vehiclesCommitBean;

	public FixedAssetsCommitBean getFixedAssetsCommitBean() {
		return fixedAssetsCommitBean;
	}

	public void setFixedAssetsCommitBean(FixedAssetsCommitBean fixedAssetsCommitBean) {
		this.fixedAssetsCommitBean = fixedAssetsCommitBean;
	}

	public VehiclesCommitBean getVehiclesCommitBean() {
		return vehiclesCommitBean;
	}

	public void setVehiclesCommitBean(VehiclesCommitBean vehiclesCommitBean) {
		this.vehiclesCommitBean = vehiclesCommitBean;
	}
	
}
