package net.syscon.s4.inmate.trust.financialsmaintenance.payees.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.InternetAddresses;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.genericservices.CustomException;
import net.syscon.s4.im.beans.Addresses;
import net.syscon.s4.im.beans.AddressesCommitBean;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.CorporatesCommitBean;
import net.syscon.s4.im.beans.InternetAddressesCommitBean;
import net.syscon.s4.im.beans.PhonesCommitBean;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inmate.trust.financialsmaintenance.payees.OumagencRepository;
import net.syscon.s4.inmate.trust.financialsmaintenance.payees.OumagencService;
import net.syscon.s4.inst.schedules.bean.VCorporateAddresses;
import net.syscon.s4.inst.schedules.bean.VCorporateAddressesCommitBean;
import net.syscon.s4.triggers.AddressesT1Service;
import net.syscon.s4.triggers.AddressesT3Service;
import net.syscon.s4.triggers.AddressesT4Service;
import net.syscon.s4.triggers.AddressesTjnService;
import net.syscon.s4.triggers.AddressesTwfService;
import net.syscon.s4.triggers.CorporatesT1Service;
import net.syscon.s4.triggers.InternetAddressesT1Service;
import net.syscon.s4.triggers.InternetAddressesT2Service;
import net.syscon.s4.triggers.PhonesT1Service;
import net.syscon.s4.triggers.PhonesT2Service;

/**
 * Class OumagencServiceImpl
 */
@Service
public class OumagencServiceImpl extends BaseBusiness implements OumagencService {

	@Autowired
	private OumagencRepository oumagencRepository;
	@Autowired
	private CorporatesT1Service corporatesT1Service;
	@Autowired
	private PhonesT1Service phonesT1Service;
	@Autowired
	private PhonesT2Service phonesT2Service;
	@Autowired
	private InternetAddressesT1Service internetAddressesT1Service;
	@Autowired
	private InternetAddressesT2Service internetAddressesT2Service;
	@Autowired
	private AddressesTwfService addressesTwfService;
	@Autowired
	private AddressesTjnService addressesTjnService;
	@Autowired
	private AddressesT4Service addressesT4Service;
	@Autowired
	private AddressesT3Service addressesT3Service;
	@Autowired
	private AddressesT1Service addressesT1Service;

	private static Logger logger = LogManager.getLogger(OumagencServiceImpl.class.getName());


	/**
	 * Creates new OumagencServiceImpl class Object
	 */
	public OumagencServiceImpl() {
		// OumagencServiceImpl
	}

