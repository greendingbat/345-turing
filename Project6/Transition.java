// Object to represent state in Turing machine
// Author: Matt Burns

public class Transition {
	// BAD BAD BAD ENCAPSULATION, I know
	public String to;
	public char read;
	public char write;
	public boolean moveDir;	
	
	public Transition(String to, char read, char write, boolean moveDir) {
		this.to = to;
		this.read = read;
		this.write = write;
		this.moveDir = moveDir;
	}
	
}
