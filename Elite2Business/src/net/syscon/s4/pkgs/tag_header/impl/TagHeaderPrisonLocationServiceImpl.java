package net.syscon.s4.pkgs.tag_header.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.pkgs.tag_header.TagHeaderPrisonLocationRepository;
import net.syscon.s4.pkgs.tag_header.TagHeaderPrisonLocationService;
import net.syscon.s4.pkgs.tag_header.TagHeaderStatusRepository;

@Service
public class TagHeaderPrisonLocationServiceImpl  implements TagHeaderPrisonLocationService{

	Logger logger = LogManager.getLogger(TagHeaderStatusServiceImpl.class);

	@Autowired
	TagHeaderPrisonLocationRepository tagHeaderPrisonLocationRepository;

	@Autowired
	TagHeaderStatusRepository tagHeaderStatusRepository;

	@Override
	public String getHeaderPrisonLocation(String activeFlag, String commActiveFlag, String commAgyLocId, String agyLocId, BigDecimal livingUnitId, BigDecimal agyImlId, Long offenderBookId, String currentUser) {
		String lvLocDescription = null;
		String lvLOutDescription = null;
		String returnString = "";
		String lvOffice = "";
		String lvCaseLoadType = null;
		Long lvMultiLocCount = null;
		String lvAgyId = null;
		try {
			if(agyLocId != null) {
				lvLocDescription = tagHeaderPrisonLocationRepository.getDescCur(agyLocId);
			}
			if(livingUnitId != null) {
				returnString = agyLocId.toString() + " [" + getTagIntLocIntLocPath(Integer.parseInt(livingUnitId.toString())) + "]";
				if(agyImlId != null) {
					returnString = returnString + "-[" + getTagIntLocIntLocPath(Integer.parseInt(agyImlId.toString())) + "]";
				}
			} else {
				if(lvLocDescription != null && !lvLocDescription.equalsIgnoreCase(""))
					returnString = lvLocDescription;
			}
			lvCaseLoadType = tagHeaderStatusRepository.getTypeCur(currentUser);
			if(lvCaseLoadType.equalsIgnoreCase("COMM")) {
				if(commActiveFlag!= null && commActiveFlag.equalsIgnoreCase("Y")) {
					lvAgyId = tagHeaderPrisonLocationRepository.commAgyCur(offenderBookId);
					if(commAgyLocId != null) {
						if(lvAgyId != null) {
							lvLocDescription = tagHeaderPrisonLocationRepository.getDescCur(lvAgyId);
						} else {
							lvLocDescription = tagHeaderPrisonLocationRepository.getDescCur(commAgyLocId);
						}
					} else {
						lvLocDescription = commAgyLocId;
					}
					lvOffice = getOfficer(offenderBookId, lvCaseLoadType);
					if(lvOffice != null && !lvOffice.equalsIgnoreCase("")) {
						lvOffice = "[" + lvOffice + "]";
					}
					lvMultiLocCount = tagHeaderPrisonLocationRepository.checkMultiLocsCur(offenderBookId);
					if(lvMultiLocCount!=null && lvMultiLocCount > 1) {
						lvOffice = lvOffice + "MULTI";
					}
					returnString = (lvLocDescription !=null?lvLocDescription:"") + (lvOffice !=null?lvOffice:"");
					if(agyLocId != null) {
						if(lvOffice != null) {
							returnString = lvLocDescription + lvOffice + " ; " + agyLocId;
						}
					}
				} else if (commActiveFlag != null && commActiveFlag.equalsIgnoreCase("N")){
					lvLOutDescription = tagHeaderPrisonLocationRepository.getDescCur("OUT");
					if(agyLocId != null) {
						if(!agyLocId.equalsIgnoreCase("OUT")) {
							returnString = lvLOutDescription + ";" + agyLocId;
						} else {
							returnString = lvLOutDescription;
						}
					} else {
						returnString = lvLOutDescription;
					}
				}	
			} else {
				if(commActiveFlag != null && commActiveFlag.equalsIgnoreCase("Y")) {
					lvAgyId = tagHeaderPrisonLocationRepository.commAgyCur(offenderBookId);
					if(lvAgyId != null) {
						returnString = returnString + " ; " + lvAgyId;
					} else {
						returnString = returnString + " ; " + commAgyLocId;
					}
				}
			}
		} catch(Exception e) {
			logger.error("TagHeaderPrisonLocationServiceImpl - getHeaderPrisonLocation " + e);
		}
		return returnString;
	}

	public String getOfficer(Long offenderBookId, String lvCaseLoadType) {
		String returnString = null;
		if(lvCaseLoadType.equalsIgnoreCase("COMM")) {
			returnString = tagHeaderPrisonLocationRepository.getCommStaffCur(offenderBookId);
		} else {
			returnString = tagHeaderPrisonLocationRepository.getInstStaffCur(offenderBookId);
		}
		return returnString;
	}

	public String getTagIntLocIntLocPath(Integer internalLocationId) {
		Integer parentIntLocId = null;
		String intLocCode = null;

		List<AgencyInternalLocations> agencyIntLocs = tagHeaderPrisonLocationRepository.getTagIntLocIntLocPath(internalLocationId);
		if(agencyIntLocs != null && agencyIntLocs.size()>0) {
			intLocCode = agencyIntLocs.get(0).getInternalLocationCode();
			parentIntLocId = agencyIntLocs.get(0).getParentInternalLocationId();
			if(parentIntLocId == null) {
				return intLocCode;
			} else {
				return getTagIntLocIntLocPath(parentIntLocId) + "-" + intLocCode;
			}

		} else {
			return " ";
		}
	}

}
