package net.syscon.s4.pkgs.ocdtmrev.impl;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseSchedules;
import net.syscon.s4.pkgs.ocdtmrev.OcdtmrevPkgRepository;

@Repository
public class OcdtmrevPkgRepositoryImpl extends RepositoryBase implements OcdtmrevPkgRepository {

	private static Logger logger = LogManager.getLogger(OcdtmrevPkgRepositoryImpl.class.getName());

	@Override
	public Date getReviewDate(final CourseSchedules searchRecord) {
		final String sql = getQuery("GET_REVIEW_DATE");
		Date retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_crs_sch_id", searchRecord.getCrsSchId()), Date.class);
		} catch (Exception e) {
			logger.error("getReviewDate", e);
			retVal = null;
		}
		return retVal;
	}

}
