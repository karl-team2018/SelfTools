
var $,tab,dataStr,layer;
layui.config({
    base : "js/"
}).extend({
    "bodyTab" : "bodyTab"
})
layui.use(['bodyTab','form','element','layer','jquery'],function(){
    var form = layui.form,
        element = layui.element;
    $ = layui.$;
    layer = parent.layer === undefined ? layui.layer : top.layer;
    tab = layui.bodyTab({
        openTabNum : "50",  //鏈€澶у彲鎵撳紑绐楀彛鏁伴噺
        url : "json/navs.json" //鑾峰彇鑿滃崟json鍦板潃
    });

    //閫氳繃椤堕儴鑿滃崟鑾峰彇宸︿晶浜屼笁绾ц彍鍗�   娉細姝ゅ鍙仛婕旂ず涔嬬敤锛屽疄闄呭紑鍙戜腑閫氳繃鎺ュ彛浼犲弬鐨勬柟寮忚幏鍙栧鑸暟鎹�
    function getData(json){
        $.getJSON(tab.tabConfig.url,function(data){
            if(json == "contentManagement"){
                dataStr = data.contentManagement;
                //閲嶆柊娓叉煋宸︿晶鑿滃崟
                tab.render();
            }else if(json == "memberCenter"){
                dataStr = data.memberCenter;
                //閲嶆柊娓叉煋宸︿晶鑿滃崟
                tab.render();
            }else if(json == "systemeSttings"){
                dataStr = data.systemeSttings;
                //閲嶆柊娓叉煋宸︿晶鑿滃崟
                tab.render();
            }else if(json == "seraphApi"){
                dataStr = data.seraphApi;
                //閲嶆柊娓叉煋宸︿晶鑿滃崟
                tab.render();
            }
        })
    }
    //椤甸潰鍔犺浇鏃跺垽鏂乏渚ц彍鍗曟槸鍚︽樉绀�
    //閫氳繃椤堕儴鑿滃崟鑾峰彇宸︿晶鑿滃崟
    $(".topLevelMenus li,.mobileTopLevelMenus dd").click(function(){
        if($(this).parents(".mobileTopLevelMenus").length != "0"){
            $(".topLevelMenus li").eq($(this).index()).addClass("layui-this").siblings().removeClass("layui-this");
        }else{
            $(".mobileTopLevelMenus dd").eq($(this).index()).addClass("layui-this").siblings().removeClass("layui-this");
        }
        $(".layui-layout-admin").removeClass("showMenu");
        $("body").addClass("site-mobile");
        getData($(this).data("menu"));
        //娓叉煋椤堕儴绐楀彛
        tab.tabMove();
    })

    //闅愯棌宸︿晶瀵艰埅
    $(".hideMenu").click(function(){
        if($(".topLevelMenus li.layui-this a").data("url")){
            layer.msg("姝ゆ爮鐩姸鎬佷笅宸︿晶鑿滃崟涓嶅彲灞曞紑");  //涓昏涓轰簡閬垮厤宸︿晶鏄剧ず鐨勫唴瀹逛笌椤堕儴鑿滃崟涓嶅尮閰�
            return false;
        }
        $(".layui-layout-admin").toggleClass("showMenu");
        //娓叉煋椤堕儴绐楀彛
        tab.tabMove();
    })

    //閫氳繃椤堕儴鑿滃崟鑾峰彇宸︿晶浜屼笁绾ц彍鍗�   娉細姝ゅ鍙仛婕旂ず涔嬬敤锛屽疄闄呭紑鍙戜腑閫氳繃鎺ュ彛浼犲弬鐨勬柟寮忚幏鍙栧鑸暟鎹�
    getData("contentManagement");

    //鎵嬫満璁惧鐨勭畝鍗曢€傞厤
    $('.site-tree-mobile').on('click', function(){
        $('body').addClass('site-mobile');
    });
    $('.site-mobile-shade').on('click', function(){
        $('body').removeClass('site-mobile');
    });

    // 娣诲姞鏂扮獥鍙�
    $("body").on("click",".layui-nav .layui-nav-item a:not('.mobileTopLevelMenus .layui-nav-item a')",function(){
        //濡傛灉涓嶅瓨鍦ㄥ瓙绾�
        if($(this).siblings().length == 0){
            addTab($(this));
            $('body').removeClass('site-mobile');  //绉诲姩绔偣鍑昏彍鍗曞叧闂彍鍗曞眰
        }
        $(this).parent("li").siblings().removeClass("layui-nav-itemed");
    })

    //娓呴櫎缂撳瓨
    $(".clearCache").click(function(){
        window.sessionStorage.clear();
        window.localStorage.clear();
        var index = layer.msg('娓呴櫎缂撳瓨涓紝璇风◢鍊�',{icon: 16,time:false,shade:0.8});
        setTimeout(function(){
            layer.close(index);
            layer.msg("缂撳瓨娓呴櫎鎴愬姛锛�");
        },1000);
    })

    //鍒锋柊鍚庤繕鍘熸墦寮€鐨勭獥鍙�
    if(cacheStr == "true") {
        if (window.sessionStorage.getItem("menu") != null) {
            menu = JSON.parse(window.sessionStorage.getItem("menu"));
            curmenu = window.sessionStorage.getItem("curmenu");
            var openTitle = '';
            for (var i = 0; i < menu.length; i++) {
                openTitle = '';
                if (menu[i].icon) {
                    if (menu[i].icon.split("-")[0] == 'icon') {
                        openTitle += '<i class="seraph ' + menu[i].icon + '"></i>';
                    } else {
                        openTitle += '<i class="layui-icon">' + menu[i].icon + '</i>';
                    }
                }
                openTitle += '<cite>' + menu[i].title + '</cite>';
                openTitle += '<i class="layui-icon layui-unselect layui-tab-close" data-id="' + menu[i].layId + '">&#x1006;</i>';
                element.tabAdd("bodyTab", {
                    title: openTitle,
                    content: "<iframe src='" + menu[i].href + "' data-id='" + menu[i].layId + "'></frame>",
                    id: menu[i].layId
                })
                //瀹氫綅鍒板埛鏂板墠鐨勭獥鍙�
                if (curmenu != "undefined") {
                    if (curmenu == '' || curmenu == "null") {  //瀹氫綅鍒板悗鍙伴椤�
                        element.tabChange("bodyTab", '');
                    } else if (JSON.parse(curmenu).title == menu[i].title) {  //瀹氫綅鍒板埛鏂板墠鐨勯〉闈�
                        element.tabChange("bodyTab", menu[i].layId);
                    }
                } else {
                    element.tabChange("bodyTab", menu[menu.length - 1].layId);
                }
            }
            //娓叉煋椤堕儴绐楀彛
            tab.tabMove();
        }
    }else{
        window.sessionStorage.removeItem("menu");
        window.sessionStorage.removeItem("curmenu");
    }
})

//鎵撳紑鏂扮獥鍙�
function addTab(_this){
    tab.tabAdd(_this);
}

//鎹愯禒寮圭獥
function donation(){
    layer.tab({
        area : ['260px', '367px'],
        tab : [{
            title : "寰俊",
            content : "<div style='padding:30px;overflow:hidden;background:#d2d0d0;'><img src='images/wechat.jpg'></div>"
        },{
            title : "鏀粯瀹�",
            content : "<div style='padding:30px;overflow:hidden;background:#d2d0d0;'><img src='images/alipay.jpg'></div>"
        }]
    })
}

//鍥剧墖绠＄悊寮圭獥
function showImg(){
    $.getJSON('json/images.json', function(json){
        var res = json;
        layer.photos({
            photos: res,
            anim: 5
        });
    });
}