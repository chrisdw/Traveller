package uk.org.downesward.traveller.characters.mongoose;

import java.util.List;

public abstract class Career extends
		uk.org.downesward.traveller.characters.Career {

	private String sepcialty;
	
	private CareerEventTable careerEventTable;
	
	public abstract List<String> getSpecialties(Character character);

	public void setCareerEventTable(CareerEventTable careerEventTable) {
		this.careerEventTable = careerEventTable;
	}

	public CareerEventTable getCareerEventTable() {
		return careerEventTable;
	}

	public void setSepcialty(String sepcialty) {
		this.sepcialty = sepcialty;
	}

	public String getSepcialty() {
		return sepcialty;
	}
}
