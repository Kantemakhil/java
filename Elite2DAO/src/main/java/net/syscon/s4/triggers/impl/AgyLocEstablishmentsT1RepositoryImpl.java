package net.syscon.s4.triggers.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgyLocEstablishments;
import net.syscon.s4.triggers.AgyLocEstablishmentsT1Repository;
@Repository
public class AgyLocEstablishmentsT1RepositoryImpl extends RepositoryBase implements AgyLocEstablishmentsT1Repository{

	private final Map<String, FieldMapper> agyLocEstMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("ESTABLISHMENT_TYPE", new FieldMapper("establishmentType")).build();
	private final Map<String, FieldMapper> refCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("CODE", new FieldMapper("code")).build();
	private final Map<String, FieldMapper> phonesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OWNER_ID", 						new FieldMapper("ownerId"))
			.put("CREATE_USER_ID", 					new FieldMapper("createUserId"))
			.put("PHONE_ID", 						new FieldMapper("phoneId"))
			.put("OWNER_CLASS", 					new FieldMapper("ownerClass"))
			.put("OWNER_CODE", 						new FieldMapper("ownerCode"))
			.put("EXT_NO", 							new FieldMapper("extNo"))
			.put("MODIFY_USER_ID", 					new FieldMapper("modifyUserId"))
			.put("OWNER_SEQ", 						new FieldMapper("ownerSeq"))
			.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 				new FieldMapper("createDatetime"))
			.put("PHONE_TYPE", 						new FieldMapper("phoneType"))
			.put("PHONE_NO", 						new FieldMapper("phoneNo"))
			.put("MODIFY_DATETIME", 				new FieldMapper("modifyDatetime"))
			.build();

	private final Map<String, FieldMapper> agyLocMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROPERTY_LEV_1_CODE", new FieldMapper("propertyLev1Code"))
			.put("ADDRESS_TYPE", new FieldMapper("addressType"))
			.put("DISABILITY_ACCESS_CODE", new FieldMapper("disabilityAccessCode"))
			.put("BAIL_OFFICE_FLAG", new FieldMapper("bailOfficeFlag"))
			.put("AREA_CODE", new FieldMapper("areaCode"))
			.put("GEOGRAPHIC_REGION_CODE", new FieldMapper("geographicRegionCode"))
			.put("HOUSING_LEV_1_CODE", new FieldMapper("housingLev1Code"))
			.put("COMMISSARY_PRIVILEGE", new FieldMapper("commissaryPrivilege"))
			.put("DEACTIVATION_DATE", new FieldMapper("deactivationDate"))
			.put("INTAKE_FLAG", new FieldMapper("intakeFlag"))
			.put("JURISDICTION_CODE", new FieldMapper("jurisdictionCode"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("HOUSING_LEV_3_CODE", new FieldMapper("housingLev3Code"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("SUB_AREA_CODE", new FieldMapper("subAreaCode"))
			.put("LONG_DESCRIPTION", new FieldMapper("longDescription"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("BUSINESS_HOURS", new FieldMapper("businessHours"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("PROPERTY_LEV_2_CODE", new FieldMapper("propertyLev2Code"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("PRINT_QUEUE", new FieldMapper("printQueue"))
			.put("ABBREVIATION", new FieldMapper("abbreviation"))
			.put("JUSTICE_AREA_CODE", new FieldMapper("justiceAreaCode"))
			.put("NOMS_REGION_CODE", new FieldMapper("nomsRegionCode"))
			.put("LABCORP_CLIENT_ID", new FieldMapper("labcorpClientId"))
			.put("CONTACT_NAME", new FieldMapper("contactName"))
			.put("HOUSING_LEV_2_CODE", new FieldMapper("housingLev2Code"))
			.put("LAST_BOOKING_NO", new FieldMapper("lastBookingNo"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("HOUSING_LEV_4_CODE", new FieldMapper("housingLev4Code"))
			.put("AGENCY_LOCATION_TYPE", new FieldMapper("agencyLocationType"))
			.put("DISTRICT_CODE", new FieldMapper("districtCode"))
			.put("PROPERTY_LEV_3_CODE", new FieldMapper("propertyLev3Code")).build();
	private final Map<String, FieldMapper> vAgyAddrMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ADDRESS_ID", new FieldMapper("addressId"))
			.put("ADDRESS_TYPE", new FieldMapper("addressType"))
			.put("ADDRESS_TYPE_DESC", new FieldMapper("addressTypeDesc"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("START_DATE", new FieldMapper("startDate"))
			.put("END_DATE", new FieldMapper("endDate"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("STREET", new FieldMapper("street"))
			.put("AREA", new FieldMapper("area"))
			.put("COUNTRY", new FieldMapper("country"))
			.put("STREET_NUMBER", new FieldMapper("streetNumber"))
			.put("MAIL_CARE_OF", new FieldMapper("mailCareOf"))
			.put("COUNTRY_CODE", new FieldMapper("countryCode"))
			.put("HOUSE", new FieldMapper("house"))
			.put("STREET_DIRECTION", new FieldMapper("streetDirection"))
			.put("STREET_DIRECTION_DESC", new FieldMapper("streetDirectionDesc"))
			.put("STREET_INFORMATION", new FieldMapper("streetInformation"))
			.put("CITY_CODE", new FieldMapper("cityCode"))
			.put("CITY_NAME", new FieldMapper("cityName"))
			.put("PROV_STATE_CODE", new FieldMapper("provStateCode"))
			.put("PROV_STATE_DESC", new FieldMapper("provStateDesc"))
			.put("ZIP_POSTAL_CODE", new FieldMapper("zipPostalCode"))
			.put("SUITE_NUMBER", new FieldMapper("suiteNumber"))
			.put("COMMENT_TEXT", new FieldMapper("commentText"))
			.put("PRIMARY_FLAG", new FieldMapper("primaryFlag"))
			.put("MAIL_FLAG", new FieldMapper("mailFlag"))
			.put("VALIDATED_FLAG", new FieldMapper("validatedFlag"))
			.build();
	private final Map<String, FieldMapper> dualMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_VALUE_2", new FieldMapper("sysDate"))
			.put("PROFILE_VALUE", new FieldMapper("user")).build();
	@Override
	public AgyLocEstablishments getAgyLocEstablishments(final String agyLocId,String establishType) {
		final String sql = getQuery("GET_AGY_LOC_ESTABLISHMENTS");
		AgyLocEstablishments retObj = new AgyLocEstablishments();
		List<AgyLocEstablishments> returnList = new ArrayList();
		final RowMapper<AgyLocEstablishments> offbkgeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgyLocEstablishments.class, vAgyAddrMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("AGY_LOC_ID", agyLocId,"ESTABLISHMENT_TYPE",establishType),
					offbkgeRowMapper);
			if(returnList != null && returnList.size() > 0) {
				retObj = returnList.get(0);
			}
		} catch (Exception e) {
			retObj = null;
		}
		return retObj;
	}
}

