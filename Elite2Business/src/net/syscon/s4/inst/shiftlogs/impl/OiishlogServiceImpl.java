package net.syscon.s4.inst.shiftlogs.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inst.shiftlogs.OiishlogRepository;
import net.syscon.s4.inst.shiftlogs.OiishlogService;
import net.syscon.s4.inst.shiftlogs.bean.AgencyShiftLogs;
import net.syscon.s4.inst.shiftlogs.bean.AgencyShiftLogsCommitBean;

/**
 * Class OiishlogServiceImpl
 */
@Service
public class OiishlogServiceImpl extends BaseBusiness implements OiishlogService {
	
	@Autowired
	private OiishlogRepository oiishlogRepo;

	/**
	 * Creates new OiishlogServiceImpl class Object
	 */
	public OiishlogServiceImpl() {
		
	} 

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public AgencyShiftLogs cgfkchkAgyShilAgyShilAgy(final AgencyShiftLogs paramBean) {
		return oiishlogRepo.cgfkchkAgyShilAgyShilAgy(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkAgyShilAgyShilRef(final ReferenceCodes paramBean) {
		return  oiishlogRepo.cgfkchkAgyShilAgyShilRef(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public AgencyInternalLocations cgfkchkAgyShilAgyShil(final AgencyInternalLocations paramBean) {
		return oiishlogRepo.cgfkchkAgyShilAgyShil(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public List<AgencyInternalLocations> cgfklkpAgyShilAgyShilAgy(final AgencyInternalLocations paramBean) {
		return  oiishlogRepo.cgfklkpAgyShilAgyShilAgy(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<AgencyShiftLogs> agyShilExecuteQuery(final AgencyShiftLogs searchRecord) {

		List<AgencyInternalLocations> lstLocations = new ArrayList<AgencyInternalLocations>();
		if (searchRecord != null) {
			lstLocations = oiishlogRepo.rgLocationRecordGroup(searchRecord.getGlobalCaseLoadId());
			if (searchRecord.getDspAgyLocId3() != null) {
				for (final AgencyInternalLocations objAgyInt : lstLocations) {
					if (searchRecord.getDspAgyLocId3().equals(objAgyInt.getCode())) {
						searchRecord.setInternalLocationId(new BigDecimal(objAgyInt.getInternalLocationId()));
						break;
					}
				}
			}
		}
		final List<AgencyShiftLogs> lstAgency = oiishlogRepo.agyShilExecuteQuery(searchRecord);
		if (lstAgency != null && lstAgency.size() > 0) {
			for (final AgencyShiftLogs objAgency : lstAgency) {
				objAgency.setDspAgyLocId4(searchRecord.getGlobalCaseLoadId());
				if (objAgency.getInternalLocationId() != null) {
					for (final AgencyInternalLocations objAgyInt : lstLocations) {
						if (objAgency.getInternalLocationId().toString()
								.equals(objAgyInt.getInternalLocationId().toString())) {
							objAgency.setDspAgyLocId3(objAgyInt.getInternalLocationCode());
							break;
						}
					}
				}
				if (objAgency.getObservationTextOne() != null) {
					objAgency.setObservationDetails(objAgency.getObservationTextOne());
       			 	objAgency.setObservationText(null);
				}
			}
		}

		return lstAgency;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstAGY_SHIL
	 *
	 * @
	 */
	@Transactional
	public Integer agyShilCommit(final AgencyShiftLogsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(bean-> bean.setCreateUserId(commitBean.getCreateUserId()));
			liReturn = oiishlogRepo.agyShilInsertAgencyShiftLogs(commitBean.getInsertList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<AgencyShiftLogs> agyShil1ExecuteQuery(final AgencyShiftLogs searchRecord) {
		return oiishlogRepo.agyShilExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstAGY_SHIL1
	 *
	 * @
	 */
	@Transactional
	public Integer agyShil1Commit(final AgencyShiftLogsCommitBean commitBean) {
		return 0;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> cgfkAgyShilAgyActivityCodRecordGroup() {
		return oiishlogRepo.cgfkAgyShilAgyActivityCodRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<AgencyLocations> rgAgencyRecordGroup(final String caseloadId) {
		return oiishlogRepo.rgAgencyRecordGroup(caseloadId);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<AgencyInternalLocations> rgLocationRecordGroup(final String agyLocId) {
		return oiishlogRepo.rgLocationRecordGroup(agyLocId);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<StaffMembers> rgStaffRecordGroup(final String caseloadId) {
		return oiishlogRepo.rgStaffRecordGroup(caseloadId);

	}

	@Override
	public List<Object> cgwhenNewFormInstance(final SysDual paramBean) {
		return null;
	}

	@Override
	public Integer agyShilInsertAgencyShiftLogs(final List<AgencyShiftLogs> lstAgyShLogs) {
		return null;
	}

	@Override
	public List<Object> cgfkchkAgyShilAgyShilc(final AgencyInternalLocations paramBean) {
		return null;
	}

}