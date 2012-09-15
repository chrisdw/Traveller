package uk.org.downesward.traveller.characters;

import java.util.List;

public abstract class Ruleset {
	private List<Campaign> campaigns;

	public void setCampaigns(List<Campaign> campaigns) {
		this.campaigns = campaigns;
	}

	public List<Campaign> getCampaigns() {
		return campaigns;
	}
	
}
