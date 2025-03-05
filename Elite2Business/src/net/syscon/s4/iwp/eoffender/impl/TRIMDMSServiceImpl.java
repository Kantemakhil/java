package net.syscon.s4.iwp.eoffender.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jboss.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.syscon.s4.common.EoffenderUtilities;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.trim.Record;
import net.syscon.s4.common.beans.trim.RecordAccessControl;
import net.syscon.s4.common.beans.trim.RecordIsCheckedOut;
import net.syscon.s4.common.beans.trim.RecordResponseStatus;
import net.syscon.s4.eoffender.beans.Document;
import net.syscon.s4.eoffender.beans.DocumentRequestBean;
import net.syscon.s4.eoffender.beans.EoffenderDetails;
import net.syscon.s4.eoffender.beans.EoffenderTemplate;
import net.syscon.s4.global.Omss40Repository;
import net.syscon.s4.globalconfiguration.OumsypflRepository;
import net.syscon.s4.iwp.eoffender.DMSService;
import net.syscon.s4.iwp.eoffender.EoffenderDocService;
import net.syscon.s4.sa.admin.OumpurgeRepository;

@Qualifier("trim")
@Service
@PropertySource("classpath:appconfig.properties")
public class TRIMDMSServiceImpl implements DMSService {

	private Logger logger = Logger.getLogger(TRIMDMSServiceImpl.class);

	public static final String CHECKED_IN = "Checked In";
	public static final String CHECKED_OUT = "Checked Out";
	public static final String ACTIVE = "Active";
	public static final String FINALIZED = "Finalized";
	public static final String LOCKED = "Locked";
	public static final String DOCUMENT_INQUIRY_SCREEN = "OCIDOCUM";
	public static final String IS_FOLDER = "TRUE";
	public static final String IS_NOT_FOLDER = "FALSE";
	public static final int TEMPLATE_LIST_CHUNK_SIZE = 40;

	@Value("${trim.url}")
	private String trimUrl;

	@Value("${uploadFolderPath}")
	private String uploadFolderPath;

	@Autowired
	private EoffenderUtilities eoffenderUtilities;

	@Autowired
	private EoffenderDocService eoffenderDocumentService;

	@Autowired
	private Environment env;

//	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	
	@Autowired
	private OumsypflRepository oumsypflRepository;
	
	@PostConstruct
	void init() {

	}

	private void addHttpRequestImpersonationHeader(HttpRequest request, String trimUser) {
		request.setHeader("userToImpersonate", trimUser);
		return;
	}

