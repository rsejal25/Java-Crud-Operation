<jsp:include page='/MasterPageTopSection.jsp' />
<script>
function myFunction()
{
let xhr=new XMLHttpRequest();
xhr.onreadystatechange=function(){
if(this.readyState==4)
{
if(this.status==200)
{
let responseData=this.responseText;
var designations=JSON.parse(responseData);
let j=1;
let table=document.getElementById("tableId");
let tbody=document.querySelectorAll("tbody")[0];
for(var i=0;i<designations.length;i++)
{
let row=document.createElement("tr");
let cell=document.createElement("td");
let cellText=document.createTextNode(j);
cell.append(cellText);
row.appendChild(cell);
cell=document.createElement("td");
cellText=document.createTextNode(designations[i].title);
cell.append(cellText);
row.appendChild(cell);
tbody.appendChild(row);
cell=document.createElement("td");
let link=document.createElement("a");
cellText=document.createTextNode("Update");
link.append(cellText);
link.href="/stylethree/EditDesignation.jsp?code="+designations[i].code;
cell.appendChild(link);
row.appendChild(cell);
cell=document.createElement("td");
link=document.createElement("a");
cellText=document.createTextNode("delete");
link.append(cellText);
link.href="/stylethree/confirmDelete.jsp?code="+designations[i].code;
cell.appendChild(link);
row.appendChild(cell);
j++;
}
table.setAttribute("border",3);
}
}
};
xhr.open('GET','designations',true);
xhr.send();
}
window.addEventListener('load',myFunction);
</script>
<table id='tableId'>
<thead>
<tr>
<th colspan='4' style='text-align:right;padding:5px'>
<a href='/stylethree/AddDesignations.jsp'>Add Designations</a>
</th>
</tr>
<tr>
<th>S.NO</th>
<th>Designation</th>
<th>Edit</th>
<th>Delete</th>
</tr>
</thead>
<tbody>
</tbody>
</table>
<jsp:include page='/MasterPageBottomSection.jsp' />