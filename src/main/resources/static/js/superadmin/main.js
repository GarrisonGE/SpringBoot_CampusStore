﻿$(function() {
	$('#centerTab').tabs({
		tools:[{
			iconCls:'icon-back',
			handler: function(){
				$.messager.confirm('注销提示', '你确定注销吗?', function(r){
					if(r){
						$.ajax({
							url : "/campusstore/local/logout",
							type : "post",
							async : false,
							cache : false,
							dataType : 'json',
							success : function(data) {
								if (data.success) {
									var usertype = $("#log-out").attr("usertype");
									// 清除成功后退出到登录界面
									window.location.href = "/campusstore/local/login";
									return false;
								}
							},
							error : function(data, error) {
								alert(error);
							}
						});
					}
				});
			}
		}]
	});
});

/**
 * 创建新选项卡
 * @param tabId    选项卡id
 * @param title    选项卡标题
 * @param url      选项卡远程调用路径
 */
function addTab(tabId,title,url){
	//如果当前id的tab不存在则创建一个tab
	if($("#"+tabId).html()==null){
		var name = 'iframe_'+tabId;
		$('#centerTab').tabs('add',{
			title: title,         
			closable:true,
			cache : false,
			//注：使用iframe即可防止同一个页面出现js和css冲突的问题
			content : '<iframe name="'+name+'"id="'+tabId+'"src="'+url+'" width="100%" height="100%" frameborder="0" scrolling="auto" ></iframe>'
		});
	}
}

