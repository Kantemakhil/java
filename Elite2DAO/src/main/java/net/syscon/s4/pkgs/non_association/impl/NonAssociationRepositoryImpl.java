package net.syscon.s4.pkgs.non_association.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.programsservices.VOffenderPrgObligations;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.AgyIntLocProfiles;
import net.syscon.s4.common.beans.OffenderAssessment;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OffenderNaDetails;
import net.syscon.s4.im.beans.OffenderStgAffiliations;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inst.movements.beans.OffRec;
import net.syscon.s4.pkgs.non_association.NonAssociationRepository;

@Repository
public class NonAssociationRepositoryImpl extends RepositoryBase implements NonAssociationRepository {

	private static Logger logger = LogManager.getLogger(NonAssociationRepositoryImpl.class.getName());

	@Autowired
	private DataSource ds;

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID", new FieldMapper("offenderId"))
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay"))
			.put("LAST_NAME", new FieldMapper("lastName")).put("FIRST_NAME", new FieldMapper("firstName")).build();

	private final Map<String, FieldMapper> offendersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("living_unit_id", new FieldMapper("offenderId"))
			.put("offender_book_id", new FieldMapper("offenderBookId")).put("ns_type", new FieldMapper("nameType"))
			.build();

	private final Map<String, FieldMapper> referenceCodeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SENTENCE_SEQ", new FieldMapper("sentenceSeq")).build();

	private final Map<String, FieldMapper> offMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SENTENCE_SEQ", new FieldMapper("sentenceSeq")).build();


	private final Map<String, FieldMapper> mMappingOciscata = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID", new FieldMapper("offenderId"))
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offender_id_display"))
			.put("LAST_NAME", new FieldMapper("last_name")).put("FIRST_NAME", new FieldMapper("first_name")).build();

	private final Map<String, FieldMapper> intLocUsgMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("LOCCODE", new FieldMapper("locCode"))
			.put("INTERNAL_LOCATION_ID", new FieldMapper("internalLocationId"))
			.put("LIST_SEQ", new FieldMapper("listSeq"))
			.build();

	private final Map<String, FieldMapper> agyIntLocMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("INT_LOC_PROFILE_CODE", 						new FieldMapper("intLocProfileCode"))
			.put("INT_LOC_PROFILE_TYPE", 						new FieldMapper("intLocProfileType"))
			.put("ROWID", 										new FieldMapper("rowid"))
			.build();


	@Override
	public List<AgencyInternalLocations> parentLocationCur(final Integer pInternalLocId, final String flag) {
		try {
			final String sql = getQuery("GET_PARENT_LOCATION_CUR");
			final RowMapper<AgencyInternalLocations> rowMapper = Row2BeanRowMapper.makeMapping(sql,
					AgencyInternalLocations.class, referenceCodeMapping);
			return namedParameterJdbcTemplate.query(sql, createParams("pInternalLocId", pInternalLocId, flag), rowMapper);
		} catch(Exception e){
			logger.error("Exeption in parentLocationCur " , e);
		}
		return null;
	}

	@Override
	public List<AgyIntLocProfiles> naTypeCur(final Integer internalLocationId, final String flag) {
		try {
			final String sql = getQuery("GET_NA_TYPE_CUR");
			final RowMapper<AgyIntLocProfiles> rowMapper = Row2BeanRowMapper.makeMapping(sql, AgyIntLocProfiles.class,
					referenceCodeMapping);
			return namedParameterJdbcTemplate.query(sql,
					createParams("internalLocationId", internalLocationId, "flag", flag), rowMapper);
		} catch(Exception e){
			logger.error("Exeption in naTypeCur " , e);
		}
		return null;
	}

