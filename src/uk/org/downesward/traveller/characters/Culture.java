package uk.org.downesward.traveller.characters;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a specific culture within a campaign, for example, zhodani or sword
 * worlds within the 3rd imperium.
 * 
 * Generates a list of species and careers.
 * @author chrisdw
 *
 */
public abstract class Culture {
	private String name;
	private List<Species> species = new ArrayList<Species>();
	private List<Career> careers = new ArrayList<Career>();
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setSpecies(List<Species> species) {
		this.species = species;
	}
	public List<Species> getSpecies() {
		return species;
	}
	public void setCareers(List<Career> careers) {
		this.careers = careers;
	}
	public List<Career> getCareers() {
		return careers;
	}
}
