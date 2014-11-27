(function($){
	var 
	_data={
	   		
	},
	_init={
		initData:function(){
			//layout
            $('body').layout('add',{
                region: 'center',
                width: 180,
                height:100
            });
			
		},
		initEvent:function(){
			
		}
	},
	_operation={
			
	};
	
	$.layout={init:_init};
})($);

$().ready(function(){
	console.log('xxxx');
	$.layout.init.initData();
	console.log('xxxx');
});