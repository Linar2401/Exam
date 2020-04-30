<#include "../basic/base.ftlh">

<#macro page_body>
    <div class="container">
        <div class="row">
            <div class="col-12">
                <form action="<@spring.url "/book/addChapter/${book.id}"/>" method="post">
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
                        <label for="text">Text</label>
                        <@spring.bind 'form.text'/>
                        <@spring.formTextarea "form.text" "class=\"form-control\" id=\"text\""/>
                        <@spring.showErrors "<br>" ""/>
                    </div>
                    <button type="submit" class="btn btn-primary">Create</button>
                </form>
            </div>
        </div>
    </div>
</#macro>

<#macro page_styles>
    <title>Create Book Chapter</title>
</#macro>

<@display_page/>