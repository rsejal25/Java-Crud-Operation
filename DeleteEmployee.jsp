<jsp:include page='/MasterPageTopSection.jsp' />
<script>
const urlParams=new URLSearchParams(window.location.search);
let employeeId=urlParams.get('employeeId');
window.addEventListener('load',loadPage);
function loadPage(){
document.getElementById('heading').innerHTML="<h1>Employee (Delete Module)</h1>";
document.getElementById('description').innerHTML="<h5>Are you sure you want to delete ??</h5>";
loadDesignations();
loadData();
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
for(var i=0;i<responseData.length;i=i+2)
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
let xhr=new XMLHttpRequest();
xhr.onreadystatechange=function(){
if(this.readyState==4 && this.status==200)
{
responseData=JSON.parse(this.responseText);

if(responseData.result=="error")
{
document.getElementById("description").innerHTML=responseData.errorString;
}
else
{
document.getElementById("name").remove();
document.getElementById("designationCode").remove();
document.getElementById("male").remove();
document.getElementById("female").remove();
document.getElementById("dateOfBirth").remove();
document.getElementById("isIndian").remove();
document.getElementById("panNumber").remove();
document.getElementById("aadharCardNumber").remove();
document.getElementById("basicSalary").remove();
document.getElementById("heading").innerHTML="Notification";
document.getElementById("description").innerHTML="Employee Deleted";
document.getElementById("employeeAddTable").remove();
let firstButton=document.getElementById("firstButton")
firstButton.remove();
//firstButton.innerHTML="Yes";
//firstButton.setAttribute('onclick','firstButtonAction();');
document.getElementById("secondButton").innerHTML="Ok";
}
}
};
xhr.open("GET","deleteEmployee?employeeId="+employeeId,true);
//xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
//alert(dataToSend);
xhr.send();
}
function loadData(){
alert('jjfjf');
let xhr=new XMLHttpRequest();
xhr.onreadystatechange=function(){
if(this.readyState==4 && this.status==200)
{
alert('djdfj');
responseData=JSON.parse(this.responseText);
if(responseData.result=="error")
{
document.getElementById("error").innerHTML=responseData.errorString;
}
else
{
let g=0;
let employeeId=responseData.employeeId;
let name=responseData.name;
let designationCode=responseData.designationCode;
let designation=responseData.designation;
let dateOfBirth=responseData.dateOfBirth;
let gender=responseData.gender;
let isIndian=responseData.isIndian;
let basicSalary=responseData.basicSalary;
let panNumber=responseData.panNumber;
let aadharCardNumber=responseData.aadharCardNumber;
console.log(isIndian);
console.log(designation);
document.getElementById("name").value=name;
document.getElementById("panNumber").value=panNumber;
document.getElementById("aadharCardNumber").value=aadharCardNumber;

if(isIndian=="true")
{
document.getElementById("isIndian").checked=true;
}
document.getElementById("basicSalary").value=basicSalary;
if(gender=='M')
{
document.getElementById("male").checked=true;
}
else
{
document.getElementById("female").checked=true;
}
document.getElementById("dateOfBirth").setAttribute('value',dateOfBirth);
let designations=document.getElementById("designationCode");
for(var i=0;i<designations.options.length;i++)
{
if(designations.options[i].value.localeCompare(designationCode)==0)
{
designations.selectedIndex=''+i;
break;
}
}
}
}
};
xhr.open('GET','editEmployee?employeeId='+employeeId,true);
xhr.send();
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
<td><button type='submit' onclick='addEmployee()' id='firstButton'>Delete</button></td>
<td><button type='Button' onclick='cancelAddition()' id='secondButton'>Cancel</button></td>
</tr>
</table>
<form action='/stylethree/Employees.jsp' id='cancelAddition'></form>
<form action='/stylethree/AddEmployee.jsp' id='firstButtonAction'></form>
<jsp:include page='/MasterPageBottomSection.jsp' />