package net.syscon.s4.pkgs.tag_booking.impl;

import java.math.BigDecimal;
import org.apache.logging.log4j.LogManager;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import org.apache.logging.log4j.Logger;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.pkgs.tag_booking.TagBookingRepository;

@Repository
public class TagBookingRepositoryImpl extends RepositoryBase implements TagBookingRepository {	
	
		private static Logger logger = LogManager.getLogger(TagBookingRepositoryImpl.class.getName());

	@Override
	public OffenderExternalMovements getOffExtMovementsdetails(final BigDecimal pOffenderBookId) {
		final String sql = getQuery("GET_EXT_LOCATION");		
		OffenderExternalMovements obj=  new OffenderExternalMovements();
		try {
			obj = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_OFFENDER_BOOK_ID", pOffenderBookId),
					new BeanPropertyRowMapper<OffenderExternalMovements>(OffenderExternalMovements.class));
		} catch (Exception e) {
			logger.error("Exception in getOffExtMovementsdetails :", e);

		}
		return obj;
		
		
	}	

}