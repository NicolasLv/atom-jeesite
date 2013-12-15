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
}
