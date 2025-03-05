package net.syscon.s4.inst.demographicsbiometrics.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.booking.beans.OffenderContactPersons;
import net.syscon.s4.inst.booking.beans.OffenderContactPersonsCommitBean;
import net.syscon.s4.inst.booking.beans.PersonEmployments;
import net.syscon.s4.inst.booking.beans.PersonEmploymentsCommitBean;
import net.syscon.s4.inst.booking.beans.PersonIdentifiers;
import net.syscon.s4.inst.booking.beans.PersonIdentifiersCommitBean;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.inst.booking.beans.PersonsCommitBean;
import net.syscon.s4.inst.booking.beans.VPersonAddress;
import net.syscon.s4.inst.demographicsbiometrics.OcdpersoRepository;
import net.syscon.s4.inst.demographicsbiometrics.OcdpersoService;
import net.syscon.s4.pkgs.ocdperso.OcdpersoPkgService;
import net.syscon.s4.triggers.OffenderContactPersonsT1Service;

@Service
public class OcdpersoServiceImpl extends BaseBusiness implements OcdpersoService {

	@Autowired
	private OcdpersoRepository ocdpersoRepository;
	@Autowired
	private OcdpersoPkgService ocdpersoservice;
	@Autowired
	private OffenderContactPersonsT1Service offenderContactPersonsT1Service;
	/**
	 * Logger object used to print the log in the file
	 */

