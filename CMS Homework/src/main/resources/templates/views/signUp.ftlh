<#import "/spring.ftl" as spring />
<#import "../ftl/layout.ftl" as t/>

<@t.layout>
    <form method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <div class="sign-form">
            <div class="mb-3">
                <label for="username" class="form-label">Nickname</label>
                <@spring.formInput "signUpForm.username" "class='form-control'"/>
                <@spring.showErrors "<br>" "error"/>
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Email address</label>
                <@spring.formInput "signUpForm.email", 'class="form-control"', "email"/>
                <@spring.showErrors "<br>" "error"/>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <@spring.formInput "signUpForm.password", 'class="form-control"', "password"/>
                <@spring.showErrors "<br>" "error"/>
            </div>
            <br>
            <input type="submit" class="btn" value="Создать">
        </div>
    </form>
</@t.layout>
