<#include "../basic/base.ftlh">

<#macro page_body>
    <div class="container">
        <div class="row">
            <div class="col-12">
                <h1>${book.name}</h1>
                <span>Authors:</span>
                <#list book.authors as author>
                    <span class="mx-2"><a href="<@spring.url"/profile/${author.id}"/>">${author.nickname}</a></span>
                </#list>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <h3>Description:</h3>
                <p>${book.description}</p>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <h3>Chapters:</h3>
                <#list chapters as chapter_id, name>
                    <h4><a href="<@spring.url "/book/view/${book.id}/${chapter_id}"/>">${name}</a></h4>
                </#list>
            </div>
        </div>

        <div class="row">
            <div class="col"></div>
        </div>
    </div>
</#macro>

<#macro page_styles>
    <title>Book</title>
</#macro>

<@display_page/>