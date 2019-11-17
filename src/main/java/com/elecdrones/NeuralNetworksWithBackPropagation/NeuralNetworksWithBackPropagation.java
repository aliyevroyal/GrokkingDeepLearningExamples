package com.elecdrones.NeuralNetworksWithBackPropagation;

import com.elecdrones.LinearAlgebra.LinearAlgebra;

import java.util.ArrayList;

public class NeuralNetworksWithBackPropagation {
    private static double alpha = 0.01;

    public static void main(String args[]) {
        LinearAlgebra linearAlgebra = new LinearAlgebra();
        /**
         * Creating and Initializing Weights, Target and Inputs
         */
        ArrayList<ArrayList<Double>> firstLayerWeightArrayList = linearAlgebra.initializeRandomMatrix(3, 4, -1, 1);
        ArrayList<ArrayList<Double>> secondLayerWeightArrayList = linearAlgebra.initializeRandomMatrix(4, 1, -1, 1);
        ArrayList<ArrayList<Double>> targetMatrixArrayList = new ArrayList<>();
        ArrayList<Double> targetVectorArrayList = new ArrayList<>();


        ArrayList<ArrayList<Double>> streetLightsMatrixArrayList = new ArrayList<>();
        ArrayList<Double> streetLightsVectorArrayList = new ArrayList<>();

        streetLightsVectorArrayList.add(1.0);
        streetLightsVectorArrayList.add(0.0);
        streetLightsVectorArrayList.add(1.0);
        streetLightsMatrixArrayList.add(streetLightsVectorArrayList);
        streetLightsVectorArrayList = new ArrayList<>();

        streetLightsVectorArrayList.add(0.0);
        streetLightsVectorArrayList.add(1.0);
        streetLightsVectorArrayList.add(1.0);
        streetLightsMatrixArrayList.add(streetLightsVectorArrayList);
        streetLightsVectorArrayList = new ArrayList<>();

        streetLightsVectorArrayList.add(0.0);
        streetLightsVectorArrayList.add(0.0);
        streetLightsVectorArrayList.add(1.0);
        streetLightsMatrixArrayList.add(streetLightsVectorArrayList);
        streetLightsVectorArrayList = new ArrayList<>();

        streetLightsVectorArrayList.add(1.0);
        streetLightsVectorArrayList.add(1.0);
        streetLightsVectorArrayList.add(1.0);
        streetLightsMatrixArrayList.add(streetLightsVectorArrayList);

        targetVectorArrayList.add(1.0);
        targetMatrixArrayList.add(targetVectorArrayList);
        targetVectorArrayList = new ArrayList<>();
        targetVectorArrayList.add(1.0);
        targetMatrixArrayList.add(targetVectorArrayList);
        targetVectorArrayList = new ArrayList<>();
        targetVectorArrayList.add(0.0);
        targetMatrixArrayList.add(targetVectorArrayList);
        targetVectorArrayList = new ArrayList<>();
        targetVectorArrayList.add(0.0);
        targetMatrixArrayList.add(targetVectorArrayList);
        //.............................................................................................................................................................................................
        NeuralNetworksWithBackPropagation(1000, streetLightsMatrixArrayList, firstLayerWeightArrayList, secondLayerWeightArrayList, targetMatrixArrayList);
    }

    /**
     * Recitifies Elements of Matrix. Returns variable if variable > 0, returns 0 otherwise
     */
    private static ArrayList<ArrayList<Double>> firstRectifyMethod(ArrayList<ArrayList<Double>> matrixArrayList) {
        double indexElement;

        for (ArrayList<Double> doubles : matrixArrayList) {
            for (int counter = 0; counter < doubles.size(); counter = counter + 1) {
                indexElement = doubles.get(counter);
                if (indexElement > 0) {
                    doubles.set(counter, indexElement);
                } else {
                    doubles.set(counter, 0.0);
                }
            }
        }
        return matrixArrayList;
    }

