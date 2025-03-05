package net.syscon.s4.inst.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.Addresses;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.VAddresses;
import net.syscon.s4.inst.OcuoccupRepository;
import net.syscon.s4.inst.OcuoccupService;
import net.syscon.s4.inst.booking.beans.OffenderContactPersons;
import net.syscon.s4.inst.movementexternal.beans.RpOtherOccupants;
import net.syscon.s4.inst.movementexternal.beans.RpOtherOccupantsCommitBean;
import net.syscon.s4.triggers.AddresesTJNService;
import net.syscon.s4.triggers.AddressesT1Service;
import net.syscon.s4.triggers.AddressesT3Service;
import net.syscon.s4.triggers.AddressesTwfService;
import net.syscon.s4.triggers.OffenderContactPersonsT1Service;

/**
 * Class OcuoccupServiceImpl
 */
@Service
public class OcuoccupServiceImpl extends BaseBusiness implements OcuoccupService {

	@Autowired
	private OcuoccupRepository ocuoccupRepository;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcuoccupServiceImpl.class.getName());

	@Autowired
	private AddressesT3Service ddressesT3Service;

	@Autowired
	private AddressesT1Service addressesT1Service;

	@Autowired
	private AddressesTwfService aDDRESSESTWFService;

	@Autowired
	private AddresesTJNService addresesTJNService;
	
	@Autowired
	private OffenderContactPersonsT1Service  offenderContactPersonsT1Service;

	/**
	 * Creates new OcuoccupServiceImpl class Object
	 */
	public OcuoccupServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public Phones rpOtherOccupantsPostQuery(final Phones paramBean) {
		Phones phones = new Phones();
		return phones;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<RpOtherOccupants> ocuoccupKeyExit(final RpOtherOccupants paramBean) {
		List<RpOtherOccupants> rpOtherOccupantsList = new ArrayList<>();
		return rpOtherOccupantsList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<RpOtherOccupants> ocuoccupPostFormsCommit(final RpOtherOccupants paramBean) {
		List<RpOtherOccupants> rpOtherOccupantsList = new ArrayList<>();
		return rpOtherOccupantsList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<OmsModules> CreateFormGlobals(final OmsModules paramBean) {
		List<OmsModules> omsModulesList = new ArrayList<>();
		return omsModulesList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<RpOtherOccupants> rpOtherOccupantsExecuteQuery(final RpOtherOccupants searchRecord) {
		List<RpOtherOccupants> returnList = new ArrayList<>();
		returnList = ocuoccupRepository.rpOtherOccupantsExecuteQuery(searchRecord);
		for (final RpOtherOccupants returnObj : returnList) {
			ocuoccupRepository.rpOtherOccupantsExecuteQuery(returnObj);
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstRP_OTHER_OCCUPANTS
	 *
	 * 
	 */
	@Transactional
	public Integer rpOtherOccupantsCommit(final RpOtherOccupantsCommitBean commitBean) {
		Integer liReturn = 0;
		List<Addresses> addressInsertList = new ArrayList<>();
		List<OffenderContactPersons> offContPerList = new ArrayList<>();
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final RpOtherOccupants occupentObj : commitBean.getInsertList()) {
				occupentObj.setCreateUserId(commitBean.getCreateUserId());
				commitBean.getInsertList().forEach(bean->bean.setCreateUserId(commitBean.getCreateUserId()));
				if (commitBean.getAddressesBean().getCommentText() != null
						&& "NEW_CONTACT".equals(commitBean.getAddressesBean().getCommentText())
						&& ("null".equals(occupentObj.getRpOtherOccupantId())
								|| occupentObj.getRpOtherOccupantId() == 0)) {
					OffenderContactPersons offContPerObj = new OffenderContactPersons();
					offContPerList = new ArrayList<>();
					Long contactPersonId = ocuoccupRepository.findNextContactPersonId();
					occupentObj.setOffenderContactPersonId(BigDecimal.valueOf(contactPersonId));
					offContPerObj.setOffenderContactPersonId(contactPersonId);
					offContPerObj.setOffenderBookId(commitBean.getAddressesBean().getOffenderBookId());
					offContPerObj.setContactType(occupentObj.getContactType());
					offContPerObj.setRelationshipType(occupentObj.getRelationshipType());
					offContPerObj.setPersonId(BigDecimal.valueOf(occupentObj.getPersonId()));
					offContPerObj.setCreateUserId(occupentObj.getCreateUserId());
					offContPerList.add(offContPerObj);
					// insert to OffenderContacts
					offenderContactPersonsT1Service.offenderContactPersonsT1(offContPerObj);
					liReturn = ocuoccupRepository.preOffenderContactsInsertRpOtherOccupants(offContPerList);
				}
				VAddresses vaddressGetVal = new VAddresses();
				vaddressGetVal.setAddressId(BigDecimal.valueOf(commitBean.getAddressesBean().getAddressId()));
				vaddressGetVal.setOwnerId(BigDecimal.valueOf(occupentObj.getPersonId()));
				String addressFlag = ocuoccupRepository.preFindAddressFlag(vaddressGetVal);
				if (addressFlag != null && addressFlag == "N") {
					Addresses instAddress = new Addresses();
					instAddress = ocuoccupRepository.preFindAddressValues(vaddressGetVal);
					instAddress.setOwnerId(BigDecimal.valueOf(occupentObj.getPersonId()));
					instAddress.setCreateUserId(occupentObj.getCreateUserId());
					instAddress.setJnOperation("INS");
					instAddress.setJnOracleUser(commitBean.getCreateUserId());
					addressInsertList.add(instAddress);
				}
			}
			// insert to Addresses
			if (addressInsertList.size() > 0) {
				try { 
					Integer retVal  = addressesT1Service.addresesT1Trigger(commitBean.getAddressesBean().getOwnerClass(), commitBean.getAddressesBean().getOwnerId()!=null?commitBean.getAddressesBean().getOwnerId().longValue(): null, commitBean.getAddressesBean().getOwnerSeq(), commitBean.getAddressesBean().getOwnerCode());
					liReturn = ocuoccupRepository.preAddressInsertRpOtherOccupants(addressInsertList);
					ddressesT3Service.AddresesT3Trigger(null, commitBean.getAddressesBean());
					aDDRESSESTWFService.addressesTwf(commitBean.getAddressesBean());
					//Integer triRetVal = addresesTJNService.addressTJNTrigger(addressInsertList);
					
				}
				catch (Exception e) {
					logger.error("in insertion:"+e);
				}
			}
			// insert to rpOtherOcupants
			liReturn = ocuoccupRepository.rpotheroccupantsInsertRpOtherOccupants(commitBean.getInsertList());
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(bean->bean.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = ocuoccupRepository.rpOtherOccupantsUpdateRpOtherOccupants(commitBean.getUpdateList());
		}//updatelist
		
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = ocuoccupRepository.rpOtherOccupantsDeleteRpOtherOccupants(commitBean.getDeleteList());
		}//deletelist
		
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<OffenderContactPersons> rgPersonNameRecordGroup(final String offenderBookId) {
		return ocuoccupRepository.rgPersonNameRecordGroup(offenderBookId);

	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<ReferenceCodes> rgContactTypesRecordGroup() {
		return ocuoccupRepository.rgContactTypesRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<ReferenceCodes> rgRelationshipsRecordGroup(final String contactCode) {
		return ocuoccupRepository.rgRelationshipsRecordGroup(contactCode);

	}

	@Override
	public List<ReferenceCodes> rgContactedRecordGroup() {
		final List<ReferenceCodes> returnList = new ArrayList<>();
		final ReferenceCodes refYObj = new ReferenceCodes();
		refYObj.setCode("Y");
		refYObj.setDescription("Yes");
		returnList.add(refYObj);
		final ReferenceCodes refNObj = new ReferenceCodes();
		refNObj.setCode("N");
		refNObj.setDescription("No");
		returnList.add(refNObj);
		return returnList;
	}

}