import org.apache.coyote.http11.filters.VoidInputFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zs.pms.po.TUser;

import redis.clients.jedis.Jedis;

@RunWith(SpringJUnit4ClassRunner.class) // 使用spring测试框架
@ContextConfiguration("classpath:applicationcontext.xml") // 引入配置文件
public class TestRedis {
	@Autowired
	RedisTemplate  rs;

	public void testGo() {
		// 创建jedis客户端 ip+port
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		// jedis.set("J1", "你好");
		System.out.println(jedis.get("J1"));

		// 存对象
		TUser user = new TUser();
		user.setRealname("关羽");
		// 不能存储对象
		// jedis.set("tank", user);

	}

	@Test
	public void testObj() {
		// 存对象
		TUser user = new TUser();
		user.setId(1000);
		user.setRealname("关羽");
		//设置对象
		rs.opsForValue().set("tank", user);
		
		TUser user2=(TUser)rs.opsForValue().get("tank");
		System.out.println(user2.getRealname());
	}

}
