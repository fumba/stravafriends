package me.fumba.stravafriends.actions;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

import me.fumba.stravafriends.common.ApplicationConstants;

@ResultPath(value = "/")
public class StravaFriendsConnectAction extends ActionSupport implements ApplicationConstants {

	private static final long serialVersionUID = 4758878788148763637L;

	private String name;
	private String pageName;
	private String errorMessage;

	@Action(value = "hello", results = { @Result(name = "success", location = "/dashboard.jsp"),
			@Result(name = "input", location = "/index.jsp"), @Result(name = "error", location = "/error.jsp") })
	@Override
	public String execute() throws Exception {

		// String result = ERROR;

		// TODO if (pageName != null) {
		return SUCCESS;
		// }
		// this.setErrorMessage("Failed");
		// return result;
	}

	public String getName() {
		return name;
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Name must be provided.")
	public void setName(String name) {
		this.name = name;
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
}