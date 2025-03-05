package net.syscon.s4.inst.transportation.maintenance.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.inst.transportation.beans.SelectVehiclesV1;
import net.syscon.s4.inst.transportation.maintenance.OiuselveRepository;
import net.syscon.s4.inst.transportation.maintenance.OiuselveService;

@Service
public class OiuselveServiceImpl implements OiuselveService {

	@Autowired
	private OiuselveRepository oiuselveRepository;

	@Override
	public List<SelectVehiclesV1> selectVehiclesExecuteQuery(SelectVehiclesV1 objSelectVehiclesV1) {
		return oiuselveRepository.selectVehiclesExecuteQuery(objSelectVehiclesV1);
	}

}
