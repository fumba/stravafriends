/**
 *Determines shared club information. 
 *
 *  @author  <a href="http://fumba.me">Fumba Chibaka</a> 
 */
package me.fumba.stravafriends.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.model.StravaAthlete;
import javastrava.api.v3.model.StravaClub;
import javastrava.api.v3.service.Strava;
import me.fumba.stravafriends.common.ApplicationConstants;
import me.fumba.stravafriends.common.StravaFriendsAppUtils;

public class MutualClubsAction extends ActionSupport implements ApplicationConstants {

	private static final long serialVersionUID = 8589104000103117539L;
	private Map<StravaClub, ArrayList<StravaAthlete>> mutualClubs;

	@Action(value = "mutualClubs", results = { @Result(name = "success", location = "/mutualclubs.jsp"),
			@Result(name = "error", location = "/error.jsp"), @Result(name = "login", location = "/index.jsp") })
	public String friendLocationList() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		Token token = (Token) session.get(TOKEN);
		if (token == null) {
			return LOGIN;
		}

		Strava strava = new Strava(token);
		StravaAthlete authenticatedAthlete = strava.getAuthenticatedAthlete();
		List<StravaAthlete> followingList = strava.listAllAthleteFriends(authenticatedAthlete.getId());
		setMutualClubs(StravaFriendsAppUtils.retrieveMutualClubs(session, authenticatedAthlete, followingList, strava));
		return SUCCESS;
	}

	public Map<StravaClub, ArrayList<StravaAthlete>> getMutualClubs() {
		return mutualClubs;
	}

	public void setMutualClubs(Map<StravaClub, ArrayList<StravaAthlete>> mutualClubs) {
		this.mutualClubs = mutualClubs;
	}

}
