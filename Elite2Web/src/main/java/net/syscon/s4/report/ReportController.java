package net.syscon.s4.report;

import java.io.File;
import java.math.BigDecimal;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import net.syscon.s4.common.DocManageUtilities;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.MessageDTO;
import net.syscon.s4.common.beans.OmsModuleParametersCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.ReportInputControll;
import net.syscon.s4.common.beans.ReportObject;
import net.syscon.s4.im.beans.IwpDocuments;
import net.syscon.s4.im.beans.OmsModuleParameters;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.sa.admin.beans.OmsModulesCommitBean;

@EliteController
public class ReportController {

    /**
     * Logger object used to print the log in the file
     */
    private static Logger logger = LogManager.getLogger(ReportController.class.getName());
    @Autowired
    private Environment env;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EliteReportService eliteReportService;

    @Autowired
    private DocManageUtilities docManageUtilities;

    @RequestMapping(value = "/report/runReport/{reportId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    @PreAuthorize("hasEliteReportRole('read', #reportId)")
    public ResponseEntity<byte[]> runReport(@PathVariable("reportId") String reportId,
            @RequestParam("reportpath") String reportpath, @RequestParam("inputParam") String inputParams) {
        String[] inputReportParameters = null;
        String paramter = "";
        if (null != inputParams && inputParams.contains(",")) {
            inputReportParameters = inputParams.split(",");
        }
        int i = 0;
        if (inputReportParameters != null) {
            paramter = "?";
            for (String inputReprtParmater : inputReportParameters) {
                if (inputReprtParmater.contains(":")) {
                    String[] parameters = inputReprtParmater.split(":");
                    String parameterKey = parameters[0];
                    String parameterValue = null != parameters && parameters.length == 2 ? parameters[1] : "";
                    if (i == 0) {
                        paramter = paramter + parameterKey + "=" + parameterValue;
                    } else {
                        paramter = paramter + "&" + parameterKey + "=" + parameterValue;
                    }
                    i++;
                }
            }
        }

        ByteArrayHttpMessageConverter byteArrayHttpMessageConverter = new ByteArrayHttpMessageConverter();

        List<MediaType> supportedApplicationTypes = new ArrayList<MediaType>();
        MediaType pdfApplication = new MediaType("application", "pdf");
        supportedApplicationTypes.add(pdfApplication);

        byteArrayHttpMessageConverter.setSupportedMediaTypes(supportedApplicationTypes);
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(byteArrayHttpMessageConverter);
        restTemplate.setMessageConverters(messageConverters);
        HttpEntity<String> request = new HttpEntity<String>(prepareHeaders());
        String url = this.getJasperUri() + "reports" + reportpath + ".pdf" + paramter;
        ResponseEntity<byte[]> response = restTemplate.exchange(url, HttpMethod.GET, request, byte[].class);
        return response;
    }

    @RequestMapping(value = "/report/runReportHTML/{reportId}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<byte[]> runReportHTML(@PathVariable("reportId") String reportId,
            @RequestParam("reportpath") String reportpath, @RequestParam("inputParam") String inputParams) {
        String[] inputReportParameters = null;
        String paramter = "";
        if (null != inputParams && inputParams.contains(",")) {
            inputReportParameters = inputParams.split(",");
        }
        int i = 0;
        if (inputReportParameters != null) {
            paramter = "?";
            for (String inputReprtParmater : inputReportParameters) {
                if (inputReprtParmater.contains(":")) {
                    String[] parameters = inputReprtParmater.split(":");
                    String parameterKey = parameters[0];
                    String parameterValue = null != parameters && parameters.length == 2 ? parameters[1] : "";
                    if (i == 0) {
                        paramter = paramter + parameterKey + "=" + parameterValue;
                    } else {
                        paramter = paramter + "&" + parameterKey + "=" + parameterValue;
                    }
                    i++;
                }
            }
        }

        ByteArrayHttpMessageConverter byteArrayHttpMessageConverter = new ByteArrayHttpMessageConverter();

        List<MediaType> supportedApplicationTypes = new ArrayList<MediaType>();
        MediaType pdfApplication = new MediaType("text", "html");
        supportedApplicationTypes.add(pdfApplication);

        byteArrayHttpMessageConverter.setSupportedMediaTypes(supportedApplicationTypes);
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(byteArrayHttpMessageConverter);
        restTemplate.setMessageConverters(messageConverters);
        HttpEntity<String> request = new HttpEntity<String>(prepareHeaders());
        String url = this.getJasperUri() + "reports" + reportpath + ".html" + paramter;
        ResponseEntity<byte[]> response = restTemplate.exchange(url, HttpMethod.GET, request, byte[].class);
        return response;
    }

    // @PreAuthorize("hasEliteReportRole('read')")
    @RequestMapping(value = "/report/allReports", method = RequestMethod.GET)
    public List<ReportObject> viewAllReports() {
        HttpEntity<String> request = new HttpEntity<String>(prepareHeaders());
        String url = this.getJasperUri() + "resources?type=reportUnit&folderUri=" + this.getReportFolder();
        ResponseEntity<ReportObject[]> response = null;
        try {
            response = new RestTemplate().exchange(url, HttpMethod.GET, request, ReportObject[].class);
        } catch (RestClientException ex) {
            if (ex.getCause() instanceof ConnectException) {
                logger.error("Report Server is down ");
            }
        }
        List<ReportObject> reportList = new ArrayList<>();
        if (response != null) {
            reportList = Arrays.asList(response.getBody());
        }
        return reportList;
    }

    @RequestMapping(value = "/report/inputControlls", method = RequestMethod.GET)
    public List<ReportInputControll> inputControlls(@RequestParam("reportpath") String reportpath) {
        HttpEntity<String> request = new HttpEntity<String>(prepareHeaders());
        String url = this.getJasperUri() + "reports" + reportpath + "/inputControls";
        ResponseEntity<ReportInputControll[]> response = null;
        try {
            response = new RestTemplate().exchange(url, HttpMethod.GET, request, ReportInputControll[].class);
        } catch (Exception ex) {
            logger.error("Error id fetching Report inputControlls " + ex);
        }
        List<ReportInputControll> inputControlls = new ArrayList<>();
        if (response.getBody() != null) {
            inputControlls = Arrays.asList(response.getBody());
        }
        return inputControlls;
    }

    private String getJasperUri() {
        return this.env.getProperty("app.report.jasper.server.uri");
    }

    private String getReportFolder() {
        return this.env.getProperty("app.report.jasper.server.report.folder");
    }

    

    

    @RequestMapping(value = "/report/allJRReports", method = RequestMethod.GET)
    public List<EliteReport> viewAllJRReports() {
    	String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        List<EliteReport> reportList = eliteReportService.getAllJRReports(userName);
        return reportList.isEmpty() ? new ArrayList<>() : reportList;
    }

    @RequestMapping(value = "/report/inputParams", method = RequestMethod.GET)
    public List<OmsModuleParameters> getReportParameters(@RequestParam("moduleName") String moduleName) {
        List<OmsModuleParameters> inputParamList = null;
        try {
            inputParamList = eliteReportService.populateRecords(moduleName);
            return inputParamList.isEmpty() ? new ArrayList<>() : inputParamList;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            logger.error("Exception :", e);
            e.printStackTrace();
            return inputParamList;
        }

    }

    /*
     * public @ResponseBody Map<String, List<JasperReportParameter>>
     * getReportParameters(
     * HttpServletRequest httpServletRequest,
     * 
     * @RequestBody List<String> moduleList) {
     * 
     * Map<String, List<JasperReportParameter>> jsperParameters = new HashMap<>();
     * try {
     * String savedLocation = httpServletRequest.getSession().getServletContext()
     * .getRealPath("/WEB-INF/classes/elitereport-exp/");
     * if (!new File(savedLocation).exists()) {
     * new File(savedLocation).mkdir();
     * }
     * jsperParameters = eliteReportService.getReportParameters(moduleList,
     * savedLocation);
     * } catch (Exception e) {
     * logger.error("Exception :", e);
     * return jsperParameters;
     * 
     * }
     * return jsperParameters;
     * }
     */

    @RequestMapping(value = "/report/importJRXmls", method = RequestMethod.POST)
    public Map<String, Boolean> uploadJrDocument(HttpServletRequest httpServletRequest,
            @RequestParam("importFile") List<MultipartFile> fileList,
            @RequestParam("moduleList") List<String> moduleList) {

        String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Map<String, Boolean> importedPrecesses = new HashMap<>();
        try {
            if (!fileList.isEmpty() && (fileList.size() == moduleList.size())) {
                importedPrecesses = eliteReportService.importProcesses(fileList, moduleList, userName);
            }
        } catch (Exception e) {
            logger.error("Exception :", e);
            return importedPrecesses;

        }
        return importedPrecesses;
    }

    @RequestMapping(value = "/report/open", method = RequestMethod.POST)
    public ResponseEntity openReport(HttpServletRequest httpServletRequest,
            @RequestBody EliteReportInputs eliteReportInputs) throws Exception {
        String savedLocation = httpServletRequest.getSession().getServletContext()
                .getRealPath("/WEB-INF/classes/elitereport-exp/");
        byte[] outFile = null;
        HttpHeaders headerRes = new HttpHeaders();
        try {
        	if (!new File(savedLocation).exists()) {
                new File(savedLocation).mkdir();
            }
			outFile = eliteReportService.openReport(savedLocation, eliteReportInputs);
		} catch (Exception e) {
			e.printStackTrace();
			// return new ResponseEntity<>(outFile, headerRes, HttpStatus.INTERNAL_SERVER_ERROR);
			throw e;
		}
        headerRes = docManageUtilities.getHttpHeadersForFileUpload(".pdf", null, true);
        EliteReport report = new EliteReport();
        report.setReportBody(outFile);
        return new ResponseEntity<>(outFile, headerRes, HttpStatus.OK);
    }
    
    
    @PostMapping(value = "/report/open/nonpdf", produces = MediaType.ALL_VALUE)
	public ResponseEntity openNonPdfReport(HttpServletRequest httpServletRequest,
            @RequestBody EliteReportInputs eliteReportInputs) throws Exception {
		ResponseEntity repsonseEntity = null;
		byte[] byteArray = null;
		HttpHeaders headerRes = new HttpHeaders();
		String savedLocation = httpServletRequest.getSession().getServletContext().getRealPath("/WEB-INF/classes/TRIM/");
		String fileName = eliteReportInputs.getModuleName();
		String fileExt = "."+eliteReportInputs.getReportType().toLowerCase();
		
		try {
			byteArray = eliteReportService.openReport(savedLocation, eliteReportInputs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(byteArray, headerRes, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		headerRes = docManageUtilities.getHttpHeadersForFileUpload(fileExt, fileName, true);
		repsonseEntity = new ResponseEntity<>(byteArray, headerRes, HttpStatus.OK);
		return repsonseEntity;
	}
    
    
    
    
    
    
    
    
    

    @RequestMapping(value = "/report/conflictParameters", method = RequestMethod.GET)
    public @ResponseBody Map<String, List<Map<String, String>>> getReportParameters(
            HttpServletRequest httpServletRequest, @RequestParam("moduleName") String moduleName) {

        Map<String, List<Map<String, String>>> confliactParamMap = new HashMap<>();
        try {
            confliactParamMap = eliteReportService.getMismatchParameters(moduleName);
        } catch (Exception e) {
            logger.error("Exception :", e);
            return confliactParamMap;

        }
        return confliactParamMap;
    }

    /**
     * Performing basic functions insert,delete, update into
     * the database table
     * 
     * @return Integer
     * @Param commitBean
     */
    @RequestMapping(value = "/report/moduleparameters", method = RequestMethod.POST)
    public @ResponseBody Map<String, String> addUpdateDeleteOmsParameters(@RequestBody final OmsModuleParametersCommitBean commitBean) {
        Map<String,String> results = new HashMap<>(1);
        try {
            String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
            commitBean.setCreateUserId(userName);
            results = eliteReportService.insertUpdateDeleteOfOmsParameters(commitBean,userName);

        } catch (Exception e) {
            logger.error("addUpdateDeleteOmsParameters: ", e);
        }
        return results;
    }
    
    /**
     * Performing basic functions insert,delete, update into Report
     * database table
     * 
     * @return Integer
     * @Param commitBean
     */
    @RequestMapping(value = "/report/commitReport", method = RequestMethod.POST)
    public @ResponseBody Integer commitReportChanges(@RequestBody final OmsModulesCommitBean commitBean) throws Exception {
        int results = 0;
        try {
            String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
            commitBean.setCreateUserId(userName);
            results = eliteReportService.commitReportChanges(commitBean);

        } catch (Exception e) {
            logger.error("addUpdateDeleteOmsParameters: ", e);
            throw new Exception(e);
        }
        return results;
    }

    private HttpHeaders prepareHeaders() {
        String userName = this.env.getProperty("app.report.jasper.server.user");
        String password = this.env.getProperty("app.report.jasper.server.pwd");
        String plainCreds = userName + ":" + password;
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        headers.add("Content-Type", "applicaion/json");
        return headers;
    }
    
	@RequestMapping(value = "/report/parameterCodeList", method = RequestMethod.GET)
	public List<ReferenceCodes> getParameterCodeList(@RequestParam("moduleName") String moduleName,
			@RequestParam("parameterName") String parameterName, @RequestParam("parameterSeq") Integer parameterSeq,
			@RequestParam("parentLov") Optional<String> parentField) {
		List<ReferenceCodes> codeList = null;
		try {
			codeList = eliteReportService.getParameterSql(moduleName, parameterName, parameterSeq, parentField);

		} catch (Exception e) {
			logger.error("Exception :", e);
			e.printStackTrace();
		}
		return codeList;
	}
	
	
	
    
}
