<#include "../basic/base.ftlh">

<#macro page_body>
    <div class="container">
        <div class="row">
            <div class="col-12">
                <form method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <@spring.bind "form"/>
                    <@spring.showErrors "<br>" "    "/>
                    <div class="form-group">
                        <label for="name">Name</label>
                        <@spring.bind 'form.name'/>
                        <input type="text" id="name" name="name" value="${book.name}" class="form-control" aria-describedby="Name">
                        <@spring.showErrors "<br>" ""/>
                    </div>
                    <div class="form-group">
                        <label for="description">Description</label>
                        <@spring.bind 'form.description'/>
                        <input type="text" id="description" name="description" value="${book.description}" class="form-control" wfd-id="15">
                        <@spring.showErrors "<br>" ""/>
                    </div><button type="submit" class="btn btn-primary">Update</button><hr>
                </form>
            </div>
        </div>
        <div class="row my-2">
            <div class="col-12">
                <a href="<@spring.url "/book/addChapter/" + book.id + "/"/>" class="btn btn-primary">Create new chapter</a><br>
                <hr>
            </div>
        </div>
        <div class="row mt-2">
            <#list chapters as  id_chapter, name>
                <div class="col-12 m-1">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">${name}</h5>
                            <div class="btn-group" role="group" aria-label="Basic example">
                                <a href="<@spring.url "/book/view/" + book.id + "/" + id_chapter/>" class="btn btn-primary">View</a>
                                <a href="<@spring.url "/book/update/" + book.id + "/" + id_chapter/>" class="btn btn-warning">Edit</a>
                                <a href="<@spring.url "/book/delete/" + book.id + "/" + id_chapter/>" class="btn btn-danger">Delete</a>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>
</#macro>

<#macro page_styles>
    <title>Update book</title>
</#macro>

<@display_page/>