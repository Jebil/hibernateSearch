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
		if (query.value != '') {

			$
					.post(
							"getSearch",
							{
								query : query.value
							},
							function(entry) {
								if (entry.length > 0) {
									entry
											.forEach(function(data) {
												$('#bookTable')
														.append(
																'<tr><td>'
																		+ data.title
																		+ '</td><td>'
																		+ data.date
																		+ '</td><td>'
																		+ data.language
																		+ '</td><td>'
																		+ data.author.name
																		+ '</td><td><input type="button" name="delete" value="Delete"onclick="deleteBook('
																		+ data.id
																		+ ')"></td></tr>');
											})
								}
							})
		}
	}
</script>
</head>
<body>
	<input type="text" onkeyup="search(this)"></input>

	<table border="1px" id="bookTable">
		<tr>
			<th>Book Title</th>
			<th>Published Date</th>
			<th>Language</th>
			<th>Author</th>
			<th>Add/Delete</th>
		</tr>
	</table>
</body>
</html>