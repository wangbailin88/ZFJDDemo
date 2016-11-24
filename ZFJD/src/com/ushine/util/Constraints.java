package com.ushine.util;

import java.util.HashMap;
import java.util.Map;

public class Constraints {
	
	/** 默认公司表名前缀 YJ_POSTINFO_ */
	public static final String YJ_POSTINFO = "YJ_POSTINFO_";

	// ======================= Vic add 20130607 ======================
	/** 描述新修改的用于滚动翻页的每个PageModel一次最大持有的数据量，查询的步长 500 */
	public static final int PAGEMODEL_STEP = 500;
	/** 第 1 个查询PageModel的序号 0 */
	public static final int PAGEMODEL_prev = 0;
	/** 第 2 个查询PageModel的序号 1 */
	public static final int PAGEMODEL_curr = 1;
	/** 第 3 个查询PageModel的序号 2 */
	public static final int PAGEMODEL_next = 2;

	// >>> 滚一滚文件记录滚动方式
	/** GYG_AUTO 自动滚动 */
	public static final String GYG_AUTO = "1";
	/** GYG_USER_OPER用户手动执行滚动 */
	public static final String GYG_USER_OPER = "0";

	/** GYG_TASK_NEW_LJ 新滚一滚任务记录-立即执行的任务 */
	public static final Integer GYG_TASK_NEW_LJ = 0;

	/** GYG_TASK_NEW_DS 新滚一滚任务记录-定时执行的任务 */
	public static final Integer GYG_TASK_NEW_DS = 1;

	/** GYG_TASK_DOING 正在执行滚一滚的任务记录 */
	public static final Integer GYG_TASK_DOING = 10;

	/** GYG_TASK_DONE 已经完成的滚一滚任务记录 */
	public static final Integer GYG_TASK_DONE = 20;

	/** GYG_DATA_ALL 滚动所有的黑名单记录 */
	public static final String GYG_DATA_ALL = "GYG_DATA_ALL";
	// <<< 滚一滚文件记录滚动方式
	// ======================= Vic add 20130607 ======================

	/**
	 * POSTINFO GRID 配置 userfav
	 */
	public static final String USER_FAV_POSTINFO_GRID = "postinfoGrid";
	public static final String USER_FAV_YUJINGINFO_GRID = "yujinginfoGrid";

	// 默认每页显示五条数据
	public static int pageSize = 20;

	public static String SORT_DESC = "DESC";

	public static String SORT_ASC = "ASC";

	public static String USER_SESSION_NAME = "user";

	public static String SOFTWARE_VERSION = "version";

	public static String SESSION_PAGESIZE = "pageSize";

	public static String LOGIN_ERROR_INFO_1 = "没有此用户!";

	public static String LOGIN_ERROR_INFO_2 = "密码错误!";

	public static String STRING_SPLIT_FLAG = ", ，";
	// 保存条件的临界值
	public static int MESSION_CONDITION_LENGTH = 1800;

	public static String MESSION_CONDITION_FLAG = "MC";

	// 检索任务
	public static final String MESSION_TYPE_INFO = "1";
	// 分析任务
	public static final String MESSION_TYPE_ANLIS = "2";
	// 报警任务
	public static final String MESSION_TYPE_ALRM = "3";
	// 统计任务
	public static final String MESSION_TYPE_TONGJI = "4";
	// 任务已读标识
	public static final String MESSION_READ_READED = "1";
	// 任务已读标识
	public static final String MESSION_READ_NOREAD = "0";
	// 检索任务案件查办类型
	public static final String MESSION_INFO_ANJIANCHABAN = "案件查办";
	// 任务运行Flag一运行
	public static final String MESSION_RUNFLAG_RUNED = "1";
	// 任务运行Flag不运行
	public static final String MESSION_RUNFLAG_STOPED = "0";
	// 任务
    
