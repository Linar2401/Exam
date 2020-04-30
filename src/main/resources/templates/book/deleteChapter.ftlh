<#include "../basic/base.ftlh">

<#macro page_body>
    <div class="container">
        <div class="row">
            <div class="col-12">
                <form action="<@spring.url "/book/delete/${book.id}/${chapter.id}"/>" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <p>Are you sure you want to delete chapter "${chapter.getName()}" in book "${book.getName()}"</p>
                    <button type="submit" class="btn btn-danger">Delete</button>
                </form>
            </div>
        </div>
    </div>
</#macro>

<#macro page_styles>
    <title>Delete Book chapter</title>
</#macro>

<@display_page/>