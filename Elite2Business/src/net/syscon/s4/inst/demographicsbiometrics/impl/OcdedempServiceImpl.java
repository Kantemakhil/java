package net.syscon.s4.inst.demographicsbiometrics.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.Addresses;
import net.syscon.s4.common.beans.AddressesCommitBean;
import net.syscon.s4.common.beans.InternetAddresses;
import net.syscon.s4.common.beans.OffenderEducations;
import net.syscon.s4.common.beans.OffenderEducationsCommitBean;
import net.syscon.s4.common.beans.OffenderEmployments;
import net.syscon.s4.common.beans.OffenderEmploymentsCommitBean;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VOffenderEducationAddresses;
import net.syscon.s4.common.beans.VOffenderEmployAddresses;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.demographicsbiometrics.OcdedempRepository;
import net.syscon.s4.inst.demographicsbiometrics.OcdedempService;
import net.syscon.s4.triggers.OffenderEmploymentsT1Service;

/**
 * Class OcdedempServiceImpl
 */
@Service
public class OcdedempServiceImpl extends BaseBusiness implements OcdedempService {
	/**
	 * Logger object used to print the log in the file
	 */

	@Autowired
	private OcdedempRepository OcdedempDao;
	
	@Autowired
	private OffenderEmploymentsT1Service offenderEmploymentsT1Service;

	/**
	 * Creates new OcdedempBusiness class Object
	 */
	public OcdedempServiceImpl() {
	}

	/**
	 * This method is execute a Repository class method when trigger event is
	 * fired
	 *
	 * @param paramBean
	 *
	 * @throws SQLException
	 */
	public List<String> offBkgOnCheckDeleteMasteroffEducationsCur(final OffenderEducations paramBean) {
		return OcdedempDao.offBkgOnCheckDeleteMasteroffEducationsCur(paramBean);
	}

	/**
	 * This method is execute a Repository class method when trigger event is
	 * fired
	 *
	 * @param paramBean
		
	 */
	public List<String> offBkgOnCheckDeleteMasteroffEmploymentsCur(final OffenderEmployments paramBean) {
		return OcdedempDao.offBkgOnCheckDeleteMasteroffEmploymentsCur(paramBean);
	}

	/**
	 * This method is execute a Repository class method when trigger event is
	 * fired
	 *
	 * @param paramBean
	 */
	public List<String> offEducationsOnCheckDeleteMastervOffEduAddrCur(final VOffenderEducationAddresses paramBean) {
		return OcdedempDao.offEducationsOnCheckDeleteMastervOffEduAddrCur(paramBean);
	}

	/**
	 * This method is execute a Repository class method when trigger event is
	 * fired
	 *
	 * @param params
	 */
	public Integer offEducationsPreInsert(final OffenderEducations paramBean) {
		return OcdedempDao.offEducationsPreInsert(paramBean);
	}

	/**
	 * This method is execute a Repository class method when trigger event is
	 * fired
	 *
	 * @param paramBean
	 */
	public Integer offEducationsPreDeleteAddress(final Addresses paramBean) {
		return OcdedempDao.offEducationsPreDeleteAddress(paramBean);
	}

	/**
	 * This method is execute a Repository class method when trigger event is
	 * fired
	 *
	 * @param paramBean
	 */
	public Integer offEducationsPreDeletePhones(final Phones paramBean) {
		return OcdedempDao.offEducationsPreDeletePhones(paramBean);
	}

	/**
	 * This method is execute a Repository class method when trigger event is
	 * fired
	 *
	 * @param paramBean
	 */
	public Integer offEducationsPreDeleteInternetAddress(final InternetAddresses paramBean) {
		return OcdedempDao.offEducationsPreDeleteInternetAddress(paramBean);
	}

	/**
	 * This method is execute a Repository class method when trigger event is
	 * fired
	 *
	 * @param paramBean
	 */
	public Integer offEmploymentsPreInsert(final OffenderEmployments paramBean) {
		return OcdedempDao.offEmploymentsPreInsert(paramBean);
	}

