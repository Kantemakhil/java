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
import net.syscon.s4.common.beans.InternetAddresses;
import net.syscon.s4.common.beans.OffenderCurfews;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.genericservices.CustomException;
import net.syscon.s4.im.beans.AddressUsages;
import net.syscon.s4.im.beans.AddressUsagesCommitBean;
import net.syscon.s4.im.beans.InternetAddressesCommitBean;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.PhonesCommitBean;
import net.syscon.s4.im.beans.VAddresses;
import net.syscon.s4.inst.demographicsbiometrics.OcdaddreRepository;
import net.syscon.s4.inst.demographicsbiometrics.OcdaddreService;
import net.syscon.s4.triggers.AddresesTJNService;
import net.syscon.s4.triggers.AddressesT1Service;
import net.syscon.s4.triggers.AddressesT2Service;
import net.syscon.s4.triggers.AddressesT3Repository;
import net.syscon.s4.triggers.AddressesT3Service;
import net.syscon.s4.triggers.AddressesT4Service;
import net.syscon.s4.triggers.AddressesTwfService;
import net.syscon.s4.triggers.InternetAddressesT1Service;
import net.syscon.s4.triggers.InternetAddressesT2Service;
import net.syscon.s4.triggers.PhonesT1Service;
import net.syscon.s4.triggers.PhonesT2Service;

/**
 * Class OcdaddreServiceImpl
 */
@Service
public class OcdaddreServiceImpl extends BaseBusiness implements OcdaddreService {
	private static Logger logger = LogManager.getLogger(OcdaddreServiceImpl.class.getName());


	@Autowired
	private OcdaddreRepository ocdaddreDao;
	@Autowired
	private AddressesT3Service addressesT3Service;
	@Autowired
	private PhonesT1Service phonesT1Service;
	@Autowired
	private PhonesT2Service phonesT2Service;
	@Autowired
	private InternetAddressesT1Service internetAddressesT1Service;
	@Autowired
	private InternetAddressesT2Service internetAddressesT2Service;
	@Autowired
	private AddressesT1Service addressesT1Service;
	@Autowired
	private AddresesTJNService addresesTJNService;
	@Autowired
	private AddressesT4Service addressesT4Service;
	@Autowired
	private AddressesTwfService addressesTwfService;
	@Autowired
	private AddressesT2Service addressesT2Service;
	@Autowired
	private AddressesT3Repository addresesT3Repository;

