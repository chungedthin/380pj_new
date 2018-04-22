<html>
    <head>
        <title>Edit</title>
    </head>
    <body>
        <c:url var="logoutUrl" value="/logout"/>
        <form action="${logoutUrl}" method="post">
            <input type="submit" value="Log out" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2>Bidding #${bidding.id}</h2>
        <form:form method="POST" enctype="multipart/form-data"
                   modelAttribute="biddingForm">   
            <form:label path="subject">Subject</form:label><br/>
            <form:input type="text" path="subject" /><br/><br/>
            <form:label path="body">Body</form:label><br/>
            <form:textarea path="body" rows="5" cols="30" /><br/><br/>
            <c:if test="${fn:length(bidding.attachments) > 0}">
                <b>Attachments:</b><br/>
                <ul>
                    <c:forEach items="${bidding.attachments}" var="attachment">
                        <li>
                            <c:out value="${attachment.name}" />
                            [<a href="<c:url 
                                    value="/bidding/${bidding.id}/delete/${attachment.name}"
                                    />">Delete</a>]
                        </li>
                    </c:forEach>
                </ul>
            </c:if>
            <b>Add attachments</b><br />
            <input type="file" name="attachments" multiple="multiple"/><br/><br/>
            <input type="submit" value="Save"/><br/><br/>
        </form:form>
        <a href="<c:url value="/bidding" />">Return to list biddings</a>
    </body>
</html>
