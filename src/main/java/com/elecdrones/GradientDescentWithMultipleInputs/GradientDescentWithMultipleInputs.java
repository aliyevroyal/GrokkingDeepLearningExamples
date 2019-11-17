package com.elecdrones.GradientDescentWithMultipleInputs;

import com.elecdrones.LinearAlgebra.LinearAlgebra;
import com.sun.istack.internal.NotNull;

import java.util.ArrayList;

class GradientDescentWithMultipleInputs {
    private static final double alpha = 0.01;

    public static void main(String args[]) {
        /**
         * Creating and Initializing Elements of Hypothesis and Cost Functions
         */
        ArrayList<ArrayList<Double>> featuresDataSetMatrixArrayList = new ArrayList<>();
        ArrayList<Double> featuresDataSetRowVectorArrayList = new ArrayList<>();
        ArrayList<ArrayList<Double>> parametersMatrixArrayList = new ArrayList<>();
        ArrayList<Double> parametersVectorArrayList = new ArrayList<>();
        ArrayList<ArrayList<Double>> targetMatrixArrayList = new ArrayList<>();
        ArrayList<Double> targetVectorArrayList = new ArrayList<>();

        /**
         * Creating and Initializing Futures
         */
        ArrayList<Double> theNumberOfToesFutureVectorArraylist = new ArrayList<>();
        ArrayList<Double> wonRecordsFutureVectorArraylist = new ArrayList<>();
        ArrayList<Double> theNumberOfFansFutureVectorArraylist = new ArrayList<>();

        parametersVectorArrayList.add(0.1);
        parametersVectorArrayList.add(0.2);
        parametersVectorArrayList.add(-0.1);
        parametersMatrixArrayList.add(parametersVectorArrayList);

        targetVectorArrayList.add(1.0);
        targetVectorArrayList.add(1.0);
        targetVectorArrayList.add(0.0);
        targetVectorArrayList.add(1.0);
        targetMatrixArrayList.add(targetVectorArrayList);

        /**
         * Assigment of Features
         */
        theNumberOfToesFutureVectorArraylist.add(8.5);
        theNumberOfToesFutureVectorArraylist.add(9.5);
        theNumberOfToesFutureVectorArraylist.add(9.9);
        theNumberOfToesFutureVectorArraylist.add(9.0);

        wonRecordsFutureVectorArraylist.add(0.65);
        wonRecordsFutureVectorArraylist.add(0.8);
        wonRecordsFutureVectorArraylist.add(0.8);
        wonRecordsFutureVectorArraylist.add(0.9);

        theNumberOfFansFutureVectorArraylist.add(1.2);
        theNumberOfFansFutureVectorArraylist.add(1.3);
        theNumberOfFansFutureVectorArraylist.add(0.5);
        theNumberOfFansFutureVectorArraylist.add(1.0);

        /**
         * Assigment of Elements of Hypothesis and Cost Functions
         */
        featuresDataSetMatrixArrayList.add(theNumberOfToesFutureVectorArraylist);
        featuresDataSetMatrixArrayList.add(wonRecordsFutureVectorArraylist);
        featuresDataSetMatrixArrayList.add(theNumberOfFansFutureVectorArraylist);


        System.out.println(featuresDataSetMatrixArrayList);
        System.out.println(parametersMatrixArrayList);
        System.out.println(targetMatrixArrayList);

        System.out.println(calculateHypothesisFunction(parametersMatrixArrayList, featuresDataSetMatrixArrayList));
        System.out.println(iterateParameters(featuresDataSetMatrixArrayList, parametersMatrixArrayList, targetMatrixArrayList));
    }

    /**
     * Calculate Hypothesis Function and Return Output Matrix
     */
    private static ArrayList<ArrayList<Double>> calculateHypothesisFunction(ArrayList<ArrayList<Double>> parametersMatrixArrayList,
                                                                            ArrayList<ArrayList<Double>> featuresDataSetMatrixArrayList) {
        ArrayList<ArrayList<Double>> outputMatrixArrayList;
        LinearAlgebra linearAlgebra = new LinearAlgebra();

        outputMatrixArrayList = linearAlgebra.multiplyMatrices(parametersMatrixArrayList, featuresDataSetMatrixArrayList);
        return outputMatrixArrayList;
    }

