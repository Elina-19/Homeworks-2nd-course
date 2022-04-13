<#import "/spring.ftl" as spring />
<#import "../ftl/layout.ftl" as t/>
<#import "../ftl/externalArticle.ftl" as externalArticle/>

<@t.layout>
    <form action="<@spring.url relativeUrl="/articles/newArticle"/>" method="POST">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <h4>
            Введите название статьи
        </h4>
        <@spring.formInput "addArticleForm.name"/>
        <@spring.showErrors "<br>" "error"/>
        <br>
        <br>
        <div id="sample">
            <@spring.formTextarea "addArticleForm.text", "class='form-control' style='width: 100%' placeholder='Введите контент статьи'"/>
            <@spring.showErrors "<br>" "error"/>
            <br>
        </div>
        <input type="checkbox" name="isForAdmin">
        <input type="submit" class="btn btn-success" value="Save">
    </form>

    <script type="text/javascript" src="//js.nicedit.com/nicEdit-latest.js"></script>
    <script type="text/javascript">
        bkLib.onDomLoaded(function() { nicEditors.allTextAreas() });
    </script>
</@t.layout>
