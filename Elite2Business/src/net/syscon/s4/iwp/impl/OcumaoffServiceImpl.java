package net.syscon.s4.iwp.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.iwp.OcumaoffRepository;
import net.syscon.s4.iwp.OcumaoffService;
import net.syscon.s4.sa.usersystemsecurity.beans.StaffLocationRoles;
import net.syscon.s4.sa.usersystemsecurity.beans.StaffLocationRolesCommitBean;

/**
 * Class OcumaoffServiceImpl
 */
@Service
public class OcumaoffServiceImpl extends BaseBusiness implements OcumaoffService {

	@Autowired
	private OcumaoffRepository ocumaoffRepository;

	@Override
	public List<StaffLocationRoles> addOfficerExecuteQuery(final StaffLocationRoles searchBean) {
		return ocumaoffRepository.addOfficerExecuteQuery(searchBean);
	}

	@Override
	public Integer updateSupervosor(final StaffLocationRolesCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(bean->bean.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = ocumaoffRepository.updateSupervosor(commitBean.getUpdateList());
		}
		return liReturn;
	}

}