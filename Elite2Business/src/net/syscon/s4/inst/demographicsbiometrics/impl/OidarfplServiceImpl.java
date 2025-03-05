package net.syscon.s4.inst.demographicsbiometrics.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.FindFacilityExecuteQueryBean;
import net.syscon.s4.common.beans.FindHousingExecuteQueryBean;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.OffenderAttributes;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.OmuavbedLivUnitsQuery;
import net.syscon.s4.im.beans.ProfileCodes;
import net.syscon.s4.inst.demographicsbiometrics.OidarfplRepository;
import net.syscon.s4.inst.demographicsbiometrics.OidarfplService;
import net.syscon.s4.pkgs.omuavbed.OmuavbedPkgService;

@Service
public class OidarfplServiceImpl extends BaseBusiness implements OidarfplService {

	@Autowired
	private OidarfplRepository oidarfplrepository;
	
	@Autowired
	private OmuavbedPkgService omuavbedPkgService;

	@Override
	public List<OmuavbedLivUnitsQuery> findFacilityExecuteQuery(FindFacilityExecuteQueryBean findFacilityExecuteQueryBean) {
		return oidarfplrepository.findFacilityExecuteQuery(findFacilityExecuteQueryBean);
	}

	@Override
	public List<OffenderAttributes> offenderCaseDetails(Long offenderBookId) {
		return oidarfplrepository.offenderCaseDetails(offenderBookId);
	}

	@Override
	public List<ProfileCodes> getMovementReasonCode() {
		return oidarfplrepository.getMovementReasonCode();
	}

	@Override
	public List<ProfileCodes> getOffenderPersonalAtt(Long offenderBookId) {
		return oidarfplrepository.getOffenderPersonalAtt(offenderBookId);
	}

	@Override
	public List<OffenderAttributes> offenderAttributeExecuteQuery(Long offenderId,Long offenderBookId) {

		return oidarfplrepository.offenderAttributeExecuteQuery(offenderId,offenderBookId);
	}

	@Override
	public List<OffenderAttributes> offenderSentenceDetails(Long offenderBookId) {

		return oidarfplrepository.offenderSentenceDetails(offenderBookId);
	}
	@Override
	public List<OmuavbedLivUnitsQuery> offenderHousingExecuteQuery(FindHousingExecuteQueryBean findHousingExecuteQueryBean) {
		OmuavbedLivUnitsQuery query=new OmuavbedLivUnitsQuery();
		BeanUtils.copyProperties(findHousingExecuteQueryBean, query);
		query.setpOffenderBookId(Long.parseLong(findHousingExecuteQueryBean.getOffenderBookId()));
		query.setpAgyLocId(findHousingExecuteQueryBean.getAgencyLocId());
		List<OmuavbedLivUnitsQuery> list= omuavbedPkgService.livUnitsQuery(query);
		return oidarfplrepository.offenderHousingExecuteQuery(findHousingExecuteQueryBean,list);
	}

	@Override
	public CourseActivities offenderPersonalDetails(Integer offenderBookId, String caseloadType) {

		CourseActivities courseActivities = new CourseActivities();

		try {
			List<Offenders> personalInformationList = new ArrayList<Offenders>();

			List<Offenders> personalInformationListCode = oidarfplrepository.offenderPersonalInformation("PI",
					offenderBookId, caseloadType);

			List<Offenders> personalInformationListText = oidarfplrepository.offenderPersonalInformationText("PI",
					offenderBookId, caseloadType);

			if (personalInformationListCode != null && personalInformationListCode.size() > 0) {
				personalInformationList.addAll(personalInformationListCode);
			}
			if (personalInformationListText != null && personalInformationListText.size() > 0) {
				personalInformationList.addAll(personalInformationListText);
			}
			if (personalInformationList != null && personalInformationList.size() > 0)
				courseActivities.setOffenderNonAssociationsByInd(personalInformationList);
			else
				courseActivities.setOffenderNonAssociationsByInd(null);

			List<Offenders> personalAttributesList = new ArrayList<Offenders>();

			List<Offenders> personalAttributesCode = oidarfplrepository.offenderPersonalInformation("PA",
					offenderBookId, caseloadType);

			List<Offenders> personalAttributesText = oidarfplrepository.offenderPersonalInformationText("PA",
					offenderBookId, caseloadType);

			if (personalAttributesCode != null && personalAttributesCode.size() > 0) {
				personalAttributesList.addAll(personalAttributesCode);
			}
			if (personalAttributesText != null && personalAttributesText.size() > 0) {
				personalAttributesList.addAll(personalAttributesText);
			}
			if (personalAttributesList != null && personalAttributesList.size() > 0)
				courseActivities.setOffenderNonAssociationsByGang(personalAttributesList);
			else
				courseActivities.setOffenderNonAssociationsByGang(null);

		} catch (Exception e) {
			return null;
		}

		return courseActivities;
	}

}
