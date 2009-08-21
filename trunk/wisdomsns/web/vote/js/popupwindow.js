HelloWindow = function(t) {
	var win = this;
	var title = t;
	HelloWindow.superclass.constructor.call(this, {
		title : title,
		layout : 'fit',
		width : 500,
		height : 300,
		closeAction : 'hide',
		plain : true,
		html:'hello',

		buttons : [ {
			text : 'Submit',
			disabled : true
		}, {
			text : 'Close',
			handler : function() {
				win.hide();
			}
		}]
	});
}

Ext.extend(HelloWindow, Ext.Window, {});

HelloBtn = function() {
	HelloBtn.superclass.constructor.call(this, {
		text : 'Show Me'
		,
	});
}

Ext.extend(HelloBtn, Ext.Button, {});

/**
Ext.onReady(function() {
	
	var helloBtn = new HelloBtn();
	helloBtn.render('helloBtn');
	helloBtn.on('click', function(e) {
		var winq = new HelloWindow("what's wrong");
		winq.show(this.getEl().dom);
		
		}
		
		);

});
*/
function show_(param){
var p = param;
var helloWindow = new HelloWindow(p);
helloWindow.show();
}
function test(){
alert("test");
}