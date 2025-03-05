package net.syscon.s4.inst.movements.maintenance.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.movements.maintenance.OcuintlcService;
import net.syscon.s4.inst.movements.maintenance.OimulocaRepository;
import net.syscon.s4.pkgs.tag_internal_locations.TagInternalLocationsService;

/**
 * Class OcuintlcServiceImpl
 * 
 */
@Service
public class OcuintlcServiceImpl extends BaseBusiness implements OcuintlcService {

	@Autowired
	private OimulocaRepository oimulocaRepository;
	@Autowired
	private TagInternalLocationsService tagInternalLocationsService;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<AgencyInternalLocations> intLocExecuteQuery(final AgencyInternalLocations searchRecord) {
		final List<AgencyInternalLocations> returnList = tagInternalLocationsService.queryInternalLocations(searchRecord);
		for (final AgencyInternalLocations bean : returnList) {
			final AgencyInternalLocations returnObj = oimulocaRepository
					.getInternalLocationRecords(bean.getInternalLocationId());
			bean.setUserDesc(returnObj.getUserDesc());
			bean.setInternalLocationType(returnObj.getInternalLocationType());
		}
		return returnList;

	}
}