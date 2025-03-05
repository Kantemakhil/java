package net.syscon.s4.inst.securitythreatgroups.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OffenderStgAssociations;
import net.syscon.s4.im.beans.OffenderStgAssociationsCommitBean;
import net.syscon.s4.inst.securitythreatgroups.OidmbrasRepository;
import net.syscon.s4.inst.securitythreatgroups.OidmbrasService;

/**
 * Class OidmbrasServiceImpl@Service
 */
@Service
public class OidmbrasServiceImpl extends BaseBusiness implements OidmbrasService {

	@Autowired
	private OidmbrasRepository oidmbrasRepository;

	/**
	 * Creates new OidmbrasServiceImpl class Object
	 */
	public OidmbrasServiceImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<OffenderStgAssociations> offenderStgAssociationsExecuteQuery(
			final OffenderStgAssociations searchRecord) {
		return oidmbrasRepository.offenderStgAssociationsExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param OffenderStgAssociationscommitBean
	 *            commitBean
	 */
	@Transactional
	public Integer offenderStgAssociationsCommit(final OffenderStgAssociationsCommitBean commitBean) {
		int liReturn = 0;
		Long offBookId = null;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(data->data.setCreateUserId(commitBean.getCreateUserId()));
			for (final OffenderStgAssociations offenderStgAss : commitBean.getInsertList()) {
				offBookId = offenderStgAss.getOffenderBookId();
			}
			List<OffenderStgAssociations> saveObj = new ArrayList<>();
			if (commitBean.getInsertList().size() > 0) {
				for (int i = 0; i < commitBean.getInsertList().size(); i++) {
					saveObj = new ArrayList<>();
					final Long stgSeq = oidmbrasRepository.offenderStgAssociationsPreInsert(offBookId);
					final OffenderStgAssociations offenderAlertObj = commitBean.getInsertList().get(i);
					offenderAlertObj.setStgSeq(stgSeq);
					saveObj.add(offenderAlertObj);
					liReturn = oidmbrasRepository.offenderStgAssociationsInsertOffenderStgAssociations(saveObj);
				}
			}
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(data->data.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = oidmbrasRepository
					.offenderStgAssociationsUpdateOffenderStgAssociations(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = oidmbrasRepository
					.offenderStgAssociationsDeleteOffenderStgAssociations(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> rgReasonCodeRecordGroup() {
		return oidmbrasRepository.rgReasonCodeRecordGroup();

	}

}