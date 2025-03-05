package net.syscon.s4.sa.admin.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.ModulePrivileges;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.OmsRoles;
import net.syscon.s4.sa.admin.OumrestaRepository;
import net.syscon.s4.sa.admin.beans.RoleInaccessibleRefCodes;

@Repository
public class OumrestaRepositoryImpl extends RepositoryBase implements OumrestaRepository {

    private static Logger logger = LogManager.getLogger(OumrestaRepositoryImpl.class.getName());

    private final Map<String, FieldMapper> modPrevMap = new ImmutableMap.Builder<String, FieldMapper>()
            .put("MODULE_NAME", new FieldMapper("moduleName"))
            .build();
    private final Map<String, FieldMapper> refMap = new ImmutableMap.Builder<String, FieldMapper>()
            .put("DOMAIN", new FieldMapper("domain"))
            .put("CODE", new FieldMapper("code"))
            .put("DESCRIPTION", new FieldMapper("description"))
            .put("'1'", new FieldMapper("  '1' "))
            .build();
    private final Map<String, FieldMapper> omsMap = new ImmutableMap.Builder<String, FieldMapper>()
            .put("DESCRIPTION", new FieldMapper("description"))
            .put("MODULE_NAME", new FieldMapper("moduleName"))
            .build();
    private final Map<String, FieldMapper> rolMap = new ImmutableMap.Builder<String, FieldMapper>()
            .put("ROLE_ID", new FieldMapper("roleId"))
            .build();
    private final Map<String, FieldMapper> omsRoleMap = new ImmutableMap.Builder<String, FieldMapper>()
            .put("ROLE_ID", new FieldMapper("roleId"))
            .put("ROLE_NAME", new FieldMapper("roleName"))
            .build();
    private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
            .put("CODE", new FieldMapper("code"))
            .put("NEWCODE", new FieldMapper("newCode"))
            .put("MODULE_NAME", new FieldMapper("moduleName"))
            .put("DOMAIN", new FieldMapper("domain"))
            .build();

    /**
     * Creates new OumrestaRepositoryImpl class Object
     */
    public OumrestaRepositoryImpl() {
    }

    /**
     * Fetch the records from database table
     *
     * @param objSearchDao OmsModules
     * @return List<OmsModules>
     * @throws SQLException
     */
    @Override
    public List<OmsModules> rleInarcExecuteQuery(final OmsModules objSearchDao) {
        final String sql = getQuery("OUMRESTA_RLEINARC_FIND_OMS_MODULES");
        final RowMapper<OmsModules> rowMap = Row2BeanRowMapper.makeMapping(sql, OmsModules.class, omsMap);
        return namedParameterJdbcTemplate.query(sql,
                createParams(), rowMap);
    }

