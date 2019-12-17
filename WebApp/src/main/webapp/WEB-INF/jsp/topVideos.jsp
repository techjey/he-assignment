<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Top Youtube videos</title>
</head>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"/>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
<body>
	<c:if test="${empty data.items}">
		<p>No data found</p>
	</c:if>
	<c:if test="${! empty data.items}">
		<table>
			<thead>
				<tr>
					<th>Title</th>
					<th>Published Date</th>
					<th>View Count</th>
					<th>Like Count</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="videoInfo" items="${data.items}">
					<tr>
						<td>${videoInfo.snippet.title}</td>
						<td>${videoInfo.snippet.publishedAt}</td>
						<td>${videoInfo.statistics.viewCount}</td>
						<td>${videoInfo.statistics.likeCount}</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="4" align="center"><hr></td>
				</tr>
				<tr>
					<td colspan="4" align="center"><button id="emailme" type="button">Email Me</button></td>
				</tr>
			</tbody>
		</table>

		<div id="dialog" title="Enter email id" style="display: none">
			<form action="sendEmail" id="myForm" method="POST">
  				<input type="text" name="emailId" id="emailId" maxlength="50"/>
  			</form>
		</div>
	</c:if>
</body>
<script type="text/javascript">
	$(document).ready(function(){
	   $("#emailme").click(function(){
		  $( "#dialog" ).dialog({ 
			   buttons: [{text: "Send",
				          type: "submit",
				   		  modal: true,
					      click: function() { 
					    	 $("#myForm").submit();
					      }}]
			});
 	   });
	});
	
	var validator = $("#myForm").validate({
		rules: {
			emailId: {
	      		required: true,
	      		email: true
	    	}
		}
	});

</script>
</html>