	@Override
	public List<Offenders> nonAssCur(final Long internalLocationId, final String intLocProfileCode, final Long pOffenderId) {
		try {
			final String sql = getQuery("GET_NON_ASS_CUR");
			final RowMapper<Offenders> rowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class, offMapping);
			return namedParameterJdbcTemplate.query(sql, createParams("internalLocationId", internalLocationId,
					"intLocProfileCode", intLocProfileCode, "pOffenderId", pOffenderId), rowMapper);
		} catch(Exception e){
			logger.error("Exeption in nonAssCur " , e);
		}
		return null;
	}

	@Override
	public List<Offenders> checkEnemyCur(final Long internalLocationId, final String nsType, final Long pOffenderBookId) {
		try {
			final String sql = getQuery("GET_CHECK_ENEMY_CUR");
			final RowMapper<Offenders> rowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class, offMapping);
			return namedParameterJdbcTemplate.query(sql, createParams("internalLocationId", internalLocationId, "nsType",
					nsType, "pOffenderBookId", pOffenderBookId), rowMapper);
		} catch(Exception e){
			logger.error("Exeption in checkEnemyCur " , e);
		}
		return null;
	}

	@Override
	public List<Offenders> getNonAssCur(final Long offenderId, final Long crsActyId, final Date offenderStartDate, final Date offenderEndDate) {
		List<Offenders> returnList = new ArrayList<Offenders>();
		try {
			final String sql = getQuery("CHK_NA_PRG_SRV_CONFLICT_RT_NON_ASS_CUR");
			final RowMapper<Offenders> nonAssRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class, mMapping);
			returnList = namedParameterJdbcTemplate
					.query(sql,
							createParams("p_offender_id", offenderId, "p_offender_start_date", offenderStartDate,
									"p_offender_end_date", offenderEndDate, "p_crs_acty_id", crsActyId),
							nonAssRowMapper);
		} catch (Exception e) {
			logger.error("getNonAssCur :", e);
		}
		return returnList;
	}

	@Override
	public List<OffRec> getGetStgNaPrgSrv(final Long offenderBookId, final Long crsActyId) {
		List<OffRec> returnList = new ArrayList<OffRec>();
		try {
			final String sql = getQuery("CHK_NA_PRG_SRV_CONFLICT_RT_GET_STG_NA_PRG_SRV");
			final RowMapper<OffRec> nonAssRowMapper = Row2BeanRowMapper.makeMapping(sql, OffRec.class, mMappingOciscata);
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("p_offender_book_id", offenderBookId, "p_crs_acty_id", crsActyId), nonAssRowMapper);
		} catch (Exception e) {
			logger.error("getGetStgNaPrgSrv :", e);
		}
		return returnList;
	}

	@Override
	public Integer getOffBkgCntCur(final Integer pLivingUnitId) {
		try {
			final String sql = getQuery("GET_OFF_BKG_CNT_CUR");
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("p_living_unit_id", pLivingUnitId),
					Integer.class);
		} catch(Exception e){
			logger.error("Exeption in getOffBkgCntCur " , e);
		}
		return null;
	}

	@Override
	public Integer getRevBedCntCur(final Integer pLivingUnitId) {
		try {
			final String sql = getQuery("GET_REV_BED_CNT_CUR");
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("p_living_unit_id", pLivingUnitId),
					Integer.class);
		} catch(Exception e){
			logger.error("Exeption in getRevBedCntCur " , e);
		}
		return null;
	}

	@Override
	public Long getLastSeq() {
		try {
			final String sql = getQuery("GET_LAST_SEQ");
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		} catch(Exception e){
			logger.error("Exeption in getLastSeq " , e);
		}
		return null;
	}

	@Override
	public Integer insertProgramNonAssocTemp(final Long lvSeq, final BigDecimal pOffenderId, final BigDecimal pOffenderBookId, final BigDecimal pPrgId, final Long pCrsActyId, final String userName) {
		try{
			final String sql = getQuery("INSERT_PROGRAMS_NON_ASSOC_TMP");
			return namedParameterJdbcTemplate.update(sql,
					createParams("LV_SEQ", lvSeq, "P_OFFENDER_ID", pOffenderId, "P_OFFENDER_BOOK_ID", pOffenderBookId,
							"P_PRG_ID", pPrgId, "P_CRS_ACTY_ID", pCrsActyId, "createUserId", userName));
		} catch(Exception e){
			logger.error("Exeption in insertProgramNonAssocTemp " , e);
		}
		return null;
	}

	@Override
	public List<Offenders> naCurSelectOperation(final BigDecimal offenderId) {
		try{
			final String sql = getQuery("NA_CUR_SELECT_OPERATION");
			final RowMapper<Offenders> rowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class, offendersMapping);
			return namedParameterJdbcTemplate.query(sql, createParams("offenderId", offenderId), rowMapper);
		} catch(Exception e){
			logger.error("Exeption in naCurSelectOperation " , e);
		}
		return null;

	}

	@Override
	public Boolean chkNaLivUnitConflict(final BigDecimal livingUnitId, final Long offenderId, final String nameType) {
		Boolean flag = false;
		Integer count = 0;
		try {
			final String sql = getQuery("CHK_NA_LIV_UNIT_CONFLICT");
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("livingUnitId", livingUnitId, "offenderId", offenderId, "nameType", nameType),
					Integer.class);
		} catch (EmptyResultDataAccessException e) {
			logger.error("chkNaLivUnitConflict :", e);
			flag = false;
		}
		if (count > 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	public BigDecimal offenderCur(final BigDecimal offId) {
		BigDecimal bookId = null;
		try {
			final String sql = getQuery("OFFENDER_CUR");
			bookId = namedParameterJdbcTemplate.queryForObject(sql, createParams("offId", offId), BigDecimal.class);
		} catch (EmptyResultDataAccessException e) {
			logger.error("offenderCur :", e);
			bookId = null;
		}
		return bookId;
	}

	@Override
	public List<AgencyInternalLocations> parentLocationCur(final BigDecimal livingUnitId) {
		List<AgencyInternalLocations> returnList = new ArrayList<AgencyInternalLocations>();
		try {
			final String sql = getQuery("PARENT_LOCATION_CUR");
			final RowMapper<AgencyInternalLocations> rowMapper = Row2BeanRowMapper.makeMapping(sql,
					AgencyInternalLocations.class, offendersMapping);
			returnList = namedParameterJdbcTemplate.query(sql, createParams("livingUnitId", livingUnitId), rowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("parentLocationCur :", e);
		}
		return returnList;
	}

	@Override
	public BigDecimal restrictionsCur(final Integer internalLocationId) {
		BigDecimal id = null;
		try {
			final String sql = getQuery("RESTRICTIONS_CUR");
			id = namedParameterJdbcTemplate.queryForObject(sql, createParams("internalLocationId", internalLocationId),
					BigDecimal.class);
		} catch (EmptyResultDataAccessException e) {
			logger.error("restrictionsCur :", e);
			id = null;
		}
		return id;
	}

	@Override
	public List<Offenders> checkEnemyCur(final BigDecimal offBookId, final BigDecimal parentLocationId) {
		List<Offenders> returnList = new ArrayList<Offenders>();
		try {
			final String sql = getQuery("CHECK_ENEMY_CUR");
			final RowMapper<Offenders> rowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class, offendersMapping);
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("offBookId", offBookId, "parentLocationId", parentLocationId), rowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("checkEnemyCur :", e);
		}
		return returnList;
	}

	@Override
	public Integer deleteFmprogNonAssocTmp(String modifyUserId) {
		Integer retVal = 0;
		try {
			String table=getTableName();
			if(table==null) 
				this.createTable();
			final String sql = getQuery("CLEAR_TEMP_LIST");
			try {
				String tableName = "PROGRAMS_NON_ASSOC_TMP";
				String whereCondition = null;
				Map<String, Object> inputMap = new HashMap<String, Object>();
				inputMap.put("modifyUserId", modifyUserId);
				updatePreDeletedRow(tableName, whereCondition, inputMap);
			} catch (Exception e) {
				logger.error(e);
			}
			retVal = namedParameterJdbcTemplate.update(sql, createParams());
			retVal = 1;
		} catch (DataAccessException e) {
			logger.error("deleteFmprogNonAssocTmp :" + e);
			retVal = 0;
		}
		return retVal;
	}

	@Override
	public Integer programsNonAssocTmp(final VOffenderPrgObligations vOff) {
		Integer retVal = 0;
		try {
			final String sql = getQuery("PROGRAMS_NON_ASSOC_TMP");
			try {
				Map<String, Object> inputMap = new HashMap<String, Object>();
				String tableName = "programs_non_assoc_tmp";
				String whereCondition = "offender_id = :p_offender_id AND offender_book_id = :p_offender_book_id AND coalesce (program_id, 0) = coalesce (:p_prg_id, 0) AND coalesce (crs_acty_id, 0) = coalesce (:p_crs_acty_id, 0)";
				inputMap.put("p_offender_id", vOff.getOffenderId());
				inputMap.put("p_offender_book_id", vOff.getOffenderBookId());
				inputMap.put("p_prg_id, 0)", vOff.getProgramId());
				inputMap.put("p_crs_acty_id, 0)", null);
				inputMap.put("modifyUserId", vOff.getModifyUserId());
				updatePreDeletedRow(tableName, whereCondition, inputMap);
			} catch (Exception e) {
				logger.error(this.getClass().getName() + " error in programsNonAssocTmp " + e.getMessage());
			}
			retVal = namedParameterJdbcTemplate.update(sql,
					createParams("p_offender_id", vOff.getOffenderId(), "p_offender_book_id", vOff.getOffenderBookId(),
							"p_prg_id", vOff.getProgramId(), "p_crs_acty_id", null));
			retVal = 1;
		} catch (Exception e) {
			logger.error("programsNonAssocTmp :" + e);
			retVal = 0;
		}
		return retVal;
	}

	@Override
	public BigDecimal lOffenderCur(final BigDecimal offenderId) {
		try {
			final String sql = getQuery("L_OFFENDER_CUR");
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("p_offender_id", offenderId),
					BigDecimal.class);
		} catch(Exception e){
			logger.error("Exeption in lOffenderCur " , e);
		}
		return null;
	}

	@Override
	public List<AgencyInternalLocations> lParentLocationCur(final BigDecimal livingUnitId) {
		try {
			final String sql = getQuery("L_PARENT_LOCATION_CUR");
			final RowMapper<AgencyInternalLocations> rowMapper = Row2BeanRowMapper.makeMapping(sql,
					AgencyInternalLocations.class, referenceCodeMapping);
			return namedParameterJdbcTemplate.query(sql, createParams("p_living_unit_id", livingUnitId), rowMapper);
		} catch(Exception e){
			logger.error("Exeption in lParentLocationCur " , e);
		}
		return null;
	}

	@Override
	public Integer lRestrictionsCur(final Integer internalLocationId) {
		Integer retVal = null;
		try {
			final String sql = getQuery("L_RESTRICTIONS_CUR");
			retVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_internal_location_id", internalLocationId), Integer.class);
		} catch (Exception e) {
			retVal = null;
			logger.error("lRestrictionsCur ", e.getMessage());
		}
		return retVal;
	}

	@Override
	public String lCheckEnemyCur(final Integer lParentLocationId, final BigDecimal lOffenderBookId) {
		String retVal = null;
		try {
			final String sql = getQuery("L_CHECK_ENEMY_CUR");
			retVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_offender_book_id", lOffenderBookId, "p_parent_living_unit_id", lParentLocationId),
					String.class);
		} catch (Exception e) {
			retVal = null;
			logger.error("lCheckEnemyCur ", e.getMessage());
		}
		return retVal;

	}

	@Override
	public List<OffenderAssessment> getRevSupLevelCur(final BigDecimal offenderBookId) {
		try {
			final String sql = getQuery("GET_REV_SUP_LEVEL_CUR");
			final RowMapper<OffenderAssessment> rowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderAssessment.class,
					referenceCodeMapping);
			return namedParameterJdbcTemplate.query(sql, createParams("p_off_book_id", offenderBookId),
					rowMapper);
		} catch(Exception e){
			logger.error("Exeption in getRevSupLevelCur " , e);
		}
		return null;
	}

	@Override
	public Integer checkLivUnitSecurityCur(final BigDecimal livingUnitId, final String lvSupLevel) {
		try {
			final String sql = getQuery("CHECK_LIV_UNIT_SECURITY_CUR");
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_living_unit_id", livingUnitId, "lv_sup_level", lvSupLevel), Integer.class);
		} catch(Exception e){
			logger.error("Exeption in checkLivUnitSecurityCur " , e);
		}
		return null;
	}

	@Override
	public String chkNaBetweenOffendersOne(final Long pOffenderBookId, final Long pVisOffenderBookId) {
		String retVal = null;
		try {
			final String sql = getQuery("CHECK_NA_CUR");
			retVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_OFFENDER_BOOK_ID", pOffenderBookId, "P_VIS_OFFENDER_BOOK_ID", pVisOffenderBookId),
					String.class);
		} catch (Exception e) {
			logger.error("chkNaBetweenOffendersOne :" + e);
		}
		return retVal;
	}

	@Override
	public String chkNaBetweenOffendersTwo(final Long pOffenderBookId, final Long pVisOffenderBookId) {
		String retVal = null;
		try {
			final String sql = getQuery("CHECK_STG_CUR");
			retVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_OFFENDER_BOOK_ID", pOffenderBookId, "P_VIS_OFFENDER_BOOK_ID", pVisOffenderBookId),
					String.class);
		} catch (Exception e) {
			logger.error("chkNaBetweenOffendersTwo :" + e);
		}
		return retVal;
	}


	public int createTable()   {
		try {
			String createTableSQL="CREATE GLOBAL TEMPORARY TABLE PROGRAMS_NON_ASSOC_TMP ( LINE INT not null, OFFENDER_ID BIGINT not null, OFFENDER_BOOK_ID BIGINT not null, PROGRAM_ID BIGINT, CRS_ACTY_ID BIGINT ) ON COMMIT DELETE ROWS"; 
			return namedParameterJdbcTemplate.update(createTableSQL,createParams());
		} catch(Exception e){
			logger.error("Exeption in createTable " , e);
		}
		return 0;
	}
	public  String getTableName() {
		String table=null;
		try (Connection conn = ds.getConnection()) {
			DatabaseMetaData meta = conn.getMetaData();
			ResultSet res = meta.getTables( null, null, "PROGRAMS_NON_ASSOC_TMP",  new String[] {"TABLE"}); 
			while (res.next()) {
				table= res.getString("TABLE_NAME");
			}
		}
		catch (Exception e) {
			logger.error("getTableName", e);
		}
		return table;
	}

	@Override
	public List<AgyIntLocProfiles> getNonAssociationConfigForLocation(final BigDecimal locationId) {
		try {
			final String sql = getQuery("NONASSOCIATIONS_GET_NONASSOCIATION_CONFIG_FOR_LOCATION");
			final RowMapper<AgyIntLocProfiles> AgyIntLocMapper = Row2BeanRowMapper.makeMapping(sql,
					AgyIntLocProfiles.class, agyIntLocMapping);
			return namedParameterJdbcTemplate.query(sql,
					createParams("internalLocationId", locationId), AgyIntLocMapper);
		} catch(Exception e){
			logger.error("Exeption in checkEnemyCur " , e);
		}
		return null;
	}

	@Override
	public List<AgyIntLocProfiles> getNonAssociationConfigForLocationCode(final String locationCode) {
		try {
			final String sql = getQuery("NONASSOCIATIONS_GET_NONASSOCIATION_CONFIG_FOR_LOCATION_CODE");
			final RowMapper<AgyIntLocProfiles> AgyIntLocMapper = Row2BeanRowMapper.makeMapping(sql,
					AgyIntLocProfiles.class, agyIntLocMapping);
			return namedParameterJdbcTemplate.query(sql,
					createParams("locationCode", locationCode), AgyIntLocMapper);
		} catch(Exception e){
			logger.error("Exeption in checkEnemyCur " , e);
		}
		return null;
	}

	@Override
	public List<OffenderNaDetails> getNonAssociationOffenderList(Integer offenderBookId) {
		try {
			final String sql = getQuery("NONASSOCIATIONS_GET_NONASSOCIATION_OFFENDERS");
			final RowMapper<OffenderNaDetails> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderNaDetails.class, intLocUsgMapping);
			return namedParameterJdbcTemplate.query(sql, createParams("offenderBookId", offenderBookId),
					columnRowMapper);
		} catch (Exception e) {
			logger.error("getNonAssociationOffenderList:" + e);
		}
		return null;
	}

	@Override
	public List<OffenderNaDetails> getNonAssociationOffenders(final BigDecimal offenderBookId, final List<AgyIntLocProfiles> profileCodeList) {
		List<OffenderNaDetails> returnList = new ArrayList<OffenderNaDetails>();
		try {
			String sql = getQuery("NONASSOCIATIONS_SCH_NONASSOCIATION_OFFENDERS");
			//sql = sql + " AND ona.ns_reason_code in(" + profileCodeList + ")";
			final RowMapper<OffenderNaDetails> VAcpProgressRowMapper = Row2BeanRowMapper.makeMapping(sql,
					OffenderNaDetails.class, intLocUsgMapping);
			List<String> profileCodeListNew=new ArrayList<>();
			for(AgyIntLocProfiles ob:profileCodeList) {
				profileCodeListNew.add(ob.getIntLocProfileCode());
			}
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("offenderBookId", offenderBookId,"profileCodeList",profileCodeListNew),
					VAcpProgressRowMapper);

		} catch (Exception e) {
			logger.error(this.getClass().getName()+" getNonAssociationOffenders", e);
			return returnList;
		}
		return returnList;
	}


	@Override
	public List<OffenderStgAffiliations> getOffendersOfNonAssociationGroup(BigDecimal offenderBookId) {
		List<OffenderStgAffiliations> returnList = new ArrayList<OffenderStgAffiliations>();
		try {
			String sql = getQuery("NONASSOCIATIONS_OFFENDERS_OF_NONASSOCIATION_GROUPS");
			final RowMapper<OffenderStgAffiliations> VAcpProgressRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderStgAffiliations.class, intLocUsgMapping);
			returnList = namedParameterJdbcTemplate.query(sql, createParams("offenderBookId", offenderBookId), VAcpProgressRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" getOffendersOfNonAssociationGroup", e);
			return returnList;
		}
		return returnList;
	}

	@Override
	public List<String> getOffendersDetailsByLoc(BigDecimal offenderBookId, Integer livingUnitId) {
		List<String> list = new ArrayList<String>();
		String sql = getQuery("NONASSOCIATIONS_DETAILS_WHILE_INTAKE_FOR_OFFENDER");
		try {
			list = namedParameterJdbcTemplate.queryForList(sql,
					createParams("offenderBookId", offenderBookId, "livingUnitId", livingUnitId), String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getOffendersDetailsByLoc", e);
			list = null;
		}
		return list;
	}

	@Override
	public List<String> getOffendersDetailsForLocationExhange(BigDecimal offenderId, Integer livingUnitId,
			BigDecimal exhangeOffenderId) {
		List<String> list = new ArrayList<String>();
		String sql = getQuery("NONASSOCIATIONS_DETAILS_WHILE_INTAKE_FOR_OFFENDER");
		try {
			list = namedParameterJdbcTemplate.queryForList(sql, createParams("offenderBookId", offenderId,
					"livingUnitId", livingUnitId, "exhangeOffenderId", exhangeOffenderId), String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getOffendersDetailsByLoc", e);
			list = null;
		}
		return list;

	}

	@Override
	public List<OffenderBookings> checkAgyNonAssociationNaCur(Long offenderId) {
		String sql = getQuery("CHECK_AGY_NON_ASSOCIATION_NA_CUR");
		List<OffenderBookings> list = new ArrayList<OffenderBookings>();
		final RowMapper<OffenderBookings> VAcpProgressRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderBookings.class, intLocUsgMapping);
		try {
			list = namedParameterJdbcTemplate.query(sql, createParams("p_offender_id", offenderId),
					VAcpProgressRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " checkAgyNonAssociationNaCur", e);
			list = null;
		}
		return list;
	}
	
	@Override
	public String chkAgyConflict(Long offenderBookId) {
		final String sql = getQuery("CHK_AGY_CONFLICT");
		String agyLoc = null;
		try {
			agyLoc = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_na_offender_book_id", offenderBookId), String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " chkAgyConflict", e);
			agyLoc = null;
		}
		return agyLoc;
	}

	@Override
	public  List<OffenderBookings> lOffenderCur(Long offenderId) {
		String sql = getQuery("NON_ASSOCIATION_L_OFFENDER_CUR_OFF_NA_DET_CUR_STG_NON_ASSO_CUR");
		List<OffenderBookings> list = new ArrayList<OffenderBookings>();
		final RowMapper<OffenderBookings> VAcpProgressRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderBookings.class, intLocUsgMapping);
		try {
			list = namedParameterJdbcTemplate.query(sql, createParams("p_offender_id", offenderId),
					VAcpProgressRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " lOffenderCur", e);
			list = null;
		}
		return list;
	}

	@Override
	public Integer offNaDetCur(Long rootOffenderId) {
		int lvReturn = 0;
		try {
			final String sql = getQuery("OFF_NA_DET_CUR");
			lvReturn = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("lv_root_offender_id", rootOffenderId), Integer.class);
		} catch(Exception e){
			logger.error("Exeption in offNaDetCur " , e);
		}
		return lvReturn;
	}

	@Override
	public Integer stgNonAssoCur(BigDecimal offenderBookId) {
		
		int lvReturn = 0;
		try {
			final String sql = getQuery("STG_NON_ASSO_CUR");
			lvReturn = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_offender_book_id", offenderBookId), Integer.class);
		} catch(Exception e){
			logger.error("Exeption in offNaDetCur " , e);
		}
		return lvReturn;
	}
	
	@Override
	public List<String> getGangConflit(BigDecimal livingUnitId, BigDecimal offenderBookId) {
		List<String> list = new ArrayList<String>();
		String sql = getQuery("NONASSOCIATIONS_GET_GANG_CONFLIT");
		try {
			list = namedParameterJdbcTemplate.queryForList(sql,
					createParams("offenderBookId", offenderBookId, "livingUnitId", livingUnitId), String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getGangConflit", e);
			list = null;
		}
		return list;
	}
	
	@Override
	public List<String> getGangConflitDataForExchange(BigDecimal offenderId, Integer livingUnitId,
			BigDecimal exhangeOffenderId) {
		List<String> list = new ArrayList<String>();
		String sql = getQuery("NONASSOCIATIONS_GET_GANG_CONFLIT_DATA_FOR_EXCHANGE");
		try {
			list = namedParameterJdbcTemplate.queryForList(sql, createParams("offenderBookId", offenderId,
					"livingUnitId", livingUnitId, "exhangeOffenderId", exhangeOffenderId), String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getGangConflitDataForExchange", e);
			list = null;
		}
		return list;

	}
}
