/*!
 * anzhongdai v2.0.1
 * Copyright 2014 anzhongdai.com, Inc.
 */
;
if(typeof jQuery === 'undefined') {
	throw new Error('AZD requires jQuery')
}

/**
 * 安众贷工具类
 */
var AZD = {};

// 设置CSS类
AZD.addClass = function(domId, clazz) {
	if($("#" + domId).length > 0) {
		$("#" + domId).addClass(clazz);
	}
};

// 设置CSS类
AZD.setClass = function(object, clazz) {
	object.attr("class", clazz);
};

// CSS类切换
AZD.switchClass = function(object, one, two) {
	if(object.hasClass(one)) {
		object.removeClass(one);
		object.addClass(two);
	} else {
		object.removeClass(two);
		object.addClass(one);
	}
};

// 删除+增加CSS类
AZD.toggleClass = function(object, remove, add) {
	if(object.hasClass(remove)) {
		object.removeClass(remove);
	}

	if(!object.hasClass(add)) {
		object.addClass(add);
	}
};

/**
 * 初始化
 */
$(document).ready(function() {
	// 回到顶部
	$("body").append("<p id=\"back-to-top\" title=\"返回顶部\"><a href=\"#body\"><span></span></a></p>");
	$("#back-to-top").hide();
	$(window).scroll(function() {
		if($(window).scrollTop() > 100) {
			$("#back-to-top").fadeIn(300);
		} else {
			$("#back-to-top").fadeOut(300);
		}
	});
	$("#back-to-top").click(function() {
		$("body,html").animate({
			scrollTop: 0
		}, 300);
		return false;
	});
	
	$("#back-to-top").tooltip({
		animation: false,
		trigger: "hover",
		placement: "left"
	});

	// Tooltip插件
	$(".otip").tooltip({
		animation: false,
		trigger: "hover",
		placement: "bottom"
	});
	$(".ktip").tooltip({
		animation: false,
		trigger: "click",
		placement: "bottom"
	});

	// Toastr提示
	if(typeof toastr !== 'undefined') {
		toastr.options = {
			"closeButton": true,
			"debug": false,
			"positionClass": "toast-bottom-full-width",
			"onclick": null,
			"showDuration": "300",
			"hideDuration": "1000",
			"timeOut": "5000",
			"extendedTimeOut": "1000",
			"showEasing": "swing",
			"hideEasing": "linear",
			"showMethod": "fadeIn",
			"hideMethod": "fadeOut"
		};
	}
	
	// F12页面提示
	if(window.console) {
		window.console.info("%c对于那些看到一个页面就要按F12的web开发者来说，你是喜欢我们的代码呢，还是发现了什么bug？不如直接和我们联系吧！", "font-size:14px;");
		window.console.info("%c邮件联系 老牛啊 obullxl@gmail.com", "color:red;font-size:14px;");
	}
	;
});

function fadeProgressModal(visiable) {
	if(visiable) {
		$("#modal-progress").modal("show");
	} else {
		$("#modal-progress").modal("hide");
	}
}

function fadeSuccessModal(data) {
	$("#success-biz-log").text(data.bizLog);
	$("#modal-success").modal("show");
}

function fadeFailureModal(data) {
	$("#failure-biz-log").text(data.bizLog);
	$("#failure-resp-code").text(data.respCode);
	$("#failure-resp-desp").text(data.respDesp);
	$("#modal-failure").modal("show");
}

/*
 * setTimeout(function() { $('.sidebar-nav').affix() }, 100);
 */

/**
 * 该方法必须与lhgdialog组件配合使用
 */
function showFailureTip(result) {
	$.dialog({
		title: "失败提示",
		icon: "error.gif",
		content: "错误码:<b>" + result.respCode + "</b><br/>错误描述:<b>" + result.respDesp + "</b>"
	});
}

/**
 * 该方法必须与lhgdialog组件配合使用
 */
function showSuccessTip(result) {
	var cfn = function() {
	};

	if(result.close) {
		cfn = result.close;
	}

	$.dialog({
		title: "成功提示",
		icon: "success.gif",
		content: result.msg,
		close: cfn
	});
};

/**
 * 发起Ajax请求 <p/> 必须参数: form, url; <br/>可选参数: smsg, sclose, scallback
 */
function ajaxRquest(args) {
	var smsg = "恭喜您，操作成功！";
	if(args["smsg"]) {
		smsg = args["smsg"];
	}

	var sclose = function() {
	};
	if(args["sclose"]) {
		sclose = args["sclose"];
	}

	jQuery.ajax({
		type: "POST",
		dataType: "json",
		url: args["url"],
		data: $("#" + args["form"]).serialize(),

		success: function(result) {
			if(result.success) {
				showSuccessTip({
					msg: smsg,
					close: sclose(result)
				});
			} else {
				showFailureTip(result);
			}

			if(args["scallback"]) {
				args["scallback"](result);
			}
		}
	});
};

/* 查看图片 */
var viewImage = function(args) {
	$.dialog.setting.min = true;
	$.dialog.setting.max = true;
	$.dialog({
		title: args.title || "查看相册图片",
		padding: args.padding || 0,
		width: args.width || '700px',
		height: args.height || '500px',
		content: '<img src="' + args.url + '"/>'
	});
};
