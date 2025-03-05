package net.syscon.s4.inst.incidentsoic.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.intakeclosure.OcdintakRepository;
import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.OicOffences;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMemberRoles;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VNameSearch;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OmsRoles;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentCharges;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentChargesCommitBean;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentParties;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentPartiesCommitBean;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentRepairs;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentRepairsCommitBean;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidents;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentsCommitBean;
import net.syscon.s4.im.incidentsoic.beans.IncidentFollowUpDetails;
import net.syscon.s4.im.incidentsoic.beans.IncidentFollowUpDetailsCommitBean;
import net.syscon.s4.im.incidentsoic.beans.IncidentStaffReport;
import net.syscon.s4.im.incidentsoic.beans.SignificantIncident;
import net.syscon.s4.im.incidentsoic.beans.SignificantIncidentCommitBean;
import net.syscon.s4.inst.incidentsoic.OidincdeRepository;
import net.syscon.s4.inst.incidentsoic.OidincdeService;
import net.syscon.s4.inst.incidentsoic.OidstfrpRepository;
import net.syscon.s4.inst.incidentsoic.maintenance.OimsrlucRepository;
import net.syscon.s4.pkgs.tag_adjudication.TagAdjudicationService;
import net.syscon.s4.triggers.AgencyIncidentChargesT1Service;
import net.syscon.s4.triggers.AgencyIncidentPartiesT1Service;
import net.syscon.s4.triggers.AgencyIncidentsTwfService;

/**
 * Class OidincdeServiceImpl
 */
@Service
public class OidincdeServiceImpl extends BaseBusiness implements OidincdeService {

	@Autowired
	private OidincdeRepository oidincdeRepository;
	
	@Autowired
	private EliteDateService dateService;
	@Autowired
	private TagAdjudicationService tagAdjudicationService;

	@Autowired
	private AgencyIncidentsTwfService agencyIncidentsTwfService;

	@Autowired
	private AgencyIncidentPartiesT1Service agencyIncidentPartiesT1Service;

	@Autowired
	private AgencyIncidentChargesT1Service agencyIncidentChargesT1Service;
	
	@Autowired
	private OidstfrpRepository oidstfrpRepository;
	
	@Autowired
	private OcdintakRepository ocdintakRepository;
	
