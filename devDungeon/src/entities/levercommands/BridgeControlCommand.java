package entities.levercommands;

import core.level.utils.Coordinate;
import utils.ICommand;
import core.Game;
import core.level.TileLevel;
import core.level.elements.tile.PitTile;
import core.level.Tile;

/**
 * The BridgeControlCommand class is responsible for controlling the bridge in the Bridge Guard
 * Riddle Level.
 *
 * <p>The bridge can be lowered and raised by executing and undoing this command.
 */
public class BridgeControlCommand implements ICommand {
  private final Coordinate topLeft;
  private final Coordinate bottomRight;

  /**
   * Creates a new BridgeControlCommand instance.
   *
   * @param topLeft The top left coordinate of the bridge.
   * @param bottomRight The bottom right coordinate of the bridge.
   */
  public BridgeControlCommand(Coordinate topLeft, Coordinate bottomRight) {
    this.topLeft = topLeft;
    this.bottomRight = bottomRight;
  }

  /**
   * Raises the bridge. By opening the pits, the bridge is raised.
   *
   * @see core.level.elements.tile.PitTile#open() PitTile.open
   * @see core.level.elements.ILevel#tilesInArea(Coordinate, Coordinate) tilesInArea
   */
  @Override
  public void execute() {
      for (Tile tile : Game.currentLevel().tilesInArea(topLeft, bottomRight)) {
          if (tile instanceof PitTile pit) {
              pit.open();  // raise bridge → open pit
          }
      }
  }

  /**
   * Lowers the bridge. By closing the pits, the bridge is lowered.
   *
   * @see core.level.elements.tile.PitTile#close() PitTile.close
   * @see core.level.elements.ILevel#tilesInArea(Coordinate, Coordinate) tilesInArea
   */
  @Override
  public void undo() {
      for (Tile tile : Game.currentLevel().tilesInArea(topLeft, bottomRight)) {
          if (tile instanceof PitTile pit) {
              pit.close();  // lower bridge → close pit
          }
      }
  }
}
