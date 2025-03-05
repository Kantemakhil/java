package net.syscon.s4.cf.deductions;

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

import net.syscon.s4.cf.deductions.beans.FeeAccountProfiles;
import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactions;
import net.syscon.s4.cf.deductions.beans.OffFeeBills;
import net.syscon.s4.cf.deductions.beans.OffFeeBillsCommitBean;
import net.syscon.s4.cf.deductions.maintenance.beans.FeeOverrideDetails;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;

/**
 * class OcufovdtController
 */
@EliteController
public class OcutrdetController {

    @Autowired
    private OcutrdetService ocutrdetService;
    /**
     * Logger object used to print the log in the file
     */
    private static Logger logger = LogManager.getLogger(OcutrdetController.class.getName());

    /**
     * getting cgfkOffDedDspDescription LOV values
     */
    @PreAuthorize("hasEliteRole('read')")
    @RequestMapping(value = "/ocutrdet/overrideTypeRecordGroup", method = RequestMethod.GET)
    public List<ReferenceCodes> overrideTypeRecordGroup() {
        List<ReferenceCodes> recordList = new ArrayList<>();
        try {
            recordList = ocutrdetService.overrideTypeRecordGroup();
        } catch (final Exception e) {
            final ReferenceCodes obj = new ReferenceCodes();
            logger.error("overrideTypeRecordGroup: ", e);
            obj.setErrorMessage(e.getMessage());
            recordList.add(obj);
        }
        return recordList;
    }

    /**
     * Fetching the record from database table
     *
     * @param searchBean {@link FeeAccountProfiles}
     * @return a list of the FeeAccountProfiles {@link FeeAccountProfiles} for the matching passed data
     */
    @PreAuthorize("hasEliteRole('read')")
    @RequestMapping(value = "/ocutrdet/feeActExecuteQuery", method = RequestMethod.POST)
    public FeeAccountProfiles feeActExecuteQuery(@RequestBody final FeeAccountProfiles searchBean) {
        FeeAccountProfiles searchResult = new FeeAccountProfiles();
        try {
            searchResult = ocutrdetService.feeActExecuteQuery(searchBean);
        } catch (final Exception e) {
            final FeeAccountProfiles bean = new FeeAccountProfiles();
            logger.error("Exception: ", e);
            bean.setErrorMessage(e.getMessage());
        }
        return searchResult;
    }

    /**
     * Fetching the record from database table
     *
     * @param searchBean {@link FeeOverrideDetails}
     * @return a list of the FeeOverrideDetails {@link FeeOverrideDetails} for the matching passed data
     */
    @PreAuthorize("hasEliteRole('read')")
    @RequestMapping(value = "/ocutrdet/billDetailsExecuteQuery", method = RequestMethod.POST)
    public List<OffFeeBills> billDetailsExecuteQuery(@RequestBody final OffFeeBills searchBean) {
        List<OffFeeBills> searchResult = new ArrayList<>();
        try {
            searchResult = ocutrdetService.billDetailsExecuteQuery(searchBean);
        } catch (final Exception e) {
            final OffFeeBills bean = new OffFeeBills();
            logger.error("Exception: ", e);
            bean.setErrorMessage(e.getMessage());
            searchResult.add(bean);
        }
        return searchResult;
    }

    
    @PreAuthorize("hasEliteRole('read')")
    @RequestMapping(value = "/ocutrdet/billTransDetailsExecuteQuery", method = RequestMethod.POST)
    public List<OffFeeBillTransactions> billTransDetailsExecuteQuery(@RequestBody final OffFeeBillTransactions searchBean) {
        List<OffFeeBillTransactions> searchResult = new ArrayList<>();
        try {
            searchResult = ocutrdetService.billTransDetailsExecuteQuery(searchBean);
        } catch (final Exception e) {
            final OffFeeBillTransactions bean = new OffFeeBillTransactions();
            logger.error("Exception: ", e);
            bean.setErrorMessage(e.getMessage());
            searchResult.add(bean);
        }
        return searchResult;
    }
    
    @PreAuthorize("hasEliteRole('read')")
    @RequestMapping(value = "/ocutrdet/billTransTotalExecuteQuery", method = RequestMethod.POST)
    public List<OffFeeBillTransactions> billTransTotalExecuteQuery(@RequestBody final OffFeeBillTransactions searchBean) {
        List<OffFeeBillTransactions> searchResult = new ArrayList<>();
        try {
            searchResult = ocutrdetService.billTransTotalExecuteQuery(searchBean);
        } catch (final Exception e) {
            final OffFeeBillTransactions bean = new OffFeeBillTransactions();
            logger.error("Exception: ", e);
            bean.setErrorMessage(e.getMessage());
            searchResult.add(bean);
        }
        return searchResult;
    }
   

    @PreAuthorize("hasEliteRole('read')")
    @RequestMapping(value = "/ocutrdet/updateBillTransactionDeails", method = RequestMethod.POST)
    public Integer updateBillTransactionDeails(@RequestBody final OffFeeBillsCommitBean bean) {
        Integer resultCode = 0;
        try {
        	resultCode = ocutrdetService.updateBillTransactionDeails(bean.getUpdateList());
        } catch (final Exception e) {
           
            logger.error("Exception: ", e);
            
        }
        return resultCode;
    }
	
    
    @PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/ocutrdet/sysPflBillAdjusExecuteQuery", method=RequestMethod.GET)
	public Integer sysPflBillAdjusExecuteQuery() {   	
    	Integer returnValue = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			returnValue = ocutrdetService.sysPflBillAdjusExecuteQuery(userName);
		} catch (Exception e) {
			final SystemProfiles bean = new SystemProfiles();
			logger.error("Exception :",e);
			bean.setErrorMessage(e.getMessage());
			//searchResult.add(bean);
		}
		return returnValue;
    			
	}
    
   
    
    @PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/ocutrdet/sysPflBillStatusExecuteQuery", method=RequestMethod.GET)
	public Integer sysPflBillStatusExecuteQuery() {   	
    	Integer returnValue = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			returnValue = ocutrdetService.sysPflBillStatusExecuteQuery(userName);
		} catch (Exception e) {
			final SystemProfiles bean = new SystemProfiles();
			logger.error("Exception :",e);
			bean.setErrorMessage(e.getMessage());
			//searchResult.add(bean);
		}
		return returnValue;
    			
	}
    
    
}