	/**
	 * Creates new OcdaddreBusiness class Object
	 */
	public OcdaddreServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param vAddresses
	 *
	 * @
	 */
	@Override
	public List<Object> offBkgOnCheckDeleteMastervAddCur(final VAddresses vAddresses) {
		return ocdaddreDao.offbkgoncheckdeletemastervaddcur(vAddresses);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param phones
	 *
	 * @
	 */
	public List<Object> offBkgOnCheckDeleteMasterphoneGlobalCur(final Phones phones) {
		return ocdaddreDao.offBkgOnCheckDeleteMasterphoneGlobalCur(phones);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param internetAddresses
	 *
	 * @
	 */
	public List<Object> offBkgOnCheckDeleteMasteremailCur(final InternetAddresses internetAddresses) {
		return ocdaddreDao.offBkgOnCheckDeleteMasteremailCur(internetAddresses);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param addresses
	 *
	 * @
	 */
	public List<Object> offBkgOnCheckDeleteMasteraddrCur(final Addresses addresses) {
		return ocdaddreDao.offBkgOnCheckDeleteMasteraddrCur(addresses);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param addresses
	 *
	 * @
	 */
	public Object vAddOnCheckDeleteMasteraddrCur(final Addresses addresses) {
		return ocdaddreDao.vAddOnCheckDeleteMasteraddrCur(addresses);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param referenceCodes
	 *
	 * @
	 */
	public List<Object> nbtCityKeyListvalgetCityDescription(final ReferenceCodes referenceCodes) {
		return ocdaddreDao.nbtCityKeyListvalgetCityDescription(referenceCodes);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param phones
	 *
	 * @
	 */
	public List<Object> addrOnCheckDeleteMasterphoneAddrCur(final Phones phones) {
		return ocdaddreDao.addrOnCheckDeleteMasterphoneAddrCur(phones);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param addressUsages
	 *
	 * @
	 */
	public Object addrOnCheckDeleteMasteraddrUsageCur(final AddressUsages addressUsages) {
		return ocdaddreDao.addrOnCheckDeleteMasteraddrUsageCur(addressUsages);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param referenceCodes
	 *
	 * @
	 */
	public List<ReferenceCodes> addrWhenCreateRecordgetCountryCur(final ReferenceCodes referenceCodes) {
		return ocdaddreDao.addrWhenCreateRecordgetCountryCur(referenceCodes);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param omsModules
	 *
	 * @
	 */
	public OmsModules createFormGlobals(final OmsModules omsModules) {
		return ocdaddreDao.createFormGlobals(omsModules);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param addressUsages
	 *
	 * @
	 */
	public Object addressTypecheckActiveTypeCur(final AddressUsages addressUsages) {
		return ocdaddreDao.addressTypecheckActiveTypeCur(addressUsages);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param referenceCodes
	 *
	 * @
	 */
	public Object validateCityInfogetCityDescription(final ReferenceCodes referenceCodes) {
		return ocdaddreDao.validateCityInfogetCityDescription(referenceCodes);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param referenceCodes
	 *
	 * @
	 */
	public List<ReferenceCodes> validateCityInfogetCityCode(final ReferenceCodes referenceCodes) {
		return ocdaddreDao.validateCityInfogetCityCode(referenceCodes);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param vAddresses
	 *
	 * @
	 */
	public Object checkHdcAddressActivecheckHdcAddressCur(final VAddresses vAddresses) {
		return ocdaddreDao.checkHdcAddressActivecheckHdcAddressCur(vAddresses);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param offenderCurfews
	 *
	 * @
	 */
	public List<OffenderCurfews> checkHdcAddressActive(final OffenderCurfews offenderCurfews) {
		return ocdaddreDao.checkHdcAddressActive(offenderCurfews);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param vAddresses
	 *
	 * @
	 */
	public Object checkHdcAddressExistcheckHdcAddressCur(final VAddresses vAddresses) {
		return ocdaddreDao.checkHdcAddressExistcheckHdcAddressCur(vAddresses);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param vAddresses
	 *
	 * @
	 */
	public List<VAddresses> vAddSearchVAddresses(final VAddresses vAddresses) {
		return ocdaddreDao.vAddSearchVAddresses(vAddresses);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param addresses
	 *
	 * @
	 */
	public Addresses searchAddresses(final Addresses addresses) {
		return ocdaddreDao.searchAddresses(addresses);

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param addressUsages
	 *
	 * @
	 */
	public List<AddressUsages> addrUsageSearchAddressUsages(final AddressUsages addressUsages) {
		return ocdaddreDao.addrUsageSearchAddressUsages(addressUsages);

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param phones
	 *
	 * @
	 */
	public List<Phones> phoneAddrSearchPhones(final Phones phones) {
		phones.setOwnerClass("ADDR");
		return ocdaddreDao.phoneAddrSearchPhones(phones);

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<Phones> phoneGlobalSearchPhones(final Phones searchRecord) {
		searchRecord.setOwnerClass("OFF");
		return ocdaddreDao.phoneAddrSearchPhones(searchRecord);

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param internetAddresses
	 *
	 * @
	 */
	public List<InternetAddresses> emailSearchInternetAddresses(final InternetAddresses internetAddresses) {
		return ocdaddreDao.emailSearchInternetAddresses(internetAddresses);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgTownRecordGroup() {
		return ocdaddreDao.rgTownRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgCountryRecordGroup() {
		return ocdaddreDao.rgCountryRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgSdirRecordGroup() {
		return ocdaddreDao.rgSdirRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgStateRecordGroup() {
		return ocdaddreDao.rgStateRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgAddressTypeRecordGroup() {
		return ocdaddreDao.rgAddressTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgPhoneTypeRecordGroup() {
		return ocdaddreDao.rgPhoneTypeRecordGroup();

	}

	/**
	 * Insert the records from database table
	 *
	 * @param commitBean
	 * 
	 * @return liReturn
	 *
	 * @
	 */
	@Transactional
	public Integer addrCommit(final AddressesCommitBean commitBean) {
		Integer liReturn = 0;
		final Phones phObj = new Phones();
		List<Phones> phReturnList = new ArrayList<>();
		List<AddressUsages> adrUsageObj = new ArrayList<>();
		final AddressUsages adrUsgObj = new AddressUsages();
	      Addresses newObj = new Addresses();
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {

			for (final Addresses adrObj : commitBean.getUpdateList()) {
				adrObj.setModifyUserId(commitBean.getCreateUserId());
                 adrObj.setVerifiedPayload(adrObj.getFullValidatedAddress());
				final Addresses listObj = ocdaddreDao.searchAddresses(adrObj);
				if (listObj.getPrimaryFlag() != null) {
					if ("Y".equals(adrObj.getPrimaryFlag())
							&& !listObj.getPrimaryFlag().equals(adrObj.getPrimaryFlag())) {
						liReturn = ocdaddreDao.preUpdateAddresses(adrObj);
					}
				}
				if (listObj.getMailFlag() != null) {
					if ("Y".equals(adrObj.getMailFlag()) && !listObj.getMailFlag().equals(adrObj.getMailFlag())) {
						liReturn = ocdaddreDao.preUpdateAddressesMailFlag(adrObj);
					}
				}
				if(adrObj.getOwnerId() == null) {
					adrObj.setOwnerId(BigDecimal.ONE);
				}
				if(adrObj.getOwnerSeq() == null) {
					adrObj.setOwnerSeq(BigDecimal.ONE);
				}
				try {
					addressesT1Service.addresesT1Trigger(adrObj.getOwnerClass(),(adrObj.getOwnerId()).longValue(), adrObj.getOwnerSeq(),
							adrObj.getOwnerCode());
				} catch (CustomException e) {
					logger.error("addrCommit", e);
				}
			}
			liReturn = ocdaddreDao.addrUpdateAddresses(commitBean.getUpdateList());
			for (final Addresses adrObj : commitBean.getUpdateList()) {
				Addresses old=new Addresses();
				old=ocdaddreDao.gettingOldRecord(adrObj.getAddressId());
				adrObj.setJnOracleUser(commitBean.getCreateUserId());
				adrObj.setJnOperation("UPD");
				adrObj.setJnSession(BigDecimal.ZERO);
			addressesT4Service.AddresesT4Trigger(adrObj);
			addressesT3Service.AddresesT3Trigger(old,adrObj);
			addressesTwfService.addressesTwf(adrObj);
			}
			//addresesTJNService.addressTJNTrigger(commitBean.getUpdateList());
		}
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
		

			for (final Addresses adrObj : commitBean.getInsertList()) {
				adrObj.setCreateUserId(commitBean.getCreateUserId());
				 adrObj.setVerifiedPayload(adrObj.getFullValidatedAddress());
				final Object addressSequence = ocdaddreDao.preInsertgetAddrId();
				final Long addrSeqValue = Long.parseLong(addressSequence.toString());
				adrObj.setAddressId(addrSeqValue);
				adrObj.setOwnerClass("OFF");
				if ("Y".equals(adrObj.getPrimaryFlag())) {
					liReturn = ocdaddreDao.preUpdateAddresses(adrObj);
				}
				if ("Y".equals(adrObj.getMailFlag())) {
					liReturn = ocdaddreDao.preUpdateAddressesMailFlag(adrObj);
				}
				if(adrObj.getOwnerId() == null) {
					adrObj.setOwnerId(BigDecimal.ONE);
				}
				if(adrObj.getOwnerSeq() == null) {
					adrObj.setOwnerSeq(BigDecimal.ONE);
				}
				try {
					addressesT1Service.addresesT1Trigger(adrObj.getOwnerClass(),(adrObj.getOwnerId()).longValue(), adrObj.getOwnerSeq(),
							adrObj.getOwnerCode());
				} catch (CustomException e) {
					logger.error("addrCommit", e);
				}

			}
			liReturn = ocdaddreDao.addrInsertAddresses(commitBean.getInsertList());
			for (final Addresses adrObj : commitBean.getInsertList()) {
				adrObj.setJnOracleUser(adrObj.getCreateUserId());
				adrObj.setJnOperation("INS");
				adrObj.setJnSession(BigDecimal.ZERO);
				addressesT3Service.AddresesT3Trigger(newObj,adrObj);
				addressesTwfService.addressesTwf(adrObj);
			}
			//addresesTJNService.addressTJNTrigger(commitBean.getInsertList());
			
			}
		
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (final Addresses adrObj : commitBean.getDeleteList()) {
				adrObj.setModifyUserId(commitBean.getCreateUserId());
				phObj.setOwnerId(BigDecimal.valueOf(adrObj.getAddressId()));
				phObj.setOwnerClass("ADDR");
				phReturnList = ocdaddreDao.phoneAddrSearchPhones(phObj);
				if (phReturnList.size() > 0) {
					liReturn = 2;
					return liReturn;
				}
				adrUsgObj.setAddressId(adrObj.getAddressId());
				adrUsageObj = ocdaddreDao.addrUsageSearchAddressUsages(adrUsgObj);
				if (adrUsageObj.size() > 0) {
					liReturn = 2;
					return liReturn;
				}
			}
			Addresses old = addresesT3Repository.getAddressOldObject(commitBean.getDeleteList().get(0));
			liReturn = ocdaddreDao.addrDeleteAddresses(commitBean.getDeleteList());
			for (final Addresses adrObj : commitBean.getDeleteList()) {
			try {
				addressesT2Service.addresesT2Trigger(adrObj.getAddressId());
			} catch (CustomException e) {
				logger.error("addrCommit", e);
			}
			adrObj.setJnOracleUser(commitBean.getCreateUserId());
			adrObj.setJnOperation("DEL");
			adrObj.setJnSession(BigDecimal.ZERO);
			 addressesT3Service.AddresesT3Trigger(old,adrObj);
			}
			//addresesTJNService.addressTJNTrigger(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param commitBean
	 * 
	 * @return liReturn
	 *
	 * @
	 */
	@Transactional
	public Integer phoneAddrCommit(final PhonesCommitBean commitBean) {
		Integer liReturn = 0;
		Phones newObj = new Phones();

		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			
			for (Phones obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				if(obj.getOwnerId() == null) {
					obj.setOwnerId(BigDecimal.ONE);
				}
				if(obj.getOwnerSeq() == null) {
					obj.setOwnerSeq(BigDecimal.ONE);
				}
				try {
					phonesT1Service.phonesT1Trigger(obj.getOwnerClass(),obj.getOwnerId().longValue(), obj.getOwnerSeq().longValue(),obj.getOwnerCode());
				} catch (CustomException e) {
					logger.error("addPhCommit", e);
				}
			}
			liReturn = ocdaddreDao.phoneAddrInsertPhones(commitBean.getInsertList());
			for (Phones data : commitBean.getInsertList()) {
				phonesT2Service.phonesT2Trigger(data, newObj);
				
				}
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (Phones obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
				if(obj.getOwnerId() == null) {
					obj.setOwnerId(BigDecimal.ONE);
				}
				if(obj.getOwnerSeq() == null) {
					obj.setOwnerSeq(BigDecimal.ONE);
				}
				try {
					phonesT1Service.phonesT1Trigger(obj.getOwnerClass(),obj.getOwnerId().longValue(), obj.getOwnerSeq().longValue(),obj.getOwnerCode());
				} catch (CustomException e) {
					logger.error("addPhCommit", e);
				}

			}
			liReturn = ocdaddreDao.phoneAddrUpdatePhones(commitBean.getUpdateList());
			for (Phones data : commitBean.getUpdateList()) {
				phonesT2Service.phonesT2Trigger(data, newObj);
				
				}

		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = ocdaddreDao.phoneAddrDeletePhones(commitBean.getDeleteList());
			for (Phones data : commitBean.getDeleteList()) {
				phonesT2Service.phonesT2Trigger(data, newObj);
				
			}
		}
		return liReturn;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param commitBean
	 * 
	 * @return liReturn
	 *
	 * @
	 */
	@Transactional
	public Integer phoneGlobalCommit(final PhonesCommitBean commitBean) {
		Integer liReturn = 0;
		Long phSeqValue = null;
		Boolean check = true;
		Phones newObj = new Phones();
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final Phones phObj : commitBean.getInsertList()) {
				phObj.setCreateUserId(commitBean.getCreateUserId());
				if (check) {
					check = false;
					phSeqValue = Long.parseLong(ocdaddreDao.preInsertgetPhoneId().toString());
				}
				phObj.setPhoneId(phSeqValue);
				phSeqValue++;
				if(phObj.getOwnerId() == null) {
					phObj.setOwnerId(BigDecimal.ONE);
				}
				if(phObj.getOwnerSeq() == null) {
					phObj.setOwnerSeq(BigDecimal.ONE);
				}
				try {
					phonesT1Service.phonesT1Trigger(phObj.getOwnerClass(),phObj.getOwnerId().longValue(), phObj.getOwnerSeq().longValue(),phObj.getOwnerCode());
				} catch (CustomException e) {
					logger.error("addPhCommit", e);
				}
			}
			
			liReturn = ocdaddreDao.phoneAddrInsertPhones(commitBean.getInsertList());
			for (Phones data : commitBean.getInsertList()) {
				phonesT2Service.phonesT2Trigger(data, newObj);
			}
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (Phones obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
				if(obj.getOwnerId() == null) {
					obj.setOwnerId(BigDecimal.ONE);
				}
				if(obj.getOwnerSeq() == null) {
					obj.setOwnerSeq(BigDecimal.ONE);
				}
				try {
					phonesT1Service.phonesT1Trigger(obj.getOwnerClass(),obj.getOwnerId().longValue(), obj.getOwnerSeq().longValue(),obj.getOwnerCode());
				} catch (CustomException e) {
					logger.error("addPhCommit", e);
				}
			}
			liReturn = ocdaddreDao.phoneAddrUpdatePhones(commitBean.getUpdateList());
			for (Phones data : commitBean.getUpdateList()) {
				phonesT2Service.phonesT2Trigger(data, newObj);
			}
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = ocdaddreDao.phoneAddrDeletePhones(commitBean.getDeleteList());
			for (Phones data : commitBean.getUpdateList()) {
				phonesT2Service.phonesT2Trigger(data, newObj);
			}
		}
		return liReturn;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param commitBean
	 * 
	 * @return liReturn
	 *
	 * @
	 */
	@Transactional
	public Integer emailCommit(final InternetAddressesCommitBean commitBean) {
		Integer liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (InternetAddresses obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				if(obj.getOwnerId() == null) {
					obj.setOwnerId(BigDecimal.ONE);
				}
				if(obj.getOwnerSeq() == null) {
					obj.setOwnerSeq(BigDecimal.ONE);
				}
				try {
					internetAddressesT1Service.internetAddressesT1Trigger(obj.getOwnerClass(), obj.getOwnerId().longValue()
							, obj.getOwnerSeq().longValue(), obj.getOwnerCode());
				} catch (CustomException e) {
					logger.error("iAddCommit", e);

				}
			}

			liReturn = ocdaddreDao.emailInsertInternetAddresses(commitBean.getInsertList());
			for (InternetAddresses data : commitBean.getInsertList()) {
				internetAddressesT2Service.internetAddressesT2Trigger(data);
				}
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (InternetAddresses obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
				if(obj.getOwnerId() == null) {
					obj.setOwnerId(BigDecimal.ONE);
				}
				if(obj.getOwnerSeq() == null) {
					obj.setOwnerSeq(BigDecimal.ONE);
				}
				try {
					internetAddressesT1Service.internetAddressesT1Trigger(obj.getOwnerClass(), obj.getOwnerId().longValue()
							, obj.getOwnerSeq().longValue(), obj.getOwnerCode());
				} catch (CustomException e) {
					logger.error("iAddCommit", e);

				}
			}
			liReturn = ocdaddreDao.emailUpdateInternetAddresses(commitBean.getUpdateList());
			for (InternetAddresses data : commitBean.getUpdateList()) {
				internetAddressesT2Service.internetAddressesT2Trigger(data);
			}
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = ocdaddreDao.emailDeleteInternetAddresses(commitBean.getDeleteList());
			for (InternetAddresses data : commitBean.getDeleteList()) {
				internetAddressesT2Service.internetAddressesT2Trigger(data);
			}
		}
		return liReturn;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param commitBean
	 * 
	 * @return liReturn
	 *
	 * @
	 */
	@Transactional
	public Integer addrUsageCommit(final AddressUsagesCommitBean commitBean) {
		Integer liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (AddressUsages obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = ocdaddreDao.addrUsageInsertAddressUsages(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (AddressUsages obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocdaddreDao.addrUsageUpdateAddressUsages(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = ocdaddreDao.addrUsageDeleteAddressUsages(commitBean.getDeleteList());
		}
		return liReturn;
	}

	@Override
	public List<VAddresses> vAddressAndPhoneExecuteQuery(final VAddresses vAddresses) {
		List<VAddresses> resultLists = ocdaddreDao.vAddSearchVAddresses(vAddresses);
		final Phones phone = new Phones();
		final AddressUsages addressUsages = new AddressUsages();
		resultLists.forEach(address -> {
			 if(address.getProvStateDesc() == null) {
         		address.setProvStateDesc(address.getProvStateCode());
           }
			
           if(address.getCityName() == null) {
       		address.setCityName(address.getCityCode());
         }
			
			if (address.getFullAddress() != null && !address.getFullAddress().trim().equals("")) {
				 String suite = "";
				 if(address.getSuiteNumber() != null ) {
					 suite = address.getSuiteNumber(); 
					 } 
				 String streetNumber = "";
				if(address.getStreetNumber() != null) {
					streetNumber = address.getStreetNumber();
				}
				
				String street = "";
				if (address.getStreet() != null) {
					street = address.getStreet();
				}
				 String direction = "";
				if (address.getStreetDirectionDesc() != null) {
					direction = address.getStreetDirectionDesc();
				}
				String city = "";
				if (address.getCityName() != null) {
					city = address.getCityName();
				}
				String state = "";
				if (address.getProvStateDesc() != null ) {
					state = address.getProvStateDesc();
				}
				String country = "";
				if(address.getCountryDesc() != null) {
					country = address.getCountryDesc();
				}
				String zipCode = "";
				if(address.getZipPostalCode() != null) {
					zipCode = address.getZipPostalCode();
				}

				if (address.getFullAddress().equalsIgnoreCase("No fixed address")) {
					address.setHouse(address.getFullAddress());
					String fullAddressNFA = "NFA -" + city + ", " + state + ", " + country;
					if (fullAddressNFA.trim().contains("- ,")) {
						fullAddressNFA = fullAddressNFA.trim().replace("- ,", "- ");
					}
					if (fullAddressNFA.trim().startsWith("-")) {
						fullAddressNFA = fullAddressNFA.trim().substring(1);
					}
					if (fullAddressNFA.trim().startsWith(",")) {
						fullAddressNFA = fullAddressNFA.trim().substring(1);
					}
					address.setFullAddress(fullAddressNFA);
				} else {
					String fullAddress = suite + " " + streetNumber + " " + street + " " + direction + "-" + city + ", " + state+ ", " + country+ ", "
							+ zipCode;
					if (fullAddress.trim().contains("- ,")) {
						fullAddress = fullAddress.trim().replace("- ,", "- ");
					}
					if (fullAddress.trim().startsWith("-")) {
						fullAddress = fullAddress.trim().substring(1);
					}
					if (fullAddress.trim().startsWith(",")) {
						fullAddress = fullAddress.trim().substring(1);
					}
					if(fullAddress.trim().endsWith(",")) {
						while(fullAddress.trim().endsWith(",")) {
							fullAddress = fullAddress.trim().substring(0, fullAddress.trim().length()-2);
						}
					}
					address.setFullAddress(fullAddress);
				
				String house = suite + " , " + streetNumber +" , " + street +" , " + direction;
				if(house.trim().contains("-")) {
					house = house.trim().replaceAll("-", "");
				}
				if (house.trim().startsWith(",")) {
					while(house.trim().startsWith(",")) {
						house = house.trim().substring(1);
					}
				}
				if (house.trim().endsWith(",")) {
					while(house.trim().endsWith(",")) {
						house = house.trim().substring(0, house.trim().length()-2);
					}
				}
				if (house.trim().contains("  ")) {
					while(house.trim().contains("  ")) {
						house = house.trim().replaceAll("  ", " ");
					}
				}
				
				if (house.trim().contains(", ,")) {
					house = house.trim().replaceAll(", ,", " , ");
				}
				address.setHouse(house);
				}
			}
			if (address.getAddressId() != null) {
				phone.setOwnerId(address.getAddressId());
				phone.setOwnerClass("ADDR");
				addressUsages.setAddressId(Long.valueOf(address.getAddressId().toString()));
				address.setPhones(ocdaddreDao.phoneAddrSearchPhones(phone));
				final List<AddressUsages> addressUsageData = ocdaddreDao.addrUsageSearchAddressUsages(addressUsages);
				addressUsageData.forEach(data -> {
					data.setCode(data.getAddressUsage());
				});
				address.setAddressUsages(addressUsageData);
			}
			if (!address.getPrimaryFlag().equals("Y")) {
				address.setPrimaryFlag(null);
			}
			if (!address.getMailFlag().equals("Y")) {
				address.setMailFlag(null);
			}
			if (!address.getActiveFlag().equals("Y")) {
				address.setActiveFlag(null);
			}
		});
		return resultLists;
	}

}