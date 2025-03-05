package net.syscon.s4.triggers.impl;

import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.StaffMembersT2Repository;

@Repository
public class StaffMembersT2RepositoryImpl extends RepositoryBase implements StaffMembersT2Repository {

	public Integer getProfileValueFromSystemProfiles() {
		final String sql = getQuery("GET_PROFILE_VALUE_FROM_SYSTEM_PROFILES");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);

	}

	public Integer getStaffIdProfileValueMod(final Long staffId, final Long profileVal) {
		final String sql = getQuery("GET_STAFF_ID_LV_PROFILE_VALUE_MOD");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("STAFF_ID", staffId, "LV_PROFILE_VALUE", profileVal), Integer.class);

	}

}
