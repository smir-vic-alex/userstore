<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="navigation inline">
    <div class="user-profile">
                <span class="avatar">
                    <img src="../img/avatar.jpg">
                </span>
        <div class="title">Смирнов Виктор</div>
    </div>
    <nav id="nav">
        <ul>
            <li><a href="${pageContext.request.contextPath}/private/create/post.do">Создать пост</a></li>
            <li><a href="${pageContext.request.contextPath}/private/client/addVKProfile.do">Добавить профиль VK</a></li>
            <li><a href="${pageContext.request.contextPath}/private/client/addTlgmBot.do">Добавить Telegram бота</a></li>
            <li><a href="${pageContext.request.contextPath}/private/client/add/tlgm/channel.do">Добавить Telegram канал</a></li>
            <li><a href="${pageContext.request.contextPath}/private/show/tlgm/bots.do">Список Telegram ботов</a></li>
            <li><a href="${pageContext.request.contextPath}/private/show/tlgm/channels.do">Список Telegram каналов</a></li>
            <li><a href="${pageContext.request.contextPath}/private/create/post/tlgm.do">Разместить Telegram пост</a></li>
            <li><a href="${pageContext.request.contextPath}/private/client/show/vk/groups.do">Список групп</a></li>
            <li><a href="${pageContext.request.contextPath}/private/create/post/vk.do">Разместить пост</a></li>
            <li><a href="/cras/">Настройки</a></li>
        </ul>
    </nav>
</div>