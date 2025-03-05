package net.syscon.s4.inmate.trust.financialsmaintenance.transaction.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.CaseloadAccountPeriods;
import net.syscon.s4.common.beans.CaseloadAccountPeriodsCommitBean;
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inmate.trust.financialsmaintenance.transaction.OtmoncoaRepository;
import net.syscon.s4.inmate.trust.financialsmaintenance.transaction.OtmoncoaService;
import net.syscon.s4.pkgs.common.CommonService;

@Service
public class OtmoncoaServiceImpl extends BaseBusiness implements OtmoncoaService {

	private static Logger logger = LogManager.getLogger(OtmoncoaServiceImpl.class.getName());

	@Autowired
	private OtmoncoaRepository otmoncoaRepository;
	
	@Autowired
	private CommonService commonService;

	/**
	 * Creates new OtmoncoaServiceImpl class Object
	 */
	public OtmoncoaServiceImpl() {
		// OtmoncoaServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<Caseloads> CgfkchkCsldApCsldApCsld(Caseloads paramBean) {
		List<Caseloads> caseloadsList = otmoncoaRepository.cgfkchkCsldApCsldApCsld(paramBean);
		return caseloadsList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<Object> CgwhenNewFormInstance() {
		return null;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<CaseloadAccountPeriods> csldApExecuteQuery(CaseloadAccountPeriods searchRecord) {
		return otmoncoaRepository.csldApExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstCSLD_AP
	 *
	 * @throws SQLException
	 */
	@Transactional
	public String csldApCommit(CaseloadAccountPeriodsCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(bean -> bean.setCreateUserId(commitBean.getCreateUserId()));
			liReturn = keyCommit(commitBean.getInsertList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bean -> bean.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = otmoncoaRepository.csldApDeleteCaseloadAccountPeriods(commitBean.getDeleteList());
		}
		return String.valueOf(liReturn);
	}

	private int keyCommit(List<CaseloadAccountPeriods> insertList) {
		try {
			insertList.forEach(data -> {
			//	otmoncoaRepository.otmoncoaGenAccountCodes(data.getCaseloadId());
				commonService.otmoncoaGenAccountCodes(data.getCaseloadId(), null, data.getCreateUserId());
			});
		} catch (Exception e) {
			logger.error("Error in Key Commit", e);
			throw new RuntimeException("otmoncoa.keycmterr");
		}
		return 1;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<Caseloads> cgfkCsldApCaseloadIdRecordGroup(final String caseloadType) {
		return otmoncoaRepository.cgfkCsldApCaseloadIdRecordGroup(caseloadType);

	}

	@Override
	public BigDecimal getTotalCount(final String caseloadId) {
		return otmoncoaRepository.getTotalCount(caseloadId);
	}

}