<#ftl encoding='UTF-8'>
<#import "/spring.ftl" as spring/>

<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  </head>
  <body>
    <a href='<@spring.url relativeUrl = "/test/change?lang=ru"/>'>Рус</a> <a href='<@spring.url relativeUrl = "/test/change?lang=en"/>'>Eng</a>
    <@spring.message 'test.mes'/>
    <@spring.message 'param.message'/>
  </body>
</html>
