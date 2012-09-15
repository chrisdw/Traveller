package uk.org.downesward.traveller.common;


public class UPP {
	public char starPort;
	public TravCode size = new TravCode(10); 
	public TravCode atmosphere = new TravCode();
	public TravCode hydro = new TravCode(10); 
	public TravCode pop = new TravCode(10); 
	public TravCode government = new TravCode(); 
	public TravCode law = new TravCode(); 
	public TravCode techLevel = new TravCode(); 

	public String physicalUPP() {
		return size.toString() + atmosphere.toString() + hydro.toString();
	}
}
