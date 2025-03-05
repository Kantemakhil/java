package net.syscon.s4.inst.demographicsbiometrics.impl;

import java.util.Comparator;

import net.syscon.s4.im.beans.AgencyParameters;
import net.syscon.s4.im.beans.FacilityParameters;

public class FacilityRank {

	static class AgencyComparator implements Comparator<AgencyParameters> {
		
//		public int compare(AgencyParameters agency1, AgencyParameters agency2) {
////			if(agency1.getParamList() !=null  || agency2.getParamList() !=null) {
////				
////			}
//			if (agency1.getParamList().stream().filter(l -> l.getRequired().equals("Y")).count() > agency2
//					.getParamList().stream().filter(l -> l.getRequired().equals("Y")).count()) {				
//				return -1;
//			} else if (agency1.getParamList().stream().filter(l -> l.getRequired().equals("Y")).count() == agency2
//					.getParamList().stream().filter(l -> l.getRequired().equals("Y")).count()) {
//				if (getTotalWeight(agency1) == getTotalWeight(agency2)) {
//					return agency1.getAgency().compareTo(agency2.getAgency());
//				} else {
//					return getTotalWeight(agency1) - getTotalWeight(agency2);
//				}
//			} else {
//				return 1;
//			}
//
//		}
		
		public int compare(AgencyParameters agency1, AgencyParameters agency2) {
			
			if (getNoOfParams(agency1, "Y") > getNoOfParams(agency2, "Y")) {
				return -1;
			} else if (getNoOfParams(agency1, "Y") == getNoOfParams(agency2, "Y")) {
				if (getTotalWeight(agency1) == getTotalWeight(agency2)) {
					return agency1.getAgency().compareTo(agency2.getAgency());
				} else {
					return - (getTotalWeight(agency1) - getTotalWeight(agency2));
				}
			} else {
				return 1;
			}
			//
		}

		private int getTotalWeight(AgencyParameters agency) {
			int sum = 0;
			for (FacilityParameters facilityParameters : agency.getParamList()) {
				if (facilityParameters.getRequired().equals("N")) {
					sum += facilityParameters.getWeight();
				}
			}
			return sum;
		}
		
		public static int getNoOfParams(AgencyParameters agency,String paramType){
			int noOfParams = 0;
			for (FacilityParameters facilityParameters : agency.getParamList()) {
				if (facilityParameters.getRequired().equals(paramType)) {
					noOfParams += 1;
				}
			}
			return noOfParams;
			
		}

	}
}
