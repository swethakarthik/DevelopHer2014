<html>
    <head>
        <title>REST with Forms</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <br />
        <form method="post" action="http://localhost:8090/manageCandidate/rest/CandidateInfo/">
        	Are you interested:<input type="checkbox" name="isInterested" value="a"></input>
            <!-- Are you interested: <input type="text" name="isInterested" id="name" /> --><br />
            VisaType: <input type="text" name="visaType" id="visaType" /><br />
            Location: <input type="text" name="location" id="location" /><br />
            <input type="text" name="managerId" value="drew" style="display:none">
            <input type="text" name="candidateId" value="xyz" style="display:none">
            
            <input type="submit" value="Submit" />
        </form>
    </body>
</html>
