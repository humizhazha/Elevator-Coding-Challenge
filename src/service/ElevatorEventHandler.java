package service;

import java.util.ArrayList;

import exception.InvalidFloorException;
import exception.NoWorkingElevatorException;
import model.Elevator;

/**
 * @author human 
 * 
 * Implement this class by considering design of YOUR OWN elevator
 *         system. Interface "PhysicalElevator" is given to you by other team,
 *         use those methods as helping methods to your design./
 */
public class ElevatorEventHandler {

	private PhisicalElevator phisicalElevator;
	private ArrayList<Elevator> elevatorList;
	private final static int MIN_FLOOR = 0;
	private final static int MAX_FLOOR = 10;

	public ElevatorEventHandler(PhisicalElevator phisicalElevator, ArrayList<Elevator> eList) {

		this.phisicalElevator = phisicalElevator;
		this.elevatorList = eList;
	}

	private boolean checkInputFloor(int floor) {
		if (floor < MIN_FLOOR || floor > MAX_FLOOR) {
			return false;
		}
		return true;
	}

	private Elevator selectNearestElevator(int floor) {
		int min = Integer.MAX_VALUE;
		Elevator selectedElevator = null;
		for (Elevator e : elevatorList) {
			int gap = Math.abs(floor - e.getCurrentFloor());
			if (gap < min) {
				min = gap;
				selectedElevator = e;
			}
		}
		return selectedElevator;
	}

	public void OnFloorButtonPress(int floor) {
		if (!checkInputFloor(floor)) {
			throw new InvalidFloorException("Input floor invalid");
		}
		Elevator targetElevator = selectNearestElevator(floor);
		if (targetElevator == null) {
			throw new NoWorkingElevatorException("No working elevator");
		}
		while (!phisicalElevator.isApproachingFloor(targetElevator, floor)) {
			if (targetElevator.getCurrentFloor() > floor) {
				phisicalElevator.startMovingDown(targetElevator);
			} else {
				phisicalElevator.startMovingUp(targetElevator);
			}
		}
	}

	public void OnInnerElevatorButtonPress(Elevator e, int floor) {
		if (!checkInputFloor(floor)) {
			throw new InvalidFloorException("Input floor invalid");
		}
		while (!phisicalElevator.isApproachingFloor(e, floor)) {
			if (e.getCurrentFloor() > floor) {
				phisicalElevator.startMovingDown(e);
			} else {
				phisicalElevator.startMovingUp(e);
			}
		}

	}

}
