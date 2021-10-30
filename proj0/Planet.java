public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	static final double g = 6.67e-11;

	/* initialize an instance of the Planet class*/
	public Planet(double xP, double yP, double xV,
		double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
    
    /* take in a Planet object and initialize an identical Planet object*/
	public Planet(Planet b){
		this.xxPos = b.xxPos;
		this.yyPos = b.yyPos;
		this.xxVel = b.xxVel;
		this.yyVel = b.yyVel;
		this.mass = b.mass;
		this.imgFileName = b.imgFileName;
	}

	/* add a method to calculate the distance between two planates*/
	/* method must have return type*/
	public double calcDistance(Planet c){
		double distance = (this.xxPos - c.xxPos)*(this.xxPos - c.xxPos) + (this.yyPos - c.yyPos)*(this.yyPos - c.yyPos);

		return Math.sqrt(distance);

	}

	/* takes in a planet, and returns a double describing the force exerted on this planet by the given planet*/
	public double calcForceExertedBy(Planet c){
		double distance = this.calcDistance(c);
		double force = (g * this.mass * c.mass )/ (distance * distance) ;

		return force;
	}
	
	/* */
	/* */
	public double calcForceExertedByX(Planet c){
		double distance = this.calcDistance(c);
		double force = this.calcForceExertedBy(c);
		double forceX = force * (c.xxPos - this.xxPos) / distance;

		return forceX;
	}

	public double calcForceExertedByY(Planet c){
		double distance = this.calcDistance(c);
		double force = this.calcForceExertedBy(c);
		double forceY = force * (c.yyPos - this.yyPos) / distance;

		return forceY;
	}
    
    /* miss int before i = 0, will get error: can't find the symbol */
    /* here should use continue, not break */
	public double calcNetForceExertedByX(Planet[] allPlanets){
		double netforceX = 0;
		for(int i = 0; i < allPlanets.length; i++){
			if (this.equals(allPlanets[i])){
				continue;
			}
			else{
				netforceX += this.calcForceExertedByX(allPlanets[i]);
			}			
		}
		return netforceX;
	}

	public double calcNetForceExertedByY(Planet[] allPlanets){
		double netforceY = 0;
		for(int i = 0; i < allPlanets.length; i++){
			if (this.equals(allPlanets[i])){
				continue;
			}
			else{
				netforceY += this.calcForceExertedByY(allPlanets[i]);
			}			
		}
		return netforceY;
	}

	/* here should use this.xxx, if use other double variables the result will be wrong */
    public void update(double dt, double fx, double fy){
    	double a_net_X = fx / this.mass;
    	double a_net_Y = fy / this.mass;
    	this.xxVel = this.xxVel + dt * a_net_X;
    	this.yyVel = this.yyVel + dt * a_net_Y;

    	this.xxPos = this.xxPos + dt * this.xxVel;
    	this.yyPos = this.yyPos + dt * this.yyVel;
    }

    public void draw(){
    	StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);// notice here should have "images/"
    }

}