	/**
	 * Creates new OcdpersoServiceImpl class Object
	 */
	public OcdpersoServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<OffenderContactPersons> offBkgOnCheckDeleteMaster(final OffenderContactPersons paramBean) {
		List<OffenderContactPersons> offenderContactPersonsList = new ArrayList<>();
		return offenderContactPersonsList;

	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<OffenderContactPersons> ChangeActiveFlag(final OffenderContactPersons paramBean) {
		List<OffenderContactPersons> offenderContactPersonsList = new ArrayList<>();
		return offenderContactPersonsList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<OffenderContactPersons> offCntPerExecuteQuery(final OffenderContactPersons searchRecord) {
		List<OffenderContactPersons> returnList = new ArrayList<>();
		returnList = ocdpersoRepository.offCntPerExecuteQuery(searchRecord);
		for (final OffenderContactPersons returnObj : returnList) {
			OffenderContactPersons object = ocdpersoservice.getPersonNames(returnObj.getPersonId().longValue());
			returnObj.setLastName(object.getLastName());
			returnObj.setFirstName(object.getFirstName());
			returnObj.setMiddleName(object.getMiddleName());
		}
		return returnList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_CNT_PER
	 *
	 * 
	 */
	@Transactional
	public Integer offCntPerCommit(final OffenderContactPersonsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final OffenderContactPersons obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				offenderContactPersonsT1Service.offenderContactPersonsT1(obj);
			}
			liReturn = ocdpersoRepository.offCntPerInsertOffenderContactPersons(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (OffenderContactPersons obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
				offenderContactPersonsT1Service.offenderContactPersonsT1(obj);
			}
			liReturn = ocdpersoRepository.offCntPerUpdateOffenderContactPersons(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = ocdpersoRepository.offCntPerDeleteOffenderContactPersons(commitBean.getDeleteList());
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
	public List<VPersonAddress> perAddrExecuteQuery(final VPersonAddress searchRecord) {
		List<VPersonAddress> returnList= ocdpersoRepository.perAddrExecuteQuery(searchRecord);
		if(returnList!=null && !returnList.isEmpty()) {
		for(VPersonAddress address:returnList) {
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
	 * @param searchRecord
	 *
	 * 
	 */
	public List<PersonIdentifiers> perIdentExecuteQuery(final PersonIdentifiers searchRecord) {
		return ocdpersoRepository.perIdentExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstPER_IDENT
	 *
	 * 
	 */
	@Transactional
	public Integer perIdentCommit(final PersonIdentifiersCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (PersonIdentifiers obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = ocdpersoRepository.perIdentInsertPersonIdentifiers(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (PersonIdentifiers obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocdpersoRepository.perIdentUpdatePersonIdentifiers(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = ocdpersoRepository.perIdentDeletePersonIdentifiers(commitBean.getDeleteList());
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
	public List<Persons> perInfoExecuteQuery(final Persons searchRecord) {
		return ocdpersoRepository.perInfoExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstPER_INFO
	 *
	 * 
	 */
	@Transactional
	public Integer perInfoCommit(final PersonsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (Persons obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocdpersoRepository.perInfoUpdatePersons(commitBean.getUpdateList());
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
	public List<PersonEmployments> perEmpExecuteQuery(final PersonEmployments searchRecord) {
		return ocdpersoRepository.perEmpExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstPER_EMP
	 *
	 * 
	 */
	@Transactional
	public Integer perEmpCommit(final PersonEmploymentsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (PersonEmployments obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = ocdpersoRepository.perEmpInsertPersonEmployments(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (PersonEmployments obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocdpersoRepository.perEmpUpdatePersonEmployments(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = ocdpersoRepository.perEmpDeletePersonEmployments(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<ReferenceCodes> rgContactTypeRecordGroup() {
		return ocdpersoRepository.rgContactTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<ReferenceCodes> rgRelTypeRecordGroup(final String contactType) {
		List<ReferenceCodes> returnLlist = ocdpersoRepository.rgRelTypeRecordGroup(contactType);
		returnLlist.forEach(element -> {
			if ("N".equals(element.getActiveFlag())) {
				element.setCanDisplay(false);
			}
		});
		return filerLovOnActiveFlag(returnLlist);
	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<ReferenceCodes> rgLanguageCodeRecordGroup() {
		 List<ReferenceCodes> refList = ocdpersoRepository.rgLanguageCodeRecordGroup();
		 return filerLovOnActiveFlag(refList);
	}

	private List<ReferenceCodes> filerLovOnActiveFlag(List<ReferenceCodes> refList) {
		if(Optional.ofNullable(refList).isPresent()) {
				refList.forEach(refcode->{
					if (ApplicationConstants.YFLAG.equals(refcode.getActiveFlag())) {
						refcode.setCanDisplay(true);
					} else {
						refcode.setCanDisplay(false);
					}
				});
			}
		return refList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<ReferenceCodes> rgMaritalStatusRecordGroup() {
		return ocdpersoRepository.rgMaritalStatusRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<ReferenceCodes> rgSexCodeRecordGroup() {
		return ocdpersoRepository.rgSexCodeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<ReferenceCodes> rgSearchTypeRecordGroup() {
		return ocdpersoRepository.rgSearchTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<ReferenceCodes> rgIdentifierTypeRecordGroup() {
		return ocdpersoRepository.rgIdentifierTypeRecordGroup();

	}

	public Integer copyOffAddress(final BigDecimal rootOffId, final Long personId, final String userName) {
		Integer addressId = Integer.valueOf(ocdpersoservice.copyOffAddr(rootOffId, personId, userName).toString());
		if (addressId > 0) {
			addressId = 1;
		} else {
			addressId = 0;
		}
		return addressId;
	}

	public Integer checkChildRecords(final Long offenderBookId, final Integer personId) {
		Integer result = 0;
		result = ocdpersoRepository.checkChildRecordsCurOne(offenderBookId, personId);
		if (result > 0) {
			return result;
		} else {
			result = ocdpersoRepository.checkChildRecordsCurTwo(offenderBookId, personId);
		}

		return result;
	}

	public Integer checkFutureVisits(final Long offenderBookId, final Integer personId) {
		Integer result = 0;
		result = ocdpersoRepository.checkFutureVisits(offenderBookId, personId);
		if (result > 0) {
			return result;
		} else {
			result = 0;
		}

		return result;
	}

	public void cancelFutureVisit(final Long offenderBookId, final Integer personId, final String userName) {
		ocdpersoservice.cancelFutureVisits(offenderBookId, Long.valueOf(personId.toString()), userName);
	}


}