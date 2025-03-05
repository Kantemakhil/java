package net.syscon.s4.report;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import net.syscon.s4.common.EliteController;

@EliteController
public class OIIMPJRPController {
	
	 private static Logger logger = LogManager.getLogger(OIIMPJRPController.class.getName());
	
	@Autowired
    private EliteReportService eliteReportService;
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oiimpjrp/importReports", method = RequestMethod.POST)
    public @ResponseBody Map<String, Boolean> importProcesses(HttpServletRequest httpServletRequest,
            @RequestParam("importFile") MultipartFile importFile) {

        String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        List<String> extensionList = Arrays.asList("zip","jrxml");
        Map<String, Boolean> importedPrecesses = new HashMap<>();
        try {
            String savedLocation = httpServletRequest.getSession().getServletContext()
                    .getRealPath("/WEB-INF/classes/report-exp/");
            if (!new File(savedLocation).exists()) {
                new File(savedLocation).mkdir();
            }
            String extension = StringUtils.getFilenameExtension(importFile.getOriginalFilename());
			if(extension!=null && !extensionList.contains(extension.toLowerCase())) {
				return Collections.emptyMap();
			}
            importedPrecesses = eliteReportService.importProcesses(importFile, savedLocation, userName);
        } catch (Exception e) {
            logger.error("Exception :", e);
            return importedPrecesses;

        }
        return importedPrecesses;
    }

}
