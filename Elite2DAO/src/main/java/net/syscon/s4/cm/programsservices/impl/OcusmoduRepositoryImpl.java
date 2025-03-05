package net.syscon.s4.cm.programsservices.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.programsservices.OcusmoduRepository;
import net.syscon.s4.cm.programsservices.VAcpSchedules;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;

@Repository
public class OcusmoduRepositoryImpl extends RepositoryBase implements OcusmoduRepository {

    private final Map<String, FieldMapper> mapping = new ImmutableMap.Builder<String, FieldMapper>()
            .put("ROLE", new FieldMapper("role")).put("FIRST_NAME", new FieldMapper("firstName")).build();

    /**
     * Creates new OcusmoduRepositoryImpl class Object
     */
    public OcusmoduRepositoryImpl() {
    }

    /**
     * Fetch the records from database table
     *
     * @param objSearchDao VAcpSchedules
     * @return List<VAcpSchedules>
     * @throws SQLException
     */
    public List<VAcpSchedules> vAcpSchExecuteQuery(final VAcpSchedules objSearchDao) {
        final String sql = getQuery("OCUSMODU_VACPSCH_FIND_V_ACP_SCHEDULES");
        final RowMapper<VAcpSchedules> mapper = Row2BeanRowMapper.makeMapping(sql, VAcpSchedules.class, mapping);
        return namedParameterJdbcTemplate.query(sql, createParams("crsActId", objSearchDao.getPhaseInstanceId(), "sessionNo", objSearchDao.getSessionNo()), mapper);
    }

    @Override
    public String getWeekDay(final String schDate) {
        final String sql = getQuery("OCUSMODU_GET_WEEKDAY");
        String data = null;
        try {
            data = namedParameterJdbcTemplate.queryForObject(sql, createParams("schDate", schDate),
                    String.class);
        } catch (Exception e) {
            return data;
        }

        return data;
    }


}
