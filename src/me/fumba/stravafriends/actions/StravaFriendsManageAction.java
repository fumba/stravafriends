/**
 * Manage strava friends. 
 *
 *  @author  <a href="http://fumba.me">Fumba Chibaka</a> 
 */

package me.fumba.stravafriends.actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.model.StravaAthlete;
import javastrava.api.v3.model.reference.StravaFollowerState;
import javastrava.api.v3.service.Strava;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import me.fumba.stravafriends.common.ApplicationConstants;

@ResultPath(value = "/")
@ParentPackage("json-default")
public class StravaFriendsManageAction extends ActionSupport implements ApplicationConstants {

	private static final long serialVersionUID = -8889492539981192410L;

	private ArrayList<StravaAthlete> athletes = new ArrayList<StravaAthlete>();

	@Action(value = "retrieveNonFollowers", results = { @Result(name = "success", type = "json"),
			@Result(name = "error", location = "/error.jsp"), @Result(name = "login", location = "/index.jsp") })
	public String retrieveNonFollowers() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		Token token = (Token) session.get(TOKEN);
		if (token == null) {
			return LOGIN;
		}
		Strava strava = new Strava(token);
		StravaAthlete authenticatedAthlete = strava.getAuthenticatedAthlete();

		List<StravaAthlete> followingList = strava.listAllAthleteFriends(authenticatedAthlete.getId());

		for (StravaAthlete followed : followingList) {
			if (followed.getFollower() != StravaFollowerState.ACCEPTED) {
				this.getAthletes().add(followed);
			}
		}
		return SUCCESS;
	}

	@Action(value = "notfollowing", results = { @Result(name = "success", location = "/notfollowing.jsp"),
			@Result(name = "error", location = "/error.jsp"), @Result(name = "login", location = "/index.jsp") })
	@Override
	public String execute() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		Token token = (Token) session.get(TOKEN);
		if (token == null) {
			return LOGIN;
		}
		return SUCCESS;
	}

	public ArrayList<StravaAthlete> getAthletes() {
		return athletes;
	}

	public void setAthletes(ArrayList<StravaAthlete> athletes) {
		this.athletes = athletes;
	}

}