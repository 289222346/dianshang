<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>commodity_detailed_info</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<t:base type="bootstrap,bootstrap-table,layer,validform,bootstrap-form"></t:base>
</head>
 <body style="overflow:hidden;overflow-y:auto;">
 <div class="container" style="width:100%;">
	<div class="panel-heading"></div>
	<div class="panel-body">
	<form class="form-horizontal" role="form" id="formobj" action="commodityDetailedInfoController.do?doUpdate" method="POST">
		<input type="hidden" id="btn_sub" class="btn_sub"/>
		<input type="hidden" id="id" name="id" value="${commodityDetailedInfo.id}"/>
	<div class="form-group">
		<label for="countInfo" class="col-sm-3 control-label">产品件装：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input id="countInfo" name="countInfo" value='${commodityDetailedInfo.countInfo}' type="text" maxlength="128" class="form-control input-sm" placeholder="请输入产品件装"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="validityPeriod" class="col-sm-3 control-label">产品效期：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
      		    <input id="validityPeriod" name="validityPeriod" type="text" class="form-control input-sm laydate-date" placeholder="请输入产品效期"  ignore="ignore"  value="<fmt:formatDate pattern='yyyy-MM-dd' type='date' value='${commodityDetailedInfo.validityPeriod}'/>" />
                <span class="input-group-addon" ><span class="glyphicon glyphicon-calendar"></span></span>
			</div>
		</div>
	</div>
					<div class="form-group">
						<label for="manufactor" class="col-sm-3 control-label">生产企业：</label>
						<div class="col-sm-7">
			          <div class="input-group" style="width:100%">
						  	 	<textarea name="manufactor" class="form-control input-sm" rows="6"  ignore="ignore" >${commodityDetailedInfo.manufactor}</textarea>
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">生产企业</label>
			          </div>
						</div>
					<div class="form-group">
						<label for="specification" class="col-sm-3 control-label">产品规格：</label>
						<div class="col-sm-7">
			          <div class="input-group" style="width:100%">
						  	 	<textarea name="specification" class="form-control input-sm" rows="6"  ignore="ignore" >${commodityDetailedInfo.specification}</textarea>
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">产品规格</label>
			          </div>
						</div>
					<div class="form-group">
						<label for="approvalNumber" class="col-sm-3 control-label">批准文号：</label>
						<div class="col-sm-7">
			          <div class="input-group" style="width:100%">
						  	 	<textarea name="approvalNumber" class="form-control input-sm" rows="6"  ignore="ignore" >${commodityDetailedInfo.approvalNumber}</textarea>
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">批准文号</label>
			          </div>
						</div>
					<div class="form-group">
						<label for="imagePath" class="col-sm-3 control-label">详情图片文件夹地址：</label>
						<div class="col-sm-7">
			          <div class="input-group" style="width:100%">
						  	 	<textarea name="imagePath" class="form-control input-sm" rows="6"  ignore="ignore" >${commodityDetailedInfo.imagePath}</textarea>
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">详情图片文件夹地址</label>
			          </div>
						</div>
					<div class="form-group">
						<label for="instructionsUrl" class="col-sm-3 control-label">说明书文件地址：</label>
						<div class="col-sm-7">
			          <div class="input-group" style="width:100%">
						  	 	<textarea name="instructionsUrl" class="form-control input-sm" rows="6"  ignore="ignore" >${commodityDetailedInfo.instructionsUrl}</textarea>
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">说明书文件地址</label>
			          </div>
						</div>
	</form>
	</div>
 </div>
<script type="text/javascript">
var subDlgIndex = '';
$(document).ready(function() {
	$(".laydate-datetime").each(function(){
		var _this = this;
		laydate.render({
		  elem: _this,
		  format: 'yyyy-MM-dd HH:mm:ss',
		  type: 'datetime',
		  ready: function(date){
		  	 $(_this).val(DateJsonFormat(date,this.format));
		  }
		});
	});
	$(".laydate-date").each(function(){
		var _this = this;
		laydate.render({
		  elem: _this,
		  format: 'yyyy-MM-dd',
		  ready: function(date){
		  	 $(_this).val(DateJsonFormat(date,this.format));
		  }
		});
	});
	//单选框/多选框初始化
	$('.i-checks').iCheck({
		labelHover : false,
		cursor : true,
		checkboxClass : 'icheckbox_square-green',
		radioClass : 'iradio_square-green',
		increaseArea : '20%'
	});
	
	//表单提交
	$("#formobj").Validform({
		tiptype:function(msg,o,cssctl){
			if(o.type==3){
				validationMessage(o.obj,msg);
			}else{
				removeMessage(o.obj);
			}
		},
		btnSubmit : "#btn_sub",
		btnReset : "#btn_reset",
		ajaxPost : true,
		beforeSubmit : function(curform) {
		},
		usePlugin : {
			passwordstrength : {
				minLen : 6,
				maxLen : 18,
				trigger : function(obj, error) {
					if (error) {
						obj.parent().next().find(".Validform_checktip").show();
						obj.find(".passwordStrength").hide();
					} else {
						$(".passwordStrength").show();
						obj.parent().next().find(".Validform_checktip").hide();
					}
				}
			}
		},
		callback : function(data) {
			var win = frameElement.api.opener;
			if (data.success == true) {
				frameElement.api.close();
			    win.reloadTable();
			    win.tip(data.msg);
			} else {
			    if (data.responseText == '' || data.responseText == undefined) {
			        $.messager.alert('错误', data.msg);
			        $.Hidemsg();
			    } else {
			        try {
			            var emsg = data.responseText.substring(data.responseText.indexOf('错误描述'), data.responseText.indexOf('错误信息'));
			            $.messager.alert('错误', emsg);
			            $.Hidemsg();
			        } catch (ex) {
			            $.messager.alert('错误', data.responseText + "");
			            $.Hidemsg();
			        }
			    }
			    return false;
			}
		}
	});
});
</script>
</body>
</html>