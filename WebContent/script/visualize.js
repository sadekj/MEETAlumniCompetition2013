// Run the script on DOM ready:
$(function() {
	$('table')
	   .visualize()
	   .appendTo('p')
	   .trigger('visualizeRefresh');
});