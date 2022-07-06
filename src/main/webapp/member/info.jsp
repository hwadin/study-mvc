<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
 <!-- info.jsp -->
 <jsp:include page="../common/header.jsp"/>
<section>
	<table>
		<tr>
			<td colspan=2>회원정보</td>
		</tr>
		<tr>
			<td>아이디</td>
			<td>${member.id}</td>
		</tr>
		<tr>
			<td>이름</td>
			<td>${member.name}</td>
		</tr>
		<tr>
			<td>나이</td>
			<td>${member.age}</td>
		</tr>
		<tr>
			<td>성별</td>
			<td>
				<c:choose>
					<c:when test="${member.gender eq 'male'}">남성</c:when>
					<c:otherwise>여성</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td>회원 등록일</td>
			<td>
				<f:formatDate value="${member.regdate}" pattern="yyyy년MM월dd일 hh시mm분ss초"/>
			</td>
		</tr>
		<tr>
			<td>회원 수정일</td>
			<td>
				<f:formatDate value="${member.updatedate}" pattern="yyyy년MM월dd일 hh시mm분ss초"/>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" onclick="location.href='test';" value="메인으로"/>
				<input type="button" onclick="location.href='update.mc';" value="회원정보수정"/>
				<input type="button" onclick="location.href='withdraw.mc';" value="회원탈퇴"/>
			</td>
		</tr>
	</table>
</section>
<jsp:include page="../common/footer.jsp"/>






