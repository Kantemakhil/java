package net.syscon.s4.services.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import net.syscon.s4.common.beans.OffenderAlertsCommitBean;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.ModulePrivileges;
import net.syscon.s4.im.beans.OffenderAlerts;
import net.syscon.s4.services.config.EliteSpringConfig;

/**
 * Class OcdalertControllerMockTestCase
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EliteSpringConfig.class })
@WebAppConfiguration
@WithMockCustomUser
public class OcdalertControllerMockTestCase extends AbstractMockTestCase {

	@Test
	public void alertExecuteQueryTestMethod() {
		try {
			final OffenderAlerts offenderAlerts = new OffenderAlerts();
			offenderAlerts.setOffenderBookId(Long.valueOf(19644));
			final String jsonInString = new ObjectMapper().writeValueAsString(offenderAlerts);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdalert/alertExecuteQuery", offenderAlerts)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].alertType", is("X")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void alertSearchOffenderAlertsTestMethod() {
		try {
			this.mockMvc
					.perform(get("/api/ocdalert/alertSearchOffenderAlerts?offenderBookId=18733")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print()).andExpect(jsonPath("$[0].createUserId").value("OMS_OWNER"));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void sysPflSearchSystemProfilesTestMethod() {
		try {
			final SystemProfiles systemProfiles = new SystemProfiles();
			systemProfiles.setProfileType("FORMAT");
			this.mockMvc
					.perform(post("/api/ocdalert/sysPflSearchSystemProfiles", systemProfiles)
							.contentType(MediaType.APPLICATION_JSON)
							.content(new ObjectMapper().writeValueAsString(systemProfiles))
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print()).andExpect(jsonPath("$[0].description").value("Birth Certificate Number"));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void alertPreInsertcTestMethod() {
		try {
			this.mockMvc
					.perform(get("/api/ocdalert/alertPreInsertc?offenderBookId=19485")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print()).andExpect(jsonPath("$").value("15"));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void alertPostQueryvarificationCurrTestMethod() {
		try {
			final ModulePrivileges modulePrivileges = new ModulePrivileges();
			modulePrivileges.setModuleName("OIMLEGST");
			this.mockMvc
					.perform(post("/api/ocdalert/alertPostQueryvarificationCurr", modulePrivileges)
							.contentType(MediaType.APPLICATION_JSON)
							.content(new ObjectMapper().writeValueAsString(modulePrivileges))
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print()).andExpect(jsonPath("$").value(0));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void cgfkchkAlertAlertRefAlertcTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocdalert/cgfkchkAlertAlertRefAlertc?alertCode=BEE_ALG&mode=N")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description").value("De-Validated"));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void cgfklkpAlertAlertRefAlertcTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(
							get("/api/ocdalert/cgfklkpAlertAlertRefAlertc?dspDescription=BTM_BUNK&dspDescription1=Bottom Bunk&dspDescription2=Bottom Bunk&alertType=M&added=Y")
									.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description").value("De-Validated"));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void getAlertReferencesTableTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocdalert/getAlertReferencesTable?code=MEP&domain=ALERT_CODE")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Epileptic")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void findDescriptionAndCodeAndDomainTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdalert/findDescriptionAndCodeAndDomain?code=1348&domain=CITY")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Permanente")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void getCodeDetailsTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(
							get("/api/ocdalert/getCodeDetails?code=FIRST&description=First Day of Month&parentCode=MONTH&domain=VIS_START")
									.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].listSeq", is(8)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void cguvchkOffAlertUkcTestMethod() {
		try {
			final OffenderAlerts offenderAlerts = new OffenderAlerts();
			offenderAlerts.setAlertSeq(Long.valueOf(1));
			offenderAlerts.setAlertDate(new Date());
			offenderAlerts.setOffenderBookId(Long.valueOf(21207));
			offenderAlerts.setAlertCode("XR");
			offenderAlerts.setAlertType("X");
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocdalert/cguvchkOffAlertUkc", offenderAlerts)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(0)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void getBookingBeginDateTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdalert/getBookingBeginDate?const0=18773")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$.bookingBeginDate", is(1370197800000L)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void findDescriptionbyCodeTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocdalert/findDescriptionbyCode").accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].listSeq", is(10)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void alertCommitTestMethod() {
		try {
			final OffenderAlertsCommitBean commitBean = new OffenderAlertsCommitBean();
			final OffenderAlerts deleteObj = new OffenderAlerts();
			deleteObj.setOffenderBookId(Long.valueOf(18915));
			deleteObj.setAlertSeq(Long.valueOf(1));
			final List<OffenderAlerts> deleteList = new ArrayList<>();
			deleteList.add(deleteObj);
			final OffenderAlerts updateObj = new OffenderAlerts();
			updateObj.setOffenderBookId(Long.valueOf(19485));
			updateObj.setAlertSeq(Long.valueOf(2));
			updateObj.setAlertCodeDes("MHT");
			final List<OffenderAlerts> updateList = new ArrayList<>();
			updateList.add(updateObj);
			final OffenderAlerts offenderAlerts = new OffenderAlerts();
			offenderAlerts.setAlertSeq(Long.valueOf(2));
			offenderAlerts.setAlertType("M");
			offenderAlerts.setAlertCode("MHT");
			offenderAlerts.setCreateUserId("OMS_OWNER");
			offenderAlerts.setVerifiedFlag("Y");
			offenderAlerts.setAlertDate(new Date());
			offenderAlerts.setAlertStatus("ACTIVE");
			offenderAlerts.setOffenderBookId(Long.valueOf(18814));
			final OffenderAlerts alertObj = new OffenderAlerts();
			alertObj.setAlertSeq(Long.valueOf(3));
			alertObj.setAlertType("M");
			alertObj.setAlertCode("MHT");
			alertObj.setCreateUserId("OMS_OWNER");
			alertObj.setVerifiedFlag("Y");
			alertObj.setAlertDate(new Date());
			alertObj.setAlertStatus("ACTIVE");
			alertObj.setOffenderBookId(Long.valueOf(18814));
			final List<OffenderAlerts> insertList = new ArrayList<>();
			insertList.add(offenderAlerts);
			insertList.add(alertObj);
			commitBean.setInsertList(insertList);
			commitBean.setDeleteList(deleteList);
			commitBean.setUpdateList(updateList);
			final String jsonInString = new ObjectMapper().writeValueAsString(commitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdalert/alertCommit", commitBean).contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(0)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void cgwhenNewFormInstancecTestMethod() {
		try {
			Object[] params = new Object[] { 1 };
			params[0] = "newInstance";
			final String jsonInString = new ObjectMapper().writeValueAsString(params);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdalert/cgwhenNewFormInstancec").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(19644)));
		} catch (Exception e) {
			logger.error(e);
		}
	}

	@Test
	public void getOffenderIdTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdalert/offbkgsExecuteQuery?offenderId=1020050")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(19644)));
		} catch (Exception e) {
			logger.error(e);
		}
	}

	@Test
	public void findDescriptionbyDomainTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocdalert/findDescriptionbyDomain?domain=MBR_VAL_RSN")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Assessment Score")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void findDescriptionbyDescriptionCodeAndParentCodeTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(
							get("/api/ocdalert/cgfklkpAlertAlertRefAlc?code=DEVALIDATE&description=De-Validated&domain=STG_VAL_ACT&parentCode=S")
									.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].listSeq", is(2)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
}
