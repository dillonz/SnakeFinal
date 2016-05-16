package graphics;

public class Board {

	private Pixel[][] pixels;
	
	public Board() {
		pixels = new Pixel[500][500];
		for (int r = 0; r < pixels.length; r++)
			for (int c = 0; c < pixels[0].length; c++)
				pixels[r][c] = new Pixel(0, 0, 0);
	}
	
	public Pixel[][] getPix() {
		return pixels;
	}
	
	public void setPix(Pixel[][] newPix) {
		for (int r = 0; r < pixels.length; r++)
			for (int c = 0; c < pixels[0].length; c++)
				pixels[r][c] = newPix[r][c];
	}
}
