package uk.org.downesward.traveller.characters;

public class Skill {
	private String name;
	private int level = 0;
	private Boolean attributeChange;
	
	public Skill()
	{
	
	}
	
	public Skill(String name, int level)
	{
		this(name, level, false);
	}
	
	public Skill(String name, int level, Boolean attributeChange)
	{
		this.name = name;
		this.level = level;
		this.attributeChange = attributeChange;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getLevel() {
		return level;
	}
	public void setAttributeChange(Boolean attributeChange) {
		this.attributeChange = attributeChange;
	}
	public Boolean getAttributeChange() {
		return attributeChange;
	}
}
