package me.fumba.stravafriends;

public class StravaFriendsConnectAction {
	private String name;

	public String execute() throws Exception {
		return "success";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}