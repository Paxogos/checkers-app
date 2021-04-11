

<#if notification??>

<#if notification.isNewGameMessage()>
  <p>${notification.text} would like to play a game.
    <a href="/game?${notification.text}">Accept</a>
  <form id="decline" action="/decline" method="post">
  <button>Decline</button>
  </form>
  </p>
<#elseif notification.isGameAcceptedMessage()>
  <p>A game has been started with ${notification.text}.<a href="/game?${notification.text}">Go to game</a></p>
<#elseif notification.isGameDeclinedMessage()>
  <p>${notification.text} declined to start a game.
  <form id="delete" action="/delete" method="post">
    <button>Okay</button>
  </form>
  </p>
</#if>

<#else>
  <div id="message" class="INFO" style="display:none">
    <!-- keep here for client-side messages -->
  </div>
</#if>
