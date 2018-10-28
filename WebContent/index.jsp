<%@page import="com.nseapiweb.api.NSEAPI"%>
<jsp:declaration>
  String symbols = "TCS,TATAMOTORS";
</jsp:declaration>

 <html>
 <head>
  <title>Pivot Trading</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
   <body>
   <div class="jumbotron text-center">
  	 <h1>Pivot Trading</h1> 
	</div>
<div class="container">
  <div class="row">
    <div class="col-sm-9">
   <table class="table table-striped">
   <thead>
   <tr>
   <th>Symbol</th>
   <th>Close</th>
   <th>Previous Close</th>
   <th>Previous Low</th>
   <th>Previous High</th>
   <th>Pivot Point</th>
   <th>S1</th>
   <th>R1</th>
   </tr>
   </thead>
   <tbody>
   <%
   for(String symbol : symbols.split(",")){ 
   String symbolName = (String) NSEAPI.getQuoteInfo(symbol).get("symbol");
   String close = (String) NSEAPI.getQuoteInfo(symbol).get("close").toString().replaceAll(",", "");
   Float previousClose = Float.parseFloat(NSEAPI.getHistoricalInfo(symbol).get("previousClose").toString().replaceAll(",", ""));
   Float priviousLow = Float.parseFloat(NSEAPI.getHistoricalInfo(symbol).get("priviousLow").toString().replaceAll(",", ""));
   Float priviousHigh = Float.parseFloat(NSEAPI.getHistoricalInfo(symbol).get("priviousHigh").toString().replaceAll(",", ""));
   Float pivotPoint = (previousClose + priviousLow + priviousHigh)/3;
   Float s1 = 2*pivotPoint - priviousHigh; 
   Float r1 = 2*pivotPoint -priviousLow;
   
   %>
   <tr>
   <td><%=  symbolName %></td>
   <td><%=  close %> </td>
   <td><%=  String.format("%.2f", previousClose) %></td>
   <td><%=  String.format("%.2f", priviousLow) %></td>
   <td><%= String.format("%.2f", priviousHigh) %></td>
   <td><%= String.format("%.2f", pivotPoint) %></td>
   <td><%= String.format("%.2f", s1) %></td>
   <td><%= String.format("%.2f", r1) %></td>
   </tr>
   <% } %>
   
   </tbody>
   </table>
      </div>
      </div>
      </div>
   </body>
</html>
