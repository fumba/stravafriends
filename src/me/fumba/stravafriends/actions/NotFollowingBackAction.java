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
	 * List of athletes that follow the authenticated user but are not friended
	 * back.
	 */
	private ArrayList<StravaAthlete> athletesNotFriendedBack = new ArrayList<StravaAthlete>();

	/**
	 * Extracts athletes who are not following back the user.
	 * 
	 * This function is called by angularjs and returns a JSON object.
	 * 
	 * @return
	 * @throws Exception
	 */
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
		this.athletesNotFollowingBack = StravaFriendsAppUtils.retrieveAthletesNotFollowingBack(
				strava.listAllAthleteFriends(authenticatedAthlete.getId()));
		return SUCCESS;
	}

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
		return SUCCESS;
	}

	/**
	 * Extracts athletes who are not friended back by the user.
	 * 
	 * This function is called by angularjs and returns a JSON object.
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "retrieveNotFriended", results = { @Result(name = "success", type = "json"),
			@Result(name = "error", location = "/error.jsp"), @Result(name = "login", location = "/index.jsp") })
	public String retrieveNotFriendedBack() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		Token token = (Token) session.get(TOKEN);
		if (token == null) {
			return LOGIN;
		}
		Strava strava = new Strava(token);
		StravaAthlete authenticatedAthlete = strava.getAuthenticatedAthlete();
		this.athletesNotFriendedBack = StravaFriendsAppUtils.retrieveAthletesNotFollowingBack(
				strava.listAllAthleteFriends(authenticatedAthlete.getId()));
		return SUCCESS;
	}

	/**
	 * Loads the screens which allows the user to search for athletes who are
	 * not being friended back.
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "loadNotFriendedBack", results = { @Result(name = "success", location = "/notfriendedback.jsp"),
			@Result(name = "error", location = "/error.jsp"), @Result(name = "login", location = "/index.jsp") })
	public String loadNotFriendedBackPage() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		Token token = (Token) session.get(TOKEN);
		if (token == null) {
			return LOGIN;
		}
		return SUCCESS;
	}

	public ArrayList<StravaAthlete> getAthletesNotFollowingBack() {
		return athletesNotFollowingBack;
	}

	public ArrayList<StravaAthlete> getAthletesNotFriendedBack() {
		return athletesNotFriendedBack;
	}

	public void setAthletesNotFriendedBack(ArrayList<StravaAthlete> athletesNotFriendedBack) {
		this.athletesNotFriendedBack = athletesNotFriendedBack;
	}

}