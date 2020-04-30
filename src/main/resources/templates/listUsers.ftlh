<#include "basic/base.ftlh">

<#macro page_body>
    <div class="container">
        <div class="row justify-content-center">
            <#list list as author>
                <div class="col-3 m-4">
                    <div class="card" style="width: 18rem;">
                        <div class="card-body">
                            <h5 class="card-title">${author.nickname}</h5>
                            <p class="card-text">Books: ${author.email}</p>
                            <a href="<@spring.url "/profile/${author.id}"/>" class="btn btn-primary">Profile</a>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
        <div class="row justify-content-center">
            <div class="col-10">
                <nav aria-label="pagination">
                    <ul class="pagination">
                        <li class="page-item <#if page==0>disabled</#if>">
                            <a class="page-link" href="<#if page!=0><@spring.url "/list/user/${page - 1}"/><#else>#</#if>" tabindex="-1" aria-disabled="true">Previous</a>
                        </li>
                        <li class="page-item active" aria-current="page">
                            <a class="page-link" href="#">${page}<span class="sr-only">(current)</span></a>
                        </li>
                        <li class="page-item <#if !nextPage>disabled</#if>">
                            <a class="page-link" href="<#if nextPage><@spring.url "/list/user/${page + 1}"/><#else>#</#if>">Next</a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</#macro>

<#macro page_styles>
    <title>${title}</title>
</#macro>

<@display_page/>