	@Autowired
	OimsrlucRepository oimsrlucRepository;
	
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidincdeServiceImpl.class);

	/**
	 * Creates new OidincdeServiceImpl class Object
	 */
	public OidincdeServiceImpl() {
		super();
	}

	/**
	 * method for query callings
	 * 
	 * @return List<Object>
	 * @param String
	 *            lstagencyincident
	 */
	public List<Object> agencyIncidentsOnCheckDeleteMasteragyIncPartiesOffenderCur(final String lstagencyincident) {
		return oidincdeRepository.agencyIncidentsOnCheckDeleteMasteragyIncPartiesOffenderCur(lstagencyincident);
	}

	/**
	 * method for query callings
	 * 
	 * @return List<Object>
	 * @param String
	 *            lstagencyincident
	 */
	public List<Object> agencyIncidentsOnCheckDeleteMasteragyIncPartiesStaffCur(final String lstagencyincident) {
		return oidincdeRepository.agencyIncidentsOnCheckDeleteMasteragyIncPartiesStaffCur(lstagencyincident);
	}

	/**
	 * method for query callings
	 * 
	 * @return List<Object>
	 * @param String
	 *            lstagencyincident
	 */
	public List<Object> agencyIncidentsOnCheckDeleteMasteragencyIncidentRepairsCur(final String lstagencyincident) {
		return oidincdeRepository.agencyIncidentsOnCheckDeleteMasteragencyIncidentRepairsCur(lstagencyincident);
	}

	/**
	 * method for query callings
	 * 
	 * @return List<Object>
	 * @param String
	 *            lstagencyincident, String lstpartyseq
	 */
	public List<Object> agyIncPartiesOffenderOnCheckDeleteMasteragencyIncidentChargesCur(final String lstagencyincident,
			final String lstpartyseq) {
		return oidincdeRepository.agyIncPartiesOffenderOnCheckDeleteMasteragencyIncidentChargesCur(lstagencyincident,
				lstpartyseq);
	}

	/**
	 * method for query callings
	 * 
	 * @return List<AgencyIncidentParties>
	 * @param String
	 *            offenderBookId, String agencyIncidentId
	 */
	public List<AgencyIncidentParties> agyIncPartiesOffenderPreInsert(final String offenderbookId,
			final String agencyIncidentId) {
		return null;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<OicOffences>
	 * @param String
	 *            systemMode
	 */
	public List<OicOffences> agencyIncidentChargesPostQuery(final String systemMode) {
		return oidincdeRepository.agencyIncidentChargesPostQuery(systemMode);
	}

	/**
	 * method for query callings
	 * 
	 * @return List<StaffMembers>
	 * @param String
	 *            systemMode
	 */
	public List<StaffMembers> setGlobalCaseloadIdworkingCaseloadCur(final String systemMode) {
		return oidincdeRepository.setGlobalCaseloadIdworkingCaseloadCur(systemMode);
	}

	/**
	 * method for query callings
	 * 
	 * @return Object
	 * @param String
	 *            systemMode
	 */
	public Object oidincdeStateadmUsrCsr(final String systemMode, final String userName) {
		return oidincdeRepository.oidincdeStateadmUsrCsr(systemMode, userName);
	}

	/**
	 * method for query callings
	 * 
	 * @return List<CaseloadAgencyLocations>
	 * @param String
	 *            caseloadId
	 */
	public List<CaseloadAgencyLocations> oidincdeState(final String caseloadId) {
		return oidincdeRepository.oidincdeState(caseloadId);
	}

	/**
	 * method for query callings
	 * 
	 * @return List<AgencyIncidents>
	 * @param String
	 *            lstagencyincident
	 */
	public List<AgencyIncidents> setUpdatedIncidentDetailsupdIncDetCur(final String lstagencyincident) {
		return oidincdeRepository.setUpdatedIncidentDetailsupdIncDetCur(lstagencyincident);
	}

	/**
	 * method for query callings
	 * 
	 * @return List<SystemProfiles>
	 * @param String
	 *            systemMode
	 */
	public List<SystemProfiles> checkUnlockAccessspProfileCur(final String systemMode) {
		return oidincdeRepository.checkUnlockAccessspProfileCur(systemMode);
	}

	/**
	 * method for query callings
	 * 
	 * @return List<StaffMemberRoles>
	 * @param String
	 *            pValue3
	 */
	public List<StaffMemberRoles> checkUnlockAccesscheckUnlockAccess(final String pValue) {
		return oidincdeRepository.checkUnlockAccesscheckUnlockAccess(pValue);
	}

	/**
	 * method for query callings
	 * 
	 * @return List<OmsRoles>
	 * @param String
	 *            pValue, String pValue2
	 */
	public List<OmsRoles> checkUnlockAccesscheckRoleAccess(final String pValue, final String pvalue) {
		return oidincdeRepository.checkUnlockAccesscheckRoleAccess(pValue, pvalue);
	}

	/**
	 * method for query callings
	 * 
	 * @return List<AgencyIncidents>
	 * @param AgencyIncidents
	 *            searchRecord
	 */
	public List<AgencyIncidents> agencyIncidentsExecuteQuery(final AgencyIncidents searchRecord) {
		final List<AgencyIncidents> returnList = oidincdeRepository.agencyIncidentsExecuteQuery(searchRecord);
		if (returnList != null && returnList.size() > 0) {
			final StaffMembers staffCodeBean = new StaffMembers();
			for (final AgencyIncidents obj : returnList) {
				if (obj != null ) {
				if (obj.getInternalLocationId() != null) {
					obj.setInterLocationIdDes(obj.getInternalLocationId().toString());
					
				}
				}
				if (obj != null) {
					staffCodeBean.setStaffId(obj.getReportedStaffId());
					staffCodeBean.setUserId(obj.getCreateUserId());
					final List<StaffMembers> returnReferenceCodeList = (List<StaffMembers>) oidincdeRepository
							.searchStaffMembersByUserId(staffCodeBean);
					for (final StaffMembers staffMembers : returnReferenceCodeList) {

						obj.setCreateStaffName(staffMembers.getLastName() + "," + staffMembers.getFirstName());
					}
				}

			}
		}
		if (returnList != null && returnList.size() > 0) {
		for(final AgencyIncidents agencyIncidents: returnList){
			List<AgencyInternalLocations> listlocations=oidincdeRepository.rgLevelInternalLocationIdsRecordGroup(agencyIncidents.getAgyLocId());
			for(final AgencyInternalLocations agencyInternal:listlocations){
				if(agencyInternal !=null){
				if(agencyIncidents.getInternalLocationId().equals(agencyInternal.getInternalLocationId())){
					agencyIncidents.setInterLocationIdDes(agencyInternal.getInternalLocationCode());
				}
				}
			}
		}
		}if (returnList != null && returnList.size() > 0) {
		for(final AgencyIncidents agencyIncidents: returnList){
			if(agencyIncidents.getLockFlag().equals("Y")){
				agencyIncidents.setFlag(true);
				agencyIncidents.setAppendDetailesflag(true);
			}else{
				agencyIncidents.setFlag(false);	
				agencyIncidents.setAppendDetailesflag(false);
			}
		}
		}
		return returnList;
	}

	/**
	 * Insert the records from database table
	 * 
	 * @param lstAgencyIncidents
	 * 
	 */

	/**
	 * Update the records from database table
	 * 
	 * @param lstAgencyIncidents
	 * 
	 */
	@Transactional
	public Integer agencyIncidentsUpdateAgencyIncidents(final List<AgencyIncidents> lstAgencyIncidents) {
		int liReturn = 0;
		liReturn = oidincdeRepository.agencyIncidentsUpdateAgencyIncidents(lstAgencyIncidents);
		for (AgencyIncidents significantIncident : lstAgencyIncidents) {
			agencyIncidentsTwfService.agencyIncidentsTwf(significantIncident);
		}
		return liReturn;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<AgencyIncidentParties>
	 * @param AgencyIncidentParties
	 *            searchRecord
	 */
	public List<AgencyIncidentParties> agyIncPartiesOffenderExecuteQuery(final AgencyIncidentParties searchRecord) {
		final VNameSearch objSearchDao = new VNameSearch();
		List<VNameSearch> listnames= new ArrayList<VNameSearch>();
		final List<AgencyIncidentParties> returnList = oidincdeRepository
				.agyIncPartiesOffenderExecuteQuery(searchRecord);
		if (returnList != null && returnList.size() > 0) {
			final ReferenceCodes refCodeBean = new ReferenceCodes();
			for (final AgencyIncidentParties obj : returnList) {

				if (obj != null) {
					if(obj.getOffenderBookId() !=null){
						objSearchDao.setOffenderBookId(obj.getOffenderBookId());
						listnames = oidincdeRepository.nameSrchExecuteoffenderQuery(objSearchDao);
						for( final VNameSearch vNameSearch:listnames){
							obj.setOffenderIdDisplay(vNameSearch.getOffenderIdDisplay());
							obj.setLname(vNameSearch.getLastName());
							obj.setFname(vNameSearch.getFirstName());
						}
					}
					if (obj.getActionCode() != null && obj.getActionCode().length() > 0) {
						refCodeBean.setDomain("INC_DECISION");
						refCodeBean.setCode(obj.getActionCode());
						final List<ReferenceCodes> returnReferenceCodeList = oidincdeRepository
								.searchEvidenceType(refCodeBean);
						if (returnReferenceCodeList != null && returnReferenceCodeList.size() > 0) {
							for (final ReferenceCodes refcodes : returnReferenceCodeList) {
								obj.setActionCodeDes(refcodes.getDescription());
							}
						}
					}
				}

			}

		}
		return returnList;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<AgencyIncidentParties>
	 * @param AgencyIncidentParties
	 *            searchRecord
	 */
	public List<AgencyIncidentParties> agyIncPartiesstaffExecuteQuery(final AgencyIncidentParties searchRecord) {
		final List<AgencyIncidentParties> returnList = oidincdeRepository
				.agyIncPartiesOffenderStaffxecuteQuery(searchRecord);
		if (returnList != null && returnList.size() > 0) {
			final StaffMembers staffCodeBean = new StaffMembers();
			final ReferenceCodes refCodeBean = new ReferenceCodes();
			for (final AgencyIncidentParties obj : returnList) {

				if (obj != null) {
					if (obj.getStaffId() != null && obj.getStaffId() > 0) {
						staffCodeBean.setStaffId(obj.getStaffId());
						final List<StaffMembers> returnStaffCodeList = (List<StaffMembers>) oidincdeRepository
								.searchStaffMembers(staffCodeBean);
						if (returnStaffCodeList != null && returnStaffCodeList.size() > 0) {
							for (final StaffMembers staffMembers : returnStaffCodeList) {
								obj.setStaffIdDes(staffMembers.getLastName() + "," + staffMembers.getFirstName());
								obj.setCode(staffMembers.getLastName() + "," + staffMembers.getFirstName());
								obj.setCode(staffMembers.getStaffId().toString());
							}

						}
						if (obj.getIncidentRole() != null && obj.getIncidentRole().length() > 0
								&& (obj.getOffenderBookId() == null || obj.getOffenderBookId() == 0)) {
							refCodeBean.setDomain("INC_STAF_PAR");
							refCodeBean.setCode(obj.getIncidentRole().trim());
							final List<ReferenceCodes> returnReferenceCodeList = oidincdeRepository
									.searchEvidenceType(refCodeBean);
							if (returnReferenceCodeList != null && returnReferenceCodeList.size() > 0) {
								for (final ReferenceCodes refcodes : returnReferenceCodeList) {
									obj.setIncidentRoleDes(refcodes.getDescription());
								}
							}
						}
					}
				}
			}
		}
		return returnList;
	}
	
	/**
	 * Insert the records from database table
	 * 
	 * @param lstAgencyIncidentParties
	 *
	 */
	@Transactional
	public Integer agyIncPartiesOffenderInsertAgencyIncidentParties(
			final List<AgencyIncidentParties> lstAgencyIncidentParties) {
		int insertPartiesCount = 0;
		int updatePartiesCount = 0;
		Integer oicIncidentId = 0;
		Integer offenderbookId;
		String agencyIncidentId;
		final List<AgencyIncidentParties> updateList = new ArrayList<AgencyIncidentParties>();
		for (final AgencyIncidentParties obj : lstAgencyIncidentParties) {
			offenderbookId = (obj.getOffenderBookId()== null )? 0 :obj.getOffenderBookId();
			agencyIncidentId = obj.getAgencyIncidentId().toString();
			Integer returnList = oidincdeRepository.agyIncPartiesOffenderPreInsert(offenderbookId, agencyIncidentId);
			if (returnList > 0) {
				returnList = 5;
				return returnList;
			}

		}
		for (final AgencyIncidentParties incidentParties : lstAgencyIncidentParties) {
			incidentParties.setCreateDateTime(dateService.getDBTime());
			incidentParties.setModifyDateTime(dateService.getDBTime());
			if (incidentParties.getPartyAddedDate() == null) {
				incidentParties.setPartyAddedDate(dateService.getDBTime());
			}
			String sysProfCode = oidincdeRepository.getProfileCode();
			if (incidentParties.getOicIncidentId() == null && incidentParties.getActionCode().equalsIgnoreCase(sysProfCode)) {
				oicIncidentId = oidincdeRepository.agencyIncidentPartiesPreSeqPreInsert(lstAgencyIncidentParties);
				incidentParties.setOicIncidentId(oicIncidentId);
				oicIncidentId = oicIncidentId +1;
			}
		}
		for (final AgencyIncidentParties obj : lstAgencyIncidentParties) {
			try {
				agencyIncidentPartiesT1Service.agencyIncidentPartiesT1Trigger(obj.getOffenderBookId().longValue(),
						obj.getAgencyIncidentId().longValue());
				obj.setLockReferenceTime(dateService.getDBTime());
			} catch (Exception e) {
				logger.error(e);
			}
		}
		
		insertPartiesCount = oidincdeRepository
				.agyIncPartiesOffenderInsertAgencyIncidentParties(lstAgencyIncidentParties);

		if (updateList.size() > 0) {
			updatePartiesCount = oidincdeRepository.agyIncPartiesOffenderUpdateAgencyIncidentParties(updateList);
		}
		return insertPartiesCount + updatePartiesCount;
	}

	/**
	 * Update the records from database table
	 * 
	 * @param lstAgencyIncidentParties
	 * 
	 */
	@Transactional
	public Integer agyIncPartiesOffenderUpdateAgencyIncidentParties(
			final List<AgencyIncidentParties> lstAgencyIncidentParties) {
		Integer oicIncidentId = 0;
		for (final AgencyIncidentParties incidentParties : lstAgencyIncidentParties) {
			String sysProfCode = oidincdeRepository.getProfileCode();
			if(!(incidentParties.getActionCode().equalsIgnoreCase(sysProfCode)))
			{
				incidentParties.setOicIncidentId(null);
			}
			
			if (incidentParties.getOicIncidentId() == null && incidentParties.getActionCode().equalsIgnoreCase(sysProfCode)) {
				oicIncidentId = oidincdeRepository.agencyIncidentPartiesPreSeqPreInsert(lstAgencyIncidentParties);
				incidentParties.setOicIncidentId(oicIncidentId);
				oicIncidentId = oicIncidentId +1;
			}
		}
		return oidincdeRepository.agyIncPartiesOffenderUpdateAgencyIncidentParties(lstAgencyIncidentParties);
	}

	/**
	 * Delete the records from database table
	 * 
	 * @param lstAgencyIncidentParties
	 *
	 */
	@Transactional
	public Integer agyIncPartiesOffenderDeleteAgencyIncidentParties(
			final List<AgencyIncidentParties> lstAgencyIncidentParties) {
		final AgencyIncidentParties chargeObj = new AgencyIncidentParties();
		for (final AgencyIncidentParties parties : lstAgencyIncidentParties) {
			chargeObj.setAgencyIncidentId(parties.getAgencyIncidentId());
			chargeObj.setPartySeq(parties.getPartySeq());
		}
		final Integer chargesList = oidincdeRepository
				.agyIncPartiesOffenderDeleteAgencyIncidentParties(lstAgencyIncidentParties);
		return chargesList;

	}

	/**
	 * method for query callings
	 * 
	 * @return List<AgencyIncidentCharges>
	 * @param AgencyIncidentCharges
	 *            searchRecord
	 */
	public List<AgencyIncidentCharges> agencyIncidentChargesExecuteQuery(final AgencyIncidentCharges searchRecord) {
		List<AgencyIncidentCharges> returnList = null;
		returnList = oidincdeRepository.agencyIncidentChargesExecuteQuery(searchRecord);
		
		for (final AgencyIncidentCharges agencyIncidentCharges : returnList) {
			if (agencyIncidentCharges.getChargedOicOffenceId() != null) {
				final List<OicOffences> listoffences = oidincdeRepository.rgOicOffenceCodesRecord();
				for (final OicOffences oicOffences : listoffences) {
					if (oicOffences.getOicOffenceId().equals(agencyIncidentCharges.getChargedOicOffenceId())){
						agencyIncidentCharges.setReportText(oicOffences.getDescription());
					    agencyIncidentCharges.setEvidenceDisposeText(oicOffences.getOicOffenceCategory());
						agencyIncidentCharges.setFindingCode(oicOffences.getOicOffenceCode());
						agencyIncidentCharges.setOicOffenceType(oicOffences.getOicOffenceType());
					}
				}
			}
		}
		return returnList;
	}

	/**
	 * Insert the records from database table
	 * 
	 * @param lstAgencyIncidentCharges
	 * 
	 */
	@Transactional
	public Integer agencyIncidentChargesInsertAgencyIncidentCharges(
			final List<AgencyIncidentCharges> lstAgencyIncidentCharges) {
		final List<AgencyIncidentCharges> updateCharges = new ArrayList<AgencyIncidentCharges>();
		int insertCount = 0;
		int updateCount = 0;
		Integer chargeSeq = null;
		try {
			
			if (lstAgencyIncidentCharges != null && lstAgencyIncidentCharges.size() > 0) {
				Boolean checkFlagTemp = true;
				List<OicOffences> listOfOff = new ArrayList<>();
				for (final AgencyIncidentCharges chargesBean : lstAgencyIncidentCharges) {
					chargeSeq = tagAdjudicationService.getnextchargeseq(chargesBean.getAgencyIncidentId());
				}
					for (final AgencyIncidentCharges chargesBean : lstAgencyIncidentCharges) {
					if (chargesBean.getPartySeq() == 0) {
						chargesBean.setPartySeq(1);
					}				
						chargesBean.setChargeSeq(chargeSeq);
						chargeSeq = chargeSeq + 1;	
						if (checkFlagTemp){
							checkFlagTemp = false;
						 listOfOff= oidincdeRepository.rgOicOffenceCodesRecord();
						}
						for (final OicOffences list : listOfOff) {
							if (chargesBean.getFindingCode().equals(list.getOicOffenceCode())) {
								chargesBean.setChargedOicOffenceId(list.getOicOffenceId());
								chargesBean.setFindingCode("null");
								chargesBean.setReportText(null);
								chargesBean.setEvidenceDisposeText(null);
								
								
							}
							
						}
						
					
				}
				if (lstAgencyIncidentCharges.size() > 0) {
					List<AgencyIncidentCharges> lstAgencyIncidentCharges1 = agencyIncidentChargesT1Service
							.agencyIncidentChargesT1Tgr(lstAgencyIncidentCharges);

					insertCount = oidincdeRepository
							.agencyIncidentChargesInsertAgencyIncidentCharges(lstAgencyIncidentCharges1);
				}
				if (updateCharges.size() > 0) {
					updateCount = oidincdeRepository.agencyIncidentChargesUpdateAgencyIncidentCharges(updateCharges);
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return insertCount + updateCount;
	}

	/**
	 * Update the records from database table
	 * 
	 * @param lstAgencyIncidentCharges
	 * 
	 */
	@Transactional
	public Integer agencyIncidentChargesUpdateAgencyIncidentCharges(
			final List<AgencyIncidentCharges> lstAgencyIncidentCharges) {
		Boolean checkFlag = true;
		List<OicOffences> listOfOffVal = new ArrayList<>();
		for (final AgencyIncidentCharges obj : lstAgencyIncidentCharges) {
			if (checkFlag) {
				checkFlag = false;
				listOfOffVal= oidincdeRepository.rgOicOffenceCodesRecord();
			}
			for (final OicOffences list : listOfOffVal) {
				if (obj.getFindingCode().equals(list.getOicOffenceCode())) {
					obj.setChargedOicOffenceId(list.getOicOffenceId());
					obj.setFindingCode("null");
					obj.setReportText(null);
					obj.setEvidenceDisposeText(null);
					
					
				}
				
			}
			
		}
		
		return oidincdeRepository.agencyIncidentChargesUpdateAgencyIncidentCharges(lstAgencyIncidentCharges);
	}

	/**
	 * Delete the records from database table
	 * 
	 * @param lstAgencyIncidentCharges
	 * 
	 */
	@Transactional
	public Integer agencyIncidentChargesDeleteAgencyIncidentCharges(
			final List<AgencyIncidentCharges> lstAgencyIncidentCharges) {
		return oidincdeRepository.agencyIncidentChargesDeleteAgencyIncidentCharges(lstAgencyIncidentCharges);
	}

	/**
	 * method for query callings
	 * 
	 * @return List<AgencyIncidentRepairs>
	 * @param AgencyIncidentRepairs
	 *            searchRecord
	 */
	public List<AgencyIncidentRepairs> agencyIncidentRepairsExecuteQuery(final AgencyIncidentRepairs searchRecord) {
		final List<AgencyIncidentRepairs> returnList = oidincdeRepository
				.agencyIncidentRepairsExecuteQuery(searchRecord);
		if (returnList != null && returnList.size() > 0) {
			final ReferenceCodes refCodeBean = new ReferenceCodes();
			for (final AgencyIncidentRepairs obj : returnList) {
				if (obj != null) {
					refCodeBean.setDomain("REPAIR_TYPE");
					refCodeBean.setCode(obj.getRepairType().trim());
					final List<ReferenceCodes> returnReferenceCodeList = oidincdeRepository
							.searchEvidenceType(refCodeBean);
					if (returnReferenceCodeList != null && returnReferenceCodeList.size() > 0) {
						for (ReferenceCodes refcodes : returnReferenceCodeList) {
							if(obj.getRepairType().equals(refcodes.getCode())){
								obj.setRepairTypeDes(refcodes.getDescription());
							}
						obj.setRepairTypeDes(refcodes.getDescription());
							obj.setCode(obj.getRepairType());
						}
					}
				}
				if(obj != null && obj.getRepairCost() != null){
					 double d1 =obj.getRepairCost();
				       

				        DecimalFormat df = new DecimalFormat("0.00");				       
				       obj.setRepairCostdes(df.format(d1));
				}
			}
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 * 
	 * @param lstAgencyIncidentRepairs
	 * 
	 */
	@Transactional
	public Integer agencyIncidentRepairsInsertAgencyIncidentRepairs(
			final List<AgencyIncidentRepairs> lstAgencyIncidentRepairs) {

		final List<AgencyIncidentRepairs> insertListRepairs = lstAgencyIncidentRepairs;
		Integer incidentId = null;
		if(insertListRepairs !=null){
			for (final AgencyIncidentRepairs agencyIncident : insertListRepairs) {
				incidentId = agencyIncident.getAgencyIncidentId();
				agencyIncident.setCreateDateTime(dateService.getDBTime());
				agencyIncident.setModifyDateTime(dateService.getDBTime());
				if (agencyIncident.getRepairCostdes() != null) {
					agencyIncident.setRepairCost(Double.parseDouble(agencyIncident.getRepairCostdes()));
				}

			}
		}
		Integer repairSeq = tagAdjudicationService.getnextrepairseq(incidentId);
		if (insertListRepairs != null) {
			for (final AgencyIncidentRepairs agencyIncident : insertListRepairs) {
				agencyIncident.setRepairSeq(repairSeq);
				repairSeq = repairSeq + 1;
			}
		}
		final List<AgencyIncidentRepairs> updateRepairs = new ArrayList<AgencyIncidentRepairs>();
		int insertCount = 0;
		int updateCount = 0;
		try {
			if (insertListRepairs != null && insertListRepairs.size() > 0) {
				insertCount = oidincdeRepository.agencyIncidentRepairsInsertAgencyIncidentRepairs(insertListRepairs);
			}
			if (updateRepairs != null && updateRepairs.size() > 0) {
				updateCount = oidincdeRepository.agencyIncidentRepairsUpdateAgencyIncidentRepairs(updateRepairs);
			}
		} catch (Exception e) {
			logger.error("",e);
		}
		return insertCount + updateCount;
	}

	/**
	 * Update the records from database table
	 * 
	 * @param lstAgencyIncidentRepairs
	 * 
	 */
	@Transactional
	public Integer agencyIncidentRepairsUpdateAgencyIncidentRepairs(final List<AgencyIncidentRepairs> lstAgencyIncidentRepairs) {
		
		return oidincdeRepository.agencyIncidentRepairsUpdateAgencyIncidentRepairs(lstAgencyIncidentRepairs);
	}

	/**
	 * Delete the records from database table
	 * 
	 * @param lstAgencyIncidentRepairs
	 *
	 */
	@Transactional
	public Integer agencyIncidentRepairsDeleteAgencyIncidentRepairs(
			final List<AgencyIncidentRepairs> lstAgencyIncidentRepairs) {
		return oidincdeRepository.agencyIncidentRepairsDeleteAgencyIncidentRepairs(lstAgencyIncidentRepairs);
	}

	/**
	 * method for query callings
	 * 
	 * @return List<ReferenceCodes>
	 * @param String
	 *            domain
	 */
	public List<ReferenceCodes> rgRepairTypesRecordGroup(final String domain) {
		return oidincdeRepository.rgRepairTypesRecordGroup(domain);

	}

	/**
	 * method for query callings
	 * 
	 * @return List<CaseloadAgencyLocations>
	 * @param String
	 *            caseloadId
	 */
	public List<CaseloadAgencyLocations> rgAgyLocIdsRecordGroup(final String caseloadId, final String caseLoadType) {
		List<CaseloadAgencyLocations> listcaseload = null;
		if("INST".equals(caseLoadType)){		
			listcaseload = oidincdeRepository.rgAgyLocIdsRecordGroup(caseloadId);
			for(final CaseloadAgencyLocations loadAgency: listcaseload){
				loadAgency.setCode(loadAgency.getAgyLocId());
			}
		} else {
			listcaseload = oidincdeRepository.getCommunityOfficesData(caseloadId);
			for(final CaseloadAgencyLocations loadAgency: listcaseload){
				loadAgency.setCode(loadAgency.getAgyLocId());
			}
		}
		return listcaseload;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<OicOffences>
	 * @param String
	 *            startDate, String endDate
	 */
	public List<OicOffences> rgOicOffenceCodesRecordGroup(final String startDate, final String endDate) {
		final List<OicOffences> listoffences = oidincdeRepository.rgOicOffenceCodesRecordGroup(startDate, endDate);
		for (final OicOffences oicOffences : listoffences) {
			oicOffences.setFindingCode(oicOffences.getOicOffenceCode());
			oicOffences.setOffenceType(oicOffences.getOffenceType());
			oicOffences.setReportText(oicOffences.getDescription());
		}
		return listoffences;
	}

	
	/**
	 * method for query callings
	 * 
	 * @return List<ReferenceCodes>
	 * @param String
	 *            domain
	 */
	public List<ReferenceCodes> rgIncidentTypesRecordGroup(final String domain, final String caseloadType) {
		List<ReferenceCodes> refList=new ArrayList<ReferenceCodes>();
		if("INST".equals(caseloadType)) {			
			refList = oidincdeRepository.rgIncidentTypesRecordGroup(domain);
			if(Optional.ofNullable(refList).isPresent()) {
				refList.forEach(refcode->{
					if (ApplicationConstants.YFLAG.equals(refcode.getActiveFlag())) {
						refcode.setCanDisplay(true);
					} else {
						refcode.setCanDisplay(false);
					}
				});
			}
		} else {
			refList = oidincdeRepository.rgIncidentTypesCommRecordGroup(domain);
			if(Optional.ofNullable(refList).isPresent()) {
				refList.forEach(refcode->{
					if (ApplicationConstants.YFLAG.equals(refcode.getActiveFlag())) {
						refcode.setCanDisplay(true);
					} else {
						refcode.setCanDisplay(false);
					}
				});
			}
		}
		return refList;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<AgencyInternalLocations>
	 * @param String
	 *            agencyLocId
	 */
	public List<AgencyInternalLocations> rgLevelInternalLocationIdsRecordGroup(final String agencyLocId) {
		 List<AgencyInternalLocations> listagy = null;
		 listagy = oidincdeRepository.rgLevelInternalLocationIdsRecordGroup(agencyLocId);
		 for(final AgencyInternalLocations agencyInternal:listagy ){
			 agencyInternal.setCode(agencyInternal.getInternalLocationCode());
			 
		 }
		return listagy;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<ReferenceCodes>
	 * @param String
	 *            domain
	 */
	public List<ReferenceCodes> rgOffInvActionCodesRecordGroup(final String domain) {
		return oidincdeRepository.rgOffInvActionCodesRecordGroup(domain);
	}

	/**
	 * method for query callings
	 * 
	 * @return List<ReferenceCodes>
	 * @param String
	 *            domain
	 */
	public List<ReferenceCodes> rgOffInvIncidentRolesRecordGroup(final String domain) {
		return oidincdeRepository.rgOffInvIncidentRolesRecordGroup(domain);
	}

	/**
	 * method for query callings
	 * 
	 * @return List<StaffMembers>
	 * @param String
	 *            caseloadId
	 */
	public List<StaffMembers> rgReportedStaffIdsRecordGroup(final String caseloadId) {
		final List<StaffMembers> listStaff = oidincdeRepository.rgReportedStaffIdsRecordGroup(caseloadId);
		for (final StaffMembers staffMembers : listStaff) {
			staffMembers.setCode(staffMembers.getStaffId());
			staffMembers.setDescription(staffMembers.getStaffName());
		}
		return listStaff;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<ReferenceCodes>
	 * @param String
	 *            domain
	 */
	public List<ReferenceCodes> rgStaffInvIncidentRolesRecordGroup(final String domain) {
		return oidincdeRepository.rgStaffInvIncidentRolesRecordGroup(domain);
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into
	 * the database table
	 * 
	 * @Param AgencyIncidentPartiesCommitBean commitBean
	 */
	@Transactional
	public Integer agencyIncidentPartiesCommit(final AgencyIncidentPartiesCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final AgencyIncidentParties obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = agyIncPartiesOffenderInsertAgencyIncidentParties(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(data -> data.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = agyIncPartiesOffenderUpdateAgencyIncidentParties(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			liReturn = agyIncPartiesOffenderDeleteAgencyIncidentParties(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into
	 * the database table
	 * 
	 * @Param AgencyIncidentRepairsCommitBean commitBean
	 */
	@Transactional
	@Override
	public Integer agencyIncidentRepairsCommit(final AgencyIncidentRepairsCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
				commitBean.getInsertList().forEach(data -> data.setCreateUserId(commitBean.getCreateUserId()));
				liReturn = agencyIncidentRepairsInsertAgencyIncidentRepairs(commitBean.getInsertList());
			}
			if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
				commitBean.getUpdateList().forEach(data -> data.setModifyUserId(commitBean.getCreateUserId()));
				liReturn = agencyIncidentRepairsUpdateAgencyIncidentRepairs(commitBean.getUpdateList());
			}
			if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
				liReturn = agencyIncidentRepairsDeleteAgencyIncidentRepairs(commitBean.getDeleteList());
			}
		} catch (Exception e) {
			logger.error("Exception raised in agencyIncidentRepairsCommit", e);
		}
		return liReturn;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into
	 * the database table
	 * 
	 * @Param AgencyIncidentPartiesCommitBean commitBean
	 */
	@Transactional
	@Override
	public Integer agyIncPartiesOffenderCommit(final AgencyIncidentPartiesCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
				for (final AgencyIncidentParties obj : commitBean.getInsertList()) {
					obj.setCreateUserId(commitBean.getCreateUserId());
				}
				liReturn = agyIncPartiesOffenderInsertAgencyIncidentParties(commitBean.getInsertList());
			}
			if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
				commitBean.getUpdateList().forEach(data -> data.setModifyUserId(commitBean.getCreateUserId()));
				liReturn = agyIncPartiesOffenderUpdateAgencyIncidentParties(commitBean.getUpdateList());
			}
			if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
				liReturn = agyIncPartiesOffenderDeleteAgencyIncidentParties(commitBean.getDeleteList());
			}
		} catch (Exception e) {
			logger.error("Exception raised in agyIncPartiesOffenderCommit", e);
		}
		return liReturn;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into
	 * the database table
	 * 
	 * @Param AgencyIncidentPartiesCommitBean commitBean
	 */
	@Transactional
	@Override
	public Integer agyIncStaffCommit(final AgencyIncidentPartiesCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
				commitBean.getInsertList().forEach(data -> data.setCreateUserId(commitBean.getCreateUserId()));
				liReturn = agyIncStaffInset(commitBean.getInsertList());
			}
			if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
				commitBean.getUpdateList().forEach(data -> {
					data.setModifyUserId(commitBean.getCreateUserId());
					data.setModifyDateTime(dateService.getDBTime());
					});
				liReturn = agyIncStaffUpdate(commitBean.getUpdateList());
			}
			if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
				liReturn = agyIncStaffDelete(commitBean.getDeleteList());
			}
		} catch (Exception e) {
			logger.error("Exception raised in agyIncPartiesOffenderCommit", e);
		}
		return liReturn;
	}

	private Integer agyIncStaffDelete(final List<AgencyIncidentParties> deleteList) {
		Integer returnVal = 0;
		if (deleteList.size() > 0) {
			

			returnVal = oidincdeRepository.deleteIncidentStaffReport(deleteList);
			    if(returnVal == 1) {
			    	returnVal = oidincdeRepository.agyIncPartiesOffenderDeleteAgencyIncidentParties(deleteList);
			    }
			}

		
		return returnVal;
	}

	private int agyIncStaffUpdate(final List<AgencyIncidentParties> updateList) {
		Integer insertParties = 1;
		for (AgencyIncidentParties repObj : updateList) {
			String oldReport =oidstfrpRepository.getsfReportAgeIncParties(repObj.getAgencyIncidentId(), repObj.getPartySeq());
			if(oldReport!= null && !oldReport.equalsIgnoreCase(repObj.getReportType())) {
				insertParties = oidincdeRepository.updIncStaffReportType(repObj.getAgencyIncidentId(), repObj.getStaffId(),repObj.getReportType(),repObj.getPartySeq(),repObj.getModifyDateTime(),repObj.getModifyUserId());
			}
		}
		if (updateList.size() > 0 && insertParties>0) {
			insertParties = oidincdeRepository.agyIncPartiesOffenderUpdateAgencyIncidentParties(updateList);
		}
		return insertParties;
	}

	private int agyIncStaffInset(final List<AgencyIncidentParties> insertList) {
		for (final AgencyIncidentParties agencyParties : insertList) {
			agencyParties.setPartyAddedDate(dateService.getDBTime());
			Boolean checkFlagCheck = true;
				if (checkFlagCheck) {
					checkFlagCheck = false;
			}
			
		}
		Integer insertParties = 0;
		if (insertList.size() > 0) {
			Integer partySeq = 0;
			for (final AgencyIncidentParties obj : insertList) {
				obj.setLockReferenceTime(dateService.getDBTime());
				obj.setCreateDateTime(dateService.getDBTime());
				try {
					if (obj.getAgencyIncidentId() != null &&  partySeq == 0) {
						partySeq = oidincdeRepository.agencyIncidentPartiesPreSeqPreInsertcDAO(obj.getAgencyIncidentId());
					}
						obj.setPartySeq(partySeq);
						partySeq = partySeq + 1;
					if(obj.getOffenderBookId()!=null && obj.getAgencyIncidentId()!=null) {
					agencyIncidentPartiesT1Service.agencyIncidentPartiesT1Trigger(obj.getOffenderBookId().longValue(),
							obj.getAgencyIncidentId().longValue());
				    }
				} catch (Exception e) {
					logger.error(e);
				}
			}
			
			  insertParties =
			  oidincdeRepository.agyIncPartiesOffenderInsertAgencyIncidentParties(insertList); if(insertParties>0) {
			 
				List<IncidentStaffReport> insList = new ArrayList<>();
				List<ReferenceCodes> staffReMainInsertList = new ArrayList<>();

				insertList.forEach( ele -> {
					IncidentStaffReport insObj = new IncidentStaffReport();
					insObj.setAgencyIncidentId(ele.getAgencyIncidentId());					
					insObj.setReportType(ele.getReportType());
					insObj.setCreateUserId(ele.getCreateUserId());
					insObj.setReportTime(dateService.getDBTime());
					insObj.setReportDate(dateService.getDBTime());
					insObj.setLockFlag("N");
					insObj.setPartySeq(ele.getPartySeq());
					insObj.setLockReferenceTime(dateService.getDBTime());
					insObj.setCreateDateTime(dateService.getDBTime());
					insObj.setCreateUserId(insertList.get(0).getCreateUserId());
					insObj.setRepCompletFlag("N");
					Integer staffId = ocdintakRepository.oldContactGetStaffId(insertList.get(0).getCreateUserId());
					insObj.setReporterStaffId(staffId);
					insList.add(insObj);
				    boolean countVal=oidincdeRepository.getStaffReportsMaintCount(ele.getReportType());
				    if(countVal==false) {
				    	ReferenceCodes codes=new ReferenceCodes();
				    	codes.setReportType(ele.getReportType());
				    	codes.setAutomaticFlag("N");
				    	codes.setCreateUserId(ele.getCreateUserId());
				    	staffReMainInsertList.add(codes);
				    }
				});
				if(staffReMainInsertList.size()>0) {
					oimsrlucRepository.refCodeCondInsertReference(staffReMainInsertList);
				}
				oidstfrpRepository.staffReportCommitDataInsert(insList);
			}
		}
		return insertParties;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into
	 * the database table
	 * 
	 * @Param AgencyIncidentChargesCommitBean commitBean
	 */
	@Transactional
	@Override
	public Integer agencyIncidentChargesCommit(final AgencyIncidentChargesCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
				commitBean.getInsertList().forEach(data -> data.setCreateUserId(commitBean.getCreateUserId()));
				liReturn = agencyIncidentChargesInsertAgencyIncidentCharges(commitBean.getInsertList());
			}
			if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
				commitBean.getUpdateList().forEach(data -> data.setModifyUserId(commitBean.getCreateUserId()));
				liReturn = agencyIncidentChargesUpdateAgencyIncidentCharges(commitBean.getUpdateList());
			}
			if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
				liReturn = agencyIncidentChargesDeleteAgencyIncidentCharges(commitBean.getDeleteList());
			}
			if (commitBean.getInsertOffenderInvList() != null && commitBean.getInsertOffenderInvList().size() > 0) {
				liReturn = agyIncPartiesOffenderInsertAgencyIncidentParties(commitBean.getInsertOffenderInvList());
			}
			if (commitBean.getInsertRepairList() != null && commitBean.getInsertRepairList().size() > 0) {
				liReturn = agencyIncidentRepairsInsertAgencyIncidentRepairs(commitBean.getInsertRepairList());
			}
			if (commitBean.getInsertStaffPartiesList() != null
					&& commitBean.getInsertStaffPartiesList().size() > 0) {
				liReturn = agyIncPartiesOffenderInsertAgencyIncidentParties(commitBean.getInsertStaffPartiesList());
			}
		} catch (Exception e) {
			logger.error("Exception raised in agencyIncidentChargesCommit", e);
		}
		return liReturn;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into
	 * the database table
	 * 
	 * @Param AgencyIncidentPartiesCommitBean commitBean
	 */
	@Transactional
	@Override
	public Integer agyIncPartiesStaffCommit(final AgencyIncidentPartiesCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
				commitBean.getInsertList().forEach(data -> data.setCreateUserId(commitBean.getCreateUserId()));
				liReturn = agyIncPartiesOffenderInsertAgencyIncidentParties(commitBean.getInsertList());
			}
			if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
				commitBean.getUpdateList().forEach(data -> data.setModifyUserId(commitBean.getCreateUserId()));
				liReturn = agyIncPartiesOffenderUpdateAgencyIncidentParties(commitBean.getUpdateList());
			}
			if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
				liReturn = agyIncPartiesOffenderDeleteAgencyIncidentParties(commitBean.getDeleteList());
			}
		} catch (Exception e) {
			logger.error("Exception raised in agyIncPartiesStaffCommit", e);
		}
		return liReturn;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert, update into the
	 * database table
	 * 
	 * @Param AgencyIncidents agencyIncidentsBean
	 */
	@Transactional
	public Integer agencyIncidentsCommit(final AgencyIncidentsCommitBean commitBean) {
		int liReturn = 0;
		if ((commitBean.getInsertList() != null) && (commitBean.getInsertList().size() > 0)) {
			commitBean.getInsertList().forEach(data -> data.setCreateUserId(commitBean.getCreateUserId()));
			liReturn = agencyIncidentsInsertAgencyIncidents(commitBean.getInsertList());
			for (AgencyIncidents significantIncident : commitBean.getInsertList()) {
				agencyIncidentsTwfService.agencyIncidentsTwf(significantIncident);
			}
		}
		if ((commitBean.getUpdateList() != null) && (commitBean.getUpdateList().size() > 0)) {
			commitBean.getUpdateList().forEach(data -> data.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = agencyIncidentsUpdateAgencyIncidents(commitBean.getUpdateList());
		}
		return liReturn;

	}
	@Transactional
	private Integer agencyIncidentsInsertAgencyIncidents(List<AgencyIncidents> insertList) {
		Integer liReturn =0;
		liReturn = oidincdeRepository.agencyIncidentsInsertAgencyIncidents(insertList);
		for (AgencyIncidents significantIncident : insertList) {
			agencyIncidentsTwfService.agencyIncidentsTwf(significantIncident);
		}
		return liReturn;
	}

	/**
	 * Performing basic Oracle form functions i.e. fetching records from
	 * database table
	 * 
	 * @Param StaffMembers staffMembers
	 */
	public List<StaffMembers> staffidExecuteQuery(final String userId) {
		return oidincdeRepository.staffidExecuteQuery(userId);

	}

	@Override
	public List<SignificantIncident> sigificantIncidentExecuteQuery(SignificantIncident commitBean) {
		return oidincdeRepository.sigificantIncidentExecuteQuery(commitBean);
	}

	@Override
	public int sigificantIncidentCommmit(SignificantIncidentCommitBean commitBean) {
		int liReturn = 0;
		try {
			Integer staffId = ocdintakRepository.oldContactGetStaffId(commitBean.getCreateUserId());
			if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
				commitBean.getInsertList().forEach(data -> {
					data.setCreateUserId(commitBean.getCreateUserId());					
					data.setRecordedByStaffId(BigDecimal.valueOf(staffId));
					});
				
				liReturn = sigificantIncidentInsertAgencyIncident(commitBean.getInsertList());
			}
			if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
				commitBean.getUpdateList().forEach(data -> {data.setModifyUserId(commitBean.getCreateUserId());
				data.setRecordedByStaffId(BigDecimal.valueOf(staffId));});
				liReturn = sigificantIncidentUpdateAgencyIncident(commitBean.getUpdateList());
			}
			if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
				commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
				liReturn = sigificantIncidentDeleteAgencyIncident(commitBean.getDeleteList());
			}
		} catch (Exception e) {
			logger.error("Exception raised in agyIncPartiesStaffCommit", e);
		}
		return liReturn;
	}

	private int sigificantIncidentInsertAgencyIncident(List<SignificantIncident> insertList) {
		int insertsigificantCount = 0;
		for (SignificantIncident significantIncident : insertList) {
			significantIncident.setSealFlag("N");
		}
		insertsigificantCount = oidincdeRepository.offenderWeaponcommitInsert(insertList);
		return insertsigificantCount;
	}

	private int sigificantIncidentUpdateAgencyIncident(List<SignificantIncident> updateList) {
		int updatesigificantCount = 0;
		for (SignificantIncident significantIncident : updateList) {
			significantIncident.setSealFlag("N");
		}
		updatesigificantCount = oidincdeRepository.offenderWeaponcommitupdate(updateList);
		return updatesigificantCount;
	}

	private int sigificantIncidentDeleteAgencyIncident(List<SignificantIncident> deleteList) {
		int deletesigificantCount = 0;
		deletesigificantCount = oidincdeRepository.offenderWeaponcommitdelete(deleteList);
		return deletesigificantCount;
	}
	
	@Override
	public Boolean getEnhancedStaffReporter(Integer staffId) {
		String retVal = oidincdeRepository.getEnhancedStaffReporter(staffId);
		if(retVal.equals("X")) {
			return true;
		}
		return false;
		
	}

	@Override
	public List<IncidentFollowUpDetails> incidentFollowUpcommit(IncidentFollowUpDetailsCommitBean commitBean) {
		final List<IncidentFollowUpDetails> liReturnData = new ArrayList<>();
		final IncidentFollowUpDetails sentenceterms = new IncidentFollowUpDetails();
		Integer liReturn = 0;
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (IncidentFollowUpDetails obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oidincdeRepository.incidentFollowUpInsertData(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (IncidentFollowUpDetails obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oidincdeRepository.incidentFollowUpUpdateData(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = oidincdeRepository.incidentFollowUpDeleteData(commitBean.getDeleteList());
		}
		sentenceterms.setReturnedOutput(BigDecimal.valueOf(liReturn));
		liReturnData.add(sentenceterms);
		return liReturnData;
	}

	@Override
	public List<IncidentFollowUpDetails> getIncidentFollowUpDetails(IncidentFollowUpDetails searchBean) {
		return oidincdeRepository.getIncidentFollowUpDetails(searchBean);
	}

	@Override
	public Map<String, Boolean> checkPermisionForTabAccess(String userName) {
		return oidincdeRepository.checkPermissionForTabAccess(userName);
	}

	public List<StaffMembers> rgRoleStaffIdsRecordGroup(final String caseloadId,String agyLocId) {
		String profileValue = oidincdeRepository.getProfileCodeForStaffSearch();
		List<StaffMembers> listStaff = new ArrayList<>();

		if (profileValue != null) {
		    if (profileValue.equalsIgnoreCase("Y")) {
		        listStaff = oidincdeRepository.rgRoleStaffIdsRecordGroup(caseloadId, agyLocId);
		    } else if (profileValue.equalsIgnoreCase("N")) {
		        listStaff = oidincdeRepository.rgRoleStaffIdsForAllAgyLocId(agyLocId);
		    }
		}
		if (listStaff != null) {
		for (final StaffMembers staffMember : listStaff) {
		    staffMember.setCode(staffMember.getStaffId());
		    staffMember.setDescription(staffMember.getStaffName());
		}
		}

		return listStaff;

		}


}
