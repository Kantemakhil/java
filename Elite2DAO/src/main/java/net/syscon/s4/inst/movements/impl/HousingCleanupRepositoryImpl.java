package net.syscon.s4.inst.movements.impl;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.Images;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inst.legals.impl.OcdcCasesRepositoryImpl;
import net.syscon.s4.inst.movements.HousingCleanupRepository;
import net.syscon.s4.inst.movements.beans.HousingBean;

@Repository
public class HousingCleanupRepositoryImpl extends RepositoryBase implements HousingCleanupRepository {
	private static Logger logger = LogManager.getLogger(OcdcCasesRepositoryImpl.class);
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
			.put("INTERNAL_LOC_ID", new FieldMapper("internalLocId")).build();
	
	private final Map<String, FieldMapper> vheaderBlock = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID", 		    new FieldMapper("offenderId"))
			.put("OFFENDER_ID_DISPLAY",	    new FieldMapper("offenderIdDisplay"))
			.put("LAST_NAME", 			    new FieldMapper("lastName"))
			.put("FIRST_NAME", 			    new FieldMapper("firstName"))
			.put("BIRTH_DATE",			    new FieldMapper("birthDate"))
			.put("OFFENDER_BOOK_ID", 		new FieldMapper("offenderBookId"))
			.put("AGY_LOC_ID", 				new FieldMapper("agyLocId"))
			.put("STATUS_DISPLAY", 			new FieldMapper("statusDisplay"))
			.put("ROOT_OFFENDER_ID", 		new FieldMapper("rootOffenderId"))
			.put("STATUS_REASON", 			new FieldMapper("statusReason"))
			.put("AGE", 					new FieldMapper("age"))
			.put("GENDER", 					new FieldMapper("gender"))
			.build();

	private JdbcTemplate jdbcTemplate;
	int result;

	public HousingCleanupRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	public Integer insertData(String userName) {
		Integer result = 1;
		try {
			if (result == 1) {
				String sqlForImage = getQuery("MAX_IMAGE_ID_FLOORPLAN");
				Integer imageId = jdbcTemplate.queryForObject(sqlForImage, Integer.class);
				if(imageId==null||imageId<1) {
					insertImageAndUpdateFloorPlan(userName);
				}
			}
		} catch (Exception e) {
			result = 0;
			logger.error("insertData:" + e.getMessage());
		}

		return result;
	}

	private Integer insertImageAndUpdateFloorPlan(String userName) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		Integer imageId;
		final String sql = getQuery("FLOOR_PLAN_DET");
		List<HousingBean> floorPlanIdList = new ArrayList<HousingBean>();
		final RowMapper<HousingBean> floorPlanRowMapper = Row2BeanRowMapper.makeMapping(sql, HousingBean.class,
				housingMapping);
		try {
			floorPlanIdList = namedParameterJdbcTemplate.query(sql, floorPlanRowMapper);

			for (HousingBean housingBean : floorPlanIdList) {

				BigDecimal floorPlanIdInsert = new BigDecimal(housingBean.getFloorPlanId());
				String img = "Img_" + housingBean.getFloorPlanId() + ".jpg";
				InputStream imgFile = readFile(img);
				byte[] bytes = IOUtils.toByteArray(imgFile);

				String sqlForImage = getQuery("MAX_IMAGE_ID");
				imageId = jdbcTemplate.queryForObject(sqlForImage, Integer.class);
				Long imageIdToBeInserted = new Long(imageId + 1);
				Date currentDate = getCurrentDatetime();
				Date captureDate = Date.valueOf(java.time.LocalDate.now());
				BigDecimal imageObjectSeq = new BigDecimal(0);

				Images insertImageRecord = new Images();
				insertImageRecord.setImageId(imageIdToBeInserted);
				insertImageRecord.setCaptureDate(captureDate);
				insertImageRecord.setImageObjectType("OMUVB_HOUSE");
				insertImageRecord.setImageObjectId(floorPlanIdInsert);
				insertImageRecord.setImageObjectSeq(imageObjectSeq);
				insertImageRecord.setImageViewType("");
				insertImageRecord.setImageThumbnail(bytes);
				insertImageRecord.setActiveFlag("N");
				insertImageRecord.setOrientationType("");
				insertImageRecord.setSealFlag(null);
				insertImageRecord.setCreateDatetime(currentDate);
				insertImageRecord.setCreateUserId("");
				insertImageRecord.setModifyDatetime(currentDate);
				insertImageRecord.setModifyUserId("oms_owner");
				result = 1;
				String sqlImages = getQuery("INSERT_IMAGE");

				result = jdbcTemplate.update(sqlImages,
						new Object[] { insertImageRecord.getImageId(), insertImageRecord.getCaptureDate(),
								insertImageRecord.getImageObjectType(), insertImageRecord.getImageObjectId(),
								insertImageRecord.getImageObjectSeq(), insertImageRecord.getImageViewType(),
								insertImageRecord.getImageThumbnail(), insertImageRecord.getActiveFlag(),
								insertImageRecord.getOrientationType(), insertImageRecord.getSealFlag(),
								insertImageRecord.getCreateDatetime(), insertImageRecord.getCreateUserId(),
								insertImageRecord.getModifyDatetime(), insertImageRecord.getModifyUserId() });

				if (result > 0) {
					System.out.println(result + " Row Inserted with imageId = " + imageId);

					String updateFloorPlanTable = getQuery("UPDATE_FLOORPLAN");
					result = jdbcTemplate.update(updateFloorPlanTable,
							new Object[] { insertImageRecord.getImageId(), housingBean.getFloorPlanId(),userName });
					System.out.println("FLOOR_PLAN Table is updated with Image_id = " + insertImageRecord.getImageId()
							+ " where FLOOR_PLAN_ID = " + housingBean.getFloorPlanId());

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = 0;
		}

		return result;
	}

	static java.sql.Date getCurrentDatetime() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Date(today.getTime());
	}

