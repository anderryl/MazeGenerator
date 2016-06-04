package MazeRunner;

import org.bukkit.Location;

public class Generator {
	public int radius;
	public int height;
	public Location origin;
	public Generator(int radii, int hieght, Location middle) {
		Branch be = new Branch(middle.clone().add(100, 0, 0), hieght, radii, 2, middle);
		Branch bw = new Branch(middle.clone().add(-100, 0, 0), hieght, radii, 1, middle);
		Branch bn = new Branch(middle.clone().add(0, 0, 100), hieght, radii, 3, middle);
		Branch bs = new Branch(middle.clone().add(0, 0, -100), hieght, radii, 4, middle);
		for (int x = 0; x < (radii * radii * Math.PI)/ 25; x++) {
			be.next();
			bw.next();
			bn.next();
			bs.next();
		}
	}
	
}
