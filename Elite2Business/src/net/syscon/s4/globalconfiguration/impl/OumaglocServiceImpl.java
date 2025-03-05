package net.syscon.s4.globalconfiguration.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.genericservices.CustomException;
import net.syscon.s4.globalconfiguration.OumaglocRepository;
import net.syscon.s4.globalconfiguration.OumaglocService;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.AgencyLocationsCommitBean;
import net.syscon.s4.im.beans.AgyLocEstablishments;
import net.syscon.s4.im.beans.AgyLocEstablishmentsCommitBean;
import net.syscon.s4.im.beans.PhonesCommitBean;
import net.syscon.s4.im.beans.VAgencyAddresses;
import net.syscon.s4.im.beans.VAgencyAddressesCommitBean;
import net.syscon.s4.inst.visitsmanagement.beans.IEPLevelCommitBean;
import net.syscon.s4.inst.visitsmanagement.beans.IepLevelBean;
import net.syscon.s4.triggers.AgencyLocationsT1Service;
import net.syscon.s4.triggers.AgyLocEstablishmentsT1Service;
import net.syscon.s4.triggers.PhonesT1Service;
import net.syscon.s4.triggers.PhonesT2Service;

/**
 * Class OumaglocServiceImpl
 */
@Service
public class OumaglocServiceImpl extends BaseBusiness implements OumaglocService {

	@Autowired
	private OumaglocRepository oumaglocRepo;
	
	@Autowired
	private AgencyLocationsT1Service agencyLocationsT1Service;
	
	@Autowired
	private PhonesT1Service phonesT1Service;
	
	@Autowired
	private PhonesT2Service phonesT2Service;
	
	@Autowired
	private AgyLocEstablishmentsT1Service agyLocEstablishmentsT1Service;
	
	@Autowired
	net.syscon.s4.triggers.AgyLocEstablishmentsT1Repository agyLocEstablishmentsT1Repository;
	/**
	 * Logger object used to print the log in the file
	 */

	private static Logger logger = LogManager.getLogger(OumaglocServiceImpl.class.getName());

