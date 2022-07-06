<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../common/header.jsp"/>
<section>
	<form action="joinSubmit.mc" method="POST">
		<table>
			<tr>
				<th colspan="2">
					<h2>회원가입</h2>
				</th>
			</tr>
			<tr>
				<td>아이디</td>
				<td>
					<input type="email" name="id" placeholder="email" required/>
				</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td>
					<input type="password" name="pass" required />
				</td>
			</tr>
			<tr>
				<td>비밀번호 확인</td>
				<td>
					<input type="password" name="rePass" required />
				</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>
					<input type="text" name="name" required/>
				</td>
			</tr>
			<tr>
				<td>나이</td>
				<td>
					<input type="number" name="age" required/>
				</td>
			</tr>
			<tr>
				<td>성별</td>
				<td>
					<input type="radio" name="gender" value="male" checked /> 남성
					<input type="radio" name="gender" value="female" /> 여성
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="회원가입"/>
				</td>
			</tr>
		</table>	
	</form>
</section>
<jsp:include page="../common/footer.jsp"/>





