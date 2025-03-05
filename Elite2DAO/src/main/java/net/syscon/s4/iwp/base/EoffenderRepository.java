package net.syscon.s4.iwp.base;

import java.util.List;

import net.syscon.s4.eoffender.beans.EoffenderDetails;
import net.syscon.s4.eoffender.beans.FileLimits;
import net.syscon.s4.eoffender.beans.MetaDataParameters;
import net.syscon.s4.eoffender.beans.UploadMetaData;
import net.syscon.s4.im.beans.Images;

public interface EoffenderRepository {

	EoffenderDetails getEoffenderDetails(String keyLogin);

	List<UploadMetaData> ocdccaseUploadMetaData(MetaDataParameters metaDataParameters);

	List<UploadMetaData> OCDREQUEUploadMetaData(MetaDataParameters metaDataParameters);

	List<UploadMetaData> OCDPROGRUploadMetaData(MetaDataParameters metaDataParameters);

	List<UploadMetaData> OCUPATOFUploadMetaData(MetaDataParameters metaDataParameters);

	List<UploadMetaData> OIDHWDETUploadMetaData(MetaDataParameters metaDataParameters);

	List<UploadMetaData> OIIVNTIFUploadMetaData(MetaDataParameters metaDataParameters);

	List<UploadMetaData> OPDPDECIUploadMetaData(MetaDataParameters metaDataParameters);

	List<UploadMetaData> OCDUPROJ_02UploadMetaData(MetaDataParameters metaDataParameters);

	List<UploadMetaData> OCDUPROJ_01UploadMetaData(MetaDataParameters metaDataParameters);

	List<UploadMetaData> OCDENFORUploadMetaData(MetaDataParameters metaDataParameters);

	List<FileLimits> getEoffenderProfileValues();

	List<Images> imageExecuteQuery(Images searchBean);
	
}
