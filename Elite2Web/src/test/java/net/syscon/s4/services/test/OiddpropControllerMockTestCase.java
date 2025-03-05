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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

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
 * Class OiddpropControllerMockTestCase
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EliteSpringConfig.class })
@WebAppConfiguration
@WithMockCustomUser
public class OiddpropControllerMockTestCase extends AbstractMockTestCase {

	/**
	 * Fetching the record from database table
	 * 
	 * @Param offPptyItemTxns
	 */
	@Test
	public void offenderPptyItemTxnsExecuteQueryTestMethod() {
		try {
			final OffenderPptyItemTxns offPptyItemTxns = new OffenderPptyItemTxns();
			offPptyItemTxns.setPropertyItemTxnId(5662);
			final String jsonInString = new ObjectMapper().writeValueAsString(offPptyItemTxns);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/oiddprop/itmTxExecuteQuery", offPptyItemTxns)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].offenderBookId", is(18773)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Inserting the record from database table
	 * 
	 * @Param insertList
	 */
	@Test
	public void offenderPptyItemTxnsInsertTestMethod() {
		try {
			final OffenderPptyItemTxns offPpItemTxns = new OffenderPptyItemTxns();
			offPpItemTxns.setPropertyItemTxnId(18891);
			offPpItemTxns.setOffenderBookId(18773);
			offPpItemTxns.setPropertyItemSeq(2);
			offPpItemTxns.setEventSeq(1);
			offPpItemTxns.setFromStatusCode("Registered");
			offPpItemTxns.setToStatusCode("code");
			offPpItemTxns.setCreateDate(new Date());
			offPpItemTxns.setCreateUserId("LL");
			offPpItemTxns.setCommentText("");
			offPpItemTxns.setAgyLocId("CCC");
			offPpItemTxns.setDisposedToOffenderFlag("N");
			offPpItemTxns.setVerificationFlag("Y");
			offPpItemTxns.setVerifyFlag("M");
			offPpItemTxns.setDisposedToCorpId(2010);
			offPpItemTxns.setDisposedToPerson("GG");
			offPpItemTxns.setPropertyContainerId(3562);
			offPpItemTxns.setPropertyContainerTxnId(2356);
			offPpItemTxns.setToPropertyContainerId(2356);
			offPpItemTxns.setDisposedToPersonId(5326);
			offPpItemTxns.setColor("BLK");
			offPpItemTxns.setMake("ll");
			offPpItemTxns.setSerialNo("12");
			offPpItemTxns.setCreateDatetime(new Timestamp(0, 0, 0, 0, 0, 0, 0));
			offPpItemTxns.setModifyDatetime(new Timestamp(0, 0, 0, 0, 0, 0, 0));
			offPpItemTxns.setModifyUserId("");
			offPpItemTxns.setSealFlag("N");

			final List<OffenderPptyItemTxns> list = new ArrayList<OffenderPptyItemTxns>();
			list.add(offPpItemTxns);

			final OffenderPptyItemTxnsCommitBean insertList = new OffenderPptyItemTxnsCommitBean();
			insertList.setInsertList(list);
			final String jsonInString = new ObjectMapper().writeValueAsString(insertList);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/oiddprop/itmTxCommit", insertList).contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Updating the record from database table
	 * 
	 * @Param updateList
	 */
	@Test
	public void offenderPptyItemTxnsUpdateTestMethod() {
		try {
			final OffenderPptyItemTxns offPpItemTxns = new OffenderPptyItemTxns();
			offPpItemTxns.setOffenderBookId(18773);
			offPpItemTxns.setPropertyItemSeq(2);
			offPpItemTxns.setEventSeq(1);
			offPpItemTxns.setFromStatusCode("Registeredw");
			offPpItemTxns.setToStatusCode("code");
			offPpItemTxns.setCreateDate(new Date());
			offPpItemTxns.setCreateUserId("LLL");
			offPpItemTxns.setCommentText("");
			offPpItemTxns.setAgyLocId("CCC");
			offPpItemTxns.setDisposedToOffenderFlag("N");
			offPpItemTxns.setVerificationFlag("Y");
			offPpItemTxns.setVerifyFlag("M");
			offPpItemTxns.setDisposedToCorpId(2010);
			offPpItemTxns.setDisposedToPerson("GG");
			offPpItemTxns.setPropertyContainerId(3562);
			offPpItemTxns.setPropertyContainerTxnId(2356);
			offPpItemTxns.setToPropertyContainerId(2356);
			offPpItemTxns.setDisposedToPersonId(5326);
			offPpItemTxns.setColor("BLK");
			offPpItemTxns.setMake("ll");
			offPpItemTxns.setSerialNo("12");
			offPpItemTxns.setCreateDatetime(new Timestamp(0, 0, 0, 0, 0, 0, 0));
			offPpItemTxns.setModifyDatetime(new Timestamp(0, 0, 0, 0, 0, 0, 0));
			offPpItemTxns.setModifyUserId("");
			offPpItemTxns.setSealFlag("N");
			offPpItemTxns.setPropertyItemTxnId(18891);
			final List<OffenderPptyItemTxns> list = new ArrayList<OffenderPptyItemTxns>();
			list.add(offPpItemTxns);
			final OffenderPptyItemTxnsCommitBean updateList = new OffenderPptyItemTxnsCommitBean();
			updateList.setUpdateList(list);
			final String jsonInString = new ObjectMapper().writeValueAsString(updateList);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/oiddprop/itmTxCommit", updateList).contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void offenderPptyItemTxnsRecordGroupTestMethod() {
		try {
			final OffenderPptyItemTxns offPpItemTxns = new OffenderPptyItemTxns();
			offPpItemTxns.setAgyLocId("ITAG");
			offPpItemTxns.setOffenderBookId(21949);
			final String jsonInString = new ObjectMapper().writeValueAsString(offPpItemTxns);
			final ResultActions resultActions = this.mockMvc
					.perform(get("/api/oiddprop/cgfkItmTxPropertyContainerRecordGroup", offPpItemTxns)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].propertyContainerId", is(3849)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 */
	@Test
	public void dspCorporateNameRecordGroupTestMethod() {
		try {
			final ResultActions resultActions = this.mockMvc
					.perform(get("/api/oiddprop/rgDspCorporateNameRecordGroup").contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].corporateId", is(2070)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 */
	@Test
	public void disposedToPersonRecordGroupTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oiddprop/rgDisposedToPersonRecordGroup").contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].personId", is(5200)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 */
	@Test
	public void fromStatusCodeRecordGroupTestMethod() {
		try {
			final ResultActions resultActions = this.mockMvc
					.perform(get("/api/oiddprop/cgfkItmTxFromStatusCodeRecordGroup")
							.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Registered Property")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param offenderPptyItems
	 */
	@Test
	public void offenderPptyItemsExecuteQueryTestMethod() {
		try {
			final OffenderPptyItems offenderPptyItems = new OffenderPptyItems();
			final String jsonInString = new ObjectMapper().writeValueAsString(offenderPptyItems);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/oiddprop/offPiExecuteQuery", offenderPptyItems)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].propertyType", is("sri")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Updating the record in database table
	 * 
	 * @Param offPptyItemsCommitBean
	 */
	@Test
	public void offenderPptyItemsCommitTestMethod() {
		try {
			final OffenderPptyItems offPptyItems = new OffenderPptyItems();
			offPptyItems.setPropertyType("Srii");
			offPptyItems.setPropertyDescription("123");
			offPptyItems.setAgyLocId("CCC");
			offPptyItems.setStatusCode("AA");
			offPptyItems.setConditionCode("JJ");
			offPptyItems.setReceivedFrom("MM");
			offPptyItems.setConfirmFlag("N");
			offPptyItems.setQuantity(2);
			offPptyItems.setCreateDatetime(new Timestamp(0, 0, 0, 0, 0, 0, 0));
			offPptyItems.setCreateUserId("KK");
			offPptyItems.setOffenderBookId(18773);
			offPptyItems.setPropertyItemSeq(1);
			final List<OffenderPptyItems> list = new ArrayList<OffenderPptyItems>();
			list.add(offPptyItems);
			final OffenderPptyItemsCommitBean offPptyItemsCommitBean = new OffenderPptyItemsCommitBean();
			offPptyItemsCommitBean.setUpdateList(list);
			final String jsonInString = new ObjectMapper().writeValueAsString(offPptyItemsCommitBean);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/oiddprop/offPiCommit", offPptyItemsCommitBean)
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
	 * Fetching the record from database table
	 * 
	 * @Param systemProfiles
	 */
	@Test
	public void systemProfilesExecuteQueryTestMethod() {
		try {
			final SystemProfiles systemProfiles = new SystemProfiles();
			final String jsonInString = new ObjectMapper().writeValueAsString(systemProfiles);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/oiddprop/sysPflExecuteQuery", systemProfiles)
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
	 * Fetching the record from database table
	 * 
	 * @Param offPpContainers
	 */
	@Test
	public void cgfkchkItmTxItmTxOffCon() {
		try {
			final OffenderPptyContainers offPpContainers = new OffenderPptyContainers();
			offPpContainers.setPropertyContainerId(3209);
			offPpContainers.setAgyLocId("SDTC");
			offPpContainers.setOffenderBookId(18813);
			final String jsonInString = new ObjectMapper().writeValueAsString(offPpContainers);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/oiddprop/cgfkchkItmTxItmTxOffConc", offPpContainers)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$.containerCode", is("BRA")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void cgfkchkItmTxItmTxRefCod() {
		try {
			final ReferenceCodes referenceCodes = new ReferenceCodes();
			referenceCodes.setCode("CELL");
			final String jsonInString = new ObjectMapper().writeValueAsString(referenceCodes);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/oiddprop/cgfkchkItmTxItmTxRefCod").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$.description", is("0Property In Cell")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void cgfkchkOffPiOffPiOffConc() {
		try {
			final OffenderPptyContainers offPptyContainer = new OffenderPptyContainers();
			offPptyContainer.setPropertyContainerId(3189);
			final String jsonInString = new ObjectMapper().writeValueAsString(offPptyContainer);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/oiddprop/cgfkchkOffPiOffPiOffConc", offPptyContainer)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$.description", is("ITAG-TR")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * method for query callings
	 */
	@Test
	public void oiddpropPrintreportoldgetprintformatcur() {
		try {
			final OmsModules omsModules = new OmsModules();
			omsModules.setModuleName("OORPLFFO");
			final String jsonInString = new ObjectMapper().writeValueAsString(omsModules);
			final ResultActions resultActions = this.mockMvc
					.perform(get("/api/oiddprop/printReportOldgetPrintFormatCur", omsModules)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$.printFormatCode", is("POR")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * method for query callings
	 */
	@Test
	public void oiddpropPrintreportoldgetprinternamecur() {
		try {
			final CaseloadFormatPrinter caseForPrinter = new CaseloadFormatPrinter();
			caseForPrinter.setCaseloadId("ITAG");
			caseForPrinter.setPrintFormatCode("C_CHK_M");
			final String jsonInString = new ObjectMapper().writeValueAsString(caseForPrinter);
			final ResultActions resultActions = this.mockMvc
					.perform(
							get("/api/oiddprop/printReportOldgetPrinterNameCur",caseForPrinter).contentType(MediaType.APPLICATION_JSON)
									.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$.description", is("Check Printer on MICR")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * method for query callings
	 */
	@Test
	public void oiddpropPrintreportgetprinternamecur() {
		try {

			final CaseloadFormatPrinter caseForPrinter = new CaseloadFormatPrinter();
			caseForPrinter.setCaseloadId("ITAG");
			caseForPrinter.setPrintFormatCode("C_CHK_M");
			final String jsonInString = new ObjectMapper().writeValueAsString(caseForPrinter);
			final ResultActions resultActions = this.mockMvc
					.perform(get("/api/oiddprop/printReportgetPrinterNameCur",caseForPrinter).contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$.description", is("Check Printer on MICR")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * method for query callings
	 */
	@Test
	public void oiddpropPrintreportrolecur() {
		try {

			final SystemProfiles systemProfiles = new SystemProfiles();
			systemProfiles.setProfileCode("PS_CORS_CODE");
			systemProfiles.setProfileType("LABEL");
			final String jsonInString = new ObjectMapper().writeValueAsString(systemProfiles);
			final ResultActions resultActions = this.mockMvc
					.perform(get("/api/oiddprop/printReportroleCur", systemProfiles)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$.profileValue", is("[Code")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	/**
	 * method for query callings
	 */
	@Test
	public void oiddpropPrintreportlsystemprofilesdesccur() {

		try {
			final SystemProfiles systemProfiles = new SystemProfiles();
			systemProfiles.setProfileType("LABEL");
			systemProfiles.setProfileCode("PS_CORS_CODE");
			final String jsonInString = new ObjectMapper().writeValueAsString(systemProfiles);
			final ResultActions resultActions = this.mockMvc
					.perform(get("/api/oiddprop/printReportlSystemProfilesDescCur", systemProfiles)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is("Course Acticity Code Label")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * method for query callings
	 */
	@Test
	public void oiddpropPrintreportrolepwd() {
		try {
			final SystemProfiles systemProfiles = new SystemProfiles();
			systemProfiles.setProfileType("SYS");
			systemProfiles.setProfileCode("ROLE_PSWD");
			final String jsonInString = new ObjectMapper().writeValueAsString(systemProfiles);
			final ResultActions resultActions = this.mockMvc
					.perform(get("/api/oiddprop/printReportrolePwd").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$.profileValue", is("2571623906878CF6d")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * method for query callings
	 */
	@Test
	public void oiddpropPrintreportlreportalias() {
		try {
			final SystemProfiles systemProfiles = new SystemProfiles();
			systemProfiles.setProfileType("CLIENT");
			systemProfiles.setProfileCode("REPORT_ALIAS");
			final String jsonInString = new ObjectMapper().writeValueAsString(systemProfiles);
			final ResultActions resultActions = this.mockMvc
					.perform(get("/api/oiddprop/printReportlReportAlias", systemProfiles)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$.profileValue", is("/tag/reports/")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
}