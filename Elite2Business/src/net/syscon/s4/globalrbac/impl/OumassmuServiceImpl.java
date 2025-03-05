package net.syscon.s4.globalrbac.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.globalrbac.OumassmuRepository;
import net.syscon.s4.globalrbac.OumassmuService;
import net.syscon.s4.im.beans.ModulePrivileges;
import net.syscon.s4.im.beans.ModulePrivilegesCommitBean;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.OmsRoles;
import net.syscon.s4.im.beans.OmsRolesCommitBean;

/**
 * Class OumassmuServiceImpl
 */
@Service
public class OumassmuServiceImpl extends BaseBusiness implements OumassmuService {

	@Autowired
	private OumassmuRepository oumassmuRepository;

	/**
	 * Creates new OumassmuServiceImpl class Object
	 */
	public OumassmuServiceImpl() {
	}


	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public OmsModules cgfkchkModPrivModPrivOms(final OmsModules paramBean) {
		final OmsModules omsModules = oumassmuRepository.cgfkchkModPrivModPrivOms(paramBean);
		return omsModules;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public ModulePrivileges cguvchkModulePrivsPk(final ModulePrivileges paramBean) {
		final ModulePrivileges modulePrivileges = oumassmuRepository.cguvchkModulePrivsPk(paramBean);
		return modulePrivileges;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<Object> cgwhenNewFormInstance() {
		return null;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<OmsRoles> omsRoleExecuteQuery(final OmsRoles searchRecord) {
		return oumassmuRepository.omsRoleExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOMS_ROLE
	 *
	 * @
	 */
	@Transactional
	public Integer omsRoleCommit(final OmsRolesCommitBean commitBean) {
		final int liReturn = 0;
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<ModulePrivileges> modPrivExecuteQuery(final ModulePrivileges searchRecord) {
		return oumassmuRepository.modPrivExecuteQuery(searchRecord);
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstMOD_PRIV
	 *
	 * @
	 */
	@Transactional
	public Integer modPrivCommit(final ModulePrivilegesCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (ModulePrivileges obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oumassmuRepository.modPrivInsertModulePrivileges(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (ModulePrivileges obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oumassmuRepository.modPrivUpdateModulePrivileges(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (ModulePrivileges obj : commitBean.getDeleteList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oumassmuRepository.modPrivDeleteModulePrivileges(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<OmsRoles> rgStaffMemberRolesRoleRecordGroup() {
		return oumassmuRepository.rgStaffMemberRolesRoleRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> cgfkModPrivAccessPrivilegeRecordGroup() {
		return oumassmuRepository.cgfkModPrivAccessPrivilegeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<OmsModules> cgfkModPrivModuleNameRecordGroup() {
		return oumassmuRepository.cgfkModPrivModuleNameRecordGroup();

	}

}


