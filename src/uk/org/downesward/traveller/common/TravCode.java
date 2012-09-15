package uk.org.downesward.traveller.common;

public class TravCode {
	private Integer mintValue;
	private String mstrList = "0123456789ABCDEFGHJKLMNPQRSTUVWXYZ";
	private Integer mintMax;
	
	public TravCode() {
		mintValue = 0;
		mintMax = mstrList.length() - 1;
	}
	
	public TravCode(Integer max) {
		mintValue = 0;
		if (max >= mstrList.length() - 1) {
			mintMax = mstrList.length() - 1;
		}
		else if (max <= 0) {
			mintMax = 0;
		}
		else {
			mintMax = max;
		}
	}
	
	public void setValue(Integer value) {
		if (value < 0)
		{
			this.mintValue = 0;
		}
		else if (value > mintMax) {
			this.mintValue = mintMax;
		}
		else {
			this.mintValue = value;
		}
	}

	public Integer getValue() {
		return mintValue;
	}
	
	@Override
	public String toString() {
		return mstrList.substring(mintValue, mintValue+1);
	}
}
