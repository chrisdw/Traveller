package uk.org.downesward.traveller.characters.mongoose;

public abstract class Event {
	private String name;
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public abstract void processEvent(Character character);
}