    /**
     * This method is used to insert the records in the data base tables based on
     *
     * @param lstOmsModules List<OmsModules>
     * @return List<Integer>
     * @throws SQLException
     */
    @Override
    public Integer rleInarcInsertOmsModules(final List<OmsModules> lstOmsModules) {
        final String sql = getQuery("OUMRESTA_RLEINARC_INSERT_OMS_MODULES");
        int[] returnArray;
        final List<SqlParameterSource> parameters = new ArrayList<>();
        for (final OmsModules obj : lstOmsModules) {
            parameters.add(new BeanPropertySqlParameterSource(obj));
        }
        returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

        if (lstOmsModules.size() == returnArray.length) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * This method is used to update the data base tables based on
     *
     * @param lstOmsModules List<OmsModules>
     * @throws SQLException
     */
    @Override
    public Integer rleInarcUpdateOmsModules(final List<OmsModules> lstOmsModules) {
        final String sql = getQuery("OUMRESTA_RLEINARC_UPDATE_OMS_MODULES");
        int[] returnArray;
        final List<SqlParameterSource> parameters = new ArrayList<>();
        for (final OmsModules omsModules : lstOmsModules) {
            parameters.add(new BeanPropertySqlParameterSource(omsModules));
        }
        returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
        if (lstOmsModules.size() == returnArray.length) {
            return 1;
        } else {
            return 0;
        }

    }

    /**
     * Fetch the records from database table
     *
     * @param objSearchDao ModulePrivileges
     * @return List<ModulePrivileges>
     * @throws SQLException
     */
    @Override
    public List<ModulePrivileges> modPrivExecuteQuery(final ModulePrivileges objSearchDao) {
        final String sql = getQuery("OUMRESTA_MODPRIV_FIND_MODULE_PRIVILEGES");
        final RowMapper<ModulePrivileges> rowMap = Row2BeanRowMapper.makeMapping(sql,
                ModulePrivileges.class, modPrevMap);
        return namedParameterJdbcTemplate
                .query(sql, createParams("moduleName", objSearchDao.getModuleName()), rowMap);
    }

    /**
     * Fetch the records from database table
     *
     * @param objSearchDao RoleInaccessibleRefCodes
     * @return List<RoleInaccessibleRefCodes>
     * @throws SQLException
     */
    @Override
    public List<RoleInaccessibleRefCodes> rleInarcRircExecuteQuery(final RoleInaccessibleRefCodes objSearchDao) {
        final String sql = getQuery("OUMRESTA_RLEINARC1_FIND_ROLE_INACCESSIBLE_REF_CODES");
        final RowMapper<RoleInaccessibleRefCodes> rowMap = Row2BeanRowMapper.makeMapping(sql,
                RoleInaccessibleRefCodes.class, rolMap);
        return namedParameterJdbcTemplate.query(sql, createParams("moduleName", objSearchDao.getModuleName(), "roleId", objSearchDao.getRoleId()), rowMap);
    }

    /**
     * This method is used to insert the records in the data base tables based on
     *
     * @param lstRoleref List<RoleInaccessibleRefCodes>
     * @return List<Integer>
     * @throws SQLException
     */
    @Override
    public Integer rleInarc1InsertRoleInaccessibleRefCodes(final List<RoleInaccessibleRefCodes> lstRoleref) {
        final String sql = getQuery("OUMRESTA_RLEINARC1_INSERT_ROLE_INACCESSIBLE_REF_CODES");
        int[] returnArray;
        final List<SqlParameterSource> parameters = new ArrayList<>();
        for (final RoleInaccessibleRefCodes obj : lstRoleref) {
            parameters.add(new BeanPropertySqlParameterSource(obj));
        }
        returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
        if (lstRoleref.size() == returnArray.length) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * This method is used to delete records from data base tables based on
     *
     * @param lstRoleref List<RoleInaccessibleRefCodes>
     * @throws SQLException
     */
    @Override
    public Integer rleInarc1DeleteRoleInaccessibleRefCodes(
            final List<RoleInaccessibleRefCodes> lstRoleref) {
        final String sql = getQuery("OUMRESTA_RLEINARC1_DELETE_ROLE_INACCESSIBLE_REF_CODES");
        int[] returnArray;
        final List<SqlParameterSource> parameters = new ArrayList<>();
        for (final RoleInaccessibleRefCodes roleInaref : lstRoleref) {
            parameters.add(new BeanPropertySqlParameterSource(roleInaref));
        }
        try {
			String tableName = "ROLE_INACCESSIBLE_REF_CODES";
			String whereClause = "ROLE_ID = :roleId and MODULE_NAME = :moduleName and CODE = :code and DOMAIN = :domain";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method rleInarc1DeleteRoleInaccessibleRefCodes", e);
		}
        returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
        if (lstRoleref.size() == returnArray.length) {
            return 1;
        } else {
            return 0;
        }

    }

    @Override
    public String getRoleName(final Long roleId) {
        final String sql = getQuery("OUMRESTA_MOD_PRIV_POSTQUERY");
        String data = null;
        try {
            data = namedParameterJdbcTemplate.queryForObject(sql, createParams("ROLEID", roleId), String.class);
        } catch (Exception e) {
            return data;
        }
        return data;
    }

    /**
     * Used to capture results from select query
     *
     * @return List<M>
     */
    @Override
    public List<ReferenceCodes> cgfkRleInarcModuleNameRecordGroup() {
        final String sql = getQuery("OUMRESTA_FIND_CGFKRLEINARCMODULENAME");
        final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

        try {
            return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
        } catch (EmptyResultDataAccessException e) {
            logger.error("Exception :", e);
            return Collections.emptyList();
        }
    }

    /**
     * Used to capture results from select query
     *
     * @return List<M>
     */
    @Override
    public List<ReferenceCodes> cgfkRleInarc1DomainRecordGroup() {
        final String sql = getQuery("OUMRESTA_FIND_CGFKRLEINARC1DOMAIN_NEW");
        final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

        try {
            return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
        } catch (EmptyResultDataAccessException e) {
            logger.error("Exception :", e);
            return Collections.emptyList();
        }
    }

    /**
     * Used to capture results from select query
     *
     * @return List<M>
     */
    @Override
    public List<ReferenceCodes> cgfkRleInarc1CodeRecordGroup(final String codeInput) {
        final String sql = getQuery("OUMRESTA_FIND_CGFKRLEINARC1CODE");
        final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

        try {
            return namedParameterJdbcTemplate.query(sql, createParams("DOMAIN", codeInput), mRowMapper);
        } catch (EmptyResultDataAccessException e) {
            logger.error("Exception :", e);
            return Collections.emptyList();
        }
    }

    /**
     * This method is execute a sql query when trigger event is called
     * <p>
     * rleInarcOnCheckDeleteMaster
     *
     * @param paramBean
     */
    @Override
    public List<ModulePrivileges> rleInarcOnCheckDeleteMaster(final ModulePrivileges paramBean) {
        final String sql = getQuery("OUMRESTA_RLE_INARC_ONCHECKDELETEMASTER");
        final RowMapper<ModulePrivileges> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ModulePrivileges.class,
                modPrevMap);
        return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
    }

    /**
     * This method is execute a sql query when trigger event is called
     * <p>
     * createFormGlobalsCREATE_FORM_GLOBALS
     *
     * @param paramBean
     */
    @Override
    public List<OmsModules> createFormGlobalsCREATE_FORM_GLOBALS(final OmsModules paramBean) {
        final String sql = getQuery("OUMRESTA_CREATE_FORM_GLOBALS_CREATE_FORM_GLOBALS");
        final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class, omsMap);
        return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
    }

