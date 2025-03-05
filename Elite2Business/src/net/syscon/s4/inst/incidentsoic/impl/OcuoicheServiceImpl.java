package net.syscon.s4.inst.incidentsoic.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.OicOffences;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.VOicIncidents;
import net.syscon.s4.im.incidentsoic.beans.OicHearingResults;
import net.syscon.s4.im.incidentsoic.beans.OicHearingResultsCommitBean;
import net.syscon.s4.im.incidentsoic.beans.OicHearings;
import net.syscon.s4.im.incidentsoic.beans.OicHearingsCommitBean;
import net.syscon.s4.inst.incidentsoic.OcuoicheRepository;
import net.syscon.s4.inst.incidentsoic.OcuoicheService;

/**
 * Class OcuoicheServiceImpl
 */
@Service
public class OcuoicheServiceImpl extends BaseBusiness implements OcuoicheService {

	@Autowired
	OcuoicheRepository ocuoicheDao;

	/**
	 * Logger object used to print the log in the file
	 */

	private static Logger logger = LogManager.getLogger(OcuoicheServiceImpl.class.getName());

	/**
	 * Creates new OcuoicheBusiness class Object
	 */
	public OcuoicheServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public CaseloadAgencyLocations oicHearPreQuery(final CaseloadAgencyLocations paramBean) {
		return ocuoicheDao.oicHearPreQuery(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 * 
	 * @param oichearingid
	 * @throws Exception
	 */
	public List<Object> oicHearOnCheckDeleteMasteroicHearResCur(final String oichearingid) {
		return ocuoicheDao.oicHearOnCheckDeleteMasteroicHearResCur(oichearingid);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 * 
	 * @param Oichearingid,ResultSeq
	 * @throws Exception
	 */
	public List<Object> ocuoicheKeyDelrecoicSancCur(final String Oichearingid, final String ResultSeq) {
		return ocuoicheDao.ocuoicheKeyDelrecoicSancCur(Oichearingid, ResultSeq);
	}

	/**
	 * Fetch the records from database table
	 * 
	 * @param searchRecord
	 * @return List<OicHearings>
	 * @throws SQLException
	 */
	public List<OicHearings> oicHearSearchOicHearings(final OicHearings searchRecord) {
		  List<OicHearings> returnList = new ArrayList<>();
		  StaffMembers staffMemObj = new StaffMembers();
		  StaffMembers staffMemList = new StaffMembers();
		  returnList = ocuoicheDao.oicHearSearchOicHearings(searchRecord);
		  for (final OicHearings obj : returnList) {
			  if (obj.getInternalLocationId() != 0) {
				 obj.setInternalLocationIdDes(obj.getInternalLocationId().toString());
				  
			  }
		   if(obj.getHearingStaffId() != null){
		    staffMemObj.setStaffId(obj.getHearingStaffId());
		    staffMemList = new StaffMembers();
		    staffMemList = ocuoicheDao.getLastNameOfStaffId(staffMemObj);
		   if (staffMemList.getLastName() != null && staffMemList.getFirstName() != null) {
		   obj.setHearingStaffIdDes(staffMemList.getLastName()+", "+staffMemList.getFirstName());
		   }
		   }
		  }
		  return returnList;
		 }

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param OicHearingsCommitBean commitBean
	 */
	@Transactional
	public List<OicHearings>oicHearCommit(final OicHearingsCommitBean commitBean) {
		List<OicHearings> returnList=new ArrayList<>();
		OicHearings returnObject=new OicHearings();
		Integer liReturn = 0;
		try {
			if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
				commitBean.getInsertList().forEach(bean->
				bean.setCreateUserId(commitBean.getCreateUserId()));
				
				liReturn = ocuoicheDao.oicHearInsertOicHearings(commitBean.getInsertList());
			}
			if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
				commitBean.getUpdateList().forEach(action->
					action.setModifyUserId(commitBean.getCreateUserId())
				);
				liReturn = ocuoicheDao.oicHearUpdateOicHearings(commitBean.getUpdateList());
			}
			if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
				commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
				liReturn = ocuoicheDao.oicHearDeleteOicHearings(commitBean.getDeleteList());
			}
			returnObject.setListSeq(liReturn);
			returnList.add(returnObject);
		} catch (Exception e) {
			logger.error("oicHearCommit", e);
		}
		return returnList;
	}

	/**
	 * Insert the records from database table
	 * 
	 * @param lstOicHearings
	 * @throws SQLException
	 */
	@Transactional
	public Integer oicHearInsertOicHearings(final List<OicHearings> lstOicHearings) {
		return ocuoicheDao.oicHearInsertOicHearings(lstOicHearings);
	}

	/**
	 * Update the records from database table
	 * 
	 * @param lstOicHearings
	 * @throws SQLException
	 */
	@Transactional
	public Integer oicHearUpdateOicHearings(final List<OicHearings> lstOicHearings) {
		return ocuoicheDao.oicHearUpdateOicHearings(lstOicHearings);
	}

	/**
	 * Insert the records from database table
	 * 
	 * @param lstOicHearings
	 * @throws SQLException
	 */
	@Transactional
	public Integer oicHearDeleteOicHearings(final List<OicHearings> lstOicHearings) {
		return ocuoicheDao.oicHearDeleteOicHearings(lstOicHearings);
	}

