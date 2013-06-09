// Run the script on DOM ready:
$(function() {
	$('table')
	   .visualize()
	   .appendTo('#chartContainer')
	   .trigger('visualizeRefresh');
	autoUpdate();
});
function autoUpdate(){
	 $('.visualize').trigger('visualizeRefresh');
	 setTimeout('autoUpdate()', 1000);
}