	/**
	 * This method is execute a Repository class method when trigger event is
	 * fired
	 *
	 * @param paramBean
	 */
	public List<String> offEmploymentsOnCheckDeleteMastervOffEmpAddrCur(final VOffenderEmployAddresses paramBean) {
		return OcdedempDao.offEmploymentsOnCheckDeleteMastervOffEmpAddrCur(paramBean);
	}

	/**
	 * This method is execute a Repository class method when trigger event is
	 * fired
	 *
	 * @param paramBean
	 */
	public Integer offEmploymentsPreDeleteAddress(final Addresses paramBean) {
		return OcdedempDao.offEmploymentsPreDeleteAddress(paramBean);
	}

	/**
	 * This method is execute a Repository class method when trigger event is
	 * fired
	 *
	 * @param paramBean
	 */
	public Integer offEmploymentsPreDeletePhones(final Phones paramBean) {
		return OcdedempDao.offEmploymentsPreDeletePhones(paramBean);
	}

	/**
	 * This method is execute a Repository class method when trigger event is
	 * fired
	 *
	 * @param paramBean
	 */
	public Integer offEmploymentsPreDeleteInternetAddress(final InternetAddresses paramBean) {
		return OcdedempDao.offEmploymentsPreDeleteInternetAddress(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchBean
	 */
	public List<OffenderEducations> offEducationsSearchOffenderEducations(final OffenderEducations searchBean) {
		return OcdedempDao.offEducationsSearchOffenderEducations(searchBean);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOffenderEducations
	 */
	@Transactional
	public Integer offEducationsInsertOffenderEducations(final List<OffenderEducations> lstOffenderEducations) {
		Integer seq = 0;
		Boolean checkFlag = true;
		for (OffenderEducations obj : lstOffenderEducations) {
			if (checkFlag) {
				checkFlag = false;
			 seq = OcdedempDao.offEducationsgetMaxBookIdEduSeq(obj.getOffenderBookId());
			}
			obj.setEducationSeq(seq);
			seq = seq + 1;
		}
		return OcdedempDao.offEducationsInsertOffenderEducations(lstOffenderEducations);
	}

	/**
	 * Update the records from database table
	 *
	 * @param lstOffenderEducations
	 */
	@Transactional
	public Integer offEducationsUpdateOffenderEducations(final List<OffenderEducations> lstOffenderEducations) {
		return OcdedempDao.offEducationsUpdateOffenderEducations(lstOffenderEducations);
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOffenderEducations
	 */
	@Transactional
	public Integer offEducationsDeleteOffenderEducations(final List<OffenderEducations> lstOffenderEducations) {
		return OcdedempDao.offEducationsDeleteOffenderEducations(lstOffenderEducations);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchBean
	 */
	public List<VOffenderEducationAddresses> vOffEduAddrSearchVOffenderEducationAddresses(
			final VOffenderEducationAddresses searchBean) {
		List<VOffenderEducationAddresses> returnList= OcdedempDao.vOffEduAddrSearchVOffenderEducationAddresses(searchBean);
		if(returnList!=null && !returnList.isEmpty()) {
		for(VOffenderEducationAddresses address:returnList) {
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
	 * Fetch the records from database table
	 *
	 * @param searchBean	
	 */
	public List<OffenderEmployments> offEmploymentsSearchOffenderEmployments(final OffenderEmployments searchBean) {
		return OcdedempDao.offEmploymentsSearchOffenderEmployments(searchBean);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOffenderEmployments	 
	 */
	@Transactional
	public Integer offEmploymentsInsertOffenderEmployments(final List<OffenderEmployments> lstOffenderEmployments) {
		Integer seq=0;
		Boolean checkFlag=true;
		for(OffenderEmployments obj:lstOffenderEmployments){
			if(checkFlag){
				checkFlag =false;
			 seq = OcdedempDao.offEmploymentsgetMaxBookIdEmpSeq(obj.getOffenderBookId());
			}			   
			obj.setEmploySeq(seq);
			seq = seq+1;
			  
			}
		return OcdedempDao.offEmploymentsInsertOffenderEmployments(lstOffenderEmployments);
	}

	/**
	 * Update the records from database table
	 *
	 * @param lstOffenderEmployments
	 */
	@Transactional
	public Integer offEmploymentsUpdateOffenderEmployments(final List<OffenderEmployments> lstOffenderEmployments) {
		Integer liReturn= 0;
		liReturn = OcdedempDao.offEmploymentsUpdateOffenderEmployments(lstOffenderEmployments);
		for (OffenderEmployments offenderEmployments : lstOffenderEmployments) {
			if (offenderEmployments.getOffenderBookId() != null) {
				offenderEmploymentsT1Service.updateReleasePlans(offenderEmployments.getOffenderBookId());
			}
			
		}
		return liReturn;
		
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOffenderEmployments
	 */
	@Transactional
	public Integer offEmploymentsDeleteOffenderEmployments(final List<OffenderEmployments> lstOffenderEmployments) {
		return OcdedempDao.offEmploymentsDeleteOffenderEmployments(lstOffenderEmployments);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchBean
	 */
	public List<VOffenderEmployAddresses> vOffEmpAddrSearchVOffenderEmployAddresses(
			final VOffenderEmployAddresses searchBean) {
		List<VOffenderEmployAddresses> returnList=OcdedempDao.vOffEmpAddrSearchVOffenderEmployAddresses(searchBean);
		if(returnList!=null && !returnList.isEmpty()) {
		for(VOffenderEmployAddresses address:returnList) {
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
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> eduSchedRgRgroup() {
		return OcdedempDao.eduSchedRgRgroup();

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> payPeriodRgRgroup() {
		return OcdedempDao.payPeriodRgRgroup();

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> occupationRgRgroup() {
		return OcdedempDao.occupationRgRgroup();

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> scheduleTypeRgRgroup() {
		return OcdedempDao.scheduleTypeRgRgroup();

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> employStsRgRgroup() {
		return OcdedempDao.employStsRgRgroup();

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> studyAreaRgRgroup() {
		return OcdedempDao.studyAreaRgRgroup();

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> eduLevelRgRgroup() {
		return OcdedempDao.eduLevelRgRgroup();

	}

	/**
	 * This method is execute a Repository class method when trigger event is
	 * fired
	 */
	public ReferenceCodes addressWhenCreateRecordgetCountryDesc() {
		return OcdedempDao.addressWhenCreateRecordgetCountryDesc();
	}

	/**
	 * This method is execute a Repository class method when trigger event is
	 * fired
	 *
	 * @param paramBean
	 */
	public List<Phones> addressKeyDelrecphoneEx(final Phones paramBean) {
		return OcdedempDao.addressKeyDelrecphoneEx(paramBean);
	}

	/**
	 * This method is execute a Repository class method when trigger event is
	 * fired
	 */
	public Object preInsertaddressIdCur() {
		return OcdedempDao.preInsertaddressIdCur();
	}

	/**
	 * This method is execute a Repository class method when trigger event is
	 * fired
	 */
	public Object validateCityInfogetCityDescription() {
		return OcdedempDao.validateCityInfogetCityDescription();
	}

	/**
	 * This method is execute a Repository class method when trigger event is
	 * fired
	 */
	public List<ReferenceCodes> validateCityInfogetCityCode() {
		return OcdedempDao.validateCityInfogetCityCode();
	}

	/**
	 * This method is execute a Repository class method when trigger event is
	 * fired
	 */
	public SystemProfiles citySystemProfilecityProfileCur() {
		return OcdedempDao.citySystemProfilecityProfileCur();
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param paramBean
	 */
	public List<Addresses> addressSearchAddresses(final Addresses paramBean) {
		return OcdedempDao.addressSearchAddresses(paramBean);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstAddresses
	 */
	@Transactional
	public Integer addressInsertAddresses(final List<Addresses> lstAddresses) {
		return OcdedempDao.addressInsertAddresses(lstAddresses);
	}

	/**
	 * Update the records from database table
	 *
	 * @param lstAddresses
	 */
	@Transactional
	public Integer addressUpdateAddresses(final List<Addresses> lstAddresses) {
		return OcdedempDao.addressUpdateAddresses(lstAddresses);
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstAddresses
	 */
	@Transactional
	public Integer addressDeleteAddresses(final List<Addresses> lstAddresses) {
		return OcdedempDao.addressDeleteAddresses(lstAddresses);
	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> rgStreetDirRgroup() {
		return OcdedempDao.rgStreetDirRgroup();

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> rgCityRgroup() {
		return OcdedempDao.rgCityRgroup();

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> rgProvStateCodeRgroup() {
		return OcdedempDao.rgProvStateCodeRgroup();

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> rgCountryRgroup() {
		return OcdedempDao.rgCountryRgroup();

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> rgTypeRgroup() {
		return OcdedempDao.rgTypeRgroup();

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> rgSpecialNeedsRgroup() {
		return OcdedempDao.rgSpecialNeedsRgroup();

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> rgCountyRgroup() {
		return OcdedempDao.rgCountyRgroup();
	}


	@Transactional
	public Integer offEducationsCommit(final OffenderEducationsCommitBean commitBean) {
		int liReturn = 0;

		if ((commitBean.getInsertList() != null) && (commitBean.getInsertList().size() > 0)) {
			for (OffenderEducations bean : commitBean.getInsertList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = offEducationsInsertOffenderEducations(commitBean.getInsertList());
		}
		if ((commitBean.getUpdateList() != null) && (commitBean.getUpdateList().size() > 0)) {
			for (OffenderEducations bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = offEducationsUpdateOffenderEducations(commitBean.getUpdateList());
		}
		if ((commitBean.getDeleteList() != null) && (commitBean.getDeleteList().size() > 0)) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = offEducationsDeleteOffenderEducations(commitBean.getDeleteList());
		}
		return liReturn;

	}

	@Transactional
	public Integer offEmploymentsCommit(final OffenderEmploymentsCommitBean commitBean) {
		int liReturn = 0;	

		if ((commitBean.getInsertList() != null) && (commitBean.getInsertList().size() > 0)) {
			for (OffenderEmployments bean : commitBean.getInsertList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = offEmploymentsInsertOffenderEmployments(commitBean.getInsertList());
		}
		if ((commitBean.getUpdateList() != null) && (commitBean.getUpdateList().size() > 0)) {
			for (OffenderEmployments bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = offEmploymentsUpdateOffenderEmployments(commitBean.getUpdateList());
		}
		if ((commitBean.getDeleteList() != null) && (commitBean.getDeleteList().size() > 0)) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = offEmploymentsDeleteOffenderEmployments(commitBean.getDeleteList());
		}
		return liReturn;
	}

	@Override
	public Integer addressCommit(final AddressesCommitBean commitBean) {
		int liReturn = 0;

		if ((commitBean.getInsertList() != null) && (commitBean.getInsertList().size() > 0)) {
			commitBean.getInsertList().forEach(bean->bean.setCreateUserId(commitBean.getCreateUserId()));
			liReturn = addressInsertAddresses(commitBean.getInsertList());
		}
		if ((commitBean.getUpdateList() != null) && (commitBean.getUpdateList().size() > 0)) {
			commitBean.getUpdateList().forEach(bean->bean.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = addressUpdateAddresses(commitBean.getUpdateList());
		}
		if ((commitBean.getDeleteList() != null) && (commitBean.getDeleteList().size() > 0)) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = addressDeleteAddresses(commitBean.getDeleteList());
		}
		return liReturn;
	}
	
}