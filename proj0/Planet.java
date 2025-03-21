public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double x = this.xxPos - p.xxPos;
        double y = this.yyPos - p.yyPos;
        return Math.sqrt(x * x + y * y);
    }

    public double calcForceExertedBy(Planet p) {
        double G = 6.67e-11;
        double distance = this.calcDistance(p);
        return G * this.mass * p.mass / (distance * distance);
    }

    public double calcForceExertedByX(Planet p) {
         double distance = this.calcDistance(p);
         double F = this.calcForceExertedBy(p);
         return ((p.xxPos - this.xxPos) / distance) * F;
    }

    public double calcForceExertedByY(Planet p) {
        double distance = this.calcDistance(p);
        double F = this.calcForceExertedBy(p);
        return ((p.yyPos - this.yyPos) / distance) * F;
    }

    public double calcNetForceExertedByX(Planet[] p) {
        double F = 0.0;
        for (Planet P : p) {
            if (this.equals(P)) {
                continue;
            }
            F += this.calcForceExertedByX(P);
        }
        return F;
    }

    public double calcNetForceExertedByY(Planet[] p) {
        double F = 0.0;
        for (Planet P : p) {
            if (this.equals(P)) {
                continue;
            }
            F += this.calcForceExertedByY(P);
        }
        return F;
    }

    public void update(double dt, double Fx, double Fy) {
        double Xa = Fx / this.mass;
        double Ya = Fy / this.mass;
        this.xxVel += dt * Xa;
        this.yyVel += dt * Ya;
        this.xxPos += dt * this.xxVel;
        this.yyPos += dt * this.yyVel;
    }

    public void draw() {
        String s = "images/" + this.imgFileName;
        StdDraw.picture(this.xxPos, this.yyPos, s);
    }
}
