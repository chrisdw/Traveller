package uk.org.downesward.traveller.characters;

import java.util.List;

/**
 * Represents a specific campaign within a ruleset, for example 3rd imperium or 
 * Babylon 5.
 * 
 * Gives a list of cultures for that campaign.
 * 
 * @author chrisdw
 *
 */
public abstract class Campaign {
	private String name;
	private List<Culture> cultures;
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setCultures(List<Culture> cultures) {
		this.cultures = cultures;
	}

	public List<Culture> getCultures() {
		return cultures;
	}
}
