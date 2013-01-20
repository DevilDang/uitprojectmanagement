package sp.form;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.validator.ValidatorForm;

import sp.blo.UserBlo;
import sp.dao.GroupDao;
import sp.dao.PMF;
import sp.dao.UserDao;
import sp.dto.Group;
import sp.dto.User;
import sp.util.Constant;

public class AccountForm extends ValidatorForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String email;
	private String fullname;
	private String password;
	private String retypepassword;

	private String permission;
	private long groupCode;

	// private String iTypeAction;

	public AccountForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getRetypepassword() {
		return retypepassword;
	}

	public void setRetypepassword(String retypepassword) {
		this.retypepassword = retypepassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public long getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(long groupCode) {
		this.groupCode = groupCode;
	}

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		String checkMode = request.getParameter("isEdit");

		ActionErrors errors = new ActionErrors();
		if (getEmail() == null || getEmail().length() < 1) {
			errors.add("username", new ActionMessage("error.username.required"));
			// TODO: add 'error.name.required' key to your resources
		} else {
			if ("add".equals(checkMode)) {
				// kiem tra login name co ton tai trong he thong hay khong
				if (UserBlo.isExistUser_byEmail(getEmail().trim())) {
					errors.add("usernameExist", new ActionMessage(
							"error.usernameExist.isExist"));
				}
			} else if ("edit".equals(checkMode)) {
				if (!UserBlo.isExistUser_byEmail(getEmail().trim())) {
					System.out.println("email " + getEmail());
					errors.add("usernameUnExist", new ActionMessage(
							"error.usernameUnExist.isUnExist"));
				}
			}

			if (User.LEADER.equals(getPermission())) {
				// kiểm tra xem đã chọn nhóm chưa và nhóm này đã có nhóm trưởng
				// chưa
				if (getGroupCode() == 0) {
					errors.add("group", new ActionMessage(
							"error.group.notchoosegroup"));
				} else {
					GroupDao groupdao = new GroupDao();
					Group group = groupdao.getGroup(getGroupCode());
					System.out
							.println("Dang tan loc" + group.getLeader().trim()
									+ "loc" + getGroupCode());
					if (group.getLeader().trim().length() > 1) {
						errors.add("group", new ActionMessage(
								"error.group.isexistleader"));
					}
				}
			}
		}

		if (getFullname() == null || getFullname().length() < 1) {
			errors.add("Fullname", new ActionMessage("error.fullname.required"));
			// TODO: add 'error.name.required' key to your resources
		}

		if (getPassword() == null || getPassword().length() < 1) {
			errors.add("Password", new ActionMessage("error.password.required"));
			// TODO: add 'error.name.required' key to your resources
		}

		if (getRetypepassword() == null || getRetypepassword().length() < 1) {
			errors.add("Retypepassword", new ActionMessage(
					"error.retypepassword.required"));
			// TODO: add 'error.name.required' key to your resources
		}

		// if(getiTypeAction() == null)
		// {
		// errors.add("Retypepassword", new
		// ActionMessage("error.retypepassword.required"));
		// }
		String page_pos = request.getParameter("page_pos");
		UserDao usertdao = new UserDao();
		List<User> list_user = usertdao.getUserListFilter(
				Integer.parseInt(page_pos), "groupID==" + getGroupCode(),
				"id desc");
		request.setAttribute(Constant.ACCOUNT_LIST, list_user);
		request.setAttribute("groupID", String.valueOf(getGroupCode()));
		request.setAttribute("page_pos", Integer.parseInt(page_pos));

		int count = 0;
		try {
			count = PMF.countNumberAll(Class.forName("sp.dto.User"),
					"groupID==" + getGroupCode());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int countpage = (count < Constant.RECORD ? 1
				: (count % Constant.RECORD == 0 ? count / Constant.RECORD
						: count / Constant.RECORD + 1));

		request.setAttribute("PAGE", countpage);
		return errors;
	}

	/*
	 * transfer Form -> DTO
	 */
	public User getUser() {
		User user = new User();
		user.setEmail(this.email.trim());
		user.setIdPermision(this.permission);
		user.setPassword(this.password);
		user.setStatusLogin(false);
		user.setName(this.fullname);
		user.setGroupID(this.groupCode);
		return user;
	}

	/*
	 * transfer DTO -> Form
	 */
	public AccountForm(User user) {
		this.email = user.getEmail().trim();
		this.permission = user.getIdPermision();
		this.password = user.getPassword();
		this.fullname = user.getName();
		this.groupCode = user.getGroupID();
	}

}
