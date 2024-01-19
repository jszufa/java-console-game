public interface IMapService {
    
    public void handleCommand(String input, Level level, Game game);
    public void printControlsMessage();
    public boolean validateCommand(char commandInput);
}
