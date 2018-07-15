<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class = "container">
 <P>Welcome to the site ${Auth_profile.name}</P>
 <p>Auth profile </p>
 ${Auth_profile}
 
 <p>profile</p>
 ${profile}
 
 <p> Post of the profile </p>
  ${profile_posts}
</div>

<%@ include file="common/footer.jspf" %>