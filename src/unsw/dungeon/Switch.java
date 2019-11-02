package unsw.dungeon;

public class Switch extends Entity  implements Floor {

	public Switch(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	final static int OFF = 0;
    final static int ON = 1;
    private int state = OFF;
	
	@Override
	public void trigger() {
		// TODO Auto-generated method stub

	}
	
	public boolean isOn () {
		if (state == OFF) return false;
		return true;
	}

}
