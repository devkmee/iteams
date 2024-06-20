package kr.or.ddit.iteams.common.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.view.AbstractView;

import kr.or.ddit.iteams.common.FTPServerFileManager;
import kr.or.ddit.iteams.common.vo.AttachTotalVO;
import kr.or.ddit.iteams.common.vo.MasterVO;

public class ImageRenderingView extends AbstractView {

	@Inject
	private FTPServerFileManager manager;
	
	@Value("#{appInfo.profileAttachPath}")
	private String profilePath;
	@Value("#{appInfo.defaultProfileName}")
	private String defaultProfile;
	
	@Value("#{appInfo.profileAttachInnerServerPath}")
	private String profileServerUrl;
	
	@Inject
	private WebApplicationContext context;

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse resp) throws Exception {
		MasterVO masterVO = (MasterVO) model.get("masterVO");
		AttachTotalVO attach = masterVO.getAttachVO();
		File profileFolder = null;
		File savedFile = null;
		String realPath = context.getServletContext().getRealPath(profileServerUrl);
		
		if (attach != null) {
			profileFolder = new File(realPath);
			if(!profileFolder.exists()) {
				profileFolder.mkdirs();
			}
			savedFile = new File(profileFolder, attach.getAttachName());
			if(!savedFile.exists()) {
				savedFile = new File(realPath, defaultProfile);
			}
		} else {
			savedFile = new File(realPath, defaultProfile);
			if(!savedFile.exists()) {
				savedFile.mkdirs();
			}
		}

		try (InputStream is = new FileInputStream(savedFile); 
				OutputStream os = resp.getOutputStream();) {
			int c = 0;
			while ((c = is.read()) != -1) {
				os.write(c);
			}
			os.flush();
		}

	}

}
