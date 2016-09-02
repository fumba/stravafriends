/**
 *  The <tt>StravaConnectionService</tt> connects to the strava API to retrieve user data.  
 *  <p>
 *  
 *  This services uses Oauth to retrieve info using the javastravav3api framework. 
 *  <p>
 *
 *  @author  <a href="http://fumba.me">Fumba Chibaka</a> 
 */
package me.fumba.stravafriends.services;

import java.io.IOException;
import com.thoughtworks.xstream.XStream;

import javastrava.api.v3.auth.AuthorisationService;
import javastrava.api.v3.auth.impl.retrofit.AuthorisationServiceImpl;
import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.model.StravaAthlete;
import javastrava.api.v3.service.Strava;
import me.fumba.stravafriends.common.ApplicationConstants;
import me.fumba.stravafriends.common.StravaConnectGetPropertyFile;

public class StravaConnectionService implements ApplicationConstants {

	private Integer applicationClientId;
	private String clientSecret;
	private String authorisationCode;
	
	public StravaConnectionService(String  code) throws NumberFormatException, IOException {
		StravaConnectGetPropertyFile properties = new StravaConnectGetPropertyFile();
		this.applicationClientId = Integer.parseInt(properties.getPropValues(APPLICATION_CLIENT_ID));
		this.clientSecret = properties.getPropValues(CLIENT_SECRET);
		this.authorisationCode = code;
	}

	public String connect() {
		
		AuthorisationService service = new AuthorisationServiceImpl();
		Token token = service.tokenExchange(applicationClientId, clientSecret, authorisationCode);
		Strava strava = new Strava(token);
		
		Integer id = token.getAthlete().getId();
		StravaAthlete athlete = strava.getAthlete(id);
		
		XStream xstream = new XStream();
		return xstream.toXML(athlete);
	}

}
