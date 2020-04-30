<#include "../basic/base.ftlh">

<#macro page_body>
    <div class="container">
        <div class="row">
            <div class="col-12">
                <form action="<@spring.url "/book/add"/>" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <@spring.bind "form"/>
                    <@spring.showErrors "<br>" "    "/>
                    <div class="form-group">
                        <label for="name">Name</label>
                        <@spring.bind 'form.name'/>
                        <@spring.formInput "form.name" "class=\"form-control\" id=\"name\" aria-describedby=\"Name\""/>
                        <@spring.showErrors "<br>" ""/>
                    </div>
                    <div class="form-group">
                        <label for="description">Description</label>
                        <@spring.bind 'form.description'/>
                        <@spring.formInput "form.description" "class=\"form-control\" id=\"description\""/>
                        <@spring.showErrors "<br>" ""/>
                    </div>
                    <button type="submit" class="btn btn-primary">Create</button>
                </form>
            </div>
        </div>
    </div>
</#macro>

<#macro page_styles>
    <title>Create book</title>
</#macro>

<@display_page/>