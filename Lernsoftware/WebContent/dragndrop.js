var drop = document.querySelector('.droppable');

// Tells the browser that we *can* drop on this target
addEvent(drop, 'dragover', cancel);
addEvent(drop, 'dragenter', cancel);

addEvent(drop, 'drop', function (event) {
  // stops the browser from redirecting off to the text.
  if (event.preventDefault) {
    event.preventDefault(); 
  }

  this.innerHTML += '<p>' + event.dataTransfer.getData('Text') + '</p>';

  return false;
});

function cancel(event) {
  if (event.preventDefault) {
    event.preventDefault();
  }
  return false;
}