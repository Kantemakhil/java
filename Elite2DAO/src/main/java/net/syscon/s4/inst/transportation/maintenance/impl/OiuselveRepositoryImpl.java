package net.syscon.s4.inst.transportation.maintenance.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.transportation.beans.SelectVehiclesV1;
import net.syscon.s4.inst.transportation.maintenance.OiuselveRepository;

@Repository
public class OiuselveRepositoryImpl extends RepositoryBase implements OiuselveRepository {

	@Override
	public List<SelectVehiclesV1> selectVehiclesExecuteQuery(SelectVehiclesV1 objSelectVehiclesV1) {

		final String sql = getQuery("OIUSELVE_SELECTVEHICLES_FIND_SELECT_VEHICLES_V1");
		final ArrayList<SelectVehiclesV1> returnList = (ArrayList<SelectVehiclesV1>) namedParameterJdbcTemplate.query(
				sql,
				createParams("formModuleName", objSelectVehiclesV1.getformModuleName(), "scheduledTripId",
						objSelectVehiclesV1.getscheduledTripId()),
				new BeanPropertyRowMapper<SelectVehiclesV1>(SelectVehiclesV1.class));
		return returnList;
	}

}
