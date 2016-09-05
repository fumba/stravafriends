/**
 *Loads attributes that are used throughout the application. 
 *
 *  @author  <a href="http://fumba.me">Fumba Chibaka</a> 
 */

package me.fumba.stravafriends.actions;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import me.fumba.stravafriends.common.ApplicationConstants;
import me.fumba.stravafriends.common.StravaConnectGetPropertyFile;

public class LoadApplicationAttributesAction extends ActionSupport implements ApplicationConstants {

	private static final long serialVersionUID = 1067202616603683783L;

	@Action(value = "loadApplicationAttributes")
	public void loadApplicationAttributes() throws IOException {
		Map<String, Object> attibutes = ActionContext.getContext().getApplication();
		attibutes.put(STRAVA_APP_AUTHORIZATION_URL, this.buildAuthURL());
	}

	/**
	 * Builds the authorization URL using attributes from
	 * stravaconfig.properties.
	 * 
	 * @return URL string
	 * @throws IOException
	 */
	private String buildAuthURL() throws IOException {

		HttpServletRequest request = ServletActionContext.getRequest();
		StringBuffer requestUrl = request.getRequestURL();

		URL urlObj = new URL(requestUrl.toString());
		String authority = urlObj.getAuthority();

		StravaConnectGetPropertyFile propertyFile = new StravaConnectGetPropertyFile();
		StringBuffer url = new StringBuffer("https://www.strava.com/oauth/authorize?");
		url.append("client_id=");
		url.append(propertyFile.getPropValues(APPLICATION_CLIENT_ID));
		url.append("&response_type=code");
		url.append("&redirect_uri=http://");
		url.append(authority);

		if (StringUtils.equals(urlObj.getHost(), "localhost")) {
			url.append("/stravafriends/connect");
		} else {
			url.append("/connect");
		}

		// Strava API scopes:
		// comma delimited string of ‘view_private’ and/or ‘write’, leave blank
		// for read-only permissions.
		url.append("&scope=view_private");

		url.append("&state=mystate");
		url.append("&approval_prompt=force");
		return url.toString();
	}

}
