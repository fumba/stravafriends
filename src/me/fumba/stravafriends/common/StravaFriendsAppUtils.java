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

import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.model.StravaActivity;
import javastrava.api.v3.model.StravaAthlete;
import javastrava.api.v3.model.StravaClub;
import javastrava.api.v3.model.reference.StravaActivityType;
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

	/**
	 * Extracts the leadership board values for the activity that is specified.
	 * 
	 * @param strava
	 * @param authenticatedUserActivities
	 * @return
	 */
	public static HashMap<Integer, LeaderboardResult> getLeaderboardData(Token token,
			StravaActivityType stravaActivityType) {
		Strava strava = new Strava(token);
		List<StravaActivity> friendActivities = strava.listAllFriendsActivities();
		HashMap<Integer, LeaderboardResult> runActivitiesMap = new HashMap<Integer, LeaderboardResult>();
		for (StravaActivity activity : friendActivities) {
			if (activity.getType() == stravaActivityType) {
				if (runActivitiesMap.get(activity.getAthlete().getId()) == null) {
					LeaderboardResult result = new LeaderboardResult();
					result.setDistance(convertMetersToMiles(activity.getDistance()));
					result.setAthlete(strava.getAthlete(activity.getAthlete().getId()));
					runActivitiesMap.put(activity.getAthlete().getId(), result);
				} else {
					LeaderboardResult result = runActivitiesMap.get(activity.getAthlete().getId());
					result.setDistance(result.getDistance() + convertMetersToMiles(activity.getDistance()));
					runActivitiesMap.put(activity.getAthlete().getId(), result);
				}
			}
		}
		return runActivitiesMap;
	}

	/**
	 * Converts meters to miles.
	 * 
	 * @param meters
	 * @return
	 */
	public static float convertMetersToMiles(Float meters) {
		return (float) (meters / 1609.344);
	}

}
