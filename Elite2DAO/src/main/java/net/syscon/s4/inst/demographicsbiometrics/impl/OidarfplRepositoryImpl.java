package net.syscon.s4.inst.demographicsbiometrics.impl;


import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.FindFacilityExecuteQueryBean;
import net.syscon.s4.common.beans.FindHousingExecuteQueryBean;
import net.syscon.s4.common.beans.LivingUnitProfile;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyParameters;
import net.syscon.s4.im.beans.FacilityParameters;
import net.syscon.s4.im.beans.FacilityPlacement;
import net.syscon.s4.im.beans.OffenderAttributes;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.OmuavbedLivUnitsQuery;
import net.syscon.s4.im.beans.ProfileCodes;
import net.syscon.s4.inst.demographicsbiometrics.OidarfplRepository;
import net.syscon.s4.inst.demographicsbiometrics.OmuavbedRepository;
import oracle.jdbc.OracleTypes;


@Repository
public class OidarfplRepositoryImpl extends RepositoryBase implements OidarfplRepository{



	public static final int MAX_WEIGHT = Integer.MAX_VALUE;
    public static final String SENTENCE_TYPE_SENT = "SENT";
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidarfplRepositoryImpl.class);
	@Autowired
	private OmuavbedRepository omuavbedRepository;

	private final Map<String, FieldMapper> facilityMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", 					new FieldMapper("pAgyLocId"))
			.put("AGENCY_LOCATION_TYPE", 		new FieldMapper("pLivingUnitType"))
			.put("DESCRIPTION", 				new FieldMapper("description"))
			.put("NO_OF_AVAILABLE", 			new FieldMapper("noOfAvailable"))
			.build();


	final Map<String, FieldMapper> offenderAttributesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CASE_TYPE", 				new FieldMapper("caseType"))
			.put("CASE_STATUS",   			new FieldMapper("caseStatus"))
			.put("CASE_SEQ", 				new FieldMapper("case_Seq"))
			.put("SENTENCE_CATEGORY",  		new FieldMapper("category"))
			.put("SENTENCE_CALC_TYPE", 		new FieldMapper("sentenceCalcType"))
			.build();

	final Map<String, FieldMapper> offenderSentenceMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SENTENCE_CATEGORY",  		new FieldMapper("category"))
			.put("SENTENCE_CALC_TYPE", 		new FieldMapper("sentenceCalcType"))
			.build();

	final Map<String, FieldMapper> offenderpesonalAttMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_TYPE", 				new FieldMapper("profileType"))
			.put("PROFILE_CODE", 				new FieldMapper("profileCode"))
			.build();

	final Map<String, FieldMapper> movementReasonMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_TYPE", 				new FieldMapper("profileType"))
			.put("PROFILE_CODE", 				new FieldMapper("profileCode"))
			.put("DESCRIPTION",   				new FieldMapper("description"))
			.put("code",   				new FieldMapper("firstName"))
			.put("name",   				new FieldMapper("lastName"))
			.build();
	private final Map<String, FieldMapper> offenderAttributeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_LABEL", 					new FieldMapper("offenderAttributeLabel"))
			.put("OFFENDER_SYSTEM_TABLE", 			new FieldMapper("offenderSystemTable"))
			.put("OFFENDER_SYSTEM_TABLE_COLUMN", 	new FieldMapper("offenderSystemTblCol"))
			.put("DOMAIN_CODE", 					new FieldMapper("domainCode"))
			.put("OFF_ATT_VAL", 					new FieldMapper("offenderAttValues"))
			.put("DOMAIN_VALUE",                    new FieldMapper("domainValue"))
			.put("UNIQUE_ID",                    	new FieldMapper("uniqueId"))
			.build();

	private final Map<String, FieldMapper> facilityPlacementMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("FACILITY_ID",						new FieldMapper("facilityId"))
			.put("OFFENDER_DOMAIN", 				new FieldMapper("offenderDomain"))
			.put("OFFENDER_CODE",					new FieldMapper("offenderCode"))
			.put("SOURCE_SYSTEM", 					new FieldMapper("sourceSystem"))
			.put("SOURCE_SYSTEM_TABLE",				new FieldMapper("sourceSystemTable"))
			.put("OFFENDER_TABLE", 					new FieldMapper("offenderTable"))
			.put("REQUIRED", 						new FieldMapper("required"))
			.put("WEIGHTAGE", 						new FieldMapper("weight"))
			.put("SOURCE_SYSTEM_TBL_COL",			new FieldMapper("sourceSystemTblCol"))
			.put("SOURCE_DOMAIN", 					new FieldMapper("sourceDomain"))
			.put("OFFENDER_TBL_COL1",				new FieldMapper("offenderTblCol1"))
			.put("OFFENDER_TBL_COL2", 				new FieldMapper("offenderTblCol2"))
			.put("MAPPED_FOR",                      new FieldMapper("mappedFor"))
			.build();

	final Map<String, FieldMapper> facilityParametersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SOURCE_SYSTEM",			 		new FieldMapper("sourceSystem"))
			.put("SOURCE_SYSTEM_TABLE_COLUMN", 		new FieldMapper("sourceSystemTable"))
			.put("SOURCE_SYSTEM_TBL_COL", 			new FieldMapper("sourceSystemTblCol"))
			.put("REQUIRED", 						new FieldMapper("required"))
			.put("WEIGHTAGE", 						new FieldMapper("weight"))
			.build();

	final Map<String, FieldMapper> housingParametersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIVING_UNIT_ID",				 new FieldMapper("livingUnitId"))
			.put("AGY_LOC_ID",					 new FieldMapper("agyLocId"))
			.put("LIVING_UNIT_TYPE",			 new FieldMapper("livingUnitType"))
			.put("LIVING_UNIT_CODE",			 new FieldMapper("livingUnitCode"))
			.put("LEVEL_1_CODE",				 new FieldMapper("level1Code"))
			.put("LEVEL_2_CODE",				 new FieldMapper("level2Code"))
			.put("LEVEL_3_CODE",				 new FieldMapper("level3Code"))
			.put("LEVEL_4_CODE",				 new FieldMapper("level4Code"))
			.put("CAPACITY",					 new FieldMapper("capacity"))
			.put("OPERATION_CAPACITY",			 new FieldMapper("operationCapacity"))
			.put("DESCRIPTION",					 new FieldMapper("description"))
			.put("NO_OF_OCCUPANT",				 new FieldMapper("noOfOccupant"))
			.build();

	@Override
	public List<OffenderAttributes> offenderCaseDetails(Long offenderBookId) {
		List<OffenderAttributes> caseResult = new ArrayList<OffenderAttributes>();
		final String sql = getQuery("OFFENDER_CASE_DETAILS");
		final RowMapper<OffenderAttributes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,	OffenderAttributes.class, offenderAttributesMapping);
		try {
			caseResult=  namedParameterJdbcTemplate.query(sql,createParams("offenderBookId",offenderBookId), referenceCodeRowMapper);

			} catch (Exception e) {
				logger.error("offenderCaseDetails"+e.getMessage());

				}
		return caseResult;
	}

	@Override
	public List<ProfileCodes> getOffenderPersonalAtt(Long offenderBookId) {
		List<ProfileCodes> caseResult = new ArrayList<ProfileCodes>();
		final String sql = getQuery("GET_OFFENDER_PESONAL_ATTRIBUTES");
		final RowMapper<ProfileCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,	ProfileCodes.class, offenderpesonalAttMapping);
		try {
			caseResult=  namedParameterJdbcTemplate.query(sql,createParams("offenderBookId",offenderBookId), referenceCodeRowMapper);

			} catch (Exception e) {
				logger.error("offenderCaseDetails"+e.getMessage());

				}
		return caseResult;
	}

	@Override
	public List<OmuavbedLivUnitsQuery> findFacilityExecuteQuery(FindFacilityExecuteQueryBean findFacilityExecuteQueryBean) {

		final List<OffenderAttributes> offenderAttributesList = findFacilityExecuteQueryBean.getPersonalAttributeSet(); //, String pLivingUnitType;
		final String caseType = findFacilityExecuteQueryBean.getCaseType();
		List<OmuavbedLivUnitsQuery> facilityData = new ArrayList<OmuavbedLivUnitsQuery>();
		List<OmuavbedLivUnitsQuery> facilityDataWithRank = new ArrayList<OmuavbedLivUnitsQuery>();
		try {

			int whereParamsCount = 0;
			StringBuilder facilityPlacementQuery = new StringBuilder(
					"SELECT SOURCE_SYSTEM, SOURCE_SYSTEM_TABLE, SOURCE_SYSTEM_TBL_COL, REQUIRED, WEIGHTAGE FROM FACILITY_PLACEMENT WHERE MAPPED_FOR='F' AND (");
			for(OffenderAttributes offenderAttributes : offenderAttributesList){

				if (offenderAttributes.getOffenderAttValues() != null && (offenderAttributes.getDomainCode() != null
						|| offenderAttributes.getDomainValue() != null)) {

					if (whereParamsCount > 0) {
						facilityPlacementQuery.append(" OR ");
					}

					if (offenderAttributes.getDomainValue() == null) {
						facilityPlacementQuery.append("(OFFENDER_CODE = '")
								.append(offenderAttributes.getOffenderAttValues()).append("'")
								.append(" AND OFFENDER_DOMAIN ='").append(offenderAttributes.getDomainCode())
								.append("')");
					} else if(offenderAttributes.getDomainCode() == null){
						facilityPlacementQuery.append("(OFFENDER_CODE = '")
								.append(offenderAttributes.getOffenderAttValues()).append("'")
								.append(" AND OFFENDER_DOMAIN ='").append(offenderAttributes.getDomainValue())
								.append("')");
					} else {
						facilityPlacementQuery.append("(OFFENDER_CODE = '")
						.append(offenderAttributes.getOffenderAttValues()).append("'")
						.append(" AND OFFENDER_DOMAIN ='").append(offenderAttributes.getDomainCode())
						.append("')");

						facilityPlacementQuery.append(" OR ");

						facilityPlacementQuery.append("(OFFENDER_CODE = '")
						.append(offenderAttributes.getOffenderAttValues()).append("'")
						.append(" AND OFFENDER_DOMAIN ='").append(offenderAttributes.getDomainValue())
						.append("')");
					}

					whereParamsCount++;
			    }
			}

			//Add Case Type
			facilityPlacementQuery.append(" OR ");

			facilityPlacementQuery.append("(OFFENDER_CODE = '")
			.append(caseType).append("'")
			.append(" AND OFFENDER_DOMAIN ='").append("LEG_CASE_TYP")
			.append("')");




			facilityPlacementQuery.append(")");

			final RowMapper<FacilityParameters> facilityParametersRowMapper = Row2BeanRowMapper.makeMapping(facilityPlacementQuery.toString(),	FacilityParameters.class, facilityParametersMapping);
			List<FacilityParameters> facilityParamsList = namedParameterJdbcTemplate.query(facilityPlacementQuery.toString(), facilityParametersRowMapper);

			int noOfRequiredParams = 0;
			for (FacilityParameters facilityParameters : facilityParamsList) {
				if (facilityParameters.getRequired().equals("Y")) {
					noOfRequiredParams = noOfRequiredParams + 1 ;
				}
			}
			
			final int  noOfRequiredParamsFinal = noOfRequiredParams - 1;
			
			
			Map<String, List<FacilityParameters>> sourceSystemTableFacilityMap = new HashMap<>();

			for(FacilityParameters facilityParam : facilityParamsList){
				if(sourceSystemTableFacilityMap.containsKey(facilityParam.getSourceSystemTable())){
					sourceSystemTableFacilityMap.get(facilityParam.getSourceSystemTable()).add(facilityParam);
				} else {
					List<FacilityParameters> tempFacilityList = new ArrayList<>();
					tempFacilityList.add(facilityParam);
					sourceSystemTableFacilityMap.put(facilityParam.getSourceSystemTable(), tempFacilityList);
				}
			}

			Map<String,List<String>> agencyLocationIdQueryMap = new HashMap<>();

			for(String sourceSystemTable : sourceSystemTableFacilityMap.keySet()){
				String sourceSystemColumn = "";
				List<String> sourceSystemCommaSeperated = new ArrayList<>();

				for(FacilityParameters facility : sourceSystemTableFacilityMap.get(sourceSystemTable)){
					sourceSystemColumn = facility.getSourceSystemTblCol();
					sourceSystemCommaSeperated.add(facility.getSourceSystem()); //= sourceSystemCommaSeperated.concat(facility.getSource_system() + "','");
				}

				StringBuilder sourceSystemQuery = new StringBuilder("SELECT AGY_LOC_ID, ").append(sourceSystemColumn).append(" FROM ").append(sourceSystemTable).append(" WHERE ").
						append(sourceSystemColumn).append(" IN (:sourceSystemList)");

				agencyLocationIdQueryMap.put(sourceSystemQuery.toString(), sourceSystemCommaSeperated);

			}

			List<String> agyLocIds = new ArrayList<>();

			final Map<String, List<String>> agyLocIdFacilityParamMap = new HashMap<>();

			int counter=0;

			for (String getAgencyLocIdQuery : agencyLocationIdQueryMap.keySet()) {
				if (counter > 0) {			
					Map<String, Object> namedParametersPrevQuery = new HashMap<>();
					namedParametersPrevQuery.put("previousSourceSystem", agyLocIds);
					namedParametersPrevQuery.put("sourceSystemList", agencyLocationIdQueryMap.get(getAgencyLocIdQuery));
					List<String> sourceSystem = agencyLocationIdQueryMap.get(getAgencyLocIdQuery);
					getAgencyLocIdQuery = getAgencyLocIdQuery.concat(" AND AGY_LOC_ID IN (:previousSourceSystem)");
					namedParameterJdbcTemplate.query(getAgencyLocIdQuery, namedParametersPrevQuery, (ResultSetExtractor<Map<String,List<String>>>) rs -> {
					    while(rs.next()){
					    	if(agyLocIdFacilityParamMap.containsKey(rs.getString(1))){
					    		agyLocIdFacilityParamMap.get(rs.getString(1)).add(rs.getString(2));
					    	} else {
					    		List<String> agyTempList = new ArrayList<>();
					    		agyTempList.add(rs.getString(2));
					    		agyLocIdFacilityParamMap.put(rs.getString(1), agyTempList);
					    	}
					    }
					    return agyLocIdFacilityParamMap;
					});
					agyLocIdFacilityParamMap.entrySet().removeIf(e -> !e.getValue().containsAll(sourceSystem));
					agyLocIds = new ArrayList<>(agyLocIdFacilityParamMap.keySet());

				} else {
					Map<String, Object> namedParameters = Collections.singletonMap("sourceSystemList", agencyLocationIdQueryMap.get(getAgencyLocIdQuery));
					namedParameterJdbcTemplate.query(getAgencyLocIdQuery, namedParameters, (ResultSetExtractor<Map<String,List<String>>>) rs -> {
					    while(rs.next()){
					    	if(agyLocIdFacilityParamMap.containsKey(rs.getString(1))){
					    		agyLocIdFacilityParamMap.get(rs.getString(1)).add(rs.getString(2));
					    	} else {
					    		List<String> agyTempList = new ArrayList<>();
					    		agyTempList.add(rs.getString(2));
					    		agyLocIdFacilityParamMap.put(rs.getString(1), agyTempList);
					    	}
					    }
					    return agyLocIdFacilityParamMap;
					});
					agyLocIds = new ArrayList<>(agyLocIdFacilityParamMap.keySet());
				}
				counter++;
			}

			List<AgencyParameters> agencyParametersList = new ArrayList<>();

			for (String agency : agyLocIdFacilityParamMap.keySet()) {

				AgencyParameters agencyParameters = new AgencyParameters();
				agencyParameters.setAgency(agency);
				List<FacilityParameters> tempList = new ArrayList<>();
				for (String param : agyLocIdFacilityParamMap.get(agency)) {

					tempList.addAll(facilityParamsList.stream()
							.filter(facility -> facility.getSourceSystem().equals(param)).collect(Collectors.toList()));
				}
				LinkedHashSet<FacilityParameters> hashSet = new LinkedHashSet<>(tempList);
				ArrayList<FacilityParameters> listWithoutDuplicates = new ArrayList<>(hashSet);
				agencyParameters.setParamList(listWithoutDuplicates);
				agencyParametersList.add(agencyParameters);
			}

		    Collections.sort(agencyParametersList, new FacilityRank.AgencyComparator());

		    agencyParametersList.removeIf(agency -> FacilityRank.AgencyComparator.getNoOfParams(agency, "Y") < noOfRequiredParamsFinal);
		    
		    List<String> agyLocIdsFiltered = new ArrayList<>();
		    
		    int rank = 1;
			for(AgencyParameters agencyParameters : agencyParametersList){
				agencyParameters.setRank(rank);
				agyLocIdsFiltered.add(agencyParameters.getAgency());
				rank++;
			}

////		    agyLocIdsFiltered.add("HRP");
//		    
//			StringBuilder findFacilitiesQuery = new StringBuilder();
//
////			String sql=getQuery("GET_LAST_QUERY");
//			Map<String, Object> namedParameters = Collections.singletonMap("agencyLocIds", agyLocIdsFiltered);
//


			
//			facilityData = namedParameterJdbcTemplate.query(findFacilitiesQuery.toString(), namedParameters, referenceCodeRowMapper);

			String agencyLocationsString = "";
			int countParam = 0;
			for(String agyLocId : agyLocIdsFiltered) {
				if(countParam==0) {
					agencyLocationsString = agencyLocationsString + "'";
				} else {
					agencyLocationsString = agencyLocationsString + "', '";
				}
				agencyLocationsString = agencyLocationsString + agyLocId;
				countParam++;
			}
			if(!agencyLocationsString.isEmpty()) {
				agencyLocationsString = agencyLocationsString + "'";
			}
			
			String sql = "SELECT AGY_LOC.AGY_LOC_ID AS AGY_LOC_ID, AGY_LOC.DESCRIPTION AS DESCRIPTION, "
					+ "SUM(AGY_INT_LOC.CAPACITY - AGY_INT_LOC.NO_OF_OCCUPANT) AS NO_OF_AVAILABLE "
					+ "FROM AGENCY_INTERNAL_LOCATIONS AGY_INT_LOC INNER JOIN AGENCY_LOCATIONS "
					+ "AGY_LOC ON AGY_INT_LOC.AGY_LOC_ID = AGY_LOC.AGY_LOC_ID "
					+ "WHERE AGY_INT_LOC.AGY_LOC_ID IN(" + agencyLocationsString + ") AND "
					+ "AGY_INT_LOC.unit_type IS NOT NULL AND AGY_INT_LOC.INTERNAL_LOCATION_Id NOT IN "
					+ "(Select PARENT_INTERNAL_LOCATION_ID from AGENCY_INTERNAL_LOCATIONS WHERE "
					+ "AGY_LOC_ID IN(" + agencyLocationsString + ") and PARENT_INTERNAL_LOCATION_ID is not null) "
					+ "GROUP BY AGY_LOC.AGY_LOC_ID, AGY_LOC.DESCRIPTION ";
			
			final RowMapper<OmuavbedLivUnitsQuery> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql, OmuavbedLivUnitsQuery.class, facilityMapping);
			facilityData = namedParameterJdbcTemplate.query(sql, createParams(), referenceCodeRowMapper);
			
			facilityData.removeIf(facility -> facility.getNoOfAvailable() == 0);

			for(AgencyParameters agencyParameters : agencyParametersList){
				for(OmuavbedLivUnitsQuery livUnitsQuery : facilityData){
					if(agencyParameters.getAgency().equals(livUnitsQuery.getpAgyLocId()))
						facilityDataWithRank.add(livUnitsQuery);
				}
			}


		} catch (Exception e) {
			logger.error("findFacilityExecuteQuery" + e);
			e.printStackTrace();

		}

		return facilityData;
	}

	@Override
	public List<ProfileCodes> getMovementReasonCode() {
		List<ProfileCodes> reasonCode = new ArrayList<ProfileCodes>();
		final String sql = getQuery("GET_MOVEMENT_REASON_CODE");
		final RowMapper<ProfileCodes> referenceCodeRowMapper =  Row2BeanRowMapper.makeMapping(sql,	ProfileCodes.class, movementReasonMapping);
		try{
			reasonCode=  namedParameterJdbcTemplate.query(sql,createParams(), referenceCodeRowMapper);

		} catch (Exception e) {
			logger.error("findFacilityExecuteQuery"+e.getMessage());

			}

		return reasonCode;
	}

	private void checkAttributeRequired(List<OffenderAttributes> offenderAttributeList) {
		String sql = "SELECT REQUIRED FROM FACILITY_PLACEMENT WHERE OFFENDER_DOMAIN = :DOMAIN LIMIT 1";
		for (OffenderAttributes offenderAttribute : offenderAttributeList) {
			try {
			String required = namedParameterJdbcTemplate
					.queryForObject(sql,
							createParams("DOMAIN", offenderAttribute.getDomainValue() == null
									? offenderAttribute.getDomainCode() : offenderAttribute.getDomainValue()),
							String.class);
			offenderAttribute.setRequired(required);
			}catch (Exception e) {
				logger.error("in checkAttributeRequired"+e);
			}
		}

	}
	
	@Override
	public List<OffenderAttributes> offenderAttributeExecuteQuery(Long offenderId, Long offenderBookId) {
		List<OffenderAttributes> offenderAttributes = new ArrayList<OffenderAttributes>();
		Map<String, String> attributeMap = new HashMap<String, String>();
		String offenderTable = "";
		String offenderTableColumn = "";
		String offenderAttLabel = "";
		String preparedSql = null;
		String offenderDomValue = "";
		Long offenderIdForSearch;
		String description = "";
		String personalAtt = "";
		String offenderAttributesValues = "";
		final String sql = getQuery("FIND_OFFENDER_ATTRIBUTES");
		final RowMapper<OffenderAttributes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderAttributes.class, offenderAttributeMapping);
		try {
			offenderAttributes = namedParameterJdbcTemplate.query(sql, referenceCodeRowMapper);
			for (OffenderAttributes offenderAttribute : offenderAttributes) {
				
				offenderTable = offenderAttribute.getOffenderSystemTable();
				offenderTableColumn = offenderAttribute.getOffenderSystemTblCol();
				offenderAttLabel = offenderAttribute.getOffenderAttributeLabel();
				offenderDomValue = offenderAttribute.getDomainValue();
				final StringBuffer sqlQuery = new StringBuffer();
				if (offenderAttribute.getUniqueId().equals("OFFENDER_ID")) {
					offenderIdForSearch = offenderId;
				} else {
					offenderIdForSearch = offenderBookId;
				}
				final String sqlForAttValue = "SELECT " + offenderTableColumn + " AS OFF_ATT_VAL  FROM " + offenderTable
						+ " WHERE " + offenderAttribute.getUniqueId() + "=" + offenderIdForSearch;
				sqlQuery.append(sqlForAttValue);
				if (offenderTable.equals("OFFENDER_PROFILE_DETAILS")) {
					sqlQuery.append(" AND " + offenderAttribute.getDomainCode() + " = '" + offenderDomValue + "'");
				}

				preparedSql = sqlQuery.toString().trim();
				try {
					if(offenderTable.equalsIgnoreCase("OFFENDER_SENTENCES")){
						
						List<String> offenderSentenceList = namedParameterJdbcTemplate.query(preparedSql,
								createParams("tableName", offenderTable, "columnName", offenderTableColumn,
										"offenderBookId", offenderBookId, "label", offenderAttLabel),
								
								(ResultSet rs, int rowNum) -> rs.getString(1));
						
					    HashSet<String>  offenderSentenceSet = new HashSet<>(offenderSentenceList);
					    
					    if(offenderSentenceSet.size() == 1)
					    	offenderAttributesValues = (String) offenderSentenceSet.toArray()[0];
					    else if(offenderSentenceSet.size() > 1) {
					    	offenderAttributesValues = SENTENCE_TYPE_SENT;
					    }
						
					}
					
					else {
						offenderAttributesValues = namedParameterJdbcTemplate.queryForObject(preparedSql,
								createParams("tableName", offenderTable, "columnName", offenderTableColumn,
										"offenderBookId", offenderBookId, "label", offenderAttLabel),
								String.class);	
					}
					
					
					if (offenderAttributesValues != null && offenderAttributesValues != "") {
						if (offenderAttribute.getDomainValue() != null) {
							description = changesCodeToDescription(offenderAttribute.getDomainValue(),
									offenderAttributesValues);
							personalAtt = fetchPersonalAttributes(offenderAttributesValues,
									offenderAttribute.getDomainValue());
						} else {
							description = changesCodeToDescription(offenderAttribute.getDomainCode(),
									offenderAttributesValues);
							personalAtt = fetchPersonalAttributes(offenderAttributesValues,
									offenderAttribute.getDomainCode());
						}
						attributeMap.put(offenderAttribute.getOffenderSystemTable(), offenderAttributesValues);
						if (attributeMap.get(offenderAttribute.getOffenderSystemTable()) != null)
							offenderAttribute
									.setOffenderAttValues(attributeMap.get(offenderAttribute.getOffenderSystemTable()));
						offenderAttribute.setAttDescription(description);
						
						if (personalAtt != null) {
							offenderAttribute.setPersonalAttributes(personalAtt);
							//offenderAttributes.add(offenderAttribute);
							offenderAttribute.setAttDescription(description);
							//for(OffenderAttributes bean:offenderAttributes) {
								//bean.setAttDescription(description);
//								bean.setPersonalAttributes(personalAtt);
							//}
						}
					}
				} catch (Exception e) {
					logger.error("offenderAttributeExecuteQuery" + e.getMessage());
				}
			}
		} catch (Exception e) {
			logger.error("offenderAttributeExecuteQuery" + e.getMessage());
		}
		checkAttributeRequired(offenderAttributes);
		return offenderAttributes;
	}

	@Override
	public List<OffenderAttributes> offenderSentenceDetails(Long offenderBookId) {
		List<OffenderAttributes> sentResult = new ArrayList<OffenderAttributes>();
		final String sql = getQuery("OFFENDER_SENTENCES_DETAILS");
		final RowMapper<OffenderAttributes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,	OffenderAttributes.class, offenderSentenceMapping);
		try {
			sentResult=  namedParameterJdbcTemplate.query(sql,createParams("offenderBookId",offenderBookId), referenceCodeRowMapper);

			} catch (Exception e) {
				logger.error("offenderSentenceDetails"+e.getMessage());

				}
		return sentResult;
	}

	private String changesCodeToDescription(String domainCode,String domainValue){
        final String sql = getQuery("FIND_REF_TABLE");
        String descriptionForCode="";
        final RowMapper referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql, FacilityPlacement.class, facilityPlacementMapping);
               List<FacilityPlacement> refCodeTable=  namedParameterJdbcTemplate.query(sql,createParams("domainCode",domainCode),referenceCodeRowMapper);
               if(refCodeTable.size()>0){
               for (FacilityPlacement facilityPlacement : refCodeTable) {
                     String descQuery="SELECT DESCRIPTION FROM "+facilityPlacement.getOffenderTable()+" WHERE "+facilityPlacement.getOffenderTblCol1()+"='"+domainValue+"' AND "+ facilityPlacement.getOffenderTblCol2()+"='"+domainCode+"'";
                     String preparedSql = descQuery.toString().trim();
                     descriptionForCode = namedParameterJdbcTemplate.queryForObject(preparedSql,createParams("refCodeTable",refCodeTable,"domainCode",domainCode,"domainValue",domainValue), String.class);
               }
               }
        return descriptionForCode;
	}
	private String fetchPersonalAttributes(String attributeValue,String domain) {
		String personalAtt="";
		String tableName="";
		List<FacilityPlacement> facilityPlacementList = new ArrayList<FacilityPlacement>();
		final String sql= getQuery("GET_ATTRIBUTE_MAPPING_CODE");
		final RowMapper<FacilityPlacement> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,	FacilityPlacement.class, facilityPlacementMapping);
		facilityPlacementList = namedParameterJdbcTemplate.query(sql, createParams("offenderCode",attributeValue,"offenderDomain",domain),referenceCodeRowMapper );
		for (FacilityPlacement facilityPlacement : facilityPlacementList) {
			if(facilityPlacement.getSourceSystem()!=null) {
			String	personalAttCode=facilityPlacement.getSourceSystem();
				tableName=facilityPlacement.getOffenderTable();
				String descQuery="SELECT DESCRIPTION FROM reference_codes WHERE code='"+facilityPlacement.getSourceSystem()+"' AND domain ='"+facilityPlacement.getSourceDomain()+"'";
                String preparedSql = descQuery.toString().trim();
                personalAtt = namedParameterJdbcTemplate.queryForObject(preparedSql,createParams("refCodeTable",tableName,"domainCode",domain,"domainValue",attributeValue), String.class);
				}
		}
		return personalAtt;
	}
	@Override
	public List<OmuavbedLivUnitsQuery> offenderHousingExecuteQuery(FindHousingExecuteQueryBean findHousingExecuteQueryBean,List<OmuavbedLivUnitsQuery> list) {


		List<OmuavbedLivUnitsQuery> livingUnitList = getLivingUnitListByOffenderBookIdAndAgyLocId(findHousingExecuteQueryBean,list);

		List<OffenderAttributes> offenderAttributesList = findHousingExecuteQueryBean.getOffenderAttributesList();

		if(offenderAttributesList.isEmpty()) {
			logger.info("Offender must have No Attribute :: Returning All Housing Location");
			return livingUnitList;
		}

		List<OmuavbedLivUnitsQuery> resultLivingUnitList = new ArrayList<>();
		try {
			List<FacilityParameters> housingSelectionCriteriaList = getOffenderCriteriaList(offenderAttributesList);



			List<FacilityParameters> requiredSelectionCriteriaList = housingSelectionCriteriaList.stream().filter(element -> element.getRequired().equals("Y")).collect(Collectors.toList());
			List<FacilityParameters> optionalSelectionCriteriaList = housingSelectionCriteriaList.stream().filter(element -> element.getRequired().equals("N")).collect(Collectors.toList());



			Map<OmuavbedLivUnitsQuery , Integer> livingUnitOptionalCriteriaWgtMap = new HashMap<>();

			livingUnitLoop:
			for (OmuavbedLivUnitsQuery livingUnit : livingUnitList) {

				List<LivingUnitProfile> livingUnitProfileList = omuavbedRepository.livingUnitProfilesByLivingUnitId(String.valueOf(livingUnit.getLivingUnitId()));

				logger.info("All Living Units Profile List ==>" +livingUnitProfileList);

				for (FacilityParameters requiredCriteria : requiredSelectionCriteriaList) {
					List<LivingUnitProfile> livingUnitProfileListFilterByRequired = livingUnitProfileList.stream()
							.filter(livingUnitProfile -> livingUnitProfile.getIntLocProfileType().equals(requiredCriteria.getSourceDomain())
									&& livingUnitProfile.getIntLocProfileCode().equals(requiredCriteria.getSourceSystem()))
							.collect(Collectors.toList());

					//If 'Required' criteria did not matched Skip the loop to Next Living-Unit
					if(livingUnitProfileListFilterByRequired.isEmpty()) {

						break livingUnitLoop;
					}

				}
				logger.info("Filtered By Required Criteria Living-Units-Profile List ==>" +livingUnitProfileList);

				//Only Proceed for 'Optional' if all all Required criteria matched with at least One Living-Unit-Profile
				if(!livingUnitProfileList.isEmpty()) {

					logger.debug("Living Units Profile List FIleterd by 'Required' Crietia ==>" +livingUnitProfileList);
					if(!optionalSelectionCriteriaList.isEmpty()){

						//Second Step: match 'Optional' :: Match Living units profiles with Faclity_placement_configuration
						logger.debug("'Optional' Filter Criteria List ==>" +optionalSelectionCriteriaList);

						int weightSum = 0;
						for (FacilityParameters optionalCriteria : optionalSelectionCriteriaList) {

							//Filter Living-Unit-Profiles based of
							List<LivingUnitProfile> livingUnitProfileListFilterByOptional = livingUnitProfileList.stream()
									.filter(livingUnitProfile -> livingUnitProfile.getIntLocProfileType().equals(optionalCriteria.getSourceDomain())
											&& livingUnitProfile.getIntLocProfileCode().equals(optionalCriteria.getSourceSystem())
									)
									.collect(Collectors.toList());


							//Add weight of All Matching Optional Criteria in List which have atleast one Matching Living-Unit-Profile
							if(!livingUnitProfileListFilterByOptional.isEmpty()) {
								weightSum = weightSum + optionalCriteria.getWeight();
							}else {
								weightSum = weightSum + 0;
							}
						}
						//put LivingUnit as Key and Value is Accumulated Sum of weight of All Optional criteria
						livingUnitOptionalCriteriaWgtMap.put(livingUnit, weightSum);

					}else
					{
						//If All required criteria are matched and NO Optional Criteria is available then
						//assign current Living Unit Maximum Weight and Move to Next Living-Unit

						livingUnitOptionalCriteriaWgtMap.put(livingUnit, MAX_WEIGHT);

					}
				}


			}

			//Sort living-Unit Optional-Criteria-Wgt Map with Weight
			livingUnitOptionalCriteriaWgtMap = sortMapByWeight(livingUnitOptionalCriteriaWgtMap);

			//extract out weight-sorted living-units in a List
			livingUnitOptionalCriteriaWgtMap.forEach((k,v)->{
				//Uncomment This Line to See Weight in UI
				//k.setNoOfOccupant(v);
				resultLivingUnitList.add(k);
				});

		} catch (Exception e) {
			logger.error("housingDataList" + e.getMessage(), e);
		}
		return livingUnitList;
	}

	public List<OmuavbedLivUnitsQuery> getLivingUnitListByOffenderBookIdAndAgyLocId(
			FindHousingExecuteQueryBean findHousingExecuteQueryBean, List<OmuavbedLivUnitsQuery> list) {
		List<OmuavbedLivUnitsQuery> livingUnitList = new ArrayList<>();
		{
			//Create a OffenderAttributes for preapring input param for Stored Procedure
			OffenderAttributes offenderAttributesObj = new OffenderAttributes();
			offenderAttributesObj.setOffenderBookId(Long.valueOf(findHousingExecuteQueryBean.getOffenderBookId()));
			offenderAttributesObj.setAgyLocId(findHousingExecuteQueryBean.getAgencyLocId());

			livingUnitList=list;
		}
		return livingUnitList;
	}

	private static  Map<OmuavbedLivUnitsQuery, Integer> sortMapByWeight(Map<OmuavbedLivUnitsQuery, Integer> WeightMap) {
		return WeightMap.entrySet().stream()
				.sorted(Map.Entry.<OmuavbedLivUnitsQuery, Integer>comparingByValue().reversed())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry :: getValue,(oldValue, newValue) -> oldValue,LinkedHashMap::new));
	}

	private List<FacilityParameters> getOffenderCriteriaList(List<OffenderAttributes> offenderAttributesList) {
		int whereParamsCount = 0;
		StringBuilder housingSelectionCriteriaSql = new StringBuilder(
				"SELECT FACILITY_ID, OFFENDER_DOMAIN, OFFENDER_CODE, SOURCE_SYSTEM, SOURCE_SYSTEM_TABLE,"
						+ " OFFENDER_TABLE, REQUIRED, WEIGHTAGE, SOURCE_SYSTEM_TBL_COL, SOURCE_DOMAIN, OFFENDER_TBL_COL1,"
						+ " OFFENDER_TBL_COL2, MAPPED_FOR FROM FACILITY_PLACEMENT WHERE MAPPED_FOR='H' AND (");
		// Fetch rows mapped from Facility parameter
		for (OffenderAttributes offenderAttributes : offenderAttributesList) {

			if (offenderAttributes.getOffenderAttValues() != null && (offenderAttributes.getDomainCode() != null
					|| offenderAttributes.getDomainValue() != null)) {

				if (whereParamsCount > 0) {
					housingSelectionCriteriaSql.append(" OR ");
				}

				if ( offenderAttributes.getDomainValue() == null) {
					housingSelectionCriteriaSql.append("(OFFENDER_CODE = '")
					.append(offenderAttributes.getOffenderAttValues()).append("'")
					.append(" AND OFFENDER_DOMAIN ='").append(offenderAttributes.getDomainCode())
					.append("')");
				} else if (offenderAttributes.getDomainCode() == null) {
					housingSelectionCriteriaSql.append("(OFFENDER_CODE = '")
					.append(offenderAttributes.getOffenderAttValues()).append("'")
					.append(" AND OFFENDER_DOMAIN ='").append(offenderAttributes.getDomainValue())
					.append("')");
				} else {

					housingSelectionCriteriaSql.append("(OFFENDER_CODE = '")
					.append(offenderAttributes.getOffenderAttValues()).append("'")
					.append(" AND OFFENDER_DOMAIN ='").append(offenderAttributes.getDomainValue())
					.append("')");
				}

				whereParamsCount++;
			}

		}
		housingSelectionCriteriaSql.append(")");

		final RowMapper<FacilityParameters> facilityParametersRowMapper = Row2BeanRowMapper.makeMapping(
				housingSelectionCriteriaSql.toString(), FacilityParameters.class, facilityPlacementMapping);

		return namedParameterJdbcTemplate.query(housingSelectionCriteriaSql.toString(), facilityParametersRowMapper);

	}

 private List<OmuavbedLivUnitsQuery> livingunitsExecuteQuery(OffenderAttributes offenderAttributes){

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
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OMUAVBED").withProcedureName("LIV_UNITS_QUERY").declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_BOOK_ID", offenderAttributes.getOffenderBookId());
		inParamMap.put("P_OFFENDER_ID", offenderAttributes.getpOffenderId());
		inParamMap.put("P_CASELOAD_ID", offenderAttributes.getpCaseloadId());
		inParamMap.put("P_AGY_LOC_ID", offenderAttributes.getAgyLocId());
		inParamMap.put("P_LIVING_UNIT_TYPE", offenderAttributes.getpLivingUnitType());
		inParamMap.put("P_LEVEL_1_CODE",offenderAttributes.getpLevel1Code());
		inParamMap.put("P_LEVEL_2_CODE", offenderAttributes.getpLevel2Code());
		inParamMap.put("P_LEVEL_3_CODE", offenderAttributes.getpLevel3Code());
		inParamMap.put("P_LEVEL_4_CODE", offenderAttributes.getpLevel4Code());
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
				lListObj.add(bean);
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return lListObj;
 }

	@Override
	public List<Offenders> offenderPersonalInformation(String profileCategory, Integer offenderBookId,
			String caseloadType) {
		List<Offenders> returnList = new ArrayList<Offenders>();
		final String sql = getQuery("OIDARFPL_OFFENDER_PERSONAL_DETAILS_CODE");
		final RowMapper<Offenders> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class,
				movementReasonMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("profileCategory", profileCategory,
					"offenderBookId", offenderBookId, "caseloadType", caseloadType), referenceCodeRowMapper);

		} catch (Exception e) {
			returnList = null;
		}

		return returnList;
	}
	
	@Override
	public List<Offenders> offenderPersonalInformationText(String profileCategory, Integer offenderBookId,
			String caseloadType) {
		List<Offenders> returnList = new ArrayList<Offenders>();
		final String sql = getQuery("OIDARFPL_OFFENDER_PERSONAL_DETAILS_TEXT");
		final RowMapper<Offenders> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class,
				movementReasonMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("profileCategory", profileCategory,
					"offenderBookId", offenderBookId, "caseloadType", caseloadType), referenceCodeRowMapper);

		} catch (Exception e) {
			returnList = null;
		}

		return returnList;
	}

}
