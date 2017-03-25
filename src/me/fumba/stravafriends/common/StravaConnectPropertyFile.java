/**
 *  The <tt>StravaConnectGetPropertyFile</tt> retrieves the config properties file from src/resources.
 *  
 *  <p>
 *  This file has the following values specified:
 *  
 *  applicationClientId= ""
 *	 clientSecret = ""
 *	 authorisationCode = ""
 *
 *  @author  <a href="http://fumba.me">Fumba Chibaka</a> 
 */

package me.fumba.stravafriends.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class StravaConnectPropertyFile implements ApplicationConstants {

	public String getPropValues(String key) throws IOException {
		Properties prop = new Properties();
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(STRAVA_CONFIG_PROPERTIES);

		if (inputStream != null) {
			prop.load(inputStream);
		} else {
			System.err.println("Property file '" + STRAVA_CONFIG_PROPERTIES + "' not found in the classpath");
			return System.getenv(key);
		}
		return prop.getProperty(key);
	}
}
