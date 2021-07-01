<jsp:include page='/MasterPageTopSection.jsp' />
<script>
window.addEventListener('load',loadPage);
function loadPage(){
document.getElementById('heading').innerHTML="<h1>Employee (AddModule)</h1>";
document.getElementById('description').innerHTML="<h3>Employee</h3>";
loadDesignations();
}
function loadDesignations()
{
let xhr=new XMLHttpRequest();
xhr.onreadystatechange=function(){
if(this.readyState==4)
{
if(this.status==200)
{
responseData=JSON.parse(this.responseText);
let select=document.getElementById("designationCode");
let tbody=document.querySelectorAll("tbody")[0];
for(var i=0;i<responseData.length;i++)
{
let option=document.createElement("option");
option.value=responseData[i].code;
option.text=responseData[i].title;
select.appendChild(option);
}
}
}
};
xhr.open('GET','designations',true);
xhr.send();
}

function addEmployee(){
let name=document.getElementById('name').value;
let designationCode=document.getElementById('designationCode').value;
let aadharCardNumber=document.getElementById('aadharCardNumber').value;
let panNumber=document.getElementById('panNumber').value;
let basicSalary=document.getElementById("basicSalary").value;
let gender="";
if(document.getElementById("male").checked)
{
gender=document.getElementById("male").value;
}
else
{
gender=document.getElementById("female").value;
}
let dateOfBirth=document.getElementById("dateOfBirth").value;
let isIndian=document.getElementById("isIndian").checked;
var employee={
"name":name,
"aadharCardNumber":aadharCardNumber,
"panNumber":panNumber,
"gender":gender,
"designationCode":designationCode,
"isIndian":isIndian,
"dateOfBirth":dateOfBirth,
"basicSalary":basicSalary
}
let xhr=new XMLHttpRequest();
xhr.onreadystatechange=function(){
if(this.readyState==4 && this.status==200)
{
var responseData=this.responseText;
res=responseData.split(",");
if(responseData.includes("error"))
{
if(responseData.includes("PanNumber"))
{
document.getElementById("panNumberErrorSection").innerHTML=res[2];
}
if(responseData.includes("AadharCardNumber"))
{
document.getElementById("aadharCardNumberErrorSection").innerHTML=res[2];
}
if(responseData.includes("DesignationCode"))
{
document.getElementById("designationCodeErrorSection").innerHTML=res[2];
}
}
else
{
responseData=responseData.split(",");
document.getElementById("name").remove();
document.getElementById("designationCode").remove();
document.getElementById("male").remove();
document.getElementById("female").remove();
document.getElementById("dateOfBirth").remove();
document.getElementById("isIndian").remove();
document.getElementById("panNumber").remove();
document.getElementById("aadharCardNumber").remove();
document.getElementById("basicSalary").remove();
document.getElementById("heading").innerHTML="Employee Added";
document.getElementById("description").innerHTML="Do you want to add more???";
document.getElementById("employeeAddTable").remove();
let firstButton=document.getElementById("firstButton");
firstButton.innerHTML="Yes";
firstButton.setAttribute('onclick','firstButtonAction();');
document.getElementById("secondButton").innerHTML="No";
}
}
};
xhr.open("POST","addEmployee",true);
xhr.setRequestHeader("Content-Type","application/json");
//alert(dataToSend);
xhr.send(JSON.stringify(employee));
}
function cancelAddition()
{
document.getElementById('cancelAddition').submit();
}
function firstButtonAction(){
document.getElementById("firstButtonAction").submit();
}
</script>
<div id='heading'></div>
<div id='description'></div>
<table id='employeeAddTable'>
<tr>
<td>Name</td>
<td><input type='text' id='name' name='name'>
<span id='nameErrorSection' style="color:red"></span>
</td>
</tr>
<tr>
<td>Designation</td>
<td><select id='designationCode' name='designationCode'>
<option value='-1'>Select Designation</option>
</select>
<span id='designationCodeErrorSection' style='color:red'></span>
</td>
</tr>
<tr>
<td>Date of Birth</td>
<td><input type='date' id='dateOfBirth' name='dateOfBirth'>
<span id='dateOfBirthErrorSection' style='color:red'></span>
</td>
</tr>
<tr>
<td>Indian ?</td>
<td><input type='checkbox' id='isIndian' name='isIndian'>
</td>
</tr>
<tr>
<td>Basic Salary</td>
<td><input type='text' id='basicSalary' name='basicSalary'>
<span id='basicSalaryErrorSection' style='color:red'></span>
</td>
</tr>
<tr>
<td>PAN Number</td>
<td><input type='text' id='panNumber' name='panNumber'>
<span id='panNumberErrorSection' style='color:red'></span>
</td>
</tr>
<tr>
<td>Aadhar Card</td>
<td><input type='text' id='aadharCardNumber' name='aadharCardNumber'>
<span id='aadharCardNumberErrorSection' style='color:red'></span>
</td>
</tr>
<tr>
<td>Gender</td>
<td><input type='radio' id='male' name='gender' value='M'>
Male
&nbsp;&nbsp;&nbsp;
<td><input type='radio' id='female' name='gender' value='F'>
Female
<span id='genderErrorSection' style='color:red'></span>
</td>
</tr>
</table>
</form>
<table>
<tr>
<td><button type='submit' onclick='addEmployee()' id='firstButton'>ADD</button></td>
<td><button type='Button' onclick='cancelAddition()' id='secondButton'>Cancel</button></td>
</tr>
</table>
<form action='/stylethree/Employees.jsp' id='cancelAddition'></form>
<form action='/stylethree/AddEmployee.jsp' id='firstButtonAction'></form>
<jsp:include page='/MasterPageBottomSection.jsp' />