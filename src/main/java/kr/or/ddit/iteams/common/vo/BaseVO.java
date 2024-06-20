package kr.or.ddit.iteams.common.vo;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.vo.SearchVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class BaseVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//최근 조회 추적용
	private List<?> traceList;

	private int rnum;
	private int rowCnt;
	
	public void setRowCnt(int rowCnt) {
		this.rowCnt = rowCnt;
	}
	
	private int totalRecord; 
	private int screenSize = 10;	//한 페이지에 보이는 게시물 수
	private int blockSize = 5;		//페이지 목록 수
	private int currentPage;
	
	private int totalPage;
	private int endRow;
	private int startRow;
	private int endPage;
	private int startPage;
	
	private int page = 1;
	
	private List<?> dataList;
	private List<?> dataListt;
	
	private Object detailSearch;
	
	public void setDetailSearch(Object detailSearch) {
		this.detailSearch = detailSearch;
	}
	
	
	
	// 단순 키워드 검색용 
	private SearchVO searchVO;
	
	
	public void setSearchVO(SearchVO searchVO) {
		this.searchVO = searchVO;
	}
	
	public void setDataList(List<?> dataList) {
		this.dataList = dataList;
	}
	public void setDataListt(List<?> dataListt) {
		this.dataListt = dataListt;
	}
	
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		totalPage = (totalRecord + (screenSize-1))/screenSize;
	}
	
	public void setPage(String page) {
		if(StringUtils.isBlank(page)) return;
		this.page = Integer.parseInt(page);		
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		endRow = currentPage * screenSize;
		startRow = endRow - (screenSize - 1);

		endPage = blockSize * ((currentPage + (blockSize - 1))/blockSize);
		startPage = endPage - (blockSize - 1);
	}
	
	private static final String PAINGPATTERN = 
			"<li class='page-item %s'><a class='page-link' data-page='%s' href='#' %s>%s</a></li>";
	private static final String CURRENTPATTERN = 
			"<li class='page-item active' aria-current='page'>\n" + 
			"<a class='page-link' href='#'>%d</a>\n" + 
			"</li>";
	private String DISABLED = "tabindex='-1' aria-disabled='true'";
	private String ENABLED = "";
	
	public String getPagingHTML() {
		StringBuffer html = new StringBuffer("<nav aria-label='paging'>\n");
		html.append("<ul class='pagination'>\n");
		endPage = endPage > totalPage ? totalPage : endPage;
		if(startPage > blockSize) {
			html.append(String.format(PAINGPATTERN, ENABLED, (startPage - blockSize), ENABLED, "이전"));
		}else {
			html.append(String.format(PAINGPATTERN, "disabled", "", DISABLED, "이전"));
			
		}
		
		for(int page = startPage; page <= endPage; page++) {
			if(page==currentPage) {
				html.append(String.format(CURRENTPATTERN, page));
			}else {
				html.append(String.format(PAINGPATTERN, ENABLED, page, ENABLED, page));
			}
		}
		
		if(endPage < totalPage) {
			html.append(String.format(PAINGPATTERN, ENABLED, (endPage + 1), ENABLED,"다음"));
		}else {
			html.append(String.format(PAINGPATTERN, "disabled", "", DISABLED,"다음"));
		}
		
		html.append("</ul></nav>");
		return html.toString();
	}
	
	
	public void setScreenSize(int size) {
		this.screenSize = size;
	}
	
	public void setBlockSize(int size) {
		this.blockSize = size;
	}
	
	public BaseVO(int screenSize, int blockSize) {
		this.screenSize = screenSize;
		this.blockSize = blockSize;
	}
}
