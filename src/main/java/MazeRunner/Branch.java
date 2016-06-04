package MazeRunner;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.material.Vine;

public class Branch {
	public Location origin;
	public int height;
	public int radius;
	Location loc;
	int dir;
	Location center;
	public HashMap<Location, Integer> air = new HashMap<Location, Integer>();
	// 1= west, 2= east, 3= north, 4= south
	public Branch(Location start, int hieght, int radii, int direction, Location middle) {
		loc = start;
		origin = start;
		height = hieght;
		radius = radii;
		dir = direction;
		center = middle;
	}
	
	public void next() {
		int n = new Random().nextInt(8);
		if (n < 4) {
			dir = n + 1;
		}
		for (int z = 0; z < 10; z ++) {
			if (loc.clone().distance(center) > radius) {
				dir = new Random().nextInt(4) + 1;
			}
			construct();
			if (dir < 3) {
				if (dir == 1) {
					//west
					loc.add(-1, 0, 0);
				}
				else {
					//east
					loc.add(1, 0, 0);
				}
			}
			else {
				if (dir == 3) {
					//north
					loc.add(0, 0, -1);
				}
				else {
					//south
					loc.add(0, 0, 1);
				}
			}
		}
	}
	
	private void construct() {
		if (Math.abs(loc.clone().subtract(center).getBlockX()) > 101 && Math.abs(loc.clone().subtract(center).getBlockZ()) > 101)
		if (dir < 3) {
			for (int x = 0; x < height + 1; x ++) {
					if (new Random().nextInt(2) == 1) {
						loc.clone().add(2, x, 0).getBlock().setType(Material.MOSSY_COBBLESTONE);
					}
					else {
						loc.clone().add(2, x, 0).getBlock().setType(Material.COBBLESTONE);
					}
								
					if (new Random().nextInt(2) == 1) {
						loc.clone().add(-2, x, 0).getBlock().setType(Material.MOSSY_COBBLESTONE);
					}
					else {
						loc.clone().add(-2, x, 0).getBlock().setType(Material.COBBLESTONE);
					}
				
				air.put(loc.clone().add(1, x, 0), 1);
				air.put(loc.clone().add(0, x, 0), 1);
				air.put(loc.clone().add(-1, x, 0), 1);
				
			}
			if (new Random().nextInt(2) == 0) {
				Location bl = loc.clone().add((new Random().nextInt(2) * 2) - 4, 0, 0);
				int bound = new Random().nextInt((height/3) * 2);
				Vine vine = new Vine();
				for (int y = new Random().nextInt(((height/3) * 2) - 2); y < bound; y ++) {
					Block b = bl.getBlock();
					if (b.getLocation().clone().subtract(loc).getBlockX() == 2) {
						vine.putOnFace(b.getFace(bl.add(-1, 0, 0).getBlock()));
						bl.add(0, 1, 0);
					}
					else {
						vine.putOnFace(b.getFace(bl.add(1, 0, 0).getBlock()));
						bl.add(0, 1, 0);
					}
				}
			}
		}
		else {
			for (int x = 0; x < height + 1; x ++) {
					if (new Random().nextInt(2) == 1) {
						loc.clone().add(0, x, 2).getBlock().setType(Material.MOSSY_COBBLESTONE);
					}
					else {
						loc.clone().add(0, x, 2).getBlock().setType(Material.COBBLESTONE);
					}
					if (new Random().nextInt(2) == 1) {
						loc.clone().add(0, x, -2).getBlock().setType(Material.MOSSY_COBBLESTONE);
					}
					else {
						loc.clone().add(0, x, -2).getBlock().setType(Material.COBBLESTONE);
					air.put(loc.clone().add(0, x, 1), 1);
					air.put(loc.clone().add(0, x, 0), 1);
					air.put(loc.clone().add(0, x, -1), 1);
					}
				
			}
			if (new Random().nextInt(2) == 0) {
				Location bl = loc.clone().add(0, 0, (new Random().nextInt(2) * 2) - 4);
				int bound = new Random().nextInt((height/3) * 2);
				Vine vine = new Vine();
				for (int y = new Random().nextInt(((height/3) * 2) - 2); y < bound; y ++) {
					Block b = bl.getBlock();
					if (b.getLocation().clone().subtract(loc).getBlockZ() == 2) {
						vine.putOnFace(b.getFace(bl.clone().add(0, 0, -1).getBlock()));
						bl.add(0, 1, 0);
					}
					else {
						vine.putOnFace(b.getFace(bl.clone().add(0, 0, 1).getBlock()));
						bl.add(0, 1, 0);
					}
				}
			}
		}
	}
	public void finish() {
		for (Location block : air.keySet()) {
			block.getBlock().setType(Material.AIR);
		}
		for (int x = 0; x < height; x ++) {
			for (int y = 0; y < 5; y ++) {
				if (dir < 3) {
					loc.clone().add((y/ 2 - 1.5) * 2, x, 0).getBlock().setType(Material.AIR);
					
				}
				else {
					loc.clone().add(0, x, (y/ 2 - 1.5) * 2).getBlock().setType(Material.AIR);
				}
			}
		}
	}
}

