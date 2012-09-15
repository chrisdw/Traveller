package uk.org.downesward.traveller.characters.mongoose.generic.careers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.org.downesward.traveller.characters.Attribute;
import uk.org.downesward.traveller.characters.Skill;
import uk.org.downesward.traveller.characters.mongoose.Career;
import uk.org.downesward.traveller.characters.mongoose.Character;
import uk.org.downesward.utiliites.Dice;

public class GenericScout extends Career {

	public GenericScout()
	{
		this.setSupportsCommission(false);
		this.setName("Scouts");
	}
	
	@Override
	public List<String> getSpecialties(Character character) {
		List<String> specialties = new ArrayList<String>();
		specialties.add("Courier");
		specialties.add("Survey");
		specialties.add("Exploration");
		return specialties;
	}

	@Override
	public Map<String, List<Skill>> getTables(
			uk.org.downesward.traveller.characters.Character character) {
		// TODO Auto-generated method stub
		Map<String, List<Skill>> tables = new HashMap<String, List<Skill>>();
		// Personal Development
		List<Skill> pd = new ArrayList<Skill>();
		pd.add(new Skill("STR", 1, true));
		pd.add(new Skill("DEX", 1, true));
		pd.add(new Skill("END", 1, true));
		pd.add(new Skill("INT", 1, true));
		pd.add(new Skill("EDU", 1, true));
		pd.add(new Skill("Jack of all Trades", 1));
		tables.put("Personal Development", pd);
		return tables;
	}

	@Override
	public Boolean enlist(
			uk.org.downesward.traveller.characters.Character character) {
		Dice die = new Dice(6);
		int roll = die.roll(2);
		for (int i = 0; i < character.getAttributes().size(); i++)
		{
			Attribute attrib = character.getAttributes().get(i);
			if (attrib.getShortName().equals("INT"))
			{
				roll += attrib.getBonus();
				break;
			}
		}
		if (roll >= 5)
		{
			return true;
		}
		return false;
	}

}
