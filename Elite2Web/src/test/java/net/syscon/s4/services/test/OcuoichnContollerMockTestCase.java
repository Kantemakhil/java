/**
 * 
 */
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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.incidentsoic.beans.OicHearingNotices;
import net.syscon.s4.im.incidentsoic.beans.OicHearingNoticesCommitBean;
import net.syscon.s4.im.incidentsoic.beans.OicHearings;
import net.syscon.s4.im.incidentsoic.beans.OicHearingsCommitBean;
import net.syscon.s4.services.config.EliteSpringConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EliteSpringConfig.class })
@WebAppConfiguration
@WithMockCustomUser
public class OcuoichnContollerMockTestCase extends AbstractMockTestCase {
	private static Logger logger = LogManager.getLogger(OcuoichnContollerMockTestCase.class);

	@Test
	public void oichearExecuteQueryTestMethod() {
		try {
			final OicHearings oicHearings = new OicHearings();
			oicHearings.setOicHearingId(1963);
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(oicHearings);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocuoichn/oicHearExecuteQuery", oicHearings)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].oicHearingType", is("DISC")));

		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void oichearCommitTestMethod() {
		try {
			final OicHearings oicHearings = new OicHearings();
			oicHearings.setOicHearingId(8798);
			oicHearings.setOicHearingType("aa");
			oicHearings.setOicIncidentId(2822);
			oicHearings.setScheduleDate(new Date());
			oicHearings.setScheduleTime(new Date());
			oicHearings.setHearingDate(new Date());
			oicHearings.setHearingTime(new Date());
			oicHearings.setHearingStaffId(0404);
			oicHearings.setVisitJusticeText("AA");
			oicHearings.setCommentText("AA");
			oicHearings.setTapeNumber("AA");
			oicHearings.setCreateDateTime(new Date());
			oicHearings.setCreateUserId("AA");
			oicHearings.setModifyDateTime(new Date());
			oicHearings.setModifyUserId("AA");
			oicHearings.setInternalLocationId(5511);
			oicHearings.setRepresentativeText("AA");
			oicHearings.setEventId(404);
			oicHearings.setEventStatus("AC");
			oicHearings.setSealFlag("Y");
			final List<OicHearings> list = new ArrayList<OicHearings>();
			list.add(oicHearings);
			final OicHearingsCommitBean oicHearingsCommitBean = new OicHearingsCommitBean();
			oicHearingsCommitBean.setInsertList(list);
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(oicHearingsCommitBean);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocuoichn/oicHearCommit", oicHearingsCommitBean)
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
	public void oichearUpdateTestMethod() {
		try {
			final OicHearings oicHearings = new OicHearings();
			oicHearings.setOicHearingType("ccc");
			oicHearings.setOicIncidentId(2822);
			oicHearings.setScheduleDate(new Date());
			oicHearings.setScheduleTime(new Date());
			oicHearings.setHearingDate(new Date());
			oicHearings.setHearingTime(new Date());
			oicHearings.setHearingStaffId(0404);
			oicHearings.setVisitJusticeText("AA");
			oicHearings.setCommentText("AA");
			oicHearings.setTapeNumber("AA");
			oicHearings.setCreateDateTime(new Date());
			oicHearings.setCreateUserId("AA");
			oicHearings.setModifyDateTime(new Date());
			oicHearings.setModifyUserId("AA");
			oicHearings.setInternalLocationId(5511);
			oicHearings.setRepresentativeText("AA");
			oicHearings.setEventId(404);
			oicHearings.setEventStatus("AC");
			oicHearings.setSealFlag("Y");
			oicHearings.setOicHearingId(2881);
			final List<OicHearings> updatelist = new ArrayList<OicHearings>();
			updatelist.add(oicHearings);
			final OicHearingsCommitBean oicHearingsCommitBean = new OicHearingsCommitBean();
			oicHearingsCommitBean.setUpdateList(updatelist);
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(oicHearingsCommitBean);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocuoichn/oicHearCommit", oicHearingsCommitBean)
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
	public void oichearDeleteTestMethod() {
		try {
			final OicHearings oicHearings = new OicHearings();
			oicHearings.setOicHearingId(8796);
			final List<OicHearings> deletelist = new ArrayList<OicHearings>();
			deletelist.add(oicHearings);
			final OicHearingsCommitBean oicHearingsCommitBean = new OicHearingsCommitBean();
			oicHearingsCommitBean.setDeleteList(deletelist);
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(oicHearingsCommitBean);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocuoichn/oicHearCommit", oicHearingsCommitBean)
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
	public void getrgAgyIncpStaffIdRecordGroupTestMethod() {
		try {

			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocuoichn/rgAgyIncpStaffIdRecordGroup?caseloadId=ITAG")
							.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].staffId", is(3849)));
		} catch (Exception e) {
			logger.error("", e);
		}

	}

	@Test
	public void getrgHearingTypeRecordGroupTestMethod() {
		try {

			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocuoichn/rgHearingTypeRecordGroup").contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Disciplinary Hearing")));

		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void getOcuoichnRgInternalLocationsRecordGroupTestMethod() {
		try {

			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocuoichn/rgInternalLocationsRecordGroup?caseloadId=ITAG")
							.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("ITAG-VISIT")));

		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void getOcuoichnRgAgyIncpStaffIdReferenceCodesTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocuoichn/rgAgyIncpStaffIdRecordGroup?caseloadId=ITAG")
							.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].staffId", is(3849)));

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Test
	public void oichearnotiExecuteQueryTestMethod() {
		try {
			OicHearingNotices oicHearingNotices = new OicHearingNotices();
			oicHearingNotices.setOicHearingId(1963);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(oicHearingNotices);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocuoichn/oicHearNotiExecuteQuery", oicHearingNotices)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].oicNoticeSeq", is(641)));

		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void oichearnotiCommitTestMethod() {
		try {
			OicHearingNotices oicHearingNotices = new OicHearingNotices();
			oicHearingNotices.setOicHearingId(2881);
			oicHearingNotices.setOicNoticeSeq(1110);
			oicHearingNotices.setDeliveryDate(new Date(6 / 14 / 2017));
			oicHearingNotices.setDeliveryTime(new Date(6 / 14 / 2017));
			oicHearingNotices.setDeliveryStaffId(3004);
			oicHearingNotices.setCreateDateTime(new Date());
			oicHearingNotices.setCreateUserId("A");
			oicHearingNotices.setModifyDateTime(new Date());
			oicHearingNotices.setModifyUserId("B");
			oicHearingNotices.setCommentText("AA");
			oicHearingNotices.setSealFlag("Y");

			List<OicHearingNotices> insertOicHearingNoticesList = new ArrayList<OicHearingNotices>();
			insertOicHearingNoticesList.add(oicHearingNotices);
			OicHearingNoticesCommitBean oicHearingNoticesCommitBean = new OicHearingNoticesCommitBean();
			oicHearingNoticesCommitBean.setInsertList(insertOicHearingNoticesList);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(oicHearingNoticesCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocuoichn/oicHearNotiCommit", oicHearingNoticesCommitBean)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));

		} catch (Exception e) {
			logger.error("", e);
			System.out.println(e);

		}
	}

	@Test
	public void oichearnotiUpdateTestMethod() {
		try {
			OicHearingNotices oicHearingNotices = new OicHearingNotices();
			oicHearingNotices.setOicNoticeSeq(111);
			oicHearingNotices.setDeliveryDate(new Date(6 / 14 / 2017));
			oicHearingNotices.setDeliveryTime(new Date(6 / 14 / 2017));
			oicHearingNotices.setDeliveryStaffId(3004);
			oicHearingNotices.setCreateDateTime(new Date());
			oicHearingNotices.setCreateUserId("AB");
			oicHearingNotices.setModifyDateTime(new Date());
			oicHearingNotices.setModifyUserId("BA");
			oicHearingNotices.setCommentText("AA");
			oicHearingNotices.setSealFlag("N");
			oicHearingNotices.setOicHearingId(2881);
			List<OicHearingNotices> updateOicHearingNoticesList = new ArrayList<OicHearingNotices>();
			updateOicHearingNoticesList.add(oicHearingNotices);
			OicHearingNoticesCommitBean oicHearingNoticesCommitBean = new OicHearingNoticesCommitBean();
			oicHearingNoticesCommitBean.setUpdateList(updateOicHearingNoticesList);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(oicHearingNoticesCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocuoichn/oicHearNotiCommit", oicHearingNoticesCommitBean)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));

		} catch (Exception e) {
			logger.error("", e);
			System.out.println(e);

		}
	}

	@Test
	public void oichearnotiDeleteTestMethod() {
		try {
			OicHearingNotices oicHearingNotices = new OicHearingNotices();
			oicHearingNotices.setOicHearingId(2881);
			List<OicHearingNotices> deleteOicHearingNoticesList = new ArrayList<OicHearingNotices>();
			deleteOicHearingNoticesList.add(oicHearingNotices);
			OicHearingNoticesCommitBean oicHearingNoticesCommitBean = new OicHearingNoticesCommitBean();
			oicHearingNoticesCommitBean.setDeleteList(deleteOicHearingNoticesList);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(oicHearingNoticesCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocuoichn/oicHearNotiCommit", oicHearingNoticesCommitBean)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));

		} catch (Exception e) {
			logger.error("", e);
			System.out.println(e);

		}
	}

	@Test
	public void oicHearOnCheckDeleteMasteroicHearNotiCurTestMethod() {
		try {
			OicHearingNotices oicHearingNotices = new OicHearingNotices();
			oicHearingNotices.setOicHearingId(2264);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(oicHearingNotices);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocuoichn/oichearoncheckdeletemasteroichearnoticur", oicHearingNotices)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0]", is(1)));

		} catch (Exception e) {
			logger.error("", e);
		}

	}

	@Test
	public void oicHearPreInsertgetEventIdCurTestMethod() {
		try {
			final Dual dual = new Dual();

			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(dual);
			this.mockMvc
					.perform(get("/api/ocuoichn/oichearpreinsertgeteventidcur", dual)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());

		} catch (Exception e) {
			logger.error("", e);
		}

	}

}
