package net.syscon.s4.report.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.sf.jasperreports.compilers.ReportClassFilter;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPropertiesUtil;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.syscon.s4.cm.intakeclosure.OcdintakRepository;
import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.OmsModuleParameter;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OmsModuleParameters;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.report.EliteReport;
import net.syscon.s4.report.EliteReportRepository;
import net.syscon.s4.report.OmsReportAsset;

@Repository
public class EliteReportRepositoryImpl extends RepositoryBase implements EliteReportRepository {

	 
    public EliteReportRepositoryImpl() {
        // EliteReportRepositoryImpl
    }
    @Autowired
	private OcdintakRepository ocdintakRepository;
    private static Logger logger = LogManager.getLogger(EliteReportRepositoryImpl.class.getName());

    private final Map<String, FieldMapper> processMapping = new ImmutableMap.Builder<String, FieldMapper>()
            .put("REPORT_ID", new FieldMapper("reportId"))
            .put("MODULE_NAME", new FieldMapper("moduleName"))
            .put("REPORT_BODY", new FieldMapper("reportBody"))
            .put("REPORT_FILE_NAME", new FieldMapper("reportFileName"))
            .put("REPORT_VERSION", new FieldMapper("reportVersion"))
            .put("CREATE_DATETIME", new FieldMapper("createDatetime"))
            .put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
            .put("CREATE_USER_ID", new FieldMapper("createUserId"))
            .put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
            .put("DESCRIPTION", new FieldMapper("description"))
            .build();

    private final Map<String, FieldMapper> omsModuleParametersMapping = new ImmutableMap.Builder<String, FieldMapper>()
            .put("PARAMETER_DOMAIN", new FieldMapper("parameterDomain"))
            .put("PARAMETER_NAME", new FieldMapper("parameterName"))
            .put("NBT_MODULE_NAME", new FieldMapper("nbtModuleName"))
            .put("PARAMETER_TYPE", new FieldMapper("parameterType"))
            .put("Y", new FieldMapper("y"))
            .put("PARAMETER_SEQ", new FieldMapper("parameterSeq"))
            .put("NBT_PARAMETER_NAME", new FieldMapper("nbtParameterName"))
            .put("PARAMETER_LOV_SELECT", new FieldMapper("parameterLovSelect"))
            .put("NBT_VALUE", new FieldMapper("nbtValue"))
            .put("OPTIONAL_FLAG", new FieldMapper("optionalFlag"))
            .put("MODULE_NAME", new FieldMapper("moduleName"))
            .put("CREATE_DATETIME", new FieldMapper("createDatetime"))
            .put("CREATE_USER_ID", new FieldMapper("createUserId"))
            .put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
            .put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
            .put("PARAMETER_LOV_TITLE", new FieldMapper("parameterLovTitle"))
            .put("PARAMETER_LOV_GROUP", new FieldMapper("parameterLovGroup"))
            .put("MULTIVALUE_FLAG", new FieldMapper("multivalueFlag"))
            .put("SEAL_FLAG", new FieldMapper("sealFlag"))
            .put("PARENT_LOV", new FieldMapper("parentLov"))

            .build();
    public final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
            .put("DESCRIPTION", new FieldMapper("description"))
            .put("CODE", new FieldMapper("code"))
            .build();

    private final Map<String, FieldMapper> assetMapping = new ImmutableMap.Builder<String, FieldMapper>()
            .put("ASSETS_ID", new FieldMapper("assetId"))
            .put("ASSET_CODE", new FieldMapper("assetCode"))
            .put("ASSET_BODY", new FieldMapper("assetBody"))
            .put("ASSETS_FILENAME", new FieldMapper("assetsFilename"))
            .put("CREATE_DATETIME", new FieldMapper("createDatetime"))
            .put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
            .put("CREATE_USER_ID", new FieldMapper("createUserId"))
            .put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
            .build();

