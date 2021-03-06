<#import "head.ftl" as head>
<#import "header.ftl" as header>
<#import "footer.ftl" as footer>

<#macro layout>
    <html>
        <@head.head/>
        <body>
            <@header.header/>
            <div class="wrapper">
                <#nested/>
            </div>
            <@footer.footer/>
            <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/js/bootstrap.min.js" integrity="sha384-oesi62hOLfzrys4LxRF63OJCXdXDipiYWBnvTl9Y9/TRlw5xlKIEHpNyvvDShgf/" crossorigin="anonymous"></script>
        </body>
    </html>
</#macro>
