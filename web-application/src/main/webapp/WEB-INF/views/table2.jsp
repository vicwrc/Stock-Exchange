<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>

<head>
<style>
.flat-table {
  display: block;
  font-family: sans-serif;
  -webkit-font-smoothing: antialiased;
  font-size: 115%;
  overflow: auto;
  width: auto;
  }
  
  th {
    background-color: rgb(112, 196, 105);
    color: white;
    font-weight: normal;
    padding: 20px 30px;
    text-align: center;
  }
  td {
    background-color: rgb(238, 238, 238);
    color: rgb(111, 111, 111);
    padding: 20px 30px;
  }
		}


</style>
</head>

<body>
	<table class="flat-table">
  <tbody>
    <tr>
      <th>Head 1</th>
      <th>Head 2</th>
      <th>Head 3</th>
      <th>Head 4</th>
      <th>Head 5</th>
    </tr>
    <tr>
      <td>Example 1</td>
      <td>Example 2</td>
      <td>Example 3</td>
      <td>Example 4</td>
      <td>Example 5</td>
    </tr>
    <tr>
      <td>Example 6</td>
      <td>Example 7</td>
      <td>Example 8</td>
      <td>Example 9</td>
      <td>Example 10</td>
    </tr>
  </tbody>
	</table>
</body>
</html>