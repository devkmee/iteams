package kr.or.ddit.vo;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import kr.or.ddit.iteams.common.vo.MasterVO;
import lombok.Getter;

@Getter
public class MasterVOWrapper extends User{
	private MasterVO authMember;

	public MasterVOWrapper(MasterVO authMember) {
		super(authMember.getMemId(), authMember.getMemPass(), 
					AuthorityUtils.commaSeparatedStringToAuthorityList(authMember.getAuthorityString() + ",IS_AUTHENTICATED_FULLY"));
		this.authMember = authMember;
	}

	

}
