package com.gmail.tomahawkmissile2.multinether;

public class Direction {
	public enum DirectionType {
		NORTH,
		SOUTH,
		EAST,
		WEST;
	}
	double yaw;
	public Direction(double yaw) {
	}
	public double getYaw() {
		return yaw;
	}
	public DirectionType getType() {
		if(yaw >= -45.0 && yaw <= 45.0) {
			return DirectionType.SOUTH;
		} else if(yaw > 45.0 && yaw < 135.0) {
			return DirectionType.WEST;
		} else if(yaw >= 135.0 && yaw <= -135.0) {
			return DirectionType.NORTH;
		} else {
			return DirectionType.EAST;
		}
	}
}