    /**
     * This method is execute a sql query when trigger event is called
     * <p>
     * cgfkchkRleInarcRleInarc
     *
     * @param paramBean
     */
    @Override
    public List<OmsModules> cgfkchkRleInarcRleInarc(final OmsModules paramBean) {
        final String sql = getQuery("OUMRESTA_CGFKCHK_RLE_INARC_RLE_INARC_O");
        final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class, omsMap);
        return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
    }

    /**
     * This method is execute a sql query when trigger event is called
     * <p>
     * cgfkchkModPrivModPrivOms
     *
     * @param paramBean
     */
    @Override
    public List<OmsRoles> cgfkchkModPrivModPrivOms(final OmsRoles paramBean) {
        final String sql = getQuery("OUMRESTA_CGFKCHK_MOD_PRIV_MOD_PRIV_OMS");
        final RowMapper<OmsRoles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsRoles.class, omsRoleMap);
        return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
    }

    /**
     * This method is execute a sql query when trigger event is called
     * <p>
     * cgfkchkRleInarc1RleInarc
     *
     * @param paramBean
     */
    @Override
    public List<ReferenceCodes> cgfkchkRleInarc1RleInarc(final ReferenceCodes paramBean) {
        final String sql = getQuery("OUMRESTA_CGFKCHK_RLE_INARC1_RLE_INARC_");
        final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
                refMap);
        return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
    }

    /**
     * This method is execute a sql query when trigger event is called
     * <p>
     * cgfkchkRleInarc1RleIna2
     *
     * @param paramBean
     */
    @Override
    public List<ReferenceCodes> cgfkchkRleInarc1RleIna2(final ReferenceCodes paramBean) {
        final String sql = getQuery("OUMRESTA_CGFKCHK_RLE_INARC1_RLE_INA2");
        final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
                refMap);
        return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
    }
    @Override
    public Date getCurrentDate() {
    	final String sql = getQuery("GET_CURRENT_DATE");
    	return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Date.class);
    }

}
