package net.syscon.s4.services.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import net.syscon.s4.common.beans.ItemTransactionStatii;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.property.bean.CaseloadFormatPrinter;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;
import net.syscon.s4.inst.property.bean.OffenderPptyItemTxns;
import net.syscon.s4.inst.property.bean.OffenderPptyItemTxnsCommitBean;
import net.syscon.s4.inst.property.bean.OffenderPptyItems;
import net.syscon.s4.inst.property.bean.OffenderPptyItemsCommitBean;
import net.syscon.s4.services.config.EliteSpringConfig;
/**
 * Class OidtpritControllerMockTestCase
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {EliteSpringConfig.class})
@WebAppConfiguration
@WithMockCustomUser
public class OidtpritControllerMockTestCase extends AbstractMockTestCase {
	/**
	* Logger object used to print the log in the file
	*/
	//private static Logger logger = Logger.getLogger(OIDTPRITServiceMockTestCase.class);
	private static Logger logger = LogManager.getLogger(OidtpritControllerMockTestCase.class);
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void oidtpritItmTxExecuteQueryTestMethod() {
		try {
			OffenderPptyItemTxns offenderPptyItemTxns = new OffenderPptyItemTxns();
			String jsonInString = new ObjectMapper().writeValueAsString(offenderPptyItemTxns);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidtprit/itmTxExecuteQuery", offenderPptyItemTxns)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].propertyItemTxnId", is(5662)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void oidtpritItmTxCommitTestMethod() {
		try {
			OffenderPptyItemTxns offenderPptyItemTxns = new OffenderPptyItemTxns();
			offenderPptyItemTxns.setPropertyItemTxnId(18890);
			offenderPptyItemTxns.setOffenderBookId(18733);
			offenderPptyItemTxns.setPropertyItemSeq(2);
			offenderPptyItemTxns.setEventSeq(1);
			offenderPptyItemTxns.setFromStatusCode("SR");
			offenderPptyItemTxns.setToStatusCode("ka");
			offenderPptyItemTxns.setCreateDate(new Date());
			offenderPptyItemTxns.setCommentText("AA");
			offenderPptyItemTxns.setAgyLocId("CCC");
			offenderPptyItemTxns.setCreateUserId("kanth");
			offenderPptyItemTxns.setDisposedToOffenderFlag("N");
			offenderPptyItemTxns.setVerificationFlag("S");
			offenderPptyItemTxns.setVerifyFlag("N");
			offenderPptyItemTxns.setDisposedToCorpId(2010);
			offenderPptyItemTxns.setDisposedToPerson("La");
			offenderPptyItemTxns.setPropertyContainerId(123);
			offenderPptyItemTxns.setPropertyContainerTxnId(123);
			offenderPptyItemTxns.setToPropertyContainerId(234);
			offenderPptyItemTxns.setDisposedToPersonId(5200);
			offenderPptyItemTxns.setColor("REd");
			offenderPptyItemTxns.setMake("gg");
			offenderPptyItemTxns.setSerialNo("11");
			offenderPptyItemTxns.setCreateDatetime(new Timestamp(0, 0, 0, 0, 0, 0, 0));
			offenderPptyItemTxns.setModifyDatetime(new Timestamp(0, 0, 0, 0, 0, 0, 0));
			offenderPptyItemTxns.setModifyUserId("uuu");
			offenderPptyItemTxns.setSealFlag("N");
			
			List<OffenderPptyItemTxns> offenderPptyItemTxnsList = new ArrayList<OffenderPptyItemTxns>();
			offenderPptyItemTxnsList.add(offenderPptyItemTxns);
			OffenderPptyItemTxnsCommitBean offenderPptyItemTxnsCommitBean = new OffenderPptyItemTxnsCommitBean();
			offenderPptyItemTxnsCommitBean.setInsertList(offenderPptyItemTxnsList);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(offenderPptyItemTxnsCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidtprit/itmTxCommit", offenderPptyItemTxnsList)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	
	@Test
	public void oidtpritItmTxUpdateTestMethod() {
		try {
			OffenderPptyItemTxns offenderPptyItemTxns = new OffenderPptyItemTxns();
			offenderPptyItemTxns.setOffenderBookId(18733);
			offenderPptyItemTxns.setPropertyItemSeq(2);
			offenderPptyItemTxns.setEventSeq(1);
			offenderPptyItemTxns.setFromStatusCode("SR");
			offenderPptyItemTxns.setToStatusCode("ka");
			offenderPptyItemTxns.setCreateDate(new Date());
			offenderPptyItemTxns.setCommentText("AA");
			offenderPptyItemTxns.setAgyLocId("CCC");
			offenderPptyItemTxns.setCreateUserId("kanth");
			offenderPptyItemTxns.setDisposedToOffenderFlag("N");
			offenderPptyItemTxns.setVerificationFlag("S");
			offenderPptyItemTxns.setVerifyFlag("N");
			offenderPptyItemTxns.setDisposedToCorpId(2010);
			offenderPptyItemTxns.setDisposedToPerson("LaL");
			offenderPptyItemTxns.setPropertyContainerId(123);
			offenderPptyItemTxns.setPropertyContainerTxnId(123);
			offenderPptyItemTxns.setToPropertyContainerId(234);
			offenderPptyItemTxns.setDisposedToPersonId(5200);
			offenderPptyItemTxns.setColor("Blue");
			offenderPptyItemTxns.setMake("gg");
			offenderPptyItemTxns.setSerialNo("11");
			offenderPptyItemTxns.setCreateDatetime(new Timestamp(0, 0, 0, 0, 0, 0, 0));
			offenderPptyItemTxns.setModifyDatetime(new Timestamp(0, 0, 0, 0, 0, 0, 0));
			offenderPptyItemTxns.setModifyUserId("uuu");
			offenderPptyItemTxns.setSealFlag("N");
			offenderPptyItemTxns.setPropertyItemTxnId(18880);
			List<OffenderPptyItemTxns> offenderPptyItemTxnsList = new ArrayList<OffenderPptyItemTxns>();
			offenderPptyItemTxnsList.add(offenderPptyItemTxns);
			OffenderPptyItemTxnsCommitBean offenderPptyItemTxnsCommitBean = new OffenderPptyItemTxnsCommitBean();
			offenderPptyItemTxnsCommitBean.setUpdateList(offenderPptyItemTxnsList);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(offenderPptyItemTxnsCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidtprit/itmTxCommit", offenderPptyItemTxnsList)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	@Test
	public void oidtpritItmTxDeleteTestMethod() {
		try {
			OffenderPptyItemTxns offenderPptyItemTxns = new OffenderPptyItemTxns();
			offenderPptyItemTxns.setPropertyItemTxnId(18880);
			List<OffenderPptyItemTxns> offenderPptyItemTxnsList = new ArrayList<OffenderPptyItemTxns>();
			offenderPptyItemTxnsList.add(offenderPptyItemTxns);
			OffenderPptyItemTxnsCommitBean offenderPptyItemTxnsCommitBean = new OffenderPptyItemTxnsCommitBean();
			offenderPptyItemTxnsCommitBean.setDeleteList(offenderPptyItemTxnsList);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(offenderPptyItemTxnsCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidtprit/itmTxCommit", offenderPptyItemTxnsList)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void oidtpritCgfkItmTxFromStatusCodeRecordGroupTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidtprit/cgfkItmTxFromStatusCodeRecordGroup")
							.contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Registered Property")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void oidtpritCgfkItmTxToStatusCodeRecordGroup() {
		try {
			ItemTransactionStatii itemTransactionStatii = new ItemTransactionStatii();
			itemTransactionStatii.setItemStatusFrom("REGISTERED");
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(itemTransactionStatii);
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidtprit/cgfkItmTxToStatusCodeRecordGroup", itemTransactionStatii)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Stored in Agency Location")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void oidtpritCgfkItmTxPropertyContainerRecordGroupTestMethod() {
		try {
			OffenderPptyContainers offenderPptyContainers = new OffenderPptyContainers();
			offenderPptyContainers.setAgyLocId("ITAG");
			offenderPptyContainers.setOffenderBookId(18773);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(offenderPptyContainers);
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidtprit/cgfkItmTxPropertyContainerRecordGroup", offenderPptyContainers)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("ITAG-TR")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void oidtpritOffPiExecuteQueryTestMethod() {
		try {
			OffenderPptyItems offenderPptyItems = new OffenderPptyItems();
			offenderPptyItems.setPropertyItemSeq(1);
			offenderPptyItems.setOffenderBookId(18773);
			String jsonInString = new ObjectMapper().writeValueAsString(offenderPptyItems);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidtprit/offPiExecuteQuery", offenderPptyItems)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].propertyDescription", is("1233")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void oidtpritOffPiCommitTestMethod() {
		try {
			OffenderPptyItems offenderPptyItems = new OffenderPptyItems();
			
			offenderPptyItems.setPropertyType("sri");
			offenderPptyItems.setPropertyDescription("1233");
			offenderPptyItems.setAgyLocId("CCC");
			offenderPptyItems.setStatusCode("ghfhgfraja");
			offenderPptyItems.setConditionCode("fhgf");
			offenderPptyItems.setReceivedFrom("OFF");
			offenderPptyItems.setPropertyContainerId(12454);
			offenderPptyItems.setCommentText("NJKh");
			offenderPptyItems.setConfirmFlag("Y");
			offenderPptyItems.setDisposedToCorpId(2010);
			offenderPptyItems.setDisposedToOffenderFlag("Y");
			offenderPptyItems.setDisposedToPerson("ARKIN");
			offenderPptyItems.setDisposedToPersonId(1111);
			offenderPptyItems.setColor("Red");
			offenderPptyItems.setMake("SSS");
			offenderPptyItems.setSerialNo("1234");
			offenderPptyItems.setQuantity(1);
			offenderPptyItems.setCreateDatetime(new Timestamp(0, 0, 0, 0, 0, 0, 0));
			offenderPptyItems.setCreateUserId("SRI");
			offenderPptyItems.setModifyDatetime(new Timestamp(0, 0, 0, 0, 0, 0, 0));
			offenderPptyItems.setModifyUserId("ggg");
			offenderPptyItems.setSealFlag("N");
			
			offenderPptyItems.setOffenderBookId(18773);
			offenderPptyItems.setPropertyItemSeq(1);
			
			List<OffenderPptyItems> offenderPptyItemsItems = new ArrayList<OffenderPptyItems>();
			offenderPptyItemsItems.add(offenderPptyItems);
			
			OffenderPptyItemsCommitBean offenderPptyItemsCommitBean = new OffenderPptyItemsCommitBean();
			offenderPptyItemsCommitBean.setUpdateList(offenderPptyItemsItems);
			
			String jsonInString = new ObjectMapper().writeValueAsString(offenderPptyItemsCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidtprit/offPiCommit", offenderPptyItemsCommitBean)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void oidtpritSysPflExecuteQueryTestMethod() {
		try {
			SystemProfiles systemProfiles = new SystemProfiles();
			systemProfiles.setProfileType("LABEL");
			systemProfiles.setProfileCode("PS_CORS_CODE");
			String jsonInString = new ObjectMapper().writeValueAsString(systemProfiles);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidtprit/sysPflExecuteQuery", systemProfiles)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Course Acticity Code Label")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 *method for query callings
	 */
	@Test
	public void oidtpritVpheadoncheckdeletemasteritmtxcur() {
		try {
			OffenderPptyItemTxns offenderPptyItemTxns = new OffenderPptyItemTxns();
			offenderPptyItemTxns.setOffenderBookId(18773);
			String jsonInString = new ObjectMapper().writeValueAsString(offenderPptyItemTxns);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidtprit/vpheadoncheckdeletemasteritmtxcur", offenderPptyItemTxns)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0]", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	/**
	 *method for query callings
	 */
	@Test
	public void oidtpritItmtxwhenvalidaterecordregitems() {
		try {
			OffenderPptyContainers offenderPptyContainers = new OffenderPptyContainers();
			offenderPptyContainers.setPropertyContainerId(3189);
			offenderPptyContainers.setAgyLocId("SDTC");
			String jsonInString = new ObjectMapper().writeValueAsString(offenderPptyContainers);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidtprit/itmtxwhenvalidaterecordregitems", offenderPptyContainers)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is("null")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	/**
	 *method for query callings
	 */
	/*@Test
	public void oidtpritItmtxwhenvalidaterecordregitems1() {
		try {
			OffenderPptyContainers offenderPptyContainers = new OffenderPptyContainers();
			offenderPptyContainers.setPropertyContainerId(3189);
			offenderPptyContainers.setAgyLocId("SDTC");
			String jsonInString = new ObjectMapper().writeValueAsString(offenderPptyContainers);
			ResultActions resultActions = this.mockMvc
					.perform(get("/oidtpritItmtxwhenvalidaterecordregitems1", offenderPptyContainers)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}*/
	/**
	 *method for query callings
	 */
	@Test
	public void oidtpritOffpipostquerycpropertytypedesc() {
		try {
			ReferenceCodes referenceCodes = new ReferenceCodes();
			referenceCodes.setCode("BABY");
			String jsonInString = new ObjectMapper().writeValueAsString(referenceCodes);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidtprit/offpipostquerycpropertytypedesc", referenceCodes)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is("Baby")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	/**
	 *method for query calling
	 */
	@Test
	public void oidtpritRunreportonthewebgetprintformatcur() {
		try {
			OmsModules omsModules = new OmsModules();
			omsModules.setModuleName("OCUHGOVE");
			String jsonInString = new ObjectMapper().writeValueAsString(omsModules);
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidtprit/runreportonthewebgetprintformatcur", omsModules)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0]", is("PORTRAIT")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	/**
	 *method for query callings
	 */
	/*@Test
	public void oidtpritCgfkchkitmtxitmtxitmtsc() { No Impl
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/oidtpritCgfkchkitmtxitmtxitmtsc", tagSearchGetOffenderRecords)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}*/
	/**
	 *method for query callings
	 */
	@Test
	public void oidtpritCgfkchkitmtxitmtxrefcodc() {
		try {
			ReferenceCodes referenceCodes = new ReferenceCodes();
			referenceCodes.setCode("CELL");
			String jsonInString = new ObjectMapper().writeValueAsString(referenceCodes);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidtprit/cgfkchkitmtxitmtxrefcodc", referenceCodes)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0]", is(5)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	/**
	 *method for query callings
	 */
	@Test
	public void oidtpritPrintreportoldgetprintformatcur() {
		try {
			OmsModules omsModules = new OmsModules();
			omsModules.setModuleName("OIRPTRAN");
			String jsonInString = new ObjectMapper().writeValueAsString(omsModules);
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidtprit/printreportoldgetprintformatcur", omsModules)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0]", is("POR")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	/**
	 *method for query callings
	 */
	@Test
	public void oidtpritPrintreportoldgetprinternamecur() {
		try {
			CaseloadFormatPrinter caseloadFormatPrinter = new CaseloadFormatPrinter();
			caseloadFormatPrinter.setCaseloadId("ITAG");
			caseloadFormatPrinter.setPrintFormatCode("C_CHK_M");
			String jsonInString = new ObjectMapper().writeValueAsString(caseloadFormatPrinter);
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidtprit/printreportoldgetprinternamecur", caseloadFormatPrinter)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0]", is("Check Printer on MICR")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	/**
	 *method for query callings
	 */
	@Test
	public void oidtpritRunreportonthewebrolecurTestMethod() {
		try {
			SystemProfiles systemProfiles = new SystemProfiles();
			String jsonInString = new ObjectMapper().writeValueAsString(systemProfiles);
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidtprit/runreportonthewebrolecur", systemProfiles)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0]", is("Y")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	/**
	 *method for query callings
	 */
	@Test
	public void oidtpritPrintreportgetprintformatcur() {
		try {
			OmsModules omsModules = new OmsModules();
			String jsonInString = new ObjectMapper().writeValueAsString(omsModules);
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidtprit/printreportgetprintformatcur", omsModules)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0]", is("POR")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	/**
	 *method for query callings
	 */
	@Test
	public void oidtpritPrintreportgetprinternamecur() {
		try {
			CaseloadFormatPrinter caseloadFormatPrinter = new CaseloadFormatPrinter();
			caseloadFormatPrinter.setCaseloadId("ITAG");
			caseloadFormatPrinter.setPrintFormatCode("C_CHK_M");
			String jsonInString = new ObjectMapper().writeValueAsString(caseloadFormatPrinter);
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidtprit/printreportgetprinternamecur", caseloadFormatPrinter)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0]", is("Check Printer on MICR")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	/**
	 *method for query callings
	 */
	@Test
	public void oidtpritPrintreportrolecur() {
		try {
			OmsModules omsModules = new OmsModules();
			String jsonInString = new ObjectMapper().writeValueAsString(omsModules);
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidtprit/printreportrolecur", omsModules)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0]", is("Y")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	/**
	 *method for query callings
	 */
	@Test
	public void oidtpritPrintreportrolepwd() { 
		try {
			SystemProfiles systemProfiles = new SystemProfiles();
			String jsonInString = new ObjectMapper().writeValueAsString(systemProfiles);
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidtprit/printreportrolepwd", systemProfiles)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0]", is("2571623906878CF6")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	/**
	 *method for query callings
	 */
	@Test
	public void oidtpritPrintreportlreportalias() { 
		try {
			SystemProfiles systemProfiles = new SystemProfiles();
			String jsonInString = new ObjectMapper().writeValueAsString(systemProfiles);
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidtprit/printreportlreportalias", systemProfiles)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0]", is("/tag/reports/")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	/**
	 *method for query callings
	 */
	@Test
	public void oidtpritPrintreportlsystemprofilesdesccur() { 
		try {
			SystemProfiles systemProfiles = new SystemProfiles();
			systemProfiles.setProfileCode("PS_CORS_CODE");
			systemProfiles.setProfileType("LABEL");
			String jsonInString = new ObjectMapper().writeValueAsString(systemProfiles);
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidtprit/printreportlsystemprofilesdesccur", systemProfiles)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0]", is("Course Acticity Code Label")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	
	}