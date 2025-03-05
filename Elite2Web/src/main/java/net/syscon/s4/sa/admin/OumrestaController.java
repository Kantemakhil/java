package net.syscon.s4.sa.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.ModulePrivileges;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.sa.admin.beans.OmsModulesCommitBean;
import net.syscon.s4.sa.admin.beans.RoleInaccessibleRefCodes;
import net.syscon.s4.sa.admin.beans.RoleInaccessibleRefCodesCommitBean;

@EliteController
public class OumrestaController {
    @Autowired
    private OumrestaService oumrestaService;
    /**
     * Logger object used to print the log in the file
     */
    private final static Logger logger = LogManager.getLogger(OumrestaController.class.getName());

    /**
     * Fetching the record from database table
     *
     * @Param searchRecord
     */
    @PreAuthorize("hasEliteRole('read')")
    @RequestMapping(value = "/oumresta/rleInarcExecuteQuery", method = RequestMethod.POST)
    public List<OmsModules> rleInarcExecuteQuery(@RequestBody final OmsModules searchBean) {
        List<OmsModules> searchResult = new ArrayList<>();
        try {
            searchResult = oumrestaService.rleInarcExecuteQuery(searchBean);
        } catch (Exception e) {
            final OmsModules bean = new OmsModules();
            logger.error("Exception :", e);
            bean.setErrorMessage(e.getMessage());
            searchResult.add(bean);
        }
        return searchResult;
    }

    /**
     * getting cgfkRleInarcModuleName LOV values
     */
    @PreAuthorize("hasEliteRole('read')")
    @RequestMapping(value = "/oumresta/cgfkRleInarcModuleNameRecordGroup", method = RequestMethod.GET)
    public List<ReferenceCodes> cgfkRleInarcModuleNameRecordGroup() {
        List<ReferenceCodes> recordList = new ArrayList<>();
        try {
            recordList = oumrestaService.cgfkRleInarcModuleNameRecordGroup();
        } catch (Exception e) {
            final ReferenceCodes obj = new ReferenceCodes();
            logger.error("Exception :", e);
            obj.setErrorMessage(e.getMessage());
            recordList.add(obj);
        }
        return recordList;
    }

    /**
     * getting cgfkRleInarcModuleName LOV values
     */
    @PreAuthorize("hasEliteRole('read')")
    @RequestMapping(value = "/oumresta/cgfkRleInarc1DomainRecordGroup", method = RequestMethod.GET)
    public List<ReferenceCodes> cgfkRleInarc1DomainRecordGroup() {
        List<ReferenceCodes> recordList = new ArrayList<>();
        try {
            recordList = oumrestaService.cgfkRleInarc1DomainRecordGroup();
        } catch (Exception e) {
            final ReferenceCodes obj = new ReferenceCodes();
            logger.error("Exception :", e);
            obj.setErrorMessage(e.getMessage());
            recordList.add(obj);
        }
        return recordList;
    }

    /**
     * getting cgfkRleInarc1Code LOV values
     */
    @PreAuthorize("hasEliteRole('read')")
    @RequestMapping(value = "/oumresta/cgfkRleInarc1CodeRecordGroup", method = RequestMethod.GET)
    public List<ReferenceCodes> cgfkRleInarc1CodeRecordGroup(
            @RequestParam(value = "codeInput") final String codeInput) {
        List<ReferenceCodes> recordList = new ArrayList<>();
        try {
            recordList = oumrestaService.cgfkRleInarc1CodeRecordGroup(codeInput);
        } catch (Exception e) {
            final ReferenceCodes obj = new ReferenceCodes();
            logger.error("Exception :", e);
            obj.setErrorMessage(e.getMessage());
            recordList.add(obj);
        }
        return recordList;
    }

    /**
     * Perfomring basic Oracle form functions i.e. insert,delete, update int the
     * database table
     *
     * @Param commitBean
     */
    @PreAuthorize("hasEliteRole('full')")
    @RequestMapping(value = "/oumresta/rleInarcCommit", method = RequestMethod.POST)
    public @ResponseBody
    Integer rleInarcCommit(@RequestBody final OmsModulesCommitBean commitBean) {
        int liReturn = 0;
        String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
        try {
            liReturn = oumrestaService.rleInarcCommit(commitBean);
        } catch (Exception e) {

            logger.error("Exception :", e);
        }
        return liReturn;
    }

    /**
     * Fetching the record from database table
     *
     * @Param searchRecord
     */
    @PreAuthorize("hasEliteRole('read')")
    @RequestMapping(value = "/oumresta/modPrivExecuteQuery", method = RequestMethod.POST)
    public List<ModulePrivileges> modPrivExecuteQuery(@RequestBody final ModulePrivileges searchBean) {
        List<ModulePrivileges> searchResult = new ArrayList<>();
        try {
            searchResult = oumrestaService.modPrivExecuteQuery(searchBean);
        } catch (Exception e) {
            final ModulePrivileges bean = new ModulePrivileges();
            logger.error("Exception :", e);
            bean.setErrorMessage(e.getMessage());
            searchResult.add(bean);
        }
        return searchResult;
    }

    /**
     * Fetching the record from database table
     *
     * @Param searchRecord
     */
    @PreAuthorize("hasEliteRole('read')")
    @RequestMapping(value = "/oumresta/rleInarcRircExecuteQuery", method = RequestMethod.POST)
    public List<RoleInaccessibleRefCodes> rleInarcRircExecuteQuery(
            @RequestBody final RoleInaccessibleRefCodes searchBean) {
        List<RoleInaccessibleRefCodes> searchResult = new ArrayList<>();
        try {
            searchResult = oumrestaService.rleInarcRircExecuteQuery(searchBean);
        } catch (Exception e) {
            final RoleInaccessibleRefCodes bean = new RoleInaccessibleRefCodes();
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
    @RequestMapping(value = "/oumresta/rleInarcRircCommit", method = RequestMethod.POST)
    public @ResponseBody
    Integer rleInarcRircCommit(@RequestBody final RoleInaccessibleRefCodesCommitBean commitBean) {
        int liReturn = 0;
        String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
        try {
            liReturn = oumrestaService.rleInarcRircCommit(commitBean);
        } catch (Exception e) {

            logger.error("Exception :", e);
            if (e instanceof DuplicateKeyException && e.getMessage().toUpperCase().contains("ROLE_INACCESSIBLE_REF_CODES_PK")) {
                liReturn = 2;
            }

        }
        return liReturn;
    }

}