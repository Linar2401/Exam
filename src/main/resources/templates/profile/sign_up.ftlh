<#include "../basic/base.ftlh">

<#macro page_body>
    ​<h2>SignIn</h2>
<#--    <form action="<@spring.url "/SignUp"/>" method="post">-->
<#--        <@spring.bind "form"/>-->
<#--        <@spring.showErrors "<br>" "    "/>-->
<#--        Nickname:<br>-->
<#--        <@spring.bind 'form.nickname'/>-->
<#--        <@spring.formInput "form.nickname"/>-->
<#--        <@spring.showErrors "<br>" ""/>-->
<#--        <br><br>-->
<#--        Email:<br>-->
<#--        <@spring.bind 'form.email'/>-->
<#--        <@spring.formInput "form.email" "" "id=\"email\""/>-->
<#--        <@spring.showErrors "<br>" ""/>-->
<#--        <br><br>-->
<#--        Password:<br>-->
<#--        <@spring.bind 'form.password'/>-->
<#--        <@spring.formPasswordInput "form.password"/>-->
<#--        <@spring.showErrors "<br>" ""/>-->
<#--        <br><br>-->
<#--        Password repeat:<br>-->
<#--        <@spring.bind 'form.password'/>-->
<#--        <@spring.formPasswordInput "form.password"/>-->
<#--        <@spring.showErrors "<br>" ""/>-->
<#--        <br><br>-->
<#--        Country:<br>-->
<#--        <@spring.bind 'form.country'/>-->
<#--        <@spring.formSingleSelect "form.country" list "class=\"selectpicker\" data-live-search=\"true\""/>-->
<#--        <@spring.showErrors "<br>" ""/>-->
<#--        <br><br>-->
<#--        Gender:<br>-->
<#--        <@spring.bind 'form.gender'/>-->
<#--        <@spring.formCheckbox "form.gender"/>-->
<#--        <@spring.showErrors "<br>" ""/>-->
<#--        <br><br>-->
<#--        <input type="submit" value="Sign In">-->
<#--    </form>-->

    <div class="container">
        <div class="row">
            <div class="col-10">
                <form action="<@spring.url "/signUp"/>" method="post">
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
                        <label for="nickname">Nickname</label>
                        <@spring.bind 'form.nickname'/>
                        <@spring.formInput "form.nickname" "class=\"form-control\" id=\"nickname\""/>
                        <@spring.showErrors "<br>" ""/>
                    </div>
                    <div class="form-group">
                        <label for="Password1">Password</label>
                        <@spring.bind 'form.password'/>
                        <@spring.formPasswordInput "form.password" "class=\"form-control\" id=\"Password1\""/>
                        <@spring.showErrors "<br>" ""/>
                    </div>
                    <div class="form-group">
                        <label for="Password2">Password Repeat</label>
                        <@spring.bind 'form.passwordRepeat'/>
                        <@spring.formPasswordInput "form.passwordRepeat" "class=\"form-control\" id=\"Password2\""/>
                        <@spring.showErrors "<br>" ""/>
                    </div>
                    <div class="form-group">
                        <label for="country">Country</label>
                        <@spring.bind 'form.country'/>
                        <@spring.formSingleSelect "form.country" list "class=\"selectpicker\" data-live-search=\"true\" class=\"form-control\" id=\"country\" aria-describedby=\"Country\""/>
                        <@spring.showErrors "<br>" ""/>
                    </div>
                    <div class="form-group form-check">
                        <@spring.bind 'form.gender'/>
                        <@spring.formCheckbox "form.gender" "type=\"checkbox\" class=\"form-check-input\" id=\"gender\""/>
                        <label class="form-check-label" for="gender">Gender</label>
                        <@spring.showErrors "<br>" ""/>
                    </div>
                    <button type="submit" class="btn btn-primary">Sign up</button>
                </form>
            </div>
        </div>
    </div>

</#macro>

<#macro page_scripts>
    <script src="js/bootstrap-select.js"></script>
</#macro>

<#macro page_styles>
    <link rel="stylesheet" href="css/bootstrap-select.css">
    <title>Sign Up</title>
</#macro>

<@display_page/>