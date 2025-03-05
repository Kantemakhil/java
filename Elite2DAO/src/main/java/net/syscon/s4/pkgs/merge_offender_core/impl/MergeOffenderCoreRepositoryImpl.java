package net.syscon.s4.pkgs.merge_offender_core.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OffenderCommunityFile;
import net.syscon.s4.common.beans.OffenderFingerprints;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.pkgs.merge_offender_core.MergeOffenderCoreRepository;
import net.syscon.s4.sa.recordmaintenance.MergeOffenderTables;
@Repository
public class MergeOffenderCoreRepositoryImpl extends RepositoryBase implements MergeOffenderCoreRepository{
	private static Logger logger = LogManager.getLogger(MergeOffenderCoreRepositoryImpl.class.getName());
	
	
	private final Map<String, FieldMapper> mergeOffenderCoreMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
						.build();


	@Override
	public List<MergeOffenderTables> mergeTableListCurser(String applnCode) {
		final String sql = getQuery("MERGE_OFFENDER_CORE_MERGE_TABLE_LIST_CURSER_DATA");
		List<MergeOffenderTables> offendercommunityPaperFiles = new ArrayList<MergeOffenderTables>();
		try {
			offendercommunityPaperFiles = namedParameterJdbcTemplate.query(sql, createParams("applnCode", applnCode),
					new BeanPropertyRowMapper<MergeOffenderTables>(MergeOffenderTables.class));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method mergeTableListCurser", e);
		}
		return offendercommunityPaperFiles;
	}

	@Override
	public List<MergeOffenderTables> mergeTableRelatedData(String tableName) {
		final String sql = getQuery("MERGE_OFFENDER_CORE_MERGE_TABLE_RELATED_DATA");
		List<MergeOffenderTables> offendercommunityPaperFiles = new ArrayList<MergeOffenderTables>();
		try {
			offendercommunityPaperFiles = namedParameterJdbcTemplate.query(sql, createParams("tableName", tableName),
					new BeanPropertyRowMapper<MergeOffenderTables>(MergeOffenderTables.class));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method mergeTableRelatedData", e);
		}
		return offendercommunityPaperFiles;
	}

	@Override
	public String extraPkColumn(String tableName) {
		final String sql = getQuery("MERGE_OFFENDER_CORE_EXTRA_PK_COLUMN");
		String lvColumnName = null;
		try {
			lvColumnName = namedParameterJdbcTemplate.queryForObject(sql, createParams("tableName", tableName), String.class);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method extraPkColumn", e);
			return null;
		}
		return lvColumnName;
	}

	@Override
	public Integer insertMergeOffenderSqls(String pSqlDml) {
		final String sql = getQuery("MERGE_OFFENDER_CORE_INSERT_MERGE_OFFENDER_SQLS");
		Integer result = null;
		try {
			result = namedParameterJdbcTemplate.update(sql, createParams("pSqlDml", pSqlDml ));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method insertMergeOffenderSqls", e);
		}
		return result;
	}

	@Override
	public OffenderFingerprints getOffFingerprintsDet(String pSqlDml) {
		OffenderFingerprints OffFingerprints = new OffenderFingerprints();
		try {
			 OffFingerprints = namedParameterJdbcTemplate.queryForObject(pSqlDml, createParams(), new BeanPropertyRowMapper<OffenderFingerprints>(OffenderFingerprints.class));
		}catch(Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getOffFingerprintsDet", e);
		}
		return OffFingerprints;
	}

	@Override
	public Integer updateOffFingrPrnt(String pSqlDml) {
			Integer result = null;
		try {
			result = namedParameterJdbcTemplate.update(pSqlDml, createParams());
		}catch(Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getOffFingerprintsDet", e);
		}
		return result;
	}

	@Override
	public Long getLvMaxSeq(String query) {
		Long result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(query, createParams(), Long.class);
		}catch(Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getLvMaxSeq", e);
		}
		return result;
	}

}
