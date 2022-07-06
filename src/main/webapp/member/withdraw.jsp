<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../common/header.jsp"/>
<section>
	<form action="withdrawSubmit.mc" method="post">
		<table>
			<tr>
				<th colspan=2>비밀번호 확인</th>
			</tr>
			<tr>
				<td>
					<input type="password" name="tempPass" placeholder="비밀번호를 입력해주세요."/>
				</td>
				<td>
					<input type="submit" value="회원탈퇴"/>
				</td>
			</tr>
		</table>
	</form>
</section>
<jsp:include page="../common/footer.jsp"/>






