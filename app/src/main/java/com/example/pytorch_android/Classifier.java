package com.example.pytorch_android;

import android.graphics.Bitmap;
import org.pytorch.Tensor;
import org.pytorch.Module;
import org.pytorch.IValue;
import org.pytorch.torchvision.TensorImageUtils;


public class Classifier {

        Module model;

        public Classifier(String modelPath){

            model = Module.load(modelPath);

        }
        /*
        public void setMeanAndStd(float[] mean, float[] std){

            this.mean = mean;
            this.std = std;
        }
        */
        public Tensor preprocess(Bitmap bitmap, int size){
            //reshape the image to desired size
            bitmap = Bitmap.createScaledBitmap(bitmap,size,size,false);
            return TensorImageUtils.bitmapToFloat32Tensor(bitmap, TensorImageUtils.TORCHVISION_NORM_MEAN_RGB, TensorImageUtils.TORCHVISION_NORM_STD_RGB);

        }

        public int argMax(float[] inputs){

            int maxIndex = -1;
            float maxvalue = -Float.MAX_VALUE;

            for (int i = 0; i < inputs.length; i++){

                if(inputs[i] > maxvalue) {

                    maxIndex = i;
                    maxvalue = inputs[i];
                }

            }


            return maxIndex;
        }

        public String predict(Bitmap bitmap){

            Tensor tensor = preprocess(bitmap,224);

            IValue inputs = IValue.from(tensor);
            Tensor outputs = model.forward(inputs).toTensor();
            float[] scores = outputs.getDataAsFloatArray();

            int classIndex = argMax(scores);

            return Constants.IMAGENET_CLASSES[classIndex];

        }
}