	//查询数据的总记录数
	public static final Long POST_NUM = 1000L;
	//分页模式的页面大小
	public static final Long POST_NUM_PAGESIZE = 100L;
	/**
	 * 中间库分组
	 */
	public static final String GROUP_TYPE_MT = "0";
	/**
	 * 检索分组
	 */
	public static final String GROUP_TYPE_INFO = "1";
	/**
	 * 分析分组
	 */
	public static final String GROUP_TYPE_ANLIS = "2";
	/**
	 * 报警分组
	 */
	public static final String GROUP_TYPE_ALRM = "3";
	/**
	 * 统计分组
	 */
	public static final String GROUP_TYPE_TONGJI = "4";
	/**
	 * 结果集分组
	 */
	public static final String GROUP_TYPE_RESULT = "5";

	// 预警级别关注
	public static final String ALRMLEVEL_GUANZHU = "2";
	// 预警级别已抓捕
	public static final String ALRMLEVEL_ZHUABU = "1";
	// 预警级别排除
	public static final String ALRMLEVEL_PAICHU = "3";
	// 预警级别线索积累
	public static final String ALRMLEVEL_XIANSUOJILEI = "4";

	// 批量上传最大上传数目限制
	public static final int BATCH_UPLOAD_INFO = 200;

	public static final Map<String, String> QUERY_SIGNS = new HashMap<String, String>();

	static {
		QUERY_SIGNS.put("0", "查看");
		QUERY_SIGNS.put("1", "查询");
		QUERY_SIGNS.put("2", "登录");
		QUERY_SIGNS.put("3", "登出");
		QUERY_SIGNS.put("4", "新增");
		QUERY_SIGNS.put("5", "修改");
		QUERY_SIGNS.put("6", "删除");
	}

	/**
	 * 信息检索排序依据
	 * 
	 * @param sortString
	 * @return
	 */
	public static String getSortRule(String sortString) {

		return " nlssort(" + sortString + ",'NLS_SORT=SCHINESE_PINYIN_M') ";
	}

