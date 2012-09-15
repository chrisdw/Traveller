package uk.org.downesward.traveller.characters.mongoose;
import java.util.ArrayList;

public class CampaignEventTable extends ArrayList<CampaignEvent> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8706334247567788168L;

	private int minYear;
	private int maxYear;
	public void setMinYear(int minYear) {
		this.minYear = minYear;
	}
	public int getMinYear() {
		return minYear;
	}
	public void setMaxYear(int maxYear) {
		this.maxYear = maxYear;
	}
	public int getMaxYear() {
		return maxYear;
	}
}