    /**
     * Calculate Cost Function and Return Error Matrix
     */
    @NotNull
    private static ArrayList<ArrayList<Double>> calculateCostFunction(@NotNull ArrayList<ArrayList<Double>> outputMatrixArrayList,
                                                                      ArrayList<ArrayList<Double>> targetMatrixArrayList) {
        ArrayList<ArrayList<Double>> errorMatrixArrayList = new ArrayList<>();
        ArrayList<Double> errorVectorArrayList = new ArrayList<>();

        for (int firstCounter = 0; firstCounter < outputMatrixArrayList.size(); firstCounter = firstCounter + 1) {
            for (int secondCounter = 0; secondCounter < outputMatrixArrayList.get(firstCounter).size(); secondCounter = secondCounter + 1) {
                errorVectorArrayList.add(Math.pow((outputMatrixArrayList.get(firstCounter).
                        get(secondCounter) - targetMatrixArrayList.get(firstCounter).get(secondCounter)), 2));
            }
            errorMatrixArrayList.add(errorVectorArrayList);
            errorVectorArrayList = new ArrayList<>();
        }
        return errorMatrixArrayList;
    }

    /**
     * Find Derivative Term
     */
    @NotNull
    private static ArrayList<ArrayList<Double>> findDerivativeTerm(ArrayList<ArrayList<Double>> featuresDataSetMatrixArrayList,
                                                                   ArrayList<ArrayList<Double>> parametersMatrixArrayList,
                                                                   ArrayList<ArrayList<Double>> targetMatrixArrayList) {
        ArrayList<ArrayList<Double>> derivativeTermMatrixArrayList = new ArrayList<>();
        ArrayList<Double> derivativeTermVectorArrayList = new ArrayList<>();

        LinearAlgebra linearAlgebra = new LinearAlgebra();
        ArrayList<ArrayList<Double>> deltaMatrixArrayList = linearAlgebra.subtractMatrices(calculateHypothesisFunction(parametersMatrixArrayList, featuresDataSetMatrixArrayList),
                targetMatrixArrayList);

        for (int firstCounter = 0; firstCounter < deltaMatrixArrayList.size(); firstCounter = firstCounter + 1)
            for (int secondCounter = 0; secondCounter < featuresDataSetMatrixArrayList.size(); secondCounter = secondCounter + 1)
                derivativeTermVectorArrayList.add(featuresDataSetMatrixArrayList.get(secondCounter).get(firstCounter) * deltaMatrixArrayList.get(firstCounter).get(firstCounter));

        derivativeTermMatrixArrayList.add(derivativeTermVectorArrayList);
        return derivativeTermMatrixArrayList;
    }

    @NotNull
    private static ArrayList<ArrayList<Double>> iterateParameters(ArrayList<ArrayList<Double>> featuresDataSetMatrixArrayList,
                                                                  @NotNull ArrayList<ArrayList<Double>> parametersMatrixArrayList,
                                                                  ArrayList<ArrayList<Double>> targetMatrixArrayList) {

        ArrayList<ArrayList<Double>> iteratedParameterMatrixArrayList = new ArrayList<>();
        ArrayList<Double> iteratedParameterVectorArrayList = new ArrayList<>();

        for (int firstCounter = 0; firstCounter < parametersMatrixArrayList.size(); firstCounter = firstCounter + 1) {
            for (int secondCounter = 0; secondCounter < parametersMatrixArrayList.get(0).size(); secondCounter = secondCounter + 1) {
                parametersMatrixArrayList.get(0).set(secondCounter, parametersMatrixArrayList.get(0).get(secondCounter) -
                        findDerivativeTerm(featuresDataSetMatrixArrayList, parametersMatrixArrayList, targetMatrixArrayList).get(0).get(secondCounter) * alpha);
                iteratedParameterVectorArrayList.add(parametersMatrixArrayList.get(0).get(secondCounter));
            }
        }

        iteratedParameterMatrixArrayList.add(iteratedParameterVectorArrayList);
        return iteratedParameterMatrixArrayList;
    }
}