    /**
     * Recitifies Elements of Matrix. Returns 1 if variable > 0, returns 0 otherwise
     */
    private static ArrayList<ArrayList<Double>> secondRectifyMethod(ArrayList<ArrayList<Double>> matrixArrayList) {
        double indexElement;

        for (ArrayList<Double> doubles : matrixArrayList) {
            for (int counter = 0; counter < doubles.size(); counter = counter + 1) {
                indexElement = doubles.get(counter);
                if (indexElement > 0) {
                    doubles.set(counter, 1.0);
                } else {
                    doubles.set(counter, 0.0);
                }
            }
        }
        return matrixArrayList;
    }

    /**
     * Nerual Networks with Backporpagation
     */
    private static void NeuralNetworksWithBackPropagation(int theNumberOfIterations, ArrayList<ArrayList<Double>> inputMatrixArrayList,
                                                          ArrayList<ArrayList<Double>> firstLayerWeightArrayList, ArrayList<ArrayList<Double>> secondLayerWeightArrayList,
                                                          ArrayList<ArrayList<Double>> targetMatrixArrayList) {
        LinearAlgebra linearAlgebra = new LinearAlgebra();

        ArrayList<ArrayList<Double>> zerothLayerNeurons = new ArrayList<>();
        ArrayList<ArrayList<Double>> firstLayerNeurons = new ArrayList<>();
        ArrayList<ArrayList<Double>> secondLayerNeurons = new ArrayList<>();

        double error = 0;

        double secondLayerDelta;
        ArrayList<ArrayList<Double>> firstLayerDelta;

        for (int firstCounter = 0; firstCounter < theNumberOfIterations; firstCounter = firstCounter + 1) {
            error = 0;
            for (int secondCounter = 0; secondCounter < inputMatrixArrayList.size(); secondCounter = secondCounter + 1) {
                zerothLayerNeurons.add(inputMatrixArrayList.get(secondCounter));
                firstLayerNeurons = firstRectifyMethod(linearAlgebra.multiplyMatrices(zerothLayerNeurons, firstLayerWeightArrayList));
                secondLayerNeurons = linearAlgebra.multiplyMatrices(firstLayerNeurons, secondLayerWeightArrayList);

                error += Math.pow((secondLayerNeurons.get(0).get(0) - targetMatrixArrayList.get(secondCounter).get(0)), 2);
                secondLayerDelta = targetMatrixArrayList.get(secondCounter).get(0) - secondLayerNeurons.get(0).get(0);
                firstLayerDelta = linearAlgebra.multiplyElementWise(linearAlgebra.multiplyMatrixByNumber(linearAlgebra.transposeMatrix(secondLayerWeightArrayList), secondLayerDelta), secondRectifyMethod(firstLayerNeurons));

                secondLayerWeightArrayList = linearAlgebra.addMatrices(secondLayerWeightArrayList, linearAlgebra.multiplyMatrixByNumber(linearAlgebra.transposeMatrix(firstLayerNeurons), secondLayerDelta * alpha));
                firstLayerWeightArrayList = linearAlgebra.addMatrices(firstLayerWeightArrayList, linearAlgebra.multiplyMatrices(linearAlgebra.transposeMatrix(zerothLayerNeurons), firstLayerDelta));

                zerothLayerNeurons = new ArrayList<>();
            }
            System.out.println("Iteration No: " + firstCounter + " Error:" + error);
            if (error < 0.04187242588257319) {
                System.out.println("secondLayerWeightArrayList: " + secondLayerWeightArrayList);
                break;
            }
        }

        if (error > 0.04187242588257319) {
            firstLayerWeightArrayList = linearAlgebra.initializeRandomMatrix(3, 4, -1, 1);
            secondLayerWeightArrayList = linearAlgebra.initializeRandomMatrix(4, 1, -1, 1);
            NeuralNetworksWithBackPropagation(theNumberOfIterations, inputMatrixArrayList,
                    firstLayerWeightArrayList, secondLayerWeightArrayList,
                    targetMatrixArrayList);
        }
    }
}