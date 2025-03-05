package net.syscon.s4.globaloffenderrecords.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.OffenderIdentifier;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.globaloffenderrecords.OcucoffeRepository;
import net.syscon.s4.globaloffenderrecords.OcucoffeService;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.OffenderDetail;
import net.syscon.s4.im.beans.OffenderDetails;
import net.syscon.s4.im.beans.OffenderIdentifiersCommitBean;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.OffendersCommitBean;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.VHeaderBlock2;
import net.syscon.s4.inst.demographicsbiometrics.OidadmisService;
import net.syscon.s4.triggers.OffIdentVineIntfTrgService;
import net.syscon.s4.triggers.OffendersTjnService;
import net.syscon.s4.triggers.OffendersVineIntfTrgService;
import net.syscon.s4.triggers.OmtoffsrcService;

/**
 * Class OcucoffeServiceImpl
 */
@Service
public class OcucoffeServiceImpl extends BaseBusiness implements OcucoffeService {
	
	@Autowired
	private EliteDateService dateService;
	
	private static Logger logger = LogManager.getLogger(OcucoffeServiceImpl.class.getName());

	@Autowired
	private OcucoffeRepository ocucoffeRepository;
	
	@Autowired
	private OidadmisService oidadmisService;
	
	@Autowired	
	private OffendersVineIntfTrgService offendersVineIntfTrgService;
	
	@Autowired
	private OmtoffsrcService omtoffsrcService;
	
	@Autowired
	private OffendersTjnService offendersTjnService;
	
	@Autowired
	private OffIdentVineIntfTrgService offIdentVineIntfTrgService;

