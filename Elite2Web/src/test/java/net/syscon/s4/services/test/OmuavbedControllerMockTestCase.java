package net.syscon.s4.services.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.syscon.s4.common.beans.TempLivingUnitProfilesCommitBean;
import net.syscon.s4.im.beans.OmuavbedLivUnitsQuery;
import net.syscon.s4.im.beans.TempLivingUnitProfiles;
import net.syscon.s4.services.config.EliteSpringConfig;

/**
 * Class OcdalertControllerMockTestCase
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EliteSpringConfig.class })
@WebAppConfiguration
@WithMockCustomUser
public class OmuavbedControllerMockTestCase extends AbstractMockTestCase {

	private static Logger logger = LogManager.getLogger(OmuavbedControllerMockTestCase.class);

	@Test
	public void livuprofuforExecuteQueryTestMethod() {
		try {
			this.mockMvc
					.perform(post("/api/omuavbed/livuprofuforExecuteQuery").content("{}")
							.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$").isEmpty());
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void livuprofuforCommitTestMethod() {
		try {
			final TempLivingUnitProfilesCommitBean commitBean = new TempLivingUnitProfilesCommitBean();
			final List<TempLivingUnitProfiles> insertList = new ArrayList<>();
			final TempLivingUnitProfiles tempLivingUnitProfiles = new TempLivingUnitProfiles();
			tempLivingUnitProfiles.setProfileType("TEST1");
			tempLivingUnitProfiles.setProfileCode("TEST");
			tempLivingUnitProfiles.setCreateDatetime("01/01/2011");
			tempLivingUnitProfiles.setCreateUserId("OMS_OWNER");
			tempLivingUnitProfiles.setModifyDatetime("01/01/2011");
			tempLivingUnitProfiles.setModifyUserId("OMS_OWNER");
			insertList.add(tempLivingUnitProfiles);
			commitBean.setInsertList(insertList);
			final String jsonInString = new ObjectMapper().writeValueAsString(commitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/omuavbed/livuprofuforCommit", tempLivingUnitProfiles)
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
	public void rgLivingUnitPagyRecordGroupTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/omuavbed/rgLivingUnitPagyRecordGroup?const0=TRY&const1=5873")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].livingUnitId").value(5874));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void rgLivingUnitLocIdRecordGroupTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/omuavbed/rgLivingUnitLocIdRecordGroup").accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].livingUnitId").value(5875));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void rgLivingUnitLevelIdRecordGroupTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(
							get("/api/omuavbed/rgLivingUnitLevelIdRecordGroup").accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].livingUnitId").value(5874));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void getrgLivingUnitRecordGroupTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/omuavbed/rgLivingUnitRecordGroup").accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].livingUnitId").value(5345));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void getrgLivingUnitTypeRecordGroupTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/omuavbed/rgLivingUnitTypeRecordGroup").accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].code").value("SEG"));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void getrgUsedForRecordGroupTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/omuavbed/rgUsedForRecordGroup").accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description").value("Unconvicted Juveniles"));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void getrgAttributesRecordGroupTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/omuavbed/rgAttributesRecordGroup").accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[1].description").value("Single Occupancy"));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void livuprofattrExecuteQueryTestMethod() {
		try {
			this.mockMvc
					.perform(post("/api/omuavbed/livuprofattrExecuteQuery").content("{}")
							.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$").isEmpty());
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void livuprofattrCommitTestMethod() {
		try {
			final TempLivingUnitProfilesCommitBean commitBean = new TempLivingUnitProfilesCommitBean();
			final List<TempLivingUnitProfiles> insertList = new ArrayList<>();
			final TempLivingUnitProfiles tempLivingUnitProfiles = new TempLivingUnitProfiles();
			tempLivingUnitProfiles.setProfileType("TEST12");
			tempLivingUnitProfiles.setProfileCode("TEST1");
			tempLivingUnitProfiles.setCreateDatetime("01/01/2011");
			tempLivingUnitProfiles.setCreateUserId("OMS_OWNER");
			tempLivingUnitProfiles.setModifyDatetime("01/01/2011");
			tempLivingUnitProfiles.setModifyUserId("OMS_OWNER");
			insertList.add(tempLivingUnitProfiles);
			commitBean.setInsertList(insertList);
			final String jsonInString = new ObjectMapper().writeValueAsString(commitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/omuavbed/livuprofattrCommit", tempLivingUnitProfiles)
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
	public void livingUnitsExecuteQueryTestMethod() {
		try {
			final OmuavbedLivUnitsQuery omuavbedLivUnitsQuery = new OmuavbedLivUnitsQuery();
			omuavbedLivUnitsQuery.setpOffenderBookId(Long.valueOf(18733));
			omuavbedLivUnitsQuery.setpOffenderId(1019021);
			omuavbedLivUnitsQuery.setpCaseloadId("");
			omuavbedLivUnitsQuery.setpAgyLocId("ITAG");
			omuavbedLivUnitsQuery.setpLivingUnitType("");
			omuavbedLivUnitsQuery.setpLevel1Code("BLOCK A");
			omuavbedLivUnitsQuery.setpLevel2Code("");
			omuavbedLivUnitsQuery.setpLevel3Code("");
			omuavbedLivUnitsQuery.setpLevel4Code("");

			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(omuavbedLivUnitsQuery);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/omuavbed/livingUnitsExecuteQuery", omuavbedLivUnitsQuery)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].livingUnitId", is(5353)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void omuavbedWhenNewFormInstancelevelCurTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/omuavbed/whenNewFormInstancelevelCur?pAgyLocId=TRY")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].housingLev1Code").value("WING"));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
}
