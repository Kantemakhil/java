package net.syscon.s4.pkgs.lock_unlock_module.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.pkgs.lock_unlock_module.LockUnlockModuleRepository;

@Repository
public class LockUnlockModuleRepositoryImpl extends RepositoryBase implements LockUnlockModuleRepository {


	@Override
	public String getLSessionLockCur(final String pModuleName, final String pCaseload, final Integer pSessionId,
			final Integer pAccountCode) {
		final String sql = getQuery("L_SESSION_LOCK_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_MODULE_NAME", pModuleName,
				"P_CASELOAD_ID", pCaseload, "P_SESSION_ID", pSessionId, "P_ACCOUNT_CODE", pAccountCode), String.class);
	}

	@Override
	public Integer insertLockedModules(final String pModuleName, final String pCaseload, final String pUser,
			final BigDecimal pRootOffenderId, final Integer pSessionId, final Integer pAccountCode,
			final String userName) {
		final String sql = getQuery("INSERT_INTO_LOCKED_MODULES");
		return namedParameterJdbcTemplate.update(sql,
				createParams("P_MODULE_NAME", pModuleName, "P_CASELOAD", pCaseload, "P_USER", pUser,
						"P_ROOT_OFFENDER_ID", pRootOffenderId, "P_SESSION_ID", pSessionId, "P_ACCOUNT_CODE",
						pAccountCode, "createUserId", userName));
	}

}
