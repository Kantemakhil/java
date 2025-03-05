package net.syscon.s4.report;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.MessageDTO;

@EliteController
public class OIRMASSETController {
	private static Logger logger = LogManager.getLogger(OIEXPJRPController.class.getName());
	
	private static final String FILE_NOT_SUPPORTED = "422";
	
	@Autowired
    private EliteReportService eliteReportService;
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oirmasset/report/asset", method = RequestMethod.GET)
    public OmsReportAsset getAssets(@RequestParam("asset_code") String assetCode) {
	    OmsReportAsset asset = null;
        try {
            asset = eliteReportService.getAsset(assetCode);
        } catch (Exception e) {
            logger.error("Exception :", e);
            e.printStackTrace();
        }
        return asset;
    }
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oirmasset/report/assets", method = RequestMethod.GET)
    public List<OmsReportAsset> getAllAssets() {
        List<OmsReportAsset> assets = null;
        try {
            assets = eliteReportService.getAssets();
        } catch (Exception e) {
            logger.error("Exception :", e);
            e.printStackTrace();
        }
        return assets;
    }
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oirmasset/report/asset", method = RequestMethod.POST)
    public MessageDTO addUpdateAsset(@RequestParam("asset_file") MultipartFile importFile,@RequestParam("assetCode") String assetCode) {
		MessageDTO message = new MessageDTO();
		  List<String> extensionList = Arrays.asList("png", "jpg", "jpeg","jrtx");
        try {
            String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
            String extension = StringUtils.getFilenameExtension(importFile.getOriginalFilename());
			if(extension!=null && !extensionList.contains(extension.toLowerCase())) {
				 message.setMessage(FILE_NOT_SUPPORTED);
				return message;
			}
            String addStatus = eliteReportService.addUpdateAsset(importFile,userName,assetCode);
            message.setMessage(addStatus);
        } catch (Exception e) {
            logger.error("Exception :", e);
            e.printStackTrace();
        }
        return message;
    }

}
