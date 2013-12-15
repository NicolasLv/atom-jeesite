(function(config) {
	config["extendDrag"] = true; // 注意，此配置参数只能在这里使用全局配置，在调用窗口的传参数使用无效
	config["lock"] = true;
	config["fixed"] = true;
	config["min"] = false;
	config["max"] = false;
	config["okVal"] = "确定";
	config["cancelVal"] = "取消";
	// [more..]
	config["icon"] = "/public/favicon.png";
	config["title"] = "弹出窗口";
})($.dialog.setting);