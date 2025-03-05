package net.syscon.s4.inst.movements.impl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.Images;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OmuavbedLivUnitsQuery;
import net.syscon.s4.inst.movements.HousingRepository;
import net.syscon.s4.inst.movements.beans.HotspotDetails;
import net.syscon.s4.inst.movements.beans.HousingBean;
import net.syscon.s4.inst.movements.beans.UnitProfile;
import oracle.jdbc.OracleTypes;

@Repository
public class HousingRepositoryImpl extends RepositoryBase implements HousingRepository {

	private static Logger logger = LogManager.getLogger(HousingRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> housingMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("p_offender_book_id", new FieldMapper("offenderBookId"))
			.put("p_offender_id", new FieldMapper("offenderId")).put("HOUSING_ID", new FieldMapper("housingId"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("INTERNAL_LOC_HOTSPOT_ID", new FieldMapper("internalLocHotspotId"))
			.put("INTERNAL_LOCATION_CODE", new FieldMapper("internalLocationCode"))
			.put("INTERNAL_LOCATION_ID", new FieldMapper("internalLocationId"))
			.put("X_COORDINATE", new FieldMapper("xCoordinate")).put("Y_COORDINATE", new FieldMapper("yCoordinate"))
			.put("PARENT_INTERNAL_LOCATION_ID", new FieldMapper("parentLocId"))
			.put("CAPACITY", new FieldMapper("capacity")).put("OPERATION_CAPACITY", new FieldMapper("totalBeds"))
			.put("NO_OF_OCCUPANT", new FieldMapper("allocatedBeds"))
			.put("INTERNAL_LOCATON_TYPE", new FieldMapper("internalLocationType"))
			.put("OPERATIONAL_CONFLICT", new FieldMapper("oprationalConflict"))
			.put("OFFENDER_CONFLICT", new FieldMapper("offenderConflict"))
			.put("SECURITY_CONFLICT", new FieldMapper("securityConflict"))
			.put("CELL_SHARING_CONFLICT", new FieldMapper("cellSharingConflict"))
			.put("IMAGE_OBJECT_ID", new FieldMapper("imageObjectId")).put("IMAGE_ID", new FieldMapper("imageId"))
			.put("FLOOR_PLAN_ID", new FieldMapper("floorPlanId"))
			.put("FLOOR_PLAN_NEXT_ID", new FieldMapper("floorPlanNextId"))
			.put("DESCRIPTION", new FieldMapper("description")).put("ROOT_FLOOR_PLAN", new FieldMapper("rootFloorPlan"))
			.put("LAST_NAME", new FieldMapper("lastName")).put("FIRST_NAME", new FieldMapper("firstName"))
			.put("PARENT_FLOOR_PLAN", new FieldMapper("parentFloorPlan"))
			.put("INTERNAL_LOC_ID", new FieldMapper("internalLocId"))
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay")).build();

	private final Map<String, FieldMapper> housingHotspotMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("INTERNAL_LOCATION_ID", new FieldMapper("internalLocationId"))
			.put("INTERNAL_LOCATION_CODE", new FieldMapper("internalLocationCode"))
			.put("INTERNAL_LOCATION_TYPE", new FieldMapper("internalLocationType"))
			.put("PARENT_INTERNAL_LOCATION_ID", new FieldMapper("parentId"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("INTERNAL_LOC_HOTSPOT_ID", new FieldMapper("internalLocHotspotId"))
			.put("X_COORDINATE", new FieldMapper("xCoordinate")).put("Y_COORDINATE", new FieldMapper("yCoordinate"))
			.build();

	private final Map<String, FieldMapper> housingUnitProfileMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("INTERNAL_LOCATION_ID", new FieldMapper("internalLocationId"))
			.put("INT_LOC_PROFILE_TYPE", new FieldMapper("profileType"))
			.put("INT_LOC_PROFILE_CODE", new FieldMapper("locCode")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.build();

	@Override
	public List<HousingBean> getHousingRecord(final HousingBean params) {
		try {
			final String sql = queryBuilderFactory.getQueryBuilder(getQuery("GET_HOUSING_NAVIGATION"), housingMapping)
					.build();
			final RowMapper<HousingBean> aliasesRowMapper = Row2BeanRowMapper.makeMapping(sql, HousingBean.class,
					housingMapping);
			String preparedSql = null;
			final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
			StringBuffer sqlQuery = new StringBuffer();
			sqlQuery.append(sql);

			if (null != params.getInternalLocationType() && !params.getInternalLocationType().equals("")
					&& params.getInternalLocationType().equalsIgnoreCase("TIER")) {
				final String addQury = "(select tag_int_loc_operation_flag(:internalLocationId) from dual) OPERATIONAL_CONFLICT,"
						+ "(select non_association_check_non_association(:offenderId,:internalLocationId) from dual) OFFENDER_CONFLICT,"
						+ "(select non_association_check_security(:offenderBookId,:internalLocationId) from dual) SECURITY_CONFLICT,"
						+ "(select omuavbed_chk_cs_conflict(:offenderBookId,:internalLocationId,:allocatedBeds) from dual) CELL_SHARING_CONFLICT";

				final String addOprConflict[] = sql.split("FROM");
				final String addedQuery = addOprConflict[0] + "," + addQury + " FROM " + addOprConflict[1];
				System.out.println(addedQuery);
				sqlQuery = new StringBuffer();
				sqlQuery.append(addedQuery);
				inParameterSource.addValue("internalLocationId", params.getInternalLocationId());
				inParameterSource.addValue("offenderId", params.getOffenderId());
				inParameterSource.addValue("offenderBookId", params.getOffenderBookId());
				inParameterSource.addValue("allocatedBeds", params.getAllocatedBeds());
			}

			List<HousingBean> results = new ArrayList<>();
			if (params.getRootFloorPlan().equals("Y")) {
				sqlQuery.append(" AND FP.ROOT_FLOOR_PLAN='Y' AND IL.AGY_LOC_ID=:AGY_LOC_ID");
				inParameterSource.addValue("AGY_LOC_ID", params.getAgyLocId());
			} else {
				sqlQuery.append(" AND FP.FLOOR_PLAN_ID =:FLOOR_PLAN_ID");
				inParameterSource.addValue("FLOOR_PLAN_ID", params.getFloorPlanNextId());
			}
			preparedSql = sqlQuery.toString().trim();
			results = namedParameterJdbcTemplate.query(preparedSql, inParameterSource, aliasesRowMapper);
			for (int i = 0; i < results.size(); i++) {
				results.get(i).setOffenderBookId(params.getOffenderBookId());
			}
			return results;
		} catch (final EmptyResultDataAccessException e) {
			System.out.println("DynamicQuery:    " + e.getMessage());
			return Collections.emptyList();
		}
	}

	@Override
	public List<HousingBean> getAllocatedBedInfo(final HousingBean params) {
		List<HousingBean> bedInfoData = new ArrayList<HousingBean>();
		final String sql = getQuery("GET_BED_INFO");
		final RowMapper<HousingBean> housingRowMapper = Row2BeanRowMapper.makeMapping(sql, HousingBean.class,
				housingMapping);
		try {
			bedInfoData = namedParameterJdbcTemplate.query(sql,
					createParams("internalLocationId", params.getInternalLocationId()), housingRowMapper);
		} catch (final Exception e) {
			logger.error("Exception: " + e.getMessage());
		}

		return bedInfoData;
	}

	@Override
	public String getOprationalConflict(final Long locId) {
		String oprConflict = null;
		final String sql = getQuery("GET_OPR_CONFLICTS");
		oprConflict = namedParameterJdbcTemplate.queryForObject(sql, createParams("internalLocationId", locId),
				String.class);
		return oprConflict;
	}

	@Override
	public String getNonAssociationConflict(final Long offenderId, final Long internalLocationId) {
		String nonAsso = null;
		final String sql = getQuery("GET_NON_ASSOCIATE_CONFLICTS");
		nonAsso = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("offenderId", offenderId, "internalLocationId", internalLocationId), String.class);
		return nonAsso;
	}

	@Override
	public String getSicurityConflict(final Long offenderBookId, final Long internalLocationId) {
		String security = null;
		final String sql = getQuery("GET_SICURITY_CONFLICTS");
		security = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("offenderBookId", offenderBookId, "internalLocationId", internalLocationId), String.class);
		return security;
	}

	@Override
	public String getCellSharingConflict(final Long offenderBookId, final Long internalLocationId, final Integer allocatedBeds) {
		String cellConflict = null;
		final String sql = getQuery("GET_CELL_CONFLICTS");
		cellConflict = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId,
				"internalLocationId", internalLocationId, "allocatedBeds", allocatedBeds), String.class);
		return cellConflict;
	}

	@Override
	public List<HousingBean> getAllImages() {
		List<HousingBean> imageList = new ArrayList<HousingBean>();
		final String sql = getQuery("GET_ALL_IMAGES");
		final RowMapper<HousingBean> housingRowMapper = Row2BeanRowMapper.makeMapping(sql, HousingBean.class,
				housingMapping);
		imageList = namedParameterJdbcTemplate.query(sql, housingRowMapper);
		return imageList;
	}

	@Override
	public List<HousingBean> getAllocatedOffenderInfo(final HousingBean params) {
		List<HousingBean> offenderInfo = new ArrayList<HousingBean>();
		final Boolean isParentNode = checkInternalLocationType(params.getInternalLocationId());
		String sql;
		if (isParentNode) {
			sql = getQuery("GET_ALLOCATED_OFF_DETAILS");
		} else {
			sql = getQuery("GET_BED_INFO");
		}

		final RowMapper<HousingBean> housingRowMapper = Row2BeanRowMapper.makeMapping(sql, HousingBean.class,
				housingMapping);
		try {
			offenderInfo = namedParameterJdbcTemplate.query(sql,
					createParams("internalLocationId", params.getInternalLocationId()), housingRowMapper);
		} catch (final Exception e) {
			logger.error("getAllocatedOffenderInfo" + e.getMessage());
		}

		return offenderInfo;
	}

	public List<OmuavbedLivUnitsQuery> livingunitsExecuteQuery(final OmuavbedLivUnitsQuery objSearchDao) {
		Map<String, Object> returnObject = null;
		final List<OmuavbedLivUnitsQuery> lListObj = new ArrayList<OmuavbedLivUnitsQuery>();
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[20];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_BOOK_ID", Types.NUMERIC),
				new SqlParameter("P_OFFENDER_ID", Types.NUMERIC), new SqlParameter("P_CASELOAD_ID", Types.VARCHAR),
				new SqlParameter("P_AGY_LOC_ID", Types.VARCHAR), new SqlParameter("P_LIVING_UNIT_TYPE", Types.VARCHAR),
				new SqlParameter("P_LEVEL_1_CODE", Types.VARCHAR), new SqlParameter("P_LEVEL_2_CODE", Types.VARCHAR),
				new SqlParameter("P_LEVEL_3_CODE", Types.VARCHAR), new SqlParameter("P_LEVEL_4_CODE", Types.VARCHAR),
				new SqlOutParameter("resultset", OracleTypes.CURSOR) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OMUAVBED").withProcedureName("LIV_UNITS_QUERY").declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_BOOK_ID", objSearchDao.getpOffenderBookId());
		inParamMap.put("P_OFFENDER_ID", objSearchDao.getpOffenderId());
		inParamMap.put("P_CASELOAD_ID", objSearchDao.getpCaseloadId());
		inParamMap.put("P_AGY_LOC_ID", objSearchDao.getpAgyLocId());
		inParamMap.put("P_LIVING_UNIT_TYPE", objSearchDao.getpLivingUnitType());
		inParamMap.put("P_LEVEL_1_CODE", objSearchDao.getpLevel1Code());
		inParamMap.put("P_LEVEL_2_CODE", objSearchDao.getpLevel2Code());
		inParamMap.put("P_LEVEL_3_CODE", objSearchDao.getpLevel3Code());
		inParamMap.put("P_LEVEL_4_CODE", objSearchDao.getpLevel4Code());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = (Map<String, Object>) simpleJDBCCall.withoutProcedureColumnMetaDataAccess()
					.execute(inParameter);
			final List<Map<String, String>> list = (List<Map<String, String>>) returnObject.get("resultset");
			for (int i = 0; i < list.size(); i++) {
				final Map<String, String> childMap = list.get(i);
				final OmuavbedLivUnitsQuery bean = new OmuavbedLivUnitsQuery();
				bean.setLivingUnitId(Long.parseLong(String.valueOf(childMap.get("LIVING_UNIT_ID"))));
				bean.setDescription(childMap.get("DESCRIPTION"));
				bean.setCapacity(Long.parseLong(String.valueOf(childMap.get("CAPACITY"))));
				bean.setUnitAtCapacity(childMap.get("UNIT_AT_CAPACITY"));
				bean.setNoOfOccupant(Long.parseLong(String.valueOf(childMap.get("NO_OF_OCCUPANT"))));
				bean.setNoOfAvailable(Long.parseLong(String.valueOf(childMap.get("NO_OF_AVAILABLE"))));
				bean.setPrisonerConflict(childMap.get("PRISONER_CONFLICT"));
				bean.setSecurityConflict(childMap.get("SECURITY_CONFLICT"));
				bean.setCellSharingConflict(childMap.get("CELL_SHARING_CONFLICT"));
				bean.setpOffenderBookId(objSearchDao.getpOffenderBookId());
				lListObj.add(bean);
			}
		} catch (final Exception e) {
			logger.error("", e);
		}
		return lListObj;
	}

