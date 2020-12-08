public interface Player{
    
    public void initPlayer(int startingSticks);
    
    public int takeTurn(int remainingSticks);
    
    public void gameOver(boolean didPlayerWin);
    
    public String getPlayerType();
    
    public String getDebugInfo();
    
}