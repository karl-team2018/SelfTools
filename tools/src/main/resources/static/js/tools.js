
var dataStr;
var tab;
var $;
var layer;
layui.config({
    base:"../js/"
}).extend({
    "bodyTab":"toolsbodyTab"
})

layui.use(['bodyTab','form','element','layer','jquery'],function(){

    var form = layui.form;
    var element = layui.element;
    $ = layui.$;
    layer = parent.layer === undefined ? layui.layer : top.layer;
    tab = layui.bodyTab({
        openTabNum : "50",
        url:"../js/testjson.json"
    });




    //点击顶部功能集菜单时，渲染右边功能集对应的具体功能列表
    function getMenuDataFromJson(menuName) {
        console.info(menuName)
        $.getJSON(tab.tabConfig.url,function (respData) {
            if(menuName == "contentManagement"){
                dataStr = respData.contentManagement;
                console.info(JSON.stringify(dataStr))
                tab.render();
            }else if(menuName=="memberCenter"){
                dataStr = respData.memberCenter;
                tab.render();
            }else if(menuName=="projectManager"){
                dataStr = respData.projectManager;
                tab.render();
            }else if(menuName=="employeeManagement"){
                dataStr = respData.employeeManagement;
                tab.render();
            }else{
                dataStr = null;
                layer.msg('功能未开发或出现错误，请联系系统管理员！');
            }
        })
    }
    //点击顶部功能集菜单时，渲染右边功能集对应的具体功能列表
    function getMenuData(menuName) {
        console.info(menuName)
        $.ajax({
            type: 'GET',
            url: '/data/menu',
            data: {},
            success: function (respData) {
                console.info(JSON.stringify(respData));
                if(menuName == "contentManagement"){
                    dataStr = respData.contentManagement;
                    console.info(JSON.stringify(dataStr))
                    tab.render();
                }else if(menuName=="memberCenter"){
                    dataStr = respData.memberCenter;
                    tab.render();
                }else if(menuName=="projectManager"){
                    dataStr = respData.projectManager;
                    tab.render();
                }else if(menuName=="employeeManagement"){
                    dataStr = respData.employeeManagement;
                    tab.render();
                }else{
                    dataStr = null;
                    layer.msg('功能未开发或出现错误，请联系系统管理员！');
                }
            },
            error:function (respMsg) {
                layer.msg(JSON.stringify(respMsg));
                if(respMsg && respMsg.responseJSON && respMsg.responseJSON.status==401){
                    window.location.href="/login"
                }

            }
        })

    }

    $(".home-toplevel-menu li").click(function () {
        $(".home-toplevel-menu li").eq($(this).index()).addClass("layui-this").siblings().removeClass("layui-this");
        //$(".layui-layout-admin").removeClass("showMenu")
        getMenuData($(this).data("menu"))
        tab.tabMove();
    })


    //左侧栏通过样式显示和隐藏
    $(".hideLeftMenu").click(function () {
        $(".layui-layout-admin").toggleClass("showLeftMenu")
        tab.tabMove();
    })

    $("body").on("click",".layui-nav .layui-nav-item a",function(){
        if($(this).siblings().length == 0){
            addTab($(this));
            $('body').removeClass('site-mobile');  //绉诲姩绔偣鍑昏彍鍗曞叧闂彍鍗曞眰
        }
        $(this).parent("li").siblings().removeClass("layui-nav-itemed");
    })



    // 清除缓存
    $(".clearCache").click(function() {
        var index = layer.msg('清除缓存中', {icon: 16, time: false, shade: 0.8});
        window.sessionStorage.clear();
        window.localStorage.clear();
        setTimeout(function () {
            layer.close(index);
            layer.msg("清除成功！");
        }, 1000);
    })

    //锁定页面事件绑定
    $(".home-lockcms").on("click",function () {
        window.sessionStorage.setItem("locktools",true);
        window.sessionStorage.setItem("lockpage_pwd","123456");
        lockPage();
    })
    //如果锁定页面时，被关闭，重新打开页面时，仍然需要输入密码
    if(window.sessionStorage.getItem("locktools") == "true"){
        lockPage();
    }

    //动态渲染出的弹窗，此处绑定body元素点击事件，过滤id为unlock的元素
    $("body").on("click","#unlock",function(){
        if($(this).siblings(".admin-header-lock-input").val() == ''){
            layer.msg("输入内容为空，请重新输入！");
            $(this).siblings(".admin-header-lock-input").focus();
        }else{

            if($(this).siblings(".admin-header-lock-input").val() == window.sessionStorage.getItem("lockpage_pwd")){
                window.sessionStorage.setItem("locktools",false);
                $(this).siblings(".admin-header-lock-input").val('');
                layer.closeAll("page");
            }else{
                layer.msg("输入的解锁密码不正确，请重新输入！");
                $(this).siblings(".admin-header-lock-input").val('').focus();
            }
        }
    });
    $(document).on('keydown', function(event) {
        var event = event || window.event;
        if(event.keyCode == 13) {
            $("#unlock").click();
        }
    });


    $(".home-showNotice").on("click",function () {
        showNoticeFunc();
    })


    $(".functionSetting").on("click",function () {
        layer.msg("暂未开发此功能，请联系系统管理员！");
    })

    $(".changeSkin").on("click",function () {
        layer.msg("暂未开发此功能，请联系系统管理员！");
    })

})

function showNoticeFunc() {
 layer.open({
     type:1,
     title:"公告示例",
     area:'600px',
     shade:0.8,
     btn:['知道了'],
     moveType:1,
     content:'121',
     success:function (layerobj) {
         
     },
     cancel:function (index,layerobj) {
         
     }
 })
}

//锁定页面
function lockPage() {
    layer.open({
        title:false,
        type:1,
        content:'<div class="admin-header-lock" id="lock-box">'+
        '<div class="admin-header-lock-img"><img src="images/face.jpg" class="userAvatar"/></div>'+
        '<div class="admin-header-lock-name" id="lockUserName">用户名</div>'+
        '<div class="input_btn">'+
        '<input type="password" class="admin-header-lock-input layui-input" autocomplete="off" placeholder="请输入正确解锁密码.." name="lockPwd" id="lockPwd" />'+
        '<button class="layui-btn" id="unlock">解锁</button>'+
        '</div>'+
        '<p>解锁密码为锁屏时设定的密码，如果忘记，可清空缓存后重新登陆！</p>'+
        '</div>',
        closeBtn:0,
        shade:0.8,
        success:function () {
            
        }
    })
    $(".admin-header-lock-input").focus();
    
}


function addTab(_this){
    tab.tabAdd(_this);
}


//慷慨捐赠
function donation(){
    layer.tab({
        area : ['260px', '367px'],
        tab : [{
            title : "微信钱包",
            content : "<div style='padding:30px;overflow:hidden;background:#d2d0d0;'><img src='../img/wechat.jpg' width='200px' height='264px'></div>"
        },{
            title : "支付宝",
            content : "<div style='padding:30px;overflow:hidden;background:#d2d0d0;'><img src='../img/alipay.jpg' width='200px' height='264px'></div>"
        }]
    })
}