<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/table.css">	
<div class="sub-wrap">
	<div class="sub-inner-box">
		<div class="sub-inner-txt">
			<span class="sub-txt"> 커뮤니티<br>
				<span class="project-txt-lighter">빠르게 변화하는 기술 트렌드 관련 다양한 콘텐츠를 제공하는 곳<br>아이팀즈입니다.</span>
			</span>
		</div>
		<div class="main-outline-box-img m-right-10">
			<img alt="" src="<%=request.getContextPath()%>/resources/images/community.png">
		</div>
	</div>
</div>
<section class="section-wrap">
	<div class="community-box-wrap">
		<div style="cursor: pointer;" class="linkBtn community-box" data-gopage="${cPath }/outs/board/free/freeBoardList.do">
			<a href="<%=request.getContextPath()%>/outs/board/free/freeBoardList.do">
				<img alt="" src="<%=request.getContextPath()%>/resources/images/c1.png">
			</a>
		</div>
		<div class="community-box">
			<a href="<%=request.getContextPath()%>/outs/board/codebook/codeList.do">
				<img alt="" src="<%=request.getContextPath()%>/resources/images/c2.png">
			</a>
		</div>
		<div class="community-box">
			<a href="<%=request.getContextPath()%>/outs/board/interview/interviewList.do">
				<img alt="" src="<%=request.getContextPath()%>/resources/images/c3.png">
			</a>
		</div>
		<div class="community-box">
			<a href="<%=request.getContextPath()%>/outs/board/news/newsList.do">
				<img alt="" src="<%=request.getContextPath()%>/resources/images/c4.png">
				</a>
			</div>
		</div>
		<div class="project-list-title m-bottom-10 m-top-30">
			<span>이번주 전체 인기글 ></span>
		</div>
		<div class="table-wrap">
			<table class="table">
			  <thead class="thead-light">
			    <tr class="tr-gray">
			      <td scope="col">No</td>
			      <td scope="col">글 제목</td>
			      <td scope="col">날짜</td>
			      <td scope="col">조회수</td>
			      <td scope="col">작성자</td>
			    </tr>
			  </thead>
			  <tbody>
			    <tr>
			      <th scope="row">1</th>
			      <td>회사 정보가 없는 회사 면접</td>
			      <td>2021.11.02</td>
			      <td>2</td>
			      <td>ksdss님</td>
			    </tr>
			    <tr>
			      <th scope="row">2</th>
			      <td>통장이...텅장되는..취미..줄여야할까요?</td>
			      <td>2021.11.02</td>
			      <td>2</td>
			      <td>yoon님</td>
			    </tr>
			    <tr>
			      <th scope="row">3</th>
			      <td>저도 스트레스 풀 수 있는 취미 갖고 싶은데..</td>
			      <td>2021.11.02</td>
			      <td>2</td>
			      <td>ksdss님</td>
			    </tr>
			    <tr>
			      <th scope="row">4</th>
			      <td>이직을 고민하고 있습니다.</td>
			      <td>2021.11.02</td>
			      <td>2</td>
			      <td>zzinew님</td>
			    </tr>
			    				    <tr>
			      <th scope="row">5</th>
			      <td>회사 정보가 없는 회사 면접</td>
			      <td>2021.11.02</td>
			      <td>2</td>
			      <td>ksdss님</td>
			    </tr>
			    <tr>
			      <th scope="row">6</th>
			      <td>통장이...텅장되는..취미..줄여야할까요?</td>
			      <td>2021.11.02</td>
			      <td>2</td>
			      <td>yoon님</td>
			    </tr>
			    <tr>
			      <th scope="row">7</th>
			      <td>저도 스트레스 풀 수 있는 취미 갖고 싶은데..</td>
			      <td>2021.11.02</td>
			      <td>2</td>
			      <td>ksdss님</td>
			    </tr>
			    <tr>
			      <th scope="row">8</th>
			      <td>이직을 고민하고 있습니다.</td>
			      <td>2021.11.02</td>
			      <td>2</td>
			      <td>zzinew님</td>
			    </tr>
			    <tr>
			      <th scope="row">9</th>
			      <td>저도 스트레스 풀 수 있는 취미 갖고 싶은데..</td>
			      <td>2021.11.02</td>
			      <td>2</td>
			      <td>ksdss님</td>
			    </tr>
			    <tr>
			      <th scope="row">10</th>
			      <td>이직을 고민하고 있습니다.</td>
			      <td>2021.11.02</td>
			      <td>2</td>
			      <td>zzinew님</td>
			    </tr>
			  </tbody>
		</table>
	</div>
</section>
