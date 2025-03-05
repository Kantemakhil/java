package net.syscon.s4.pkgs.iwp_10g.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.pkgs.iwp_10g.Iwp10gRepository;
import net.syscon.s4.sa.admin.beans.IwpTemplateRoles;

@Repository
public class Iwp10gRepositoryImpl extends RepositoryBase implements Iwp10gRepository {

	private static Logger logger = LogManager.getLogger(Iwp10gRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> mapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("TEMPLATE_ID", new FieldMapper("templateId")).put("ROLE_CODE", new FieldMapper("roleCode"))
			.put("ROLE_NAME", new FieldMapper("roleName")).build();
	// This PROCEDURE is used to get_templ_roles from database
	@Override
	public List<IwpTemplateRoles> getTempRolesDetails(final Long tempid, final String tempRoleCode) {
		final String sql = getQuery("GET_TEMP_ROLES_ONE");
		List<IwpTemplateRoles> iwptemp = new ArrayList<>();
		final RowMapper<IwpTemplateRoles> mRowMapper = Row2BeanRowMapper.makeMapping(sql, IwpTemplateRoles.class,
				mapping);
		try {
			iwptemp = namedParameterJdbcTemplate.query(sql,
					createParams("TEMPID", tempid, "TEMP_ROLE_CODE", tempRoleCode), mRowMapper);
		} catch (Exception e) {
			logger.error("getTempRolesDetails :", e);
		}
		return iwptemp;
	}
	// This PROCEDURE is used to get_templ_roles from database
	@Override
	public List<IwpTemplateRoles> getTempRolesDetailsSecond(final Long tempid) {
		final String sql = getQuery("GET_TEMP_ROLES_SECOND");
		List<IwpTemplateRoles> iwptemp = new ArrayList<>();
		final RowMapper<IwpTemplateRoles> mRowMapper = Row2BeanRowMapper.makeMapping(sql, IwpTemplateRoles.class,
				mapping);
		try {
			iwptemp = namedParameterJdbcTemplate.query(sql, createParams("TEMPID", tempid), mRowMapper);
		} catch (Exception e) {
			logger.error("getTempRolesDetailsSecond :", e);
		}
		return iwptemp;
	}

}
