package com.elecdrones.NeuralNetworksWith2LayersExample;

import java.util.ArrayList;

class NeuralNetworksWith2LayersExample {
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

        ArrayList<ArrayList<Double>> weightsMatrixArrayList = new ArrayList<>();
        ArrayList<Double> weightsVectorArrayList = new ArrayList<>();

        weightsVectorArrayList.add(0.1);
        weightsVectorArrayList.add(0.1);
        weightsVectorArrayList.add(-0.3);
        weightsMatrixArrayList.add(weightsVectorArrayList);//Output Hurt?
        weightsVectorArrayList = new ArrayList<>();
        weightsVectorArrayList.add(0.1);
        weightsVectorArrayList.add(0.2);
        weightsVectorArrayList.add(0.0);
        weightsMatrixArrayList.add(weightsVectorArrayList);//Output Win?
        weightsVectorArrayList = new ArrayList<>();
        weightsVectorArrayList.add(0.0);
        weightsVectorArrayList.add(1.3);
        weightsVectorArrayList.add(0.1);
        weightsMatrixArrayList.add(weightsVectorArrayList);//Output Sad?

        for (int firstCounter = 0; firstCounter < theNumberOfToesArrayList.size(); firstCounter = firstCounter + 1) {
            inputsArrayList.add(theNumberOfToesArrayList.get(firstCounter));
            inputsArrayList.add(currentWinningPosibilityArrayList.get(firstCounter));
            inputsArrayList.add(theNumberOfFansArrayList.get(firstCounter));

            System.out.println(predict(inputsArrayList, weightsMatrixArrayList));
            inputsArrayList = new ArrayList<>();
        }
    }

    private static ArrayList<Double> predict(ArrayList<Double> inputsArrayList, ArrayList<ArrayList<Double>> weightsMatrixArrayList) {
        ArrayList<Double> predictionArrayList = new ArrayList<>();
        ArrayList<Double> arrayList = new ArrayList<>();
        double sum = 0;

        for (int firstCounter = 0; firstCounter < weightsMatrixArrayList.size(); firstCounter = firstCounter + 1) {
            for (int secondCounter = 0; secondCounter < inputsArrayList.size(); secondCounter = secondCounter + 1) {
                arrayList.add(inputsArrayList.get(secondCounter) * weightsMatrixArrayList.get(firstCounter).get(secondCounter));
            }
            for (int thirdCounter = 0; thirdCounter < arrayList.size(); thirdCounter = thirdCounter + 1)
                sum = sum + arrayList.get(thirdCounter);

            arrayList = new ArrayList<>();
            predictionArrayList.add(sum);
            sum = 0;
        }

        return predictionArrayList;
    }
}