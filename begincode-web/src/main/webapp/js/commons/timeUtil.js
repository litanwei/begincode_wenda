/**
 * Created by Stay on 2016/9/17.
 */
/**
 * 此方法用于返回 xxx 前提问(回答)
 * @param problemAuthorName   问题的作者
 * @param size    问题对应的回答数
 * @param answer   回答实体json
 * @param time     问题创建的时间  (时间格式: yyyy-MM-dd HH:mm:ss)
 * @returns {string}   返回的文字
 */
function formatTime(problemAuthorName, size, answer, time) {
    var date = new Date(time);
    var dateNow = new Date();
    var diffTime = (dateNow.getTime() - date.getTime()) / 1000;
    if (size == 0) {
        if (diffTime < 60) {
            return "刚刚提问";
        }
        else if (diffTime > 60 && diffTime < 3600) {
            return problemAuthorName + " " + Math.ceil(diffTime / 60) + "分钟前提问";
        }
        else if (diffTime > 3600 && diffTime < 86400) {
            return problemAuthorName + " " + Math.ceil(diffTime / 60 / 60) + "小时前提问";
        } else if (diffTime > 86400 && diffTime < 259000) {
            return problemAuthorName + " " + Math.ceil(diffTime / 60 / 60 / 24) + "天前提问";
        } else if (diffTime > 259000) {
            return problemAuthorName + " " + Math.ceil(diffTime / 60 / 60 / 24) + "天" + "前提问";
        } else {
            return problemAuthorName + " " + (date.getMonth() + 1) + "月" + date.getDate() + "日" + "前回答";
        }
    } else {
        if (diffTime < 60) {
            return "刚刚提问";
        }
        else if (diffTime > 60 && diffTime < 3600) {
            return answer.userName + " " + Math.ceil(diffTime / 60) + "分钟前回答";
        }
        else if (diffTime > 3600 && diffTime < 86400) {
            return answer.userName + " " + Math.ceil(diffTime / 60 / 60) + "小时前回答";
        } else if (diffTime > 86400 && diffTime < 259000) {
            return answer.userName + " " + Math.ceil(diffTime / 60 / 60 / 24) + "天前回答";
        } else if (diffTime > 259000) {
            return answer.userName + " " + Math.ceil(diffTime / 60 / 60 / 24) + "天" + "前回答";
        } else {
            return answer.userName + " " + (date.getMonth() + 1) + "月" + date.getDate() + "日" + "前回答";
        }
    }


}