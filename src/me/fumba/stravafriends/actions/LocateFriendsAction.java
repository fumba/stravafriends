/**
 *Determines the geographical location for friends. 
 *
 *  @author  <a href="http://fumba.me">Fumba Chibaka</a> 
 */
package me.fumba.stravafriends.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.model.StravaAthlete;
import javastrava.api.v3.service.Strava;
import me.fumba.stravafriends.common.ApplicationConstants;

public class LocateFriendsAction extends ActionSupport implements ApplicationConstants {

	private static final long serialVersionUID = 7561019900720521832L;

	/**
	 * List of friends for the logged athlete
	 * 
	 * @return
	 * @throws Exception
	 */
	private ArrayList<StravaAthlete> friends;

	private String friendLocations;

	@Action(value = "friendLocationList", results = { @Result(name = "success", location = "/friendlocationslist.jsp"),
			@Result(name = "error", location = "/error.jsp"), @Result(name = "login", location = "/index.jsp") })
	public String friendLocationList() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		Token token = (Token) session.get(TOKEN);
		if (token == null) {
			return LOGIN;
		}

		Strava strava = new Strava(token);
		StravaAthlete authenticatedAthlete = strava.getAuthenticatedAthlete();
		this.setFriends((ArrayList<StravaAthlete>) strava.listAllAthleteFriends(authenticatedAthlete.getId()));

		return SUCCESS;
	}

	@Action(value = "friendLocationMap", results = { @Result(name = "success", location = "/friendlocationsmap.jsp"),
			@Result(name = "error", location = "/error.jsp"), @Result(name = "login", location = "/index.jsp") })
	public String friendLocationMap() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		Token token = (Token) session.get(TOKEN);
		if (token == null) {
			return LOGIN;
		}

		Strava strava = new Strava(token);
		StravaAthlete authenticatedAthlete = strava.getAuthenticatedAthlete();
		List<StravaAthlete> friendList = strava.listAllAthleteFriends(authenticatedAthlete.getId());
		String[][] locationList = new String[friendList.size()][2];
		int index = 0;
		for (StravaAthlete friend : friendList) {
			locationList[index][0] = friend.getCity();
			locationList[index++][1] = friend.getCountry();
		}

		Gson gson = new GsonBuilder().create();
		this.setFriendLocations(gson.toJson(locationList));
		return SUCCESS;
	}

	public ArrayList<StravaAthlete> getFriends() {
		return friends;
	}

	public void setFriends(ArrayList<StravaAthlete> friends) {
		this.friends = friends;
	}

	public String getFriendLocations() {
		return friendLocations;
	}

	public void setFriendLocations(String friendLocations) {
		this.friendLocations = friendLocations;
	}

}