	@Override
	public Integer findBaseImageArch(final String agyLocId) {
		Integer imageId = null;
		final String sql = getQuery("GET_BASE_IMAGE");
		try {
		imageId = namedParameterJdbcTemplate.queryForObject(sql, createParams("agyLocId", agyLocId), Integer.class);
		}catch (Exception e) {
			logger.error("Exception : findBaseImageArch  ", e);
			imageId = 0;
		}
		return imageId;
	}

	@Override
	public List<HousingBean> getBreadCrumbs(final Integer floorPlanNextId) {
		List<HousingBean> breadCrumbList = new ArrayList<HousingBean>();
		if (floorPlanNextId == null || floorPlanNextId == 1) {
			final String sql = getQuery("GET_BREADCRUMB_FOR_LANDING_PAGE");
			final RowMapper<HousingBean> housingRowMapper = Row2BeanRowMapper.makeMapping(sql, HousingBean.class,
					housingMapping);
			try {

				breadCrumbList = namedParameterJdbcTemplate.query(sql, createParams("floorPlanId", floorPlanNextId),
						housingRowMapper);
			} catch (final Exception e) {
				logger.error("getBreadCrumbs" + e.getMessage());
			}
		}

		else {
			final String sql = getQuery("GET_BREADCRUMB");
			final RowMapper<HousingBean> housingRowMapper = Row2BeanRowMapper.makeMapping(sql, HousingBean.class,
					housingMapping);
			try {

				breadCrumbList = namedParameterJdbcTemplate.query(sql, createParams("floorPlanId", floorPlanNextId),
						housingRowMapper);
			} catch (final Exception e) {
				logger.error("getBreadCrumbs" + e.getMessage());
			}
		}

		return breadCrumbList;
	}

