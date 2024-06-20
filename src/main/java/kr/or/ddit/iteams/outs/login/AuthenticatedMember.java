package kr.or.ddit.iteams.outs.login;

import java.security.Principal;
import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import kr.or.ddit.iteams.common.vo.MasterVO;

public class AuthenticatedMember implements Authentication{
	
	private MasterVO masterVO;
	private boolean auth = false;
	
	public AuthenticatedMember(MasterVO masterVO) {
		this.masterVO = masterVO;
	}

	@Override
	public String getName() {
		return masterVO.getMemId();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.createAuthorityList(masterVO.getMemRole());
	}

	@Override
	public Object getCredentials() {
		return masterVO.getMemPass();
	}

	@Override
	public MasterVO getDetails() {
		return masterVO;
	}

	@Override
	public Object getPrincipal() {
		return masterVO;
	}

	@Override
	public boolean isAuthenticated() {
		return auth;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		auth = isAuthenticated;
	}

	

}
