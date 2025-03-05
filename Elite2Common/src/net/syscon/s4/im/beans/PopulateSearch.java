package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class PopulateSearch extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("tspo")
	private TagSearchPopulateOffDetails tspo;

	@JsonProperty("imgs")
	private Images imgs;

	@JsonProperty("ofd")
	private List<OffenderProfileDetails> ofd;

	@JsonProperty("opa")
	private OffenderPhysicalAttributes opa;

	@JsonProperty("offs")
	private List<Offenders> offs;
	
	public PopulateSearch()
	{
		// PopulateSearch
	}

	public List<Offenders> getOffs() {
		return offs;
	}

	public void setOffs(final List<Offenders> offs) {
		this.offs = offs;
	}

	public TagSearchPopulateOffDetails getTspo() {
		return tspo;
	}

	public void setTspo(final TagSearchPopulateOffDetails tspo) {
		this.tspo = tspo;
	}

	public Images getImgs() {
		return imgs;
	}

	public void setImgs(final Images imgs) {
		this.imgs = imgs;
	}

	public List<OffenderProfileDetails> getOfd() {
		return ofd;
	}

	public void setOfd(final List<OffenderProfileDetails> ofd) {
		this.ofd = ofd;
	}

	public OffenderPhysicalAttributes getOpa() {
		return opa;
	}

	public void setOpa(final OffenderPhysicalAttributes opa) {
		this.opa = opa;
	}

}
