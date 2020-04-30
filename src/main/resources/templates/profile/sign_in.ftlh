<#include "../basic/base.ftlh">
<#macro page_styles>
    <title>Sign In</title>
</#macro>

<#macro page_body>
    ​<h2>SignIn</h2>
<#--    <form action="<@spring.url "/signIn"/>" method="post">-->
<#--        <@spring.bind "form"/>-->
<#--        <@spring.showErrors "<br>" "    "/>-->
<#--        Email:<br>-->
<#--        <@spring.bind 'form.email'/>-->
<#--        <@spring.formInput "form.email"/>-->
<#--        <@spring.showErrors "<br>" ""/>-->
<#--        <br><br>-->
<#--        Password:<br>-->
<#--        <@spring.bind 'form.password'/>-->
<#--        <@spring.formPasswordInput "form.password"/>-->
<#--        <@spring.showErrors "<br>" ""/>-->
<#--        <br><br>-->
<#--        <input type="submit" value="Sign In">-->
<#--    </form>-->
    <div class="container">
        <div class="row">
            <div class="col-10">
                <form action="<@spring.url "/signIn"/>" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <@spring.bind "form"/>
                    <@spring.showErrors "<br>" "    "/>
                    <div class="form-group">
                        <label for="email">Email address</label>
                        <@spring.bind 'form.email'/>
                        <@spring.formInput "form.email" "class=\"form-control\" id=\"email\" aria-describedby=\"Email\""/>
                        <@spring.showErrors "<br>" ""/>
                    </div>
                    <div class="form-group">
                        <label for="Password1">Password</label>
                        <@spring.bind 'form.password'/>
                        <@spring.formPasswordInput "form.password" "class=\"form-control\" id=\"Password1\""/>
                        <@spring.showErrors "<br>" ""/>
                    </div>
                    <button type="submit" class="btn btn-primary">Sign In</button>
                </form>
            </div>
        </div>
    </div>
</#macro>

<@display_page/>