	@Override
	public List<HousingBean> getParentChildInternalLocation(final String agyLocId) {
		List<HousingBean> bedCellRelationship = new ArrayList<HousingBean>();
		final String sql = getQuery("GET_CELL_PARENT_CHILD_REL");
		final RowMapper<HousingBean> housingRowMapper = Row2BeanRowMapper.makeMapping(sql, HousingBean.class,
				housingMapping);
		try {
			bedCellRelationship = namedParameterJdbcTemplate.query(sql, createParams("agyLocId", agyLocId),
					housingRowMapper);
		} catch (final Exception e) {
			logger.error("getParentChildInternalLocation" + e.getMessage());
		}
		return bedCellRelationship;
	}

	private Boolean checkInternalLocationType(final Long internalLocationId) {
		final String sql = getQuery("PARENT_LOCATION_COUNT");
		Integer parentInternalLocationCount = 0;
		parentInternalLocationCount = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("internalLocationId", internalLocationId), Integer.class);
		if (parentInternalLocationCount > 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public List<HousingBean> getAllocatedBedInfoForSelectedHotSpot(final HousingBean params) {
		List<HousingBean> bedInfoData = new ArrayList<HousingBean>();
		final String sql = getQuery("GET_BED_INFO_FOR_SELECTED_HOTSPOT");
		final RowMapper<HousingBean> housingRowMapper = Row2BeanRowMapper.makeMapping(sql, HousingBean.class,
				housingMapping);
		try {
			bedInfoData = namedParameterJdbcTemplate.query(sql,
					createParams("internalLocationId", params.getInternalLocationId()), housingRowMapper);
		} catch (final Exception e) {
			logger.error("Exception: " + e.getMessage());
		}

		return bedInfoData;
	}

	@Override
	public List<HousingBean> populateDataForFloorPlan(final Long internalLocationId) {
		List<HousingBean> dataForFloorPlan = new ArrayList<HousingBean>();
		final String sql = getQuery("GET_INFO_FOR_FLOOR_PLAN");
		final RowMapper<HousingBean> housingRowMapper = Row2BeanRowMapper.makeMapping(sql, HousingBean.class,
				housingMapping);
		try {
			dataForFloorPlan = namedParameterJdbcTemplate.query(sql,
					createParams("internalLocationId", internalLocationId), housingRowMapper);
		} catch (final Exception e) {
			logger.error("Exception: " + e.getMessage());
		}

		return dataForFloorPlan;
	}

	@Override
	public Boolean isEverParentNode(final Long internalLocationId) {
		final String sql = getQuery("PARENT_LOCATION_COUNT");
		Integer parentInternalLocationCount = 0;
		parentInternalLocationCount = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("internalLocationId", internalLocationId), Integer.class);
		if (parentInternalLocationCount > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<HotspotDetails> getFacilityHotspotsDetails(final String agyLocId) {
		List<HotspotDetails> hotspotsForFloorPlan = new ArrayList<HotspotDetails>();
		final String sql = getQuery("GET_HOTSPOTS_FOR_FACILITY_PLAN");
		final RowMapper<HotspotDetails> housingRowMapper = Row2BeanRowMapper.makeMapping(sql, HotspotDetails.class,
				housingHotspotMapping);
		try {
			final MapSqlParameterSource querryParams = createParams("AGY_LOC_ID", agyLocId);
			hotspotsForFloorPlan = namedParameterJdbcTemplate.query(sql, querryParams, housingRowMapper);
		} catch (final Exception e) {
			logger.error("Exception: " + e.getMessage());
		}

		return hotspotsForFloorPlan;
	}

	@Override
	public List<HotspotDetails> getUnitHotspotsDetails(final String agyLocId, final Long unitInternalLocationId) {
		List<HotspotDetails> hotspotsForFloorPlan = new ArrayList<HotspotDetails>();
		final String sql = getQuery("GET_HOTSPOTS_FOR_FLOOR_PLAN");
		final RowMapper<HotspotDetails> housingRowMapper = Row2BeanRowMapper.makeMapping(sql, HotspotDetails.class,
				housingHotspotMapping);
		try {
			final MapSqlParameterSource querryParams = createParams("AGY_LOC_ID", agyLocId, "UNIT_ID",
					unitInternalLocationId);
			hotspotsForFloorPlan = namedParameterJdbcTemplate.query(sql, querryParams, housingRowMapper);
		} catch (final Exception e) {
			logger.error("Exception: " + e.getMessage());
		}

		return hotspotsForFloorPlan;
	}

	@Override
	public void setUnitHotspotsDetails(final HotspotDetails hotspotDetails) {
		final String sql = getQuery("SET_HOTSPOTS");
		try {
			final MapSqlParameterSource querryParams = createParams("LOCATION_ID", hotspotDetails.getInternalLocationId(),
					"HOTSPOT_ID", hotspotDetails.getInternalLocHotspotId(), "X_COORDINATE",
					hotspotDetails.getxCoordinate(), "Y_COORDINATE", hotspotDetails.getyCoordinate(),"modifyUserId",hotspotDetails.getModifyUserId());
			namedParameterJdbcTemplate.update(sql, querryParams);
		} catch (final Exception e) {
			logger.error("Exception: " + e.getMessage());
		}
	}

	@Override
	public Integer facilityPlanUpload(final String agyLocId, final byte[] planData) {
		final String imageIdSql = getQuery("GET_FACILITY_PLAN_IMAGE_ID");
		Integer imageId = null;
		try {
			final MapSqlParameterSource querryParams = createParams("AGY_LOC_ID", agyLocId);
			imageId = namedParameterJdbcTemplate.queryForObject(imageIdSql, querryParams, Integer.class);
		} catch (final Exception e) {
			logger.error("Exception: " + e.getMessage());
		}

		return imageId;
	}

	@Override
	public Integer unitPlanUpload(final String agyLocId, final Long unitInternalLocationId, final byte[] planData) {
		final String imageIdSql = getQuery("GET_UNIT_PLAN_IMAGE_ID");
		Integer imageId = null;
		try {
			final MapSqlParameterSource querryParams = createParams("AGY_LOC_ID", agyLocId, 
					"UNIT_ID", unitInternalLocationId);
			imageId = namedParameterJdbcTemplate.queryForObject(imageIdSql, querryParams, Integer.class);
		} catch (final Exception e) {
			logger.error("Exception: " + e.getMessage());
		}

		return imageId;

	}

	@Override
	public List<UnitProfile> getUnitProfiles(final Long unitInternalLocationId) {
		List<UnitProfile> hotspotsForFloorPlan = new ArrayList<UnitProfile>();
		final String sql = getQuery("GET_UNIT_PROFILES");
		final RowMapper<UnitProfile> housingRowMapper = Row2BeanRowMapper.makeMapping(sql, UnitProfile.class,
				housingUnitProfileMapping);
		try {
			final MapSqlParameterSource querryParams = createParams("UNIT_ID", unitInternalLocationId);
			hotspotsForFloorPlan = namedParameterJdbcTemplate.query(sql, querryParams, housingRowMapper);
		} catch (final Exception e) {
			logger.error("Exception: " + e.getMessage());
		}

		return hotspotsForFloorPlan;
	}

	@Override
	public void saveUnitHotspotsDetails(final HotspotDetails hotspotDetails) {

		final String sql = getQuery("INSERT_HOTSPOTS");
		try {
			namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(hotspotDetails));
		} catch (final Exception e) {
			logger.error("Exception: " + e.getMessage());
		}

	}

	@Override
	public Integer getUnitHotspotsDetails(final Long internalLocationId, final Integer floorPlanId) {
		Integer hotSpot = null;
		final String sql = getQuery("GET_HOTSPOTA_FOR_INTERNAL_LOCATION");
		hotSpot = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("internalLocationId", internalLocationId, "floorPlanId", floorPlanId), Integer.class);
		return hotSpot;
	}

