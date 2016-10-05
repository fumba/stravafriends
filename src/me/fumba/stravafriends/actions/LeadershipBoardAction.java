/**
 *Determines running and biking leadership statistics. 
 *
 *  @author  <a href="http://fumba.me">Fumba Chibaka</a> 
 */
package me.fumba.stravafriends.actions;

import java.util.HashMap;
import java.util.Map;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.model.reference.StravaActivityType;
import me.fumba.stravafriends.common.ApplicationConstants;
import me.fumba.stravafriends.common.LeaderboardResult;
import me.fumba.stravafriends.common.StravaFriendsAppUtils;

@ParentPackage("json-default")
public class LeadershipBoardAction extends ActionSupport implements ApplicationConstants {

	private static final long serialVersionUID = 1641471177459230928L;

	HashMap<Integer, LeaderboardResult> runActivitiesMap;

	HashMap<Integer, LeaderboardResult> bikeActivitiesMap;

	@Action(value = "runLeaderboard", results = { @Result(name = "success", location = "/runleaderboard.jsp"),
			@Result(name = "error", location = "/error.jsp"), @Result(name = "login", location = "/index.jsp") })
	public String runLeaderboard() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		Token token = (Token) session.get(TOKEN);
		if (token == null) {
			return LOGIN;
		}
		this.runActivitiesMap = StravaFriendsAppUtils.getLeaderboardData(token, StravaActivityType.RUN);
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
		this.bikeActivitiesMap = StravaFriendsAppUtils.getLeaderboardData(token, StravaActivityType.RIDE);
		return SUCCESS;
	}

	public HashMap<Integer, LeaderboardResult> getRunActivitiesMap() {
		return runActivitiesMap;
	}

	public void setRunActivitiesMap(HashMap<Integer, LeaderboardResult> runActivitiesMap) {
		this.runActivitiesMap = runActivitiesMap;
	}

	public HashMap<Integer, LeaderboardResult> getBikeActivitiesMap() {
		return bikeActivitiesMap;
	}

	public void setBikeActivitiesMap(HashMap<Integer, LeaderboardResult> bikeActivitiesMap) {
		this.bikeActivitiesMap = bikeActivitiesMap;
	}

}
