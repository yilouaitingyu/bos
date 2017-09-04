package cn.huchao.util;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huchao
 * @2017年9月4日
 * @description 分页相关方法的工具类
 */
public class PageUtil {
	private static final Logger logger = LoggerFactory.getLogger(PageUtil.class);

	/** 私有构造器 **/
	private PageUtil() {
	}

	/**
	 * @description 页面分页条件转换为数据库需要的分页条件
	 * @param map
	 *            ，参数需要pageFlag，page第几页，pageSize每页多少行，使用默认的分页变量名
	 * @return，当分页失败时，会返回一个空的map集合
	 * @2017年9月4日
	 * @author huchao
	 */
	public static Map<String, Object> getPageCond(Map<String, Object> map) {
		Map<String, Object> pageCond = new HashMap<>();
		boolean pageFlag = false;// 默认不分页
		int startRow;// 开始行
		int pageSize;// 每页展示多少行
		if (StringUtil.isNotEmpty(map.get("pageFlag"))) {
			try {
				pageFlag = Boolean.parseBoolean(StringUtil.clearBlank(map.get("pageFlag")));
			} catch (Exception e) {
				logger.error("是否分页转换失败！", e);
				return new HashMap<>();//
			}
			pageCond.put("pageFlag", StringUtil.clearBlank(pageFlag));
		}
		if (pageFlag) {
			int page = Integer.parseInt(StringUtil.clearBlank(map.get("page")));// 第几页
			pageSize = Integer.parseInt(StringUtil.clearBlank(map.get("pageSize")));
			pageCond.put("pageSize", pageSize);// 每页数量
			if (page <= 0 || pageSize <= 0) {
				logger.error("分页信息错误！");
				return new HashMap<>();
			}
			startRow = (page - 1) * pageSize;// 开始行
			pageCond.put("startRow", startRow);
		}
		return pageCond;
	}

	/**
	 * @description 页面分页条件转换为数据库需要的分页条件
	 * @param map
	 *            ，参数名封装分页数组名需要，分页数组名表示分页数据的变量名，
	 *            数组的前三个变量为：是否分页，展示第几页，每页展示多少行
	 *            ,返回的分页参数变量名为默认的变量名，pageFlag是否分页，startRow开始行，pageSize每页多少行，
	 * @return，当分页失败时，会返回一个空的map集合
	 * @2017年9月4日
	 * @author huchao
	 */
	public static Map<String, Object> getPageCondByArr(Map<String, Object> map, String[] pageArr) {
		Map<String, Object> pageCond = new HashMap<>();
		boolean pageFlag = false;// 默认不分页
		int startRow;// 开始行
		int pageSize;// 每页展示多少行
		String str1 = pageArr[0];
		String str2 = pageArr[1];
		String str3 = pageArr[2];
		if (StringUtil.isNotEmpty(map.get(str1))) {
			try {
				pageFlag = Boolean.parseBoolean(StringUtil.clearBlank(map.get(str1)));
			} catch (Exception e) {
				logger.error("是否分页转换失败！", e);
				return new HashMap<>();//
			}
			pageCond.put("pageFlag", StringUtil.clearBlank(pageFlag));
		}
		if (pageFlag) {
			int page = Integer.parseInt(StringUtil.clearBlank(map.get(str2)));// 第几页
			pageSize = Integer.parseInt(StringUtil.clearBlank(map.get(str3)));
			if (page <= 0 || pageSize <= 0) {
				logger.error("分页信息错误！");
				return new HashMap<>();
			}
			pageCond.put("pageSize", pageSize);// 每页数量
			startRow = (page - 1) * pageSize;// 开始行
			pageCond.put("startRow", startRow);
		}
		return pageCond;
	}
}
