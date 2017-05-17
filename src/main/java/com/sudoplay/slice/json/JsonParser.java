package com.sudoplay.slice.json;

import com.sudoplay.slice.slice.ImageSliceRequest;
import com.sudoplay.slice.slice.ImageSliceRequestFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for parsing a json file into a list of image slice requests.
 */
public class JsonParser {

  private ImageSliceRequestFactory imageSliceRequestFactory;

  public JsonParser(ImageSliceRequestFactory imageSliceRequestFactory) {
    this.imageSliceRequestFactory = imageSliceRequestFactory;
  }

  public List<ImageSliceRequest> getImageSliceRequestList(JSONObject jsonObject) throws JSONException {

    List<ImageSliceRequest> imageSliceRequestList = new ArrayList<>();

    JSONArray sliceJsonArray = jsonObject.getJSONArray("slices");

    for (int i = 0; i < sliceJsonArray.length(); i++) {
      JSONObject slicesArrayEntryJsonObject = sliceJsonArray.getJSONObject(i);

      String sourceImageString = slicesArrayEntryJsonObject.getString("sourceImage");
      String targetPath = "";

      if (slicesArrayEntryJsonObject.has("targetPath")) {
        targetPath = slicesArrayEntryJsonObject.getString("targetPath");
      }

      JSONArray sourceTargetJsonArray = slicesArrayEntryJsonObject.getJSONArray("targetSlices");

      for (int j = 0; j < sourceTargetJsonArray.length(); j++) {
        imageSliceRequestList.add(this.convertJSONObjectToImageSliceRequest(
            sourceTargetJsonArray.getJSONObject(j),
            Paths.get(sourceImageString),
            Paths.get(targetPath)
        ));
      }

    }

    return imageSliceRequestList;

  }

  private ImageSliceRequest convertJSONObjectToImageSliceRequest(
      JSONObject jsonObject,
      Path sourceImagePath,
      Path targetPath
  ) throws JSONException {

    String targetImagePath = jsonObject.getString("target");
    JSONArray position = jsonObject.getJSONArray("position");
    int positionX = position.getInt(0);
    int positionY = position.getInt(1);

    return this.imageSliceRequestFactory.create(
        sourceImagePath,
        targetPath.resolve(targetImagePath),
        16,
        16,
        positionX,
        positionY
    );
  }

}