	// 每页显示条数常量
	public static Map<String, String> getConstantsMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("pageSize", pageSize + "");
		return map;
	}

	public static String turnMyPlatform(String ss) {
		String result = "我的全部任务";
		if ("0".equals(ss)) {
			result = "我的全部任务";
		}
		if ("1".equals(ss)) {
			result = "我的检索任务";
		}
		if ("2".equals(ss)) {
			result = "我的分析任务";

		}
		if ("3".equals(ss)) {
			result = "我的预警任务";
		}
		if ("4".equals(ss)) {
			result = "我的统计任务";
		}
		return result;
	}

	public static String turnMyResult(String ss) {
		String result = "我的检索结果集";

		if ("1".equals(ss)) {
			result = "我的检索结果集";
		}
		if ("2".equals(ss)) {
			result = "我的分析结果集";

		}
		if ("3".equals(ss)) {
			result = "我的预警结果集";
		}
		if ("4".equals(ss)) {
			result = "我的统计结果集";
		}
		return result;
	}

	/**
	 * 返回任务级别
	 * 
	 * @param str
	 * @return
	 */
	public static String turnMessionLevel(String str) {
		String result = "";
		if (str.equals("1")) {
			result = "重要";
		}
		if (str.equals("2")) {
			result = "一般";
		}
		if (str.equals("3")) {
			result = "巡检";
		}
		return result;
	}

	public static String getStateJson2() {
		return "#{'1':'可用','0':'不可用'}";
	}

	// 是否常量
	public static Map<String, String> getYesOrNoJson() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "是");
		map.put("0", "否");
		return map;
	}

	// 状态常量
	public static Map<String, String> getStateJson() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "可用");
		map.put("0", "不可用");
		return map;
	}

	// 共享级别常量
	public static Map<String, String> getShareJson() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "一级共享");
		map.put("2", "二级共享");
		map.put("3", "三级共享");
		return map;
	}

	// 预警级别常量
	public static Map<String, String> getAlarmJson() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "一级预警");
		map.put("2", "二级预警");
		map.put("3", "三级预警");
		return map;
	}

	// 性别
	public static Map<String, String> getSexJson() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "男");
		map.put("2", "女");
		map.put("3", "保密");
		return map;
	}

	public static int getPageSize() {
		return pageSize;
	}

	public static void setPageSize(int pageSize) {
		Constraints.pageSize = pageSize;
	}

	// 我的工作台任务类型
	public static Map<String, String> getMessionJson() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "检索任务");
		map.put("2", "分析任务");
		map.put("3", "预警任务");
		map.put("4", "统计任务");
		return map;
	}

	// 我的工作台任务级别
	public static Map<String, String> getMessionGrade() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "紧急");
		map.put("2", "重要");
		map.put("3", "一般");
		return map;
	}

	// 我的工作台是否已读
	public static Map<String, String> getIsRead() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("0", "未读");
		map.put("1", "已读");
		return map;
	}

	public static String viewnull(String ss) {
		String result = "";
		if (ss == null) {

		} else {
			result = ss.trim();
		}
		return result;
	}

	/**
	 * 操作类型 新增
	 */
	public static final String LOG_OPERATION_TYPE_CREATE = "新增";

	/**
	 * 操作类型 查找
	 */
	public static final String LOG_OPERATION_TYPE_QUERY = "查询";

	/**
	 * 操作类型 删除
	 */
	public static final String LOG_OPERATION_TYPE_DELETE = "删除";

	/**
	 * 操作类型 更新
	 */
	public static final String LOG_OPERATION_TYPE_UPDATE = "修改";

	/**
	 * 操作类型 登录
	 */
	public static final String LOG_OPERATION_TYPE_LOGIN = "登录";

	/**
	 * 操作类型 登出
	 */
	public static final String LOG_OPERATION_TYPE_LOGOUT = "登出";

	/**
	 * 操作结果 成功
	 */
	public static final String LOG_OPERATION_RESULT_SUCCESS = "成功";

	/**
	 * 操作结果 失败
	 */
	public static final String LOG_OPERATION_RESULT_FAILURE = "失败";

	/**
	 * 操作内容 查询操作日志
	 */
	public static final String LOG_OPERATION_CONTENT_LOG_QUERY = "查询操作日志";

	/**
	 * 操作内容 查看操作日志
	 */
	public static final String LOG_OPERATION_CONTENT_LOG_LOOK = "查看操作日志";
	/**
	 * 操作内容 查看数据导入记录
	 */
	public static final String LOG_OPERATION_CONTENT_FILE_RECORD_LOOK = " 查看数据导入记录";

	/**
	 * 操作内容 查询我的任务
	 */
	public static final String LOG_OPERATION_CONTENT_MESSION_QUERY = "查询我的任务";

	/**
	 * 操作内容 查看我的任务
	 */
	public static final String LOG_OPERATION_CONTENT_MESSION_LOOK = "查看我的任务";

	/**
	 * 操作内容 新增我的任务信息
	 */
	public static final String LOG_OPERATION_CONTENT_MESSION_ADD = "新增我的任务信息";

	/**
	 * 操作内容 删除我的任务信息
	 */
	public static final String LOG_OPERATION_CONTENT_MESSION_DELETE = "删除我的任务信息";

	/**
	 * 操作内容 修改我的任务信息
	 */
	public static final String LOG_OPERATION_CONTENT_MESSION_MODIFY = "修改我的任务信息";

	/**
	 * 操作内容 查询公司信息
	 */
	public static final String LOG_OPERATION_CONTENT_COMPANY_QUERY = "查询公司信息";

	/**
	 * 操作内容 查看公司信息
	 */
	public static final String LOG_OPERATION_CONTENT_COMPANY_LOOK = "查看公司信息";

	/**
	 * 操作内容 新增公司信息
	 */
	public static final String LOG_OPERATION_CONTENT_COMPANY_ADD = "新增公司信息";

	/**
	 * 操作内容 删除公司信息
	 */
	public static final String LOG_OPERATION_CONTENT_COMPANY_DELETE = "删除公司信息";

	/**
	 * 操作内容 修改公司信息
	 */
	public static final String LOG_OPERATION_CONTENT_COMPANY_MODIFY = "修改公司信息";

	/**
	 * 操作内容 查询点部信息
	 */
	public static final String LOG_OPERATION_CONTENT_DEPARTMENT_QUERY = "查询点部信息";

	/**
	 * 操作内容 查看点部信息
	 */
	public static final String LOG_OPERATION_CONTENT_DEPARTMENT_LOOK = "查看点部信息";

	/**
	 * 操作内容 新增点部信息
	 */
	public static final String LOG_OPERATION_CONTENT_DEPARTMENT_ADD = "新增点部信息";

	/**
	 * 操作内容 删除点部信息
	 */
	public static final String LOG_OPERATION_CONTENT_DEPARTMENT_DELETE = "删除点部信息";

	/**
	 * 操作内容 修改点部信息
	 */
	public static final String LOG_OPERATION_CONTENT_DEPARTMENT_MODIFY = "修改点部信息";

	/**
	 * 操作内容 查询黑名单库信息
	 */
	public static final String LOG_OPERATION_CONTENT_BLACKBANK_QUERY = "查询黑名单库信息";

	/**
	 * 操作内容 查看黑名单库信息
	 */
	public static final String LOG_OPERATION_CONTENT_BLACKBANK_LOOK = "查看黑名单库信息";

	/**
	 * 操作内容 新增黑名单库信息
	 */
	public static final String LOG_OPERATION_CONTENT_BLACKBANK_ADD = "新增黑名单库信息";

	/**
	 * 操作内容 删除黑名单库信息
	 */
	public static final String LOG_OPERATION_CONTENT_BLACKBANK_DELETE = "删除黑名单库信息";

	/**
	 * 操作内容 修改黑名单库信息
	 */
	public static final String LOG_OPERATION_CONTENT_BLACKBANK_MODIFY = "修改黑名单库信息";

	/**
	 * 操作内容 查询黑名单列表信息
	 */
	public static final String LOG_OPERATION_CONTENT_BLACKLIST_QUERY = "查询黑名单列表信息";

	/**
	 * 操作内容 查看黑名单列表信息
	 */
	public static final String LOG_OPERATION_CONTENT_BLACKLIST_LOOK = "查看黑名单列表信息";

	/**
	 * 操作内容 新增黑名单列表信息
	 */
	public static final String LOG_OPERATION_CONTENT_BLACKLIST_ADD = "新增黑名单列表信息";

	/**
	 * 操作内容 删除黑名单列表信息
	 */
	public static final String LOG_OPERATION_CONTENT_BLACKLIST_DELETE = "删除黑名单列表信息";

	/**
	 * 操作内容 修改黑名单列表信息
	 */
	public static final String LOG_OPERATION_CONTENT_BLACKLIST_MODIFY = "修改黑名单列表信息";

	/**
	 * 操作内容 查询中间库信息
	 */
	public static final String LOG_OPERATION_CONTENT_MIDTABLE_QUERY = "查询中间库信息";

	/**
	 * 操作内容 查看中间库信息
	 */
	public static final String LOG_OPERATION_CONTENT_MIDTABLE_LOOK = "查看中间库信息";

	/**
	 * 操作内容 新增中间库信息
	 */
	public static final String LOG_OPERATION_CONTENT_MIDTABLE_ADD = "新增中间库信息";

	/**
	 * 操作内容 删除中间库信息
	 */
	public static final String LOG_OPERATION_CONTENT_MIDTABLE_DELETE = "删除中间库信息";

	/**
	 * 操作内容 修改中间库信息
	 */
	public static final String LOG_OPERATION_CONTENT_MIDTABLE_MODIFY = "修改中间库信息";

	/**
	 * 操作内容 查询用户信息
	 */
	public static final String LOG_OPERATION_CONTENT_USER_QUERY = "查询用户信息";

	/**
	 * 操作内容 查看用户信息
	 */
	public static final String LOG_OPERATION_CONTENT_USER_LOOK = "查看用户信息";

	/**
	 * 操作内容 新增用户信息
	 */
	public static final String LOG_OPERATION_CONTENT_USER_ADD = "新增用户信息";

	/**
	 * 操作内容 删除用户信息
	 */
	public static final String LOG_OPERATION_CONTENT_USER_DELETE = "删除用户信息";

	/**
	 * 操作内容 修改用户信息
	 */
	public static final String LOG_OPERATION_CONTENT_USER_MODIFY = "修改用户信息";

	/**
	 * 操作内容 查询角色信息
	 */
	public static final String LOG_OPERATION_CONTENT_ROLE_QUERY = "查询角色信息";

	/**
	 * 操作内容 查看角色信息
	 */
	public static final String LOG_OPERATION_CONTENT_ROLE_LOOK = "查看角色信息";

	/**
	 * 操作内容 新增角色信息
	 */
	public static final String LOG_OPERATION_CONTENT_ROLE_ADD = "新增角色信息";

	/**
	 * 操作内容 删除角色信息
	 */
	public static final String LOG_OPERATION_CONTENT_ROLE_DELETE = "删除角色信息";

	/**
	 * 操作内容 修改角色信息
	 */
	public static final String LOG_OPERATION_CONTENT_ROLE_MODIFY = "修改角色信息";

	/**
	 * 操作内容 查看报警布控信息
	 */
	public static final String LOG_OPERATION_CONTENT_ALARM_QUERY = "查看报警布控信息";

	/**
	 * 操作内容 新增报警布控信息
	 */
	public static final String LOG_OPERATION_CONTENT_ALARM_ADD = "新增报警布控信息";

	/**
	 * 操作内容 删除报警布控信息
	 */
	public static final String LOG_OPERATION_CONTENT_ALARM_DELETE = "删除报警布控信息";

	/**
	 * 操作内容 修改报警布控信息
	 */
	public static final String LOG_OPERATION_CONTENT_ALARM_MODIFY = "修改报警布控信息";

	/**
	 * 操作内容 查询权限信息
	 */
	public static final String LOG_OPERATION_CONTENT_POWER_QUERY = "查询权限信息";

	/**
	 * 操作内容 查看权限信息
	 */
	public static final String LOG_OPERATION_CONTENT_POWER_LOOK = "查看权限信息";

	/**
	 * 操作内容 新增权限信息
	 */
	public static final String LOG_OPERATION_CONTENT_POWER_ADD = "新增权限信息";

	/**
	 * 操作内容 删除权限信息
	 */
	public static final String LOG_OPERATION_CONTENT_POWER_DELETE = "删除权限信息";

	/**
	 * 操作内容 修改权限信息
	 */
	public static final String LOG_OPERATION_CONTENT_POWER_MODIFY = "修改权限信息";

	/**
	 * 操作内容 查询权限信息
	 */
	public static final String LOG_OPERATION_CONTENT_POSTINFO_QUERY = "查询postinfo信息";

	/**
	 * 操作内容 新增信息检索任务
	 */
	public static final String LOG_OPERATION_CONTENT_POSTINFO_ADD = "新增信息检索任务";

	/**
	 * 操作内容 删除信息检索
	 */
	public static final String LOG_OPERATION_CONTENT_POSTINFO_DELETE = "删除信息检索任务";

	/**
	 * 操作内容 修改信息检索
	 */
	public static final String LOG_OPERATION_CONTENT_POSTINFO_MODIFY = "修改信息检索任务";

	/**
	 * 操作内容 查看数据分析信息
	 */
	public static final String LOG_OPERATION_CONTENT_ANALYSIS_LOOK = "查看数据分析信息";

	/**
	 * 操作内容 查询数据分析信息
	 */
	public static final String LOG_OPERATION_CONTENT_ANALYSIS_QUERY = "查询数据分析任务";
	/**
	 * 操作内容 复制数据分析信息
	 */
	public static final String LOG_OPERATION_CONTENT_ANALYSIS_COPY = "复制数据分析任务";
	/**
	 * 操作内容 创建数据分析任务模板
	 */
	public static final String LOG_OPERATION_CONTENT_ANALYSIS_COPY_TPL = "创建数据分析任务模板";
	/**
	 * 操作内容 新增数据分析信息
	 */
	public static final String LOG_OPERATION_CONTENT_ANALYSIS_ADD = "新增数据分析任务";

	/**
	 * 操作内容 删除数据分析信息
	 */
	public static final String LOG_OPERATION_CONTENT_ANALYSIS_DELETE = "删除数据分析信息";

	/**
	 * 操作内容 修改数据分析信息
	 */
	public static final String LOG_OPERATION_CONTENT_ANALYSIS_MODIFY = "修改数据分析信息";

	/**
	 * 操作内容 查看数据分析步骤信息
	 */
	public static final String LOG_OPERATION_CONTENT_ANALYSIS_STEP_QUERY = "查看数据分析步骤";

	/**
	 * 操作内容 查看数据分析步骤结果
	 */
	public static final String LOG_OPERATION_CONTENT_ANALYSIS_STEP_RESULT = "查看数据分析步骤结果";

	/**
	 * 操作内容 查看数据分析中间库数据
	 */
	public static final String LOG_OPERATION_CONTENT_ANALYSIS_MIDTB_QUERY = "查看数据分析中间库数据";

	/**
	 * 操作内容 删除数据分析步骤结果数据
	 */
	public static final String LOG_OPERATION_CONTENT_ANALYSIS_STEP_RESULT_DELETE = "删除分析步骤结果数据";

	/**
	 * 操作内容 新增数据分析步骤信息
	 */
	public static final String LOG_OPERATION_CONTENT_ANALYSIS_STEP_ADD = "新增数据分析步骤";

	/**
	 * 操作内容 删除数据分析步骤信息
	 */
	public static final String LOG_OPERATION_CONTENT_ANALYSIS_STEP_DELETE = "删除数据分析步骤";

	/**
	 * 操作内容 修改数据分析步骤信息
	 */
	public static final String LOG_OPERATION_CONTENT_ANALYSIS_STEP_MODIFY = "修改数据分析步骤";

	/**
	 * 操作内容 刷新预警，任务信息
	 */
	public static final String LOG_OPERATION_CONTENT_MESSION_FLUSH = "刷新预警，任务信息";

	public static final String LOG_OPERATION_CONTENT_ANALYSIS_STEP_RESULT_RECOVER = "恢复数据分析数据";

	//上家查询参数
	public static final String PHONE_QUERY_TYPE_SHANGJIA = "1";
	//下家查询参数
	public static final String PHONE_QUERY_TYPE_XIAJIA = "2";
	
	
	/**
	 * 自提
	 */
	public static int since_lifting_members_0 = 0;
	/**
	 * 非自提
	 */
	public static int since_lifting_members_1 = 1;
	/**
	 * 自送件
	 */
	public static int since_delivery_0 = 0;
	/**
	 * 非自送件
	 */
	public static int since_delivery_1 = 1;
	/**
	 * 保价
	 */
	public static int insured_0 = 0;
	/**
	 * 非保价
	 */
	public static int insured_1 = 1;
	/**
	 * 执行结果 success 成功，error 错误，starts 开始
	 */
	public static String success = "success";
	public static String error = "error";
	public static String starts = "starts";

	public static String fieldValueComparisonImpl_type = "1";
	public static String sameDifferentImpl_type = "2";
	public static String screeningConditionsImpl_type = "3";
	public static String fieldCountImpl_type = "4";
	public static String functionOperationImpl_type = "5";
	public static String keywordAlgorithmImpl_type = "6";
	public static String dataCountImpl_type = "7";

	/**
	 * 0:新任务 1：异常任务 2：完成任务 3：正在执行的任务
	 */
	public static String newTask = "0";
	public static String expTask = "1";
	public static String okTask = "2";
	public static String openingTask = "3";
	
	/**
	 * 字段 
	 */
	public static String filed = "1";
	/**
	 * 运算符
	 */
	public static String operator = "2";
	/**
	 * 常量
	 */
	public static String constan = "3";
	/**
	 * 函数
	 */
	public static String function = "4";
	/**
	 * 值库
	 */
	public static String value = "5";
}
