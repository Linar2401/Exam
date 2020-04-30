<#include "../basic/base.ftlh">

<#macro page_body>
    ​<h2>Profile</h2>
    <div class="container">
        <div class="row">
            <div class="col-10">
                <h3>Author info:</h3>
                <p>Nickname: ${author.nickname}</p>
                <p>Email: ${author.email}</p>
            </div>
        </div>
        <div class="row">
            <div class="col-12">
                <h3 class="m-2">Books</h3>
                <div class="container-fluid">
                    <div class="row">
                        <#list bookList as book>
                            <div class="card col-3 m-2" style="width: 18rem;">
                                <div class="card-body">
                                    <h5 class="card-title">${book.name}</h5>
                                    <h6 class="card-subtitle mb-2 text-muted">Size: ${book.chapterCounter}</h6>
                                    <p class="card-text">${book.description}</p>
                                    <div class="btn-group" role="group" aria-label="Basic example">
                                        <a type="button" class="card-link btn btn-primary" href="<@spring.url "/book/view/${book.id}"/>">
                                            View
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </#list>
                    </div>
                </div>

            </div>
        </div>
    </div>


</#macro>

<#macro page_styles>
    <title>Author</title>
</#macro>

<@display_page/>