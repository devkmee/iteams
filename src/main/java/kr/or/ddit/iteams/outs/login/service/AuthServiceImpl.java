package kr.or.ddit.iteams.outs.login.service;

import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.outs.login.AuthenticatedMember;
import kr.or.ddit.iteams.outs.login.dao.AuthDAO;

@Service
public class AuthServiceImpl implements AuthService {
	
	@Inject
	private AuthDAO dao;
	
	@Resource(name="passwordEncoder")
	private PasswordEncoder encoder;
	
//	@Inject
//	private SecurityContext context;
//	
//	@Inject
//	private SecurityContextHolder holder;
	
	@Override
	public void selectForLogin(MasterVO masterVO) throws IllegalAccessException, InvocationTargetException {
		MasterVO saved = dao.selectMemberForAuth(masterVO.getMemId());
		
		if(saved == null) {
			masterVO.setResult(ServiceResult.NOTEXIST);
			return;
		}
		
		if(encoder.matches(masterVO.getMemPass(),saved.getMemPass())) {
			MasterVO authMember = null;
			authMember = dao.selectMemberForPMSAuth(masterVO);
			if(authMember == null) {
				authMember = dao.selectForLogin(masterVO);
			}
			
			BeanUtils.copyProperties(masterVO, authMember);
//			Authentication authentication = new AuthenticatedMember(masterVO);
//			context.setAuthentication(authentication);
			masterVO.setResult(ServiceResult.OK);
			
		} else {
			masterVO.setResult(ServiceResult.INVALIDPASSWORD);
		}
	}

}
