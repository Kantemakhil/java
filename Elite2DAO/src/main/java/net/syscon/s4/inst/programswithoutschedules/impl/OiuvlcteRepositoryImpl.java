package net.syscon.s4.inst.programswithoutschedules.impl;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.VAddresses;
import net.syscon.s4.inst.programswithoutschedules.OiuvlcteRepository;

@Repository
public class OiuvlcteRepositoryImpl extends RepositoryBase implements OiuvlcteRepository {

	private final Map<String, FieldMapper> courseActivitiesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CRS_ACTY_ID", new FieldMapper("crsActyId")).put("PROGRAM_ID", new FieldMapper("programId"))
			.put("SERVICES_ADDRESS_ID", new FieldMapper("servicesAddressId"))
			.put("INTERNAL_LOCATION_ID", new FieldMapper("internalLocationId"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId")).build();
	
	private final Map<String, FieldMapper> vAgyAddrMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ADDRESS_ID", new FieldMapper("addressId"))
			.put("ADDRESS_TYPE", new FieldMapper("addressType"))
			.put("ADDRESS_TYPE_DESC", new FieldMapper("addressTypeDesc"))
			.put("STREET", new FieldMapper("street"))
			.put("AREA", new FieldMapper("area"))
			.put("COUNTRY", new FieldMapper("country"))
			.put("STREET_NUMBER", new FieldMapper("streetNumber"))
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
			.build();

	/**
	 * Creates new OiuvlcteRepositoryImpl class Object
	 */
	public OiuvlcteRepositoryImpl() {
		// OiuvlcteRepositoryImpl
	}

	@Override
	public CourseActivities getCrsDetails(final CourseActivities obj) {
		final String sql = getQuery("OIUVLCTE_CALL_OCISCATA");
		 CourseActivities returnObj ;
		final RowMapper<CourseActivities> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, CourseActivities.class,
				courseActivitiesMapping);
		
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("CRSACTYID", obj.getCrsActyId()), columnRowMapper);
		} catch (final Exception e) {
			returnObj = null;
		}
		return returnObj;
	}

	@Override
	public String getFacilityDet(final CourseActivities obj) {
		Map<String, Object> returnObject = null;
		String facility = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_INTERNAL_LOCATION_ID", Types.NUMERIC),
				new SqlOutParameter("RETURN_VALUE", Types.VARCHAR) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_INT_LOC").withFunctionName("INT_LOC_DESC").declareParameters(sqlParameters);
		inParamMap.put("P_INTERNAL_LOCATION_ID", obj.getInternalLocationId());

		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			facility = (String) returnObject.get("RETURN_VALUE");
		} catch (final Exception e) {
			facility = null;
		}
		return facility;
	}

	@Override
	public VAddresses getAdresss(final long addressId) {
		final String sql = getQuery("OIUVLCET_VADDRESS");
		final RowMapper<VAddresses> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VAddresses.class, vAgyAddrMapping);
		final VAddresses returnObj =  namedParameterJdbcTemplate.queryForObject(sql,
				createParams("addressId",addressId), columnRowMapper);
		return returnObj;
	}

}
