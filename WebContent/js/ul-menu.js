var oUl1 = document.getElementsByTagName("ul")[0];
var aLi1 = oUl1.children;
var aUl2 = oUl1.getElementsByTagName("ul");
for(var i=0;i<aLi1.length;i++){
	aLi1[i].onmouseover = function(){
		this.getElementsByTagName("ul")[0].style.display = "block";
		this.style.backgroundColor = "transparent";
	}
}
for(var i=0;i<aLi1.length;i++){
	aLi1[i].onmouseout = function(){
		this.getElementsByTagName("ul")[0].style.display = "none";
		this.style.backgroundColor = "transparent";
	}
}
for(var i=0;i<aUl2.length;i++){
	var aLi2 = aUl2[i].getElementsByTagName("li");
	for(var j=0;j<aLi2.length;j++){
		aLi2[j].onmouseover = function(){
			this.getElementsByTagName("a")[0].style.color = "#1CC7D0";
		}
		aLi2[j].onmouseout = function(){
			this.getElementsByTagName("a")[0].style.color = "white";
		}
	}
}