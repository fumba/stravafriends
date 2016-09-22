/**
 * A collection of functions used throughout the application. 
 * 
 * @author  <a href="http://fumba.me">Fumba Chibaka</a> 
 */
package me.fumba.stravafriends.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javastrava.api.v3.model.StravaAthlete;
import javastrava.api.v3.model.StravaClub;
import javastrava.api.v3.model.reference.StravaFollowerState;
import javastrava.api.v3.service.Strava;

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

	/**
	 * Extracts mutual clubs between the authenticated user and their follower
	 * list.
	 * 
	 * @param session
	 * 
	 * @param authenticatedAthlete
	 * @param followingList
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<StravaClub, ArrayList<StravaAthlete>> retrieveMutualClubs(Map<String, Object> session,
			StravaAthlete authenticatedAthlete, List<StravaAthlete> followingList, Strava strava) {
		if (session.get("mutualClubs") != null) {
			return (Map<StravaClub, ArrayList<StravaAthlete>>) session.get("mutualClubs");
		}
		Map<StravaClub, ArrayList<StravaAthlete>> mutualClubHashMap = new HashMap<StravaClub, ArrayList<StravaAthlete>>();

		ArrayList<StravaAthlete> matchedClubMembersList = null;
		if (authenticatedAthlete.getClubs() != null) {
			for (StravaClub authUserClub : authenticatedAthlete.getClubs()) {
				matchedClubMembersList = new ArrayList<StravaAthlete>();
				for (StravaAthlete authUserClubMember : strava.listAllClubMembers(authUserClub.getId())) {
					for (StravaAthlete follower : followingList) {
						if (follower.getId().equals(authUserClubMember.getId())) {
							matchedClubMembersList.add(follower);
							mutualClubHashMap.put(authUserClub, matchedClubMembersList);
						}
					}
				}
			}
		}
		session.put("mutualClubs", mutualClubHashMap);
		return mutualClubHashMap;
	}
}
