<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	${result}입니다.
	${list[0]}
	${list[1]}
	${notice.b}<br>
	${empty param.n? '값이 비어있습니다.':param.n}<br>
	${param.n/2}<br>
	${header.accept}
</body>
</html>