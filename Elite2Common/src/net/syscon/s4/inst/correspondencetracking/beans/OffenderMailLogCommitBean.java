package net.syscon.s4.inst.correspondencetracking.beans;

import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderMailLogCommitBean extends BaseModel{
	
		private static final long serialVersionUID = 1L;
		@JsonProperty("insertList")
		private List<OffenderMailLog> insertList;
		@JsonProperty("deleteList")
		private List<OffenderMailLog> deleteList;
		@JsonProperty("updateList")
		private List<OffenderMailLog> updateList;
		
		public List<OffenderMailLog> getInsertList() {
			return insertList;
		}
		public void setInsertList(List<OffenderMailLog> insertList) {
			this.insertList = insertList;
		}
		public List<OffenderMailLog> getDeleteList() {
			return deleteList;
		}
		public void setDeleteList(List<OffenderMailLog> deleteList) {
			this.deleteList = deleteList;
		}
		public List<OffenderMailLog> getUpdateList() {
			return updateList;
		}
		public void setUpdateList(List<OffenderMailLog> updateList) {
			this.updateList = updateList;
		}
		
		

	}



