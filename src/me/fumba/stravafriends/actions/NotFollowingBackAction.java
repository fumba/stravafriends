/**
 *A collections of functions used when retrieving the list of athletes that the authenticated user follows but do not follow back. 
 *
 *  @author  <a href="http://fumba.me">Fumba Chibaka</a> 
 */

package me.fumba.stravafriends.actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.model.StravaAthlete;
import javastrava.api.v3.service.Strava;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import me.fumba.stravafriends.common.ApplicationConstants;
import me.fumba.stravafriends.common.StravaFriendsAppUtils;

@ResultPath(value = "/")
@ParentPackage("json-default")
public class NotFollowingBackAction extends ActionSupport implements ApplicationConstants {

	private static final long serialVersionUID = -8889492539981192410L;

	/**
	 * List of athletes that do not follow back the authenticated user.
	 */
	private ArrayList<StravaAthlete> athletesNotFollowingBack = new ArrayList<StravaAthlete>();

	/**
	 * Loads the screens which allows the user to search for athletes who are
	 * not following them back.
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "loadNotFollowingBack", results = { @Result(name = "success", location = "/notfollowingback.jsp"),
			@Result(name = "error", location = "/error.jsp"), @Result(name = "login", location = "/index.jsp") })
	public String loadNotFollowingBackPage() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		Token token = (Token) session.get(TOKEN);
		if (token == null) {
			return LOGIN;
		}
		Strava strava = new Strava(token);
		StravaAthlete authenticatedAthlete = strava.getAuthenticatedAthlete();
		this.athletesNotFollowingBack = StravaFriendsAppUtils
				.retrieveAthletesNotFollowingBack(strava.listAllAthleteFriends(authenticatedAthlete.getId()));
		return SUCCESS;
	}

	public ArrayList<StravaAthlete> getAthletesNotFollowingBack() {
		return athletesNotFollowingBack;
	}

}