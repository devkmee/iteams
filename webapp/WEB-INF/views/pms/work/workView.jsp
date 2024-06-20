<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<body>
<div class="page-breadcrumb">
<div class="flex-end m-bottom-10">
	<c:if test="${authMember.memId eq masterVO.workWriter or authMember.memId eq masterVO.workCharger or authMember.authority eq 'PM' or authMember.authority eq 'PL'}">
		<button type="button" 
			class="linkBtn btn pms-btn width-60 m-right-5" 
			data-gopage="${cPath}/pms/work/${authMember.proNo }/workUpdate.do?what=${masterVO.workNum}&writer=${masterVO.workWriter}&charger=${masterVO.workCharger}">수정</button>
		<button type="button"
			class="linkBtn btn pms-btn width-60 m-right-5" 
			data-gopage="${cPath}/pms/work/${authMember.proNo }/workDelete.do?what=${masterVO.workNum}&writer=${masterVO.workWriter}&charger=${masterVO.workCharger}">삭제</button>
	</c:if>
	<button type="button"
		class="linkBtn btn pms-btn width-60" 
		data-gopage="${cPath}/pms/work/${authMember.proNo }/workList.do?what=${masterVO.workNum}&writer=${masterVO.workWriter}&charger=${masterVO.workCharger}">리스트</button>
</div>
	<table class="table table-veiw">
  <tbody>
  	<tr>
      <th scope="row">유형</th>
      <td>${masterVO.workTypeValue }</td>
    </tr>
    <tr>
      <th scope="row">제목</th>
      <td>${masterVO.workTitle }</td>
    </tr>
    <tr>
      <th scope="row">상태</th>
      <td>${masterVO.workStateValue }</td>
    </tr>
    <tr>
      <th scope="row">시작시간</th>
      <td>${masterVO.startDate }</td>
    </tr>
    <tr>
      <th scope="row">완료기한</th>
      <td>${masterVO.endDate }</td>
    </tr>
    <tr>
      <th scope="row">우선순위</th>
      <td>${masterVO.workPriorityValue }</td>
    </tr>
    <tr>
      <th scope="row">진척도</th>
      <td>${masterVO.workProgressValue}</td>
    </tr>
    <tr>
      <th scope="row">담당자</th>
      <td>${masterVO.chargerName }</td>
    </tr>
    <tr>
      <th scope="row">설명</th>
      <c:if test="${not empty masterVO.workContent }">
	      <td>${masterVO.workContent }</td>
      </c:if>
      <c:if test="${empty masterVO.workContent }">
	      <td>설명 없음.</td>
      </c:if>
    </tr>
    <tr>
      <th scope="row">상위일감</th>
      <c:if test="${not empty masterVO.workParentValue }">
	      <td>${masterVO.workParentValue }</td>
      </c:if>
      <c:if test="${empty masterVO.workParentValue }">
	      <td>상위일감 없음.</td>
      </c:if>
    </tr>
    <tr>
      <th scope="row">첨부파일</th>
      <td colspan="2">
      	<c:if test="${not empty masterVO.attachList }">
      		<c:forEach items="${masterVO.attachList}" var="attach">
      			<a href="${cPath}/pms/${authMember.proNo}/download.do?what=${attach.attNo}">${attach.attachOrigin }</a>
      		</c:forEach>
      	</c:if>
      	<c:if test="${empty masterVO.attachList }">
      		<span>첨부파일 없음.</span>
      	</c:if>
      </td>
    </tr>
  </tbody>
</table>
</div>
</body>
