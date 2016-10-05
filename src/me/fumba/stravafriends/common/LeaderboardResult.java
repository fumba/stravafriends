package me.fumba.stravafriends.common;

import javastrava.api.v3.model.StravaAthlete;

public class LeaderboardResult {

	private StravaAthlete athlete;
	private Float distance;

	public StravaAthlete getAthlete() {
		return athlete;
	}

	public void setAthlete(StravaAthlete athlete) {
		this.athlete = athlete;
	}

	public Float getDistance() {
		return distance;
	}

	public void setDistance(Float distance) {
		this.distance = distance;
	}

}
