<#include "../basic/base.ftlh">

<#macro page_body>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-10">
                    <a type="button" class="btn btn-success mr-3" href="<@spring.url "/book/view/${chapter.getBook().id}"/>">
                        Back to book
                    </a>
            </div>
        </div>
        <div class="row justify-content-center">
            <div class="col-10 pt-2">
                <h4>Name:${chapter.name}</h4>
                <hr>
                <p>${chapter.text}</p>
                <hr>
            </div>
            <div class="col-4">
                <nav aria-label="pagination">
                    <ul class="pagination">
                        <li class="page-item <#if first>disabled</#if>">
                            <a class="page-link" href="<#if !first><@spring.url "/book/view/${chapter.book.id}/${chapter.id}?next=false"/><#else>#</#if>" tabindex="-1" aria-disabled="true">Previous</a>
                        </li>
                        <li class="page-item <#if last>disabled</#if>">
                            <a class="page-link" href="<#if !last><@spring.url "/book/view/${chapter.book.id}/${chapter.id}?next=true"/><#else>#</#if>">Next</a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
        <div class="row justify-content-center">
            <div class="col-10">
                <hr>
                <h3>Comments:</h3>
                <div id="comments">
                    <#list comments as comment>
                        <div class="card my-1">
                            <div class="card-header">
                                <a href="<@spring.url "/profile/${comment.user.id}"/>">${comment.user.nickname}</a>
                            </div>
                            <div class="card-body">
                                <p class="card-text">${comment.text}</p>
                            </div>
                            <div class="card-footer text-muted">
                                ${comment.createdAt}
                            </div>
                        </div>
                    </#list>
                </div>
                <#if nextComment>
                    <input class="hidden" id="nextComment" value="True"></input>
                    <input class="hidden" id="chapterId" value="${chapter.id}"></input>
                    <button type="button" class="btn btn-info align-self-center" id="nextComments">Load next comment</button>
                </#if>
                <#if user?has_content>
                    <form action="<@spring.url "/comment/add"/>" method="post">
                        <@spring.bind "form"/>
                        <@spring.showErrors "<br>" "    "/>
                        <div class="form-group">
                            <@spring.bind 'form.chapterId'/>
<#--                            <@spring.formHiddenInput "form.chapterId" "value=${chapter.id} class=\"form-control\" id=\"chapterId\" aria-describedby=\"Chapter Id\""/>-->
                            <input type="hidden" id="chapterId" name="chapterId" value="${chapter.id}" class="form-control" aria-describedby="Chapter Id">
                            <@spring.showErrors "<br>" ""/>
                        </div>
                        <div class="form-group">
                            <@spring.bind 'form.userId'/>
<#--                            <@spring.formHiddenInput "form.userId" "${user.id} class=\"form-control\" id=\"userId\" aria-describedby=\"user Id\""/>-->
                            <input type="hidden" id="userId" name="userId" value="${user.id}" 5class="form-control" aria-describedby="user Id">
                            <@spring.showErrors "<br>" ""/>
                        </div>
                        <div class="form-group">
                            <@spring.bind 'form.bookId'/>
<#--                            <@spring.formHiddenInput "form.bookId" "value=${chapter.book.id} class=\"form-control\" id=\"bookId\" aria-describedby=\"user Id\""/>-->
                            <input type="hidden" id="bookId" name="bookId" value="${chapter.book.id}" class="form-control" aria-describedby="user Id">
                            <@spring.showErrors "<br>" ""/>
                        </div>
                        <div class="form-group">
                            <label for="text">Text</label>
                            <@spring.bind 'form.text'/>
                            <@spring.formTextarea "form.text" "class=\"form-control\" id=\"text\" required=\"required\""/>
                            <@spring.showErrors "<br>" ""/>
                        </div>
                        <button type="submit" class="btn btn-primary">Post</button>
                    </form>
                <#else>
                    <h4>Sign in or Sign up to post comment</h4>
                    <a type="button" class="btn btn-success mr-3" href="<@spring.url "/signIn"/>">
                        Sign in
                    </a>
                    <a type="button" class="btn btn-warning mr-3" href="<@spring.url "/signUp"/>">
                        Sign up
                    </a>
                </#if>
            </div>
        </div>
    </div>
</#macro>

<#macro page_styles>
    <title>Chapter</title>
</#macro>

<#macro page_scripts>
    <script src="/js/comments.js"></script>
</#macro>


<@display_page/>