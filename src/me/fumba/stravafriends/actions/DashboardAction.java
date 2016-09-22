/**
 * Connects to strava service to retrieve athlete information. 
 *
 *  @author  <a href="http://fumba.me">Fumba Chibaka</a> 
 */

package me.fumba.stravafriends.actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.model.StravaAthlete;
import javastrava.api.v3.service.Strava;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.interceptor.SessionAware;

import me.fumba.stravafriends.common.ApplicationConstants;
import me.fumba.stravafriends.common.StravaFriendsAppUtils;
import me.fumba.stravafriends.services.StravaConnectionService;

@ResultPath(value = "/")
public class DashboardAction extends ActionSupport implements ApplicationConstants, SessionAware {

	private static final long serialVersionUID = 4758878788148763637L;

	private String pageName;
	private String errorMessage;

	private String tokenXML;
	private StravaAthlete authenticatedAthlete;

	private int notFollowingBackCount;
	private int notFriendedBackCount;
	private int mutualClubs;
	private int suggestedClubs;

	@Action(value = "connect", results = { @Result(name = "success", location = "/dashboard.jsp"),
			@Result(name = "error", location = "/error.jsp"), @Result(name = "login", location = "/index.jsp") })
	@Override
	public String execute() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();

		if (ActionContext.getContext().getParameters() == null) {
			return LOGIN;
		}
		Map<String, Object> parameters = ActionContext.getContext().getParameters();
		String[] codeArray = ((String[]) parameters.get(CODE));
		if (ArrayUtils.isEmpty(codeArray)) {
			return LOGIN;
		}
		StravaConnectionService stravaConnService = new StravaConnectionService(codeArray[0]);

		Token token = stravaConnService.connect();
		session.put(LOGIN, STRING_TRUE);
		session.put(CODE, stravaConnService.getAuthorisationCode());
		session.put(TOKEN, token);

		Strava strava = new Strava(token);

		this.authenticatedAthlete = strava.getAuthenticatedAthlete();

		List<StravaAthlete> followingList = strava.listAllAthleteFriends(this.authenticatedAthlete.getId());
		this.setNotFollowingBackCount(StravaFriendsAppUtils.retrieveAthletesNotFollowingBack(followingList).size());
		this.setNotFriendedBackCount(StravaFriendsAppUtils.retrieveAthletesNotFriendedBack(followingList).size());

		this.setMutualClubs(StravaFriendsAppUtils
				.retrieveMutualClubs(session, this.authenticatedAthlete, followingList, strava).size());
		return SUCCESS;
	}

	@Action(value = "logout", results = { @Result(name = "success", location = "/index.jsp"),
			@Result(name = "error", location = "/error.jsp") })
	public String logout() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.remove(LOGIN);
		session.remove(CODE);
		session.remove(TOKEN);
		return SUCCESS;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getTokenXML() {
		return tokenXML;
	}

	public void setTokenXML(String tokenXML) {
		this.tokenXML = tokenXML;
	}

	@Override
	public void setSession(Map<String, Object> session) {

	}

	public int getNotFollowingBackCount() {
		return notFollowingBackCount;
	}

	public void setNotFollowingBackCount(Integer notFollowingBackCount) {
		this.notFollowingBackCount = notFollowingBackCount;
	}

	public StravaAthlete getAuthenticatedAthlete() {
		return authenticatedAthlete;
	}

	public void setAuthenticatedAthlete(StravaAthlete authenticatedAthlete) {
		this.authenticatedAthlete = authenticatedAthlete;
	}

	public int getNotFriendedBackCount() {
		return notFriendedBackCount;
	}

	public void setNotFriendedBackCount(Integer notFriendedBackCount) {
		this.notFriendedBackCount = notFriendedBackCount;
	}

	public int getMutualClubs() {
		return mutualClubs;
	}

	public void setMutualClubs(int mutualClubs) {
		this.mutualClubs = mutualClubs;
	}

	public int getSuggestedClubs() {
		return suggestedClubs;
	}

	public void setSuggestedClubs(int suggestedClubs) {
		this.suggestedClubs = suggestedClubs;
	}
}