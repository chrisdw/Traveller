package uk.org.downesward.traveller.characters;

import java.util.List;
import java.util.Map;

/**
 * Represents a specific career within a ruleset, such as Scout or Lurker.
 * 
 * @author chrisdw
 *
 */
public abstract class Career {
	private String name;
	private boolean supportsCommission;
	private List<Long> moneyBenefits;
	private List<String> materialBenefits;
	private int termsServed;
	private int rank;
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setSupportsCommission(boolean supportsCommission) {
		this.supportsCommission = supportsCommission;
	}
	public boolean isSupportsCommission() {
		return supportsCommission;
	}
	
	public abstract Map<String, List<Skill>> getTables(Character character);
	
	public abstract Boolean enlist(Character character);
	
	public void setMoneyBenefits(List<Long> moneyBenefits) {
		this.moneyBenefits = moneyBenefits;
	}
	public List<Long> getMoneyBenefits() {
		return moneyBenefits;
	}
	public void setMaterialBenefits(List<String> materialBenefits) {
		this.materialBenefits = materialBenefits;
	}
	public List<String> getMaterialBenefits() {
		return materialBenefits;
	}
	public void setTermsServed(int termsServed) {
		this.termsServed = termsServed;
	}
	public int getTermsServed() {
		return termsServed;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getRank() {
		return rank;
	}
}
