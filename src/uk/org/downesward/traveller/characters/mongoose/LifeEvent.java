package uk.org.downesward.traveller.characters.mongoose;

public class LifeEvent extends Event {

	@Override
	public void processEvent(Character character) {
		character.getJournal().add(this.getName());
	}

}
