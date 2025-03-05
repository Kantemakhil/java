package net.syscon.s4.common.beans.trim;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Record {

	@JsonProperty("RecordDocumentType")
    private RecordDocumentType recordDocumentType;

	@JsonProperty("RecordNumber")
    private RecordNumber recordNumber;

	@JsonProperty("RecordTitle")
    private RecordTitle recordTitle;

	@JsonProperty("RecordDocumentStatus")
    private RecordDocumentStatus recordDocumentStatus;

	@JsonProperty("RecordDateFinalized")
	private RecordDateFinalized recordDateFinalized;

	@JsonProperty("RecordIsCheckedOut")
	private RecordIsCheckedOut recordIsCheckedOut;


	@JsonProperty("RecordClassification")
	private RecordClassification recordClassification;

	@JsonProperty("Uri")
	private String uri;

	@JsonProperty("RecordDateCreated")
	private RecordDateCreated recordDateCreated;
	
	@JsonProperty("RecordDateModified")
	private RecordDateModified recordDateModified;

	@JsonProperty("RecordAccessControl")
	private RecordAccessControl recordAccessControl;

	@JsonProperty("RecordCreator")
	private RecordCreator recordCreator;

	@JsonProperty("RecordAuthor")
	private RecordAuthor recordAuthor;
	
	@JsonProperty("Fields")
	private Fields fields;
	
	@JsonProperty("ClassificationIdNumber")
	private ClassificationId classificationIdNumber;
	

	public RecordDocumentStatus getRecordDocumentStatus() {
		return recordDocumentStatus;
	}

	public void setRecordDocumentStatus(RecordDocumentStatus recordDocumentStatus) {
		this.recordDocumentStatus = recordDocumentStatus;
	}

	public RecordIsCheckedOut getRecordIsCheckedOut() {
		return recordIsCheckedOut;
	}

	public void setRecordIsCheckedOut(RecordIsCheckedOut recordIsCheckedOut) {
		this.recordIsCheckedOut = recordIsCheckedOut;
	}


	public RecordDocumentType getRecordDocumentType() {
		return recordDocumentType;
	}

	public void setRecordDocumentType(RecordDocumentType recordDocumentType) {
		this.recordDocumentType = recordDocumentType;
	}

	public RecordNumber getRecordNumber() {
		return recordNumber;
	}

	public void setRecordNumber(RecordNumber recordNumber) {
		this.recordNumber = recordNumber;
	}

	public RecordTitle getRecordTitle() {
		return recordTitle;
	}

	public void setRecordTitle(RecordTitle recordTitle) {
		this.recordTitle = recordTitle;
	}


	public RecordDateFinalized getRecordDateFinalized() {
		return recordDateFinalized;
	}

	public void setRecordDateFinalized(RecordDateFinalized recordDateFinalized) {
		this.recordDateFinalized = recordDateFinalized;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Record [recordDocumentType=");
		builder.append(recordDocumentType);
		builder.append(", recordNumber=");
		builder.append(recordNumber);
		builder.append(", recordTitle=");
		builder.append(recordTitle);
		builder.append(", recordDocumentStatus=");
		builder.append(recordDocumentStatus);
		builder.append(", recordDateFinalized=");
		builder.append(recordDateFinalized);
		builder.append(", recordIsCheckedOut=");
		builder.append(recordIsCheckedOut);
		builder.append(", recordClassification=");
		builder.append(recordClassification);
		builder.append(", uri=");
		builder.append(uri);
		builder.append(", recordDateCreated=");
		builder.append(recordDateCreated);
		builder.append(", recordDateModified=");
		builder.append(recordDateModified);
		builder.append(", recordAccessControl=");
		builder.append(recordAccessControl);
		builder.append(", recordCreator=");
		builder.append(recordCreator);
		builder.append(", recordAuthor=");
		builder.append(recordAuthor);
		builder.append(", fields=");
		builder.append(fields);
		builder.append(", ClassificationIdNumber=");
		builder.append(classificationIdNumber);
		
		builder.append("]");
		return builder.toString();
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public RecordClassification getRecordClassification() {
		return recordClassification;
	}

	public void setRecordClassification(RecordClassification recordClassification) {
		this.recordClassification = recordClassification;
	}

	public RecordDateCreated getRecordDateCreated() {
		return recordDateCreated;
	}

	public void setRecordDateCreated(RecordDateCreated recordDateCreated) {
		this.recordDateCreated = recordDateCreated;
	}
	
	public RecordDateModified getRecordDateModified() {
		return recordDateModified;
	}

	public void setRecordDateModified(RecordDateModified recordDateModified) {
		this.recordDateModified = recordDateModified;
	}

	public RecordAccessControl getRecordAccessControl() {
		return recordAccessControl;
	}

	public void setRecordAccessControl(RecordAccessControl recordAccessControl) {
		this.recordAccessControl = recordAccessControl;
	}

	public RecordCreator getRecordCreator() {
		return recordCreator;
	}

	public void setRecordCreator(RecordCreator recordCreator) {
		this.recordCreator = recordCreator;
	}

	public Fields getFields() {
		return fields;
	}

	public void setFields(Fields fields) {
		this.fields = fields;
	}

	public ClassificationId getClassificationIdNumber() {
		return classificationIdNumber;
	}

	public void setClassificationIdNumber(ClassificationId classificationIdNumber) {
		this.classificationIdNumber = classificationIdNumber;
	}

	public RecordAuthor getRecordAuthor() {
		return recordAuthor;
	}

	public void setRecordAuthor(RecordAuthor recordAuthor) {
		this.recordAuthor = recordAuthor;
	}
	
	
}
