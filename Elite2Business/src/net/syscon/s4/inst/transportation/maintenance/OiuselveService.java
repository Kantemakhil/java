package net.syscon.s4.inst.transportation.maintenance;

import java.util.List;

import net.syscon.s4.inst.transportation.beans.SelectVehiclesV1;

public interface OiuselveService {
	List<SelectVehiclesV1> selectVehiclesExecuteQuery(SelectVehiclesV1 objSelectVehiclesV1);

}
