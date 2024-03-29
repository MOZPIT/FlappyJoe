import java.awt.Image;
import java.util.LinkedList;
import java.util.Iterator;


/*
 * The TileMap class contains the data for a tile-based map, including Sprites. Each title is a reference to an Image. Of course, Images are uesd multiple times in the tile map.
 */

public class TileMap {
	private Image[][] tiles; //array of arrays
	private LinkedList<Sprite> sprites;
	private Sprite player;
	
	/*
	 * Creates new TileMap with the specified witdth and height (in the number of tiles) of the map.
	 */
	public TileMap(int width, int height) {
		tiles = new Image[width][height];
		sprites = new LinkedList<Sprite>();
	}
	
	/*
	 * Gets the width of this TileMap (number of tiles accross)
	 */
	public int getWidth() {
		return tiles.length;
	}
	
	/*
	 * Gets the height of this TileMap (number of tiles down)
	 */
	public int getHeight() {
		return tiles[0].length;
	}
	
	/*
	 * Gets the tile at the specified location. Returns null if no tile is at the location or if the location is out of bounds.
	 */
	public Image getTile(int x, int y) {
		if(x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
			return null;
		}else {
			return tiles[x][y];
		}
	}
	
	/*
	 * Sets the tile at the specified location
	 */
	public void setTile(int x, int y, Image tile) {
		tiles[x][y] = tile;
	}
	
	/*
	 * Gets the player Sprite
	 */
	public Sprite getPlayer() {
		return player;
	}
	
	/*
	 * Sets the player Sprite
	 */
	public void setPlayer(Sprite player) {
		this.player = player;
	}
	
	/*
	 * Adds a Sprite object to this map.
	 */
	public void addSprite(Sprite sprite) {
		sprites.add(sprite);
	}
	
	/*
	 * Removes s Sprite object from this map.
	 */
	public void removeSprite(Sprite sprite) {
		sprites.remove(sprite);
	}
	
	/*
	 * Gets an Iterator of all the Sprites in this map, excluding the player Sprite.
	 */
	public Iterator<Sprite> getSprites() {
		return sprites.iterator();
	}
}//end of main class


