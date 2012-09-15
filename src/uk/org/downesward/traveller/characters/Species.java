package uk.org.downesward.traveller.characters;

public abstract class Species {
	private String name;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	/**
	 * Generate a random character of the correct species
	 * @return
	 */
	public abstract Character generate();
}
