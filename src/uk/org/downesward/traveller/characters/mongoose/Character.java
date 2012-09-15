package uk.org.downesward.traveller.characters.mongoose;

import java.util.List;

public class Character extends uk.org.downesward.traveller.characters.Character {
	private List<String> traits;

	public void setTraits(List<String> traits) {
		this.traits = traits;
	}

	public List<String> getTraits() {
		return traits;
	}
}
