/**
 * Connects to strava service to retrieve athlete information. 
 *
 *  @author  <a href="http://fumba.me">Fumba Chibaka</a> 
 */

package me.fumba.stravafriends.actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.thoughtworks.xstream.XStream;

import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.model.StravaAthlete;
import javastrava.api.v3.service.Strava;

import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.interceptor.SessionAware;

import me.fumba.stravafriends.common.ApplicationConstants;
import me.fumba.stravafriends.services.StravaConnectionService;

@ResultPath(value = "/")
public class StravaFriendsConnectAction extends ActionSupport implements ApplicationConstants, SessionAware {

	private static final long serialVersionUID = 4758878788148763637L;

	private String pageName;
	private String errorMessage;

	private String tokenXML;
	private StravaAthlete stravaAthlete;

	private Integer notFollowingBackCount;

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
		Integer id = token.getAthlete().getId();
		this.setStravaAthlete(strava.getAthlete(id));

		this.setNotFollowingBackCount(
				this.getStravaAthlete().getFriendCount() - this.getStravaAthlete().getFollowerCount());

		XStream xstream = new XStream();
		this.setTokenXML(xstream.toXML(this.getStravaAthlete()));
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

	public StravaAthlete getStravaAthlete() {
		return stravaAthlete;
	}

	public void setStravaAthlete(StravaAthlete stravaAthlete) {
		this.stravaAthlete = stravaAthlete;
	}

	@Override
	public void setSession(Map<String, Object> session) {

	}

	public Integer getNotFollowingBackCount() {
		return notFollowingBackCount;
	}

	public void setNotFollowingBackCount(Integer notFollowingBackCount) {
		this.notFollowingBackCount = notFollowingBackCount;
	}
}