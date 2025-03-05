package net.syscon.s4.inst.movements.proposedmovements.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.movements.proposedmovements.OiuschcoRepository;
import net.syscon.s4.inst.movements.proposedmovements.beans.VOffSchOverview;

@Repository
public class OiuschcoRepositoryImpl extends RepositoryBase implements OiuschcoRepository {

	private static Logger logger = LogManager.getLogger(OiuschovRepositoryimpl.class.getName());

	@Override
	public List<VOffSchOverview> vOffSchOverviewExecuteQuery(VOffSchOverview searchRecord) {
		String sql = getQuery("OIUSCHCO_VOFFSCH_OVERVIEW_DATA");
		if ("OIDPHUNC".equals(searchRecord.getModuleName())) {
			sql = sql
					+ "  AND (TO_DATE(EVENT_DATE::text,'yyyy/mm/dd') =TO_DATE(current_timestamp ::text,'yyyy/mm/dd') + 14 or event_date is null) and int_ext in ('INTERNAL','HEARING') order by event_date asc ";
		} else if ("OIDPEXIM".equals(searchRecord.getModuleName())) {
			sql = sql + "  AND  int_ext in ('INTERNAL','EXTERNAL','HEARING') order by event_date asc ";
		}

		final ArrayList<VOffSchOverview> returnList = (ArrayList<VOffSchOverview>) namedParameterJdbcTemplate.query(sql,
				createParams("offender_book_id", searchRecord.getOffenderBookId()),
				new BeanPropertyRowMapper<VOffSchOverview>(VOffSchOverview.class));
		return returnList;
	}

}
