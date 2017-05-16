package com.sudoplay.slice.json;

import com.sudoplay.slice.slice.ImageSliceRequest;
import com.sudoplay.slice.slice.ImageSliceRequestFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    JSONObject slicesJsonObject = jsonObject.getJSONObject("slices");
    Set<String> sourceImagePathSet = slicesJsonObject.keySet();

    for (String sourceImagePath : sourceImagePathSet) {

      if ("//".equals(sourceImagePath)) {
        continue;
      }

      JSONArray sourceTargetJsonArray = slicesJsonObject.getJSONArray(sourceImagePath);

      for (int i = 0; i < sourceTargetJsonArray.length(); i++) {
        JSONObject sourceTargetJsonObject = sourceTargetJsonArray.getJSONObject(i);
        String targetImagePath = sourceTargetJsonObject.getString("target");
        JSONArray position = sourceTargetJsonObject.getJSONArray("position");
        int positionX = position.getInt(0);
        int positionY = position.getInt(1);

        ImageSliceRequest request = this.imageSliceRequestFactory.create(
            Paths.get(sourceImagePath),
            Paths.get(targetImagePath),
            16,
            16,
            positionX,
            positionY
        );

        imageSliceRequestList.add(request);
      }

    }

    return imageSliceRequestList;
  }

}
