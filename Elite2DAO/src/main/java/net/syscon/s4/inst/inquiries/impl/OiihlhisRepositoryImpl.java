package net.syscon.s4.inst.inquiries.impl;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.BedAssignmentHistories;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.inst.inquiries.OiihlhisRepository;

@Repository
public class OiihlhisRepositoryImpl extends RepositoryBase implements OiihlhisRepository {

	private static Logger logger = LogManager.getLogger(OiihlhisRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> livingUnitsMapping = new ImmutableMap.Builder<String, FieldMapper>().build();

	public List<BedAssignmentHistories> bedAhExecuteQuery(BedAssignmentHistories objSearchDao) {
		final String sql = getQuery("OIIHLHIS_BEDAH_FIND_BED_ASSIGNMENT_HISTORIES");
		final RowMapper<BedAssignmentHistories> BedAssignmentHistoriesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				BedAssignmentHistories.class, livingUnitsMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("offender_book_id", objSearchDao.getOffenderBookId()),
				BedAssignmentHistoriesRowMapper);
	}

	public LivingUnits postQuery(Integer livingUnitId) {
		final String sql = getQuery("OIIHLHIS_POST_QUERY");
		LivingUnits retObj = null;
		try {
			retObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("LIVING_UNIT_ID", livingUnitId),
					new BeanPropertyRowMapper<LivingUnits>(LivingUnits.class));
		} catch (Exception e) {
			logger.error("Exception in postQuery()");
		}
		return retObj;
	}

}
