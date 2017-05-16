package com.sudoplay.slice.slice;

import com.sudoplay.slice.Logger;
import com.sudoplay.slice.StringLoader;
import com.sudoplay.slice.json.JsonParser;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides a request list by parsing a filename list.
 */
public class ImageSliceRequestListJsonFileAdapter {

  private Logger logger;
  private StringLoader stringLoader;
  private JsonParser jsonParser;

  public ImageSliceRequestListJsonFileAdapter(
      Logger logger,
      StringLoader stringLoader,
      JsonParser jsonParser
  ) {
    this.logger = logger;
    this.stringLoader = stringLoader;
    this.jsonParser = jsonParser;
  }

  public List<ImageSliceRequest> adapt(List<String> jsonFilenameList) {
    List<ImageSliceRequest> imageSliceRequestList = new ArrayList<>();

    for (String filename : jsonFilenameList) {

      String jsonString;

      try {
        jsonString = this.stringLoader.load(Paths.get(filename));

      } catch (IOException e) {
        this.logger.error("Unable to load json string from file: {}", filename);
        e.printStackTrace();
        continue;
      }

      try {
        imageSliceRequestList.addAll(this.jsonParser.getImageSliceRequestList(new JSONObject(jsonString)));

      } catch (JSONException e) {
        this.logger.error("Unable to parse json string from file: {}", filename);
        e.printStackTrace();
        continue;
      }

      this.logger.info("Parsed [{}]", filename);

    }

    return imageSliceRequestList;
  }

}
