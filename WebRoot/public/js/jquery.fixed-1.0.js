/**
 * @author Mr.Tu
 * http://www.loveweb8.com/
 *
 * Version 1.0
 * Copyright (c) 2014 ÎÒ°®Web°É
 *
 * Licensed under the MIT license:
 * http://www.opensource.org/licenses/mit-license.php
 */
	
(function($) {

	var _options = {};

	jQuery.fn.fiexd = function(options) {
		var id = $(this).attr("id");
		_options[id] = $.extend({}, $.fn.fiexd.defaults, options);
		
		var obj = $(this);
		var offsetTop = this.offset().top - parseInt(_options[id].top);
		
		_scroll($(document).scrollTop() > offsetTop);
		
		$(window).scroll( function() {
			_scroll($(document).scrollTop() > offsetTop);
		});
		
		function _scroll(isChange){
			if(isChange){
				if($.browser.msie && ($.browser.version == 6.0)){
					obj.css({"position":"absolute", "top":$(document).scrollTop()+parseInt(_options[id].top)})
				}else{
					obj.css({"position":"fixed", "top":_options[id].top});
				}
			}else{
				obj.css({"position":"", "top":""})
			}
		}
	}
	
	// Ä¬ÈÏÖµ
	jQuery.fn.fiexd.defaults = {
		top: '0px'
	};

})(jQuery);

