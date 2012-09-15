package uk.org.downesward.traveller.characters.mongoose;

import java.util.ArrayList;
import java.util.List;

import uk.org.downesward.traveller.characters.Ruleset;
import uk.org.downesward.traveller.characters.mongoose.generic.GenericCampaign;

public class Mongoose extends Ruleset {

	public Mongoose()
	{
		List<uk.org.downesward.traveller.characters.Campaign> campaigns = new ArrayList<uk.org.downesward.traveller.characters.Campaign>();
		
		campaigns.add(new GenericCampaign());
		
		this.setCampaigns(campaigns);
	}
}
