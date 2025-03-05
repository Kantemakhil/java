package net.syscon.s4.sa.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.AgencyLocationsCommitBean;
import net.syscon.s4.im.beans.Areas;
import net.syscon.s4.sa.admin.OumagyrgRepository;
import net.syscon.s4.sa.admin.OumagyrgService;

/**
 * Class OumagyrgServiceImpl
 */
@Service
public class OumagyrgServiceImpl extends BaseBusiness implements OumagyrgService {

	@Autowired
	private OumagyrgRepository oumagyrgRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 * @return List<AgencyLocations>
	 */
	public List<AgencyLocations> agyLocExecuteQuery(final AgencyLocations searchRecord) {
		return oumagyrgRepository.agyLocExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstAGY_LOC
	 * @return Integer
	 */
	@Transactional
	public Integer agyLocCommit(final AgencyLocationsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (AgencyLocations obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oumagyrgRepository.agyLocUpdateAgencyLocations(commitBean.getUpdateList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 * 
	 * @return List<ReferenceCodes>
	 *
	 */
	public List<ReferenceCodes> agencyLocationTypeRgRecordGroup() {
		return oumagyrgRepository.agencyLocationTypeRgRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 * 
	 * @return List<ReferenceCodes>
	 *
	 */
	public List<ReferenceCodes> geographicRegionRgRecordGroup() {
		return oumagyrgRepository.geographicRegionRgRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 * 
	 * @return List<Areas>
	 *
	 */
	public List<Areas> subAreaRgRecordGroup(final String subAreaType) {
		final String[] returnArray = subAreaType.split("-");
		
		if(subAreaType.contains("-")){
		}
		List<Areas> returnList = oumagyrgRepository.subAreaRgRecordGroup(returnArray[0], returnArray[1]);
		return returnList;
	}
	
	/**
	 * This method is used to execute a record group
	 * 
	 * @return List<Areas>
	 *
	 */
	public List subAreaRgRecordGroupTot() {
		return oumagyrgRepository.subAreaRgRecordGroupTot();
	}
	
	public List areaRgRecordGroupTot() {
		return oumagyrgRepository.areaRgRecordGroupTot();
	}
	
	

	/**
	 * This method is used to execute a record group
	 * 
	 * @return List<Areas>
	 *
	 */
	public List<Areas> areaRgRecordGroup(final String agyLocType) {
		final String[] returnArray = agyLocType.split("-");
		if (agyLocType.contains("-")) {
		}
		List<Areas> returnList =  oumagyrgRepository.areaRgRecordGroup(returnArray[0], returnArray[1]);
		return returnList;
	}


	/**
	 * This method is used to execute a record group
	 * 
	 * @return List<ReferenceCodes>
	 *
	 */
	public List<ReferenceCodes> justiceAreaRgRecordGroup() {
		return oumagyrgRepository.justiceAreaRgRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 * 
	 * @return List<Areas>
	 *
	 */
	public List<Areas> nomsRegionRgRecordGroup() {
		final List<Areas> returnList = oumagyrgRepository.nomsRegionRgRecordGroup();
		Integer count = 0;
		for (final Areas areas : returnList) {
		count = count + 1;
		areas.setListSeq(count);
		}
		return returnList;
	}


}