<!DOCTYPE html>
<html>
    <head>
        <title>Customer Support</title>
    </head>
    <body>
        <c:url var="logoutUrl" value="/logout"/>
        <form action="${logoutUrl}" method="post">
            <input type="submit" value="Log out" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2>Biddingss</h2>
        <security:authorize access="hasRole('ADMIN')">    
            <a href="<c:url value="/user" />">Manage User Accounts</a><br /><br />
        </security:authorize>
        <a href="<c:url value="/bidding/create" />">Create a Bidding</a><br /><br />

        <c:choose>
            <c:when test="${fn:length(biddingDatabase) == 0}">
                <i>There are no biddings in the system.</i>
            </c:when>
            <c:otherwise>
                <c:forEach items="${biddingDatabase}" var="bidding">
                    Bidding ${bidding.id}:
                    <a href="<c:url value="/bidding/view/${bidding.id}" />">
                        <c:out value="${bidding.subject}" /></a>
                    (customer: <c:out value="${bidding.customerName}" />)
                    <security:authorize access="hasRole('ADMIN') or 
                                        principal.username=='${bidding.customerName}'">
                        [<a href="<c:url value="/bidding/edit/${bidding.id}" />">Edit</a>]
                    </security:authorize>
                    <security:authorize access="hasRole('ADMIN')">            
                        [<a href="<c:url value="/bidding/delete/${bidding.id}" />">Delete</a>]
                    </security:authorize>
                    <br /><br />
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </body>
</html>
