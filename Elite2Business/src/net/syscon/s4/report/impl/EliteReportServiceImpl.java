package net.syscon.s4.report.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.ElementKeyExporterFilterFactory;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleCsvExporterConfiguration;
import net.sf.jasperreports.export.SimpleCsvReportConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterConfiguration;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import net.sf.jasperreports.export.WriterExporterOutput;
import net.sf.jasperreports.export.XlsMetadataReportConfiguration;
import net.syscon.s4.cm.intakeclosure.OcdintakRepository;
import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.Utilities;
import net.syscon.s4.common.beans.OmsModuleParameter;
import net.syscon.s4.common.beans.OmsModuleParametersCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.genericservices.CustomException;
import net.syscon.s4.genericservices.ErrorCodes;
import net.syscon.s4.im.beans.OmsModuleParameters;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.report.EliteReport;
import net.syscon.s4.report.EliteReportInputs;
import net.syscon.s4.report.EliteReportRepository;
import net.syscon.s4.report.EliteReportService;
import net.syscon.s4.report.OmsReportAsset;
import net.syscon.s4.report.ReportType;
import net.syscon.s4.sa.admin.beans.OmsModulesCommitBean;

@Service
public class EliteReportServiceImpl extends BaseBusiness implements EliteReportService {

    @Autowired
    EliteReportRepository eliteReportRepository;
    
    @Autowired
   	private OcdintakRepository ocdintakRepository;
     private static String BACK_SLASH = "/";
	
	private static String STYLE_TEMPLATES = "StyleTemplates";
    private static Logger logger = LogManager.getLogger(EliteReportServiceImpl.class.getName());

