package net.syscon.s4.cm.demographics.maintenance.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.demographics.maintenance.OcmohstaRepository;
import net.syscon.s4.cm.demographics.maintenance.OcmohstaService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.CommunityHeaderStatuses;
import net.syscon.s4.im.beans.CommunityHeaderStatusesCommitBean;

/**
 * Class OcmohstaServiceImpl
 */
@Service
public class OcmohstaServiceImpl extends BaseBusiness implements OcmohstaService {

	@Autowired
	private OcmohstaRepository ocmohstaRepository;
	public static final Integer STATUSCOUNT = 2;
	public static final Integer HIRSEQCOUNT = 3;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<CommunityHeaderStatuses> comHdrStExecuteQuery(CommunityHeaderStatuses searchRecord) {
		return ocmohstaRepository.comHdrStExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstCOM_HDR_ST
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer comHdrStCommit(CommunityHeaderStatusesCommitBean commitBean) {
		Integer liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (CommunityHeaderStatuses bean : commitBean.getInsertList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
			}
			Integer dupStatCodeValue = statcodedupValidation(commitBean.getInsertList());
			if (dupStatCodeValue == 2) {
				return dupStatCodeValue;
			}
			Integer dupHirSeqValue = hirarchyDupValidation(commitBean.getInsertList());
			if (dupHirSeqValue == 3) {
				return dupHirSeqValue;
			}
			liReturn = ocmohstaRepository.comHdrStInsertCommunityHeaderStatuses(commitBean.getInsertList());

		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (CommunityHeaderStatuses bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			Integer dupHirSeqValue = hirarchyDupValidationWhenUpdate(commitBean.getUpdateList());
			if (dupHirSeqValue == 3) {
				return dupHirSeqValue;
			}
			liReturn = ocmohstaRepository.comHdrStUpdateCommunityHeaderStatuses(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (CommunityHeaderStatuses bean : commitBean.getDeleteList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocmohstaRepository.comHdrStDeleteCommunityHeaderStatuses(commitBean.getDeleteList());
		}
		return liReturn;

	}

	public Integer statcodedupValidation(List<CommunityHeaderStatuses> listobj) {
		final List<String> statusObj = listobj.stream().map(data -> data.getStatusCode()).collect(Collectors.toList());
		final Integer statusCount = ocmohstaRepository.getStatuscodeCount(statusObj);
		if (statusCount > 0) {
			return STATUSCOUNT;
		}
		return 0;
	}

	public Integer hirarchyDupValidation(List<CommunityHeaderStatuses> listobj) {
		final List<BigDecimal> hirarchyObj = listobj.stream().map(data -> data.getHierarchySequence())
				.collect(Collectors.toList());
		final Integer hirarchyCount = ocmohstaRepository.gethierarchyCount(hirarchyObj);
		if (hirarchyCount > 0) {
			return HIRSEQCOUNT;
		}
		return 0;
	}
	public Integer hirarchyDupValidationWhenUpdate(List<CommunityHeaderStatuses> listobj) {
		final List<BigDecimal> hirarchyObj = listobj.stream().map(data -> data.getHierarchySequence())
				.collect(Collectors.toList());
		final List<String> rowId = listobj.stream().map(data -> data.getRowId())
				.collect(Collectors.toList());
		final Integer hirarchyCount = ocmohstaRepository.hirarchyDupValidationWhenUpdate(hirarchyObj,rowId);
		if (hirarchyCount > 0) {
			return HIRSEQCOUNT;
		}
		return 0;
	}
}