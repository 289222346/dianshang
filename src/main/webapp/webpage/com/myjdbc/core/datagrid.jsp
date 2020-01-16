<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="/lib/jquery-easyui-1.8.8/themes/bootstrap/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="/lib/jquery-easyui-1.8.8/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="/lib/jquery-easyui-1.8.8/themes/color.css"/>
    <script type="text/javascript" src="/lib/jquery-easyui-1.8.8/jquery.min.js"></script>
    <script type="text/javascript" src="/lib/jquery-easyui-1.8.8/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/lib/jquery-easyui-1.8.8/locale/easyui-lang-zh_CN.js"></script>

    <link rel="stylesheet" type="text/css" href="/plug-in/ace/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="/plug-in/ace/css/font-awesome.css"/>

    <title>后台管理系统</title>

</head>
<body>
<div class="easyui-layout" fit="true">
    <button onclick="aaa()"></button>
    <%--<div style="text-align: center">--%>
    <%--<h2>${datagrid.title}</h2>--%>
    <%--</div>--%>
    <table title="${datagrid.title}" fit="true" id="${datagrid.id}"
           class="easyui-datagrid" url="develop.do?list"
           fitColumns="${datagrid.fitColumns}" pagination="${datagrid.pagination}" toolbar="#toolbar">
        <thead id="bodydiv">
        <%--外层List--%>
        <c:forEach items="${datagrid.columns}" var="list" varStatus="listi">
            <tr>
                    <%--内层List--%>
                <c:forEach items="${list}" var="column" varStatus="columni">
                    <th field="${column.id}" width="100" colspan="${column.colspan}"
                        rowspan="${column.rowspan}" align="${column.align}"
                        <%--是否隐藏--%>
                            <c:if test="${column.hidden}">
                                hidden
                            </c:if>
                    >${column.title}</th>
                </c:forEach>
                <c:if test="${listi.index==0}">
                    <th width="100" formatter="formatFunction" field="cz" rowspan="${fn:length(datagrid.columns)}"
                        styler="stylerFuction" align="center"> 操作
                    </th>
                </c:if>
            </tr>
        </c:forEach>
        </thead>
    </table>


    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true"
           onclick="newUser()">添加</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true"
           onclick="editUser()">修改</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
           onclick="destroyUser()">删除</a>
    </div>
</div>


<%--<div id="dlg" class="easyui-dialog" style="width:400px"--%>
<%--data-options="closed:true,modal:true,border:'thin',buttons:'#dlg-buttons'">--%>
<%--<form id="fm" method="post" novalidate style="margin:0;padding:20px 50px">--%>
<%--<h3>User Information</h3>--%>
<%--<div style="margin-bottom:10px">--%>
<%--<input name="firstName" class="easyui-textbox" required="true" label="First Name:" style="width:100%"/>--%>
<%--</div>--%>
<%--<div style="margin-bottom:10px">--%>
<%--<input name="lastName" class="easyui-textbox" required="true" label="Last Name:" style="width:100%"/>--%>
<%--</div>--%>
<%--<div style="margin-bottom:10px">--%>
<%--<input name="phone" class="easyui-textbox" required="true" label="Phone:" style="width:100%"/>--%>
<%--</div>--%>
<%--<div style="margin-bottom:10px">--%>
<%--<input name="email" class="easyui-textbox" required="true" validType="email" label="Email:"--%>
<%--style="width:100%"/>--%>
<%--</div>--%>
<%--</form>--%>
<%--</div>--%>
<%--<div id="dlg-buttons">--%>
<%--<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()"--%>
<%--style="width:90px">Save</a>--%>
<%--<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"--%>
<%--onclick="javascript:$('#dlg').dialog('close')" style="width:90px">Cancel</a>--%>
<%--</div>--%>

