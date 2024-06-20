package kr.or.ddit.iteams.pms.zoom;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ZoomController {

	@RequestMapping(value="/pms/zoom/zoomIn.do")
	public String doProcess() {
		return "pms/zoom/zoomForm";
	}
}
