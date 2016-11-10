/**
 * Created by Stay on 2016/9/21.
 */
function pagination(data,url,id,method) {
    $("#paginationId").empty();
    $("#paginationId").jqPaginator({
        totalCounts: data.totalNum,
        pageSize: data.pageEachSize,
        visiblePages: 4,
        first: '<li class="first"><a href="javascript:void(0);">首页<\/a><\/li>',
        prev: '<li class="prev"><a href="javascript:void(0);"><i class="arrow arrow2"><\/i>上一页<\/a><\/li>',
        next: '<li class="next"><a href="javascript:void(0);">下一页<i class="arrow arrow3"><\/i><\/a><\/li>',
        last: '<li class="last"><a href="javascript:void(0);">末页<\/a><\/li>',
        page: '<li class="page"><a href="javascript:void(0);">{{page}}<\/a><\/li>',
        activeClass: 'active',
        onPageChange: function (currentPage, type) {
            if (type == "change") {
                $.ajax({
                    type: method,
                    url: ctx+"/problem/"+url+".htm?page="+currentPage,
                    dataType: "json",
                    success: function (data) {
                        getProblems(data, id);
                    }
                });
            }
        }
    })
}
function userPagination(data,url,id,method) {
    $("#paginationId").empty();
    $("#paginationId").jqPaginator({
        totalCounts: data.totalNum,
        pageSize: data.pageEachSize,
        visiblePages: 4,
        first: '<li class="first"><a href="javascript:void(0);">首页<\/a><\/li>',
        prev: '<li class="prev"><a href="javascript:void(0);"><i class="arrow arrow2"><\/i>上一页<\/a><\/li>',
        next: '<li class="next"><a href="javascript:void(0);">下一页<i class="arrow arrow3"><\/i><\/a><\/li>',
        last: '<li class="last"><a href="javascript:void(0);">末页<\/a><\/li>',
        page: '<li class="page"><a href="javascript:void(0);">{{page}}<\/a><\/li>',
        activeClass: 'active',
        onPageChange: function (currentPage, type) {
            if (type == "change") {
                $.ajax({
                    type: method,
                    url: ctx+"/user"+url+".htm?page="+currentPage,
                    dataType: "json",
                    success: function (data) {
                        getProblems(data, id);
                    }
                });
            }
        }
    })
}
function userAnswerPagination(data,url,id,method) {
    $("#paginationId").empty();
    $("#paginationId").jqPaginator({
        totalCounts: data.totalNum,
        pageSize: data.pageEachSize,
        visiblePages: 4,
        first: '<li class="first"><a href="javascript:void(0);">首页<\/a><\/li>',
        prev: '<li class="prev"><a href="javascript:void(0);"><i class="arrow arrow2"><\/i>上一页<\/a><\/li>',
        next: '<li class="next"><a href="javascript:void(0);">下一页<i class="arrow arrow3"><\/i><\/a><\/li>',
        last: '<li class="last"><a href="javascript:void(0);">末页<\/a><\/li>',
        page: '<li class="page"><a href="javascript:void(0);">{{page}}<\/a><\/li>',
        activeClass: 'active',
        onPageChange: function (currentPage, type) {
            if (type == "change") {
                $.ajax({
                    type: method,
                    url: ctx+"/user"+url+".htm?page="+currentPage,
                    dataType: "json",
                    success: function (data) {
                        answerHtml(id,data.data);
                    }
                });
            }
        }
    })
}
function messagePagination(data,url,id,method) {
    $("#paginationId").empty();
    $("#paginationId").jqPaginator({
        totalCounts: data.totalNum,
        pageSize: data.pageEachSize,
        visiblePages: 4,
        first: '<li class="first"><a href="javascript:void(0);">首页<\/a><\/li>',
        prev: '<li class="prev"><a href="javascript:void(0);"><i class="arrow arrow2"><\/i>上一页<\/a><\/li>',
        next: '<li class="next"><a href="javascript:void(0);">下一页<i class="arrow arrow3"><\/i><\/a><\/li>',
        last: '<li class="last"><a href="javascript:void(0);">末页<\/a><\/li>',
        page: '<li class="page"><a href="javascript:void(0);">{{page}}<\/a><\/li>',
        activeClass: 'active',
        onPageChange: function (currentPage, type) {
            if (type == "change") {
                $.ajax({
                    type: method,
                    url: ctx+"/message"+url+".htm?page="+currentPage,
                    dataType: "json",
                    success: function (data) {
                        messageHtml(data.data.data,id);
                    }
                });
            }
        }
    })
}
