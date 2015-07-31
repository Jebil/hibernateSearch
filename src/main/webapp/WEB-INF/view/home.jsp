<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Listing</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>
	function addBook(){
		var title=$('#title').val();
		var language=$('#language').val();
		var language=$('#language').val();
		var author=$('#author').val();
		$.post("addBook",{title:title,language:language,author:author},function(data){
			$('#bookTable').append('<tr><td>'+data.title+'</td><td>'+data.language+'</td><td>'+data.author.name+'</td><td><input type="button" name="delete" value="Delete"onclick="deleteBook('+data.id+')"></td></tr>');
		})
	}
	
	function deleteBook(id){
		$.post("deleteBook",{id:id},function(){
			location.reload();
		})
	}
</script>
</head>
<body>
	<table id="bookTable" style="width: 100%; text-align: center; font-style: oblique; font-size: 30px; font-family: serif;">
		<tr>
			<th>Book Title</th>
			<th>Language</th>
			<th>Author</th>
			<th>Add/Delete</th>
		</tr>
		<tr>
			<td><input type="text" id="title"></td>
			<td><input type="text" id="language"></td>
			<td><input type="text" id="author"></td>
			<td><input type="button" name="Add" value="Add"
				onclick="addBook()"></td>
		</tr>
		<c:forEach var="book" items="${bookList }">
			<tr>
				<td>${book.title}</td>
				<td>${book.language}</td>
				<td>${book.author.name}</td>
				<td><input type="button" name="delete" value="Delete"
					onclick="deleteBook(${book.id})"></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>