<script type="text/javascript">
    $(document).ready(function () {
        <%--$('#${datagrid.id}').datagrid({--%>
        <%--onLoadSuccess: aaaxx--%>
        <%--})--%>
        <%--heigth: 500--%>
        <%--})--%>
        // var title = v.title;
        // alert(title);
        <%--$('#${datagrid.id}').title('${datagrid.title}');--%>
        <%--$('#${datagrid.id}').height('300px');--%>
        <%--var v = $('#${datagrid.id}')--%>
    });

    <%--function aaaxx(data) {--%>
    <%--// alert("加载成");--%>
    <%--&lt;%&ndash;data.rows[0].name = value;&ndash;%&gt;--%>
    <%--&lt;%&ndash;&lt;%&ndash;var rows = $('#${datagrid.id}').datagrid('getRows')&ndash;%&gt;&ndash;%&gt;--%>
    <%--&lt;%&ndash;&lt;%&ndash;rows[0].name = value;&ndash;%&gt;&ndash;%&gt;--%>
    <%--&lt;%&ndash;console.log(data);&ndash;%&gt;--%>
    <%--var v = $('#bodydiv');--%>
    <%--console.log(v);--%>
    <%--$.parser.parse(v);//重新渲染标签,解决按钮样式失效--%>
    <%--return data;--%>
    <%--}--%>

    function stylerFuction(value, row, index) {
        // return 'easyui-linkbutton';
    }

    function formatFunction(value, row, index) {
        value = '<a href="javascript:void(0)"  class="ace_button"  plain="true" onclick="editUser()"><i class="fa fa-edit"></i>修改</a>';
        return value;
    }

    function aaa() {
        var rows = $('#${datagrid.id}').datagrid();
        console.log(rows);
        // $.parser.parse(value);//重新渲染标签,解决按钮样式失效

    }

    <%--var url;--%>

    <%--function newUser() {--%>
    <%--$('#dlg')--%>
    <%--.dialog('open')--%>
    <%--.dialog('center')--%>
    <%--.dialog('setTitle', 'New User');--%>
    <%--$('#fm').form('clear');--%>
    <%--url = '/user/add';--%>
    <%--}--%>

    <%--function editUser() {--%>
    <%--var row = $('#dg').datagrid('getSelected');--%>
    <%--if (row) {--%>
    <%--$('#dlg')--%>
    <%--.dialog('open')--%>
    <%--.dialog('center')--%>
    <%--.dialog('setTitle', 'Edit User');--%>
    <%--$('#fm').form('load', row);--%>
    <%--url = '/user/save';--%>
    <%--}--%>
    <%--}--%>

    <%--function saveUser() {--%>
    <%--$('#fm').form('submit', {--%>
    <%--url: url,--%>
    <%--onSubmit: function () {--%>
    <%--return $(this).form('validate');--%>
    <%--},--%>
    <%--success: function (result) {--%>
    <%--var result = eval('(' + result + ')');--%>
    <%--if (result.errorMsg) {--%>
    <%--$.messager.show({--%>
    <%--title: 'Error',--%>
    <%--msg: result.errorMsg--%>
    <%--});--%>
    <%--} else {--%>
    <%--$('#dlg').dialog('close'); // close the dialog--%>
    <%--$('#dg').datagrid('reload'); // reload the user data--%>
    <%--}--%>
    <%--}--%>
    <%--});--%>
    <%--}--%>

    <%--function destroyUser() {--%>
    <%--var row = $('#dg').datagrid('getSelected');--%>
    <%--if (row) {--%>
    <%--$.messager.confirm(--%>
    <%--'Confirm',--%>
    <%--'Are you sure you want to destroy this user?',--%>
    <%--function (r) {--%>
    <%--if (r) {--%>
    <%--$.post(--%>
    <%--'/user/delete',--%>
    <%--{id: row.id},--%>
    <%--function (result) {--%>
    <%--if (result.success) {--%>
    <%--$('#dg').datagrid('reload'); // reload the user data--%>
    <%--} else {--%>
    <%--$.messager.show({--%>
    <%--// show error message--%>
    <%--title: 'Error',--%>
    <%--msg: result.errorMsg--%>
    <%--});--%>
    <%--}--%>
    <%--},--%>
    <%--'json'--%>
    <%--);--%>
    <%--}--%>
    <%--}--%>
    <%--);--%>
    <%--}--%>
    <%--}--%>
</script>

</body>
</html>