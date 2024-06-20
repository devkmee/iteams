<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>    
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/projectList.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/project/projectList.js"></script>
<div>
	<div class="sub-wrap">
		<div class="sub-inner-box">
			<div class="sub-inner-txt">
				<span class="sub-txt">마이페이지<br>
					<span class="project-txt-lighter">커리어 관리가 즐거워지고, 오늘보다 더 나은 내일을 꿈꿀 수 있는 곳<br>아이팀즈입니다.</span>
				</span>
			</div>
			<div class="main-outline-box-img m-right-10">
				<img alt="" src="<%=request.getContextPath()%>/resources/images/project-img.png">
			</div>
		</div>
	</div>
	<section class="section-wrap">
		<div class="project-list-title m-bottom-10 m-top-30">
			<span>내가 스크랩한 공고글 ></span> 
		</div>
		
		<c:set var="projectList" value="${base.dataList }" />
		
		<div class="project-list-wrap">
			<c:choose>
				<c:when test="${empty projectList }">
					<div class="project-list-box-wrap">
	                    <span>스크랩한 공고글이 없습니다.</span>
	                </div>  
				</c:when>
                <c:otherwise>
                 	  <c:forEach var="project" items="${projectList}" varStatus="state">
	                 	<c:if test="${state.index mod 3 eq 0}">
	                 		</div>
	                 	</c:if>
                 		<c:if test="${state.index eq 0 || state.index mod 3 eq 0}">
                 			<div class="project-list-box-wrap">
                 		</c:if>
		                    	<c:url value="/outs/board/projectboard/projectView.do" var="scrapURL">
		                    		<c:param name="what" value="${project.boNum }" />
								</c:url>
		                    <div class="project-list-box linkBtn" data-gopage="${scrapURL}">
		                    	<span>${project.clientName }</span>
		                    	<div class="protitle">${project.projectName }</div>
		                    	<span>모집인원 : ${project.projectRecruit}명</span>
								<c:choose>
									<c:when test="${not empty project.limitModifyDate}">
										<span class="project-list-date">공고기한 : ${project.permissionDate} ~ ${project.limitModifyDate}</span>
									</c:when>
									<c:otherwise>
										<span class="project-list-date">공고기한 : ${project.permissionDate} ~ ${project.limitDate}</span>
									</c:otherwise>
								</c:choose>
								<div>
								 	<c:choose>
							      	  <c:when test="${empty project.projectTechValue }">
								      	<div>#${project.projectTech }</div>
								      </c:when>
								      <c:otherwise>
							      		<div class="flex-start m-top-10" style="flex-wrap: wrap;">
									      	<c:forEach items="${project.projectTechValue }" var="tech" begin="0" end="3">
									      		<div class="tech-box-small">
										      		<div class="tech-img-small">
										      			<img alt="${tech}" src="<%=request.getContextPath()%>/resources/images/skill/${tech}.png"> 
										      		</div>
									      			<span class="tech-txt">${tech}</span>
									      		</div>
									      	</c:forEach>
							      		</div>
								      </c:otherwise>
							      </c:choose>
							    </div>
		                    </div>
	                    <c:if test="${status.last}">
	                 		</div>
	                 	</c:if>
                    </c:forEach>
                 </c:otherwise>
            </c:choose>
		</div>
		
		
	<div class="paging-wrap">
		<div id="pagingArea"> ${base.pagingHTML } </div>	
	</div>		
		
		
	</section>
</div>		

<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/custom/paging.js"></script>
