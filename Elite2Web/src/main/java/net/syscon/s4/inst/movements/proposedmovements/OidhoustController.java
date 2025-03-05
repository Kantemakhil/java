package net.syscon.s4.inst.movements.proposedmovements;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.movements.proposedmovements.beans.OffenderProposedIntlocs;
import net.syscon.s4.inst.movements.proposedmovements.beans.VHousingMoves;
import net.syscon.s4.inst.transportation.maintenance.OffenderLocChngDtls;

@EliteController
public class OidhoustController {

	@Autowired
	private OidhoustService oidhoustService;

	private static Logger logger = LogManager.getLogger(OidhoustController.class.getName());

	/**
	 * getting rgFromLivUnit LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidhoust/rgAgyIdRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgAgyIdRecordGroup(@RequestParam(value = "caseloadId") final String caseloadId) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidhoustService.rgAgyIdRecordGroup(caseloadId);
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception occured in " + this.getClass().getName() + " rgAgyIdRecordGroup:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgFromLivUnit LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidhoust/rgLocFromRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgLocFromRecordGroup(@RequestParam(value = "agyLocId") final String agyLocId) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidhoustService.rgLocFromRecordGroup(agyLocId);
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception occured in " + this.getClass().getName() + " rgLocFromRecordGroup:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgFromLivUnit LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidhoust/rgLocToRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgLocToRecordGroup(@RequestParam(value = "fromAgyLocId") final String fromAgyLocId) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidhoustService.rgLocToRecordGroup(fromAgyLocId);
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception occured in " + this.getClass().getName() + " rgLocToRecordGroup:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgFromLivUnit LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidhoust/rgMoveTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgMoveTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidhoustService.rgMoveTypeRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception occured in " + this.getClass().getName() + " rgMoveTypeRecordGroup:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgFromLivUnit LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidhoust/rgMoveReasonRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgMoveReasonRecordGroup(
			@RequestParam(value = "movementType") final String movementType) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidhoustService.rgMoveReasonRecordGroup(movementType);
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception occured in " + this.getClass().getName() + " rgMoveReasonRecordGroup:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgFromLivUnit LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidhoust/rgAppStatusRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgAppStatusRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidhoustService.rgAppStatusRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception occured in " + this.getClass().getName() + " rgAppStatusRecordGroup:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgFromLivUnit LOV values
	 */

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidhoust/rgTxnStatusRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgTxnStatusRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {

			recordList = oidhoustService.rgTxnStatusRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception occured in " + this.getClass().getName() + " rgMoveReasonRecordGroup:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidhoust/housMoveExecuteQuery", method = RequestMethod.POST)
	public List<VHousingMoves> housMoveExecuteQuery(@RequestBody VHousingMoves searchBean) {
		List<VHousingMoves> searchResult = new ArrayList<>();
		try {
			searchResult = oidhoustService.housMoveExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " housMoveExecuteQuery :", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidhoust/populateInmaDetails", method = RequestMethod.POST)
	public List<OffenderProposedIntlocs> populateInmaDetails(@RequestBody VHousingMoves searchBean) {
		List<OffenderProposedIntlocs> searchResult = new ArrayList<>();
		try {
			searchResult = oidhoustService.populateInmaDetails(searchBean);
		} catch (Exception e) {
			OffenderProposedIntlocs bean = new OffenderProposedIntlocs();
			logger.error("Exception occured in " + this.getClass().getName() + "populateInmaDetails :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param updateBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidhoust/inmateCommit", method = RequestMethod.POST)
	public @ResponseBody Integer inmateCommit(@RequestBody VHousingMoves updateBean) {
		Integer liReturn = 0;
		try {
			liReturn = oidhoustService.inmateCommit(updateBean);
		} catch (Exception e) {

			logger.error("Exception occured in " + this.getClass().getName() + " inmateCommit ", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidhoust/populatestatDetDetails", method = RequestMethod.POST)
	public List<OffenderLocChngDtls> populatestatDetDetails(@RequestBody VHousingMoves searchBean) {
		List<OffenderLocChngDtls> searchResult = new ArrayList<>();
		try {
			searchResult = oidhoustService.populatestatDetDetails(searchBean);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + "populatestatDetDetails ", e);
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
	@RequestMapping(value = "/oidhoust/statDetCommit", method = RequestMethod.POST)
	public @ResponseBody Integer statDetCommit(@RequestBody OffenderLocChngDtls commitBean) {
		Integer liReturn = 0;
		try {
			liReturn = oidhoustService.statDetCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception occured in " + this.getClass().getName() + " statDetCommit ", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidhoust/getCurInmAppStatus", method = RequestMethod.POST)
	public String getCurInmAppStatus(@RequestBody VHousingMoves searchBean) {
		String statusCode = null;
		try {
			statusCode = oidhoustService.getCurInmAppStatus(searchBean);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " getCurInmAppStatus :", e);
		}
		return statusCode;
	}

}
