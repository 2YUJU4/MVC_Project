<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<title>Output</title>
</head>
<body>
<table border="1" align="center" class="message">
<thead>
	<tr>
		<th>SEQ</th>
		<th>NICKNAME</th>
		<th>MESSAGE</th>
		<th>BUTTONS</th>
	</tr>
</thead>	
<tbody>
<c:if test="$(list == null)">
	<tr>
		<td colspan=4>보여줄 메세지가 없습니다.</td>
	</tr>

</c:if>
	<c:forEach items="${list}" var="dto">
	<tr>
	 <td>${dto.getSeq()}</td>
	 <td>${dto.getNickname()}</td>
	 <td>${dto.getMessage()}</td>
	 <td>
	 	<button type="button" class="deleteBtn" value="${dto.getSeq()}">삭제</button>
	 	<button type="button" class="modifyBtn" value="${dto.getSeq()}">수정</button>
	 </td>
	</tr>	
	 </c:forEach>
	 <tr>
		<td colspan=4>
		<button type="button">Back</button>
		</td>
	</tr>
</tbody>	 
</table>
	 <script>
	 	//삭제버튼을 눌렀을때
	 	$(".message").on("click",".deleteBtn",function(){
	 		//버튼이 눌렀을때 그 행에 대한 seq(버튼 value)값 가져오기
	 		let seq = $(this).val();
	 		//form동적 생성
	 		let deleteForm = $("<form></form>");
	 		//form 속성 - action, method
	 		
	 		//attr(어떤속성 , 어떤속성값) -> 속성값 부여 메서드
	 		deleteForm.attr("action","/delete.msg");
	 		deleteForm.attr("method","post");
	 		
	 		//데이터를 전송하기 위해 form에 input 태그를 추가
	 		deleteForm.append($("<input>", {type: "text", name: "seq", value: seq}));
	 		
	 		//body요소에 form요소를 실제로 추가
	 		$(deleteForm).appendTo("body").css("display","none");
	 		
	 		//스크립트 영역에서 만든 deleteForm을 즉시 submit해버리기~! (form태그의 action으로 이동)
	 		deleteForm.submit();
	 	});
	 	
	 	
	 	
	 	//수정버튼을 눌렀을 때
	 	$(".message").on("click",".modifyBtn",function(){
	 		let seq = $(this).val();	
	 		//seq번호를 /modify url로 get방식(단순 조회) 전송
	 		location.href = "/modify.msg?seq="+seq;
	 	
	 	})
	 	
	 
	 </script>
</body>
</html>