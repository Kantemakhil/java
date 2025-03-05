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
import net.syscon.s4.inst.legals.beans.AdjustCommitBean;
import net.syscon.s4.inst.legals.beans.AdjustmentDetails;
import net.syscon.s4.inst.legals.beans.AdjustmentDetailsCommitBean;
import net.syscon.s4.inst.legals.beans.Adjustments;
import net.syscon.s4.inst.legals.beans.BailStatus;
import net.syscon.s4.inst.legals.beans.CourtCases;
import net.syscon.s4.inst.legals.beans.SentenceAdjustment;

@EliteController
public class OidsenadController {
	
	@Autowired
	private OidsenadService oidsenadService;
	
	private static Logger logger = LogManager.getLogger(OidsenadController.class.getName());
	/***
	 * fetch data for grid from offender_offenses table
	 * @param paramBean
	 * @return
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidsenad/populateSentAdjustment", method = RequestMethod.POST)
	public List<SentenceAdjustment> populateSentAdjustment(@RequestBody final CourtCases offenderCases) {
			List<SentenceAdjustment> result = new ArrayList<SentenceAdjustment>();
		try {			
				result = oidsenadService.populateSentAdjustment(offenderCases);
			}
		catch (Exception e) {
			logger.error("populateSentAdjustment", e);
		}
		return result;
	}
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidsenad/debitType", method = RequestMethod.GET)
	public List<BailStatus> populateDebitTypeData() {
			List<BailStatus> result = new ArrayList<BailStatus>();
		try {			
				result = oidsenadService.populateDebitTypeData();
			}
		catch (Exception e) {
			logger.error("populateDebitTypeData", e);
		}
		return result;
	}
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oidsenad/populateDebitCredit",method=RequestMethod.POST)
	public List<AdjustmentDetails> populateDebitCredit(@RequestBody SentenceAdjustment sentenceAdjustment)
	{
		List<AdjustmentDetails> result= new ArrayList<AdjustmentDetails>();
		try {
			result= oidsenadService.populateDebitCredit(sentenceAdjustment);
		}
		catch(Exception e) {
			logger.error("populateDebitCredit",e);
		}
		return result;
	}
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value="/oidsenad/insertDebitCreditRecord",method=RequestMethod.POST)
	public @ResponseBody Integer insertDebitCreditRecord(@RequestBody AdjustmentDetailsCommitBean adjustmentDetailsCommitBean) {
		int result=0 ;
			try {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				adjustmentDetailsCommitBean.setCreateUserId(userName);
				result = oidsenadService.insertDebitCreditRecord(adjustmentDetailsCommitBean);
				
			}catch(Exception e) {
				logger.error("insertDebitCreditRecord", e);
			}
		return result;
	}
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidsenad/populateAdjustTypeLov", method = RequestMethod.GET)
	public List<BailStatus> populateAdjustTypeLov() {
			List<BailStatus> result = new ArrayList<BailStatus>();
		try {			
				result = oidsenadService.populateAdjustTypeLov();
			}
		catch (Exception e) {
			logger.error("populateAdjustTypeLov", e);
		}
		return result;
	}
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oidsenad/populateAdjustData",method=RequestMethod.GET)
	public List<Adjustments> populateAdjustData(@RequestParam Long offenderBookId)
	{
		List<Adjustments> result= new ArrayList<Adjustments>();
		try {
			result= oidsenadService.populateAdjustData(offenderBookId);
		}
		catch(Exception e) {
			logger.error("populateAdjustData",e);
		}
		return result;
	}
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value="/oidsenad/updateAdjustData",method=RequestMethod.POST)
	public Integer updateAdjustData(@RequestBody AdjustCommitBean adjustBean){
	{
		Integer result=1;
		//Integer res=1;
		
		if(null != adjustBean.getDeleteList()&& adjustBean.getDeleteList().size() > 0){
			try{
				 oidsenadService.postInsertAdjustRecord(adjustBean.getDeleteList());
			}catch(Exception e){
				logger.error("postInsertAdjustRecord",e);
			}
		}
		
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			adjustBean.setCreateUserId(userName);
			result= oidsenadService.updateAdjustData(adjustBean);
		}
		catch(Exception e) {			
			logger.error("updateAdjustData",e);
		}
		if(result == 1){
		if(null != adjustBean.getInsertList()&& adjustBean.getInsertList().size() > 0){	
			try{
				 oidsenadService.postInsertAdjustRecord(adjustBean.getInsertList());
			}catch(Exception e){
				logger.error("postInsertAdjustRecord: ",e);
			}			
		}
		if(null != adjustBean.getUpdateList() && adjustBean.getUpdateList().size() > 0){
			try{
				 oidsenadService.postInsertAdjustRecord(adjustBean.getUpdateList());
			}catch(Exception e){
				logger.error("postInsertAdjustRecord: ",e);
			}
		  }		
		}
		return result;
	}	
	}
}
