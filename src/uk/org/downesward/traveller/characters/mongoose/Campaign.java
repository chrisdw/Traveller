package uk.org.downesward.traveller.characters.mongoose;

public class Campaign extends uk.org.downesward.traveller.characters.Campaign {
	private CampaignEventTable campaignEventTable;

	public void setCampaignEventTable(CampaignEventTable campaignEventTable) {
		this.campaignEventTable = campaignEventTable;
	}

	public CampaignEventTable getCampaignEventTable() {
		return campaignEventTable;
	}
}
