/**
 * jQuery Permission 1.0
 * 
 * Author Joe
 */
(function($) {
	$.permission = function() {
		$("[permission]").each(function(j,d) {if($.inArray($(d).attr("permission"), $("#_permission").attr("data").trim().split(","))>-1){$(d).hide();}});
	};
})(jQuery);