	private InputStream readFile(String fileName) {
		ClassPathResource resource = new ClassPathResource(fileName);
		InputStream inputStream = null;
		try {
			inputStream = resource.getInputStream();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inputStream;

	}
	
	@Override
	public List<Offenders> getOffenderDetails(List<Offenders> offenderInsertList) {
		List<Offenders> offenderDetails = new ArrayList<Offenders>();
		String parameters="";
		final String sql = getQuery("OFFENDER_DETAILS");
		for(Offenders offender : offenderInsertList) {
			 parameters = parameters+","+offender.getFirstName();
		}
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		String dob = dateFormat.format(offenderInsertList.get(0).getBirthDate());
		final RowMapper<Offenders> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,Offenders.class, vheaderBlock);
		try {
			offenderDetails=  namedParameterJdbcTemplate.query(sql,createParams("parameters",parameters,"dob",dob),referenceCodeRowMapper);
			} catch (Exception e) {
				logger.error("getOffenderDetails"+e.getMessage());
				}
		return offenderDetails;
	}
	@Override
	public List<Offenders> getOffenderDet(List<Offenders> offenderInsertList,String userName) {
		List<Offenders> offenderDetails = new ArrayList<Offenders>();
		String parameters="";
		final String sql = getQuery("OFFENDER_DET");
		for(Offenders offender : offenderInsertList) {
			 parameters = parameters+","+offender.getFirstName();
		}
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		String dob = dateFormat.format(offenderInsertList.get(0).getBirthDate());
		parameters=parameters.substring(1);
		final RowMapper<Offenders> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,Offenders.class, vheaderBlock);
		try {
			offenderDetails=  namedParameterJdbcTemplate.query(sql,createParams("parameters",parameters,"dob",dob,"USERID",userName),referenceCodeRowMapper);
			} catch (Exception e) {
				logger.error("getOffenderDet"+e.getMessage());
				}
		return offenderDetails;
	}

	@Override
	public Boolean checkAgyLocationExist() {
		Integer count =0; 
		final String sql = getQuery("FETCH_LOCATIONS_COUNT");
		count = namedParameterJdbcTemplate.queryForObject(sql,createParams(),Integer.class);
		if(count>0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Integer assignDefaultLocation(String userName) {
		Integer liReturn = 1;
		final String sql = getQuery("ASSIGN_DEFUALT_LOCATION_TO_OFFENDERS");
		try {
			liReturn = namedParameterJdbcTemplate.update(sql,createParams("USERID",userName));
		}catch (Exception e) {
			liReturn = -1;
			logger.error("assignDefaultLocation"+e.getMessage());
		}
		return liReturn;
	}
}
