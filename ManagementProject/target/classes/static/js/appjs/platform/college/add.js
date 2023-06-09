layui.config({
	    base: ctx +'layui/' //静态资源所在路径
	  }).use(['index','form','element', 'admin'], function(){
		  var laydate = layui.laydate
			,$ = layui.jquery;
			var form = layui.form;
			var element = layui.element;
			
			form.on('submit(layuiadmin-app-form-submit)', function(data) {
				
				$.ajax({
					cache : true,
					type : "POST",
					url : ctx +"student/college/save",
					data : $('#signupForm').serialize(),// 你的formid
					async : false,
					error : function(request) {
						parent.layer.alert("Connection error");
					},
					success : function(data) {
						if (data.code == 0) {
							parent.layer.msg("操作成功");
							parent.reLoad(1);
							var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
							parent.layer.close(index);

						} else {
							parent.layer.alert(data.msg)
						}

					}
				});
				return false;
			})
	  })