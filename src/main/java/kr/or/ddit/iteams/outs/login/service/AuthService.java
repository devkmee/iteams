package kr.or.ddit.iteams.outs.login.service;

import java.lang.reflect.InvocationTargetException;

import kr.or.ddit.iteams.common.vo.MasterVO;

public interface AuthService {
	
	/**
	 * 로그인 진행(회원 기본정보, 클라이언트 정보, 개발자 정보 전부 포함해서 MasterVO에 넣어서 나옴)
	 * @param masterVO
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public void selectForLogin(MasterVO masterVO) throws IllegalAccessException, InvocationTargetException;

}
