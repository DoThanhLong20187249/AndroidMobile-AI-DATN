/*
 * Copyright 2022 Google LLC. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.antispoofingfacerecognition;

import android.content.Context;
import android.nfc.Tag;
import android.os.Environment;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import static com.serenegiant.utils.UIThreadHelper.runOnUiThread;

import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.facemesh.FaceMesh;
import com.google.mlkit.vision.facemesh.FaceMeshDetection;
import com.google.mlkit.vision.facemesh.FaceMeshDetector;
import com.google.mlkit.vision.facemesh.FaceMeshDetectorOptions;
import com.google.mlkit.vision.facemesh.FaceMeshPoint;

import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Selfie Face Detector Demo.
 */
public class FaceMeshDetectorProcessor extends VisionProcessorBase<List<FaceMesh>> {

    private static final String TAG = "SelfieFaceProcessor";

    private final FaceMeshDetector detector;

    private volatile FaceMesh faceMesh;
    public static int saveMeshRequest = 0;
    public static int savingMesh = 0;
    //  public static Context context;
    public static EditText edtName = null;
    public static TextView txtUserName = null;


    public FaceMeshDetectorProcessor(Context context) {
        super(context);
        FaceMeshDetectorOptions.Builder optionsBuilder = new FaceMeshDetectorOptions.Builder();
        if (PreferenceUtils.getFaceMeshUseCase(context) == FaceMeshDetectorOptions.BOUNDING_BOX_ONLY) {
            optionsBuilder.setUseCase(FaceMeshDetectorOptions.BOUNDING_BOX_ONLY);
        }

        detector = FaceMeshDetection.getClient(optionsBuilder.build());
    }

    @Override
    public void stop() {
        super.stop();
        detector.close();
    }

    @Override
    protected Task<List<FaceMesh>> detectInImage(InputImage image) {

        return detector.process(image);
    }

