package kr.or.ddit.iteams.outs.login.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthServiceImplTest {
	
	

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		log.info(encoder.encode("1111"));
	}

}
