package net.syscon.s4.pkgs.oms_ir.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.pkgs.oms_ir.OmsIrRepository;

@Repository
public class OmsIrRepositoryImpl extends RepositoryBase implements OmsIrRepository {

	final private static Logger logger = LogManager.getLogger(OmsIrRepositoryImpl.class.getName());

	@Override
	public Boolean ChangeUserPassword(final String userName, final String passWord, final String newPassword,String loggedUserName) {
			String preparedSql = getQuery("OMSLR_CHANGE_USER_PASSWORD");
		int ret = 0;
		if (userName != null) {
			try {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("password", newPassword);
				paramMap.put("userName", userName);
				paramMap.put("modifyUserId", loggedUserName);
				ret = namedParameterJdbcTemplate.update(preparedSql, paramMap);
			} catch (Exception e) {
				logger.error("ChangeUserPassword"+e);
			}
		}
		if (ret == 1) {
			return true;
		}
		return false;
	}

}