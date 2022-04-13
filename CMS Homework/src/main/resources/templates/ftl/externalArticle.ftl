<#import "/spring.ftl" as spring />

<#macro externalArticle article>
    <div class="container-fluid wrapper">
    <div class="external-book">
        <div class="book-content">
            <button class="btn" type="button">
                <a href="<@spring.url relativeUrl="/${article.slug}"/>">${article.name}</a>
            </button>
        </div>
    </div>
</div>
</#macro>