	/**
	 * Fetch the records from database table corpExecuteQuery
	 * 
	 * @param searchRecord
	 */
	public List<Corporates> corpExecuteQuery(final Corporates searchRecord) {
		return oumagencRepository.corpExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table corpCommit
	 * 
	 * @param commitBean
	 * @return Integer
	 */
	@Transactional(rollbackFor = Exception.class)
	public String corpCommit(final CorporatesCommitBean commitBean) {
		int liReturn = 0;
		BigDecimal corporteId = null;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (Corporates data : commitBean.getInsertList()) {
				data.setCreateUserId(commitBean.getCreateUserId());
				corporteId = oumagencRepository.getCorporateId();
				data.setCorporateId(corporteId);
			}

			liReturn = oumagencRepository.corpInsertCorporates(commitBean.getInsertList());
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (Corporates data : commitBean.getUpdateList()) {
				data.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oumagencRepository.corpUpdateCorporates(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (Corporates data : commitBean.getDeleteList()) {
				data.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oumagencRepository.corpDeleteCorporates(commitBean.getDeleteList());
			if(liReturn == 1) {				
				corporatesT1Service.corporateT1Trigger(corporteId);
			} else {
				return String.valueOf(liReturn);
			}
		}
		if (corporteId == null) {
			return String.valueOf(liReturn);
		} else {
			return corporteId.toString();
		}
	}

	/**
	 * Fetch the records from database table addrExecuteQuery
	 * 
	 * @param searchRecord
	 */
	public List<VCorporateAddresses> addrExecuteQuery(final VCorporateAddresses searchRecord) {
		List<VCorporateAddresses> returnList= oumagencRepository.addrExecuteQuery(searchRecord);
		if(returnList!=null && !returnList.isEmpty()) {
			for(VCorporateAddresses address:returnList) {
				if(address.getProvStateDesc() == null) {
					address.setProvStateDesc(address.getProvStateCode());
				}

				if(address.getCityName() == null) {
					address.setCityName(address.getCityCode());
				}
			}
		}
		return returnList;

	}

	/**
	 * Insert the records from database table addrCommit
	 * 
	 * @param commitBean
	 * @return Integer
	 * 
	 */
	@Transactional(rollbackFor = Exception.class)
	public Integer addrCommit(final VCorporateAddressesCommitBean commitBean) {
		int liReturn = 0;
		return liReturn;
	}

	/**
	 * Fetch the records from database table addPhExecuteQuery
	 * 
	 * @param searchRecord
	 */
	public List<Phones> addPhExecuteQuery(final Phones searchRecord) {
		return oumagencRepository.addPhExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table addPhCommit
	 * 
	 * @param lstADD_PH
	 * @return Integer
	 */
	@Transactional(rollbackFor = Exception.class)
	public Integer addPhCommit(final PhonesCommitBean commitBean) throws Exception{
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (Phones data : commitBean.getInsertList()) {
				data.setCreateUserId(commitBean.getCreateUserId());
				phonesT1Service.phonesT1Trigger(data.getOwnerClass(), data.getOwnerId()!=null?data.getOwnerId().longValue():null, data.getOwnerSeq()!=null?data.getOwnerSeq().longValue():null, data.getOwnerCode());
			}
			liReturn = oumagencRepository.addPhInsertPhones(commitBean.getInsertList());
			commitBean.getInsertList().forEach(data-> phonesT2Service.phonesT2Trigger(oumagencRepository.getphonesoldRecord(data.getPhoneId()), data));
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (Phones data : commitBean.getUpdateList()) {
				data.setModifyUserId(commitBean.getCreateUserId());
				phonesT1Service.phonesT1Trigger(data.getOwnerClass(), data.getOwnerId()!=null?data.getOwnerId().longValue():null, data.getOwnerSeq()!=null?data.getOwnerSeq().longValue():null, data.getOwnerCode());
			}
			liReturn = oumagencRepository.addPhUpdatePhones(commitBean.getUpdateList());
			commitBean.getUpdateList().forEach(data-> phonesT2Service.phonesT2Trigger(oumagencRepository.getphonesoldRecord(data.getPhoneId()), data));
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (Phones data : commitBean.getDeleteList()) {
				data.setModifyUserId(commitBean.getCreateUserId());
				Phones old=oumagencRepository.getphonesoldRecord(data.getPhoneId());
				if (old != null) {
					old.setCreateUserId(commitBean.getCreateUserId());
					phonesT2Service.phonesT2Trigger(old, data);
				}
			}
			liReturn = oumagencRepository.addPhDeletePhones(commitBean.getDeleteList());		
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */

	public List<Phones> corPhoneExecuteQuery(final Phones searchRecord) {
		return oumagencRepository.corPhoneExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table corPhoneCommit
	 * 
	 * @param lstCOR_PHONE
	 * @return Integer
	 * 
	 */
	@Transactional(rollbackFor = Exception.class)
	public Integer corPhoneCommit(final CorporatesCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			liReturn = oumagencRepository.corpInsertCorporates(commitBean.getInsertList());

		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			liReturn = oumagencRepository.corpUpdateCorporates(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (Corporates data : commitBean.getDeleteList()) {
				data.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oumagencRepository.corpDeleteCorporates(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table iAddExecuteQuery
	 * 
	 * @param searchRecord
	 */
	public List<InternetAddresses> iAddExecuteQuery(final InternetAddresses searchRecord) {
		return oumagencRepository.iAddExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table iAddCommit
	 * 
	 * @param lstI_ADD
	 * @return Integer
	 */
	@Transactional(rollbackFor = Exception.class)
	public Integer iAddCommit(final InternetAddressesCommitBean commitBean) throws Exception{
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (InternetAddresses data : commitBean.getInsertList()) {
				data.setCreateUserId(commitBean.getCreateUserId());
				internetAddressesT1Service.internetAddressesT1Trigger(data.getOwnerClass(), data.getOwnerId()!=null?data.getOwnerId().longValue():null, data.getOwnerSeq()!=null?data.getOwnerSeq().longValue():null, data.getOwnerCode());
			}
			liReturn = oumagencRepository.iAddInsertInternetAddresses(commitBean.getInsertList());
			commitBean.getInsertList().forEach(data->internetAddressesT2Service.internetAddressesT2Trigger(data));

		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (InternetAddresses data : commitBean.getUpdateList()) {
				data.setModifyUserId(commitBean.getCreateUserId());
				internetAddressesT1Service.internetAddressesT1Trigger(data.getOwnerClass(), data.getOwnerId()!=null?data.getOwnerId().longValue():null, data.getOwnerSeq()!=null?data.getOwnerSeq().longValue():null, data.getOwnerCode());
			}
			liReturn = oumagencRepository.iAddUpdateInternetAddresses(commitBean.getUpdateList());
			commitBean.getUpdateList().forEach(data->internetAddressesT2Service.internetAddressesT2Trigger(data));

		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (InternetAddresses data : commitBean.getDeleteList()) {
				data.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oumagencRepository.iAddDeleteInternetAddresses(commitBean.getDeleteList());
			commitBean.getDeleteList().forEach(data->internetAddressesT2Service.internetAddressesT2Trigger(data));
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table addressesExecuteQuery
	 * 
	 * @param searchRecord
	 */
	public List<Addresses> addressesExecuteQuery(final Addresses searchRecord) {
		return oumagencRepository.addressesExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table addressesCommit
	 * 
	 * @param commitBean
	 * @return Integer
	 * 
	 */
	@Transactional(rollbackFor = Exception.class)
	public Integer addressesCommit(final AddressesCommitBean commitBean) throws Exception{
		int liReturn = 0;
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(bean -> bean.setCreateUserId(commitBean.getCreateUserId()));
			liReturn = oumagencRepository.addressesUpdateAddresses(commitBean.getUpdateList());
			commitBean.getUpdateList().forEach(data->{
				net.syscon.s4.common.beans.Addresses addrs=new net.syscon.s4.common.beans.Addresses();
				BeanUtils.copyProperties(data,addrs);
				addressesTwfService.addressesTwf(addrs);
				//addressesTjnService.addressesTjn(null, addrs, ApplicationConstants.UPDATE);
				addressesT4Service.AddresesT4Trigger(addrs);
				Addresses old=oumagencRepository.getAddressoldRecord(data.getAddressId());
				net.syscon.s4.common.beans.Addresses oldAddrs=new net.syscon.s4.common.beans.Addresses();
				BeanUtils.copyProperties(old,oldAddrs);
				addressesT3Service.AddresesT3Trigger(oldAddrs,addrs);
				try {
					addressesT1Service.addresesT1Trigger(data.getOwnerClass(),data.getOwnerId()!=null?data.getOwnerId().longValue():null,data.getOwnerSeq(),data.getOwnerCode());
				} catch (CustomException e) {
					throw new RuntimeException();
				}
			});
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bean -> bean.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = oumagencRepository.addressesDeleteAddresses(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group rgCaseloadRecordGroup
	 */
	public List<Caseloads> rgCaseloadRecordGroup() {
		return oumagencRepository.rgCaseloadRecordGroup();

	}

	/**
	 * This method is used to execute a record group rgTypeRecordGroup
	 */
	public List<ReferenceCodes> rgTypeRecordGroup() {
		return oumagencRepository.rgTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group rgIclassRecordGroup
	 */
	public List<ReferenceCodes> rgIclassRecordGroup() {
		return oumagencRepository.rgIclassRecordGroup();

	}

	@Override
	public BigDecimal getCorporateChilds(final BigDecimal corporateId) {
		return oumagencRepository.getCorporateChilds(corporateId);
	}

}