/**
 *Determines running and biking leadership statistics. 
 *
 *  @author  <a href="http://fumba.me">Fumba Chibaka</a> 
 */
package me.fumba.stravafriends.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.model.StravaActivity;
import javastrava.api.v3.model.StravaAthlete;
import javastrava.api.v3.model.reference.StravaActivityType;
import javastrava.api.v3.service.Strava;
import me.fumba.stravafriends.common.ApplicationConstants;

@ParentPackage("json-default")
public class LeadershipBoardAction extends ActionSupport implements ApplicationConstants {

	private static final long serialVersionUID = 1641471177459230928L;
	ArrayList<StravaAthlete> followers;

	@Action(value = "runLeaderboard", results = { @Result(name = "success", location = "/runleaderboard.jsp"),
			@Result(name = "error", location = "/error.jsp"), @Result(name = "login", location = "/index.jsp") })
	public String runLeaderboard() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		Token token = (Token) session.get(TOKEN);
		if (token == null) {
			return LOGIN;
		}
		return SUCCESS;
	}

	@Action(value = "retrieveRunLeaderboard", results = { @Result(name = "success", type = "json"),
			@Result(name = "error", location = "/error.jsp"), @Result(name = "login", location = "/index.jsp") })
	public String retrieveRunLeaderboard() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		Token token = (Token) session.get(TOKEN);
		if (token == null) {
			return LOGIN;
		}

		Strava strava = new Strava(token);
		StravaAthlete authenticatedAthlete = strava.getAuthenticatedAthlete();
		followers = (ArrayList<StravaAthlete>) strava.listAllAthleteFriends(authenticatedAthlete.getId());

		List<StravaActivity> authenticatedUserActivities = strava.listAllAuthenticatedAthleteActivities();
		List<StravaActivity> friendActivities = strava.listAllFriendsActivities();
		// collect all activities
		authenticatedUserActivities.addAll(friendActivities);
		HashMap<StravaAthlete, Float> runActivitiesMap = new HashMap<StravaAthlete, Float>();
		for (StravaActivity activity : authenticatedUserActivities) {
			if (activity.getType() == StravaActivityType.RUN) {
				if (runActivitiesMap.get(activity.getAthlete()) == null) {
					runActivitiesMap.put(activity.getAthlete(), activity.getDistance());
				} else {
					runActivitiesMap.put(activity.getAthlete(),
							runActivitiesMap.get(activity.getAthlete()) + activity.getDistance());
				}
			}
		}
		return SUCCESS;
	}

	@Action(value = "bikeLeaderboard", results = { @Result(name = "success", location = "/bikeleaderboard.jsp"),
			@Result(name = "error", location = "/error.jsp"), @Result(name = "login", location = "/index.jsp") })
	public String bikeLeaderboard() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		Token token = (Token) session.get(TOKEN);
		if (token == null) {
			return LOGIN;
		}
		return SUCCESS;
	}

	public ArrayList<StravaAthlete> getFollowers() {
		return followers;
	}

	public void setFollowers(ArrayList<StravaAthlete> followers) {
		this.followers = followers;
	}
}
