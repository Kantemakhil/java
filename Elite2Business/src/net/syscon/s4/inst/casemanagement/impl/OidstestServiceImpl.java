package net.syscon.s4.inst.casemanagement.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.globalconfiguration.OcmpconfRepository;
import net.syscon.s4.globalconfiguration.impl.OcmpconfServiceImpl;
import net.syscon.s4.im.beans.IwpDocuments;
import net.syscon.s4.inst.casemanagement.OidstestRepository;
import net.syscon.s4.inst.casemanagement.OidstestService;
import net.syscon.s4.inst.casemanagement.beans.OffenderSampleSubstances;
import net.syscon.s4.inst.casemanagement.beans.OffenderSampleSubstancesCommitBean;
import net.syscon.s4.inst.casemanagement.beans.OffenderSamples;
import net.syscon.s4.inst.casemanagement.beans.OffenderSamplesCommitBean;
import net.syscon.s4.inst.property.bean.Corporate;
import net.syscon.s4.pkgs.oms_utils.OmsUtilsService;

/**
 * Class OidstestServiceImpl
 */
@Service
public class OidstestServiceImpl extends BaseBusiness implements OidstestService {

	@Autowired
	private OidstestRepository oidstestRepository;
	
	@Autowired
	private OmsUtilsService omsUtilsService;
	
	@Autowired
	private OcmpconfRepository ocmpconfRepository;
	
	private static Logger logger = LogManager.getLogger(OidstestServiceImpl.class.getName());

