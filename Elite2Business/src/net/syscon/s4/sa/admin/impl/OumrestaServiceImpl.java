package net.syscon.s4.sa.admin.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.ModulePrivileges;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.sa.admin.OumrestaRepository;
import net.syscon.s4.sa.admin.OumrestaService;
import net.syscon.s4.sa.admin.beans.OmsModulesCommitBean;
import net.syscon.s4.sa.admin.beans.RoleInaccessibleRefCodes;
import net.syscon.s4.sa.admin.beans.RoleInaccessibleRefCodesCommitBean;

/**
 * Class OumrestaServiceImpl
 */
@Service
public class OumrestaServiceImpl extends BaseBusiness implements OumrestaService {

    public OumrestaServiceImpl() {
    }

    @Autowired
    private OumrestaRepository oumrestaRepository;

    /**
     * Fetch the records from database table
     *
     * @param searchRecord
     * @throws SQLException
     */
    @Override
    public List<OmsModules> rleInarcExecuteQuery(final OmsModules searchRecord) {
        return oumrestaRepository.rleInarcExecuteQuery(searchRecord);

    }

    /**
     * Insert the records from database table
     *
     * @param commitBean
     * @throws SQLException
     */
    @Transactional
    @Override
    public Integer rleInarcCommit(final OmsModulesCommitBean commitBean) {
        int liReturn = 0;
        
        if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
        	for (OmsModules obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
            liReturn = oumrestaRepository.rleInarcInsertOmsModules(commitBean.getInsertList());
        }
        if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
        	for (OmsModules obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
            liReturn = oumrestaRepository.rleInarcUpdateOmsModules(commitBean.getUpdateList());
        }
        return liReturn;
    }

    /**
     * Fetch the records from database table
     *
     * @param searchRecord
     * @throws SQLException
     */
    @Override
    public List<ModulePrivileges> modPrivExecuteQuery(final ModulePrivileges searchRecord) {
        final List<ModulePrivileges> liList = oumrestaRepository.modPrivExecuteQuery(searchRecord);
        liList.forEach(obj -> {
            final String roleName = oumrestaRepository.getRoleName(obj.getRoleId());
            if (roleName != null) {
                obj.setDspRoleName(roleName);
            }
        });
        return liList;

    }

    /**
     * Fetch the records from database table
     *
     * @param searchRecord
     * @throws SQLException
     */
    @Override
    public List<RoleInaccessibleRefCodes> rleInarcRircExecuteQuery(final RoleInaccessibleRefCodes searchRecord) {
        return oumrestaRepository.rleInarcRircExecuteQuery(searchRecord);

    }

    /**
     * Insert the records from database table
     *
     * @param commitBean
     * @throws SQLException
     */
    @Transactional
    @Override
    public Integer rleInarcRircCommit(final RoleInaccessibleRefCodesCommitBean commitBean) {
        int liReturn = 0;
        if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
        	for (RoleInaccessibleRefCodes obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
            liReturn = oumrestaRepository.rleInarc1InsertRoleInaccessibleRefCodes(commitBean.getInsertList());
        }
        if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
        	commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
            liReturn = oumrestaRepository.rleInarc1DeleteRoleInaccessibleRefCodes(commitBean.getDeleteList());
        }
        return liReturn;
    }

    /**
     * This method is used to execute a record group
     *
     * @throws SQLException
     */
    @Override
    public List<ReferenceCodes> cgfkRleInarcModuleNameRecordGroup() {
        return oumrestaRepository.cgfkRleInarcModuleNameRecordGroup();

    }

    /**
     * This method is used to execute a record group
     *
     * @throws SQLException
     */
    @Override
    public List<ReferenceCodes> cgfkRleInarc1DomainRecordGroup() {
        return oumrestaRepository.cgfkRleInarc1DomainRecordGroup();

    }

    /**
     * This method is used to execute a record group
     *
     * @throws SQLException
     */
    @Override
    public List<ReferenceCodes> cgfkRleInarc1CodeRecordGroup(final String codeInput) {
        return oumrestaRepository.cgfkRleInarc1CodeRecordGroup(codeInput);

    }

}