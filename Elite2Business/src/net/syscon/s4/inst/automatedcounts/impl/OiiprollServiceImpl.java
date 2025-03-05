package net.syscon.s4.inst.automatedcounts.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.VLivingUnitSummaries;
import net.syscon.s4.inst.automatedcounts.OiiprollRepository;
import net.syscon.s4.inst.automatedcounts.OiiprollService;
import net.syscon.s4.inst.movements.beans.VIntLocSummaries;

/**
 * Class OiiprollServiceImpl
 */
@Service
public class OiiprollServiceImpl extends BaseBusiness implements OiiprollService {

	@Autowired
	private OiiprollRepository oiiprollRepository;

	/**
	 * Creates new OiiprollServiceImpl class Object
	 */
	public OiiprollServiceImpl() {
		
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public AgencyLocations oiiprollWhenNewFormInstance(final AgencyLocations paramBean) {
		return oiiprollRepository.oiiprollWhenNewFormInstance(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<VLivingUnitSummaries> lvUntSmExecuteQuery(final VLivingUnitSummaries searchRecord, final String type) {
		if (type.equals("E")) {
			searchRecord.setParentLivingUnitId(null);
		} else if (type.equals("N")) {
			searchRecord.setParentLivingUnitId(searchRecord.getLivingUnitId().longValue());
		} else {
			if (!type.equals("P")) {
				return Collections.emptyList();
			}
		}
		return oiiprollRepository.lvUntSmExecuteQuery(searchRecord, type);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public VLivingUnitSummaries lvUntSmTotalCount(final VLivingUnitSummaries searchRecord, final String type) {
		return oiiprollRepository.lvUntSmTotalCount(searchRecord, type);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<VIntLocSummaries> itLcSmExecuteQuery(final VIntLocSummaries searchRecord, final String type) {
		if (type.equals("E")) {
			searchRecord.setParentInternalLocationId(null);
		} else if (type.equals("N")) {
			searchRecord.setParentInternalLocationId(searchRecord.getInternalLocationId());
		} else {
			if (!type.equals("P")) {
				return Collections.emptyList();
			}
		}
		return oiiprollRepository.itLcSmExecuteQuery(searchRecord, type);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public VIntLocSummaries itLcSmTotalCount(final VIntLocSummaries searchRecord, final String type) {
		return oiiprollRepository.itLcSmTotalCount(searchRecord, type);
	}

	@Override
	public List<AgencyLocations> rgAgyLocRecordGroup(final String caseloadId) {
		List<AgencyLocations> returnList = new ArrayList<>();
		returnList = oiiprollRepository.rgAgyLocRecordGroup(caseloadId);
		for (final AgencyLocations agyLocations : returnList) {
			agyLocations.setCode(agyLocations.getAgyLocId());
		}
		return returnList;
	}

}