	/**
	 * Fetch the records from database table
	 * 
	 * @param searchRecord
	 * @return List<OicHearingResults>
	 * @throws SQLException
	 */
	public List<OicHearingResults> oicHearResSearchOicHearingResults(final OicHearingResults searchRecord) {
		List<OicHearingResults> returnList = new ArrayList<>();
		List<ReferenceCodes> refCode = new ArrayList<>();
		final OicOffences paramBean = new OicOffences();
		returnList = ocuoicheDao.oicHearResSearchOicHearingResults(searchRecord);
		for (final OicHearingResults obj : returnList) {
			paramBean.setOicOffenceId(Integer.valueOf(obj.getOicOffenceId().toString()));
			final OicOffences bean = agyInciChgPostQuery(paramBean);
			obj.setOicOffenceCode(bean.getOicOffenceCode());
			obj.setChargeDescription(bean.getDescription());
			refCode = ocuoicheDao.rgOffenceCodeRecordGroup();
			for (final ReferenceCodes ref : refCode) {
				if (bean.getOicOffenceCode().equals(ref.getCode())) {
					obj.setType(ref.getDescription());
				}
			}
			// obj.setType(bean.getOicOffenceType());
			paramBean.setOicOffenceId(Integer.valueOf(obj.getOicResultOffenceId().toString()));
			final OicOffences resultBean = agyInciChgPostQuery(paramBean);
			obj.setResultOicOffenceCode(resultBean.getOicOffenceCode());
			obj.setChargeDescriptionResult(resultBean.getDescription());
			refCode = ocuoicheDao.rgOffenceCodeRecordGroup();
			for (final ReferenceCodes ref : refCode) {
				if (bean.getOicOffenceCode().equals(ref.getCode())) {
					obj.setTypeResult(ref.getDescription());
				}
			}
		}
		return returnList;
	}

	public OicOffences agyInciChgPostQuery(final OicOffences paramBean) {
		return ocuoicheDao.agyInciChgPostQuery(paramBean);
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param OicHearingResultsCommitBean commitBean
	 */
	@Transactional
	public Integer oicHearResCommit(final OicHearingResultsCommitBean commitBean) {
		Integer liReturn = 0;
		try {
			if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
				for (OicHearingResults obj : commitBean.getInsertList()) {
					obj.setCreateUserId(commitBean.getCreateUserId());
				}
				liReturn = ocuoicheDao.oicHearResInsertOicHearingResults(commitBean.getInsertList());
			}
			if (commitBean.getUpdateList() != null && (commitBean.getUpdateList().size() > 0)) {
				for (OicHearingResults obj : commitBean.getUpdateList()) {
					obj.setModifyUserId(commitBean.getCreateUserId());
				}
				liReturn = ocuoicheDao.oicHearResUpdateOicHearingResults(commitBean.getUpdateList());
			}
			if (commitBean.getDeleteList() != null && (commitBean.getDeleteList().size() > 0)) {
				liReturn = ocuoicheDao.oicHearResDeleteOicHearingResults(commitBean.getDeleteList());
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return liReturn;
	}

	/**
	 * Insert the records from database table
	 * 
	 * @param lstOicHearingResults
	 * @throws SQLException
	 */
	@Transactional
	public Integer oicHearResInsertOicHearingResults(final List<OicHearingResults> lstOicHearingResults) {
		return ocuoicheDao.oicHearResInsertOicHearingResults(lstOicHearingResults);
	}

	/**
	 * Update the records from database table
	 * 
	 * @param lstOicHearingResults
	 * @throws SQLException
	 */
	@Transactional
	public Integer oicHearResUpdateOicHearingResults(final List<OicHearingResults> lstOicHearingResults) {
		return ocuoicheDao.oicHearResUpdateOicHearingResults(lstOicHearingResults);
	}

	/**
	 * Insert the records from database table
	 * 
	 * @param lstOicHearingResults
	 * @throws SQLException
	 */
	@Transactional
	public Integer oicHearResDeleteOicHearingResults(final List<OicHearingResults> lstOicHearingResults) {
		return ocuoicheDao.oicHearResDeleteOicHearingResults(lstOicHearingResults);
	}

	/**
	 * This method is used to execute a record group
	 * 
	 * @param String
	 *            agencyIncidentId,String partySeq
	 * @return List<ReferenceCodes>
	 * @throws SQLException
	 */
	public List<OicOffences> rgIncidentChargesRecordGroup(final VOicIncidents searchBean) {
		return ocuoicheDao.rgIncidentChargesRecordGroup(searchBean);

	}

	/**
	 * This method is used to execute a record group R
	 * 
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgOffenceCodeRecordGroup() {
		return ocuoicheDao.rgOffenceCodeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 * 
	 * @return List<ReferenceCodes>
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgHearingTypeRecordGroup() {
		return ocuoicheDao.rgHearingTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 * 
	 * @return List<ReferenceCodes>
	 * @param String
	 *            caseloadId
	 * @throws SQLException
	 */
	public List<AgencyInternalLocations> rgInternalLocationsRecordGroup(final String caseloadId) {
		List<AgencyInternalLocations> returnList = new ArrayList<>();
		returnList = ocuoicheDao.rgInternalLocationsRecordGroup(caseloadId);
		for (final AgencyInternalLocations obj : returnList) {
			obj.setCode(obj.getInternalLocationId().toString());
		}
		return returnList;
	}

	/**
	 * This method is used to execute a record group
	 * 
	 * @return List<ReferenceCodes>
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgPleaRecordGroup() {
		return ocuoicheDao.rgPleaRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 * 
	 * @return List<ReferenceCodes>
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgFindingRecordGroup() {
		return ocuoicheDao.rgFindingRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 * 
	 * @param String
	 *            caseloadId
	 * @return List<StaffMembers>
	 * @throws SQLException
	 */
	public List<StaffMembers> rgAgyIncpStaffIdRecordGroup(final StaffMembers caseloadId) {
		List<StaffMembers> returnList = new ArrayList<>();
		returnList = ocuoicheDao.rgAgyIncpStaffIdRecordGroup(caseloadId);
		for (final StaffMembers obj : returnList) {
			obj.setCode(obj.getStaffId());
		}
		return returnList;
	}

}