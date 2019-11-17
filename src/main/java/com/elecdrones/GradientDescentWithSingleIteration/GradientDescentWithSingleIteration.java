package com.elecdrones.GradientDescentWithSingleIteration;

class GradientDescentWithSingleIteration {
    public static void main(String[] args) {
        double input = 1.1;
        double weight = 0.0;
        double output;
        double target = 0.8;

        double error;

        double alpha;
        double delta;
        double weightDelta;

        for (int counter = 0; counter < 4; counter++) {
            output = input * weight;
            error = Math.pow((output - target), 2);
            delta = output - target;
            weightDelta = delta * input;
            weight = weight - weightDelta;
            System.out.println("Error: " + error + " Output: " + output);
            System.out.println("Delta: " + delta + " Weight Delta: " + weightDelta);
        }
    }
}