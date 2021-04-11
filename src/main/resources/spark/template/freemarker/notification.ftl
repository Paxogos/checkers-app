

<#if notification??>

<#if notification.isNewGameMessage()>
  <p>${notification.text} would like to play a game.
    <a href="/game?${notification.text}">Accept</a>
  <form id="delete" action="/decline" method="post">
  <button>Decline</button>
  </form>
  </p>
<#elseif notification.isGameAcceptedMessage()>
  <p>A game has been started with ${notification.text}.<a href="/game?${notification.text}">Go to game</a></p>
<#elseif notification.isGameDeclinedMessage()>
  <p>${notification.text} declined to start a game. <a href="/delete">Okay</a></p>
</#if>

<#else>
  <div id="message" class="INFO" style="display:none">
    <!-- keep here for client-side messages -->
  </div>
</#if>
