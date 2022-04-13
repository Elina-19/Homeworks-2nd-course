<#import "/spring.ftl" as spring />
<#import "../ftl/layout.ftl" as t/>
<#import "../ftl/externalArticle.ftl" as externalArticle/>

<@t.layout>
    <#list articles as article>
        <@externalArticle.externalArticle article=article/>
    </#list>
</@t.layout>
