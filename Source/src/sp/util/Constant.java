package sp.util;

public class Constant {
	
	
//-- start Thuy --
	//COMMON
			public final static String SUCCESS ="success";
			public final static String FAILURE ="failure";
			public final static int RECORD =10;
			public final static String BLANK="";
			public final static String MODE_INSERT="1";
			public final static String MODE_UPDATE="2";
			public final static String OPEN= "Open";
			public final static String CLOSE= "Close";
			
			//SESSION
			public final static String RECORD_LIST ="record_list";
			public final static String RECORD_PAGE_LIST ="record_page_list";
			public final static String RECORD_PAGE_NUMBER ="record_page_number";
			public final static String RECORD_TOTAL_NUMBER ="record_total_number";
			public final static String RECORD_SORT ="record_sort";
			public final static String RECORD_FLAG="record_flag";
	//USER
		//PERMISION
		public final static int ADMIN_INT =1;
		public final static int PM_INT=2;
		public final static int LEADER_INT =3;
		public final static int EMPLOYEE_INT =4;

		//ORGANIZATION
		public final static String ORGANIZATION_LIST ="org_list";
		public final static String ORG_PAGE_LIST ="org_page_list";
		public final static String ORGANIZATION ="organization";
		public final static String ORG_PAGE_NUMBER ="org_page";
		public final static String ORG_TOTAL_NUMBER ="org_total_number";
		
	//REPORT
		//DEFINE REPORT_STATUS
		public final static String REPORT_NEW = "New"; //1
		public final static String REPORT_REVIEW = "Review"; //2
		public final static String REPORT_REQUEST_UPDATE = "Request_update"; //3
		public final static String REPORT_UPDATED = "Updated"; //4
		public final static String REPORT_FINISH = "Finish";   //5
		public final static String REPORT_CANCEL = "Cancel";   //6
		public final static String REPORT_CLOSE = "Close";     //0
		
		//SESSION
//		public final static String REPORT_LIST ="report_list";
		public final static String REPORT ="report";
//		public final static String REPORT_PAGE_LIST ="report_page_list";
//		public final static String REPORT_PAGE_NUMBER ="report_page_number";
//		public final static String REPORT_TOTAL_NUMBER ="report_total_number";
//		public final static String REPORT_SORT ="report_sort";
//		public final static String REPORT_FLAG="report_flag";
		public final static String REPORT_FILE_NAME="report_file_name";
		public final static String REPORT_FILE_ID="report_file_id";
	
		
		//sort
		public final static String IDPROJECTLIST ="idProList";
		public final static String IDREQLIST ="idReqList";
		public final static String IDGROUPLIST ="idGroupList";
		public final static String IDTASKLIST ="idTaskList";
		public final static String PERMISSION ="permission";
		
	//TASK
		//DEFINE TASK STATUS
		public final static String TASK_NEW ="New";
		public final static String TASK_DOING ="Doing";
		public final static String TASK_FINISH ="Finish";
		public final static String TASK_CANCEL="Cancel";
		public final static String TASK_CLOSE ="Close";
		
		//KIND TASK
		public final static String TASK_TEST="Test";
		public final static String TASK_TESTCASE="TestCase";
		public final static String TASK_USECASE="UseCase";
		public final static String TASK_BAN_GIAO="BanGiao";
		public final static String TASK_CODE="Code";
		
		//default: status sort
		public final static String DEFAULT_STATUS ="status != 'Close'";
		public final static String DEFAULT_STATUS_INT ="status != 0";
		
		//REQUIREMENT
	
		public final static String REQ_GROUP_FREE= "req_group_free";
		public final static String REQ ="req";
		
		//GROUP
		public final static String GROUP_FREE_REQ= "freeReq"; // duoc assign vo du an , nhung chua co req
		public final static String GROUP_ASSIGN_REQ= "assignReq"; // duoc assign vo du an, da co Req -> remind: khi update status thi phai update status cua Group
		
		//TASK
		public final static String USER_FREE_TASK= "freeTask"; // nhan vien chua co task
		public final static String USER_ASSIGN_TASK= "assignTask"; // nhan vien da duoc set task
		public final static String TASK_USER_FREE= "task_user_free";
		public final static String TASK= "task";
		
		//
		public final static String STATUS_LATE = "Đang trễ";
		public final static String STATUS_GOOD = "Tốt";
//-- end Thuy --	
		
	// Po
	//Account
	public final static String ACCOUNT_LIST ="account_list";
	//Po
	
	// Type Login
	public final static String GOOGLE_AUTHENTICATION ="GoogleAuthentication";
	public final static String MY_AUTHENTICATION ="MyAuthentication";
	public final static String Type_Login ="typeLogin";
	public final static String User_Login ="UserLogIN";
	
	// project, task, request
	public final static int STATUS_OPEN = 1;   
	public final static int STATUS_CLOSE = 0;
	public final static String PROJECT_LIST ="project_list";
	public final static String GROUP_LIST ="group_list";
	
	
}
