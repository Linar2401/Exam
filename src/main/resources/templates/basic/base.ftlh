<#import "/spring.ftl" as spring>
<#macro page_styles>

</#macro>

<#macro page_nav>
    <nav class="navbar navbar-expand-lg navbar-dark" id="navbar">
        <a class="navbar-brand" href="#">Books shelter</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="<@spring.url "/list/user/0"/>">Authors<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="<@spring.url "/list/books/0"/>">Books</a>
                </li>
            </ul>
            <#if user?has_content>
                <a type="button" class="btn btn-success mr-3" href="<@spring.url "/profile"/>">
                    Profile
                </a>
                <a type="button" class="btn btn-danger mr-3" href="<@spring.url "/logout"/>">
                    Logout
                </a>
            <#else>
                <a type="button" class="btn btn-success mr-3" href="<@spring.url "/signIn"/>">
                    Sign in
                </a>
                <a type="button" class="btn btn-warning mr-3" href="<@spring.url "/signUp"/>">
                    Sign up
                </a>
            </#if>

        </div>
    </nav>
</#macro>

<#macro page_body>
    <h1>Basic Page</h1>
    <p>This is the body of the page!</p>
</#macro>

<#macro page_scripts>
    <script></script>
</#macro>

<#macro page_bottom>
    <footer class="p-3 my-md-5 border-top w-100">
        <h6>Footer</h6>
    </footer>
</#macro>

<#macro display_page>
    <!doctype html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <@page_styles/>
        <link rel="stylesheet" href="<@spring.url "/css/bootstrap.css"></@spring.url>">
        <link rel="stylesheet" href="<@spring.url "/css/base.css"></@spring.url>">
        <style>
            .hidden{
                display: none;
            }
        </style>
        <title>Document</title>
    </head>
    <body>
        <@page_nav/>
        <div class="main_container p-5">
            <@page_body/>
        </div>



<#--        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>-->
<#--        <script src="js/bootstrap.js"></script>-->
        <script src="/js/jquery-3.2.1.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.bundle.min.js"></script>

        <@page_scripts/>
        <@page_bottom/>
    </body>
    </html>
</#macro>