<!DOCTYPE HTML>
<html>
<head>
<meta charset='utf-8'>
<link rel='stylesheet' type='text/css' href='/styletwo1/css/styles.css'>
<script>
function myfunction(){
let xhr=new XMLHttpRequest();
xhr.onreadystatechange=function(){
if(this.readyState==4 && this.status==200)
{
let response=this.responseText;
if(response.includes("error"))
{
document.getElementById("login").submit();
}
else
{
response=response.split(",");
document.getElementById('username').innerHTML=response[0];
}
}
};
xhr.open("GET","guard",true);
xhr.send();
}
function logout(){
alert('hellog');
let xhr=new XMLHttpRequest();
xhr.onreadystatechange=function(){
if(this.readyState==4 && this.status==200)
{
document.getElementById("login").submit();
}
};
xhr.open('Get','logout',true);
xhr.send();
}
window.addEventListener('load',myfunction);
</script>
</head>
<body>
<form action="/stylethree/LoginForm.jsp" id='login'></form>
<div class='main-container'>
<div class='header'>
<img src='/styletwo1/image/logo.png' class='logo'>
<div class='brand-name'>Thinking Machine</div>
<div class="login">
<p id='username'></p>
<button type='button' onclick='logout()'>logout</button>
</div>
</div>
<div class='content'>
<div class='content-left-panel'>
<b>Designation</b>
<a href='/stylethree/Designations.jsp'>Designations</a><br>
<b>Employee</b>
<a href='/stylethree/Employees.jsp'>Employees</a><br>
<a href='/stylethree/index.jsp'>Home</a><br>
<b>Home</b>
</div>
<div class='content-right-panel'>
