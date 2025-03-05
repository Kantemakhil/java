package net.syscon.s4.inst.legals;

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
import net.syscon.s4.inst.legals.beans.Charges;
import net.syscon.s4.inst.legals.beans.HoldWarrentDetainer;
import net.syscon.s4.inst.legals.beans.HoldsWarantsHistory;
import net.syscon.s4.inst.legals.beans.HoldsWarantsHistoryCommitBean;
import net.syscon.s4.inst.legals.beans.HwdetChargesCommitBean;
import net.syscon.s4.inst.legals.beans.HwdetCommitBean;
import net.syscon.s4.inst.legalscreens.maintenance.OcmlesetService;


@EliteController
public class OidhwdetController {
	
	@Autowired
	private OidhwdetService oidhwdetService;
	
	@Autowired
	private OcmlesetService ocmlesetService;
	
	private static Logger logger = LogManager.getLogger(OumorcodController.class.getName());
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidhwdet/searchHoldWarrentDetainer", method = RequestMethod.GET)
	public List<HoldWarrentDetainer> holdWarrentDetainerData(@RequestParam final Long offenderBookId) {
		List<HoldWarrentDetainer> holdWarrentDetainerData = new ArrayList<HoldWarrentDetainer>();
		try {			
			holdWarrentDetainerData = oidhwdetService.searchHoldWarrentDetainer(offenderBookId);
			}
			
		catch (Exception e) {
			logger.error("holdWarrentDetainerData", e);
		}
		return holdWarrentDetainerData;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidhwdet/searchCharges", method = RequestMethod.GET)
	public List<Charges> searchCharges(@RequestParam final Long hwdId) {
		List<Charges> chargesData = new ArrayList<Charges>();
		try {			
			chargesData = oidhwdetService.searchCharges(hwdId);
			}
			
		catch (Exception e) {
			logger.error("searchCharges", e);
		}
		return chargesData;
	}
	
	/***
	 * insert,update,delete hold warrent
	 * @param hwDetCommitBean
	 * @return
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidhwdet/insertUpdateDeleteHwdet", method = RequestMethod.POST)
	public @ResponseBody Integer insertUpdateDeleteHwdet(@RequestBody HwdetCommitBean hwDetCommitBean ) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		hwDetCommitBean.setCreateUserId(userName);
		try{
			liReturn = oidhwdetService.insertUpdateDeleteCourtReport(hwDetCommitBean);
		}
		catch (Exception e){
			logger.error("insertUpdateDeleteCourtReport", e);
		}
		return liReturn;
	}
	
	/***
	 * insert,update,delete hold charges
	 * @param hwdetChargesCommitBean
	 * @return
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidhwdet/insertUpdateDeleteHwdetCharges", method = RequestMethod.POST)
	public @ResponseBody Integer insertUpdateDeleteHwdetCharges(@RequestBody HwdetChargesCommitBean hwdetChargesCommitBean) {
		int liReturn = 0;
		try{
			if (hwdetChargesCommitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				hwdetChargesCommitBean.setCreateUserId(userName);
			}
			liReturn = oidhwdetService.insertUpdateDeleteHwdetCharges(hwdetChargesCommitBean);
		}
		catch (Exception e){
			logger.error("insertUpdateDeleteHwdetCharges", e);
		}
		return liReturn;
	}
	
	/**
	 * POPULATE HISTORY DATA CONTROLLER.
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidhwdet/populateHistory", method = RequestMethod.GET)
	public List<HoldsWarantsHistory> getHistory(@RequestParam final Long hwdId) {
		List<HoldsWarantsHistory> chargesData = new ArrayList<HoldsWarantsHistory>();
		try {			
			chargesData = oidhwdetService.populateHistory(hwdId);
			}
			
		catch (Exception e) {
			logger.error("getHistory", e);
		}
		return chargesData;
	}
	
	/**
	 * Insert,Update and Delete History Record.
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidhwdet/updateHistory", method = RequestMethod.POST)
	public @ResponseBody Integer insertUpdateDeleteHwdetCharges(@RequestBody HoldsWarantsHistoryCommitBean historyCommitBean) {
		int liReturn = 0;
		try{
			if (historyCommitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				historyCommitBean.setCreateUserId(userName);
			}
			liReturn = oidhwdetService.updateHwdetHistory(historyCommitBean);
		}
		catch (Exception e){
			logger.error("insertUpdateDeleteHwdetCharges", e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidhwdet/getBailMandatorySetting", method = RequestMethod.GET)
	public String getBailMandatorySetting(@RequestParam("code") final String code) {
		String returnValue=null;
		try {
			returnValue = ocmlesetService.getLegalSettingValue(code);
		} catch (Exception e) {
			logger.error("Exception in : getBailMandatorySetting : {}", e);
		}
		return returnValue;
	}
}


