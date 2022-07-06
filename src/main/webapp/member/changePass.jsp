<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../common/header.jsp" />
<section>
	<form action="changePassSubmit.mc" method="post">
		<input type="hidden" name="id" value="${id}" />
		<input type="hidden" name="code" value="${code}" />
		<table>
			<tr>
				<td colspan="2">
					<h1>비밀번호 변경</h1>
				</td>
			</tr>
			<tr>
				<td>새로운 비밀번호 입력</td>
				<td>
					<input type="password" name="pass"/> <br/>			
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="변경하러 가기" />
				</td>
			</tr>
		</table>
	</form>
</section> 
<jsp:include page="../common/footer.jsp" />