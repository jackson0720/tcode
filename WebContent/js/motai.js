//第一步点击p标签出现modal模态框
//第二部实现tab选项卡
//遍历tab
//去掉当前所有的on属性
//给当前的span添加on
//去掉所有的form
//让当前form显示出来

window.onload = function(){
    let btn =  document.getElementById('phone');//获取打开模态框的按钮
    let out = document.getElementById("out");  //模态框的显示隐藏
    let close = document.getElementsByClassName("close")[0]; //获取关闭按钮

    let spans = document.getElementsByClassName("tab");  //获取tab标题
    let divs = document.getElementsByClassName("content"); //获取tab内容


  //模态框的打开与关闭
    btn.onclick  = function(){
        out.style.display = "block"
    }
    close.onclick = function(){
        out.style.display ="none"
    }
    for(let i = 0;i<spans.length;i++){
        spans[i].onclick = function(){
            for(let j = 0;j<spans.length;j++){
                divs[j].style.display = "none";
                spans[j].style.borderColor ="transparent";
                spans[j].style.color ="";
            }
            this.style.borderColor = "#ff7500";  
            divs[i].style.display = "block";
            this.style.color = "#ff7500";
        }
    }

}