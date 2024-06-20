package kr.or.ddit.iteams.pms.documents.controller;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.annotations.Authenticated;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.iteams.common.FTPServerFileManager;
import kr.or.ddit.iteams.common.vo.MasterVO;
import kr.or.ddit.iteams.pms.documents.service.DocumentService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class DocumentViewController {
	
	@Inject
	private DocumentService service;
	
	@Inject
	private FTPServerFileManager manager;
		
	@RequestMapping(value="/pms/documents/{proNo}/documentsView.do")
	public String getView(@RequestParam("what") String what
			,Model model) throws IllegalAccessException, InvocationTargetException {
		MasterVO masterVO = new MasterVO();
		masterVO.setDocNum(what);
		service.selectDocuments(masterVO);

		model.addAttribute("docNum", what);
		log.info("수정권한 : {}",masterVO.getEditRange());
		model.addAttribute("masterVO", masterVO);
		
		return "pms/documents/documentsView";
	}
	
	@RequestMapping(value="/pms/documents/{proNo}/documentsView.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object doProcess(@ModelAttribute MasterVO masterVO
			, @Authenticated MasterVO authMember) throws Exception {
		masterVO.setProNo(authMember.getProNo());
		masterVO.setDocNum(masterVO.getWhat());
		
		service.selectDocuments(masterVO);
		ServiceResult result = masterVO.getResult();
		String viewName = null;
		
		Map<String, List<List<String>>> resultMap = new HashMap<>();
		List<List<String>> excelList = new ArrayList<>();
		
		if(result == ServiceResult.OK) {
			manager.downLoadFileForPMS(masterVO);
			
			try(InputStream is = masterVO.getFileDownLoadInputStream();
				HSSFWorkbook workBook = new HSSFWorkbook(is);
				) {
				HSSFSheet curSheet;
	            HSSFRow   curRow;
	            HSSFCell  curCell;

				for(int sheetIdx = 0; sheetIdx < workBook.getNumberOfSheets(); sheetIdx++) {
					curSheet = workBook.getSheetAt(sheetIdx);
					for(int rowIdx = 0; rowIdx < curSheet.getPhysicalNumberOfRows(); rowIdx++) {
						curRow = curSheet.getRow(rowIdx);
						List<String> curList = new ArrayList<>();
						for(int cellIdx = 0; cellIdx < curRow.getPhysicalNumberOfCells(); cellIdx++) {
							curCell = curRow.getCell(cellIdx);
							String value = null;
                            // cell 스타일이 다르더라도 String으로 반환 받음
                            switch (curCell.getCellType()){
                            case HSSFCell.CELL_TYPE_FORMULA:
                                value = curCell.getCellFormula();
                                break;
                            case HSSFCell.CELL_TYPE_NUMERIC:
                                value = curCell.getNumericCellValue()+"";
                                break;
                            case HSSFCell.CELL_TYPE_STRING:
                                value = curCell.getStringCellValue()+"";
                                break;
                            case HSSFCell.CELL_TYPE_BLANK:
                                value = curCell.getBooleanCellValue()+"";
                                break;
                            case HSSFCell.CELL_TYPE_ERROR:
                                value = curCell.getErrorCellValue()+"";
                                break;
                            default:
                                value = new String();
                                break;
                            }                          
                            curList.add(value);
						}
						excelList.add(curList);
					}
				}
			}
		}
		
		List<List<String>> outer = new ArrayList<>();
		List<String> metaList = new ArrayList<>();
		metaList.add(masterVO.getDocTitle());
		metaList.add(masterVO.getAttNo());
		metaList.add(masterVO.getEditRange());
		metaList.add(masterVO.getDevId());
		outer.add(metaList);
		resultMap.put("data", excelList);
		resultMap.put("meta", outer);
		
		return resultMap;
	}
}
