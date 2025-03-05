package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.cf.offendertransactions.beans.PrintReceiptsTmp;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.PrintReceiptsTmpTjnRepository;

@Repository
public class PrintReceiptsTmpTjnRepositoryImpl extends RepositoryBase implements PrintReceiptsTmpTjnRepository {

	private static final Logger logger = LogManager.getLogger(PrintReceiptsTmpTjnRepositoryImpl.class);

	@Override
	public Integer insertPrintReceiptsTmpTjn(final PrintReceiptsTmp obj) {
		Integer retVal = null;
		final String Sql = getQuery("PRINT_RECEIPTS_TMP_TJN_INSERT");
		try {
			retVal = namedParameterJdbcTemplate.update(Sql, new BeanPropertySqlParameterSource(obj));
		} catch (Exception e) {
			logger.error("insertPrintReceiptsTmpTjn :", e);
		}
		return retVal;
	}

	@Override
	public Integer updatePrintReceiptsTmpTjn(final PrintReceiptsTmp obj) {
		Integer retVal = null;
		final String sql = getQuery("PRINT_RECEIPTS_TMP_TJN_UPDATE");
		try {
			retVal = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(obj));
		} catch (Exception e) {
			logger.error("updatePrintReceiptsTmpTjn :", e);
		}
		return retVal;
	}

	@Override
	public Integer deletePrintReceiptsTmpTjn(final PrintReceiptsTmp obj) {
		Integer retVal = null;
		final String sql = getQuery("PRINT_RECEIPTS_TMP_TJN_DELETE");
		try {
			retVal = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(obj));
		} catch (Exception e) {
			logger.error("deletePrintReceiptsTmpTjn :", e);
		}
		return retVal;
	}

}
