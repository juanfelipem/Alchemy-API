/**
 * File: TaxonomyAlchemyEntity.java
 * Original Author: Dan Brown <dan@likethecolor.com>
 * Copyright 2012 Dan Brown <Dan Brown <dan@likethecolor.com>>
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
package com.likethecolor.alchemy.api.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * AlchemyAPI provides easy-to-use facilities for Determine the taxonomy of your page
 * Example usage: @see <a href="http://www.alchemyapi.com/api/taxonomy_calls/urls.html">http://www.alchemyapi.com/api/taxonomy_calls/urls.html</a>
 */
public class TaxonomyAlchemyEntity extends AbstractAlchemyEntity {
  private String label;
  
  private String confident;

  public TaxonomyAlchemyEntity() {
    super();
  }

  public TaxonomyAlchemyEntity(final String label, final Double score, final String confident) {
    super(score);
    setConfident(confident);
    setLabel(label);
  }

  public void setLabel(String label) {
    if(label != null) {
        label = label.trim();
    }
    this.label = label;
  }

  public String getLabel() {
    return label;
  }

  public TaxonomyAlchemyEntity clone() {
    return new TaxonomyAlchemyEntity(getLabel(), getScore(), getConfident());
  }

  @Override
  public boolean equals(Object o) {
    if(this == o) {
      return true;
    }
    if(o == null || getClass() != o.getClass()) {
      return false;
    }
    if(!super.equals(o)) {
      return false;
    }

    TaxonomyAlchemyEntity entity = (TaxonomyAlchemyEntity) o;

    if(label != null ? !label.equals(entity.label)
                        : entity.label != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (label != null ? label.hashCode() : 0);
    return result;
  }

  /**
   * @return string representing this object
   *
   * @see #toString(org.apache.commons.lang.builder.ToStringStyle)
   */
  @Override
  public String toString() {
    return toString(ToStringStyle.DEFAULT_STYLE);
  }

  /**
   * @return string representing this object
   *
   * @see ToStringBuilder
   */
  public String toString(final ToStringStyle style) {
    return new ToStringBuilder(this, style)
        .append("label", getLabel())
        .append("score", getScore())
        .append("confident", getConfident())
        .toString();
  }

public String getConfident() {
    return confident;
}

public void setConfident(String confident) {
    this.confident = confident;
}
}
