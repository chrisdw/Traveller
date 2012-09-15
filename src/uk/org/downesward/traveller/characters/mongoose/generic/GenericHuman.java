package uk.org.downesward.traveller.characters.mongoose.generic;

import uk.org.downesward.traveller.characters.Attribute;
import uk.org.downesward.traveller.characters.Character;
import uk.org.downesward.traveller.characters.Species;
import uk.org.downesward.utiliites.Dice;

public class GenericHuman extends Species {

	@Override
	public Character generate() {
		Character character = new Character();
		Dice dice = new Dice(6);

		character.getAttributes().add(new Attribute("Strength", "STR", 15, dice.roll(2)));
		character.getAttributes().add(new Attribute("Dexterity", "DEX", 15, dice.roll(2)));
		character.getAttributes().add(new Attribute("Endurance", "END", 15, dice.roll(2)));
		character.getAttributes().add(new Attribute("Intelligence", "INT", 15, dice.roll(2)));
		character.getAttributes().add(new Attribute("Education", "EDU", 15, dice.roll(2)));
		character.getAttributes().add(new Attribute("Social Standing", "SOC", 15, dice.roll(2)));
		
		return character;
	}

}
