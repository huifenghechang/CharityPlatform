
$(function() {
 	// 发布項目
 	$("#submitproject").click(function() {

 		// var id = $('input#id').val();
		var title = $('input#title').val();
		var summary = $('#summary').val();
		var content = $('#content').val();

		$.ajax({
			type: "POST",
		    url: '/u/projects/edit',
			datatype:"json",
			// contentType: "application/json; charset=utf-8",
           /* data: JSON.stringify({
        		"title": title,
            	"summary": summary ,
            	"content": content}),*/
            data: {title: title, summary: summary , content: content},
			 success: function(data){
				 if (data.success) {
					// 成功后，重定向
					 window.location = data.body;
                 } else {
					 toastr.error("error! i failed"+data.message);
				 }
		     },
		     error : function() {
		    	 toastr.error("error!-function");
		     }
		})
 	})
});