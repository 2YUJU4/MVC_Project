<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modify</title>
<style>
#seqRow{
	display : none;
}

</style>
</head>
<body>
	<form id="modifyForm" action="/modifyProc.msg" method="post">
		<input type="text" name="nickname" value="${dto.nickname}">
		<input type="text" name="message" value="${dto.message}">
		<input id="seqRow" type="text" name="seq" value="${dto.seq}">
		<button id="completeBtn" type="button">수정완료</button>
	</form>
	<script>
	
	$("#completeBtn").on("click",function(){
		let answer = confirm("정말 수정 하시겠습니까?")
		
		if(answer){//confirm 확인버튼을 눌렀다면
			$("#modifyForm").submit();
		}else{//confirm 취소버튼을 눌렀다면
			
		}
	})
	</script>
</body>
</html>