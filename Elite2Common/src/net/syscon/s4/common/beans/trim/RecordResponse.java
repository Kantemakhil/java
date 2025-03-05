package net.syscon.s4.common.beans.trim;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RecordResponse {
  
	@JsonProperty("Results")
	private List<Record> resultList = new ArrayList<>();
	@JsonProperty("PropertiesAndFields")
	private PropertiesAndFields PropertiesAndFieldsObject;
	@JsonProperty("TotalResults")
	private int TotalResults;
	@JsonProperty("CountStringEx")
	private String CountStringEx;
	@JsonProperty("MinimumCount")
	private int MinimumCount;
	@JsonProperty("Count")
	private int Count;
	@JsonProperty("HasMoreItems")
	private boolean HasMoreItems;
	@JsonProperty("SearchTitle")
	private String SearchTitle;
	@JsonProperty("HitHighlightString")
	private String HitHighlightString;
	@JsonProperty("TrimType")
	private String TrimType;
	@JsonProperty("ResponseStatus")
	private RecordResponseStatus ResponseStatusObject;

	

	public List<Record> getResultList() {
		return resultList;
	}



	public void setResultList(List<Record> resultList) {
		this.resultList = resultList;
	}



	public PropertiesAndFields getPropertiesAndFieldsObject() {
		return PropertiesAndFieldsObject;
	}



	public void setPropertiesAndFieldsObject(PropertiesAndFields propertiesAndFieldsObject) {
		PropertiesAndFieldsObject = propertiesAndFieldsObject;
	}



	public int getTotalResults() {
		return TotalResults;
	}



	public void setTotalResults(int totalResults) {
		TotalResults = totalResults;
	}



	public String getCountStringEx() {
		return CountStringEx;
	}



	public void setCountStringEx(String countStringEx) {
		CountStringEx = countStringEx;
	}



	public int getMinimumCount() {
		return MinimumCount;
	}



	public void setMinimumCount(int minimumCount) {
		MinimumCount = minimumCount;
	}



	public int getCount() {
		return Count;
	}



	public void setCount(int count) {
		Count = count;
	}



	public boolean isHasMoreItems() {
		return HasMoreItems;
	}



	public void setHasMoreItems(boolean hasMoreItems) {
		HasMoreItems = hasMoreItems;
	}



	public String getSearchTitle() {
		return SearchTitle;
	}



	public void setSearchTitle(String searchTitle) {
		SearchTitle = searchTitle;
	}



	public String getHitHighlightString() {
		return HitHighlightString;
	}



	public void setHitHighlightString(String hitHighlightString) {
		HitHighlightString = hitHighlightString;
	}



	public String getTrimType() {
		return TrimType;
	}



	public void setTrimType(String trimType) {
		TrimType = trimType;
	}



	public RecordResponseStatus getResponseStatusObject() {
		return ResponseStatusObject;
	}



	public void setResponseStatusObject(RecordResponseStatus responseStatusObject) {
		ResponseStatusObject = responseStatusObject;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RecordResponse [resultList=");
		builder.append(resultList);
		builder.append(", PropertiesAndFieldsObject=");
		builder.append(PropertiesAndFieldsObject);
		builder.append(", TotalResults=");
		builder.append(TotalResults);
		builder.append(", CountStringEx=");
		builder.append(CountStringEx);
		builder.append(", MinimumCount=");
		builder.append(MinimumCount);
		builder.append(", Count=");
		builder.append(Count);
		builder.append(", HasMoreItems=");
		builder.append(HasMoreItems);
		builder.append(", SearchTitle=");
		builder.append(SearchTitle);
		builder.append(", HitHighlightString=");
		builder.append(HitHighlightString);
		builder.append(", TrimType=");
		builder.append(TrimType);
		builder.append(", ResponseStatusObject=");
		builder.append(ResponseStatusObject);
		builder.append("]");
		return builder.toString();
	}

}