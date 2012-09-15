package uk.org.downesward.traveller.characters;

/**
 * Represents an attribute of a character such as Strength or Social Standing
 * @author chrisdw
 *
 */
public class Attribute {
	private String name;
	private String shortName;
	private int maxValue = 15;
	private int value;
	
	public Attribute()
	{	
	}
	
	public Attribute(String name, String shortName, int maxValue, int value)
	{
		this.name = name;
		this.shortName = shortName;
		this.maxValue = maxValue;
		this.value = value;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getShortName() {
		return shortName;
	}
	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}
	public int getMaxValue() {
		return maxValue;
	}
	public void setValue(int value) {
		if (value <= this.maxValue) {
			this.value = value;
		}
	}
	public int getValue() {
		return value;
	}
	
	public int getBonus() {
		if (value <= 2) return -3;
		if (value >= 4 && value <= 5) return -1;
		if (value >= 6 && value <= 8) return 0;
		if (value >= 15) return 3;
		return 0;
	}
}
