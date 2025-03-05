package net.syscon.s4.sa.admin.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.MenuSecurities;
import net.syscon.s4.common.beans.MenuSecuritiesCommitBean;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.VMenuSecs;
import net.syscon.s4.sa.admin.OummenusRepository;
import net.syscon.s4.sa.admin.OummenusService;


/**
 * Class OummenusServiceImpl
 */
@Service
public class OummenusServiceImpl extends BaseBusiness implements OummenusService {

	@Autowired
	private OummenusRepository oummenusRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<VMenuSecs> vMenuSecsExecuteQuery(final VMenuSecs searchRecord) {
		return oummenusRepository.vMenuSecsExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstV_MENU_SECS
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer vMenuSecsCommit(final MenuSecuritiesCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (MenuSecurities bean : commitBean.getInsertList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
				bean.setModifyUserId(commitBean.getCreateUserId());
				if(bean.getModuleName()==null ||bean.getModuleName().isEmpty()) {
					bean.setModuleName(null);
				}
				if(bean.getModuleName()!=null) {					
					OmsModules omsModules = oummenusRepository.getOmsModule(bean.getModuleName());
					if (omsModules != null && omsModules.getDynamicForm() != null) {
							bean.setDynamicForm(omsModules.getDynamicForm());
					}
					oummenusRepository.descUpdateQuery(bean.getModuleDescription(),bean.getModifyUserId(), bean.getModuleName());
				}
			}
			liReturn = oummenusRepository.vMenuSecsInsertQuery(commitBean.getInsertList());

		}
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (MenuSecurities bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
				bean.setSealFlag(null);
				if (bean.getModuleName() != null) {
					oummenusRepository.descUpdateQuery(bean.getModuleDescription(),bean.getModifyUserId(), bean.getModuleName());
				}
			}
			liReturn = oummenusRepository.vMenuSecsUpdateQuery(commitBean.getUpdateList());

		}
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			for (MenuSecurities object : commitBean.getDeleteList()) {
				object.setModifyUserId(commitBean.getCreateUserId());
				Integer countParentMenu=oummenusRepository.getParentMenuExistCount(object.getMenuId());
				if(countParentMenu > 0) {
					return 2;
				}
				
			}
			liReturn = oummenusRepository.vMenuSecsDeleteQuery(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<OmsModules> rgMenuSecDescRecordGroup() {
		List<OmsModules> returnList = oummenusRepository.rgMenuSecDescRecordGroup();
		returnList.forEach(action -> {
			action.setModuleName(action.getDescription());
			action.setDescription(action.getCode());
		});
		return returnList;
	}

}