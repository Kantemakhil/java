package net.syscon.s4.inst.securitythreatgroups.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.FormAccessibleForms;
import net.syscon.s4.common.beans.FormAccessibleFormsCommitBean;
import net.syscon.s4.common.beans.OffenderAssessments;
import net.syscon.s4.common.beans.OffenderAssessmentsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SecurityThreatGroups;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OffenderStgAffiliations;
import net.syscon.s4.im.beans.OffenderStgAffiliationsCommitBean;
import net.syscon.s4.im.beans.OffenderStgDetails;
import net.syscon.s4.im.beans.OffenderStgDetailsCommitBean;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.classification.beans.Assessments;
import net.syscon.s4.inst.securitythreatgroups.OidmbrdtRepository;
import net.syscon.s4.inst.securitythreatgroups.OidmbrdtService;
import net.syscon.s4.pkgs.oms_form_access.OmsFormAccessService;

/**
 * Class OidmbrdtServiceImpl
 */
@Service
public class OidmbrdtServiceImpl extends BaseBusiness implements OidmbrdtService {

	@Autowired
	private OidmbrdtRepository oidmbrdtRepository;

	@Autowired
	private OmsFormAccessService omsFormAccessService;

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<OffenderStgAffiliations> offBkgOnCheckDeleteMaster(final OffenderStgAffiliations paramBean) {
		return null;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public Assessments offenderAssessmentsPostQuery(final OffenderAssessments paramBean) {
		return oidmbrdtRepository.offenderAssessmentsPostQueryGetDesc(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public Integer offenderStgAffiliationsPreInsert(final OffenderStgAffiliations paramBean) {
		return oidmbrdtRepository.offenderStgAffiliationsPreInsert(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public SecurityThreatGroups offenderStgAffiliationsPostQuery(final OffenderStgAffiliations paramBean) {
		return oidmbrdtRepository.offenderStgAffiliationsPostQuery(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<OffenderStgDetails> offenderStgAffiliationsOnCheckDeleteMaster(final OffenderStgDetails paramBean) {
		return null;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public OffenderStgDetails offenderStgDetailsPostQuery(final OffenderStgDetails paramBean) {
		return oidmbrdtRepository.offenderStgDetailsPostQuery(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public OmsModules formAccessibleFormsPostQuery(final FormAccessibleForms paramBean) {
		return oidmbrdtRepository.formAccessibleFormsPostQuery(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public SystemProfiles populateRg() {
		return oidmbrdtRepository.populateRg();
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public Integer checkGroupInsert(final OffenderStgAffiliations paramBean) {
		return oidmbrdtRepository.checkGroupInsert(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<Integer> checkGroupFlag(final OffenderStgAffiliations paramBean) {
		return oidmbrdtRepository.checkGroupFlag(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OffenderAssessments> offenderAssessmentsExecuteQuery(final OffenderAssessments searchRecord) {
		List<OffenderAssessments> returnList = new ArrayList<>();
		returnList = oidmbrdtRepository.offenderAssessmentsExecuteQuery(searchRecord);
		for (final OffenderAssessments obj : returnList) {
			if (obj.getAssessmentTypeId() != null) {
				final Assessments returnObj = offenderAssessmentsPostQuery(obj);
				obj.setAssessmentTypeCode(returnObj.getDescription());
			}
			if (obj.getAssessorStaffId() != null) {
				final StaffMembers returnobject = oidmbrdtRepository.offenderAssessmentsPostQuery(obj);
				obj.setAssessorName(returnobject.getDescription());
			}
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFFENDER_ASSESSMENTS
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer offenderAssessmentsCommit(final OffenderAssessmentsCommitBean commitBean) {
		return null;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OffenderStgAffiliations> offenderStgAffiliationsExecuteQuery(
			final OffenderStgAffiliations searchRecord) {
		List<OffenderStgAffiliations> returnList = new ArrayList<>();
		returnList = oidmbrdtRepository.offenderStgAffiliationsExecuteQuery(searchRecord);
		for (final OffenderStgAffiliations obj : returnList) {
			final SecurityThreatGroups returnObj = offenderStgAffiliationsPostQuery(obj);
			obj.setDescription(returnObj.getDescription());
		}

		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFFENDER_STG_AFFILIATIONS
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer offenderStgAffiliationsCommit(final OffenderStgAffiliationsCommitBean commitBean) {
		Integer liReturn = 0;
		List<OffenderStgAffiliations> insertList = new ArrayList<>();
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final OffenderStgAffiliations obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				final Integer returnVal = checkGroupInsert(obj);
				if (returnVal == 1) {
					return 2;
				}
				insertList = new ArrayList<>();
				final Integer SeqValue = offenderStgAffiliationsPreInsert(obj);
				obj.setStgSeq(BigDecimal.valueOf(SeqValue));
				if ("true".equals(obj.getActiveFlag())) {
					obj.setActiveFlag("Y");
				} else {
					obj.setActiveFlag("N");
				}
				insertList.add(obj);
				liReturn = oidmbrdtRepository.offenderStgAffiliationsInsert(insertList);
			}
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (final OffenderStgAffiliations obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
				if ("true".equals(obj.getActiveFlag())) {
					obj.setActiveFlag("Y");
				} else {
					obj.setActiveFlag("N");
				}
				if(obj.getNotifiedDate() != null) {
					obj.setNotifiedBy(obj.getNotifiedByTemp());
				}
			}
			liReturn = oidmbrdtRepository
					.offenderStgAffiliationsUpdateOffenderStgAffiliations(commitBean.getUpdateList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OffenderStgDetails> offenderStgDetailsExecuteQuery(final OffenderStgDetails searchRecord) {
		final List<OffenderStgDetails> returnList = new ArrayList<>();
		if (searchRecord.getOffenderBookId() != null && searchRecord.getStgSeq() != null) {
			searchRecord.setActionCode("VALIDATE");
			final OffenderStgDetails valDateObj = offenderStgDetailsPostQuery(searchRecord);
			searchRecord.setValDate(valDateObj.getValDate());
			searchRecord.setActionCode("DEVALIDATE");
			final OffenderStgDetails devalDateObj = offenderStgDetailsPostQuery(searchRecord);
			searchRecord.setDeValDate(devalDateObj.getValDate());
			returnList.add(searchRecord);
		}
		return returnList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFFENDER_STG_DETAILS
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer offenderStgDetailsCommit(final OffenderStgDetailsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(bean-> bean.setCreateUserId(commitBean.getCreateUserId()));
			liReturn = oidmbrdtRepository.offenderStgDetailsInsertOffenderStgDetails(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(bean-> bean.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = oidmbrdtRepository.offenderStgDetailsUpdateOffenderStgDetails(commitBean.getUpdateList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<FormAccessibleForms> formAccessibleFormsExecuteQuery(final String offenderBookId,
			final String offenderId, String userName) {
		List<FormAccessibleForms> returnList = oidmbrdtRepository.formAccessibleFormsExecuteQuery(userName);
		for (final FormAccessibleForms obj : returnList) {
			if (obj.getDestinationForm() != null) {
				final OmsModules returnObj = formAccessibleFormsPostQuery(obj);
				obj.setDescription(returnObj.getDescription());
			}
			obj.setOriginatingForm("OIDMBRDT"); 
			obj.setOffenderId(new BigDecimal(offenderId));
			obj.setBookId(new BigDecimal(offenderBookId));
			final String checkFlagValue = omsFormAccessService.checkDataAvailable(obj);
			obj.setCheckFlag(checkFlagValue);
		}
		return returnList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstFORM_ACCESSIBLE_FORMS
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer formAccessibleFormsCommit(final FormAccessibleFormsCommitBean commitBean) {
		return null;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<SecurityThreatGroups> rgGroupRecordGroup() {
		return oidmbrdtRepository.rgGroupRecordGroup();
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgReasonRecordGroup() {
		return oidmbrdtRepository.rgReasonRecordGroup();
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgExpReasonRecordGroup() {
		return oidmbrdtRepository.rgExpReasonRecordGroup();
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<SecurityThreatGroups> rgStg1RecordGroup() {
		return oidmbrdtRepository.rgStg1RecordGroup();
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<SecurityThreatGroups> rgStg2RecordGroup() {
		List<SecurityThreatGroups> returnList = oidmbrdtRepository.rgStg2RecordGroup();
		returnList = returnList.stream().filter(distinctByKey(dup -> dup.getStgCode())).collect(Collectors.toList());
		returnList.forEach(element -> {
			if ("N".equals(element.getActiveFlag())) {
				element.setCanDisplay(false);
			}
		});
		return returnList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<SecurityThreatGroups> rgStg3RecordGroup() {
		List<SecurityThreatGroups> returnList = oidmbrdtRepository.rgStg3RecordGroup();
		returnList = returnList.stream().filter(distinctByKey(dup -> dup.getStgCode())).collect(Collectors.toList());
		returnList.forEach(element -> {
			if ("N".equals(element.getActiveFlag())) {
				element.setCanDisplay(false);
			}
		});
		return returnList;
	}

	private <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
		final Map<Object, Boolean> map = new ConcurrentHashMap<>();
		return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}
}