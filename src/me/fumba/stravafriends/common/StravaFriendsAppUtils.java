/**
 * A collection of functions used throughout the application. 
 * 
 * @author  <a href="http://fumba.me">Fumba Chibaka</a> 
 */
package me.fumba.stravafriends.common;

import java.util.ArrayList;
import java.util.List;

import javastrava.api.v3.model.StravaAthlete;
import javastrava.api.v3.model.reference.StravaFollowerState;

public class StravaFriendsAppUtils {

	/**
	 * Extracts a lost of athletes that the user follows but do not follow
	 * him/her back.
	 * 
	 * @param followedList
	 * @return
	 */
	public static ArrayList<StravaAthlete> retrieveAthletesNotFollowingBack(List<StravaAthlete> followedList) {
		ArrayList<StravaAthlete> nonFollowerList = new ArrayList<StravaAthlete>();
		for (StravaAthlete followed : followedList) {
			if (followed.getFollower() != StravaFollowerState.ACCEPTED) {
				nonFollowerList.add(followed);
			}
		}
		return nonFollowerList;
	}

	/**
	 * Extracts the list of athletes that the authenticated user does not follow
	 * back.
	 */
	public static ArrayList<StravaAthlete> retrieveAthletesNotFriendedBack(List<StravaAthlete> followedList) {
		// Retrieve athletes that the authenticated user follows but they do not
		// follow back
		ArrayList<StravaAthlete> notFollowedList = new ArrayList<StravaAthlete>();
		for (StravaAthlete friend : followedList) {
			if (friend.getFriend() != StravaFollowerState.ACCEPTED) {
				notFollowedList.add(friend);
			}
		}
		return notFollowedList;
	}
}
