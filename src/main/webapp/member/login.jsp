<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../common/header.jsp"/>
<section>
	<form action="loginSubmit.mc" method="POST">
		<table>
			<tr>
				<th colspan="2">
					<h2>로그인</h2>
				</th>
			</tr>
			<tr>
				<td>아이디</td>
				<td><input type="text" name="id" required/></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="pass" required/></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="checkbox" name="check"/>
					로그인 정보 저장
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="로그인"/>
					<input type="button" onclick="location.href='findPass.mc';" value="비밀번호찾기" />
				</td>
			</tr>
		</table>
	</form>
</section>
<jsp:include page="../common/footer.jsp"/>




