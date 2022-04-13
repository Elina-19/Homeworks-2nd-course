<#import "/spring.ftl" as spring />
<#import "../ftl/layout.ftl" as layout/>

<@layout.layout>
        <div class="chapter-content">
                <h1>${article.name}</h1>
                <br>
                <#outputformat 'plainText'>
                        ${article.text}
                </#outputformat>
        </div>
</@layout.layout>
