package it.exercise.iscs.model;

public class Weather {
	
	private short day;
	private int MxT;
	private int MnT;
	
	public Weather (short day, int mxT, int mnT) {
		this.day = day;
		MxT = mxT;
		MnT = mnT;
	}

	public short getDay() {
		return day;
	}

	public void setDay(short day) {
		this.day = day;
	}

	public int getMxT() {
		return MxT;
	}

	public void setMxT(int mxT) {
		MxT = mxT;
	}

	public int getMnT() {
		return MnT;
	}

	public void setMnT(int mnT) {
		MnT = mnT;
	}

	@Override
	public String toString() {
		return "Weather [day=" + day + ", MxT=" + MxT + ", MnT=" + MnT + "]";
	}
}
