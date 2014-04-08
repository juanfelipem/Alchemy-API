/**
 * File: CategoriesParser.java
 * Original Author: Dan Brown <dan@likethecolor.com>
 * Copyright 2012 Dan Brown <dan@likethecolor.com>
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
package com.likethecolor.alchemy.api.parser.json;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.likethecolor.alchemy.api.entity.Response;
import com.likethecolor.alchemy.api.entity.TaxonomyAlchemyEntity;

public class TaxonomyParser extends AbstractParser<TaxonomyAlchemyEntity> {
  protected void populateResponse(final Response<TaxonomyAlchemyEntity> response) {
    final JSONObject jsonObject = getJSONObject();
    final JSONArray taxonomyItems = getJSONArray(JSONConstants.RANKED_TAXONOMY, jsonObject);

    if(taxonomyItems.length() > 0) {
      JSONObject taxonomyJsonObject;
      TaxonomyAlchemyEntity entity;
      String label;
      Double score;
      String confident;
      for(int i = 0; i < taxonomyItems.length(); i++) {
        taxonomyJsonObject = getTaxonomyItem(taxonomyItems, i);

        label = getString(JSONConstants.RANKED_TAXONOMY_LABEL_KEY, taxonomyJsonObject);
        score = getDouble(JSONConstants.RANKED_TAXONOMY_SCORE_KEY, taxonomyJsonObject);
        confident = getString(JSONConstants.RANKED_TAXONOMY_CONFIDENT_KEY, taxonomyJsonObject);
        if(isValidTaxonomyItem(label, score, confident)) {
          entity = new TaxonomyAlchemyEntity(label, score, confident);
          response.addEntity(entity);
        }
      }
    }
  }

  /**
   * Return a json object from the provided array.  Return an empty object if
   * there is any problems fetching the category data.
   *
   * @param taxonomyItems array of taxonomy data
   * @param index of the object to fetch
   *
   * @return json object from the provided array
   */
  private JSONObject getTaxonomyItem(final JSONArray taxonomyItems, final int index) {
    JSONObject object = new JSONObject();
    try {
      object = (JSONObject) taxonomyItems.get(index);
    }
    catch(JSONException e) {
      e.printStackTrace();
    }
    return object;
  }

  /**
   * Return true if at least one of the values is not null/empty.
   *
   * @param label the label of the taxonomy item
   * @param score the sentiment score
   * @param confident is alchemy confident with the response?
   *
   * @return true if at least one of the values is not null/empty
   */
  private boolean isValidTaxonomyItem(final String label, final Double score, final String confident) {
    return !StringUtils.isBlank(label)
           || score != null || confident != null;
  }
}