	/**
	 * Creates new OidstestServiceImpl class Object
	 */
	public OidstestServiceImpl() {

	}


	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<OffenderSamples> subSamplExecuteQuery(final OffenderSamples searchRecord) {
		final List<OffenderSamples> returnList = oidstestRepository.subSamplExecuteQuery(searchRecord);
		for (final OffenderSamples obj : returnList) {
			if (obj.getTestStaffId() != null) {
				final StaffMembers returnObj = oidstestRepository.subSamplPostQueryStaffId(obj);
				if (returnObj.getStaffId() != null) {
					obj.setTestedBy(String.valueOf(returnObj.getStaffId()));
					obj.setTestedBy((obj.getSealFlag() != null && obj.getSealFlag().equals("Y")) ? String.valueOf(returnObj.getStaffId()):null);
				}
			}
			if (obj.getTestCorporateId() != null) {
				final Corporate returnObj = oidstestRepository.subSamplPostQueryCorporateId(obj);
				if (returnObj.getCorporateId() != 0) {
					obj.setTestedBy((obj.getSealFlag() != null && obj.getSealFlag().equals("Y")) ? String.valueOf(returnObj.getCorporateId()):null);
				}
			}
			

			Integer returnObj = oidstestRepository.setPositiveTestedFlag(obj);
			if (returnObj > 0) {
				obj.setTestedPositive("Y");
			} else {
				obj.setTestedPositive("N");
			}

			obj.setTestedPositive(obj.getTestedPositive() != null ? obj.getTestedPositive() : "N");
			final Integer count = oidstestRepository.getTheCountOfOffenderSampleSubstances(obj);
			obj.setCountOffSub(count);
			obj.setStdTemp(obj.getSampleTestDate());
			obj.setTestedByTemp(String.valueOf(obj.getTestedBy()));
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSUB_SAMPL
	 *
	 * @
	 */
	@Transactional
	public Integer offSamplCommit(final OffenderSamplesCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		OffenderSampleSubstances bean = new OffenderSampleSubstances();
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			final List<OffenderSamples> insertList = commitBean.getInsertList();

			for (final OffenderSamples obj : insertList) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				obj.setModifyUserId(commitBean.getCreateUserId());
				if ("true".equals(obj.getExternalTestAgencyFlag())) {
					obj.setExternalTestAgencyFlag("Y");
				} else {
					obj.setExternalTestAgencyFlag("N");
				}
				obj.setTestedPositiveFlag("true".equalsIgnoreCase(obj.getTestedPositive())?"Y":"N");
			}
			liReturn = oidstestRepository.offSamplInsertOffenderSamples(commitBean.getInsertList());
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			final List<OffenderSamples> updateList = commitBean.getUpdateList();

			for (final OffenderSamples obj : updateList) {
				obj.setModifyUserId(commitBean.getCreateUserId());

				if ("true".equals(obj.getExternalTestAgencyFlag())) {
					obj.setExternalTestAgencyFlag("Y");
				} else {
					obj.setExternalTestAgencyFlag("N");
				}
				obj.setTestedPositiveFlag("true".equalsIgnoreCase(obj.getTestedPositive())?"Y":"N");
			}
			liReturn = oidstestRepository.offSamplUpdateOffenderSamples(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			bean.setOffenderSampleId(commitBean.getDeleteList().get(0).getOffenderSampleId());
			final List<OffenderSampleSubstances> offSubCount = oidstestRepository.offenderSampleSubstancesExecuteQuery(bean);
			if (offSubCount != null && !offSubCount.isEmpty()) {
				return 2;
			}
			liReturn = oidstestRepository.offSamplDeleteOffenderSamples(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<OffenderSampleSubstances> offenderSampleSubstancesExecuteQuery(final OffenderSampleSubstances searchRecord) {
		return oidstestRepository.offenderSampleSubstancesExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSUB_TEST
	 *
	 * @
	 */
	@Transactional
	public Integer subTestCommit(final OffenderSampleSubstancesCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			OffenderSamples offenderSamples = new OffenderSamples();
			for (final OffenderSampleSubstances obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				obj.setModifyUserId(commitBean.getCreateUserId());
				offenderSamples.setOffenderSampleId(obj.getOffenderSampleId());	
			}
			liReturn = oidstestRepository.subTestInsertOffenderSampleSubstances(commitBean.getInsertList());
			commitBean.getInsertList().forEach(bean->{
			offenderSamples.setOffenderSampleId(bean.getOffenderSampleId());
			commitBean.getOffenderSamples().setTestedPositive(oidstestRepository.setPositiveTestedFlag(offenderSamples)>0?"true":"false");});
			OffenderSamplesCommitBean offenderSamplesCommitBean = new OffenderSamplesCommitBean();
			List<OffenderSamples> list = new ArrayList<OffenderSamples>();
			list.add(commitBean.getOffenderSamples());
			offenderSamplesCommitBean.setCreateUserId(commitBean.getCreateUserId());
			offenderSamplesCommitBean.setUpdateList(list);
			offSamplCommit(offenderSamplesCommitBean);
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			OffenderSamples offenderSamples = new OffenderSamples();
			for (final OffenderSampleSubstances obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oidstestRepository.subTestUpdateOffenderSampleSubstances(commitBean.getUpdateList());
			commitBean.getUpdateList().forEach(bean->{
			offenderSamples.setOffenderSampleId(bean.getOffenderSampleId());
			commitBean.getOffenderSamples().setTestedPositive(oidstestRepository.setPositiveTestedFlag(offenderSamples)>0?"true":"false");});
			OffenderSamplesCommitBean offenderSamplesCommitBean = new OffenderSamplesCommitBean();
			List<OffenderSamples> list = new ArrayList<OffenderSamples>();
			offenderSamplesCommitBean.setCreateUserId(commitBean.getCreateUserId());
			list.add(commitBean.getOffenderSamples());
			offenderSamplesCommitBean.setUpdateList(list);
			offSamplCommit(offenderSamplesCommitBean);
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			OffenderSamples offenderSamples = new OffenderSamples();
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = oidstestRepository.subTestDeleteOffenderSampleSubstances(commitBean.getDeleteList());	
			commitBean.getDeleteList().forEach(bean->{
			offenderSamples.setOffenderSampleId(bean.getOffenderSampleId());
			commitBean.getOffenderSamples().setTestedPositive(oidstestRepository.setPositiveTestedFlag(offenderSamples)>0?"true":"false");});
			OffenderSamplesCommitBean offenderSamplesCommitBean = new OffenderSamplesCommitBean();
			List<OffenderSamples> list = new ArrayList<OffenderSamples>();
			offenderSamplesCommitBean.setCreateUserId(commitBean.getCreateUserId());
			list.add(commitBean.getOffenderSamples());
			offenderSamplesCommitBean.setUpdateList(list);
			offSamplCommit(offenderSamplesCommitBean);
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles searchRecord) {
		return oidstestRepository.sysPflExecuteQuery(searchRecord);

	}


	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<StaffMembers> rgWitnessRecordGroup(String takenStaffId) {
		if ("undefined".equals(takenStaffId)) {
			takenStaffId = null;
		}
		final List<StaffMembers> returnlist = oidstestRepository.rgWitnessRecordGroup(takenStaffId);
		if (takenStaffId != null) {
			for (final StaffMembers obj : returnlist) {
				if (takenStaffId.equals(obj.getCode())) {
					obj.setCanDisplay(false);
				}
			}
		}
		return returnlist;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgSubTesRsltRecordGroup() {
		return oidstestRepository.rgSubTesRsltRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgSubTesDispRecordGroup() {
		return oidstestRepository.rgSubTesDispRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgSubTesTypeRecordGroup() {
		return oidstestRepository.rgSubTesTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgSubTesRsnRecordGroup() {
		return oidstestRepository.rgSubTesRsnRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<StaffMembers> rgTakenByRecordGroup(String staffId) {
		if ("undefined".equals(staffId) || "null".equals(staffId)) {
			staffId = null;
		}
		final List<StaffMembers> returnlist = oidstestRepository.rgTakenByRecordGroup(staffId);
		if (staffId != null) {
		}
		return returnlist;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgTestedByRecordGroup(String extAgencyFlag) {
		if ("true".equals(extAgencyFlag)) {
			extAgencyFlag = "Y";
		} else {
			extAgencyFlag = "N";
		}
		final List<ReferenceCodes> returnList = oidstestRepository.rgTestedByRecordGroup(extAgencyFlag);
		for (final ReferenceCodes obj : returnList) {
			if (obj.getCode() == null) {
				obj.setCode(obj.getStaffId() != null ? obj.getStaffId().toString():null);
			}

		}
		return returnList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgSubstanceRecordGroup() {
		return oidstestRepository.rgSubstanceRecordGroup();

	}


	/**
	 * Method is used to get the staffId from DB
	 */
	public BigDecimal getStaffId(String userId) {
		return  omsUtilsService.getStaffid(userId);
	}


	@Override
	public Boolean checkDocumentDependency(Integer offenderBookId, String screenId, String displayNo) {
		List<IwpDocuments> createdDocs = new ArrayList<IwpDocuments>();
		try {
			createdDocs =  ocmpconfRepository.checkOrderDependency(offenderBookId,  screenId,  displayNo);
			if(createdDocs!= null && createdDocs.size()>0) {
				return true;
			}
		} catch (Exception e) {
			logger.error("checkDocumentDependency offenderBookId: {},displayNo: {}, screenId: {} ",offenderBookId,displayNo,screenId );
		}
		return false;
	}

}