	private HttpClient getHttpClient() {
		HttpClientBuilder clientHelper = HttpClientBuilder.create();

		CredentialsProvider creds = new BasicCredentialsProvider();
		creds.setCredentials(AuthScope.ANY, new NTCredentials(env.getProperty("trim.apiUser"),
				env.getProperty("trim.apiPwd"), "", env.getProperty("trim.dev")));
		clientHelper.setDefaultCredentialsProvider(creds);

		RequestConfig config = RequestConfig.custom().setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM))
				.build();

		HttpClient httpClient = clientHelper.setDefaultRequestConfig(config).build();
		return httpClient;
	}

	@Override
	public List<Document> listDocuments(DocumentRequestBean documentRequestBean) {
		List<Document> returnDocList = new ArrayList<>();
		long startTime = System.currentTimeMillis();
		logger.info("listDocuments Inputs ::" + documentRequestBean);
		
		
		
		
		
		/*try {
			EoffenderDetails eoffenderDetails = documentRequestBean.getEoffenderDetails();
            String activeFlag = "Y&N";
			String moduleName = eoffenderDetails.getModuleName();
			Long offenderBookId = eoffenderDetails.getOffenderBookId();
			String userId = eoffenderDetails.getUserId();
			logger.info("listDocuments  For OffenderBookingId " + offenderBookId + " DisplayOffender Id "
					+ eoffenderDetails.getOffenderIdDisplay() + " For Module Name " + moduleName);
			List<EoffenderTemplate> templateDBList = new ArrayList<>();
			List<EoffenderTemplate> templateList = new ArrayList<>();
			List<EoffenderTemplate> templateFolderList = new ArrayList<>();
			List<String> roleList = eoffenderDocumentService.getStaffRoles(userId);
			try {
				long starTemplate = System.currentTimeMillis();
				templateDBList = eoffenderDocumentService.getTemplates(moduleName, roleList, activeFlag);
				logger.info("listDocuments template and folder List can be access for ModuleName " + moduleName + " :: "
						+ templateDBList);
				templateList = templateDBList.stream().filter(template -> IS_NOT_FOLDER.equals(template.getIsFolder()))
						.collect(Collectors.toList());
				logger.info("listDocuments filter template List can be access :: " + templateList);
				templateFolderList = templateDBList.stream()
						.filter(template -> IS_FOLDER.equals(template.getIsFolder())).collect(Collectors.toList());
				logger.info("listDocuments filter template Folder List can be access :: " + templateFolderList);
				Map<String, String> templateUriMap = partitionedTemplateUriMap(templateList,
						documentRequestBean.getEoffenderDetails().getTrimUser());
				logger.info("listDocuments templateUriMap :: " + templateUriMap);

				for (EoffenderTemplate eoffenderTemplate : templateList) {
					eoffenderTemplate.setUri(templateUriMap.get(eoffenderTemplate.getTemplateId()));
				}

			} catch (Exception e) {

				logger.error("Getting Template List is Failed ::" + e.getMessage(), e);
			}

			List<String> templateNameList = templateList.stream().map(template -> template.getUri())
					.collect(Collectors.toList());
			logger.info("listDocuments templateNameList :: " + templateNameList);

			List<String> templateFolderNameList = templateFolderList.stream().map(template -> template.getTemplateId())
					.collect(Collectors.toList());
			logger.info("listDocuments templateFolderNameList :: " + templateFolderNameList);

			String templateFolderNameListCommaSeparated = templateFolderNameList.stream()
					.collect(Collectors.joining(","));

			Map<String, String> templateFolderClassificationUriMap = getClassficationUriForFolder(
					templateFolderNameListCommaSeparated, documentRequestBean.getEoffenderDetails().getTrimUser());
			logger.info("listDocuments templateFolderClassificationUriMap :: " + templateFolderClassificationUriMap);

			String url = this.trimUrl + "/Record";

			StringBuilder qParams = new StringBuilder();

			Long objectId = documentRequestBean.getEoffenderDetails().getObjectId();
			String objectType = documentRequestBean.getEoffenderDetails().getObjectType();

			qParams.append("type:").append("2017").append(",").append("bookingno:")
					.append(documentRequestBean.getEoffenderDetails().getOffenderBookingNo() + "");

			HttpClient httpClient = getHttpClient();
			URIBuilder builder = new URIBuilder(url).setParameter("q", qParams.toString())
					.setParameter("properties",
							"RecordAuthor,number,RecordTitle,MIN,OffenderName,DocumentType,ObjectType,ObjectId,Status,TemplateUri,RecordRecordType,electronic,Classification,Container,RecordDocumentStatus,RecordDateFinalized,RecordIsCheckedOut,RecordCheckedOutOn,uri,RecordDateCreated,RecordDateModified")
					.setParameter("ExcludeCount", "True").setParameter("sortBy", "createdOn-")
					.setParameter("pageSize", env.getProperty("trim.pageSize"));

			URI uri = builder.build();

			HttpGet get = new HttpGet(uri);
			get.setHeader("Accept", "application/json");
			get.setHeader("accept-encoding", "gzip, deflate");
			long starttrim = System.currentTimeMillis();
			this.addHttpRequestImpersonationHeader(get, documentRequestBean.getEoffenderDetails().getTrimUser());
			org.apache.http.HttpResponse response = httpClient.execute(get);
			int httpStatus = response.getStatusLine().getStatusCode();
			String jsonResponse = EntityUtils.toString(response.getEntity(), "UTF-8");
			logger.info("listDocuments response  : " + jsonResponse + ", offenderBookingNo "
					+ documentRequestBean.getEoffenderDetails().getOffenderBookingNo() + ", offenderIdDisplay "
					+ documentRequestBean.getEoffenderDetails().getOffenderIdDisplay());
			logger.info("listDocuments response status : " + httpStatus + ", offenderBookingNo "
					+ documentRequestBean.getEoffenderDetails().getOffenderBookingNo() + ", offenderIdDisplay "
					+ documentRequestBean.getEoffenderDetails().getOffenderIdDisplay());
			if (httpStatus == EoffenderUtilities.STATUS_SUCCESS) {

				JSONObject jsonObject = new JSONObject(jsonResponse);
				JSONArray resultArray = jsonObject.getJSONArray("Results");

				logger.info("listDocuments result array  : " + resultArray + ", offenderBookingNo "
						+ documentRequestBean.getEoffenderDetails().getOffenderBookingNo() + ", offenderIdDisplay "
						+ documentRequestBean.getEoffenderDetails().getOffenderIdDisplay());

				ObjectMapper objectMapper = new ObjectMapper();

				CollectionType typeReference = TypeFactory.defaultInstance().constructCollectionType(List.class,
						Record.class);

				List<Record> trimRecordList = objectMapper.readValue(resultArray.toString(), typeReference);

				logger.info("listDocuments trim record list  : " + trimRecordList + ", offenderBookingNo "
						+ documentRequestBean.getEoffenderDetails().getOffenderBookingNo() + ", offenderIdDisplay "
						+ documentRequestBean.getEoffenderDetails().getOffenderIdDisplay());

				// FILTER TRIM-RECORD-LIST Bases on TEMPLATE_NAME_LIST
				List<Record> filterTrimRecordList = trimRecordList;
				// List<Record> filterTrimFolderRecordList = trimRecordList;
				long startFilter = System.currentTimeMillis();
				filterTrimRecordList = trimRecordList.stream().filter(r -> templateNameList
						.contains(r.getFields().getTemplateUri().getValue())
						|| templateFolderClassificationUriMap.containsKey(
								r.getRecordClassification() == null ? "" : r.getRecordClassification().getUri()))
						.collect(Collectors.toList());
				logger.info("listDocuments filterTrimRecordList for accessed template and folder  : "
						+ filterTrimRecordList + ", offenderBookingNo "
						+ documentRequestBean.getEoffenderDetails().getOffenderBookingNo() + ", offenderIdDisplay "
						+ documentRequestBean.getEoffenderDetails().getOffenderIdDisplay());

				if (objectId != null && objectId != 0L) {

					filterTrimRecordList = filterTrimRecordList.stream().filter(r -> {
						if (String.valueOf(objectId).equals(r.getFields().getObjectId().getValue())
								&& objectType.equals(r.getFields().getObjectType().getValue())) {
							return true;
						} else {
							return false;
						}

					}).collect(Collectors.toList());
					logger.info("listDocuments filterTrimRecordList using objectId  : " + objectId + ", ObjectType : "
							+ objectType + " filterTrimRecordList :: " + filterTrimRecordList + ", offenderBookingNo "
							+ documentRequestBean.getEoffenderDetails().getOffenderBookingNo() + ", offenderIdDisplay "
							+ documentRequestBean.getEoffenderDetails().getOffenderIdDisplay());
				}
				String timeZone = env.getProperty("app.timezone.format");
				for (Record record : filterTrimRecordList) {
					Document document = new Document();
					document.setFileName(eoffenderUtilities.getFileExtension(record.getRecordTitle().getValue()));
					// document.setDocumentType(classificationTemplateNameMap.get(record.getRecordClassification().getUri()));//For
					// Now Show 'Classification' as Document Type, Later will
					// show template Name here

					if (record.getFields().getTemplateUri() != null) {

						if (!"null".equals(record.getFields().getTemplateUri().getValue())
								&& !EoffenderUtilities.isEmpty(record.getFields().getTemplateUri().getValue())) {
							// FOR TEMPLATE
							templateList.forEach(template -> {
								if (record.getFields().getTemplateUri().getValue().equals(template.getUri())) {
									document.setDocumentType(template.getCode());
								}
							});
						} else {
							// FOR FOLDER
							String folderNo = templateFolderClassificationUriMap
									.get(record.getRecordClassification().getUri());
							if (!EoffenderUtilities.isEmpty(folderNo)) {
								document.setDocumentType(templateFolderList.stream()
										.filter(template -> folderNo.equals(template.getTemplateId())).findFirst().get()
										.getCode());
							}
						}
					}
					document.setDocumentName(record.getRecordTitle().getValue());
					document.setDocumentAuthor(
							record.getRecordAuthor() == null ? "" : record.getRecordAuthor().getNameString());
					if (record.getRecordDateCreated().getDateTime() != null) {
						try {
							Instant instant = Instant.parse(record.getRecordDateCreated().getDateTime());
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a")
									.withLocale(Locale.ENGLISH).withZone(ZoneId.of(timeZone));
							String dateString = formatter.format(instant);
							document.setCreatedDate(dateString);
							Date createdDate = Date.from(instant);
							document.setTimeStamp(instant.getEpochSecond());
							document.setDate(createdDate);
						} catch (Exception e) {
							logger.error("Parsing Created Date is Failed ", e);
						}
					} else {
						document.setCreatedDate(null);
					}
					if (record.getRecordDateModified()!=null && record.getRecordDateModified().getDateTime() != null) {
						try {
							Instant instant = Instant.parse(record.getRecordDateModified().getDateTime());
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a")
									.withLocale(Locale.ENGLISH).withZone(ZoneId.of(timeZone));
							String dateString = formatter.format(instant);
							document.setModifiedDate(dateString);
//							Date createdDate = Date.from(instant);
//							document.setTimeStamp(instant.getEpochSecond());
//							document.setDate(createdDate);
						} catch (Exception e) {
							logger.error("Parsing Created Date is Failed ", e);
						}
					} else {
						document.setModifiedDate(null);
					}
					document.setStatus(record.getRecordDocumentStatus().getValue());
					document.setDocumentId(record.getRecordNumber().getValue());
					document.setUri(record.getUri());

					returnDocList.add(document);

				}
				// Sort List by Date Descending Order
				returnDocList.sort((o1, o2) -> o2.getTimeStamp().compareTo(o1.getTimeStamp()));
				logger.info("listDocuments final trimRecordList  : " + returnDocList + ", offenderBookingNo "
						+ documentRequestBean.getEoffenderDetails().getOffenderBookingNo() + ", offenderIdDisplay "
						+ documentRequestBean.getEoffenderDetails().getOffenderIdDisplay());
			} else {
				logger.error("listDocuments Status : " + httpStatus + ", offenderBookingNo "
						+ documentRequestBean.getEoffenderDetails().getOffenderBookingNo() + ", offenderIdDisplay "
						+ documentRequestBean.getEoffenderDetails().getOffenderIdDisplay());
			}

		} catch (org.apache.http.ParseException | JSONException | URISyntaxException | IOException e) {
			e.printStackTrace();
			logger.error("listDocuments Error Fetching Documents : " + e + ", offenderBookingNo "
					+ documentRequestBean.getEoffenderDetails().getOffenderBookingNo() + ", offenderIdDisplay "
					+ documentRequestBean.getEoffenderDetails().getOffenderIdDisplay());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("listDocuments Error Fetching Documents : " + e + ", offenderBookingNo "
					+ documentRequestBean.getEoffenderDetails().getOffenderBookingNo() + ", offenderIdDisplay "
					+ documentRequestBean.getEoffenderDetails().getOffenderIdDisplay());
		}
		 long endTime = System.currentTimeMillis();
		 long duration = (endTime - startTime); 
		 logger.info("Execution time in mili seconds :: "+ duration);*/
		return returnDocList;
	}
	
	@Override
	public String getStatausOfDocument(String documentId) {
		String Status="";	
		String url = this.trimUrl + "/Record";

		StringBuilder qParams = new StringBuilder();
		qParams.append(documentId + "");
     try {
		HttpClient httpClient = getHttpClient();
		URIBuilder builder = new URIBuilder(url).setParameter("q", qParams.toString())
				.setParameter("properties",
						"RecordAuthor,number,RecordTitle,MIN,OffenderName,DocumentType,ObjectType,ObjectId,Status,TemplateUri,RecordRecordType,electronic,Classification,Container,RecordDocumentStatus,RecordDateFinalized,RecordIsCheckedOut,RecordCheckedOutOn,uri,RecordDateCreated,RecordDateModified")
				.setParameter("ExcludeCount", "True").setParameter("sortBy", "createdOn-")
				.setParameter("pageSize", env.getProperty("trim.pageSize"));

		URI uri = builder.build();

		HttpGet get = new HttpGet(uri);
		get.setHeader("Accept", "application/json");
		get.setHeader("accept-encoding", "gzip, deflate");
		org.apache.http.HttpResponse response = httpClient.execute(get);
		int httpStatus = response.getStatusLine().getStatusCode();
		if(httpStatus==200)
		{
		String jsonResponse = EntityUtils.toString(response.getEntity(), "UTF-8");
		JSONObject jsonObject = new JSONObject(jsonResponse);
		JSONArray resultArray = jsonObject.getJSONArray("Results");
		ObjectMapper objectMapper = new ObjectMapper();
		CollectionType typeReference = TypeFactory.defaultInstance().constructCollectionType(List.class,
				Record.class);
		List<Record> trimRecordList = objectMapper.readValue(resultArray.toString(), typeReference);
		logger.info(trimRecordList.toString());
		Status=trimRecordList.get(0).getRecordDocumentStatus().getValue();
		
		}
}catch (Exception e) {
	logger.error(e.getMessage());
}
			return Status;
	}

	@Override
	public String getURIOffenderManagmentFile(String min, String trimUser, String OffenderName, String dob) {

		// Check if "OFFENDER MANAGEMENT FILE" EXIST if NOT THEN CREATE ONE AND
		// RETURN OFFENDER FILE INFO
		logger.info("getURIOffenderManagmentFile  min  : " + min + " trimUser :" + trimUser + "OffenderName :"
				+ OffenderName + " dob :" + dob);
		String offenderManagmentFileUri = StringUtils.EMPTY;

		String recordEndPoint = this.trimUrl + "/Record";

		HttpClient httpClient = getHttpClient();

		try {
			URIBuilder builder = new URIBuilder(recordEndPoint).setParameter("q", "type:4,min:" + min)
					.setParameter("ExcludeCount", "True").setParameter("format", "json");

			URI uri = builder.build();

			HttpGet get = new HttpGet(uri);
			get.setHeader("Accept", "application/json");
			this.addHttpRequestImpersonationHeader(get, trimUser);
			org.apache.http.HttpResponse response = httpClient.execute(get);
			int httpStatus = response.getStatusLine().getStatusCode();

			logger.info("getURIOffenderManagmentFile  httpStatus  : " + httpStatus + ", min  : " + min);
			String jsonResponse = EntityUtils.toString(response.getEntity(), "UTF-8");

			logger.info("getURIOffenderManagmentFile  Response  : " + jsonResponse + ", min  : " + min);
			if (response.getStatusLine().getStatusCode() == HttpStatus.OK.value()) {

				JSONObject recordResponse = new JSONObject(jsonResponse);
				JSONArray resultArray = recordResponse.getJSONArray("Results");
				ObjectMapper objectMapper = new ObjectMapper();

				CollectionType typeReference = TypeFactory.defaultInstance().constructCollectionType(List.class,
						Record.class);

				List<Record> resultList = objectMapper.readValue(resultArray.toString(), typeReference);

				if (resultList.isEmpty()) {
					// CREATE OFFENDER MANAGEMENT FILE and Return URI

					String classification = "7183"; // "CASE FILE"
					String title = min + "-" + OffenderName + "-" + dob;
					String recordType_OFFENDER_MANAGEMENT_FILE = "4";
					try {
						HttpPost post = new HttpPost(this.trimUrl + "/Record");
						post.setHeader("Accept", "application/json");
						List<NameValuePair> pairs = new ArrayList<NameValuePair>();
						pairs.add(new BasicNameValuePair("RecordType", recordType_OFFENDER_MANAGEMENT_FILE));
						pairs.add(new BasicNameValuePair("Title", title));
						pairs.add(new BasicNameValuePair("Classification", classification));
						pairs.add(new BasicNameValuePair("MIN", min));
						post.setEntity(new UrlEncodedFormEntity(pairs));

						this.addHttpRequestImpersonationHeader(post, trimUser);
						org.apache.http.HttpResponse res = httpClient.execute(post);
						int httpStatus1 = res.getStatusLine().getStatusCode();
						logger.info("getURIOffenderManagmentFile  httpStatus  : " + httpStatus1 + ", min  : " + min);
						String jsonRes = EntityUtils.toString(res.getEntity(), "UTF-8");
						logger.info("getURIOffenderManagmentFile  Response  : " + jsonRes + ", min  : " + min);

						JSONObject jsonObject = new JSONObject(jsonRes);

						if (res.getStatusLine().getStatusCode() == HttpStatus.OK.value()) {

							// JSONObject newOMFResponse =
							// jsonRes.getBody().getObject();

							JSONArray jsonArray = jsonObject.getJSONArray("Results");
							if (jsonArray.length() > 0) {

								JSONObject record = (JSONObject) jsonArray.get(0);
								offenderManagmentFileUri = String.valueOf(record.getInt("Uri"));

							}

						}

					} catch (org.apache.http.ParseException | JSONException | IOException e) {
						logger.error("getURIOffenderManagmentFile Fail to create  OFFENDER_MANAGEMENT_FILE" + e
								+ ", min  : " + min);
					}

				} else {
					// RETURN URI of OFFENDER MANAGEMENT FILE
					Record offenderManagementFileRecord = resultList.get(0);
					offenderManagmentFileUri = offenderManagementFileRecord.getUri();
				}
			}
		} catch (org.apache.http.ParseException | JSONException | URISyntaxException | IOException e) {
			// TODO Auto-generated catch block
			logger.error("Fail to create new OFFENDER_MANAGEMENT_FILE" + e + ", min  : " + min);
		}
		return offenderManagmentFileUri;
	}

	/**
	 * will return uri of SUB FOLDER
	 */
	@Override
	public String createSubFolder(String containerRecordNumber, String title, String classification,
			String offenedrBookingNo, String offenderIdDisplay, String trimUser) {

		String subFolderRecordUri = "";
		logger.info("createSubFolder Creating Sub Folder containerRecordNumber: " + containerRecordNumber
				+ ", classification: " + classification + ",offenedrBookingNo: " + offenedrBookingNo
				+ ", offenderIdDisplay: " + offenderIdDisplay);
		try {

			// CREATE SUB FOLDER

			String url = this.trimUrl + "/Record";

			HttpClient httpClient = getHttpClient();

			HttpPost post = new HttpPost(url);
			post.setHeader("Accept", "application/json");
			List<NameValuePair> pairs = new ArrayList<NameValuePair>();
			pairs.add(new BasicNameValuePair("RecordType", "2015"));
			pairs.add(new BasicNameValuePair("Container", containerRecordNumber));
			pairs.add(new BasicNameValuePair("Title", title));
			pairs.add(new BasicNameValuePair("Classification", classification));
			// pairs.add(new BasicNameValuePair("MIN", min));
			pairs.add(new BasicNameValuePair("MIN", offenderIdDisplay));
			pairs.add(new BasicNameValuePair("BookingNo", offenedrBookingNo));
			post.setEntity(new UrlEncodedFormEntity(pairs));
			this.addHttpRequestImpersonationHeader(post, trimUser);
			org.apache.http.HttpResponse response = httpClient.execute(post);
			int httpStatus = response.getStatusLine().getStatusCode();
			logger.info(
					"createSubFolder Creating Sub Folder Status: " + httpStatus + ", classification: " + classification
							+ ",offenedrBookingNo: " + offenedrBookingNo + ", offenderIdDisplay: " + offenderIdDisplay);
			String jsonResponse = EntityUtils.toString(response.getEntity(), "UTF-8");

			logger.info("createSubFolder Creating Sub Folder jsonResponse: " + jsonResponse + ", classification: "
					+ classification + ",offenedrBookingNo: " + offenedrBookingNo + ", offenderIdDisplay: "
					+ offenderIdDisplay);
			JSONObject jsonObject = new JSONObject(jsonResponse);

			if (httpStatus == EoffenderUtilities.STATUS_SUCCESS) {

				JSONArray jsonArray = jsonObject.getJSONArray("Results");

				if (jsonArray.length() > 0) {

					JSONObject record = (JSONObject) jsonArray.get(0);
					subFolderRecordUri = String.valueOf(record.getInt("Uri"));

				}

			}

		} catch (org.apache.http.ParseException | IOException e) {
			logger.error("createSubFolder Creating Sub Folder Failed :: " + e.getMessage() + ", classification: "
					+ classification + ",offenedrBookingNo: " + offenedrBookingNo + ", offenderIdDisplay: "
					+ offenderIdDisplay);
		}
		return subFolderRecordUri;
	}

	@Override
	public boolean isCheckedOutStatus(String documentUri, String trimUser) {
		boolean result = false;
		logger.info("isCheckedOutStatus documentUri : " + documentUri + ", trimUser : " + trimUser);
		try {

			HttpClient httpClient = getHttpClient();

			URIBuilder builder = new URIBuilder(this.trimUrl + "/Record").setParameter("q", "uri:" + documentUri)
					.setParameter("properties", "RecordDocumentStatus");

			URI uri = builder.build();

			HttpGet get = new HttpGet(uri);
			get.setHeader("Accept", "application/json");
			this.addHttpRequestImpersonationHeader(get, trimUser);
			org.apache.http.HttpResponse response = httpClient.execute(get);
			int httpStatus = response.getStatusLine().getStatusCode();

			String jsonResponse = EntityUtils.toString(response.getEntity(), "UTF-8");

			JSONObject jsonObject = new JSONObject(jsonResponse);

			logger.info("isCheckedOutStatus Response" + jsonResponse + ", documentUri" + documentUri);

			// JSONObject jsonObject = jsonResponse.getBody().getObject();

			JSONArray resultsJsonArray = jsonObject.getJSONArray("Results");

			if (resultsJsonArray.length() > 0 && httpStatus == EoffenderUtilities.STATUS_SUCCESS) {

				JSONObject resultJsonObject = resultsJsonArray.getJSONObject(0);

				JSONObject recordDocumentStatusJsonObject = resultJsonObject.getJSONObject("RecordDocumentStatus");

				if (CHECKED_OUT.equals(recordDocumentStatusJsonObject.getString("Value"))) {
					result = true;
				}

			}

		} catch (org.apache.http.ParseException | IOException | URISyntaxException e) {
			logger.error("isCheckedOutStatus Checking Checkout Status is Failed :: " + e.getMessage(), e);
		}
		return true;
	}

	@Override
	public String checkedOutDocument(String documentUri, String trimUser) {

		logger.info("checkedOutDocument documentUri : " + documentUri + ", trimUser : " + trimUser);
		String result = "CHECK-OUT SUCCESS";
		try {

			HttpClient httpClient = getHttpClient();

			HttpPost post = new HttpPost(this.trimUrl
					+ "/Record/?q=all&format=json&properties=number,templateuri,RecordIsCheckedOut,RecordCheckedOutOn,RecordDateFinalized");
			post.setHeader("Accept", "application/json");
			List<NameValuePair> pairs = new ArrayList<NameValuePair>();
			pairs.add(new BasicNameValuePair("Uri", documentUri));
			pairs.add(new BasicNameValuePair("Properties", "RecordCheckedOutTo"));
			pairs.add(new BasicNameValuePair("Checkout", "{\"CheckoutComments\": \"checked-Out via EOffender \"}"));
			post.setEntity(new UrlEncodedFormEntity(pairs));
			this.addHttpRequestImpersonationHeader(post, trimUser);
			org.apache.http.HttpResponse response = httpClient.execute(post);
			int httpStatus = response.getStatusLine().getStatusCode();
			logger.info("checkedOutDocument httpStatus: " + httpStatus + ", documentUri" + documentUri);
			String jsonResponse = EntityUtils.toString(response.getEntity(), "UTF-8");

			if (httpStatus == EoffenderUtilities.STATUS_SUCCESS) {
				JSONObject jsonObject = new JSONObject(jsonResponse);
				logger.info("checkedOutDocument Response: " + jsonResponse + ", documentUri" + documentUri);
				JSONObject ResponseStatus = jsonObject.getJSONObject("ResponseStatus");
				if (ResponseStatus.length() != 0) {
					if (ResponseStatus.has("Message") && !ResponseStatus.isNull("Message")) {
						String message = ResponseStatus.getString("Message");
						result = "CHECK-OUT FAILED :: " + httpStatus + " " + message;
						logger.error("checkedOutDocument CHECK-OUT FAILED : " + result + ", documentUri" + documentUri);
					}
				}
			} else {
				JSONObject jsonObject = new JSONObject(jsonResponse);
				JSONObject ResponseStatus = jsonObject.getJSONObject("ResponseStatus");
				String message = ResponseStatus.getString("Message");
				result = "CHECK-OUT FAILED :: " + httpStatus + " " + message;
			}
		} catch (org.apache.http.ParseException | IOException e) {
			result = "CHECK-OUT FAILED :: " + e.getMessage();
			logger.error("checkedOutDocument CHECK-OUT FAILED :" + result, e);
		}
		return result;

	}

	@Override
	public String finalisedDocument(String recordNumber, String trimUser) {
		logger.info("finalisedDocument recordNumber : " + recordNumber + " trimUser : " + trimUser);
		String documentUri = getURIForRecordNumber(recordNumber, trimUser);

		String result = "FINAL FAILED";
		try {

			HttpClient httpClient = getHttpClient();

			HttpPost post = new HttpPost(this.trimUrl
					+ "/Record/?q=all&format=json&properties=number,templateuri,RecordIsCheckedOut,RecordCheckedOutOn,RecordDateFinalized");
			post.setHeader("Accept", "application/json");
			List<NameValuePair> pairs = new ArrayList<NameValuePair>();
			pairs.add(new BasicNameValuePair("Uri", documentUri));
			pairs.add(new BasicNameValuePair("Properties", "RecordDocumentStatus"));
			pairs.add(new BasicNameValuePair("SetAsFinal", "{\"SetAsFinalRemoveOldRevisions\": false}"));
			post.setEntity(new UrlEncodedFormEntity(pairs));
			this.addHttpRequestImpersonationHeader(post, trimUser);
			org.apache.http.HttpResponse response = httpClient.execute(post);
			int httpStatus = response.getStatusLine().getStatusCode();
			logger.info("finalisedDocument httpStatus : " + httpStatus + ", recordNumber" + recordNumber);
			String jsonResponse = EntityUtils.toString(response.getEntity(), "UTF-8");

			JSONObject jsonObject = new JSONObject(jsonResponse);

			logger.info("finalisedDocument Response: " + jsonResponse + ", recordNumber" + recordNumber);

			// JSONObject jsonObject = jsonResponse.getBody().getObject();

			JSONArray resultsArray = jsonObject.getJSONArray("Results");

			if (resultsArray.length() > 0 && httpStatus == EoffenderUtilities.STATUS_SUCCESS) {

				JSONObject resultJSONObject = resultsArray.getJSONObject(0);

				JSONObject recordDocumentStatus = resultJSONObject.getJSONObject("RecordDocumentStatus");
				if (FINALIZED.equals(recordDocumentStatus.getString("Value"))) {
					result = "FINAL SUCESSS";
				}

			} else {
				JSONObject ResponseStatus = jsonObject.getJSONObject("ResponseStatus");

				if (ResponseStatus.length() != 0) {
					if (ResponseStatus.has("Message") && !ResponseStatus.isNull("Message")) {
						String message = ResponseStatus.getString("Message");
						result = "FINAL FAILED :: " + message;
					}

				}

			}

		} catch (org.apache.http.ParseException | IOException e) {
			result = "FINAL FAILED :: " + e.getMessage();
			logger.error("finalisedDocument FINAL FAILED: " + result, e);
		}
		return result;

	}

	@Override
	public String updateDocument(String recordType, String containerRecordNumber, String title, String classification,
			String filePath, String documentUri, String offenderIdDisplay, String offenderBookingNo, String trimUser)
			throws IOException {

		logger.info("recordType " + recordType + ", containerRecordNumber " + containerRecordNumber + ", title " + title
				+ ", classification " + classification + ", filePath " + filePath + ", documentUri " + documentUri
				+ ", offenderIdDisplay " + offenderIdDisplay + ", offenderBookingNo " + offenderBookingNo
				+ ", trimUser " + trimUser);

		String result = "SUCCESS";
		try {
			HttpClient httpClient = getHttpClient();

			HttpPost post = new HttpPost(this.trimUrl
					+ "/Record/?q=all&format=json&properties=number,templateuri,RecordIsCheckedOut,RecordCheckedOutOn,RecordDateFinalized");

			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			builder.addTextBody("RecordType", recordType);
			// builder.addTextBody("MIN", MIN);
			builder.addTextBody("MIN", offenderIdDisplay);
			builder.addTextBody("BookingNo", offenderBookingNo);
			builder.addTextBody("Classification", classification);
//			builder.addTextBody("Title", title);
			builder.addTextBody("container", containerRecordNumber);
			builder.addTextBody("keepBookedOut", "false");
			builder.addTextBody("makeNewRevision", "true");
			builder.addTextBody("Uri", documentUri);
			builder.addBinaryBody("Files", new File(filePath), ContentType.APPLICATION_OCTET_STREAM, "NewFileWithWatcher");

			org.apache.http.HttpEntity multipart = builder.build();
			post.setEntity(multipart);
			this.addHttpRequestImpersonationHeader(post, trimUser);
			org.apache.http.HttpResponse response = httpClient.execute(post);
			int httpStatus = response.getStatusLine().getStatusCode();
			logger.info("updateDocument httpStatus : " + httpStatus);
			String jsonResponse = EntityUtils.toString(response.getEntity(), "UTF-8");
			logger.info("updateDocument jsonResponse : " + jsonResponse);
			if (httpStatus == EoffenderUtilities.STATUS_SUCCESS) {
				JSONObject jsonObject = new JSONObject(jsonResponse);
				logger.info("updateDocument: " + jsonResponse + ", offenderBookingNo: " + offenderBookingNo
						+ " offenderIdDisplay: " + offenderIdDisplay);
				JSONObject ResponseStatus = jsonObject.getJSONObject("ResponseStatus");
				if (ResponseStatus.length() != 0) {
					if (ResponseStatus.has("Message") && !ResponseStatus.isNull("Message")) {
						String message = ResponseStatus.getString("Message");
						result = "UPLOAD FAILED :: " + message;
						logger.warn("updateDocument: " + result);
					}
				}
			} else {
				JSONObject jsonObject = new JSONObject(jsonResponse);
				JSONObject ResponseStatus = jsonObject.getJSONObject("ResponseStatus");
				String message = ResponseStatus.getString("Message");
				result = "UPLOAD FAILED :: " + message;
			}
		} catch (org.apache.http.ParseException e) {
			result = "UPLOAD FAILED :: " + e.getMessage();
			logger.warn("updateDocument: " + result, e);
		}
		return result;
	}

	
	
	@Override
	public String createDocument(String recordType, String containerRecordNumber, String title, String classification,
			String filePath, String templateUri, String objectId, String objectType, String locationUri,
			String offenderIdDisplay, String offenderBookingNo, boolean isFolder, String trimUser) throws IOException {

		logger.info("recordType " + recordType + ", containerRecordNumber " + containerRecordNumber + ", title " + title
				+ ", classification " + classification + ", filePath " + filePath + ", templateUri " + templateUri
				+ ", objectId " + objectId + ", objectType " + objectType + ", locationUri " + locationUri
				+ ", offenderIdDisplay " + offenderIdDisplay + "isFolder " + isFolder + " trimUser " + trimUser);

		String result = "SUCCESS";

		try {
			HttpClient httpClient = getHttpClient();
			HttpPost post = new HttpPost(this.trimUrl + "/Record");
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			post.setHeader("Accept", "application/json");
			if (!EoffenderUtilities.isEmpty(objectId) && !EoffenderUtilities.isEmpty(objectType)) {
				builder.addTextBody("ObjectId", objectId).addTextBody("ObjectType", objectType);
			}
			builder.addTextBody("RecordType", recordType);
			builder.addTextBody("Container", containerRecordNumber);
			builder.addTextBody("Title", title);
			builder.addTextBody("Classification", classification);
			builder.addTextBody("MIN", offenderIdDisplay);
			builder.addTextBody("BookingNo", offenderBookingNo);
			if (isFolder) {
				templateUri = "";
			}
			logger.info("createDocument final templateUri :: " + templateUri + ", for offenderIdDisplay "
					+ offenderIdDisplay);
			builder.addTextBody("TemplateUri", templateUri);
			builder.addTextBody("RecordAuthor", locationUri);
			builder.addBinaryBody("Files", new File(filePath), ContentType.APPLICATION_OCTET_STREAM, title);

			org.apache.http.HttpEntity multipart = builder.build();
			post.setEntity(multipart);
			this.addHttpRequestImpersonationHeader(post, trimUser);
			org.apache.http.HttpResponse response = httpClient.execute(post);
			int httpStatus = response.getStatusLine().getStatusCode();

			String jsonResponse = EntityUtils.toString(response.getEntity(), "UTF-8");

			JSONObject jsonObject = new JSONObject(jsonResponse);
			logger.info("createDocument Status : " + httpStatus + " offenderBookingNo: " + offenderBookingNo
					+ " offenderIdDisplay: " + offenderIdDisplay);
			logger.info("createDocument Response : " + jsonResponse + " offenderBookingNo: " + offenderBookingNo
					+ " offenderIdDisplay: " + offenderIdDisplay);

			JSONObject ResponseStatus = jsonObject.getJSONObject("ResponseStatus");

			if (ResponseStatus.length() != 0) {
				if (ResponseStatus.has("Message") && !ResponseStatus.isNull("Message")) {
					String message = ResponseStatus.getString("Message");
					result = "UPLOAD FAILED :: " + message;
					logger.warn("createDocument: " + result + " offenderBookingNo: " + offenderBookingNo
							+ " offenderIdDisplay: " + offenderIdDisplay);
				}
			}
		} catch (org.apache.http.ParseException e) {
			result = "UPLOAD FAILED :: " + e.getMessage();
			logger.error("createDocument: Failed to upload document " + result + " offenderBookingNo: "
					+ offenderBookingNo + " offenderIdDisplay: " + offenderIdDisplay);
		}
		return result;
	}

	@Override
	public String cancelDocument(String documentUri, String trimUser) {

		logger.info("cancelDocument documentUri: " + documentUri + ", trimUser: " + trimUser);

		String result = "CANCEL CHECKOUT FAILED";
		try {

			HttpClient httpClient = getHttpClient();

			HttpPost post = new HttpPost(this.trimUrl
					+ "/Record/?q=all&format=json&properties=number,templateuri,RecordIsCheckedOut,RecordCheckedOutOn,RecordDateFinalized");
			post.setHeader("Accept", "application/json");
			List<NameValuePair> pairs = new ArrayList<NameValuePair>();
			pairs.add(new BasicNameValuePair("Uri", documentUri));
			pairs.add(new BasicNameValuePair("Properties", "RecordDocumentStatus"));
			pairs.add(new BasicNameValuePair("UndoCheckout", "{}"));
			post.setEntity(new UrlEncodedFormEntity(pairs));
			this.addHttpRequestImpersonationHeader(post, trimUser);
			org.apache.http.HttpResponse response = httpClient.execute(post);
			int httpStatus = response.getStatusLine().getStatusCode();
			logger.info("cancelDocument httpStatus: " + httpStatus + ", documentUri : " + documentUri);
			String jsonResponse = EntityUtils.toString(response.getEntity(), "UTF-8");

			JSONObject jsonObject = new JSONObject(jsonResponse);

			logger.info("cancelDocument Response: " + jsonResponse + ", documentUri : " + documentUri);

			// JSONObject jsonObject = jsonResponse.getBody().getObject();

			JSONArray resultsArray = jsonObject.getJSONArray("Results");

			if (httpStatus == EoffenderUtilities.STATUS_SUCCESS && resultsArray.length() > 0) {

				JSONObject resultJSONObject = resultsArray.getJSONObject(0);

				JSONObject recordDocumentStatus = resultJSONObject.getJSONObject("RecordDocumentStatus");
				if (CHECKED_IN.equals(recordDocumentStatus.getString("Value"))) {
					result = "CANCEL CHECKOUT SUCESSS";
				}
			} else {
				JSONObject ResponseStatus = jsonObject.getJSONObject("ResponseStatus");

				if (ResponseStatus.length() != 0) {
					if (ResponseStatus.has("Message") && !ResponseStatus.isNull("Message")) {
						String message = ResponseStatus.getString("Message");
						result = "CANCEL CHECKOUT FAILED :: " + message;
					}
				}
			}
		} catch (org.apache.http.ParseException | IOException e) {
			result = "CANCEL CHECKOUT FAILED :: " + e.getMessage();
			logger.error("cancelDocument : " + result, e);
		}
		return result;
	}

	@Override
	public String getClassficationFromTemlateId(String templateId, String trimUser) {
		logger.info("getClassficationFromTemlateId templateId: " + templateId + ", trimUser : " + trimUser);
		String classificationUri = "";

		String url = this.trimUrl + "/Record/" + templateId;

		HttpClient httpClient = getHttpClient();
		try {
			URIBuilder builder = new URIBuilder(url).setParameter("properties", "classification").setParameter("format",
					"json");

			URI uri = builder.build();

			HttpGet get = new HttpGet(uri);
			get.setHeader("Accept", "application/json");
			this.addHttpRequestImpersonationHeader(get, trimUser);
			org.apache.http.HttpResponse response = httpClient.execute(get);
			int httpStatus = response.getStatusLine().getStatusCode();
			logger.info("getClassficationFromTemlateId httpStatus: " + httpStatus + ", templateId : " + templateId);
			String jsonResponse = EntityUtils.toString(response.getEntity(), "UTF-8");
			logger.info("getClassficationFromTemlateId jsonResponse: " + jsonResponse + ", templateId : " + templateId);
			JSONObject recordResponse = new JSONObject(jsonResponse);
			JSONArray resultArray = recordResponse.getJSONArray("Results");

			if (response.getStatusLine().getStatusCode() == EoffenderUtilities.STATUS_SUCCESS) {

				ObjectMapper objectMapper = new ObjectMapper();

				CollectionType typeReference = TypeFactory.defaultInstance().constructCollectionType(List.class,
						Record.class);

				List<Record> trimRecordList = objectMapper.readValue(resultArray.toString(), typeReference);

				// List<Record> trimRecordList = recordResponse.getResultList();

				if (!trimRecordList.isEmpty()) {
					classificationUri = trimRecordList.get(0).getRecordClassification().getUri();
				}
			} else {

				RecordResponseStatus responseStatusObject = (RecordResponseStatus) response;// recordResponse.getResponseStatusObject();
				if (responseStatusObject != null)

					logger.error("getClassficationFromTemlateId Can't Find Classification for given Document Type :: "
							+ responseStatusObject.getMessage() + ", templateId" + templateId);
			}

		} catch (RestClientException | org.apache.http.ParseException | IOException | URISyntaxException e) {
			logger.error("getClassficationFromTemlateId Failed :: " + e.getMessage(), e);
		}

		return classificationUri;

	}

	@Override
	public Map<String, String> getClassficationUriForFolder(String folderNoCommaSeparated, String trimUser) {
		Map<String, String> classificationMap = new HashMap<String, String>();
		// List<String> classificationUriList = new ArrayList<>();
		logger.info("getClassficationUriForFolder  folderNoCommaSeparated : " + folderNoCommaSeparated + ", trimUser : "
				+ trimUser);
		try {
			HttpClient httpClient = getHttpClient();

			URIBuilder builder = new URIBuilder(this.trimUrl + "/Classification")
					.setParameter("q", "number:" + folderNoCommaSeparated).setParameter("properties", "Uri, IdNumber");

			URI uri = builder.build();

			HttpGet get = new HttpGet(uri);
			get.setHeader("Accept", "application/json");
			this.addHttpRequestImpersonationHeader(get, trimUser);
			org.apache.http.HttpResponse response = httpClient.execute(get);
			int httpStatus = response.getStatusLine().getStatusCode();
			logger.info(
					"getClassficationUriForFolder httpStatus: " + httpStatus + ", folderNo" + folderNoCommaSeparated);
			String jsonResponse = EntityUtils.toString(response.getEntity(), "UTF-8");
			logger.info("getClassficationUriForFolder jsonResponse: " + jsonResponse);
			JSONObject recordResponse = new JSONObject(jsonResponse);
			JSONArray resultArray = recordResponse.getJSONArray("Results");

			if (response.getStatusLine().getStatusCode() == EoffenderUtilities.STATUS_SUCCESS) {

				ObjectMapper objectMapper = new ObjectMapper();

				CollectionType typeReference = TypeFactory.defaultInstance().constructCollectionType(List.class,
						Record.class);

				List<Record> trimRecordList = objectMapper.readValue(resultArray.toString(), typeReference);

				if (!trimRecordList.isEmpty()) {
					for (int i = 0; i < trimRecordList.size(); i++) {
						classificationMap.put(trimRecordList.get(i).getUri(),
								trimRecordList.get(i).getClassificationIdNumber().getValue());
					}
				}
			} else {
				logger.error("getClassficationUriForFolder Can't Find Classification URI for given Folder Type :: "
						+ response!=null?response.getStatusLine().getStatusCode():null + " Trim Exception" + ", folderNo"
						+ folderNoCommaSeparated);
			}

		} catch (RestClientException | org.apache.http.ParseException | IOException | URISyntaxException e) {
			logger.error("getClassficationUriForFolder Failed :: " + e.getMessage(), e);
		} catch (Exception ex) {
			logger.error("getClassficationUriForFolder Failed :: " + ex.getMessage(), ex);
		}

		return classificationMap;

	}

	@Override
	public Map<String, String> isSubfolderExist(String containerURI, String objectId, boolean isFolder,
			String trimUser) {
		logger.info("isSubfolderExist containerURI : " + containerURI + ", objectId " + objectId + ", trimUser "
				+ trimUser + ", isFolder " + isFolder);
		Map<String, String> result = new HashMap<>();
		String url = this.trimUrl + "/Record";
		logger.info("isSubfolderExist containerURI: " + containerURI + ", objectId: " + objectId);
		HttpClient httpClient = getHttpClient();
		try {
			URIBuilder builder = new URIBuilder(url);

			StringBuilder qParams = new StringBuilder();
			qParams.append("container:").append(containerURI).append(",type:2015").append(",classification:")
					.append(objectId);

			builder.setParameter("q", qParams.toString()).setParameter("properties", "RecordNumber,uri,classification")
					.setParameter("CountResults", "true").setParameter("format", "json");

			URI uri = builder.build();
			HttpGet get = new HttpGet(uri);
			get.setHeader("Accept", "application/json");
			this.addHttpRequestImpersonationHeader(get, trimUser);
			org.apache.http.HttpResponse response = httpClient.execute(get);
			int httpStatus = response.getStatusLine().getStatusCode();
			logger.info("isSubfolderExist Status: " + httpStatus + "containerURI: " + containerURI + ", objectId: "
					+ objectId);
			String jsonResponse = EntityUtils.toString(response.getEntity(), "UTF-8");
			logger.info("isSubfolderExist jsonResponse: " + jsonResponse + "containerURI: " + containerURI
					+ ", objectId: " + objectId);

			if (httpStatus == EoffenderUtilities.STATUS_SUCCESS) {
				JSONObject jsonObject = new JSONObject(jsonResponse);
				JSONArray resultArray = jsonObject.getJSONArray("Results");
				ObjectMapper objectMapper = new ObjectMapper();
				CollectionType typeReference = TypeFactory.defaultInstance().constructCollectionType(List.class,
						Record.class);

				List<Record> trimRecordList = objectMapper.readValue(resultArray.toString(), typeReference);
				if (!trimRecordList.isEmpty()) {

					String subFolderClassificationUri = trimRecordList.get(0).getRecordClassification().getUri();
					String subFolderRecordNumber = trimRecordList.get(0).getRecordNumber().getValue();
					String subFolderUri = trimRecordList.get(0).getUri();
					result.put("subFolderRecordNumber", subFolderRecordNumber);
					result.put("subFolderUri", subFolderUri);
					result.put("subFolderClassificationUri", subFolderClassificationUri);

				}
			} else {
				logger.info("isSubfolderExist Failed Status: " + httpStatus + "containerURI: " + containerURI
						+ ", objectId: " + objectId);

			}

		} catch (org.apache.http.ParseException | JSONException | URISyntaxException | IOException e) {
			logger.error("isSubfolderExist Error: " + e + "containerURI: " + containerURI + ", objectId: " + objectId);
		}

		return result;

	}

	@Override
	public String getRecordNumberForURI(String uri, String trimUser) {
		logger.info("getRecordNumberForURI URI: " + uri + ", trimUser : " + trimUser);
		logger.info("getRecordNumberForURI URI: " + uri);
		String recordNumber = "";
		String url = this.trimUrl + "/Record";
		HttpClient httpClient = getHttpClient();

		try {
			URIBuilder builder = new URIBuilder(url);

			StringBuilder qParams = new StringBuilder();
			qParams.append("uri:").append(uri); // Need to conform if "uri"
												// could used here in case of
												// Template

			builder.setParameter("q", qParams.toString()).setParameter("properties", "RecordNumber")
					.setParameter("CountResults", "true").setParameter("format", "json");

			URI buildUri = builder.build();
			HttpGet get = new HttpGet(buildUri);
			get.setHeader("Accept", "application/json");
			this.addHttpRequestImpersonationHeader(get, trimUser);
			org.apache.http.HttpResponse response = httpClient.execute(get);
			int httpStatus = response.getStatusLine().getStatusCode();
			logger.info("getRecordNumberForURI httpStatus: " + httpStatus + "URI: " + uri);
			String jsonResponse = EntityUtils.toString(response.getEntity(), "UTF-8");
			logger.info("getRecordNumberForURI Response: " + jsonResponse + "URI: " + uri);

			if (httpStatus == EoffenderUtilities.STATUS_SUCCESS) {
				JSONObject jsonObject = new JSONObject(jsonResponse);
				JSONArray resultArray = jsonObject.getJSONArray("Results");
				ObjectMapper objectMapper = new ObjectMapper();
				CollectionType typeReference = TypeFactory.defaultInstance().constructCollectionType(List.class,
						Record.class);
				List<Record> trimRecordList = objectMapper.readValue(resultArray.toString(), typeReference);
				if (!trimRecordList.isEmpty()) {
					recordNumber = trimRecordList.get(0).getRecordNumber().getValue();
				}
			} else {
				recordNumber = "";
			}
		} catch (org.apache.http.ParseException | JSONException | URISyntaxException | IOException e) {
			logger.error("getRecordNumberForURI Error: " + e + "URI: " + uri);
		}

		return recordNumber;

	}

	public String getURIForRecordNumber(String recordNumber, String trimUser) {

		logger.info("getURIForRecordNumber RecordNumber: " + recordNumber + ", trimUser : " + trimUser);

		String recordUri = "";

		String url = this.trimUrl + "/Record";
		HttpClient httpClient = getHttpClient();

		try {
			URIBuilder builder = new URIBuilder(url);
			StringBuilder qParams = new StringBuilder();
			qParams.append("Number:").append(recordNumber);

			builder.setParameter("q", qParams.toString()).setParameter("properties", "Uri").setParameter("format",
					"json");

			URI buildUri = builder.build();
			HttpGet get = new HttpGet(buildUri);
			get.setHeader("Accept", "application/json");
			this.addHttpRequestImpersonationHeader(get, trimUser);
			org.apache.http.HttpResponse response = httpClient.execute(get);
			int httpStatus = response.getStatusLine().getStatusCode();
			logger.info("getURIForRecordNumber httpStatus : " + httpStatus);
			String jsonResponse = EntityUtils.toString(response.getEntity(), "UTF-8");
			logger.info("getURIForRecordNumber Response: " + jsonResponse);
			if (httpStatus == EoffenderUtilities.STATUS_SUCCESS) {
				JSONObject jsonObject = new JSONObject(jsonResponse);
				JSONArray resultArray = jsonObject.getJSONArray("Results");
				ObjectMapper objectMapper = new ObjectMapper();
				CollectionType typeReference = TypeFactory.defaultInstance().constructCollectionType(List.class,
						Record.class);
				List<Record> trimRecordList = objectMapper.readValue(resultArray.toString(), typeReference);
				if (!trimRecordList.isEmpty()) {
					recordUri = trimRecordList.get(0).getUri();
				}
			}
		} catch (org.apache.http.ParseException | JSONException | URISyntaxException | IOException e) {
			// TODO Auto-generated catch block
			logger.error("getURIForRecordNumber Error: " + e);
		}

		return recordUri;

	}

	@Override
	public RecordAccessControl getSecurityAccessControlData(String containerURI, String edrms_id, boolean isFolder) {

		logger.info("getSecurityAccessControlData containerURI : " + containerURI + ", edrms_id: " + edrms_id);
		String url = this.trimUrl + "/Record";
		HttpClient httpClient = getHttpClient();
		logger.info("getSecurityAccessControlData containerURI: " + containerURI + ", edrms_id" + edrms_id);
		try {
			URIBuilder builder = new URIBuilder(url);
			if (isFolder) {

				StringBuilder qParams = new StringBuilder();
				qParams.append("container:").append(containerURI).append(",type:2015").append(",uri:").append(edrms_id); // Need
																															// to
																															// conform
																															// if
																															// "uri"
																															// could
																															// used
																															// here
																															// in
																															// case
																															// of
																															// Template

				builder.setParameter("q", qParams.toString()).setParameter("properties", "RecordAccessControl")
						.setParameter("CountResults", "true").setParameter("format", "json");

			} else {
				url = url + "/" + edrms_id;
				StringBuilder qParams = new StringBuilder();
				qParams.append("container:").append(containerURI).append(",type:2015");

				builder.setParameter("q", qParams.toString()).setParameter("properties", "RecordAccessControl")
						.setParameter("format", "json");

			}
			URI uri = builder.build();
			HttpGet get = new HttpGet(uri);
			get.setHeader("Accept", "application/json");
			org.apache.http.HttpResponse response = httpClient.execute(get);
			int httpStatus = response.getStatusLine().getStatusCode();

			String jsonResponse = EntityUtils.toString(response.getEntity(), "UTF-8");
			logger.info("getSecurityAccessControlData Response : " + jsonResponse + ", edrms_id " + edrms_id);

			if (httpStatus == 200) {
				// recordResponse = response.getBody();
			}

			JSONObject jsonObject = new JSONObject(jsonResponse);
			JSONArray resultArray = jsonObject.getJSONArray("Results");
			ObjectMapper objectMapper = new ObjectMapper();
			CollectionType typeReference = TypeFactory.defaultInstance().constructCollectionType(List.class,
					Record.class);

			List<Record> trimRecordList = objectMapper.readValue(resultArray.toString(), typeReference);

			if (!trimRecordList.isEmpty()) {

				return trimRecordList.get(0).getRecordAccessControl();

			}
		} catch (org.apache.http.ParseException | JSONException | URISyntaxException | IOException e) {
			// TODO Auto-generated catch block
			logger.error("getSecurityAccessControlData Error: " + e + ", edrms_id " + edrms_id);
		}

		return null;

	}

	@Override
	public String getLocationUri(String location, String trimUser) {
		logger.info("getLocationUri location : " + location + ", trimUser : " + trimUser);
		String locationUri = null;
		String result = "INVALID USER";
		try {

			StringBuilder qParams = new StringBuilder();
			qParams.append("login").append("=").append(location); // Need to
																	// conform
																	// if "uri"
																	// could
																	// used here
																	// in case
																	// of
																	// Template

			HttpClient httpClient = getHttpClient();

			URIBuilder builder = new URIBuilder(this.trimUrl + "/Location").setParameter("q", qParams.toString());

			URI uri = builder.build();

			HttpGet get = new HttpGet(uri);
			get.setHeader("Accept", "application/json");
			this.addHttpRequestImpersonationHeader(get, trimUser);
			org.apache.http.HttpResponse response = httpClient.execute(get);
			int httpStatus = response.getStatusLine().getStatusCode();
			logger.info("getLocationUri httpStatus : " + httpStatus + ", location " + location);
			String jsonResponse = EntityUtils.toString(response.getEntity(), "UTF-8");
			logger.info("getLocationUri jsonResponse : " + jsonResponse + ", location " + location);

			if (httpStatus == EoffenderUtilities.STATUS_SUCCESS) {
				JSONObject jsonObject = new JSONObject(jsonResponse);

				logger.info("getLocationUri Response :" + jsonResponse);

				JSONArray resultsArray = jsonObject.getJSONArray("Results");

				if (resultsArray.length() > 0) {
					JSONObject resultJSONObject = resultsArray.getJSONObject(0);
					locationUri = String.valueOf(resultJSONObject.getInt("Uri"));
				}
			}

		} catch (org.apache.http.ParseException | IOException | URISyntaxException e) {
			result = "Fetch location Uri Failed :: " + e.getMessage();
			logger.warn(result, e);
		}
		return locationUri;
	}

	@Override
	public Map<String, String> getTemplateUriIdMap(List<EoffenderTemplate> templateList, String trimUser) {
		logger.info("getTemplateUriIdMap templateList :" + templateList + ", trimUser : " + trimUser);
		String url = this.trimUrl + "/Record";
		StringBuilder qParams = new StringBuilder();
		Map<String, String> templateUriIdMap = new HashMap<>();
		//qParams.append(templateList.stream().map(templateObject -> templateObject.getTemplateId()).collect(Collectors.joining(",")));
		HttpClient httpClient = getHttpClient();
		logger.info("getTemplateUriIdMap  templateList ::" + qParams.toString());
		try {
			URIBuilder builder = new URIBuilder(url).setParameter("q", "number:"+qParams.toString())
					.setParameter("properties", "RecordNumber,Status,TemplateUri,uri")
					.setParameter("sortBy", "createdOn-").setParameter("ExcludeCount", "True")
					.setParameter("pageSize", env.getProperty("trim.pageSize"));

			URI uri = builder.build();

			HttpGet get = new HttpGet(uri);
			get.setHeader("Accept", "application/json");
			this.addHttpRequestImpersonationHeader(get, trimUser);
			org.apache.http.HttpResponse response = httpClient.execute(get);
			int httpStatus = response.getStatusLine().getStatusCode();
			logger.info("getTemplateUriIdMap httpStatus : " + httpStatus +" for url "+builder.toString()+" , it's response is  "+response);
			if (httpStatus == EoffenderUtilities.STATUS_SUCCESS) {
				String jsonResponse = EntityUtils.toString(response.getEntity(), "UTF-8");
				logger.info("getTemplateUriIdMap jsonResponse : " + jsonResponse);
				JSONObject jsonObject = new JSONObject(jsonResponse);
				JSONArray resultArray = jsonObject.getJSONArray("Results");

				logger.info("getTemplateUriIdMap result array  : " + resultArray);

				ObjectMapper objectMapper = new ObjectMapper();

				CollectionType typeReference = TypeFactory.defaultInstance().constructCollectionType(List.class,
						Record.class);

				List<Record> trimRecordList = objectMapper.readValue(resultArray.toString(), typeReference);

				trimRecordList.forEach(record -> {
					templateUriIdMap.put(record.getRecordNumber().getValue(), record.getUri());
				});
			}
		} catch (org.apache.http.ParseException | URISyntaxException | IOException e) {

			e.printStackTrace();
		}
		return templateUriIdMap;
	}

	@Override
	public Map<String, String> partitionedTemplateUriMap(List<EoffenderTemplate> templateList, String trimUser) {
		logger.info("partitionedTemplateUriMap templateList :" + templateList + ", trimUser : " + trimUser);
		Map<String, String> templateUriIdMap = new HashMap<>();
		//float size = templateList.size();
		//int partitions = (int) Math.ceil(size / TEMPLATE_LIST_CHUNK_SIZE);
		//ExecutorService templateListExecutor = Executors.newFixedThreadPool(partitions);

		List<List<EoffenderTemplate>> partitionedTemplateList = getPartitionedList(templateList);

		List<Future<Map<String, String>>> futureList = new ArrayList<>();
		List<Callable<Map<String, String>>> taskList = new ArrayList<>();

		for (List<EoffenderTemplate> templateListChunk : partitionedTemplateList) {
			Callable<Map<String, String>> templateUriMapCallable = () -> {
				return getTemplateUriIdMap(templateListChunk, trimUser);
			};

			taskList.add(templateUriMapCallable);
			futureList.add(taskExecutor.submit(templateUriMapCallable));
		}

//		try {
//			futureList = taskExecutor.invokeAll(taskList);
//		} catch (InterruptedException e) {
//			logger.error("partitionedTemplateUriMap Error: " + e);
//		}
		//templateListExecutor.shutdown();

		for (int i = 0; i < futureList.size(); i++) {
			Future<Map<String, String>> future = futureList.get(i);
			try {
				templateUriIdMap.putAll(future.get());
			} catch (InterruptedException | ExecutionException e) {
				logger.error("partitionedTemplateUriMap Error: " + e);
			}
		}

		return templateUriIdMap;
	}

	private List<List<EoffenderTemplate>> getPartitionedList(List<EoffenderTemplate> templateList) {
		List<List<EoffenderTemplate>> partitionedTemplateList = new ArrayList<>();
		float size = templateList.size();
		int partitions = (int) Math.ceil(size / TEMPLATE_LIST_CHUNK_SIZE);
		int begin = 0;
		int end = 0;
		while (partitions > 0) {
			end = begin + TEMPLATE_LIST_CHUNK_SIZE;
			if (end >= size) {
				end = (int) size;
			}
			partitionedTemplateList.add(templateList.subList(begin, end));
			begin = begin + TEMPLATE_LIST_CHUNK_SIZE;
			partitions--;
		}
		return partitionedTemplateList;
	}
	
	@Override
	public org.apache.http.HttpResponse viewTRIMFile(String documentUri, String trimUser) throws ClientProtocolException, IOException {
				
		logger.info("viewTRIMFile from documentLink: " +documentUri +" trimUser :: "+trimUser);
		String fileDownloadUrl = env.getProperty("trim.url")
				+ "/Record/".concat(documentUri).concat("/File/document");
	    
	    HttpClient httpClient = getHttpClient();
              
        HttpGet get = new HttpGet(fileDownloadUrl);
        
        get.setHeader("Content-type", "application/x-www-form-urlencoded");
        get.setHeader("Cache-Control", "no-cache");
        this.addHttpRequestImpersonationHeader(get, trimUser);
		org.apache.http.HttpResponse response = httpClient.execute(get);
		int httpStatus = response.getStatusLine().getStatusCode();
		
		logger.info("viewTRIMFile response  : "+response +", URI "+ documentUri);
		logger.info("viewTRIMFile response status : "+httpStatus +", URI "+ documentUri);
		
		
	    return response;
		
	}
	
	public org.apache.http.HttpResponse downloadFileFromTRIM(Document documentMetadata) throws ClientProtocolException, IOException{
		String fileDownloadUrl = env.getProperty("trim.url")
				+ "/Record/".concat(documentMetadata.getDocumentId()).concat("/File/document");
		HttpClient httpClient = getHttpClient();
              
        HttpGet get = new HttpGet(fileDownloadUrl);

        get.setHeader("Content-type", "application/x-www-form-urlencoded");
        get.setHeader("Cache-Control", "no-cache");
        this.addHttpRequestImpersonationHeader(get, documentMetadata.getTrimUser());
		org.apache.http.HttpResponse response = httpClient.execute(get);
		return response;
	}

	@Override
	public Integer getLanguageId() {
		Integer languageId=null;
		SystemProfiles searchObj=new SystemProfiles();
		searchObj.setProfileType("CLIENT");
		searchObj.setProfileCode("SPEL_LANG_ID");
		List<SystemProfiles> profileData=oumsypflRepository.sysPflExecuteQuery(searchObj);
		if(profileData!=null && profileData.get(0)!=null && profileData.get(0).getProfileValue()!=null) {
		Resource spellCheckResource = new ClassPathResource("spellcheck.json");
		String jsonContent;
		try {
		jsonContent = new String(Files.readAllBytes(spellCheckResource.getFile().toPath()), StandardCharsets.UTF_8);
		JsonArray spellDictionaryItems = new Gson().fromJson(jsonContent, JsonArray.class);
		for(int i = 0; i < spellDictionaryItems.size(); i++) {
			JsonObject spellCheckerInfo = spellDictionaryItems.get(i).getAsJsonObject();
			 if(spellCheckerInfo.has("DictionaryPath") && spellCheckerInfo.get("DictionaryPath")!=null && (spellCheckerInfo.get("DictionaryPath").toString().toUpperCase().contains(profileData.get(0).getProfileValue().toString()))) {
				 languageId=spellCheckerInfo.get("LanguadeID").getAsInt();
			 }
		}
		} catch (IOException e) {
			logger.error("Error in getLanguageId :: " + e.getMessage(), e);
		}
		}
		return languageId;
	}

}