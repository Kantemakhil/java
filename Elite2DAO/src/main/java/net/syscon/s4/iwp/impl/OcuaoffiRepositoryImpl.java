package net.syscon.s4.iwp.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.casemanagement.beans.StaffMembersV2;
import net.syscon.s4.iwp.OcuaoffiRepository;

@Repository
public class OcuaoffiRepositoryImpl extends RepositoryBase implements OcuaoffiRepository {

	private final Map<String, FieldMapper> staffMembersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("BIRTHDATE", new FieldMapper("birthdate"))
			.put("LAST_NAME", new FieldMapper("lastName")).put("FIRST_NAME", new FieldMapper("firstName"))
			.put("SAC_STAFF_ID", new FieldMapper("sacStaffId")).put("SCHEDULE_TYPE", new FieldMapper("scheduleType"))
			.put("ROLE", new FieldMapper("role")).put("POSITION", new FieldMapper("position"))
			.put("DOMAIN", new FieldMapper("domain")).put("DESCRIPTION", new FieldMapper("description"))
			.put("MODE", new FieldMapper("mode")).build();

	public List<StaffMembersV2> addStaffExecuteQuery(final StaffMembersV2 staffMembers) {
		final String sql = getQuery("OCUAOFFI_ADDSTAFF_FIND_STAFF_MEMBERS_V2");
		final RowMapper<StaffMembersV2> rowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembersV2.class,
				staffMembersMapping);
		return namedParameterJdbcTemplate.query(sql,
				createParams("STAFF_ID", staffMembers.getSacStaffId(), "AGY_LOC_ID", staffMembers.getCalAgyLocId()),
				rowMapper);

	}
}
