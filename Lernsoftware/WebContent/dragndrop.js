$(function() {
	$(".draggable").each(function(index, item) {
		item.setAttribute('draggable', 'true');

		addEvent(item, 'dragstart', function(e) {
			// only dropEffect='copy' will be dropable
			e.dataTransfer.effectAllowed = 'move';

			// required otherwise doesn't work
			e.dataTransfer.setData('item', item.id);
		});
	});

	$(".droppable").each(function(index, item) {
		// Tells the browser that we *can* drop on this target
		addEvent(item, 'dragover', cancelDefault);
		addEvent(item, 'dragenter', cancelDefault);

		addEvent(item, 'drop', function(event) {
			// stops the browser from redirecting off to the text.
			if (event.preventDefault) {
				event.preventDefault();
			}
			
			var answerid = event.dataTransfer.getData('item');
			var answeritem = $("#"+answerid+".draggable")[0];

			var droppableid = MD5(item.id);
			var responsefield = $("[name=" + droppableid + "]")[0];

			responsefield.value = answerid;
			item.innerHTML = answeritem.innerHTML;

			return false;
		});
	});
	
	function cancelDefault(event) {
		if (event.preventDefault) {
			event.preventDefault();
		}
		return false;
	}
});