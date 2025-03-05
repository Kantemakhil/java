package net.syscon.s4.sa.admin.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OffenderIdentifier;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.pkgs.oms_search.OmsSearchService;
import net.syscon.s4.sa.admin.UpdoffidRepository;
import net.syscon.s4.sa.admin.UpdoffidService;
import net.syscon.s4.triggers.OffIdentVineIntfTrgService;
import net.syscon.s4.triggers.OffendersTjnService;
import net.syscon.s4.triggers.OffendersVineIntfTrgService;
import net.syscon.s4.triggers.OmtoffsrcService;

/**
 * Class UpdoffidServiceImpl
 */
@Service
public class UpdoffidServiceImpl extends BaseBusiness implements UpdoffidService {

	@Autowired
	private UpdoffidRepository updoffidRepository;
	
	@Autowired
	private OmsSearchService omsSearchService;
	
	@Autowired
	private OmtoffsrcService omtoffsrcService;
	
	@Autowired
	private OffendersVineIntfTrgService offendersVineIntfTrgService;
	
	@Autowired
	private OffendersTjnService offendersTjnService;
	
	@Autowired
	private OffIdentVineIntfTrgService offIdentVineIntfTrgService;

	@Override
	public Integer checkOffenderIdDisplay(final String offIdDisplay) {
		return omsSearchService.checkOffenderIdDisplay(offIdDisplay);
	}

	@Override
	public Integer updateOffIdDisplay(final VHeaderBlock searchBean) {
		Integer lireturn = 0;
		final OffenderIdentifier insertBean = new OffenderIdentifier();
		Offenders obj = new Offenders();
		BeanUtils.copyProperties(searchBean, obj);
		List<Offenders> triList = new ArrayList<Offenders>();
		obj.setOffenderId(searchBean.getOffenderId().longValue());
		obj.setSealFlag(searchBean.getSealFlag());
		obj.setLastName(searchBean.getLastName());
		obj.setFirstName(searchBean.getFirstName());
		obj.setMiddleName(searchBean.getMiddleName());
		obj.setRootOffenderId(searchBean.getRootOffenderId());
		obj.setAliasOffenderId(searchBean.getAliasOffenderId()!=null ? searchBean.getAliasOffenderId().longValue() : null);
		triList.add(obj);
		List<Offenders> list = omtoffsrcService.omtoffsrc(triList, "UPDATE");
		List<Offenders> oldList = updoffidRepository.getOldOffender( searchBean.getRootOffenderId());
		searchBean.setModifyUserId(searchBean.getCreateuserId());
		lireturn = updoffidRepository.updateOffIdDisplay(searchBean.getNbtAssignReason(),
				searchBean.getRootOffenderId(),searchBean.getCreateuserId());
		
		offendersVineIntfTrgService.offendersVineIntfTrg(triList, "UPDATE");
		//offendersTjnService.offendersTjn(triList, oldList, "UPDATE");
		
		insertBean.setOffenderId(searchBean.getOffenderId().longValue());
		insertBean.setOffenderIdSeq(updoffidRepository.getoffenderSeq(searchBean.getOffenderId()));
		String profileValue = updoffidRepository.getProfileValue();
		if (profileValue != null) {
			insertBean.setIdentifierType(profileValue);
		} else {
			insertBean.setIdentifierType("SBI");
		}
		insertBean.setIdentifier(searchBean.getOffenderIdDisplay());
		insertBean.setRootOffenderId(searchBean.getRootOffenderId());
		insertBean.setCaseloadType(searchBean.getAgyLocType());
		insertBean.setCreateDateTime(searchBean.getCreateDatetime());
		insertBean.setIssuedDate(searchBean.getCreateDatetime());
		insertBean.setCreateUserId(searchBean.getCreateuserId());
		insertBean.setModifyUserId(searchBean.getCreateuserId());
		insertBean.setVerifiedFlag("Y");
		insertBean.setIssuedAuthorityText(null);
		lireturn = updoffidRepository.insertOffIdentifiers(insertBean);
		List<OffenderIdentifier> offIdList = new ArrayList<OffenderIdentifier>();
		offIdList.add(insertBean);
		offIdentVineIntfTrgService.offIdentVineIntfTrg(offIdList, null, "INSERT");
		return lireturn;
	}

}