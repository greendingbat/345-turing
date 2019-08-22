// Object to represent an edge (transition) in Turing Machine
// Author: Matthew Burns

import java.util.ArrayList;
import java.util.HashMap;

public class State {
	private boolean isAccept;
	private String stateName;
	public HashMap<Character, Transition> transitionList;
	public ArrayList<Character> validChars;

	public State(String name, boolean isAccept) {
		this.isAccept = isAccept;
		this.stateName = name;
		this.transitionList = new HashMap<Character, Transition>();
		this.validChars = new ArrayList<Character>();
	}
	
	public void addTransition(Transition t) {
		transitionList.put(t.read, t);
		validChars.add(t.read);
	}
	
	public Boolean isAccept() {
		return this.isAccept;
	}
	
	public String name() {
		return this.stateName;
	}
	
}