    @Override
    protected void onSuccess(@NonNull List<FaceMesh> faces, @NonNull GraphicOverlay graphicOverlay) {
        for (FaceMesh face : faces) {
            graphicOverlay.add(new FaceMeshGraphic(graphicOverlay, face));
        }

        if (faces != null) {
            if (faces.size() > 0) {
                List<FaceMeshPoint> points = new ArrayList<>();
//                for (FaceMesh face : faces) {
//                    List<FaceMeshPoint> tmpPoints = face.getAllPoints();
//
//                    Log.d("TAG", ' ' + String.valueOf(tmpPoints.size()) + " points");
//                    points.addAll(tmpPoints);
//                }
                points = faces.get(0).getAllPoints();
                FaceMeshGraphic.meshPointList = points;

                // detect nháy mắt
                int check = checkEyes(points.get(159),points.get(145),points.get(386),points.get(374));
                if (check == 1){
                    Log.d(TAG,"Eye is Close: "+String.valueOf(check));

                }else{
                    Log.d(TAG,"Eye is Open: "+String.valueOf(check));
                }






                if (saveMeshRequest == 1) {
                    savingMesh = 1;
                    saveMeshRequest = 0;
                    PersonFace.addUser(edtName.getText().toString());
                    //1
                    PersonFace.addPoint(points.get(423));
                    PersonFace.addPoint(points.get(9));
                    PersonFace.addPoint(points.get(203));
                    //2
                    PersonFace.addPoint(points.get(423));
                    PersonFace.addPoint(points.get(5));
                    PersonFace.addPoint(points.get(203));
                    //3
                    PersonFace.addPoint(points.get(423));
                    PersonFace.addPoint(points.get(6));
                    PersonFace.addPoint(points.get(203));
                    //4
                    PersonFace.addPoint(points.get(423));
                    PersonFace.addPoint(points.get(336));
                    PersonFace.addPoint(points.get(203));
                    //5
                    PersonFace.addPoint(points.get(423));
                    PersonFace.addPoint(points.get(107));
                    PersonFace.addPoint(points.get(203));
                    //6
                    PersonFace.addPoint(points.get(333));
                    PersonFace.addPoint(points.get(107));
                    PersonFace.addPoint(points.get(104));
                    //7
                    PersonFace.addPoint(points.get(377));
                    PersonFace.addPoint(points.get(18));
                    PersonFace.addPoint(points.get(148));
                    //8
                    PersonFace.addPoint(points.get(400));
                    PersonFace.addPoint(points.get(43));
                    PersonFace.addPoint(points.get(176));
                    //9
                    PersonFace.addPoint(points.get(400));
                    PersonFace.addPoint(points.get(273));
                    PersonFace.addPoint(points.get(176));
                    //10
                    PersonFace.addPoint(points.get(57));
                    PersonFace.addPoint(points.get(203));
                    PersonFace.addPoint(points.get(226));
                    //11
                    PersonFace.addPoint(points.get(446));
                    PersonFace.addPoint(points.get(423));
                    PersonFace.addPoint(points.get(287));
                    //12
                    PersonFace.addPoint(points.get(276));
                    PersonFace.addPoint(points.get(334));
                    PersonFace.addPoint(points.get(336));
                    //13
                    PersonFace.addPoint(points.get(107));
                    PersonFace.addPoint(points.get(105));
                    PersonFace.addPoint(points.get(46));
                    //14
                    PersonFace.addPoint(points.get(159));
                    PersonFace.addPoint(points.get(145));
                    PersonFace.addPoint(points.get(130));
                    //15
                    PersonFace.addPoint(points.get(159));
                    PersonFace.addPoint(points.get(145));
                    PersonFace.addPoint(points.get(133));
                    //16
                    PersonFace.addPoint(points.get(386));
                    PersonFace.addPoint(points.get(374));
                    PersonFace.addPoint(points.get(398));
                    //17
                    PersonFace.addPoint(points.get(386));
                    PersonFace.addPoint(points.get(374));
                    PersonFace.addPoint(points.get(263));
                    //18
                    PersonFace.addPoint(points.get(17));
                    PersonFace.addPoint(points.get(95));
                    PersonFace.addPoint(points.get(324));
                    //19
                    PersonFace.addPoint(points.get(405));
                    PersonFace.addPoint(points.get(95));
                    PersonFace.addPoint(points.get(324));
                    //20
                    PersonFace.addPoint(points.get(181));
                    PersonFace.addPoint(points.get(95));
                    PersonFace.addPoint(points.get(324));
                    //21
                    PersonFace.addPoint(points.get(308));
                    PersonFace.addPoint(points.get(0));
                    PersonFace.addPoint(points.get(191));
                    //22
                    PersonFace.addPoint(points.get(308));
                    PersonFace.addPoint(points.get(269));
                    PersonFace.addPoint(points.get(191));
                    //23
                    PersonFace.addPoint(points.get(308));
                    PersonFace.addPoint(points.get(39));
                    PersonFace.addPoint(points.get(191));
                    //24
                    PersonFace.addPoint(points.get(287));
                    PersonFace.addPoint(points.get(2));
                    PersonFace.addPoint(points.get(57));

                    //25
                    PersonFace.addPoint(points.get(323));
                    PersonFace.addPoint(points.get(446));
                    PersonFace.addPoint(points.get(287));
                    //26
                    PersonFace.addPoint(points.get(93));
                    PersonFace.addPoint(points.get(226));
                    PersonFace.addPoint(points.get(57));
                    File localFile = new File(Environment.getExternalStorageDirectory().getPath(), "mySaveFile.txt");
                    Log.i(TAG, "file path: " + localFile.getPath());
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put(PersonFace.getLastUserName(), PersonFace.getLastPoint());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    if (!localFile.exists()){
                        localFile.mkdirs();
                    }
                    try {
                        Writer writer = new FileWriter(localFile);
                        writer.write(jsonObject.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    savingMesh = 0;


                } else if (PersonFace.size() > 0) {
                    for (int userIndex = 0; userIndex < PersonFace.size(); userIndex++) {
                        String name = PersonFace.getUserName(userIndex);
                        int score = PersonFace.countMacht(userIndex, points);
                        if (score >= 24) {
                            int finalUserid = userIndex;
                            name = PersonFace.getUserName(finalUserid);
                            Log.d(TAG, "Name show 1" + String.valueOf(name));

//                            textUser = LivePreviewActivity.txtShowName;
                            txtUserName.setText(name);
                            Log.i(TAG, "run: " + txtUserName.getText());
                            Log.d(TAG, "Name show 2" + String.valueOf(name));

                            Log.d(TAG, "SCORE: MATCHED");
                            Log.d(TAG, "Size: " + String.valueOf(PersonFace.size()));
                            Log.d(TAG, "Size Points " + String.valueOf(PersonFace.getPointsSize()));
                            Log.d(TAG, "Name  " + String.valueOf(name));
                            break;
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    txtUserName.setText("UnKnown!!!");
                                }
                            });


                        }


                    }
                }
            }
        }


    }


    @Override
    protected void onFailure(@NonNull Exception e) {

        Log.e(TAG, "Face detection failed " + e);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txtUserName.setText("NO FACE");
            }
        });
    }

    public static int checkEyes(FaceMeshPoint A, FaceMeshPoint B, FaceMeshPoint C, FaceMeshPoint D){
        double x0 = A.getPosition().getX();
        double y0 = A.getPosition().getY();
        double z0 = A.getPosition().getZ();
        double x1 = B.getPosition().getX();
        double y1 = B.getPosition().getY();
        double z1 = B.getPosition().getZ();
        double x2 = C.getPosition().getX();
        double y2 = C.getPosition().getY();
        double z2 = C.getPosition().getZ();
        double x3 = D.getPosition().getX();
        double y3 = D.getPosition().getY();
        double z3 = D.getPosition().getZ();

        double eyeLeft = Math.sqrt((x1 - x0) * (x1 - x0) + (y1 - y0) * (y1 - y0) );
        double eyeRight =Math.sqrt((x2 - x3) * (x2 - x3) + (y2 - y3) * (y2 - y3) );
        double tmp = eyeRight + eyeLeft;
        Log.d(TAG,"Distance eye Left"+String.valueOf(eyeLeft));
        Log.d(TAG,"Distance eye Right"+String.valueOf(eyeRight));
        if (tmp <= 2 && tmp > 0){
            return  1;

        }else{
            return 0;
        }

    }
}
