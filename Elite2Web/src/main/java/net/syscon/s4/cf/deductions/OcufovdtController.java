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
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.cf.deductions.beans.FeeAccountProfiles;
import net.syscon.s4.cf.deductions.maintenance.beans.FeeOverrideDetails;
import net.syscon.s4.cf.deductions.maintenance.beans.FeeOverrideDetailsCommitBean;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;

/**
 * class OcufovdtController
 */
@EliteController
public class OcufovdtController {

    @Autowired
    private OcufovdtService ocufovdtService;
    /**
     * Logger object used to print the log in the file
     */
    private static Logger logger = LogManager.getLogger(OcufovdtController.class.getName());

    /**
     * getting cgfkOffDedDspDescription LOV values
     */
    @PreAuthorize("hasEliteRole('read')")
    @RequestMapping(value = "/ocufovdt/overrideTypeRecordGroup", method = RequestMethod.GET)
    public List<ReferenceCodes> overrideTypeRecordGroup() {
        List<ReferenceCodes> recordList = new ArrayList<>();
        try {
            recordList = ocufovdtService.overrideTypeRecordGroup();
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
    @RequestMapping(value = "/ocufovdt/feeActExecuteQuery", method = RequestMethod.POST)
    public FeeAccountProfiles feeActExecuteQuery(@RequestBody final FeeAccountProfiles searchBean) {
        FeeAccountProfiles searchResult = new FeeAccountProfiles();
        try {
            searchResult = ocufovdtService.feeActExecuteQuery(searchBean);
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
    @RequestMapping(value = "/ocufovdt/feeOverdDetExecuteQuery", method = RequestMethod.POST)
    public List<FeeOverrideDetails> feeOverdDetExecuteQuery(@RequestBody final FeeOverrideDetails searchBean) {
        List<FeeOverrideDetails> searchResult = new ArrayList<>();
        try {
            searchResult = ocufovdtService.feeOverdDetExecuteQuery(searchBean);
        } catch (final Exception e) {
            final FeeOverrideDetails bean = new FeeOverrideDetails();
            logger.error("Exception: ", e);
            bean.setErrorMessage(e.getMessage());
            searchResult.add(bean);
        }
        return searchResult;
    }

    /**
     * Perfomring basic Oracle form functions i.e. insert,delete, update int the
     * database table
     *
     * @param commitBean {@link FeeOverrideDetailsCommitBean}
     * @return a list of the FeeOverrideDetails {@link FeeOverrideDetailsCommitBean} for the matching passed data
     */
    @PreAuthorize("hasEliteRole('full')")
    @RequestMapping(value = "/ocufovdt/feeOverdDetCommit", method = RequestMethod.POST)
    public @ResponseBody
    Integer feeOverdDetCommit(@RequestBody final FeeOverrideDetailsCommitBean commitBean) {
        int liReturn = 0;
        try {
        	String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        	commitBean.setCreateUserId(userName);
            liReturn = ocufovdtService.feeOverdDetCommit(commitBean);
        } catch (final Exception e) {

            logger.error("feeOverdDetCommit : ", e);
        }
        return liReturn;
    }

    @PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/ocufovdt/sysPflExecuteQuery", method=RequestMethod.GET)
	public Integer sysPflExecuteQuery() {
		Integer returnValue = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			returnValue = ocufovdtService.sysPflExecuteQuery(userName);
		} catch (Exception e) {
			final SystemProfiles bean = new SystemProfiles();
			logger.error("Exception :",e);
			bean.setErrorMessage(e.getMessage());
			//searchResult.add(bean);
		}
		return returnValue;
	}
	
    @PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/ocufovdt/getAddedByName", method=RequestMethod.GET)
	public String getAddedByName() {
		String returnValue = null;
		try {
			returnValue = ocufovdtService.getAddedByName();
		} catch (Exception e) {
			final SystemProfiles bean = new SystemProfiles();
			logger.error("Exception :",e);
			bean.setErrorMessage(e.getMessage());
			//searchResult.add(bean);
		}
		return returnValue;
	}
	
    
    @PreAuthorize("hasEliteRole('read')")
    @RequestMapping(value = "/ocufovdt/feeOverCheckoverLapping", method = RequestMethod.POST)
    public FeeOverrideDetails feeOverCheckoverLapping(@RequestBody final FeeOverrideDetails searchBean) {
    	FeeOverrideDetails searchResult = new FeeOverrideDetails();
        try {
            searchResult = ocufovdtService.feeOverCheckoverLapping(searchBean);
        } catch (final Exception e) {
            final FeeOverrideDetails bean = new FeeOverrideDetails();
            logger.error("Exception: ", e);
            bean.setErrorMessage(e.getMessage());
        }
        return searchResult;
    }

    
}