	/**
	 * Creates new OcucoffeServiceImpl class Object
	 */
	public OcucoffeServiceImpl() {
		// OcucoffeServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 * 
	 * @return List<Object>
	 * @param OffenderIdentifiers
	 *            paramBean
	 */
	public List<Object> offOnCheckDeleteMasteroffIdAllCur(final OffenderIdentifier paramBean) {
		return ocucoffeRepository.offOnCheckDeleteMasteroffIdAllCur(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 * 
	 * @return List<Object>
	 * @param Offenders
	 *            paramBean
	 */
	public List<Object> offOnCheckDeleteMasteraliasCur(final Offenders paramBean) {
		return ocucoffeRepository.offOnCheckDeleteMasteraliasCur(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 * 
	 * @return List<Object>
	 * @param OffenderIdentifiers
	 *            paramBean
	 */
	public List<Object> aliasOnCheckDeleteMasteroffIdCur(final OffenderIdentifier paramBean) {
		return ocucoffeRepository.aliasOnCheckDeleteMasteroffIdCur(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 * 
	 * @param ReferenceCodes
	 *            paramBean
	 */
	public ReferenceCodes whenNewRecordInstancedefaultSeqCur(final ReferenceCodes paramBean) {
		return ocucoffeRepository.whenNewRecordInstancedefaultSeqCur(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 * 
	 * @param Dual
	 *            paramBean
	 */
	public Object whenNewFormInstance(final Dual paramBean) {
		return ocucoffeRepository.whenNewFormInstance(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 * 
	 * 
	 * @param ReferenceCodes
	 *            paramBean
	 */
	public ReferenceCodes postQueryreferenceCodesC(final ReferenceCodes paramBean) {
		return ocucoffeRepository.postQueryreferenceCodesC(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 * 
	 * @param Offenders
	 *            paramBean
	 */
	public Offenders postInsertgetRootOffenderId(final Offenders paramBean) {
		return ocucoffeRepository.postInsertgetRootOffenderId(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 * 
	 * @param Dual
	 *            paramBean
	 */
	public Object preInsertgetNextAlias(final Dual paramBean) {
		return ocucoffeRepository.preInsertgetNextAlias(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 * 
	 * @param OffenderIdentifiers
	 *            paramBean
	 */
	public List<Object> preInsertgetNextIdentifier(final OffenderIdentifier paramBean) {
		return ocucoffeRepository.preInsertgetNextIdentifier(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param OffenderIdentifiers
	 *            paramBean
	 */
	public List<Object> preInsertrecordEx(final OffenderIdentifier paramBean) {
		return ocucoffeRepository.preInsertrecordEx(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 * 
	 * @param OmsModules
	 *            paramBean
	 */
	public List<Object> createFormGlobals(final OmsModules paramBean) {
		return ocucoffeRepository.createFormGlobals(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 * 
	 * @param SystemProfiles
	 *            paramBean
	 */
	public SystemProfiles ageValidationvsRangecur(final SystemProfiles paramBean) {
		return ocucoffeRepository.ageValidationvsRangecur(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 * 
	 * @param Dual
	 *            paramBean
	 */
	public Object ageValidationvsAgecur(final Date paramBean) {
		return ocucoffeRepository.ageValidationvsAgecur(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 * 
	 * @param Offenders
	 *            paramBean
	 */
	public Object validateAliasescheckForDupOffCur(final Offenders paramBean) {
		return ocucoffeRepository.validateAliasescheckForDupOffCur(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 * 
	 * @param Offenders
	 *            paramBean
	 */
	public Object validateAliasescheckDupNameCur(final Offenders paramBean) {
		return ocucoffeRepository.validateAliasescheckDupNameCur(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param OffenderIdentifiers
	 *            paramBean
	 */
	public List<Object> checkPncExistsgetPncEx(final OffenderIdentifier paramBean) {
		return ocucoffeRepository.checkPncExistsgetPncEx(paramBean);
	}

	/**
	 * Fetch the records from database table
	 * 
	 * @param Offenders
	 *            searchRecord
	 */
	public List<Offenders> offsearchOffenders(final Offenders searchRecord) {
		return ocucoffeRepository.offsearchOffenders(searchRecord);

	}

	/**
	 * Insert the records from database table
	 * 
	 * @param lstOffenders
	 * 
	 */
	@Transactional
	public Long offInsertOffenders(final List<Offenders> lstOffenders) {
		Long result = 0L;
		List<Offenders> offenderList = new ArrayList<>();
		for (Offenders offList : lstOffenders) {
			offList.setCreateDate(dateService.getDBTime());
			//offList.setModifyDateTime(dateService.getDBTime());
			offList.setCaseloadType("INST"); //TODO - Case-load Type should be configurable
			if (offList.getOffenderId() == null) {
				Long offenderid = ocucoffeRepository.preInsertgetNextAliasDAO();
				offList.setOffenderId(offenderid);
			}
			if (offList.getRootOffenderId() == null || offList.getRootOffenderId() == new BigDecimal(0)) {
				offList.setRootOffenderId(new BigDecimal(offList.getOffenderId()));
			}
			if (offList.getOffenderIdDisplay() == null || offList.getOffenderIdDisplay().equalsIgnoreCase("undefined")
					|| offList.getOffenderIdDisplay().equals("null")) {
				if (offList.getOffenderId().toString().equals(offList.getRootOffenderId().toString())) {
					Long repeat = Long.valueOf(checkOffenderIdDisplay(offList.getOffenderIdDisplay()));
					if (repeat > 0) {
						offList.setOffenderIdDisplay(String.valueOf(offList.getOffenderId() + 1));
					} else {
						offList.setOffenderIdDisplay(String.valueOf(offList.getOffenderId()));
					}

				} else {
					offList.setOffenderIdDisplay(String.valueOf(offList.getOffenderId()));
				}
			}
			if("Y".equals(ocucoffeRepository.getIdDisplayProfileValue())) {
				if (offList.getOffenderIdDisplay().length() < 10) {
					for (int i = offList.getOffenderIdDisplay().length(); i < 10; i++) {
						offList.setOffenderIdDisplay("0" + offList.getOffenderIdDisplay());
					}
				}
			}
			offenderList.add(offList);
			//BEFORE EACH ROW TRIGGER OMTOFFSRC
			offenderList=omtoffsrcService.omtoffsrc(offenderList, "INSERT");
			result = ocucoffeRepository.offInsertOffenders(offenderList);
			//AFTER EACH ROW TRIGGER OFFENDERS_VINE_INTF_TRG
			offendersVineIntfTrgService.offendersVineIntfTrg(offenderList, "INSERT");
			//AFTER EACH ROW TRIGGER OFFENDERS_TJN
			//offendersTjnService.offendersTjn(offenderList, null, "INSERT");
			if(result!=null) {
				offenderList.get(0).setRequestStatus("NEW");
				offenderList.get(0).setExternalId(ocucoffeRepository.fetchExternalId().longValue());
				//EXTERNAL_SEARCH_PREINSERT_GET_NEXT
				ocucoffeRepository.offInsertExternalSearchOffenders(offenderList);
			}
			offenderList.clear();
		}
		return result;
	}

	/**
	 * Fetch the records from database table
	 * 
	 * @return List<Offenders>
	 * @param Offenders
	 *            searchRecord
	 */
	public List<Offenders> aliasSearchOffenders(final Offenders searchRecord) {
		return ocucoffeRepository.aliasSearchOffenders(searchRecord);

	}

	/**
	 * Insert the records from database table
	 * 
	 * @param lstOffenders
	 * 
	 */
	@Transactional
	public Integer aliasInsertOffenders(final List<Offenders> lstOffenders) {
		return ocucoffeRepository.aliasInsertOffenders(lstOffenders);
	}

	/**
	 * Fetch the records from database table
	 * 
	 * @return List<OffenderIdentifiers>
	 * @param OffenderIdentifiers
	 *            searchRecord
	 */
	public List<OffenderIdentifier> offIdSearchOffenderIdentifiers(final OffenderIdentifier searchRecord)
			throws SQLException {
		return ocucoffeRepository.offIdSearchOffenderIdentifiers(searchRecord);

	}

	/**
	 * Insert the records from database table
	 * 
	 * @param lstOffenderIdentifiers
	 * 
	 */
	@Transactional
	public String offIdInsertOffenderIdentifiers(final List<OffenderIdentifier> lstOffenderIdentifiers) {
		String isInserted = "0";
		List<OffenderIdentifier> offenderIdentifiersList = new ArrayList<>();
		try {
			for (OffenderIdentifier identifiers : lstOffenderIdentifiers) {
				if (identifiers.getCreateDateTime() == null) {
					identifiers.setCreateDateTime(dateService.getDBTime());
				}
				List<Object> sequence = this.preInsertgetNextIdentifier(identifiers);
				for (Object seq : sequence) {
					identifiers.setOffenderIdSeq(String.valueOf(seq));
				}
				if(identifiers.getIdentifierType().equals("PNC")) {
					Boolean valid = ocucoffeRepository.checkPncValidation(identifiers.getIdentifier());
					if(!valid) {
						isInserted = "Invalid PNC";
						return isInserted;
					}
					List<Object> repeat = ocucoffeRepository.checkPncExistsgetPncEx(identifiers);
					if(!repeat.isEmpty()) {
						isInserted = "PNC Exist";
						return isInserted;
					}
				}
				offenderIdentifiersList.add(identifiers);
				isInserted = ocucoffeRepository.offIdInsertOffenderIdentifiers(offenderIdentifiersList)+"";
				//AFTER EACH ROW OFF_IDENT_VINE_INTF_TRG
				offIdentVineIntfTrgService.offIdentVineIntfTrg(offenderIdentifiersList, null, "INSERT");
				offenderIdentifiersList.clear();
			}

		} catch (Exception e) {
			logger.error("offIdInsertOffenderIdentifiers",e.getMessage());
			isInserted = e.getMessage().toUpperCase();

		}
		return isInserted;
	}

	/**
	 * Update the records from database table
	 * 
	 * @param lstOffenderIdentifiers
	 * 
	 */
	@Transactional
	public String offIdUpdateOffenderIdentifiers(final List<OffenderIdentifier> lstOffenderIdentifiers) {
		String retVal=null;
		retVal= ocucoffeRepository.offIdUpdateOffenderIdentifiers(lstOffenderIdentifiers)+ "";
		offIdentVineIntfTrgService.offIdentVineIntfTrg(lstOffenderIdentifiers, null, "UPDATE");
		return retVal;

	}

	/**
	 * Fetch the records from database table
	 * 
	 * @return List<OffenderIdentifiers>
	 * @param OffenderIdentifiers
	 *            searchRecord
	 */
	public List<OffenderIdentifier> offIdAllSearchOffenderIdentifiers(final OffenderIdentifier searchRecord) {
		return ocucoffeRepository.offIdAllSearchOffenderIdentifiers(searchRecord);

	}

	/**
	 * This method is used to execute a record group
	 * 
	 * @return List<ReferenceCodes>
	 * @param ReferenceCodes
	 *            obj
	 */
	public List<ReferenceCodes> rgIdentifierTypeRecordGroup() {
		return ocucoffeRepository.rgIdentifierTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 * 
	 * @return List<ReferenceCodes>
	 */
	@Override
	public List<ReferenceCodes> rgAliasNameTypeRecordgroup() {
		return ocucoffeRepository.rgAliasNameTypeRecordGroup();
	}

	/**
	 * This method is used to execute a record group
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgOffSuffixRecordGroup() {
		return ocucoffeRepository.rgOffSuffixRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 * 
	 */
	public List<ReferenceCodes> rgOffSexRecordGroup() {
		return ocucoffeRepository.rgOffSexRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgOffRaceRecordGroup() {
		return ocucoffeRepository.rgOffRaceRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 * 
	 * @param List<ReferenceCodes>
	 * 
	 */
	public List<ReferenceCodes> rgIdSourceRecordGroup() {
		return ocucoffeRepository.rgIdSourceRecordGroup();

	}

	@Override
	public List<ReferenceCodes> rgIdentifierTypeRgroup() {
		return null;
	}

	@Transactional
	public String insertUpdateDeleteOffenderIdentifiers(final OffenderIdentifiersCommitBean commitBean) {
		String liReturn = "0";
		if ((commitBean.getInsertList() != null) && (commitBean.getInsertList().size() > 0)) {
			List<OffenderIdentifier> lstOffenderIdentifiers = commitBean.getInsertList();
			lstOffenderIdentifiers.forEach(data->{
			data.setCreateUserId(commitBean.getCreateUserId());
			});
			liReturn = offIdInsertOffenderIdentifiers(lstOffenderIdentifiers);
		}
		if ((commitBean.getUpdateList() != null) && (commitBean.getUpdateList().size() > 0)) {
			commitBean.getUpdateList().forEach(data->{
				data.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = offIdUpdateOffenderIdentifiers(commitBean.getUpdateList());
		}
		return liReturn;
	}

	// @Transactional
	// public Integer insertUpdateOffenders(final OffenderIdentifiersCommitBean
	// commitBean) {
	// int liReturn = 0;
	// if ((commitBean.getInsertList() != null) &&
	// (commitBean.getInsertList().size() > 0)) {
	// List<Offenders> lstOffenders = commitBean.getInsertList();
	// liReturn = aliasInsertOffenders(lstOffenders);
	// }
	// if ((commitBean.getUpdateList() != null) &&
	// (commitBean.getUpdateList().size() > 0)) {
	// liReturn = offIdUpdateOffenderIdentifiers(commitBean.getUpdateList());
	// }
	// return liReturn;
	// }

	@Transactional
	public Integer insertUpdateDeleteOffenders(final OffendersCommitBean commitBean) {
		int liReturn = 0;
		if ((commitBean.getInsertList() != null) && (commitBean.getInsertList().size() > 0)) {
			liReturn = aliasInsertOffenders(commitBean.getInsertList());
		}
		return liReturn;
	}

	@Transactional
	public Long offInsertOffenders(final OffendersCommitBean commitBean) {
		Long liReturn = 0L;
		if ((commitBean.getInsertList() != null) && (commitBean.getInsertList().size() > 0)) {
			commitBean.getInsertList().forEach(bean->{
				bean.setCreateUserId(commitBean.getCreateUserId());
			});
			liReturn = offInsertOffenders(commitBean.getInsertList());
		}
		return liReturn;
	}

	@Override
	public Integer getOffenderMinAge(String caseload) {
		return ocucoffeRepository.getOffenderMinAge(caseload);
	}

	@Override
	public String checkOffenderIdDisplay(final String poffenderIdDisplay) {
		String offenderIdDisplay = poffenderIdDisplay;
		String result = null;
		try {
			if (offenderIdDisplay == null || offenderIdDisplay.equals("null") || offenderIdDisplay.equals("")) {
//				Long offenderid = ocucoffeRepository.preInsertgetNextAliasDAO();
//				offenderIdDisplay = offenderid.toString();
				return 0 + "";
			}
			if (offenderIdDisplay.length() < 10) {
				for (int i = offenderIdDisplay.length(); i < 10; i++) {
					offenderIdDisplay = "0" + offenderIdDisplay;
				}
			}
			result = ocucoffeRepository.checkOffenderIdDisplay(offenderIdDisplay)+"";
		} catch (Exception e) {
			logger.error("checkOffenderIdDisplay", e);
			result = e.getMessage();
		}
		return result;
	}

	@Override
	public Date ocucoffeGetCurrentDate() {
		return ocucoffeRepository.ocucoffeGetCurrentDate();
	}
	
	/*
	 * (non-Javadoc)
	 * @see net.syscon.s4.globaloffenderrecords.OcucoffeService#createOffenders(java.util.List)
	 */
	@Transactional
	@Override
	public OffenderDetails createOffenders(final List<OffenderDetail> offenderDetails, final String userName) {
		
		logger.info("createOffenders started");
		
		List<Offenders> lstOffenders = new ArrayList<>();
		for(OffenderDetail offenderDetail: offenderDetails) {
			Offenders offenders =  new Offenders();
			offenders.setAge(new BigDecimal(ageValidationvsAgecur(offenderDetail.getBirthDate()).toString()));
			offenders.setAliasNameType("Working"); // TODO Configure Alias Name Type
			offenders.setAliasOffenderId(null);
			offenders.setBirthDate(offenderDetail.getBirthDate());
			offenders.setFirstName(offenderDetail.getFirstName());
			offenders.setLastName(offenderDetail.getLastName());
			offenders.setMiddleName(offenderDetail.getMiddleName());
			offenders.setSexCode(offenderDetail.getSexCode());
			offenders.setRaceCode(offenderDetail.getRaceCode());
			offenders.setOffenderIdDisplay(null);
			offenders.setIdSourceCode("SEQ");
			offenders.setOffenderNameSeq(new BigDecimal(1));
			lstOffenders.add(offenders);
			logger.info("createOffenders Offender creation started for - "+offenderDetail.getFirstName() +", "+offenderDetail.getLastName());
		}
		long offenderId = offInsertOffenders(lstOffenders);
		
		logger.info("createOffenders Offender Created successfully with offender Id - "+offenderId);
		
		
		 List<OffenderExternalMovements> offenderExternalMovements = new ArrayList<>();
		 
		 OffenderExternalMovements offenderExternalMovement = new OffenderExternalMovements();
		 offenderExternalMovement.setActiveFlag("Y");
		 offenderExternalMovement.setCreateDatetime(new Date());
		 offenderExternalMovement.setCreateUserId("JTI_USER");
		 offenderExternalMovement.setDirectionCode("IN");
		 offenderExternalMovement.setFromAgyLocId("OUT");
		 offenderExternalMovement.setLivUnitDesc("ITAG-A&D");
		 offenderExternalMovement.setModifyDatetime(new Date());
		 offenderExternalMovement.setModifyUserId("JTI_USER");
		 offenderExternalMovement.setMovementReasonCode("NEW");
		 offenderExternalMovement.setOffenderId(new BigDecimal(offenderId));
		 offenderExternalMovement.setRootOffenderId(new BigDecimal(offenderId));
		 offenderExternalMovement.setToAgyLocId("ITAG"); // TODO Configure Location in which Facility it will go.
		 
		 
		 //TODO - Change the date according to new logic
		 offenderExternalMovement.setMovementDate(new Date());
		 offenderExternalMovement.setMovementTime(new Date());
		 offenderExternalMovements.add(offenderExternalMovement);
		 
		 logger.info("createOffenders Offender external movement started for offender Id - "+offenderId);
		 OffenderBookings  offenderBookings = oidadmisService.offemInsertOffenderExternalMovements(offenderExternalMovements, userName);
		 logger.info("createOffenders Offender external movement process successfully with offender Id - "+offenderId + " and offedenrBooking ID is "+offenderBookings.getOffenderBookId());
		
		if(offenderBookings != null) {
			VHeaderBlock2 vheaderBlock = new VHeaderBlock2();
			vheaderBlock.setStatusDisplay("ACTIVE");
			vheaderBlock.setActiveFlag(offenderBookings.getActiveFlag());
			vheaderBlock.setPrisonLocation(offenderBookings.getLivUnitDesc());
			vheaderBlock.setStatusReason("ADM - "+offenderBookings.getStatusReason());
			vheaderBlock.setBookingStatus(offenderBookings.getBookingStatus());
			vheaderBlock.setOffenderBookId(new BigDecimal(offenderBookings.getOffenderBookId()));
			logger.info("createOffenders Offender booking update started for offender Id - "+offenderId);
			Integer returnId = oidadmisService.offBookingUpdateOffenderExternalMovements(vheaderBlock);
			logger.info("createOffenders Offender booking updated successfully for offender Id - "+offenderId +" and return ID is "+returnId);
		}
		
		
		for(OffenderDetail offenderDetail: offenderDetails) {
			offenderDetail.setOffenderId(offenderId);
			offenderDetail.setOffenderBookId(offenderBookings.getOffenderBookId());
		}
		
		OffenderDetails offDetails = new OffenderDetails();
		offDetails.setOffenders(offenderDetails);
		return offDetails;
	}


}