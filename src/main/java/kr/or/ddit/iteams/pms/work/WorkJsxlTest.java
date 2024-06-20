package kr.or.ddit.iteams.pms.work;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.pms.work.service.WorkService;
import net.sf.jxls.transformer.XLSTransformer;

@Controller
public class WorkJsxlTest {
	
	@Inject
	private WorkService service;
	
	private String sample = "D:/template/workTemplate.xlsx";
	
	@RequestMapping("/excelTest.do")
	public String doProcess(HttpServletResponse response) {
		
		Map<String, Object> model = new HashMap<>();
		MasterVO masterVO = new MasterVO();
		masterVO.setProNo("1");
		masterVO.setCurrentPage(1);
		masterVO.setTotalRecord(3);
		service.selectWorkList(masterVO);
		List<MasterVO> dataList = (List<MasterVO>) masterVO.getDataList();
		model.put("count", dataList.size());
		model.put("list", dataList);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		model.put("DownloadDate", sdf.format(new Date()));
		
		OutputStream os = null;
		InputStream is = null;

		try {
			String fileName = "excelTest";
			
			File file = new File(sample);
			is = new FileInputStream(file);
			
			response.setHeader("Content-Type", "application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");


			XLSTransformer transformer = new XLSTransformer();
//			Context context = new Context();
//			context.putVar("list", dataList);
//			context.putVar("count", dataList.size());
//			context.putVar("DownloadDate", sdf.format(new Date()));
			Workbook excel = transformer.transformXLS(is, model);
			os = new FileOutputStream("D:/template/genWork.xlsx");
//			JxlsHelper.getInstance().processTemplate(is, os, context);
			excel.write(os);
			excel.close();
			os.flush();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
		}
		
		return "";
	}
	
	
}
