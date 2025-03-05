package net.syscon.s4.inmate.trust.trustaccounts.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inmate.beans.Remitters;
import net.syscon.s4.inmate.beans.RemittersCommitBean;
import net.syscon.s4.inmate.trust.trustaccounts.OtmremitRepository;
import net.syscon.s4.inmate.trust.trustaccounts.OtmremitService;

/**
 * Class OtmremitServiceImpl
 */
@Service
public class OtmremitServiceImpl extends BaseBusiness implements OtmremitService {

	@Autowired
	private OtmremitRepository otmremitRepository;

	/**
	 * Creates new OtmremitServiceImpl class Object
	 */
	public OtmremitServiceImpl() {
		// OtmremitServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public ReferenceCodes cgfklkpRemRemRefCodeF(final ReferenceCodes paramBean) {
		final ReferenceCodes referenceCodes = (ReferenceCodes) otmremitRepository.cgfklkpRemRemRefCodeF(paramBean);
		return referenceCodes;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<ReferenceCodes> cgfkchkRemRemRefCodeF(final ReferenceCodes paramBean) {
		final List<ReferenceCodes> referenceCodesList = otmremitRepository.cgfklkpRemRemRefCodeF(paramBean);
		return referenceCodesList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public ReferenceCodes cgfklkpRemRemRefCode(final ReferenceCodes paramBean) {
		final ReferenceCodes referenceCodes = (ReferenceCodes) otmremitRepository.cgfklkpRemRemRefCodeF(paramBean);
		return referenceCodes;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<Remitters> cgrichkRemitters(final Remitters paramBean) {
		final List<Remitters> remittersList = (List<Remitters>) otmremitRepository.cgrichkRemitters(paramBean);
		return remittersList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<Remitters> remExecuteQuery(final Remitters searchRecord) {
		return otmremitRepository.remExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstREM
	 *
	 * @
	 */
	@Transactional
	public Integer remCommit(final RemittersCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (Remitters data : commitBean.getInsertList()) {
				data.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = otmremitRepository.remInsertRemitters(commitBean.getInsertList());
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (Remitters data : commitBean.getUpdateList()) {
				data.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = otmremitRepository.remUpdateRemitters(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = otmremitRepository.remDeleteRemitters(commitBean.getDeleteList());
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
	public Remitters remitExecuteQuery(final Remitters searchRecord) {
		return otmremitRepository.remitExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstREM1
	 *
	 * @
	 */
	@Transactional
	public Integer rem1Commit(final RemittersCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (Remitters data : commitBean.getInsertList()) {
				data.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = otmremitRepository.remInsertRemitters(commitBean.getInsertList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = otmremitRepository.remDeleteRemitters(commitBean.getDeleteList());
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
		return otmremitRepository.sysPflExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSYS_PFL
	 *
	 * @
	 */
	@Transactional
	public Integer sysPflCommit(final SystemProfilesCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			liReturn = otmremitRepository.sysPflInsertSystemProfiles(commitBean.getInsertList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = otmremitRepository.sysPflDeleteSystemProfiles(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> cgfkRemDspDescriptionRecordGroup() {
		return otmremitRepository.cgfkRemDspDescriptionRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> cgfkRemitDspDescriptionRecordGroup() {
		return otmremitRepository.cgfkRemitDspDescriptionRecordGroup();

	}

	@Override
	public List<ReferenceCodes> getCodes() {
		return otmremitRepository.getCodes();
	}

}