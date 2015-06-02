<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
 <html>
 <head>
 <title>Messages</title>
 <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
 </head>
 <body>

     <h1>Fix Emulator</h1>
     <hr>
     <h3>Set sequence : ${session.session.toString()} </h3>

    <form>
        <table class="flat-table">
            <tbody>
                <tr>
                    <td>IN</td>
                    <td><input type="text" name="seqIn" value="${String.valueOf(sessionHandler.getNextSenderMsgSeqNum())}"></td>
                </tr>
                <tr>
                    <td>OUT</td>
                    <td><input type="text" name="seqOut" value="${String.valueOf(sessionHandler.getNextTargetMsgSeqNum())}"></td>
                </tr>
            </tbody>
        </table>

        <input type="button" value="Set Sequence">

        <hr>
        <h3>Set sequence : ${session.session.toString()} </h3>

        <table class="flat-table">
                    <tbody>
                        <tr>
                            <td>Current status</td>
                            <td>${session.status}</td>
                        </tr>

                    </tbody>
                </table>

        <input type="button" value="Start">
        <input type="button" value="Stop">
    </form>


 </body>
 </html>