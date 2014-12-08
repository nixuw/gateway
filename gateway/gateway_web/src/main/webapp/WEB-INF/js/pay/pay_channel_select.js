
function setBank(bankCode){
	$('#channelId').val($('#ALIPAY_value').val());
	$('#channelCode').val('ALIPAY');
	$('#bankCode').val(bankCode);
}

$(function(){
	$('#star').raty({
		hintList:  ['1', '2', '3', '4', '5'],   //评分分数
		number: 5,				//显示星星数量
		path: "../common/images/star",         //星星图片路径
		starOff: 'score-off.png',    //指针离开时星星的图片
		starOn: 'score-on.png',      //指针在星星上方时星星的图片
		size: 16,		//图片大小
		//readOnly: true,       //只读，不可编辑
		score: 3,            //初始化显示评分，默认为0	
		space: false      //星星间距,默认为true
	});
});

function dialog(dom){
	if(isRadio()){
		tipsWindown({
			title:"网上支付提示",
			content:"id:dialog",
			width:550,
			height:200,
			drag:"false",
			showbg:"true",
			btn:"false",
			windowclose:"false"
		});
		$('#payForm').submit();
	}
}

function closePopWindow(){
	closeWindown();
	showselect('t123_');
	$("#windownbg").remove();
	$("#windown-box").fadeOut("slow",function(){$(this).remove();});
}

function closewindow(){
	if (navigator.userAgent.indexOf("MSIE") > 0) {  
        if (navigator.userAgent.indexOf("MSIE 6.0") > 0) {  
            window.opener = null; window.close();  
        }  
        else {  
            window.open('', '_top'); 
            window.top.close();  
        }  
    }  
    else if (navigator.userAgent.indexOf("Firefox") > 0) {  
       // window.location.href = 'about:blank ';  
    	window.open('','_parent','');  
    	window.close(); 
    }  
    else {  
        window.opener = null;   
        window.open('', '_self', '');  
        window.close();  
    }  
}

function callBack(flag){
	var isSuccess = flag;
	var requestId = $("#requestId").val();
	var href = $("#returnHref").val();
	var hrefFrom = href+"?requestId="+requestId+"&isSuccess="+isSuccess;
	$("#returnForm").attr("action", hrefFrom);
	$("#returnForm").submit();
}

//是否选择支付方式
function isRadio(){
	var channelId =  $('#channelId').val();
	if(channelId==""||channelId=="null"||channelId==null){
		alert("您好，请选择支付平台！");
		return false;
	}else{
		return true;
	}	
}