    @Override
    public byte[] exportReports(List<String> modules, String savedLocation) throws Exception {
        List<EliteReport> reportForModules = eliteReportRepository.getReportForModules(modules);
        byte[] outZip = null;
        String respJson = null;
        String jsonFileName = "exportedParam.json";

        // Getting oms module params
        List<String> listExitingModules = reportForModules.stream().map(report -> report.getModuleName())
                .collect(Collectors.toList());
        List<OmsModuleParameter> listOmsModuleParam = eliteReportRepository.getAllModuleParameters(listExitingModules);

        Map<String, List<OmsModuleParameter>> listParams = listOmsModuleParam.stream()
                .collect(Collectors.groupingBy(OmsModuleParameter::getModuleName));

        // tracks the created files for deletion
        List<File> fileList = new ArrayList<>();

        try {
            Iterator<EliteReport> listReports = reportForModules.iterator();
            while (listReports.hasNext()) {
                EliteReport eliteReport = listReports.next();

                // Create an xml file
                File jrxmlFile = new File(savedLocation + eliteReport.getReportFileName());
                fileList.add(jrxmlFile);

                FileOutputStream fo = new FileOutputStream(jrxmlFile);

                // write to xml file
                fo.write(eliteReport.getReportBody());

                // save and close the file
                fo.flush();
                fo.close();
                eliteReport.setReportBody(null);
            }

            // Create the json object from list and remove null values
            respJson = new ObjectMapper().setSerializationInclusion(Include.NON_NULL).writeValueAsString(listParams);

            // Create a json file
            File jsonFile = new File(savedLocation + jsonFileName);
            fileList.add(jsonFile);
            FileOutputStream fo = new FileOutputStream(jsonFile);

            // write to json file
            fo.write(respJson.getBytes());

            // save and close the file
            fo.flush();
            fo.close();

            outZip = Utilities.compressFiles(fileList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        } finally {
            if (!fileList.isEmpty()) {
                Iterator<File> iter = fileList.iterator();
                while (iter.hasNext()) {
                    Path filePath = iter.next().toPath();
                    // delete files
                    Files.deleteIfExists(filePath);
//                  iter.next().delete();
                }
            }
        }
        return outZip;
    }

    @Override
    public Map<String, Boolean> importProcesses(MultipartFile importFile, String savedLocation, String userName)
            throws Exception {
        // tracks the created files for deletion
        List<File> fileList = new ArrayList<>();
        Map<String, Boolean> outStatus = new HashMap<>();

        // importFile.get
        String fileExt = FilenameUtils.getExtension(importFile.getOriginalFilename());
        List<EliteReport> listReports = new ArrayList<>();
        try {
            if (fileExt.equalsIgnoreCase("zip")) {
                // extract the zip file
                fileList = Utilities.unZipFiles(importFile, savedLocation);
                  logger.info("Imported zip file is extracted");
                for (File file : fileList) {

                    // getting extension of file inside zip
                    String fileExtensen = FilenameUtils.getExtension(file.getName());
                    logger.info("Imported  fileExtension"+fileExtensen);
                    if (fileExtensen.equalsIgnoreCase("json")) {
                        TypeReference<Map<String, List<OmsModuleParameter>>> type = new TypeReference<Map<String, List<OmsModuleParameter>>>() {
                        };
                        Map<String, List<OmsModuleParameter>> data = new ObjectMapper().readValue(file, type);
                        List<OmsModuleParameter> listOmsModParam = new ArrayList<>();
                        data.forEach((key, value) -> {
                            value.forEach(mod -> mod.setCreateUserId(userName));
                            listOmsModParam.addAll(value);
                        });
                        listOmsModParam.forEach(ele->ele.setModifyUserId(userName));
                        eliteReportRepository.deleteOmsModuleList(listOmsModParam);
                        eliteReportRepository.insertOmsModuleList(listOmsModParam);
                    } else if (fileExtensen.equalsIgnoreCase("jrxml")) {
                        EliteReport report = new EliteReport();
                        String moduleName = FilenameUtils.getBaseName(file.getName());
                        if(moduleName!=null) {
                        	report.setModuleName(moduleName.toUpperCase());
                        }
                        report.setReportFileName(file.getName());
                        report.setReportVersion(1L);
                        byte[] jsrFileBytes = Files.readAllBytes(Paths.get(savedLocation + file.getName()));
                        if(!parseJasperFileByteData(jsrFileBytes)) {
                        	logger.info("Unable to Parse the file");
                        	outStatus.put("isAllowed", false);
                        	return outStatus;
                        }
                        report.setReportBody(jsrFileBytes);
                        report.setModifyUserId(userName);
                        report.setCreateUserId(userName);
                        listReports.add(report);
                    }
                }

            } else {
            	 if(!parseJasperFileByteData(importFile.getBytes())) {
            		 logger.info("Unable to Parse the file");
                 	outStatus.put("isAllowed", false);
                 	return outStatus;
                 }
                EliteReport report = new EliteReport();
                String moduleName = FilenameUtils.getBaseName(importFile.getOriginalFilename());
                if(moduleName!=null) {
                	report.setModuleName(moduleName.toUpperCase());
                }
                report.setReportFileName(importFile.getOriginalFilename());
                report.setReportVersion(1L);
                report.setReportBody(importFile.getBytes());
                report.setModifyUserId(userName);
                report.setCreateUserId(userName);
                listReports.add(report);
            }

            if (!listReports.isEmpty()) {
                outStatus = eliteReportRepository.saveReportsForModules(listReports);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        } finally {

            if (!fileList.isEmpty()) {
                Iterator<File> iter = fileList.iterator();
                while (iter.hasNext()) {
                    Path filePath = iter.next().toPath();
                    // delete files
                    Files.deleteIfExists(filePath);
//                  iter.next().delete();
                }
            }
        }
        return outStatus;
    }
    
    private boolean parseJasperFileByteData(byte[] reportBody) throws JRException, SQLException {
        Map<String,Object> parameteres=new HashMap<>();
        String basePath = this.getClass().getResource(BACK_SLASH + STYLE_TEMPLATES).getPath();
        parameteres.put("templateLocation", basePath);
        JasperPrint jasperPrint = null;
          JasperReport jasperReport;
          InputStream reportInStream = new ByteArrayInputStream(reportBody);
			jasperReport = JasperCompileManager.compileReport(reportInStream);
			jasperPrint = eliteReportRepository.fillReport(jasperReport, parameteres);
		
       if(jasperPrint == null) {
    	   return false;
       }
        return true;

    }


    @Override
    public List<EliteReport> getAllJRReports(String userName) {
        return eliteReportRepository.getAllJRReports(userName);
    }

    @Override
    public Map<String, Boolean> importProcesses(List<MultipartFile> fileList, List<String> moduleList, String userName)
            throws Exception {

        Map<String, Boolean> output = null;
        List<EliteReport> listReports = new ArrayList<>();

        try {
            int index = 0;
            for (MultipartFile multipartFile : fileList) {
                EliteReport report = new EliteReport();
                report.setModuleName(moduleList.get(index++));
                report.setReportFileName(multipartFile.getOriginalFilename());
                report.setReportVersion(1L);
                report.setReportBody(multipartFile.getBytes());
                report.setModifyUserId(userName);
                report.setCreateUserId(userName);
                listReports.add(report);
            }
            output = eliteReportRepository.saveReportsForModules(listReports);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return output;
    }

    @Override
    public byte[] openReport(final String savedLocation, EliteReportInputs eliteReportInputs) throws Exception {
    	File styleFile = null;
    	byte[] reportOutput = null;
        try {
        	String basePath = this.getClass().getResource(BACK_SLASH + STYLE_TEMPLATES).getPath();
	    	String moduleName = eliteReportInputs.getModuleName();
	        final Map<String, Object> parameteres = new HashMap<String, Object>();
	        
	        List<String> modules = new ArrayList<String>();
	        modules.add(moduleName);
	        List<EliteReport> reportForModules = eliteReportRepository.getReportForModules(modules);
	        if (null != reportForModules && reportForModules.size() > 0) {
	            Map<String, String> jasperByteParam = parseJasperByteParametes(reportForModules.get(0).getReportBody());
	
	            if(!jasperByteParam.isEmpty()) {
	            ArrayList<String> paramNames = new ArrayList<String>(jasperByteParam.keySet());
	            
	            
	            List<OmsReportAsset> omsAssetsByCodes = eliteReportRepository.getOmsAssetsByCodes(paramNames);
	            
	            if (null == omsAssetsByCodes || omsAssetsByCodes.size() < paramNames.size()) {
	                throw new CustomException(HttpStatus.BAD_REQUEST, ErrorCodes.REPORT_ASSETS_MISSING_ERROR);
	            }
	            
	            for (OmsReportAsset asset : omsAssetsByCodes) {
	
	                if (jasperByteParam.containsKey(asset.getAssetCode())) {
	                    String type = jasperByteParam.get(asset.getAssetCode());
	                    if (type.equals("java.io.InputStream")) {
	                        InputStream targetStream = new ByteArrayInputStream(asset.getAssetBody());
	                        parameteres.put(asset.getAssetCode(), targetStream);
	                    } else if (type.equals("java.io.File")) {
	                        styleFile = new File(savedLocation+asset.getAssetsFilename());
	                        try {
	                            FileUtils.writeByteArrayToFile(styleFile, asset.getAssetBody());
	                        } catch (IOException e) {
	                            throw new CustomException(HttpStatus.BAD_REQUEST,ErrorCodes.REQUEST_INPUT_ERROR,"Asset file is invalid for code : "+asset.getAssetCode());
	                        }
	                        parameteres.put(asset.getAssetCode(), styleFile);
	                    }
	                }
	
	            }
	
	        }}
	        
	
	        eliteReportInputs.getParamValues().forEach((inputParam) -> {
	        	if(inputParam.getParamType().equalsIgnoreCase("DATE")){
	        		try {
	        			Date date1= new  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(inputParam.getParamValue().toString());
						parameteres.put(inputParam.getParamKey(), date1);
					} catch (ParseException e) {
						e.printStackTrace();
					}
	        		
	        	} else {
	        		parameteres.put(inputParam.getParamKey(), inputParam.getParamValue());
	        	}
	            
	        });
	        
	        parameteres.put("templateLocation", basePath);
	        
	        reportOutput = this.generateReport(savedLocation,parameteres, null,eliteReportInputs.getReportType(),reportForModules);
        } catch(Exception ex) {
        	logger.error("Error in openReport ",ex.getMessage());
        	ex.printStackTrace();
        	throw new CustomException(ex.getMessage());
        } finally {
        	if(styleFile!=null ) {
        		//Files.deleteIfExists(Paths.get(savedLocation));
        	}
        }
        return reportOutput;
    }
    
    private Map<String,String> parseJasperByteParametes(byte[] reportBody)
            throws IOException, SAXException, ParserConfigurationException, XPathExpressionException {
        Map<String,String> listOmsModParam =new HashMap<>();

        // Prasing the jrxml bytes
        try (InputStream targetStream = new ByteArrayInputStream(reportBody);) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            Document doc = factory.newDocumentBuilder().parse(targetStream);
            doc.getDocumentElement().normalize();

            // Building Xpath
            XPath xPath = XPathFactory.newInstance().newXPath();
            String expression = "jasperReport/parameter";
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
            
            for (int i = 0; i < nodeList.getLength(); i++) {
            	Node node = nodeList.item(i);
                if (node instanceof Element) {
                    Element child = (Element) node;
                    if ((null != child && null != child.getAttribute("class")) &&
                            (child.getAttribute("class").equals("java.io.InputStream")
                                    || child.getAttribute("class").equals("java.io.File"))) {
                        listOmsModParam.put(child.getAttribute("name"), child.getAttribute("class"));
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

        return listOmsModParam;

    }

    public byte[] generateReport(final String savedLocation,
            final Map<String, Object> parameteres, final List<?> fields, String reportType, List<EliteReport> reportForModules)
            throws Exception {
        byte[] returnReport = null;
        JasperPrint jasperPrint = null;
        try {
            
        	logger.info("EliteReportServiceImpl.generateReport parameteres :: {}", parameteres);
            // File jrxmlFile = new File(savedLocation +
            // reportForModules.get(0).getReportFileName());
            // FileOutputStream fo = new FileOutputStream(jrxmlFile);

            // write to xml file
            // fo.write(eliteReport.getReportBody());

            // save and close the file
            // fo.flush();
            // fo.close();
            logger.info("EliteReportServiceImpl.generateReport reportForModules :: {}", reportForModules);
            InputStream reportInStream = new ByteArrayInputStream(reportForModules.get(0).getReportBody());
            final JasperReport jasperReport = JasperCompileManager.compileReport(reportInStream);
            logger.info("EliteReportServiceImpl.generateReport compiled report :: {}", jasperReport);

            //if (fields != null && !fields.isEmpty()) {
            //    jasperPrint = JasperFillManager.fillReport(jasperReport, parameteres,
            //            new JRBeanCollectionDataSource(fields));
            //} else {
            //    jasperPrint = eliteReportRepository.fillReport(jasperReport, parameteres);
            //}
          
            if (null != reportType && reportType.equals(ReportType.EXCEL.name()) || reportType.equals(ReportType.CSV.name())) {
            	parameteres.put(JRParameter.IS_IGNORE_PAGINATION, true);
            	parameteres.put(XlsMetadataReportConfiguration.PROPERTY_WRITE_HEADER, false);
            	parameteres.put(ElementKeyExporterFilterFactory.PROPERTY_EXCLUDED_KEY_PREFIX, "exclude.origin.band.1");
            	
            	//parameteres.put(JRParameter.he, true);
            }
            
            jasperPrint = eliteReportRepository.fillReport(jasperReport, parameteres);
            logger.info("EliteReportServiceImpl.generateReport jasperPrint :: {}", jasperPrint);
            
            returnReport = exportReportType(jasperPrint,reportType);
        } catch (final Exception e) {
        	logger.error("EliteReportServiceImpl.generateReport :: {}", e);
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR,ErrorCodes.REPORT_PROCESSING_ERROR);
        }
        return returnReport;
    }

    private byte[] exportReportType(JasperPrint jasperPrint, String reportType) throws JRException {
        byte[] returnReport = null;
        if (null != reportType && reportType.equals(ReportType.EXCEL.name())) {
        	
            // Create an instance of JRXlsExporter
            JRXlsExporter exporter = new JRXlsExporter();

            
            JRCsvExporter exporterOne = new JRCsvExporter();
            exporterOne.setExporterInput(new SimpleExporterInput(jasperPrint));
            
            // Set the input JasperPrint object
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));

            // Set the Excel-specific parameters
            SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
            configuration.setOnePagePerSheet(false);
            configuration.setDetectCellType(true);
            configuration.setCollapseRowSpan(false);
            
            exporter.setConfiguration(configuration);
           
            // Export the report to a byte array
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
            exporter.exportReport();

            // Get the byte array
            returnReport = outputStream.toByteArray();
        }  else if (null != reportType && reportType.equals(ReportType.CSV.name())) {
        
        	// Create an instance of JRXlsExporter
        	JRCsvExporter exporter = new JRCsvExporter();  
            // Set the input JasperPrint object
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));

            // Set the Excel-specific parameters
            SimpleCsvReportConfiguration configuration =new SimpleCsvReportConfiguration(); 
            exporter.setConfiguration(configuration);
           
            // Export the report to a byte array
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            exporter.setExporterOutput(new SimpleWriterExporterOutput(outputStream));
            exporter.exportReport();

            // Get the byte array
            returnReport = outputStream.toByteArray();
            
        
        } else if (null != reportType && reportType.equals(ReportType.HTML.name())) {
            // Create an instance of JRHtmlExporter
            HtmlExporter exporter = new HtmlExporter();

            // Set the input JasperPrint object
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));

            // Set the HTML-specific parameters
            SimpleHtmlExporterConfiguration configuration = new SimpleHtmlExporterConfiguration();
            configuration.setHtmlHeader("<h1>Report</h1>");
            exporter.setConfiguration(configuration);

            // Export the report to a byte array
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            exporter.setExporterOutput(new SimpleHtmlExporterOutput(outputStream));
            exporter.exportReport();

            // Get the byte array
            returnReport = outputStream.toByteArray();
        } else {
            returnReport = JasperExportManager.exportReportToPdf(jasperPrint);
        }
        return returnReport;
    }

    @Override
    public Map<String, List<JasperReportParameter>> getReportParameters(List<String> moduleList)
            throws Exception {

        List<EliteReport> reports = eliteReportRepository.getNameBodyForModules(moduleList);

        Map<String, List<JasperReportParameter>> parameterListByName = new HashMap<>();

        try {

            Iterator<EliteReport> reportIterator = reports.iterator();
            while (reportIterator.hasNext()) {
                EliteReport report = reportIterator.next();

                List<OmsModuleParameter> parseJasperParametes = parseJasperParametes(report.getReportBody());

                if (null != parseJasperParametes && !parseJasperParametes.isEmpty()) {
                    List<JasperReportParameter> jasperReportList = parseJasperParametes.stream()
                            .map(param -> new JasperReportParameter(param.getParameterName(), param.getParameterType()))
                            .collect(Collectors.toList());
                    parameterListByName.put(report.getModuleName(), jasperReportList);
                }
                report.setReportBody(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }

        return parameterListByName;
    }

    @Override
    public List<OmsModuleParameters> populateRecords(final String moduleName) throws Exception {
        return eliteReportRepository.populateRecords(moduleName);
    }
    
    @Override
    public Map<String, List<Map<String, String>>> getMismatchParameters(String moduleName) throws Exception {
    	
    	List<String> moduleList = new ArrayList<String>();
    	moduleList.add(moduleName);

        List<EliteReport> reports = eliteReportRepository.getNameBodyForModules(moduleList);

        List<OmsModuleParameter> dbModuleParam = eliteReportRepository.getAllModuleParameters(moduleList);

        Map<String, List<OmsModuleParameter>> reportModuleParam = new HashMap<>();

        try {

            Iterator<EliteReport> reportIterator = reports.iterator();

            while (reportIterator.hasNext()) {

                EliteReport report = reportIterator.next();
                List<OmsModuleParameter> omsParamList = parseJasperParametes(report.getReportBody());
                reportModuleParam.put(report.getModuleName(), omsParamList);

                report.setReportBody(null);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return extractConflictParam(reportModuleParam, dbModuleParam, moduleList);
    }
    
    private List<Map<String, String>> processParameters(List<OmsModuleParameter> dbParamList,
            List<OmsModuleParameter> reportParamList) {

        Map<String, Map<String, String>> paramMap = new HashMap<>();
        if(dbParamList !=null) {
        	dbParamList.forEach(a -> {
                Map<String, String> paramConflictDetails = new HashMap<>(3);
                paramConflictDetails.put("parameter_name", a.getParameterName());
                paramConflictDetails.put("source", "DB");
                paramConflictDetails.put("conflict", "Y");
                paramMap.put(a.getParameterName(), paramConflictDetails);
            });
        }
        
        if(reportParamList != null) {
        	reportParamList.forEach(b -> {
                if (paramMap.containsKey(b.getParameterName())) {
                    Map<String, String> exitingParamConDtls = paramMap.get(b.getParameterName());
                    exitingParamConDtls.put("source", exitingParamConDtls.get("source") + "R");
                    exitingParamConDtls.put("conflict", "N");
                    paramMap.put(b.getParameterName(), exitingParamConDtls);
                } else {
                    Map<String, String> paramConflictDetails = new HashMap<>(3);
                    paramConflictDetails.put("source", "R");
                    paramConflictDetails.put("parameter_name", b.getParameterName());
                    paramConflictDetails.put("conflict", "Y");
                    paramMap.put(b.getParameterName(), paramConflictDetails);
                }
            });
        }
        
        List<Map<String, String>> paramList = new ArrayList<>(paramMap.size());
        paramMap.forEach((k, v) -> paramList.add(v));
        return paramList;
    }
    
    private Map<String, List<Map<String, String>>> extractConflictParam(Map<String, List<OmsModuleParameter>> reportModuleParam,List<OmsModuleParameter> dbModuleParam, List<String> moduleList) {
        Map<String, List<OmsModuleParameter>> dbParamsByModule = dbModuleParam.stream()
                .collect(Collectors.groupingBy(OmsModuleParameter::getModuleName));

        moduleList.forEach((module) -> {
            processParameters(dbParamsByModule.get(module), reportModuleParam.get(module));
        });

        Map<String, List<Map<String, String>>> paramListByModule = new HashMap<>();

        moduleList.stream().forEach(module -> {
            List<Map<String, String>> moduleParameters = processParameters(dbParamsByModule.get(module),
                    reportModuleParam.get(module));
            paramListByModule.put(module, moduleParameters);
        });

        return paramListByModule;
    }

    private List<OmsModuleParameter> parseJasperParametes(byte[] reportBody)
            throws IOException, SAXException, ParserConfigurationException, XPathExpressionException {
        List<OmsModuleParameter> listOmsModParam = new ArrayList<>();

        // Prasing the jrxml bytes
        try (InputStream targetStream = new ByteArrayInputStream(reportBody);) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            Document doc = factory.newDocumentBuilder().parse(targetStream);
            doc.getDocumentElement().normalize();

            // Building Xpath
            XPath xPath = XPathFactory.newInstance().newXPath();
            String expression = "jasperReport/parameter";
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node instanceof Element) {
                    Element child = (Element) node;
                    if ((null != child && null != child.getAttribute("class")) &&
                            (child.getAttribute("class").equals("java.io.InputStream")
                                    || child.getAttribute("class").equals("java.io.File"))) {
                        // nothing to do
                    } else {
                    	if(!child.getAttribute("name").equalsIgnoreCase("templateLocation")) {
                    		 OmsModuleParameter parameter = new OmsModuleParameter();
                             parameter.setParameterName(child.getAttribute("name"));
                             parameter.setParameterType(child.getAttribute("class"));
                             listOmsModParam.add(parameter);
                    	}
                       

                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

        return listOmsModParam;

    }

	@Override
	public List<ReferenceCodes> getParameterSql(String moduleName, String parameterName, Integer parameterSeq,
			Optional<String> parentFiled) {
		List<ReferenceCodes> codeList = null;
		String parameterSql = eliteReportRepository.getParameterSql(moduleName, parameterName, parameterSeq);
		if (parentFiled.isPresent()) {
			String[] keyValue = parentFiled.get().split(":");
			String sqlQuery = parameterSql.replace(":"+keyValue[0], "'" + keyValue[1] + "'");
			codeList = eliteReportRepository.getCodeList(sqlQuery);
		} else {
			codeList = eliteReportRepository.getCodeList(parameterSql);
		}
		return codeList;
	}

    @Override
    public Map<String, String> insertUpdateDeleteOfOmsParameters(OmsModuleParametersCommitBean commitBean,
            String userName) {

        Map<String, String> outMessages = new HashMap<>();

        List<OmsModuleParameter> insertList = commitBean.getInsertList();
        List<OmsModuleParameter> updateList = commitBean.getUpdateList();
        List<OmsModuleParameter> deleteList = commitBean.getDeleteList();

        if (null != insertList && !insertList.isEmpty()) {
            String moduleName = insertList.get(0).getModuleName();

            List<OmsModuleParameter> responseList = eliteReportRepository.getOmsParameterNameByModuleName(moduleName);

            Set<String> exitingParameters = responseList.stream().map(e -> e.getParameterName())
                    .collect(Collectors.toSet());

            List<OmsModuleParameter> finalInsersableData = insertList.stream()
                    .filter((p) -> !exitingParameters.contains(p.getParameterName())).collect(Collectors.toList());

            Long maxSeq = eliteReportRepository.getMaxParameterSeqByModuleName(moduleName);
            if (!finalInsersableData.isEmpty()) {
                Iterator<OmsModuleParameter> iterator = finalInsersableData.iterator();
                while (iterator.hasNext()) {
                    OmsModuleParameter module = iterator.next();
                    module.setParameterSeq(maxSeq!=null?++maxSeq:1);
                    module.setCreateUserId(userName);
                    module.setModifyUserId(userName);
                }
                Integer failedRecords = eliteReportRepository.insetUpdateDeleteOmsModuleParams(finalInsersableData,
                        "INSERT");
                outMessages.put("InsertStatus",
                        failedRecords == 0 ? "SUCCESS"
                                : "Insertion Failed for " + failedRecords
                                        + (insertList.size() - finalInsersableData.size()) + " Records");
            } else {
                outMessages.put("InsertStatus", "Insertion Failed for " + insertList.size() + " Records");
            }
        }
        if (null != updateList && !updateList.isEmpty()) {
            Iterator<OmsModuleParameter> iterator = updateList.iterator();
            while (iterator.hasNext()) {
                OmsModuleParameter mod = iterator.next();
                mod.setModifyUserId(userName);
            }
            Integer failedRecords = eliteReportRepository.insetUpdateDeleteOmsModuleParams(updateList, "UPDATE");
            outMessages.put("UpdateStatus",
                    failedRecords == 0 ? "SUCCESS" : "Updation Failed for " + failedRecords + " Records");
        }
        if (null != deleteList && !deleteList.isEmpty()) {
            Integer failedRecords = eliteReportRepository.insetUpdateDeleteOmsModuleParams(deleteList, "DELETE");
            outMessages.put("DeleteStatus",
                    failedRecords == 0 ? "SUCCESS" : "Deletion Failed for " + failedRecords + " Records");
        }
        return outMessages;
    }
    
    @Override
    public int commitReportChanges(final OmsModulesCommitBean commitBean) throws Exception {
    	List<OmsModules> updateList = commitBean.getUpdateList();
        List<OmsModules> deleteList = commitBean.getDeleteList();
        int processed = 0;
        if (null != updateList && !updateList.isEmpty()) {
        	for(OmsModules omsModules:updateList) {
        		omsModules.setModifyUserId(commitBean.getCreateUserId());
        	}
        	Integer insertedRecords = eliteReportRepository.updateOMSModules(updateList);
        	processed = insertedRecords;
        }
        if (null != deleteList && !deleteList.isEmpty()) {
        	deleteList.forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
        	Integer deletedRecords = eliteReportRepository.deleteOMSModuleReports(deleteList);
        	processed = processed + deletedRecords;
        }
    	//Integer insertedRecords = eliteReportRepository.insetUpdateDeleteOmsModuleParams(updateList);
    	return processed;
    }

    @Override
    public OmsReportAsset getAsset(String asset) {
        return eliteReportRepository.getOmsAssetByCode(asset);
    }

    @Override
    public List<OmsReportAsset> getAssets() {
        return eliteReportRepository.getAllOmsAssets();
    }

    @Override
    public String addUpdateAsset(MultipartFile assetMultipart,String userName,String assetCode) throws Exception {
        OmsReportAsset asset = new OmsReportAsset();
        try {
            asset.setAssetBody(assetMultipart.getBytes());
            asset.setCreateUserId(userName);
            asset.setModifyUserId(userName);
            asset.setAssetsFilename(assetMultipart.getOriginalFilename());
            asset.setAssetCode(assetCode);
        }catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return eliteReportRepository.insertUpdateOmsAsset(asset);
    }
    
}
