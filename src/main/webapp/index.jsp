<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Boolean Model App</title>
    <style>
        body {
            background-image: url("https://cdn.hipwallpaper.com/i/13/85/eLtqQ9.png");
        }
    </style>
</head>
<body>
<div style="color: aliceblue">
    <div style="text-align: center">0
        <h1 style="background-color: black; padding: 15px; display: inline-block">
            Boolean Model Web Application
        </h1><br>

        <form method="post" action="BooleanModelApp" style="background-color: rgba(0,0,0,0.75); width: 40%; display: inline-block; padding: 10px; margin: 10px 15px">
            <label for="query" style="font-size: x-large; margin:  7px ">Enter query</label><br>
            <input type="text" style="font-size: large; width: 100%; margin: 7px" id="query" name="query"><br>
            <input type="submit" style="font-size: medium; margin: 7px">
        </form>

        <div style="background-color: rgba(0,0,0,0.75); padding: 10px; margin: 10px 15px">
            <h2>Query results:</h2>
            <h2><%=request.getAttribute("query")%></h2>
        </div>
    </div>

    <div style="padding-left: 8%; padding-right: 8%; padding-top: 20px">
        <div style="background-color: rgba(0,0,0,0.75); width: 29%; float: left; display: inline-block; padding: 10px; margin: 1%">
            <h2>Small database (100 docs)</h2>
            <h3>Linear Search Time:         <%=request.getAttribute("smallSimpleTime")%> ns</h3>
            <h3>Inverted Index Search Time: <%=request.getAttribute("smallBooleanTime")%> ns</h3>
            <h3>Coefficient: x<%=request.getAttribute("smallCoeff")%> faster</h3>
            <br>
            <h3>Found <%=request.getAttribute("smallTotal")%> files:</h3>
            <%
                ArrayList<String> result = (ArrayList<String>)request.getAttribute("smallResult");
                if (result != null && !result.isEmpty()) {
                    for(String s:result){%>
                        <p>File #<%=s%></p>
                    <%}
                }
            %>
        </div>

        <div style="background-color: rgba(0,0,0,0.75); width: 29%; float: left; display: inline-block; padding: 10px; margin: 1%">
            <h2>Medium database (500 docs)</h2>
            <h3>Linear Search Time:         <%=request.getAttribute("mediumSimpleTime")%> ns</h3>
            <h3>Inverted Index Search Time: <%=request.getAttribute("mediumBooleanTime")%> ns</h3>
            <h3>Coefficient: x<%=request.getAttribute("mediumCoeff")%> faster</h3>
            <br>
            <h3>Found <%=request.getAttribute("mediumTotal")%> files:</h3>
            <%
                ArrayList<String> result1 = (ArrayList<String>)request.getAttribute("mediumResult");
                if (result1 != null && !result.isEmpty()) {
                    for(String s:result1){%>
                        <p>File #<%=s%></p>
                    <%}
                }
            %>
        </div>

        <div style="background-color: rgba(0,0,0,0.75); width: 29%; float: left; display: inline-block; padding: 10px; margin: 1%">
            <h2>Big database (1000 docs)</h2>
            <h3>Linear Search Time:         <%=request.getAttribute("bigSimpleTime")%> ns</h3>
            <h3>Inverted Index Search Time: <%=request.getAttribute("bigBooleanTime")%> ns</h3>
            <h3>Coefficient: x<%=request.getAttribute("bigCoeff")%> faster</h3>
            <br>
            <h3>Found <%=request.getAttribute("bigTotal")%> files:</h3>
            <%
                ArrayList<String> result2 = (ArrayList<String>)request.getAttribute("bigResult");
                if (result2 != null && !result.isEmpty()) {
                    for(String s:result2){%>
                        <p>File #<%=s%></p>
                    <%}
                }
            %>
        </div>
    </div>
</div>


</body>
</html>