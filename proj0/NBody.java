public class NBody{

	/* In class*/
	public static double readRadius(String filename){
		In in = new In(filename);
		int i = in.readInt();
		double radius = in.readDouble();

		return radius;
	}

	public static Planet[] readPlanets(String filename){
		In in = new In(filename);
		int i = in.readInt();
		double radius = in.readDouble();

		Planet[] planets = new Planet[i];
		for(int j = 0; j < i; j++){
			double xPos = in.readDouble();
			double yPos = in.readDouble();
			double xVel = in.readDouble();
			double yVel = in.readDouble();
			double mass = in.readDouble();
			String img = in.readString();
			planets[j] = new Planet(xPos, yPos, xVel, yVel, mass, img);
		}
		
		return planets;


	}

	
	public static void main(String[] args){

		/* collect all needed input*/
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		Planet[] planets = readPlanets(filename);
		double radius = readRadius(filename);
	
		/* draw the backgroud using stddraw */
		String imageToDraw = filename;
		
		StdDraw.setScale(-radius, radius);
		/* Clears the drawing window. */
		StdDraw.clear();
		StdDraw.picture(0, 0, "images/starfield.jpg");

		/* use draw method draw each of the planets */
		for (int i = 0; i < planets.length; i++){
			planets[i].draw(); // here, when compile must use "javac -encoding UTF-8 NBody.java"
		}
	

	StdDraw.enableDoubleBuffering();

	for(double t = 0.0; t <= T; t = t + dt){

		double[] xForces = new double[planets.length];
    	double[] yForces = new double[planets.length];


		for(int i = 0; i < planets.length; i++){
			xForces[i] = planets[i].calcNetForceExertedByX(planets);
			yForces[i] = planets[i].calcNetForceExertedByY(planets);
		}

		for(int i = 0; i < planets.length; i++){
			planets[i].update(t,xForces[i],yForces[i]);
			StdDraw.picture(0, 0, "images/starfield.jpg");
			planets[i].draw();
		}
	
		/* Shows the drawing to the screen, and waits 2000 milliseconds. */
		StdDraw.show();
		StdDraw.pause(10);		

		}

    StdOut.printf("%d\n", planets.length);
	StdOut.printf("%.2e\n", radius);
	for(int i = 0; i < planets.length; i++){
		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", planets[i].xxPos,
			planets[i].yyPos, planets[i].xxVel, planets[i].yyVel, planets[i].mass,
			planets[i].imgFileName);
    	}

}		

}