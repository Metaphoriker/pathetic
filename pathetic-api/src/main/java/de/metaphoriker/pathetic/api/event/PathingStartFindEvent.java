package de.metaphoriker.pathetic.api.event;

import java.util.List;

import de.metaphoriker.pathetic.api.pathing.configuration.PathfinderConfiguration;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import de.metaphoriker.pathetic.api.pathing.filter.PathFilter;
import de.metaphoriker.pathetic.api.pathing.filter.PathFilterStage;
import de.metaphoriker.pathetic.api.wrapper.PathPosition;

/** An event called when a Pathfinder starts pathing. */
@Getter
@RequiredArgsConstructor
public class PathingStartFindEvent implements PathingEvent {

  @NonNull private final PathPosition start;
  @NonNull private final PathPosition target;
  @NonNull private final List<PathFilter> filters;
  @NonNull private final List<PathFilterStage> filterStages;
  @NonNull private final PathfinderConfiguration pathfinderConfiguration;
}
