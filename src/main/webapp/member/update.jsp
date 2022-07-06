<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../common/header.jsp"/>
<section>
	<form id="updateForm" action="updateSubmit.mc" method="POST">
		<input type="hidden" name="num" value="${member.num}"/>
		<table>
			<tr>
				<th colspan=2><h2>회원정보수정</h2></th>
			</tr>
			<tr>
				<td>아이디</td>
				<td>
					<input type="email" name="id" placeholder="email" value="${member.id}" readonly required/>
				</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td>
					<input type="password" name="pass" id="pass" required/>
				</td>
			</tr>
			<tr>
				<td>비밀번호확인</td>
				<td>
					<input type="password" name="rePass" id="rePass" required/>
				</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>
					<input type="text" name="name" value="${member.name}" required/>
				</td>
			</tr>
			<tr>
				<td>나이</td>
				<td>
					<input type="number" name="age" value="${member.age}" required/>
				</td>
			</tr>
			<tr>
				<td>성별</td>
				<td>
					<input type="radio" name="gender" value="male" ${member.gender eq 'male' ? 'checked' : ''} /> 남성					
					<input type="radio" name="gender" value="female" ${member.gender ne 'male' ? 'checked' : ''} /> 여성
				</td>
			</tr>
			<tr>
				<td colspan=2>
					<input type="button" id="btn" value="수정완료"/>
				</td>
			</tr>
		</table>
	</form>
</section>
<script>
	var btn = document.getElementById("btn");
	var pass = document.getElementById("pass");
	var rePass = document.getElementById("rePass");
	
	btn.onclick = function(){
		alert('click');

		if(pass.value.length > 0 && pass.value == rePass.value){
			var form = document.getElementById("updateForm");
			form.submit();
		}else{
			alert('비밀번호를 확인하세요.');
			pass.value = "";
			rePass.value = "";
			pass.focus();
		}
	}
</script>
<jsp:include page="../common/footer.jsp"/>













