package net.syscon.s4.triggers.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.Addresses;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.triggers.AddressesT3Repository;
@Repository
public class AddressesT3RepositoryImpl extends RepositoryBase implements AddressesT3Repository{
	private static Logger logger = LogManager.getLogger(AddressesT3RepositoryImpl.class.getName());
	
	private final Map<String, FieldMapper> addressesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("END_DATE", 						new FieldMapper("endDate"))
			.put("OWNER_ID", 						new FieldMapper("ownerId"))
			.put("ADDRESS_TYPE", 					new FieldMapper("addressType"))
			.put("SUITE_NUMBER", 					new FieldMapper("suiteNumber"))
			.put("ZIP_POSTAL_CODE", 				new FieldMapper("zipPostalCode"))
			.put("OWNER_SEQ", 						new FieldMapper("ownerSeq"))
			.put("NO_FIXED_ADDRESS_FLAG", 			new FieldMapper("noFixedAddressFlag"))
			.put("MAIL_FLAG", 						new FieldMapper("mailFlag"))
			.put("BUSINESS_HOUR", 					new FieldMapper("businessHour"))
			.put("CITY_NAME", 						new FieldMapper("cityName"))
			.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 				new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME", 				new FieldMapper("modifyDatetime"))
			.put("PRIMARY_FLAG", 					new FieldMapper("primaryFlag"))
			.put("STREET", 							new FieldMapper("street"))
			.put("STREET_DIRECTION", 				new FieldMapper("streetDirection"))
			.put("COMMENT_TEXT", 					new FieldMapper("commentText"))
			.put("CREATE_USER_ID", 					new FieldMapper("createUserId"))
			.put("CAPACITY", 						new FieldMapper("capacity"))
			.put("OWNER_CLASS", 					new FieldMapper("ownerClass"))
			.put("OWNER_CODE", 						new FieldMapper("ownerCode"))
			.put("CITY_CODE", 						new FieldMapper("cityCode"))
			.put("MODIFY_USER_ID", 					new FieldMapper("modifyUserId"))
			.put("CONTACT_PERSON_NAME", 			new FieldMapper("contactPersonName"))
			.put("SERVICES_FLAG", 					new FieldMapper("servicesFlag"))
			.put("PROV_STATE_CODE", 				new FieldMapper("provStateCode"))
			.put("START_DATE", 						new FieldMapper("startDate"))
			.put("STREET_NUMBER", 					new FieldMapper("streetNumber"))
			.put("MAIL_CARE_OF", 					new FieldMapper("mailCareOf"))
			.put("ADDRESS_ID", 						new FieldMapper("addressId"))
			.put("COUNTRY_CODE", 					new FieldMapper("countryCode"))
			.put("VALIDATED_PAF_FLAG", 				new FieldMapper("validatedPafFlag"))
			.put("SPECIAL_NEEDS_CODE", 				new FieldMapper("specialNeedsCode"))
		    .build();

	public Addresses getAddressOldObject(final Addresses object) {
		Addresses address=null;
		final String sql = getQuery("ADDRESS_T3_GET_ADDRESS_OBJECT");
		List<Addresses> list = new ArrayList<Addresses>();
		 try {
			 final RowMapper<Addresses> AddressesRowMapper = Row2BeanRowMapper.makeMapping(sql, Addresses.class,
						addressesMapping);
			 list =  namedParameterJdbcTemplate.query(sql,
						createParams("addressId", (int)object.getAddressId()), AddressesRowMapper);
			 if (list!=null) {
				 address=list.get(0);
			}
		} catch (Exception e) {
			 address=null;
			 logger.error("error", e);
		}
		return address;
		
	}
}

