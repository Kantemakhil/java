package net.syscon.s4.inst.securitythreatgroupsmaintenance.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.OffenderStgAffiliations;
import net.syscon.s4.im.beans.VStgLocationMembers;
import net.syscon.s4.inst.securitythreatgroupmaintenance.OiistgmbRepository;
import net.syscon.s4.inst.securitythreatgroupsmaintenance.OiistgmbService;

/**
 * public OiistgmbServiceImpl()
 */
@Service
public class OiistgmbServiceImpl extends BaseBusiness implements OiistgmbService {

	@Autowired
	private OiistgmbRepository oiistgmbRepository;

	/**
	 * Creates new OimtgoptServiceImpl class Object
	 */
	public OiistgmbServiceImpl() {

	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 * @return agencyLocationsList
	 */
	public List<AgencyLocations> getLocationDescription(final AgencyLocations paramBean) {
		return oiistgmbRepository.getLocationDescription(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 * 
	 * @return List<OffenderStgAffiliations>
	 * @param params
	 *
	 */
	public List<OffenderStgAffiliations> getNumberOfMembers(final OffenderStgAffiliations paramBean) {
		return oiistgmbRepository.getNumberOfMembers(paramBean);
	}

	/**
	 * Fetch the records from database table
	 * 
	 * @return List<LivingUnits>
	 * @param searchRecord
	 *
	 */
	public List<LivingUnits> livingUnitsExecuteQuery(final LivingUnits searchRecord) {
		final List<LivingUnits> returnList = oiistgmbRepository.livingUnitsExecuteQuery(searchRecord);
		for (final LivingUnits obj : returnList) {
			final Integer count = oiistgmbRepository.getCountOfNumber(obj.getAgyLocId(),obj.getLivingUnitId(),searchRecord.getStgId());
			if (count != null) {
				obj.setNumbers(count);
			}
		}
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 * 
	 * @return List<VStgLocationMembers>
	 * @param searchRecord
	 *
	 */
	public List<VStgLocationMembers> vStgLocationMembersExecuteQuery(final VStgLocationMembers searchRecord) {
		final List<VStgLocationMembers> returnList = oiistgmbRepository.vStgLocationMembersExecuteQuery(searchRecord);
		returnList.forEach(action -> {
			final String profileType = oiistgmbRepository.getStatus();
			if ("STGV".equals(profileType)) {
				final String reqApprovsalFlag = oiistgmbRepository.getStatusAssesments(profileType);
				if ("Y".equals(reqApprovsalFlag)) {
					final String assesmentOne = oiistgmbRepository
							.getStatusOffenderAssesmentsDataOne(action.getOffenderBookId(), profileType);
					if (assesmentOne != null) {
						final String getStatus = getStatusData(assesmentOne);
						if (getStatus != null) {
							action.setStatus(getStatus);
						}
					}
				} else {
					final String assesmentTwo = oiistgmbRepository
							.getStatusOffenderAssesmentsDataTwo(action.getOffenderBookId(), profileType);
					if (assesmentTwo != null) {
						final String getStatus = getStatusData(assesmentTwo);
						if (getStatus != null) {
							action.setStatus(getStatus);
						}
					}
				}
			}
		});
		return returnList;

	}

	private String getStatusData(final String assesmentOne) {
		return oiistgmbRepository.getStatusData(assesmentOne);
	}

}
