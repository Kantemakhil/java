package net.syscon.s4.report;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.OmsModuleParametersCommitBean;
import net.syscon.s4.im.beans.OmsModuleParameters;
import net.syscon.s4.report.impl.JasperReportParameter;
import net.syscon.s4.sa.admin.beans.OmsModulesCommitBean;

/**
 * Interface EliteReportService
 */
public interface EliteReportService {

	byte[] exportReports(List<String> modules, String savedLocation) throws Exception;

    /**
     * Import Report file in jrxml and zip formats.
     * 
     * @param importFile imported files.
     * @param savedLocation server Temp location.
     * @param userName 
     * @return
     */
    Map<String, Boolean> importProcesses(MultipartFile importFile, String savedLocation, String userName) throws Exception;
    
    Map<String, Boolean> importProcesses(List<MultipartFile> fileList, List<String> moduleList , String userName) throws Exception;

    List<EliteReport> getAllJRReports(String userName);

    Map<String, List<JasperReportParameter>> getReportParameters(List<String> moduleList) throws Exception;
    
    List<OmsModuleParameters> populateRecords(final String moduleName) throws Exception;
    
    byte[] openReport(final String savedLocation, EliteReportInputs eliteReportInputs) throws Exception;

    List<ReferenceCodes> getParameterSql(String moduleName, String parameterName, Integer parameterSeq, Optional<String> parentField);

    Map<String, List<Map<String, String>>> getMismatchParameters(String moduleName) throws Exception;
    
    Map<String, String> insertUpdateDeleteOfOmsParameters(OmsModuleParametersCommitBean commitBean, String userName);
    
    int commitReportChanges(final OmsModulesCommitBean commitBean) throws Exception;

    OmsReportAsset getAsset(String asset);

    List<OmsReportAsset> getAssets();

    String addUpdateAsset(MultipartFile assetMultipart,String userName,String assetCode) throws Exception;


}
