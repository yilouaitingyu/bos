package cn.huchao.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

/**
 * @author huchao
 * @2017年7月14日
 * @description 集合工具类
 */
public class ListUtil {
	/**
	 * @description 将一个List<Map>中某一个键对应的值，全取出来，形成List集合，当没有这个字段，或者取出为空，则不添加
	 * @param mapList
	 * @param key
	 * @return
	 * @2017年7月6日
	 * @author huchao
	 */
	@SuppressWarnings("rawtypes")
	public static List<String> getValuesList(List<Map> mapList, String key) {
		List<String> resultList = new ArrayList<String>();
		if (CollectionUtils.isNotEmpty(mapList)) {
			for (Map map : mapList) {
				if (StringUtil.isNotEmpty(map.get(key))) {
					resultList.add(StringUtil.clearBlank(map.get(key)));
				}
			}
		}
		return resultList;
	}

	/**
	 * @description 根据sign从字典表中获取的List<Map>，如果code对上，则取value对应的值形成List集合
	 * @param dictMapList
	 * @param key
	 * @return
	 * @2017年7月6日
	 * @author huchao
	 */
	@SuppressWarnings("rawtypes")
	public static List<String> getDictList(List<Map<String, Object>> dictMapList, String key) {
		List<String> resultList = new ArrayList<String>();
		if (CollectionUtils.isNotEmpty(dictMapList)) {
			for (Map map : dictMapList) {
				if (StringUtil.isNotEmpty(key)) {
					if (key.equals(map.get("code"))) {
						resultList.add(StringUtil.clearBlank(map.get("value")));
					}
				}
			}
		}
		return resultList;
	}

	/**
	 * @description 获取两个List的不同元素
	 * @param list1
	 * @param list2
	 * @return
	 * @2017年7月12日
	 * @author huchao
	 */
	public static List<String> getDiffList(List<String> list1, List<String> list2) {
		// long st = System.nanoTime();
		Map<String, Integer> map = new HashMap<String, Integer>(list1.size() + list2.size());
		List<String> diff = new ArrayList<String>();
		for (String string : list1) {
			map.put(string, 1);
		}
		for (String string : list2) {
			Integer cc = map.get(string);
			if (cc != null) {
				map.put(string, ++cc);
				continue;
			}
			map.put(string, 1);
		}
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			if (entry.getValue() == 1) {
				diff.add(entry.getKey());
			}
		}
		// System.out.println("getDiffrent3 total times " + (System.nanoTime() -
		// st));
		return diff;
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
			map.put(str, map.get(str));
		}
		return params;
	}

	public static void main(String[] args) {

		/*
		 * List<String> list1 = new ArrayList<>(); list1.add("1");
		 * list1.add("2"); list1.add("3"); list1.add("4"); list1.add("5");
		 * List<String> list2 = new ArrayList<>(); list2.add("3");
		 * list2.add("4"); list2.add("5"); list2.add("6"); list2.add("7");
		 * List<String> diffrent3 = getDiffList(list1, list2);
		 * System.out.println(diffrent3);
		 */

	}
}
