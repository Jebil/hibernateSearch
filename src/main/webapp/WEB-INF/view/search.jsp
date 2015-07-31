<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>
	function search(query) {
		$("#bookTable").find("tr:gt(0)").remove();
		if (query.value.trim() != '') {

			$
					.post(
							"getSearch",
							{
								query : query.value
							},
							function(entry) {
								if (entry.length > 0) {
									$('#bookTable').show();
									$('#noResults').hide();
									entry
											.forEach(function(data) {
												$('#bookTable')
														.append(
																'<tr><td>'
																		+ data.title
																		+ '</td><td>'
																		+ data.language
																		+ '</td><td>'
																		+ data.author.name
																		+ '</td><td><input type="button" name="delete" value="Delete"onclick="deleteBook('
																		+ data.id
																		+ ')"></td></tr>');
											})
								} else {
									$('#bookTable').hide();
									$('#noResults').show();
									$('#noResults').text("No results....");
								}
							})
		} else {
			$('#noResults').hide();
		}
	}

	function deleteBook(id) {
		$.post("deleteBook", {
			id : id
		}, function() {
			location.reload();
		})
	}
</script>
</head>
<body>
	<input placeholder="Search......."
		style="width: 50%; height: 45px; margin-left: 22%; margin-bottom: 10%; margin-top: 10%; font-size: 30px; font-style: oblique; font-family: serif; margin-right: 22%;"
		type="text" onkeyup="search(this)"></input>
	<table id="bookTable"
		style="width: 100%; text-align: center; font-style: oblique; font-size: 30px; font-family: serif; display: none;">
		<tr>
			<th>Book Title</th>
			<th>Language</th>
			<th>Author</th>
			<th>Delete</th>
		</tr>
	</table>
	<div id="noResults"
		style="width: 100%; text-align: center; font-style: oblique; font-size: 30px; font-family: serif;"></div>
</body>
</html>