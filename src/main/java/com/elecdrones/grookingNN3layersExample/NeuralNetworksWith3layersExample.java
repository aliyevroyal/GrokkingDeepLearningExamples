package com.elecdrones.grookingNN3layersExample;

import java.util.ArrayList;

class NeuralNetworksWith3layersExample {
    public static void main(String[] args) {
        ArrayList<Double> theNumberOfToesArrayList = new ArrayList<>();
        theNumberOfToesArrayList.add(8.5);
        theNumberOfToesArrayList.add(9.5);
        theNumberOfToesArrayList.add(9.9);
        theNumberOfToesArrayList.add(9.0);

        ArrayList<Double> currentWinningPosibilityArrayList = new ArrayList<>();
        currentWinningPosibilityArrayList.add(0.65);
        currentWinningPosibilityArrayList.add(0.8);
        currentWinningPosibilityArrayList.add(0.8);
        currentWinningPosibilityArrayList.add(0.9);

        ArrayList<Double> theNumberOfFansArrayList = new ArrayList<>();
        theNumberOfFansArrayList.add(1.2);
        theNumberOfFansArrayList.add(1.3);
        theNumberOfFansArrayList.add(0.5);
        theNumberOfFansArrayList.add(1.0);

        ArrayList<Double> inputsArrayList = new ArrayList<>();

        ArrayList<ArrayList<Double>> firstWeightsMatrixArrayList = new ArrayList<>();
        ArrayList<Double> vectorArrayList = new ArrayList<>();

        vectorArrayList.add(0.1);
        vectorArrayList.add(0.2);
        vectorArrayList.add(-0.1);
        firstWeightsMatrixArrayList.add(vectorArrayList);
        vectorArrayList = new ArrayList<>();
        vectorArrayList.add(-0.1);
        vectorArrayList.add(0.1);
        vectorArrayList.add(0.9);
        firstWeightsMatrixArrayList.add(vectorArrayList);
        vectorArrayList = new ArrayList<>();
        vectorArrayList.add(0.1);
        vectorArrayList.add(0.4);
        vectorArrayList.add(0.1);
        firstWeightsMatrixArrayList.add(vectorArrayList);
        vectorArrayList = new ArrayList<>();

        ArrayList<ArrayList<Double>> secondWeightsMatrixArrayList = new ArrayList<>();

        vectorArrayList.add(0.3);
        vectorArrayList.add(1.1);
        vectorArrayList.add(-0.3);
        secondWeightsMatrixArrayList.add(vectorArrayList);
        vectorArrayList = new ArrayList<>();
        vectorArrayList.add(0.1);
        vectorArrayList.add(0.2);
        vectorArrayList.add(0.0);
        secondWeightsMatrixArrayList.add(vectorArrayList);
        vectorArrayList = new ArrayList<>();
        vectorArrayList.add(0.0);
        vectorArrayList.add(1.3);
        vectorArrayList.add(0.1);
        secondWeightsMatrixArrayList.add(vectorArrayList);

        ArrayList<ArrayList> weightsVectorArrayList = new ArrayList<>();
        weightsVectorArrayList.add(firstWeightsMatrixArrayList);
        weightsVectorArrayList.add(secondWeightsMatrixArrayList);

        for (int firstCounter = 0; firstCounter < theNumberOfToesArrayList.size(); firstCounter = firstCounter + 1) {
            inputsArrayList.add(theNumberOfToesArrayList.get(firstCounter));
            inputsArrayList.add(currentWinningPosibilityArrayList.get(firstCounter));
            inputsArrayList.add(theNumberOfFansArrayList.get(firstCounter));

            System.out.println(predict(predict(inputsArrayList, weightsVectorArrayList.get(0)), weightsVectorArrayList.get(1)));

            inputsArrayList = new ArrayList<>();
        }
    }

    private static ArrayList<Double> predict(ArrayList<Double> inputsArrayList, ArrayList<ArrayList<Double>> weightsMatrixArrayList) {
        ArrayList<Double> predictionArrayList = new ArrayList<>();
        ArrayList<Double> arrayList = new ArrayList<>();
        double sum = 0;

        for (int firstCounter = 0; firstCounter < inputsArrayList.size(); firstCounter = firstCounter + 1) {
            for (int secondCounter = 0; secondCounter < weightsMatrixArrayList.get(firstCounter).size(); secondCounter = secondCounter + 1) {
                sum = sum + inputsArrayList.get(secondCounter) * weightsMatrixArrayList.get(firstCounter).get(secondCounter);
            }
            predictionArrayList.add(sum);
            sum = 0;
        }

        return predictionArrayList;
    }
}