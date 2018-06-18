<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>

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
                <td><a href="/group/${groupId}/members/delete/${member.id}">Исключить</a></td>
            </tr>
            </tbody>
        </c:forEach>
    </table>
</div>
<div class="panel panel-success">
    <div class="panel-heading" role="button" data-toggle="collapse" href="#addMember" aria-expanded="true" aria-controls="addMember">
        Добавить в группу
    </div>
    <div class="panel-body collapse in" id="addMember" aria-expanded="true">
        <ul class="pagination">
            <li>
                <a href="#" aria-label="Previous">
                    <span aria-hidden="true">«</span>
                </a>
            </li>
            <li>
                <a href="#" aria-label="Next">
                    <span aria-hidden="true">»</span>
                </a>
            </li>
        </ul>
        <input type="text" class="form-control cst-inline-search" id="task-table-filter" data-action="filter" data-filters="#task-table" placeholder="Фильтр" />
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
                    <td><a href="/group/${groupId}/members/add/${user.id}">Добавить</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

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