package uk.org.downesward.traveller.characters;

import java.util.List;
import java.util.Map;

/**
 * Represents a single character
 * @author chrisdw
 *
 */
public class Character {
	private List<Attribute> attributes;
	private List<Skill> skills;
	private String name;
	private Species species;
	private List<String> journal;
	private Campaign campaign;
	private Culture culture;
	private int yearOfBirth;
	private int age = 18;
	private long money;
	private Map<String, Integer> benefits;
	
	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}

	public List<Attribute> getAttributes() {
		return attributes;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setSpecies(Species species) {
		this.species = species;
	}

	public Species getSpecies() {
		return species;
	}

	public void setJournal(List<String> journal) {
		this.journal = journal;
	}

	public List<String> getJournal() {
		return journal;
	}

	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}

	public Campaign getCampaign() {
		return campaign;
	}

	public void setCulture(Culture culture) {
		this.culture = culture;
	}

	public Culture getCulture() {
		return culture;
	}

	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}

	public int getYearOfBirth() {
		return yearOfBirth;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	public void setMoney(long money) {
		this.money = money;
	}

	public long getMoney() {
		return money;
	}

	public void setBenefits(Map<String, Integer> benefits) {
		this.benefits = benefits;
	}

	public Map<String, Integer> getBenefits() {
		return benefits;
	}
	
}
