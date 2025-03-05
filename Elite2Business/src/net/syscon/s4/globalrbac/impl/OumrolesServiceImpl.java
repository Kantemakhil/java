package net.syscon.s4.globalrbac.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.globalrbac.OumrolesRepository;
import net.syscon.s4.globalrbac.OumrolesService;
import net.syscon.s4.im.beans.OmsRoles;
import net.syscon.s4.im.beans.OmsRolesCommitBean;

/**
 * Class OumrolesServiceImpl
 */
@Service
public class OumrolesServiceImpl extends BaseBusiness implements OumrolesService {

	@Autowired
	private OumrolesRepository oumrolesRepository;

	/**
	 * Creates new OumrolesServiceImpl class Object
	 */
	public OumrolesServiceImpl() {
		
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public Object omsRolePreInsert() {
		final Object object = oumrolesRepository.omsRolePreInsert();
		return object;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<OmsRoles> omsRoleExecuteQuery(OmsRoles searchRecord) {
		return oumrolesRepository.omsRoleExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOMS_ROLE
	 *
	 * @
	 */
	@Transactional
	public Integer omsRoleCommit(OmsRolesCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (OmsRoles obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oumrolesRepository.omsRoleInsertOmsRoles(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (OmsRoles obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oumrolesRepository.omsRoleUpdateOmsRoles(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (OmsRoles obj : commitBean.getDeleteList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oumrolesRepository.omsRoleDeleteOmsRoles(commitBean.getDeleteList());
		}
		return liReturn;
	}

}