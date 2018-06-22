<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class = "container">
 <P>Welcome to the site ${profile.name}</P>
 ${Auth_profile}
 ${Auth_profile.name}
</div>

<%@ include file="common/footer.jspf" %>