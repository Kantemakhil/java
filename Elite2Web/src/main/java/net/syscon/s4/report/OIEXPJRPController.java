package net.syscon.s4.report;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.DocManageUtilities;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.im.beans.OmsModules;

@EliteController
public class OIEXPJRPController {
	
	 private static Logger logger = LogManager.getLogger(OIEXPJRPController.class.getName());
		
		@Autowired
	    private EliteReportService eliteReportService;
		
		@Autowired
	    private DocManageUtilities docManageUtilities;
		
		@PreAuthorize("hasEliteRole('full')")
		@RequestMapping(value = "/oiexpjrp/exportReports", method = RequestMethod.POST)
	    public @ResponseBody ResponseEntity exportReports(HttpServletRequest httpServletRequest,
	            @RequestBody List<OmsModules> lstOfProcessMain) {
	        byte[] outFile = null;
	        HttpHeaders headerRes = new HttpHeaders();
	        Map<String, String> errorObj = new HashMap<>();
	        errorObj.put("error", "");
	        try {
	            String savedLocation = httpServletRequest.getSession().getServletContext()
	                    .getRealPath("/WEB-INF/classes/elitereport-exp/");
	            if (!new File(savedLocation).exists()) {
	                new File(savedLocation).mkdir();
	            }

	            List<String> moduleNames = lstOfProcessMain.stream().map((oms) -> oms.getModuleName())
	                    .collect(Collectors.toList());
	            headerRes = docManageUtilities.getHttpHeadersForFileUpload("application/octet-stream", null, false);
	            outFile = eliteReportService.exportReports(moduleNames, savedLocation);
	        } catch (Exception e) {
	            logger.error("Exception :", e);
	            errorObj.put("error", e.getMessage());
	            return new ResponseEntity<>(errorObj, null, HttpStatus.OK);

	        }
	        return new ResponseEntity<>(outFile, headerRes, HttpStatus.OK);
	    }

}
