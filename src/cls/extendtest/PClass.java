package cls.extendtest;

public class PClass {
	private String s;

	@SuppressWarnings("unused")
	private PClass() {

	}

	protected PClass(String s) {
		this.s = s;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

}
