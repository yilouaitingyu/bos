package cn.huchao.bean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.huchao.util.BeanUtil;




/**
 * @author huchao
 *	@2017年8月21日
 *	@description
 *	
 */
public class Entity implements Serializable{
	private static final long serialVersionUID = 7285580022695393203L;
	private static final Logger logger = LoggerFactory.getLogger(Entity.class);
	public String toString() {
		Map<String, Object> map = BeanUtil.convertBean2Map(this);
		return map.toString();
	}
	/**
	 * 深度克隆
	 * 
	 * @return 克隆的类
	 */
	@SuppressWarnings("unchecked")
	public <T> T deepClone() {
		try {
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			ObjectOutputStream oo = new ObjectOutputStream(bo);
			oo.writeObject(this);
			ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
			ObjectInputStream oi = new ObjectInputStream(bi);
			// Return Target Object
			return (T) oi.readObject();
		} catch (Exception e) {
			logger.error("deepClone", "", e);
			return null;
		}
	}
}

