var reg = {
    username:/^[A-Za-z0-9\u4e00-\u9fa5]{3,20}$/,
    //用来判断用户名，第一位不能为数字，也就是小写字母或者大写字母，后面的内容\w表示字符（数字字母下划线）
    //要求是5-10位字符，所以出去第一位，还需要4-9位的\w
    password:/^[\w]{6,16}$/,
    //用来判断密码，html结构中要求是数字字符6到18位，\d表示数字
    tel:/^1[34578]\d{9}$/,
    //用来判断电话号码，通常手机号第一位为1，第二位只可能出现3.4.5.7.8，后面剩下的9位数字随机
    email:/^[1-9a-zA-Z_]\w*@[a-zA-Z0-9]+(\.[a-zA-Z]{2,})+$/,
    //用来判断邮箱，通常邮箱没有以0开头的，所以第一位为1-9数字或者小写字母或者大写字母，第二位开始任意字符
    //也可以只有第一位没有第二位，*表示至少0个，@后面同理，小写字母或者大写字母或者数字，.需要转意符，所以写成\.
    //点后面通常是com或者cn或者com.cn，所以是小写字母或者大写字母至少两位
    IDCard:/^[1-9]\d{7}((0[1-9])|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0[1-9])|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/,
    // IDCard:/^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$/,
    //用来判断身份证，身份证号有15位和18位之分，所以写两个，用或者
};
var arr = [
    // $("[name='user']")[0],
    $("[name='username']")[0],
    $("[name='password']")[0]
    // $("[name='tel']")[0],
    // $("[name='email']")[0],
    // $("[name='IDCard']")[0]
    //    document.getElementsByName('user')[0],
    //    document.getElementsByName('username')[0],
    //    document.getElementsByName('pwd')[0],
    //    document.getElementsByName('tel')[0],
    //    document.getElementById('email'),
    //    document.getElementsByName('IDCard')[0]
];

for(var i=0;i<arr.length;i++) {
    arr[i].onblur = function () {
        if (this.value) {
            if (reg[this.name].test(this.value)) {
                // this.parentNode.className = 'form-group col-md-5 right';   //判断正确的时候加的class
                this.nextElementSibling.style.display = 'none'; //判断正确让span隐藏
                // this.nextElementSibling.nextElementSibling.style.display = 'none'; //判断正确让i隐藏
                this.nextElementSibling.nextElementSibling.className = 'form-valid'; //判断正确显示正确的i
            } else {
                // this.parentNode.className = 'form-group col-md-5 wrong';   //判断错误的时候加的class
                this.nextElementSibling.style.display = 'block'; //判断错误显示span进行提示
                this.nextElementSibling.nextElementSibling.className = 'form-invalid'; //判断错误显示错误的i
                // this.nextElementSibling.className = 'wrong';
                // this.focus();
            }
        }
    };
}
//
// var oTip = document.getElementById('tip');
// var opwd = document.getElementsByName('pwd2')[0];
//
// opwd.onfocus = function(){
//     if(!arr[1].value){
//         arr[1].focus();
//         oTip.className = 'show';
//         setTimeout(function () {
//             oTip.className = '';
//         },1000);
//     }
// };
//
// opwd.onblur = function(){
//     if(this.value){
//         if(this.value != arr[1].value){
//             this.focus();
//             this.parentNode.className ='wrong';
//         }else{
//             this.parentNode.className ='right';
//         }
//     }
// };