package net.syscon.s4.pkgs.oumtrnbk.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.pkgs.oumtrnbk.OumtrnbkPkgRepository;

@Repository
public class OumtrnbkPkgRepositoryImpl extends RepositoryBase implements OumtrnbkPkgRepository {

	private static Logger logger = LogManager.getLogger(OumtrnbkPkgRepositoryImpl.class.getName());

	@Override
	public Integer isOneBookingOnly(OffenderBookings bean) {
		final String sql = getQuery("OUMTRNBKPKG_COUNT_OFF_BOOKINGS");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,createParams("p_root_off_id",bean.getFromOffRootId()), Integer.class);
		}catch (Exception e) {
			logger.error(this.getClass().getName()+" isOneBookingOnly");
			return 0;
		}
	}
	
	@Override
	public Integer getInstBookActive(final Long offenderBookId) {
		final String sql = getQuery("OUMTRNBKPKG_GET_INST_BOOK_ACTIVE");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("p_off_book_id", offenderBookId),
					Integer.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getInstBookActive");
			return 0;
		}
	}
	
	@Override
	public Integer getToBookActive(Long rootOffIdTo) {
		final String sql = getQuery("OUMTRNBKPKG_GET_TO_BOOK_ACTIVE");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("p_root_off_id_to", rootOffIdTo),
					Integer.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getToBookActive");
			return 0;
		}
	}

	@Override
	public Integer getActiveBookingCur(Long rootOffId) {
		final String sql = getQuery("OUMTRNBKPKG_GET_ACTIVE_BOOKING_CUR");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("p_root_off_id", rootOffId),
					Integer.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getActiveBookingCur");
			return 0;
		}
	}

}
