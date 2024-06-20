package kr.or.ddit.iteams.common.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import kr.or.ddit.iteams.common.FTPServerFileManager;
import kr.or.ddit.iteams.common.vo.AttachTotalVO;
import kr.or.ddit.iteams.common.vo.MasterVO;

public class DownloadView extends AbstractView{

	@Inject
	private FTPServerFileManager manager;
	
	private InputStream  is = null;
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse resp) throws Exception {
		MasterVO masterVO = (MasterVO) model.get("masterVO");
		AttachTotalVO attach = masterVO.getAttachVO();
		
		String fileName = URLEncoder.encode(attach.getAttachOrigin() , "UTF-8").replaceAll("\\+", " ");
		resp.setContentType("application/octet-stream");
		resp.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		resp.setHeader("Content-Length", attach.getAttachSize().toString());
		
		try (OutputStream os = resp.getOutputStream();){
			masterVO.setFileDownLoadOutputStream(os);
			manager.downLoadFileForPMS(masterVO);
			is = masterVO.getFileDownLoadInputStream();
			int c = 0;
			while((c = is.read()) != -1) {
				os.write(c);
			}
			os.flush();

		} finally {
			if(is != null) {
				is.close();
			}
		}
		
	}

}
