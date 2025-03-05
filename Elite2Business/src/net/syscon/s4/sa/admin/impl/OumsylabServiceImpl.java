package net.syscon.s4.sa.admin.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.ModulePrivileges;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.SystemLables;
import net.syscon.s4.sa.admin.OumrestaRepository;
import net.syscon.s4.sa.admin.OumsylabRepository;
import net.syscon.s4.sa.admin.OumsylabService;
import net.syscon.s4.sa.admin.beans.OmsModulesCommitBean;
import net.syscon.s4.sa.admin.beans.RoleInaccessibleRefCodes;
import net.syscon.s4.sa.admin.beans.RoleInaccessibleRefCodesCommitBean;

/**
 * Class OumrestaServiceImpl
 */
@Service
public class OumsylabServiceImpl extends BaseBusiness implements OumsylabService {

    public OumsylabServiceImpl() {
    }

    @Autowired
    private OumrestaRepository oumrestaRepository;
    
    @Autowired
    private OumsylabRepository oumsylabRepository;

    /**
     * Fetch the records from database table
     *
     * @param searchRecord
     * @throws SQLException
     */
    @Override
    public List<OmsModules> rleInarcExecuteQuery(final OmsModules searchRecord) {
        		List<OmsModules> omsModules= null;
        		omsModules=oumsylabRepository.rleInarcExecuteQuery(searchRecord);
        		OmsModules omsmod= new OmsModules();
        		OmsModules omsmod1= new OmsModules();
        		OmsModules omsmod2= new OmsModules();
        		omsmod.setModuleName("COMMON");
        		omsmod.setDescription("Common Labels");
        		omsmod.setModuleType("SCREEN");
        		omsmod1.setModuleName("PROFILE");
        		omsmod1.setDescription("System Profile");
        		omsmod1.setModuleType("SCREEN");
        		omsmod2.setModuleName("LOGIN");
        		omsmod2.setDescription("Login Properties");
        		omsmod2.setModuleType("SCREEN");
        		omsModules.add(omsmod);
        		omsModules.add(omsmod1);
        		omsModules.add(omsmod2);
        		return omsModules;
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
            liReturn = oumrestaRepository.rleInarcInsertOmsModules(commitBean.getInsertList());
        }
        if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
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
            liReturn = oumrestaRepository.rleInarc1InsertRoleInaccessibleRefCodes(commitBean.getInsertList());

        }
        if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
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

	@Override
	public List<SystemLables> labelExecuteQuery(String codeInput) {
		return oumsylabRepository.labelExecuteQuery(codeInput);
	}
	
	@Override
	public List<SystemLables> labelByKeyExecuteQuery(String codeInput) {
		return oumsylabRepository.labelByKeyExecuteQuery(codeInput);
	}

	@Override
	public int updateSystemlabel(List<SystemLables> updateList) {
		return oumsylabRepository.updateSystemlabel(updateList);
	}

	@Override
	public List<SystemLables> getPropertiesFromDb() {
		return oumsylabRepository.getPropertiesFromDb();
	}
	
	@Override
	public Integer setSystemLables(SystemLables systemlable) {
		return oumsylabRepository.setSystemLables(systemlable);
	}

	@Override
	public List<SystemProfiles> getSystemProfiles() {
		return oumsylabRepository.getSystemProfiles();
	}

	@Override
	public Integer setSystemProfileToSystemlabels(SystemLables systemLablesTemp) {
		return oumsylabRepository.setSystemProfileToSystemlabels(systemLablesTemp);
	}

	@Override
	public Integer countOfLabel() {
		 return oumsylabRepository.countOfLabel();
	}

	@Override
	public Integer countOfProfile() {
		return oumsylabRepository.countOfProfile();
	}

	@Override
	public List<SystemLables> getPropertiesFromDb(int startIndex, int endIndex) {
		return oumsylabRepository.getSystemLabel(startIndex, endIndex);
	}

}