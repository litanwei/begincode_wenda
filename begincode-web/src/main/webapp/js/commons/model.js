/**
 * Created by Stay on 2016/11/17.
 */
/**
 * 点击关闭此模态框不会跳转
 * 
 * @param msg
 */
function showModelNoBack(msg) {
    $("#errorMessage").html(msg);
    $("#ajaxModal").modal("show");
}

/**
 * 点击关闭此模态框会跳转回首页
 *
 */
function showModelBack(msg) {
    $("#tipMessage").html(msg);
    $("#myModal").modal({backdrop: 'static', keyboard: false}).modal("show"); //禁用点击空白地方关闭modal框
}
