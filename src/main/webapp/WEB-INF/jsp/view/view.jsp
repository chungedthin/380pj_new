<%-- 
    Document   : view
    Created on : Apr 20, 2018, 2:11:07 PM
    Author     : s1177437
--%>
<!DOCTYPE html>
<html>
    <head>
        <title><c:out value="${bidding.itemsubject}" /></title>
    </head>
    <body>
        <c:url var="logoutUrl" value="/logout"/>
        <form action="${logoutUrl}" method="post">
            <input type="submit" value="Log out" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2> #${biddingId}: <c:out value="${bidding.itemsubject}" /></h2>
        <security:authorize access="hasRole('ADMIN') or principal.username=='${bidding.customerName}'">
            [<a href="<c:url value="/bidding/edit/${biddingId}" />">Edit</a>]
        </security:authorize>
        <security:authorize access="hasRole('ADMIN')">
            [<a href="<c:url value="/bidding/delete/${biddingId}" />">Delete</a>]
        </security:authorize>
        <br /><br />
        <i>Customer Name - <c:out value="${bidding.ownerName}" /></i><br /><br />
        <c:out value="${bidding.body}" /><br /><br />
        <c:if test="${bidding.numberOfAttachments > 0}">
            Photo:
            <c:forEach items="${bidding.attachments}" var="attachment" varStatus="status">
                <c:if test="${!status.first}">, </c:if>
                <a href="<c:url value="/bidding/${biddingId}/photo/${attachment.name}" />">
                    <c:out value="${attachment.name}" /></a>
            </c:forEach><br /><br />
        </c:if>
        <i>Item info. - <c:out value="${bidding.itemContent}" /></i><br /><br />
        <i>Item price - <c:out value="${bidding.price}" /></i><br /><br />
        <i>Item status - <c:out value="${bidding.status}" /></i><br /><br />
        
        <a href="<c:url value="/bidding" />">Return to list</a>
    </body>
</html>