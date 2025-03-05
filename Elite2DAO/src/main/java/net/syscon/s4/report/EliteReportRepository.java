package net.syscon.s4.report;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.syscon.s4.common.beans.OmsModuleParameter;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.OmsModuleParameters;
import net.syscon.s4.im.beans.OmsModules;

/**
 * Interface OumcpassRepository
 */
public interface EliteReportRepository {

    List<EliteReport> getReportForModules(List<String> modules);

    List<EliteReport> getNameBodyForModules(List<String> listModules);

    Map<String, Boolean> saveReportsForModules(List<EliteReport> listReports) throws Exception;
    
    Long getHistoryReportId();

    List<EliteReport> getAllJRReports(String userId);

    List<OmsModuleParameters> populateRecords(final String moduleName);
    
    JasperPrint fillReport(JasperReport jasperReport, Map<String, Object> parameters) throws JRException, SQLException;

    List<OmsModuleParameter> getAllModuleParameters(List<String> modules);

    Map<String, Boolean> insertOmsModuleList(List<OmsModuleParameter> listOmsModParam);

    Map<String, Boolean> deleteOmsModuleList(List<OmsModuleParameter> listOmsModParam);

	String getParameterSql(String moduleName, String parameterName, Integer parameterSeq);

	List<ReferenceCodes> getCodeList(String parameterSql);
	
	List<OmsModuleParameter> getOmsParameterNameByModuleName(String moduleName);

    Integer insetUpdateDeleteOmsModuleParams(List<OmsModuleParameter> insertList,String operation);
    
    Integer updateOMSModules(List<OmsModules> updateList) throws Exception;
    
    Integer deleteOMSModuleReports(List<OmsModules> deleteList) throws Exception;

    Long getMaxParameterSeqByModuleName(String moduleName);

	List<ReferenceCodes> getInstituteList();

    OmsReportAsset getOmsAssetByCode(String asset);
    
    List<OmsReportAsset> getOmsAssetsByCodes(List<String> assets);

    List<OmsReportAsset> getAllOmsAssets();

    String insertUpdateOmsAsset(OmsReportAsset asset);

}