	@Override
	public Integer getIntLocHotSpotId(final Integer floorPlanId) {
		Integer hotSpotId = null;
		final String sql = getQuery("GET_INT_LOCATION_ID");
		hotSpotId = namedParameterJdbcTemplate.queryForObject(sql, createParams("floorPlanId", floorPlanId),
				Integer.class);
		return hotSpotId;
	}

	@Override
	public void updateImageThumbnail(final Integer imageId, final byte[] planData) {
		final String sql = getQuery("UPDATE_PLAN_IMAGE");
		try {
			final MapSqlParameterSource querryParams = createParams("IMAGE_THUMBNAIL_DATA", planData, "IMAGE_ID", imageId);
			namedParameterJdbcTemplate.update(sql, querryParams);
		} catch (final Exception e) {
			logger.error("Exception: " + e.getMessage());
		}
	}

	@Override
	public Integer getMaxImageId() {
		Integer imageId = null;
		final String sqlForImage = getQuery("IMAGE_ID_SEQ");
		imageId = namedParameterJdbcTemplate.queryForObject(sqlForImage, createParams(), Integer.class);
		return imageId;
	}

	@Override
	public Integer insertImage(final Images imageDetails) {

		final String sql = getQuery("INSERT_IMAGES");
		Integer result = null;
		try {
			result = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(imageDetails));
		} catch (final Exception e) {

			result = null;
			return result;
		}
		return result;
	}

	@Override
	public Integer insertFloorPlanDetails(final Integer floorPlanId, final Integer imageId, final String rootFloorPlan,
			final Integer parentFloorPlan, final String agyLocId, final Long unitInternalLocationId,String createUserId) {
		final String sql = getQuery("INSERT_FLOOR_PLAN");
		Integer result = null;
		try {
			result = namedParameterJdbcTemplate.update(sql,
					createParams("floorPlanId", floorPlanId, "imageId", imageId, "rootFloorPlan", rootFloorPlan,
							"parentFloorPlan", parentFloorPlan, "agyLocId", agyLocId, "unitInternalLocationId",
							unitInternalLocationId,"createUserId",createUserId));
		} catch (final Exception e) {

			result = null;
			return result;
		}
		return result;
	}

	@Override
	public Integer getFloorPlanId(final String agyLocId, final Long unitInternalLocationId) {
		final String sql = getQuery("GET_FLOOR_PLAN_ID");
		Integer result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("agyLocId", agyLocId, "unitInternalLocationId", unitInternalLocationId),
					Integer.class);
		} catch (final Exception e) {

			result = null;
			return result;
		}
		return result;
	}

	@Override
	public Integer updateAgyIntLocDetails(final Long unitInternalLocationId, final Integer floorPlanNextId,String userName) {
		final String sql = getQuery("UPDATE_AGY_LOC_INT_HOTSPOT");
		Integer result = null;
		try {
			result = namedParameterJdbcTemplate.update(sql,
					createParams("floorPlanNextId", floorPlanNextId, "unitInternalLocationId", unitInternalLocationId,"modifyUserId",userName));
		} catch (final Exception e) {
			result = null;
			return result;
		}
		return result;
	}

	@Override
	public Integer getFloorPlanIdSeq() {
		Integer floorId = null;
		final String sqlForImage = getQuery("FLOOR_PLNA_ID_SEQ");
		floorId = namedParameterJdbcTemplate.queryForObject(sqlForImage, createParams(), Integer.class);
		return floorId;
	}

	@Override
	public Integer updateFloorPlanDetails(final Integer floorPlanId, final Integer imageId,String modifyUserId) {
		final String sql = getQuery("UPDATE_FLOOR_PLAN_DETAILS");
		Integer result = null;
		try {
			result = namedParameterJdbcTemplate.update(sql,
					createParams("floorPlanId", floorPlanId, "imageId", imageId,"modifyUserId",modifyUserId));
		} catch (final Exception e) {
			result = null;
			return result;
		}
		return result;
	}

	@Override
	public List<HotspotDetails> getAllChildDataForFloor(final Long unitInternalLocationId) {
		List<HotspotDetails> bedInfoData = new ArrayList<HotspotDetails>();
		final String sql = getQuery("GET_CHILD_HOTSPOTS_FOR_FLOOR");
		final RowMapper<HotspotDetails> housingRowMapper = Row2BeanRowMapper.makeMapping(sql, HotspotDetails.class,
				housingHotspotMapping);
		try {
			bedInfoData = namedParameterJdbcTemplate.query(sql,
					createParams("internalLocationId", unitInternalLocationId), housingRowMapper);
		} catch (final Exception e) {
			logger.error("Exception: " + e.getMessage());
		}

		return bedInfoData;
	}

	@Override
	public Integer getFloorId(final Long unitInternalLocationId) {
		final String sql = getQuery("GET_FLOOR_ID");
		Integer result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("unitInternalLocationId", unitInternalLocationId), Integer.class);
		} catch (final Exception e) {

			result = null;
			return result;
		}
		return result;
	}

	@Override
	public List<HotspotDetails> getAllChildDataForParent(final String agyLocId) {
		List<HotspotDetails> bedInfoData = new ArrayList<HotspotDetails>();
		final String sql = getQuery("GET_CHILD_HOTSPOT_FOR_PARENT_FLOOR");
		final RowMapper<HotspotDetails> housingRowMapper = Row2BeanRowMapper.makeMapping(sql, HotspotDetails.class,
				housingHotspotMapping);
		try {
			bedInfoData = namedParameterJdbcTemplate.query(sql, createParams("agyLocId", agyLocId), housingRowMapper);
		} catch (final Exception e) {
			logger.error("Exception: " + e.getMessage());
		}

		return bedInfoData;
	}

	@Override
	public HousingBean getFloorDetails(final String agyLocId, final Long unitInternalLocationId) {
		final String sql = getQuery("GET_FLOOR_DETAILS");
		HousingBean returnObj;
		final RowMapper<HousingBean> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, HousingBean.class,
				housingMapping);
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("unitInternalLocationId", unitInternalLocationId, "agyLocId", agyLocId),
					columnRowMapper);
		} catch (final Exception e) {
			logger.error("Exception: " + e.getMessage());
			returnObj = null;
		}
		return returnObj;
	}

	@Override
	public HotspotDetails getInternalLocDetails(final String agyLocId, final Long unitInternalLocationId) {
		final String sql = getQuery("GET_AGENCY_LOCATION_DETAILS");
		HotspotDetails returnObj;
		final RowMapper<HotspotDetails> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, HotspotDetails.class,
				housingHotspotMapping);
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("unitInternalLocationId", unitInternalLocationId, "agyLocId", agyLocId),
					columnRowMapper);
		} catch (final Exception e) {
			logger.error("Exception: " + e.getMessage());
			returnObj = null;
		}
		return returnObj;
	}

	@Override
	public List<HousingBean> getFloorData(final Integer floorId) {
		List<HousingBean> bedInfoData = new ArrayList<HousingBean>();
		final String sql = getQuery("GET_BREADCRUMB_DETAIL");
		final RowMapper<HousingBean> housingRowMapper = Row2BeanRowMapper.makeMapping(sql, HousingBean.class,
				housingMapping);
		try {
			bedInfoData = namedParameterJdbcTemplate.query(sql,
					createParams("floorId", floorId), housingRowMapper);
		} catch (final Exception e) {
			logger.error("Exception: " + e.getMessage());
		}

		return bedInfoData;
	}

	@Override
	public Integer updateHotSpotDetails(final List<HousingBean> hotspotDetails) {
		
		final String sql = getQuery("UPDATE_HOTSPOTS");
		int[] returnArray;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final HousingBean hotSpotData : hotspotDetails) {
			parameters.add(new BeanPropertySqlParameterSource(hotSpotData));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (hotspotDetails.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer getFloorIdByImage(Integer imageId) {
		final String sql = getQuery("GET_GET_FLOOR_ID_BY_IMAGES");
		Integer result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("imageId", imageId), Integer.class);
		} catch (final Exception e) {

			result = null;
			return result;
		}
		return result;
	}

	@Override
	public Integer resetFloorHotSpotDetails(Integer floorId,String userName) {
		final String sql = getQuery("RESET_HOTSPOTS");
		Integer returnVal;
		try {
			final MapSqlParameterSource querryParams = createParams("floorPlanId", floorId,
					 "X_COORDINATE",0, "Y_COORDINATE", 0,"modifyUserId",userName);
			returnVal=namedParameterJdbcTemplate.update(sql, querryParams);
		} catch (final Exception e) {
			returnVal=0;
			logger.error("Exception: " + e.getMessage());
		}
		return returnVal;
		
	}
	

}
