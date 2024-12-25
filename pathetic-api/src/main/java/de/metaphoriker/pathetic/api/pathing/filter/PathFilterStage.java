package de.metaphoriker.pathetic.api.pathing.filter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/** A stage for multiple PathFilters. */
public final class PathFilterStage {

  private final Set<PathFilter> filters = new HashSet<>();

  public PathFilterStage(PathFilter... pathFilter) {
    filters.addAll(Arrays.asList(pathFilter));
  }

  /**
   * Filters the given context with all filters in the stage.
   *
   * @param context The context to filter.
   * @return true if the context passes all filters, false otherwise.
   */
  public boolean filter(PathValidationContext context) {
    return filters.stream().allMatch(filter -> filter.filter(context));
  }

  /** Cleans up all filters in the stage. */
  public void cleanup() {
    filters.forEach(PathFilter::cleanup);
  }

  public Set<PathFilter> getFilters() {
    return this.filters;
  }

  public boolean equals(final Object o) {
    if (o == this) return true;
    if (!(o instanceof PathFilterStage)) return false;
    final PathFilterStage other = (PathFilterStage) o;
    final Object this$filters = this.getFilters();
    final Object other$filters = other.getFilters();
    if (this$filters == null ? other$filters != null : !this$filters.equals(other$filters))
      return false;
    return true;
  }

  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    final Object $filters = this.getFilters();
    result = result * PRIME + ($filters == null ? 43 : $filters.hashCode());
    return result;
  }

  public String toString() {
    return "PathFilterStage(filters=" + this.getFilters() + ")";
  }
}
