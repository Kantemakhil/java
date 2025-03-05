package net.syscon.s4.pkgs.omkcopy.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.inst.casemanagement.beans.WorkFlows;
import net.syscon.s4.pkgs.omkcopy.OmkcopyRepository;
import net.syscon.s4.pkgs.omkcopy.OmkcopyService;
import net.syscon.s4.sa.admin.beans.AllTabColumns;
import net.syscon.s4.sa.admin.beans.CopyTables;

@Service("OmkcopyServiceImpl_pg")
public class OmkcopyServiceImpl implements OmkcopyService {

	private static Logger logger = LogManager.getLogger(OmkcopyServiceImpl.class.getName());
	@Autowired
	@Qualifier("OmkcopyRepositoryImpl_pg")
	private OmkcopyRepository OmkcopyRepository;

	private static final String N = "N";
	private static final String Y = "Y";
	private static final String CRI = "CRI";

	@Override
	@Transactional
	public Integer copyBookingData(final String moveType,final String pMoveReason, final Long pOldBookId, final Long pNewBookId,
			final String userName) {
		Map<String, Object> outParams = new HashMap<String, Object>();
		final String pReturnText = null;
		final String vParent = null;
		final String pMoveType = CRI;
		Integer returnValue = 0;
		try {
			// COPY_BOOK_DATA() Procedure 82
			outParams = copyBookData(moveType, pMoveReason, pOldBookId, pNewBookId, pReturnText, vParent);

			// COPY_WORK_FLOWS Procedre 92
			copyWorkFlows(pOldBookId, pNewBookId, userName);
			if (outParams.size() == 0) {
				returnValue = 1;
			}
		} catch (Exception e) {
			returnValue = 0;
		}
		return returnValue;
	}

	@Override
	public void copyWorkFlows(final Long pOldBookId, final Long pNewBookId, final String userName) {
		Long lvWorkFlowId;
		List<WorkFlows> workFlowList = new ArrayList<WorkFlows>();
		// work_flow_cur cursor 565
		workFlowList = OmkcopyRepository.workFlowCur(pOldBookId);
		for (final WorkFlows workFlows : workFlowList) {
			// get_id_cur 568
			lvWorkFlowId = OmkcopyRepository.getIdCur();
			// Insert WORK_FLOWS 572
			OmkcopyRepository.insertWorkFlows(lvWorkFlowId, workFlows.getObjectCode(), pNewBookId,
					workFlows.getObjectSeq(), userName);
			// Insert WORK_FLOW_LOGS 581
			OmkcopyRepository.insertworkFlowLogs(lvWorkFlowId, userName);
		}
	}

	// COPY_BOOK_DATA() Procedure Impl 103
	@Override
	public Map<String, Object> copyBookData(final String pMoveType, final String pMoveReason, final Long pOldBookId,
			final Long pNewBookId, String pReturnText, String vParent) {
		final Map<String, Object> outParams = new HashMap<String, Object>();
		List<CopyTables> copyTabList = new ArrayList<CopyTables>();
		String vErrorText = null;
		Integer vLengthOfTableName;
		String vMoveType;
		String vMoveReason;
		Long vOldBookId;
		Long vNewBookId;
		String vReturnText = "";

		try {
			if (pOldBookId == null || pOldBookId == 0) {
				throw new Exception("Offender_book_id to copy from is invalid or missing.");
			} else if (pNewBookId == null || pNewBookId == 0) {
				throw new Exception("Offender_book_id to copy to is invalid or missing.");
			} else if (pNewBookId == pOldBookId) {
				throw new Exception("Old and new Offender_book_id are the same.");
			} else if (pMoveType == null) {
				throw new Exception("Movement type is missing.");
			} else if (pMoveReason == null) {
				throw new Exception("Movement reason is missing.");
			}

			vMoveType = pMoveType;
			vMoveReason = pMoveReason;
			vOldBookId = pOldBookId;
			vNewBookId = pNewBookId;

			// copy_tables_cur 212
			copyTabList = OmkcopyRepository.copyTablesCur(pMoveType, pMoveReason, vParent);
			for (CopyTables copyTables : copyTabList) {
				// DYNAMIC_COPY_TABLE( ) 215
				dynamicCopyTable(copyTables.getTableName(), copyTables.getColName(), copyTables.getSeqName(),
						copyTables.getParentTable(), vMoveType, vMoveReason, vOldBookId, vNewBookId);

				if (vReturnText.length() > 1) {
					vErrorText = copyTables.getTableName();
					vLengthOfTableName = vErrorText.length();
					vErrorText = vErrorText.concat(vReturnText.substring(1, (80 - vLengthOfTableName)));
				}
				vParent = copyTables.getTableName();
				// COPY_BOOK_DATA() Procedure 235
				//copyBookData(vMoveType, vMoveReason, vOldBookId, vNewBookId, pReturnText, vParent);
			}
			if (vErrorText != null) {
				pReturnText = vErrorText;
			}
		} catch (Exception e) {
			logger.error("copyBookData ", e);
		}
		outParams.put("p_return_text", pReturnText);
		outParams.put("v_parent", vParent);

		return outParams;
	}

