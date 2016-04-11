package client.model;

public abstract class Tile {
	public int x;
	public int y;
	public final int id;
	
	public Tile(final int id){
		this.id = id;
	}
	
	public int getID(){
		return this.id;
	}
}
