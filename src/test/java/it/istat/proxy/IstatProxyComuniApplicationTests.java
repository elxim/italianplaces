package it.istat.proxy;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.istat.proxy.storage.Comuni;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IstatProxyComuniApplicationTests {

	
	@Autowired
	private Comuni comuni;
	
	@Test
	public void contextLoads() {
	}
	
	
	@Test
	public void comuniLoads() {
		
		assertTrue(comuni.isLoaded());
		
	}
	
}
