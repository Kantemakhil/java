package net.syscon.s4.iwp.eoffender;

import java.util.List;

import net.syscon.s4.eoffender.beans.EoffenderDetails;
import net.syscon.s4.eoffender.beans.FileLimits;
import net.syscon.s4.eoffender.beans.MetaDataParameters;
import net.syscon.s4.eoffender.beans.UploadMetaData;
import net.syscon.s4.im.beans.Images;

public interface EoffenderService {

	EoffenderDetails getEoffenderDetails(String keyLogin);

	List<UploadMetaData> getUploadMetaData(MetaDataParameters metaDataParameters);
	
	List<FileLimits> getEoffenderProfileValues();

	List<Images> imageExecuteQuery(Images searchBean);

}
