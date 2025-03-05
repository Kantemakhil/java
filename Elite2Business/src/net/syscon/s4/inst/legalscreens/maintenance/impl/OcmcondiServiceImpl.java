package net.syscon.s4.inst.legalscreens.maintenance.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.casemanagement.beans.CommunityConditions;
import net.syscon.s4.inst.legals.beans.OffenderSentConditions;
import net.syscon.s4.inst.legalscreens.maintenance.OcmcondiRepository;
import net.syscon.s4.inst.legalscreens.maintenance.OcmcondiService;
import net.syscon.s4.inst.legalscreens.maintenance.bean.CommunityConditionsCommitBean;
import net.syscon.s4.inst.legalscreens.maintenance.bean.TableColumnNameBean;

/**
 * Class OcmcondiServiceImpl
 */
@Service
public class OcmcondiServiceImpl extends BaseBusiness implements OcmcondiService {

	@Autowired
	private OcmcondiRepository ocmcondiRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<CommunityConditions> comCondExecuteQuery(final CommunityConditions searchRecord) {
		return ocmcondiRepository.comCondExecuteQuery(searchRecord);

	}
	
	@Override
	public List<CommunityConditions> comCondFilteredData(final OffenderSentConditions searchRecord) {
		return ocmcondiRepository.comCondFilteredData(searchRecord);

	}
	

	/**
	 * Insert the records from database table
	 *
	 * @param lstCOM_COND
	 *
	 * @throws SQLException
	 */
	@Transactional
	public List<CommunityConditions> comCondCommit(final CommunityConditionsCommitBean commitBean) {
		final List<CommunityConditions> liReturnData = new ArrayList<>();
		CommunityConditions ommunityCondition = new CommunityConditions();
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (CommunityConditions obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = ocmcondiRepository.comcondInsertCommunityConditions(commitBean.getInsertList());
			ommunityCondition.setReturnValue(liReturn);
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (CommunityConditions obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocmcondiRepository.comCondUpdateCommunityConditions(commitBean.getUpdateList());
			ommunityCondition.setReturnValue(liReturn);
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().stream().forEach(e -> e.setModifyUserId(commitBean.getCreateUserId()));
			ommunityCondition = ocmcondiRepository.comCondDeleteCommunityConditions(commitBean.getDeleteList());
		}

		liReturnData.add(ommunityCondition);
		return liReturnData;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgCatRecordGroup() {
		return ocmcondiRepository.rgCatRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgTypeRecordGroup() {
		return ocmcondiRepository.rgTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgUnitRecordGroup() {
		return ocmcondiRepository.rgUnitRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgSvcOblRecordGroup() {
		return ocmcondiRepository.rgSvcOblRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgFunctionTypeRecordGroup() {
		return ocmcondiRepository.rgFunctionTypeRecordGroup();

	}

	/**
	 * This method is used to get the parent table list and parent table data
	 *
	 * @throws SQLException
	 */

	public CommunityConditions getDeleteRecordOrNot(final CommunityConditions searchRecord) {
		List<TableColumnNameBean> returnList = new ArrayList<>();
		returnList = ocmcondiRepository.getTableColumNames();
		returnList.forEach(ele -> {
			if (ele.getTableName() != null) {
				final Integer count = ocmcondiRepository.getDeleteRecordOrNot(searchRecord, ele.getTableName());
				if (count > 0) {
					searchRecord.setDeleteRecordCountData(count);

				}

			}
		});

		return searchRecord;

	}

}
