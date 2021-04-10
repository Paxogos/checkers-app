<#if notification??>

<#if notification.isNewGameMessage()>
  <p>${notification.text} would like to play a game.
    <a href="/game?${notification.text}">Accept</a>
    <a href="/declineGame" method="post">Decline</a>
  </p>
<#elseif notification.isGameAcceptedMessage()>
  <p>A game has been started with ${notification.text}.</p>
<#elseif notification.isGameDeclinedMessage()>
  <p>${notification.text} declined to start a game.</p>
</#if>

<#else>
  <div id="message" class="INFO" style="display:none">
    <!-- keep here for client-side messages -->
  </div>
</#if>
