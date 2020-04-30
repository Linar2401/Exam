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
                        <input type="text" id="name" name="name" value="${chapter.name}" class="form-control" aria-describedby="Name">
                        <@spring.showErrors "<br>" ""/>
                    </div>
                    <div class="form-group">
                        <label for="description">Text</label>
                        <@spring.bind 'form.text'/>
                        <textarea id="text" name="text" class="form-control">${chapter.text}</textarea>
                        <@spring.showErrors "<br>" ""/>
                    </div>
                    <button type="submit" class="btn btn-primary">Update</button><hr>
                </form>
            </div>
        </div>
    </div>
</#macro>

<#macro page_styles>
    <title>Update book chapter</title>
</#macro>

<@display_page/>