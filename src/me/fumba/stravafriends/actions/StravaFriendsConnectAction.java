/**
 * Connects to strava service to retrieve athlete information. 
 *
 *  @author  <a href="http://fumba.me">Fumba Chibaka</a> 
 */

package me.fumba.stravafriends.actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import me.fumba.stravafriends.common.ApplicationConstants;
import me.fumba.stravafriends.services.StravaConnectionService;

@ResultPath(value = "/")
public class StravaFriendsConnectAction extends ActionSupport implements ApplicationConstants {

	private static final long serialVersionUID = 4758878788148763637L;

	private String pageName;
	private String errorMessage;

	private String tokenXML;

	@Action(value = "connect", results = { @Result(name = "success", location = "/dashboard.jsp"),
			@Result(name = "error", location = "/error.jsp") })
	@Override
	public String execute() throws Exception {
		Map<String, Object> parameters = ActionContext.getContext().getParameters();
		StravaConnectionService stravaConnService = new StravaConnectionService(((String[]) parameters.get(CODE))[0]);
		this.setTokenXML(stravaConnService.connect());
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
}