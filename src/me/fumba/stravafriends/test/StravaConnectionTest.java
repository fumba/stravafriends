package me.fumba.stravafriends.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import me.fumba.stravafriends.services.StravaConnectionService;

public class StravaConnectionTest {

	@Test
	public void test() {

		try {
			StravaConnectionService stravaConnectionService = new StravaConnectionService();
			assertEquals(true, stravaConnectionService.connect());
		} catch (NumberFormatException | IOException e) {
		}
	}

}
