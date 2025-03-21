public class NBody {
    public static double readRadius(String s) {
        In in = new In(s);
        in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String s) {
        In in = new In(s);
        int N = in.readInt();
        in.readDouble();
        Planet[] p = new Planet[N];
        for (int i = 0; i < N; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            p[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
        }
        return p;
    }

    public static void  main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double universeradius = readRadius(filename);
        String background = "images/starfield.jpg";
        StdDraw.setScale(-universeradius, universeradius);
        StdDraw.enableDoubleBuffering();
        StdDraw.picture(0, 0, background);
        Planet[] p = readPlanets(filename);
        for (Planet P : p) {
            P.draw();
        }
        int N = p.length;
        double t = 0.0;
        while (t <= T) {
            double[] xForce = new double[N];
            double[] yForce = new double[N];
            for (int i = 0; i < N; i++) {
                xForce[i] = p[i].calcNetForceExertedByX(p);
                yForce[i] = p[i].calcNetForceExertedByY(p);
            }
            for (int i = 0; i < N; i++) {
                p[i].update(dt, xForce[i], yForce[i]);
            }
            StdDraw.picture(0, 0, background);
            for (Planet P : p) {
                P.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            t += dt;
        }
        StdOut.printf("%d\n", p.length);
        StdOut.printf("%.2e\n", universeradius);
        for (int i = 0; i < p.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    p[i].xxPos, p[i].yyPos, p[i].xxVel,
                    p[i].yyVel, p[i].mass, p[i].imgFileName);
        }
    }
}
