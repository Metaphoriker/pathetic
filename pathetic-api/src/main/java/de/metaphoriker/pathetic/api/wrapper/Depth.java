package de.metaphoriker.pathetic.api.wrapper;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Represents the depth of a pathfinding node or element.
 * This class provides methods to get and increment the depth value.
 */
@Getter
@EqualsAndHashCode
@AllArgsConstructor(staticName = "of")
public class Depth {

  private int value;

  /**
   * Increments the depth value by one.
   */
  public void increment() {
    value++;
  }
}
