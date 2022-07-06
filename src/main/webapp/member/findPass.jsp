<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../common/header.jsp"/>
<section>
	<article>
		<form action="findPassSubmit.mc" method="POST">
			<table>
				<tr>
					<td colspan="2"><h2>비밀번호찾기</h2></td>
				</tr>
				<tr>
					<td colspan="2">
						회원가입 시 등록 한 아이디(email)와 이름을 입력해주세요!
					</td>
				</tr>
				<tr>
					<td>아이디</td>
					<td>
						<input type="text" name="id" />		
					</td>
				</tr>
				<tr>
					<td>이름</td>
					<td>
						<input type="text" name="name" />		
					</td>
				</tr>
				<tr>
					<th colspan="2">
						<input type="submit" value="확인"/>
					</th>
				</tr>
			
			</table>
		</form>
	</article>
</section>
<jsp:include page="../common/footer.jsp"/>