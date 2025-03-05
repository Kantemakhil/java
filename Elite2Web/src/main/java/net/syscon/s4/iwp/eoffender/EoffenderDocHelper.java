package net.syscon.s4.iwp.eoffender;

import java.io.File;
import java.io.FileInputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.aspose.words.DocumentBuilder;
import com.aspose.words.License;

@Component
public class EoffenderDocHelper {
	
	private static Logger logger = LogManager.getLogger(EoffenderDocHelper.class.getName());
	
	public static boolean isAsposeLicenseValid(License license) throws Exception {
		if (license.isLicensed()) {
			logger.info("isAsposeLicenseValid :: Licence is valid");
			return true;
		} else {
			logger.info("isAsposeLicenseValid :: Licence has been Expired");
			return false;
		}
	}
	
	public static void insertTextInBlankFile(File trimTempFile){
		com.aspose.words.Document asposeDocument;
		try (FileInputStream trimTempFileInputStream = new FileInputStream(trimTempFile);){
			asposeDocument = new com.aspose.words.Document(trimTempFileInputStream);
			DocumentBuilder builder = new DocumentBuilder(asposeDocument);
			builder.write(" ");
			asposeDocument.save(trimTempFile.getAbsolutePath());
		} catch (Exception e) {
			logger.error("insertTextInBlankFile error :: ", e);
		}
	}

}
