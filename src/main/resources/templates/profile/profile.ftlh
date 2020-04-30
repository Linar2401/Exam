<#include "../basic/base.ftlh">

<#macro page_styles>
    <title>Profile</title>
</#macro>

<#macro page_body>
    ​<h2>Profile</h2>
    <div class="container">
        <div class="row">
            <div class="col-10">
                <h3>Profile info:</h3>
                <p>Nickname: ${user.nickname}</p>
                <p>Email: ${user.email}</p>
                <p>Country: ${user.country}</p>
            </div>
        </div>
        <div class="row">
            <div class="col-12">
                <h3 class="m-2">Books</h3>
                <a type="button" class="btn btn-primary m-2" href="<@spring.url "/book/add"/>">
                    Create new book
                </a>
                <hr>
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
                                        <a type="button" class="card-link btn btn-success" href="<@spring.url "/book/update/${book.id}"/>">
                                            Edit
                                        </a>
                                        <a type="button" class="card-link btn btn-danger" href="<@spring.url "/book/delete/${book.id}"/>">
                                            Delete
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

<@display_page/>