package cn.huchao.util;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huchao
 * @2017年9月4日
 * @description Map相关的方法的工具类
 */
public class MapUtil {
	private static final Logger logger = LoggerFactory.getLogger(MapUtil.class);

	/** 私有构造器 **/
	private MapUtil() {
	}

	/**
	 * @description 取第一个数组的值作为value，组成新的map，注意：当有多个值的时候会丢失值
	 * @return
	 * @2017年8月31日
	 * @author huchao
	 */
	public static Map<String, Object> getMapFromReq(Map<String, String[]> parameterMap) {
		Map<String, Object> map = new HashMap<>();
		for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
			map.put(entry.getKey(), StringUtil.clearBlank(entry.getValue()[0]));
		}
		return map;
	}

	/**
	 * @description 从一个map集合中，获取数组所代表的key对应的键值对，将其封装进一个新的集合中
	 * @param map
	 * @param strArr
	 * @return
	 * @2017年9月1日
	 * @author huchao
	 */
	public static Map<String, Object> getMapFromMapByArray(Map<String, Object> map, String[] strArr) {
		Map<String, Object> params = new HashMap<>();
		if (map == null) {
			return params;
		}
		for (String str : strArr) {
			if (map.get(str) != null) {
				map.put(str, map.get(str));
			}
		}
		return params;
	}
}
