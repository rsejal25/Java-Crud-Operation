<jsp:include page='/MasterPageTopSection.jsp' />
<script>
function Employee(){
this.name="";
this.employeeId="";
this.panNumber="";
this.aadharCardNumber="";
this.designation="";
this.basicSalary="";
this.employeeId="";
this.isIndian=false;
this.dateOfBirth=null;
this.designationCode=0;
}
var employees=[];
var employee;
function myfunction(){
let xhr=new XMLHttpRequest();
xhr.onreadystatechange=function(){
if(this.readyState==4 && this.status==200)
{
//employees=new Employee();
let responseData=JSON.parse(this.responseText);
//employees=new Employee();
var i=0;
var k=0;
while(i<responseData.length)
{
employee=new Employee();
employee.employeeId=responseData[i].employeeId;
employee.name=responseData[i].name;
employee.designationCode=responseData[i].designationCode;
employee.designation=responseData[i].designation;
employee.dateOfBirth=responseData[i].dateOfBirth;
employee.gender=responseData[i].gender;
employee.isIndian=responseData[i].isIndian;
employee.basicSalary=responseData[i].basicSalary;
employee.panNumber=responseData[i].panNumber;
employee.aadharCardNumber=responseData[i].aadharCardNumber;
console.log(employee.aadharCardNumber);
employees[k]=employee;
i++;
k++;
}
populateEmployeesGridTable();
}
};
xhr.open('GET','employees',true);
xhr.send();
}
function populateEmployeesGridTable(){
console.log('hhh');
var employeeGridTable=document.getElementById("employeesGridTable");
var employeeGridBody=employeeGridTable.getElementsByTagName("tbody")[0];
var employeeGridTableBodyRowTemplate=employeeGridBody.getElementsByTagName("tr")[0];
employeeGridTableBodyRowTemplate.remove();
var employeeGridTableColumnTemplateCollection=employeeGridTableBodyRowTemplate.getElementsByTagName("th");
var cellTemplate;
var k;
var dynamicRowCells;
var placeHolderFor;
console.log('dnjndjkd');
console.log(employees);
console.log(employees.length);
for(k=0;k<employees.length;k++)
{
dynamicRow=employeeGridTableBodyRowTemplate.cloneNode(true);
employeeGridBody.appendChild(dynamicRow);
console.log('djfj');
dynamicRowCells=dynamicRow.getElementsByTagName("td");
for(var i=0;i<dynamicRowCells.length;i++)
{
console.log('hello');
cellTemplate=dynamicRowCells[i];
placeHolderFor=cellTemplate.getAttribute("placeHolderId");
if(placeHolderFor==null) continue;
if(placeHolderFor=="serialNumber") cellTemplate.innerHTML=(k+1);
if(placeHolderFor=="designationCode") cellTemplate.innerHTML=employees[k].designationCode;
if(placeHolderFor=="name") cellTemplate.innerHTML=employees[k].name;

if(placeHolderFor=="designation") cellTemplate.innerHTML=employees[k].designation;
if(placeHolderFor=="dateOfBirth") cellTemplate.innerHTML=employees[k].dateOfBirth;
if(placeHolderFor=="gender") cellTemplate.innerHTML=employees[k].gender;
if(placeHolderFor=="isIndian") cellTemplate.innerHTML=employees[k].isIndian;
if(placeHolderFor=="aadharCardNumber") cellTemplate.innerHTML=employees[k].aadharCardNumber;
if(placeHolderFor=="panNumber") cellTemplate.innerHTML=employees[k].panNumber;
if(placeHolderFor=="basicSalary") cellTemplate.innerHTML=employees[k].basicSalary;
if(placeHolderFor=="employeeId") cellTemplate.innerHTML=employees[k].employeeId;
if(placeHolderFor=="editOption") cellTemplate.innerHTML="<a href='/stylethree/EditEmployee.jsp?employeeId="+employees[k].employeeId+"'>Edit</a>";
if(placeHolderFor=="deleteOption") cellTemplate.innerHTML="<a href='/stylethree/DeleteEmployee.jsp?employeeId="+employees[k].employeeId+"'>delete</a>";
}
dynamicRow.onclick=createDynamicRowHandler(dynamicRow,employees[k].employeeId);
}
}
function createDynamicRowHandler(rowAdd,employeeId){
return function(){
selectEmployee(rowAdd,employeeId);
}
}
var selectedRow=null;
function selectEmployee(row,employeeId)
{
if(selectedRow==row) return;
if(selectedRow!=null)
{
selectedRow.style.background="white";
selectedRow.style.color="black";
}
row.style.background='#7C7B7B';
row.style.color="white";
selectedRow=row;
var i;
for(i=0;i<employees.length;i++)
{
if(employees[i].employeeId==employeeId)
{
break;
}
}
var emp=employees[i];
document.getElementById('detailPanel_employeeId').innerHTML=emp.employeeId;
document.getElementById('detailPanel_name').innerHTML=emp.name;
document.getElementById('detailPanel_designation').innerHTML=emp.designation;
document.getElementById('detailPanel_dateOfBirth').innerHTML=emp.dateOfBirth;
document.getElementById('detailPanel_gender').innerHTML=emp.gender;
console.log(emp.isIndian+emp.name);
if(emp.isIndian==true)
{
document.getElementById('detailPanel_isIndian').innerHTML="Yes";
}
else
{
document.getElementById('detailPanel_isIndian').innerHTML="No";
}
document.getElementById('detailPanel_panNumber').innerHTML=emp.panNumber;
document.getElementById('detailPanel_aadharCardNumber').innerHTML=emp.aadharCardNumber;
document.getElementById('detailPanel_basicSalary').innerHTML=emp.basicSalary;
}
window.addEventListener('load',myfunction);
</script>
<div class='employeeGrid'>
<table border='1' id='employeesGridTable'>
<thead>
<tr>
<th colspan='6' class='employeeGridHeader'>
<a href='/stylethree/AddEmployee.jsp'>Add Employee</a>
</th>
</tr>
<tr>
<th class='employeeGridSNOColumnTitle'>S.NO</th>
<th class='employeeGridIdColumnTitle'>Id.</th>
<th class='employeeGridNameColumnTitle'>Name</th>
<th class='employeeGridDesignationColumnTitle'>Designation</th>
<th class='employeeGridEditOptionColumnTitle'>Edit</th>
<th class='employeeGridDeleteOptionColumnTitle'>Delete</th>
</tr>
</thead>
<tbody>
<tr style='cursor:pointer'>
<td style='text-align:right' placeHolderId='serialNumber'></td>
<td placeHolderId='employeeId'></td>
<td placeHolderId='name'></td>
<td placeHolderId='designation'></td>
<td placeHolderId='editOption' style='text-align:center'></td>
<td placeHolderId='deleteOption' style='text-align:center'></td>
</tr>
</tbody>
</table>
</div>
<div style='height:17vh;margin-top:5px;padding:5px;margin-right:5px;margin-bottom:5px;margin-left:5px;border:1px solid black'>
<label style='background:gray;color:white;padding-right:5px;padding-left:5px'>Details</label>
<table border='0' width='100%'>
<tr>
<td>Employee Id : <span id='detailPanel_employeeId' style='margin-right:30px'></span></td>
<td>Name : <span id='detailPanel_name' style='margin-right:30px'></span></td>
<td>Designation : <span id='detailPanel_designation' style='margin-right:30px'></span></td>
</tr>
<tr>
<td>Date of birth : <span id='detailPanel_dateOfBirth' style='margin-right:30px'></span></td>
<td>Gender : <span id='detailPanel_gender' style='margin-right:30px'></span></td>
<td>Is Indian : <span id='detailPanel_isIndian' style='margin-right:30px'></span></td>
</tr>
<tr>
<td>Basic salary : <span id='detailPanel_basicSalary' style='margin-right:30px'></span></td>
<td>PAN Number : <span id='detailPanel_panNumber' style='margin-right:30px'></span></td>
<td>Aadhar card Number : <span id='detailPanel_aadharCardNumber' style='margin-right:30px'></span></td>
</tr>
</table>
<jsp:include page='/MasterPageBottomSection.jsp' />