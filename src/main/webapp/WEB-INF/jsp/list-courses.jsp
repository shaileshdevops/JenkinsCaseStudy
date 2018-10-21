<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<style>
	.table-striped
	{
		font-family:Calibri;
	    font-size:23px;
	}
	.btn-success
	{
		font-family:Calibri;
	    font-size:23px;
	}
	.btn-warning
	{
		font-family:Calibri;
	    font-size:23px;
	}
</style>	
	<div class="container">
		<table class="table table-striped">
			<caption>Current Enrolled Courses</caption>
			<thead>
				<tr>
					<th>Course Name</th>
					<th>Target Date</th>
					<th>Completed?</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${courseList}" var="course">
					<tr>
						<td>${course.desc}</td>
						<td><fmt:formatDate value="${course.targetDate}" pattern="dd/MM/yyyy"/></td>
						<td>${course.done}</td>
						<td><a type="button" class="btn btn-success"
							href="/update-course?id=${course.id}">Update</a></td>
						<td><a type="button" class="btn btn-warning"
							href="/delete-course?id=${course.id}">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div>
			<a class="button" href="/add-course">Add new Course</a>
		</div>
	</div>
<%@ include file="common/footer.jspf" %>