	/**
	 * Creates new OumaglocServiceImpl class Object
	 */
	public OumaglocServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public VAgencyAddresses agyLocOnCheckDeleteMaster(final VAgencyAddresses paramBean) {
		return paramBean;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public Phones vAgyAddrOnCheckDeleteMaster(final Phones paramBean) {
		return paramBean;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public Object phonesPreInsert() {
		return null;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<AgencyLocations> agyLocExecuteQuery(final AgencyLocations searchRecord) {
		return oumaglocRepo.agyLocExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstAGY_LOC
	 *
	 * 
	 */
	@Transactional
	public Integer agyLocCommit(final AgencyLocationsCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
				for (AgencyLocations obj : commitBean.getInsertList()) {
					obj.setCreateUserId(commitBean.getCreateUserId());
					List<AgencyLocations> list=new ArrayList<AgencyLocations>();
					list.add(obj);
					liReturn = oumaglocRepo.agyLocInsertAgencyLocations(list);
					try {
						agencyLocationsT1Service.agencyLocationsT1Trigger(null, obj, obj.getCreateUserId());
					}catch (Exception e) {
						logger.error("",e);
					}		
				}				
			}
			if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
				for (AgencyLocations obj : commitBean.getUpdateList()) {
					obj.setModifyUserId(commitBean.getCreateUserId());
					AgencyLocations old=oumaglocRepo.getOldAgencyLocations(obj.getAgyLocId());
					List<AgencyLocations> list=new ArrayList<AgencyLocations>();
					list.add(obj);
					liReturn = oumaglocRepo.agyLocUpdateAgencyLocations(list);
					try {
						obj.setCreateUserId(commitBean.getCreateUserId());
						agencyLocationsT1Service.agencyLocationsT1Trigger(old, obj, obj.getModifyUserId());
					}catch (Exception e) {
						logger.error(e);
					}
				}
				
			}
		} catch (Exception e) {
			logger.error("In method agyLocCommit", e);
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
	public List<VAgencyAddresses> vAgyAddrExecuteQuery(final VAgencyAddresses searchRecord) {
		List<VAgencyAddresses> returnList= oumaglocRepo.vAgyAddrExecuteQuery(searchRecord);
		if(returnList!=null && !returnList.isEmpty()) {
		for(VAgencyAddresses address:returnList) {
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
	 * Insert the records from database table
	 *
	 * @param lstV_AGY_ADDR
	 *
	 * 
	 */
	@Transactional
	public Integer vAgyAddrCommit(final VAgencyAddressesCommitBean commitBean) {
		return 0;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<Phones> phonesExecuteQuery(final Phones searchRecord) {
		return oumaglocRepo.phonesExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstPHONES
	 *
	 * 
	 */
	@Transactional
	public Integer phonesCommit(final PhonesCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final Phones phObj : commitBean.getInsertList()) {
				phObj.setCreateUserId(commitBean.getCreateUserId());
				final Object phoneSequence = oumaglocRepo.phonesPreInsert();
				final Long phSeqValue = Long.parseLong(phoneSequence.toString());
				phObj.setPhoneId(phSeqValue);
				List<Phones> list=new ArrayList<Phones>();
				list.add(phObj);
				try {
					phonesT1Service.phonesT1Trigger(phObj.getOwnerClass(),phObj.getOwnerId() != null ?phObj.getOwnerId().longValue():null, phObj.getOwnerSeq() != null ?phObj.getOwnerSeq().longValue():null, phObj.getOwnerCode());
				} catch (CustomException e1) {
					e1.printStackTrace();
				}
				liReturn = oumaglocRepo.phonesInsertPhones(list);
				
				try {
					phonesT2Service.phonesT2Trigger(null, phObj);
				}catch (Exception e) {
					logger.error(e);
				}
			}
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (Phones obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
				List<Phones> list=new ArrayList<Phones>();
				list.add(obj);
				try {
					phonesT1Service.phonesT1Trigger(obj.getOwnerClass(),obj.getOwnerId() != null ?obj.getOwnerId().longValue():null, obj.getOwnerSeq() != null ?obj.getOwnerSeq().longValue():null, obj.getOwnerCode());
				} catch (CustomException e1) {
					e1.printStackTrace();
				}
				Phones old=null;
				List<Phones> listPhones=oumaglocRepo.phonesExecuteQuery(obj);
				for(Phones data:listPhones){
					if(obj.getPhoneId() ==data.getPhoneId() ) {
						old=data;
					}
				}
				liReturn = oumaglocRepo.phonesUpdatePhones(list);
				
				try {
					obj.setCreateUserId(commitBean.getCreateUserId());
					phonesT2Service.phonesT2Trigger(old, obj);
				}catch (Exception e) {
					logger.error(e);
				}
			}
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (Phones obj : commitBean.getDeleteList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
				List<Phones> list=new ArrayList<Phones>();
				list.add(obj);
				
				Phones old=null;
				List<Phones> listPhones=oumaglocRepo.phonesExecuteQuery(obj);
				for(Phones data:listPhones){
					if(obj.getPhoneId() ==data.getPhoneId() ) {
						old=data;
					}
				}
				liReturn = oumaglocRepo.phonesDeletePhones(list);
				
				try {				
					phonesT2Service.phonesT2Trigger(old, obj);
				}catch (Exception e) {
					logger.error(e);
				}
			}
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
	public List<AgyLocEstablishments> agyLocEstExecuteQuery(final AgyLocEstablishments searchRecord) {
		return oumaglocRepo.agyLocEstExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstAGY_LOC_EST
	 *
	 * 
	 */
	@Transactional
	public Integer agyLocEstCommit(final AgyLocEstablishmentsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (AgyLocEstablishments obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oumaglocRepo.agyLocEstInsertAgyLocEstablishments(commitBean.getInsertList());
			for (AgyLocEstablishments obj : commitBean.getInsertList()) {
			agyLocEstablishmentsT1Service.agyLocEstablishmentsT1(obj,"INSERT",null);
		}
			}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (AgyLocEstablishments obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oumaglocRepo.agyLocEstUpdateAgyLocEstablishments(commitBean.getUpdateList());
			for (AgyLocEstablishments obj : commitBean.getUpdateList()) {
				AgyLocEstablishments oldAgyLocEstablishments=new AgyLocEstablishments();
				 oldAgyLocEstablishments = agyLocEstablishmentsT1Repository.getAgyLocEstablishments(obj.getAgyLocId(),obj.getEstablishmentType());
				 obj.setCreateUserId(commitBean.getCreateUserId());
				agyLocEstablishmentsT1Service.agyLocEstablishmentsT1(obj,"UPDATE",oldAgyLocEstablishments);
			}
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			AgyLocEstablishments oldAgyLocEstablishments=new AgyLocEstablishments();
			for (AgyLocEstablishments obj : commitBean.getDeleteList()) {
			 oldAgyLocEstablishments = agyLocEstablishmentsT1Repository.getAgyLocEstablishments(obj.getAgyLocId(),obj.getEstablishmentType());
			 obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oumaglocRepo.agyLocEstDeleteAgyLocEstablishments(commitBean.getDeleteList());
			for (AgyLocEstablishments obj : commitBean.getDeleteList()) {
				agyLocEstablishmentsT1Service.agyLocEstablishmentsT1(obj,"DELETE",oldAgyLocEstablishments);
			}
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<ReferenceCodes> rgPhoneTypeRecordGroup() {
		return oumaglocRepo.rgPhoneTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<ReferenceCodes> rgAgencyLocationTypeRecordGroup() {
		return oumaglocRepo.rgAgencyLocationTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<ReferenceCodes> rgJurisdictionRecordGroup() {
		return oumaglocRepo.rgJurisdictionRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<ReferenceCodes> rgDisabilityAccessCodeRecordGroup() {
		return oumaglocRepo.rgDisabilityAccessCodeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<ReferenceCodes> rgHousingLevelCodesRecordGroup() {
		return oumaglocRepo.rgHousingLevelCodesRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<ReferenceCodes> rgEstablishmentTypeRecordGroup() {
		return oumaglocRepo.rgEstablishmentTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<ReferenceCodes> rgYnFlagRecordGroup() {
		return oumaglocRepo.rgYnFlagRecordGroup();
	}
	
	
	@Override
	public Integer  iepLevelCommit(final IEPLevelCommitBean commitBean) {
		Integer liReturn=null;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (IepLevelBean agyIntLoc : commitBean.getInsertList()) {
				agyIntLoc.setCreateUserId(commitBean.getCreateUserId());
				liReturn = oumaglocRepo.iepLevelCommit(agyIntLoc);
			}
		}

		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (IepLevelBean agyIntLoc : commitBean.getUpdateList()) {
				agyIntLoc.setModifyUserId(commitBean.getCreateUserId());
				liReturn = oumaglocRepo.iepLevelCommitUpdate(agyIntLoc.getAgyLocId(),agyIntLoc.getIepLevelCode(), agyIntLoc.getModifyUserId());
			}

		}
		
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (IepLevelBean agyIntLoc : commitBean.getDeleteList()) {
				liReturn = oumaglocRepo.iepLevelCommitDelete(agyIntLoc.getAgyLocId(), commitBean.getCreateUserId());
			}

		}
		return liReturn;
	}
	
	@Override
	public IepLevelBean getIepdetails(String agyLocId) {
		return oumaglocRepo.getIepdetails(agyLocId);
	}
	

}