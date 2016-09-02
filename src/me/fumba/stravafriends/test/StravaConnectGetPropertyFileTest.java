package me.fumba.stravafriends.test;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;

import me.fumba.stravafriends.common.ApplicationConstants;
import me.fumba.stravafriends.common.StravaConnectGetPropertyFile;

public class StravaConnectGetPropertyFileTest implements ApplicationConstants {

	@Test
	public void retrievePropertiesFile() {
		StravaConnectGetPropertyFile stravaConnectGetPropertyFile = new StravaConnectGetPropertyFile();
		try {
			String applicationClientId = stravaConnectGetPropertyFile.getPropValues(APPLICATION_CLIENT_ID);
			assertNotNull(applicationClientId);

			String clientSecret = stravaConnectGetPropertyFile.getPropValues(CLIENT_SECRET);
			assertNotNull(clientSecret);

		} catch (IOException e) {

		}
	}

}
