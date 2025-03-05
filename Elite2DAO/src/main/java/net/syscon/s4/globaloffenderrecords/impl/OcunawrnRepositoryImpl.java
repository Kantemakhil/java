package net.syscon.s4.globaloffenderrecords.impl;

import java.math.BigDecimal;
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
import net.syscon.s4.globaloffenderrecords.OcunawrnRepository;
import net.syscon.s4.im.beans.OffenderNonAssociations;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;

@Repository
public class OcunawrnRepositoryImpl extends RepositoryBase implements OcunawrnRepository {
	
	private static Logger logger = LogManager.getLogger(OcunawrnRepositoryImpl.class.getName());
	
	private final Map<String, FieldMapper> offenderNaDetailsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", 						new FieldMapper("offenderBookId")).build();
	
	public List<OffenderNonAssociations> getNonAssociationsOffenders(final BigDecimal offenderBookId) {
		final String sql = getQuery("OCUNAWARN_CHK_NONASSOCIATIONS");
		List<OffenderNonAssociations> returnList = new ArrayList<OffenderNonAssociations>();
		final RowMapper<OffenderNonAssociations> VAcpProgressRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderNonAssociations.class,
				offenderNaDetailsMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("offenderBookId", offenderBookId), VAcpProgressRowMapper);
		} catch (Exception e) {
			logger.error("Exception in " + this.getClass().getName() + " getNonAssociationsOffenders", e);
		}
		return returnList;
	}
	
	@Override
	public List<OffenderIndSchedules> checkOffenderScheduleConflicts(OffenderNonAssociations offNa,VOffenderAllSchedules vOffSch) {
		final String sql = getQuery("OCDCLOGS_CHECK_SCHEDULE_CONFLICTS");
		List<OffenderIndSchedules> returnList = new ArrayList();
		final RowMapper<OffenderIndSchedules> rowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderIndSchedules.class, offenderNaDetailsMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("offenderBookID",offNa.getNsOffenderBookId(),"eventDate",vOffSch.getEventDate(),
					                                                         "toAgyLocId",vOffSch.getToAgyLocId()),rowMapper);
		} catch (Exception e) {
			logger.error("Exception in " + this.getClass().getName() + " checkOffenderScheduleConflicts", e);  
		}
		return returnList;
	}

}