    @Override
    public List<EliteReport> getReportForModules(List<String> listModules) {
        final String sql = getQuery("GET_ELITE_REPORTS_BY_MODULE_NAMES");

        HashSet<String> moduleSet = new HashSet<>(listModules);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("MODULE_NAMES", moduleSet);

        final RowMapper<EliteReport> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, EliteReport.class,
                processMapping);
        return namedParameterJdbcTemplate.query(sql, parameters, columnRowMapper);
    }

    @Override
    public List<EliteReport> getNameBodyForModules(List<String> listModules) {
        final String sql = getQuery("GET_NAME_BODY_BY_MODULE_NAMES");

        HashSet<String> moduleSet = new HashSet<>(listModules);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("MODULE_NAMES", moduleSet);

        final RowMapper<EliteReport> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, EliteReport.class,
                processMapping);
        return namedParameterJdbcTemplate.query(sql, parameters, columnRowMapper);
    }

    @Override
    public Map<String, Boolean> saveReportsForModules(List<EliteReport> listReports) {
        List<EliteReport> existingResporModules = getOmsModuleByName(listReports);

        Iterator<EliteReport> listIterator = listReports.iterator();
        List<EliteReport> updateList = new ArrayList<>();

        if (!existingResporModules.isEmpty()) {
            while (listIterator.hasNext()) {
                EliteReport report = listIterator.next();
                if (existingResporModules.contains(report)) {
                    int indexOf = existingResporModules.indexOf(report);
                    EliteReport eliteReport = existingResporModules.get(indexOf);
                    report.setReportVersion(eliteReport.getReportVersion() + 1);
                    updateList.add(report);
                    listIterator.remove();
                }
            }
        }

        insertOmsModule(listReports);

        // insert update
        Map<String, Boolean> insertProcess = insertReports(listReports);
        Map<String, Boolean> updateProcess = updateReports(updateList);

        // update History
        existingResporModules.stream().filter((module) -> {
            return updateProcess.containsKey(module.getModuleName()) ? updateProcess.get(module.getModuleName())
                    : false;

        });

        insertReportsHistory(existingResporModules);

        insertProcess.putAll(updateProcess);

        return insertProcess;
    }

    public List<EliteReport> getOmsModuleByName(List<EliteReport> listReports) {
        List<String> listModuleNames = listReports.stream().map((report) -> report.getModuleName())
                .collect(Collectors.toList());
        final String sql = getQuery("GET_ELITE_REPORTS_BY_MODULE_NAMES");

        HashSet<String> moduleSet = new HashSet<>(listModuleNames);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("MODULE_NAMES", moduleSet);
        final RowMapper<EliteReport> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, EliteReport.class,
                processMapping);

        List<EliteReport> listExitingModule = namedParameterJdbcTemplate.query(sql,
                parameters, columnRowMapper);
        return listExitingModule;
    }

    public boolean insertOmsModule(List<EliteReport> lstOfProcessMain) {
        if (!lstOfProcessMain.isEmpty()) {
            List<String> lstModuleName = lstOfProcessMain.stream().map((report) -> report.getModuleName())
                    .collect(Collectors.toList());

            List<String> exitingModuleName = getModulesByModuleNames(lstModuleName);

            String sql = getQuery("ELITE_REPORTS_MODULE_NAME_INSERT_PROCESS");
            int[] returnArray = new int[] {};

            List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();

            for (final EliteReport processMain : lstOfProcessMain) {
                if (!exitingModuleName.contains(processMain.getModuleName())) {
                    parameters.add(new BeanPropertySqlParameterSource(processMain));
                }
            }
            returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

            for (int i = 0; i < returnArray.length; i++) {
                if (returnArray[i] == 0) {
                    return Boolean.FALSE;
                }
            }
        }
        return Boolean.TRUE;
    }

    public List<String> getModulesByModuleNames(List<String> listModules) {
        final String sql = getQuery("GET_ELITE_MODULES_BY_MODULE_NAMES");

        HashSet<String> moduleSet = new HashSet<>(listModules);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("MODULE_NAMES", moduleSet);

        return namedParameterJdbcTemplate.queryForList(sql, parameters, String.class);
    }

    public Map<String, Boolean> insertReports(List<EliteReport> lstOfProcessMain) {
        String sql = getQuery("ELITE_REPORTS_INSERT_PROCESS");
        int[] returnArray = new int[] {};
        List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();

        Map<String, Boolean> processStatus = new HashMap<>(lstOfProcessMain.size());

        for (final EliteReport processMain : lstOfProcessMain) {
            parameters.add(new BeanPropertySqlParameterSource(processMain));
        }
        returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

        int counter = 0;
        for (EliteReport report : lstOfProcessMain) {
            processStatus.put(report.getModuleName(), returnArray[counter] == 0 ? Boolean.FALSE : Boolean.TRUE);
        }
        return processStatus;
    }

    public Map<String, Boolean> updateReports(List<EliteReport> lstOfProcessMain) {
        String sql = getQuery("ELITE_REPORTS_UPDATE_BY_MODULE_NAME_PROCESS");
        int[] returnArray = new int[] {};

        List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();

        Map<String, Boolean> processStatus = new HashMap<>(lstOfProcessMain.size());

        for (final EliteReport processMain : lstOfProcessMain) {
            parameters.add(new BeanPropertySqlParameterSource(processMain));
        }
        returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

        int counter = 0;
        for (EliteReport report : lstOfProcessMain) {
            processStatus.put(report.getModuleName(), returnArray[counter] == 0 ? Boolean.FALSE : Boolean.TRUE);
        }
        return processStatus;
    }

    public Map<String, Boolean> insertReportsHistory(List<EliteReport> lstOfProcessMain) {
        String sql = getQuery("ELITE_REPORTS_HISTORY_INSERT_PROCESS");
        int[] returnArray = new int[] {};
        List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();

        Map<String, Boolean> processStatus = new HashMap<>(lstOfProcessMain.size());

        for (final EliteReport processMain : lstOfProcessMain) {
            parameters.add(new BeanPropertySqlParameterSource(processMain));
        }
        returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

        int counter = 0;
        for (EliteReport report : lstOfProcessMain) {
            processStatus.put(report.getModuleName(), returnArray[counter] == 0 ? Boolean.FALSE : Boolean.TRUE);
        }
        return processStatus;
    }

    @Override
    public Long getHistoryReportId() {
        final String sql = getQuery("HISTORY_REPORT_ID");
        return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
    }

    @Override
    public List<EliteReport> getAllJRReports(String userId) {
        final String sql = getQuery("GET_ALL_ELITE_REPORT");

        final RowMapper<EliteReport> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, EliteReport.class,
                processMapping);
        return namedParameterJdbcTemplate.query(sql, createParams("userId", userId),columnRowMapper);

    }

    @Override
    public List<OmsModuleParameters> populateRecords(final String moduleName) {
        final String sql = getQuery("OSUREPOR_POPULATE_RECORDS");
        final RowMapper<OmsModuleParameters> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
                OmsModuleParameters.class, omsModuleParametersMapping);
        List<OmsModuleParameters> returnList = new ArrayList<>();
        returnList = namedParameterJdbcTemplate.query(sql, createParams("MODULENAME", moduleName),
                columnRowMapper);
        return returnList;
    }

    public JasperPrint fillReport(JasperReport jasperReport, Map<String, Object> parameters)
            throws JRException, SQLException {
        JasperPrint jasperPrint = null;
        String whiteListClass = ocdintakRepository.getProfileValueConstants(ApplicationConstants.CLIENT, ApplicationConstants.PROPERTY_PREFIX_CLASS_WHITELIST);
        logger.info("whiteListClass connfigued"+whiteListClass);
        JasperReportsContext jasperReportsContext=DefaultJasperReportsContext.getInstance();
        JRPropertiesUtil properties=JRPropertiesUtil.getInstance(jasperReportsContext);
        properties.setProperty(ReportClassFilter.PROPERTY_PREFIX_CLASS_FILTER_ENABLED, "true"); 
        properties.setProperty(ReportClassFilter.PROPERTY_PREFIX_CLASS_WHITELIST, whiteListClass == null ? "" : whiteListClass); 
        
        try (Connection connection = dataSource.getConnection()) {
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
            logger.info("Report is genetrated");
        } catch (Exception ex) {
        	 logger.info("Exception in fillReport"+ex.getMessage());
            ex.printStackTrace();
        }

        return jasperPrint;
    }

    @Override
    public List<OmsModuleParameter> getAllModuleParameters(List<String> listModules) {

        final String sql = getQuery("GET_OMS_MODULE_PARAMETERS_BY_MODULE_NAME");

        HashSet<String> moduleSet = new HashSet<>(listModules);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("MODULE_NAMES", moduleSet);

        final RowMapper<OmsModuleParameter> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
                OmsModuleParameter.class,
                omsModuleParametersMapping);
        List<OmsModuleParameter> listOmsModuleParam = namedParameterJdbcTemplate.query(sql, parameters,
                columnRowMapper);
        return listOmsModuleParam;

    }

    @Override
    public Map<String, Boolean> insertOmsModuleList(List<OmsModuleParameter> listOmsModParam) {
        int[] returnArray = insertUpdateRecords("INSERT_OMS_MODULE_PARAMETERS_WITH_REPORTS", listOmsModParam);
        Map<String, Boolean> processStatus = new HashMap<>();

        int counter = 0;
        listOmsModParam.stream().forEachOrdered(omsModule -> processStatus.put(omsModule.getModuleName(),
                returnArray[counter] == 0 ? Boolean.FALSE : Boolean.TRUE));
        return processStatus;
    }

    @Override
    public Map<String, Boolean> deleteOmsModuleList(List<OmsModuleParameter> listOmsModParam) {

        List<OmsModuleParameter> listModules = listOmsModParam.stream()
                .filter(distinctByKey(OmsModuleParameter::getModuleName)).collect(Collectors.toList());
		for (OmsModuleParameter omsModuleParameter : listModules) {
			try {
				Map<String, Object> inputMap = new HashMap<String, Object>();
				String tableName = "OMS_MODULE_PARAMETERS";
				String whereCondition = "MODULE_NAME = :moduleName";
				inputMap.put("moduleName", omsModuleParameter.getModuleName());
				inputMap.put("modifyUserId", omsModuleParameter.getModifyUserId());
				updatePreDeletedRow(tableName, whereCondition, inputMap);
			} catch (Exception e) {
				logger.error(this.getClass().getName() + " error in deleteOmsModuleList " + e.getMessage());
			}
		}
        int[] returnArray = insertUpdateRecords("DELETE_OMS_MODULE_PARAMETERS_BY_MODULE_NAME", listModules);

        Map<String, Boolean> processStatus = new HashMap<>();

        int counter = 0;
        listOmsModParam.stream().forEachOrdered(omsModule -> processStatus.put(omsModule.getModuleName(),
                returnArray[counter] == 0 ? Boolean.FALSE : Boolean.TRUE));
        return processStatus;
    }

    @Override
    public List<OmsModuleParameter> getOmsParameterNameByModuleName(String moduleName) {
        final String sql = getQuery("GET_OMS_MODULE_PARAM_NAME_BY_MODULE_NAME");

        final RowMapper<OmsModuleParameter> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
                OmsModuleParameter.class, omsModuleParametersMapping);
        List<OmsModuleParameter> returnList = new ArrayList<>();
        returnList = namedParameterJdbcTemplate.query(sql, createParams("MODULENAME", moduleName),
                columnRowMapper);
        return returnList;
    }

    @Override
    public Integer insetUpdateDeleteOmsModuleParams(List<OmsModuleParameter> insertUpdateList, String operation) {
        String sql = "";
        if (operation.equals("INSERT")) {
            sql = "INSERT_OMS_MODULE_PARAMETERS_WITH_REPORTS";
        } else if (operation.equals("UPDATE")) {
            sql = "UPDATE_MODULE_PARAMS_BY_NAME_SEQ_MODULENAME";
        } else if (operation.equals("DELETE")) {
            sql = "DELETE_OMS_MODULE_PARAMETERS_BY_SEQ_NAME_MODNAME";
			try {
				String tableName = "OMS_MODULE_PARAMETERS";
				String whereCondition = "MODULE_NAME = :moduleName AND PARAMETER_NAME = :parameterName AND PARAMETER_SEQ = :parameterSeq";
				for (OmsModuleParameter omsModuleParameter : insertUpdateList) {
					Map<String, Object> inputMap = new HashMap<String, Object>();
					inputMap.put("moduleName", omsModuleParameter.getModuleName());
					inputMap.put("parameterName", omsModuleParameter.getParameterName());
					inputMap.put("parameterSeq", omsModuleParameter.getParameterSeq());
					inputMap.put("modifyUserId", omsModuleParameter.getModifyUserId());
					updatePreDeletedRow(tableName, whereCondition, inputMap);
				}
			} catch (Exception e) {
				logger.error(this.getClass().getName() + " error in insetUpdateDeleteOmsModuleParams " + e.getMessage());
			}
        }
        int[] insertUpdateRecords = insertUpdateRecords(sql, insertUpdateList);

        int successCount = 0;
        for (int i = 0; i < insertUpdateRecords.length; i++) {
            if (insertUpdateRecords[i] != 0) {
                successCount = successCount + 1;
            }
        }
        return insertUpdateList.size() - successCount;
    }

    private <T> int[] insertUpdateRecords(String query, List<T> lstOfProcessMain) {
        String sql = getQuery(query);
        int[] returnArray = new int[] {};
        List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();

        lstOfProcessMain.forEach(t -> parameters.add(new BeanPropertySqlParameterSource(t)));
        returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

        return returnArray;
    }

    @Override
    public Integer updateOMSModules(List<OmsModules> updateList) throws Exception {
        final String sql = getQuery("UPDATE_OMS_MODULES");
        int[] returnArray = new int[] {};
        List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
        updateList.forEach(t -> parameters.add(new BeanPropertySqlParameterSource(t)));
        returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

        return returnArray != null ? returnArray.length : 0;
    }

    @Override
    public Integer deleteOMSModuleReports(List<OmsModules> deleteList) throws Exception {
        final String sql = getQuery("DELETE_OMS_MODULE_REPORTS");
        int[] returnArray = new int[] {};
        List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
        deleteList.forEach(t -> parameters.add(new BeanPropertySqlParameterSource(t)));
		try {
			String tableName = "oms_module_report";
			String whereCondition = "MODULE_NAME = :moduleName";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
        returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

        return returnArray != null ? returnArray.length : 0;
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    @Override
    public String getParameterSql(String moduleName, String parameterName, Integer parameterSeq) {
        final String sql = getQuery("GET_OMS_MODULE_PARAMETERS_SQL");
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("MODULE_NAMES", moduleName);
        parameters.addValue("PARAMETER_NAME", parameterName);
        parameters.addValue("PARAMETER_SEQ", parameterSeq);
        return namedParameterJdbcTemplate.queryForObject(sql, parameters, String.class);
    }

    @Override
    public List<ReferenceCodes> getCodeList(String parameterSql) {
        final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(parameterSql,
                ReferenceCodes.class, referenceCodesMapping);
        List<ReferenceCodes> codeList = namedParameterJdbcTemplate.query(parameterSql, referenceCodeRowMapper);
        return codeList;
    }

    @Override
    public Long getMaxParameterSeqByModuleName(String moduleName) {
        final String sql = getQuery("GET_MAX_PARAMETER_SEQ_BY_MODULE_NAME");
        return namedParameterJdbcTemplate.queryForObject(sql, createParams("MODULENAME", moduleName), Long.class);
    }

    @Override
    public List<ReferenceCodes> getInstituteList() {
        final String sql = getQuery("GET_INSTITUTION_SQL");
        final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
                ReferenceCodes.class, referenceCodesMapping);
        List<ReferenceCodes> codeList = namedParameterJdbcTemplate.query(sql, referenceCodeRowMapper);
        return codeList;
    }

    @Override
    public OmsReportAsset getOmsAssetByCode(String assetCode) {
        final String sql = getQuery("GET_ASSET_BY_ASSET_CODE");
        final RowMapper<OmsReportAsset> rowMap = Row2BeanRowMapper.makeMapping(sql, OmsReportAsset.class, assetMapping);
        List<OmsReportAsset> listAssets = namedParameterJdbcTemplate.query(sql, createParams("ASSETCODE", assetCode),
                rowMap);
        if (null != listAssets && !listAssets.isEmpty()) {
            return listAssets.get(0);
        }
        return null;
    }

    @Override
    public List<OmsReportAsset> getAllOmsAssets() {
        final String sql = getQuery("GET_ALL_ASSET");
        final RowMapper<OmsReportAsset> rowMap = Row2BeanRowMapper.makeMapping(sql, OmsReportAsset.class, assetMapping);
        return namedParameterJdbcTemplate.query(sql,
                rowMap);
    }

    @Override
    public String insertUpdateOmsAsset(OmsReportAsset asset) {
        int[] insertUpdateRecords = null;
        List<OmsReportAsset> listAsset = Arrays.asList( asset);
        boolean assetExist = assetExist(asset.getAssetCode());
        if(assetExist) {
            insertUpdateRecords = insertUpdateRecords("UPDATE_OMS_ASSET",listAsset);
        }else {
            insertUpdateRecords = insertUpdateRecords("INSERT_OMS_ASSET",listAsset);
        }
        
        if(insertUpdateRecords.length>0 && insertUpdateRecords[0]>0) {
            return "Asset Inserted/Updated Sucessfully";
        }
        return "Asset Insertion/Updation Failed";
    }
    
    private boolean assetExist(String assetCode) {
        final String sql = getQuery("EXIST_OMS_ASSET_BY_CODE");
        Long queryForObject = namedParameterJdbcTemplate.queryForObject(sql, createParams("ASSETCODE", assetCode),
                Long.class);
        return (null != queryForObject && queryForObject > 0);
    }

    @Override
    public List<OmsReportAsset> getOmsAssetsByCodes(List<String> assets) {
        final String sql = getQuery("GET_ASSETS_BY_CODES");

        HashSet<String> assetCodeSet = new HashSet<>(assets);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("ASSET_CODES", assetCodeSet);

        final RowMapper<OmsReportAsset> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsReportAsset.class,
                assetMapping);
        return namedParameterJdbcTemplate.query(sql, parameters, columnRowMapper);
    }
    

}