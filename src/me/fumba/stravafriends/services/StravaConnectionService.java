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
import javastrava.api.v3.auth.AuthorisationService;
import javastrava.api.v3.auth.impl.retrofit.AuthorisationServiceImpl;
import javastrava.api.v3.auth.model.Token;
import me.fumba.stravafriends.common.ApplicationConstants;
import me.fumba.stravafriends.common.StravaConnectPropertyFile;

public class StravaConnectionService implements ApplicationConstants {

	private Integer applicationClientId;
	private String clientSecret;
	private String authorisationCode;

	public StravaConnectionService(String code) throws NumberFormatException, IOException {
		StravaConnectPropertyFile properties = new StravaConnectPropertyFile();
		this.applicationClientId = Integer.parseInt(properties.getPropValues(APPLICATION_CLIENT_ID));
		this.clientSecret = properties.getPropValues(CLIENT_SECRET);
		this.setAuthorisationCode(code);
	}

	public Token connect() {
		AuthorisationService service = new AuthorisationServiceImpl();
		Token token = service.tokenExchange(applicationClientId, clientSecret, authorisationCode);
		return token;
	}

	public String getAuthorisationCode() {
		return authorisationCode;
	}

	public void setAuthorisationCode(String authorisationCode) {
		this.authorisationCode = authorisationCode;
	}
}
