
function removeTask(taskId){

    $.ajax({
        url: '/private/remove/post.do',
        type: 'POST',
        data: 'taskId=' + taskId,
        dataType: 'json',
        success: function(jsonData) {

            if(jsonData.message && jsonData.taskId) {
                $('<div class="userMessage">'+jsonData.message+'</div>').insertBefore('#row-task-id-' + jsonData.taskId);
                $('#row-task-id-' + jsonData.taskId).remove();
            }
            if (jsonData.error && jsonData.taskId) {
                $('#row-task-id-' + jsonData.taskId).append('<div class="errorMessage">'+jsonData.error+'</div>');
            }
            else if (jsonData.error) {
                $('#globalMessage').append('<div class="errorMessage">'+jsonData.error+'</div>');
            }
        }
    });
}