<jsp:include page='MasterPageTopSection.jsp' />
<script>
function cancelEdition(){
document.getElementById("cancelEdit").submit();
}
function pageLoader(){
alert('jjfjf');
let xhr=new XMLHttpRequest();
xhr.onreadystatechange=function(){
if(this.readyState==4 && this.status==200)
{
alert('djdfj');
let responseData=JSON.parse(this.responseText);
if(responseData.result=="error")
{
document.getElementById("error").innerHTML="invalid data";
}
else
{
document.getElementById("heading").innerHTML="Designation Delete Module";
document.getElementById("description").innerHTML="Delete Designation?";
document.getElementById("code").value=responseData.code;
document.getElementById("title").innerHTML=responseData.title;
}
}
};
const urlParams=new URLSearchParams(window.location.search);
let code=urlParams.get('code');
xhr.open('GET','editDesignation?code='+code,true);
xhr.send();
}
function editDesignation(){
let code=document.getElementById("code").value;
let title=document.getElementById("title").value;
let xhr=new XMLHttpRequest();
xhr.onreadystatechange=function(){
if(this.readyState==4 && this.status==200)
{
let responseData=JSON.parse(this.responseText);
if(responseData.result=="error")
{
document.getElementById("errorSection").innerHTML="cannot delete";
document.getElementById("title").focus();
}
else
{
document.getElementById("description").innerHTML="Designation Deleted";
document.getElementById("secondButton").remove();
firstButton=document.getElementById("firstButton");
firstButton.innerHTML="Ok";
firstButton.setAttribute("onclick","cancelEdition();");
document.getElementById("title").remove();
}
}
};
xhr.open('GET','deleteDesignation?code='+code,true);
//xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
xhr.send();
}
window.addEventListener('load',pageLoader);
</script>
<div id='heading'></div>
<span id='description'></span>
<p id='title'></p>
<input type='hidden' id='code'>
<span id='errorSection'></span>
<button type='button' onclick='editDesignation()' id='firstButton'>Delete</button>
<button type='button' onclick='cancelEdition()' id='secondButton'>Cancel</button>
<form action='/stylethree/Designations.jsp' id='cancelEdit'></form>
<jsp:include page='MasterPageBottomSection.jsp' />