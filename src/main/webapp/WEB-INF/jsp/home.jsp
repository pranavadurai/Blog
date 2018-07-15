<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class = "container">
 <p>Initial page</p>
 ${Auth_profile}
 </br>
 ${posts}
 <p>Posts</p>
 <c:forEach var = "post" items="${posts}">
    <p>${post.id}, ${post.tweet}, ${post.profile.id }</p>
 </c:forEach>
</div>

<%@ include file="common/footer.jspf" %>