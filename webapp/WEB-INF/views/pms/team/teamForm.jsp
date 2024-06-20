<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/hireList.css">
<div class="pms-main-wrap">
     <div class="sub-title-wrap">
       <i class="fas fa-chevron-right"></i>
       	팀원 디렉토리
     </div>
</div>
<div class="team-wrap">
	 <c:set var ="member" value="${vo }"></c:set>
	 <c:choose>
		 <c:when test="${empty member }">
            <div class="interview-box-wrap">
                <span>팀원이 없습니다.</span>
            </div>  
          </c:when>
          <c:otherwise> 
			<c:forEach items="${member}" var="list" varStatus="status">  
				 <c:if test="${ status.index % 2 eq 0 }">
				 	<div class="scrap-box-wrap">
					<c:forEach var="j" begin="${ status.index }" end="${ status.index + (2 - 1) }" step="1">
						 <c:if test="${ member[j] ne null }">
							<div class="scrap-box">
								<div class="scrap-img">
									<img src="${cPath}/imageRender.do?what=${member[j].devImg}" alt="${cPath}${member[j].defaultImage}" style="object-fit:cover;">
								</div>
								<div class="scrap-txt">
									<c:url value="/pms/team/${authMember.proNo}/teamView.do" var="viewURL">
										<c:param name="what" value="${member[j].promemNum}" />
									</c:url>
									<a class="team-name" href="${viewURL }">${member[j].devId }</a>
									<span>개발자이름 : ${member[j].realName }</span>
									<span>개발포지션: ${member[j].authority }</span>
									<span>직무 : ${member[j].devJob }</span>
									<span>연락처 : ${member[j].memTel }</span>
									<span>메일 : ${member[j].memMail }</span>
									<%-- <p class="card-text">프로젝트번호 : ${member.promemNum }</p>
									<p class="card-text">참여여부 : ${member.outedNy }</p> --%>
								</div>
							</div>
						</c:if>
					</c:forEach>
					</div>
				</c:if>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</div> 
