package net.syscon.s4.sa.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.sa.admin.beans.AllTabColumns;
import net.syscon.s4.sa.admin.beans.CopyTables;
import net.syscon.s4.sa.admin.beans.CopyTablesCommitBean;

/**
 * OumcdtabController
 */
@EliteController
public class OumcdtabController {
	@Autowired
	private OumcdtabService oumcdtabService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumcdtabController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumcdtab/modifyTabExecuteQuery", method = RequestMethod.POST)
	public List<CopyTables> modifyTabExecuteQuery(@RequestBody final CopyTables searchBean) {
		List<CopyTables> searchResult = new ArrayList<>();
		try {
			  searchResult = oumcdtabService.modifyTabExecuteQuery(searchBean);
		} catch (Exception e) {
			final CopyTables bean = new CopyTables();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oumcdtab/modifyTabCommit", method = RequestMethod.POST)
	public @ResponseBody List<CopyTables> modifyTabCommit(@RequestBody final CopyTablesCommitBean commitBean) {
		List<CopyTables> liReturn = new ArrayList<CopyTables>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			   liReturn = oumcdtabService.modifyTabCommit(commitBean);
		} catch (Exception e) {
			final CopyTables obj = new CopyTables();
			logger.error("modifyTabCommit : oumcdtab:", e);
			obj.setErrorMessage(e.getMessage().toUpperCase());
			liReturn.add(obj);
		}
		return liReturn;
	}

	/**
	 * getting cgfkModifyTabMovementType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumcdtab/cgfkModifyTabMovementTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkModifyTabMovementTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oumcdtabService.cgfkModifyTabMovementTypeRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Oumcdtab:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkModifyTabMovementReaso LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumcdtab/cgfkModifyTabMovementReasoRecordGroup", method = RequestMethod.GET)
	public List<MovementReasons> cgfkModifyTabMovementReasoRecordGroup(
			@RequestParam("movementType") final String movementType) {
		List<MovementReasons> recordList = new ArrayList<MovementReasons>();
		try {
			recordList = oumcdtabService.cgfkModifyTabMovementReasoRecordGroup(movementType);
		} catch (Exception e) {
			final MovementReasons obj = new MovementReasons();
			logger.error("Exception : Oumcdtab:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting lovParentTable LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumcdtab/lovParentTableRecordGroup", method = RequestMethod.GET)
	public List<AllTabColumns> lovParentTableRecordGroup() {
		List<AllTabColumns> recordList = new ArrayList<AllTabColumns>();
		try {
			recordList = oumcdtabService.lovParentTableRecordGroup();
		} catch (Exception e) {
			final AllTabColumns obj = new AllTabColumns();
			logger.error("Exception : Oumcdtab:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting lovTableName LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumcdtab/lovTableNameRecordGroup", method = RequestMethod.GET)
	public List<AllTabColumns> lovTableNameRecordGroup() {
		List<AllTabColumns> recordList = new ArrayList<AllTabColumns>();
		try {
			recordList = oumcdtabService.lovTableNameRecordGroup();
		} catch (Exception e) {
			final AllTabColumns obj = new AllTabColumns();
			logger.error("Exception : Oumcdtab:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting lovColumnName LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumcdtab/lovColumnNameRecordGroup", method = RequestMethod.GET)
	public List<AllTabColumns> lovColumnNameRecordGroup(@RequestParam("tableName") final String tableName) {
		List<AllTabColumns> recordList = new ArrayList<AllTabColumns>();
		try {
			recordList = oumcdtabService.lovColumnNameRecordGroup(tableName);
		} catch (Exception e) {
			final AllTabColumns obj = new AllTabColumns();
			logger.error("Exception : Oumcdtab:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting lovSeqName LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumcdtab/lovSeqNameRecordGroup", method = RequestMethod.GET)
	public List<AllTabColumns> lovSeqNameRecordGroup() {
		List<AllTabColumns> recordList = new ArrayList<AllTabColumns>();
		try {
			recordList = oumcdtabService.lovSeqNameRecordGroup();
		} catch (Exception e) {
			final AllTabColumns obj = new AllTabColumns();
			logger.error("Exception : Oumcdtab:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

}