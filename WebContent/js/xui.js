(function ($) {
	$.fn.xtable = function(options){
		var ths = this;
		if(ths.length===0){$.logError('xtable Unable to read element '+ths.selector);return;}
		var xfn = $(ths.selector).data(ths.selector);
		if(xfn){ return xfn; }
		var defaults = {
			url:null,
			deleteUrl:null,
			data:{},
			paging:null,
			page:1,
			rows:20,
			xdata:null,
			minus:0,
			width:null,
			height:null,
			load:true,
			resize:false,
			drag:false,
			bodyHeight:null,
			maxHeight:null,
			xnodata:'<div class="xnodata">暂无数据</div>',
			xupdate:'.xupdate',
			xdelete:'.xdelete',
			xdeletes:'.xdeletes',
			deleteMsg:'确定删除此条记录吗？',
			deletesMsg:'确定批量删除选中记录吗？',
			format:function(row){},
			bind:function(){},
			success:function(data){},
			deleteSuccess:function(data){},
			update:function(row,btn){}
		}
		var ops = $.extend(defaults,options,{
			tpl:null,
			template:null,
			list:null,
			total:0
		});
		var inside = {
			init:function(){
				inside.template();
				inside.setPaging();
				inside.setSize();
				if(ops.load)inside.load();
			},
			reinit:function(){
				inside.setPaging();
				inside.load();
			},
			template:function(){
				ops.tpl = ths.find('.xtemplate');
				ops.template = ops.tpl.html();
			},
			setPaging:function(){
				if(ops.paging){
					ops.data.page = ops.page<1?1:ops.page;
					ops.data.rows = ops.rows;
				}
			},
			setSize:function(){
				if(ops.height){
					ths.height(ops.height);
				}
				if(ops.bodyHeight){
					ths.find('.xbody').height(ops.bodyHeight);
				}
				if(ops.width){
					ths.width(ops.width);
				}
			},
			load:function(){
				if(ops.url){
					$.ajax({
		                url:ops.url,
		                data:ops.data,
		                type:'POST',
		                dataType:'json',
		                success:inside.success,
		                error:inside.error
		            });
				}else if(ops.xdata){
					ops.list = ops.xdata.list;
					ops.total = ops.xdata.total;
					inside.draw();
				}
				if(ths.has('.xcheckbox')){
					ths.find('.xcheckbox').prop('checked',false);
				}
			},
			success:function(data){
				ops.list = data.list;
            	ops.total = data.total;
				inside.draw();
			},
			draw:function(){
            	ops.tpl.empty();
				ops.tpl.show();
				if(ops.total>0){
					ths.find(".xnodata").remove();
				}else{
					ths.append(ops.xnodata);
				}
				if(ops.list!==null){
					$.each(ops.list,function(index,row){
						row.index = index;
						ops.format(row);
						ops.tpl.append($.xtemplate(ops.template,row));
					});
				}
				bind.init();
            	inside.resize();
            	inside.initPaging();
            	inside.call();
            	inside.headResize();
			},
			error:function(e){
				$.error('数据加载失败！');
			},
			resize:function(){
				inside.bodyResize();
				inside.headResize();
			},
			headResize:function(){
				var xhead = ths.find('.xhead th');
				ops.tpl.find('tr:first td').each(function(index,row){
					if(index===xhead.length-1)return;
					$(row).width($(xhead[index]).width());				
				});
			},
			bodyResize:function(){
				if(ops.resize){
					var height = ops.maxHeight - ths.find('.xhead th').outerHeight() - ops.minus;
					ths.find('.xbody').css('max-height',height+'px');
				}
			},
			initPaging:function(){
				if(ops.paging){
					$(ops.paging).xpaging({
						page:ops.page,
						rows:ops.rows,
						total:ops.total,
						change:inside.loadPaging
					});
				}
			},
			loadPaging:function(data){
				ops.page = data.page;
				ops.rows = data.rows;
				inside.setPaging();
				inside.load();
			},
			call:function(){
				if(ops.total>0){
					ops.bind();
				}
        		ops.success({total:ops.total,list:ops.list});
			},
			reload:function(data,bool){
				if(data){
					if(bool===true){
						ops.data = $.extend(ops.data,data,{page:1,rows:ops.rows});
					}else{
						ops.data = $.extend(data,{page:1,rows:ops.rows});
					}
					ops.page = 1;
				}
				inside.load();
			}
		}
		var bind = {
			init:function(){
				bind.windowResize();
				if(ops.total>0){
					bind.checkbox();
					bind.update();
					bind.xdelete();
					bind.xdeletes();
				}
				if(ops.drag){
					bind.xdrag();
				}
			},
			checkbox:function(){
				if(ths.has('.xcheckbox')){
					ths.find('.xcheckbox').change(function(){
						if($(this).is(':checked')){
							ths.find('.xtemplate input:checkbox').prop('checked',true);
						}else{
							ths.find('.xtemplate input:checkbox').prop('checked',false);
						}
					});
				}
			},
			windowResize:function(){
				$(window).resize(function(){
					inside.resize();
				});
			},
			update:function(){
				var xbtn = ths.find(ops.xupdate)
				if(xbtn.length > 0){
					xbtn.xbtn(function(btn){
						var row = outside.getRow(btn.xv);
						ops.update(row,btn);
					});
				}
			},
			xdelete:function(){
				var xbtn = ths.find(ops.xdelete)
				if(xbtn.length > 0){
					xbtn.xbtn(function(btn){
						$.xalert({msg:ops.deleteMsg,confirm:true,
							okBack:function(){
								bind.del(btn,btn.xv);
							}
						});
					});
				}
			},
			xdeletes:function(){
				var xbtn = ths.find(ops.xdeletes)
				if(xbtn.length > 0){
					xbtn.xbtn(function(btn){
						var ids = outside.getSelected();
						if(ids!=''){
							$.xalert({msg:ops.deletesMsg,confirm:true,
								okBack:function(){
									bind.del(btn,ids);
								}
							});
						}else{
							$.xalert({msg:'请选择要删除的记录'});
						}
					});
				}
			},
			del:function(btn,ids){
				if(ops.deleteUrl!==null){
					btn.close();
					$.xpost({
						url:ops.deleteUrl,
						data:{ids:ids},
						success:function(data){btn.open();ops.deleteSuccess(data);},
						error:function(){btn.open();$.xalert({msg:'请求失败！'});}
	                });
				}else{
					$.logError('xtable no deleteUrl');
				}
			},
			xdrag:function(){
				ths.find("th").each(function(){
					$(this).append('<span class="xdrag"></span>');
				});
				var drag = {move:false};
				ths.find(".xdrag").mousedown(function(e){
					drag.move = true;
					drag.tw = ths.width();
					drag.th = $(this).parent()
					drag.hw = drag.th.width();
					drag.x = e.pageX;
					document.body.onselectstart = function(){return false;};
				});			
				$(document).mouseup(function(e){
					drag.move = false;
					document.body.onselectstart = function(){return true};
				});
				$(document).mousemove(function(e){
					if(drag.move){
						var hw = drag.hw + e.pageX - drag.x;
						if(hw>25){
							ths.width(drag.tw + e.pageX - drag.x);
							drag.th.width(drag.hw + e.pageX - drag.x);
							inside.headResize();
						}
					}
				});
			}
		}
		var xtable = function(){
			inside.init();
		}
		var table = new xtable();
		var outside = {
			setData:function(data){
				ops.list=data;
				inside.reinit();
			},
			getData:function(){
				return ops.list;
			},
			getRow:function(index){
				return ops.list[index];
			},
			reload:function(data){
				inside.reload(data);
			},
			getSelected:function(){
				var ids = '';
				ths.find('.xtemplate input:checkbox:checked').each(function(index,row){
					ids += $(row).data('xv')+',';
				});
				if(ids !== '')ids = ids.substring(0,ids.length-1);
				return ids;
			},
			setSelected:function(ids){
				$.each(ids,function(index,row){
					$('.xtemplate input:checkbox').each(function(i,ck){
						if(ck.data('xv') == row){
							ck.prop('checked',true)
						}
					});
				});
			},
			clearSelected:function(){
				ths.find('input:checkbox').prop('checked',false);
			},
			setBodyHeight:function(height){
				ths.find('.xbody').css('height',height+'px');
			},
			resize:function(){
				inside.resize();
			}
		}
		$.extend(table,outside);
		$(ths.selector).data(ths.selector,table);
		return table;
	}
	$.fn.xpaging = function(options){
		var ths = this;
		if(ths.length===0){$.logError('xpaging Unable to read element '+ths.selector);return;}
		var defaults = {
			page:null,
			rows:null,
			total:null,
			change:function(e){}
		}
		var xdata = {
			page:null,
			rows:null,
			total:null,
			prev:null,
			next:null,
			first:null,
			last:null,
			size:null
		}
		var ops = $.extend(defaults,options);
		var inside = {
			init:function(){
				inside.template();
				inside.draw();
			},
			template:function(){
				if(!ths.data('template')){
					var tpl = ths.find('.xtemplate');
					var html = tpl.html();
					ths.data('template',html);
					tpl.empty();
				}
			},
			draw:function(){
				inside.setData();
				inside.setHtml();
				inside.bind();
			},
			setData:function(){
				xdata.page = ops.page;
				xdata.rows = ops.rows;
				xdata.total = ops.total;
				xdata.size = parseInt((xdata.total+xdata.rows-1)/xdata.rows);	
				xdata.prev = xdata.page>1?xdata.page-1:1;
				xdata.next = xdata.page<xdata.size?xdata.page+1:xdata.size;
				xdata.first = 1;
				xdata.last = xdata.size;
			},
			setHtml:function(){
				var html = $.xtemplate(ths.data('template'),xdata);
				var tpl = ths.find('.xtemplate');
				tpl.html(html);
				tpl.show();				
			},
			toPage:function(page){
				ops.page = page;
				inside.draw();
				inside.call();
			},
			setRows:function(rows){
				ops.rows = rows;
				inside.draw();
				inside.call();
			},
			bind:function(){
				ths.find('.xnext,.xprev,.xfirst,.xlast').click(function(){
					inside.toPage(parseInt($(this).data('xv')));
				});
				ths.find('.xgo').click(function(){
					var xpage = $(this).parent().find('.xpage').val();
					var page = parseInt(xpage);
					if(isNaN(page)){
						$.error('请输入有效数字！');
					}else if(page<1||page>xdata.size){
						$.error('请输入有效范围！');
					}else{
						inside.toPage(page);
					}
				});
			},
			call:function(){
				ops.change({
					page:xdata.page,
					rows:xdata.rows
				});
			}
		}
		var xpaging = function(){
			inside.init();
		}
		var paging = new xpaging();
		var outside = {
			reload:function(){
			},
			setData:function(){
			}
		}
		$.extend(paging,outside);
		return paging;
	}
	$.fn.xlist = function(options) {
		var ths = this;
		if(ths.length===0){$.logError('xlist Unable to read element '+ths.selector);return;}
		var xfn = $(ths.selector).data(ths.selector);
		if(xfn){ return xfn; }
		var defaults = {
			url:null,
			data:{},
			page:1,
			rows:20,
			xdata:null,
			xload:'.xload',
			xnodata:'.xnodata',
			format:function(row){},
			success:function(data){}
		}
		var ops = $.extend(defaults,options,{
			tpl:null,
			template:null,
			list:null,
			loadBtn:null,
			total:0
		});
		var inside = {
			init:function(){
				inside.loadTpl();
				inside.setPaging();
				inside.load();
				inside.bind();
			},
			loadTpl:function(){
				ops.tpl = ths.find('.xtemplate');
				ops.template = ops.tpl.html();
            	ops.tpl.empty();
				ops.tpl.show();
			},
			setPaging:function(){
				ops.data.page = ops.page<1?1:ops.page;
				ops.data.rows = ops.rows;
			},
			load:function(){
				if(ops.url){
					$.ajax({
		                url:ops.url,
		                data:ops.data,
		                type:'POST',
		                dataType:'json',
		                success:inside.success,
		                error:inside.error
		            });
				}else if(ops.xdata){
					ops.list = ops.xdata.list;
					ops.total = ops.xdata.total;
					inside.draw();
				}
			},
			reload:function(data){
				ops.tpl.empty();
				$.extend(ops.data,data,{page:1});
				inside.load();
			},
			error:function(){
				$.error('数据加载失败！');
			},
			success:function(data){
				ops.list = data.list;
            	ops.total = data.total;
				inside.draw();
			},
			draw:function(){
				if(ops.total>0){
					ths.find(ops.xnodata).hide();
				}else{
					ths.find(ops.xnodata).show();
				}
				if(ops.list!==null)
				$.each(ops.list,function(index,row){
					row.index = index;
					ops.format(row);
					var x = $.xtemplate(ops.template,row);
					ops.tpl.append(x);
				});
				inside.more();
				inside.call();
				if(ops.loadBtn)ops.loadBtn.open();
			},
			bind:function(){
				ops.loadBtn = ths.find(ops.xload).xbtn(function(){
					ops.loadBtn.close();
					ops.data.page++;
					inside.load();
				});
			},
			more:function(){
				var size = parseInt((ops.total+ops.rows-1)/ops.rows);
				if(size>1&&ops.data.page<size){
					ths.find(ops.xload).show();
				}else{
					ths.find(ops.xload).hide();
				}
			},
			call:function(){
        		ops.success({total:ops.total,list:ops.list});
			},
			getRow:function(index){
				return ops.list[index];
			}
		}
		var xlist = function(){
			inside.init();
		}
		var list = new xlist();
		var outside = {
			reload:function(data){
				inside.reload(data);
			},
			getRow:function(index){
				return inside.getRow(index);
			}
		}
		$.extend(list,outside);
		$(ths.selector).data(ths.selector,list);
		return list;
	}
	$.fn.xtabs = function(options) {
		var ths = this;
		if(ths.length===0){$.logError('xtabs Unable to read element '+ths.selector);return;}
		var xfn = $(ths.selector).data(ths.selector);
		if(xfn){ return xfn; }
		var defaults = {
			tab:0,
			width:null,
			height:null
		}
		var ops = $.extend(defaults,options);
		var inside = {
			init:function(){
				inside.resize();
				inside.open(ops.tab);
				inside.bind();
			},
			resize:function(){
				if(ops.width){
					ths.width(ops.width);
				}
				if(ops.height){
					ths.height(ops.height);
				}
			},
			open:function(index){
				if(index!==ops.tab){
					ths.find('.tabs_head').children().eq(ops.tab).removeClass('selected');
					ths.find('.tabs_body').children().eq(ops.tab).hide();
					ops.tab = index;
				}
				ths.find('.tabs_head').children().eq(index).addClass('selected');
				ths.find('.tabs_body').children().eq(index).show();
			},
			bind:function(){
				inside.bindTab();
			},
			bindTab:function(){
				var headChildren = ths.find('.tabs_head').children();
				var bodyChildren = ths.find('.tabs_body').children();
				$.each(headChildren,function(index,row){
					$(row).click(function(){
						var ths = $(this);
						ths.parent().find('.selected').removeClass('selected');
						ths.addClass('selected');
						bodyChildren.eq(ops.tab).hide();
						bodyChildren.eq(index).show();
						ops.tab = index;
					});
				});
			}
		}
		var xtabs = function(){
			inside.init();
		}
		var tabs = new xtabs();
		var outside = {
			open:function(index){
				inside.open(index);
			},
			getOpen:function(){
				return ops.tab; 
			}
		}
		$.extend(tabs,outside);
		$(ths.selector).data(ths.selector,tabs);
		return tabs;
	}

	$.fn.xform = function (options) {
		var ths = this;
		var defaults = {
			url:null,
			save:null,
			update:null,
			updateId:null,
			xbtn:null,
			method:'POST',
			dataType:'json',
			fileupload:false,
			semantic:false,
			checkall:false,
			data:[],
			xcheck:[],
			xcombo:[],
			success:function(d){},
			error:function(e){$.logError(e);},
			submitp:function(){return true;},
			submitb:function(){}
		}
		var ops = $.extend(defaults,options);
		var combos = [];
		var inside = {
			init:function(){
				inside.initCombo();
				inside.initFile();
				inside.bind();
			},
			initCombo:function(){
				$.each(ops.xcombo,function(index,row){
					combos[index] = $(row.id).xcombo(row);
				})
			},
			initFile:function(){
				var enctype = 'multipart/form-data';
				if(ops.fileupload === true && ths.attr('enctype') !== enctype){
					ths.attr('encoding', enctype);
					ths.attr('enctype', enctype);
				}
				if(ops.fileupload === false && ths.attr('enctype') === enctype){
					ops.fileupload = true;
				}
				inside.fileapi = $('<input type="file"/>').get(0).files !== undefined;
				inside.formdata = window.FormData !== undefined;
			},
			bind:function(){
				if(ops.xbtn!==null){
					$(ops.xbtn).xbtn(function(){
						outside.submit();
					});
				}
			},
			submit:function(){
				$.ajax({
	                url:ops.url,
	                type:ops.method,
	                dataType:ops.dataType,
	                data:inside.getData(),
					success:inside.success,
					error:inside.error
	            });
			},
			submitp:function(){
				if(ops.xbtn){$.closeBtn(ops.xbtn);}
				ops.submitp();
			},
			submitb:function(){
				if(ops.xbtn){$.openBtn(ops.xbtn);}
				ops.submitb();
			},
			success:function(d){
				inside.submitb();
				ops.success(d);
			},
			error:function(e){
				inside.submitb();
				ops.error(e);
			},
			getData:function(){
				var data = [];
				for(var n in ops.data){
	            	data.push({name: n, value: ops.data[n]});
				}
				var form = ths[0];
				var formId = ths.attr('id');
			    var els = ops.semantic ? form.getElementsByTagName('*') : form.elements;
			    if (els && !/MSIE [678]/.test(navigator.userAgent)) {
			        els = $(els).get();
			    }
			    if (!els || !els.length) {
			        return data;
			    }
			    var i,j,n,v,el,max,jmax;
			    for(i=0, max=els.length; i < max; i++) {
			        el = els[i];
			        n = el.name;
			        if (!n || el.disabled) {
			            continue;
			        }
			        v = inside.fieldValue(el,true);
			        if (v && v.constructor === Array) {
			            for(j=0, jmax=v.length; j < jmax; j++) {
			            	data.push({name: n, value: v[j]});
			            }
			        }
			        else if (inside.fileapi && el.type === 'file') {
			            var files = el.files;
			            if (files.length) {
			                for (j=0; j < files.length; j++) {
			                	data.push({name: n, value: files[j], type: el.type});
			                }
			            }
			        }
			        else if (v !== null && typeof v !== 'undefined') {
			        	data.push({name: n, value: v, type: el.type, required: el.required});
			        }
			    }
			    return data;
			},
			fieldValue:function(el, successful) {
			    var n = el.name, t = el.type, tag = el.tagName.toLowerCase();
			    if (successful === undefined) {
			        successful = true;
			    }

			    if (successful && (!n || el.disabled || t === 'reset' || t === 'button' ||
			        (t === 'checkbox' || t === 'radio') && !el.checked ||
			        (t === 'submit' || t === 'image') && el.form && el.form.clk != el ||
			        tag === 'select' && el.selectedIndex === -1)) {
			            return null;
			    }

			    if (tag === 'select') {
			        var index = el.selectedIndex;
			        if (index < 0) {
			            return null;
			        }
			        var a = [], ops = el.options;
			        var one = (t === 'select-one');
			        var max = (one ? index+1 : ops.length);
			        for(var i=(one ? index : 0); i < max; i++) {
			            var op = ops[i];
			            if (op.selected) {
			                var v = op.value;
			                if (!v) {
			                    v = (op.attributes && op.attributes.value && !(op.attributes.value.specified)) ? op.text : op.value;
			                }
			                if (one) {
			                    return v;
			                }
			                a.push(v);
			            }
			        }
			        return a;
			    }
			    return $(el).val();
			},
			check:function(){
				return ths.xcheck(ops.xcheck,ops.checkall);
			},
			clearError:function(){
				$.each(ops.xcheck,function(index,row){
					if(row.msgBox===null||row.msgBox===undefined){
						$('input[name=' + row.name + ']').next('label').remove();
					}else{
						$(row.msgBox).html('');
					}
				})
			},
			checkUrl:function(){
				if(ops.updateId!==null){
					var uid = ths.find(ops.updateId).val();
					if (uid === undefined || uid === "") {
						ops.url = ops.save;
					}else{
						ops.url = ops.update;
					}
				}else{
					if(ops.save)ops.url = ops.save;
				}
			}
		}
		var formdata = {
			submit:function(){
		        var fdata = new FormData();
		        var data = inside.getData();
		        for (var i=0; i < data.length; i++) {
		        	fdata.append(data[i].name, data[i].value);
		        }
	        	for(n in ops.data){
	        		fdata.append(n, ops.data[n]);
	    		}
	        	formdata.doSubmit(fdata);
			},
			doSubmit:function(formdata){
		        $.ajax({
	                url:ops.url,
	                type:ops.method,
					success:inside.success,
					error:inside.error,
					contentType: false,
		            processData: false,
		            cache: false,
		            data:formdata
		        });
			}
		}
		var iframe = {
			id:null,
			io:null,
			checkCount:20,
			submit:function(){
				iframe.id = 'xui_form_' + (new Date().getTime());
				iframe.io = $('<iframe id="' + iframe.id + '" name="' + iframe.id + '"/>').appendTo('body');
				iframe.io.attr('src', window.ActiveXObject ? 'javascript:false' : 'about:blank');
				iframe.io.css({ position: 'absolute', top: '-1000px', left: '-1000px' });
				iframe.io.bind('load', iframe.cb);
				iframe.doSubmit();
			},
			doSubmit:function(){
				ths.attr('action', ops.url);
				ths.attr("method", "POST");
				var t = ths.attr('target');
				ths.attr('target', iframe.id);
				var paramFields = $();
				try {
					for(var n in ops.data){
						var field = $('<input type="hidden" name="' + n + '">').val(ops.data[n]).appendTo(ths);
						paramFields = paramFields.add(field);
					}
					iframe.checkState();
					ths[0].submit();
				} finally {
					ths.attr('action', ops.url);
					t ? ths.attr('target', t) : ths.removeAttr('target');
					paramFields.remove();
				}
			},
			cb:function(){
				iframe.io.unbind();
				var data = '';
				try{
					var body = iframe.io.contents().find('body');
					data = body.html();
					if (data === ''){
						if (--iframe.checkCount){
							setTimeout(iframe.cb, 200);
							return;
						}
					}
					var ta = body.find('>textarea');
					if (ta.length){
						data = ta.val();
					} else {
						var pre = body.find('>pre');
						if (pre.length){
							data = pre.html();
						}
					}
				} catch(e){
				}
				ops.success(data);
				setTimeout(function(){ iframe.io.unbind(); iframe.io.remove(); }, 200);
			},
			checkState:function(){
				var f = $('#'+iframe.id);
				if (!f.length){return}
				try{
					var s = f.contents()[0].readyState;
					if (s && s.toLowerCase() === 'uninitialized'){
						setTimeout(iframe.checkState, 100);
					}
				} catch(e){
					iframe.cb();
				}
			}
		}
		var xform = function(){
			inside.init();
		}
		var form = new xform();
		var outside = {
			submit:function(){
				if(inside.submitp()!==false && inside.check()){
					inside.checkUrl();
					if(ops.fileupload&&inside.formdata){
						formdata.submit();
					}else if(ops.fileupload){
						iframe.submit();
					}else{
						inside.submit();
					}
				}else if(ops.xbtn){
					$.openBtn(ops.xbtn);
				}
			},
			setData:function(data){
				outside.clear();
				$.each(data, function (name, ival) {
					var $input = ths.find('input[name = ' + name + ']');
					if ($input.prop('type') === 'radio' || $input.prop('type') === 'checkbox') {
						$input.each(function(){
							if(Object.prototype.toString.apply(ival) === '[object Array]'){
								for (var i in ival) {
									if($(this).val() === ival[i]){
										$(this).prop('checked', true);
									}
								}
							} else{
								if($(this).val() == ival){
									$(this).prop('checked', true);
								}
							}
						});
					} else{
						ths.find('[name = '+name+']').val(ival);
					}
					$input = ths.find('select[name = ' + name + ']');
					if($input){
						$input.trigger('change');
					}
				});
				$.each(combos,function(index, row){row.val(row.val())});
			},
			setUrl:function(url){
				ops.url = url;
			},
			show:function(){
				ths.css('display','block');
				outside.resize();
			},
			hide:function(){
				ths.css('display','none');
			},
			resize:function(){
				var thsh = ths.find('.minwin_box').outerHeight();
				var thsw = ths.find('.minwin_box').outerWidth();
				var winh=$(window).outerHeight();
				if(thsh>winh){
					ths.find('.minwin_box').css({
						'max-height':(winh-50)+'px',
						'margin-top':'-'+(winh-50)/2+'px',
						'margin-left':'-'+(thsw)/2+'px'
					});
					ths.find('.minwin_box ul').css({
						'max-height':winh-174+'px',
		                'overflow':'auto'
		        	});
				}else{
					ths.find('.minwin_box').css({
						'margin-top':'-'+(thsh)/2+'px',
						'margin-left':'-'+(thsw)/2+'px'
					});
				}
			},
			clear:function(){
				ths.find('input[type = text],input[type = hidden],input[type = password],input[type = file],textarea').val('');
				$.each(combos,function(index,row){row.clear()});
				inside.clearError();
			},
			getData:function(){
				return inside.getData();
			},
			check:function(){
				return inside.check();
			}
		}
		$.extend(form,outside);
		return form;
    }
	$.fn.xwindow = function(options){
		var ths = this;
		if(ths.length===0){$.logError('xwindow Unable to read element '+ths.selector);return;}
		var xfn = $(ths.selector).data(ths.selector);
		if(xfn){
			return xfn;
		}
		var defaults = {
			width: 500,
			height: 300,
			open:false
		}
		var ops = $.extend(defaults, options);
		var inside = {
			init:function(){
				inside.draw();
				inside.bind();
				if(ops.open){
					inside.open();
				}
			},
			draw:function(){
				ths.find('.xbox').css({
					'width':ops.width,
					'height':ops.height,
					'margin-top':-ops.height/2+'px',
					'margin-left':-ops.width/2+'px'
				});
			},
			bind:function(){
				ths.find('.xclose').click(function(){
					ths.hide();
				});
			},
			open:function(){
				ths.show();
			},
			close:function(){
				ths.hide();
			}
		}
		var xwindow = function(){
			inside.init();
		}
		var mywindow = new xwindow();
		var outside = {
			open:function(){
				inside.open();
			},
			close:function(){
				inside.close();
			},
			setWidth:function(w){
				ths.find('.xbox').css({'width':w+'px'});
			},
			setTitle:function(title){
				ths.find('.xbox').find('.title').eq(0).html(title);
			}
		}
		$.extend(mywindow,outside);
		$(ths.selector).data(ths.selector,mywindow);
		return mywindow;
	}
	$.fn.xcombo = function(options){
		var ths = this;
		if(ths.length===0){$.logError('xcombo Unable to read element '+ths.selector);return;}
		var xfn = $(ths.selector).data(ths.selector);
		if(xfn){ return xfn; }
		var defaults = {
			url:null,
			data:null,
			width:null,
			height:null,
			xdata:null,
			xtext:'请选择',
			selectValue:null,
			selectIndex:null,
			template:null,
			change:function(xv,text,row){},
			success:function(){}
		}
		var ops = $.extend(defaults,options,{
			tpl:null,
			template:null
		});
		var inside = {
			init:function(){
				inside.append();
				inside.template();
				inside.load();
			},
			load:function(){
				if(ops.url){
					$.ajax({
						url:ops.url,
						data:ops.data,
						type:'POST',
						dataType:'json',
						success:inside.success,
						error:inside.error
					});
				}else if(ops.xdata){
					inside.draw(ops.xdata);
				}else{
	            	inside.bind();
	            	inside.call();
				}
			},
			append:function(){
				var name = ths.data("name");
				if(name!==undefined){
					ths.append('<input type="hidden" name="'+name+'">');
				}
				ths.append('<div class="xtext">'+ops.xtext+'</div>');
				ths.append('<i class="xshow"></i>');
			},
			template:function(){
				ths.width(ops.width);
				ths.height(ops.height);
				ops.tpl = ths.find('.xtemplate');
				if(ops.tpl.length>0){
					ops.template = ops.tpl.html();
					ops.tpl.empty();
					ops.tpl.css('width',ops.width);
				}else{
					ths.find('ul').css('width',ops.width);
				}
			},
			success:function(data){
				ops.xdata = data;
				inside.draw(data);
			},
			draw:function(){
            	ops.tpl.empty();
            	$.each(ops.xdata,function(index,row){
            		row.index = index;
            		$($.xtemplate(ops.template,row).trim()).appendTo(ops.tpl).attr('data-index',index);
            	});
            	if(ops.selectIndex!=null){
            		inside.select(ops.selectIndex);
            	}
            	if(ops.selectValue!=null){
            		inside.val(ops.selectValue);
            	}
            	inside.bind();
            	inside.call();
			},
			error:function(e){
				$.error('数据加载失败！')
			},
			bind:function(){
				ths.unbind('click').click(function(){
					ths.find('ul').toggle();
				});
				$(document).mousedown(function(event) {
					if ($(event.target).parents().hasClass('xcombo') === false) {
						ths.find('ul').hide();
					}
				});
				ths.find('li').unbind('click').click(function(){
					inside.itemClick(this);
				})
			},
			call:function(){
				if(ops.success){
            		ops.success();
            	}
			},
			itemClick:function(item){
				var xv = $(item).data('xv');
				var index = $(item).data('index');
				var text = $(item).html();
				ths.find('input[type = hidden]').val(xv);
				ths.find('.xtext').html(text);
				ths.find('li').removeClass('selected');
				$(item).addClass('selected');
				if(ops.change){ops.change(xv,text,ops.xdata[index]);}
			},
			select:function(i){
				ths.find('ul').children().each(function(index,row){
					if(i === index){
						inside.itemClick(row);
					}
				});
				return this;
			},
			val:function(v){
				ths.find('ul').children().each(function(index,row){
					var value = $(row).data('xv');
					if(v == value){
						inside.itemClick(row);
					}
				});
			}
		}
		var xcombo = function(){
			inside.init();
		}
		var combo = new xcombo();		
		var outside = {
			setData:function(data){
				ops.xdata = data;
				inside.load();
			},
			getData:function(){
				return ops.xdata;
			},
			getRow:function(index){
				return ops.xdata[index];
			},
			select:function(i){
				inside.select(i);
			},
			val:function(v){
				if(v != undefined){
					inside.val(v);
				}else{
					return ths.find('input[type = hidden]').val();
				}
			},
			clear:function(){
				ths.find('li').removeClass('selected');
				ths.find('.xtext').html('请选择');
				ths.find('input[type = hidden]').val('');
				return this;
			},
			reload:function(data){
				$.extend(ops.data,data);
				inside.load();
			}
		}
		$.extend(combo,outside);
		$(ths.selector).data(ths.selector,combo);
		return combo;
	}
	$.fn.xaccordion = function(options){
		var ths = this;
		var defaults = {
			url:null,
			data:null,
			xdata:null,
			width:'auto',
			height:'auto',
			basePath:'',
			format:function(row){},
			success:function(){},
			parentClick:function(ops,event){},
			sonClick:function(ops,event){}
		}
		var ops = $.extend(defaults,options,{
			tpl:null,
			template:null,
			template_son:null
		});
		var inside = {
			init:function(){
				inside.template();
				inside.setSize();
				inside.load();
			},
			template:function(){
				var tpl_son = ths.find('.xson');
				ops.template_son = tpl_son.html();
				tpl_son.html("{xson}");
				ops.tpl = ths.find('.xtemplate');
				ops.template = ops.tpl.html();
			},
			setSize:function(){
				ths.width(ops.width);
				ths.height(ops.height);
			},
			load:function(){
				if(ops.url){
					$.ajax({
						url:ops.url,
						data:ops.data,
						type:'get',
						dataType:'json',
						success:inside.success,
						error:inside.error
					});
				}else if(ops.xdata){
					inside.draw(ops.xdata);
				}
			},
			success:function(data){
				inside.draw(data.data);
			},
			error:function(){
				$.error('数据加载失败！');
			},
			draw:function(data){
				ops.xdata = data;
            	ops.tpl.empty();
				ops.tpl.show();
            	$.each(ops.xdata,function(index,row){
            		row.index = index;
            		ops.format(row);
            		if(row.son!==undefined&&row.son!==null){
            			row.xson=inside.getSon(row.son);
            		}
            		ops.tpl.append($.xtemplate(ops.template,row));
            	});
            	inside.bind();
            	inside.call();
			},
			getSon:function(son){
				var html = '';
				$.each(son,function(index,row){
					html += $.xtemplate(ops.template_son,row)
				});
				return html;
			},
			bind:function(){
				inside.bindParent();
				inside.bindSon();
			},
			bindParent:function(){
				ths.find('.xparent').xbtn(function(btn, event){
					ths.find('.xson').hide();
					ths.find(".parent.selected").removeClass("selected");
					btn.ths.find(".parent").addClass("selected");
					btn.ths.find('.xson').show();
					ops.parentClick(btn, event)
				});
			},
			bindSon:function(){
				var son = ths.find('.son');
				if(son!==undefined&&son.length>0){
					ths.find('.son').xbtn(function(btn, event){
						ths.find(".selected").removeClass("selected");
						btn.ths.addClass("selected");
						ops.sonClick(btn, event)
					});
				}
			},
			call:function(){
				ops.success();
			},
			clear:function(){
            	ops.tpl.empty();
			}
		}
		var xaccordion = function(){
			inside.init();
		}
		var accordion = new xaccordion();
		var outside = {
			setData:function(data){
				ops.xdata = data;
				inside.load();
			},
			clear:function(){
				inside.clear();
			},
			select:function(i){
				ths.find('.xparent').each(function(index,row){
					if(i === index){
						$(row).trigger("click");
					}
				});
				return this;
			}
		}
		$.extend(accordion,outside);
		return accordion;
	}
    $.fn.xcheck=function(options,checkall){
    	var ths = this;
    	if(ths.length===0){$.logError('xcheck Unable to read element '+ths.selector);return;}
		if($.isArray(options)){
			$.each(options,function(index,row){
				if(row.msgBox===null||row.msgBox===undefined){
					$('input[name=' + row.name + ']').next('label').remove();
				}else{
					$(row.msgBox).html('');
				}
			})
			var bool = true;
			$.each(options,function(index,row){
				var element = ths.find('[name=' + row.name + ']')
				if(!element.xcheck(row)){
					bool = false;
					if(!checkall)return false;
				}
			})
			return bool;
		}
    	var _v = ths.val();
    	var defaults = {
			label:'',
			xcheck:null,
			regular:null,
			msgBox:null,
			msg:'<label class="error">{1}</label>',
			msgNull:'不能为空',
    		msgEmail:'请输入有效电子邮件',
    		msgPhone:'请输入有效手机号码',
    		msgPhoneline:'请输入有的座机号码',
    		msgPhoneall:'请输入有效电话号码',
    		msgUrl:'请输入有效的网址',
    		msgMax:'最多可以输入 {1} 个字符',
    		msgMin:'最少要输入 {1} 个字符',
    		msgLength:'请输入长度为 {1} 个字符',
    		msgNum:'请输入数字',
    		msgInt:'请输入整数',
    		msgIntp:'请输入非负整数',
    		msgIntb:'请输入非正整数',
    		msgFloat:'请输入浮点数',
    		msgFloatp:'请输非负浮点数',
    		msgFloatb:'请输非正浮点数',
    		msgMaxnum:'请输入不大于 {1} 的数值',
    		msgMinnum:'请输入不小于 {1} 的数值',
    		msgRegular:'输入格式有误',
    		msgEquals:'两次输入不一致',
    		msgCheck:'输入格式有误',
    		isNull:function(val){return $.trim(val)==='';},
    		isEmail:function(val){return /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/.test(val);},
    	    isPhone :function(val){return /^1\d{10}$/.test(val);},
    	    isPhoneline :function(val){return /^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/.test(val);},
    	    isPhoneall :function(val){return /(^1\d{10})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/.test(val);},
    	    isUrl:function(val){return /^(?:(?:(?:https?|ftp):)?\/\/)(?:\S+(?::\S*)?@)?(?:(?!(?:10|127)(?:\.\d{1,3}){3})(?!(?:169\.254|192\.168)(?:\.\d{1,3}){2})(?!172\.(?:1[6-9]|2\d|3[0-1])(?:\.\d{1,3}){2})(?:[1-9]\d?|1\d\d|2[01]\d|22[0-3])(?:\.(?:1?\d{1,2}|2[0-4]\d|25[0-5])){2}(?:\.(?:[1-9]\d?|1\d\d|2[0-4]\d|25[0-4]))|(?:(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)(?:\.(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)*(?:\.(?:[a-z\u00a1-\uffff]{2,})).?)(?::\d{2,5})?(?:[/?#]\S*)?$/i.test(val);},
    		isMax:function(val,max){return val.length <= max},
    		isMin:function(val,min){return val.length >= min},
    		isLength:function(val,min,max){return max ? val.length >= min && val.length <= max : val.length >= min;},
    		isNum:function(val){return /^(?:-?\d+|-?\d{1,3}(?:,\d{3})+)?(?:\.\d+)?$/.test(val);},
    		isInt:function(val){return /^-?[1-9]\d*|0$/.test(val);},
    		isIntp:function(val){return /^\d+$/.test(val);},
    		isIntb:function(val){return /^((-\d+)|(0+))$/.test(val);},
    		isFloat:function(val){return /^-?([1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0)$/.test(val);},
    		isFloatp:function(val){return /^[1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0$/.test(val);},
    		isFloatb:function(val){return /^(-([1-9]\d*\.\d*|0\.\d*[1-9]\d*))|0?\.0+|0$/.test(val);},
    		isMaxnum:function(val,max){try{return parseFloat(val) <= parseFloat(max);}catch (e) {return false;}},
    		isMinnum:function(val,min){try{return parseFloat(val) >= parseFloat(min);}catch (e) {return false;}},
    		isEquals:function(val){return $('input[name=' + ops.equals + ']').val()===val;},
    		check:function(){return true;}
		}
		var ops = $.extend(defaults, options, {
			type:[]
		});
		var inside = {
			init:function(){
				if(ops.xcheck){
					ops.type = ops.xcheck.split(',');
				}
			},
			error:function(e){
				if(ops.msgBox===null){
					ths.after($.format(ops.msg, e));
				}else{
					$(ops.msgBox).html(e);
				}
				return false;
			},
			clear:function(){
				if(ops.msgBox===null){
					ths.next('label').remove();
				}else{
					$(ops.msgBox).html('');
				}
			},
			checkXcheck:function(){
				if(ops.type.indexOf('null')==-1 && ops.isNull(_v)){return true;}
				for (i in ops.type) {
					var item = ops.type[i];
					if (item === 'null' && ops.isNull(_v)) {
						return inside.error(ops.label + ops.msgNull);
					} else if (item === 'null' && ops.isNull(_v)) {
						return inside.error(ops.label + ops.msgNull);
					} else if (item === 'email' && !ops.isEmail(_v)) {
						return inside.error(ops.msgEmail);
					} else if (item === 'phone' && !ops.isPhone(_v)) {
						return inside.error(ops.msgPhone);
					} else if (item === 'phoneline' && !ops.isPhoneline(_v)) {
						return inside.error(ops.msgPhoneline);
					} else if (item === 'phoneall' && !ops.isPhoneall(_v)) {
						return inside.error(ops.msgPhoneall);
					} else if (item === 'url' && !ops.isUrl(_v)) {
						return inside.error(ops.msgUrl);
					} else if (item.indexOf(':') != -1) {
						var arr = item.split(':');
						if (arr[0] === 'length' && !ops.isLength(_v, arr[1], arr[2])) {
							return inside.error(ops.label + $.format(ops.msgLength, arr[2]? arr[1] + '~' + arr[2] : arr[1]));
						}else if (arr[0] === 'max' && !ops.isMax(_v, arr[1])) {
							return inside.error(ops.label + $.format(ops.msgMax, arr[1]));
						} else if (arr[0] === 'min' && !ops.isMin(_v, arr[1])) {
							return inside.error(ops.label + $.format(ops.msgMin, arr[1]));
						} else if (arr[0] === 'maxnum' && !ops.isMaxnum(_v, arr[1])) {
							return inside.error(ops.label + $.format(ops.msgMinnum, arr[1]));
						} else if (arr[0] === 'minnum' && !ops.isMinnum(_v, arr[1])) {
							return inside.error(ops.label + $.format(ops.msgMinnum, arr[1]));
						}
					} else if (item === 'num' && !ops.isNum(_v)) {
						return inside.error(ops.label + ops.msgNum);
					} else if (item === 'int' && !ops.isInt(_v)) {
						return inside.error(ops.label + ops.msgInt);
					} else if (item === 'intp' && !ops.isIntp(_v)) {
						return inside.error(ops.label + ops.msgIntp);
					} else if (item === 'intb' && !ops.isIntb(_v)) {
						return inside.error(ops.label + ops.msgIntb);
					} else if (item === 'float' && !ops.isFloat(_v)) {
						return inside.error(ops.label + ops.msgFloat);
					} else if (item === 'floatp' && !ops.isFloatp(_v)) {
						return inside.error(ops.label + ops.msgFloatp);
					} else if (item === 'floatb' && !ops.isFloatb(_v)) {
						return inside.error(ops.label + ops.msgFloatb);
					} else if (item === 'equals' && !ops.isEquals(_v)) {
						return inside.error(ops.msgEquals);
					}
				}
				return true;
			},
			checkRegular:function(){
				var bool = ops.regular ? ops.regular.test(_v) : true;
    	    	if(!bool)inside.error(ops.label + ops.msgRegular);
    	    	return bool;
			},
			checkCheck:function(){
    	    	var bool = ops.check();
    	    	if(!bool)inside.error(ops.label + ops.msgCheck);
    	    	return bool;
    	    }
		}
		var xcheck = function(){
			inside.init();
		}
		var check = new xcheck();
		var outside = {
			check:function(){
				inside.clear();
				return inside.checkXcheck() && inside.checkRegular() && inside.checkCheck();
			}
		}
		$.extend(check,outside);
		return check.check();
    }
	$.fn.xupload = function(options) {
		var ths = this;
		if(ths.length===0){$.logError('xupload Unable to read element '+ths.selector);return;}
		var xupload = $(ths.selector).data(ths.selector);
		if(xupload){ return xupload; }
		var defaults = {
			url: null,
			form: null,
			xbtn: '.xbtn',
			xfile: '.xfile',
			xbox:'.xbox',
			ximg: '.ximg',
			xdel: '.xdel',
			imgBox:'<div class="xbox"><img class="ximg"/><span class="xdel">删除</span></div>',
			imgWidth: 100,
			imgHeight: 'auto',
			basePath: '',
			img: null,
			success: function(e) {},
			errorMsg : '请上传图片文件！'
		}
		var ops = $.extend(defaults, options, {
			ths:null
		});		
		var inside = {
			init:function(){
				if(ops.img){
					inside.loadImg(ops.img);
				}
				ops.ths = ths.find(ops.xfile);
				inside.bind();
			},
			bind:function(){
				inside.bindChange();
				inside.bindBtn();
			},
			bindChange:function(){
				ops.ths.unbind('change').change(function() {
					var val = ops.ths.val();
					if(!inside.checkImg(val))return;
					if(ops.url){
						inside.submit();
					}else{
						if(this.files && this.files[0]){
							var reader = new FileReader();
							reader.onload = function() {
								inside.loadImg(reader.result);
							}
							reader.readAsDataURL(this.files[0]);
						}else{
							inside.loadImg(val);
						}
					}
				});
			},
			checkImg:function(value){
				if(/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(value)){
					return true;
				}else{
					$.xalert({msg:ops.errorMsg});
					return false;
				}
			},
			bindBtn:function(){
				ths.find(ops.xbtn).unbind('click').click(function(){
					ops.ths.trigger('click');
				});
			},
			drawImgBox:function(){
				ths.find(ops.xbox).remove();
				ths.find(ops.xfile).after(ops.imgBox);
				ths.find(ops.xdel).unbind('click').click(function(){
					inside.clear();
				});
			},
			loadImg:function(src){
				if(navigator.userAgent.match(/MSIE/) != null){
					inside.drawImgBox();
					var imgDiv = ths.find(ops.ximg)[0];
	                imgDiv.style.width = ops.imgWidth;
	                imgDiv.style.height = ops.imgHeight;
	                imgDiv.src = 'data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==';
                	imgDiv.style.filter='progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)';
                	imgDiv.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
					ths.find(ops.xbox).show();
				}else{
					inside.drawImgBox();
					var img = ths.find(ops.ximg);
					img.attr('src',src);
					img.width(ops.imgWidth);
					img.height(ops.imgHeight);
					ths.find(ops.xbox).show();
				}
			},
			clear:function(){
				ths.find(ops.xbox).remove();
				var old = ops.ths;
				ops.ths = old.clone().val('');
				old.after(ops.ths);
				old.remove();
				inside.bind();
			},
			submit:function(){
				var form = $('<form enctype="multipart/form-data" method="post"></form>').appendTo('body');
				var old = ops.ths;
				ops.ths = old.clone().val('');
				old.after(ops.ths);
				form.append(old);
				inside.bind();
				form.xform({
					url:ops.url,
					fileupload:true,
					success:function(data){
						if(data.code===1){
							inside.loadImg(data);
							ops.success(data);
						}
						form.remove();
						ops.success(data);
					},
					error:function(){form.remove();$.xalert({msg:'请求失败！'});}
				}).submit();
			}
		}
		var ximgUpload = function(){
			inside.init();
		}
		var imgUpload = new ximgUpload();
		var outside = {
			loadImg:function(src){
				inside.loadImg(src);
			},
			clear:function(){
				inside.clear();
			}
		}
		$.extend(imgUpload,outside);
		$(ths.selector).data(ths.selector,imgUpload);
		return imgUpload;
	}
	$.fn.xdate = function(options) {
		var ths = this;
		if(ths.length===0){$.logError('xdate Unable to read element '+ths.selector);return;}
		var xupload = $(ths.selector).data(ths.selector);
		if(xupload){ return xupload; }
		var defaults = {
			id:'xdate_box',
			minDate:null,
			maxDate:null,
			time:false
		}
		var ops = $.extend(defaults, options,{
			box:null,
			xdata:null,
			week:['日', '一', '二', '三', '四', '五', '六'],
			months:[31, null, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31],
			y:null,
			m:null,
			d:null,
			hms:['00','00','00']
		});		
		var inside = {
			init:function(){
				inside.bindOpen();
			},
			now:function(){
				var now = new Date;
				ops.y = now.getFullYear();
				ops.m = now.getMonth();
				ops.d = now.getDate();
				if(ops.time){
					ops.hms[0] = inside.fill(now.getHours());
					ops.hms[1] = inside.fill(now.getMinutes());
					ops.hms[2] = inside.fill(now.getSeconds());
				}
			},
			create:function(){
				var html = '<div id="'+ops.id+'" class="xdate_box">'+
						'<div class="xdate_ym"><span class="prev_y"><i></i></span><span class="xdate_y">2015年</span><span class="next_y"><i></i></span>'+
						'<span class="prev_m"><i></i></span><span class="xdate_m">12月</span><span class="next_m"><i></i></span>'+
						'<div class="more_y"><a class="prev_ys"><i></i></a><ul><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li></ul><a class="next_ys"><i></i></a></div>'+
						'<div class="more_m"><ul><li m="0">1月</li><li m="1">2月</li><li m="2">3月</li><li m="3">4月</li><li m="4">5月</li><li m="5">6月</li><li m="6">7月</li><li m="7">8月</li><li m="8">9月</li><li m="9">10月</li><li m="10">11月</li><li m="11">12月</li></ul></div></div>'+
						'<table><thead><tr><th>日</th><th>一</th><th>二</th><th>三</th><th>四</th><th>五</th><th>六</th></tr></thead><tbody>'+
						'<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><tr/><tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><tr/>'+
						'<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><tr/><tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><tr/>'+
						'<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><tr/><tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><tr/></tbody></table>'+
						'<div class="xdate_bar">'+
						'<div class="xdate_time"><span>00</span>:<span>00</span>:<span>00</span></div>'+
						'<div class="more_time"><b>×</b><div class="more_title">时间</div><div class="more_hms"></div></div>'+
						'<div class="xdate_btns"><span class="xdate_clear">清空</span><span class="xdate_today">今天</span><span class="xdate_ok">确定</span></div>'+
						'</div><div></div></div>';
				ops.box = $(html).appendTo('body');
				ops.box.mouseup(function(e){e.stopPropagation ? e.stopPropagation() : e.cancelBubble = !0;});
				if(ops.time){$(".xdate_time").css("display","block")}
				inside.bindBtn();
				inside.moveDay(ops.y,ops.m,ops.d);
			},
			moveDay:function(y,m,d){
				inside.closeBox();
				var move = new Date(y,m,d||ops.d);
				ops.y = move.getFullYear();
				ops.m = move.getMonth();
				ops.d = move.getDate();
				$('.xdate_y').html(ops.y+'年');
				$('.xdate_m').html(ops.m+1+'月');
				inside.draw(ops.y,ops.m,ops.d);
			},
			moreY:function(y){
				var i=6,start = y - (y - 1900) % 12;
				$('.more_y li').each(function(){
					$(this).attr('y',start).html(start++);
				});
				$(".prev_ys").attr("y",y-12);
				$(".next_ys").attr("y",y+12);
				$(".more_y").show();
			},
			moreTime:function(index){
				var html = '';
				$.each(new Array(0 === index ? 24 : 60), function(i) {
					html += '<span>' + i + '</span>'
				});
				$(".more_title").html(["小时", "分钟", "秒数"][index]);
				$(".more_hms").html(html);
				$(".more_time").show();
				$(".more_hms span").click(function(){
					var vl = inside.fill(parseInt($(this).html()));
					ops.hms[index] = vl; 
					$(".xdate_time").children().eq(index).html(vl);
					$(".more_time").hide();
				});
			},
			draw:function(y,m,d){
				var now = null, date = 1, month = inside.getMonth(m+1), max = inside.getDays(y, m), week = inside.getWeek(y,m,1), 
					prevM = inside.getMonth(m), prevY = inside.getYear(y, m); prevMax = inside.getDays(y, m-1), prevDate = prevMax - (week===0?7:week), 
					nextDate = 1, nextM = inside.getMonth(m+2), nextY = inside.getYear(y, m+2);
				$('.xdate_ths').removeClass('xdate_ths');
				$('#xdate_box td').each(function(index,row){
					if(prevDate < prevMax){
						$(row).addClass('xdate_other').html(++prevDate).attr('y', prevY).attr('m', prevM).attr('d', prevDate);
						now = new Date(prevY,prevM-1,prevDate);
					}else if(date <= max){
						if(date===d){
							$(row).addClass('xdate_ths');
						}
						now = new Date(y,month-1,date);
						$(row).removeClass('xdate_other').attr('y', y).attr('m', month).attr('d', date).html(date++);
					}else{
						now = new Date(nextY,nextM-1,nextDate);
						$(row).addClass('xdate_other').attr('y', nextY).attr('m', nextM).attr('d', nextDate).html(nextDate++);
					}
					if(inside.isMinDate(now)||inside.isMaxDate(now)){
						$(row).addClass("xdate_no");
					}else{
						$(row).removeClass("xdate_no");
					}
				});
				$(".xdate_time span").each(function(index,row){
					row.innerHTML = ops.hms[index];
				})
				inside.resize();
				ops.box.show();
			},
			bindOpen:function(){
				ths.attr("readonly",true);
				ths.click(function(e){inside.open();});
				$(document).mouseup(function(a){ ops.box && inside.close(); });
			},
			bindBtn:function(){
				$('.prev_y').click(function(){inside.prevYear();})
				$('.next_y').click(function(){inside.nextYear();})
				$('.prev_m').click(function(){inside.prevMonth();})
				$('.next_m').click(function(){inside.nextMonth();})
				$('.xdate_box td').click(function(){
					var td = $(this);
					var y = parseInt(td.attr('y'));
					var m = parseInt(td.attr('m'));
					var d = parseInt(td.attr('d'));
					inside.setDate(y,m,d);
				})
				$('.xdate_y').click(function(){
					inside.closeBox();
					inside.moreY(ops.y);
				})
				$('.xdate_m').click(function(){
					inside.closeBox();
					$(".more_m").show();
				})
				$(".prev_ys,.next_ys").click(function(){
					var y = parseInt($(this).attr('y'));
					inside.moreY(y);
				})
				$(".more_y li").click(function(){
					var y = parseInt($(this).attr('y'));
					inside.moveDay(y,ops.m);
					$(".more_y").hide();
				})
				$(".more_m li").click(function(){
					var m = parseInt($(this).attr('m'));
					inside.moveDay(ops.y,m);
					$(".more_m").hide();
				})
				$(".xdate_clear").click(function(){
					inside.clear();
				})
				$(".xdate_today").click(function(){
					inside.now();
					inside.setDate(ops.y, ops.m+1, ops.d);
				})
				$(".xdate_ok").click(function(){
					inside.setDate(ops.y, ops.m+1, ops.d);
				})
				$(".xdate_ok").click(function(){
					inside.setDate(ops.y, ops.m+1, ops.d);
				})
				$(".xdate_time span").each(function(index,row){
					$(row).click(function(){
						inside.moreTime(index);
					})
				})
				$(".more_time b").click(function(){
					$(".more_time").hide();
				})
			},
			open:function(){
				var val = ths.val();
				if(val===''){
					inside.now();
				}
				inside.create();
			},
			close:function(){
				ops.box.remove();
			},
			clear:function(){
				ths.val("");
				$(".xdate_time span").each(function(index,row){
					row.innerHTML = "00";
					ops.hms[index] = "00";
				})
				inside.close();
			},
			closeBox:function(){
				$(".more_y").hide();
				$(".more_m").hide();
			},
			setDate:function(y,m,d){
				var now = new Date(y,m-1,d||ops.d);
				if(inside.isMinDate(now)){return}
				if(inside.isMaxDate(now)){return}
				m = inside.fill(m);
				d = inside.fill(d);
				var vl = y+'-'+m+'-'+d;
				if(ops.time){
					vl += ' '+ops.hms[0]+':'+ops.hms[1]+':'+ops.hms[2];
				}
				ths.val(vl);
				inside.moveDay(y,m-1,d);
				inside.close();
			},
			resize:function(){
				var top = ths.offset().top+ths.outerHeight()-1;
				if((top + ops.box.outerHeight()) > $(document).height()){
    	    		top = ths.offset().top - ops.box.outerHeight();
    	    	}
				ops.box.css({
					position:'fixed',
					left:ths.offset().left,
					top:ths.offset().top+ths.outerHeight()-1
				});
			},
			getDays:function(y,m){
				return ops.months[m] || new Date(y, (m+1), 0).getDate()
			},
			getYear:function(y,m){
				return m<1?y-1:m>12?y+1:y;
			},
			getMonth:function(m){
				return m<1?12:m>12?1:m;
			},
			getWeek:function(y,m,d){
				return new Date(y,m,d).getDay();
			},
			fill:function(a) {
				return 10 > a ? '0' + (0 | a) : a
			},
			prevYear:function(){
				inside.moveDay(ops.y-1,ops.m);
			},
			nextYear:function(){
				inside.moveDay(ops.y+1,ops.m);
			},
			prevMonth:function(){
				inside.moveDay(ops.y,ops.m-1);
			},
			nextMonth:function(){
				inside.moveDay(ops.y,ops.m+1);
			},
			isMinDate:function(date){
				if(ops.minDate!==null){
					var min = new Date(ops.minDate.replace(/-/g, "/"));
					if(date<min){
						return true;
					}
				}
				return false;
			},
			isMaxDate:function(date){
				if(ops.maxDate!==null){
					var max = new Date(ops.maxDate.replace(/-/g, "/"));
					if(date>max){
						return true;
					}
				}
				return false;
			}
		}
		var xdate = function(){
			inside.init();
		}
		var date = new xdate();
		var outside = {
			val:function(v){
				if(v != undefined){
					var date = v.split(" ");
					var ymd = date[0].split("-");
					if(ops.time&&date[1]!=null){
						var hms = date[1].split(":");
						ops.hms = hms;
					}
					inside.setDate(ymd[0],ymd[1],ymd[2]);
				}else{
					return ths.val()
				}
			}
		}
		$.extend(date,outside);
		$(ths.selector).data(ths.selector,date);
		return date;
	}
	$.fn.xslider = function(options) {
		var ths = this;
		if(ths.length===0){$.logError('xslider Unable to read element '+ths.selector);return;}
		var xfn = $(ths.selector).data(ths.selector);
		if(xfn){ return xfn; }
		var defaults = {
			url:null,
			xdata:null,
			autoplay:true,
			resize:true,
			width:'100%',
			basePath:'',
			index:0,
			duration:800,
			millisec:4000,
			format:function(row){},
			success:function(data){}
		}
		var ops = $.extend(defaults,options,{
			xbox:null,
			xpoint:null,
			xboxItem:'<a class="xitem" href="{url}"><img src="{img}"><div class="xtitle"><span>{title}</span></div></a>',
			xpointItem:'<span></span>',
			play:null,
			events:null
		});
		var inside = {
			init:function(){
				ops.events = ['mousedown', 'mousemove', 'mouseup'];
			    if (window.navigator.msPointerEnabled) ops.events = ['MSPointerDown', 'MSPointerMove', 'MSPointerUp'];
			    if (window.navigator.pointerEnabled) ops.events = ['pointerdown', 'pointermove', 'pointerup'];
				ths.width(ops.width);
				inside.loadBox();
				inside.loadData();
				inside.resize();
				inside.playStart();
				inside.bind();
			},
			loadBox:function(){
				var box = ths.find('.xbox');
				var point = ths.find(".xpoint");
				ops.xbox = box.length > 0 ? box : $('<div class="xbox"></div>').appendTo(ths);
				ops.xpoint = point.length > 0 ? point : $('<div class="xpoint"></div>').appendTo(ths);
			},
			loadData:function(){
				if(ops.url){
					$.ajax({
		                url:ops.url,
		                data:ops.data,
		                type:'POST',
		                dataType:'json',
		                success:inside.success,
		                error:inside.error
		            });
				}else if(ops.xdata){
					inside.draw();
				}else{
					ops.xdata = ops.xbox.children();
					inside.drawPoint();
				}
			},
			success:function(data){
            	ops.xdata = data;
				inside.draw();
			},
			draw:function(){
				$.each(ops.xdata,function(index,row){
					row.img = ops.basePath + row.img;
					ops.xbox.append($.xtemplate(ops.xboxItem,row));
				});
				inside.drawPoint();
			},
			drawPoint:function(){
				$.each(ops.xdata,function(index,row){
					ops.xpoint.append(ops.xpointItem);
				});
				ops.xpoint.children().eq(0).addClass("selected");
			},
			playStart:function(){
				if(ops.autoplay){
					clearTimeout(ops.play);
					ops.play = setTimeout(function(){outside.next();},ops.millisec);
				}
			},
			playStop:function(){
				if(ops.autoplay){
					clearTimeout(ops.play);
				}
			},
			move:function(off){
				if(ops.xdata.length>1){
					var left = ops.index * ths.width();
					if(off){
						ops.xbox.css({ left : -left });
					}else{
						ops.xbox.animate({ left : -left }, ops.duration, inside.playStart);
					}
					ops.xpoint.find(".selected").removeClass("selected");
					ops.xpoint.children().eq(ops.index).addClass("selected");
				}
			},
			bind:function(){
				if(ops.resize){
					inside.bindResize();
				}
				if(ops.xdata.length>1){
					inside.bindMove();
				}
				inside.bindPoint();
			},
			bindResize:function(){
				$(window).resize(function(){
					inside.resize();
					inside.move(true);
				});
			},
			bindMove:function(){
				var move = false, isTouch = false, downx, movex, leftStart, direction,
				eventStop = function(event){
					event.stopPropagation ? event.stopPropagation() : event.cancelBubble = !0;
					event.preventDefault ? event.preventDefault() : event.returnValue = !1;
				}
				var mousedown = function(event) {
					eventStop(event);
					isTouch = event.type === 'touchstart';
					move = true;
					downx = isTouch ? event.originalEvent.targetTouches[0].pageX : (event.pageX || event.clientX);
					leftStart = ops.xbox.offset().left;
					inside.playStop();
				},
				mousemove = function(event) {
					eventStop(event);
					if(move){
						var pageX = isTouch ? event.originalEvent.targetTouches[0].pageX : (event.pageX || event.clientX);
						movex = (pageX-downx);
						var left = leftStart + movex;
						ops.xbox.css({ left : left });
					}
				},
				mouseup = function(event){
					eventStop(event);
					if(move){movex < -10 ? outside.next() : movex > 10 ? outside.prev() : inside.move(); move = false;} 
				}
				ops.xbox.bind('click', eventStop);
				ops.xbox.bind('mousedown', mousedown);
				ops.xbox.bind('mousemove', mousemove)
				ops.xbox.bind('mouseup', mouseup);
				ops.xbox.bind('mouseleave', mouseup);
				if(ops.touch){
					ops.xbox.bind('touchstart', mousedown);
					ops.xbox.bind('touchmove', mousemove);
					ops.xbox.bind('touchend', mouseup);
				}
			},
			bindPoint:function(){
				ops.xpoint.find("span").each(function(index){
					$(this).click(function(){
						inside.playStop();
						ops.index = index;
						inside.move();
					});
				})
			},
			resize:function(){
				var w = ths.width();
				ops.xbox.width(w * ops.xdata.length).show();
				ths.find(".xitem").width(w);
			}
		}
		var xslider = function(){
			inside.init();
		}
		var slider = new xslider();
		var outside = {
			prev:function(){
				ops.index =  ops.index - 1 < 0 ? ops.xdata.length-1 : ops.index - 1;
				inside.move();
			},
			next:function(){
				ops.index =  ops.index + 1 > ops.xdata.length-1 ? 0 : ops.index + 1;
				inside.move();
			}
		}
		$.extend(slider,outside);
		$(ths.selector).data(ths.selector,slider);
		return slider;
	}
	
	$.fn.xbtn = function(options){
		var ths = this;
		if(ths.length===0){$.logError('xbtn Unable to read element '+ths.selector);return;}
		var xfn = $(ths.selector).data(ths.selector);
		if(xfn){ return xfn; }
		var defaults = {
			click:function(){}
		}
		if(typeof options === 'function'){
			options = {click:options}
		}
		var ops = $.extend(defaults, options, {
			ths:null,
			btn:true,
			close:function(){ops.btn=false;},
			open:function(){ops.btn=true;}
		});
		var inside = {
			init:function(){
				ths.unbind('click').click(function(event){
					event.stopPropagation();
					ops.ths = $(this);
					ops.xv = ops.ths.data('xv');
					$.extend(ops,ops.ths.get(0).dataset);
					if(ops.btn){
						ops.click(ops, event);
					}
				});
			},
			close:function(){
				ops.btn=false;
				ops.ths.addClass("xloading");
			},
			open:function(){
				ops.btn=true;
				ops.ths.removeClass("xloading");
			}
		}
		var xbtn = function(){
			inside.init();
		}
		btn = new xbtn();
		var outside = {
			close:function(){
				inside.close();
			},
			open:function(){
				inside.open();
			}
		}
		$.extend(btn,outside);
		$(ths.selector).data(ths.selector,btn);
		return btn;
	}
	$.closeBtn = function(target){
		$(target).xbtn().close();
	}
	$.openBtn = function(target){
		$(target).xbtn().open();
	}
	
	$.xtemplate = function (template, data) {
		return template.replace(/\{([\w\.]*)\}/g, function(str, key) {
			var keys = key.split('.'), v = data[keys.shift()];
			for (var i = 0, l = keys.length; i < l; i++) v = v[keys[i]];
			return (typeof v !== 'undefined' && v !== null) ? v : '';
		});
	}
	$.format = function(str){
		var args = arguments, re = new RegExp('\\{([1-' + args.length + '])\\}', 'g');
		return String(str).replace( re, function($1, $2) {
			return args[$2];
		});
	}
	$.log = function(content){
		if (window.console && window.console.log){
			window.console.log(content);
		}
	}
	$.logError = function(content){
		if (window.console && window.console.error){
			window.console.error(content);
		}
	}
	$.error = function(e,time,css){
		$('.msg_win').remove();
		var time = time||5000;
		var css = css===null?'style="top:0"':'style="'+css+'"';
		var dom = '<div class="msg_win" '+css+'><div class="error_box">'+e+'</div></div>';
		var xno = $(dom).appendTo('body');
		xno.fadeOut(time,function(){
			xno.remove();
		});
	}
	$.ok = function(e,time,css){
		$('.msg_win').remove();
		var time = time||4000;
		var css = css===null?'style="top:0"':'style="'+css+'"';
		var dom = '<div class="msg_win" '+css+'><div class="ok_box">'+e+'</div></div>';
		var xno = $(dom).appendTo('body');
		xno.fadeOut(time,function(){
			xno.remove();
		});
	}
	$.xget = function(options){
		var defaults = {
			url:null,
			data:null,
            type:'get',
            dataType:'json',
			success:function(){},
			error:function(){$.xalert({msg:'请求失败！'});}
		}
		var ops = $.extend(defaults,options);
		$.ajax(ops);
	}
	$.xpost = function(options){
		options.type = 'post';
		$.xget(options);
	}
	$.xalert = function(options) {
		var defaults = {
			id:'xalert',
			title:'信息提示',
			ok:'确定',
			no:'取消',
			msg:'',
			width: 260,
			confirm : false,
			okBack:function(){},
			noBack:function(){}
		}
		var ops = $.extend(defaults, options);
		ops.winId = ops.id+'_win';
		ops.coverId = ops.id+'_cove';
		ops.okId = ops.id+'_ok';
		ops.noId = ops.id+'_no';
		var inside = {
			init:function(){
				var buttons = '<div id="'+ops.okId+'">'+ops.ok+'</div>';
				if(ops.confirm){
					buttons = '<div id="'+ops.noId+'" class="no">'+ops.no+'</div><div id="'+ops.okId+'" class="ok right">'+ops.ok+'</div>';
				}
				var dom = '<div id="'+ops.coverId+'" class="xcover"></div>'+
				'<div id="'+ops.winId+'" class="xalert">'+
				'<div class="title">'+ops.title+'</div>'+
				'<div class="content">'+ops.msg+'</div>'+
				'<div class="buttons">'+buttons+'</div>'+				
				'</div>';
				$('body').append(dom);
				$('#'+ops.winId).width(ops.width);
			},
			bind:function(){
				$('#'+ops.okId).click(function(){
					outside.close();
					ops.okBack();
				});
				$('#'+ops.noId).click(function(){
					outside.close();
					ops.noBack();
				});
			},
			open:function(){
				$('#'+ops.winId).show();
				$('#'+ops.coverId).show();
			}
		}
		var xalert =  function(){
			inside.init();
			inside.bind();
			inside.open();
		}
		var alert = new xalert();
		var outside = {
			open:function(){
				inside.open();
			},
			close:function(){
				$('#'+ops.winId).remove();
				$('#'+ops.coverId).remove();
			}
		}
		$.extend(alert,outside);
		return alert;
	}
})(jQuery);