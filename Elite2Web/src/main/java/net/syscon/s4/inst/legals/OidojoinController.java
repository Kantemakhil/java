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
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.inst.legals.beans.BailStatus;
import net.syscon.s4.inst.legals.beans.CourtCases;
import net.syscon.s4.inst.legals.beans.PriorHistory;
import net.syscon.s4.inst.legals.beans.PriorHistoryCommitBean;
@EliteController
public class OidojoinController {
		@Autowired
		private OidojoinService oidojoinService;
		
		private static Logger logger = LogManager.getLogger(OidojoinController.class.getName());
		
		/***
		 * fetch data for grid from offender_offenses table
		 * @param paramBean
		 * @return
		 */
		@PreAuthorize("hasEliteRole('read')")
		@RequestMapping(value = "/oidojoin/populateGridData", method = RequestMethod.POST)
		public List<PriorHistory> populateGridData(@RequestBody final CourtCases offenderCases) {
				List<PriorHistory> Result = new ArrayList<PriorHistory>();
			try {			
					Result = oidojoinService.populateGridData(offenderCases);
				}
			catch (Exception e) {
				logger.error("populateGridData", e);
			}
			return Result;
		}
		/***
		 * method written for PriorHistoryBlock fetching order Types in PriorHistoryBlock
		 */
		 @PreAuthorize("hasEliteRole('read')")
		@RequestMapping(value = "/oidojoin/orderType", method = RequestMethod.GET)
		public List<BailStatus> populateOrderType() {
			List<BailStatus> Result = new ArrayList<BailStatus>();
			try {
				Result = oidojoinService.populateOrderType();
				
			} catch (Exception e) {
				logger.error("populateOrderType", e);
			}
			return Result;
		}
		/***
		 * method written for status fetching status in PriorHistoryBlock
		 */
	    @PreAuthorize("hasEliteRole('read')")
		@RequestMapping(value = "/oidojoin/status", method = RequestMethod.GET)
		public List<BailStatus> populateStatus() {
			List<BailStatus> Result = new ArrayList<BailStatus>();
			try {
				Result = oidojoinService.populateStatus();
				
			} catch (Exception e) {
				logger.error("populateStatus", e);
			}
			return Result;
		}
		/***
		 * method written for status fetching source in PriorHistoryBlock
		 */
		 @PreAuthorize("hasEliteRole('read')")
		@RequestMapping(value = "/oidojoin/source", method = RequestMethod.GET)
		public List<BailStatus> populateSource() {
			List<BailStatus> Result = new ArrayList<BailStatus>();
			try {
				Result = oidojoinService.populateSource();
				
			} catch (Exception e) {
				logger.error("populateSource", e);
			}
			return Result;
		}
		/***
		 * method written for status fetching county in PriorHistoryBlock
		 */
		 @PreAuthorize("hasEliteRole('read')")
		@RequestMapping(value = "/oidojoin/county", method = RequestMethod.GET)
		public List<BailStatus> populateCounty() {
			List<BailStatus> Result = new ArrayList<BailStatus>();
			try {
				Result = oidojoinService.populateCounty();
				
			} catch (Exception e) {
				logger.error("populateCounty", e);
			}
			return Result;
		}
		/***
		 * method written for status fetching state in PriorHistoryBlock
		 */
		 @PreAuthorize("hasEliteRole('read')")
		@RequestMapping(value = "/oidojoin/state", method = RequestMethod.GET)
		public List<BailStatus> populateState() {
			List<BailStatus> Result = new ArrayList<BailStatus>();
			try {
				Result = oidojoinService.populateState();
				
			} catch (Exception e) {
				logger.error("populateState", e);
			}
			return Result;
		}
		/**
		 * To Insert the new court case
		 * @param courtCaseCommit
		 * @return
		 */
		 @PreAuthorize("hasEliteRole('full')")
		@RequestMapping(value = "/oidojoin/newGridRecord", method = RequestMethod.POST)
		public @ResponseBody Integer newGridRecord(@RequestBody PriorHistoryCommitBean priorHistoryCommit) {
			int result=0 ;
				try {
					String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
					priorHistoryCommit.setCreateUserId(userName);
					result = oidojoinService.insertNewRecord(priorHistoryCommit);
					
				}catch(Exception e) {
					logger.error("newRecord", e);
				}
			return result;
		}
		
}
