<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	<script type="text/javascript">
	function validateCatalogId() {
		var xmlHttpRequest = init();
		function init() {
			if (window.XMLHttpRequest)
				return new XMLHttpRequest;
			else if (window.ActiveXObject)
				return new ActiveXObject("Microsoft.XMLHTTP");
		}
		var catalogId = document.getElementById("catalogId");
		xmlHttpRequest.open("GET", "AjaxFormServlet?catalogId=" +
				encodeURIComponent(catalogId.value), true);
		xmlHttpRequest.onreadystatechange = processRequest;
		xmlHttpRequest.send(null);
		function processRequest() {
			if (xmlHttpRequest.readyState == 4)
				if (xmlHttpRequest.status == 200)
					processResponse();
		}
		function processResponse() {
			var xmlMessage = xmlHttpRequest.responseXML;
			var valid = xmlMessage.getElementsByTagName("valid")[0].firstChild.nodeValue;
			if (valid == "true") {
				var validationMessage = document.getElementById("validationMessage");
				validationMessage.innerHTML = "Catalog Id is Valid";
				document.getElementById("submitForm").disabled = false;

				var journalElem = document.getElementById("journal");
				journalElem.value = "";
				var pubElem = document.getElementById("publisher");
				pubElem.value = "";
				var edElem = document.getElementById("edition");
				edElem.value = "";
				var titleElem = document.getElementById("title");
				titleElem.value = "";
				var authorElem = document.getElementById("author");
				authorElem.value = "";
			}
			if (valid == "false") {
				var validationMessage = document.getElementById("validationMessage");
				validationMessage.innerHTML = "Catalog Id is NOT Valid";
				document.getElementById("submitForm").disabled = true;

				var journal = xmlMessage.getElementsByTagName("journal")[0].firstChild.nodeValue;
				var publisher = xmlMessage.getElementsByTagName("publisher")[0].firstChild.nodeValue;
				var edition = xmlMessage.getElementsByTagName("edition")[0].firstChild.nodeValue;
				var title = xmlMessage.getElementsByTagName("title")[0].firstChild.nodeValue;
				var author = xmlMessage.getElementsByTagName("author")[0].firstChild.nodeValue;
				
				var journalElem = document.getElementById("journal");
				journalElem.value = journal;
				var pubElem = document.getElementById("publisher");
				pubElem.value = publisher;
				var edElem = document.getElementById("edition");
				edElem.value = edition;
				var titleElem = document.getElementById("title");
				titleElem.value = title;
				var authorElem = document.getElementById("author");
				authorElem.value = author;
			}
		}
	}

	
	</script>
</head>
<body>
	<h1>Form for Catalog entry</h1>
	<form name="AjaxFormServlet" action="AjaxFormServlet" method="post">
		<table>
		<tr>
			<td>Catalog Id:</td>
			<td><input type="text" size="20" id="catalogId" name="catalogId"
					onkeyup="validateCatalogId()"></td>
			<td><div id="validationMessage"></div></td>
		</tr>
		<tr>
			<td>Journal:</td>
			<td><input type="text" size="20" id="journal" name="journal"
					onkeyup="validateCatalogId()"></td>
			<td>&nbsp</td>
		</tr>
		<tr>
			<td>Publisher:</td>
			<td><input type="text" size="20" id="publisher" name="publisher"
					onkeyup="validateCatalogId()"></td>
			<td>&nbsp</td>
		</tr>
		<tr>
			<td>Edition:</td>
			<td><input type="text" size="20" id="edition" name="edition"
					onkeyup="validateCatalogId()"></td>
			<td>&nbsp</td>
		</tr>
		<tr>
			<td>Title:</td>
			<td><input type="text" size="20" id="title" name="title"
					onkeyup="validateCatalogId()"></td>
			<td>&nbsp</td>
		</tr>
		<tr>
			<td>Author:</td>
			<td><input type="text" size="20" id="author" name="author"
					onkeyup="validateCatalogId()"></td>
			<td>&nbsp</td>
		</tr>
		<tr>
			<td><input type="submit" value="Create Catalog" id="submitForm"
					name="submitForm"></td>
		</tr>
		</table>
	</form>
</body>
</html>