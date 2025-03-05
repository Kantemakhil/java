package net.syscon.s4.sa.admin.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.FormAccessibleForms;
import net.syscon.s4.common.beans.FormAccessibleFormsCommitBean;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.sa.admin.OumformsRepository;
import net.syscon.s4.sa.admin.OumformsService;
import net.syscon.s4.sa.admin.beans.AccessibleFormTables;
import net.syscon.s4.sa.admin.beans.AccessibleFormTablesCommitBean;
import net.syscon.s4.sa.admin.beans.AllTabColumns;

/**
 * Class OumformsServiceImpl
 */
@Service
public class OumformsServiceImpl extends BaseBusiness implements OumformsService { 

	@Autowired
	private OumformsRepository oumformsRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<OmsModules> omsModExecuteQuery(final OmsModules searchRecord) {
		return oumformsRepository.omsModExecuteQuery(searchRecord);

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<FormAccessibleForms> fafExecuteQuery(final FormAccessibleForms searchRecord) {
		return oumformsRepository.fafExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstFAF
	 *
	 * 
	 */
	@Transactional
	public Integer fafCommit(final FormAccessibleFormsCommitBean commitBean) {
		int liReturn = 0;
		AccessibleFormTables accessibleFormTables = new AccessibleFormTables();
		List<AccessibleFormTables> childList = new ArrayList<>();

		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (final FormAccessibleForms obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oumformsRepository.fafInsertFormAccessibleForms(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (FormAccessibleForms obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oumformsRepository.fafUpdateFormAccessibleForms(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			for (FormAccessibleForms a : commitBean.getDeleteList()) {
				a.setModifyUserId(commitBean.getCreateUserId());
				accessibleFormTables.setDestinationForm(a.getDestinationForm());
				accessibleFormTables.setOriginatingForm(a.getOriginatingForm());
				List<AccessibleFormTables> returnList = oumformsRepository.accessTabExecuteQuery(accessibleFormTables);
				if (returnList.size() > 0) {
					returnList.forEach(action -> {
						childList.add(action);
					});
				}
			}
			if (childList.size() > 0) {
				 oumformsRepository.accessTabDeleteAccessibleFormTables(childList);
			}
			liReturn = oumformsRepository.fafDeleteFormAccessibleForms(commitBean.getDeleteList());
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
	public List<AccessibleFormTables> accessTabExecuteQuery(final AccessibleFormTables searchRecord) {
		return oumformsRepository.accessTabExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstACCESS_TAB
	 */
	@Transactional
	public AccessibleFormTables accessTabCommit(final AccessibleFormTablesCommitBean commitBean) {
		AccessibleFormTables returnData = new AccessibleFormTables();
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (final AccessibleFormTables obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			returnData = oumformsRepository.accessTabInsertAccessibleFormTables(commitBean.getInsertList());
		}
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			returnData = oumformsRepository.accessTabDeleteAccessibleFormTablesTwo(commitBean.getDeleteList());
		}
		return returnData;
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgModuleNameRecordGroup() {
		return oumformsRepository.rgModuleNameRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<AllTabColumns> rgTableNameRecordGroup() {
		return oumformsRepository.rgTableNameRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<OmsModules> rgDestinationFormRecordGroup() {
		return oumformsRepository.rgDestinationFormRecordGroup();

	}

}