package uk.org.downesward.traveller.characters.mongoose.generic;

import uk.org.downesward.traveller.characters.Culture;

public class GenericCulture extends Culture {

	public GenericCulture()
	{
		this.setName("Generic Culture");
		this.getSpecies().add(new GenericHuman());
	}
}
