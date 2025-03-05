package net.syscon.s4.inst.demographicsbiometrics.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.Addresses;
import net.syscon.s4.common.beans.AddressesCommitBean;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.demographicsbiometrics.OcdoapopRepository;
import net.syscon.s4.inst.demographicsbiometrics.OcdoapopService;
import net.syscon.s4.triggers.AddresesTJNService;
import net.syscon.s4.triggers.AddressesT1Service;
import net.syscon.s4.triggers.AddressesT2Service;
import net.syscon.s4.triggers.AddressesT3Service;
import net.syscon.s4.triggers.AddressesT4Service;

/**
 * Class OcdoapopServiceImpl
 */
@Service
public class OcdoapopServiceImpl extends BaseBusiness implements OcdoapopService {

	private static Logger logger = LogManager.getLogger(OcdpersoServiceImpl.class.getName());
	
	@Autowired
	private OcdoapopRepository ocdoapopRepository;
	@Autowired
	private AddresesTJNService addresesTJNService;
	@Autowired
	private AddressesT1Service addresesT1Service;
	@Autowired
	private AddressesT2Service addresesT2Service;
	@Autowired
	private AddressesT4Service addresesT4Service;
	@Autowired
	private AddressesT3Service addresesT3Service;

	/**
	 * Creates new OcdoapopServiceImpl class Object
	 */
	public OcdoapopServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public ReferenceCodes addressWhenCreateRecord(final ReferenceCodes paramBean) {
		ReferenceCodes referenceCodes = ocdoapopRepository.addressWhenCreateRecord(paramBean);
		return referenceCodes;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<Phones> addressKeyDelrec(final Phones paramBean) {
		final List<Phones> phones = ocdoapopRepository.addressKeyDelrec(paramBean);
		return phones;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public Object ocdoapopPreInsert() {
		final Object object = ocdoapopRepository.ocdoapopPreInsert();
		return object;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public SystemProfiles citySystemProfile(final SystemProfiles paramBean) {
		SystemProfiles systemProfiles = ocdoapopRepository.citySystemProfile(paramBean);
		return systemProfiles;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<Addresses> addressExecuteQuery(final Addresses searchRecord) {
		return ocdoapopRepository.addressExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstADDRESS
	 *
	 * @
	 */
	@Transactional
	public Addresses addressCommit(final AddressesCommitBean commitBean) {
		int liReturn = 0;
		Addresses returnObj=new Addresses();
		List<Addresses> addressJnComitList = new ArrayList<>();
		Addresses old = new Addresses();
		try {
			if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
				for (Addresses bean : commitBean.getUpdateList()) {
					bean.setModifyUserId(commitBean.getCreateUserId());
					bean.setVerifiedPayload(bean.getFullValidatedAddress());
					try {
						addresesT1Service.addresesT1Trigger(bean.getOwnerClass(),bean.getOwnerId()!=null? bean.getOwnerId().longValue():null,
								bean.getOwnerSeq(), bean.getOwnerCode());
					} catch (Exception e) {
						logger.error("Error in Class " + this.getClass().getName() +"In addressCommit method",e);
					}
				}
				liReturn = ocdoapopRepository.addressUpdateAddresses(commitBean.getUpdateList());
				for (Addresses objectOne : commitBean.getUpdateList()) {
					objectOne.setJnOracleUser(commitBean.getCreateUserId());
					objectOne.setJnOperation("UPD");
					objectOne.setJnSession(BigDecimal.ZERO);
					objectOne.setCreateUserId(commitBean.getCreateUserId());
					try {
						addresesT4Service.AddresesT4Trigger(objectOne);
						addresesT3Service.AddresesT3Trigger(old, objectOne);
					} catch (Exception e) {
						logger.error("Error in Class " + this.getClass().getName() +"In addressCommit method",e);
					}
					addressJnComitList.add(objectOne);
				}
				/*
				 * try { addresesTJNService.addressTJNTrigger(addressJnComitList); } catch
				 * (Exception e) { logger.error("Error in Class " + this.getClass().getName()
				 * +"In addressCommit method",e); }
				 */
			}
			if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
				final List<Addresses> addressList = new ArrayList<>();
				for (final Addresses address : commitBean.getInsertList()) {
					try {
						addresesT1Service.addresesT1Trigger(address.getOwnerClass(), address.getOwnerId()!=null?address.getOwnerId().longValue():null,
								address.getOwnerSeq(), address.getOwnerCode());
					} catch (Exception e) {
						logger.error("Error in Class " + this.getClass().getName() +"In addressCommit method",e);
					}
					address.setAddressId(Long.valueOf(ocdoapopRepository.ocdoapopPreInsert().toString()));
					address.setCreateUserId(commitBean.getCreateUserId());
					address.setVerifiedPayload(address.getFullValidatedAddress());
					addressList.add(address);
					liReturn = ocdoapopRepository.addressInsertAddresses(addressList);
					try {
						addresesT3Service.AddresesT3Trigger(old, address);
					} catch (Exception e) {
						logger.error("Error in Class " + this.getClass().getName() +"In addressCommit method",e);
					}
					addressList.clear();
				}
				for (Addresses objectTwo : commitBean.getInsertList()) {
					objectTwo.setJnOracleUser(commitBean.getCreateUserId());
					objectTwo.setJnOperation("UPD");
					objectTwo.setJnSession(BigDecimal.ZERO);
					objectTwo.setCreateUserId(commitBean.getCreateUserId());
					try {
						addresesT3Service.AddresesT3Trigger(old, objectTwo);
					} catch (Exception e) {
						logger.error("Error in Class " + this.getClass().getName() +"In addressCommit method",e);
					}
					addressJnComitList.add(objectTwo);
				}
				/*
				 * try { addresesTJNService.addressTJNTrigger(addressJnComitList); } catch
				 * (Exception e) { logger.error("Error in Class " + this.getClass().getName()
				 * +"In addressCommit method",e); }
				 */
			}
			if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
				commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
				liReturn = ocdoapopRepository.addressDeleteAddresses(commitBean.getDeleteList());
				for (Addresses objectThree : commitBean.getDeleteList()) {
					objectThree.setJnOracleUser(commitBean.getCreateUserId());
					objectThree.setJnOperation("UPD");
					objectThree.setJnSession(BigDecimal.ZERO);
					objectThree.setCreateUserId(commitBean.getCreateUserId());
					addressJnComitList.add(objectThree);
					try {
						addresesT2Service.addresesT2Trigger(objectThree.getAddressId());
					} catch (Exception e) {
						logger.error("Error in Class " + this.getClass().getName() +"In addressCommit method",e);
					}
				}
				/*
				 * try { addresesTJNService.addressTJNTrigger(addressJnComitList); } catch
				 * (Exception e) { logger.error("Error in Class " + this.getClass().getName()
				 * +"In addressCommit method",e); }
				 */

			}
		} catch (Exception e) {
			logger.error("In addressCommit method while deleting address"+e.getMessage());
			returnObj.setErrorMessage(e.getMessage());
		}
		returnObj.setAddressId(liReturn);

		return returnObj;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgCityRecordGroup() {
		return ocdoapopRepository.rgCityRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgCountyRecordGroup() {
		return ocdoapopRepository.rgCountyRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgCountryRecordGroup() {
		return ocdoapopRepository.rgCountryRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgTypeRecordGroup() {
		return ocdoapopRepository.rgTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgSpecialNeedsRecordGroup() {
		return ocdoapopRepository.rgSpecialNeedsRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgProvStateCodeRecordGroup() {
		return ocdoapopRepository.rgProvStateCodeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgStreetDirRecordGroup() {
		return ocdoapopRepository.rgStreetDirRecordGroup();

	}

}