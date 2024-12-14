package de.metaphoriker.pathetic.api.wrapper;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Depth {

  private int depth;

  public Depth(int depth) {
    this.depth = depth;
  }

  public void increment() {
    depth++;
  }
}
