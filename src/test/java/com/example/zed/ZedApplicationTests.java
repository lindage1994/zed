package com.example.zed;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZedApplicationTests {

	@Test
	public void contextLoads() {
		Map<String,String> map =  new HashMap<>();
		map.size();
	}

}
