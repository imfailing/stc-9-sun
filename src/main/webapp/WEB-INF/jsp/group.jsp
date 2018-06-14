<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>

<div class="panel panel-default">
    <!-- Содержание панели по умолчанию -->
    <div class="panel-heading">${group.title}</div>
    <!-- Таблица -->
    <table class="table">
        <form:form method="post" action="" modelAttribute="group" cssClass="form-inline">
            <form:input path="id" hidden="true"/>
            <tr>
                <td>Название</td>
                <td>${group.title}</td>
                <td>
                    <c:if test="${group.id != 0}">
                        <a role="button" data-toggle="collapse" href="#changeTitle" aria-expanded="false" aria-controls="changeTitle">
                            Изменить
                        </a>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td colspan="3" class="custom-td">
                    <div <c:if test="${group.id != 0}"> class="collapse" id="changeTitle" </c:if>>
                        <div class="well">
                            <div class="form-group">
                                <form:input path="title" placeholder="Введите название" cssClass="form-control"/>
                                <form:errors path="title" cssClass="alert alert-danger custom-alert" role="alert"/>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td>Описание</td>
                <td>${group.description}</td>
                <td>
                    <c:if test="${group.id != 0}">
                        <a role="button" data-toggle="collapse" href="#changeDescription" aria-expanded="false" aria-controls="changeDescription">
                            Изменить
                        </a>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td colspan="3" class="custom-td">
                    <div <c:if test="${group.id != 0}"> class="collapse" id="changeDescription" </c:if>>
                        <div class="well">
                            <div class="form-group">
                                <form:input path="description" placeholder="Введите описание" cssClass="form-control"/>
                                <form:errors path="description" cssClass="alert alert-danger custom-alert" role="alert"/>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td>Дата начала</td>
                <td>${group.start_date}</td>
                <td>
                    <c:if test="${group.id != 0}">
                        <a role="button" data-toggle="collapse" href="#changeStart" aria-expanded="false" aria-controls="changeStart">
                            Изменить
                        </a>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td colspan="3" class="custom-td">
                    <div <c:if test="${group.id != 0}"> class="collapse" id="changeStart" </c:if>>
                        <div class="well">
                            <div class="form-group">
                                <form:input path="start_date" placeholder="Введите дату начала" cssClass="form-control"/>
                                <form:errors path="start_date" cssClass="alert alert-danger custom-alert" role="alert"/>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td>Дата завершения</td>
                <td>${group.finished_date}</td>
                <td>
                    <c:if test="${group.id != 0}">
                        <a role="button" data-toggle="collapse" href="#changeFinish" aria-expanded="false" aria-controls="changeFinish">
                            Изменить
                        </a>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td colspan="3" class="custom-td">
                    <div <c:if test="${group.id != 0}"> class="collapse" id="changeFinish" </c:if>>
                        <div class="well">
                            <div class="form-group">
                                <form:input path="finished_date" placeholder="Введите дату завершения" cssClass="form-control"/>
                                <form:errors path="finished_date" cssClass="alert alert-danger custom-alert" role="alert"/>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td colspan="3">
                    <button type="submit" class="btn btn-success">Сохранить</button>
                    <c:if test="${group.id != 0}">
                        <a role="button" class="btn btn-danger" href="/group/del/${group.id}">
                            Удалить
                        </a>
                    </c:if>
                </td>
            </tr>
        </form:form>
    </table>
</div>

<c:if test="${group.id != 0}">
    <div class="panel panel-success">
        <div class="panel-heading" role="button" data-toggle="collapse" href="#addMember" aria-expanded="false" aria-controls="addMember">
            Добавить в группу
        </div>
        <div class="panel-body collapse" id="addMember">
            <input type="text" class="form-control" id="task-table-filter" data-action="filter" data-filters="#task-table" placeholder="Фильтр" />
            <table class="table table-hover" id="task-table">
                <thead>
                <tr>
                    <th>id</th>
                    <th>ФИО</th>
                    <th>E-Mail</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>${user.id}</td>
                        <td><a href="/users/${user.id}/">${user.fullName}</a></td>
                        <td>${user.email}</td>
                        <td><a href="/group/${group.id}/members/add/${user.id}">Добавить</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="panel panel-default">
        <!-- Содержание панели по умолчанию -->
        <div class="panel-heading">Состав</div>
        <!-- Таблица -->
        <table class="table table-hover">
            <thead>
            <tr>
                <th>#</th>
                <th>ФИО</th>
                <th>E-Mail</th>
                <th></th>
            </tr>
            </thead>
            <c:set var="number" value="0"/>
            <c:forEach items="${members}" var="member">
            <tbody>
                <tr>
                    <td>${number=number+1}</td>
                    <td><a href="/users/${member.id}/">${member.fullName}</a></td>
                    <td>${member.email}</td>
                    <td><a href="/group/${group.id}/members/delete/${member.id}">Исключить</a></td>
                </tr>
            </tbody>
            </c:forEach>
        </table>
    </div>
</c:if>
<%@include file="footer.jsp" %>
<script>
    (function(){
        'use strict';
        var $ = jQuery;
        $.fn.extend({
            filterTable: function(){
                return this.each(function(){
                    $(this).on('keyup', function(e){
                        $('.filterTable_no_results').remove();
                        var $this = $(this),
                            search = $this.val().toLowerCase(),
                            target = $this.attr('data-filters'),
                            $target = $(target),
                            $rows = $target.find('tbody tr');

                        if(search == '') {
                            $rows.show();
                        } else {
                            $rows.each(function(){
                                var $this = $(this);
                                $this.text().toLowerCase().indexOf(search) === -1 ? $this.hide() : $this.show();
                            })
                            if($target.find('tbody tr:visible').size() === 0) {
                                var col_count = $target.find('tr').first().find('td').size();
                                var no_results = $('<tr class="filterTable_no_results"><td colspan="'+col_count+'">No results found</td></tr>')
                                $target.find('tbody').append(no_results);
                            }
                        }
                    });
                });
            }
        });
        $('[data-action="filter"]').filterTable();
    })(jQuery);

    $(function(){
        // attach table filter plugin to inputs
        $('[data-action="filter"]').filterTable();

        $('.container').on('click', '.panel-heading span.filter', function(e){
            var $this = $(this),
                $panel = $this.parents('.panel');

            $panel.find('.panel-body').slideToggle();
            if($this.css('display') != 'none') {
                $panel.find('.panel-body input').focus();
            }
        });
        $('[data-toggle="tooltip"]').tooltip();
    })
</script>