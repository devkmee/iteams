package kr.or.ddit.iteams.outs.login.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.vo.ComboVO;
import kr.or.ddit.iteams.outs.login.vo.JoinDevRequestVO;

public interface JoinService {
	public ServiceResult createMember(JoinDevRequestVO member) throws Exception;
	public ServiceResult createMemberCli(JoinDevRequestVO member) throws Exception;
	public int idCheck(String memId);
	public List<ComboVO> findTechCodeByKeyword(String keyword);
	public ServiceResult changePassword(JoinDevRequestVO params);
	/*public ServiceResult createClientMember(JoinDevRequestVO member);*/
}