	// DYNAMIC_COPY_TABLE( ) 273 Impl
	@Override
	public void dynamicCopyTable(final String pTableName, final String pColName, final String pSeqName,
			final String pParentTable, final String vMoveType, final String vMoveReason, final Long vOldBookId,
			final Long vNewBookId) {
		List<AllTabColumns> allTColList = new ArrayList<AllTabColumns>();
		List<CopyTables> colSeqNamList = null;

		String vInsColList = "";
		String vSelColList = "";
		String vColName;
		String vSeqName;
		Integer vDeltaValue;
		Integer vPkColCount = 0;
		String wrongColumnNameFlag = N;
		String lvActiveOnly = "";

		// column_list_cur 309
		allTColList = OmkcopyRepository.columnListCur(pTableName);
		for (final AllTabColumns allTabColumns : allTColList) {
			vColName = null;
			vSeqName = null;
			// getting wrongColumnNameFlag 320
			wrongColumnNameFlag = OmkcopyRepository.getWrongColumnNameFlag(pTableName);
			// getting Col and Seq Names 334
			colSeqNamList = OmkcopyRepository.getColSeqNames(allTabColumns.getColumnName(), vMoveType, vMoveReason);
			for (final CopyTables copyTables : colSeqNamList) {
				vColName = copyTables.getColName();
				vSeqName = copyTables.getSeqName();
			}
			if (vColName != null && pParentTable == null) {
				vPkColCount = 0;
				// getting pk Col Count 355
				vPkColCount = OmkcopyRepository.getVpkColCount(allTabColumns.getColumnName(), pTableName);
				if (vPkColCount > 0) {
					if (vSeqName != null) {
						vSelColList = vSelColList.concat(vSeqName).concat(".NEXTVAL,");
					}
				} else {
					vSelColList = vSelColList.concat(allTabColumns.getColumnName()).concat(", ");
				}
			} else if (vColName != null && pParentTable != null) {
				if (Y.equals(wrongColumnNameFlag)) {
					// getting Delta value
					vDeltaValue = OmkcopyRepository.vSelectStringOne(allTabColumns.getColumnName(), pParentTable,
							vOldBookId, vNewBookId);
				} else {
					// getting Delta value
					vDeltaValue = OmkcopyRepository.vSelectStringTwo(allTabColumns.getColumnName(), pParentTable,
							vOldBookId, vNewBookId);
				}
				vSelColList = vSelColList.concat(allTabColumns.getColumnName()).concat("+" + vDeltaValue + ",");
			}
			if (vColName == null) {
				if ("JN_OPERATION".equals(allTabColumns.getColumnName())) {
					vSelColList = vSelColList.concat("INS").concat(",");
				} else if ("JN_ORACLE_USER".equals(allTabColumns.getColumnName())) {
					vSelColList = vSelColList.concat("USER").concat(",");
				} else if ("JN_DATETIME".equals(allTabColumns.getColumnName())) {
					vSelColList = vSelColList.concat("SYSDATE").concat(",");
				} else if ("JN_NOTES".equals(allTabColumns.getColumnName())) {
					vSelColList = vSelColList.concat("COPY_BOOKING_DATA - From offender_book_id").concat(",");
				} else if ("JN_APPLN".equals(allTabColumns.getColumnName())) {
					vSelColList = vSelColList.concat("COPY_TABLE").concat(",");
				} else if ("JN_SESSION".equals(allTabColumns.getColumnName())) {
					vSelColList = vSelColList.concat("NULL").concat(",");
				} else if ("OFFENDER_BOOK_ID".equals(allTabColumns.getColumnName())) {
					vSelColList = vSelColList.concat(":new_book_id").concat(",");
				} else if ("OFFENDER_BOOKING_ID".equals(allTabColumns.getColumnName())) {
					vSelColList = vSelColList.concat(":new_book_id").concat(",");
				} else {
					vSelColList = vSelColList.concat(allTabColumns.getColumnName()).concat(",");
				}
			}
			vInsColList = vInsColList.concat(allTabColumns.getColumnName()).concat(",");
		}
		if (vInsColList != null) {
			vInsColList = vInsColList.substring(0, (vInsColList.length() - 1));
			vSelColList = vSelColList.substring(0, (vSelColList.length() - 1));

			if (pTableName.contains("OFFENDER_CONTACT_PERSONS")) {
				lvActiveOnly = " AND ACTIVE_FLAG = 'Y' ";
			}
			if ("Y".equals(wrongColumnNameFlag)) {
				OmkcopyRepository.insertSqlOne(vInsColList, vSelColList, lvActiveOnly, pTableName, vNewBookId,
						vOldBookId);
			} else {
				OmkcopyRepository.insertSqlTwo(vInsColList, vSelColList, lvActiveOnly, pTableName, vNewBookId,
						vOldBookId);
			}

		}

	}

}
