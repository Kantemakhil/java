package net.syscon.s4.iwp.eoffender.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import net.syscon.s4.eoffender.beans.EoffenderDetails;
import net.syscon.s4.eoffender.beans.FileLimits;
import net.syscon.s4.eoffender.beans.MetaDataParameters;
import net.syscon.s4.eoffender.beans.UploadMetaData;
import net.syscon.s4.im.beans.Images;
import net.syscon.s4.iwp.base.EoffenderRepository;
import net.syscon.s4.iwp.base.TemplateRepository;
import net.syscon.s4.iwp.eoffender.EoffenderService;

@Service
public class EoffenderServiceImpl implements EoffenderService{

	@Autowired
	private EoffenderRepository eoffenderRepository;

	@Autowired
	private TemplateRepository templetRepository;

	@Override
	public EoffenderDetails getEoffenderDetails(String keyLogin) {

		return eoffenderRepository.getEoffenderDetails(keyLogin);
	}

	@Override
	public List<UploadMetaData> getUploadMetaData(MetaDataParameters metaDataParameters) {

		List<UploadMetaData> result = new ArrayList<>();

		String screenId = metaDataParameters.getModuleName();
if(screenId !=null) {
		switch(screenId){
		case "OCDCCASE" :
		case "OCDBAILD" :
			result = eoffenderRepository.ocdccaseUploadMetaData(metaDataParameters);
			break;
		case "OCDREQUE" :
			result = eoffenderRepository.OCDREQUEUploadMetaData(metaDataParameters);
			break;
		case "OCDPROGR_03" :
			result = eoffenderRepository.OCDPROGRUploadMetaData(metaDataParameters);
			break;
		case "OCUPATOF":
			result = eoffenderRepository.OCUPATOFUploadMetaData(metaDataParameters);
			break;
		case "OIDHWDET":
			result = eoffenderRepository.OIDHWDETUploadMetaData(metaDataParameters);
			break;
		case "OIIVNTIF":
			result = eoffenderRepository.OIIVNTIFUploadMetaData(metaDataParameters);
			break;
		case "OPDPDECI":
			result = eoffenderRepository.OPDPDECIUploadMetaData(metaDataParameters);
			break;
		case "OCDUPROJ_02":
			result = eoffenderRepository.OCDUPROJ_02UploadMetaData(metaDataParameters);
			break;
		case "OCDUPROJ_01":
			result = eoffenderRepository.OCDUPROJ_01UploadMetaData(metaDataParameters);
			break;
		case "OCDENFOR":
			result = eoffenderRepository.OCDENFORUploadMetaData(metaDataParameters);
			break;
		 default:
			 result =  new ArrayList<>();
		}
}
		return result ;
	}

	@Override
	public List<FileLimits> getEoffenderProfileValues() {
		
		return eoffenderRepository.getEoffenderProfileValues();
	}

	@Override
	public List<Images> imageExecuteQuery(Images searchBean) {
		
		return eoffenderRepository.imageExecuteQuery(searchBean);
	}

}
