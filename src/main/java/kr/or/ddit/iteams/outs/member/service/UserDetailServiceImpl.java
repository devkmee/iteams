package kr.or.ddit.iteams.outs.member.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.login.dao.AuthDAO;
import kr.or.ddit.iteams.outs.member.dao.MemberDAO;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import kr.or.ddit.vo.MasterVOWrapper;

@Service("userDetailService")
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {
	
	@Inject
	private AuthDAO dao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		MasterVO member = dao.selectMemberForAuth(username);
		
		if(member==null)
			throw new UsernameNotFoundException(username+"에 해당하는 사용자가 없음.");
		
		MasterVO authMember = null;
		List<String> authList = new ArrayList<>();
		
		authMember = dao.selectMemberForPMSAuth(member);
		
		if(authMember == null) {
			authMember = dao.selectForLogin(member);
			authList.add(authMember.getMemRole());
		} else {
			authList.add(authMember.getMemRole());
			authList.add(authMember.getAuthority());
		}
		
		authMember.setAuthorityList(authList);
		
		try {
			BeanUtils.copyProperties(member, authMember);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		
//		log.info("소속 프로젝트 번호 리스트 : {}", member.getProNoList().toString());
		MasterVOWrapper masterVOWrapper = new MasterVOWrapper(member);
		Collection<GrantedAuthority> authMemberAuth = masterVOWrapper.getAuthorities();
		log.info("id : {} / password : {}, auth : {}", masterVOWrapper.getUsername(), masterVOWrapper.getPassword(), authMemberAuth);
		return masterVOWrapper;
	}

}
