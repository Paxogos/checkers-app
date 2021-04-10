<!DOCTYPE html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
  <meta http-equiv="refresh" content="10">
  <title>Web Checkers | ${title}</title>
  <link rel="stylesheet" type="text/css" href="/css/style.css"></head>

<body>
<div class="page">

  <h1>Web Checkers | ${title}</h1>

  <!-- Provide a navigation bar -->
  <#include "nav-bar.ftl" />

  <div class="body">

    <!-- Provide a message to the user, if supplied. -->
    <#include "message.ftl" />
      <#include "notification.ftl" />

    <#if currentUser??>
    <p> Click a name to start a game! </p>
    <p> Players: </p>
        <#list availablePlayerList as player>
          <p><a href="/game?${player}">${player}</a></p>
        </#list>
        <#if availablePlayerList?size == 0>
            <p>There are no other players available at this time.</p>
        </#if>

        <#if currentGameList??>
            <p>Current Games:</p>
            <#list currentGameList as player>
                <p><a href="/game?${player}">${player}</a></p>
            </#list>
            <#else><p>You do not have any current games.</p>
        </#if>



    <#else>
        <#assign size = lobbySize>
        There <#if size == 1>is<#else>are</#if> ${size} player<#if size != 1>s</#if> online.
    </#if>
    <!-- TODO: future content on the Home:
            to start games,
            spectating active games,
            or replay archived games
    -->



  </div>

</div>
</body>

</html>
