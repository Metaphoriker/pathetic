package org.patheloper.util;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.patheloper.api.pathing.filter.Depending;
import org.patheloper.api.pathing.filter.PathFilter;
import org.patheloper.api.pathing.filter.PathValidationContext;

public class FilterDependencyValidator {

  /**
   * Validates the dependencies of the given filter based on the @Depending annotation. This method
   * checks if the filters specified in the @Depending annotation of the current filter pass their
   * validation.
   *
   * @param filter The filter whose dependencies are to be validated.
   * @param context The context containing the position, parent position, and snapshot manager.
   * @param allFilters The list of all filters applied in the current pathfinding operation.
   * @param cache A map to cache filter results to avoid double execution.
   * @return true if all the dependencies pass their validation, or if there are no dependencies;
   *     false otherwise.
   * @throws IllegalStateException if a required dependency cannot be instantiated.
   */
  public static boolean validateDependencies(
      PathFilter filter,
      PathValidationContext context,
      List<PathFilter> allFilters,
      Map<Class<? extends PathFilter>, Boolean> cache) {

    return validateDependenciesRecursive(filter, context, allFilters, cache, new HashSet<>());
  }

  private static boolean validateDependenciesRecursive(
      PathFilter filter,
      PathValidationContext context,
      List<PathFilter> allFilters,
      Map<Class<? extends PathFilter>, Boolean> cache,
      Set<Class<? extends PathFilter>> seen) {

    if (seen.contains(filter.getClass())) {
      return true; // Avoid circular dependencies
    }

    Depending depending = filter.getClass().getAnnotation(Depending.class);
    if (depending == null) {
      return true;
    }

    seen.add(filter.getClass());

    for (Class<? extends PathFilter> dependency : depending.value()) {
      if (!validateSingleDependency(dependency, context, allFilters, cache, seen)) {
        return false;
      }
    }
    return true;
  }

  private static boolean validateSingleDependency(
      Class<? extends PathFilter> dependency,
      PathValidationContext context,
      List<PathFilter> allFilters,
      Map<Class<? extends PathFilter>, Boolean> cache,
      Set<Class<? extends PathFilter>> seen) {

    PathFilter dependencyFilter = findFilterByClass(dependency, allFilters);
    if (dependencyFilter == null) {
      dependencyFilter = createFilterInstance(dependency);
    }

    if (!validateDependenciesRecursive(dependencyFilter, context, allFilters, cache, seen)) {
      return false;
    }

    PathFilter finalDependencyFilter = dependencyFilter;
    return cache.computeIfAbsent(dependency, k -> finalDependencyFilter.filter(context));
  }

  private static PathFilter findFilterByClass(
      Class<? extends PathFilter> filterClass, List<PathFilter> allFilters) {

    for (PathFilter filter : allFilters) {
      if (filter.getClass().equals(filterClass)) {
        return filter;
      }
    }
    return null;
  }

  private static PathFilter createFilterInstance(Class<? extends PathFilter> filterClass) {
    try {
      return filterClass.getDeclaredConstructor().newInstance();
    } catch (ReflectiveOperationException e) {
      throw new IllegalStateException("Cannot instantiate filter: " + filterClass.getName(), e);
    }
  }
}
