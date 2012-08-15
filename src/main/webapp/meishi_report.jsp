<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/kernel.css" type="text/css">

<script type="text/javascript" src="<%=request.getContextPath()%>/my97/WdatePicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/kernel.js"></script>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	function query_total_count(){
		//count_of_day
		var count_of_day_URL = 'report.jsp?1=1';
		count_of_day_URL += '&jasper=date_count.jasper';
		count_of_day_URL += '&date_start=' + encodeURIComponent(Kernel.Common.getDomById('indateStart').value);
		Kernel.Common.getDomById('countofday_ifm').src = count_of_day_URL;
		window.setInterval("reinitIframe('countofday_ifm')", 200);
		
		//count_of_day chart
		var count_of_day_URL = 'report.jsp?1=1';
		count_of_day_URL += '&jasper=date_count_chart.jasper';
		count_of_day_URL += '&date_start=' + encodeURIComponent(Kernel.Common.getDomById('indateStart').value);
		Kernel.Common.getDomById('countofday_chart_ifm').src = count_of_day_URL;
		window.setInterval("reinitIframe('countofday_chart_ifm')", 200);
	}
	
	function reinitIframe(fm_id){
		var iframe = document.getElementById(fm_id);
		try{
			var bHeight = iframe.contentWindow.document.body.scrollHeight;
			var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
			var height = Math.max(bHeight, dHeight);
			iframe.height = height;
			
			var bWidth = iframe.contentWindow.document.body.scrollWidth;
			var dWidth = iframe.contentWindow.document.documentElement.scrollWidth;
			var width = Math.max(bWidth, dWidth);
			iframe.width = width;
		}catch (ex){}
	}
	
</script>
</head>
<body>
	<table border="1">
		<tr>
			<td colspan="2"><b>总计报表</b></td>
		</tr>
		<tr>
			<td colspan="2"><font size="2">时间区间选择</font></td>
		</tr>
		<tr>
			<td>
				<font size="2">时间从:</font>
				<input type="text" id="indateStart" class="Wdate" onfocus="new WdatePicker({dateFmt:'yyyy/MM/dd',isShowClear:true,readOnly:true})"/>
			</td>
			<td>
				<font size="2">时间到:</font>
				<input type="text" id="indateEnd" class="Wdate" onfocus="new WdatePicker({dateFmt:'yyyy/MM/dd',isShowClear:true,readOnly:true})"/>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="button" name="sub_but" value="提交" onclick="query_total_count();"/>
			</td>
		</tr>
	</table>
	
	<iframe id="countofday_ifm" src="#" scrolling="no" frameborder="0" ></iframe>
	<iframe id="countofday_chart_ifm" src="#" scrolling="no" frameborder="0" ></iframe>
</body>
</html>