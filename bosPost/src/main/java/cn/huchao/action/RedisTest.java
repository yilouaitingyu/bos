package cn.huchao.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/*import cn.zifangsky.mapper.UsrUserMapper;
import cn.zifangsky.mapper.UsrUserRoleMapper;
import cn.zifangsky.model.UsrRole;
import cn.zifangsky.model.UsrUser;*/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/spring/spring.xml"})
public class RedisTest {

	/*@Autowired
	private UsrUserMapper userMapper;
	
	@Autowired
	private UsrUserRoleMapper userRoleMapper;*/
	
	@Resource(name="redisTemplate")
	private RedisTemplate<String, Object> redisTemplate;
	
	@Resource(name="stringRedisTemplate")
	private StringRedisTemplate stringRedisTemplate;
	
	/**
	 * ValueOperations、SetOperations、ZSetOperations
	 * ListOperations、HashOperations
	 */
	@Test
	public void testSaveAndGet(){
		/*UsrUser user = userMapper.selectByPrimaryKey(1);
		ValueOperations<String, Object> valueOperation = redisTemplate.opsForValue();
		valueOperation.set("user" + user.getId(), user);
		
		UsrUser result = (UsrUser) valueOperation.get("user" + user.getId());
		System.out.println("name: " + result.getUsername());*/
		
	}
	
	@Test
	public void testOpsForList(){
	/*	List<UsrRole> roles = userRoleMapper.selectRolesByUserId(1);
		ValueOperations<String, Object> opsForList = redisTemplate.opsForValue();
		
		opsForList.set("roles", roles);
		
		List<UsrRole> result = (List<UsrRole>) opsForList.get("roles");

		System.out.println(result.get(0).getRolename());*/
		
	}
	
	@Test
	public void testOpsForMap(){
		Map<String, String> score = new HashMap<>();
		score.put("stuA", "89");
		score.put("stuB", "63");
		score.put("stuC", "94");
		score.put("stuD", "21");
		
		
		ValueOperations<String, Object> opsForList = redisTemplate.opsForValue();
		
		opsForList.set("scores", score);
		
		Map<String, String> result = (Map<String, String>) opsForList.get("scores");

		System.out.println(result.get("stuA"));
		
	}
	
	/**
	 * BoundKeyOperations、BoundValueOperations、BoundSetOperations
	 * BoundListOperations、BoundSetOperations、BoundHashOperations
	 */
	@Test
	public void testBoundOperations(){
		BoundValueOperations<String, Object> boundValueOperations = redisTemplate.boundValueOps("BoundTest");
		//设置值
//		boundValueOperations.set("test12345");
		//设置过期时间
//		boundValueOperations.expire(100, TimeUnit.SECONDS);
		//重命名Key
//		boundValueOperations.rename("BoundTest123");
	
		System.out.println("key: " + boundValueOperations.getKey());
		System.out.println(boundValueOperations.get());
		System.out.println("expire: " + boundValueOperations.getExpire());
	}

}

