package de.metaphoriker.model.pathing.result;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import de.metaphoriker.api.pathing.result.Path;
import de.metaphoriker.api.pathing.result.PathState;
import de.metaphoriker.api.pathing.result.PathfinderResult;

@AllArgsConstructor
public class PathfinderResultImpl implements PathfinderResult {

  private final PathState pathState;
  private final Path path;

  @Override
  public boolean successful() {
    return pathState == PathState.FOUND;
  }

  @Override
  public boolean hasFailed() {
    return pathState == PathState.FAILED
        || pathState == PathState.LENGTH_LIMITED
        || pathState == PathState.MAX_ITERATIONS_REACHED;
  }

  @Override
  public boolean hasFallenBack() {
    return pathState == PathState.FALLBACK;
  }

  @Override
  @NonNull
  public PathState getPathState() {
    return this.pathState;
  }

  @NonNull
  @Override
  public Path getPath() {
    return this.path;
  }
}
