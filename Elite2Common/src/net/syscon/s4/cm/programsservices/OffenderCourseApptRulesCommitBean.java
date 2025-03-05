package net.syscon.s4.cm.programsservices;

import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderCourseApptRulesCommitBean extends BaseModel {
	
		private static final long serialVersionUID = 1L;
		private List<OffenderCourseApptRule> insertList;
		private List<OffenderCourseApptRule> deleteList;
		private List<OffenderCourseApptRule> updateList;
		public List<OffenderCourseApptRule> getInsertList() {
			return insertList;
		}
		public void setInsertList(List<OffenderCourseApptRule> insertList) {
			this.insertList = insertList;
		}
		public List<OffenderCourseApptRule> getDeleteList() {
			return deleteList;
		}
		public void setDeleteList(List<OffenderCourseApptRule> deleteList) {
			this.deleteList = deleteList;
		}
		public List<OffenderCourseApptRule> getUpdateList() {
			return updateList;
		}
		public void setUpdateList(List<OffenderCourseApptRule> updateList) {
			this.updateList = updateList;
		}
		
}
