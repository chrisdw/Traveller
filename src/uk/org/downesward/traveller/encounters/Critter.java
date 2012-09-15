package uk.org.downesward.traveller.encounters;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Critter {
	public String die;
	public String attrib;
	public String description;
	public String armour;
	public String weight;
	public String wounds;
	public String weapons;
	public String behaviour;
	public String family;

	public Critter() {
		// Empty critter
	}
	
	public Critter(Element eCritter) {
		Integer dNum = Integer.parseInt(eCritter.getAttribute("dnum"));
		Integer critterType = Integer.parseInt(eCritter.getAttribute("type"));
		;
		this.die = dNum.toString();
		this.attrib = eCritter.getAttribute("attribute");
		this.description = eCritter.getElementsByTagName("description").item(0)
				.getTextContent();
		if (critterType < 55) {
			this.weight = eCritter.getElementsByTagName("weight").item(0)
					.getTextContent();
			this.armour = eCritter.getElementsByTagName("armour").item(0)
					.getTextContent();
			this.weapons = eCritter.getElementsByTagName("weapons").item(0)
					.getTextContent();

			NodeList wounds = eCritter.getElementsByTagName("wounds");
			if (wounds.getLength() > 0) {
				this.wounds = wounds.item(0).getTextContent();
			}
			String strAttack = "A"
					+ eCritter.getElementsByTagName("attack").item(0)
							.getTextContent();
			String strFlee = "F"
					+ eCritter.getElementsByTagName("flee").item(0)
							.getTextContent();
			String strSpeed = "S"
					+ eCritter.getElementsByTagName("speed").item(0)
							.getTextContent();

            if (critterType < 14)
            {
                // Herbivore
                this.behaviour = strFlee + strAttack + strSpeed;
            }
            else
            {
            	this.behaviour = strAttack + strFlee + strSpeed;
            }			
    		this.family = eCritter.getAttribute("family");
		}
	}
}
