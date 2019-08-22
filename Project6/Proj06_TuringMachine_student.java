// CSC345 Project6
// Author: Matthew Burns

import java.util.HashMap;

public class Proj06_TuringMachine_student implements Proj06_TuringMachine {

	// Map of all machine states
	// <Name, State object>
	private HashMap<String, State> machine;
	
	// Will be filled in with first state read from file
	private State startState;

	public Proj06_TuringMachine_student() {
		this.machine = new HashMap<String, State>();
		this.startState = null;
	}

	@Override
	public void addState(String stateName, boolean accept) {
		State newState = new State(stateName, accept);
		machine.put(stateName, newState);
		if (startState == null) {
			startState = newState;
		}

	}

	@Override
	public void addTransition(String from, char read, char write, boolean moveDir, String to) {
		machine.get(from).addTransition(new Transition(to, read, write, moveDir));
	}

	@Override
	public void run(String startString, boolean debug) {
		boolean debugVar = debug;
		String tape = startString;
		int headPos = 0;
		char readChar = tape.charAt(headPos);
		State curr = startState;

		printState(tape, headPos, curr.name());
		
		while (!curr.isAccept()) {

			// read char at current head position
			readChar = tape.charAt(headPos);

			// get which edge we're using
			Transition moveTo = curr.transitionList.get(readChar);

			// move curr to new state
			if (curr.validChars.contains(readChar)) {
				curr = machine.get(moveTo.to);
			} else {
				break;
			}

			// update tape
			tape = tape.substring(0, headPos) + moveTo.write + tape.substring(headPos + 1, tape.length());

			// move head
			if (moveTo.moveDir) {
				// move right
				headPos++;
			} else {
				// move left
				headPos--;
			}

			// Pad tape if necessary
			if (headPos >= tape.length()) {
				tape = tape + '.';
			} else if (headPos < 0) {
				tape = '.' + tape;
				headPos = 0;
			}
			
			if (debugVar) {
				printState(tape, headPos, curr.name());
			}
		}
		
		if (!debugVar) {
			printState(tape, headPos, curr.name());
		}
		
		System.out.print("\n");
		if (curr.isAccept()) {
			
			System.out.print("MACHINE ACCEPTS!\n");
		} else {
			System.out.print("MACHINE REJECTS!\n");
		}
	}
	
	// Helper function to print current machine state
	private void printState(String tape, int headPos, String state) {
		System.out.print(tape + "\n");
		for (int i = 0; i < headPos; i++) {
			System.out.print(" ");
		}
		System.out.print("^   state: " + state + "\n");
	}

}
