<#import "/spring.ftl" as spring />

<#macro header>
    <header>
        <nav class="navbar fixed-top navbar-expand-lg navbar-light">
            <div class="container-fluid">
                <a class="navbar-brand" href="<@spring.url relativeUrl="/articles"/>">CMS-Site</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="actions collapse navbar-collapse" id="navbarSupportedContent">
                    <div class="d-flex">
                        <button class="btn" type="button">
                            <a href="<@spring.url relativeUrl="/signIn"/>" class="logout-button" id="signIn">Войти</a>
                        </button>
                        <button class="btn" type="button">
                            <a href="<@spring.url relativeUrl="/signUp"/>" class="logout-button" id="signUp">Регистрация</a>
                        </button>
                        <a>
                            <img class="userIcon" src="<@spring.url relativeUrl="/images/user.png"/>"/>
                        </a>
                    </div>
                    <div>
                        <button class="btn" type="button">
                            <a href="<@spring.url relativeUrl="/profile"/>" class="logout-button">Профиль</a>
                        </button>
                    </div>
                </div>
            </div>
        </nav>
    </header>
</#macro>
