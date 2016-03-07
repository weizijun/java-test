package spring.data.redis;

import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.support.collections.DefaultRedisSet;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = { 
		"classpath:/springDataRedis.xml"
		}) 
public class TestSpring {
	@Autowired
	private RedisTemplate<String, Student> redisTemplate;
	
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGet() {
//		String key = "spring";
//        ListOperations<String, String> lop = redisTemplate.opsForList();
////        RedisSerializer<String> serializer = new StringRedisSerializer();
////        redisTemplate.setKeySerializer(serializer);
////        redisTemplate.setValueSerializer(serializer);
// 
//        lop.leftPush(key, "aaa");
//        lop.leftPush(key, "bbb");
//        long size = lop.size(key); // rt.boundListOps(key).size();
  
		RedisSerializer<String> serializer = new StringRedisSerializer();
		RedisSerializer<Student> vs = new JacksonJsonRedisSerializer<Student>(Student.class);
		redisTemplate.setKeySerializer(serializer);
		redisTemplate.setValueSerializer(vs);
        Student student = new Student();
        student.setId("id");
        
        Set<Student> rSet = new DefaultRedisSet<Student>("test3" ,redisTemplate);
        rSet.add(student);
        
        student.setId("id2");
        rSet.add(student);
        
        student.setId("id3");
        rSet.add(student);
        
        for (Student inStudent : rSet) {
        	System.out.println(inStudent.getId());
        }
	}
	
}
